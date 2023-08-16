package com.demo.fpo.apiservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.fpo.NsmLsmRepo.D_EMP_ROLErepo_NSM;
import com.demo.fpo.apibean.FromandToDate;
import com.demo.fpo.apibean.PostDataServe;
import com.demo.fpo.apibean.PostDataServe1;
import com.demo.fpo.apibean.PreDesresponseData;
import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.FPOresponseData;
import com.demo.fpo.apimodel.FpoSite_Allot;
import com.demo.fpo.apimodel.Fposubmit;
import com.demo.fpo.apimodel.OOEarrdata;
import com.demo.fpo.apimodel.Predes_fpo_Req;
import com.demo.fpo.apimodel.OOEresponseData;
import com.demo.fpo.apirepository.Article_Predes_Info_repo;
import com.demo.fpo.apirepository.FPOarrdatarepo;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.apirepository.FpomaininsRepo;
import com.demo.fpo.apirepository.PreDesRepo;
import com.demo.fpo.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class PreDesRestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(PreDesRestService.class);


	@Autowired
	private RestTemplate restClient;

	@Autowired
	PreDes_Req loadDateServicefpo;
	
	@Autowired
	Article_Predes_Info_repo article_Predes_Info_repo;
	
	@Autowired
	ArrpostDataserveFPO  arrpostDataServe;

    @Autowired
    FposubmitService fposubmitService;

	@Resource
	private ReportService reportService;
	
	@Autowired
	D_EMP_ROLErepo_NSM demproleNSM;
	
	@Autowired
	public PreDesRepo loadfrmtodtFPO;

	private String dataUrl;
	
	public java.sql.Date sentdt;
	public Date frdt;
	public Date todt;
	public Long id;
	public int timelapse;
	public Long recnt;
	public Date cdate;
	public String token;
	public String tokenUrl;
	@Autowired
	ArrpostDataserveOOE  arrpostDataServeOOE;

	private String getToken() {
		PostDataServe postDataServe=arrpostDataServeOOE.fetArrPostData();
		
		token=postDataServe.getArrToken();
		tokenUrl=postDataServe.getArrEndpoint();
		
		String doubleQuote = "\"";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(doubleQuote + token + doubleQuote, headers);
		log.info("Token: " + token);
		System.out.println("Token: " + token);
		System.out.println("Token Url: " + tokenUrl);
		System.out.println("Data Url: " + dataUrl);
		ResponseEntity<String> response = restClient.exchange(tokenUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("AuthenticationToken: " + response.getBody().toString());
		return response.getBody().toString();
	}

	public List<ResponseEntity<String>> fpopredes() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();

		List<Predes_fpo_Req> fromToDts = loadDateServicefpo.getAllData();
		

		 dataUrl=arrpostDataServe.fetchPreDesURL();
		
	
				ObjectMapper mapper = new ObjectMapper();
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				String token = getToken();
				
			for(Predes_fpo_Req fromToDt : fromToDts) {
				
				log.info("fromdt " + frdt);
				log.info("todt " + todt);
				
				FromandToDate dtinput=new FromandToDate();
				
				dtinput.setFromDate(fromToDt.getFromDate());
				dtinput.setToDate(fromToDt.getToDate());
				
			log.info("Request Body " + mapper.writeValueAsString(dtinput));
			headers.add("Authorization", "Bearer " + token);
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(dtinput, headers);

			ResponseEntity<String> response = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, String.class);
			
			String res;
			String res1;
			res1="\"No Data Available\"";
			
			res = mapper.writeValueAsString(response.getBody().toString());
			

	       if(res.equals(res1)) {
	    	   System.out.println("Response1 Body " + res1.trim());
	    	   recnt=(long) 0;
	       }
	    	   else {
			PreDesresponseData responsedata= mapper.readValue(response.getBody().toString(), PreDesresponseData.class);
			
				if(null != responsedata)
							log.info("Data Length: " + responsedata.getData().size());
				
				
				java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				cdate=currentDate;
					responseEntityList.add(response);
					Integer size = responsedata.getData().size();		
					responseEntityList.add(response);
					Integer size1 = responsedata.getData().size();
					for(int i = 0; i < size1; i++) {
						
						Article_Predes_Info article_Predes_Info = new Article_Predes_Info();
						
						Date predecdt = responsedata.getData().get(i).getPredes_dt();
						
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(predecdt);
						cal.add(Calendar.HOUR_OF_DAY,-5);
						cal.add(Calendar.MINUTE,-30);
						predecdt = new Date (cal.getTimeInMillis());
						
						
						
						
						article_Predes_Info.setArticle_id(responsedata.getData().get(i).getArticle_id());
						article_Predes_Info.setRecp_id(responsedata.getData().get(i).getRecp_id());
						article_Predes_Info.setPredes_dt(predecdt);
						article_Predes_Info.setGen_dt(currentDate);
						
						article_Predes_Info_repo.save(article_Predes_Info);
						
						updateClearanceSite(responsedata.getData().get(i).getRecp_id(),responsedata.getData().get(i).getArticle_id());
						
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
					System.out.println("ARR_REQ updated");
					Predes_fpo_Req dtmodel = new Predes_fpo_Req();
					frdt=todt;
					//dtmodel.setId(id+1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				    calendar.add(Calendar.SECOND, 1);
				    frdt=calendar.getTime();
					dtmodel.setFromDate(frdt);
					dtmodel.setSuccess("N");
					dtmodel.setReccnt(0);
					dtmodel.setTimelapse(timelapse);
					dtmodel.setCrdt(sentdt);
					dtmodel.setUpdt(null);
					//Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				   // calendar.add(Calendar.HOUR_OF_DAY, timelapse);
				    calendar.add(Calendar.MINUTE, timelapse);
				    todt=calendar.getTime();
					dtmodel.setToDate(todt);
					//dtmodel=loadDateServicefpo.updatefpomodelEntity(dtmodel);
					loadfrmtodtFPO.save(dtmodel);
		}			
				return responseEntityList;

	}

	public void updateClearanceSite(String recp_id, String article_id) {
		
		Fposubmit fpomainlist = fposubmitService.itemId(article_id);
		String clr_site = "";
		if(fpomainlist != null) {
		System.out.println("substr of recp is" + recp_id.substring(6,11));
		List<FpoSite_Allot> allot=reportService.getFpoSite_Allot();
        System.out.println("RECEPTACLE ID is " +recp_id);
		String mcl = recp_id.substring(11,12);
		String mct = recp_id.substring(12,13);
		
		String cus_site = fpomainlist.getCUS_SITE();
		String site_code = "";
		
		if(cus_site != null) {
		if (cus_site.equalsIgnoreCase("INBOM5") || cus_site.equalsIgnoreCase("INBPS5") || cus_site.equalsIgnoreCase("INMAA5") || cus_site.equalsIgnoreCase("INFCH5"))

		{int letter_air = 0,letter_sal = 0,letter_sea = 0,ems_air = 0,ems_sea = 0,ems_sal = 0,parcel_air = 0,parcel_sal = 0,parcel_sea = 0;
		
		for (FpoSite_Allot fpoSite_Allot : allot) {

 			clr_site = "";
			site_code = fpoSite_Allot.getSITE_CODE();
			letter_air = fpoSite_Allot.getLETTER_AIR();
			letter_sal = fpoSite_Allot.getLETTER_SAL();
			letter_sea = fpoSite_Allot.getLETTER_SEA();
			ems_air = fpoSite_Allot.getEMS_AIR();
			ems_sea = fpoSite_Allot.getEMS_SEA();
			ems_sal = fpoSite_Allot.getEMS_SAL();
			parcel_air = fpoSite_Allot.getPARCEL_AIR();
			parcel_sal = fpoSite_Allot.getPARCEL_SAL();
			parcel_sea = fpoSite_Allot.getPARCEL_SEA();
		
		
				
			
		if (cus_site.equalsIgnoreCase("INBOM5") || cus_site.equalsIgnoreCase("INBPS5") )
		{
		if (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("A") && letter_air==1 && site_code.equalsIgnoreCase("INBOM5"))
		    clr_site = "INBOM5";
		else if (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("A") && letter_air==1 && site_code.equalsIgnoreCase("INBPS5"))
		    clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("B") && letter_sal==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("B") && letter_sal==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("C") && letter_sea==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("C") && letter_sea==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		else if (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("A") && ems_air==1 && site_code.equalsIgnoreCase("INBOM5"))
		    clr_site="INBOM5";
		else if (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("A") && ems_air==1 && site_code.equalsIgnoreCase("INBPS5"))
		    clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("B") && ems_sal==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("B") && ems_sal==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("C") && ems_sea==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("C") && ems_sea==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		else if (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("A") && parcel_air==1 && site_code.equalsIgnoreCase("INBOM5"))
		    clr_site="INBOM5";
		else if (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("A") && parcel_air==1 && site_code.equalsIgnoreCase("INBPS5"))
		    clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("B") && parcel_sal==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("B") && parcel_sal==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("C") && parcel_sea==1 && site_code.equalsIgnoreCase("INBOM5"))
		     clr_site="INBOM5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("C") && parcel_sea==1 && site_code.equalsIgnoreCase("INBPS5"))
		     clr_site="INBPS5";
		}
		else if  (cus_site.equalsIgnoreCase("INMAA5") || cus_site.equalsIgnoreCase("INFCH5"))
		{
		if (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("A") && letter_air==1 && site_code.equalsIgnoreCase("INMAA5"))
		    clr_site="INMAA5";
		else if (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("A") && letter_air==1 && site_code.equalsIgnoreCase("INFCH5"))
		    clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("B") && letter_sal==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("B") && letter_sal==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("C") && letter_sea==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("A") && mct.equalsIgnoreCase("C") && letter_sea==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";
		else if (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("A") && ems_air==1 && site_code.equalsIgnoreCase("INMAA5"))
		    clr_site="INMAA5";
		else if (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("A") && ems_air==1 && site_code.equalsIgnoreCase("INFCH5"))
		    clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("B") && ems_sal==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("B") && ems_sal==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("C") && ems_sea==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("B") && mct.equalsIgnoreCase("C") && ems_sea==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";
		else if (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("A") && parcel_air==1 && site_code.equalsIgnoreCase("INMAA5"))
		    clr_site="INMAA5";
		else if (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("A") && parcel_air==1 && site_code.equalsIgnoreCase("INFCH5"))
		    clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("B") && parcel_sal==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("B") && parcel_sal==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("C") && parcel_sea==1 && site_code.equalsIgnoreCase("INMAA5"))
		     clr_site="INMAA5";
		else if  (mcl.equalsIgnoreCase("C") && mct.equalsIgnoreCase("C") && parcel_sea==1 && site_code.equalsIgnoreCase("INFCH5"))
		     clr_site="INFCH5";

		}
		else
		  clr_site=cus_site;
		
		if(clr_site != "") {
			demproleNSM.updateClrSite(clr_site,article_id);
			return;
		}
		}
		
}
	}
		
	//	else
	//	{
	//		 clr_site=cus_site;
	//		 demproleNSM.updateClrSite(clr_site,article_id);
	//			return;

	//	}
		   
	}
	}

	

}
