package org.sid.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientsController {

	@Autowired
	private ClientRepository clientRepository;
	final private String RESSOURCE_NAME = "/clients";
	
	/**
	The operations for Client
	 */
	
	@RequestMapping(RESSOURCE_NAME)
	public String index(Model model, String mc,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="100") int size){
		model.addAttribute("motCle", mc);
		try{
			List<Client> listClients = clientRepository.findAll();
			model.addAttribute("listClients", listClients);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		return RESSOURCE_NAME + "/list";
	}

	@RequestMapping(value=RESSOURCE_NAME + "/create", method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("client", new Client());
		return RESSOURCE_NAME + "/create";
	}
	
	@RequestMapping(value=RESSOURCE_NAME + "/createEdit", method=RequestMethod.POST)
	public String createEdit(Model model, @Valid Client client, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "redirect:" + RESSOURCE_NAME + "/create";
		clientRepository.save(client);
		return "redirect:" + RESSOURCE_NAME;
	}
	
	@RequestMapping(value="/clients/delete", method=RequestMethod.GET)
	public String delete(int id) {
		clientRepository.delete(id);
		return "redirect:" + RESSOURCE_NAME;
	}

}
