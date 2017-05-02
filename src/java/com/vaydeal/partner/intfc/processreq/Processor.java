/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.intfc.processreq;

/**
 *
 * @author rifaie
 */
public interface Processor {

    /**
     *
     * @return 
     * @throws java.lang.Exception
     */
    public Object processRequest() throws Exception;
    
    /**
     *
     * @param status
     * @return
     */
    public Object generateResponse(boolean status);
    
    public void closeConnection() throws Exception;

}