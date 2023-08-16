package com.demo.fpo.apiservice;



//import javax.transaction.Transactional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.cusrsp;
import com.demo.fpo.apirepository.fpocusrspRepo;

//import org.springframework.stereotype.Service;

@Component
public class fpocusrspService {

	@Autowired
    private  fpocusrspRepo cusrspRepo;
	
	public List getAllUsers() {
        return (List) this.cusrspRepo.findAll();
    }

    public cusrsp updateFpomaininsEntity(cusrsp resp){
     return cusrspRepo.save(resp);
    
    }
}