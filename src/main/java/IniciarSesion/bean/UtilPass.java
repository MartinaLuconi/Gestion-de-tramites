/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion.bean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author marti
 */

public class UtilPass {
    public static String obtieneMD5(String cadena)
    {
    String hashedOutput="";
    String stringToHash = cadena; 
    MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

    messageDigest.update(stringToHash.getBytes());
    byte[] digiest = messageDigest.digest();
     hashedOutput = DatatypeConverter.printHexBinary(digiest);
    System.out.println(hashedOutput);
            } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    return hashedOutput;
    }
}
