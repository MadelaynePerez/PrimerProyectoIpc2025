/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DatosDB.QueryEnsamblajePieza;
import Modelos.Componente;
import Modelos.Computadora;
import Modelos.EnsamblajePieza;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class EnsamblePiezaService {

    QueryEnsamblajePieza QueryEnsamblePieza = new QueryEnsamblajePieza();
    
    public boolean RegistrarEnsamblePiezas(int idComputadora, String[] piezasIds, String[] cantidades) {
        List<EnsamblajePieza> piezas = new ArrayList<>();
        for (int i = 0; i < piezasIds.length; i++) {
            int idPieza = Integer.parseInt(piezasIds[i]);
            int cantidad = Integer.parseInt(cantidades[i]);

            EnsamblajePieza pieza = new EnsamblajePieza(-1, new Computadora(idComputadora, "", 0), new Componente(idPieza, "", 0, 0), cantidad);
            this.QueryEnsamblePieza.crear(pieza);
        }
        
        return true;
    }
}
