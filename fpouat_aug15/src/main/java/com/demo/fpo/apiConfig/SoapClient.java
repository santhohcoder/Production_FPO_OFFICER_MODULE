package com.demo.fpo.apiConfig;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.addressing.client.ActionCallback;
import org.springframework.ws.soap.addressing.version.Addressing10;

import com.demo.fpo.apidatafetcher.stub.CreateOrUpdateResponses;
import com.demo.fpo.apidatafetcher.stub.LoadResponse;
import com.demo.fpo.apimodel.PostDataServecds;
import com.demo.fpo.apiservice.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;

//@Service
public class SoapClient extends WebServiceGatewaySupport {
//	@Autowired
//	private CusitmrspRepo cusitmrsprepo;
	
	@Autowired
	PostService postService;
	
	public String url;
	public String methodurl;
	
		public LoadResponse Load(Object request) {
		
		PostDataServecds postDataServe=postService.fetchPostData();
		url=postDataServe.getCusitmurl1();
		methodurl=postDataServe.getCusitmurl2();
		
		 //List<Cusitmrspurl> cusitmlst = cusitmrsprepo.getcusitm();
			//url=((List<Cusitmrspurl>) cusitmlst).get(0).getCUSITM_URL();
			//methodurl=((List<Cusitmrspurl>) cusitmlst).get(0).getCUSITM_METHODURL();
//			System.out.println(cusitmlst.size());
//			System.out.println("URL is "+url);
//			System.out.println("MethodURL is "+methodurl);
		// @SuppressWarnings("rawtypes")
		// JAXBElement res = (JAXBElement)
		// getWebServiceTemplate().marshalSendAndReceive(url, request);
//		LoadResponse response = (LoadResponse) getWebServiceTemplate()
//		        .marshalSendAndReceive("http://ipsweb.cept.gov.in/CDS.API/CDSAPIService.svc", request);
		try {
			LoadResponse response = (LoadResponse) getWebServiceTemplate().marshalSendAndReceive(request,
					new ActionCallback(new URI(methodurl), new Addressing10(),
							new URI(url)));
//			LoadResponse response = (LoadResponse) getWebServiceTemplate().marshalSendAndReceive(request,
//					new ActionCallback(new URI("http://tempuri.org/ICDSAPIService/Load"), new Addressing10(),
//							new URI("http://uat.cept.gov.in/CDS.API/CDSAPIService.svc")));
			
			
//		LoadResponse response = (LoadResponse) getWebServiceTemplate().marshalSendAndReceive(request);
			return response;
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		// return (LoadResponse) res.getValue();
	}
	
	public CreateOrUpdateResponses LoadResponse(Object request) {
		PostDataServecds postDataServe=postService.fetchPostData();
		url=postDataServe.getCusrspurl1();
		methodurl=postDataServe.getCusrspurl2();
		
//		List<Cusitmrspurl> cusitmlst = cusitmrsprepo.getcusitm();
//		url=((List<Cusitmrspurl>) cusitmlst).get(0).getCUSRSP_URL();
//		methodurl=((List<Cusitmrspurl>) cusitmlst).get(0).getCUSRSP_METHODURL();
//		System.out.println(cusrsplst.size());
//		System.out.println("URL is "+url);
//		
//		System.out.println("MethodURL is "+methodurl);
        try{
        	//CreateOrUpdateResponses response = (CreateOrUpdateResponses) getWebServiceTemplate().marshalSendAndReceive(request, new ActionCallback(new URI("http://tempuri.org/ICDSAPIService/CreateOrUpdateResponses"), new Addressing10(),new URI("http://uat.cept.gov.in/CDS.API/CDSAPIService.svc")));
        	getWebServiceTemplate().marshalSendAndReceive(request, new ActionCallback(new URI(methodurl), new Addressing10(),new URI(url)));
            return new CreateOrUpdateResponses();
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }

		
	}
}
