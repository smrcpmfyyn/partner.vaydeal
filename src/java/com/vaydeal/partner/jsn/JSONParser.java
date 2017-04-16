/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.jsn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import com.vaydeal.partner.result.LogResult;
import com.vaydeal.partner.result.RPResult;
import com.vaydeal.partner.result.RequestPromotionResult;
import java.io.IOException;

/**
 * @company techvay
 * @author rifaie
 */
public class JSONParser {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static VerifyToken parseJSONVT(String vt) throws IOException {
        VerifyToken res;
        res = MAPPER.readValue(vt, VerifyToken.class);
        return res;
    }

    public static RPResult parseJSONRP(String reqv) throws IOException {
        RPResult res;
        res = MAPPER.readValue(reqv, RPResult.class);
        return res;
    }

    public static LogResult parseJSONLog(String reqv) throws IOException {
        LogResult res;
        res = MAPPER.readValue(reqv, LogResult.class);
        return res;
    }

    public static RequestPromotionResult parseJSONReqPromo(String reqv) throws IOException {
        RequestPromotionResult res;
        res = MAPPER.readValue(reqv, RequestPromotionResult.class);
        return res;
    }
}
