/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.intfc.processreq;

import com.vaydeal.partner.mongo.mod.VerifyToken;

/**
 *
 * @author rifaie
 */
public interface NPProcessor extends Processor{

    /**
     *
     * @return 
     * @throws Exception
     */
    public int changePassword() throws Exception;

    /**
     *
     * @return 
     * @throws Exception
     */
    public long updateTokenStatus() throws Exception;

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    public VerifyToken getUserId(String token) throws Exception;
}