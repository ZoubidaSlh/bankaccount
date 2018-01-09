package org.sid.dao;

import java.util.List;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteEpargneRepository extends CompteRepository {
	List<Compte> findAll();
}
