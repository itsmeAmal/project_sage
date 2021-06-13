/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.AttendanceV3Dao;
import cazzendra.pos.model.attendanceV3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root_user
 */
public class AttendanceV3DaoImpl implements AttendanceV3Dao {

    private String selectQuery = "select id, lec_code, student_code, date, date_time, fee, remark, status,"
            + " lecturer_name, student_name from attendance_v3";

    @Override
    public boolean addAttendanceV3(attendanceV3 attendanceV3) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("insert into attendance_v3(lec_code, student_code, date, fee, "
                + " remark, lecturer_name, student_name)"
                + " values (?,?,?,?,?,?,?)");
        ps.setString(1, attendanceV3.getLecturerCode());
        ps.setString(2, attendanceV3.getStudentCode());
        ps.setDate(3, attendanceV3.getDate());
        ps.setBigDecimal(4, attendanceV3.getFee());
        ps.setString(5, attendanceV3.getRemark());
        ps.setString(6, attendanceV3.getLecturerName());
        ps.setString(7, attendanceV3.getStudentName());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public boolean updateAttendanceV3(attendanceV3 attendanceV3) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAttendanceV3(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getAttendanceV3ByAttribute(String attribute, String condition, String value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(selectQuery, attribute, condition, value);
    }

    @Override
    public ResultSet getAllV3Records() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery);
    }

}