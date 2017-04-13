/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.intfc.processreq.GetNoOfAffiliatesProcessor;
import com.vaydeal.partner.message.ResponseMsg;
import com.vaydeal.partner.resp.mod.GetNoOfAffiliatesSuccessResponse;

/**
 *
 * @author USER
 */
public class ProcessGetNoOfAffiliates implements GetNoOfAffiliatesProcessor{
    private final DBConnect dbc;
    private int noOfAffiliates;

    public ProcessGetNoOfAffiliates() throws Exception{
        this.dbc = DB.getConnection();
    }

    @Override
    public GetNoOfAffiliatesSuccessResponse processRequest() throws Exception {
        GetNoOfAffiliatesSuccessResponse response = null;
        getNoOfAffiliates();
        response = generateResponse(true);
        return response;
    }

    @Override
    public GetNoOfAffiliatesSuccessResponse generateResponse(boolean status) {
        GetNoOfAffiliatesSuccessResponse SResp = new GetNoOfAffiliatesSuccessResponse(ResponseMsg.RESP_OK, noOfAffiliates);
        return SResp;
   }

    @Override
    public void getNoOfAffiliates() throws Exception {
        noOfAffiliates = dbc.getNoOfAffiliates();
    }
}
