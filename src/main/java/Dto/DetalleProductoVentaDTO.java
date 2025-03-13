/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author Ana
 */
public class DetalleProductoVentaDTO {

    private int idEnsamblado;
    private String nombreComputadora;
    private double precioUnitario;
    private int cantidad;

    public DetalleProductoVentaDTO(int idEnsamblado, String nombreComputadora, double precioUnitario, int cantidad) {
        this.idEnsamblado = idEnsamblado;
        this.nombreComputadora = nombreComputadora;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
