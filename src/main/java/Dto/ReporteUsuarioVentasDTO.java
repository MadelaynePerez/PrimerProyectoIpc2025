/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class ReporteUsuarioVentasDTO {

    private int idUsuario;
    private String nombreUsuario;
    private int cantidadVentas;
    private double totalVentas;
    private List<ReporteVentaDTO> ventas;

    public ReporteUsuarioVentasDTO(int idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.cantidadVentas = 0;
        this.totalVentas = 0;
        this.ventas = new ArrayList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public List<ReporteVentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<ReporteVentaDTO> ventas) {
        this.ventas = ventas;
    }

    public void addVenta(ReporteVentaDTO venta) {
        this.ventas.add(venta);
        this.cantidadVentas++;
        this.totalVentas += venta.getTotalVenta();
    }
}
