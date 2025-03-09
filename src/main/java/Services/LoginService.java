/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryUsuario;
import Modelos.Usuario;
import Utils.EncriptarContrasenia;

/**
 *
 * @author DELL
 */
public class LoginService {
    
    public Usuario Login(String username, String password){
        QueryUsuario queryUsuario = new QueryUsuario();
        EncriptarContrasenia encriptador = new EncriptarContrasenia();
        return queryUsuario.login(username, encriptador.contraseniaEncriptada(password));
    }
}
