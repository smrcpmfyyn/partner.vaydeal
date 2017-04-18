/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class UpdateProfile {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private final String name;
    private final String address1;
    private final String address2;
    private final String pin;
    private final String mobile;
    private final String email;
    private final String company;
    private final String designation;

    public UpdateProfile(String at, String name, String address1, String address2, String pin, String mobile, String email, String company, String designation) {
        this.at = at;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.pin = pin;
        this.mobile = mobile;
        this.email = email;
        this.company = company;
        this.designation = designation;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public String getAt() {
        return at;
    }

    public String getName() {
        return name;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPin() {
        return pin;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAffiliate_user_id() {
        return affiliate_user_id;
    }

    public void setAffiliate_user_id(String affiliate_user_id) {
        this.affiliate_user_id = affiliate_user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }
}
