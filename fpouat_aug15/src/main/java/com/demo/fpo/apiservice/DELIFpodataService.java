package com.demo.fpo.apiservice;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.DELIfpodata;
import com.demo.fpo.apirepository.DELIFpodatarepo;

@Component
public class DELIFpodataService {

	@Autowired
	public DELIFpodatarepo delidataRepo;
	
	public List<DELIfpodata> saveData(List<DELIfpodata> Delidata){
	     return delidataRepo.saveAll(Delidata);	
}
	public DELIfpodata saveDELI(DELIfpodata delifpodata){
	     return delidataRepo.save(delifpodata);
	    
	    }
}
