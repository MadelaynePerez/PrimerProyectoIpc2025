/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author Ana
 */
public class DevolucionDto {

    private String nombreComputadora;
    private int idEnsamble;
    private double precio;
    private int idVenta;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DevolucionDto(String nombreComputadora, int idEnsamble, double precio, int idVenta) {
        this.nombreComputadora = nombreComputadora;
        this.idEnsamble = idEnsamble;
        this.precio = precio;
        this.idVenta = idVenta;
    }

}
