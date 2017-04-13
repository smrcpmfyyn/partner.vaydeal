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
import com.vaydeal.partner.intfc.processreq.LogProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.Login;
import com.vaydeal.partner.resp.mod.LogSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessLog implements LogProcessor {

    private final Login log;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessLog(Login log) throws Exception {
        this.log = log;
        dbc = DB.getConnection();
        mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = log.getuName() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(log.getAff_user_id(), accessToken);
    }

    @Override
    public LogSuccessResponse processRequest() throws Exception {
        LogSuccessResponse obj = null;
        if (generateToken()) {
            if (updateLog()) {
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
    public LogSuccessResponse generateResponse(boolean status) {
        LogSuccessResponse resp;
        if (status) {
            resp = new LogSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new LogSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public boolean updateLog() throws Exception {
        return dbc.updateLog(log.getuName());
    }
}

