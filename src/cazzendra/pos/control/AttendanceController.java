/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.core.Validations;
import cazzendra.pos.daoImpl.AttendanceDaoImpl;
import cazzendra.pos.model.Attendance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author personal
 */
public class AttendanceController {

    public static void AddAttendance(String paymentAmount, int IsMonthlyPayment, int LecturerId,
            String LecturerName, String PaidForMonth, String PaidForYear, int StudentId,
            String StudentName, int SubjectId, String SubjectName) throws SQLException {
        Attendance Attendance = new Attendance();
        Attendance.setDailyPaymentAmount(Validations.getBigDecimalOrZeroFromString(paymentAmount));
//        Attendance.setDate(commonController.getCurrentJavaSqlTime()); 
        Attendance.setIsMonthlyPayment(IsMonthlyPayment);
        Attendance.setLecturerId(LecturerId);
        Attendance.setLecturerName(LecturerName);
        Attendance.setPaidForMonth(PaidForMonth);
        Attendance.setPaidForYear(PaidForYear);
        Attendance.setStudentId(StudentId);
        Attendance.setStudentName(StudentName);
        Attendance.setSubjectId(SubjectId);
        Attendance.setSubjectName(SubjectName);
        new AttendanceDaoImpl().AddAttendance(Attendance);
    }

    public static boolean IsPaidForthisMonth(int StudentId, String Month, String Year, String Subject, String PaymentStatus) throws SQLException {
        ArrayList<String[]> AttributeConditionValueList = new ArrayList<>();
        /*
        attendance_id, attendance_date, attendance_student_id, attendance_student_name, attendance_subject_id, 
        attendance_subject_name, attendance_lecturer_id, attendance_lecturer_name, attendance_day_payment, 
        attendance_is_monthly_pay, attendance_paid_for_month, attendance_paid_for_year
         */

        boolean Status = false;

        String[] ACV1 = {"attendance_student_id", commonConstants.Sql.EQUAL, Integer.toString(StudentId)};
        AttributeConditionValueList.add(ACV1);

        String[] ACV2 = {"attendance_paid_for_month", commonConstants.Sql.EQUAL, Month};
        AttributeConditionValueList.add(ACV2);

        String[] ACV3 = {"attendance_paid_for_year", commonConstants.Sql.EQUAL, Year};
        AttributeConditionValueList.add(ACV3);

        String[] ACV4 = {"attendance_subject_name", commonConstants.Sql.EQUAL, Subject};
        AttributeConditionValueList.add(ACV4);

        String[] ACV5 = {"attendance_is_monthly_pay", commonConstants.Sql.EQUAL, PaymentStatus};
        AttributeConditionValueList.add(ACV5);

        ResultSet Rset = new AttendanceDaoImpl().GetattendanceByMoreAttributes(AttributeConditionValueList, commonConstants.Sql.AND);
        
        while (Rset.next()) {            
              Status = true;
        }
        
//        if (Rset.equals(null)) {
//            Status = false;
//        } else {
//            Status = true;
//        }

        return Status;

    }

}
