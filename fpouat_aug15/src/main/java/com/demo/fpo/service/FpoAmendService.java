package com.demo.fpo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.controller.LoginController;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.model.FPO_AMEND;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.repos.Asstreportrepost;
import com.demo.fpo.repos.C_CUSITMrepost;
import com.demo.fpo.repos.DECI_REASrepost;
import com.demo.fpo.repos.FPO_AMENDrepost;
import com.demo.fpo.repos.FPO_AMENDrepostImpl;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FPO_ORDERrepost;
import com.demo.fpo.repos.FpoMainAmendRepo;
import com.demo.fpo.repos.FpoQueryRepo;

@Service
@Component
public class FpoAmendService {

	@Autowired
	FPO_AMENDrepostImpl fpoAmendRepostImpl;

	@Autowired
	FPO_AMENDrepost fpoAmendRepo;

	@Autowired
	FpoMainAmendRepo fpoMainAmendRepo;

	@Autowired
	Asstreportrepost asstreportrepost;

	@Autowired
	C_CUSITMrepost CUSITMrepost;

	@Autowired
	FPO_MAINrepost FPOrepost;

	@Autowired
	FPO_ITEM_DETrepost FPO_ITEMrepost;

	@Autowired
	FPO_ORDERrepost FPO_ORDERrepost;

	@Autowired
	DECI_REASrepost DECI_REASrepost;

	@Autowired
	FpoService fpoService;

