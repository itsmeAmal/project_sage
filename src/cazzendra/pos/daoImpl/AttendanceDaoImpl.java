/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.AttendanceDao;
import cazzendra.pos.model.Attendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author personal
 */
public class AttendanceDaoImpl implements AttendanceDao {

    private String SelectQuery = "select attendance_id, date(attendance_date) as attendance_date, attendance_student_id, "
            + " attendance_student_name, attendance_subject_id, attendance_subject_name, "
            + " attendance_lecturer_id, attendance_lecturer_name, attendance_day_payment, "
            + " attendance_is_monthly_pay, attendance_paid_for_month, attendance_paid_for_year, "
            + " if(attendance_is_monthly_pay=1,'Yes',(if(attendance_is_monthly_pay=2,'FREE CARD', 'No'))) as is_monthly_payment from attendance";

    @Override
    public void AddAttendance(Attendance Attendance) throws SQLException {
        Connection Con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement Ps = Con.prepareStatement("insert into attendance (attendance_student_id, "
                + " attendance_student_name, attendance_subject_id, attendance_subject_name, "
                + " attendance_lecturer_id, attendance_lecturer_name, attendance_day_payment, attendance_is_monthly_pay, "
                + " attendance_paid_for_month, attendance_paid_for_year) values (?,?,?,?,?,?,?,?,?,?)");
        Ps.setInt(1, Attendance.getStudentId());
        Ps.setString(2, Attendance.getStudentName());
        Ps.setInt(3, Attendance.getSubjectId());
        Ps.setString(4, Attendance.getSubjectName());
        Ps.setInt(5, Attendance.getLecturerId());
        Ps.setString(6, Attendance.getLecturerName());
        Ps.setBigDecimal(7, Attendance.getDailyPaymentAmount());
        Ps.setInt(8, Attendance.getIsMonthlyPayment());
        Ps.setString(9, Attendance.getPaidForMonth());
        Ps.setString(10, Attendance.getPaidForYear());
        Ps.executeUpdate();
        Ps.close();
    }

    @Override
    public ResultSet GetAllAttendanceRecord() throws SQLException {
        return new CommonDaoImpl().getAllRecords(SelectQuery);
    }

    @Override
    public ResultSet GetAttendanceByAttribute(String Attribute, String Condition, String Value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(SelectQuery, Attribute, Condition, Value);
    }

    public ResultSet GetattendanceByMoreAttributes(ArrayList<String[]> AttributeCOnditionValueList, String Operator) throws SQLException {
        return new CommonDaoImpl().getResultByAttributesWithJoinOperator(SelectQuery, AttributeCOnditionValueList, Operator);
    }

}
