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
import com.vaydeal.partner.intfc.processreq.GetPaymentsProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.req.mod.GetPayments;
import com.vaydeal.partner.resp.mod.GetPaymentsSuccessResponse;
import com.vaydeal.partner.resp.mod.AffiliatePayments;
import java.util.ArrayList;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessGetPayments implements GetPaymentsProcessor{
    
    private final GetPayments req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;
    private String accessToken;
    private String totalPaid;
    private String activePayments;
    private ArrayList<AffiliatePayments> ap;

    public ProcessGetPayments(GetPayments gu) throws Exception{
        this.req = gu;
        this.dbc = DB.getConnection();
        this.mdbc =DB.getMongoConnection();
        this.ap = new ArrayList<AffiliatePayments>();
    }

    @Override
    public boolean generateToken() throws Exception {
        Random ran = new Random();
        String ts = req.getAt() + ran.nextLong();
        accessToken = Hashing.genAccessToken(ts);
        return mdbc.updateAccessToken(req.getAffiliate_user_id(), accessToken);
    }

    @Override
    public boolean getPayments() throws Exception {
        dbc.getAffiliatePayments(req,ap);
        totalPaid = dbc.getTotalPayment(req.getAffiliate());
        activePayments = dbc.getActivePayments(req.getAffiliate());
        return ap.size()>0;
    }

    @Override
    public GetPaymentsSuccessResponse processRequest() throws Exception {
        GetPaymentsSuccessResponse obj = null;
        if (generateToken()) {
            if (getPayments()) {
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
    public GetPaymentsSuccessResponse generateResponse(boolean status) {
        GetPaymentsSuccessResponse resp;
        if (status) {
            resp = new GetPaymentsSuccessResponse(ResponseMsg.RESP_OK, accessToken,ap,totalPaid,activePayments);
        } else {
            resp = new GetPaymentsSuccessResponse(ResponseMsg.RESP_NOT_OK, accessToken,totalPaid,activePayments);
        }
        return resp;
    }
    
    @Override
    public void closeConnection() throws Exception {
        dbc.closeConnection();
        mdbc.closeConnection();
    }

}

