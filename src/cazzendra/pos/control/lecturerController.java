/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.control;

import cazzendra.pos.daoImpl.lecturerDaoImpl;
import cazzendra.pos.model.lecturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amal
 */
public class lecturerController {

    public static boolean addLecturer(String title, String name, String email, String contactNo,
            String detail, String prefixCode) throws SQLException {
        lecturer lecturer = new lecturer();
        lecturer.setName(name);
        lecturer.setDetail(detail);
        lecturer.setContactNo(contactNo);
        lecturer.setEmail(email);
        lecturer.setStatus(lecturer.ACTIVE);
        lecturer.setTitle(title);
        lecturer.setPrefixCode(prefixCode);
        return new lecturerDaoImpl().addLecturer(lecturer);
    }

    public static ResultSet getAllLecturers() throws SQLException {
        return new lecturerDaoImpl().getAlllecturers();
    }

    public static ResultSet getLecturerResultSetByOneAttribute(String attribute,
            String condition, String value) throws SQLException {
        return new lecturerDaoImpl().getLecturerByOneAttribute(attribute, condition, value);
    }

    public static boolean updateLecturer(lecturer lecturer) throws SQLException {
        return new lecturerDaoImpl().updateLecturer(lecturer);
    }

    public static lecturer getLecturerByLecturerId(int lecturerId) throws SQLException {
        ResultSet rset = getLecturerResultSetByOneAttribute("lecturer_id",
                commonConstants.Sql.EQUAL, Integer.toString(lecturerId));
        lecturer lecturer = null;
        while (rset.next()) {
            lecturer = new lecturer();
            lecturer.setId(rset.getInt("lecturer_id"));
            lecturer.setDetail(rset.getString("lecturer_detail"));
            lecturer.setTitle(rset.getString("lecturer_title"));
            lecturer.setName(rset.getString("lecturer_name"));
            lecturer.setEmail(rset.getString("lecturer_email"));
            lecturer.setContactNo(rset.getString("lecturer_contact_no"));
            lecturer.setStatus(rset.getInt("lecturer_status"));
            lecturer.setPrefixCode(rset.getString("lecturer_prefix_code"));
        }
        return lecturer;
    }

    public static ResultSet getLecturerByMoreAttributes(ArrayList<String[]> attributeConditionValueList, String operator) throws SQLException {
        return new lecturerDaoImpl().getLecturerByMoreAttributes(attributeConditionValueList, operator);
    }

}
