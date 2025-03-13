/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DatosDB.QueryDevolucion;
import DatosDB.QueryVenta;
import Modelos.Computadora;
import Modelos.Devolucion;
import Modelos.Venta;
import Services.ComputadoraService;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "RealizarDevolucionServlet", urlPatterns = {"/RealizarDevolucionServlet"})
public class RealizarDevolucionServlet extends HttpServlet {

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
            out.println("<title>Servlet RealizarDevolucionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RealizarDevolucionServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        int id = Integer.parseInt(request.getParameter("ensamble").toString());

        QueryVenta queryVenta = new QueryVenta();
        Venta venta = queryVenta.encontrarVentaPorId(id);

        if (venta != null) {

            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaSieteDiasDespues = venta.getFechaVenta().toLocalDate().plusDays(7);
            int idVenta = Integer.parseInt(request.getParameter("idVenta").toString());
            QueryDevolucion queryDevolucion = new QueryDevolucion();
            if (fechaActual.isAfter(fechaSieteDiasDespues)) {

                if (queryDevolucion.buscarPorIdventayIdEnsamblado(idVenta, id) != null) {
                    request.setAttribute("mensaje", "este producto ya ha sido devuelto.");
                } else {
                    ComputadoraService computadoraService = new ComputadoraService();

                    queryDevolucion.crear(idVenta, id);
                    request.setAttribute("mensaje", "Devolución hecha con éxito.");

                }

            } else {
                request.setAttribute("mensaje", "No se puede hacer la devolución, ya que la venta tiene más de 7 días.");

            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("puntoDeVenta/RegistroDevolucion.jsp");
        dispatcher.forward(request, response);
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
