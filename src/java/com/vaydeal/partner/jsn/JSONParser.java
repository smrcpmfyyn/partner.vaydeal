/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.jsn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import com.vaydeal.partner.result.ChangePasswordResult;
import com.vaydeal.partner.result.GetAffiliateUsersResult;
import com.vaydeal.partner.result.GetMyProfileResult;
import com.vaydeal.partner.result.GetPaymentsResult;
import com.vaydeal.partner.result.LogResult;
import com.vaydeal.partner.result.RPResult;
import com.vaydeal.partner.result.RequestPromotionResult;
import com.vaydeal.partner.result.UpdateProfileResult;
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

    public static AffiliateID parseJSONAID(String aff) throws IOException {
        AffiliateID res;
        res = MAPPER.readValue(aff, AffiliateID.class);
        return res;
    }

    public static GetPaymentsResult parseJSONGAUR(String reqv) throws IOException {
        GetPaymentsResult res;
        res = MAPPER.readValue(reqv, GetPaymentsResult.class);
        return res;
    }

    public static GetMyProfileResult parseJSONGMPR(String reqv) throws IOException {
        GetMyProfileResult res;
        res = MAPPER.readValue(reqv, GetMyProfileResult.class);
        return res;
    }

    public static UpdateProfileResult parseJSONUPR(String reqv) throws IOException {
        UpdateProfileResult res;
        res = MAPPER.readValue(reqv, UpdateProfileResult.class);
        return res;
    }

    public static ChangePasswordResult parseJSONCPR(String reqv) throws IOException {
        ChangePasswordResult res;
        res = MAPPER.readValue(reqv, ChangePasswordResult.class);
        return res;
    }

    public static GetAffiliateUsersResult parseJSONGAUsR(String reqv) throws IOException {
        GetAffiliateUsersResult res;
        res = MAPPER.readValue(reqv, GetAffiliateUsersResult.class);
        return res;
    }
}
