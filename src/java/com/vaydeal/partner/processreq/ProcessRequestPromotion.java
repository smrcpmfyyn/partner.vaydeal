/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.intfc.processreq.RequestPromotionProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.RequestPromotion;
import com.vaydeal.partner.resp.mod.RequestPromotionSuccessResponse;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessRequestPromotion implements RequestPromotionProcessor {

    private final RequestPromotion req;
    private final DBConnect dbc;

    public ProcessRequestPromotion(RequestPromotion req) throws Exception {
        this.req = req;
        dbc = DB.getConnection();
    }

    @Override
    public boolean addPromotionRequest() throws Exception {
        int c = dbc.addPromotionRequest(req);
        return c==1;
    }

    @Override
    public RequestPromotionSuccessResponse processRequest() throws Exception {
        RequestPromotionSuccessResponse response = null;
        try {
            if (addPromotionRequest()) {
                response = generateResponse(true);
            } else {
                response = generateResponse(false);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    @Override
    public RequestPromotionSuccessResponse generateResponse(boolean status) {
        RequestPromotionSuccessResponse resp;
        if (status) {
            resp = new RequestPromotionSuccessResponse(ResponseMsg.RESP_OK);
        } else {
            resp = new RequestPromotionSuccessResponse(ResponseMsg.RESP_NOT_OK);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
    }

}
