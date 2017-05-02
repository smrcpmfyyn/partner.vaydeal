/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.processreq;

import com.vaydeal.partner.db.DB;
import com.vaydeal.partner.db.MongoConnect;
import com.vaydeal.partner.hash.Hashing;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class ProcessLogout {
    private final String at;
    private final MongoConnect mdbc;

    public ProcessLogout(String at) throws Exception {
        this.at = at;
        this.mdbc = DB.getMongoConnection();
    }
    
    public String logout() throws Exception{
        mdbc.logout(at);
        mdbc.closeConnection();
        Random ran = new Random();
        String ts = at + ran.nextLong();
        return Hashing.genAccessToken(ts);
    }
}
