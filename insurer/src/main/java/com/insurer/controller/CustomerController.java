package com.insurer.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurer.service.RestService;
import com.insurer.vo.CustomerDetails;
import com.insurer.vo.QuoteVo;

@Controller
public class CustomerController {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	RestService restService;
	
	static Locale local=new Locale("en");
	
	@SuppressWarnings("deprecation")
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public String doSearch(CustomerDetails customerDetails,Model model){
		System.out.println("here");
		System.out.println(customerDetails.toString());
		// do your conditional logic in here to check if form parameters were populated or not
	    // then do something about getting results from the repository
	    /*List<String> students = repository.find....;
	    // return a model and a view (just as an example)
	    ModelAndView mv = new ModelAndView();
	    mv.addObject(students);
	    mv.setViewName("/results");*/
		if(!NumberUtils.isNumber(customerDetails.getDrivingExp())) {
			model.addAttribute("msg", messageSource.getMessage("INVALID_EXPERIENCE", new Object[0],local));
			return "index";
		}
		
		List<QuoteVo> quoteList=new ArrayList<>();
		try {
			quoteList = restService.getRestReponse(customerDetails);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "greeting";
		}
		
		
		model.addAttribute("quotes", quoteList);
	    return "qoutes";
	}
}
