package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.delistatLoad;
import com.demo.fpo.apirepository.DELIStatLoad;





@Component
public class DELIstatLoadspno {
	
	@Autowired
	public  DELIStatLoad delistatload;
	
	public List<delistatLoad> getAllData(){
		return delistatload.getspeedno();
	}
	
}
