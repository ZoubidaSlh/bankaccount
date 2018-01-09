package org.sid.web;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.sid.dao.UserRepository;
import org.sid.entities.User;
import org.sid.entities.UsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

	@Autowired
	private UserRepository userRepository;
	final private String RESSOURCE_NAME = "/users";
	
	/**
		The operations for User
	 */
	
	@RequestMapping(RESSOURCE_NAME)
	public String index(Model model, String mc,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="100") int size){
		model.addAttribute("motCle", mc);
		try{
			List<User> listUsers = (List<User>) userRepository.findAll();
			model.addAttribute("listUsers", listUsers);
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		return RESSOURCE_NAME + "/list";
	}

	@RequestMapping(value=RESSOURCE_NAME + "/create", method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		return RESSOURCE_NAME + "/create";
	}
	
	@RequestMapping(value=RESSOURCE_NAME + "/createEdit", method=RequestMethod.POST)
	public String createEdit(Model model, String username, String password, String[] roles) {
		
		User user = new User(username, GenerateHadhMD5(password));
 
		user = userRepository.save(user);
		for( int i = 0; i < roles.length; i++)
		{
			user.getRoles().add(new UsersRoles(roles[i], user));
		}
		
		userRepository.save(user);
		return "redirect:" + RESSOURCE_NAME + "?" + String.join(";", roles);
	}
	
	@RequestMapping(value="/users/delete", method=RequestMethod.GET)
	public String delete(int id) {
		userRepository.delete(id);
		return "redirect:" + RESSOURCE_NAME;
	}
	
	private String GenerateHadhMD5(String passwordToHash) {

        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}

}
