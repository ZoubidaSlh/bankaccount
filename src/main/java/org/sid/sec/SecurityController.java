package org.sid.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping(value="/login")
	public String login(){
		return "/users/login";
	}
	
	@RequestMapping(value="/")
	public String home(){
		
		return "redirect:/";
	}	
	
	@RequestMapping(value="/403")
	public String accessDenied(){
		
		return "403";
	}
	
}