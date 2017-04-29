/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

import com.vaydeal.partner.hash.Hashing;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class AddAffiliateUser {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private final String designation;
    private final String name;
    private final String email;
    private final String mobile;
    private String new_user_id;
    private String password;
    private String salt;
    private String passwordToken;

    public AddAffiliateUser(String at, String designation, String name, String email, String mobile) {
        this.at = at;
        this.designation = designation;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
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

    public void setNew_user_id(String new_user_id) {
        this.new_user_id = new_user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
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

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getNew_user_id() {
        return new_user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordToken() {
        return passwordToken;
    }
    
    public void setAutoGeneratedValues() throws Exception {
        generatePassword();
        genHash();
    }

    private void generatePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Random ran = new Random();
        int length = ran.nextInt(10) + 30;
        password = new_user_id + affiliate_user_id + length;
        salt = Hashing.generateSalt(length);
        password = Hashing.hashPassword(password.toCharArray(), salt.getBytes(), 24000, 256);
    }

    /**
     *
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public void genHash() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        passwordToken = Hashing.genVKey(email + user_type + password + System.currentTimeMillis());
    }

}