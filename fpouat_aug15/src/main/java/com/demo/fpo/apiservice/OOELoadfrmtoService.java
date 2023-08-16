package com.demo.fpo.apiservice;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.OOEfromtodt;
import com.demo.fpo.apirepository.OOELoadfrmtodt;


@Component
public class OOELoadfrmtoService {
	
	@Autowired
	public OOELoadfrmtodt loadfrmtodtooe;
	
	public List<OOEfromtodt> getAllData(){
		return loadfrmtodtooe.getAllData();
	}
	
	public OOEfromtodt updatedelimodelEntity(OOEfromtodt  FromTodtooe) {
		return loadfrmtodtooe.save(FromTodtooe);
	}
	public void findById(Long id,Date sentdt, Long recnt, Long dupl) {
		
		loadfrmtodtooe.updatearrreq(id, sentdt, recnt,dupl);
	}
}
