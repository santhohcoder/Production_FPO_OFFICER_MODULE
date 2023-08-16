package com.demo.fpo.NsmLsmService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.NsmLsmModel.D_EMP_ROLES;
import com.demo.fpo.NsmLsmModel.EDIT_D_EMP_ROLES;
import com.demo.fpo.NsmLsmModel.EDIT_FPO_SITE;
import com.demo.fpo.NsmLsmModel.FPO_SITE;
import com.demo.fpo.NsmLsmModel.FPO_SITE_INFO;
import com.demo.fpo.NsmLsmModel.FPO_SITE_MGMT;
import com.demo.fpo.NsmLsmModel.NSM_procesdata;
import com.demo.fpo.NsmLsmModel.REALLOCATION;
import com.demo.fpo.NsmLsmRepo.D_EMP_ROLErepo_NSM;
import com.demo.fpo.NsmLsmRepo.EDIT_D_EMP_ROLES_repo;
import com.demo.fpo.NsmLsmRepo.FPO_SITE_EDITrepo;
import com.demo.fpo.NsmLsmRepo.FpoSiteMgmtRepo;
import com.demo.fpo.NsmLsmRepo.FPO_SITErepo;

@Service
@Component
public class NSMSiteService {

	/*
	 * @Autowired FpoSiteRepo fpoSiteRepo;
	 */

	@Autowired
	FpoSiteMgmtRepo fpositemgmtrepo;

	@Autowired
	FPO_SITE_EDITrepo siteeditrepo;
	
	@Autowired
	FPO_SITErepo fpositerepo;

	@Autowired
	D_EMP_ROLErepo_NSM demproleNSM;

	@Autowired
	EDIT_D_EMP_ROLES_repo editDemprolerepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * @Autowired Reallocation_repo reallocaterepo;
	 */

	/*
	 * public List<String> getAllStates() { return fpoSiteRepo.getAllStates(); }
	 * 
	 * public boolean addSite(FpoSite fpoSite) { FpoSite fpoSiteNewObj =
	 * fpoSiteRepo.findBysiteCode(fpoSite.getSiteCode()); if (fpoSiteNewObj == null)
	 * { fpoSiteRepo.addSite(fpoSite.getSiteName(),
	 * fpoSite.getSiteCode().toUpperCase(), fpoSite.getSiteState()); //
	 * fpoSiteRepo.save(fpoSite); return true; } else { return false; }
	 * 
	 * }
	 */

	public List<Collection> getactiveSite() {
		return fpositemgmtrepo.getactiveSites();
	}

	/*
	 * public List<Collection> getAllActivateSite() { return
	 * fpoSiteRepo.getAllActivateSite(); }
	 * 
	 * public FpoSite getSiteBySiteCode(String siteCode) { return
	 * fpoSiteRepo.findBysiteCode(siteCode); }
	 * 
	 * public void updateFpoSite(FpoSite fpoSite) {
	 * fpoSiteRepo.updateFpoSite(fpoSite.getSiteName(), fpoSite.getSiteCode(),
	 * fpoSite.getSiteState()); }
	 * 
	 * public List<String> getAllSieCode(String stateName) { return
	 * fpoSiteRepo.getSiteCode(stateName); }
	 */

	// NSM code

	public FPO_SITE_MGMT fpositeedit(FPO_SITE_MGMT fpositeedit,  HttpServletRequest request, HttpSession session) throws ParseException {
		EDIT_FPO_SITE editsite = new EDIT_FPO_SITE();
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
	//	fpositemgmtrepo.updatefpositemgmt(fpositeedit.getSiteType(), fpositeedit.getSiteState(),date,request.getSession().getAttribute("offId").toString(),fpositeedit.getReason(),  fpositeedit.getSiteCode());
		String StateName = fpositemgmtrepo.getStateName(fpositeedit.getSiteState());
		fpositemgmtrepo.updatefpositemgmt(fpositeedit.getSiteType(), StateName,date,request.getSession().getAttribute("offId").toString(),fpositeedit.getReason(),  fpositeedit.getSiteCode());
		fpositerepo.updatefposite(fpositeedit.getClustered(),fpositeedit.getClussite(),fpositeedit.getSiteCode());
		FPO_SITE_MGMT getaddsite = fpositemgmtrepo.geteditsite(fpositeedit.getSiteCode());
		FPO_SITE getfposite = fpositerepo.getfposite(fpositeedit.getSiteCode());
	//	fpositemgmtrepo.sitedelete(fpositeedit.getSiteCode());
		editsite.setOffid(getaddsite.getOffid());
		editsite.setSiteCode(getaddsite.getSiteCode());
		editsite.setSiteName(getaddsite.getSiteName());
		editsite.setSiteState(getaddsite.getSiteState());
		editsite.setSiteStateCd(getaddsite.getSiteStateCd());
		editsite.setSiteType(getaddsite.getSiteType());
		editsite.setSitests(getaddsite.getSitests());
		editsite.setCreateddate(getaddsite.getCreateddate());
		editsite.setReason(getaddsite.getReason());
		editsite.setClustered(getfposite.getClustered());
		editsite.setClussite(getfposite.getClussite());
		siteeditrepo.save(editsite);
	//	String fpositestate = fpositeedit.getSiteState();
	//	String fpoSs = fpositemgmtrepo.siteState(fpositestate);
	
		return fpositeedit;

	}

