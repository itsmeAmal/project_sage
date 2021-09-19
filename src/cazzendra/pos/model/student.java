/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.model;

import java.sql.Date;

/**
 *
 * @author Nethmi Dasanayaka
 */
public class student {

    public static int ACTIVE_STUDENT = 1;
    public static int INACTIVE_STUDENT = 0;

    private int id;
    private String name;
    private String email1;
    private String email2;
    private String regNo;
    private String contactNo;
    private String detail;
    private int status;
    private String gender;
    private String guardianContact;
    private String currentAddress;
    private Date dob;
    private String guardianName;
    private String grade;
    private String isInstituteStudent;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email1
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * @param email1 the email1 to set
     */
    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    /**
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return the regNo
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * @param regNo the regNo to set
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the guardianContact
     */
    public String getGuardianContact() {
        return guardianContact;
    }

    /**
     * @param guardianContact the guardianContact to set
     */
    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    /**
     * @return the currentAddress
     */
    public String getCurrentAddress() {
        return currentAddress;
    }

    /**
     * @param currentAddress the currentAddress to set
     */
    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the guardianName
     */
    public String getGuardianName() {
        return guardianName;
    }

    /**
     * @param guardianName the guardianName to set
     */
    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the isInstituteStudent
     */
    public String getIsInstituteStudent() {
        return isInstituteStudent;
    }

    /**
     * @param isInstituteStudent the isInstituteStudent to set
     */
    public void setIsInstituteStudent(String isInstituteStudent) {
        this.isInstituteStudent = isInstituteStudent;
    }

    /**
     * @return the batchId
     */
}
