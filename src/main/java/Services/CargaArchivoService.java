/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryComponente;
import DatosDB.QueryComputadora;
import DatosDB.QueryUsuario;
import Modelos.Rol;
import Modelos.Usuario;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class CargaArchivoService {

    public void CargarArchivo(ArrayList<String> lineas) {
        QueryComponente queryComponente = new QueryComponente();
        QueryUsuario queryUsuario = new QueryUsuario();
        QueryComputadora queryComputadora = new QueryComputadora();
        
        for (int i = 0; i < lineas.size(); i++) {
            String recuperarPalabra = lineas.get(i);

            int inicio = recuperarPalabra.indexOf("(") + 1;
            int fin = recuperarPalabra.lastIndexOf(")");
            String contenido = recuperarPalabra.substring(inicio, fin);
            String[] valores = contenido.split(",");

            if (recuperarPalabra.startsWith("USUARIO")) {

                String usuario = valores[0].replace("\"", "").trim();
                String password = valores[1].replace("\"", "").trim();
                int rol = Integer.parseInt(valores[2].trim());
                
                Usuario user = new Usuario(-1, usuario, password, new Rol(rol, ""));

                queryUsuario.crear(user);
                

            } else if (recuperarPalabra.startsWith("PIEZA")) {

                String pieza = valores[0].replace("\"", "").trim();
                double precio = Double.parseDouble(valores[1].trim());
                
                

            } else if (recuperarPalabra.startsWith("COMPUTADORA")) {
                String nombreComputadora = valores[0].replace("\"", "").trim();
                double precioComputadora = Double.parseDouble(valores[1].trim());

            } else if (recuperarPalabra.startsWith("ENSAMBLE_PIEZA")) {
                String nombreEnsamblaje = valores[0].replace("\"", "").trim();
                String descripcionPieza = valores[1].replace("\"", "").trim();
                int cantidad = Integer.parseInt(valores[2].trim());

            } else if (recuperarPalabra.startsWith("ENSAMBLAR_COMPUTADORA")) {
                String tipoComputadora = valores[0].replace("\"", "").trim(); 
                String usuario = valores[1].trim(); // 
                String fecha = valores[2].replace("\"", "").trim(); 
            }
        }

    }
}
