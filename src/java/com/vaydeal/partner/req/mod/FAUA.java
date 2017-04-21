/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.req.mod;

import com.vaydeal.partner.jsn.JSONParser;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @company techvay
 * @author rifaie
 */
public class FAUA {
    private final String at;
    private String affiliate_user_id;
    private String user_type;
    private String affiliate;
    private final AffiliateActivityFilter ftr;
    private final int maxEntries;
    private final int pageNo;
    
    public FAUA(String at, JSONObject ftr, String maxEntries, String pageNo) throws IOException, JSONException {
        this.at = at;
        this.maxEntries = Integer.parseInt(maxEntries);
        this.pageNo = Integer.parseInt(pageNo);
        if (ftr.length() == 0 || ftr == null) {
            this.ftr = new AffiliateActivityFilter();
        } else {
            this.ftr = JSONParser.parseJSONAF(ftr.toString());
        }
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

    public AffiliateActivityFilter getFtr() {
        return ftr;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public int getPageNo() {
        return pageNo;
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
}
