package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashdoardController {
	
	@GetMapping("/")
	public String index() {
		return "index";
		
	}
}
