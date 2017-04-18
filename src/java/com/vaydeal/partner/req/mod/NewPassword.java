/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

import com.vaydeal.partner.hash.Hashing;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class NewPassword {
    private final String token;
    private String userid;
    private String newPassword;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public NewPassword(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }
    
    /**
     * this will change the password
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    public void changePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Random ran = new Random();
        int length = ran.nextInt(10) + 30;
        salt = Hashing.generateSalt(length);
        System.out.println(newPassword);
        System.out.println(salt);
        newPassword = Hashing.hashPassword(newPassword.toCharArray(), salt.getBytes(), 24000, 256);
    }
}