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
import com.vaydeal.partner.intfc.processreq.UpdateProfileProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.UpdateProfile;
import com.vaydeal.partner.resp.mod.UpdateProfileSuccessResponse;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessUpdateProfile implements UpdateProfileProcessor{
    
    private final UpdateProfile req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessUpdateProfile(UpdateProfile gu) throws Exception{
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
    public boolean updateProfile() throws Exception {
        return dbc.updateProfile(req);
    }

    @Override
    public UpdateProfileSuccessResponse processRequest() throws Exception {
        UpdateProfileSuccessResponse obj = null;
        if (generateToken()) {
            if (updateProfile()) {
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
    public UpdateProfileSuccessResponse generateResponse(boolean status) {
        UpdateProfileSuccessResponse resp;
        if (status) {
            resp = new UpdateProfileSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new UpdateProfileSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}


