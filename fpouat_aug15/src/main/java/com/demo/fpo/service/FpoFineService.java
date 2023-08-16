package com.demo.fpo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoFine;
import com.demo.fpo.model.FpoFineAmend;
import com.demo.fpo.repos.FPO_AMENDrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoFineAmendRepo;
import com.demo.fpo.repos.FpoFineRepo;
import com.demo.fpo.repos.FpoPenalRepo;

@Service
@Component
public class FpoFineService {

	@Autowired
	FpoFineRepo fpoFineRepo;

	@Autowired
	FpoPenalRepo fpoPenalRepo;

	@Autowired
	FpoPenalService fpoPenalService;

	@Autowired
	FPO_MAINrepost fpoMainRepo;

	@Autowired
	FPO_AMENDrepost fpoMainAmendRepo;

	@Autowired
	FpoFineAmendRepo fpoFineAmendRepo;
	
	
	

	@Autowired
	FpoService fpoService;

	public List<FpoFine> saveFineData(List<FpoFine> fpoFines) {
		java.util.Date utilDate = new java.util.Date();
		fpoPenalService.saveFineData(fpoFines.get(0).getFpoPenalList());
		fpoFineRepo.saveAll(fpoFines);
		List<FPO_MAIN> fpoMainList = fpoMainRepo.getmain(fpoFines.get(0).getCinNo());
		fpoMainList.get(0).setDEP_CMTS(fpoFines.get(0).getComments());
		fpoMainList.get(0).setTOT_FINE(fpoFines.get(0).getTotFine());
		fpoMainList.get(0).setTOT_PENAL(fpoFines.get(0).getTotPenalty());
		fpoMainList.get(0).setTOT_AMT_PAYABLE(fpoFines.get(0).getTotalDutyPayable());
		fpoMainRepo.save(fpoMainList.get(0));
		return fpoFines;

	}

	public List<FpoFine> saveAmendFineData(List<FpoFine> fpoFines) {
		List<FpoFine> fpoFineList = fpoFineRepo.getAllFine(fpoFines.get(0).getCinNo());
		if(null != fpoFineList && !fpoFineList.isEmpty())
		moveFineToAmend(fpoFineList);
		fpoFineRepo.deleteAll(fpoFineList);
		fpoFineRepo.saveAll(fpoFines);
		fpoPenalService.saveAmendPenalData(fpoFines.get(0).getFpoPenalList());
		
		List<FPO_MAIN> fpoMainList = fpoMainRepo.getmain(fpoFines.get(0).getCinNo());
		fpoMainList.get(0).setDEP_CMTS(fpoFines.get(0).getComments());
		fpoMainList.get(0).setTOT_FINE(fpoFines.get(0).getTotFine());
		fpoMainList.get(0).setTOT_PENAL(fpoFines.get(0).getTotPenalty());
		fpoMainList.get(0).setTOT_AMT_PAYABLE(fpoFines.get(0).getTotalDutyPayable());
		fpoMainRepo.save(fpoMainList.get(0));
		
		
		return fpoFines;
	}

	public void moveFineToAmend(List<FpoFine> fpoFine) {
		java.util.Date utilDate = new java.util.Date();
		List<FpoFineAmend> fpoFineAmendList = new ArrayList<FpoFineAmend>();
		for (FpoFine fpoFines : fpoFine) {
			FpoFineAmend fpoFineAmend = new FpoFineAmend();
			fpoFineAmend.setCinNo(fpoFines.getCinNo());
			fpoFineAmend.setCinDT(fpoFines.getCinDT());
			fpoFineAmend.setITEM_ID(fpoFines.getITEM_ID());
			fpoFineAmend.setCUS_SITE(fpoFines.getCUS_SITE());
			fpoFineAmend.setPOSTINGDT(fpoFines.getPOSTINGDT());
			fpoFineAmend.setFineAmt(fpoFines.getFineAmt());
			fpoFineAmend.setFineUs(fpoFines.getFineUs());
			fpoFineAmend.setOffId(fpoFines.getOffId());
			fpoFineAmend.setRole(fpoFines.getRole());
			fpoFineAmend.setAMEND_DT(utilDate);
			fpoFineAmend.setAMEND_FLAG("U");
			fpoFineAmendList.add(fpoFineAmend);
		}
		fpoFineAmendRepo.saveAll(fpoFineAmendList);
	}
}