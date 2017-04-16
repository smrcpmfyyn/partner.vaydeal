/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.db;

import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.req.mod.RequestPromotion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int getNoOfAffiliates() throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliates");
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkUID(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger WHERE affiliate_user_id = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkNBUID(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
    
    public List<String> getPassDSalt(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT salt,password,affiliate_user_id FROM affiliate_logger WHERE affiliate_user_id = ?");
        List<String> proD = new ArrayList<>();
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            proD.add(rs.getString("salt"));
            proD.add(rs.getString("password"));
            proD.add(rs.getString("affiliate_user_id"));
        }
        return proD;
    }

    public boolean updateLog(String uName) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET last_logged = now(), log_count = logcount+1 WHERE uname = ?");
        ps.setString(1, uName);
        int c = ps.executeUpdate();
        if (c == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int addPromotionRequest(RequestPromotion req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("INSERT INTO affiliate_request(affiliate_request_company,affiliate_request_website,affiliate_request_name,affiliate_request_email,affiliate_request_mobile,affiliate_request_status,affiliate_request_date) VALUES(?,?,?,?,?,1,NOW())");
        ps.setString(1, req.getCompany());
        ps.setString(2, req.getWebsite());
        ps.setString(3, req.getName());
        ps.setString(4, req.getEmail());
        ps.setString(5, req.getMobile());
        int c = ps.executeUpdate();
        return c;
    }

}
