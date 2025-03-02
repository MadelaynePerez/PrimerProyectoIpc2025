/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author DELL
 */
public class EncriptarContrasenia {
    
    public String contraseniaEncriptada(String contrasenia){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(contrasenia.getBytes());

            // Convierte el hash en una cadena hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
                return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }    
}
