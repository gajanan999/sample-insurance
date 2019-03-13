package com.insurer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.insurer.config.CommonUtility;
import com.insurer.dao.RestRequestsRepository;
import com.insurer.entities.RestRequestEntity;
import com.insurer.vo.CustomerDetails;
import com.insurer.vo.Quote;
import com.insurer.vo.QuoteVo;
import com.insurer.vo.RequestPolicyVo;

@Service
public class RestService {

	private static final Logger log = LoggerFactory.getLogger(RestService.class);
	
	private RestTemplate restTemplate =null;
	
	@Autowired
	RestRequestsRepository restRequestsRepository;
	
	@Autowired
	CommonUtility commonUtility;
	
	public RestTemplate getRestTemplate() {
		if(null == restTemplate) {
			return new RestTemplate();
		}
		return restTemplate;
	}
	static String URL="http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-2/rest/policy";
	public List<QuoteVo> getRestReponse(CustomerDetails customerDetails) throws ParseException{
		List<QuoteVo> quoteList=new ArrayList<>();
		List<RestRequestEntity> restRequestEntities=restRequestsRepository.findAll();
		
		for(RestRequestEntity restRequestEntity:restRequestEntities) {
			
			if("GET".equals(restRequestEntity.getMethodType())) {
				
				Quote quoteVoXML =new Quote();
				Double quote=getQuoteFromInsurerTWO(customerDetails,restRequestEntity.getUrl(),quoteVoXML,restRequestEntity.getUsername(),restRequestEntity.getPassword());
				quoteList.add(getQuoteObject(restRequestEntity.getInsurerName(), "Yes", 7500.0, 100.0, quote));
			
				
			}else if("POST".equals(restRequestEntity.getMethodType())) {
				Double quote=getQuoteFromInsurerOne(customerDetails,restRequestEntity.getUrl(),restRequestEntity.getUsername(),restRequestEntity.getPassword());
			    quoteList.add(getQuoteObject(restRequestEntity.getInsurerName(), "Yes", 9000.0, 80.0, quote));
				}
			
		}
		return quoteList;
	}
	
	
	public <T> Double getQuoteFromInsurerTWO(CustomerDetails customerDetails,String url,T response,String username,String password) {
		 
	   // Date date=new SimpleDateFormat("yyyy-MM-dd").parse(customerDetails.getDob());
	    Date date=commonUtility.getDateFromString("yyyy-MM-dd", customerDetails.getDob());
	    //String dateInFormat=new SimpleDateFormat("dd.MM.yyyy").format(date);
	    String dateInFormat=commonUtility.getStringFromDate("dd.MM.yyyy", date);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	            .queryParam("tpnr",customerDetails.getTpnr())
	            .queryParam("dob", dateInFormat);
	    
	
	    System.out.println(builder.toUriString());
	   // String result = restTemplate.getForObject(builder.toUriString(),  String.class);
	  
	   
	    response=getRest(builder.toUriString(),"GET",null,response,username,password);
	    Double qoute=0.0;
	    if(null != response) {
	    	qoute=((Quote) response).getPremium();
	    	System.out.println(response.toString());
	    }
	    
	    
		return qoute;
	}
	
	public Double getQuoteFromInsurerOne(CustomerDetails customerDetails,String url,String username,String password) {
		LocalDate birthday=commonUtility.getLocalDate(commonUtility.getDateFromString("yyyy-MM-dd", customerDetails.getDob()));
		Period period=Period.between(birthday, LocalDate.now());
		RequestPolicyVo requestPolicyVo=new RequestPolicyVo();
		requestPolicyVo.setTechnicalPassportNr(customerDetails.getTpnr());
		requestPolicyVo.setAge(period.getYears());
		requestPolicyVo.setExperience(Integer.valueOf(customerDetails.getDrivingExp()));
		LinkedHashMap<String, Double> map=new LinkedHashMap<>();
		map=getRest(URL,"POST",requestPolicyVo,map,username,password);
		Double qoute=0.0;
		if(map.size()>0) {
			qoute=map.get("qoute");
		}
		
	    log.info(map + " " + map.toString());
	    System.out.println(map);
	    return qoute;
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
	
	
	
	public <T,K> T getRest(String url,String httpMethod,K request,T reponse,String username,String password) {
		try {
			RestTemplate restTemplate = getRestTemplate();
			restTemplate=getRestTemplateWithAuth(restTemplate,username,password);
			if(httpMethod.equals("GET")) {
				reponse = (T) restTemplate.getForObject(url,  reponse.getClass());
			}else if(httpMethod.equals("POST")){
				reponse=(T) restTemplate.postForObject(URL, request, reponse.getClass());
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		
		return reponse;
	}
	
	
	public RestTemplate getRestTemplateWithAuth(RestTemplate restTemplate,String username,String password) {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
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
	
	public List<RestRequestEntity> getRestRequestEntityList() {
		List<RestRequestEntity> requestList=new ArrayList<>();
		
		requestList=restRequestsRepository.findAll();
		if(!(requestList.size()>0)) {
			boolean dataAdded=addRequestsToDB();
			if(dataAdded)
				requestList=restRequestsRepository.findAll();
		}
		
		return requestList;
	}
	
	public boolean addRequestsToDB() {
		try {
			RestRequestEntity restRequestEntity= new RestRequestEntity();
			restRequestEntity.setId(Long.valueOf(1));
			restRequestEntity.setInsurerName("Insurer-1");
			restRequestEntity.setUrl("http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-1/rest/qoute");
			restRequestEntity.setUsername("insurer");
			restRequestEntity.setPassword("password");
			restRequestEntity.setMethodType("GET");
			updateRestRequestEntity(restRequestEntity);
			RestRequestEntity restRequestEntityTwo= new RestRequestEntity();
			restRequestEntityTwo.setId(Long.valueOf(2));
			restRequestEntityTwo.setInsurerName("Insurer-2");
			restRequestEntityTwo.setUrl("http://ec2-52-56-231-146.eu-west-2.compute.amazonaws.com/insurer-2/rest/policy");
			restRequestEntityTwo.setUsername("insurer");
			restRequestEntityTwo.setPassword("password");
			restRequestEntityTwo.setMethodType("POST");
			updateRestRequestEntity(restRequestEntityTwo);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void updateRestRequestEntity(RestRequestEntity restRequestEntity) {
		restRequestsRepository.save(restRequestEntity);
	}
	
}
;