/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.req.mod;

import com.vaydeal.partner.hash.Hashing;

/**
 *
 * @author USER
 */
public class Login {
    private final String uName;
    private String password;
    private String salt;
    private String aff_user_id;
    private String aff_user_type;

    public Login(String uName, String password) {
        this.uName = uName;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setAff_user_id(String aff_user_id) {
        this.aff_user_id = aff_user_id;
    }

    public void setAff_user_type(String aff_user_type) {
        this.aff_user_type = aff_user_type;
    }

    public String getuName() {
        return uName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getAff_user_id() {
        return aff_user_id;
    }

    public String getAff_user_type() {
        return aff_user_type;
    }
    
    public void changePassword() throws Exception{
        password = Hashing.hashPassword(password.toCharArray(), salt.getBytes(), 24000, 256);
    }
    
}
