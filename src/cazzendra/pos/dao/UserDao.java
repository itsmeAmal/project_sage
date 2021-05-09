/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.dao;

import cazzendra.pos.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amal
 */
public interface UserDao {

    void addUser(User user) throws SQLException;

    ResultSet getAllUsers() throws SQLException;

    void updateUser(User user) throws SQLException;
}
