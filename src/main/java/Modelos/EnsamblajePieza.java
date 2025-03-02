/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author DELL
 */
public class EnsamblajePieza {

    private int idEnsamblaje;
    private Computadora computadora;
    private Componente componente;
    private int cantidad;

    public int getIdEnsamblaje() {
        return idEnsamblaje;
    }

    public void setIdEnsamblaje(int idEnsamblaje) {
        this.idEnsamblaje = idEnsamblaje;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public EnsamblajePieza(int idEnsamblaje, Computadora computadora, Componente componente, int cantidad) {
        this.idEnsamblaje = idEnsamblaje;
        this.computadora = computadora;
        this.componente = componente;
        this.cantidad = cantidad;
    }

}
