/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class ReporteVentaDTO {

    private int idVenta;
    private Date fechaVenta;
    private String nombreCliente;
    private String nitCliente;
    private double totalVenta;
    private List<DetalleProductoVentaDTO> productos;

    public ReporteVentaDTO(int idVenta, Date fechaVenta, String nombreCliente, String nitCliente, double totalVenta) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.nombreCliente = nombreCliente;
        this.nitCliente = nitCliente;
        this.totalVenta = totalVenta;
        this.productos = new ArrayList<>();
    }

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

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public List<DetalleProductoVentaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleProductoVentaDTO> productos) {
        this.productos = productos;
    }

    public void addProducto(DetalleProductoVentaDTO producto) {
        this.productos.add(producto);
    }
}
