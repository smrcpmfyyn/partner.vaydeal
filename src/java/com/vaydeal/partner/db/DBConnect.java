/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.db;

import com.vaydeal.partner.req.mod.NewPassword;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class DBConnect {
    
    private Connection connect = null;
    private ResultSet rs;
    
    public DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/vaydeal", "techvay", "techvay");
    }
    
    /**
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        connect.close();
    }

    public int changePassword(NewPassword np) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET password = ? ,salt = ? WHERE affiliate_user_id = ?");
        ps.setString(1, np.getNewPassword());
        ps.setString(2, np.getSalt());
        ps.setString(3, np.getUserid());
        return ps.executeUpdate();
    }
}
