/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DatosDB.QueryEnsambleComputadora;
import DatosDB.QueryVenta;
import Dto.ComputadoraVenta;
import Dto.VentaDto;
import Modelos.Cliente;
import Modelos.Computadora;
import Modelos.ComputadoraEnsamblada;
import Services.EnsambleComputadoraService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ana
 */
@WebServlet(name = "RegistrarVentaServlet", urlPatterns = {"/RegistrarVentaServlet"})
public class RegistrarVentaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarVentaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarVentaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        
        
        Cliente cliente = new Cliente(-1, nit, nombre, direccion);
        
        HttpSession session = request.getSession();
        List<ComputadoraVenta> computadoras = (List<ComputadoraVenta>) session.getAttribute("ventas"); 
        
        
        List<Computadora> computadorasSeleccionadas = new ArrayList<>();
        List <VentaDto> ventascompus = new ArrayList<>();
        
      
        for (ComputadoraVenta computadora : computadoras) {
            String cantidadStr = request.getParameter("compra-" + computadora.getIdCompotadora());
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > 0) {
                    VentaDto ventaas = new VentaDto(computadora.getIdCompotadora(), cantidad);
                    ventascompus.add(ventaas);
                    QueryVenta queryVenta = new QueryVenta();
                    queryVenta.RegistrarVenta(cliente, ventascompus);
                   
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
