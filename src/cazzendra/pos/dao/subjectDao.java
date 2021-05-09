/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.subject;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nethmi Dasanayaka
 */
public interface subjectDao {
    
    boolean addSubject(subject subject) throws SQLException;
    
    ResultSet getAllSubject() throws SQLException;
    
    ResultSet getSubjectByOneAttribute(String attribute, String condition, String value) throws SQLException;
    
    boolean updateSubject(subject subject) throws SQLException;
    
    boolean deleteSubject(int subjectId) throws SQLException;  
}
