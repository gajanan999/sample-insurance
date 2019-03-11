package com.insurer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurer.entities.RestRequestEntity;



@Controller
public class NavigationController {

	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public String openAdminConfigurationPage(Model model){
		
		List<RestRequestEntity> requestList=new ArrayList<>();
		RestRequestEntity restRequestEntity= new RestRequestEntity();
		restRequestEntity.setId(1);
		restRequestEntity.setUrl("http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-1/rest/qoute");
		restRequestEntity.setUsername("insurer");
		restRequestEntity.setPassword("password");
		restRequestEntity.setMethodType("GET");
		restRequestEntity.setConsumeType("application/json");
		restRequestEntity.setProduceType("");
		restRequestEntity.setRequestBody("{'tpnr':'45654', 'age'=45, 'experience'=30}");
		requestList.add(restRequestEntity);
		
		model.addAttribute("requestList", requestList);
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
