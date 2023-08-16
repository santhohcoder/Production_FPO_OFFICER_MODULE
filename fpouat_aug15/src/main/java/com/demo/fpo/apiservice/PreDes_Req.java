package com.demo.fpo.apiservice;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apimodel.Predes_fpo_Req;
import com.demo.fpo.apimodel.Predes_fpo_Req;
import com.demo.fpo.apirepository.FPOLoadfrmtodt;
import com.demo.fpo.apirepository.PreDesRepo;
@Service
public class PreDes_Req {
	
	@Autowired
	public PreDesRepo loadfrmtodtFPO;
	
	public List<Predes_fpo_Req> getAllData(){
		return loadfrmtodtFPO.getAllData();
	}
	
	public Predes_fpo_Req updatefpomodelEntity(Predes_fpo_Req  FromTodtfpo) {
		return loadfrmtodtFPO.save(FromTodtfpo);
	}
	public void findById(Long id,Date sentdt, Long recnt) {
		
		loadfrmtodtFPO.updatearrreq(id, sentdt, recnt);
	}

}