	public String insertdt(HttpServletRequest request) throws ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		String mailclsAPR = request.getParameterValues("checkedarray")[0];
		String[] rlnme = request.getParameterValues("rolenme")[0].split(",");
		String usid = request.getParameter("usrid");
		String cs = request.getParameter("cusSite");
		String lgusr = request.getParameter("LoginUser");
		String mailclsSUP = null;
		String mailclsPIN = null;
		if (!(rlnme[0].equals("PNA") || rlnme[0].equals("NAL") || rlnme[0].equals("NRP")))
		{mailclsSUP = request.getParameterValues("mailclsSUP")[0];
		mailclsPIN = request.getParameterValues("mailclsPIN")[0];}
		for (String offcer : rlnme) {
			if (offcer.equals("PAO") || offcer.equals("PSU") || offcer.equals("PIN")) {
				if (offcer.equals("PAO")) {
					D_EMP_ROLES emproles = new D_EMP_ROLES();
					emproles.setAssgnDt(date);
					emproles.setStartDt(date);
					emproles.setMailclasscd(mailclsAPR);
					emproles.setUserid(usid);
					emproles.setRoleName(offcer);
					emproles.setCusSite(cs);
					emproles.setAssignBy(lgusr);
					demproleNSM.save(emproles);
				} else if (offcer.equals("PSU")) {
					D_EMP_ROLES emproleSUP = new D_EMP_ROLES();
					emproleSUP.setAssgnDt(date);
					emproleSUP.setStartDt(date);
					emproleSUP.setMailclasscd(mailclsSUP);
					emproleSUP.setUserid(usid);
					emproleSUP.setRoleName(offcer);
					emproleSUP.setCusSite(cs);
					emproleSUP.setAssignBy(lgusr);
					demproleNSM.save(emproleSUP);
				}  else if (offcer.equals("PIN")) {
					D_EMP_ROLES emprolePIN = new D_EMP_ROLES();
					emprolePIN.setAssgnDt(date);
					emprolePIN.setStartDt(date);
					emprolePIN.setMailclasscd(mailclsPIN);
					emprolePIN.setUserid(usid);
					emprolePIN.setRoleName(offcer);
					emprolePIN.setCusSite(cs);
					emprolePIN.setAssignBy(lgusr);
					demproleNSM.save(emprolePIN);
				}

			} else {
				D_EMP_ROLES insertdata = new D_EMP_ROLES();
				insertdata.setAssgnDt(date);
				insertdata.setStartDt(date);
				insertdata.setUserid(usid);
				insertdata.setRoleName(offcer);
				insertdata.setCusSite(cs);
				insertdata.setAssignBy(lgusr);
				demproleNSM.save(insertdata);
			}

		}
		return null;

	}

	public String editrole(HttpServletRequest request) throws ParseException {
		
		List<D_EMP_ROLES> getolddt = demproleNSM.getolddata(request.getParameter("usrid"),
				request.getParameter("cusSite"));
		Date currentdate = new Date();
		System.out.println("userid is " + request.getParameter("usrid"));
		System.out.println("cusite is " + request.getParameter("cusSite"));
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		for (D_EMP_ROLES dataval : getolddt) {
			EDIT_D_EMP_ROLES assignolddata = new EDIT_D_EMP_ROLES();
			assignolddata.setRoleName(dataval.getRoleName());
			assignolddata.setStartDt(dataval.getStartDt());
			assignolddata.setEndDt(date);
			assignolddata.setAssgnDt(dataval.getAssgnDt());
			assignolddata.setAssignBy(dataval.getAssignBy());
			assignolddata.setRevokedBy((request.getParameter("LoginUser")));
			assignolddata.setCusSite(dataval.getCusSite());
			assignolddata.setMailclasscd(dataval.getMailclasscd());
			assignolddata.setUserid(dataval.getUserid());
			editDemprolerepo.save(assignolddata);
		}
		if ( request.getParameterValues("rolenme")[0].contains("PNA"))
		{
			List<D_EMP_ROLES> getothPNA = demproleNSM.getolddataothPNA(request.getParameter("usrid"));
			for (D_EMP_ROLES dataval : getothPNA) {
				EDIT_D_EMP_ROLES assignolddata = new EDIT_D_EMP_ROLES();
				assignolddata.setId(dataval.getId());
				assignolddata.setRoleName(dataval.getRoleName());
				assignolddata.setStartDt(dataval.getStartDt());
				assignolddata.setEndDt(date);
				assignolddata.setAssgnDt(dataval.getAssgnDt());
				assignolddata.setAssignBy(dataval.getAssignBy());
				assignolddata.setRevokedBy((request.getParameter("LoginUser")));
				assignolddata.setCusSite(dataval.getCusSite());
				assignolddata.setMailclasscd(dataval.getMailclasscd());
				assignolddata.setUserid(dataval.getUserid());
				editDemprolerepo.save(assignolddata);
			}									
			demproleNSM.delothPNA(request.getParameter("usrid"));
		}
		if ( request.getParameterValues("rolenme")[0].contains("PLA"))
		{
			/*List<D_EMP_ROLES> getothPLA = demproleNSM.getolddataothPLA(request.getParameter("usrid"),request.getParameter("cusSite"));
			for (D_EMP_ROLES dataval : getothPLA) {
				assignolddata.setId(dataval.getId());
				assignolddata.setRoleName(dataval.getRoleName());
				assignolddata.setStartDt(dataval.getStartDt());
				assignolddata.setEndDt(date);
				assignolddata.setAssgnDt(dataval.getAssgnDt());
				assignolddata.setAssignBy(dataval.getAssignBy());
				assignolddata.setRevokedBy((request.getParameter("LoginUser")));
				assignolddata.setCusSite(dataval.getCusSite());
				assignolddata.setMailclasscd(dataval.getMailclasscd());
				assignolddata.setUserid(dataval.getUserid());
				editDemprolerepo.save(assignolddata);
			}*/									
			demproleNSM.delothPLA(request.getParameter("usrid"),request.getParameter("cusSite"));
		}
		else
		{demproleNSM.Deleteoldsite(request.getParameter("cusSite"), request.getParameter("usrid"));
		String mailclsAPR = request.getParameterValues("checkedarray")[0];			
		String[] rlnme = request.getParameterValues("rolenme")[0].split(",");
		String usid = request.getParameter("usrid");
		String cs = request.getParameter("cusSite");
		String lgusr = request.getParameter("LoginUser");
		String mailclsSUP = null;
		String mailclsPIN = null;
		if (!(rlnme[0].equals("PNA") || rlnme[0].equals("NAL") || rlnme[0].equals("NRP")))
		{mailclsSUP = request.getParameterValues("mailclsSUP")[0];
		mailclsPIN = request.getParameterValues("mailclsPIN")[0];}
		for (String offcer : rlnme) {
			if (offcer.equals("PAO") || offcer.equals("PSU") ||  offcer.equals("PIN")) {
				if (offcer.equals("PAO")) {
					D_EMP_ROLES editemproles = new D_EMP_ROLES();
					editemproles.setAssgnDt(date);
					editemproles.setStartDt(date);
					editemproles.setMailclasscd(mailclsAPR);
					editemproles.setUserid(usid);
					editemproles.setRoleName(offcer);
					editemproles.setCusSite(cs);
					editemproles.setAssignBy(lgusr);
					demproleNSM.save(editemproles);
				} else if (offcer.equals("PSU")) {
					D_EMP_ROLES editemproleSUP = new D_EMP_ROLES();
					editemproleSUP.setAssgnDt(date);
					editemproleSUP.setStartDt(date);
					editemproleSUP.setMailclasscd(mailclsSUP);
					editemproleSUP.setUserid(usid);
					editemproleSUP.setRoleName(offcer);
					editemproleSUP.setCusSite(cs);
					editemproleSUP.setAssignBy(lgusr);
					demproleNSM.save(editemproleSUP);
				} else if (offcer.equals("PIN")) {
					D_EMP_ROLES editemprolePIN = new D_EMP_ROLES();
					editemprolePIN.setAssgnDt(date);
					editemprolePIN.setStartDt(date);
					editemprolePIN.setMailclasscd(mailclsPIN);
					editemprolePIN.setUserid(usid);
					editemprolePIN.setRoleName(offcer);
					editemprolePIN.setCusSite(cs);
					editemprolePIN.setAssignBy(lgusr);
					demproleNSM.save(editemprolePIN);
				}
				

			} else {
				D_EMP_ROLES editinsertdata = new D_EMP_ROLES();
				editinsertdata.setAssgnDt(date);
				editinsertdata.setStartDt(date);
				editinsertdata.setUserid(usid);
				editinsertdata.setRoleName(offcer);
				editinsertdata.setCusSite(cs);
				editinsertdata.setAssignBy(lgusr);
				demproleNSM.save(editinsertdata);
			}
		}

		}
		return null;

	}
	
	
	public List<NSM_procesdata> getprecedata1(String frmdt, String todt, String getsite) {
		List<NSM_procesdata> val1 = new ArrayList<NSM_procesdata>();
		try {
			
			String querystr = "select CUSCount,Av,duty,stscunt,assAv,assDuty,assAmtchrg,assdutyfg,ooccunt,oocAv,oocDuty,oocAmtchrg,oocdutyfg,detcunt,detAv,detDuty,detAmtchrg,detdutyfg,delvycunt,delvyAv,delvyDuty,delvyAmtchrg,delvydutyfg,undelycunt,undelyAv,undelyDuty,undelyAmtchrg,undelydutyfg  from (select (Select count(*) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as CUSCount,\r\n"
					+ "					(Select sum(tot_ass_val) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as Av, (Select sum(tot_amt_payable) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as duty, \r\n"
					+ "					(Select count(*) from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite) as stscunt,\r\n"
					+ "					(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assAv, \r\n"
					+ "					(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assDuty,\r\n"
					+ "					(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assAmtchrg,\r\n"
					+ "					(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assdutyfg,\r\n"
					+ "					(Select count(*) from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite) as ooccunt,\r\n"
					+ "					(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocAv,\r\n"
					+ "					(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocDuty,\r\n"
					+ "					(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocAmtchrg,\r\n"
					+ "					(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocdutyfg,\r\n"
					+ "					(Select count(*) from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite) as detcunt,\r\n"
					+ "					(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detAv, \r\n"
					+ "					(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detDuty,\r\n"
					+ "					(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detAmtchrg,\r\n"
					+ "					(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detdutyfg, \r\n"
					+ "					(Select count(*) from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite) as delvycunt,\r\n"
					+ "					(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyAv, \r\n"
					+ "					(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyDuty,\r\n"
					+ "					(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyAmtchrg,\r\n"
					+ "					(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvydutyfg, \r\n"
					+ "					(select count(*) from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')as undelycunt,\r\n"
					+ "					(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyAv, \r\n"
					+ "					(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyDuty,\r\n"
					+ "					(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyAmtchrg,\r\n"
					+ "					(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelydutyfg \r\n"
					+ "					from dual)";
	
			Query query = entityManager.createNativeQuery(querystr, NSM_procesdata.class);
			query.setParameter("frmdt", frmdt);
			query.setParameter("todt", todt);
			query.setParameter("getsite", getsite);
			val1 = (List<NSM_procesdata>) query.getResultList();

			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
			
		return val1;
	}



public List<NSM_procesdata> getprecedata(String frmdt, String todt, String getsite) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public String rmveroles (HttpServletRequest request) throws ParseException {
		String cs = request.getParameter("cusSite");
		String userid = request.getParameter("usrid");
		EDIT_D_EMP_ROLES assignolddata = new EDIT_D_EMP_ROLES();
		List<D_EMP_ROLES> getolddt = demproleNSM.getolddata(userid,cs);
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		for (D_EMP_ROLES dataval : getolddt) {
			assignolddata.setId(dataval.getId());
			assignolddata.setRoleName(dataval.getRoleName());
			assignolddata.setStartDt(dataval.getStartDt());
			assignolddata.setEndDt(date);
			assignolddata.setAssgnDt(dataval.getAssgnDt());
			assignolddata.setAssignBy(dataval.getAssignBy());
			assignolddata.setRevokedBy((request.getParameter("LoginUser")));
			assignolddata.setCusSite(dataval.getCusSite());
			assignolddata.setMailclasscd(dataval.getMailclasscd());
			assignolddata.setUserid(dataval.getUserid());
			editDemprolerepo.save(assignolddata);
		}
		demproleNSM.remveroles(request.getParameter("usrid"), cs);			
		return null;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/*public NSM_procesdata getarictleprcesdata(String getsite, String frmdt, String todt) {
		NSM_procesdata val1 = new NSM_procesdata();
		try {
			
			String querystr = "select 1 id,CUSCount,Av,duty,stscunt,assAv,assDuty,assAmtchrg,assdutyfg,ooccunt,oocAv,oocDuty,oocAmtchrg,oocdutyfg,detcunt,detAv,detDuty,detAmtchrg,detdutyfg,delvycunt,delvyAv,delvyDuty,delvyAmtchrg,delvydutyfg,undelycunt,undelyAv,undelyDuty,undelyAmtchrg,undelydutyfg  from (select (Select count(*) from fpo_main where trunc(job_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as CUSCount,\r\n"
					+ "(Select sum(tot_ass_val) from fpo_main where trunc(job_dt) between '"+ frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as Av, (Select sum(tot_amt_payable) from fpo_main where trunc(job_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as duty, \r\n"
					+ "(Select count(*) from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as stscunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as assAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as assDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as assAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as assdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as ooccunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as oocAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as oocDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as oocAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as oocdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as detcunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as detAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as detDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as detAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as detdutyfg, \r\n"
					+ "(Select count(*) from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"') as delvycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as delvyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as delvyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as delvyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' and cus_site= '"+getsite+"')) as delvydutyfg, \r\n"
					+ "(select count(*) from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')as undelycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelydutyfg \r\n"
					+ "from dual)\r\n"
					+ "";
	
			Query query = entityManager.createNativeQuery(querystr, NSM_procesdata.class);
			val1 = (NSM_procesdata) query.getSingleResult();

			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
			
		return val1;
	}*/
	
	public NSM_procesdata getallprcsdata(String frmdt, String todt) {
		NSM_procesdata val1 = new NSM_procesdata();
		try {
			
			String querystr = "select CUSCount,Av,duty,stscunt,assAv,assDuty,assAmtchrg,assdutyfg,ooccunt,oocAv,oocDuty,oocAmtchrg,oocdutyfg,detcunt,detAv,detDuty,detAmtchrg,detdutyfg,delvycunt,delvyAv,delvyDuty,delvyAmtchrg,delvydutyfg,undelycunt,undelyAv,undelyDuty,undelyAmtchrg,undelydutyfg  from (select (Select count(*) from fpo_main where trunc(job_dt) between '"+frmdt+"' and '"+todt+"') as CUSCount,\r\n"
					+ "(Select sum(tot_ass_val) from fpo_main where trunc(job_dt) between '"+frmdt+"' and '"+todt+"') as Av, (Select sum(tot_amt_payable) from fpo_main where trunc(job_dt) between '"+frmdt+"' and '"+todt+"' ) as duty, \r\n"
					+ "(Select count(*) from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' ) as stscunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' )) as assAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' )) as assDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' )) as assAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between '"+frmdt+"' and '"+todt+"' )) as assdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' ) as ooccunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' )) as oocAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' )) as oocDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' )) as oocAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between '"+frmdt+"' and '"+todt+"' )) as oocdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' ) as detcunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' )) as detAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' )) as detDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' )) as detAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between '"+frmdt+"' and '"+todt+"' )) as detdutyfg, \r\n"
					+ "(Select count(*) from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' ) as delvycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' )) as delvyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' )) as delvyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' )) as delvyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between '"+frmdt+"' and '"+todt+"' )) as delvydutyfg, \r\n"
					+ "(select count(*) from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')as undelycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between '"+frmdt+"' and '"+todt+"' and deli_status not in 'DL')) as undelydutyfg \r\n"
					+ "from dual)\r\n"
					+ "";
	
			Query query = entityManager.createNativeQuery(querystr, NSM_procesdata.class);
			val1 = (NSM_procesdata) query.getSingleResult();

			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
			
		return val1;
	}
	
	// Re-allocate Service
	
	/*
	 * public REALLOCATION reallocateservice(REALLOCATION reallocateval, HttpSession
	 * session) throws ParseException { Date currentdate = new Date();
	 * SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String
	 * date1 = time.format(currentdate); Date date = time.parse(date1);
	 * reallocateval.setReallocation_dt(date);
	 * reallocateval.setOff_id(request.getSession().getAttribute("offId").toString());
	 * reallocateval.setRole(request.getSession().getAttribute("role").toString());
	 * reallocateval.setCus_site(request.getSession().getAttribute("LSMcuSite").toString());
	 * reallocaterepo.save(reallocateval); return null; }
	 */
	
	public String updatereallocation(String roles, String frmssid, String tossid, String mailcls) {
		demproleNSM.replaceoffid(roles, frmssid, tossid, mailcls);
		return null;
	}
	
	//
	public void updateDetails(FPO_SITE_INFO sitedetails) {
		
	}
}
