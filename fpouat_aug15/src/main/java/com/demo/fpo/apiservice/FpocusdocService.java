package com.demo.fpo.apiservice;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Fpocusitmdoc;
import com.demo.fpo.apirepository.FpocusdocRepo;



@Component
public class FpocusdocService {
	@Autowired
    private  FpocusdocRepo fpocusdocRepo;
	
	public List getAllUsers() {
        return (List) this.fpocusdocRepo.findAll();
    }

    public Fpocusitmdoc saveEntity(Fpocusitmdoc fpocusdoc){
     return fpocusdocRepo.save(fpocusdoc);
    
    }
}
