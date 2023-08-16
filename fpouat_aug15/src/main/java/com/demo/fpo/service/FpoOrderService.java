package com.demo.fpo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.model.FPO_AC_CMTS;
import com.demo.fpo.model.FPO_APR_CMTS;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FPO_ORDER;
import com.demo.fpo.repos.FPO_AC_CMTSRepo;
import com.demo.fpo.repos.FPO_Apr_CMTSRepo;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FPO_ORDERrepost;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoQueryDecisionRepo;

@Service
@Component
public class FpoOrderService {

	@Autowired
	FPO_ORDERrepost fpoOrderRepo;

	@Autowired
	FPO_MAINrepost fpo_mainrepost;

	@Autowired
	FpoOrderService fpoOrderService;

	@Autowired
	FPO_AC_CMTSRepo fpoAcCmtsRepo;

	@Autowired
	FPO_Apr_CMTSRepo fpoAprCmtsRepo;
	
	@Autowired 
	FpoMvmntRepo fpomvmt;
	
	@Autowired
	FpoQueryDecisionRepo fpoQueryDecisionRepo;
	
	@Autowired
	FpoCurQueService fpoCurQueService;

	public int getchoice(String ExamOrder) {
		int choice=0;
		if (ExamOrder.equals("Scan"))
			choice=1;
		else if (ExamOrder.equals("Detain"))
			choice=2;
		else if (ExamOrder.equals("Examination"))
			choice=3;
		else if (ExamOrder.equals("Detain,Examination"))
			choice=4;
		System.out.println("choice is " + choice);
		return choice;
	}

	public void saveOrder(FPO_ORDER fpoOrder, HttpSession session, HttpServletRequest request) {

		java.util.Date utilDate = new java.util.Date();
		fpoOrder.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoOrder.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoOrder.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoOrder.setORDER_DATE(utilDate);
		fpoOrder.setITEM_ID(fpo_mainrepost.getitemid(fpoOrder.getCIN_NO()));
		fpoOrderRepo.save(fpoOrder);

	}

	public void updateRole(FPO_ORDER fpoOrder, HttpSession session, HttpServletRequest request) {
		fpoOrderService.saveOrder(fpoOrder,  session, request);
		fpo_mainrepost.updateRole(fpoOrder.getCIN_NO());
	}
	
public void updateQueryType(FPO_ORDER fpoOrder, HttpSession session, HttpServletRequest request) {
		
		fpoQueryDecisionRepo.updateQryTypeP1(fpoOrder.getCIN_NO());
		
	}
	
	public void updateFirstOrder(FPO_ORDER fpoOrder, HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		fpoOrderService.savefirstOrder(fpoOrder,  session, request);
		fpo_mainrepost.updateRole(fpoOrder.getCIN_NO());
		fpomvmt.updextdt(fpoOrder.getCIN_NO(), curdt);
	}
	
	public FPO_ORDER updateMainRole(FPO_ORDER fpoOrder,HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		fpo_mainrepost.updateRole(fpoOrder.getCIN_NO());
		fpomvmt.updextdt(fpoOrder.getCIN_NO(), curdt);
		fpoOrderService.updateQueryType(fpoOrder, session, request);
		//fpoOrderService.saveOrder(fpoOrder,session,request);
		return fpoOrder;
	}
	
	public FPO_ORDER deleteCheck(FPO_ORDER fpoOrder) {
		fpoOrderRepo.updateStus(fpoOrder.getCIN_NO());
		return fpoOrder;
	}
/*
	public FPO_AC_CMTS sendBackApr(FPO_AC_CMTS fpoCmts, HttpSession session, HttpServletRequest request) {
		int seqNo = 0;
		java.util.Date curdt = new java.util.Date();
		List<FPO_AC_CMTS> seqNoList = fpoAcCmtsRepo.getAcseqNo(fpoCmts.getCin_NO());
		if (null != seqNoList && !seqNoList.isEmpty()) {
			seqNo = seqNoList.size();
		}
		fpoCmts.setSeq_NO(seqNo + 1l);
		fpoCmts.setOff_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoCmts.setCmts(fpoCmts.getCmts());
		fpoCmts.setEntry_DT(curdt);
		fpo_mainrepost.updateRoleApr(fpoCmts.getCin_NO());
		fpomvmt.updextdt(fpoCmts.getCin_NO(), curdt);
		return fpoAcCmtsRepo.save(fpoCmts);
	}
*/
	
