package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import br.com.aplcurso.model.Estado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstadoCadastrar", urlPatterns = {"/EstadoCadastrar"})
public class EstadoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            EstadoDAO dao = new EstadoDAO();

            int id = Integer.parseInt(request.getParameter("id"));
            String nomeEstado  = request.getParameter("nomeestado");
            String siglaEstado = request.getParameter("siglaestado").toUpperCase().trim();

            if (siglaEstado.length() != 2) {
                // Sigla deve ter exatamente 2 caracteres
                response.getWriter().write("3");
            } else if (dao.siglaExiste(siglaEstado) && id == 0) {
                // Sigla já cadastrada (só bloqueia na inclusão)
                response.getWriter().write("4");
            } else if (nomeEstado.isBlank() || nomeEstado.isEmpty()) {
                // Nome vazio
                response.getWriter().write("5");
            } else {
                Estado oEstado = new Estado();
                oEstado.setId(id);
                oEstado.setNomeEstado(nomeEstado);
                oEstado.setSiglaEstado(siglaEstado);

                if (dao.cadastrar(oEstado)) {
                    response.getWriter().write("1"); // sucesso
                } else {
                    response.getWriter().write("0"); // erro ao gravar
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Estado: " + ex.getMessage());
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