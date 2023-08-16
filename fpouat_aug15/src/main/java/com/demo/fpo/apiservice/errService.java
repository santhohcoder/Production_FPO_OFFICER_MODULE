package com.demo.fpo.apiservice;


//import consumeCDSAPI.consumeRepos.FpomaininsRepo;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.errinsert;
import com.demo.fpo.apirepository.errinsertRepo;


@Component
public class errService {

	@Autowired
    private  errinsertRepo errinsRepo;
	
	public List getAllUsers() {
        return (List) this.errinsRepo.findAll();
    }

    public errinsert updateFpomaininsEntity(errinsert errins){
     return errinsRepo.save(errins);
    
    }
}