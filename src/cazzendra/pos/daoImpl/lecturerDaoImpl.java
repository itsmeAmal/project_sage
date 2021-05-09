/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.lecturerDao;
import cazzendra.pos.model.lecturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amal
 */
public class lecturerDaoImpl implements lecturerDao {

    private String selectQuery = "select lecturer_id, lecturer_title, lecturer_name, lecturer_email, lecturer_contact_no, lecturer_detail, lecturer_status from lecturer";

    @Override
    public boolean addLecturer(lecturer lecturer) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("insert into lecturer(lecturer_title, lecturer_name, lecturer_email, lecturer_contact_no, "
                + " lecturer_detail, lecturer_status) values (?,?,?,?,?,?)");
        ps.setString(1, lecturer.getTitle());
        ps.setString(2, lecturer.getName());
        ps.setString(3, lecturer.getEmail());
        ps.setString(4, lecturer.getContactNo());
        ps.setString(5, lecturer.getDetail());
        ps.setInt(6, lecturer.getStatus());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public ResultSet getAlllecturers() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery);
    }

    @Override
    public ResultSet getLecturerByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(selectQuery, attribute, condition, value);
    }

    @Override
    public boolean updateLecturer(lecturer lecturer) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("update lecturer set lecturer_title=?, lecturer_name=?, lecturer_email=?, lecturer_contact_no=?,"
                + " lecturer_detail=?, lecturer_status=? where lecturer_id=?");
        ps.setString(1, lecturer.getTitle());
        ps.setString(2, lecturer.getName());
        ps.setString(3, lecturer.getEmail());
        ps.setString(4, lecturer.getContactNo());
        ps.setString(5, lecturer.getDetail());
        ps.setInt(6, lecturer.getStatus());
        ps.setInt(7, lecturer.getId());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public boolean deleteLecturer(int lecturerId) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("delete from lecturer where lecturer_id=?");
        ps.setInt(1, lecturerId);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public ResultSet getLecturerByMoreAttributes(ArrayList<String[]> attributeConditionValueList, String operator) throws SQLException {
        return new CommonDaoImpl().getResultByAttributesWithJoinOperator(selectQuery, attributeConditionValueList, operator);
    }

}
