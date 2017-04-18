/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.support.controller;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.DBConnect;
import java.sql.SQLException;

/**
 * @company techvay
 * @author rifaie
 */
public class BlockAffiliateUser {
    private final String affiliate_user_id;
    private final DBConnect dbc;

    public BlockAffiliateUser(String affiliate_user_id) throws ClassNotFoundException, SQLException {
        this.affiliate_user_id = affiliate_user_id;
        this.dbc = DB.getConnection();
    }
    
    public boolean block() throws SQLException{
        if(dbc.blockAffiliate(affiliate_user_id)){
            return true;
        }
        return false;
    }
}
