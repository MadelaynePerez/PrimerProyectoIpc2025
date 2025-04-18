/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class ComputadoraEnsamblada {

    private int idEnsamblado;
    private Computadora computadora;
    private Usuario usuario;
    private Date fechaEnsamblaje;
    private double costoTotal;
    private boolean vendido;
    private double nuevoPrecio;

    public double getNuevoPrecio() {
        return nuevoPrecio;
    }

    public void setNuevoPrecio(double nuevoPrecio) {
        this.nuevoPrecio = nuevoPrecio;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
    
    public int getIdEnsamblado() {
        return idEnsamblado;
    }

    public void setIdEnsamblado(int idEnsamblado) {
        this.idEnsamblado = idEnsamblado;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaEnsamblaje() {
        return fechaEnsamblaje;
    }

    public void setFechaEnsamblaje(Date fechaEnsamblaje) {
        this.fechaEnsamblaje = fechaEnsamblaje;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public ComputadoraEnsamblada() {

    }

    public ComputadoraEnsamblada(int idEnsamblado, Computadora computadora, Usuario usuario, Date fechaEnsamblaje, double costoTotal, boolean vendido, double nuevoPrecio) {
        this.idEnsamblado = idEnsamblado;
        this.computadora = computadora;
        this.usuario = usuario;
        this.fechaEnsamblaje = fechaEnsamblaje;
        this.costoTotal = costoTotal;
        this.vendido= vendido;
        this.nuevoPrecio= nuevoPrecio;
    }

}
