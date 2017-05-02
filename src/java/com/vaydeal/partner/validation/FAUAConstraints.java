/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.validation;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.intfc.validation.FAUAValidator;
import com.vaydeal.partner.message.CorrectMsg;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.regx.RegX;
import com.vaydeal.partner.req.mod.AffiliateActivityFilter;
import com.vaydeal.partner.req.mod.FAUA;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @company techvay
 * @author rifaie
 */
public class FAUAConstraints implements FAUAValidator {

    private final FAUA req;
    private final DBConnect dbc;
    private final MongoConnect mdbc;

    public FAUAConstraints(FAUA gu) throws Exception {
        this.req = gu;
        this.mdbc = DB.getMongoConnection();
        this.dbc = DB.getConnection();
    }

    @Override
    public String validateFilter() throws Exception {
        AffiliateActivityFilter ftr = req.getFtr();
        String valid = "";
        valid += validateUID(ftr) + "#";
        valid += validateUType(ftr) + "#";
        valid += validateActivity(ftr) + "#";
        valid += validateEntryStatus(ftr);
        return valid;
    }

    private String validateUID(AffiliateActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_UID;
        String regX = RegX.REGX_DIGIT;
        if (ftr.getUid() != null) {
            String aid = ftr.getUid();
            if (validate(aid, regX)) {
                if (dbc.checkNBAffiliateID(aid)) {
                    valid = CorrectMsg.CORRECT_FTR_UID;
                } else {
                    valid = ErrMsg.ERR_FTR_UID_NOT_EXISTS;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_UID;
        }
        return valid;
    }
    
    private String validateUType(AffiliateActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_UTYPE;
        String regX = RegX.REGX_STRING_UPPER_AND_LOWER;
        if (ftr.getuType() != null) {
            String[] utypes = ftr.getuType();
            for (String utype : utypes) {
                if (validate(utype, regX)) {
                    if (utype.matches("super")||utype.matches("sub")) {
                        valid = CorrectMsg.CORRECT_FTR_UTYPE;
                    } else {
                        valid = ErrMsg.ERR_FTR_UTYPE_NOT_EXISTS;
                        break;
                    }
                } else {
                    valid = ErrMsg.ERR_FTR_UTYPE_NOT_EXISTS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_UTYPE;
        }
        return valid;
    }

    private String validateActivity(AffiliateActivityFilter ftr) throws SQLException {
        String valid = ErrMsg.ERR_FTR_ACTIVITY;
        String regX = RegX.REGX_ACTIVITY;
        if (ftr.getActivity() != null) {
            String[] activities = ftr.getActivity();
            for (String activity : activities) {
                if (validate(activity, regX)) {
                    if (dbc.checkAffiliateActivity(activity)) {
                        valid = CorrectMsg.CORRECT_FTR_ACTIVITY;
                    } else {
                        valid = ErrMsg.ERR_FTR_ACTIVITY_NOT_EXISTS;
                        break;
                    }
                } else {
                    valid = ErrMsg.ERR_FTR_ACTIVITY_NOT_EXISTS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_ACTIVITY;
        }
        return valid;
    }

    private String validateEntryStatus(AffiliateActivityFilter ftr) {
        String valid = ErrMsg.ERR_ENTRY_STATUS;
        ArrayList<String> al = new ArrayList<>();
        al.add("valid");
        al.add("invalid");
        al.add("blocked");
        if (ftr.getEntryStatus() != null) {
            String[] status = ftr.getEntryStatus();
            for (String stat : status) {
                if (al.contains(stat)) {
                    valid = CorrectMsg.CORRECT_FTR_ENTRY_STATUS;
                }else{
                    valid = ErrMsg.ERR_ENTRY_STATUS;
                    break;
                }
            }
        } else {
            valid = CorrectMsg.CORRECT_FTR_ENTRY_STATUS;
        }
        return valid;
    }

    @Override
    public String validateAccessToken() throws Exception {
        String at = req.getAt();
        String valid = ErrMsg.ERR_ACCESS_TOKEN;
        AffiliateID aff = mdbc.getAffiliateID(at);
        if (!aff.getUser_id().startsWith(ErrMsg.ERR_MESSAGE)) {
            if (dbc.checkNBAffiliateID(aff.getUser_id())) {
                valid = CorrectMsg.CORRECT_ACCESS_TOKEN;
                req.setAffiliate_user_id(aff.getUser_id());
                req.setUser_type(aff.getUser_type());
                req.setAffiliate(aff.getAffiliate());
            } else {
                valid = ErrMsg.ERR_AT_BLOCKED;
            }
        }
        return valid;
    }

    @Override
    public String validateUserType(String adminType) throws Exception {
        String valid = ErrMsg.ERR_USER_TYPE;
        String uType = req.getUser_type();
        if (uType.matches("super")) {
            valid = CorrectMsg.CORRECT_USER_TYPE;
        }
        return valid;
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
        mdbc.closeConnection();
    }

    
}
