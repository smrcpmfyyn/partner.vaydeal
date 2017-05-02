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
import com.vaydeal.partner.intfc.processreq.ResetAffiliateUserProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.ResetAffiliateUser;
import com.vaydeal.partner.resp.mod.ResetAffiliateUserSuccessResponse;
import java.sql.SQLException;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessResetAffiliateUser implements ResetAffiliateUserProcessor {

    private final ResetAffiliateUser req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;

    public ProcessResetAffiliateUser(ResetAffiliateUser gu) throws Exception {
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
    public boolean resetAffiliateUser() throws Exception {
        boolean flag = false;
        if(dbc.changePassword(req)){
            sendSetPasswordEmail(req.getEmail(), req.getPasswordToken(), req.getName());
            flag = true;
        }
        return flag;
    }

    @Override
    public ResetAffiliateUserSuccessResponse processRequest() throws Exception {
        ResetAffiliateUserSuccessResponse obj = null;
        if (generateToken()) {
            if (resetAffiliateUser()) {
                if (mdbc.updateAUPasswordToken(req.getUser_id(), req.getPasswordToken())) {
                    obj = generateResponse(true);
                }
            } else {
                obj = generateResponse(false);
            }
        } else {
            obj = generateResponse(false);
        }
        return obj;
    }

    @Override
    public ResetAffiliateUserSuccessResponse generateResponse(boolean status) {
        ResetAffiliateUserSuccessResponse resp;
        if (status) {
            resp = new ResetAffiliateUserSuccessResponse(ResponseMsg.RESP_OK, accessToken);
        } else {
            resp = new ResetAffiliateUserSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken);
        }
        return resp;
    }

    private void sendSetPasswordEmail(String email, String passwordToken, String name) throws Exception {
        AffiliateEmail.sendAffiliateUserResetPassword(email, passwordToken, name);
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
