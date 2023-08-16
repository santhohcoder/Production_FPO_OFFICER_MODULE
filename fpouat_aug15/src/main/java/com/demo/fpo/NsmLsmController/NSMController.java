package com.demo.fpo.NsmLsmController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.NsmLsmModel.D_EMP_ROLES;
import com.demo.fpo.NsmLsmModel.EDIT_D_EMP_ROLES;
import com.demo.fpo.NsmLsmModel.EDIT_FPO_SITE_ALLOT;
import com.demo.fpo.NsmLsmModel.FPO_PUSH;
import com.demo.fpo.NsmLsmModel.FPO_SITE;
import com.demo.fpo.NsmLsmModel.FPO_SITE_ALLOT;
import com.demo.fpo.NsmLsmModel.FPO_SITE_INFO;
import com.demo.fpo.NsmLsmModel.FPO_SITE_MGMT;
import com.demo.fpo.NsmLsmModel.NSM_procesdata;
import com.demo.fpo.NsmLsmModel.OTH_STATUS_FPOSITE;
import com.demo.fpo.NsmLsmModel.Pin_Code_Map;
import com.demo.fpo.NsmLsmModel.Pincode_chnage;
import com.demo.fpo.NsmLsmRepo.ACL_ASSVALrepo;
import com.demo.fpo.NsmLsmRepo.D_EMP_ROLErepo_NSM;
import com.demo.fpo.NsmLsmRepo.EDIT_D_EMP_ROLES_repo;
import com.demo.fpo.NsmLsmRepo.EDIT_FPO_ALLOT_REPO;
import com.demo.fpo.NsmLsmRepo.FPO_PUSH_repo;
import com.demo.fpo.NsmLsmRepo.FPO_SITE_ALLOT_REPO;
import com.demo.fpo.NsmLsmRepo.FPO_SITE_INFO_REPO;
import com.demo.fpo.NsmLsmRepo.FPO_SITErepo;
import com.demo.fpo.NsmLsmRepo.FpoSiteMgmtRepo;
import com.demo.fpo.NsmLsmRepo.NsmLsm_D_EMPrepo;
import com.demo.fpo.NsmLsmRepo.OTH_STS_FPOrepo;
import com.demo.fpo.NsmLsmRepo.PLA_DOCrepo;
import com.demo.fpo.NsmLsmRepo.Pincode_Map_CusSiterepo;
import com.demo.fpo.NsmLsmRepo.RELEASE_MGMTrepo;
import com.demo.fpo.NsmLsmRepo.pincode_mapping_repo;
import com.demo.fpo.NsmLsmService.NSMSiteService;
import com.demo.fpo.controller.InputValidateController;
import com.demo.fpo.model.ACL_ASSVAL;
import com.demo.fpo.model.PLA_DOC;
import com.demo.fpo.repos.D_EMP_FPOrepo;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FeedbackRepo;
import com.demo.fpo.repos.OsPathFpoRepo;
import com.demo.fpo.service.FpoDashboardService;
import com.demo.fpo.service.ReportService;
//import com.itextpdf.text.DocumentException;
import com.lowagie.text.DocumentException;


//import jdk.internal.org.jline.utils.Log;

@Controller
public class NSMController {
	
	private static final Logger Log = LogManager.getLogger(NSMController.class);  
	

	
	@Autowired
	PLA_DOCrepo pladocrepo;
	
	
	@Autowired
	InputValidateController inputvalidate;
	
	@Autowired
	OsPathFpoRepo ospathrepo;
	
	@Autowired
	FPO_MAINrepost FPOrepost;

	@Autowired
	NsmLsm_D_EMPrepo DempFpo;
	
	@Autowired
	ACL_ASSVALrepo aclrepo;
	
	@Autowired
	D_EMP_FPOrepo Demprepo;
	
	@Autowired
	EDIT_D_EMP_ROLES_repo DemproleseditFpo;

	@Autowired
	NSMSiteService fpoSiteService;

	@Autowired
	D_EMP_ROLErepo_NSM demproleNSM;

	@Autowired
	FpoSiteMgmtRepo fpositemgmtrepo;
	
	@Autowired
	FPO_SITErepo fpositemainrepo;

	@Autowired
	OTH_STS_FPOrepo othstsfporepo;

	@Autowired
	FPO_SITE_INFO_REPO fpositerepo;

	@Autowired
	FPO_PUSH_repo fpopushrepo;
	
	@Autowired
	Pincode_Map_CusSiterepo pincodemaprepo;
	
	@Autowired
	pincode_mapping_repo pinmaprepo;
	
	@Autowired
	FpoDashboardService fpoDashboardService;

	@Autowired
	FPO_SITE_ALLOT_REPO fpoSiteAllotrepo;
	
	@Autowired
	EDIT_FPO_ALLOT_REPO editfpoallotrepo;
	
	@Autowired
	RELEASE_MGMTrepo releasemgmtrepo;

	@Autowired
	FeedbackRepo feedbcRepo;
	
	@Resource
	private ReportService reportService;

	@RequestMapping("/feedbackstnsm")
	public ModelAndView feedbackstab(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Feedbacknsm");
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		List<String> feedbackval = feedbcRepo.feedbacknsmval();
		List<String> feedbackrplylinkval = feedbcRepo.feedbackrplylnkval();
		model.addAttribute("feedbackval", feedbackval);
		model.addAttribute("feedbackrplylinkval", feedbackrplylinkval);
		return modelAndView;
	}
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public ModelAndView
	 * login(Model models) { ModelAndView modelAndView = new ModelAndView("login");
	 * models.addAttribute("check", "world"); return modelAndView; }
	 */

	 //
	@RequestMapping(value = "/getLoginuserRole")
	public @ResponseBody String getroleNme(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String usersiteCde = request.getParameter("usrID");
		 String getrle = demproleNSM.getrolenm(usersiteCde);
		 System.out.println(getrle);
		return getrle;
	}
	
	// Hemanth-code
//	@RequestMapping(value = "/loginUser", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody String loginUser(@RequestBody D_EMP_ROLES empName, HttpServletRequest request,
//			HttpServletResponse response, Model model, HttpSession session) {
//		try {
//			Date currentdate = new Date();
//			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			System.out.println(time.format(currentdate));
//			String curtime = time.format(currentdate);
//			String userid = empName.getUserid();
//			System.out.println(userid);
//			String pwd = empName.getPwd();
//			String NsmSiteNme = demproleNSM.getNsmsiteNme(userid);
//			String Nsmsitestate = NsmSiteNme.split(",")[1];
//			String Nsmsitecde = NsmSiteNme.split(",")[0];
//			System.out.println(Nsmsitecde);
//			Long fou = demproleNSM.userfou(userid, pwd);
//	
//			 request.getSession().setAttribute("role", demproleNSM.getrolenm(userid));
//			if (fou > 0) {
//				 if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NSA") ) {
//			     request.getSession().setAttribute("resp", "1");
//				 request.getSession().setAttribute("datatime", curtime);
//				 request.getSession().setAttribute("data", userid);
//				 request.getSession().setAttribute("Nsmsitenme", Nsmsitestate);
//				 request.getSession().setAttribute("Nsmsitecde", Nsmsitecde);
//				 request.getSession().setAttribute("offId", userid);
//				 request.getSession().setAttribute("role", demproleNSM.getrolenm(userid));
//				 request.getSession().setAttribute("cuSite", demproleNSM.getcussite(userid));
//				 request.getSession().setAttribute("offName", demproleNSM.getempname(userid));
//				 request.getSession().setAttribute("data1", demproleNSM.getrolenm(userid));
//				 request.getSession().setAttribute("data2", demproleNSM.getcussite(userid));
//				 request.getSession().setAttribute("data3", demproleNSM.getempname(userid));
//			}
//			 }else {
//					
//					 request.getSession().setAttribute("resp", "0");
//				}
//				return request.getSession().getAttribute("resp").toString();
//
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	
	

	@RequestMapping("/nsm_module")
	public ModelAndView nsmModule(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/nsm_module");
		return modelAndView;
	}

	
	@RequestMapping("/maintenance")
	public ModelAndView maintenancePage(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("maintenance");
		return modelAndView;
	}
	
	@RequestMapping("/getdocview") 
	  public ModelAndView currentlsmview(Model model, HttpServletRequest request) { 
		 ModelAndView modelAndView = new ModelAndView("admin/nsm/listofcurtLsm"); 
		  String offid = request.getParameter("offid").toString();
		  String docfile = pladocrepo.findByssoid(offid);	  
	  model.addAttribute("docfile", docfile); 
	  return modelAndView; 
	  }

	
	
	
	@RequestMapping("/nsmHome")
	public ModelAndView nsmhme(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/home");
		return modelAndView;
	}

	@RequestMapping("/usermanagement")
	public ModelAndView usermanage(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/usermanagement");
		return modelAndView;
	}

	@RequestMapping("/current_nsm")
	public ModelAndView currentnsm(Model model, HttpServletRequest request) {
		List<Collection> Nsmdata = fpositemgmtrepo.getNsmdata();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/listofcurtNsm");
		model.addAttribute("Nsmdata", Nsmdata);
		List<Collection> Nsmdatapast = fpositemgmtrepo.getNsmdatapast();
		model.addAttribute("Nsmdatapast", Nsmdatapast);
		return modelAndView;
	}

	@RequestMapping("/submissionofdash")
	public ModelAndView dashsub(Model model, HttpServletRequest request) {
		List<Collection> stcd = demproleNSM.getsitecde();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/SubmisofDash");
		model.addAttribute("stcd", stcd);
		return modelAndView;
	}

	@RequestMapping("/siteAddActiveView")
	public ModelAndView addActiveView(Model model,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/siteMangement");
		List<String> statelist = fpositemgmtrepo.getaddsites();
		List<String> fpolistsite = fpositemgmtrepo.getsitenames();
		model.addAttribute("fpolistsite", fpolistsite);
		String loginid = request.getSession().getAttribute("offId").toString();
		List<String> siteCodeList = fpositemgmtrepo.getstates();
		model.addAttribute("loginid", loginid);
		model.addAttribute("siteCodeList", siteCodeList);
		model.addAttribute("statelist", statelist);
		return modelAndView;
	
		
	}

	@RequestMapping("/current_lsm")
	public ModelAndView currentlsm(Model model, HttpServletRequest request) {
		List<Collection> Lsmdata = fpositemgmtrepo.getLsmdata();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/listofcurtLsm");
		model.addAttribute("Lsmdata", Lsmdata);
		return modelAndView;
	}

	@RequestMapping("/nsmreport")
	public ModelAndView reports(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/nsm_reports");
		return modelAndView;
	}

	@RequestMapping("/miscellaneous")
	public ModelAndView miscellaneous(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Miscellaneous");
		return modelAndView;
	}

	// LSM-Hstry
	@RequestMapping("/lsmhstry")
	public ModelAndView LsmHstry(Model model, HttpServletRequest request) {
		List<String> getLsmsiteHstry = fpositemgmtrepo.getLsmsiteHstry();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/LSMHstry");
		model.addAttribute("getLsmsiteHstry", getLsmsiteHstry);
		return modelAndView;
	}

	@RequestMapping("/chngethreshold")
	public ModelAndView changevalue(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Mise_changethreshold");
	//	String thresholdval = fpositemgmtrepo.getthresholdval(); // commented for threshold changes
	//	String getassvaldate = fpositemgmtrepo.getassvaldate();  // commented for threshold changes
		String thresholdval = aclrepo.getthresholdval(); // changed for thresholdvalue
		String getassvaldate = aclrepo.getassvaldate(); // changed for thresholdvalue
		
		String checkThreshold = "0";
		
		checkThreshold =  reportService.getdataFromaclassval();
		if(checkThreshold.equals("1")) {
			model.addAttribute("checkThreshold", checkThreshold);
		}
		else {
			model.addAttribute("checkThreshold", checkThreshold);
		}
		
		model.addAttribute("checkThreshold", checkThreshold);
		
		List<String> getallthresoldval = aclrepo.getthresoldvalall(); // changed for thresholdvalue
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("thresholdval", thresholdval);
		model.addAttribute("loginid", loginid);
		model.addAttribute("getassvaldate", getassvaldate);
		model.addAttribute("getallthresoldval", getallthresoldval); // changed for thresholdvalue
		return modelAndView;
	}

//	@RequestMapping("/thresholdvaluehistory")
//	 // add this annotation to return JSON response
//	public @ResponseBody List<Collection> thresholdvaluehistory( HttpServletRequest request, HttpServletResponse response) {
//	    String thresholdval = aclrepo.getthresholdval();
//	  
//	    List<String> getallthresoldval = aclrepo.getthresoldvalall();
//	    List<Collection> getallthresoldvalue = aclrepo.getthresoldvalueall();
//	    
//	    System.out.println("total values are:"+getallthresoldval);
//	    return getallthresoldvalue; // return the list as JSON
//	}

	@RequestMapping("/thresholdvaluehistory")
	public ModelAndView thresholdvaluehistory(Model model, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("admin/nsm/thresholdvaluehistory");
		String from_date = request.getParameter("fromdate");
		List<String> getallthresoldval = aclrepo.getthresoldvalall();
		
		String zfilename = aclrepo.getpdf(from_date);
		model.addAttribute("zfilename", zfilename);
		model.addAttribute("getallthresoldval", getallthresoldval);
		return models;
	}
	
	// allocate NSM
	@RequestMapping("/allocate_NSM")
	public ModelAndView allocateNSM(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/allocateNSM");
		return modelAndView;
	}

	// Remove Lsm
	@RequestMapping("/remove_NSM")
	public ModelAndView removeNSM(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/removeNSM");
		return modelAndView;
	}

