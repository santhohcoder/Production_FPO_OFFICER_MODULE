package com.demo.fpo.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.fpo.apimodel.FPOfromtodt;
import com.demo.fpo.apimodel.FPOresponseData;
import com.demo.fpo.bean.CustomIdName;
import com.demo.fpo.bean.DepartmentCommentsBean;
import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.model.ReportColumns;
import com.demo.fpo.model.SelectModel;
import com.demo.fpo.prop.StaticConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Component
public class RMSRespService {
	public Long recnt;
	@Autowired
	private RestTemplate restClient;

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public void getRSPmainresp()  throws IOException{
		
		
		HttpHeaders headers = new HttpHeaders();
		
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		
	
				ObjectMapper mapper = new ObjectMapper();
				//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//mapper.setDateFormat(df);
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
				ResponseEntity<String> response = restClient.exchange("http://10.1.10.184:8080/Importfpo/home/responsedata", HttpMethod.GET, requestEntity, String.class);
				String res;
				String res1;
				res = mapper.writeValueAsString(response.getBody().toString());
				res1="\"No Data Available\"";
				if(res.equals(res1)) 
				{
					System.out.println("yes nod data");
					System.out.println("no data available");}
					else {
			    	  // System.out.println("Response1 Body " + res1.trim());
					   System.out.println("yes data here");
			    	   System.out.println("Response body  " + mapper.writeValueAsString(response.getBody().toString()));
			    	   out.write(mapper.writeValueAsString(response.getBody().toString()));
			    	   recnt=(long) 0;
			       }		
		
	}
}
