/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryCliente;
import DatosDB.QueryComponente;
import Modelos.Cliente;

/**
 *
 * @author Ana
 */
public class ClienteService {
    private final QueryCliente queryCliente = new QueryCliente();
    public Cliente obtenerClientePorNit (String nit){
        return this.queryCliente.obtenerClientePorNit(nit);
    } 
}