	// allocate LSM
	@RequestMapping("/allocate_LSM")
	public ModelAndView allocateLSM(Model model, HttpServletRequest request, HttpSession session) {
		List<String> listsite = fpositemgmtrepo.getLsmSitenme();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/allocateLSM");
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		model.addAttribute("listsite", listsite);
		return modelAndView;
	}
	
	@RequestMapping("/edit_LSM")
	public ModelAndView editLSM(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/editLSM");
		List<String> listsite = fpositemgmtrepo.sitecdnm();
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		model.addAttribute("listsite", listsite);
		List<String> removelsm = fpositemgmtrepo.sitecdnm();
		model.addAttribute("loginid", loginid);
		return modelAndView;
	}
	
	
	@RequestMapping("/view_LSM")
	public ModelAndView viewLSM(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewLSM");
		List<String> listsite = fpositemgmtrepo.sitecdnm();
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		model.addAttribute("listsite", listsite);
		List<String> removelsm = fpositemgmtrepo.sitecdnm();
		model.addAttribute("loginid", loginid);
		return modelAndView;
	}

	@RequestMapping("/remove_LSM")
	public ModelAndView removeLsm(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/RemoveLsm");
		List<String> removelsm = fpositemgmtrepo.sitecdnm();
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		model.addAttribute("removelsm", removelsm);
		return modelAndView;
	}

	@RequestMapping("/removeLsmdata")
	public @ResponseBody List<Collection> removeLsmdata(Model model, HttpServletRequest request) {
		String getcuSite = request.getParameter("cusSite");
		List<Collection> removelsm = fpositemgmtrepo.removelsmdata(getcuSite);
		return removelsm;
	}

