package com.demo.fpo.apiConfig;


import java.io.InputStream;
import java.util.List;

import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import com.demo.fpo.apimodel.Cusitmrspurl;
import com.demo.fpo.apirepository.CusitmrspRepo;







@SuppressWarnings("unused")
@Configuration
public class BeanConfig {
	
	@Autowired
	private CusitmrspRepo cusitmrsprepo;
	
	public String url;
	public String methodurl;
	@Bean
	public SaajSoapMessageFactory messageFactory() throws SOAPException {
		SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory();
		saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_12);
		return saajSoapMessageFactory;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.demo.fpo.apidatafetcher.stub");
		return marshaller;
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}

	@Bean
	public SoapClient soapConnector(Jaxb2Marshaller marshaller, SaajSoapMessageFactory messageFactory) {
        //String urlname;
        //urlname="http://uat.cept.gov.in/CDS.API/CDSAPIService.svc";
		List<Cusitmrspurl> cusitmlst = cusitmrsprepo.getcusitm();
		url=((List<Cusitmrspurl>) cusitmlst).get(0).getCUSITM_URL();
		SoapClient client = new SoapClient();
		//client.setDefaultUri("http://uat.cept.gov.in/CDS.API/CDSAPIService.svc");
		client.setDefaultUri(url);
		client.setMessageFactory(messageFactory);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
