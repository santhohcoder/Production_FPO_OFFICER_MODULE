package com.demo.fpo.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.model.FpoStatus;



@Component
public class FpostatusService {
	@Autowired
	private FpoStatusRepo fpoStatusrepo;
	public FpoStatus updateFpostatinsEntity(FpoStatus Fpostatins){
	     return fpoStatusrepo.save(Fpostatins);
	    
	    }
}
