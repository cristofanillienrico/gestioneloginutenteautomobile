package it.prova.gestioneloginutenteautomobile.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneloginutenteautomobile.model.StatoUtente;
import it.prova.gestioneloginutenteautomobile.service.MyServiceFactory;
import it.prova.gestioneloginutenteautomobile.service.UtenteService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameParam = request.getParameter("inputUsername");
		String passwordParam = request.getParameter("inputPassword");

		UtenteService serviceUtente = MyServiceFactory.getUtenteServiceInstance();

		// validazione
		if (usernameParam != null && usernameParam == "" && passwordParam != null && passwordParam == "") {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		// && serviceUtente.trovaConUsernameEPassword(usernameParam,
		// passwordParam).getStato() == StatoUtente.ATTIVO
		try {
			if (serviceUtente.trovaConUsernameEPassword(usernameParam, passwordParam) == null) {

				request.setAttribute("errorMessage", "Accesso non consentito");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			} else {
				
				request.getSession().setAttribute("user_info",
						serviceUtente.trovaConUsernameEPassword(usernameParam, passwordParam));
				request.getRequestDispatcher("automobile/ricerca.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}

}
