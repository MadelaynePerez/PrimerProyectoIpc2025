/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author Ana
 */
public class ProductoGananciaDTO {

    private int idEnsamblado;
    private String nombreComputadora;
    private double costoEnsamblaje;
    private double precioVenta;
    private double ganancia;
    private int cantidadVendida;

    public ProductoGananciaDTO(int idEnsamblado, String nombreComputadora, double costoEnsamblaje,
            double precioVenta, int cantidadVendida) {
        this.idEnsamblado = idEnsamblado;
        this.nombreComputadora = nombreComputadora;
        this.costoEnsamblaje = costoEnsamblaje;
        this.precioVenta = precioVenta;
        this.cantidadVendida = cantidadVendida;
        this.ganancia = (precioVenta - costoEnsamblaje) * cantidadVendida;
    }

    public int getIdEnsamblado() {
        return idEnsamblado;
    }

    public void setIdEnsamblado(int idEnsamblado) {
        this.idEnsamblado = idEnsamblado;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public double getCostoEnsamblaje() {
        return costoEnsamblaje;
    }

    public void setCostoEnsamblaje(double costoEnsamblaje) {
        this.costoEnsamblaje = costoEnsamblaje;
        this.ganancia = (this.precioVenta - costoEnsamblaje) * this.cantidadVendida;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
        this.ganancia = (precioVenta - this.costoEnsamblaje) * this.cantidadVendida;
    }

    public double getGanancia() {
        return ganancia;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
        this.ganancia = (this.precioVenta - this.costoEnsamblaje) * cantidadVendida;
    }
}
