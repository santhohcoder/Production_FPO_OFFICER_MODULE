package com.demo.fpo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.demo.fpo.model.FpoPenal;
import com.demo.fpo.model.FpoPenalAmend;
import com.demo.fpo.repos.FpoPenalAmendRepo;
import com.demo.fpo.repos.FpoPenalRepo;

@Service
@Component
public class FpoPenalService {

	@Autowired
	FpoPenalRepo fpoPenalRepo;
	
	@Autowired
	FpoPenalAmendRepo fpoPenalAmendRepo;
	
	
	public void saveFineData(List<FpoPenal> fpoPenal) {
		fpoPenalRepo.saveAll(fpoPenal);
	}
	
	
	public List<FpoPenal> saveAmendPenalData(List<FpoPenal> fpoPenals) {
		List<FpoPenal> fpoPenalList = fpoPenalRepo.getAllPenalty(fpoPenals.get(0).getCinNo());
		if(null != fpoPenalList && !fpoPenalList.isEmpty())
			movePenalToAmend(fpoPenalList);
		fpoPenalRepo.deleteAll(fpoPenalList);
		fpoPenalRepo.saveAll(fpoPenals);
		return fpoPenals;
	}
	
	public void movePenalToAmend(List<FpoPenal> fpoPenal) {
		java.util.Date utilDate = new java.util.Date();
		List<FpoPenalAmend> fpoPenalAmendList = new ArrayList<FpoPenalAmend>();
		for (FpoPenal fpoPenals : fpoPenal) {
			FpoPenalAmend fpoPenalAmend = new FpoPenalAmend();
			
			fpoPenalAmend.setCinNo(fpoPenals.getCinNo());
			fpoPenalAmend.setCinDT(fpoPenals.getCinDT());
			fpoPenalAmend.setITEM_ID(fpoPenals.getITEM_ID());
			fpoPenalAmend.setCUS_SITE(fpoPenals.getCUS_SITE());
			fpoPenalAmend.setPOSTINGDT(fpoPenals.getPOSTINGDT());
			fpoPenalAmend.setPenalAmt(fpoPenals.getPenalAmt());
			fpoPenalAmend.setPenalUs(fpoPenals.getPenalUs());
			fpoPenalAmend.setOffId(fpoPenals.getOffId());
			fpoPenalAmend.setRole(fpoPenals.getRole());
			fpoPenalAmend.setAMEND_DT(utilDate);
			fpoPenalAmend.setAMEND_FLAG("U");
			fpoPenalAmendList.add(fpoPenalAmend);
		}
		fpoPenalAmendRepo.saveAll(fpoPenalAmendList);
	}
	
}
