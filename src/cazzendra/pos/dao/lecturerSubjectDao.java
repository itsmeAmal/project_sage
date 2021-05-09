/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.lecturerSubject;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public interface lecturerSubjectDao {

    boolean AddLecturerSubject(lecturerSubject subject) throws SQLException;

    boolean updateLectureSubject(lecturerSubject subject) throws SQLException;

    ResultSet getAllLectureSubjects() throws SQLException;

    ResultSet getLectureSubjectByOneAttribute(String attribute, String condition, String value) throws SQLException;

    boolean deleteLectureSubject(int lectureSubjectId) throws SQLException;

}
