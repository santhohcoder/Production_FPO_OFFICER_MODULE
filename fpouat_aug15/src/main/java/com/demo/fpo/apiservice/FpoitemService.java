package com.demo.fpo.apiservice;


//import consumeCDSAPI.consumeRepos.FpomaininsRepo;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Fpoitemins;
import com.demo.fpo.apirepository.FpoiteminsRepo;



@Component
public class FpoitemService {

	@Autowired
    private  FpoiteminsRepo fpoiteminsRepo;
	
	public List getAllUsers() {
        return (List) this.fpoiteminsRepo.findAll();
    }

    public Fpoitemins updateFpomaininsEntity(Fpoitemins fpoitemins){
     return fpoiteminsRepo.save(fpoitemins);
    
    }
}
