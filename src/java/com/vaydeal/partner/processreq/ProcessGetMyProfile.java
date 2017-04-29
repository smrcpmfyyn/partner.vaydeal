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
import com.vaydeal.partner.intfc.processreq.GetMyProfileProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.GetMyProfile;
import com.vaydeal.partner.resp.mod.GetMyProfileSuccessResponse;
import com.vaydeal.partner.resp.mod.AffiliateProfile;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetMyProfile implements GetMyProfileProcessor{
    
    private final GetMyProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private AffiliateProfile profile;

    public ProcessGetMyProfile(GetMyProfile gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public boolean getMyProfile() throws Exception {
        profile = dbc.getAffiliateProfile(req.getAffiliate_user_id());
        return !profile.getEmail().equals("invalid");
    }

    @Override
    public GetMyProfileSuccessResponse processRequest() throws Exception {
        GetMyProfileSuccessResponse obj = null;
        if (generateToken()) {
            if (getMyProfile()) {
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
    public GetMyProfileSuccessResponse generateResponse(boolean status) {
        GetMyProfileSuccessResponse resp;
        if (status) {
            resp = new GetMyProfileSuccessResponse(ResponseMsg.RESP_OK, accessToken, profile);
        } else {
            resp = new GetMyProfileSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }
    
}
