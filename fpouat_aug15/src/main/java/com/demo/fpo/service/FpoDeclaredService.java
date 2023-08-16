package com.demo.fpo.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.apirepository.DutyCalcDetailsRepo;
import com.demo.fpo.apirepository.fposubmititemRepoImpl;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.model.FPOSecondDefQry;
import com.demo.fpo.model.FPO_ASS_PAO_CMTS;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoItemDetOthDuty;
import com.demo.fpo.model.FpoItemDetOthDutyAmend;
import com.demo.fpo.model.FpoMvmnt;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryDecision;
import com.demo.fpo.model.FpoQueryDin;
import com.demo.fpo.model.FpoQueryamend;
import com.demo.fpo.model.Fpo_qry_raise;
import com.demo.fpo.model.Itemdet;
import com.demo.fpo.model.Reply;
import com.demo.fpo.repos.FPO_AMENDrepost;
import com.demo.fpo.repos.FPO_ASS_PAO_CMTSRepo;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoAddlQryRepo;
import com.demo.fpo.repos.FpoCurQueRepo;
import com.demo.fpo.repos.FpoItemDetOthDutyAmendRepo;
import com.demo.fpo.repos.FpoItemDetOthDutyRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoQueryAmendRepo;
import com.demo.fpo.repos.FpoQueryDecisionRepo;
import com.demo.fpo.repos.FpoQueryDinRepo;
import com.demo.fpo.repos.FpoQueryRepo;

@Service
@Component
public class FpoDeclaredService {
	
	@Autowired
	FpoQueryDinRepo fpoq;

	@Autowired
	FPO_ASS_PAO_CMTSRepo fpopaocmts;
	
	@Autowired
	FPO_ITEM_DETrepost fpoItemDetRepo;

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FPO_AMENDrepost fpoAmendRepost;

	@Autowired
	FpoCurQueRepo fpocurquerepo;

	@Autowired
	FpoService fpoService;
	
	@Autowired
	FpoMvmntRepo fpoMvmntRepo;
	
	@Autowired
	FpoQueryDecisionRepo fpoQueryDecisionRepo;

	@Autowired
	FpoItemDetOthDutyRepo fpoItemDetOthDutyRepo;

	@Autowired
	FpoItemDetOthDutyAmendRepo fpoItemDetOthDutyAmendRepo;

	@Autowired
	FpoDeclaredService fpoDeclaredService;
	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	FPO_MAINrepost fpoRepost;
	
	@Autowired
	FpoQueryAmendRepo fpoqryamdRepo;
	
	@Autowired
	FpoAddlQryRepo fpoAddlQryRepo;
	

