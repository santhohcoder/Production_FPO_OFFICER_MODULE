package com.demo.fpo.apicontroller;

import com.demo.fpo.apiservice.DELIRestService;
import com.demo.fpo.apiservice.DeliStatRestService;
import com.demo.fpo.apiservice.FPORestService;
import com.demo.fpo.apiservice.OOERestService;
import com.demo.fpo.apiservice.PreDesRestService;
import com.demo.fpo.apiservice.RestService;
import com.demo.fpo.model.DCALLQRY_GEN;
import com.demo.fpo.model.FPO_MAIL;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.MailSeqRepo;
import com.demo.fpo.service.RMSRespService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class FetchData {
 //   private static final Logger log = org.slf4j.LoggerFactory.getLogger(FetchData.class);

    @Autowired
    private DeliStatRestService restServiceDELISTAT;

    @Autowired
    private RestService restService;
    
    @Autowired
    private OOERestService restServiceOOE;
    
    @Autowired
    private DELIRestService restServiceDELI;
    
    @Autowired
    private FPORestService restServiceFPO;

    
    @Autowired
    private PreDesRestService restServicepredes;
    
    @Autowired
	FPO_MAINrepost FPOrepost;
    
    @Autowired
    RMSRespService RMSResp;
    

  // @Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "${cronjob.fpoval}")
    @GetMapping(value = "/fetchdataFPO", produces = { MediaType.APPLICATION_JSON_VALUE })
  //  @PostMapping(value = "/fetchdata", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ResponseEntity<String>> fetchDataFPO() throws IOException{

	String host = "proxy.cbec.gov.in";
		String port = "3128";
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
		System.setProperty("https.proxyHost", host);
		System.setProperty("https.proxyPort", port);

		return restServiceFPO.fetchData();
    }


   // @Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "${cronjob.ooeval}")
    @GetMapping(value = "/fetchdataOOE", produces = { MediaType.APPLICATION_JSON_VALUE })
    //  @PostMapping(value = "/fetchdata", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ResponseEntity<String>> fetchDataOOE() throws IOException{
	   String host = "proxy.cbec.gov.in";
		String port = "3128";
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
		System.setProperty("https.proxyHost", host);
		System.setProperty("https.proxyPort", port);

  		return restServiceOOE.fetchData();
      }


 //  @Scheduled(cron = "0 0/1 * * * *")
   @Scheduled(cron = "${cronjob.delival}")
    @GetMapping(value = "/fpodelivery", produces = { MediaType.APPLICATION_JSON_VALUE })
    //  @PostMapping(value = "/fetchdata", produces = { MediaType.APPLICATION_JSON_VALUE })
      public List<ResponseEntity<String>> fetchDatadeli() throws IOException{

 String host = "proxy.cbec.gov.in";
		String port = "3128";
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxyPort", port);
		System.setProperty("https.proxyHost", host);
		System.setProperty("https.proxyPort", port);

  		return restServiceDELI.fetchData();
      }

//   @Scheduled(cron = "0 0/30 * * * *")
 //   @Scheduled(cron = "${cronjob.othval}")
//   @GetMapping(value = "/fpodelistat", produces = { MediaType.APPLICATION_JSON_VALUE })
//  // @PostMapping(value = "/fpodelivery", produces = { MediaType.APPLICATION_JSON_VALUE })
//    public List<ResponseEntity<String>> fetchData() throws IOException, ParseException{
//    //public @ResponseBody fromtodt  fetchData(@RequestBody fromtodt Fromtodt){
////        log.info("RequestBody: "+requestBody);
////        return restService.fetchData(requestBody);
////    	log.info("RequestBody: " );
//      	String host = "proxy.cbec.gov.in";
//		String port = "3128";
//		System.setProperty("http.proxyHost", host);
//		System.setProperty("http.proxyPort", port);
//		System.setProperty("https.proxyHost", host);
//		System.setProperty("https.proxyPort", port);
//
//	   return restServiceDELISTAT.fetchData();
//    }


  // @Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "${cronjob.othval}")
   @GetMapping(value = "/fpodelistatspeed", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ResponseEntity<String>> fpodelistatspeed()
			throws IOException, ParseException, XPathExpressionException, SAXException, ParserConfigurationException {
		
				restServiceDELISTAT.fpospeedposttoass();
				 String host = "proxy.cbec.gov.in";
					String port = "3128";
					System.setProperty("http.proxyHost", host);
					System.setProperty("http.proxyPort", port);
					System.setProperty("https.proxyHost", host);
					System.setProperty("https.proxyPort", port);
		return restServiceDELISTAT.fetchDataspeedpost();
	}

   
    
  // @Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "${cronjob.othval}")
   @GetMapping(value = "/fpopredes", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ResponseEntity<String>> fpopredes() throws IOException, ParseException{
  String host = "proxy.cbec.gov.in";
    		String port = "3128";
    		System.setProperty("http.proxyHost", host);
    		System.setProperty("http.proxyPort", port);
    		System.setProperty("https.proxyHost", host);
    		System.setProperty("https.proxyPort", port); 
	   return restServicepredes.fpopredes();
    }
    
  
  // @Scheduled(cron = "0 0/1 * * * *")
 /*  @Scheduled(cron = "${cronjob.rmsval}")
   @GetMapping(value = "/rmsrespmain", produces = { MediaType.APPLICATION_JSON_VALUE })
    public void getrmsrespmain() throws IOException, ParseException{
    	 String host = "proxy.cbec.gov.in";
 		String port = "3128";
 		System.setProperty("http.proxyHost", host);
 		System.setProperty("http.proxyPort", port);
 		System.setProperty("https.proxyHost", host);
 		System.setProperty("https.proxyPort", port); 
   	 RMSResp.getRSPmainresp();

    }*/
    
    
   
    
}
