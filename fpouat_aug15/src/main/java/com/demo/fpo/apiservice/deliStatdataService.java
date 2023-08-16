package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Delistatusdata;
import com.demo.fpo.apirepository.delistatdataRepo;


@Component
public class deliStatdataService {

	@Autowired
	public delistatdataRepo delistatrepo;
	public List<Delistatusdata> saveData(List<Delistatusdata> delistatusdata){
	     return delistatrepo.saveAll(delistatusdata);	
}
	public Delistatusdata saveDELI(Delistatusdata delistatdata){
	     return delistatrepo.save(delistatdata);
}
}