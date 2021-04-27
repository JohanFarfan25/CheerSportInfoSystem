package com.club.utils;

import com.itextpdf.text.pdf.codec.Base64;
import static com.sun.xml.ws.security.trust.sts.BaseSTSImpl.ENCRYPT_KEY;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public final class AuthenticationUtils {
    
    /**
     * Returns SHA-256 encoded string
     * @param password - the string to be encoded
     * @return SHA-256 encoded string
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the system
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the system
     */
    public static String encodeSHA256(String password) 
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }
    
    
    public static String decryptSHA256(String password) throws Exception {
    byte[] encryptedBytes=Base64.decode( password.replace("\n", "") );
         
    Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
 
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, aesKey);
 
    String decrypted = new String(cipher.doFinal(encryptedBytes));
         
    return decrypted;
    }
        
        
   //private static String decrypt(String encrypted) throws Exception {
    //byte[] encryptedBytes=Base64.decode( encrypted.replace("\n", "") );
         
    //Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
 
    //Cipher cipher = Cipher.getInstance("AES");
    //cipher.init(Cipher.DECRYPT_MODE, aesKey);
 
    //String decrypted = new String(cipher.doFinal(encryptedBytes));
         
    //return decrypted;
    //}     
        
    
}