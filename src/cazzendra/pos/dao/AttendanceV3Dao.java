/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.attendanceV3;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author root_user
 */
public interface AttendanceV3Dao {

    public boolean addAttendanceV3(attendanceV3 attendanceV3Dao) throws SQLException;

    public boolean updateAttendanceV3(attendanceV3 attendanceV3Dao) throws SQLException;

    public boolean deleteAttendanceV3(int id) throws SQLException;

    public ResultSet getAttendanceV3ByAttribute(String attribute, String condition, String value) throws SQLException;

    public ResultSet getAllV3Records() throws SQLException;
    
    public boolean updatePayment(int paymentId, BigDecimal paymentAmount)throws SQLException;

}
