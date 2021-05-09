/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.daoImpl.subjectDaoImpl;
import cazzendra.pos.model.subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author personal
 */
public class subjectController {

    public static boolean addSubject(String courseLevel, String detail,
            String moduleCode, String subjectName, String semester, int courseId) throws SQLException {
        subject subject = new subject();
        subject.setCourseLevel(courseLevel);
        subject.setDetail(detail);
        subject.setModuleCode(moduleCode);
        subject.setName(subjectName);
        subject.setSemester(semester);
        subject.setStatus(subject.ACTIVE_SUBJECT);
        subject.setCourseId(courseId);
        return new subjectDaoImpl().addSubject(subject);
    }

    public static ResultSet getAllSubjects() throws SQLException {
        return new subjectDaoImpl().getAllSubject();
    }

    public static ResultSet getSubjectByOneAttribute(String attribute,
            String condition, String value) throws SQLException {
        return new subjectDaoImpl().getSubjectByOneAttribute(attribute, condition, value);
    }

    public static boolean updateSubject(subject subject) throws SQLException {
        return new subjectDaoImpl().updateSubject(subject);
    }

    public static subject getSubjectBySubjectId(int subjectId) throws SQLException {
        ResultSet rset = getSubjectByOneAttribute("subject_id", commonConstants.Sql.EQUAL, Integer.toString(subjectId));
        subject subject = null;
        while (rset.next()) {
            subject = new subject();
            subject.setCourseId(rset.getInt("subject_course_id"));
            subject.setCourseLevel(rset.getString("subject_course_level"));
            subject.setSemester(rset.getString("subject_semester"));
            subject.setName(rset.getString("subject_name"));
            subject.setId(rset.getInt("subject_id"));
            subject.setModuleCode(rset.getString("subject_module_code"));
            subject.setDetail(rset.getString("subject_detail"));
            subject.setStatus(rset.getInt("subject_status"));
        }
        return subject;
    }

    public static ResultSet getActiveAndCourseJoinedSubjectDetails() throws SQLException {
        return new subjectDaoImpl().getActiveAndCourseJoinedSubjectDetails();
    }

    public static ResultSet getSubjectByMoreAttributes(ArrayList<String[]> attributeConditionValueList, String operator) throws SQLException {
        return new subjectDaoImpl().getSubjectByMoreAttributes(attributeConditionValueList, operator);
    }

}
