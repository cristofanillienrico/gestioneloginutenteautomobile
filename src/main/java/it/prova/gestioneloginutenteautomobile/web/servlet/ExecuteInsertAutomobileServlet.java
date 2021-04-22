package it.prova.gestioneloginutenteautomobile.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneloginutenteautomobile.model.Automobile;
import it.prova.gestioneloginutenteautomobile.service.MyServiceFactory;
import it.prova.gestioneloginutenteautomobile.utility.UtilityArticoloForm;

@WebServlet("/ExecuteInsertAutomobileServlet")
public class ExecuteInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("user_info") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// estraggo input
		String marcaInputParam = request.getParameter("marca");
		String modelloInputParam = request.getParameter("modello");
		String cilindrataInputStringParam = request.getParameter("cilindrata");
		String dataImmatricolazioneStringParam = request.getParameter("dataImmatricolazione");

		// questa variabile mi serve in quanto sfrutto in un colpo la validazione
		// della data ed il suo parsing che non posso fare senza un try catch
		// a questo punto lo incapsulo in un metodo apposito
		Date dataImmatricolazioneParsed = UtilityArticoloForm
				.parseDateArrivoFromString(dataImmatricolazioneStringParam);

		// valido input tramite apposito metodo e se la validazione fallisce torno in
		// pagina
		if (!UtilityArticoloForm.validateInput(marcaInputParam, modelloInputParam, cilindrataInputStringParam,
				dataImmatricolazioneStringParam) || dataImmatricolazioneParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		Automobile libroInstance = new Automobile(marcaInputParam, modelloInputParam,
				Integer.parseInt(cilindrataInputStringParam), dataImmatricolazioneParsed);
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getAutomobileServiceInstance().inserisciNuovo(libroInstance);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("automobile/result.jsp").forward(request, response);

	}

}
