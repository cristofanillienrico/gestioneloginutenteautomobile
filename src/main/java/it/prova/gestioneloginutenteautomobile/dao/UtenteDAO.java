package it.prova.gestioneloginutenteautomobile.dao;

import it.prova.gestioneloginutenteautomobile.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Utente findByUsernameAndPassword(String usernameInput,String passwordInput);
	
	
	
	

}
