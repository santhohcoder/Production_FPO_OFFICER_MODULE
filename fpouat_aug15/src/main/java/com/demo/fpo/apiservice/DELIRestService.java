package com.demo.fpo.apiservice;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.fpo.apibean.PostDataServe1;
import com.demo.fpo.apimodel.DELIfromtodt;
import com.demo.fpo.apimodel.DELIresponseData;
import com.demo.fpo.apirepository.DELIFpodatarepo;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.apirepository.FpomaininsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class DELIRestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(DELIRestService.class);

	//private static final String JsonParser = null;

	@Autowired
	private RestTemplate restClient;
	
	//@Autowired
	//private Loadfrmtodt loaddata;

	@Autowired
	private FpoStatusRepo fpostatusrepo;

	@Autowired
	private FpomaininsRepo fpomaininsrepo;
	
	@Autowired
	private DELIFpodatarepo Delirepo;
	
	@Autowired
	DELILoadfrmtoService loadDateServicedeli;
	
	@Autowired
	DELIFpodataService fpoDeliDataService;
	
	@Autowired
	DeliService deliService;

//	@Value("${authentication.token}")
	private String token;
//
//	@Value("${authentication.tokenendpoint}")
	private String tokenUrl;
//
//	@Value("${restUrl.dataendpoint}")
	private String dataUrl;
	
	public java.sql.Date sentdt;
	public Date frdt;
	public Date todt;
	public Long id;
	public Long recnt;
	public int timelapse;
	
	private String getToken() {
		PostDataServe1 postDataServe1=deliService.fetDeliPostData();
		token=postDataServe1.getDeliToken();
		tokenUrl=postDataServe1.getDeliEndpoint();
		dataUrl=postDataServe1.getDeliDataEndpoint();
		String doubleQuote = "\"";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(doubleQuote + token + doubleQuote, headers);
		log.info("Token: " + token);
		System.out.println("Token: " + token);
		System.out.println("Token Url: " + tokenUrl);
		ResponseEntity<String> response = restClient.exchange(tokenUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("AuthenticationToken: " + response.getBody().toString());
		return response.getBody().toString();
	}

	//public fromtodt fetchData(@RequestBody fromtodt Fromtodt) {
	//@SuppressWarnings("deprecation")
	public List<ResponseEntity<String>> fetchData() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		List<DELIfromtodt> fromToDtsdeli = loadDateServicedeli.getAllData();
		ObjectMapper mapper = new ObjectMapper();
//		fromDt.get(0).getFromDt();
//		fromDt.get(0).getTodt();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = getToken();
		//int i=0;
		for(DELIfromtodt fromToDtdeli : fromToDtsdeli) {
		log.info("Bearer " + token);
		log.info("Request Body " + mapper.writeValueAsString(fromToDtdeli));
		//log.info("Request Body " + mapper.writeValueAsString(fromToDt.get(0)));
		log.info("Request Body " + fromToDtdeli);
		headers.add("Authorization", "Bearer " + token);
		//HttpEntity<Object> requestEntity = new HttpEntity<Object>(mapper.writeValueAsString(fromToDt.get(0)), headers);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(fromToDtdeli, headers);
        
		ResponseEntity<String> response = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, String.class);
		
		String res;
		String res1;
		res1="\"No Data Available\"";
		
		res = mapper.writeValueAsString(response.getBody().toString());
		
//		System.out.println("Response body  " + mapper.writeValueAsString(response.getBody().toString()));
//		System.out.println("Res1 Body " + res1.trim());
//		System.out.println("Res body " + res.trim());
       if(res.equals(res1)) {
    	   System.out.println("Response1 Body " + res1.trim());
    	   recnt=(long) 0;
       }
    	   else {
    		   
		DELIresponseData responsedata = mapper.readValue(response.getBody().toString(), DELIresponseData.class);
		if(null != responsedata)
			//fpoDeliDataService.saveData(responsedata.getData());
		log.info("Data Length: " + responsedata.getData().size());
		responseEntityList.add(response);
		Integer size1 = responsedata.getData().size();
		//Long j = new Long(size1);
		for(int i = 0; i < size1; i++) {
		String itemid = responsedata.getData().get(i).getItemid();
		Date delvdt = responsedata.getData().get(i).getDelivery_date();
		//String delvdt = responsedata.getData().get(i).getDelivery_date().toString();
		String delstatus = responsedata.getData().get(i).getDelivery_status();
		System.out.println("ITEM: " + itemid);
		System.out.println("DELI_DT: " + delvdt);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(delvdt);
		cal.add(Calendar.HOUR_OF_DAY,-5);
		cal.add(Calendar.MINUTE,-30);
		delvdt = new Date (cal.getTimeInMillis());
		responsedata.getData().get(i).setDelivery_date(delvdt);
		
		
		
		
		//if (delstatus.trim().equals("DL")) { 
			int cntitm =fpostatusrepo.getfpostat(itemid);
		if (cntitm > 0) {
			fpostatusrepo.upddelvstatus(delvdt, itemid);
		fpomaininsrepo.upddelistatus(itemid);}
		int cntitm1 =Delirepo.getDeli(itemid);
		if (cntitm1 == 0) {
			fpoDeliDataService.saveDELI(responsedata.getData().get(i));
		}
		
		//}
		}
		
		log.info("Data Length: " + responsedata.getData().size());
		Integer size = responsedata.getData().size();		
		log.info("Is Equal: " + size.equals(responsedata.getCount()));
		responseEntityList.add(response);
		
		Long l= new Long(size);
		recnt=l;
    	   }
       
		id=fromToDtdeli.getId();
		java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		sentdt=currentDate1;
		loadDateServicedeli.findById(id, sentdt, recnt);
		frdt=fromToDtdeli.getFromDate();
		todt=fromToDtdeli.getToDate();
		timelapse=(int) fromToDtdeli.getTimelapse();
		System.out.println("fpo_deli updated");
		DELIfromtodt dtmodel = new DELIfromtodt();
		frdt=todt;
		dtmodel.setId(id+1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(todt);
	    calendar.add(Calendar.SECOND, 1);
	    frdt=calendar.getTime();
		dtmodel.setFromDate(frdt);
		dtmodel.setSuccess("N");
		dtmodel.setReccnt(0);
		dtmodel.setCrdt(sentdt);
		dtmodel.setUpdt(null);
		//Calendar calendar = Calendar.getInstance();
		calendar.setTime(todt);
	   // calendar.add(Calendar.HOUR_OF_DAY, 24);
	    calendar.add(Calendar.MINUTE, timelapse);
	    todt=calendar.getTime();
		dtmodel.setToDate(todt);
		dtmodel.setTimelapse(timelapse);
		dtmodel=loadDateServicedeli.updatedelimodelEntity(dtmodel);	
		//i=i+1;
		}
		
		
		return responseEntityList;
//		return (ResponseEntity<String>) ResponseEntity.ok();
	}
		
}
