package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.FPOarrdata;
import com.demo.fpo.apirepository.FPOarrdatarepo;


@Component
public class FPOarrdataService {

	@Autowired
	public FPOarrdatarepo arrdataFPORepo;
	public List<FPOarrdata> saveData(List<FPOarrdata> ArrdataFPO){
	     return arrdataFPORepo.saveAll(ArrdataFPO);	
}
	public FPOarrdata saveFPO(FPOarrdata fpoarrdata){
	     return arrdataFPORepo.save(fpoarrdata);
	    
	    }
}
