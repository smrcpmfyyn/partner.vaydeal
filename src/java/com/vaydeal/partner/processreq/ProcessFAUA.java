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
    private int currentPage;
    private int nextPage;
    private int previousPage;
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
    public boolean getAllFAUA() throws Exception {
        activities = mdbc.getAllFAUA(req);
        currentPage = Integer.parseInt(req.getPageNo());
        int status = getPaymentsPageStatus();
        switch (status) {
            case 0:
                nextPage = currentPage + 1;
                previousPage = 0;
                break;
            case 1:
                nextPage = 0;
                previousPage = currentPage - 1;
                break;
            default:
                nextPage = currentPage + 1;
                previousPage = currentPage - 1;
                break;
        }
        return activities.size() > 0;
    }

    @Override
    public FAUASuccessResponse processRequest() throws Exception {
        FAUASuccessResponse obj = null;
        if (generateToken()) {
            if (getAllFAUA()) {
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
    public FAUASuccessResponse generateResponse(boolean status) {
        FAUASuccessResponse resp;
        if (status) {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_OK, accessToken, activities,currentPage,nextPage,previousPage,Integer.parseInt(req.getMaxEntries()),Integer.parseInt(req.getPageNo()));
        } else {
            resp = new FAUASuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

    private int getPaymentsPageStatus() {
        int cp = Integer.parseInt(req.getPageNo());
        int mp = req.getMaxPageNo();
        if (cp == mp) {
            return 1;
        } else if (cp == 1) {
            return 0;
        } else {
            return 2;
        }
    }
}
