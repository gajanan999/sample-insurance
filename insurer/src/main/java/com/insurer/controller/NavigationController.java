package com.insurer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurer.service.RestService;
import com.insurer.vo.RestRequestsDetails;



@Controller
public class NavigationController {

	@Autowired
	RestService restService;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public String openAdminConfigurationPage(Model model){
		
		RestRequestsDetails restRequestsDetails=new RestRequestsDetails();
		restRequestsDetails.setRequestList(restService.getRestRequestEntityList());
		model.addAttribute("restRequestsDetails", restRequestsDetails);
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
