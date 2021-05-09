/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.UserDao;
import cazzendra.pos.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public class UserDaoImpl implements UserDao {

    private String selectQuery = "select user_id, user_name, user_pw, user_type from user";

    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = connection.prepareStatement("insert into user(user_name, user_pw, user_type) values (?,?,?)");
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getType());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public ResultSet getAllUsers() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("update user set user_pw=? where user_id=?");
        ps.setString(1, user.getPassword());
        ps.setInt(2, user.getId());
        ps.executeUpdate();
        ps.close();
    }

    public boolean getUserByUnameAndPw(String uname, String pw) throws SQLException {
        boolean status = false;
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("select user_id, user_name, "
                + " user_pw from user where user_name=? and user_pw=?");
        ps.setString(1, uname);
        ps.setString(2, pw);
        ResultSet rset = ps.executeQuery();
        if (rset.next()) {
            status = true;
        }
        return status;
    }

    public User getUserByUserNameAndPw(String uname, String pw) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("select user_id, user_name, "
                + " user_pw, user_type from user where user_name=? and user_pw=?");
        ps.setString(1, uname);
        ps.setString(2, pw);
        ResultSet rset = ps.executeQuery();
        User user = null;
        while (rset.next()) {
            user = new User();
            user.setId(rset.getInt("user_id"));
            user.setUserName(rset.getString("user_name"));
            user.setPassword(rset.getString("user_pw"));
            user.setType(rset.getString("user_type"));
        }
        return user;
    }

    public ResultSet getUserByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(selectQuery, attribute, condition, value);
    }

}
