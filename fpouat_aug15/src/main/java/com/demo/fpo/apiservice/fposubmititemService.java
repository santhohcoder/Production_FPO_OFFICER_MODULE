package com.demo.fpo.apiservice;




import java.math.BigDecimal;

import org.hibernate.mapping.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.fposubmititem;
import com.demo.fpo.apirepository.fposubmititemRepo2;
import com.demo.fpo.apirepository.fposubmititemRepoImpl;



@Component
public class fposubmititemService {

	
	@Autowired
    private  fposubmititemRepoImpl fposubmititemRepo;
	
	@Autowired
	private fposubmititemRepo2 FposubmititemRepo2;
	
	public List getAllUsers() {
        return (List) this.fposubmititemRepo.findAll();
    }

	public void findById(Long id, BigDecimal assinsfrt, BigDecimal bcdamt,BigDecimal swsamt,BigDecimal igstamt, BigDecimal dutyitm){
		FposubmititemRepo2.upfpoitm(id, assinsfrt, bcdamt, swsamt, igstamt,dutyitm);
		
	}
//	public List getAllData(String cinno) {
//		//List<fposubmititem> fpoitemList = FposubmititemRepo2.findById(@Param("cinno") List<Long> FposubmititemRepo2);
//		//List fpoitemList =  (List) FposubmititemRepo2.findAllById(cinno);
//		//java.util.List<fposubmititem> fposubmititemList = FposubmititemRepo2.findAllByName(cinno);
//		//List<MyObject> findByInventoryIds(@Param("ids") List<Long> inventoryIdList);
//		//return  this.FposubmititemRepo2.findAllById(cinno);
//		//return  this.FposubmititemRepo2.findById(cinno);
//		System.out.println(this.FposubmititemRepo2.getAllData(cinno));
//		return (List) this.FposubmititemRepo2.getAllData(cinno);
////		return null;
//	}
	
	
	
//	@Transactional(readOnly = true)
//	public List<fposubmititem> getAllcinno(List cinno) {
//		List<fposubmititem> fposubmititemList = (List<fposubmititem>) FposubmititemRepo2.findAllById(cinno);
//		return fposubmititemList;
//	}
	
    public fposubmititem updateFpomaininsEntity(fposubmititem fposubmititem){
     return fposubmititemRepo.save(fposubmititem);
    
    }
}