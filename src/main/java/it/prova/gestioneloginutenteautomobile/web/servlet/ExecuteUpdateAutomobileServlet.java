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

@WebServlet("/ExecuteUpdateAutomobileServlet")
public class ExecuteUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user_info") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String idParam = request.getParameter("idAutomobile");
		String marcaParam = request.getParameter("marca");
		String modelloParam = request.getParameter("modello");
		String cilindrataParam = request.getParameter("cilindrata");
		String dataImmatricolazioneParam = request.getParameter("dataImmatricolazione");

		Date dataImmatricolazioneParsed = UtilityArticoloForm.parseDateArrivoFromString(dataImmatricolazioneParam);

		if (!UtilityArticoloForm.validateInput(marcaParam, modelloParam, cilindrataParam, dataImmatricolazioneParam)
				|| dataImmatricolazioneParsed == null
				|| UtilityArticoloForm.avoidNumberException(cilindrataParam) == null) {

			Automobile automobileTemp = new Automobile();
			automobileTemp.setId(Long.parseLong(idParam));
			automobileTemp.setMarca(marcaParam);
			automobileTemp.setModello(modelloParam);

			if (UtilityArticoloForm.avoidNumberException(cilindrataParam) != null)
				automobileTemp
						.setCilindrata(Integer.parseInt(UtilityArticoloForm.avoidNumberException(cilindrataParam)));

			automobileTemp.setDataImmatricolazione(dataImmatricolazioneParsed);

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("automobile_da_modificare", automobileTemp);
			request.getRequestDispatcher("/automobile/edit.jsp").forward(request, response);

			return;

		}

		Automobile automobileInstance = new Automobile(marcaParam, modelloParam, Integer.parseInt(cilindrataParam),
				dataImmatricolazioneParsed);

		automobileInstance.setId(Long.parseLong(idParam));

		try {

			MyServiceFactory.getAutomobileServiceInstance().aggiorna(automobileInstance);
			request.setAttribute("listaAutomobiliAttribute", MyServiceFactory.getAutomobileServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;

		}

		request.getRequestDispatcher("automobile/result.jsp").forward(request, response);
	}

}