	@Autowired
	FpoDeclaredService fpoDeclaredService;

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	public List<FPO_ITEM_DET> amendItems(FPO_ITEM_DET fpoItemDet, HttpSession session, HttpServletRequest request) {

		java.util.Date utilDate = new java.util.Date();
		List<FPO_AMEND> fpoAmend = fpoService.getFpoAmendOnCinItemNo(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		Long maxAmSlNo = fpoService.getMaxFpoItemAmendOnCinItemNo(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());
		List<FPO_ITEM_DET> fpoItemDET = FPO_ITEMrepost.getItemOnCin(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO());

		FpoQuery fpoQuery = new FpoQuery();
		if (null != fpoItemDet.getQuery() && !fpoItemDet.getQuery().isEmpty()
				&& fpoItemDet.getFlagRequestQry().equals("Q")) {
			Long queryNumber = fpoQueryRepo.getMaxQueryNoOnCinNo(fpoItemDet.getCinNo());
			if (null != queryNumber && !queryNumber.toString().isEmpty())
				fpoQuery.setQRY_NO(fpoQueryRepo.getMaxQueryNoOnCinNo(fpoItemDet.getCinNo()) + 1);
			else
				fpoQuery.setQRY_NO(1l);

			fpoQuery.setCinNo(fpoItemDet.getCinNo());
			fpoQuery.setITEM_ID(fpoItemDet.getITEM_ID());
			fpoQuery.setQRY_DATE(fpoItemDet.getASS_DT());
			fpoQuery.setQRY(fpoItemDet.getQuery());
			fpoQuery.setITEM_NO(fpoItemDet.getITEM_NO());
			fpoQuery.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoQuery.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoQuery.setQRY_DATE(utilDate);
//			fpoQuery.setPOSTINGDT(fpoItemDet.getPOSTINGDT());
			fpoQuery.setQRY_TYP("E");
			fpoQueryRepo.save(fpoQuery);
		}

		if (fpoItemDet.getFlagRequest().equals("N")) {
			if (!((fpoAmend == null) || fpoAmend.isEmpty())) {
				fpoService.moveItemToAmend(fpoItemDET.get(0), maxAmSlNo + 1l,  session,request);
				fpoItemDET.get(0).setAMEND_SERAIL_NO(maxAmSlNo + 1l);
			} else {

				fpoService.moveItemToAmend(fpoItemDET.get(0), 1l,  session,request);
				fpoItemDET.get(0).setAMEND_SERAIL_NO(1l);
			}

			if (null != fpoItemDet.getBCD_NOTN() && fpoItemDet.getBCD_NSNO().isEmpty()
					&& fpoItemDet.getBCD_NOTN().equals("Select NOTN"))
				fpoItemDET.get(0).setBCD_NOTN(null);
			else
				fpoItemDET.get(0).setBCD_NOTN(fpoItemDet.getBCD_NOTN());

			if (null != fpoItemDet.getBCD_NSNO() && !fpoItemDet.getBCD_NSNO().isEmpty()
					&& fpoItemDet.getBCD_NSNO().equals("Select NSNO"))
				fpoItemDET.get(0).setBCD_NSNO(null);
			else
				fpoItemDET.get(0).setBCD_NSNO(fpoItemDet.getBCD_NSNO());

			if (null != fpoItemDet.getIGST_NOTN() && !fpoItemDet.getIGST_NOTN().isEmpty()
					&& fpoItemDet.getIGST_NOTN().equals("Select NOTN"))
				fpoItemDET.get(0).setIGST_NOTN(null);
			else
				fpoItemDET.get(0).setIGST_NOTN(fpoItemDet.getIGST_NOTN());

			if (null != fpoItemDet.getIGST_NSNO() && !fpoItemDet.getIGST_NSNO().isEmpty()
					&& fpoItemDet.getIGST_NSNO().equals("Select NSNO"))
				fpoItemDET.get(0).setIGST_NSNO(null);
			else
				fpoItemDET.get(0).setIGST_NSNO(fpoItemDet.getIGST_NSNO());

			if (null != fpoItemDet.getSW_NOTN() && !fpoItemDet.getSW_NOTN().isEmpty()
					&& fpoItemDet.getSW_NOTN().equals("Select NOTN"))
				fpoItemDET.get(0).setSW_NOTN(null);
			else
				fpoItemDET.get(0).setSW_NOTN(fpoItemDet.getSW_NOTN());

			if (null != fpoItemDet.getSW_NSNO() && !fpoItemDet.getSW_NSNO().isEmpty()
					&& fpoItemDet.getSW_NSNO().equals("Select NSNO"))
				fpoItemDET.get(0).setSW_NSNO(null);
			else
				fpoItemDET.get(0).setSW_NSNO(fpoItemDet.getSW_NSNO());

			fpoItemDET.get(0).setMODIFIED("Y");
			fpoItemDET.get(0).setAMEND_FLAG("U");
			fpoItemDET.get(0).setAMEND_DT(utilDate);
			fpoItemDET.get(0).setOFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoItemDET.get(0).setROLE(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			fpoItemDET.get(0).setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpoItemDET.get(0).setASSESS_VAL(fpoItemDet.getASSESS_VAL());
			fpoItemDET.get(0).setASS_DT(fpoItemDet.getASS_DT());
			fpoItemDET.get(0).setRate(fpoItemDet.getRate());
			fpoItemDET.get(0).setSW_AMT_FG(fpoItemDet.getSW_AMT_FG());
			fpoItemDET.get(0).setSW_AMT(fpoItemDet.getSW_AMT());
			fpoItemDET.get(0).setSW_RTA(fpoItemDet.getSW_RTA());
			fpoItemDET.get(0).setIGST_AMT(fpoItemDet.getIGST_AMT());
			fpoItemDET.get(0).setIGST_AMT_FG(fpoItemDet.getIGST_AMT_FG());
			fpoItemDET.get(0).setIGST_RTA(fpoItemDet.getIGST_RTA());
			fpoItemDET.get(0).setBCD_AMT(fpoItemDet.getBCD_AMT());
			fpoItemDET.get(0).setBCD_AMT_FG(fpoItemDet.getBCD_AMT_FG());
			fpoItemDET.get(0).setBCD_RTA(fpoItemDet.getBCD_RTA());
			fpoItemDET.get(0).setNETWT(fpoItemDet.getNETWT());
			fpoItemDET.get(0).setCURRCD(fpoItemDet.getCURRCD());
			fpoItemDET.get(0).setGEN_CTH(fpoItemDet.getGEN_CTH());
			fpoItemDET.get(0).setCTH_REVISED(fpoItemDet.getCTH_REVISED());
			fpoItemDET.get(0).setNETWT(fpoItemDet.getNETWT());
			fpoItemDET.get(0).setNOU(fpoItemDet.getNOU());
			fpoItemDET.get(0).setITEM_DESC(fpoItemDet.getITEM_DESC());
			fpoItemDET.get(0).setDUTY(fpoItemDet.getDUTY());
			fpoItemDET.get(0).setDUTY_FG(fpoItemDet.getDUTY_FG());
			fpoItemDET.get(0).setAllItemDuty(fpoItemDet.getAllItemDuty());
			fpoItemDET.get(0).setAllItemDutyFg(fpoItemDet.getAllItemDutyFg());
			FPO_ITEMrepost.save(fpoItemDET.get(0));

			Long maxMainAmend = fpoMainAmendRepo.getFpoAmendOnCinItemNo(fpoItemDet.getCinNo());

			List<FPO_MAIN> fpoMain = fpoMainRepost.getmain(fpoItemDet.getCinNo());
			if (null != maxMainAmend)
				fpoMain.get(0).setAMEND_SERIAL_NO(maxMainAmend + 1l);
			else
				fpoMain.get(0).setAMEND_SERIAL_NO(1l);
			fpoService.moveMainToAmend(fpoMain.get(0),session);
			fpoMain.get(0).setAMEND_FLAG("U");
			if (null != maxMainAmend)
				fpoMain.get(0).setAMEND_SERIAL_NO(maxMainAmend + 1l);
			else
				fpoMain.get(0).setAMEND_SERIAL_NO(maxMainAmend);
			fpoMain.get(0).setAMEND_DT(utilDate);
			fpoMain.get(0).setTOT_DUTY(fpoItemDet.getAllItemDuty());
			System.out.println("dutcalc value is " +request.getSession().getAttribute("dutyCalc_category").toString());
			System.out.println("nature type cd is " +fpoMain.get(0).getNATURE_TYPE_CD().trim());
			System.out.println("equals "+ request.getSession().getAttribute("dutyCalc_category").toString().equals(fpoMain.get(0).getNATURE_TYPE_CD().trim()));
			float f2=((DUTY_CALC_DETAILS) request.getSession().getAttribute("dutyCalc")).getAssval_Amt();
			float f1=fpoMain.get(0).getTOT_ASS_VAL();
			System.out.println("in fpoamendservice f1,f2,comparison is "+ f1 + "," + f2 + "," + Float.compare(f1,f2));
			if (request.getSession().getAttribute("dutyCalc_category").toString().equals(fpoMain.get(0).getNATURE_TYPE_CD().trim()))
			  fpoMain.get(0).setTOT_AMT_PAYABLE(fpoItemDet.getAllItemDuty());
			else if (Float.compare(f1, f2) > 0 )
				{System.out.println("greater amount");
				fpoMain.get(0).setTOT_AMT_PAYABLE(fpoItemDet.getAllItemDuty());
				}
			else
			{ System.out.println("lesser amount");
			  fpoMain.get(0).setTOT_AMT_PAYABLE(0f);}
			fpoMain.get(0).setTotalDutyFg(fpoItemDet.getAllItemDutyFg());
			fpoMainRepost.save(fpoMain.get(0));
		}
		return fpoItemDET;
	}

	public FPO_ITEM_DET getLatestAmendOnCinAndItemNo(FPO_ITEM_DET fpoItemDet) {
		return fpoService.moveAmendToItem(
				fpoAmendRepo.getLatestAmendOnCinAndItemNo(fpoItemDet.getCinNo(), fpoItemDet.getITEM_NO()));
	}

	public FPO_ITEM_DET getFirstAmendOnCinAndItemNo(String cinNo, Long itemNumber) {
		return fpoService.moveAmendToItem(fpoAmendRepo.getLatestAmendOnCinAndItemNo(cinNo, itemNumber));
	}
}
