/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.email.AffiliateEmail;
import com.vaydeal.partner.hash.Hashing;
import com.vaydeal.partner.intfc.processreq.AddAffiliateUserProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.AddAffiliateUser;
import com.vaydeal.partner.resp.mod.AddAffiliateUserSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessAddAffiliateUser implements AddAffiliateUserProcessor {

    private final AddAffiliateUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessAddAffiliateUser(AddAffiliateUser gu) throws Exception {
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc = DB.getMongoConnection();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public void addUser() throws Exception {
        try {
            dbc.addAffliateUser(req);
            mdbc.addAffiliateUser(req);
        } catch (Exception e) {
            revokeAddAffiliateUser(req);
            throw e;
        }
    }

    @Override
    public void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AffiliateEmail.sendAffiliateUserResetPassword(email, passwordToken, name);
    }

    @Override
    public AddAffiliateUserSuccessResponse processRequest() throws Exception {
        AddAffiliateUserSuccessResponse obj = null;
        if (generateToken()) {
            addUser();
            sendSetPasswordEmail(req.getEmail(), req.getPasswordToken(), req.getName());
            obj = generateResponse(true);
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public AddAffiliateUserSuccessResponse generateResponse(boolean status) {
        AddAffiliateUserSuccessResponse resp;
        if (status) {
            resp = new AddAffiliateUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new AddAffiliateUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }
    
    private void revokeAddAffiliateUser(AddAffiliateUser au) throws SQLException {
        dbc.removeAffiliateUser(au.getNew_user_id());
        mdbc.removeAffiliateUser(au.getNew_user_id());
    }

    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
