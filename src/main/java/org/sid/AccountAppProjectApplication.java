package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AccountAppProjectApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired 
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(AccountAppProjectApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		Client c1 = clientRepository.save(new Client("Zoubida", "z.aitsaleh@mundiapolis.ma"));
//		
//		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
//		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 12000, c2, 5.5 ));
//		
//		operationRepository.save(new Versement(new Date(), 8000, cp1));
//		operationRepository.save(new Versement(new Date(), 1000, cp1));
//		operationRepository.save(new Versement(new Date(), 2000, cp1));
//		operationRepository.save(new Retrait(new Date(), 1000, cp1));
//		
//		operationRepository.save(new Versement(new Date(), 7000, cp2));
//		operationRepository.save(new Versement(new Date(), 1500, cp2));
//		operationRepository.save(new Versement(new Date(), 3000, cp2));
//		operationRepository.save(new Retrait(new Date(), 2000, cp2));
//		
//		banqueMetier.verser("c1", 11111);
//		clientRepository.delete(3);
		
	}
}
