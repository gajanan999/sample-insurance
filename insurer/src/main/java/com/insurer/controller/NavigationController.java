package com.insurer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class NavigationController {

	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public String openAdminConfigurationPage(){
	    return "adminPanel";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/home")
	public String openIndexPage(){
	    return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/contact")
	public String openContactPage(){
	    return "contact";
	}
}
