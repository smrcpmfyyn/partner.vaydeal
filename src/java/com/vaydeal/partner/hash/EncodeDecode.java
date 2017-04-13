/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.hash;

import java.util.Base64;

/**
 * @company techvay
 * @author rifaie
 */

public final class EncodeDecode {
    
    /**
     *
     * @param input
     * @return
     */
    public static String encode(byte [] input){
        String encode = Base64.getEncoder().encodeToString(input);
        return encode;
    }
    
    /**
     *
     * @param input
     * @return
     */
    public static byte [] decode(String input){
        byte [] decode = Base64.getDecoder().decode(input);
        return decode;
    }
}

