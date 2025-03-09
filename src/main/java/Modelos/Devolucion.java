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
public class Devolucion {
    private int idDevolucion;
    private Venta venta;
    private Date fechaDevolucion;
    private double perdida;
    private ComputadoraEnsamblada ComputadoraEnsamblada;

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    public Devolucion(int idDevolucion, Venta venta, Date fechaDevolucion, double perdida, ComputadoraEnsamblada ensamblada) {
        this.idDevolucion = idDevolucion;
        this.venta = venta;
        this.fechaDevolucion = fechaDevolucion;
        this.perdida = perdida;
        this.ComputadoraEnsamblada = ensamblada;
    }

    public ComputadoraEnsamblada getComputadoraEnsamblada() {
        return ComputadoraEnsamblada;
    }

    public void setComputadoraEnsamblada(ComputadoraEnsamblada ComputadoraEnsamblada) {
        this.ComputadoraEnsamblada = ComputadoraEnsamblada;
    }
    
    
}
