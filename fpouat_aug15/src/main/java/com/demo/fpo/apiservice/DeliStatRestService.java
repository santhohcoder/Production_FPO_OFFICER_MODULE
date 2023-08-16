package com.demo.fpo.apiservice;




import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.fpo.apiConfig.SoapClient;
import com.demo.fpo.apibean.PostDataServe;
import com.demo.fpo.apibean.SpeedPostNo;
import com.demo.fpo.apidatafetcher.stub.LoadResponse;
import com.demo.fpo.apimodel.DELISTATresponseData;
import com.demo.fpo.apimodel.Delistatusdata;
import com.demo.fpo.apirepository.DELIStatLoad;
import com.demo.fpo.apirepository.delistatdataRepo;
import com.demo.fpo.repos.FpoDcallQRYRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoQueryDecisionRepo;
import com.demo.fpo.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class DeliStatRestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(DeliStatRestService.class);

	//private static final String JsonParser = null;

	@Autowired
	private RestTemplate restClient;
	
	@Autowired
	DELIStatLoad delistatload;
	
	@Autowired
	FpoDcallQRYRepo fpoDcallQRYRepo;
	
	@Resource
	private ReportService reportService;
		
	@Autowired
	deliStatdataService dataService;
	
	@Autowired
	delistatdataRepo dataRepo;
	
	
	@Autowired
	DelistatDataserve DataServe;
	
	@Autowired
    FpoMvmntRepo fpoMvmntRepo;

    @Autowired
    FpoQueryDecisionRepo fpoQueryDecisionRepo;

    @Autowired
    FpoDcallQRYRepo fpoDcallQryRepo;


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
	public static String fpoSeqNo;
	public static String uniqId;
	public String cintmp = "";
	public java.sql.Date sentdt;
	public Date frdt;
	public Date todt;
	public Long id;
	public Long ooeid;
	public int timelapse;
	public Long recnt;
	public Long dupl;
	public int pincodefou = 0;
	public String pincode;
	public String fpocode;
	public String cussite;
	public String cinno;
	public Date cindt;
	public Date cdate;

	private String getToken() {
		PostDataServe postDataServe=DataServe.fetArrPostData();
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
		System.out.println("Data Url: " + dataUrl);
		ResponseEntity<String> response = restClient.exchange(tokenUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("AuthenticationToken: " + response.getBody().toString());
		return response.getBody().toString();
	}

	
	public List<ResponseEntity<String>> fetchData() throws IOException, ParseException{
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		/*
		 * List<SpeedPostNo> speedpostno = reportService.getspeedno();
		 * 
		 * PostDataServe postDataServe=DataServe.fetArrPostData();
		 * dataUrl=postDataServe.getArrDataEndpoint();
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); //DateFormat df = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //mapper.setDateFormat(df);
		 * headers.setContentType(MediaType.APPLICATION_JSON); //String token =
		 * getToken(); for(SpeedPostNo speedpostlst : speedpostno) { log.info("fromdt "
		 * + frdt); log.info("todt " + todt); log.info("dataUrl " + dataUrl);
		 * //log.info("Request Body " + mapper.writeValueAsString(fromToDt.get(0)));
		 * log.info("Request Body " + mapper.writeValueAsString(speedpostlst));
		 * //headers.add("Authorization", "Bearer " + token); //HttpEntity<Object>
		 * requestEntity = new
		 * HttpEntity<Object>(mapper.writeValueAsString(fromToDt.get(0)), headers);
		 * HttpEntity<Object> requestEntity = new HttpEntity<Object>(speedpostlst,
		 * headers); // ResponseEntity<responseData> responsedata =
		 * restClient.exchange(dataUrl, HttpMethod.POST, requestEntity,
		 * responseData.class); ResponseEntity<String> response =
		 * restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, String.class);
		 * String res; String res1; res1="\"No Data Available\"";
		 * 
		 * res = mapper.writeValueAsString(response.getBody().toString());
		 * 
		 * 
		 * if(res.equals(res1)) { System.out.println("Response1 Body " + res1.trim());
		 * recnt=(long) 0; dupl=(long) 0; } else { List<delistatusdata> responsedata=
		 * mapper.readValue(response.getBody().toString(), new
		 * TypeReference<List<delistatusdata>>(){});
		 * 
		 * if(null != responsedata)
		 * //arrDataOOEService.saveData(responsedata.getData());
		 * //arrDataOOEService.saveData(responsedata.getData());
		 * log.info("Data Length: " + responsedata.size());
		 * 
		 * Collections.sort(responsedata);
		 * 
		 * 
		 * java.sql.Date currentDate = new
		 * java.sql.Date(Calendar.getInstance().getTime().getTime()); cdate=currentDate;
		 * responseEntityList.add(response); //Integer size =
		 * responsedata.getData().size(); responseEntityList.add(response); Integer
		 * size1 = responsedata.size(); dupl=(long) 0; for(int i = 0; i < size1; i++) {
		 * //delistatusdata Delistatdata= new delistatusdata(); // DateFormat dateFormat
		 * = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss"); String itemid =
		 * responsedata.get(i).getArticlenumber(); Date recvdt =
		 * responsedata.get(i).getReceived_date(); //String
		 * ooe=responsedata.getData().get(i).getOOE(); String
		 * delistatus=responsedata.get(i).getDelistat();
		 * 
		 * String DateandTime =
		 * responsedata.get(i).getDate().concat(" ").concat(responsedata.get(i).getTime(
		 * ));
		 * 
		 * responsedata.get(i).setReceived_date(new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(DateandTime));
		 * 
		 * System.out.println("ITEM: " + itemid + "recvdt :" + recvdt +"DS "+delistatus
		 * +"Time "+responsedata.get(i).getTime()); dataRepo.deleteSpeedPost(itemid);
		 * dataService.saveDELI(responsedata.get(i));
		 * 
		 * if(delistatus.equalsIgnoreCase("Not Delivered") ||
		 * delistatus.equalsIgnoreCase("Item Delivery Confirmed")) {
		 * fpoDcallQRYRepo.updateSpeedPostDeliStatus(itemid,delistatus); } }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 */
			return responseEntityList;
	}


	public List<ResponseEntity<String>> fpospeedposttoass() {
		
		List<ResponseEntity<String>> result = new ArrayList<>();
		
		List<String> cinnos = fpoDcallQryRepo.getCinNoFromDCALLQRY_GEN();
		
		for (String cinno : cinnos) {
			
			fpoMvmntRepo.updateSpeedPosttoAssforFPO_MVMNT(cinno,new Date());
			fpoQueryDecisionRepo.updateSpeedPosttoAssforFPO_QRY_DECI(cinno,new Date());
			fpoDcallQRYRepo.updateASS_MVMNT(cinno,new Date());
		}

		return result;
		
	}


	public List<ResponseEntity<String>> fetchDataspeedpost() throws IOException, ParseException, SAXException, ParserConfigurationException {
		


		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		
		List<SpeedPostNo> speedpostno = reportService.getspeedno();
				
			for(SpeedPostNo speedpostlst : speedpostno) {
				
				String dataUrl="http://data.cept.gov.in/CustomTracking/TrackConsignment.asmx/ArticleTracking?Article="+speedpostlst.getSpeedpost_no()+"&RequestingApplication=Cust0M$Tr@ck";
				//String dataUrl="https://data.cept.gov.in/CustomTracking/TrackConsignment.asmx/ArticleTracking?Article=JB261930549IN&RequestingApplication=Cust0M$Tr@ck";
				
				System.out.println(dataUrl);
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(dataUrl);

	            // normalize XML response
	            doc.getDocumentElement().normalize();
	            
	            NodeList nodeList = doc.getElementsByTagName("dt_Response");
	            
	            Node node = nodeList.item(0);
	            
	            if(node.getNodeType() == Node.ELEMENT_NODE) {
	                Element elem = (Element) node;
	                
	                String issucc = elem.getElementsByTagName("IsSucc").item(0).getTextContent();
	                
	                if(issucc.equalsIgnoreCase("Y")) {

	                    NodeList dt_Details = doc.getElementsByTagName("dt_Details");
	                    
	                    if(dt_Details.getLength() > 0) {

							dataRepo.deleteSpeedPost(speedpostlst.getSpeedpost_no());
	                    	
	                    }
	                    
	                    int check = 0;
	                    
	                	 for (int i = 0; i < dt_Details.getLength(); i++) {
	                     	
	                         Node dt_Detailsnode = dt_Details.item(i);
	                         
	                         if(dt_Detailsnode.getNodeType() == Node.ELEMENT_NODE) {
	                        	 
	                        	 Element dt_Detailselem = (Element) dt_Detailsnode;
	                        	 
//	                        	 System.out.println(dt_Detailselem.getElementsByTagName("EvntDate").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntDate").item(0).getTextContent());
//	                        	 System.out.println(dt_Detailselem.getElementsByTagName("EvntLctn").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntLctn").item(0).getTextContent());
//	                        	 System.out.println(dt_Detailselem.getElementsByTagName("EvntCd").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntCd").item(0).getTextContent());
//	                        	 System.out.println(dt_Detailselem.getElementsByTagName("Rmrks").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("Rmrks").item(0).getTextContent());
//	                        	 
	                        	 
	                        	 String delistatus = dt_Detailselem.getElementsByTagName("EvntCd").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntCd").item(0).getTextContent();
	                        	 String recvdt = dt_Detailselem.getElementsByTagName("EvntDate").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntDate").item(0).getTextContent();
	                        	 String EvntLctn = dt_Detailselem.getElementsByTagName("EvntLctn").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("EvntLctn").item(0).getTextContent();
	                        	 String rmrks = dt_Detailselem.getElementsByTagName("Rmrks").item(0).getTextContent() == null ? "":dt_Detailselem.getElementsByTagName("Rmrks").item(0).getTextContent();
	                        	 
									System.out.println("ITEM: " + speedpostlst.getSpeedpost_no() + "recvdt :" + recvdt + "DS " + delistatus	+ "Time " + recvdt);
									
									
									Delistatusdata delistatusdata = new Delistatusdata();
									
									delistatusdata.setArticlenumber(speedpostlst.getSpeedpost_no());
									delistatusdata.setDELI_STATUS(delistatus);
									delistatusdata.setOFFICE(EvntLctn);
									delistatusdata.setRECD_DT(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(recvdt) );
									delistatusdata.setRemarks(rmrks);
									delistatusdata.setGENDT(new Date());
									
									
									
									dataService.saveDELI(delistatusdata);

									if (delistatus.equalsIgnoreCase("Item Delivered") || delistatus.equalsIgnoreCase("Item Delivery Confirmed")) {
										fpoDcallQRYRepo.updateSpeedPostDeliStatus(speedpostlst.getSpeedpost_no(), delistatus);
										check = 1;
									}else if(delistatus.equalsIgnoreCase("return") && check == 0) {
										fpoDcallQRYRepo.updateSpeedPostDeliStatus(speedpostlst.getSpeedpost_no(), "Not Delivered");
									}
	                        	 
	                         }
	                		 
	                	 }
	                	
	                }
	                
	                
	            }
	            
	            
			}
			return responseEntityList;

	 
	}
}
	
	