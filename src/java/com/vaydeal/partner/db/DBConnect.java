/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.db;

import com.vaydeal.partner.req.mod.ChangePassword;
import com.vaydeal.partner.req.mod.GetAffiliateUsers;
import com.vaydeal.partner.req.mod.GetPayments;
import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.req.mod.RequestPromotion;
import com.vaydeal.partner.req.mod.UpdateProfile;
import com.vaydeal.partner.resp.mod.AffiliatePayments;
import com.vaydeal.partner.resp.mod.AffiliateProfile;
import com.vaydeal.partner.resp.mod.AffiliateUser;
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
        connect = DriverManager.getConnection("jdbc:mysql://worddb.c8s8lmxdo3ux.ap-south-1.rds.amazonaws.com:3306/vaydeal", "worduser", "sooraj123");
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
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkUID(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger WHERE affiliate_user_id = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int checkNBUID(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
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
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET last_logged = now(), log_count = log_count+1 WHERE affiliate_user_id = ?");
        ps.setString(1, uName);
        int c = ps.executeUpdate();
        return c == 1;
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

    public boolean blockAffiliateUser(String affiliate_user_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = 2 WHERE affiliate_user_id = ?");
        ps.setString(1, affiliate_user_id);
        int count = ps.executeUpdate();
        ps.close();
        return count == 1;
    }

    public boolean checkNBAffiliateID(String user_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, user_id);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean checkAffiliate(String affiliate) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliates WHERE company = ? AND status = 'active'");
        ps.setString(1, affiliate);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean checkNBAU(String uname) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked a  INNER JOIN affiliate b ON a.affiliate = b.company AND b.status = 1 WHERE affiliate_user_id = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public void getAffiliatePayments(GetPayments req, ArrayList<AffiliatePayments> ap) throws SQLException {
        PreparedStatement ps = null;
        int me = Integer.parseInt(req.getMaxEntries());
        int pn = Integer.parseInt(req.getPageNo());
        int start = (pn - 1) * me;
        switch (req.getQuery()) {
            case "0":
                ps = connect.prepareStatement("SELECT time_stamp,reference_no,amount,status FROM vaydeal.affiliate_premium_payments WHERE company = ? LIMIT ?,?");
                ps.setString(1, req.getUser_type());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                break;
            case "1":
                ps = connect.prepareStatement("SELECT time_stamp,reference_no,amount,status FROM vaydeal.affiliate_premium_payments WHERE company = ? AND status = 'active' LIMIT ?,?");
                ps.setString(1, req.getUser_type());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                break;
            case "2":
                ps = connect.prepareStatement("SELECT time_stamp,reference_no,amount,status FROM vaydeal.affiliate_premium_payments WHERE company = ? AND status = 'blocked' LIMIT ?,?");
                ps.setString(1, req.getUser_type());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                break;
        }
        while (rs.next()) {
            AffiliatePayments aps = new AffiliatePayments(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            ap.add(aps);
        }
        rs.close();
        ps.close();
    }

    public String getTotalPayment(String affiliate) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT SUM(amount) FROM affiliate_premium_payments WHERE company = ?");
        ps.setString(1, affiliate);
        rs = ps.executeQuery();
        String totalPaid = "0";
        if (rs.next()) {
            totalPaid = rs.getString(1);
        }
        return totalPaid;
    }

    public String getActivePayments(String affiliate) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT SUM(amount) FROM affiliate_premium_payments WHERE company = ? AND status = 'active'");
        ps.setString(1, affiliate);
        rs = ps.executeQuery();
        String activePayments = "0";
        if (rs.next()) {
            activePayments = rs.getString(1);
        }
        return activePayments;
    }

    public String getUserType(String uName) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_type FROM affiliate_users WHERE affiliate_user_id = ?");
        ps.setString(1, uName);
        rs = ps.executeQuery();
        rs.next();
        String type = rs.getString(1);
        rs.close();
        ps.close();
        return type;
    }

    public AffiliateProfile getAffiliateProfile(String affiliate_user_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_name, affiliate_user_address1, affiliate_user_address2, affiliate_user_pin, affiliate_user_mobile,affiliate_user_email,affiliate,affiliate_user_designation FROM affiliate_users WHERE affiliate_user_id = ?");
        ps.setString(1, affiliate_user_id);
        rs = ps.executeQuery();
        AffiliateProfile profile;
        if (rs.next()) {
            profile = new AffiliateProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
        } else {
            profile = new AffiliateProfile("invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid");
        }
        rs.close();
        ps.close();
        return profile;
    }

    public boolean checkEmail(String param, String user_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_email = ? AND  affiliate_user_id = ?");
        ps.setString(1, param);
        ps.setString(2, user_id);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean checkComapany(String param, String email) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_email = ? AND affiliate = ?");
        ps.setString(1, email);
        ps.setString(2, param);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean updateProfile(UpdateProfile req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user SET affiliate_user_name = ?, affiliate_user_address1 = ?, affiliate_user_address2 = ?, affiliate_user_pin = ?, affiliate_user_mobile = ?, affiliate_user_designation = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getName());
        ps.setString(2, req.getAddress1());
        ps.setString(3, req.getAddress2());
        ps.setString(4, req.getPin());
        ps.setString(5, req.getMobile());
        ps.setString(6, req.getDesignation());
        ps.setString(7, req.getAffiliate_user_id());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean changePassword(ChangePassword req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET password = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getNewPassword());
        ps.setString(3, req.getAffiliate_user_id());
        int c = ps.executeUpdate();
        return c==1;
    }

    public void getAffiliateUsers(GetAffiliateUsers req, ArrayList<AffiliateUser> au) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_id,affiliate_user_name,affiliate_user_designation,affiliate_user_type,affiliate_user_status FROM affiliate_users WHERE affiliate = ?");
        ps.setString(1, req.getAffiliate());
        rs = ps.executeQuery();
        while (rs.next()) {
            AffiliateUser aus = new AffiliateUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
            au.add(aus);
        }
        rs.close();
        ps.close();
    }

}
