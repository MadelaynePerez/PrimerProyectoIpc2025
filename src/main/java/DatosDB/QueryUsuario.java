/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDB;

import Modelos.Coneccion;
import Modelos.Usuario;
import Utils.EncriptarContrasenia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class QueryUsuario {
    public boolean crear(Usuario usuario)  {
     Connection connection = null;
        PreparedStatement pstmt = null;
        EncriptarContrasenia encriptado = new EncriptarContrasenia();

        try {
            connection = Coneccion.getConnection();
            String sql = "INSERT INTO Usuario (nombre_usuario, password, id_rol) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, usuario.getNombreUsuario());
            pstmt.setString(2, encriptado.contraseniaEncriptada(usuario.getPassword())); 
            pstmt.setInt(3, usuario.getRol().getIdRol()); // Guardar ID del rol

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (Exception e) {
            Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QueryUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false; 
    }
}


