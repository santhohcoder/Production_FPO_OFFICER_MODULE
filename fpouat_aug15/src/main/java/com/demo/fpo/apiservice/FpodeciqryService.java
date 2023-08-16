package com.demo.fpo.apiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apirepository.FpodeciqryRepo;
import com.demo.fpo.apirepository.FpodeciqryRepo.Deciqryfpostatus;
 




@Service
public class FpodeciqryService {
	@Autowired
	public FpodeciqryRepo fpoDeciqryRepo;

	public List<Deciqryfpostatus> getAllData() {
		return fpoDeciqryRepo.getdeciqry();
	}

//	public  Deciqryfpostatus findById(String cinno) {
//		// TODO Auto-generated method stub
//
////		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
//		Deciqryfpostatus DeciqryfpostatusList = fpoDeciqryRepo.getdeciqry1(cinno);
//		return DeciqryfpostatusList;
////		return null;
//	}
//////	
//	public  List<Deciqryfpostatus> getdeciqry(Long id) {
////		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
//		List<Deciqryfpostatus> deciqryfpostatus = fpoDeciqryRepo.getdeciqry(id);
//		return deciqryfpostatus;
////		return null;
//	}
//	public List<Fpoqrydeci> getAllData() {
//		return fpoDeciqryRepo.getdeciqry1();
//	}
}
