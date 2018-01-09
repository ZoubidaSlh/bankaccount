package org.sid.web;

import java.util.Date;
import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteEpargneRepository;
import org.sid.dao.CompteRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankAccountController {

	@Autowired
	private IBanqueMetier banqueMetier;

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private CompteEpargneRepository compteEpargeRepository;
	// private CompteCourantRepository compteCourantRepository;
	@Autowired
	private ClientRepository clientRepository;

	final private String RESSOURCE_NAME = "/bank-account";

	/**
	 * Listing CompteEpargne & CompteCourant : GET
	 */
	@RequestMapping(RESSOURCE_NAME)
	public String index(Model model) {

		try {
			List<Compte> listComptesEpargne = compteEpargeRepository.findAll();
			List<Compte> listComptesCourant = compteRepository.findAll();

			model.addAttribute("listComptesEpargne", listComptesEpargne);
			model.addAttribute("listComptesCourant", listComptesCourant);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return RESSOURCE_NAME + "/list";
	}

	/**
	 * Create new Account Action : GET
	 */
	@RequestMapping(value = RESSOURCE_NAME + "/create", method = RequestMethod.GET)
	public String create(Model model) {
		List<Client> listClients = clientRepository.findAll();
		model.addAttribute("listClients", listClients);
		return RESSOURCE_NAME + "/create";
	}

	/**
	 * Create new Account Action : POST
	 */
	@RequestMapping(value = RESSOURCE_NAME + "/create", method = RequestMethod.POST)
	public String create(Model model, String typeCompte, String codeCompte, String solde, String decouvert, String taux,
			Client client) {
		// return "redirect:" + RESSOURCE_NAME + "?typeCompte=" + typeCompte;

		try {
			if (typeCompte.equals("CC")) {
				Compte cc = new CompteCourant(codeCompte, new Date(), Double.parseDouble(solde), client,
						Double.parseDouble(decouvert));
				compteRepository.save(cc);
			} else if (typeCompte.equals("CE")) {
				Compte ce = new CompteEpargne(codeCompte, new Date(), Double.parseDouble(solde), client,
						Double.parseDouble(taux));
				compteRepository.save(ce);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:" + RESSOURCE_NAME + "?codeCompte=" + codeCompte + "&error=" + e.getMessage();
		}

		return "redirect:" + RESSOURCE_NAME;
	}

	/**
	 * Create new Account Action : GET
	 */
	@RequestMapping(value = RESSOURCE_NAME + "/operations", method = RequestMethod.GET)
	public String operations(Model model, String codeCompte, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			model.addAttribute("compte", cp);
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listOperations", pageOperations.getContent());
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		return RESSOURCE_NAME + "/operations";
	}

	/**
	 * The operations for Operation : POST
	 */
	@RequestMapping(value = RESSOURCE_NAME + "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {

		try {
			if (typeOperation.equals("versement")) {
				banqueMetier.verser(codeCompte, montant);
			} else if (typeOperation.equals("retrait")) {
				banqueMetier.retirer(codeCompte, montant);
			} else if (typeOperation.equals("virement")) {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error=" + e.getMessage();
		}

		return "redirect:" + RESSOURCE_NAME + "/operations?codeCompte=" + codeCompte;
	}

}
