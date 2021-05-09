/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author personal
 */
public class Attendance {

    /*
    attendance_id, attendance_date, attendance_student_id, attendance_student_name, 
    attendance_subject_id, attendance_subject_name, attendance_lecturer_id,
    attendance_lecturer_name, attendance_day_payment, attendance_is_monthly_pay, 
    attendance_paid_for_month, attendance_paid_for_year
     */
    private int Id;
    private Timestamp Date;
    private int StudentId;
    private String StudentName;
    private int SubjectId;
    private String SubjectName;
    private int LecturerId;
    private String LecturerName;
    private BigDecimal DailyPaymentAmount;
    private int IsMonthlyPayment;
    private String PaidForMonth;
    private String paidForYear;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Date
     */
    public Timestamp getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    /**
     * @return the StudentId
     */
    public int getStudentId() {
        return StudentId;
    }

    /**
     * @param StudentId the StudentId to set
     */
    public void setStudentId(int StudentId) {
        this.StudentId = StudentId;
    }

    /**
     * @return the StudentName
     */
    public String getStudentName() {
        return StudentName;
    }

    /**
     * @param StudentName the StudentName to set
     */
    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    /**
     * @return the SubjectId
     */
    public int getSubjectId() {
        return SubjectId;
    }

    /**
     * @param SubjectId the SubjectId to set
     */
    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    /**
     * @return the SubjectName
     */
    public String getSubjectName() {
        return SubjectName;
    }

    /**
     * @param SubjectName the SubjectName to set
     */
    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    /**
     * @return the LecturerId
     */
    public int getLecturerId() {
        return LecturerId;
    }

    /**
     * @param LecturerId the LecturerId to set
     */
    public void setLecturerId(int LecturerId) {
        this.LecturerId = LecturerId;
    }

    /**
     * @return the LecturerName
     */
    public String getLecturerName() {
        return LecturerName;
    }

    /**
     * @param LecturerName the LecturerName to set
     */
    public void setLecturerName(String LecturerName) {
        this.LecturerName = LecturerName;
    }

    /**
     * @return the DailyPaymentAmount
     */
    public BigDecimal getDailyPaymentAmount() {
        return DailyPaymentAmount;
    }

    /**
     * @param DailyPaymentAmount the DailyPaymentAmount to set
     */
    public void setDailyPaymentAmount(BigDecimal DailyPaymentAmount) {
        this.DailyPaymentAmount = DailyPaymentAmount;
    }

    /**
     * @return the IsMonthlyPayment
     */
    public int getIsMonthlyPayment() {
        return IsMonthlyPayment;
    }

    /**
     * @param IsMonthlyPayment the IsMonthlyPayment to set
     */
    public void setIsMonthlyPayment(int IsMonthlyPayment) {
        this.IsMonthlyPayment = IsMonthlyPayment;
    }

    /**
     * @return the PaidForMonth
     */
    public String getPaidForMonth() {
        return PaidForMonth;
    }

    /**
     * @param PaidForMonth the PaidForMonth to set
     */
    public void setPaidForMonth(String PaidForMonth) {
        this.PaidForMonth = PaidForMonth;
    }

    /**
     * @return the paidForYear
     */
    public String getPaidForYear() {
        return paidForYear;
    }

    /**
     * @param paidForYear the paidForYear to set
     */
    public void setPaidForYear(String paidForYear) {
        this.paidForYear = paidForYear;
    }

}