	public List<FPO_ITEM_DET> getAssesItem(String cinNo) {
		System.out.println("cinno is " + cinNo);
		List<FPO_ITEM_DET> itemYesNoDateOriginalList = new ArrayList<FPO_ITEM_DET>();
		/*
		 * List<FPO_ITEM_DET> yesDateItems = fpoItemDetRepo.getYESDATE(cinNo);
		 * List<FPO_ITEM_DET> noDateItems = fpoItemDetRepo.getNODATE(cinNo);
		 * List<FPO_ITEM_DET> amendDateItems = fpoItemDetRepo.getAmendDATE(cinNo);
		 */

		Long maxSize = fpoItemDetRepo.maxOfItemNumber(cinNo);

		for (int i = 1; i <= maxSize.intValue(); i++) {
			itemYesNoDateOriginalList.add(getItemOnCin(cinNo, Long.valueOf(String.valueOf(i))).get(0));
		}

		/*
		 * itemYesNoDateOriginalList.addAll(amendDateItems);
		 * itemYesNoDateOriginalList.addAll(yesDateItems);
		 * itemYesNoDateOriginalList.addAll(noDateItems); HashSet<FPO_ITEM_DET> hashSet
		 * = new HashSet(itemYesNoDateOriginalList); if (null != amendDateItems &&
		 * !amendDateItems.isEmpty()) {
		 * itemYesNoDateOriginalList.addAll(amendDateItems); if (null != yesDateItems &&
		 * !yesDateItems.isEmpty()) { for (int i=0; i<amendDateItems.size();i++) { for
		 * (int j=0; j<yesDateItems.size(); j++) { if
		 * (yesDateItems.get(i).getITEM_NO().equals(yesDateItems.get(j).getITEM_NO())) {
		 * yesDateItems.remove(j); i=0; j=-1; }else { break; } } } } } if (null !=
		 * yesDateItems && !yesDateItems.isEmpty()) { if (null != amendDateItems &&
		 * !amendDateItems.isEmpty()) { for (FPO_ITEM_DET yesDateItemss : yesDateItems)
		 * { for (FPO_ITEM_DET amendDateItem : amendDateItems) { if
		 * (!amendDateItem.getITEM_NO().equals(yesDateItemss.getITEM_NO())) {
		 * itemYesNoDateOriginalList.add(yesDateItemss); }else { break; } } } } else {
		 * itemYesNoDateOriginalList.addAll(yesDateItems); } for (FPO_ITEM_DET
		 * noDateItem : noDateItems) { for (FPO_ITEM_DET yesDateItem : yesDateItems) {
		 * if (!yesDateItem.getITEM_NO().equals(noDateItem.getITEM_NO())) {
		 * itemYesNoDateOriginalList.add(noDateItem); } } } } else {
		 * itemYesNoDateOriginalList.addAll(noDateItems); }
		 */
		return itemYesNoDateOriginalList;
	}
	
	
	public void movFPOqryamend(Fpo_qry_raise fpo_qry_raise, String others) {
		java.util.Date utilDate = new java.util.Date();
		List<FpoQuery> fpoqryList = fpoQueryRepo.getAllData(fpo_qry_raise.getCinNo());
		List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_qry_raise.getCinNo());
		for (int i = 0; i < fpoqryList.size(); i++) {
		FpoQueryamend fpoQueryamd = new FpoQueryamend();
		fpoQueryamd.setCinNo(fpoqryList.get(i).getCinNo());
		fpoQueryamd.setCUS_SITE(fpoqryList.get(i).getCUS_SITE());
		fpoQueryamd.setItem_no(fpoqryList.get(i).getItem_no());
		fpoQueryamd.setQRY(fpoqryList.get(i).getQRY());
		fpoQueryamd.setQRY_NO(fpoqryList.get(i).getQRY_NO());
		fpoQueryamd.setQRY_DATE(fpoqryList.get(i).getQRY_DATE());
		fpoQueryamd.setQRY_OFF_ID(fpoqryList.get(i).getQRY_OFF_ID());
		fpoQueryamd.setQRY_ROLE(fpoqryList.get(i).getQRY_ROLE());
		fpoQueryamd.setDEFUALT_QUERY(fpoqryList.get(i).getDEFUALT_QUERY());
		fpoQueryamd.setEmail_Id(fpoqryList.get(i).getEmail_Id());
		fpoQueryamd.setMobile_No(fpoqryList.get(i).getMobile_No());
	//	fpoQueryamd.setMark(fpoqryList.get(i).getMark());
		fpoQueryamd.setMark(others);
		fpoQueryamd.setITEM_ID(fpoqryList.get(i).getITEM_ID());
		//if (fpoqryList.get(i).getItem_no()==null )
		    fpoQueryamd.setGENERAL_QUERY(DINList.get(0).getRemarks());
		fpoqryamdRepo.save(fpoQueryamd);}
	}

	public FPO_ITEM_DET getDeclaredStatus(FPO_ITEM_DET fpoItemDet) {
		Date othAmdDt = fpoItemDetOthDutyRepo.getOthrAmdDt(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		Date othAssDt = fpoItemDetOthDutyRepo.getOthrAssDt(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		Date ninAmdDt = fpoItemDetRepo.getMaxAmdDt(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		Date ninAssDt = fpoItemDetRepo.getMaxAssDt(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		Date qryDt = fpoQueryRepo.getQryDt(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		int othVal = 0;
		int ninVal = 0;
		int qryVal = 0;
		int assQry = 0;

		if (null != othAmdDt) {
			if (null != ninAmdDt) {
				othVal = othAmdDt.compareTo(ninAmdDt);
			}
			if (null != qryDt) {
				othVal = othAmdDt.compareTo(qryDt);
			}
		}

		if (null != qryDt && (null != ninAmdDt)) {
			assQry = qryDt.compareTo(ninAmdDt);
		}

		if (othVal < 1) {
			if (null != qryDt && (null == ninAssDt)) {
				if (null != ninAmdDt) {
					qryVal = qryDt.compareTo(ninAmdDt);
					ninVal = ninAmdDt.compareTo(qryDt);
				} else {
					qryVal = 1;
				}
			} else if (null != ninAmdDt) {
				ninVal = 1;
			}
		}

		if (othVal > 0) {
			fpoItemDet.setFlagRequest("O");
			return fpoItemDet;
		} else if (qryVal > 0) {
			fpoItemDet.setFlagRequest("Q");
			return fpoItemDet;
		} else if (ninVal > 0) {
			fpoItemDet.setFlagRequest("N");
			return fpoItemDet;
		} else if (assQry > 0) {
			fpoItemDet.setFlagRequest("Y");
			return fpoItemDet;
		}
		return null;
	}

	public FPO_ITEM_DET getAssessementSatus(FPO_ITEM_DET fpoItemDet) {
		String amendFlag = null;
		String assessmentFlag = null;
		String queryFlag = null;
		String page = null;
        Long assval = fpoMainRepost.gettotassval(fpoItemDet.getCinNo());
        if (assval > 0) {
		List<FPO_ITEM_DET> fpoItemDetList = getAssesItem(fpoItemDet.getCinNo());

		for (FPO_ITEM_DET fpoItem : fpoItemDetList) {
			if (null != fpoItem.getAMEND_DT()) {
				amendFlag = "amendFlag";
			} else if (null != fpoItem.getASS_DT()) {
				assessmentFlag = "assessmentFlag";
			}
		}

		List<FpoItemDetOthDuty> fpoItmOthrList = fpoItemDetOthDutyRepo.getAllOthrOnCinNor(fpoItemDet.getCinNo());

		for (FpoItemDetOthDuty fpoItmOthr : fpoItmOthrList) {
			if (null != fpoItmOthr.getAMEND_DT()) {
				amendFlag = "amendFlag";
			} else if (null != fpoItmOthr.getASS_DT()) {
				assessmentFlag = "assessmentFlag";
			}
		}
		fpoItemDet.setCouupdass_dt(fpoQueryRepo.getstatusass(fpoItemDet.getCinNo()));}
		Long maxQueryNo = fpoQueryRepo.getMaxQueryNoOnCinNo(fpoItemDet.getCinNo());
		if (null != maxQueryNo) {
			queryFlag = "queryFlag";
		}
		if (null != queryFlag) {
			page = "ead_query";
		} 
		else if (assval > 0)
		{  if (null != amendFlag) {
			page = "ead_item";
		} else if (null != assessmentFlag) {
			page = "order";
		}}
		else
			page = "order";
		System.out.println("page is"+page);
		System.out.println("No. of items pending for assessment is" +fpoQueryRepo.getstatusass(fpoItemDet.getCinNo()));
		fpoItemDet.setPage(page);
		return fpoItemDet;
	}

	public FPO_ITEM_DET pgetAssessementSatus(FPO_ITEM_DET fpoItemDet) {
		String amendFlag = null;
		String assessmentFlag = null;
		String queryFlag = null;
		String page = null;
		List<FPO_ITEM_DET> fpoItemDetList = getAssesItem(fpoItemDet.getCinNo());
		for (FPO_ITEM_DET fpoItem : fpoItemDetList) {
			if (null != fpoItem.getAMEND_DT()) {
				amendFlag = "amendFlag";
			} else if (null != fpoItem.getASS_DT()) {
				assessmentFlag = "assessmentFlag";
			}
		}

		List<FpoItemDetOthDuty> fpoItmOthrList = fpoItemDetOthDutyRepo.getAllOthrOnCinNor(fpoItemDet.getCinNo());

		for (FpoItemDetOthDuty fpoItmOthr : fpoItmOthrList) {
			if (null != fpoItmOthr.getAMEND_DT()) {
				amendFlag = "amendFlag";
			} else if (null != fpoItmOthr.getASS_DT()) {
				assessmentFlag = "assessmentFlag";
			}
		}

		Long maxQueryNo = fpoQueryRepo.getMaxQueryNoOnCinNo(fpoItemDet.getCinNo());
		if (null != maxQueryNo) {
			queryFlag = "queryFlag";
		}
		if (null != queryFlag) {
			page = "pen_ead_query";
		} else if (null != amendFlag) {
			page = "ead_item";
		} else if (null != assessmentFlag) {
			page = "order";
		}
		fpoItemDet.setPage(page);
		return fpoItemDet;
	}

	public List<FPO_ITEM_DET> getOneAssesItem(String cinNo, Long itemNumber) {
		System.out.println("cinno is " + cinNo);
		System.out.println("itemno is " + itemNumber);
		List<FPO_ITEM_DET> yesDateItems = fpoItemDetRepo.getYESDATEOne(cinNo, itemNumber);
		List<FPO_ITEM_DET> noDateItems = fpoItemDetRepo.getNODATEOne(cinNo, itemNumber);
		List<FPO_ITEM_DET> fpoAmendDet = fpoItemDetRepo.maxOfAmendDet(cinNo, itemNumber);

		if (null != fpoAmendDet && !fpoAmendDet.isEmpty())
			return fpoAmendDet;

		if (null != yesDateItems && !yesDateItems.isEmpty())
			return yesDateItems;
		else
			return noDateItems;

	}

	public List<FPO_ITEM_DET> getItemOnCin(String cinNo, Long itemNumber) {
		System.out.println("itemno is " + itemNumber);
		return fpoItemDetRepo.getItemOnCin(cinNo, itemNumber);

	}

	public List<FPO_ITEM_DET> getAssesItemOnCinAndNo(String cinNo, String itemNumber) {
		List<FPO_ITEM_DET> itemYesNoDateOriginalList = new ArrayList<FPO_ITEM_DET>();
		List<FPO_ITEM_DET> yesDateItems = fpoItemDetRepo.getYESDATEOnCinNo(cinNo, Long.parseLong(itemNumber) + 1);
		List<FPO_ITEM_DET> noDateItems = fpoItemDetRepo.getNODATEOnCinNo(cinNo, Long.parseLong(itemNumber) + 1);

		if (null != yesDateItems && !yesDateItems.isEmpty()) {
			itemYesNoDateOriginalList.addAll(yesDateItems);
			for (FPO_ITEM_DET noDateItem : noDateItems) {
				for (FPO_ITEM_DET yesDateItem : yesDateItems) {
					if (!yesDateItem.getITEM_NO().equals(noDateItem.getITEM_NO())) {
						itemYesNoDateOriginalList.add(noDateItem);
					}
				}
			}
		} else {
			itemYesNoDateOriginalList.addAll(noDateItems);
		}
		return itemYesNoDateOriginalList;
	}

	public FPO_MAIN allotoffid(FPO_MAIN fpomain, HttpSession session, HttpServletRequest request)
	{
		System.out.println(fpomain.getId());
		System.out.println(request.getSession().getAttribute("offId"));
		if ( request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAC"))
		{ fpoMainRepost.allotacloffid(fpomain.getId(), request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		
		fpoQueryDecisionRepo.updoffidqrydec(fpomain.getId(),request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("role").toString());
		System.out.println("cms here");
		}
		else
		{fpoMainRepost.allotoffid(fpomain.getId(), request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());}			
		return fpomain;
	}
	
	public FPO_ITEM_DET eadItemQryUpdate(FPO_ITEM_DET fpo_item_det, HttpSession session, HttpServletRequest request) {

		java.util.Date utilDate = new java.util.Date();
		List<FPO_ITEM_DET> fpoItemDET = fpoItemDetRepo.getItemByIdNo(fpo_item_det.getCinNo(),
				fpo_item_det.getItemNumber());

		// only Query update
		FpoQuery fpoQuery = new FpoQuery();
		Long queryNumber = fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_item_det.getCinNo());
		if (null != queryNumber && !queryNumber.toString().isEmpty())
			fpoQuery.setQRY_NO(fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_item_det.getCinNo()) + 1);
		else
			fpoQuery.setQRY_NO(1l);
		fpoQuery.setCinNo(fpo_item_det.getCinNo());
		fpoQuery.setITEM_ID(fpo_item_det.getItemId());
		fpoQuery.setQRY_DATE(fpo_item_det.getASS_DT());
		fpoQuery.setQRY(fpo_item_det.getQuery());
		fpoQuery.setITEM_NO(fpo_item_det.getItemNumber());
		fpoQuery.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoQuery.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoQuery.setQRY_DATE(utilDate);
//		fpoQuery.setPOSTINGDT(fpo_item_det.getPOSTINGDT());
		fpoQuery.setQRY_TYP("E");
		fpoQueryRepo.save(fpoQuery);
		fpoItemDetRepo.updateAssDate(fpo_item_det.getCinNo(), fpo_item_det.getItemNumber(), utilDate,
				request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(), request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		int assessmentSize = fpoItemDetRepo.getItem(fpo_item_det.getCinNo()).size();
		Long size = fpoItemDetRepo.getMaxAssess(fpo_item_det.getCinNo());
		if (assessmentSize == size)
			return new FPO_ITEM_DET();
		else
			return fpoItemDET.get(0);

	}
	
	
	public void  movedi(FpoMvmnt fpoMvmnt, HttpSession session, HttpServletRequest request)
	{
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		java.util.Date curdt = new java.util.Date();
		String Cinno = fpoMvmnt.getCinNo();
		fpoService.updfpocurque(Cinno, "P6", "EDI", utilDate,  session,request);
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		fpoQueryDecisionRepo.updqryEDI(Cinno, utilDate);
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		fpoService.insertIntofpoMvmntDb(fpoMvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "EDI", "P6",  session,request);
	}

	public FPO_ITEM_DET eadItemAssessmentUpdate(FPO_ITEM_DET fpo_item_det, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		FPO_ITEM_DET fpoItemDetList = new FPO_ITEM_DET();
		List<FPO_ITEM_DET> fpoItemDET = fpoItemDetRepo.getItemByIdNo(fpo_item_det.getCinNo(),
				fpo_item_det.getItemNumber());

		// only assessment update
		System.out.println("item number is " + fpo_item_det.getItemNumber());
		System.out.println("BCD NOTN is " + fpo_item_det.getBcdNt());
		fpoService.moveItemToAmend(fpoItemDET.get(0), 1l,  session,request);
		fpoItemDET.get(0).setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoItemDET.get(0).setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoItemDET.get(0).setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoItemDET.get(0).setBCD_AMT(fpo_item_det.getBcdAmt());
		fpoItemDET.get(0).setBCD_AMT_FG(fpo_item_det.getBcdAmtFg());
		fpoItemDET.get(0).setBCD_NOTN(fpo_item_det.getBcdNt());
		fpoItemDET.get(0).setBCD_NSNO(fpo_item_det.getBcdNtNo());
		fpoItemDET.get(0).setBCD_RTA(fpo_item_det.getBcdRate());
		
		fpoItemDET.get(0).setIGST_AMT(fpo_item_det.getIgstAmt());
		fpoItemDET.get(0).setIGST_AMT_FG(fpo_item_det.getIgstAmtFg());
		fpoItemDET.get(0).setIGST_NOTN(fpo_item_det.getIgstNt());
		fpoItemDET.get(0).setIGST_NSNO(fpo_item_det.getIgstNtNo());
		fpoItemDET.get(0).setIGST_RTA(fpo_item_det.getIgstRate());
		fpoItemDET.get(0).setSW_AMT(fpo_item_det.getSwAmt());
		fpoItemDET.get(0).setSW_AMT_FG(fpo_item_det.getSwAmtFg());
		// fpoItemDET.get(0).setSW_NOTN(fpo_item_det.getSwNt());
		// fpoItemDET.get(0).setSW_NSNO(fpo_item_det.getSwNtNo());
		fpoItemDET.get(0).setSW_RTA(fpo_item_det.getSwRate());
		fpoItemDET.get(0).setDUTY(fpo_item_det.getSingleItemDuty());
		fpoItemDET.get(0).setDUTY_FG(fpo_item_det.getDutyFg());
		fpoItemDET.get(0).setASS_DT(utilDate);
		fpoItemDET.get(0).setUPDASS_DT(utilDate);
	//	fpoItemDET.get(0).setASSESS_VAL(fpo_item_det.getASSESS_VAL());
	//	fpoItemDET.get(0).setASSVAL_INSFRT(fpo_item_det.getASSVAL_INSFRT());
		fpoItemDET.get(0).setGEN_CTH(fpo_item_det.getGEN_CTH());
		fpoItemDetRepo.save(fpoItemDET.get(0));

		List<FPO_MAIN> fpoMain = fpoMainRepost.getmain(fpo_item_det.getCinNo());
		fpoService.moveMainToAmend(fpoMain.get(0),session);
		fpoMain.get(0).setTOT_DUTY(fpo_item_det.getAllItemDuty());
		fpoMain.get(0).setTotalDutyFg(fpo_item_det.getAllItemDutyFg());
		fpoMain.get(0).setTOT_AMT_PAYABLE(fpo_item_det.getDutyPayable());
		/*float f2=((DUTY_CALC_DETAILS) request.getSession().getAttribute("dutyCalc")).getAssval_Amt();
		float f1=fpoMain.get(0).getTOT_ASS_VAL();
		if (((DUTY_CALC_DETAILS) request.getSession().getAttribute("dutyCalc")).getCategory().trim().equals(fpoMain.get(0).getNATURE_TYPE_CD().trim()))
		     fpoMain.get(0).setTOT_AMT_PAYABLE(fpo_item_det.getAllItemDuty());
		else if (Float.compare(f1, f2) > 0 )
		    {System.out.println("greater amount");
		     fpoMain.get(0).setTOT_AMT_PAYABLE(fpo_item_det.getAllItemDuty());}
	    else
	     { System.out.println("lesser amount");
	       fpoMain.get(0).setTOT_AMT_PAYABLE(0f);}*/
		fpoMainRepost.save(fpoMain.get(0));

		int assessmentSize = fpoItemDetRepo.getItem(fpo_item_det.getCinNo()).size();
		Long size = fpoItemDetRepo.getMaxAssess(fpo_item_det.getCinNo());
		
		
		String strdt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

		
		if (assessmentSize == size) {
			fpoMainRepost.updateAssdate(fpo_item_det.getCinNo(), utilDate, request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAO"))
				fpocurquerepo.updfpostatusapr(fpo_item_det.getCinNo(), strdt, request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			else if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAC"))
				fpocurquerepo.updfpostatusacl(fpo_item_det.getCinNo(), strdt, request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			return new FPO_ITEM_DET();
		} else
			return fpoItemDET.get(0);
	}

	public FPO_ASS_PAO_CMTS updatepaocmts(FPO_ASS_PAO_CMTS fpoasspaocmts, String cusite, String role, String offid) {
		int cou = fpoRepost.getcoupaoass(fpoasspaocmts.getCin_NO(),fpoasspaocmts.getStage(),cusite);
		int maxcou = fpoRepost.getcoupaoass(fpoasspaocmts.getCin_NO(),cusite);
		java.util.Date curdt = new java.util.Date();
		if ( cou > 0)
			fpoRepost.updcoupaoass(fpoasspaocmts.getCin_NO(),fpoasspaocmts.getStage(),cusite, fpoasspaocmts.getCmts(),curdt);
		else
		{
			FPO_ASS_PAO_CMTS paocmts = new FPO_ASS_PAO_CMTS();
			paocmts.setCin_NO(fpoasspaocmts.getCin_NO());
			paocmts.setCmts(fpoasspaocmts.getCmts());
			paocmts.setStage(fpoasspaocmts.getStage());
			paocmts.setCus_site(cusite);
			paocmts.setOff_ID(offid);
			paocmts.setRole(role);
			if (maxcou==0)
			  paocmts.setSeq_NO(1);
			else
			{
				int seqno = fpoRepost.getseqnopaoass(fpoasspaocmts.getCin_NO(),cusite);
				 paocmts.setSeq_NO(seqno+1);
			}
			paocmts.setItem_ID(fpoasspaocmts.getItem_ID());
			paocmts.setEntry_DT(curdt);
			fpopaocmts.save(paocmts);			
		}
		return fpoasspaocmts;		
	}
	
	public FPO_ITEM_DET updatedSpecificItemValue(FPO_ITEM_DET fpoItemDet) {
		FPO_ITEM_DET fpoItemDET = getOneAssesItem(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber()).get(0);
	//	fpoItemDET.setQuery(fpoQueryRepo.getQueryOnCinAndItemNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber()));// removed which is written by Fareed as felt it is not required
		return fpoItemDET;
	}

	public FPO_ITEM_DET nextItem(FPO_ITEM_DET fpoItemDet) {
		List<FPO_MAIN> fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo());
		Long nextItem = fpoItemDet.getItemNumber() + 1;
		if (Long.parseLong(fpoItemDetRepo.getTotalNoItems(fpoItemDet.getCinNo()).get(0).toString()) < nextItem) {
			return null;
		}
		FPO_ITEM_DET fpoItemDET = getItemOnCin(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() + 1).get(0);
		fpoItemDET.setAllItemDuty(fpoMain.get(0).getTOT_DUTY());
		fpoItemDET.setAllItemDutyFg(fpoMain.get(0).getTotalDutyFg());
		fpoItemDET.setQuery(fpoQueryRepo.getQueryOnCinAndItemNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() + 1));

		List<FpoItemDetOthDuty> fpoItemDetOthDutyList = fpoItemDetOthDutyRepo
				.getOthrNextOnCinItmNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() + 1);
		fpoItemDET.setFpoItemDetOthDuty(fpoItemDetOthDutyList);
		return fpoItemDET;
	}
	
	public FPO_ITEM_DET currItem(FPO_ITEM_DET fpoItemDet) {
		List<FPO_MAIN> fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo());
		Long nextItem = fpoItemDet.getItemNumber();
		
		FPO_ITEM_DET fpoItemDET = getItemOnCin(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() ).get(0);
		fpoItemDET.setAllItemDuty(fpoMain.get(0).getTOT_DUTY());
		fpoItemDET.setAllItemDutyFg(fpoMain.get(0).getTotalDutyFg());
		fpoItemDET.setQuery(fpoQueryRepo.getQueryOnCinAndItemNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber()));

		List<FpoItemDetOthDuty> fpoItemDetOthDutyList = fpoItemDetOthDutyRepo
				.getOthrNextOnCinItmNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() );
		fpoItemDET.setFpoItemDetOthDuty(fpoItemDetOthDutyList);
		return fpoItemDET;
	}

	public FPO_ITEM_DET previousItem(FPO_ITEM_DET fpoItemDet) {
		List<FPO_MAIN> fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo());
		Long previousItem = fpoItemDet.getItemNumber() - 1;
		if (0 == previousItem) {
			return null;
		}

		FPO_ITEM_DET fpoItemDET = getItemOnCin(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() - 1).get(0);
		fpoItemDET.setAllItemDuty(fpoMain.get(0).getTOT_DUTY());
		fpoItemDET.setAllItemDutyFg(fpoMain.get(0).getTotalDutyFg());
		fpoItemDET.setQuery(fpoQueryRepo.getQueryOnCinAndItemNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() - 1));

		List<FpoItemDetOthDuty> fpoItemDetOthDutyList = fpoItemDetOthDutyRepo
				.getOthrNextOnCinItmNo(fpoItemDet.getCinNo(), fpoItemDet.getItemNumber() - 1);
		fpoItemDET.setFpoItemDetOthDuty(fpoItemDetOthDutyList);
		System.out.println("cinno is " + fpoItemDet.getCinNo());
		Long itemno = fpoItemDet.getItemNumber() - 1;
		String cinno = fpoItemDet.getCinNo();
		System.out.println("Itemno is " + itemno);
		Long cou = fpoItemDetOthDutyRepo.othdutycou(cinno, itemno);
		System.out.println("count is  " + cou);
		return fpoItemDET;
	}

	public List<Object> getOtherFourDigi(String twoDigiCode) {
		return fpoItemDetRepo.getOtherFourDigi(twoDigiCode);
	}

	public List<Object> getOtherEightDigi(String fourDigiCode) {
		return fpoItemDetRepo.getOtherEightDigi(fourDigiCode);
	}

	public List<String> getCountryName(String curCode) {
		return fpoItemDetRepo.getCountryName(curCode);
	}

	public Float totalDuty = 0.0F;
	private static DecimalFormat df2 = new DecimalFormat("#.##");

//	public FPO_ITEM_DET updateExchangeRate(FPO_ITEM_DET fpoItemDet, HttpSession session, HttpServletRequest request) {
//		java.util.Date utilDate = new java.util.Date();
//		List<FPO_ITEM_DET> fpoItemDets = getAssesItem(fpoItemDet.getCinNo());
//		List<FPO_ITEM_DET> fpoItemDetArray = new ArrayList<FPO_ITEM_DET>();
//		List<FPO_ITEM_DET> fpoItemDetArrayCal = new ArrayList<FPO_ITEM_DET>();
//		FPO_MAIN fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo()).get(0);
//		for (FPO_ITEM_DET fpoItem : fpoItemDets) {
//
//			List<Object> bcdRate = new ArrayList<Object>();
//			List<Object> igstRate = new ArrayList<Object>();
//			List<Object> swRate = new ArrayList<Object>();
//			bcdRate.add(fpoItem.getBCD_RTA());
//			igstRate.add(fpoItem.getIGST_RTA());
//			swRate.add(fpoItem.getSW_RTA());
//			fpoItem.setASSESS_VAL(fpoItemDet.getRate() * fpoItemDet.getDECL_VAL());
//			fpoItem.setRate(fpoItemDet.getRate());
//			fpoItemDetArrayCal.add(fpoItem);
//			bcdRate = fpoService.bcdCalculation(bcdRate, null, fpoItemDetArrayCal);
//			igstRate = fpoService.igstCalculation(igstRate, null, fpoItemDetArrayCal);
//			swRate = fpoService.swCalculation(swRate, null, fpoItemDetArrayCal);
//			Float dutyval = Float.valueOf(bcdRate.get(1).toString()) + Float.valueOf(igstRate.get(1).toString())
//					+ Float.valueOf(swRate.get(1).toString());
//			fpoItem.setDUTY(Float.valueOf(df2.format(dutyval).toString()));
//			totalDuty = totalDuty + fpoItem.getDUTY();
//			fpoItem.setBCD_AMT(Float.parseFloat(bcdRate.get(1).toString()));
//			fpoItem.setBCD_AMT_FG(0F);
//			fpoItem.setIGST_AMT(Float.parseFloat(igstRate.get(1).toString()));
//			fpoItem.setIGST_AMT_FG(0F);
//			fpoItem.setSW_AMT(Float.parseFloat(swRate.get(1).toString()));
//			fpoItem.setSW_AMT_FG(0F);
//			fpoItem.setAMEND_DT(utilDate);
//			fpoItem.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
//			fpoItem.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
//			fpoItem.setAllItemDuty(Float.valueOf(df2.format(totalDuty)));
//			fpoItem.setAllItemDutyFg(0.0F);
//			fpoItemDetArray.add(fpoItem);
//			fpoMain.setTOT_DUTY(Float.valueOf(bcdRate.get(5).toString()) + Float.valueOf(igstRate.get(5).toString())
//					+ Float.valueOf(swRate.get(5).toString()));
//			fpoMain.setTOT_ASS_VAL(fpoItemDet.getRate() * fpoItemDet.getDECL_VAL());
//			fpoMain.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
//			fpoMain.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
//			fpoMain.setTotalDutyFg(0F);
//		}
//
//		fpoMainRepost.save(fpoMain);
//
//		fpoItemDetRepo.saveAll(fpoItemDetArray);
//
//		return fpoItemDetArray.get(0);
//	}
	
	@Autowired
	fposubmititemRepoImpl FposubmititemRepoImpl;
	
	@Autowired
	DutyCalcDetailsRepo dutyCalcDetailsRepo;
	
	/*public FPO_ITEM_DET updateExchangeRate(FPO_ITEM_DET fpoItemDet, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		List<FPO_ITEM_DET> fpoItemDets = getAssesItem(fpoItemDet.getCinNo());
		List<FPO_ITEM_DET> fpoItemDetArray = new ArrayList<FPO_ITEM_DET>();
		List<FPO_ITEM_DET> fpoItemDetArrayCal = new ArrayList<FPO_ITEM_DET>();
		List<DUTY_CALC_DETAILS> dutyCalc = dutyCalcDetailsRepo.findAll();
		FPO_MAIN fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo()).get(0);
		float TotAssVal = 0.0f;
	
		for (FPO_ITEM_DET fpoItem : fpoItemDets) {

			List<Object> bcdRate = new ArrayList<Object>();
			List<Object> igstRate = new ArrayList<Object>();
			List<Object> swRate = new ArrayList<Object>();
			bcdRate.add(fpoItem.getBCD_RTA());
			igstRate.add(fpoItem.getIGST_RTA());
			swRate.add(fpoItem.getSW_RTA());
			fpoItem.setASSESS_VAL(fpoItemDet.getRate() * fpoItemDet.getDECL_VAL());
			fpoItem.setRate(fpoItemDet.getRate());
			fpoItemDetArrayCal.add(fpoItem);			
			fpoItem.setAMEND_DT(utilDate);
			fpoItem.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoItem.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoItem.setAllItemDuty(Float.valueOf(df2.format(totalDuty)));
			fpoItem.setAllItemDutyFg(0.0F);
			fpoItemDetArray.add(fpoItem);
			TotAssVal = TotAssVal + fpoItem.getASSESS_VAL();
		//	TotDuty = TotDuty + fpoItem.getDUTY();
		}
		
	
		float inscalcval, frtcalcval;
		float inscurrt, frtcurrrt;
		float insval, frtval;
		
		
		if (fpoMain.getINS_VAL_CURRCD() == null || fpoMain.getINS_VAL() == null) {
			inscalcval = 0;
		} else if (FposubmititemRepoImpl.getcurrt(fpoMain.getINS_VAL_CURRCD()) != null) {
			inscurrt = Float
					.parseFloat(FposubmititemRepoImpl.getcurrt(fpoMain.getINS_VAL_CURRCD()));
		//	fpoMain.setINS_currrt(inscurrt);
			inscalcval = fpoMain.getINS_VAL().floatValue() * inscurrt;
		} else {
			inscurrt = 0;
			inscalcval = 0;
		}
		System.out.println("INScalcval :" + inscalcval);
		if (fpoMain.getPOSTAGE_CURR_CD() == null || fpoMain.getPOSTAGE_AMT() == null)
			frtcalcval = 0;
		else if (FposubmititemRepoImpl.getcurrt(fpoMain.getPOSTAGE_CURR_CD()) != null) {
			frtcurrrt = Float
					.parseFloat(FposubmititemRepoImpl.getcurrt(fpoMain.getPOSTAGE_CURR_CD()));
		//	fpoMain.setFRT_currrt(frtcurrrt);
			frtcalcval = fpoMain.getPOSTAGE_AMT().floatValue() * frtcurrrt;
		} else {
			frtcurrrt = 0;
			frtcalcval = 0;
		}
		System.out.println("FRTcalcval :" + frtcalcval);
		if (inscalcval == 0 || inscalcval > (float) (TotAssVal * 0.01125)) {
			insval = (float) (TotAssVal * 0.01125);
			System.out.println("INSval :" + insval);
			inscalcval = insval;
			// fposubmit.setINS_calc_val(parseFloat(totcalcassval * 0.01125));
			// fposubmit.setINS_calc_val(insval);
		    //	fpoMain.setINS_calc_val(ic1);
			System.out.println("INSval :" + insval);
		} else {
		//	fpoMain.setINS_calc_val(inscalcval);
			System.out.println("INScalcval :" + inscalcval);
		}

		if (frtcalcval == 0 || frtcalcval > (float) (TotAssVal * 0.20)) {
			frtval = (float) (TotAssVal * 0.20);
			System.out.println("FRTval :" + frtval);
			frtcalcval = frtval;
		    //	fpoMain.setFRT_calc_val(frt2);
			//  fposubmit.setFRT_calc_val(parseFloat(totcalcassval * 0.0020));}
		} else {
		//	fpoMain.setFRT_calc_val(frtcalcval);
		}
		float oldTotAssVal;
		
		oldTotAssVal = TotAssVal;
		
		TotAssVal = TotAssVal + inscalcval + frtcalcval;
		float allTotDuty = 0.0f;
		float allTotDutyfg = 0.0f;
		for (FPO_ITEM_DET fpoItem : fpoItemDets) {
			
			float TotDuty = 0.0f;
			float TotDutyFg = 0.0f;
		//	val = fpoItemDet.getRate() * fpoItemDet.getDECL_VAL();
			float val = (fpoItem.getASSESS_VAL() * oldTotAssVal) / TotAssVal;
			float bcdDuty = 0.0f;
			float igstDuty = 0.0f;
			float swDuty = 0.0f;
			float bcdDutyfg = 0.0f;
			float igstDutyfg = 0.0f;
			float swDutyfg = 0.0f;
			float bcdDuty1 = 0.0f;
			float igstDuty1 = 0.0f;
			float swDuty1 = 0.0f;
			bcdDuty = val * fpoItem.getBCD_RTA();
			swDuty = bcdDuty * fpoItem.getSW_RTA();
			igstDuty = (bcdDuty + swDuty + val) * fpoItem.getIGST_RTA();
			TotDuty = bcdDuty + swDuty + igstDuty;
			bcdDuty1 = val * dutyCalc.get(0).getBcdRta_eff();
			swDuty1 = bcdDuty1 * fpoItem.getSW_RTA();
			igstDuty1 = (bcdDuty1 +swDuty1 + val) * fpoItem.getIGST_RTA();
		
//			Float dutyval = Float.valueOf(bcdRate.get(1).toString()) + Float.valueOf(igstRate.get(1).toString())
//					+ Float.valueOf(swRate.get(1).toString());
			fpoItem.setDUTY(bcdDuty + igstDuty + swDuty);
//	        totalDuty = totalDuty + fpoItem.getDUTY();
			if(fpoMain.getNATURE_TYPE_CD().equals(dutyCalc.get(0).getCategory())) {
				TotDutyFg = TotDutyFg + 0.0f;
			} else {
				bcdDutyfg = bcdDuty1 - bcdDuty;
				igstDutyfg = igstDuty1 - igstDuty;
				swDutyfg = swDuty1 - swDuty;
				TotDutyFg = TotDutyFg + bcdDutyfg + igstDutyfg + swDutyfg;
			}
			fpoItem.setBCD_AMT(bcdDuty);
			fpoItem.setBCD_AMT_FG(bcdDutyfg);
			fpoItem.setIGST_AMT(igstDuty);
			fpoItem.setIGST_AMT_FG(igstDutyfg);
			fpoItem.setSW_AMT(swDuty);
			fpoItem.setSW_AMT_FG(swDutyfg);
			fpoItem.setASSVAL_INSFRT(val);
			fpoItem.setDUTY(TotDuty);
			
			allTotDuty = allTotDuty + TotDuty;
			allTotDutyfg = allTotDutyfg + TotDutyFg;
			
			
		}
		
		fpoMain.setTOT_DUTY(allTotDuty);
		fpoMain.setTOT_ASS_VAL(TotAssVal);
		fpoMain.setTotalDutyFg(allTotDutyfg);
		fpoMain.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoMain.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoMain.setTotalDutyFg(0F);

		fpoMainRepost.save(fpoMain);

		fpoItemDetRepo.saveAll(fpoItemDetArray);

		return fpoItemDetArray.get(0);
	}*/
	
	
	
	public FPO_ITEM_DET updateExchangeRate(FPO_ITEM_DET fpoItemDet, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		List<FPO_ITEM_DET> fpoItemDets = getAssesItem(fpoItemDet.getCinNo());
		List<FPO_ITEM_DET> fpoItemDetArray = new ArrayList<FPO_ITEM_DET>();
		
		System.out.println("cinno is " +fpoItemDet.getCinNo());
	//	List<FPO_ITEM_DET> fpoItemDets = new ArrayList<FPO_ITEM_DET>();
		List<DUTY_CALC_DETAILS> dutyCalc = dutyCalcDetailsRepo.findAll();
		FPO_MAIN fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo()).get(0);
		
		Long maxSize = fpoItemDetRepo.maxOfItemNumber(fpoItemDet.getCinNo());
		float allTotDuty = 0.0f;
		float allTotDutyfg = 0.0f;
		float TotAssVal = 0.0f;
//		for (int i = 1; i <= maxSize.intValue(); i++) {
//			fpoItemDets.add(getItemOnCin(fpoItemDet.getCinNo(), Long.valueOf(String.valueOf(i))).get(0));
		for(FPO_ITEM_DET fpoItemDetn: fpoItemDets) {
			//	List<FPO_ITEM_DET> fpoItemDetArrayCal = new ArrayList<FPO_ITEM_DET>();
			List<Object> bcdRate = new ArrayList<Object>();
			List<Object> igstRate = new ArrayList<Object>();
			List<Object> swRate = new ArrayList<Object>();
			bcdRate.add(fpoItemDetn.getBCD_RTA());
			igstRate.add(fpoItemDetn.getIGST_RTA());
			swRate.add(fpoItemDetn.getSW_RTA());
			
			fpoItemDetn.setRate(fpoItemDet.getRate());
		//	fpoItemDetArrayCal.add(fpoItem);			
			fpoItemDetn.setAMEND_DT(utilDate);
			fpoItemDetn.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoItemDetn.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoItemDetn.setAllItemDuty(Float.valueOf(df2.format(totalDuty)));
			fpoItemDetn.setAllItemDutyFg(0.0F);
			fpoItemDetArray.add(fpoItemDetn);
		}
		for (int i = 1; i <= maxSize.intValue(); i++) {
		fpoItemDets.add(getItemOnCin(fpoItemDet.getCinNo(), Long.valueOf(String.valueOf(i))).get(0));
		  fpoItemDets.get(i).setASSESS_VAL(fpoItemDets.get(i).getRate() * fpoItemDets.get(i).getDECL_VAL());
			TotAssVal = TotAssVal + fpoItemDets.get(i).getASSESS_VAL();
		//	TotDuty = TotDuty + fpoItem.getDUTY();
		}
		
	
		float inscalcval, frtcalcval;
		float inscurrt, frtcurrrt;
		float insval, frtval;
		
		
		if (fpoMain.getINS_VAL_CURRCD() == null || fpoMain.getINS_VAL() == null) {
			inscalcval = 0;
		} else if (FposubmititemRepoImpl.getcurrt(fpoMain.getINS_VAL_CURRCD()) != null) {
			inscurrt = Float
					.parseFloat(FposubmititemRepoImpl.getcurrt(fpoMain.getINS_VAL_CURRCD()));
		//	fpoMain.setINS_currrt(inscurrt);
			inscalcval = fpoMain.getINS_VAL().floatValue() * inscurrt;
		} else {
			inscurrt = 0;
			inscalcval = 0;
		}
		System.out.println("INScalcval :" + inscalcval);
		if (fpoMain.getPOSTAGE_CURR_CD() == null || fpoMain.getPOSTAGE_AMT() == null)
			frtcalcval = 0;
		else if (FposubmititemRepoImpl.getcurrt(fpoMain.getPOSTAGE_CURR_CD()) != null) {
			frtcurrrt = Float
					.parseFloat(FposubmititemRepoImpl.getcurrt(fpoMain.getPOSTAGE_CURR_CD()));
		//	fpoMain.setFRT_currrt(frtcurrrt);
			frtcalcval = fpoMain.getPOSTAGE_AMT().floatValue() * frtcurrrt;
		} else {
			frtcurrrt = 0;
			frtcalcval = 0;
		}
		System.out.println("FRTcalcval :" + frtcalcval);
		if (inscalcval == 0 || inscalcval > (float) (TotAssVal * 0.01125)) {
			insval = (float) (TotAssVal * 0.01125);
			System.out.println("INSval :" + insval);
			inscalcval = insval;
			// fposubmit.setINS_calc_val(parseFloat(totcalcassval * 0.01125));
			// fposubmit.setINS_calc_val(insval);
		    fpoMain.setINS_CALC_VAL(insval);
			System.out.println("INSval :" + insval);
		} else {
		//	fpoMain.setINS_calc_val(inscalcval);
			System.out.println("INScalcval :" + inscalcval);
		}

		if (frtcalcval == 0 || frtcalcval > (float) (TotAssVal * 0.20)) {
			frtval = (float) (TotAssVal * 0.20);
			System.out.println("FRTval :" + frtval);
			frtcalcval = frtval;
		    fpoMain.setFRT_CALC_VAL(frtval);
			//  fposubmit.setFRT_calc_val(parseFloat(totcalcassval * 0.0020));}
		} else {
		//	fpoMain.setFRT_calc_val(frtcalcval);
		}
		float oldTotAssVal;
		
		oldTotAssVal = TotAssVal;
		
		TotAssVal = TotAssVal + inscalcval + frtcalcval;
		
		for (int i = 1; i <= maxSize.intValue(); i++) {
			fpoItemDets.add(getItemOnCin(fpoItemDet.getCinNo(), Long.valueOf(String.valueOf(i))).get(0));
			float TotDuty = 0.0f;
			float TotDutyFg = 0.0f;
		//	val = fpoItemDet.getRate() * fpoItemDet.getDECL_VAL();
			float val = (fpoItemDets.get(i).getASSESS_VAL() * TotAssVal) /  oldTotAssVal;
			float bcdDuty = 0.0f;
			float igstDuty = 0.0f;
			float swDuty = 0.0f;
			float bcdDutyfg = 0.0f;
			float igstDutyfg = 0.0f;
			float swDutyfg = 0.0f;
			float bcdDuty1 = 0.0f;
			float igstDuty1 = 0.0f;
			float swDuty1 = 0.0f;
			bcdDuty = (val * fpoItemDets.get(i).getBCD_RTA())/100;
			swDuty = (bcdDuty * fpoItemDets.get(i).getSW_RTA())/100;
			igstDuty = ((bcdDuty + swDuty + val) * fpoItemDets.get(i).getIGST_RTA()) / 100;
			TotDuty = bcdDuty + swDuty + igstDuty;
			bcdDuty1 = (val * dutyCalc.get(0).getBcdRta_eff())/100;
			swDuty1 = (bcdDuty1 * fpoItemDets.get(i).getSW_RTA())/100;
			igstDuty1 = ((bcdDuty1 +swDuty1 + val) * fpoItemDets.get(i).getIGST_RTA())/100;
		
//			Float dutyval = Float.valueOf(bcdRate.get(1).toString()) + Float.valueOf(igstRate.get(1).toString())
//					+ Float.valueOf(swRate.get(1).toString());
			fpoItemDets.get(i).setDUTY(bcdDuty + igstDuty + swDuty);
//	        totalDuty = totalDuty + fpoItem.getDUTY();
			if(fpoMain.getNATURE_TYPE_CD().equals(dutyCalc.get(0).getCategory())) {
				TotDutyFg = 0.0f;
			} else {
				bcdDutyfg = bcdDuty1 - bcdDuty;
				igstDutyfg = igstDuty1 - igstDuty;
				swDutyfg = swDuty1 - swDuty;
				TotDutyFg = bcdDutyfg + igstDutyfg + swDutyfg;
			}
			fpoItemDets.get(i).setBCD_AMT(bcdDuty);
			fpoItemDets.get(i).setBCD_AMT_FG(bcdDutyfg);
			fpoItemDets.get(i).setIGST_AMT(igstDuty);
			fpoItemDets.get(i).setIGST_AMT_FG(igstDutyfg);
			fpoItemDets.get(i).setSW_AMT(swDuty);
			fpoItemDets.get(i).setSW_AMT_FG(swDutyfg);
			fpoItemDets.get(i).setASSVAL_INSFRT(val);
			fpoItemDets.get(i).setDUTY(TotDuty);
			fpoItemDets.get(i).setDUTY_FG(TotDutyFg);
			
			allTotDuty = allTotDuty + TotDuty;
			allTotDutyfg = allTotDutyfg + TotDutyFg;
			
			
		}
		
		
		fpoMain.setTOT_DUTY(allTotDuty);
		fpoMain.setTOT_ASS_VAL(TotAssVal);
		fpoMain.setTotalDutyFg(allTotDutyfg);
		fpoMain.setTOTASS_CALC_VAL(oldTotAssVal);
		fpoMain.setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoMain.setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());

		fpoMainRepost.save(fpoMain);

		fpoItemDetRepo.saveAll(fpoItemDetArray);

		return fpoItemDetArray.get(0);
	}
	

	public FPO_ITEM_DET getDutyOthersDet(FPO_ITEM_DET fpoItemDet) {

		if (null != fpoItemDet && null != fpoItemDet.getDutyCdOthers()) {
			switch (fpoItemDet.getDutyCdOthers().intValue()) {
			case 1:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 3:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 5:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 6:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 7:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 8:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 9:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 17:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 18:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 19:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 21:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 22:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 23:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersBcdRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersBcdNotification(fpoItemDet.getCTH()));
				break;
			case 24:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSDARate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSDANotification(fpoItemDet.getCTH()));
				break;
			case 25:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersADDRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersADDNotification(fpoItemDet.getCTH()));
				break;
			case 26:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSGDRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSGDNotification(fpoItemDet.getCTH()));
				break;
			case 27:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersCTDRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersCTDNotification(fpoItemDet.getCTH()));
				break;
			case 28:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersSaptaRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersSaptaNotification(fpoItemDet.getCTH()));
				break;
			case 32:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersIgstRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersIgstNotification(fpoItemDet.getCTH()));
				break;
			case 34:
				fpoItemDet.setDutyRateOthers(fpoItemDetRepo.getOthersCCNRate(fpoItemDet.getCTH()));
				fpoItemDet.setListObjects(fpoItemDetRepo.getOthersCCNNotification(fpoItemDet.getCTH()));
				break;
			default:
				break;
			}
		}
		return fpoItemDet;
	}

	public FPO_ITEM_DET getDutyOthersSlNo(FPO_ITEM_DET fpoItemDet) {

		if (null != fpoItemDet && null != fpoItemDet.getDutyCdOthers()) {
			switch (fpoItemDet.getDutyCdOthers().intValue()) {
			case 1:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 3:
				fpoItemDet.setListObjects(
						fpoItemDetRepo.getOthersSaptaSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 5:
				fpoItemDet.setListObjects(
						fpoItemDetRepo.getOthersIgstSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 6:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersCCNSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 7:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersSDASlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 8:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersSGDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 9:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersADDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 17:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersCTDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 18:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersCVDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 19:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 21:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 22:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 23:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 24:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersSDASlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 25:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersADDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 26:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersSGDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 27:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersCTDSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 28:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersBcdSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 32:
				fpoItemDet.setListObjects(
						fpoItemDetRepo.getOthersIgstSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			case 34:
				fpoItemDet
						.setListObjects(fpoItemDetRepo.getOthersCCNSlNo(fpoItemDet.getBCD_NOTN(), fpoItemDet.getCTH()));
				break;
			default:
				break;
			}
		}
		return fpoItemDet;
	}

	public FPO_ITEM_DET getEachRate(FPO_ITEM_DET fpoItemDet) {
		List<Object> rateObject = new ArrayList<Object>();
		if (null != fpoItemDet && null != fpoItemDet.getDutyCdOthers()) {
			switch (fpoItemDet.getDutyCdOthers().intValue()) {
			case 1:
				rateObject = fpoItemDetRepo.getBcdRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH());
				if (null != rateObject && !rateObject.isEmpty())
					fpoItemDet.setBCD_RTA(Float.valueOf(rateObject.get(0).toString()));
				break;
			case 3:
				fpoItemDet.setBCD_RTA(
						fpoItemDetRepo.getOthersSaptaSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 5:
				fpoItemDet.setBCD_RTA(
						fpoItemDetRepo.getOthersSaptaSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 6:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCCNSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 7:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersSDASlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 8:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersSGDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 9:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersADDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 17:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCTDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 18:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 19:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 21:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 22:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 23:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 24:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 25:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 26:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 27:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 28:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCVDSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			case 32:
				rateObject = fpoItemDetRepo.getIgstRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH());
				if (null != rateObject && !rateObject.isEmpty())
					fpoItemDet.setBCD_RTA(Float.valueOf(rateObject.get(0).toString()));
				break;
			case 34:
				fpoItemDet
						.setBCD_RTA(fpoItemDetRepo.getOthersCCNSlnoRate(fpoItemDet.getBCD_NSNO(), fpoItemDet.getCTH()));
				break;
			default:
				break;
			}
		}
		return fpoItemDet;
	}

//	public List<FpoItemDetOthDuty> getFpoItemOthersList(List<FpoItemDetOthDuty> fpoItemDetOthDutyList) {
	public String getFpoItemOthersList(List<FpoItemDetOthDuty> fpoItemDetOthDutyList, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		List<FPO_MAIN> fpoMainList = fpoMainRepost.getmain(fpoItemDetOthDutyList.get(0).getCIN_NO());
		fpoService.moveMainToAmend(fpoMainList.get(0),session);
		List<FPO_ITEM_DET> fpoItemDetList = fpoItemDetRepo
				.getItemByCinNoAndItemNo(fpoItemDetOthDutyList.get(0).getCIN_NO(), 1l);
		Long amendSerialNor = fpoAmendRepost.getMaxFpoItemAmendOnCinItemNo(fpoItemDetOthDutyList.get(0).getCIN_NO(),
				fpoItemDetOthDutyList.get(0).getITEM_NO());
		if (null != amendSerialNor) {
			amendSerialNor = amendSerialNor + 1;
		} else {
			amendSerialNor = 1L;
		}
		//fpoDeclaredService.saveOthersAmendList(fpoItemDetOthDutyList, session, request);
		fpoService.moveItemToAmend(fpoItemDetList.get(0), amendSerialNor,  session,request);
		fpoMainList.get(0).setTOT_DUTY(fpoItemDetOthDutyList.get(0).getAllItemAmount());
		fpoMainList.get(0).setTotalDutyFg(fpoItemDetOthDutyList.get(0).getAllItemAmountFg());
		fpoMainRepost.save(fpoMainList.get(0));
		// fpoItemDetList.get(0).setDUTY(fpoItemDetOthDutyList.get(0).getTotAllAmount());

		/*
		 * fpoItemDetRepo.markEntryAsRead(fpoItemDetList.get(0).getId(),
		 * fpoItemDetOthDutyList.get(0).getTotAllAmount(),
		 * fpoItemDetOthDutyList.get(0).getTotAllAmountFg());
		 */

		// fpoItemDetRepo.save(fpoItemDetList.get(0));
		// fpoMainRepost.updateAssdate(fpoItemDetOthDutyList.get(0).getCIN_NO(),
		// utilDate);
		// fpoItemDetRepo.updAssItm(fpoItemDetOthDutyList.get(0).getCIN_NO(), strdt,
		// fpoItemDetList.get(0).getITEM_NO(), request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(),
		// request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		// fpoItemDetRepo.updAssItm(fpoItemDetOthDutyList.get(0).getCIN_NO(), utilDate,
		// fpoItemDetOthDutyList.get(0).getITEM_NO(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(),
		// request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		List<FpoItemDetOthDuty> fpoItemDbOthDutyList = fpoItemDetOthDutyRepo.getAllOthrOnCinNor(
				fpoItemDetOthDutyList.get(0).getCIN_NO(), fpoItemDetOthDutyList.get(0).getITEM_NO());
		List<FpoItemDetOthDutyAmend> fpoItemDetOthDutyAmendList = new ArrayList<FpoItemDetOthDutyAmend>();
		amendSerialNor = fpoItemDetOthDutyAmendRepo.getMaxSrlNo(fpoItemDetOthDutyList.get(0).getCIN_NO(),
				fpoItemDetOthDutyList.get(0).getITEM_NO());

		if (null != amendSerialNor) {
			amendSerialNor = amendSerialNor + 1;
		} else {
			amendSerialNor = 1L;
		}
		for (FpoItemDetOthDuty fpoItemDutyOth : fpoItemDbOthDutyList) {
			FpoItemDetOthDutyAmend fpoItemDetOthDutyAmend = new FpoItemDetOthDutyAmend();
			fpoItemDetOthDutyAmend.setAMEND_DT(utilDate);
			fpoItemDetOthDutyAmend.setAMEND_FLAG("U");
			fpoItemDetOthDutyAmend.setAMEND_SERAIL_NO(amendSerialNor);
			fpoItemDetOthDutyAmend.setASS_DT(fpoItemDutyOth.getASS_DT());
			fpoItemDetOthDutyAmend.setCIN_DT(fpoItemDutyOth.getCIN_DT());
			fpoItemDetOthDutyAmend.setCIN_NO(fpoItemDutyOth.getCIN_NO());
			fpoItemDetOthDutyAmend.setCTH(fpoItemDutyOth.getCTH());
			fpoItemDetOthDutyAmend.setCUS_SITE(fpoItemDutyOth.getCUS_SITE());
			fpoItemDetOthDutyAmend.setDUTY_AMT(fpoItemDutyOth.getDUTY_AMT());
			fpoItemDetOthDutyAmend.setDUTY_CD(fpoItemDutyOth.getDUTY_CD());
			fpoItemDetOthDutyAmend.setDUTY_DESC(fpoItemDutyOth.getDUTY_DESC());
			fpoItemDetOthDutyAmend.setDUTY_FG(fpoItemDutyOth.getDUTY_FG());
			fpoItemDetOthDutyAmend.setDUTY_NOTN(fpoItemDutyOth.getDUTY_NOTN());
			fpoItemDetOthDutyAmend.setDUTY_RTA(fpoItemDutyOth.getDUTY_RTA());
			fpoItemDetOthDutyAmend.setDUTY_SLNO(fpoItemDutyOth.getDUTY_SLNO());
			fpoItemDetOthDutyAmend.setITEM_ID(fpoItemDutyOth.getITEM_ID());
			fpoItemDetOthDutyAmend.setITEM_NO(fpoItemDutyOth.getITEM_NO());
			fpoItemDetOthDutyAmend.setOFF_ID(fpoItemDutyOth.getOFF_ID());
			fpoItemDetOthDutyAmend.setPOSTINGDT(fpoItemDutyOth.getPOSTINGDT());
			fpoItemDetOthDutyAmend.setROLE(fpoItemDutyOth.getROLE());
			fpoItemDetOthDutyAmendList.add(fpoItemDetOthDutyAmend);
		}
		
		
		fpoItemDetRepo.updAssItm(fpoItemDetOthDutyList.get(0).getCIN_NO(), utilDate,
				fpoItemDetOthDutyList.get(0).getITEM_NO(), fpoItemDetOthDutyList.get(0).getTotAllAmount(),
				request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(), request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
         
		fpoItemDetOthDutyAmendRepo.saveAll(fpoItemDetOthDutyAmendList);
		
		for (int i = 0; i < fpoItemDetOthDutyList.size(); i++) {
			
			Long dutycd = fpoItemDetOthDutyList.get(0).getDUTY_CD();
			String cinno = fpoItemDetOthDutyList.get(0).getCIN_NO();
			Long itemno =  fpoItemDetOthDutyList.get(0).getITEM_NO();
			String cussite = request.getSession().getAttribute("cuSite").toString();
			
			int cou = fpoItemDetRepo.getcouitemdetothduty(cinno,itemno,cussite);
			if (cou > 0)
			try {
			   fpoItemDetRepo.delitemdetothduty(cinno,itemno,cussite);}
			catch (Exception e) {
			  
			}


			if (fpoItemDetOthDutyList.get(i).getDUTY_NOTN() != null)
				if ((fpoItemDetOthDutyList.get(i).getDUTY_NOTN().contains("Select"))
						|| (fpoItemDetOthDutyList.get(i).getDUTY_NOTN().contains("Enter"))) {
					fpoItemDetOthDutyList.get(i).setDUTY_NOTN(null);
				}

			if (fpoItemDetOthDutyList.get(i).getDUTY_SLNO() != null)
				if ((fpoItemDetOthDutyList.get(i).getDUTY_SLNO().contains("Select"))
						|| (fpoItemDetOthDutyList.get(i).getDUTY_SLNO().contains("Enter"))) {
					fpoItemDetOthDutyList.get(i).setDUTY_SLNO(null);
				}

			if (fpoItemDetOthDutyList.get(i).getDUTY_DESC() != null)
				if ((fpoItemDetOthDutyList.get(i).getDUTY_DESC().contains("Select"))
						|| (fpoItemDetOthDutyList.get(i).getDUTY_DESC().contains("Enter"))) {
					fpoItemDetOthDutyList.get(i).setDUTY_DESC(null);
				}
			fpoItemDetOthDutyList.get(i).setCIN_DT(fpoMainList.get(0).getCIN_DT());
			fpoItemDetOthDutyList.get(i).setCUS_SITE(fpoMainList.get(0).getCUS_SITE());
			fpoItemDetOthDutyList.get(i).setPOSTINGDT(fpoMainList.get(0).getPOSTINGDT());
		
		}
		fpoItemDetOthDutyRepo.saveAll(fpoItemDetOthDutyList);
		int assessmentSize = fpoItemDetRepo.getItem(fpoItemDetOthDutyList.get(0).getCIN_NO()).size();
		Long size = fpoItemDetRepo.getMaxAssess(fpoItemDetOthDutyList.get(0).getCIN_NO());
		String strdt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		if (assessmentSize == size) {
			fpoMainRepost.updateAssdate(fpoItemDetOthDutyList.get(0).getCIN_NO(), utilDate, request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("APR"))
				fpocurquerepo.updfpostatusapr(fpoItemDetOthDutyList.get(0).getCIN_NO(), strdt, request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			else if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("PAO")) // Kanishkar in this function added this else if  on 21/04/2023
				fpocurquerepo.updfpostatusapr(fpoItemDetOthDutyList.get(0).getCIN_NO(), strdt, request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString()); 
			else if (request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equals("ACL"))
				fpocurquerepo.updfpostatusacl(fpoItemDetOthDutyList.get(0).getCIN_NO(), strdt, request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			return null;
		} else
			return "Pending";
	}

	public String saveOthersAmendList(List<FpoItemDetOthDuty> fpoItemDetOthDutyList, HttpSession session, HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();
		List<FpoItemDetOthDuty> fpoItemDbOthDutyList = fpoItemDetOthDutyRepo.getAllOthrOnCinNor(
				fpoItemDetOthDutyList.get(0).getCIN_NO(), fpoItemDetOthDutyList.get(0).getITEM_NO());
		List<FpoItemDetOthDutyAmend> fpoItemDetOthDutyAmendList = new ArrayList<FpoItemDetOthDutyAmend>();
		Long amendSerialNor = fpoItemDetOthDutyAmendRepo.getMaxSrlNo(fpoItemDetOthDutyList.get(0).getCIN_NO(),
				fpoItemDetOthDutyList.get(0).getITEM_NO());
		Boolean delFlagOthr = false;
		Boolean delFlagNinEgt = false;

		if (null != amendSerialNor) {
			amendSerialNor = amendSerialNor + 1;
		} else {
			amendSerialNor = 1L;
		}

		for (FpoItemDetOthDuty fpoItemDutyOth : fpoItemDbOthDutyList) {
			FpoItemDetOthDutyAmend fpoItemDetOthDutyAmend = new FpoItemDetOthDutyAmend();
			fpoItemDetOthDutyAmend.setAMEND_DT(utilDate);
			fpoItemDetOthDutyAmend.setAMEND_FLAG("U");
			fpoItemDetOthDutyAmend.setAMEND_SERAIL_NO(amendSerialNor);
			fpoItemDetOthDutyAmend.setASS_DT(fpoItemDutyOth.getASS_DT());
			fpoItemDetOthDutyAmend.setCIN_DT(fpoItemDutyOth.getCIN_DT());
			fpoItemDetOthDutyAmend.setCIN_NO(fpoItemDutyOth.getCIN_NO());
			fpoItemDetOthDutyAmend.setCTH(fpoItemDutyOth.getCTH());
			fpoItemDetOthDutyAmend.setCUS_SITE(fpoItemDutyOth.getCUS_SITE());
			fpoItemDetOthDutyAmend.setDUTY_AMT(fpoItemDutyOth.getDUTY_AMT());
			fpoItemDetOthDutyAmend.setDUTY_CD(fpoItemDutyOth.getDUTY_CD());
			fpoItemDetOthDutyAmend.setDUTY_DESC(fpoItemDutyOth.getDUTY_DESC());
			fpoItemDetOthDutyAmend.setDUTY_FG(fpoItemDutyOth.getDUTY_FG());
			fpoItemDetOthDutyAmend.setDUTY_NOTN(fpoItemDutyOth.getDUTY_NOTN());
			fpoItemDetOthDutyAmend.setDUTY_RTA(fpoItemDutyOth.getDUTY_RTA());
			fpoItemDetOthDutyAmend.setDUTY_SLNO(fpoItemDutyOth.getDUTY_SLNO());
			fpoItemDetOthDutyAmend.setITEM_ID(fpoItemDutyOth.getITEM_ID());
			fpoItemDetOthDutyAmend.setITEM_NO(fpoItemDutyOth.getITEM_NO());
			fpoItemDetOthDutyAmend.setOFF_ID(fpoItemDutyOth.getOFF_ID());
			fpoItemDetOthDutyAmend.setPOSTINGDT(fpoItemDutyOth.getPOSTINGDT());
			fpoItemDetOthDutyAmend.setROLE(fpoItemDutyOth.getROLE());
			fpoItemDetOthDutyAmendList.add(fpoItemDetOthDutyAmend);
		}

		for (FpoItemDetOthDuty fpoItemDetOthDuty : fpoItemDetOthDutyList) {
			if (null != fpoItemDetOthDuty.getDeltFlag() && fpoItemDetOthDuty.getDeltFlag().equals("O")) {
				delFlagOthr = true;
			} else if (null != fpoItemDetOthDuty.getDeltFlag() && fpoItemDetOthDuty.getDeltFlag().equals("N")) {
				delFlagNinEgt = true;
			}
		}

		try {
			if (delFlagOthr)
				fpoItemDetOthDutyRepo.deleteOthr(fpoItemDbOthDutyList.get(0).getCIN_NO(),
						fpoItemDbOthDutyList.get(0).getITEM_NO());
			else if (delFlagNinEgt)
				fpoItemDetOthDutyRepo.deleteNinEght(fpoItemDbOthDutyList.get(0).getCIN_NO(),
						fpoItemDbOthDutyList.get(0).getITEM_NO());
		} catch (Exception e) {

		}
		fpoItemDetOthDutyAmendRepo.saveAll(fpoItemDetOthDutyAmendList);

		for (int i = 0; i < fpoItemDetOthDutyList.size(); i++) {
			fpoItemDetOthDutyList.get(i).setAMEND_SERAIL_NO(amendSerialNor);
			fpoItemDetOthDutyList.get(i).setAMEND_DT(utilDate);
			fpoItemDetOthDutyList.get(i).setAMEND_FLAG("U");
		}
		return getFpoItemOthersList(fpoItemDetOthDutyList,  session, request);
	}

	public List<FpoItemDetOthDuty> saveFpoItemNinEgtOthrList(List<FpoItemDetOthDuty> fpoItemDetOthDutyList) {
		return fpoItemDetOthDutyRepo.saveAll(fpoItemDetOthDutyList);
	}

	public List<FpoItemDetOthDuty> getOthThanNintyEight(FpoItemDetOthDuty fpoItemDetOthDuty) {
		List<FPO_ITEM_DET> fpoItem = fpoItemDetRepo.getItemOnCin(fpoItemDetOthDuty.getCIN_NO(),
				fpoItemDetOthDuty.getITEM_NO());
		List<FpoItemDetOthDuty> fpoItemDetOthDutyList = fpoItemDetOthDutyRepo
				.getFpoOthAssDutyOnCinItmNo(fpoItemDetOthDuty.getCIN_NO(), fpoItemDetOthDuty.getITEM_NO());
		if (null != fpoItemDetOthDutyList && !fpoItemDetOthDutyList.isEmpty() && null != fpoItem.get(0).getASS_DT()
				&& fpoItemDetOthDutyList.get(0).getASS_DT().compareTo(fpoItem.get(0).getASS_DT()) > 0) {
			return fpoItemDetOthDutyList;
		} else if (null != fpoItemDetOthDutyList && !fpoItemDetOthDutyList.isEmpty()
				&& null == fpoItem.get(0).getASS_DT()) {
			return fpoItemDetOthDutyList;
		}
		return null;
	}

	public List<FpoItemDetOthDuty> getOthrOnCinNor(FpoItemDetOthDuty fpoItemDetOthDuty) {
		// List<FPO_ITEM_DET> fpoItem =
		// fpoItemDetRepo.getItemOnCin(fpoItemDetOthDuty.getCIN_NO(),fpoItemDetOthDuty.getITEM_NO());
		return fpoItemDetOthDutyRepo.getOthrOnCinNor(fpoItemDetOthDuty.getCIN_NO(), fpoItemDetOthDuty.getITEM_NO());
		/*
		 * if(null != fpoItemDetOthDutyList && null != fpoItem.get(0).getASS_DT() &&
		 * fpoItemDetOthDutyList.get(0).getASS_DT().compareTo(fpoItem.get(0).getASS_DT()
		 * ) > 0) { return fpoItemDetOthDutyList; }else if(null != fpoItemDetOthDutyList
		 * && null == fpoItem.get(0).getASS_DT()) { return fpoItemDetOthDutyList; }
		 * return null;
		 */
	}

	public List<FpoItemDetOthDuty> getOthrOnCinNorItem(FpoItemDetOthDuty fpoItemDetOthDuty) {
		// List<FPO_ITEM_DET> fpoItem =
		// fpoItemDetRepo.getItemOnCin(fpoItemDetOthDuty.getCIN_NO(),fpoItemDetOthDuty.getITEM_NO());
		return fpoItemDetOthDutyRepo.getOthrOnCinNorItem(fpoItemDetOthDuty.getCIN_NO(), fpoItemDetOthDuty.getITEM_NO());
		/*
		 * if(null != fpoItemDetOthDutyList && null != fpoItem.get(0).getASS_DT() &&
		 * fpoItemDetOthDutyList.get(0).getASS_DT().compareTo(fpoItem.get(0).getASS_DT()
		 * ) > 0) { return fpoItemDetOthDutyList; }else if(null != fpoItemDetOthDutyList
		 * && null == fpoItem.get(0).getASS_DT()) { return fpoItemDetOthDutyList; }
		 * return null;
		 */
	}

	public List<FpoItemDetOthDuty> getAllOthrOnCinNor(FpoItemDetOthDuty fpoItemDetOthDuty) {
		return fpoItemDetOthDutyRepo.getAllOthrOnCinNor(fpoItemDetOthDuty.getCIN_NO(), fpoItemDetOthDuty.getITEM_NO());
	}

	public FpoItemDetOthDuty getDUTYOnCd(FpoItemDetOthDuty fpoItemDetOthDuty) {
		fpoItemDetOthDuty.setCdDesc(fpoItemDetRepo.getDUTYOnCd(fpoItemDetOthDuty.getDUTY_CD()));
		return fpoItemDetOthDuty;
	}
	
	
	
	/*public void movFPOqryamend(Fpo_qry_raise fpo_qry_raise) {
		java.util.Date utilDate = new java.util.Date();
		List<FpoQuery> fpoqryList = fpoQueryRepo.getAllData(fpo_qry_raise.getCinNo());
		List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_qry_raise.getCinNo());
		for (int i = 0; i < fpoqryList.size(); i++) {
		FpoQueryamend fpoQueryamd = new FpoQueryamend();
		fpoQueryamd.setCinNo(fpoqryList.get(i).getCinNo());
		fpoQueryamd.setCUS_SITE(fpoqryList.get(i).getCUS_SITE());
		fpoQueryamd.setItem_no(fpoqryList.get(i).getItem_no());
		fpoQueryamd.setQRY(fpoqryList.get(i).getQRY());
		fpoQueryamd.setQRY_NO(fpoqryList.get(i).getQRY_NO());
		fpoQueryamd.setQRY_DATE(fpoqryList.get(i).getQRY_DATE());
		fpoQueryamd.setQRY_OFF_ID(fpoqryList.get(i).getQRY_OFF_ID());
		fpoQueryamd.setQRY_ROLE(fpoqryList.get(i).getQRY_ROLE());
		fpoQueryamd.setDEFUALT_QUERY(fpoqryList.get(i).getDEFUALT_QUERY());
		fpoQueryamd.setEmail_Id(fpoqryList.get(i).getEmail_Id());
		fpoQueryamd.setMobile_No(fpoqryList.get(i).getMobile_No());
		fpoQueryamd.setMark(fpoqryList.get(i).getMark());
		fpoQueryamd.setITEM_ID(fpoqryList.get(i).getITEM_ID());
		//if (fpoqryList.get(i).getItem_no()==null )
		    fpoQueryamd.setGENERAL_QUERY(DINList.get(0).getRemarks());
		fpoqryamdRepo.save(fpoQueryamd);}
	}

	public void addFpoQuery(Fpo_qry_raise fpo_qry_raise,String que, HttpSession session, HttpServletRequest request) {

		java.util.Date utilDate = new java.util.Date();
		for (Itemdet itemdet : fpo_qry_raise.getItemdet()) {
			
			if(!itemdet.getItemqry().isEmpty() && itemdet.getItemqry()!=null) {
				
			

			List<FPO_ITEM_DET> fpoItemDET = fpoItemDetRepo.getItemByIdNo(fpo_qry_raise.getCinNo(),
					Long.parseLong(itemdet.getItemno()));
			
			Long maxid=fpoItemDetRepo.getMaxIdOfFPOQUERY(fpo_qry_raise.getCinNo(),Long.parseLong(itemdet.getItemno()),que);
			
			
			
			// only Query update
			FpoQuery fpoQuery = new FpoQuery();
			Long queryNumber = fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_qry_raise.getCinNo());
			if (null != queryNumber && !queryNumber.toString().isEmpty())
				fpoQuery.setQRY_NO(fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_qry_raise.getCinNo()) + 1);
			else
				fpoQuery.setQRY_NO(1l);
			fpoQuery.setCinNo(fpo_qry_raise.getCinNo());
			fpoQuery.setITEM_ID(fpo_qry_raise.getItemId());
			fpoQuery.setQRY(itemdet.getItemqry());
			fpoQuery.setITEM_NO(Long.parseLong(itemdet.getItemno()));
			fpoQuery.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoQuery.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoQuery.setQRY_DATE(utilDate);
//			fpoQuery.setPOSTINGDT(fpo_qry_raise.getPOSTINGDT());
			fpoQuery.setQRY_TYP(que);
			if(maxid!=null && maxid>0) {
				fpoQuery.setId(maxid);
			}
			fpoQueryRepo.save(fpoQuery);
			fpoItemDetRepo.updateAssDate(fpo_qry_raise.getCinNo(), Long.parseLong(itemdet.getItemno()), utilDate,
					request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(), request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			int assessmentSize = fpoItemDetRepo.getItem(fpo_qry_raise.getCinNo()).size();
			Long size = fpoItemDetRepo.getMaxAssess(fpo_qry_raise.getCinNo());
			}
		}
		/*
		 * if (assessmentSize == size) return new fpo_qry_raise(); else return
		 * fpoItemDET.get(0);
		 */

//	}*/


public void addFpoQuery(Fpo_qry_raise fpo_qry_raise,String que,String others, HttpSession session, HttpServletRequest request) {

		java.util.Date utilDate = new java.util.Date();
		for (Itemdet itemdet : fpo_qry_raise.getItemdet()) {
			
			if(!itemdet.getItemqry().isEmpty() && itemdet.getItemqry()!=null) {
				
			

			List<FPO_ITEM_DET> fpoItemDET = fpoItemDetRepo.getItemByIdNo(fpo_qry_raise.getCinNo(),
					Long.parseLong(itemdet.getItemno()));
			
			Long maxid=fpoItemDetRepo.getMaxIdOfFPOQUERY(fpo_qry_raise.getCinNo(),Long.parseLong(itemdet.getItemno()),que);
			
			
			
			// only Query update
			FpoQuery fpoQuery = new FpoQuery();
			Long queryNumber = fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_qry_raise.getCinNo());
			if (null != queryNumber && !queryNumber.toString().isEmpty())
				fpoQuery.setQRY_NO(fpoQueryRepo.getMaxQueryNoOnCinNo(fpo_qry_raise.getCinNo()) + 1);
			else
				fpoQuery.setQRY_NO(1l);
			fpoQuery.setCinNo(fpo_qry_raise.getCinNo());
			fpoQuery.setITEM_ID(fpo_qry_raise.getItemId());
			fpoQuery.setQRY(itemdet.getItemqry());
			fpoQuery.setITEM_NO(Long.parseLong(itemdet.getItemno()));
			fpoQuery.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoQuery.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoQuery.setQRY_DATE(utilDate);
//			fpoQuery.setPOSTINGDT(fpo_qry_raise.getPOSTINGDT());
			fpoQuery.setQRY_TYP(que);
			fpoQuery.setMark(others);
			if(maxid!=null && maxid>0) {
				fpoQuery.setId(maxid);
			}
			fpoQueryRepo.save(fpoQuery);
			fpoItemDetRepo.updateAssDate(fpo_qry_raise.getCinNo(), Long.parseLong(itemdet.getItemno()), utilDate,
					request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(), request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			int assessmentSize = fpoItemDetRepo.getItem(fpo_qry_raise.getCinNo()).size();
			Long size = fpoItemDetRepo.getMaxAssess(fpo_qry_raise.getCinNo());
			}
		}
		/*
		 * if (assessmentSize == size) return new fpo_qry_raise(); else return
		 * fpoItemDET.get(0);
		 */

	}


	/*public void addFpoDocumentsAndGenaralQuery(Fpo_qry_raise fpoItemDet, String que, HttpSession session, HttpServletRequest request) {
		int index;
		Long cou = fpoQueryRepo.getcou(fpoItemDet.getCinNo(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		if (cou > 1)
		   fpoQueryRepo.delrec(fpoItemDet.getCinNo(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		List<FpoQuery> fpoQueryRepoList = new ArrayList<FpoQuery>();
		List<FPO_ITEM_DET> fpoItemList = fpoDeclaredService.getAssesItem(fpoItemDet.getCinNo());
		int dinSerialNumber = 0;
		List<FpoQueryDin> fpoQueryDinList = fpoQueryDinRepo.getFpoQueryDIN(fpoItemDet.getCinNo());
		//String fpoQueryData = fpoItemDet.getQuery();
		String[] fpoQueryList = null;
		/*
		 * if (null != fpoQueryData) fpoQueryList = fpoQueryData.split(",");
		 */
	/*	Long maxQueryNo = fpoQueryRepo.getMaxQueryNo();
		if (null == maxQueryNo || maxQueryNo.toString().isEmpty())
			maxQueryNo = 0l;
		for (FPO_ITEM_DET fpoItem : fpoItemList) {
			FpoQuery fpoQuery = new FpoQuery();
			fpoQuery.setCinNo(fpoItem.getCinNo());
			fpoQuery.setCUS_SITE(fpoItem.getCUS_SITE());
			fpoQuery.setITEM_ID(fpoItem.getITEM_ID());
			fpoQuery.setITEM_NO(fpoItem.getITEM_NO());
//			fpoQuery.setPOSTINGDT(fpoItem.getPOSTINGDT());
			index = fpoItem.getITEM_NO().intValue();
			fpoQuery.setQRY_DATE(new Date());
			fpoQuery.setQRY_OFF_ID(fpoItem.getOFF_ID());
			fpoQuery.setQRY_ROLE(fpoItem.getROLE());
			fpoQuery.setQRY_TYP(que);
			fpoQuery.setQRY_NO(maxQueryNo + 1l);
			fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			
			if (null != fpoQueryList) {
				if (index > fpoQueryList.length) {
					fpoQuery.setQRY("");
				} else if (!(null == fpoQueryList[index - 1]) && !(fpoQueryList[index - 1].isEmpty())) {
					fpoQuery.setQRY(fpoQueryList[index - 1]);
					fpoQueryRepoList.add(fpoQuery);
				}
			}
		}
		int count = fpoService.getDefualtQuery().size();
		String defqry = "";
		
		for(int i=1; i <= count;i++) {
			
			String checkbox = "checkbox"+i;
			
			defqry = defqry + request.getParameter(checkbox);
			
			//defqry.concat(request.getParameter(checkbox).toString());
			
			if(count != i) {
				//defqry.concat(";");
				defqry = defqry + ";";
			}
		}

		FpoQuery fpoQueryRemarks = new FpoQuery();
		fpoQueryRemarks.setCinNo(fpoItemDet.getCinNo());
		fpoQueryRemarks.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoQueryRemarks.setITEM_ID(fpoItemDet.getItemId());
		que=fpocurquerepo.getcurqueCin(fpoItemDet.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString()).substring(0,1);
//		fpoQueryRemarks.setPOSTINGDT(fpoItemDet.getPOSTINGDT());
		fpoQueryRemarks.setDEFUALT_QUERY(defqry);
		fpoQueryRemarks.setQRY_DATE(new Date());
		fpoQueryRemarks.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		fpoQueryRemarks.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		fpoQueryRemarks.setQRY_TYP(que);
		fpoQueryRemarks.setEmail_Id(null);
		fpoQueryRemarks.setMobile_No(null);
		fpoQueryRemarks.setQRY_NO(maxQueryNo + 1l);
		fpoQueryRemarks.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoQueryRepoList.add(fpoQueryRemarks);
		fpoQueryRepo.saveAll(fpoQueryRepoList);


			if (null != fpoQueryDinList && !fpoQueryDinList.isEmpty()) {
				dinSerialNumber = fpoQueryDinList.size();
			}

			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			String[] dateArray = date.split("-");
			FpoQueryDin fpoQueryDin = new FpoQueryDin();
			fpoQueryDin.setCinNo(fpoItemDet.getCinNo());
			fpoQueryDin.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoQueryDin.setItemid(fpoItemDet.getItemId());
			fpoQueryDin.setRemarks(fpoItemDet.getGeneralquery());
			fpoQueryDin.setDepComments(null);
			fpoQueryDin.setDin1(null);
			fpoQueryDin.setDinSerialNumber(dinSerialNumber + 1l);
			String fn="";
			if (que=="E")
				fn="EAD";
			else if (que=="N")
				fn="AAA";
			else if (que=="P")
				fn="AAF";				
			fpoQueryDin
				.setUniqueNo(fn + request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString() + dateArray[0] + dateArray[1] + dateArray[2] + gen());
	
			fpoQueryDinRepo.save(fpoQueryDin);

	}*/

public void addFpoDocumentsAndGenaralQuery(Fpo_qry_raise fpoItemDet,String others, String que, HttpSession session, HttpServletRequest request) {
	String itemId1 = fpoQueryDinRepo.getgmail(fpoItemDet.getItemId());
	String decmt = fpoQueryDinRepo.getdcallnoq(fpoItemDet.getItemId());
	String mobile = fpoQueryDinRepo.getmobile(fpoItemDet.getItemId());
	String decmtacl = fpoQueryDinRepo.getdecomacl(fpoItemDet.getItemId());
	
	
//	if(que.length()>1) {
//		que=que.substring(0, 1);
//	}
	
	
	int index;
	Long cou = fpoQueryRepo.getcou(fpoItemDet.getCinNo(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	if (cou > 1)
	   fpoQueryRepo.delrec(fpoItemDet.getCinNo(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	List<FpoQuery> fpoQueryRepoList = new ArrayList<FpoQuery>();
	List<FPO_ITEM_DET> fpoItemList = fpoDeclaredService.getAssesItem(fpoItemDet.getCinNo());
	int dinSerialNumber = 0;
	List<FpoQueryDin> fpoQueryDinList = fpoQueryDinRepo.getFpoQueryDIN(fpoItemDet.getCinNo());
	//String fpoQueryData = fpoItemDet.getQuery();
	String[] fpoQueryList = null;
	/*
	 * if (null != fpoQueryData) fpoQueryList = fpoQueryData.split(",");
	 */
	Long maxQueryNo = fpoQueryRepo.getMaxQueryNo();
	if (null == maxQueryNo || maxQueryNo.toString().isEmpty())
		maxQueryNo = 0l;
	for (FPO_ITEM_DET fpoItem : fpoItemList) {
		FpoQuery fpoQuery = new FpoQuery();
		fpoQuery.setCinNo(fpoItem.getCinNo());
		fpoQuery.setCUS_SITE(fpoItem.getCUS_SITE());
		fpoQuery.setITEM_ID(fpoItem.getITEM_ID());
		fpoQuery.setITEM_NO(fpoItem.getITEM_NO());
//		fpoQuery.setPOSTINGDT(fpoItem.getPOSTINGDT());
		index = fpoItem.getITEM_NO().intValue();
		fpoQuery.setQRY_DATE(new Date());
		fpoQuery.setQRY_OFF_ID(fpoItem.getOFF_ID());
		fpoQuery.setQRY_ROLE(fpoItem.getROLE());
		fpoQuery.setQRY_TYP(que);
		fpoQuery.setMark(others);
		fpoQuery.setQRY_NO(maxQueryNo + 1l);
		fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		
		if (null != fpoQueryList) {
			if (index > fpoQueryList.length) {
				fpoQuery.setQRY("");
			} else if (!(null == fpoQueryList[index - 1]) && !(fpoQueryList[index - 1].isEmpty())) {
				fpoQuery.setQRY(fpoQueryList[index - 1]);
				fpoQueryRepoList.add(fpoQuery);
			}
		}
	}
	int count = fpoService.getDefualtQuery().size();
	String defqry = "";
	
	for(int i=1; i <= count;i++) {
		
		String checkbox = "checkbox"+i;
		
		defqry = defqry + request.getParameter(checkbox);
		
		//defqry.concat(request.getParameter(checkbox).toString());
		
		if(count != i) {
			//defqry.concat(";");
			defqry = defqry + ";";
		}
	}

	FpoQuery fpoQueryRemarks = new FpoQuery();
	fpoQueryRemarks.setCinNo(fpoItemDet.getCinNo());
	fpoQueryRemarks.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	fpoQueryRemarks.setITEM_ID(fpoItemDet.getItemId());
//	que=fpocurquerepo.getcurqueCin(fpoItemDet.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString()).substring(0,1);
	//que=fpocurquerepo.getcurqueCin(fpoItemDet.getCinNo()).substring(0,1));
	que=(fpocurquerepo.getcurqueCin(fpoItemDet.getCinNo())).substring(0, 1);
	
//	if(que.length()>1) {
//		que=que.substring(0, 1);
//	}
	System.out.println("checking que value"+que);
//	fpoQueryRemarks.setPOSTINGDT(fpoItemDet.getPOSTINGDT());
	fpoQueryRemarks.setDEFUALT_QUERY(defqry);
	fpoQueryRemarks.setQRY_DATE(new Date());
	fpoQueryRemarks.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	fpoQueryRemarks.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
	fpoQueryRemarks.setQRY_TYP(que);
	fpoQueryRemarks.setMark(others);
	//fpoQueryRemarks.setEmail_Id(null);
	fpoQueryRemarks.setEmail_Id(itemId1);
	fpoQueryRemarks.setMobile_No(mobile);
	//fpoQueryRemarks.setMobile_No(null);
	fpoQueryRemarks.setQRY_NO(maxQueryNo + 1l);
	fpoQueryRemarks.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	fpoQueryRepoList.add(fpoQueryRemarks);
	fpoQueryRepo.saveAll(fpoQueryRepoList);


		if (null != fpoQueryDinList && !fpoQueryDinList.isEmpty()) {
			dinSerialNumber = fpoQueryDinList.size();
		}

		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String[] dateArray = date.split("-");
		FpoQueryDin fpoQueryDin = new FpoQueryDin();
		fpoQueryDin.setCinNo(fpoItemDet.getCinNo());
		fpoQueryDin.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		fpoQueryDin.setItemid(fpoItemDet.getItemId());
		fpoQueryDin.setRemarks(fpoItemDet.getGeneralquery());
		fpoQueryDin.setDepComments(decmt);
		fpoQueryDin.setDepCmtsAcl(decmtacl);
		fpoQueryDin.setDin1(null);
		fpoQueryDin.setDinSerialNumber(dinSerialNumber + 1l);
		String fn="";
		if (que=="E")
			fn="EAD";
		else if (que=="N")
			fn="AAA";
		else if (que=="P")
			fn="AAF";				
		fpoQueryDin
			.setUniqueNo(fn + request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString() + dateArray[0] + dateArray[1] + dateArray[2] + gen());

		fpoQueryDinRepo.save(fpoQueryDin);

} 
    




	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
	
	public void addFpoAddQry(FPOSecondDefQry fPOSecondDefQry, FPO_ITEM_DET fpo_item_det, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub

		List<FPO_MAIN> fpoMainList = fpoRepost.getmain(fPOSecondDefQry.getCinNo());
		List<FPO_MAIN> fpoMain = fpoRepost.getmain(fpo_item_det.getCinNo());
		System.out.println("ACL ASS VALUE IS " + fpoRepost.getaclassval());
		if (fpoMain.get(0).getTOT_ASS_VAL() > fpoRepost.getaclassval()
				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAO"))) {
			fpoRepost.updateRole(fpoMain.get(0).getId());
			
}
		
		
		else if (fpoMain.get(0).getTOT_ASS_VAL() > fpoRepost.getaclassval()
				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAC"))) {
			System.out.println("inside");
			fpoRepost.updatingRole(fpoMain.get(0).getId());
			
		}


		
		
		
		
		if (!fpoMainList.isEmpty()) {

			
			//List<FpoAddlQry> articleAwaitingQuery = null;
			String role=request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			 if (fPOSecondDefQry.getReply().size()>0)
		     	fpoAddlQryRepo.deleteFpoAddlQrybyCinno(fPOSecondDefQry.getCinNo(),role);
			
			for (Reply iterable_element : fPOSecondDefQry.getReply()) {
				
				/*
				 * if(iterable_element.getSlno() != null) { articleAwaitingQuery =
				 * fpoAddlQryRepo.getFpoAddlQry(fPOSecondDefQry.getCinNo(),iterable_element.
				 * getSlno()); }else { articleAwaitingQuery =
				 * fpoAddlQryRepo.getFpoAddlQry(fPOSecondDefQry.getCinNo()); }
				 */
					
				
				FpoAddlQry fpoAddlQry = new FpoAddlQry();
				
				if(iterable_element.getDesc1() != null && !iterable_element.getDesc1().isEmpty() && !iterable_element.getDesc1().isEmpty()) {
				fpoAddlQry.setCIN_NO(fPOSecondDefQry.getCinNo());
				fpoAddlQry.setITEM_ID(fPOSecondDefQry.getItemId());
				fpoAddlQry.setQRY_DEF_SLNO(iterable_element.getSlno());
				if(!iterable_element.getDesc1().equalsIgnoreCase("undefined")) {
					fpoAddlQry.setQRY_DESC(iterable_element.getDesc1());
				}
				

				fpoAddlQry.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
				fpoAddlQry.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
				fpoAddlQry.setQRY_DATE(new Date());
				fpoAddlQry.setQRY_TYPE(fPOSecondDefQry.getQrytype());
			//	fpoAddlQry.setDIN1(fPOSecondDefQry.getDinNo());
				/*
				 * if (!articleAwaitingQuery.isEmpty()) {
				 * fpoAddlQry.setQRY_ID(articleAwaitingQuery.get(0).getQRY_ID()); }
				 */
				
				fpoAddlQryRepo.save(fpoAddlQry);
				}

				if(iterable_element.getInpReq().equalsIgnoreCase("2")) {
					if(iterable_element.getDesc2() != null && !iterable_element.getDesc2().isEmpty() && !iterable_element.getDesc2().isEmpty()) {
					 fpoAddlQry = new FpoAddlQry();
					fpoAddlQry.setCIN_NO(fPOSecondDefQry.getCinNo());
					fpoAddlQry.setITEM_ID(fPOSecondDefQry.getItemId());
					fpoAddlQry.setQRY_DEF_SLNO(iterable_element.getSlno());
					
					if(!iterable_element.getDesc2().equalsIgnoreCase("undefined")) {
						fpoAddlQry.setQRY_DESC(iterable_element.getDesc2());
					}
					
					/*
					 * if (!articleAwaitingQuery.isEmpty()) {
					 * fpoAddlQry.setQRY_ID(articleAwaitingQuery.get(0).getQRY_ID()); }
					 */
					fpoAddlQry.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
					fpoAddlQry.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
					fpoAddlQry.setQRY_DATE(new Date());
					fpoAddlQry.setQRY_TYPE(fPOSecondDefQry.getQrytype());
				//	fpoAddlQry.setDIN1(fPOSecondDefQry.getDinNo());
					fpoAddlQryRepo.save(fpoAddlQry);
					}
				}
			}
			
			//articleAwaitingQuery = fpoAddlQryRepo.getFpoAddlQry(fPOSecondDefQry.getCinNo());
			
			FpoAddlQry fpoAddlQry = new FpoAddlQry();
			fpoAddlQry.setCIN_NO(fPOSecondDefQry.getCinNo());
			fpoAddlQry.setITEM_ID(fPOSecondDefQry.getItemId());
			fpoAddlQry.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoAddlQry.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoAddlQry.setQRY_DATE(new Date());
			fpoAddlQry.setQRY_TYPE(fPOSecondDefQry.getQrytype());
		//	fpoAddlQry.setDIN1(fPOSecondDefQry.getDinNo());
			

			if (fPOSecondDefQry.getEmail() != null)
			{	fpoAddlQry.setQRY_EMAILID(fPOSecondDefQry.getEmail()); 
   		        request.getSession().setAttribute("toEnteredMailiD",fPOSecondDefQry.getEmail());
			}
			if (fPOSecondDefQry.getMobile() != null)
			{	fpoAddlQry.setQRY_MOBILENO(fPOSecondDefQry.getMobile());
			request.getSession().setAttribute("toEnteredMobileNumber",fPOSecondDefQry.getMobile());
			}
			/*
			 * if (!articleAwaitingQuery.isEmpty()) {
			 * fpoAddlQry.setQRY_ID(articleAwaitingQuery.get(0).getQRY_ID()); }
			 */
			
			
			
			
			
			/*
			 * if(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equalsIgnoreCase("APR")) {
			 * fpoAddlQry.setQRY_DEPCMTS_APR(fPOSecondDefQry.getQuery()); }else
			 * if(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString().equalsIgnoreCase("ACL")) {
			 * fpoAddlQry.setQRY_DEPCMTS_ACL(fPOSecondDefQry.getQuery()); }
			 */
			fpoAddlQry.setQRY_DESC(fPOSecondDefQry.getQuery());
			fpoAddlQryRepo.save(fpoAddlQry);
			
			
		//	fpoQueryDinRepo.updateAdditionQueryDinNo(cinNo, dinNo);
		}
			
	}
	
	
	

	@Autowired
	FPO_MAINrepost FPOrepost;
	
	
	@Autowired
	FPO_MAINrepost fpomainrepo;
	
	public FpoQueryDecision updatemvmtandqrydeci(FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt, HttpSession session,
			HttpServletRequest request) {
		//System.out.println("fpoqryDec.getCIN_NO()=" + fpoqryDec.getCIN_NO());
		System.out.println("fpomvmnt.getCinNo()=" + fpomvmnt.getCinNo());
		
		String Cinno = fpomvmnt.getCinNo();
		//String offID = request.getSession().getAttribute("role").toString();
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(Cinno);
		Float maxaclassval = FPOrepost.getaclassval();
		System.out.println("threashold value" + maxaclassval);
		//System.out.println("article value value" + fpoMain.get(0).getTOT_ASS_VAL());
		//System.out.println("Role" + fpoMain.get(0).getROLE());
		
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
		//String offid = fpoMvmntRepo.getoffid(Cinno);
		//String role = fpoMvmntRepo.getrole(Cinno);
		String offid = request.getSession().getAttribute("offId").toString();
		String role = request.getSession().getAttribute("role").toString();
		System.out.println("cinno=" + Cinno + "offid=" + offid + "role=" + role);
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		String stage;
		if (fpoMain.get(0).getARR_SCAN()==null)
			stage="A2";
		else if (fpoMain.get(0).getARR_SCAN().equals("Y"))
			stage="A3";
		else
			stage="A2";
		
		if((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAO")  ) {
			fpoService.insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "PAO", stage, session,
					request);
		//	fpoQueryDecisionRepo.updateForAOtoAC(fpoqryDec.getCIN_NO(), utilDate, fpoMain.get(0).getACL_OFFID(), "PAC");
			fpoQueryDecisionRepo.updassaddlqry(Cinno, utilDate, "PAC", stage);
		} 
		// for approval only
		else if ((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAC")  ) {
			fpoService.insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "IMP", stage, session,
					request);
		//	fpoQueryDecisionRepo.updateForAOtoAC(fpoqryDec.getCIN_NO(), utilDate,fpoMain.get(0).getACL_OFFID(), "IMP");
			fpoQueryDecisionRepo.updassaddlqry(Cinno, utilDate, "IMP",stage);
		}
//		else {
//		insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "IMP", "P2", session,
//				request);
//		fpoQueryDecisionRepo.updassqry(fpoqryDec.getCIN_NO(), utilDate, "IMP");
//		}
		//updfpocurque(Cinno, "P2", "IMP", utilDate, session, request);
		
		
		return fpoqryDec;
	}
	
	
	
	
	
	
}
