package com.demo.fpo.apiservice;

import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.fpo.apirepository.LoadFpoRepo;

import com.demo.fpo.apimodel.Loadmodel;








@Service
public class LoadService {

	@Autowired
	public LoadFpoRepo loadFpoRepo;

//	public List<Loadmodel> getAllData() {
//		return loadFpoRepo.getLoad();
//	};

	public List<Loadmodel> getAllData() {
		List<Loadmodel> loadlist = ((LoadFpoRepo) loadFpoRepo).getAllData();
		return loadlist;
	}
	
	
	
	public Loadmodel updateLoadmodelEntity(Loadmodel loadmodel) {
		return loadFpoRepo.save(loadmodel);
	}
	public void findById(Long id,Date sentdt,Long cdrec) {
		// TODO Auto-generated method stub
		
		//System.out.println(123);
		loadFpoRepo.updateLoad(id,sentdt,cdrec);
	}
	
		
	}
