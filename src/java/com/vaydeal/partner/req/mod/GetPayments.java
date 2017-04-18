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
public class GetPayments {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private final String query;
    private final String maxEntries;
    private final String pageNo;

    public GetPayments(String at, String query, String maxEntries, String pageNo) {
        this.at = at;
        this.query = query;
        this.maxEntries = maxEntries;
        this.pageNo = pageNo;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public void setAffiliate_user_id(String affiliate_user_id) {
        this.affiliate_user_id = affiliate_user_id;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
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

    public String getQuery() {
        return query;
    }

    public String getMaxEntries() {
        return maxEntries;
    }

    public String getPageNo() {
        return pageNo;
    }
}
