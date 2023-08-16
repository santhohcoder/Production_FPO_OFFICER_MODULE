package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apimodel.Reasoncd;
import com.demo.fpo.apirepository.ReasoncdRepo;




	@Service
	public class ReasoncdService {
		@Autowired
		public ReasoncdRepo reasoncdRepo;
		
		public  List<Reasoncd> getAllData(Long reascd) {
			// TODO Auto-generated method stub

//			List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
			List<Reasoncd> reasList = reasoncdRepo.getAllData(reascd);
			return reasList;
		}
}
