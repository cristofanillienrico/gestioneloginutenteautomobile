package it.prova.gestioneloginutenteautomobile.service;

import java.util.List;

import it.prova.gestioneloginutenteautomobile.dao.AutomobileDAO;
import it.prova.gestioneloginutenteautomobile.model.Automobile;

public interface AutomobileService {

	// per injection
	public void setAutomobileDAO(AutomobileDAO automobileDAO);

	public List<Automobile> listAll() throws Exception;

	public Automobile caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	public List<Automobile> trovaDaEsempio(Automobile automobileInput) throws Exception;
}
