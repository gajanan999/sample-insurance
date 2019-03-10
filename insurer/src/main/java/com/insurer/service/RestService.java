package com.insurer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.insurer.vo.QuoteVo;
import com.insurer.vo.CustomerDetails;
import com.insurer.vo.Quote;
import com.insurer.vo.RequestPolicyVo;

@Service
public class RestService {

	private static final Logger log = LoggerFactory.getLogger(RestService.class);
	
	private RestTemplate restTemplate =null;
	
	public RestTemplate getRestTemplate() {
		if(null == restTemplate) {
			return new RestTemplate();
		}
		return restTemplate;
	}
	static String URL="http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-2/rest/policy";
	public List<QuoteVo> getRestReponse(CustomerDetails customerDetails) throws ParseException{
		List<QuoteVo> quoteList=new ArrayList<>();

		RequestPolicyVo requestPolicyVo=new RequestPolicyVo();
		requestPolicyVo.setTechnicalPassportNr(customerDetails.getTpnr());
		requestPolicyVo.setAge(30);
		requestPolicyVo.setExperience(Integer.valueOf(customerDetails.getDrivingExp()));
		
		LinkedHashMap<String, Double> map=new LinkedHashMap<>();
	
		//map = restTemplate.postForObject(URL, requestPolicyVo, LinkedHashMap.class);
		map=getRest(URL,"POST",requestPolicyVo,map);
	    log.info(map + " " + map.toString());
	    System.out.println(map);
	    quoteList.add(getQuoteObject("Insurer-1", "Yes", 9000.0, 80.0, map.get("qoute")));
	    
	  
	    String url="http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-1/rest/qoute";
	    Date date=new SimpleDateFormat("yyyy-MM-dd").parse(customerDetails.getDob());
	    String dateInFormat=new SimpleDateFormat("dd.MM.yyyy").format(date);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	            .queryParam("tpnr",customerDetails.getTpnr())
	            .queryParam("dob", dateInFormat);
	    
	
	    System.out.println(builder.toUriString());
	   // String result = restTemplate.getForObject(builder.toUriString(),  String.class);
	   // Quote quoteVoXML  = restTemplate.getForObject(builder.toUriString(),  Quote.class);
	    Quote quoteVoXML =new Quote();
	    quoteVoXML=getRest(builder.toUriString(),"GET",null,quoteVoXML);
	    quoteList.add(getQuoteObject("Insurer-2", "Yes", 7500.0, 100.0, quoteVoXML.getPremium()));
	    System.out.println(quoteVoXML.toString());
	    
	  //  quoteList.add(quote);
		return quoteList;
	}
	
	public QuoteVo getQuoteObject(String insurerName,String cashless,Double idv,Double zeroDepricationAddon,Double premium) {
		QuoteVo quoteVo = new QuoteVo();
		quoteVo.setInsurer(insurerName);
		quoteVo.setCashless(cashless);
		quoteVo.setIdv(idv);
		quoteVo.setZeroDepricationAddon(zeroDepricationAddon);
		quoteVo.setPremium(premium);
		return quoteVo;
	}
	
	
	
	public <T,K> T getRest(String url,String httpMethod,K request,T reponse) {
		RestTemplate restTemplate = getRestTemplate();
		restTemplate=getRestTemplateWithAuth(restTemplate);
		if(httpMethod.equals("GET")) {
			reponse = (T) restTemplate.getForObject(url,  reponse.getClass());
		}else if(httpMethod.equals("POST")){
			reponse=(T) restTemplate.postForObject(URL, request, reponse.getClass());
		}
		
		return reponse;
	}
	
	
	public RestTemplate getRestTemplateWithAuth(RestTemplate restTemplate) {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("insurer", "password"));
		return restTemplate;	
	}
	
	public HttpHeaders  getHttpHeaders() {
		String plainCreds = "insurer:password";
		byte[] plainCredsBytes = plainCreds.getBytes();
		Encoder encoder=Base64.getEncoder();
		byte[] base64CredsBytes =  encoder.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}
	
}
;