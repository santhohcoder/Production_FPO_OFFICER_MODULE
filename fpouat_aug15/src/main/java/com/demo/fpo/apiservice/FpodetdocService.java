package com.demo.fpo.apiservice;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Fpodocdet;
import com.demo.fpo.apirepository.FpodetdocRepo;




@Component
public class FpodetdocService {
	@Autowired
    private  FpodetdocRepo fpodocdetRepo;
	
	public List getAllUsers() {
        return (List) this.fpodocdetRepo.findAll();
    }
	
    public Fpodocdet saveEntity(Fpodocdet fpodocdet){
     return fpodocdetRepo.save(fpodocdet);
    
    }
}
