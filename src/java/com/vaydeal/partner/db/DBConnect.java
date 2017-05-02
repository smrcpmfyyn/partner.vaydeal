/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.db;

import com.vaydeal.partner.req.mod.AddAffiliateUser;
import com.vaydeal.partner.req.mod.ChangePassword;
import com.vaydeal.partner.req.mod.ForgotPassword;
import com.vaydeal.partner.req.mod.GetAffiliateUserIds;
import com.vaydeal.partner.req.mod.GetAffiliateUsers;
import com.vaydeal.partner.req.mod.GetPayments;
import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.req.mod.RequestPromotion;
import com.vaydeal.partner.req.mod.ResetAffiliateUser;
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
import java.util.Random;

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
        PreparedStatement ps = connect.prepareStatement("SELECT salt,password,affiliate_user_id,affiliate_user_type FROM affiliate_logger WHERE affiliate_user_id = ?");
        List<String> proD = new ArrayList<>();
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            proD.add(rs.getString("salt"));
            proD.add(rs.getString("password"));
            proD.add(rs.getString("affiliate_user_id"));
            proD.add(rs.getString("affiliate_user_type"));
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
        PreparedStatement ps = connect.prepareStatement("INSERT INTO affiliate_request(affiliate_request_company,affiliate_request_website,affiliate_request_name,affiliate_request_email,affiliate_request_mobile,affiliate_request_date,affiliate_request_update_date) VALUES(?,?,?,?,?,NOW(),NOW())");
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
                ps.setString(1, req.getAffiliate());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                break;
            case "1":
                ps = connect.prepareStatement("SELECT time_stamp,reference_no,amount,status FROM vaydeal.affiliate_premium_payments WHERE company = ? AND status = 'active' LIMIT ?,?");
                ps.setString(1, req.getAffiliate());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                break;
            case "2":
                ps = connect.prepareStatement("SELECT time_stamp,reference_no,amount,status FROM vaydeal.affiliate_premium_payments WHERE company = ? AND status = 'expired' LIMIT ?,?");
                ps.setString(1, req.getAffiliate());
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
        if (totalPaid == null) {
            totalPaid = "0";
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
        if (activePayments == null) {
            activePayments = "0";
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
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_name, affiliate_user_address1, affiliate_user_address2, affiliate_user_place, affiliate_user_pin, affiliate_user_mobile,affiliate_user_email,affiliate,affiliate_user_designation FROM affiliate_users WHERE affiliate_user_id = ?");
        ps.setString(1, affiliate_user_id);
        rs = ps.executeQuery();
        AffiliateProfile profile;
        if (rs.next()) {
            profile = new AffiliateProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
        } else {
            profile = new AffiliateProfile("invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid");
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
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user SET affiliate_user_name = ?, affiliate_user_address1 = ?, affiliate_user_address2 = ?, affiliate_user_place = ?, affiliate_user_pin = ?, affiliate_user_mobile = ?, affiliate_user_designation = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getName());
        ps.setString(2, req.getAddress1());
        ps.setString(3, req.getAddress2());
        ps.setString(4, req.getPlace());
        ps.setString(5, req.getPin());
        ps.setString(6, req.getMobile());
        ps.setString(7, req.getDesignation());
        ps.setString(8, req.getAffiliate_user_id());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean changePassword(ChangePassword req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET password = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getNewPassword());
        ps.setString(2, req.getAffiliate_user_id());
        int c = ps.executeUpdate();
        return c == 1;
    }

    public void getAffiliateUsers(GetAffiliateUsers req, ArrayList<AffiliateUser> au) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_id,affiliate_user_name,affiliate_user_designation,affiliate_user_type,affiliate_user_status FROM affiliate_users WHERE affiliate = ?");
        ps.setString(1, req.getAffiliate());
        rs = ps.executeQuery();
        while (rs.next()) {
            AffiliateUser aus = new AffiliateUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            au.add(aus);
        }
        rs.close();
        ps.close();
    }

    public boolean changeAffiliateUserStatus(String user_id, String status) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = ? WHERE affiliate_user_id = ?");
        ps.setString(1, status);
        ps.setString(2, user_id);
        int c = ps.executeUpdate();
        return c == 1;
    }

    public boolean changePassword(ResetAffiliateUser req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET password = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getPassword());
        ps.setString(2, req.getUser_id());
        int c = ps.executeUpdate();
        return c == 1;
    }

    public void getUserDetails(String param, ArrayList<String> al) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_email, affiliate_user_name FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        if (rs.next()) {
            al.add(rs.getString(1));
            al.add(rs.getString(2));
        }
        rs.close();
        ps.close();
    }
    
    public void getUserDetails(String param, ArrayList<String> al,String affiliate) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_email, affiliate_user_name FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ? AND affiliate = ?");
        ps.setString(1, param);
        ps.setString(2, affiliate);
        rs = ps.executeQuery();
        if (rs.next()) {
            al.add(rs.getString(1));
            al.add(rs.getString(2));
        }
        rs.close();
        ps.close();
    }

    public int checkAffiliateUserMobile(String mobile) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_mobile FROM affiliate_logger_not_blocked WHERE affiliate_user_mobile = ?");
        ps.setString(1, mobile);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public int checkAffiliateUserEmail(String email) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_email FROM affiliate_logger_not_blocked WHERE affiliate_user_email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public String getNewAffiliateUserId() throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_id FROM affiliate_logger");
        ArrayList<String> al = new ArrayList<>();
        String new_affiliate_user_id = "";
        rs = ps.executeQuery();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        Random random = new Random();
        new_affiliate_user_id = "" + (random.nextInt(9999999) + 45573456);
        while (al.contains(new_affiliate_user_id)) {
            new_affiliate_user_id = "" + (random.nextInt(9999999) + 45573456);
        }
        return new_affiliate_user_id;
    }

    public void addAffliateUser(AddAffiliateUser req) throws SQLException {
        addAffiliateUserDetails(req);
        addAffiliateUserLogin(req);
    }

    private void addAffiliateUserDetails(AddAffiliateUser req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("INSERT INTO affiliate_user(affiliate_user_id,affiliate,affiliate_user_name,affiliate_user_email,affiliate_user_mobile,affiliate_user_type,affiliate_user_designation) VALUES(?,?,?,?,?,2,?)");
        ps.setString(1, req.getNew_user_id());
        ps.setString(2, req.getAffiliate());
        ps.setString(3, req.getName());
        ps.setString(4, req.getEmail());
        ps.setString(5, req.getMobile());
        ps.setString(6, req.getDesignation());
        ps.executeUpdate();
        ps.close();
    }

    private void addAffiliateUserLogin(AddAffiliateUser req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("INSERT INTO affiliate_user_login(affiliate_user_id,password,salt,last_logged) VALUES (?,?,?,NOW())");
        ps.setString(1, req.getNew_user_id());
        ps.setString(2, req.getPassword());
        ps.setString(3, req.getSalt());
        ps.executeUpdate();
        ps.close();
    }

    public void removeAffiliateUser(String new_user_id) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("DELETE FROM affiliate_user WHERE affiliate_user_id = ?");
        ps.setString(1, new_user_id);
        ps.executeUpdate();
        ps.close();
    }

    public void getAffiliateUserIds(GetAffiliateUserIds req, ArrayList<String> auids) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate_user_id FROM affiliate_users WHERE affiliate = ?");
        ps.setString(1, req.getAffiliate());
        rs = ps.executeQuery();
        while (rs.next()) {
            auids.add(rs.getString(1));
        }
        rs.close();
        ps.close();
    }

    public boolean checkAffiliateActivity(String activity) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_activities WHERE activity = ?");
        ps.setString(1, activity);
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        return count == 1;
    }

    public boolean checkAffiliateID(String param, String affiliate) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM affiliate_logger WHERE affiliate_user_id = ? and affiliate = ?");
        ps.setString(1, param);
        ps.setString(2, affiliate);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public ArrayList<String> getAffiliateActivities() throws SQLException {
        ArrayList<String> al = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement("SELECT activity FROM affiliate_activities");
        rs = ps.executeQuery();
        while(rs.next()){
            al.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        return al;
    }

    public ArrayList<String> getUserAttributes(String param) throws SQLException {
        ArrayList<String> al = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement("SELECT affiliate,affiliate_user_type FROM affiliate_users WHERE affiliate_user_id = ?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        if(rs.next()){
            al.add(rs.getString(1));
            al.add(rs.getString(2));
        }
        rs.close();
        ps.close();
        return al;
    }

    public boolean changePassword(ForgotPassword req) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("UPDATE affiliate_user_login SET password = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getPassword());
        ps.setString(2, req.getUid());
        int c = ps.executeUpdate();
        return c == 1;
    }

    public int getMaxPageNo(String view, int max) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM " + view);
        rs = ps.executeQuery();
        rs.next();
        double count = rs.getInt(1);
        int mpn = 0;
        if (count > 0) {
            mpn = (int) Math.ceil(count / max);
        }
        rs.close();
        ps.close();
        return mpn;
    }

    public int getMaxPageNo(String view, int max, String whereCondition) throws SQLException {
        PreparedStatement ps = connect.prepareStatement("SELECT count(*) FROM " + view + " WHERE " + whereCondition);
        rs = ps.executeQuery();
        rs.next();
        double count = rs.getInt(1);
        int mpn = 0;
        if (count > 0) {
            mpn = (int) Math.ceil(count / max);
        }
        rs.close();
        ps.close();
        return mpn;
    }

    public int getPaymentsPageMaxPageNo(GetPayments req) throws SQLException {
        String where = " company = '"+req.getAffiliate()+"' ";
        int cp = Integer.parseInt(req.getPageNo());
        int mp = 0;
        int max = Integer.parseInt(req.getMaxEntries());
        switch(req.getQuery()){
            case "0":
                mp = getMaxPageNo("affiliate_premium_payments", max, where);
                break;
            case "1":
                where += "AND status = 'active'";
                mp = getMaxPageNo("affiliate_premium_payments", max, where);
                break;
            case "2":
                where += "AND status = 'expired'";
                mp = getMaxPageNo("affiliate_premium_payments", max, where);
                break;
        }
        return mp;
    }
}
