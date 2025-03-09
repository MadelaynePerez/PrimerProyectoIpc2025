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

    public ComputadoraEnsamblada(int idEnsamblado, Computadora computadora, Usuario usuario, Date fechaEnsamblaje, double costoTotal) {
        this.idEnsamblado = idEnsamblado;
        this.computadora = computadora;
        this.usuario = usuario;
        this.fechaEnsamblaje = fechaEnsamblaje;
        this.costoTotal = costoTotal;
    }

}
