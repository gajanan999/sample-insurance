package com.insurer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.insurer.entities.RestRequestEntity;
import com.insurer.service.RestService;
import com.insurer.vo.RestRequestsDetails;



@Controller
public class RestConfigurationController {
	
	@Autowired
	RestService restService;

	@RequestMapping(method=RequestMethod.POST, value="/updateRequests")
	public ModelAndView doUpdate(RestRequestsDetails restRequestsDetails,Model model){
		
		
		if(null != restRequestsDetails.getRequestList() && restRequestsDetails.getRequestList().size()>0) {
			for(RestRequestEntity req:restRequestsDetails.getRequestList()) {
				restService.updateRestRequestEntity(req);
			}
		}
		
		return new ModelAndView("redirect:/admin");
	    //return "adminPanel";
	}
}
