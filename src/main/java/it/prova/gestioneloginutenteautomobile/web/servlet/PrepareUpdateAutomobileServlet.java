package it.prova.gestioneloginutenteautomobile.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestioneloginutenteautomobile.service.MyServiceFactory;


@WebServlet("/PrepareUpdateAutomobileServlet")
public class PrepareUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PrepareUpdateAutomobileServlet() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user_info") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String idAutomobileParam = request.getParameter("idAutomobile");

        if (!NumberUtils.isCreatable(idAutomobileParam)) {
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {

            request.setAttribute("automobile_da_modificare", MyServiceFactory.getAutomobileServiceInstance().caricaSingoloElemento(Long.parseLong(idAutomobileParam)));

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/automobile/edit.jsp").forward(request, response);
	}

}
