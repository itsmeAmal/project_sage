/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.lecturer;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nethmi Dasanayaka
 */
public interface lecturerDao {

    boolean addLecturer(lecturer lecturer) throws SQLException;

    ResultSet getAlllecturers() throws SQLException;

    ResultSet getLecturerByOneAttribute(String attribute, String condition, String value) throws SQLException;

    boolean updateLecturer(lecturer lecturer) throws SQLException;

    boolean deleteLecturer(int lecturerId) throws SQLException;
}