	//santh,veem,kani
//	public FPO_AC_CMTS sendBackApr(FPO_AC_CMTS fpoCmts, HttpSession session, HttpServletRequest request) {
//		int seqNo = 0;
//		
//		String stage=fpoAcCmtsRepo.stages(fpoCmts.getCin_NO());
//		if(stage == null)
//			 stage = "";
//		java.util.Date curdt = new java.util.Date();
//		List<FPO_AC_CMTS> seqNoList = fpoAcCmtsRepo.getAcseqNo(fpoCmts.getCin_NO());
//		if (null != seqNoList && !seqNoList.isEmpty()) {
//			seqNo = seqNoList.size();
//		}
//		fpoCmts.setSeq_NO(seqNo + 1l);
//		fpoCmts.setOff_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
//		fpoCmts.setCmts(fpoCmts.getCmts());
//		fpoCmts.setEntry_DT(curdt);
//		fpo_mainrepost.updateRoleApr(fpoCmts.getCin_NO());
//		if(stage.equals("EAD")) {
//		   fpomvmt.updextdt(fpoCmts.getCin_NO(), curdt);
//		   fpoCurQueService.updateQueryEnterStatus(fpoCmts.getCin_NO(), "PAC", "A2" , session, request);
//		}
//			fpomvmt.updextdt(fpoCmts.getCin_NO(), curdt);
//			return fpoAcCmtsRepo.save(fpoCmts);
//	}
	
	public FPO_AC_CMTS sendBackApr(FPO_AC_CMTS fpoCmts, HttpSession session, HttpServletRequest request) {
		int seqNo = 0;
		
		String stage=fpoAcCmtsRepo.stages(fpoCmts.getCin_NO());
		List<FPO_MAIN> fpoMain = fpo_mainrepost.getmain(fpoCmts.getCin_NO());
		Float maxaclassval = fpo_mainrepost.getaclassval();
		if(stage == null)
			 stage = "";
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		List<FPO_AC_CMTS> seqNoList = fpoAcCmtsRepo.getAcseqNo(fpoCmts.getCin_NO());
		if (null != seqNoList && !seqNoList.isEmpty()) {
			seqNo = seqNoList.size();
		}
		fpoCmts.setSeq_NO(seqNo + 1l);
		fpoCmts.setOff_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoCmts.setCmts(fpoCmts.getCmts());
		fpoCmts.setEntry_DT(curdt);
		fpo_mainrepost.updateRoleApr(fpoCmts.getCin_NO());
		if(stage.equals("EAD")) {
		   fpomvmt.updextdt(fpoCmts.getCin_NO(), curdt);
		   fpoCurQueService.updateQueryEnterStatus(fpoCmts.getCin_NO(), "PAC", "A2" , session, request);
		}
		if((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAC")) {
			fpoQueryDecisionRepo.updassqry(fpoCmts.getCin_NO(), utilDate, "PAO");
		}
			fpomvmt.updextdt(fpoCmts.getCin_NO(), curdt);
			return fpoAcCmtsRepo.save(fpoCmts);
	}
	public FPO_APR_CMTS sendBackAcl(FPO_APR_CMTS aprCmts, HttpSession session, HttpServletRequest request) {
		int seqNo = 0;
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		List<FPO_APR_CMTS> seqNoList = fpoAprCmtsRepo.getAprseqNo(aprCmts.getCin_NO());
		if (null != seqNoList && !seqNoList.isEmpty()) {
			seqNo = seqNoList.size();
		}
		aprCmts.setSeq_NO(seqNo + 1l);
		aprCmts.setCmts(aprCmts.getCmts());
		aprCmts.setEntry_DT(curdt);
		
		List<FPO_MAIN> fpoMain = fpo_mainrepost.getmain(aprCmts.getCin_NO());
		Float maxaclassval = fpo_mainrepost.getaclassval();
	
	//	System.out.println(request.getSession().getAttribute("offId").toString());
		aprCmts.setOff_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		if((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAO") ) {
			fpoQueryDecisionRepo.updassqry(aprCmts.getCin_NO(), utilDate, "PAC");
		}
		fpomvmt.updextdt(aprCmts.getCin_NO(), curdt);
		fpo_mainrepost.updateRole(aprCmts.getCin_NO());
		return fpoAprCmtsRepo.save(aprCmts);
	}

	public void savefirstOrder(FPO_ORDER fpoOrder, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		fpoOrder.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoOrder.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoOrder.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoOrder.setITEM_ID(fpo_mainrepost.getitemid(fpoOrder.getCIN_NO()));
		fpoOrder.setFIRST_CHECK("Y");
		fpoOrder.setORDER_DATE(utilDate);
		fpoOrderRepo.save(fpoOrder);
		fpomvmt.updextdt(fpoOrder.getCIN_NO(),utilDate);
        fpo_mainrepost.updateRole(fpoOrder.getCIN_NO());		
	}

	

	public String getOrderCinNo(String cinNo) {
		Long orderNo = fpoOrderRepo.getOrderCinNo(cinNo);
		if (null != orderNo) {
			return orderNo.toString();
		}
		return null;
	}

	public String getfirstOrder(String cinNo) {
		Long firstOrder = fpoOrderRepo.getfirstOrder(cinNo);
		if (null != firstOrder) {
			return firstOrder.toString();
		}
		return null;
	}

	public String getExamOrder(String cinNo) {
		String examOrder = fpoOrderRepo.getOrder(cinNo);
		if (null != examOrder) {
			return examOrder;
		}
		return null;
	}

}
