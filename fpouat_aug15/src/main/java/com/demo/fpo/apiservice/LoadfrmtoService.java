package com.demo.fpo.apiservice;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.fromtodt;
import com.demo.fpo.apirepository.Loadfrmtodt;

@Component
public class LoadfrmtoService {
	
	@Autowired
	public Loadfrmtodt loadfrmtodt;
	
	public List<fromtodt> getAllData(){
		return loadfrmtodt.getAllData();
	}
	
	public fromtodt updatedelimodelEntity(fromtodt  FromTodt) {
		return loadfrmtodt.save(FromTodt);
	}
	public void findById(Long id,Date sentdt, Long recnt) {
		
		loadfrmtodt.updatearrreq(id, sentdt, recnt);
	}
}
