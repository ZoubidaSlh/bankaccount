package org.sid.dao;

import java.util.List;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends JpaRepository<Compte, String>, CrudRepository<Compte, String> {
	@Query("SELECT compte FROM Compte compte ")
	public List<Compte> findAll();
}