	@RequestMapping(value = "/deletelsm", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody String deleteLsm(@RequestBody String remarks, Model model, HttpServletRequest request, HttpSession session)
			throws ParseException {
		String ssoid = request.getParameter("ssoid");
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		String usrid = request.getSession().getAttribute("offId").toString();
		List<D_EMP_ROLES> demp = demproleNSM.getalldata(ssoid,"PLA");
		inseditroles(demp.get(0),"D");
		fpositemgmtrepo.deletelsmsite(ssoid, date, usrid, remarks);
		return remarks;
	}

	public void inseditroles(D_EMP_ROLES demp,String status)
	{		
		EDIT_D_EMP_ROLES editdemp = new EDIT_D_EMP_ROLES();
		editdemp.setUserid(demp.getUserid());
		editdemp.setRoleName(demp.getRoleName());
		editdemp.setAssignBy(demp.getAssignBy());
		editdemp.setAssgnDt(demp.getAssgnDt());
		editdemp.setCusSite(demp.getCusSite());
	    editdemp.setDesign(demp.getDesign());
		editdemp.setStartDt(demp.getStartDt());
		editdemp.setEndDt(demp.getEndDt());
		editdemp.setRevokedBy(demp.getRevokedBy());
		editdemp.setReason(demp.getReason());
		editdemp.setMobileno(demp.getMobileno());
		editdemp.setEmail(demp.getEmail());
		editdemp.setStatus(status);
		DemproleseditFpo.save(editdemp);
	}
	
	
	@RequestMapping("/viewdirectory")
	public ModelAndView viewdirectory(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/ViewDirectory");
		return modelAndView;
	}

//	@RequestMapping("/fpositeadd")
//	public ModelAndView addfposite(Model model, HttpServletRequest request, HttpSession session) {
//		ModelAndView modelAndView = new ModelAndView("admin/nsm/fpositeadd");
//		List<String> fpolistsite = fpositemgmtrepo.getsitenames();
//		model.addAttribute("fpolistsite", fpolistsite);
//		String loginid = request.getSession().getAttribute("offId").toString();
//		List<String> siteCodeList = fpositemgmtrepo.getstates();
//		model.addAttribute("loginid", loginid);
//		model.addAttribute("siteCodeList", siteCodeList);
////		List<Collection> siteNameSiteCode= fpositemgmtrepo.getSiteNameSiteCode();
////		model.addAttribute("siteNameSiteCode", siteNameSiteCode);
//		return modelAndView;
//	}

	
	
	
	
	@RequestMapping("/viewusers")
	public ModelAndView viewusers(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/ViewActusers&UserHstry");
		return modelAndView;

	}

	@RequestMapping("/viewusersHstry")
	public ModelAndView viewUsersHstry(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewUserHstry");
		List<Collection> siteList = fpoSiteService.getactiveSite();
		List<Collection> activelst = fpositemgmtrepo.getactiveusers();
		model.addAttribute("siteList", siteList);
		model.addAttribute("activelst", activelst);
		return modelAndView;

	}

	@RequestMapping("/viewActiveUsers")
	public ModelAndView viewActiveUsers(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewUsersfpo");
		List<Collection> siteList = fpoSiteService.getactiveSite();
	//	List<Collection> getlistOfUser = demproleNSM.getrevokedData(request.getSession().getAttribute("LSMcuSite").toString());
		 List<Collection> activelstlsm = fpositemgmtrepo.getactiveusers();
	//	model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
	//	model.addAttribute("getlistOfUser", getlistOfUser);
		 model.addAttribute("activelstlsm", activelstlsm);
		model.addAttribute("siteList", siteList);
		return modelAndView;

	}
	
	
	@RequestMapping("/rolesheld")
	public ModelAndView rolesheld(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/rolesheld");
		List<Collection> siteList = fpoSiteService.getactiveSite();
	//	List<Collection> getlistOfUser = demproleNSM.getrevokedData(request.getSession().getAttribute("LSMcuSite").toString());
		 List<Collection> activelstlsm = fpositemgmtrepo.getactiveusers();
	//	model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
	//	model.addAttribute("getlistOfUser", getlistOfUser);
		 model.addAttribute("activelstlsm", activelstlsm);
		model.addAttribute("siteList", siteList);
		return modelAndView;

	}

	@RequestMapping("/viewfposite")
	public ModelAndView viewfposite(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewfposite");
		List<Collection> siteList = fpositemgmtrepo.viewallsite();
		List<String> getsiteName = fpositemgmtrepo.getsitename();
		model.addAttribute("fpositeList", siteList);
		model.addAttribute("getsiteName", getsiteName);
		return modelAndView;
	}

//	@RequestMapping("/activatesiteView")
//	public ModelAndView activatesiteView(Model model, HttpServletRequest request) {
//		List<String> statelist = fpositemgmtrepo.getaddsites();
//		ModelAndView modelAndView = new ModelAndView("admin/nsm/fpoactivesite");
//		model.addAttribute("statelist", statelist);
//		return modelAndView;
//	}

	@RequestMapping(value = "/getfpositeadd", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	@ResponseBody
	public FPO_SITE_MGMT addsitemgmt(@RequestBody FPO_SITE_MGMT fpositemgmt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		/* String emplyName = fpositemgmtrepo.getoffcierName(request.getSession().getAttribute("offId").toString()); */
		
		
		String sitename = fpositemgmt.getSiteName();
		if (sitename != null) {
		if(inputvalidate.ValidaString(sitename) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitename.length() > 35 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		String mapcode = fpositemgmt.getMapcode();
		if (mapcode != null) {
		if(inputvalidate.ValidaString(mapcode) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (mapcode.length() > 6 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		String sitecode = fpositemgmt.getSiteCode();
		if (sitecode != null) {
		if(inputvalidate.ValidaString(sitecode) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitecode.length() > 6 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		String sitetype = fpositemgmt.getSiteType();
		if (sitetype != null) {
		if(inputvalidate.ValidaString(sitetype) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitetype.length() > 10 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		String sitestate= fpositemgmt.getSiteState();
		if (sitestate != null) {
		if(inputvalidate.ValidaString(sitestate) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitestate.length() > 35 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		if(inputvalidate.ValidaString(fpositemgmt.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getReason().length() == 0 || fpositemgmt.getReason().isEmpty() || fpositemgmt.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		
		
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		FPO_SITE newsite = new FPO_SITE();		
		newsite.setSiteCode(fpositemgmt.getSiteCode());
		newsite.setSiteName(fpositemgmt.getSiteName());
		newsite.setClustered(fpositemgmt.getClustered());
		newsite.setClussite(fpositemgmt.getClussite());
		newsite.setMapcode(fpositemgmt.getMapcode());
		newsite.setSiteType(fpositemgmt.getSiteType());
		newsite.setSiteState(fpositemgmt.getSiteState());
		newsite.setCrtdt(date);
		newsite.setCrtby(request.getSession().getAttribute("offId").toString());
		fpositemainrepo.save(newsite);
		OTH_STATUS_FPOSITE othstesave = new OTH_STATUS_FPOSITE();
		othstesave.setSiteCode(fpositemgmt.getSiteCode());
		othstesave.setSitename(fpositemgmt.getSiteName());
		othstesave.setSitests("N");
		/* othstesave.setOffName(emplyName); */
		othstesave.setReason(fpositemgmt.getReason());
		othstesave.setOffId(request.getSession().getAttribute("offId").toString());
		othstesave.setCrtdt(date);
		othstsfporepo.save(othstesave);
		String fpositestate = fpositemgmt.getSiteState();
		fpositemgmt.setOffid(request.getSession().getAttribute("offId").toString());
		/* fpositemgmt.setOffName(emplyName); */
		fpositemgmt.setSitests("N");
		fpositemgmt.setCreateddate(date);
		String fpoSs = fpositemgmtrepo.siteState(fpositestate);
		fpositemgmt.setSiteStateCd(fpoSs);
		return fpositemgmtrepo.save(fpositemgmt);
	}
	
	
	

	@RequestMapping("/viewactiveList")
	public ModelAndView editSiteList(Model model, HttpServletRequest request, HttpSession session) {
		String offId = request.getSession().getAttribute("offId").toString();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewEditFpoSite");
		List<Collection> activeList = fpositemgmtrepo.viewactivesite();
		model.addAttribute("activeList", activeList);
		model.addAttribute("offId", offId);
		return modelAndView;
	}

	@RequestMapping(value = "/editfpositesave", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SITE_MGMT editsitemgmt(@RequestBody FPO_SITE_MGMT fpositemgmt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {		
		if(inputvalidate.ValidaString(fpositemgmt.getSiteName()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getSiteName().length() == 0 || fpositemgmt.getSiteName().isEmpty() || fpositemgmt.getSiteName().length() > 35 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		if(inputvalidate.ValidaString(fpositemgmt.getSiteCode()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getSiteCode().length() == 0 || fpositemgmt.getSiteCode().isEmpty() || fpositemgmt.getSiteCode().length() > 6 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		String sitemap= fpositemgmt.getMapcode();
		if(sitemap != null) {
		if(inputvalidate.ValidaString(fpositemgmt.getMapcode()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getMapcode().length() > 6 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		String sitestate = fpositemgmt.getSiteState();
		if(sitestate != null) {
		if(inputvalidate.ValidaString(fpositemgmt.getSiteState()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getSiteState().length() == 0 || fpositemgmt.getSiteState().isEmpty() || fpositemgmt.getSiteState().length() > 35 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		if(inputvalidate.ValidaString(fpositemgmt.getSiteType()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getSiteType().length() == 0 || fpositemgmt.getSiteType().isEmpty() || fpositemgmt.getSiteType().length() > 10 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		if(inputvalidate.ValidaString(fpositemgmt.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getReason().length() == 0 || fpositemgmt.getReason().isEmpty() || fpositemgmt.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		String clusite= fpositemgmt.getSiteStateCd();
		if(clusite != null) {
		if(inputvalidate.ValidaString(fpositemgmt.getClussite()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (fpositemgmt.getClussite().length() > 6 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		}
		
		String sitestatecd= fpositemgmt.getSiteStateCd();
		if(sitestatecd != null) {
		if(inputvalidate.ValidaString(fpositemgmt.getSiteStateCd()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		String sitests= fpositemgmt.getSitests();
		if(sitests != null) {
		if(inputvalidate.ValidaString(fpositemgmt.getSitests()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		fpoSiteService.fpositeedit(fpositemgmt,request,session);
		return fpositemgmt;
	}

// Edit/delete fpo site
//	@RequestMapping("/editSite/{siteCode}/{siteName}/{siteState}/{siteType}/{clustered}/{clussite}")
//	@RequestMapping("/editSite")
//	public ModelAndView multiplePathVariable(@RequestBody FPO_SITE_MGMT editsite, Model model, HttpSession session, HttpServletRequest request) {
//		String officerid = request.getSession().getAttribute("offId").toString();
//		System.out.println("officerid is "+ officerid);
//		String siteCode = editsite.getSiteCode();
//		String siteName = editsite.getSiteName();
//		System.out.println("code is "+siteCode+" and sitename is "+siteName);
//		List<String> listsite = fpositemgmtrepo.getsitenamestoalter(siteCode);
//		List<String> liststate = fpositemgmtrepo.getstates();
//		ModelAndView modelAndView = new ModelAndView("admin/nsm/editSite");
//		model.addAttribute("listsite", listsite);
//		model.addAttribute("liststate", liststate);
//		model.addAttribute("siteCode", siteCode);
//		model.addAttribute("siteName", siteName);
//		model.addAttribute("officerid", officerid);
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/editSite", method = RequestMethod.GET)
	public ModelAndView editSite(@RequestParam("siteCode") String siteCode, @RequestParam("siteName") String siteName, Model model, HttpSession session, HttpServletRequest request) {
	    String officerid = request.getSession().getAttribute("offId").toString();
	    System.out.println("officerid is " + officerid);
	    System.out.println("code is " + siteCode + " and sitename is " + siteName);
	    List<String> listsite = fpositemgmtrepo.getsitenamestoalter(siteCode);
	    List<String> liststate = fpositemgmtrepo.getstates();
	    ModelAndView modelAndView = new ModelAndView("admin/nsm/editSite");
	    model.addAttribute("listsite", listsite);
	    model.addAttribute("liststate", liststate);
	    model.addAttribute("siteCode", siteCode);
	    model.addAttribute("siteName", siteName);
	    model.addAttribute("officerid", officerid);
	    return modelAndView;
	}

	// View Active users of Fpo Site
/*	@RequestMapping(value = "/userSite", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ModelAndView viewuserSite(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewactiveusers");
		String siteCode = request.getParameter("siteCode");
		System.out.println(siteCode);
		log.info("siteCode is " + siteCode);
		List<Collection> idndusrnme = fpositemgmtrepo.getidandname(siteCode);
		String getcount = fpositemgmtrepo.getcountofusers(siteCode);
		model.addAttribute("idndusrnme", idndusrnme);
		log.info("idndusrnme length = " + idndusrnme.size());
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("getcount", getcount);
		log.info("getcount = " + getcount);
		return modelAndView;
	}*/
	
	
	@RequestMapping(value = "/userSite", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ModelAndView viewuserSite(HttpServletRequest request, Model model) {
		
		ModelAndView modelAndView = new ModelAndView("admin/nsm/viewactiveusers");
		String siteCode = request.getParameter("siteCode");
		String siteName = request.getParameter("siteName");
		System.out.println(siteCode);
		System.out.println(siteName);
		Log.info("siteCode is " + siteCode);
		List<Collection> idndusrnme = fpositemgmtrepo.getidandname(siteCode);
		String getcount = fpositemgmtrepo.getcountofusers(siteCode);
		model.addAttribute("idndusrnme", idndusrnme);
		System.out.println(idndusrnme);
		Log.info("idndusrnme length = " + idndusrnme.size());
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("siteName", siteName);
		model.addAttribute("getcount", getcount);
		Log.info("getcount = " + getcount);
		return modelAndView;
	}
	

	@RequestMapping(value = "/fpositeactivate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SITE_MGMT siteactive(@RequestBody FPO_SITE_MGMT activesite, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		
		
		if(inputvalidate.ValidaString(activesite.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (activesite.getReason().length() == 0 || activesite.getReason().isEmpty() || activesite.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		OTH_STATUS_FPOSITE othsts = new OTH_STATUS_FPOSITE();
		othsts.setSiteCode(activesite.getSiteCode());
		othsts.setSitename(activesite.getSiteName());
		othsts.setReason(activesite.getReason());
		othsts.setOffId(request.getSession().getAttribute("offId").toString());
		othsts.setSitests("Y");
		othsts.setCrtdt(date);
		othstsfporepo.save(othsts);
		fpositemgmtrepo.activatesite(activesite.getSiteCode(), activesite.getReason(), date,request.getSession().getAttribute("offId").toString());
		fpositemgmtrepo.activatefposite(activesite.getSiteCode(),date,request.getSession().getAttribute("offId").toString());
		FPO_SITE_MGMT fpoSites = new FPO_SITE_MGMT();
		fpoSites.setSitests("Y");
		return fpoSites;

	}

	@RequestMapping(value = "getCiteCode", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	@ResponseBody
	public List<String> search(HttpServletRequest request, HttpServletResponse response) {
		return fpositemgmtrepo.getdistrcit(request.getParameter("term"));
	}

//	@RequestMapping(value = "/getAssmaxval", produces = {
//			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody FPO_SITE_MGMT getAssmaxval(@RequestBody String value, HttpServletRequest request,
//			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
//		String threshldresp = value.replace("\"", "");
//		String assmaxamut = request.getParameter("assmaxamt");
//		String crtby = request.getSession().getAttribute("offId").toString();
//		Date currentdate = new Date();
//		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date1 = time.format(currentdate);
//		Date date = time.parse(date1);
//		FPO_SITE_MGMT assdate = new FPO_SITE_MGMT();
//		assdate.setReason(date1);
//		fpositemgmtrepo.updateassmaxval(assmaxamut, crtby, date, threshldresp);
//		return assdate;
//	}
	
	/*
	 * @RequestMapping(value = "/getAssmaxval") public @ResponseBody FPO_SITE_MGMT
	 * getAssmaxval(@ModelAttribute ACL_ASSVAL assmaxval, HttpServletRequest
	 * request, HttpServletResponse response, Model model, HttpSession session)
	 * throws ParseException, IllegalStateException, IOException, DocumentException
	 * { ACL_ASSVAL allmaxass = new ACL_ASSVAL(); // to retrive data without ""
	 * String threshldresp = request.getParameter("threshldresp").replace("\"","");
	 * System.out.println(threshldresp); String assmaxamt =
	 * request.getParameter("assmaxamt"); System.out.println(assmaxamt);
	 * 
	 * String crtby = request.getSession().getAttribute("offId").toString(); Date
	 * currentdate = new Date(); SimpleDateFormat time = new
	 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String date1 =
	 * time.format(currentdate); System.out.println(date1); Date date =
	 * time.parse(date1); Date sysdt = new Date();
	 * 
	 * 
	 * 
	 * SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String
	 * date2 = time1.format(sysdt);
	 * 
	 * String date3=date2.replace("/", ""); String date4=date3.replace(":", "");
	 * String date6=date4.replace(" ", ""); System.out.println(date6); Date date5 =
	 * time.parse(date1); FPO_SITE_MGMT assdate = new FPO_SITE_MGMT(); String
	 * docname = request.getParameter("docname").replace(".pdf","") + date6
	 * +"thv.pdf"; System.out.println(docname); assdate.setReason(date1); String
	 * qryreplypath = ospathrepo.getothPath(); File destination = new
	 * File(qryreplypath + docname);
	 * assmaxval.getFilename().transferTo(destination);
	 * System.out.println(destination); System.out.println(destination);
	 * allmaxass.setMaxassval(Integer.parseInt(assmaxamt));
	 * allmaxass.setCreatedby(crtby); allmaxass.setReason(threshldresp);
	 * allmaxass.setFromdt(date); allmaxass.setDocname(docname);
	 * allmaxass.setDOC_PATH(destination.getPath()); allmaxass.getDocname();
	 * aclrepo.updatetodate(); aclrepo.save(allmaxass); return assdate; }
	 */
	
//	@RequestMapping(value = "/getAssmaxval")
//	public @ResponseBody FPO_SITE_MGMT getAssmaxval(@ModelAttribute ACL_ASSVAL assmaxval, HttpServletRequest request,
//		HttpServletResponse response, Model model, HttpSession session) throws ParseException, IllegalStateException, IOException, DocumentException {
//		ACL_ASSVAL allmaxass = new ACL_ASSVAL();
//		// to retrive data without "" 
//		String threshldresp = request.getParameter("threshldresp").replace("\"","");
//		System.out.println(threshldresp);
//		String assmaxamt = request.getParameter("assmaxamt");
//		System.out.println(assmaxamt);
//		
//		String crtby = request.getSession().getAttribute("offId").toString();
//		Date currentdate = new Date();
//		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date1 = time.format(currentdate);
//		System.out.println(date1); 
//		Date date = time.parse(date1);
//		Date sysdt = new Date();
//		
//		
//		
//		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date2 = time1.format(sysdt);
//		
//		String date3=date2.replace("/", "");
//		String date4=date3.replace(":", "");
//		String date6=date4.replace(" ", "");
//		System.out.println(date6); 
//		Date date5 = time.parse(date1);
//		FPO_SITE_MGMT assdate = new FPO_SITE_MGMT();
//		String docname = request.getParameter("docname").replace(".pdf","") + date6 +"thv.pdf";
//		String docname1 = request.getParameter("docname");
//		System.out.println(docname);
//		assdate.setReason(date1);
//		String qryreplypath = ospathrepo.getothPath();
//		File destination = new File(qryreplypath + docname);
//		if(docname1 != "") {
//			assmaxval.getFilename().transferTo(destination);
//			allmaxass.setDocname(docname);
//			allmaxass.setDOC_PATH(destination.getPath());
//		}
//		
//		System.out.println(destination);
//		System.out.println(destination);
//		allmaxass.setMaxassval(Integer.parseInt(assmaxamt));
//		allmaxass.setCreatedby(crtby);
//		allmaxass.setReason(threshldresp);
//		allmaxass.setFromdt(date);
//		//allmaxass.setDocname(docname);
//		//allmaxass.setDOC_PATH(destination.getPath());
//		allmaxass.getDocname();
//		aclrepo.updatetodate();
//		aclrepo.save(allmaxass); 	
//		return assdate;
//	}
	
	
	
	@RequestMapping(value = "/getAssmaxval")
	public @ResponseBody FPO_SITE_MGMT getAssmaxval(@ModelAttribute ACL_ASSVAL assmaxval, HttpServletRequest request,
		HttpServletResponse response, Model model, HttpSession session) throws ParseException, IllegalStateException, IOException, DocumentException {
		ACL_ASSVAL allmaxass = new ACL_ASSVAL();
		// to retrive data without "" 
		String threshldresp = assmaxval.getReason().replace("\"","");
		System.out.println(threshldresp);
		int assmaxamt = assmaxval.getMaxassval();
		System.out.println(assmaxamt);
		
		String crtby = request.getSession().getAttribute("offId").toString();
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		System.out.println(date1); 
		Date date = time.parse(date1);
		Date sysdt = new Date();
		
		
		
		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date2 = time1.format(sysdt);
		
		String date3=date2.replace("/", "");
		String date4=date3.replace(":", "");
		String date6=date4.replace(" ", "");
		System.out.println(date6); 
		Date date5 = time.parse(date1);
		FPO_SITE_MGMT assdate = new FPO_SITE_MGMT();
		String docname = assmaxval.getDocname().replace(".pdf","") + date6 +"thv.pdf";
		String docname1 = request.getParameter("docname2");
		System.out.println(docname);
		assdate.setReason(date1);
		String qryreplypath = ospathrepo.getothPath();
		File destination = new File(qryreplypath + docname);
		if(Integer.parseInt(docname1) != 1) {
			assmaxval.getFilename().transferTo(destination);
			allmaxass.setDocname(docname);
			allmaxass.setDOC_PATH(destination.getPath());
		}
		
		System.out.println(destination);
		System.out.println(destination);
		allmaxass.setMaxassval(assmaxamt);
		allmaxass.setCreatedby(crtby);
		allmaxass.setReason(threshldresp);
		allmaxass.setFromdt(date);
		//allmaxass.setDocname(docname);
		//allmaxass.setDOC_PATH(destination.getPath());
		allmaxass.getDocname();
		aclrepo.updatetodate();
		aclrepo.save(allmaxass); 	
		return assdate;
	}



	@RequestMapping(value = "/blockfposite", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SITE_MGMT blckFpoSite(@RequestBody FPO_SITE_MGMT blkesite, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		

		if(inputvalidate.ValidaString(blkesite.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (blkesite.getReason().length() == 0 || blkesite.getReason().isEmpty() || blkesite.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }

		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		OTH_STATUS_FPOSITE othsts = new OTH_STATUS_FPOSITE();
		othsts.setSiteCode(blkesite.getSiteCode());
		othsts.setSitename(blkesite.getSiteName());
		othsts.setOffId(request.getSession().getAttribute("offId").toString());
		othsts.setSitests("B");
		othsts.setReason(blkesite.getReason());
		othsts.setCrtdt(date);
		othstsfporepo.save(othsts);
		fpositemgmtrepo.blcfposite(blkesite.getSiteCode(), blkesite.getReason());
		FPO_SITE_MGMT fpoSites = new FPO_SITE_MGMT();
		fpoSites.setSitests("blocked");
		return blkesite;
	}

	@RequestMapping(value = "/unblockfposite", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SITE_MGMT unblckFpoSite(@RequestBody FPO_SITE_MGMT unblksite, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		
		if(inputvalidate.ValidaString(unblksite.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (unblksite.getReason().length() == 0 || unblksite.getReason().isEmpty() || unblksite.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }

		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		OTH_STATUS_FPOSITE othsts = new OTH_STATUS_FPOSITE();
		othsts.setSiteCode(unblksite.getSiteCode());
		othsts.setSitename(unblksite.getSiteName());
		othsts.setOffId(request.getSession().getAttribute("offId").toString());
		othsts.setSitests("U");
		othsts.setReason(unblksite.getReason());
		othsts.setCrtdt(date);
		othstsfporepo.save(othsts);
		fpositemgmtrepo.unBlcFpoSite(unblksite.getSiteCode(), unblksite.getReason());
		FPO_SITE_MGMT fpoSites = new FPO_SITE_MGMT();
		fpoSites.setSitests("unblocked");
		return unblksite;
	}

	@RequestMapping(value = "/deletefposite", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SITE_MGMT deleteFpoSite(@RequestBody FPO_SITE_MGMT deletesite, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		
		if(inputvalidate.ValidaString(deletesite.getReason()) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (deletesite.getReason().length() == 0 || deletesite.getReason().isEmpty() || deletesite.getReason().length() > 256 ) {	
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
		       }
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		OTH_STATUS_FPOSITE othsts = new OTH_STATUS_FPOSITE();
		othsts.setSiteCode(deletesite.getSiteCode());
		othsts.setSitename(deletesite.getSiteName());
		othsts.setOffId(request.getSession().getAttribute("offId").toString());
		othsts.setSitests("D");
		othsts.setReason(deletesite.getReason());
		othsts.setCrtdt(date);
		othstsfporepo.save(othsts);
		fpositemgmtrepo.delfposite(deletesite.getSiteCode(), deletesite.getReason());
		//fpositemainrepo.delfposite(deletesite.getSiteCode());
		fpositemgmtrepo.sitedelete(deletesite.getSiteCode());
		FPO_SITE_MGMT fpoSites = new FPO_SITE_MGMT();
		fpoSites.setSitests("deleted");
		return fpoSites;
	}
	//------------------------------------
	// commented for changing as JSON to  form in html for file upload
	
	// Add Lsm 
//	@RequestMapping(value = "/addLsm", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody D_EMP_ROLES addLsm(@RequestBody D_EMP_ROLES addlsmsite, HttpServletRequest request,
//			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
//		Date currentdate = new Date();
//		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date1 = time.format(currentdate);
//		String reason=request.getParameter("reason");
//		Date date = time.parse(date1);
//		addlsmsite.setCusSite(addlsmsite.getCusSite());
//		addlsmsite.setDesign(addlsmsite.getDesign());
//		System.out.println(addlsmsite.getCusSite());
//		System.out.println(addlsmsite.getEmpName());
//		addlsmsite.setAssignBy(request.getSession().getAttribute("offId").toString());
//		addlsmsite.setStartDt(date);
//		addlsmsite.setAssgnDt(date);
//		addlsmsite.setMobileno(addlsmsite.getMobileno());
//		addlsmsite.setEmail(addlsmsite.getEmail());
//		addlsmsite.setReason(reason);
//		addlsmsite.setRoleName("PLA");
//		demproleNSM.save(addlsmsite);
//
//		return addlsmsite;
//
//	}
	
	//-------------------------------------------
	@RequestMapping(value = "/addLsm")
	public @ResponseBody void addLsm(@ModelAttribute D_EMP_ROLES addlsmsite, HttpServletRequest request,  			
	HttpServletResponse response, Model model, HttpSession session)
	throws Exception {
		try {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		String reason=request.getParameter("reason");
		if(addlsmsite.getCusSite() != null) {
			if(inputvalidate.ValidaString(addlsmsite.getCusSite()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(addlsmsite.getDocname() != null) {
			if(inputvalidate.ValidaString(addlsmsite.getDocname()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(addlsmsite.getUserid() != null) {
			if(inputvalidate.ValidaString(addlsmsite.getUserid()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(reason != null) {
			if(inputvalidate.ValidaString(reason) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(addlsmsite.getMobileno() != null && !addlsmsite.getMobileno().isEmpty()) {
			if(inputvalidate.Validanum(addlsmsite.getMobileno()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(addlsmsite.getEmail() != null && !addlsmsite.getEmail().isEmpty()) {
			if(inputvalidate.ValidateEmail(addlsmsite.getEmail()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}

			if(addlsmsite.getFilename() != null) {
			if(inputvalidate.validaFile(addlsmsite.getFilename()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}
			if(addlsmsite.getDesign() != null) {
			if(inputvalidate.ValidaString(addlsmsite.getDesign()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return;
					}
			}

		Date date = time.parse(date1);
		fpoSiteService.editrole(request);
	    addlsmsite.setCusSite(request.getParameter("cusSite"));
		  addlsmsite.setDesign(addlsmsite.getDesign());
		  System.out.println(addlsmsite.getCusSite());
		  System.out.println(addlsmsite.getEmpName());
		  addlsmsite.setAssignBy(request.getSession().getAttribute("offId").toString());
		  addlsmsite.setStartDt(date); 
		  addlsmsite.setAssgnDt(date);
		  addlsmsite.setMobileno(addlsmsite.getMobileno());
		  addlsmsite.setEmail(addlsmsite.getEmail()); 
		  addlsmsite.setReason(reason);
		  addlsmsite.setRoleName("PLA"); 
		  demproleNSM.save(addlsmsite);
		 				
		String qryreplypath = ospathrepo.getothPath();
		System.out.println(qryreplypath);
		File destination = new File(qryreplypath + addlsmsite.getDocname());
		System.out.println(destination);
	     System.out.println(addlsmsite.getFilename());	
						
		PLA_DOC pladoc = new PLA_DOC();
		pladoc.setDOC_NAME(addlsmsite.getDocname());
		pladoc.setUPLOADED_DT(date);
		pladoc.setPLA_OFFID(addlsmsite.getUserid());
		pladoc.setUPLOADED_BY(request.getSession().getAttribute("offId").toString());
		pladoc.setDOC_PATH(destination.getPath()); 
		addlsmsite.getFilename().transferTo(destination);
		//pladoc.getFileAttachment().transferTo(destination);
		pladocrepo.save(pladoc);
		} catch (IllegalStateException  | IOException e ) {
			e.printStackTrace();
		} 
		//return addlsmsite;

	}

	
	
	@RequestMapping(value = "/updLsm", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody D_EMP_ROLES updLsm(@RequestBody D_EMP_ROLES updlsmsite, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws Exception {
		if(updlsmsite.getCusSite() != null) {
			if(inputvalidate.ValidaString(updlsmsite.getCusSite()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getDocname() != null) {
			if(inputvalidate.ValidaString(updlsmsite.getDocname()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getUserid() != null) {
			if(inputvalidate.ValidaString(updlsmsite.getUserid()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getReason() != null) {
			if(inputvalidate.ValidaString(updlsmsite.getReason()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getMobileno() != null && !updlsmsite.getMobileno().isEmpty()) {
			if(inputvalidate.Validanum(updlsmsite.getMobileno()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getEmail() != null && !updlsmsite.getEmail().isEmpty()) {
			if(inputvalidate.ValidateEmail(updlsmsite.getEmail()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}
			if(updlsmsite.getDesign() != null) {
			if(inputvalidate.ValidaString(updlsmsite.getDesign()) == 1) {
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
		        return null;
					}
			}

		
		Date currentdate = new Date();
		System.out.println("cms here");
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		String usrid = request.getSession().getAttribute("offId").toString();
		List<D_EMP_ROLES> demp = demproleNSM.getalldata( updlsmsite.getUserid(),"PLA");
		inseditroles(demp.get(0),"M");
		fpositemgmtrepo.upddata(updlsmsite.getCusSite(), updlsmsite.getUserid(), updlsmsite.getDesign(), updlsmsite.getMobileno(), updlsmsite.getEmail(),updlsmsite.getReason(),usrid,date);
		System.out.println("updated");

		return updlsmsite;

	}

// Auto populate Site add based on fpo site
	@RequestMapping(value = "/getsiteoflist")
	public @ResponseBody List<Collection> getsitelist(HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		String sitelist = request.getParameter("list");
		if (sitelist != null) {
		if(inputvalidate.ValidaString(sitelist) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitelist.length() > 35 ) {	
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
	        return null;
		       }
		}
		List<Collection> activedata = fpositemgmtrepo.getsitelist(sitelist);
		return activedata;
	}
	
	@RequestMapping(value = "/getsiteinfo")
	public @ResponseBody List<Collection> getsiteinfo(HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		String sitelist = request.getParameter("list");
		if (sitelist != null) {
		if(inputvalidate.ValidaString(sitelist) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitelist.length() > 35 ) {	
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
	        return null;
		       }
		}
		
		List<Collection> activedata = fpositemgmtrepo.getsiteinfo(sitelist);
		return activedata;
	}
	
	@RequestMapping("/getclussite")
	public @ResponseBody List<Collection> getclussite(HttpServletRequest request, HttpServletResponse response,
			Model model)throws Exception {
		String sitelist = request.getParameter("list");
		if (sitelist != null) {
		if(inputvalidate.ValidaString(sitelist) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		else if (sitelist.length() > 6 ) {	
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        return null;
		       }
		}
		List<Collection> activedata = fpositemgmtrepo.getclussiteinfo(sitelist);
		return activedata;
	}
	
	
/*	@RequestMapping(value = "/getssolist")
	public @ResponseBody List<Collection> getssolist(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String sitelist = request.getParameter("list");
		List<Collection> activedata = fpositemgmtrepo.getssolist(ssolist);
		return activedata;
	}*/

	// Auto populate name and designation on Lsm
	@RequestMapping(value = "/getDesignandNme")
	public @ResponseBody List<Collection> getempName(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String sitelist = request.getParameter("ssoid");
		String cussite = request.getParameter("cusite");
		List<Collection> activedata,testdata;
		activedata=null;
		if (fpositemgmtrepo.plafouint(sitelist,cussite) == 0)
		  activedata= fpositemgmtrepo.getnamendesg(sitelist,cussite);
		else
		  activedata=fpositemgmtrepo.chkplafou(sitelist,cussite);
		return activedata;
	}
	
	@RequestMapping(value = "/getssofou")
	public @ResponseBody List<Collection> getssofou(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String sitelist = request.getParameter("ssoid");
		String cussite = request.getParameter("cusite");
		List<Collection> activedata,testdata;
		activedata = fpositemgmtrepo.plafouemp(sitelist);
		return activedata;
	}
	

	@RequestMapping(value = "/getplaalreadyfou")
	public @ResponseBody int getplaalreadyfou(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String offid = request.getParameter("ssoid");
		String cussite = request.getParameter("cusite");
	    int activedata = fpositemgmtrepo.plaalreadyfou(offid,cussite);
	    return activedata;
	}

	// view history Site name select options
	@RequestMapping(value = "/getFpoSiteName")
	public @ResponseBody List<Collection> getsitests(HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		
		
		String siteNme = request.getParameter("siteNme");
		if(siteNme != null) {
		if(inputvalidate.ValidaString(siteNme) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		List<Collection> activedata = null;
		if (siteNme.equals("All Sites"))
			activedata = fpositemgmtrepo.getsiteNameHstryall(siteNme);
		else
		activedata = fpositemgmtrepo.getsiteNameHstry(siteNme);
		return activedata;
	}

	// view Lsm Site Hstry
	@RequestMapping(value = "/getLSMHstry")
	public @ResponseBody List<Collection> getHstry(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String siteNme = request.getParameter("siteNme");
		List<Collection> activedata = null;
		if (siteNme.equals("All Sites"))
			activedata = fpositemgmtrepo.getLsmHstryall();
		else
		    activedata = fpositemgmtrepo.getLsmHstry(siteNme);
		return activedata;
	}

	// GetCountof LSM
	@RequestMapping(value = "/getCountofLSM")
	public @ResponseBody Long getLSMCunt(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String siteNme = request.getParameter("LSMsitecode");
		if(siteNme != null) {
		if(inputvalidate.ValidaString(siteNme) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		Long getcuntdata = fpositemgmtrepo.getLSMsitecdeCunt(siteNme);
		
		return getcuntdata;
	}
	

	// Get List of ssoid
	@RequestMapping(value = "/getlistofssoid")
	public @ResponseBody String[] getlistofssid(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String lsmSite = request.getParameter("cusSite");
		if (lsmSite != null) {
		if(inputvalidate.ValidaString(lsmSite) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		String[] ssoidlist = fpositemgmtrepo.listofSSOid(lsmSite);
		if (ssoidlist.length > 0)
		    return ssoidlist;
		else
			return null;
	}

	@RequestMapping(value = "/Lsmssoiddata")
	public @ResponseBody List<Collection> getssoidinfrm(HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception{
		String Lsmssid = request.getParameter("SSOid");
		if (Lsmssid != null) {
		if(inputvalidate.ValidaString(Lsmssid) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		List<Collection> getvalueofid = fpositemgmtrepo.getvalueofUser(Lsmssid);
		if (getvalueofid.size() > 0)
		      return getvalueofid;
		else
			  return null;
	}

//get all added sites
	@RequestMapping(value = "/getalladdedsite")
	public @ResponseBody List<Collection> getaddsites(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Collection> activedata = fpositemgmtrepo.getaddedsites();
		if (activedata.size() > 0)
		    return activedata;
		else
			return null;
	}

	// show site history
	@RequestMapping(value = "/showsitehstry")
	public @ResponseBody List<Collection> showsitehstry(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) {
		String siteCde = request.getParameter("siteCde");
		List<Collection> shwsitehstry;
		if (siteCde.equals("All Sites"))
			shwsitehstry = fpositemgmtrepo.shwsiteHstryall(siteCde);
		else
		    shwsitehstry = fpositemgmtrepo.shwsiteHstry(siteCde);
		if (shwsitehstry.size() > 0)
			return shwsitehstry;
		else
			return null;
	}

	// show view user site
	@RequestMapping(value = "/getusersite")
	public @ResponseBody List<Collection> getusersite(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String usersiteCde = request.getParameter("userstecde");
		List<Collection> viewusersite;
		System.out.println("sitecode is"+usersiteCde);
		if (usersiteCde.equals("All Sites"))
			viewusersite = fpositemgmtrepo.viewusersiteall(usersiteCde);
		else
		    viewusersite = fpositemgmtrepo.viewusersite(usersiteCde);
		if (viewusersite.size() > 0)
		    return viewusersite;
		else
			return null;
	}

	// Get allocate lsm
	@RequestMapping(value = "/getlsmofficer")
	public @ResponseBody List<Collection> getlsmdata(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Collection> allocatedlsm = fpositemgmtrepo.getallctlsm();
		return allocatedlsm;
	}

//LSM Module
	@RequestMapping("/lsm_module")
	public ModelAndView lsmModule(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/lsm/Lsm_module");
		return modelAndView;
	}

	@RequestMapping("/allocation_of_roles")
	public ModelAndView Allocateroles(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/lsm/Allocateroles");
		return modelAndView;
	}
	
	@RequestMapping("/nsm_allotrole")
	public ModelAndView Nms_allotrole(Model model, HttpServletRequest request, HttpSession session) {
        String[] getNsmusrList = fpositemgmtrepo.getNsasitecd(request.getSession().getAttribute("Nsmsitecde").toString());
		ModelAndView modelAndView = new ModelAndView("admin/nsm/NSM_role_allot");
        model.addAttribute("getLstofNsmusr", getNsmusrList);
        List<Collection> getoffdata = fpositemgmtrepo.getNsauserinfo(request.getSession().getAttribute("Nsmsitecde").toString());
		model.addAttribute("Nsmoffid", request.getSession().getAttribute("offId").toString());
		model.addAttribute("getLstofuserdata",getoffdata);
		return modelAndView;
	}

	@RequestMapping("/roles_allocation")
	public ModelAndView Roleallocation(Model model, HttpServletRequest request, HttpSession session) {
		List<String> allocatedlsm = fpositemgmtrepo.getLsmusers(request.getSession().getAttribute("LSMcuSite").toString());
		List<Collection> getallroles_a = DempFpo.getallroles_a(request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("LSMcuSite").toString());
		List<Collection> getallroles_b = DempFpo.getallroles_b(request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("LSMcuSite").toString());
		List<Collection> getallroles_c = DempFpo.getallroles_c(request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("allocatedlsm", allocatedlsm);
		model.addAttribute("getallroles_a", getallroles_a);
		model.addAttribute("getallroles_b", getallroles_b);
		model.addAttribute("getallroles_c", getallroles_c);
		model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("Lsmoffid", request.getSession().getAttribute("offId").toString());
		ModelAndView modelAndView = new ModelAndView("admin/lsm/roles_allocation");
		return modelAndView;
	}

// View Active Users in lsm
	@RequestMapping("/active_users")
	public ModelAndView activeroles(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> getlistOfUser = demproleNSM.getLsmuserList(request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("getlistOfUser", getlistOfUser);
		ModelAndView modelAndView = new ModelAndView("admin/lsm/viewLsmActiveUsers");
		return modelAndView;
	}

// get active users by user-id in LSM Script 
	@RequestMapping(value = "/viewActiveUserLsm")
	public @ResponseBody List<Collection> getactiveusers(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws Exception, Exception {
		String id = request.getParameter("userid");
		if(inputvalidate.Validanum(id) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (id.length() == 0 || id.isEmpty() || id.length() > 8 ) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}
		List<Collection> getLsmuser = demproleNSM.getLsmactveUsr(request.getSession().getAttribute("LSMcuSite").toString(), id);
		return getLsmuser;
	}

	// View Hstryofroles
	@RequestMapping(value = "/gethstryofroles")
	public @ResponseBody List<Collection> gethstryroles(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String usersiteCde = request.getParameter("userstecde");
		List<Collection> viewusersite;
		if (usersiteCde.equals("All Sites"))
			viewusersite = fpositemgmtrepo.viewhstryrolesall(usersiteCde);
		else			
		    viewusersite = fpositemgmtrepo.viewhstryroles(usersiteCde);
		return viewusersite;
	}

	// Update Site Details
	@RequestMapping(value = "/updateuserdetails")
	public @ResponseBody String updatedetails(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) {
		String usrid = request.getParameter("userdetls");
		String mblnum = request.getParameter("mblenum");
		String mailid = request.getParameter("mailid");
		demproleNSM.updatedetails(usrid, mblnum, mailid);
		return null;
	}

// Get all Active user in Lsm
	@RequestMapping(value = "/getallLsmactiveusr")
	public @ResponseBody List<Collection> getallactiveusr(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) {
		List<Collection> getAllLsmuser = demproleNSM.getallLsmactveUsr(request.getSession().getAttribute("LSMcuSite").toString());
		return getAllLsmuser;
	}

// View Lsm role Histry
	@RequestMapping("/viewLsmroleHstry")
	public ModelAndView viewLsmroleHstry(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> getlistOfUser = demproleNSM.getrevokedData(request.getSession().getAttribute("LSMcuSite").toString());
		 List<Collection> activelstlsm = fpositemgmtrepo.getactiveusers();
		model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("getlistOfUser", getlistOfUser);
		 model.addAttribute("activelstlsm", activelstlsm);
		ModelAndView modelAndView = new ModelAndView("admin/lsm/viewLsmSiteHstry");
		return modelAndView;
	}

	// View Lsm Site Hstry data
	@RequestMapping(value = "/viewLsmHstrydata")
	public @ResponseBody List<Collection> getLsmsitedata(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws Exception, Exception {
		String id = request.getParameter("userid");
		
		if(inputvalidate.Validanum(id) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (id.length() == 0 || id.isEmpty() || id.length() > 8 ) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}
		
		
		List<Collection> getLsmhstrydata = demproleNSM.getLsmuserHstry(request.getSession().getAttribute("LSMcuSite").toString(), id);
		return getLsmhstrydata;
	}
	
	// View Lsm Site Hstry data
		@RequestMapping(value = "/viewallLsmHstrydata")
		public @ResponseBody List<Collection> getallLsmsitedata(HttpServletRequest request, HttpServletResponse response,
				Model model, HttpSession session) {
			String id = request.getParameter("userid");
			List<Collection> getLsmhstrydata = demproleNSM.getallLsmuserHstry(request.getSession().getAttribute("LSMcuSite").toString());
			return getLsmhstrydata;
		}
	

	@RequestMapping(value = "/lsmloginUser", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody String LsmloginUser(@RequestBody D_EMP_ROLES empName, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		try {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String curtime = time.format(currentdate);
		String userid = empName.getUserid();
		String lsmsite = fpositemgmtrepo.getLsmSitecde(userid);
		String pwd = empName.getPwd();
		String Lsmsitename = "";
		if (lsmsite != null) {
			Lsmsitename = lsmsite.split(",")[1];
		}
		Long fou = demproleNSM.userfou(userid);
		 request.getSession().setAttribute("role", demproleNSM.getrolenm(userid));
		if (fou > 0) {
			 if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("PLA") ) {
			 request.getSession().setAttribute("resp", "1");
			 request.getSession().setAttribute("Lsmsitenme", Lsmsitename);
			 request.getSession().setAttribute("LSMcuSite", demproleNSM.LsmSiteCde(userid));
			 request.getSession().setAttribute("datatime", curtime);
			 request.getSession().setAttribute("data", userid);
			//offId = userid;
			 request.getSession().setAttribute("offId", userid);
			//role = demproleNSM.getrolenm(userid);
			 request.getSession().setAttribute("role", demproleNSM.getrolenm(userid));
			//LSMcuSite = demproleNSM.LsmSiteCde(userid);
			//offName = demproleNSM.getempname(userid);
			 request.getSession().setAttribute("offName", demproleNSM.getempname(userid));

			 request.getSession().setAttribute("data1", demproleNSM.getrolenm(userid));
			 request.getSession().setAttribute("data2", demproleNSM.LsmSiteCde(userid));
			 request.getSession().setAttribute("data3", demproleNSM.getempname(userid));
		} 
	}else {
			
			 request.getSession().setAttribute("resp", "0");
		}
		return request.getSession().getAttribute("resp").toString();

	}catch (Exception e) {
		return null;
	}
	}

	@RequestMapping(value = "/getoffname&desg")
	public @ResponseBody List<Collection> usernameandDesg(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws Exception {
		String id = request.getParameter("userid");
		
		if(inputvalidate.ValidaString(id) == 1) 
		    return null;			
		else if (id.length() == 0 || id.isEmpty() || id.length() > 50 ) 
			return null;
		List<Collection> Lsmval = demproleNSM.LsmuserId(id);
		List<Collection> mcpao = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PAO");
		List<Collection> mcpin = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PIN");
		List<Collection> mcpsu = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PSU");
		Lsmval.add(mcpao);
		Lsmval.add(mcpin);
		Lsmval.add(mcpsu);
		return Lsmval;
	}
	
	@RequestMapping(value = "/getUserProfile")
	public ModelAndView getuserprofile(HttpServletRequest request,Model model) {
		ModelAndView models = new ModelAndView("/userprofile");
	try {
		String id = request.getParameter("userid");
		List<Collection> ProfData = null;
		ProfData = Demprepo.getprofile(id);
		model.addAttribute("check",ProfData);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return models;
}
	
	@RequestMapping(value = "/getoffname&desgedit")
	public @ResponseBody List<Collection> usernameandDesgedit(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws IOException, Exception {
		String id = request.getParameter("userid");
		if(inputvalidate.ValidaString(id) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		if(inputvalidate.ValidaString(id) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		if(inputvalidate.Validanum(id) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (id.length() == 0 || id.isEmpty() || id.length() > 8 ) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}

	
		List<Collection> Lsmval = demproleNSM.editroles(id);
		List<Collection> mcpao = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PAO");
		List<Collection> mcpin = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PIN");
		List<Collection> mcpsu = DempFpo.getmailclass(request.getSession().getAttribute("LSMcuSite").toString(),"PSU");
		Lsmval.add(mcpao);
		Lsmval.add(mcpin);
		Lsmval.add(mcpsu);
		return Lsmval;
	}

	@RequestMapping(value = "/getadminrolenm")
	public @ResponseBody List<Collection> getadminrolenm(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		/* String Usrdsc = request.getParameter("userdesc"); */
		List<Collection> Lsmadimnrole = DempFpo.getdisproleNm_a();
		return Lsmadimnrole;
	}
	
	// Get Nsm Adminrole
	@RequestMapping(value = "/getNsmadminrolenm")
	public @ResponseBody List<Collection> getNsmadminrolenm(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		/* String Usrdsc = request.getParameter("userdesc"); */
		String Nsmsiteaces = request.getParameter("NsmSitAccess");
		List<Collection> Nsmadimnrole = DempFpo.getNsmadminrole_a(Nsmsiteaces);
		return Nsmadimnrole;
	}
	
	//Get count of PLA
	@RequestMapping(value = "/getcountofPLA")
	public @ResponseBody int getcountofPLA(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		String Lsmrole = request.getParameter("roleNm");
		int count = DempFpo.getPLAcount(Lsmrole, request.getSession().getAttribute("LSMcuSite").toString());
		return count;
	}
	
	//Get count of PAA
		@RequestMapping(value = "/getcountofPAA")
		public @ResponseBody int getcountofPAA(HttpServletRequest request, HttpServletResponse response,
			 HttpSession session) {
			String Lsmrole = request.getParameter("roleNm");
			int count = DempFpo.getPAAcount(Lsmrole, request.getSession().getAttribute("LSMcuSite").toString());
			return count;
		}
	
	//Get count of ARP
			@RequestMapping(value = "/getcountofARP")
			public @ResponseBody int getcountofARP(HttpServletRequest request, HttpServletResponse response,
				 HttpSession session) {
				String Lsmrole = request.getParameter("roleNm");
				int count = DempFpo.getARPcount(Lsmrole, request.getSession().getAttribute("LSMcuSite").toString());
				return count;
			}
		
	@RequestMapping(value = "/getdisprolenm")
	public @ResponseBody List<Collection> getdisprleNm(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		/* String Usrdsc = request.getParameter("userdesc"); */
		List<Collection> Lsmdisprole = DempFpo.getdisproleNm_b();
		return Lsmdisprole;
	}
	
	@RequestMapping(value = "/getmiserole")
	public @ResponseBody List<Collection> getMiscerole(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		/* String Usrdsc = request.getParameter("userdesc"); */
		List<Collection> Lsmmiscrole = DempFpo.getdisproleNm_c();
		return Lsmmiscrole;
	}
	
	@RequestMapping(value = "/getNsmmiserole")
	public @ResponseBody List<Collection> getNsmMiscerole(HttpServletRequest request, HttpServletResponse response,
		 HttpSession session) {
		/* String Usrdsc = request.getParameter("userdesc"); */
		String Nsmsiteaces = request.getParameter("NsmSitAccess");
		List<Collection> Nsmmiscrole = DempFpo.getNsmmiscel_c(Nsmsiteaces);
		return Nsmmiscrole;
	}
	
	@RequestMapping(value = "/getassignedroleforNsm")
	public @ResponseBody List<Collection> getrolenamefornsm(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) {
		String id = request.getParameter("userid");
		List<Collection> getNsmroledata = demproleNSM.nsmAssignedRole(request.getSession().getAttribute("LSMcuSite").toString(), id);
		return getNsmroledata;
	}
	
	@RequestMapping(value = "/getanyothroleNSM")
	public @ResponseBody int getanyothroleNSM(HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String id = request.getParameter("userid");
		int anyothrole = demproleNSM.getanytransrole(id);
		return anyothrole;
	}
	
	@RequestMapping(value = "/getanyothroleLSM")
	public @ResponseBody int getanyothroleLSM(HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String id = request.getParameter("userid");
		String cusite =  request.getParameter("cusite");
		int anyothrole = demproleNSM.getanyothtransrole(id,cusite);
		return anyothrole;
	}
	
// LSM Role Updating
	@RequestMapping(value = "/assignroles", method = RequestMethod.GET)
	public @ResponseBody D_EMP_ROLES roleAssign(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		fpoSiteService.insertdt(request);
		D_EMP_ROLES succes = new D_EMP_ROLES();
		return succes;
	}
	
	
//Edit Lsm Roles
	@RequestMapping(value = "/editassignroles", method = RequestMethod.GET)
	public @ResponseBody D_EMP_ROLES editrole(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		fpoSiteService.editrole(request);
		D_EMP_ROLES succes = new D_EMP_ROLES();
		return succes;
	}

//Remove all roles
	@RequestMapping(value = "/removeallroles", method = RequestMethod.GET)
	public @ResponseBody D_EMP_ROLES removeroles(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		fpoSiteService.rmveroles(request);
		D_EMP_ROLES succes = new D_EMP_ROLES();
		return succes;
	}

// Edit Roles
	@RequestMapping("/editroles")
	public ModelAndView Lsmroleedit(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> getassid = demproleNSM.getassid(request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("getuserid", getassid);
		ModelAndView modelAndView = new ModelAndView("admin/lsm/edit_roles");
		model.addAttribute("LsmcuSite", request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("Lsmoffid", request.getSession().getAttribute("offId").toString());
		return modelAndView;
	}
	
	@RequestMapping("/Nsm_editroles")
	public ModelAndView Nsmroleedit(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> getassid = demproleNSM.getNsmusreditinfo();
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Nsm_editroles");
		model.addAttribute("getNsmuserid", getassid);
		model.addAttribute("Nsmsitecde", request.getSession().getAttribute("Nsmsitecde").toString());
		model.addAttribute("Nsmoffid", request.getSession().getAttribute("offId").toString());		
		return modelAndView;
	}

	
	
	
	
	@RequestMapping("/getPnacount")
	public @ResponseBody Long Pnacount() {
		Long Pnacunt = DempFpo.getPNAcount();
		return Pnacunt;
	}
	
	@RequestMapping("/getPnaoffid")
	public @ResponseBody List<Collection> getPnaoffid(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> pnainfo = DempFpo.getPNAdata();
		return pnainfo;
	}
	
	@RequestMapping("/getARPcount")
	@ResponseBody
	public int ARPcount() {
		int Arpcunt = DempFpo.getARPcount();
		return Arpcunt;
	}
	
	@RequestMapping(value = "/getroleandmailcls")
	public @ResponseBody List<Collection> getrolename(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws Exception, Exception {
		String id = request.getParameter("userid");
		
		if(inputvalidate.Validanum(id) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (id.length() == 0 || id.isEmpty() || id.length() > 8 ) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}
		
		
		System.out.println("id="+id);		
		List<Collection> getroledata = demproleNSM.getassignedroles(request.getSession().getAttribute("LSMcuSite").toString(), id);
		return getroledata;
	}

	// Site Management in LSM
	
	@RequestMapping("/Lsm_siteUpdate")
	public ModelAndView SiteUpdate(Model model, HttpServletRequest request, HttpSession session) {
		Object siteDetails = demproleNSM.siteDetails(request.getSession().getAttribute("LSMcuSite").toString());
		Object[] data = (Object[])siteDetails;
		for(Object val : data) {
			System.out.println(val);
		}
		ModelAndView modelAndView = new ModelAndView("admin/lsm/Lsm_siteUpdate");
		String cuSite=request.getSession().getAttribute("LSMcuSite").toString();
		Object fpoSiteAllotDetails = fpoDashboardService.getFpoSiteAllotDetails(cuSite);
		model.addAttribute("fpoSiteAllotDetails", fpoSiteAllotDetails);
		model.addAttribute("siteDetails", siteDetails);
		return modelAndView;
	}
	
	
	@RequestMapping("/releasemanagement")
	public ModelAndView releasemanagement(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/ReleaseManagement");
		String loginid = request.getSession().getAttribute("offId").toString();
		model.addAttribute("loginid", loginid);
		List<String> releasemgmtval = releasemgmtrepo.getreleasemgmtallval();
		
		model.addAttribute("releasemgmtval", releasemgmtval);
		return modelAndView;
	}
	
	
	@RequestMapping("/releaseMgmt")
	public @ResponseBody List<Collection> releaseMgmt( HttpServletRequest request, HttpServletResponse response) { 
		List<Collection> releasemgmtval = releasemgmtrepo.getreleaseMgmt();
	    return releasemgmtval; 
	}
	
	/*
	 * @RequestMapping("/processUpdateHistory") public ModelAndView
	 * processUpdateHistory(Model model, HttpServletRequest request) { ModelAndView
	 * modelAndView = new ModelAndView("admin/lsm/processUpdateHistory"); String
	 * uptodate = request.getParameter("uptodate"); List<String> processallval =
	 * editfpoallotrepo.getprocessallval(); String processfilename =
	 * editfpoallotrepo.getprocesspdf(uptodate);
	 * model.addAttribute("processfilename", processfilename);
	 * model.addAttribute("processallval", processallval);
	 * 
	 * return modelAndView; }
	 */
	
	@RequestMapping("/processUpdateHistory")
	public ModelAndView processUpdateHistory(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/lsm/processUpdateHistory");
		String sitecode =request.getSession().getAttribute("LSMcuSite").toString();
		String siteid = request.getSession().getAttribute("offId").toString();
		String siterole =request.getSession().getAttribute("role").toString();
		String uptodate = request.getParameter("uptodate");
		List<String> processallval = editfpoallotrepo.getprocessallval(sitecode,siteid);
		String processfilename = editfpoallotrepo.getprocesspdf(uptodate);
		model.addAttribute("processfilename", processfilename);
		model.addAttribute("processallval", processallval);

		return modelAndView;
	}
	
	@RequestMapping("/processUpdateHistory1")
	public ModelAndView processUpdateHistory1(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Nsmprocessupdatehistory");
		String uptodate = request.getParameter("uptodate");
		List<String> processallval = editfpoallotrepo.getprocessallval1();
		String processfilename = editfpoallotrepo.getprocesspdf(uptodate);
		model.addAttribute("processfilename", processfilename);
		model.addAttribute("processallval", processallval);

		return modelAndView;
	}
	
	@RequestMapping("/Lsm_site_mgmt")
	public ModelAndView LsmSiteMgmgt(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/lsm/Lsm_sitemgmt");
		return modelAndView;
	}

	@RequestMapping("/Push_article")
	public ModelAndView pusharticle(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/lsm/Push_article");
		return modelAndView;
	}

	// Update Site Details
	@RequestMapping("/addsiteinfo")
	public @ResponseBody FPO_SITE_INFO siteinfo(@RequestBody FPO_SITE_INFO sitedata, HttpServletRequest request, HttpSession session) throws Exception {
		sitedata.setOffid(request.getSession().getAttribute("offId").toString());
		sitedata.setRole(request.getSession().getAttribute("role").toString());
		
		

		if(inputvalidate.ValidaString(sitedata.getAddress()) == 1) {
		 return null;	
		}
		else if (sitedata.getAddress().length() == 0 || sitedata.getAddress().isEmpty() || sitedata.getAddress().length() > 512 ) {
			return null;
			
		}
		
		
		if(inputvalidate.Validanum(sitedata.getPincode()) == 1)
			return null;
		else if (sitedata.getPincode().length() == 0 || sitedata.getPincode().isEmpty() || sitedata.getPincode().length() > 6 ) {
			return null;
		}	
		
		if(inputvalidate.ValidaString(sitedata.getFpositeName()) == 1 )
			return null;
		else if (sitedata.getFpositeName().length() == 0 || sitedata.getFpositeName().isEmpty() || sitedata.getFpositeName().length() > 50 ) {
			return null;
		}
		
		
		if(inputvalidate.ValidaString(sitedata.getCity()) == 1 )
			return null;
		else if (sitedata.getCity().length() == 0 || sitedata.getCity().isEmpty() || sitedata.getCity().length() > 50 ) {
			return null;
		}
		
		if(inputvalidate.ValidaString(sitedata.getState()) == 1 )
			return null;
		else if (sitedata.getState().length() == 0 || sitedata.getState().isEmpty() || sitedata.getState().length() > 50 ) {
			return null;
		}
		
		if(inputvalidate.ValidaString(sitedata.getPhne()) == 1 )
			return null;
		else if (sitedata.getPhne().length() == 0 || sitedata.getPhne().isEmpty() || sitedata.getPhne().length() > 50 ) {
			return null;
		}
	
		if(inputvalidate.ValidateEmail(sitedata.getEmail()) == 1 )
			return null;
		else if (sitedata.getEmail().length() == 0 || sitedata.getEmail().isEmpty() || sitedata.getEmail().length() > 125 ) {
			return null;
		}
		
		if(inputvalidate.ValidaString(sitedata.getVisithrs()) == 1 )
			return null;
		else if (sitedata.getVisithrs().length() == 0 || sitedata.getVisithrs().isEmpty() || sitedata.getVisithrs().length() > 100 ) {
			return null;
		}
		


		
		
		
		
		
		
		
		
		
		FPO_SITE_INFO siteDetails = fpositerepo.getSite(request.getSession().getAttribute("LSMcuSite").toString());
		//Update method with primary key
		if(siteDetails.getId() != null) {
			sitedata.setId(siteDetails.getId());
		}
		fpositerepo.save(sitedata);
		
		return sitedata;

	}

	@RequestMapping(value = "/getfpocusite")
	public @ResponseBody void getCusiteofitem(HttpServletRequest request, HttpServletResponse response, Model model) {
		String siteNme = request.getParameter("articleid");
		String articlecus = demproleNSM.getarticleCussite(siteNme);
		List<Collection> pushtoSite = demproleNSM.pushsiteCode(articlecus);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("articlecus", articlecus);
		jsonObj.put("pushtoSite", pushtoSite);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/updatelsmdetails")
	public ModelAndView LsmUpdatedetails(Model model, HttpServletRequest request, HttpSession session) {
		List<Collection> Upddetailforuser = demproleNSM.getLsmuserList(request.getSession().getAttribute("LSMcuSite").toString());
		model.addAttribute("upddetails", Upddetailforuser);
		ModelAndView modelAndView = new ModelAndView("admin/lsm/LsmUpdateDetails");
		return modelAndView;
	}

	@RequestMapping(value = "/pushitems", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_PUSH pushsitecde(@RequestBody FPO_PUSH fpopush, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		fpopush.setPushdt(date);
		fpopush.setOffid(request.getSession().getAttribute("offId").toString());
		fpopush.setRole(request.getSession().getAttribute("role").toString());
		fpopushrepo.save(fpopush);
		return fpopush;

	}

	/*@RequestMapping(value = "/getarticleprcsdata")
	public @ResponseBody ModelAndView articledata(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException {
		String getsite = request.getParameter("selsite");
		System.out.println(getsite);
		String frmdt = request.getParameter("frmdt");
		System.out.println(frmdt);
		String todt = request.getParameter("todt");
		System.out.println(todt);
		ModelAndView modelAndView = new ModelAndView("/admin/nsm/processtabledata");
		if (getsite != "All Site" && getsite.length() == 6) {
		//	NSM_procesdata getdata = fpoSiteService.getarictleprcesdata(getsite, frmdt, todt);
			List<NSM_procesdata> getdata = FPOrepost.getprecedata(frmdt, todt, getsite);
			model.addAttribute("getprcsdata", getdata);
		} else {
			NSM_procesdata allsitedata = fpoSiteService.getallprcsdata(frmdt, todt);
			model.addAttribute("getprcsdata", allsitedata);
		}
		model.addAttribute("getsite", getsite);
		return modelAndView;
	}*/
	
	@RequestMapping(value = "/getarticleprcsdata")
	public @ResponseBody ModelAndView articledata(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		
		
		
		
		String getsite = request.getParameter("selsite");
		
		System.out.println(getsite);
		String frmdt="";
		String todt ="";
		if(getsite != null) {
		if(inputvalidate.ValidaString(getsite) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		if (request.getParameter("frmdt").length() == 10)
		  frmdt = request.getParameter("frmdt");
		System.out.println(frmdt);
		if(frmdt != null && !frmdt.isEmpty() ) {
		if(inputvalidate.Validadate(frmdt) == 1 ) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		if (request.getParameter("todt").length() == 10)
		   todt = request.getParameter("todt");
		System.out.println(todt);
		if(todt != null && !todt.isEmpty() ) {
		if(inputvalidate.Validadate(todt) == 1) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set status code 400
	        return null;
				}
		}
		ModelAndView modelAndView = new ModelAndView("/admin/nsm/processtabledata");
		if (getsite != "All Site" && getsite.length() == 6) {
		//	NSM_procesdata getdata = fpoSiteService.getarictleprcesdata(getsite, frmdt, todt);
			List<NSM_procesdata> getdata = fpoSiteService.getprecedata1(frmdt, todt, getsite);
			model.addAttribute("getprcsdata", getdata);
		} else {
			NSM_procesdata allsitedata = fpoSiteService.getallprcsdata(frmdt, todt);
			model.addAttribute("getprcsdata", allsitedata);
		}
		model.addAttribute("getsite", getsite);
		return modelAndView;
	}


	// Re-allocation
	/*
	 * @RequestMapping(value="/reallocation") public ModelAndView reallocate(Model
	 * model) { List<String> getresn = demproleNSM.getreallocationdesc();
	 * model.addAttribute("getresn", getresn); ModelAndView mav = new
	 * ModelAndView("/admin/lsm/reallocation"); return mav; }
	 */

	/*
	 * @RequestMapping(value = "/getreallocationdata", produces = {
	 * MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	 * public @ResponseBody REALLOCATION getreallocatevalue(@RequestBody
	 * REALLOCATION reallocate, HttpServletRequest request, HttpServletResponse
	 * response, Model model, HttpSession session) throws ParseException {
	 * 
	 * String roles = request.getParameter("rolesassigned"); String[] arryrol =
	 * request.getParameterValues("rolesassigned");
	 * reallocate.setRoles_assigned(roles); String frmssid =
	 * reallocate.getFrom_ssoid(); String tossid = reallocate.getTo_ssoid(); String
	 * mailcls = reallocate.getMail_class(); String artid =
	 * reallocate.getArticle_id();
	 * fpoSiteService.reallocateservice(reallocate,session);
	 * 
	 * if (artid != "") { demproleNSM.specificid(roles, frmssid, tossid, mailcls,
	 * artid); } else { for (String role : arryrol) {
	 * fpoSiteService.updatereallocation(role, frmssid, tossid, mailcls); }
	 * 
	 * }
	 * 
	 * return reallocate; }
	 */

	@RequestMapping(value = "/Lsmgetassignedroles")
	public @ResponseBody List<String> getreallocatevalue(HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String usrid = request.getParameter("ssoid");
		List<String> getroles = demproleNSM.getrolename(usrid);
		return getroles;
	}
	
	
	

//	@RequestMapping(value = "/tossoidroles")
//	public @ResponseBody List<String> tossoidroles(HttpServletRequest request, HttpServletResponse response,
//			Model model) throws ParseException {
//		String usrid = request.getParameter("ssoid");
//		List<String> getroles = demproleNSM.getrolename(usrid);
//		return getroles;
//	}

//	@RequestMapping(value = "/getmailClas")
//	public @ResponseBody List<String> Mailclas(HttpServletRequest request, HttpServletResponse response, Model model)
//			throws ParseException {
//		String usrid = request.getParameter("ssoid");
//		String rolenme = request.getParameter("mailcls");
//		List<String> getroles = demproleNSM.getmailclass(usrid, rolenme);
//		return getroles;
//	}

	// Pincode mapping
	@RequestMapping("/pincodemap")
	public ModelAndView pincodemap(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/PincodeMapping");
		List<Collection> cs = demproleNSM.getCuSite();
		String[] stList = demproleNSM.getnewstate();
		model.addAttribute("stList", stList);
		model.addAttribute("siteCd", cs);
		return modelAndView;
	}

	@RequestMapping(value = "/getpincdemappingdt")
	public @ResponseBody List<Collection> pincodemapdata(HttpServletResponse response,HttpServletRequest request, Model model) throws IOException, Exception {
		String selval = request.getParameter("Cs");
		
		
		if(inputvalidate.ValidaString(selval) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (selval.length() == 0 || selval.isEmpty() || selval.length() > 50 ) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}
		List<Collection> getdata;
		if (selval.equals("All Sites"))
			getdata = demproleNSM.getpincdemapall(selval);
		else
		    getdata = demproleNSM.getpincdemap(selval);
		return getdata;
	}
	
	//Get Mapped State and Cusite
	@RequestMapping(value = "/getmappedSite")
	public @ResponseBody List<Collection> getmappedSite(HttpServletRequest request) {
		String pinval = request.getParameter("singlepin");
		List<Collection> mappeddata = demproleNSM.getmapSite(pinval);
		return mappeddata;
	}
	
	@RequestMapping("/pincodes")
	public ModelAndView pincodes(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/PincodeView");
		List<Collection> stlist = demproleNSM.getstnm_pincodes();
		model.addAttribute("stlist",stlist);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/getpincodesstate")
	public @ResponseBody List<Collection> getpincodesstate(HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws IOException, Exception {
		String stnm = request.getParameter("stname");
		
		if(inputvalidate.ValidaString(stnm) == 1) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data."); 
		    return null;		}	
		else if (stnm.length() == 0 || stnm.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
			return null;
		}
		
		
		List<Collection> PincodeList = demproleNSM.getpincodes(stnm);
		return PincodeList;
	}
	
	
	@RequestMapping("/cntrycodes")
	public ModelAndView cntrycodes(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/CntrycdView");
		List<Collection> codes = demproleNSM.getcntrycd();
		model.addAttribute("CntrycdList",codes);
		return modelAndView;
	}
	
	@RequestMapping("/currcodes")
	public ModelAndView currcodes(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/CurrcdView");
		List<Collection> codes = demproleNSM.getcurrcd();
		model.addAttribute("CurrcdList",codes);
		return modelAndView;
	}
	
	@RequestMapping("/statecodes")
	public ModelAndView statecodes(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/StatecdView");
		List<Collection> codes = demproleNSM.getstcd();
		model.addAttribute("StatecdList",codes);
		return modelAndView;
	}
	
	@RequestMapping("/ooeview")
	public ModelAndView ooecodes(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/ooecdView");
		List<Collection> codes = demproleNSM.getooecd();
		model.addAttribute("ooecdList",codes);
		return modelAndView;
	}
	
	
	@RequestMapping("/fpositesview")
	public ModelAndView fpositesview(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/FPOSitesView");
		List<Collection> sites = demproleNSM.getfposites();
		model.addAttribute("FPOsiteList",sites);
		return modelAndView;
	}
	
	
	
	//Get unmapped Cusite
	@RequestMapping(value = "/getUnmapCs")
	public @ResponseBody List<Collection> getunmapSite(HttpServletRequest request) {
		String mapsite = request.getParameter("mapCs");
		List<Collection> mappeddata = demproleNSM.getUnmapSite(mapsite);
		return mappeddata;
	}
	
	@RequestMapping(value = "/pincodemapinsert", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody Pincode_chnage insertmapSite(@RequestBody Pincode_chnage insertdata, HttpSession session,HttpServletRequest request, Model model) throws ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		insertdata.setMap_dt(date);
		insertdata.setOff_id(request.getSession().getAttribute("offId").toString());
		pincodemaprepo.save(insertdata);
		String oldcusite = insertdata.getMapped_site_code();
		String stpin = insertdata.getStart_pincode();
		String enpin = insertdata.getEnd_pincode();
		String stnm = insertdata.getNew_state();
		String cusite = insertdata.getNew_map_cussite();
		String pcusite = cusite.substring(0,5);
		String fpocode = demproleNSM.getfpocode(oldcusite,stpin,enpin);
		int ins=0;
		if (fpocode==null || fpocode=="")
			{fpocode=demproleNSM.getfpocodenew(cusite);
			 ins=1;
			}
		String sitenm = demproleNSM.getsitename(cusite);
		sitenm = sitenm.substring(sitenm.indexOf(",")+1,sitenm.length());
		if (ins==1)
		{			
		Pin_Code_Map pincdemap =  new Pin_Code_Map();
		pincdemap.setInbound_start_pincode(stpin);
		pincdemap.setInbound_end_pincode(enpin);
		pincdemap.setState_name(stnm);
        pincdemap.setCustoms_fpo_site_code(cusite);
        pincdemap.setFpo_destn(sitenm);
        pincdemap.setFpo_code(fpocode);
        pincdemap.setPostal_site_code(cusite.substring(0,5));
		pinmaprepo.save(pincdemap);}
		else
		{
			demproleNSM.updPINMAP(fpocode,stnm,stpin,enpin,sitenm,pcusite,cusite);
		}
		
		if (insertdata.getOff_id().length() == 8)
		    return insertdata;
		else
		    return null;    	
	}
	
	@RequestMapping(value = "/getnewmapstate")
	public @ResponseBody String[] getState(HttpServletRequest request) {
		String[] stateList = demproleNSM.getnewstate();
		if (stateList.length > 0)
		    return stateList;
		else
			return null;
		
	}
	
	//get mapped state and sitecde using startpin and end pin
	@RequestMapping(value = "/mappedstateandpin")
	public @ResponseBody List<Collection> mappedsite(HttpServletRequest request) {
		String startpin = request.getParameter("startpin");
		String endpin = request.getParameter("endpin");
		List<Collection> mappeddata = demproleNSM.rangepincode(startpin,endpin);
		return mappeddata;
	}
	
	@RequestMapping(value = "/getstatepincodes")
	public @ResponseBody ModelAndView listPincodeByState(HttpServletRequest request, Model model) {
		String stNm = request.getParameter("StNm");
		List<Collection> pincdeList = demproleNSM.listofpincode(stNm);
		ModelAndView modelAndView = new ModelAndView("admin/nsm/pincodelistpopup");
		model.addAttribute("pincdeList", pincdeList);
		NSMController nsm = new NSMController();
		return modelAndView;
	}
	
	@RequestMapping("/pendingparcel")
	public ModelAndView pendingparcel(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/nsm/Pendingparcel");
		List<Collection> notmappeddata = DempFpo.getnotmappedSite();
		List<Collection> yettomap = DempFpo.yettobemappedSite();
		List<Collection> mapbyoff = DempFpo.mapbyoff();
		model.addAttribute("notmappeddata", notmappeddata);
		model.addAttribute("yettobemapped", yettomap);
		model.addAttribute("mapbyoff",mapbyoff);
		return modelAndView;
	}
	
	
//	public void insfpoeditfpoallot(FPO_SITE_ALLOT sitedata, HttpServletRequest request)
//	{
//		EDIT_FPO_SITE_ALLOT editfpoallot = new EDIT_FPO_SITE_ALLOT();
//		editfpoallot.setLetterAir(sitedata.getLetterAir());
//		editfpoallot.setLetterSal(sitedata.getLetterSal());
//		editfpoallot.setLetterSea(sitedata.getLetterSea());
//		editfpoallot.setEmsAir(sitedata.getEmsAir());
//		editfpoallot.setEmsSal(sitedata.getEmsSal());
//		editfpoallot.setEmsSea(sitedata.getEmsSea());
//		editfpoallot.setParcelAir(sitedata.getParcelAir());
//		editfpoallot.setParcelSal(sitedata.getParcelSal());
//		editfpoallot.setParcelSea(sitedata.getParcelSea());
//		editfpoallot.setUpdDate(new Date());
//		editfpoallot.setSiteCode(request.getSession().getAttribute("LSMcuSite").toString());
//		editfpoallot.setOffid(request.getSession().getAttribute("offId").toString());
//		editfpoallot.setRole(request.getSession().getAttribute("role").toString());
//		editfpoallotrepo.save(editfpoallot);
//	}
	
	public void insfpoeditfpoallot(FPO_SITE_ALLOT sitedata, HttpServletRequest request)
	{
		EDIT_FPO_SITE_ALLOT editfpoallot = new EDIT_FPO_SITE_ALLOT();
		editfpoallot.setLetterAir(sitedata.getLetterAir());
		editfpoallot.setLetterSal(sitedata.getLetterSal());
		editfpoallot.setLetterSea(sitedata.getLetterSea());
		editfpoallot.setEmsAir(sitedata.getEmsAir());
		editfpoallot.setEmsSal(sitedata.getEmsSal());
		editfpoallot.setEmsSea(sitedata.getEmsSea());
		editfpoallot.setParcelAir(sitedata.getParcelAir());
		editfpoallot.setParcelSal(sitedata.getParcelSal());
		editfpoallot.setParcelSea(sitedata.getParcelSea());
		editfpoallot.setDocname(sitedata.getDocname());
		editfpoallot.setDOC_PATH(sitedata.getDOC_PATH());
		editfpoallot.setUpdDate(new Date());
		editfpoallot.setSiteCode(request.getSession().getAttribute("LSMcuSite").toString());
		editfpoallot.setOffid(request.getSession().getAttribute("offId").toString());
		editfpoallot.setRole(request.getSession().getAttribute("role").toString());
		editfpoallotrepo.save(editfpoallot);
	}
	
	// Update Site Details
/*	@RequestMapping("/addSiteAllotDetails")
	public @ResponseBody FPO_SITE_ALLOT updateSiteAllotinfo(@RequestBody FPO_SITE_ALLOT sitedata, HttpServletRequest request, HttpSession session) {

		String sitecode =request.getSession().getAttribute("LSMcuSite").toString();
		Date cdate = new Date();
		List<FPO_SITE_ALLOT> siteDetails = fpoSiteAllotrepo.getSite(sitecode);
		FPO_SITE_ALLOT siteupd = new FPO_SITE_ALLOT();
		//Update method with primary key
		if(siteDetails.get(0).getId() != null) {
		siteupd.setId(siteDetails.get(0).getId());
		}
		insfpoeditfpoallot(siteDetails.get(0), request);
	//	fpoSiteAllotrepo.updfpoallotsiteems(sitecode,sitedata.getEmsAir(),sitedata.getEmsSal(),sitedata.getEmsSea());
	//	fpoSiteAllotrepo.updfpoallotsiteltr(sitecode,sitedata.getLetterAir(),sitedata.getLetterSal(),sitedata.getLetterSea());
	//	fpoSiteAllotrepo.updfpoallotsitepar(sitecode,sitedata.getParcelAir(),sitedata.getParcelSal(),sitedata.getParcelSea());
	//	fpoSiteAllotrepo.updfpoallotsiteoth(sitecode,request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("role").toString(),cdate);
		siteupd.setLetterAir(sitedata.getLetterAir());
		siteupd.setLetterSal(sitedata.getLetterSal());
		siteupd.setLetterSea(sitedata.getLetterSea());
		siteupd.setEmsAir(sitedata.getEmsAir());
		siteupd.setEmsSal(sitedata.getEmsSal());
		siteupd.setEmsSea(sitedata.getEmsSea());
		siteupd.setParcelAir(sitedata.getParcelAir());
		siteupd.setParcelSal(sitedata.getParcelSal());
		siteupd.setParcelSea(sitedata.getParcelSea());
		siteupd.setUpdDate(new Date());
		siteupd.setSiteCode(sitecode);
		fpoSiteAllotrepo.save(siteupd);
		return sitedata;
	}

}*/

	
	

/*
 * @RequestMapping("/addSiteAllotDetails") public @ResponseBody FPO_SITE_ALLOT
 * updateSiteAllotinfo(Model model,@ModelAttribute FPO_SITE_ALLOT sitedata,
 * HttpServletRequest request, HttpSession session) throws
 * IllegalStateException, IOException { String sitecode
 * =request.getSession().getAttribute("LSMcuSite").toString(); String siteoffid
 * =request.getSession().getAttribute("offId").toString(); String siterole
 * =request.getSession().getAttribute("role").toString(); // added date for
 * storing docname with date and time for unique to show Date sysdt = new
 * Date(); SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 * String date2 = time1.format(sysdt); String date3=date2.replace("/", "");
 * String date4=date3.replace(":", ""); String date6=date4.replace(" ", "");
 * String docName = request.getParameter("docName").replace(".pdf","") + date6
 * +"pu.pdf"; ///////////// Date cdate = new Date(); List<FPO_SITE_ALLOT>
 * siteDetails = fpoSiteAllotrepo.getSite(sitecode); FPO_SITE_ALLOT siteupd =
 * new FPO_SITE_ALLOT(); //Update method with primary key
 * if(siteDetails.get(0).getId() != null) {
 * siteupd.setId(siteDetails.get(0).getId()); }
 * 
 * //
 * fpoSiteAllotrepo.updfpoallotsiteems(sitecode,sitedata.getEmsAir(),sitedata.
 * getEmsSal(),sitedata.getEmsSea()); //
 * fpoSiteAllotrepo.updfpoallotsiteltr(sitecode,sitedata.getLetterAir(),sitedata
 * .getLetterSal(),sitedata.getLetterSea()); //
 * fpoSiteAllotrepo.updfpoallotsitepar(sitecode,sitedata.getParcelAir(),sitedata
 * .getParcelSal(),sitedata.getParcelSea()); //
 * fpoSiteAllotrepo.updfpoallotsiteoth(sitecode,request.getSession().
 * getAttribute("offId").toString(),request.getSession().getAttribute("role").
 * toString(),cdate); String qryreplypath = ospathrepo.getothPath(); File
 * destination = new File(qryreplypath + docName);
 * sitedata.getFilename().transferTo(destination);
 * System.out.println(destination); System.out.println(destination);
 * siteupd.setLetterAir(sitedata.getLetterAir());
 * siteupd.setLetterSal(sitedata.getLetterSal());
 * siteupd.setLetterSea(sitedata.getLetterSea());
 * siteupd.setEmsAir(sitedata.getEmsAir());
 * siteupd.setEmsSal(sitedata.getEmsSal());
 * siteupd.setEmsSea(sitedata.getEmsSea());
 * siteupd.setParcelAir(sitedata.getParcelAir());
 * siteupd.setParcelSal(sitedata.getParcelSal());
 * siteupd.setParcelSea(sitedata.getParcelSea()); siteupd.setUpdDate(new
 * Date()); siteupd.setSiteCode(sitecode); siteupd.setOffid(siteoffid);
 * siteupd.setRole(siterole); siteupd.setDocname(docName);
 * siteupd.setDOC_PATH(destination.getPath()); fpoSiteAllotrepo.save(siteupd);
 * insfpoeditfpoallot(siteDetails.get(0), request); return siteupd; }
 */
	
//	@RequestMapping("/addSiteAllotDetails")
//	public @ResponseBody FPO_SITE_ALLOT updateSiteAllotinfo(Model model,@ModelAttribute FPO_SITE_ALLOT sitedata, HttpServletRequest request, HttpSession session) throws IllegalStateException, IOException {
//		String sitecode =request.getSession().getAttribute("LSMcuSite").toString();
//		String siteoffid =request.getSession().getAttribute("offId").toString();
//		String siterole =request.getSession().getAttribute("role").toString();
//		// added date for storing docname with date and time for unique to show
//		Date sysdt = new Date();
//		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		String date2 = time1.format(sysdt);
//		String date3=date2.replace("/", "");
//		String date4=date3.replace(":", "");
//		String date6=date4.replace(" ", "");
//		String docName = request.getParameter("docName").replace(".pdf","") + date6 +"pu.pdf";
//		/////////////
//		Date cdate = new Date();
//		List<FPO_SITE_ALLOT> siteDetails = fpoSiteAllotrepo.getSite(sitecode);
//		FPO_SITE_ALLOT siteupd = new FPO_SITE_ALLOT();
//		//Update method with primary key
//		if(siteDetails.get(0).getId() != null) {
//		siteupd.setId(siteDetails.get(0).getId());
//		}
//		
//	//	fpoSiteAllotrepo.updfpoallotsiteems(sitecode,sitedata.getEmsAir(),sitedata.getEmsSal(),sitedata.getEmsSea());
//	//	fpoSiteAllotrepo.updfpoallotsiteltr(sitecode,sitedata.getLetterAir(),sitedata.getLetterSal(),sitedata.getLetterSea());
//	//	fpoSiteAllotrepo.updfpoallotsitepar(sitecode,sitedata.getParcelAir(),sitedata.getParcelSal(),sitedata.getParcelSea());
//	//	fpoSiteAllotrepo.updfpoallotsiteoth(sitecode,request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("role").toString(),cdate);
//		String qryreplypath = ospathrepo.getothPath();
//		File destination = new File(qryreplypath + docName);
//		String docname1 = request.getParameter("docName");
//		if(docname1 != "") {
//			sitedata.getFilename().transferTo(destination);
//			siteupd.setDocname(docName);
//			siteupd.setDOC_PATH(destination.getPath());
//		}
//		
//		System.out.println(destination);
//		System.out.println(destination);
//		siteupd.setLetterAir(sitedata.getLetterAir());
//		siteupd.setLetterSal(sitedata.getLetterSal());
//		siteupd.setLetterSea(sitedata.getLetterSea());
//		siteupd.setEmsAir(sitedata.getEmsAir());
//		siteupd.setEmsSal(sitedata.getEmsSal());
//		siteupd.setEmsSea(sitedata.getEmsSea());
//		siteupd.setParcelAir(sitedata.getParcelAir());
//		siteupd.setParcelSal(sitedata.getParcelSal());
//		siteupd.setParcelSea(sitedata.getParcelSea());
//		siteupd.setUpdDate(new Date());
//		siteupd.setSiteCode(sitecode);
//		siteupd.setOffid(siteoffid);
//		siteupd.setRole(siterole);
//		//siteupd.setDocname(docName);
//		//siteupd.setDOC_PATH(destination.getPath());
//		fpoSiteAllotrepo.save(siteupd);
//		insfpoeditfpoallot(siteDetails.get(0), request);
//		return siteupd;
//	}
	
	
	
	
	@RequestMapping("/addSiteAllotDetails")
	public @ResponseBody FPO_SITE_ALLOT updateSiteAllotinfo(Model model,@ModelAttribute FPO_SITE_ALLOT sitedata, HttpServletRequest request, HttpSession session) throws Exception {
		String sitecode =request.getSession().getAttribute("LSMcuSite").toString();
		String siteoffid =request.getSession().getAttribute("offId").toString();
		String siterole =request.getSession().getAttribute("role").toString();
		// added date for storing docname with date and time for unique to show
		Date sysdt = new Date();
		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date2 = time1.format(sysdt);
		String date3=date2.replace("/", "");
		String date4=date3.replace(":", "");
		String date6=date4.replace(" ", "");
		String docName = sitedata.getDocname().replace(".pdf","") + date6 +"pu.pdf";
		/////////////
		Date cdate = new Date();
		List<FPO_SITE_ALLOT> siteDetails = fpoSiteAllotrepo.getSite(sitecode);
		FPO_SITE_ALLOT siteupd = new FPO_SITE_ALLOT();
		//Update method with primary key
		if(siteDetails.get(0).getId() != null) {
		siteupd.setId(siteDetails.get(0).getId());
		}
		
	//	fpoSiteAllotrepo.updfpoallotsiteems(sitecode,sitedata.getEmsAir(),sitedata.getEmsSal(),sitedata.getEmsSea());
	//	fpoSiteAllotrepo.updfpoallotsiteltr(sitecode,sitedata.getLetterAir(),sitedata.getLetterSal(),sitedata.getLetterSea());
	//	fpoSiteAllotrepo.updfpoallotsitepar(sitecode,sitedata.getParcelAir(),sitedata.getParcelSal(),sitedata.getParcelSea());
	//	fpoSiteAllotrepo.updfpoallotsiteoth(sitecode,request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("role").toString(),cdate);
		String qryreplypath = ospathrepo.getothPath();
		File destination = new File(qryreplypath + docName);
		String docname1 = request.getParameter("docname1");
		
		long maxFileSize = 1 * 1024 * 1024;
		String originalFilename = null;
		String fileExtension = "";
		MultipartFile mul = sitedata.getFilename() ;
		if (mul != null) {
		   originalFilename = sitedata.getFilename().getOriginalFilename(); 
		int dotIndex = originalFilename.lastIndexOf(".");
        
        if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
            fileExtension = originalFilename.substring(dotIndex + 1).toLowerCase();
            
            if (!"pdf".equalsIgnoreCase(fileExtension)) 
                return null; 
            
            
            
            if (originalFilename.contains("<") || originalFilename.contains(">") || originalFilename.contains("*") || originalFilename.contains("?")) 
                return null; 
            
    		
            if (destination.isFile() || sitedata.getFilename().getSize() > maxFileSize) 
                return null; 
            
         }
        }

		if(inputvalidate.Validanum(sitedata.getLetterAir()) == 1 && sitedata.getLetterAir() != null ) 
		    return null;
		
		
		if(inputvalidate.Validanum(sitedata.getLetterSal()) == 1 && sitedata.getLetterSal() != null ) 
		    return null;
		
		if(inputvalidate.Validanum(sitedata.getLetterSea()) == 1 && sitedata.getLetterSea() != null) 
		    return null;
		
		
		if(inputvalidate.Validanum(sitedata.getEmsAir()) == 1 && sitedata.getEmsAir() != null) 
		    return null;
		
		if(inputvalidate.Validanum(sitedata.getEmsSal()) == 1  && sitedata.getEmsSal() !=null) 
		    return null;
		
		if(inputvalidate.Validanum(sitedata.getEmsSea()) == 1 && sitedata.getEmsSea() != null ) 
		    return null;
		
		
		if(inputvalidate.Validanum(sitedata.getParcelAir()) == 1  && sitedata.getParcelAir() != null) 
		    return null;
		
		if(inputvalidate.Validanum(sitedata.getParcelSal()) == 1 && sitedata.getParcelSal() != null) 
		    return null;
		
		if(inputvalidate.Validanum(sitedata.getParcelSea()) == 1 && sitedata.getParcelSea() != null) 
		    return null;
		
		if(Integer.parseInt(docname1) != 1) {
			sitedata.getFilename().transferTo(destination);
			siteupd.setDocname(docName);
			siteupd.setDOC_PATH(destination.getPath());
		}
		
		System.out.println(destination);
		System.out.println(destination);
		siteupd.setLetterAir(sitedata.getLetterAir());
		siteupd.setLetterSal(sitedata.getLetterSal());
		siteupd.setLetterSea(sitedata.getLetterSea());
		siteupd.setEmsAir(sitedata.getEmsAir());
		siteupd.setEmsSal(sitedata.getEmsSal());
		siteupd.setEmsSea(sitedata.getEmsSea());
		siteupd.setParcelAir(sitedata.getParcelAir());
		siteupd.setParcelSal(sitedata.getParcelSal());
		siteupd.setParcelSea(sitedata.getParcelSea());
		siteupd.setUpdDate(new Date());
		siteupd.setSiteCode(sitecode);
		siteupd.setOffid(siteoffid);
		siteupd.setRole(siterole);
		//siteupd.setDocname(docName);
		//siteupd.setDOC_PATH(destination.getPath());
		fpoSiteAllotrepo.save(siteupd);
		insfpoeditfpoallot(siteDetails.get(0), request);
		return siteupd;
	}
	
/*	 @Scheduled(cron = "0 0/1 * * * *")
	  public void fetchempupdemproles() throws Exception {
//				 try {
		 Date currentdate = new Date();
			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date1 = time.format(currentdate);
			Date date = time.parse(date1);
		List<Collection> delempdata =  FPOrepost.getmodempdeldata();
		 int j = delempdata.size();
		 System.out.println("size is"+j);
		 for (Object column : delempdata)
       {
       	Object[] stringArray = (Object[]) column;
       	String userid = stringArray[0].toString();
    	String cusite = null;
    	List<D_EMP_ROLES> emproles;
       	if (stringArray[1] != null)
       	{ cusite = stringArray[1].toString();
       	  if (Demprepo.validsite(cusite) > 0)
             emproles = demproleNSM.getemprolesdata(userid, cusite);
       	  else
       		 {emproles = demproleNSM.getemprolesdata(userid);
       		  cusite=null;}
       		 }
       	else
       	 emproles = demproleNSM.getemprolesdata(userid);
       	System.out.println("userid is " + userid);
       	System.out.println("cus site is " + cusite);
       	emproles.get(0).setEndDt(date);
       	emproles.get(0).setRevokedBy("SYSTEM");
       	emproles.get(0).setReason("SI REVOKED THE ACCESS FROM THE APPLICATION");
       	inseditroles(emproles.get(0),"D");
       	if (cusite==null)
       		demproleNSM.Deleteoldsite(userid);
       	else
       	    demproleNSM.Deleteoldsite(cusite, userid);
       }
		 
		List<Collection> chgempdata =  FPOrepost.getmodempchgdata(); 
		j=chgempdata.size();
		 System.out.println("size is"+j);
		 for (Object column : chgempdata)
       {
       	Object[] stringArray = (Object[]) column;
       	String userid = stringArray[0].toString();
       	String cusite = stringArray[2].toString();
          System.out.println("userid is " + userid);
       	System.out.println("cus site is " + cusite);
       	if (Demprepo.validsite(cusite) > 0)
       	{  List<D_EMP_ROLES> emproles = demproleNSM.getemprolesdata(userid, cusite);  
       	if (emproles.size() > 0)
       	{emproles.get(0).setEndDt(date);
       	emproles.get(0).setRevokedBy("SYSTEM");
       	emproles.get(0).setReason("SI REMAPPED THE SITE IN THE APPLICATION");
       	inseditroles(emproles.get(0),"D");
       	demproleNSM.Deleteoldsite(cusite, userid);}}
       }		       
	 }*/
}
