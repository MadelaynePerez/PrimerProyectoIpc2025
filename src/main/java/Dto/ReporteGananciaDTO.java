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
public class ReporteGananciaDTO {

    private double gananciaTotal;
    private List<ProductoGananciaDTO> productos;

    public ReporteGananciaDTO() {
        this.gananciaTotal = 0;
        this.productos = new ArrayList<>();
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public List<ProductoGananciaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoGananciaDTO> productos) {
        this.productos = productos;
    }

    public void addProducto(ProductoGananciaDTO producto) {
        this.productos.add(producto);
        this.gananciaTotal += producto.getGanancia();
    }
}
