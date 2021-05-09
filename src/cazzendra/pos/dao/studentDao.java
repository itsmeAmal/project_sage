/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.student;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public interface studentDao {

    boolean addStudent(student student) throws SQLException;

    ResultSet getAllStudents() throws SQLException;

    ResultSet getStudentByOneAttribute(String attribute, String condition, String value) throws SQLException;

    boolean updateStudent(student student) throws SQLException;

    boolean deleteStudent(int studentId) throws SQLException;

}
