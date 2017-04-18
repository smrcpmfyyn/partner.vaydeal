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
import com.vaydeal.partner.intfc.processreq.GetAffiliateUsersProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.GetAffiliateUsers;
import com.vaydeal.partner.resp.mod.GetAffiliateUsersSuccessResponse;
import com.vaydeal.partner.resp.mod.AffiliateUser;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetAffiliateUsers implements GetAffiliateUsersProcessor{
    
    private final GetAffiliateUsers req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private ArrayList<AffiliateUser> au;

    public ProcessGetAffiliateUsers(GetAffiliateUsers gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.au = new ArrayList<AffiliateUser>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public boolean getAffiliateUsers() throws Exception {
        dbc.getAffiliateUsers(req,au);
        return au.size()>0;
    }

    @Override
    public GetAffiliateUsersSuccessResponse processRequest() throws Exception {
        GetAffiliateUsersSuccessResponse obj = null;
        if (generateToken()) {
            if (getAffiliateUsers()) {
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
    public GetAffiliateUsersSuccessResponse generateResponse(boolean status) {
        GetAffiliateUsersSuccessResponse resp;
        if (status) {
            resp = new GetAffiliateUsersSuccessResponse(ResponseMsg.RESP_OK, accessToken,au);
        } else {
            resp = new GetAffiliateUsersSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

}


