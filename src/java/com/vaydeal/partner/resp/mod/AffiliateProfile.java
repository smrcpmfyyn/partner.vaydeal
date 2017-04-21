/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class AffiliateProfile {
    private final String name;
    private final String address1;
    private final String address2;
    private final String pin;
    private final String mobileNo;
    private final String email;
    private final String company;
    private final String designation;

    public AffiliateProfile(String name, String address1, String address2, String pin, String mobileNo, String email, String company, String designation) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.pin = pin;
        this.mobileNo = mobileNo;
        this.email = email;
        this.company = company;
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\", \"address1\":\"" + address1 + "\", \"address2\":\"" + address2 + "\", \"pin\":\"" + pin + "\", \"mobileNo\":\"" + mobileNo + "\", \"email\":\"" + email + "\", \"company\":\"" + company + "\", \"designation\":\"" + designation + "\"}";
    }
    
    
}
