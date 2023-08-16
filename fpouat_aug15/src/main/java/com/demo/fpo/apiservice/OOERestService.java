package com.demo.fpo.apiservice;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.demo.fpo.apimodel.Fposubmit;
import com.demo.fpo.apimodel.OOEarrdata;
import com.demo.fpo.apimodel.OOEfromtodt;
import com.demo.fpo.apimodel.OOEresponseData;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.apirepository.FpomaininsRepo;
import com.demo.fpo.apirepository.FposubmitRepoImpl;
import com.demo.fpo.apirepository.OOEarrdatarepo;
import com.demo.fpo.repos.CININFO_repo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.fpo.apiservice.PreDesRestService;
import com.demo.fpo.model.CIN_INFO;
@Service
public class OOERestService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(OOERestService.class);

	//private static final String JsonParser = null;

	@Autowired
	private RestTemplate restClient;
	
	@Autowired
	private CININFO_repo cininfoREPO;
	
	@Autowired
	FposubmitRepoImpl fposubmitRepoImpl;

	@Autowired
	OOELoadfrmtoService loadDateOOEService;
	
	@Autowired
	FposubmitService fposubmitService;
	
	@Autowired
	OOEarrdataService arrDataOOEService;
	
	@Autowired
	PreDesRestService PREDESservice;
	
	@Autowired
	OOEarrdatarepo ooearrdataRepo;
	
	@Autowired
	private FpoStatusRepo fpostatusrepo;
	
	@Autowired
	private FpomaininsRepo fpomaininsrepo;
	@Autowired
	ArrpostDataserveOOE  arrpostDataServeOOE;
	
	
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
	public static String cinSeqNo;
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
		PostDataServe postDataServe=arrpostDataServeOOE.fetArrPostData();
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

	
	public List<ResponseEntity<String>> fetchData() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		List<ResponseEntity<String>> responseEntityList = new ArrayList<ResponseEntity<String>>();
		List<OOEfromtodt> fromToDts = loadDateOOEService.getAllData();
		
	
				ObjectMapper mapper = new ObjectMapper();
				//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//mapper.setDateFormat(df);
				headers.setContentType(MediaType.APPLICATION_JSON);
			String token = getToken();
			for(OOEfromtodt fromToDtooe : fromToDts) {
				log.info("fromdt " + frdt);
				log.info("todt " + todt);
			log.info("Bearer " + token);
			//log.info("Request Body " + mapper.writeValueAsString(fromToDt.get(0)));
			log.info("Request Body " + mapper.writeValueAsString(fromToDtooe));
			headers.add("Authorization", "Bearer " + token);
			//HttpEntity<Object> requestEntity = new HttpEntity<Object>(mapper.writeValueAsString(fromToDt.get(0)), headers);
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(fromToDtooe, headers);
//			ResponseEntity<responseData> responsedata = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, responseData.class);
			ResponseEntity<String> response = restClient.exchange(dataUrl, HttpMethod.POST, requestEntity, String.class);
			String res;
			String res1;
			res1="\"No Data Available\"";
			
			res = mapper.writeValueAsString(response.getBody().toString());
			

	       if(res.equals(res1)) {
	    	   System.out.println("Response1 Body " + res1.trim());
	    	   recnt=(long) 0;
	    	   dupl=(long) 0;
	       }
	    	   else {
			OOEresponseData responsedata= mapper.readValue(response.getBody().toString(), OOEresponseData.class);
					
				if(null != responsedata)
					//arrDataOOEService.saveData(responsedata.getData());
					//arrDataOOEService.saveData(responsedata.getData());
							log.info("Data Length: " + responsedata.getData().size());
				java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				java.util.Date recvnewdt;

				cdate=currentDate;
					responseEntityList.add(response);
					Integer size = responsedata.getData().size();		
					responseEntityList.add(response);
					Integer size1 = responsedata.getData().size();
					dupl=(long) 0;
					for(int i = 0; i < size1; i++) {
						OOEarrdata ooedata= new OOEarrdata();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
						String itemid = responsedata.getData().get(i).getArticlenumber();
					    Date bookdt = responsedata.getData().get(i).getBookingDate();
						//String ooe=responsedata.getData().get(i).getOOE();
						String recpid=responsedata.getData().get(i).getReceptacle_id();
						pincode=responsedata.getData().get(i).getA_PostCode();
						Date recvdt = responsedata.getData().get(i).getReceived_Event_date();
						//String postdt=responsedata.getData().get(i).getBookingDate().toString();
						String postdt=dateFormat.format(bookdt);  
						System.out.println("ITEM: " + itemid + "Bookdt :" + postdt +" "+bookdt);
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(recvdt);
						cal.add(Calendar.HOUR_OF_DAY,-5);
						cal.add(Calendar.MINUTE,-30);
						recvdt = new Date (cal.getTimeInMillis());
						responsedata.getData().get(i).setReceived_Event_date(recvdt);
						
						
						
						//int cntitm1 =ooearrdataRepo.getarrOOE(bookdt, itemid, ooe, recpid,recvdt);
						//int cntitm1 =ooearrdataRepo.getarrOOE(itemid, ooe, recpid);
						
							pincodefou = 0;
							fpocode = "";
							cussite="";
							int cntitm =fpostatusrepo.getfpostat(itemid);
							if (cntitm > 0) {
							
							//Fposubmit fpomainlist = fposubmitService.itemIdbookdt(itemid, postdt);
							Fposubmit fpomainlist = fposubmitService.itemId(itemid);
							if (fpomainlist !=null) {
							cinno=fpomainlist.getCIN_NO();
							cindt=fpomainlist.getCindt();
						    pincode=fpomainlist.getPINCODE();
							fpocode=fpomainlist.getFPO_CODE();
							cussite=fpomainlist.getCUS_SITE();
							fpostatusrepo.updrcvdtooestatus(recvdt, itemid);
							fpomaininsrepo.updooestatus(itemid);
							if (fpomainlist.getCLR_SITE() == null || fpomainlist.getCLR_SITE() == "")
							{
								if (!(responsedata.getData().get(i).getReceptacle_id() != null || responsedata.getData().get(i).getReceptacle_id() != "" || responsedata.getData().get(i).getReceptacle_id().length()>0))
								  PREDESservice.updateClearanceSite(responsedata.getData().get(i).getReceptacle_id(),responsedata.getData().get(i).getArticlenumber());
							}
							if (fpomainlist.getCUS_SITE() != null)
							{  if(responsedata.getData().get(i).getOOE().substring(0, 5).equalsIgnoreCase(fpomainlist.getCUS_SITE().substring(0, 5)) || (responsedata.getData().get(i).getOOE().substring(0, 5).equalsIgnoreCase("INCOK") && fpomainlist.getCUS_SITE().substring(0, 5).equalsIgnoreCase("INKOC"))) {
								{fpomaininsrepo.updfpostatus(itemid);
								fpostatusrepo.updrcvdtfpostatus(recvdt, itemid);
								}
							}
							}
							
							}
							}
							else
							{
								if (pincode != null || pincode != "") {
									pincode = pincode.replaceAll("\\s+","");
									pincode = pincode.trim();
									if (pincode.length() == 7 && pincode.substring(3, 4) == " ") {
									
										System.out.println("cms here in space between pincodes"+pincode );
										pincode = pincode.substring(0, 3) + pincode.substring(4, 7);
										pincode = pincode.replaceAll("\\s+","");
										pincode = pincode.trim();
																	
									}
									System.out.println("isdigit outcome is " + IsDigit(pincode));
									if (IsDigit(pincode)) {
										if (pincode.length() == 6) {
											pincode = pincode.substring(0, 6);
											System.out.println("pincodes=" + pincode);
											fpocode = fposubmitRepoImpl.getfponame(pincode, "PINCODE");
											System.out.println("fpocode=" + fpocode);
											if (fpocode != null) {
												cussite =fposubmitRepoImpl.getfposite(fpocode);
												pincodefou = 1;
											} else {
												fpocode = fposubmitRepoImpl.getfponamemapping(pincode);
												if (fpocode != null) {
													cussite =fposubmitRepoImpl.getfposite(fpocode);
													pincodefou = 1;
												}
											}
										}
										else
										{
											pincode="";
										}
								}
									else
									{
										pincode="";
									}
								}
								Date dt =  responsedata.getData().get(i).getBookingDate();
								System.out.println("dt is " + dt);
								DateFormat formatter = new SimpleDateFormat ("dd/MM/YYYY");
								System.out.println (formatter.format (dt));
								cintmp = formatter.format(dt);
								System.out.println("cintmp is " + cintmp);
								fpoSeqNo = fposubmitRepoImpl.getOOESeq();
								System.out.println("fpoSeqNo is " + fpoSeqNo );
								System.out.println("first 2 is " + cintmp.substring(0,2));
								System.out.println("next 2 is " + cintmp.substring(3,5));
								System.out.println("last 4 is " + cintmp.substring(8,10));
								uniqId = cintmp.substring(8,10) + "MCAA" + cintmp.substring(0, 2) + cintmp.substring(3,5);
								System.out.println("uniqId is " + uniqId);
								cindt=cdate;
								System.out.println("Cindt is "+ cindt);
								int j=0;
							//	do
							//	{
								cinSeqNo = cininfoREPO.getcinarrseq().toString();
								System.out.println("cinSeqNo is "+ cinSeqNo);
								cinno = getCinNor();
								System.out.println("cinno is " + cinno);
							//	if (fpomaininsrepo.chkcinfou(cinno)>0)
							//		System.out.println("Cinno already exists...");
							/*	else
									{
									    j=1;
									    CIN_INFO cininfo = new CIN_INFO();
									    cininfo.setCinNO(cinno);
									    cininfo.setCinDt(cdate);
									    cininfo.setItemID(itemid);
									    cininfo.setPostingDT(postdt);
									    cininfoREPO.save(cininfo);
									}*/
							//	}
							//	while (j==0);								
							}
							int cntitm1 =ooearrdataRepo.getarrOOE(itemid, recpid);
							if (cntitm1 == 0) {
							ooedata.setA_Addr1(responsedata.getData().get(i).getA_Addr1());
							ooedata.setA_Addr2(responsedata.getData().get(i).getA_Addr2());
							ooedata.setA_Name(responsedata.getData().get(i).getA_Name());
							ooedata.setA_PostCode(responsedata.getData().get(i).getA_PostCode());
							ooedata.setArticlenumber(responsedata.getData().get(i).getArticlenumber());
							ooedata.setBookingDate(responsedata.getData().get(i).getBookingDate());
							ooedata.setCIN_NO(cinno);									
							ooedata.setCindt((java.sql.Date) cindt);
							ooedata.setCUS_SITE(cussite);
							ooedata.setDestnCountry(responsedata.getData().get(i).getDestnCountry());
							ooedata.setFPO_CODE(fpocode);
							ooedata.setItemValue(responsedata.getData().get(i).getItemValue());
							ooedata.setItemWeight(responsedata.getData().get(i).getItemWeight());
							ooedata.setMailclass(responsedata.getData().get(i).getMailclass());
							ooedata.setOOE(responsedata.getData().get(i).getOOE());
							ooedata.setOriginCountry(responsedata.getData().get(i).getOriginCountry());
							ooedata.setPINCODE(pincode);
							ooedata.setReceived_Event_date(responsedata.getData().get(i).getReceived_Event_date());
							ooedata.setReceptacle_id(responsedata.getData().get(i).getReceptacle_id());
							ooedata.setS_NameAddr(responsedata.getData().get(i).getS_NameAddr());
							ooedata.setS_PostCode(responsedata.getData().get(i).getS_PostCode());
							//arrDataOOEService.saveOOE(responsedata.getData().get(i));
							arrDataOOEService.saveOOE(ooedata);
						}
						else {
							ooedata.setA_Addr1(responsedata.getData().get(i).getA_Addr1());
							ooedata.setA_Addr2(responsedata.getData().get(i).getA_Addr2());
							ooedata.setA_Name(responsedata.getData().get(i).getA_Name());
							ooedata.setA_PostCode(responsedata.getData().get(i).getA_PostCode());
							ooedata.setArticlenumber(responsedata.getData().get(i).getArticlenumber());
							ooedata.setBookingDate(responsedata.getData().get(i).getBookingDate());
							ooedata.setCIN_NO(cinno);									
							ooedata.setCindt((java.sql.Date) cindt);
							ooedata.setCUS_SITE(cussite);
							ooedata.setDestnCountry(responsedata.getData().get(i).getDestnCountry());
							ooedata.setFPO_CODE(fpocode);
							ooedata.setItemValue(responsedata.getData().get(i).getItemValue());
							ooedata.setItemWeight(responsedata.getData().get(i).getItemWeight());
							ooedata.setMailclass(responsedata.getData().get(i).getMailclass());
							ooedata.setOOE(responsedata.getData().get(i).getOOE());
							ooedata.setOriginCountry(responsedata.getData().get(i).getOriginCountry());
							ooedata.setPINCODE(pincode);
							ooedata.setReceived_Event_date(responsedata.getData().get(i).getReceived_Event_date());
							ooedata.setReceptacle_id(responsedata.getData().get(i).getReceptacle_id());
							ooedata.setS_NameAddr(responsedata.getData().get(i).getS_NameAddr());
							ooedata.setS_PostCode(responsedata.getData().get(i).getS_PostCode());
							//arrDataOOEService.saveOOE(responsedata.getData().get(i));
							ooedata.setStatus("D");
							arrDataOOEService.saveOOE(ooedata);	
							
						dupl=dupl+1;
						}
							ooearrdataRepo.updcinarrinfo();
					}	
					Long l= new Long(size);
					recnt=l;
	    	   }
					id=fromToDtooe.getId();
					java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					sentdt=currentDate1;
					loadDateOOEService.findById(id, sentdt, recnt, dupl);
					frdt=fromToDtooe.getFromDate();
					todt=fromToDtooe.getToDate();
					timelapse=(int) fromToDtooe.getTimelapse();
					System.out.println("ARR_REQ updated");
					OOEfromtodt dtmodel = new OOEfromtodt();
					frdt=todt;
					dtmodel.setId(id+1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				    calendar.add(Calendar.SECOND, 1);
				    frdt=calendar.getTime();
					dtmodel.setFromDate(frdt);
					dtmodel.setSuccess("N");
					dtmodel.setReccnt(0);
					dtmodel.setDupl(0);
					dtmodel.setTimelapse(timelapse);
					dtmodel.setCrdt(sentdt);
					dtmodel.setUpdt(null);
					//Calendar calendar = Calendar.getInstance();
					calendar.setTime(todt);
				   // calendar.add(Calendar.HOUR_OF_DAY, timelapse);
				    calendar.add(Calendar.MINUTE, timelapse);
				    todt=calendar.getTime();
					dtmodel.setToDate(todt);
					dtmodel=loadDateOOEService.updatedelimodelEntity(dtmodel);
					
		}			
				return responseEntityList;

	}
	public final boolean IsDigit(String s) {
		boolean IsDigit = true;

		if (s != null && !s.isEmpty()) {
			for (char c : s.toCharArray()) {
				if (!(Character.isDigit(c))) {
					return false;
				}
			}
		}

		return IsDigit;
	}
	public static String getCinNor() {

		String prefix = uniqId;
		String suffix = "00";
		String beforeSuffix = cinSeqNo;
		System.out.println("prefix is" + prefix);
		System.out.println("suffix is" + suffix);
		System.out.println("before suffix is +beforeSuffix");

		if (Integer.parseInt(beforeSuffix) < 99999999 && Integer.parseInt(beforeSuffix) > 0)
			beforeSuffix = String.format("%08d", Integer.parseInt(beforeSuffix) + 1);
		else
			beforeSuffix = String.format("%08d", Integer.parseInt("1"));

		// generatedSequenceValue = prefix + beforeSuffix + suffix;

		return prefix + beforeSuffix + suffix;

	}
	}
