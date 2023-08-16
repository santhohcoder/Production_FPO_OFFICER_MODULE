package com.demo.fpo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.controller.LoginController;
import com.demo.fpo.model.CUSRSP_SENT;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoCurQue;
import com.demo.fpo.model.FpoMvmnt;
import com.demo.fpo.model.FpoStatus;
import com.demo.fpo.repos.CUSRSP_SENTRepo;
import com.demo.fpo.repos.FpoCurQueRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FPO_MAINrepost;

@Service
@Component
public class FpoCurQueService {

	@Autowired
	FpoCurQueRepo fpoCurQueRepo;

	@Autowired
	FpoMvmntRepo fpoMvmntRepo;

	@Autowired
	FpoStatusRepo fpoStatusRepo;
	
    @Autowired
    FPO_MAINrepost FPORepost;
	

	public void addUserQue(String cinNo, String itemId, String currQue, HttpSession session, HttpServletRequest request) {
		java.util.Date queDate = new java.util.Date();
		FpoCurQue fpoCurQue = new FpoCurQue();
		fpoCurQue.setCinNo(cinNo);
		fpoCurQue.setCurrQue(currQue);
		fpoCurQue.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoCurQue.setItemId(itemId);
		fpoCurQue.setOffId(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoCurQue.setQueDt(queDate);

		/*Long maxQueryNumber = fpoCurQueRepo.getMaxQnoOnCinNo(cinNo, request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());*/
		// changed for clearance site
		Long maxQueryNumber = fpoCurQueRepo.getMaxQnoOnCinNo(cinNo);
		//Long id = fpoCurQueRepo.getIdOnCin(cinNo, request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		Long id = fpoCurQueRepo.getIdOnCin(cinNo);

		if (null != id)
			fpoCurQue.setId(id);

		if (null != maxQueryNumber) {
			fpoCurQue.setQueNo(maxQueryNumber + 1l);
		} else {
			fpoCurQue.setQueNo(1l);
		}
		fpoCurQue.setRole(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoCurQueRepo.save(fpoCurQue);
	}

	public void updateUSerEnterStatus(String cinNo, String currQue, HttpSession session, String stage, HttpServletRequest request ) {
		System.out.println("comes in inserting new record of FPOMVMNT");
		java.util.Date queDate = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long maxRecods = fpoMvmntRepo.getRecordOnCin(cinNo,request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		String cusite=request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		Long slNo = fpoMvmntRepo.getMaxSlOnCin(cinNo);
		java.util.Date cindt = fpoMvmntRepo.getcindtmvmnt(cinNo);
        System.out.println("currQue is" + currQue);
		if (null != slNo)
			slNo = slNo + 1;
		else
			slNo = 1l;
        System.out.println("Maxrecords is " + maxRecods);
		if (maxRecods == 0) {
		System.out.println("comes in insertion");
			FpoMvmnt fpoMvmnt = new FpoMvmnt();
			fpoMvmnt.setCinNo(cinNo);
			fpoMvmnt.setRole(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoMvmnt.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoMvmnt.setOffId(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoMvmnt.setEndDt(queDate);
			fpoMvmnt.setCinDt(cindt);
			fpoMvmnt.setSlNo(slNo);
			fpoMvmnt.setItemid(FPORepost.getitemid(cinNo));
			if (slNo > 1)
				if (stage.equals("E1"))
				{	
					fpoMvmntRepo.updextdtstr(cinNo, utilDate);
					if (FPORepost.getarrfpo(FPORepost.getitemid(cinNo),cusite) > 0)
						stage="P1";
					else
					    stage="N1";				
				}
			fpoMvmnt.setStage(stage);
			fpoMvmntRepo.save(fpoMvmnt);
		} else if (currQue == "IMP") {
			FpoMvmnt fpoMvmnt = new FpoMvmnt();
			fpoMvmnt.setCinNo(cinNo);
			fpoMvmnt.setRole(currQue);
			fpoMvmnt.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoMvmnt.setEndDt(queDate);
			fpoMvmnt.setCinDt(cindt);
			fpoMvmnt.setSlNo(slNo);
			if (slNo > 1)
			if (stage.equals("E1"))
			{
				fpoMvmntRepo.updextdtstr(cinNo, utilDate);
				stage="P1";
			}
			fpoMvmnt.setStage(stage);
			fpoMvmnt.setItemid(FPORepost.getitemid(cinNo));
			fpoMvmntRepo.save(fpoMvmnt);
		}
	}
	
	public void updateQueryEnterStatus(String cinNo, String currQue,String stage, HttpSession session, HttpServletRequest request) {
		java.util.Date queDate = new java.util.Date();
		Long slNo = fpoMvmntRepo.getMaxSlOnCin(cinNo);
		java.util.Date cindt = fpoMvmntRepo.getcindtmvmnt(cinNo);
		if (null != slNo)
			slNo = slNo + 1;
		else
			slNo = 1l;
		FpoMvmnt fpoMvmnt = new FpoMvmnt();
		fpoMvmnt.setCinNo(cinNo);
		fpoMvmnt.setRole(currQue);
		fpoMvmnt.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoMvmnt.setOffId(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoMvmnt.setEndDt(queDate);
		fpoMvmnt.setCinDt(cindt);
		fpoMvmnt.setSlNo(slNo);
		fpoMvmnt.setStage(stage);
		fpoMvmnt.setItemid(FPORepost.getitemid(cinNo));
		fpoMvmntRepo.save(fpoMvmnt);
	}

	public void updateUSerExitStatus(String cinNo, String currQue) {
		java.util.Date queDate = new java.util.Date();
		fpoMvmntRepo.getIdOnCin(cinNo);
		fpoMvmntRepo.updateUSerExitStatus(fpoMvmntRepo.getIdOnCin(cinNo), queDate);
		/*
		 * FpoMvmnt fpoMvmnt = new FpoMvmnt(); fpoMvmnt.setCinNo(cinNo);
		 * fpoMvmnt.setCurrQue(currQue); fpoMvmnt.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		 * fpoMvmnt.setOffId(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString()); fpoMvmnt.setEndDt(queDate);
		 * fpoMvmnt.setExtDt(queDate); fpoMvmnt.setCinDt(queDate);
		 * fpoMvmntRepo.save(fpoMvmnt);
		 */
	}

	public void updateStatus(FPO_MAIN fpoMain, HttpSession session, HttpServletRequest request) {
		java.util.Date curDate = new java.util.Date();
		Long id=fpoStatusRepo.getIdFpoStatus(fpoMain.getId());
		String offId = request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString();
		FpoStatus fpoStatus = new FpoStatus();
		
		if(id != null) {
			fpoStatus.setId(id);
		
		fpoStatus.setCinNo(fpoMain.getId());
		fpoStatus.setCinDt(fpoMain.getCIN_DT());
		fpoStatus.setItemId(fpoMain.getITEM_ID());
		fpoStatus.setCuSite(fpoMain.getCUS_SITE());
		fpoStatus.setPstDt(fpoMain.getPOSTINGDT());
		fpoStatus.setAssDt(curDate);
        System.out.println("role is " + request.getSession().getAttribute("role"));
		fpoStatus.setMsgTypeCd(fpoMain.getMESG_TYPE_CD());
		fpoStatusRepo.save(fpoStatus);
		}
		 if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAO")) {
				System.out.println("here in APR");
				FPORepost.updfpostatusapr(curDate,offId,fpoMain.getId(),fpoMain.getCUS_SITE());
			} 
		else if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAC")) {
				System.out.println("here in ACL");
				FPORepost.updfpostatusacl(curDate,offId,fpoMain.getId(),fpoMain.getCUS_SITE());
	        }
	}
}
