package org.sid.dao;

import java.util.List;

import org.sid.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query("SELECT c FROM Client c WHERE c.nom LIKE :x")
	public Page<Client> chercherClient(@Param("x")String mc, Pageable pageable);
	
	@Query("SELECT c FROM Client c ORDER BY c.code DESC")
	public List<Client> findAll();
}
