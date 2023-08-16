package com.demo.fpo.configuration;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Configuration
@AutoConfigureBefore({JacksonAutoConfiguration.class})
public class DateTimeAutoConfiguration {
	@Bean
	  public Jackson2ObjectMapperBuilderCustomizer 
	        jacksonObjectMapperBuilderCustomizer() {
	    return new Jackson2ObjectMapperBuilderCustomizer() {
	    
	      @Override
	      public void customize(
	              Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
	        final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	        //final String timeFormat = "hh:mm:ss a";
	        //final String dateTimeFormat = "dd/MM/yyyy HH:mm:ss a";
	        jacksonObjectMapperBuilder
	            .serializers(
	                new LocalDateSerializer(
	                    DateTimeFormatter.ofPattern(dateFormat)))
	            .deserializers(
	                new LocalDateDeserializer(
	                    DateTimeFormatter.ofPattern(dateFormat)));
	            
	      }
	    };
}
}
