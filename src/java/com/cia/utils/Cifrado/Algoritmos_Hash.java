/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cia.utils.Cifrado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author PCSISTEMAS1
 */
public class Algoritmos_Hash {
    
    
     //algoritmos
public static String MD2 = "MD2";
public static String MD5 = "MD5";
public static String SHA1 = "SHA-1";
public static String SHA256 = "SHA-256";
public static String SHA384 = "SHA-384";
public static String SHA512 = "SHA-512";
	
private static String toHexadecimal(byte[] digest){
String hash = "";
       for(byte aux : digest) {
           int b = aux & 0xff >> 4;
           if (Integer.toHexString(b).length() == 1) hash += "0";
	            hash += Integer.toHexString(b);
	        }
	        return hash;
	    }
	 
	 
	    public static String getStringMessageDigest(String param, String algorithm){
	        byte[] digest = null;
	        byte[] buffer = param.getBytes();
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
	            messageDigest.reset();
	            messageDigest.update(buffer);
	            digest = messageDigest.digest();
	        } catch (NoSuchAlgorithmException ex) {
	            System.out.println("Error creando Digest");
	        }
	        return toHexadecimal(digest);
	    } 
    
}
