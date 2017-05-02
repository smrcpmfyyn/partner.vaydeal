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
public class ChangeAffiliateUserStatus {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private final String user_id;
    private final String status;

    public ChangeAffiliateUserStatus(String at, String user_id, String status) {
        this.at = at;
        this.user_id = user_id;
        this.status = status;
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

    public String getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }
}
