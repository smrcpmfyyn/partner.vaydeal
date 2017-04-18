/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.GetPaymentsValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
//import com.vaydeal.partner.mongo.mod.AdminID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.GetPayments;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @company techvay
 * @author rifaie
 */
public class GetPaymentsConstraints implements GetPaymentsValidator {

    private final GetPayments req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public GetPaymentsConstraints(GetPayments req) throws Exception {
        this.req = req;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateQuery() throws Exception {
        String valid = ErrMsg.ERR_QUERY;
        String regX = RegX.REGX_DIGIT;
        String query = req.getQuery();
        if (validate(query, regX)) {
//            if(qu)
//            if (!queryType.equals("nothing")) {
//                valid = CorrectMsg.CORRECT_QUERY;
//                req.setQueryType(queryType);
//            } else {
//                valid = ErrMsg.ERR_QUERY_NO_RESULT;
//            }
        }
        return valid;
    }

    @Override
    public String validateOffset() throws Exception {
//        String valid = ErrMsg.ERR_OFFSET;
//        String regx = RegX.REGX_DIGIT;
//        if (validate(req.getPageNo(), regx)) {
//            if (validate(req.getMaxEntries(), regx)) {
//                valid = CorrectMsg.CORRECT_OFFSET;
//            }
//        }
//        return valid;
        return "";
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
//        String valid = ErrMsg.ERR_ACCESS_TOKEN;
//        AdminID admin = mdbc.getAdminID(at);
//        if (!admin.getProfile_id().startsWith(ErrMsg.ERR_MESSAGE)) {
//            if (dbc.checkNBAdminID(admin.getProfile_id())) {
////                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
//                req.setAdmin_id(admin.getProfile_id());
//                req.setUtype(admin.getType());
//            } else {
////                valid = ErrMsg.ERR_AT_BLOCKED;
//            }
//        }
//        return valid;
        return "";
    }

    @Override
    public String validateAffiliate(String affiliate) throws Exception {
//        String valid = ErrMsg.ERR_ADMIN_TYPE;
//        HashSet<String> types = dbc.getSubTypes(req.getUtype());
//        if (types.contains(adminType)) {
//            valid = CorrectMsg.CORRECT_ADMIN_TYPE;
//        }
//        return valid;
        return "";
    }

    @Override
    public boolean validate(String value, String regX) {
        boolean valid = false;
        if (value.matches(regX)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public void closeConnection() throws SQLException {
        dbc.closeConnection();
    }
}
