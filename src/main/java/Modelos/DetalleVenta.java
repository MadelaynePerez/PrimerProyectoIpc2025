/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author DELL
 */
public class DetalleVenta {
     private int idDetalle;
    private Venta venta;
    private ComputadoraEnsamblada computadoraEnsamblada;
    private int cantidad;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ComputadoraEnsamblada getComputadoraEnsamblada() {
        return computadoraEnsamblada;
    }

    public void setComputadoraEnsamblada(ComputadoraEnsamblada computadoraEnsamblada) {
        this.computadoraEnsamblada = computadoraEnsamblada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleVenta(int idDetalle, Venta venta, ComputadoraEnsamblada computadoraEnsamblada, int cantidad) {
        this.idDetalle = idDetalle;
        this.venta = venta;
        this.computadoraEnsamblada = computadoraEnsamblada;
        this.cantidad = cantidad;
    }
    
    
}
