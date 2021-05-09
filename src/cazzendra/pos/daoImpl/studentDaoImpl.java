package cazzendra.pos.daoImpl;

import cazzendra.pos.connection.DatabaseConnection;
import cazzendra.pos.dao.studentDao;
import cazzendra.pos.model.student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amal
 */
public class studentDaoImpl implements studentDao {

    private String selectQuery = "select student_id, student_name, student_email_1, student_email_2, "
            + "student_reg_no, student_contact_no, student_detail, student_status, student_batch_id, "
            + "student_group_id, student_special_id, student_address, student_contact_no_2, student_image_path, "
            + "student_guardian_name, student_guardian_contact_no, student_dob, student_grade from student";

    private String selectQuery2 = "select student_id, student_name, student_email_1, student_email_2, student_reg_no, "
            + " student_contact_no, student_detail, student_status, student_batch_id, student_group_id, student_special_id,"
            + " student_address, student_contact_no_2, student_image_path, student_guardian_name, student_guardian_contact_no, "
            + " group_name, group_batch_id, group_type, group_detail, "
            + " (select group_name from group_info where group_id=student_special_id) as specil_group_name, student_grade "
            + " from student left join group_info on group_id=student_group_id";

    @Override
    public boolean addStudent(student student) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("insert into student (student_name, student_email_1, student_email_2, "
                + " student_reg_no, student_contact_no, student_detail, student_status, student_batch_id, student_group_id,"
                + " student_special_id, student_id, student_dob, student_guardian_name, student_grade) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail1());
        ps.setString(3, student.getEmail2());
        ps.setString(4, student.getRegNo());
        ps.setString(5, student.getContactNo());
        ps.setString(6, student.getDetail());
        ps.setInt(7, student.getStatus());
        ps.setString(8, student.getGender());
        ps.setString(9, student.getGuardianContact());
        ps.setString(10, student.getCurrentAddress());
        ps.setInt(11, getNextStudentId());
        ps.setDate(12, student.getDob());
        ps.setString(13, student.getGuardianName());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public ResultSet getAllStudents() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery);
    }

    @Override
    public ResultSet getStudentByOneAttribute(String attribute, String condition, String value) throws SQLException {
        return new CommonDaoImpl().getResultByAttribute(selectQuery, attribute, condition, value);
    }

    @Override
    public boolean updateStudent(student student) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("update student set student_name=?,"
                + " student_email_1=?, student_email_2=?, student_reg_no=?, student_contact_no=?, student_detail=?,"
                + " student_status=?, student_batch_id=?, student_group_id=?, student_special_id=?, student_status=?, "
                + " student_dob=?,student_guardian_name=?, student_grade=? where student_id=?");
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail1());
        ps.setString(3, student.getEmail2());
        ps.setString(4, student.getRegNo());
        ps.setString(5, student.getContactNo());
        ps.setString(6, student.getDetail());
        ps.setInt(7, student.ACTIVE_STUDENT);
        ps.setString(8, student.getGender());
        ps.setString(9, student.getGuardianContact());
        ps.setString(10, student.getCurrentAddress());
        ps.setInt(11, student.getStatus());
        ps.setDate(12, student.getDob());
        ps.setString(13, student.getGuardianName());
        ps.setString(14, student.getGrade());
        ps.setInt(15, student.getId());
        ps.executeUpdate();
        ps.close();
        return true;
    }

    @Override
    public boolean deleteStudent(int studentId) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("delete from student where student_id=?");
        ps.setInt(1, studentId);
        ps.executeUpdate();
        ps.close();
        return true;
    }

    public ResultSet getAllStudentsWithOtherJoinDetails() throws SQLException {
        return new CommonDaoImpl().getAllRecords(selectQuery2);
    }

    public ResultSet getStudentsByMoreAttributes(ArrayList<String[]> AttributeConditionValueList, String Operator) throws SQLException {
        return new CommonDaoImpl().getResultByAttributesWithJoinOperator(selectQuery, AttributeConditionValueList, Operator);
    }

    public int getNextStudentId() throws SQLException {
        int nextId = 0;
        Connection con = DatabaseConnection.getDatabaseConnection();
        PreparedStatement ps = con.prepareStatement("select max(student_id) as student_id from student");
        ResultSet rset = ps.executeQuery();
        while (rset.next()) {
            nextId = rset.getInt("student_id");
        }
        return ++nextId;
    }
}
