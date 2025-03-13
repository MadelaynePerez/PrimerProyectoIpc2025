/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryComponente;
import Modelos.Componente;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ComponenteService {
    
    private final QueryComponente queryComponente = new QueryComponente();
    
    public boolean RegistrarComponente(String nombre, double coso, int stock){
        Componente componente = new Componente(-1, nombre, coso, stock);
        return this.queryComponente.crear(componente);
    }
    
    public boolean EditarComponente(int idComponente, String nombre, double costo, int cantidadStock){
        return this.queryComponente.editarComponente(idComponente, nombre,costo,cantidadStock);
    }
    
    public boolean EliminarComponente(int idComponente){
        return this.queryComponente.eliminarComponente(idComponente);
    }
    
    public List<Componente> ListarComponentes(boolean orden){
        return this.queryComponente.listarComponentes(orden);
    }
    public List<Componente> obtenerComponentesSinStock(){
        return this.queryComponente.obtenerComponentesSinStock();
    }
}
