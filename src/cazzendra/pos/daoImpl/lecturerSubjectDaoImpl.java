/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.lecturerSubjectDao;
import cazzendra.pos.model.lecturerSubject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public class lecturerSubjectDaoImpl implements lecturerSubjectDao {

    private String selectQuery = "select lecturer_subject_id, lecturer_subject_lecturer_id, lecturer_subject_subject_id,"
            + " lecturer_subject_detail_1, lecturer_subject_detail_2, lecturer_subject_detail_3, lecturer_subject_satus_1,"
            + " lecturer_subject_satus_2, lecturer_subject_satus_3 from lecturer_subject";

    @Override
    public boolean AddLecturerSubject(lecturerSubject subject) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("insert into lecturer_subject(lecturer_subject_lecturer_id, "
                + " lecturer_subject_subject_id, lecturer_subject_detail_1, lecturer_subject_detail_2, lecturer_subject_detail_3, "
                + " lecturer_subject_satus_1, lecturer_subject_satus_2, lecturer_subject_satus_3) values (?,?,?,?,?,?,?,?)");
        ps.setInt(1, subject.getLecturerId());
        ps.setInt(2, subject.getSubjectId());
        ps.setString(3, subject.getDetail1());
        ps.setString(4, subject.getDetail2());
        ps.setString(5, subject.getDetail3());
        ps.setInt(6, subject.getStatus1());
        ps.setInt(7, subject.getStatus2());
        ps.setInt(8, subject.getStatus3());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public boolean updateLectureSubject(lecturerSubject subject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getAllLectureSubjects() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery);
    }

    @Override
    public ResultSet getLectureSubjectByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(selectQuery, attribute, condition, value);
    }

    @Override
    public boolean deleteLectureSubject(int lectureSubjectId) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("delete from lecturer_subject where lecturer_subject_id=?");
        ps.setInt(1, lectureSubjectId);
        ps.executeUpdate();
        ps.close();
        return true;
    }
}
