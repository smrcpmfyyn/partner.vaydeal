/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @company techvay
 * @author rifaie
 */
public final class Hashing {

    /**
     *
     * @param length
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSalt(final int length) throws NoSuchAlgorithmException {
        String salt;
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] saltb = new byte[length];
        sr.nextBytes(saltb);
        salt = EncodeDecode.encode(saltb);
        return salt;
    }

    /**
     *
     * @param password
     * @param salt
     * @param iterations
     * @param keyLength
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hash;
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKey key = skf.generateSecret(spec);
        byte[] res = key.getEncoded();
        hash = EncodeDecode.encode(res);
        return hash;
    }

    /**
     *
     * @param valuetoHash
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String genVKey(final String valuetoHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String hash;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(valuetoHash.getBytes("UTF-8"));
        hash = EncodeDecode.encode(bytes);
        return hash;
    }
    
    public static String genAccessToken(final String valuetoHash) throws Exception{
        String hash;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(valuetoHash.getBytes("UTF-8"));
        hash = EncodeDecode.encode(bytes);
        String token = "";
        Random ran = new Random();
        for(int i=0;i<44;i++){
            token += hash.charAt(ran.nextInt(85));
        }      
        return token;
    }

}
