package com.demo.fpo.apiservice;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.fpo.apibean.PostDataServe;
import com.demo.fpo.apimodel.fromtodt;
import com.demo.fpo.apimodel.responseData;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestService.class);

	//private static final String JsonParser = null;

	@Autowired
	private RestTemplate restClient;

	@Autowired
	LoadfrmtoService loadDateService;
	
	@Autowired
	arrdataService arrDataService;
	
	@Autowired
	private FpoStatusRepo fpostatusrepo;
	
	@Autowired
	ArrpostDataserveOOE  arrpostDataServe;
	
//	@Autowired
//	Loadfrmtodt reqdt;
	
  //@Value("${authentication.token}")
	private String token;

	//@Value("${authentication.tokenendpoint}")
	private String tokenUrl;

	//@Value("${restUrl.dataendpoint}")
	private String dataUrl;
	
//	public RestService(Loadfrmtodt reqdt) {
//		this.reqdt=reqdt;
//	}
	public java.sql.Date sentdt;
	public Date frdt;
	public Date todt;
	public Long id;
	public Long recnt;
	

	private String getToken() {
		PostDataServe postDataServe=arrpostDataServe.fetArrPostData();
		token=postDataServe.getArrToken();
		tokenUrl=postDataServe.getArrEndpoint();
		dataUrl=postDataServe.getArrDataEndpoint();
		String doubleQuote = "\"";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(doubleQuote + token + doubleQuote, headers);
		log.info("Token: " + token);
		System.out.println("Token: " + token);
		System.out.println("Token Url: " + tokenUrl);
		System.out.println("in gettoken");

		ResponseEntity<String> response = restClient.exchange(tokenUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("AuthenticationToken: " + response.getBody().toString());
		return response.getBody().toString();
	}

	
	public List<ResponseEntity<String>> fetchData() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		List<fromtodt> fromToDts = loadDateService.getAllData();
		System.out.println("Token: " + token);
		System.out.println("Token Url: " + tokenUrl);
		System.out.println("in fetchdata function");
	
				ObjectMapper mapper = new ObjectMapper();
				//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//mapper.setDateFormat(df);
				headers.setContentType(MediaType.APPLICATION_JSON);
			String token = getToken();
			for(fromtodt fromToDt : fromToDts) {
				log.info("fromdt " + frdt);
				log.info("todt " + todt);
			log.info("Bearer " + token);
			//log.info("Request Body " + mapper.writeValueAsString(fromToDt.get(0)));
			log.info("Request Body " + mapper.writeValueAsString(fromToDt));
			headers.add("Authorization", "Bearer " + token);
			//HttpEntity<Object> requestEntity = new HttpEntity<Object>(mapper.writeValueAsString(fromToDt.get(0)), headers);
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(fromToDt, headers);
//			ResponseEntity<responseData> responsedata = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, responseData.class);
			ResponseEntity<String> response = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, String.class);
			String res;
			String res1;
			res1="\"No Data Available\"";
			
			res = mapper.writeValueAsString(response.getBody().toString());
			
//			System.out.println("Response body  " + mapper.writeValueAsString(response.getBody().toString()));
//			System.out.println("Res1 Body " + res1.trim());
//			System.out.println("Res body " + res.trim());
	       if(res.equals(res1)) {
	    	   System.out.println("Response1 Body " + res1.trim());
	    	   recnt=(long) 0;
	       }
	    	   else {
	    		  			
			responseData responsedata= mapper.readValue(response.getBody().toString(), responseData.class);
			
				if(null != responsedata)
					arrDataService.saveData(responsedata.getData());
							log.info("Data Length: " + responsedata.getData().size());
					responseEntityList.add(response);
					Integer size = responsedata.getData().size();		
					responseEntityList.add(response);
					Integer size1 = responsedata.getData().size();
					for(int i = 0; i < size1; i++) {
						String itemid = responsedata.getData().get(i).getArticlenumber();
						Date recvdt = responsedata.getData().get(i).getReceived_Event_date();
						System.out.println("ITEM: " + itemid);
						int cntitm =fpostatusrepo.getfpostat(itemid);
						if (cntitm > 0) {
						fpostatusrepo.updrcvdtfpostatus(recvdt, itemid);}
					}	
										
					Long l= new Long(size);
					recnt=l;
	    	   }
					id=fromToDt.getId();
					java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					sentdt=currentDate1;
					loadDateService.findById(id, sentdt, recnt);
					frdt=fromToDt.getFromDate();
					todt=fromToDt.getToDate();
					System.out.println("ARR_REQ updated");
					fromtodt dtmodel = new fromtodt();
					frdt=todt;
					dtmodel.setId(id+1);
					dtmodel.setFromDate(frdt);
					dtmodel.setSuccess("N");
					dtmodel.setReccnt(0);
					dtmodel.setCrdt(sentdt);
					dtmodel.setUpdt(null);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				    calendar.add(Calendar.HOUR_OF_DAY, 1);
				    todt=calendar.getTime();
					dtmodel.setToDate(todt);
					dtmodel=loadDateService.updatedelimodelEntity(dtmodel);
			
					
		}			
				return responseEntityList;

	}

	}
