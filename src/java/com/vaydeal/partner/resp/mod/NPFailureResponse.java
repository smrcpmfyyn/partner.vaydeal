/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.resp.mod;

import com.vaydeal.partner.result.RPResult;
/**
 * @company techvay
 * @author rifaie
 */
public class NPFailureResponse extends RPFailureResponse{

        public NPFailureResponse(RPResult rpr, String error) {
        super(rpr, error);
    }
        
}
