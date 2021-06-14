/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.daoImpl.AttendanceV3DaoImpl;
import cazzendra.pos.daoImpl.CommonDaoImpl;
import cazzendra.pos.model.attendanceV3;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author root_user
 */
public class AttendanceV3Controller {

    public static boolean addAttendance(String lecturerCode, String studentCode, Date date,
            BigDecimal fee, String remark, String lecturerName, String studentName) throws SQLException {
        attendanceV3 attendanceV3 = new attendanceV3();
        attendanceV3.setLecturerCode(lecturerCode);
        attendanceV3.setStudentCode(studentCode);
        attendanceV3.setDate(date);
        attendanceV3.setFee(fee);
        attendanceV3.setRemark(remark);
        attendanceV3.setLecturerName(lecturerName);
        attendanceV3.setStudentName(studentName);
        return new AttendanceV3DaoImpl().addAttendanceV3(attendanceV3);
    }

    public static ResultSet getAll() throws SQLException {
        return new AttendanceV3DaoImpl().getAllV3Records();
    }

    public static ResultSet getByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new AttendanceV3DaoImpl().getAttendanceV3ByAttribute(attribute, condition, value);
    }

    public static ResultSet getByMoreAttributes(ArrayList<String[]> attributeConditionValueList, String Operator) throws SQLException {
        return new AttendanceV3DaoImpl().getByMoreAttributes(attributeConditionValueList, Operator);
    }

}
