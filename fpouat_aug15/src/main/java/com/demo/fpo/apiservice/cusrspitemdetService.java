package com.demo.fpo.apiservice;


import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.cusrspitemdet;
import com.demo.fpo.apirepository.fpocusrspitemRepo;

@Component
public class cusrspitemdetService {

	@Autowired
    private  fpocusrspitemRepo cusrspitemRepo;
	
	public List getAllUsers() {
        return (List) this.cusrspitemRepo.findAll();
    }

    public cusrspitemdet updateFpomaininsEntity(cusrspitemdet resp){
     return cusrspitemRepo.save(resp);
    
    }
}