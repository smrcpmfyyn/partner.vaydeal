/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.processreq.NPProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.resp.mod.NPSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessNP implements NPProcessor{
    
    private final NewPassword req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public ProcessNP(NewPassword req) throws Exception {
        this.req = req;
        dbc = DB.getConnection();
        mdbc = DB.getMongoConnection();
    }

    @Override
    public int changePassword() throws Exception {
        int cp = dbc.changePassword(req);
        dbc.closeConnection();
        return cp;
    }

    @Override
    public long updateTokenStatus() throws Exception {
        long ts = mdbc.updateTokenStatus(req);
        return ts;
    }

    @Override
    public NPSuccessResponse processRequest() throws Exception {
        NPSuccessResponse response = null;
        try{
            req.setUserid(getUserId(req.getToken()).getUser_id());
            int cp = changePassword();
            if(cp == 1){
                long ts = updateTokenStatus();
                if(ts == 1){
                    response = generateResponse(true);
                }else{
                    response = generateResponse(false);
                }
            }else{
                response = generateResponse(false);
            }
        }catch(Exception ex){
            throw ex;
        }
        return response;
    }

    @Override
    public NPSuccessResponse generateResponse(boolean status) {
        NPSuccessResponse resp;
        if (status) {
            resp = new NPSuccessResponse(ResponseMsg.RESP_OK);
        } else {
            resp = new NPSuccessResponse(ResponseMsg.RESP_NOT_OK);
        }
        return resp;
    }

    @Override
    public VerifyToken getUserId(String token) throws Exception {
        VerifyToken vt = mdbc.getUserId(token);
        return vt;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}
