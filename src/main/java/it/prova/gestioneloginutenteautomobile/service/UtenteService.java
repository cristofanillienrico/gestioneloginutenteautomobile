package it.prova.gestioneloginutenteautomobile.service;

import java.util.List;

import it.prova.gestioneloginutenteautomobile.dao.UtenteDAO;
import it.prova.gestioneloginutenteautomobile.model.Utente;

public interface UtenteService {

	// per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;

	public Utente trovaConUsernameEPassword(String usernameInput,String passwordInput);

}
