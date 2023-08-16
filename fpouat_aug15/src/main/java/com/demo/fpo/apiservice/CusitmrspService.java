package com.demo.fpo.apiservice;


	
	import java.util.List;
	

	import org.springframework.beans.factory.annotation.Autowired;
	
	import org.springframework.stereotype.Service;

import com.demo.fpo.apimodel.Cusitmrspurl;
import com.demo.fpo.apirepository.CusitmrspRepo;

	


	@Service
	public class CusitmrspService {

		@Autowired
		public CusitmrspRepo cusitmrepo;

		public List<Cusitmrspurl> getAllData() {
			return cusitmrepo.getcusitm();
		};

}
