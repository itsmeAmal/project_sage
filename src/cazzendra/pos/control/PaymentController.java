/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.daoImpl.PaymentDaoImpl;
import cazzendra.pos.model.Payment;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author personal
 */
public class PaymentController {

    public static void AddPayment(int StudentId, String PaymentForMonth, String PaymentForYear,
            int SubjectId, String Grade, String Detail, int IsMonthlyPayment, String SubjectName,
            BigDecimal Amount, int LecturerId, String LecturerName, int Status) throws SQLException {
        Payment Payment = new Payment();
        Payment.setDetail(Detail);
        Payment.setIsMonthlyPayment(IsMonthlyPayment);
        Payment.setPaymentForMonth(PaymentForMonth);
        Payment.setPaymentForYear(PaymentForYear);
        Payment.setSubjectId(SubjectId);
        Payment.setSubjectName(SubjectName);
        Payment.setPaymentForClass(Grade);
        Payment.setStudentId(StudentId);
        Payment.setPaymentAmount(Amount);
        Payment.setLecturerId(LecturerId);
        Payment.setLecturerName(LecturerName);
        Payment.setStatus(Status);
        new PaymentDaoImpl().AddPayment(Payment);
    }

    public static ResultSet GetPaymentByMoreAttributes(ArrayList<String[]> attributeConditionValueList, String Operator) throws SQLException {
        return new PaymentDaoImpl().GetPaymentByMoreAttributes(attributeConditionValueList, Operator);
    }

    public static boolean IsMonthlyPaymentCleared(int studentId, String year, String month, String grade,
            int status, int subjectId, int lecturerId) throws SQLException {
        ArrayList<String[]> attributeCOnditionValueList = new ArrayList<>();

        String[] ACV1 = {"payment_student_id", commonConstants.Sql.EQUAL, Integer.toString(studentId)};
        attributeCOnditionValueList.add(ACV1);

        String[] ACV2 = {"payment_for_year", commonConstants.Sql.EQUAL, year};
        attributeCOnditionValueList.add(ACV2);

        String[] ACV3 = {"payment_for_month", commonConstants.Sql.EQUAL, month};
        attributeCOnditionValueList.add(ACV3);

        String[] ACV4 = {"payment_class", commonConstants.Sql.EQUAL, grade};
        attributeCOnditionValueList.add(ACV4);

        String[] ACV5 = {"payment_status", commonConstants.Sql.EQUAL, Integer.toString(status)};
        attributeCOnditionValueList.add(ACV5);

        String[] ACV6 = {"payment_subject_id", commonConstants.Sql.EQUAL, Integer.toString(subjectId)};
        attributeCOnditionValueList.add(ACV6);

        String[] ACV7 = {"payment_lecturer_id", commonConstants.Sql.EQUAL, Integer.toString(lecturerId)};
        attributeCOnditionValueList.add(ACV7);

        boolean isExist = false;

        ResultSet rset = GetPaymentByMoreAttributes(attributeCOnditionValueList, commonConstants.Sql.AND);

        while (rset.next()) {
            isExist = true;
        }

        return isExist;

    }
    
    public static void PaymentByMultipleAttributes(String year, String month, int lecturerId){
    
        
        
        
    }
    
}
