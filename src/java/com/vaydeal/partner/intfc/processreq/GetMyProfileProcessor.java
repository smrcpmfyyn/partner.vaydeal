/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.intfc.processreq;

/**
 * @company techvay
 * @author rifaie
 */
public interface GetMyProfileProcessor extends Processor {
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean generateToken() throws Exception;
    
    public boolean getMyProfile() throws Exception;
}
