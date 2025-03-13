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
public class ReporteDevolucionDTO {

    private int idDevolucion;
    private int idVenta;
    private Date fechaVenta;
    private Date fechaDevolucion;
    private String nombreCliente;
    private String nitCliente;
    private double totalVenta;
    private double perdida;
    private int idEnsamblado;
    private String nombreComputadora;

    public ReporteDevolucionDTO(int idDevolucion, int idVenta, Date fechaVenta, Date fechaDevolucion,
            String nombreCliente, String nitCliente, double totalVenta, double perdida,
            int idEnsamblado, String nombreComputadora) {
        this.idDevolucion = idDevolucion;
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.fechaDevolucion = fechaDevolucion;
        this.nombreCliente = nombreCliente;
        this.nitCliente = nitCliente;
        this.totalVenta = totalVenta;
        this.perdida = perdida;
        this.idEnsamblado = idEnsamblado;
        this.nombreComputadora = nombreComputadora;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
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

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
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

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
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
}
