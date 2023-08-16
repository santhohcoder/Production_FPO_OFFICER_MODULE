package com.demo.fpo.apiservice;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apirepository.FposubmitRepo;
import com.demo.fpo.apirepository.FposubmitRepo2;
import com.demo.fpo.apimodel.Fposubmit;

@Component
public class FposubmitService {

	
	@Autowired
	private FposubmitRepo fposubmitRepo;
	@Autowired
	private FposubmitRepo2 fposubmitRepo2;
	
	public List<Fposubmit> getAllUsers() {
		 return this.fposubmitRepo.findAll();
    }
	public  Fposubmit findById(String cinno) {
		// TODO Auto-generated method stub

//		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
		Fposubmit fposubmitList = fposubmitRepo2.findById(cinno);
		return fposubmitList;
//		return null;
	}

	public  Fposubmit itemIdbookdt(String itemid,String bookdt) {
		// TODO Auto-generated method stub

//		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
		Fposubmit fposubmitList = fposubmitRepo2.itemIdbookdt(itemid, bookdt);
		return fposubmitList;
//		return null;
	}

	public  Fposubmit itemId(String itemid) {
		// TODO Auto-generated method stub

//		List<Fposubmit> fposubmitList = fposubmitRepo2.findById(cinno);
		if (fposubmitRepo2.getcouitemId(itemid) > 0)
		{    Fposubmit fposubmitList = fposubmitRepo2.itemId(itemid);
		return fposubmitList;}
		else
		return null;
	}
	
    public Fposubmit updateFpomaininsEntity(Fposubmit fposubmit){
     return fposubmitRepo.save(fposubmit);
               
    }
}