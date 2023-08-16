package com.demo.fpo.apiservice;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Cmisseditem;
import com.demo.fpo.apirepository.CmisseditemRepo;

@Component
public class CmisseditemService {
	@Autowired
	private CmisseditemRepo cmisseditemrepo;
	public List getAllUsers() {
        return (List) this.cmisseditemrepo.findAll();
    }
	
    public Cmisseditem saveEntity(Cmisseditem cmisseditem){
     return cmisseditemrepo.save(cmisseditem);
    
    }
}
