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
public class ReporteComputadoraVendidaDTO {

    private int idComputadora;
    private String nombreComputadora;
    private int cantidadVendida;
    private double totalVentas;
    private List<VentaComputadoraDTO> ventas;

    public ReporteComputadoraVendidaDTO(int idComputadora, String nombreComputadora) {
        this.idComputadora = idComputadora;
        this.nombreComputadora = nombreComputadora;
        this.cantidadVendida = 0;
        this.totalVentas = 0;
        this.ventas = new ArrayList<>();
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public List<VentaComputadoraDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaComputadoraDTO> ventas) {
        this.ventas = ventas;
    }

    public void addVenta(VentaComputadoraDTO venta) {
        this.ventas.add(venta);
        this.cantidadVendida += venta.getCantidad();
        this.totalVentas += venta.getTotal();
    }
}
