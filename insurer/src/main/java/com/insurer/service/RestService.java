package com.insurer.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
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
import com.insurer.vo.QuoteVoXML;
import com.insurer.vo.RequestPolicyVo;

@Service
public class RestService {

	 private static final Logger log = LoggerFactory.getLogger(RestService.class);
	
	 static String URL="http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-2/rest/policy";
	public List<QuoteVo> getRestReponse(){
		List<QuoteVo> quoteList=new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate=getRestTemplateWithAuth(restTemplate);
		
		
		RequestPolicyVo requestPolicyVo=new RequestPolicyVo();
		requestPolicyVo.setTechnicalPassportNr("12136");
		requestPolicyVo.setAge(20);
		requestPolicyVo.setExperience(10);
		
		LinkedHashMap<String, Double> map=new LinkedHashMap<>();
	
		map = restTemplate.postForObject(URL, requestPolicyVo, LinkedHashMap.class);

	    log.info(map + " " + map.toString());
	    System.out.println(map);
	    
	    
	    
	    HttpHeaders headers=getHttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
	    String url="http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-1/rest/qoute";
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	            .queryParam("tpnr", "1213214134")
	            .queryParam("dob", "29.09.1994");
	    
	  //  Object o=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, Object.class);
	   // System.out.println(o.toString());
	    System.out.println(builder.toUriString());
	    String result = restTemplate.getForObject(builder.toUriString(),  String.class);
		   
		
	   // QuoteVoXML quoteVoXM= restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, QuoteVoXML.class);
	   // QuoteVoXML quoteVoXML = response.getBody();
	    System.out.println(result);
	    
	  //  quoteList.add(quote);
		return quoteList;
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