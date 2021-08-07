/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.dao.AttendanceV3Dao;
import cazzendra.pos.daoImpl.AttendanceV3DaoImpl;
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

    private AttendanceV3Dao attendanceV3Dao;

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

    public boolean updatePayment(int paymentId, BigDecimal paymentAmount, Date paymentDate) throws SQLException {
        return new AttendanceV3DaoImpl().updatePayment(paymentId, paymentAmount, paymentDate);
    }

    public static attendanceV3 getPaymentById(int id) throws SQLException {
        attendanceV3 v3 = null;
        ResultSet rset = getByOneAttribute("id", commonConstants.Sql.EQUAL, Integer.toString(id));
        while (rset.next()) {
            v3 = new attendanceV3();
            v3.setDate(rset.getDate("att_date"));
            v3.setId(rset.getInt("id"));
            v3.setLecturerCode(rset.getString("lec_code"));
            v3.setStudentCode(rset.getString("student_code"));
            v3.setDateTime(rset.getTimestamp("date_time"));
            v3.setFee(rset.getBigDecimal("fee"));
            v3.setRemark(rset.getString("remark"));
            v3.setLecturerName(rset.getString("lecturer_name"));
            v3.setStudentName(rset.getString("student_name"));
        }
        return v3;
    }
}
