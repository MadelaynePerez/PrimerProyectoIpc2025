/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryUsuario;
import Modelos.Rol;
import Modelos.Usuario;
import java.util.List;

/**
 *
 * @author DELL
 */
public class UsuarioService {

    private final QueryUsuario QueryUsuario = new QueryUsuario();

    public boolean CrearUsuario(String usuario, String password, int idRol) {
        Usuario user = new Usuario(-1, usuario, password, new Rol(idRol, ""));
        return this.QueryUsuario.crear(user);
    }

    public boolean DesactivarUsuario(int userId) {
        return this.QueryUsuario.inactivarUsuario(userId);
    }

    public List<Usuario> ListarUsuarios() {
        return this.QueryUsuario.listarUsuarios();
    }
    
    public boolean CambiarRolDeUsuario(int rolId, int userId){
        return this.QueryUsuario.CambiarRol(userId, rolId);
    }
}
