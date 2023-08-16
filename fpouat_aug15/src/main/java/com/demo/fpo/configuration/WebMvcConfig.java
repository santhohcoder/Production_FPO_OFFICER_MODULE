package com.demo.fpo.configuration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demo.fpo.model.Filepath;
import com.demo.fpo.model.ReportColumns;
import com.demo.fpo.service.ReportService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ReportService reportService;

	@Bean
	WebMvcConfigurer configurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {

				Filepath filepath = reportService.getFilePaths();

				registry.addResourceHandler("/file/pdf/**").addResourceLocations("file:" + filepath.getDcall_qry_view())
						.addResourceLocations("file:" + filepath.getDcall_qry_rply())
						.addResourceLocations("file:" + filepath.getExamination_queue())
						.addResourceLocations("file:" + filepath.getDetained_queue())
						.addResourceLocations("file:" + filepath.getOoc_cancel())
						.addResourceLocations("file:" + filepath.getOth_doc());
			}
		};
	}
	
	  @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new CacheControlInterceptor());
	 }

}