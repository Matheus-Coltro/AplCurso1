package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import br.com.aplcurso.model.Estado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstadoCarregar", urlPatterns = {"/EstadoCarregar"})
public class EstadoCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            EstadoDAO dao = new EstadoDAO();
            Estado oEstado = (Estado) dao.carregar(id);
            request.setAttribute("estado", oEstado);
            request.getRequestDispatcher("/cadastros/estado/estadoCadastrar.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao carregar Estado: " + ex.getMessage());
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