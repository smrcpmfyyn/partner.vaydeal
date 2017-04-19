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
import com.vaydeal.partner.intfc.processreq.FAUAProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.FAUA;
import com.vaydeal.partner.resp.mod.Activity;
import com.vaydeal.partner.resp.mod.FAUASuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessFAUA implements FAUAProcessor {

    private ArrayList<Activity> activities;
    private final FAUA req;
    private String accessToken;
    private final MongoConnect mdbc;
    private final DBConnect dbc;

    public ProcessFAUA(FAUA req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
        this.activities = new ArrayList<>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public void getAllFAUA() throws Exception {
        activities = mdbc.getAllFAUA(req);
    }

    @Override
    public FAUASuccessResponse processRequest() throws Exception {
        FAUASuccessResponse obj = null;
        if (generateToken()) {
            getAllFAUA();
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public FAUASuccessResponse generateResponse(boolean status) {
        FAUASuccessResponse resp;
        if (status) {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_OK, accessToken, activities);
        } else {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}

