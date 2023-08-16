package com.demo.fpo.apiservice;


import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.cusrspitemtaxes;
import com.demo.fpo.apirepository.fpocusrspitemtaxesRepo;


@Component
public class cusrspitemtaxService {

	@Autowired
    private  fpocusrspitemtaxesRepo cusrspitemtaxRepo;
	
	public List getAllUsers() {
        return (List) this.cusrspitemtaxRepo.findAll();
    }

    public cusrspitemtaxes updateFpomaininsEntity(cusrspitemtaxes resp){
     return cusrspitemtaxRepo.save(resp);
    
    }
}