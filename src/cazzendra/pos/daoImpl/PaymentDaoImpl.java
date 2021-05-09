/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.PaymentDao;
import cazzendra.pos.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author personal
 */
public class PaymentDaoImpl implements PaymentDao {

    private String SelectQuery = "select payment_id, payment_student_id, payment_is_monthly_payment, "
            + " payment_amount, payment_for_year, payment_for_month, payment_date, payment_class, "
            + " payment_status, payment_detail, payment_subject_id, payment_subject_name,"
            + " payment_lecturer_id, payment_lecturer_name from payment";

    @Override
    public void AddPayment(Payment Payment) throws SQLException {
        Connection Con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement Ps = Con.prepareStatement("insert into payment(payment_student_id, payment_is_monthly_payment, "
                + "payment_amount, payment_for_year, payment_for_month, payment_date, payment_class, "
                + "payment_status, payment_detail, payment_subject_id, payment_subject_name, "
                + "payment_lecturer_id, payment_lecturer_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Ps.setInt(1, Payment.getStudentId());
        Ps.setInt(2, Payment.getIsMonthlyPayment());
        Ps.setBigDecimal(3, Payment.getPaymentAmount());
        Ps.setString(4, Payment.getPaymentForYear());
        Ps.setString(5, Payment.getPaymentForMonth());
        Ps.setTimestamp(6, Payment.getPaymentDate());
        Ps.setString(7, Payment.getPaymentForClass());
        Ps.setInt(8, Payment.getStatus());
        Ps.setString(9, Payment.getDetail());
        Ps.setInt(10, Payment.getSubjectId());
        Ps.setString(11, Payment.getSubjectName());
        Ps.setInt(12, Payment.getLecturerId());
        Ps.setString(13, Payment.getLecturerName());
        Ps.executeUpdate();
        Ps.close();
    }

    @Override
    public ResultSet GetAllPayments() throws SQLException {
        return new CommonDaoImpl().getAllRecords(SelectQuery);
    }

    @Override
    public ResultSet GetPaymentByAttribute(String Attribute, String Condition, String Value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(SelectQuery, Attribute, Condition, Value);
    }

    @Override
    public ResultSet GetPaymentByMoreAttributes(ArrayList<String[]> AttributeCOnditionValueList, String Operator) throws SQLException {
        return new CommonDaoImpl().getResultByAttributesWithJoinOperator(SelectQuery, AttributeCOnditionValueList, Operator);
    }

}
