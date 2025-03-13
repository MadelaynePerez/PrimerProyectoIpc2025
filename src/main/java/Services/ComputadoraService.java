/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryComputadora;
import Modelos.Computadora;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ComputadoraService {
    
    private final QueryComputadora QueryComputadora = new QueryComputadora();
    
    public boolean CrearComputadora(String nombre, double precioVenta){
        return this.QueryComputadora.crear(new Computadora(-1,nombre,precioVenta));
    }
    
    public List<Computadora> ListarComputadoras(){
        return this.QueryComputadora.listarComputadoras();
    }
    public List<Computadora> listarComputadorasParaEnsamblar(){
        return this.QueryComputadora.listarComputadorasParaEnsamblar();
    }
    public Computadora encontrarPorId (int idComputadora){
        return this.QueryComputadora.encontrarPorId(idComputadora);
    }
}
