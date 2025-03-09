/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryEnsambleComputadora;
import Modelos.ComputadoraEnsamblada;
import Utils.ConvertidorFecha;
import java.util.List;

public class EnsambleComputadoraService {

    private final QueryEnsambleComputadora QueryEnsambleComputadora = new QueryEnsambleComputadora();
    private final ConvertidorFecha ConvertidorFecha = new ConvertidorFecha();

    public boolean Ensamblar(int computadoraId, int userId) {
        return this.QueryEnsambleComputadora.ensamblarComputadora(computadoraId, userId, ConvertidorFecha.ObtenerFechaActual());
    }

    public List<ComputadoraEnsamblada> ListarComputadorasEnsambladas(boolean orden) {
        return this.QueryEnsambleComputadora.listarComputadorasEnsambladas(orden);
    }
    public boolean stockNecesarioParaEnsamblar(int idComputadora) {
        return this.QueryEnsambleComputadora.stockNecesarioParaEnsamblar(idComputadora);
    }
}
