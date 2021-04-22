package it.prova.gestioneloginutenteautomobile.dao;

public class MyDaoFactory {

	private static UtenteDAO uteneteDaoInstance = null;
	private static AutomobileDAO automobileDaoInstance = null;


	public static UtenteDAO getUtenteDAOInstance() {
		if (uteneteDaoInstance == null)
			uteneteDaoInstance = new UtenteDAOImpl();

		return uteneteDaoInstance;
	}
	
	public static AutomobileDAO getAutomobileDAOInstance() {
		if (automobileDaoInstance == null)
			automobileDaoInstance = new AutomobileDAOImpl();

		return automobileDaoInstance;
	}

}
