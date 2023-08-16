package com.demo.fpo.apiservice;


import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Fpomainins;
import com.demo.fpo.apirepository.FpomaininsRepo;


@Component
public class FpomaininsService {

	@Autowired
    private  FpomaininsRepo fpomaininsRepo;
	
//	public List getAllData() {
//		return (List) this.fpomaininsRepo.getfpomain();
//		
//	}
	
	
	public List getAllUsers() {
        return (List) this.fpomaininsRepo.findAll();
    }


    public Fpomainins updateFpomaininsEntity(Fpomainins fpomainins){
     return fpomaininsRepo.save(fpomainins);
      
    }
}
