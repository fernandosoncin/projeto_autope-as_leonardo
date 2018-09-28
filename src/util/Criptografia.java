package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Fellipe
 */
public class Criptografia {
    public static String converter(String original){
        try {
            StringBuilder hexString = new StringBuilder();
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
            
            for(byte b : messageDigest){
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            //
        }
        return "";
    }
    
}
