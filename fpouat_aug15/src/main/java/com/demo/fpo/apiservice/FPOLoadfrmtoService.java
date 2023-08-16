package com.demo.fpo.apiservice;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.FPOfromtodt;
import com.demo.fpo.apirepository.FPOLoadfrmtodt;



@Component
public class FPOLoadfrmtoService {
	
	@Autowired
	public FPOLoadfrmtodt loadfrmtodtFPO;
	
	public List<FPOfromtodt> getAllData(){
		return loadfrmtodtFPO.getAllData();
	}
	
	public FPOfromtodt updatefpomodelEntity(FPOfromtodt  FromTodtfpo) {
		return loadfrmtodtFPO.save(FromTodtfpo);
	}
	public void findById(Long id,Date sentdt, Long recnt) {
		
		loadfrmtodtFPO.updatearrreq(id, sentdt, recnt);
	}
}
