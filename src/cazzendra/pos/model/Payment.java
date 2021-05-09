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
public class Payment {

    private int id;
    private int studentId;
    private int isMonthlyPayment;
    private BigDecimal paymentAmount;
    private String paymentForYear;
    private String paymentForMonth;
    private Timestamp paymentDate;
    private String paymentForClass;
    private int status;
    private String detail;
    private int SubjectId;
    private String SubjectName;
    private int LecturerId;
    private String LecturerName;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the isMonthlyPayment
     */
    public int getIsMonthlyPayment() {
        return isMonthlyPayment;
    }

    /**
     * @param isMonthlyPayment the isMonthlyPayment to set
     */
    public void setIsMonthlyPayment(int isMonthlyPayment) {
        this.isMonthlyPayment = isMonthlyPayment;
    }

    /**
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * @return the paymentForYear
     */
    public String getPaymentForYear() {
        return paymentForYear;
    }

    /**
     * @param paymentForYear the paymentForYear to set
     */
    public void setPaymentForYear(String paymentForYear) {
        this.paymentForYear = paymentForYear;
    }

    /**
     * @return the paymentForMonth
     */
    public String getPaymentForMonth() {
        return paymentForMonth;
    }

    /**
     * @param paymentForMonth the paymentForMonth to set
     */
    public void setPaymentForMonth(String paymentForMonth) {
        this.paymentForMonth = paymentForMonth;
    }

    /**
     * @return the paymentDate
     */
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the paymentForClass
     */
    public String getPaymentForClass() {
        return paymentForClass;
    }

    /**
     * @param paymentForClass the paymentForClass to set
     */
    public void setPaymentForClass(String paymentForClass) {
        this.paymentForClass = paymentForClass;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
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

}
