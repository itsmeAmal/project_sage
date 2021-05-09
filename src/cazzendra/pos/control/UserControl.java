/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.daoImpl.UserDaoImpl;
import cazzendra.pos.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public class UserControl {

    public static void addUser(String userName, String password, String type) throws SQLException {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setType(type);
        new UserDaoImpl().addUser(user);
    }

    public static ResultSet getAllUsers() throws SQLException {
        return new UserDaoImpl().getAllUsers();
    }

    public static void updateUser(String password) throws SQLException {
        User user = new User();
        user.setPassword(password);
        new UserDaoImpl().updateUser(user);
    }

    public static boolean getUserByUnameAndPw(String uname, String pw) throws SQLException {
        return new UserDaoImpl().getUserByUnameAndPw(uname, pw);
    }

    public static User getUserByUserNameAndPw(String uname, String pw) throws SQLException {
        return new UserDaoImpl().getUserByUserNameAndPw(uname, pw);
    }

    public static User getUserByUserId(int userId) throws SQLException {
        ResultSet rset = new UserDaoImpl().getUserByOneAttribute("user_id", CommonConstants.sql.EQUAL, Integer.toString(userId));
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

}
