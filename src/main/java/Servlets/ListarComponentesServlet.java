/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Modelos.Componente;
import Modelos.Usuario;
import Services.ComponenteService;
import Services.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "ListarComponentesServlet", urlPatterns = {"/ListarComponentesServlet"})
public class ListarComponentesServlet extends HttpServlet {

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
            out.println("<title>Servlet ListarComponentesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarComponentesServlet at " + request.getContextPath() + "</h1>");
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
        ComponenteService componenteService = new ComponenteService();
        List<Componente> componentes = componenteService.ListarComponentes(true);
        request.setAttribute("componentes", componentes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EnsamblajeFabrica/listarComponentes.jsp");
        dispatcher.forward(request, response);
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

        String action = request.getParameter("action");
        int idComponente = Integer.parseInt(request.getParameter("id"));
        ComponenteService componenteService = new ComponenteService();

        switch (action) {
            case "modificar":

                String nombre = request.getParameter("nombre");
                int stock = Integer.parseInt(request.getParameter("stock"));
                double precio = Double.parseDouble(request.getParameter("precio"));
                boolean resultado = componenteService.EditarComponente(idComponente, nombre, precio, stock);
                if (resultado) {
                    request.setAttribute("mensaje", "componente modificado correctamente");
                } else {
                    request.setAttribute("mensaje", "Error, no se pudo modificar :c");
                }

                break;
            case "eliminar":
              boolean eliminar =  componenteService.EliminarComponente(idComponente);
                if (eliminar) {
                    request.setAttribute("mensaje", "componente modificado correctamente");
                } else {
                    request.setAttribute("mensaje", "Error, no se puede eliminar :c");
                }
                break;
        }
        doGet(request, response);
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
