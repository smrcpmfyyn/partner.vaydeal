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
import com.vaydeal.partner.intfc.processreq.GetAffiliateUserIdsProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.GetAffiliateUserIds;
import com.vaydeal.partner.resp.mod.GetAffiliateUserIdsSuccessResponse;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliateUserIds implements GetAffiliateUserIdsProcessor{
    
    private final GetAffiliateUserIds req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<String> auids;

    public ProcessGetAffiliateUserIds(GetAffiliateUserIds gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.auids = new ArrayList<String>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public boolean getAffiliateUserIds() throws Exception {
        dbc.getAffiliateUserIds(req,auids);
        return auids.size()>0;
    }

    @Override
    public GetAffiliateUserIdsSuccessResponse processRequest() throws Exception {
        GetAffiliateUserIdsSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliateUserIds()) {
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
    public GetAffiliateUserIdsSuccessResponse generateResponse(boolean status) {
        GetAffiliateUserIdsSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateUserIdsSuccessResponse(ResponseMsg.RESP_OK, accessToken,auids);
        } else {
            resp = new GetAffiliateUserIdsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
}


