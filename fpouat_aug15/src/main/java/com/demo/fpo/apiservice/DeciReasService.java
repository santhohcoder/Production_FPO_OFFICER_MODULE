package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apimodel.DeciReas;
import com.demo.fpo.apirepository.DeciReasRepo;







@Service
public class DeciReasService {
	@Autowired
	public DeciReasRepo decireasRepo;
	
	public  List<DeciReas> getAllData(Long decicd) {
		// TODO Auto-generated method stub

//		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
		List<DeciReas> decireasList = decireasRepo.getAllData(decicd);
		return decireasList;
	}
	
	
}
