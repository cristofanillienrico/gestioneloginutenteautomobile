package it.prova.gestioneloginutenteautomobile.dao;

import java.util.List;

import it.prova.gestioneloginutenteautomobile.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {
	
	public List<Automobile> findByExample(Automobile automobileInput) throws Exception;

}
