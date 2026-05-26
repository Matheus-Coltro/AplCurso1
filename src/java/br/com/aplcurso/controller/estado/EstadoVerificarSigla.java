package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstadoVerificarSigla", urlPatterns = {"/EstadoVerificarSigla"})
public class EstadoVerificarSigla extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try {
            EstadoDAO dao = new EstadoDAO();
            String sigla = request.getParameter("sigla").toUpperCase().trim();
            if (dao.siglaExiste(sigla)) {
                response.getWriter().write("1"); // já existe
            } else {
                response.getWriter().write("0"); // livre
            }
        } catch (Exception ex) {
            System.out.println("Erro ao verificar sigla: " + ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}