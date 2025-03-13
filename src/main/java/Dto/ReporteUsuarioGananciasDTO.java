/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author Ana
 */
public class ReporteUsuarioGananciasDTO {

    private int idUsuario;
    private String nombreUsuario;
    private double totalGanancias;
    private ReporteGananciaDTO reporteGanancia;

    public ReporteUsuarioGananciasDTO(int idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.totalGanancias = 0;
        this.reporteGanancia = new ReporteGananciaDTO();
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

    public double getTotalGanancias() {
        return totalGanancias;
    }

    public void setTotalGanancias(double totalGanancias) {
        this.totalGanancias = totalGanancias;
    }

    public ReporteGananciaDTO getReporteGanancia() {
        return reporteGanancia;
    }

    public void setReporteGanancia(ReporteGananciaDTO reporteGanancia) {
        this.reporteGanancia = reporteGanancia;
        this.totalGanancias = reporteGanancia.getGananciaTotal();
    }
}
