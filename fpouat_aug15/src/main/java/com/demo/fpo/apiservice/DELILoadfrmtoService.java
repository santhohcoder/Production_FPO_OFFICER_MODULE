package com.demo.fpo.apiservice;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.DELIfromtodt;
import com.demo.fpo.apirepository.DELILoadfrmtodt;





@Component
public class DELILoadfrmtoService {
	
	@Autowired
	public DELILoadfrmtodt loadfrmtodtdeli;
	
	public List<DELIfromtodt> getAllData(){
		return loadfrmtodtdeli.getfrmtodt();
	}
	public DELIfromtodt updatedelimodelEntity(DELIfromtodt  FromTodtdeli) {
		return loadfrmtodtdeli.save(FromTodtdeli);
	}
		public void findById(Long id,Date sentdt, Long recnt) {
			
			loadfrmtodtdeli.updatedelireq(id, sentdt, recnt);
		}
}
