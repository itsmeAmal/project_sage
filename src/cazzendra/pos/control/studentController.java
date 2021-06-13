/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.daoImpl.studentDaoImpl;
import cazzendra.pos.model.student;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amal
 */
public class studentController {

    public static boolean addStudent(String name, String email1, String email2,
            String regNo, String contactNo, String detail,
            String guardianContactNo, String gender, String currentAddress, Date dob, String grade) throws SQLException {
        student student = new student();
        student.setName(name);
        student.setEmail1(email1);
        student.setEmail2(email2);
        student.setRegNo(regNo);
        student.setContactNo(contactNo);
        student.setGuardianName(detail);
        student.setGuardianContact(guardianContactNo);
        student.setGender(gender);
        student.setCurrentAddress(currentAddress);
        student.setStatus(student.ACTIVE_STUDENT);
        student.setDob(dob);
        student.setGrade(grade);
        return new studentDaoImpl().addStudent(student);
    }

    public static ResultSet getAllStudents() throws SQLException {
        return new studentDaoImpl().getAllStudents();
    }

    public static ResultSet getStudentResultSetByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new studentDaoImpl().getStudentByOneAttribute(attribute, condition, value);
    }

    public static boolean getStudentByStudentCode(String studentId) throws SQLException {
        boolean status = false;
        student student = getStudentByStudentId(Integer.valueOf(studentId));
        if (student == null) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    public static boolean updateStudentDetails(student student) throws SQLException {
        return new studentDaoImpl().updateStudent(student);
    }

    public static student getStudentByStudentId(int studentId) throws SQLException {
        ResultSet rset = getStudentResultSetByOneAttribute("student_id", commonConstants.Sql.EQUAL, Integer.toString(studentId));
        student student = null;
        while (rset.next()) {
            student = new student();
            student.setId(rset.getInt("student_id"));
            student.setName(rset.getString("student_name"));
            student.setEmail1(rset.getString("student_email_1"));
            student.setEmail2(rset.getString("student_email_2"));
            student.setRegNo(rset.getString("student_reg_no"));
            student.setContactNo(rset.getString("student_contact_no"));
            student.setDetail(rset.getString("student_detail"));
            student.setStatus(rset.getInt("student_status"));
            student.setGender(rset.getString("student_batch_id"));
            student.setGuardianContact(rset.getString("student_group_id"));
            student.setCurrentAddress(rset.getString("student_special_id"));
            student.setDob(rset.getDate("student_dob"));
            student.setGuardianName(rset.getString("student_guardian_name"));
            student.setGrade(rset.getString("student_grade"));
        }
        return student;
    }

    public static ResultSet getAllStudentsWithOtherJoinDetails() throws SQLException {
        return new studentDaoImpl().getAllStudentsWithOtherJoinDetails();
    }

    public static ResultSet GetStudentByMoreAttributes(ArrayList<String[]> AttributeCOnditionValueList, String Operator) throws SQLException {
        return new studentDaoImpl().getStudentsByMoreAttributes(AttributeCOnditionValueList, Operator);
    }

}
