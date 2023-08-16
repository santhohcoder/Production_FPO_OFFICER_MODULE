package com.demo.fpo.apiservice;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.OOEarrdata;
import com.demo.fpo.apirepository.OOEarrdatarepo;



@Component
public class OOEarrdataService {

	@Autowired
	public OOEarrdatarepo ArrdataOOERepo;
	public List<OOEarrdata> saveData(List<OOEarrdata> ArrdataOOE){
	     return ArrdataOOERepo.saveAll(ArrdataOOE);	
}
	
	public OOEarrdata saveOOE(OOEarrdata ooearrdata){
	     return ArrdataOOERepo.save(ooearrdata);
	    
	    }
	

}
