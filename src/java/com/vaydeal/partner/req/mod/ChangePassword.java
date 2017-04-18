/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

import com.vaydeal.partner.hash.Hashing;

/**
 * @company techvay
 * @author rifaie
 */
public class ChangePassword {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private String currentPassword;
    private String salt;
    private String newPassword;

    public ChangePassword(String at, String currentPassword, String newPassword) {
        this.at = at;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setAffiliate_user_id(String affiliate_user_id) {
        this.affiliate_user_id = affiliate_user_id;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAt() {
        return at;
    }

    public String getAffiliate_user_id() {
        return affiliate_user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    public void changeCurrentPassword() throws Exception{
        currentPassword = Hashing.hashPassword(currentPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
    
    public void changeNewPassword() throws Exception{
        newPassword = Hashing.hashPassword(newPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
}
