/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.jsn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import com.vaydeal.partner.req.mod.AffiliateActivityFilter;
import com.vaydeal.partner.resp.mod.Activity;
import com.vaydeal.partner.result.AddAffiliateUserResult;
import com.vaydeal.partner.result.ChangeAffiliateUserStatusResult;
import com.vaydeal.partner.result.ChangePasswordResult;
import com.vaydeal.partner.result.FAUAResult;
import com.vaydeal.partner.result.GetAffiliateUserIdsResult;
import com.vaydeal.partner.result.GetAffiliateUsersResult;
import com.vaydeal.partner.result.GetFilterResult;
import com.vaydeal.partner.result.GetMyProfileResult;
import com.vaydeal.partner.result.GetPaymentsResult;
import com.vaydeal.partner.result.LogResult;
import com.vaydeal.partner.result.RPResult;
import com.vaydeal.partner.result.RequestPromotionResult;
import com.vaydeal.partner.result.ResetAffiliateUserResult;
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

    public static ChangeAffiliateUserStatusResult parseJSONCAUSR(String reqv) throws IOException {
        ChangeAffiliateUserStatusResult res;
        res = MAPPER.readValue(reqv, ChangeAffiliateUserStatusResult.class);
        return res;
    }

    public static ResetAffiliateUserResult parseJSONRAUR(String reqv) throws IOException {
        ResetAffiliateUserResult res;
        res = MAPPER.readValue(reqv, ResetAffiliateUserResult.class);
        return res;
    }

    public static AddAffiliateUserResult parseJSONAAU(String reqv) throws IOException {
        AddAffiliateUserResult res;
        res = MAPPER.readValue(reqv, AddAffiliateUserResult.class);
        return res;
    }

    public static GetAffiliateUserIdsResult parseJSONGAUsI(String reqv) throws IOException {
        GetAffiliateUserIdsResult res;
        res = MAPPER.readValue(reqv, GetAffiliateUserIdsResult.class);
        return res;
    }

    public static AffiliateActivityFilter parseJSONAF(String ftr) throws IOException {
        AffiliateActivityFilter res;
        res = MAPPER.readValue(ftr, AffiliateActivityFilter.class);
        return res;
    }

    public static FAUAResult parseJSONFAUA(String reqv) throws IOException {
        FAUAResult res;
        res = MAPPER.readValue(reqv, FAUAResult.class);
        return res;
    }

    public static Activity parseJSONActivity(String act) throws IOException {
        Activity res;
        res = MAPPER.readValue(act, Activity.class);
        return res;
    }

    public static GetFilterResult parseJSONGFR(String reqv) throws IOException {
        GetFilterResult res;
        res = MAPPER.readValue(reqv, GetFilterResult.class);
        return res;
    }
}
