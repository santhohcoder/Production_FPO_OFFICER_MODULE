package com.demo.fpo;

import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;





import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;






import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;





@Configuration
@EnableCaching
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.demo.*")
/*
 * @ComponentScan(includeFilters = @ComponentScan.Filter(type =
 * FilterType.ASPECTJ, pattern = "com.demo.*"))
 */
public class DemoApplication extends SpringBootServletInitializer{
	
	//public class DataFetcherApplication {
//		@PostConstruct
//		  void started() {
//		    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		  }
	
	public void init() throws IOException 
    {
      String log4jConfPath="../resources/log4j.properties";
      PropertyConfigurator.configure(log4jConfPath);
    }
	
	public static void main(String[] args) {
		for (String arg: args) {
			 System.out.println("arg value is " + arg);
		}
		if (args.length > 0)
			System.out.println("First argument in the List "+ args[0]);
		System.setProperty("javax.xml.bind.JAXBContextFactory", "com.sun.xml.bind.v2.ContextFactory");
//		String host = "proxy.cbec.gov.in";
//		String port = "3128";
//		System.out.println("Using proxy: " + host + ":" + port);
//		System.setProperty("http.proxyHost", host);
//		System.setProperty("http.proxyPort", port);
//		System.setProperty("https.proxyHost", host);
//		System.setProperty("https.proxyPort", port);


		SpringApplication.run(DemoApplication.class, args);
		
	}

	
	
	@Bean
	CacheManager cacheManager() {

	SimpleCacheManager cacheManager = new SimpleCacheManager();
	cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("getBcdSlNoId"),
	new ConcurrentMapCache("getIgstSerialNoId"), new ConcurrentMapCache("getSwSerialNoId"), new ConcurrentMapCache("getDUTY")));
	return cacheManager;
	}
}
