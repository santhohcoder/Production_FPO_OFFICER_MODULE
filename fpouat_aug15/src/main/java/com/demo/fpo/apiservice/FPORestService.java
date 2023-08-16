package com.demo.fpo.apiservice;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.demo.fpo.apimodel.FPOfromtodt;
import com.demo.fpo.apimodel.FPOresponseData;
import com.demo.fpo.apimodel.Fposubmit;
import com.demo.fpo.apirepository.FPOarrdatarepo;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.apirepository.FpomaininsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FPORestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(FPORestService.class);

	//private static final String JsonParser = null;

	@Autowired
	private RestTemplate restClient;

	@Autowired
	FPOLoadfrmtoService loadDateServicefpo;
	
	@Autowired
	FPOarrdataService arrDataFPOService;
	
	@Autowired
	private FpoStatusRepo fpostatusrepo;
	
	@Autowired
	private FPOarrdatarepo fpoarrRepo;
	
	@Autowired
	private FpomaininsRepo fpomaininsrepo; 
	
	@Autowired
	ArrpostDataserveFPO  arrpostDataServe;

    @Autowired
    FposubmitService fposubmitService;
	
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
	public int timelapse;

	private String getToken() {
		PostDataServe postDataServe=arrpostDataServe.fetArrPostData();
		token=postDataServe.getArrToken();
		tokenUrl=postDataServe.getArrEndpoint();
		dataUrl=postDataServe.getArrDataEndpoint();
		String doubleQuote = "\"";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity<String> requestEntity = new HttpEntity<>(doubleQuote + token + doubleQuote, headers);
		log.info("Token: " + token);
		System.out.println("Token: " + token);
		System.out.println("Token Url: " + tokenUrl);
		ResponseEntity<String> response = restClient.exchange(tokenUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("AuthenticationToken: " + response.getBody().toString());
		return response.getBody().toString();
	}

	
	public List<ResponseEntity<String>> fetchData() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		List<FPOfromtodt> fromToDts = loadDateServicefpo.getAllData();
		
	
				ObjectMapper mapper = new ObjectMapper();
				//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//mapper.setDateFormat(df);
				headers.setContentType(MediaType.APPLICATION_JSON);
			String token = getToken();
			for(FPOfromtodt fromToDt : fromToDts) {
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
	    		  			
			FPOresponseData responsedata= mapper.readValue(response.getBody().toString(), FPOresponseData.class);
			
				if(null != responsedata)
					//arrDataFPOService.saveData(responsedata.getData());
					log.info("Data Length: " + responsedata.getData().size());
					responseEntityList.add(response);
					Integer size = responsedata.getData().size();		
					responseEntityList.add(response);
					Integer size1 = responsedata.getData().size();
					for(int i = 0; i < size1; i++) {
						String itemid = responsedata.getData().get(i).getArticlenumber();
						Date recvdt = responsedata.getData().get(i).getReceived_date();
						System.out.println("original recvdt is " + recvdt);
						int cntitm =fpostatusrepo.getfpostat(itemid);
						String recdfpo=responsedata.getData().get(i).getRECD_FPO();
						String bagno=responsedata.getData().get(i).getBagNumber();
						Calendar cal = Calendar.getInstance();
						cal.setTime(recvdt);
						cal.add(Calendar.HOUR_OF_DAY,-5);
						cal.add(Calendar.MINUTE,-30);
						recvdt = new Date (cal.getTimeInMillis());
						responsedata.getData().get(i).setReceived_date(recvdt);
						System.out.println("after manipulation  recvdt is " + recvdt);
						Fposubmit fpomainlist = fposubmitService.itemId(itemid);
						if (fpomainlist != null) {
							if (fpomainlist.getCUS_SITE() != null) {
						if (cntitm > 0 && recdfpo.substring(0, 5).equalsIgnoreCase(fpomainlist.getCUS_SITE().substring(0, 5)) || (recdfpo.substring(0, 5).equalsIgnoreCase("INCOK") && fpomainlist.getCUS_SITE().substring(0, 5).equalsIgnoreCase("INKOC"))) {
							
						fpostatusrepo.updrcvdtfpostatus(recvdt, itemid);
						fpomaininsrepo.updfpostatus(itemid);
						}
						}}
						int cntitm1 =fpoarrRepo.getarrFPO(recvdt, itemid, recdfpo, bagno);
						if (cntitm1 == 0) {
							arrDataFPOService.saveFPO(responsedata.getData().get(i));
						}
						else {
							fpoarrRepo.updarrfpoinfo(recvdt, itemid, recdfpo, bagno);
							arrDataFPOService.saveFPO(responsedata.getData().get(i));
							
						}
						
													}	
					
										
					Long l= new Long(size);
					recnt=l;
	    	   }
					id=fromToDt.getId();
					java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					sentdt=currentDate1;
					loadDateServicefpo.findById(id, sentdt, recnt);
					frdt=fromToDt.getFromDate();
					todt=fromToDt.getToDate();
					timelapse=(int) fromToDt.getTimelapse();
					System.out.println("ARR_REQ updated" + timelapse );
					FPOfromtodt dtmodel = new FPOfromtodt();
					frdt=todt;
					dtmodel.setId(id+1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				    calendar.add(Calendar.SECOND, 1);
				    frdt=calendar.getTime();
				    timelapse=(int) fromToDt.getTimelapse();
					dtmodel.setFromDate(frdt);
					dtmodel.setSuccess("N");
					dtmodel.setTimelapse((long) timelapse);
					dtmodel.setReccnt(0);
					dtmodel.setCrdt(sentdt);
					dtmodel.setUpdt(null);
					//System.out.println(timelapse);
					//Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				
				    //calendar.add(Calendar.HOUR_OF_DAY, timelapse);
				    calendar.add(Calendar.MINUTE, timelapse);
				    todt=calendar.getTime();
					dtmodel.setToDate(todt);
					
					dtmodel=loadDateServicefpo.updatefpomodelEntity(dtmodel);
			
					
		}			
				return responseEntityList;

	}

	}
