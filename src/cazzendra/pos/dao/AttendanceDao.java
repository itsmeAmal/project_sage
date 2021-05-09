/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.Attendance;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author personal
 */
public interface AttendanceDao {

    void AddAttendance(Attendance Attendance) throws SQLException;

    ResultSet GetAllAttendanceRecord() throws SQLException;

    ResultSet GetAttendanceByAttribute(String Attribute, String Condition, String Value) throws SQLException;

}
