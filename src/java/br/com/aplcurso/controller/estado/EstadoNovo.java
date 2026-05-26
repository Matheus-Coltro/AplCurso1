package br.com.aplcurso.controller.estado;

import br.com.aplcurso.model.Estado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstadoNovo", urlPatterns = {"/EstadoNovo"})
public class EstadoNovo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            Estado oEstado = new Estado();
            request.setAttribute("estado", oEstado);
            request.getRequestDispatcher("/cadastros/estado/estadoCadastrar.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao abrir novo Estado: " + ex.getMessage());
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