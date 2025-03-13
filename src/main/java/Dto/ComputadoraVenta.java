/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author Ana
 */
public class ComputadoraVenta {
    private int idCompotadora;
    private double Precioventa;
    private int total;
    private String nombreComputadora;

    public int getIdCompotadora() {
        return idCompotadora;
    }

    public void setIdCompotadora(int idCompotadora) {
        this.idCompotadora = idCompotadora;
    }

    public double getPrecioventa() {
        return Precioventa;
    }

    public void setPrecioventa(double Precioventa) {
        this.Precioventa = Precioventa;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public ComputadoraVenta(int idCompotadora, double Precioventa, int total, String nombreComputadora) {
        this.idCompotadora = idCompotadora;
        this.Precioventa = Precioventa;
        this.total = total;
        this.nombreComputadora = nombreComputadora;
    }
    
}
