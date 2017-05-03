/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.hash.Hashing;
import com.vaydeal.partner.intfc.processreq.GetFilterProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.GetFilter;
import com.vaydeal.partner.resp.mod.GetFilterSuccessResponse;
import com.vaydeal.partner.resp.mod.Filter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetFilter implements GetFilterProcessor{
    
    private final GetFilter req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private Filter filter;

    public ProcessGetFilter(GetFilter gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.filter = new Filter();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public boolean getFilter() throws Exception {
        filter.addFilter("Activity Status", getStatusFilter());
        filter.addFilter("User Type", getUTypeFilter());
        filter.addFilter("Activities", getAffiliateActivities());
        return filter.size()>0;
    }

    @Override
    public GetFilterSuccessResponse processRequest() throws Exception {
        GetFilterSuccessResponse obj = null;
        if (generateToken()) {
            if (getFilter()) {
                obj = generateResponse(true);
            } else {
                obj = generateResponse(false);
            }
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public GetFilterSuccessResponse generateResponse(boolean status) {
        GetFilterSuccessResponse resp;
        if (status) {
            resp = new GetFilterSuccessResponse(ResponseMsg.RESP_OK, accessToken,filter);
        } else {
            resp = new GetFilterSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken,filter);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

    private ArrayList<String> getStatusFilter() {
        ArrayList<String> fl = new ArrayList<>();
        fl.add("valid");
        fl.add("invalid");
        fl.add("blocked");
        return fl;
    }

    private ArrayList<String> getAffiliateActivities() throws SQLException {
        return dbc.getAffiliateActivities();
    }

    private ArrayList<String> getUTypeFilter() {
        ArrayList<String> fl = new ArrayList<>();
        fl.add("super");
        fl.add("sub");
        return fl;
    }

}