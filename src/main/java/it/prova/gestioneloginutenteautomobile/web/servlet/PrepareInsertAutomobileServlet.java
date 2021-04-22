package it.prova.gestioneloginutenteautomobile.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PrepareInsertAutomobileServlet")
public class PrepareInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user_info") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);

	}

}
