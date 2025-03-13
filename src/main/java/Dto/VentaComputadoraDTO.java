/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.sql.Date;

/**
 *
 * @author Ana
 */
public class VentaComputadoraDTO {

    private int idVenta;
    private Date fechaVenta;
    private String nombreCliente;
    private int cantidad;
    private double precioUnitario;
    private double total;

    // Constructor
    public VentaComputadoraDTO(int idVenta, Date fechaVenta, String nombreCliente,
            int cantidad, double precioUnitario) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = cantidad * precioUnitario;
    }

    // Getters y setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.total = cantidad * this.precioUnitario;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.total = this.cantidad * precioUnitario;
    }

    public double getTotal() {
        return total;
    }
}
