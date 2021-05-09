/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 4m4l
 */
public interface commonDao {
    
    ResultSet getAllRecords(String  selectQuery)throws SQLException;
    
    ResultSet getResultByAttribute(String selectQuery, String attribute, String condition, String value)throws SQLException;
    
}
