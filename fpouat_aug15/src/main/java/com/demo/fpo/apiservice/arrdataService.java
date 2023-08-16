package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.arrdata;
import com.demo.fpo.apirepository.arrdatarepo;

@Component
public class arrdataService {

	@Autowired
	public arrdatarepo arrdataRepo;
	public List<arrdata> saveData(List<arrdata> Arrdata){
	     return arrdataRepo.saveAll(Arrdata);	
}
}
