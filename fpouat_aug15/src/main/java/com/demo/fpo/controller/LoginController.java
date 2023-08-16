package com.demo.fpo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.el.parser.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.NsmLsmRepo.D_EMP_ROLErepo_NSM;
import com.demo.fpo.NsmLsmRepo.FpoSiteMgmtRepo;
import com.demo.fpo.apirepository.DutyCalcDetailsRepo;
import com.demo.fpo.bean.ActiveuserLSM;
import com.demo.fpo.bean.AlertBean;
import com.demo.fpo.bean.ArticleArrInfo;
import com.demo.fpo.bean.CountrywiseOOCPending;
import com.demo.fpo.bean.CtryWsAssCountPercentage;
import com.demo.fpo.bean.CtryWsAssPercentage;
import com.demo.fpo.bean.CtryWsInnerPercentage;
import com.demo.fpo.bean.CtryWsMidPercentage;
import com.demo.fpo.bean.CtryWsPercentage;
import com.demo.fpo.bean.DcallHistory2;
import com.demo.fpo.bean.DcallHistory3;
import com.demo.fpo.bean.MailclassCountryWise;
import com.demo.fpo.bean.OOCDuty;
import com.demo.fpo.bean.OocPercentage;
import com.demo.fpo.bean.PincodeCount;
import com.demo.fpo.bean.TotalArticlesCount;
import com.demo.fpo.bean.TotalCountMailClass;
import com.demo.fpo.bean.TotalIcCount;
import com.demo.fpo.bean.TotalImportCounts;
import com.demo.fpo.model.AMEND_ADD_REAS;
import com.demo.fpo.model.CUSRSP_SENT;
import com.demo.fpo.model.DCALLQRY_GEN;
import com.demo.fpo.model.DECI_REAS;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.model.D_EMP_ROLES;
import com.demo.fpo.model.DeliveryArticlesColumns;
import com.demo.fpo.model.FPO_AC_CMTS;
import com.demo.fpo.model.FPO_AMEND;
import com.demo.fpo.model.FPO_APR_CMTS;
import com.demo.fpo.model.FPO_ASS_PAO_CMTS;
import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIL;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FPO_MANUAL_COMMERCIAL;
import com.demo.fpo.model.FPO_MAP_SITE;
import com.demo.fpo.model.FPO_OOC;
import com.demo.fpo.model.FPO_ORDER;
import com.demo.fpo.model.FPO_RE_CALL;
import com.demo.fpo.model.FPO_SETASIDE;
import com.demo.fpo.model.Feedback;
import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoFine;
import com.demo.fpo.model.FpoItemDetOthDuty;
import com.demo.fpo.model.FpoItemDetOthDutyAmend;
import com.demo.fpo.model.FpoMvmnt;
import com.demo.fpo.model.FpoPenal;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryDecision;
import com.demo.fpo.model.FpoQueryDin;
import com.demo.fpo.model.FpoSecondDefaultQuery;
import com.demo.fpo.model.Fpo_Alert;
import com.demo.fpo.model.Fpo_Alert_View;
import com.demo.fpo.model.Fpo_Item_Query;
import com.demo.fpo.model.Fpo_qry_raise;
import com.demo.fpo.model.Push_dcall;
import com.demo.fpo.model.REALLOCATION;
import com.demo.fpo.model.RaiseComplaints;
import com.demo.fpo.model.Recently_process;
import com.demo.fpo.model.SelectTag;
import com.demo.fpo.prop.PropertyFile;
import com.demo.fpo.repos.AmendAddReasRepo;
import com.demo.fpo.repos.Asstreportrepost;
import com.demo.fpo.repos.CUSRSP_SENTRepo;
import com.demo.fpo.repos.C_CUSITMrepost;
import com.demo.fpo.repos.DECI_REASrepost;
import com.demo.fpo.repos.D_EMP_ROLESrepo;
import com.demo.fpo.repos.D_EMPrepo;
import com.demo.fpo.repos.Dcall_pushRepo;
import com.demo.fpo.repos.FPOManComm_REPO;
import com.demo.fpo.repos.FPO_AC_CMTSRepo;
import com.demo.fpo.repos.FPO_Apr_CMTSRepo;
import com.demo.fpo.repos.FPO_EXAM_FINDINGSrepost;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FPO_ORDERrepost;
import com.demo.fpo.repos.FeedbackRepo;
import com.demo.fpo.repos.FpoAddlQryRepo;
import com.demo.fpo.repos.FpoAlertViewRepo;
import com.demo.fpo.repos.FpoCurQueRepo;
import com.demo.fpo.repos.FpoDcallQRYRepo;
import com.demo.fpo.repos.FpoDetainedInfoRepo;
import com.demo.fpo.repos.FpoFineRepo;
import com.demo.fpo.repos.FpoItemDetOthDutyAmendRepo;
import com.demo.fpo.repos.FpoItemDetOthDutyRepo;
import com.demo.fpo.repos.FpoLoginTrackRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoPenalRepo;
import com.demo.fpo.repos.FpoQueryDecisionRepo;
import com.demo.fpo.repos.FpoQueryDinRepo;
import com.demo.fpo.repos.FpoQueryRepo;
import com.demo.fpo.repos.FpoScanInfoRepo;
import com.demo.fpo.repos.Fpo_AlertRepo;
import com.demo.fpo.repos.Fpoallot_repo;
import com.demo.fpo.repos.MailSeqRepo;
import com.demo.fpo.repos.OsPathFpoRepo;
import com.demo.fpo.repos.RaiseComplaintsRepo;
import com.demo.fpo.service.FpoAmendService;
import com.demo.fpo.service.FpoCurQueService;
import com.demo.fpo.service.FpoDashboardService;
import com.demo.fpo.service.FpoDeclaredService;
import com.demo.fpo.service.FpoFineService;
import com.demo.fpo.service.FpoOrderService;
import com.demo.fpo.service.FpoQueryService;
import com.demo.fpo.service.FpoService;
import com.demo.fpo.service.ReportService;
import com.demo.fpo.service.SearchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.lowagie.text.DocumentException;

@Controller
public class LoginController {

	private static final Logger log = LogManager.getLogger(LoginController.class);

	@Autowired
	RaiseComplaintsRepo risecomplantrepo;

	@Autowired
	OsPathFpoRepo ospathrepo;

	@Autowired
	D_EMPrepo DempFpo;

	@Autowired
	D_EMP_ROLErepo_NSM demproleNSM;

	@Autowired
	FpoSiteMgmtRepo fpositemgmtrepo;

	@Autowired
	FpoMvmntRepo fpomvmntrepo;

	@Resource
	private ReportService reportService;
	@Autowired
	FeedbackRepo feedbcRepo;

	@Autowired
	FPO_ITEM_DETrepost fpoItemDetRepo;

	FpoMvmnt fpomvmnt;
	

	@Autowired
	D_EMP_ROLESrepo Demprole;

	@Autowired
	Asstreportrepost asstreportrepost;

	@Autowired
	C_CUSITMrepost CUSITMrepost;

	@Autowired
	FPO_MAINrepost FPOrepost;

	@Autowired
	FPO_AC_CMTSRepo fpoAcCmts;

	@Autowired
	FPO_Apr_CMTSRepo fpoAprCmts;

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
	FpoAmendService fpoAmendService;

	@Autowired
	FpoCurQueRepo fpoCurQueRepo;

	@Autowired
	FpoCurQueService fpoCurQueService;

	@Autowired
	FpoQueryService fpoQueryService;

	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	FPOManComm_REPO fpoManCommRepo;

	@Autowired
	FpoOrderService fpoOrderService;

	@Autowired
	SearchService searchService;

	@Autowired
	FpoFineService fpoFineService;

	@Autowired
	FpoItemDetOthDutyRepo fpoItemDetOthDutyRepo;

	@Autowired
	FpoItemDetOthDutyAmendRepo fpoItemDetOthDutyAmendRepo;

	@Autowired
	FpoFineRepo fpoFineRepo;

	@Autowired
	AmendAddReasRepo amdaddRepo;

	@Autowired
	FpoPenalRepo fpoPenalRepo;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FpoQueryDinRepo fpoquerydinrepo;

	@Autowired
	FpoQueryDecisionRepo fpoquerydecisionrepo;

	@Autowired
	CUSRSP_SENTRepo cusSentRepo;

	@Autowired
	DutyCalcDetailsRepo dutyCalcDetailsRepo;

	@Resource
	private Dcall_pushRepo dcall_pushRepo;

	@Autowired
	FpoDcallQRYRepo fpoDcallQryRepo;

	@Autowired
	Fpoallot_repo fpoallotRepo;

	@Autowired
	FpoDetainedInfoRepo fpoDetainedInfoRepo;

	@Autowired
	FPO_EXAM_FINDINGSrepost fpoExamFindingsRepost;

	@Autowired
	FpoAddlQryRepo fpoAddlQryRepo;

	@Resource
	private Fpo_AlertRepo fpo_AlertRepo;

	@Autowired
	FpoAlertViewRepo fpoAlertViewRepo;

	@Autowired
	FpoLoginTrackRepo fpoLoginTrackRepo;

	@Autowired
	FpoDashboardService fpoDashboardService;

	@Autowired
	FpoScanInfoRepo fpoScanInfoRepo;

	@Autowired
	MailSeqRepo MailRepo;
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	/*
	 * public static String offId = ""; public static String role = ""; public
	 * static String cuSite = ""; public static String offName = ""; public static
	 * String cinno = "";
	 */
	/*
	 * public static String itemid; public static String to; public static String
	 * filename; public static String tomailid; public static String dinno; public
	 * static String recpname; public static String dcallno; public static String
	 * url; public static String smsurl; public static Long modalshow = 0L;
	 */
	// public static DUTY_CALC_DETAILS dutyCalc = new DUTY_CALC_DETAILS();
	/*
	 * public static String toMobileNumber; public static String
	 * toEnteredMobileNumber;
	 */

//	public String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
//	public DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//	public Date curdt = new Date();

	@RequestMapping("/recentpro")
	public ModelAndView recentpro(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("recentlyprocess");
		String offid = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		// getAttribute("offId") == null ? null :
		// request.getSession().getAttribute("offId").toString())
		List<Recently_process> recentarr = reportService.getrecentproc(offid,role,cusite);
		for (Recently_process recen : recentarr) {

			recen.setCurrentStatus(fpoService.getpos(recen.getITEM_ID(), session, request));
			recen.setRecp_id(FPOrepost.getrecpid(recen.getITEM_ID()));
			recen.setBag_no(FPOrepost.getbagno(recen.getITEM_ID()));
		}
		models.addObject("recent", recentarr);
		model.addAttribute("offId", offid);
		return models;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(Model models, HttpServletRequest request, HttpSession session,HttpServletResponse response) {
	
//	  String ssoid = request.getHeader("OAM_REMOTE_USER");
//	  System.out.println("OAM REMOTE USER FROM HEADER INFORMATION IS " + ssoid);
//	  log.info("sso id in header information is " + ssoid);
	  String ssoid = request.getParameter("OAM_REMOTE_USER");
      System.out.println("OAM REMOTE USER FROM PARAMTER INFORMATION IS " + ssoid);
      log.info("sso id from parameter information is " + ssoid);
      


		// String ssoid="80000415";
		if (ssoid == "" || ssoid == null) {
			System.out.println("ssoid is null");
			log.info("ssoid / OAM_REMOTE_USER is null... now redirecting to error page...");
			ModelAndView modelAndView = new ModelAndView("redirect:/error");
			return modelAndView;
		} else {
			System.out.println("ssoid=" + ssoid);
			log.info("ssoid=" + ssoid);
			log.info("now proceeding...");
			String cussite = DempFpo.getempsite(ssoid);
			if (cussite=="" || cussite==null) {
				System.out.println("No Authentication");
				ModelAndView modelAndView = new ModelAndView("redirect:/error");
				return modelAndView;}
			String dt = FPOrepost.getstdt();
//	    Date curdt = new Date();
			session = request.getSession(true);
			String id = request.getSession().getId();
			log.info("starting session id= " + id);
			request.getSession().setAttribute("stime", dt);
			String sitenm = null;
			if (cussite != null) {
				request.getSession().setAttribute("offId", ssoid);
				sitenm = DempFpo.getsitenm(cussite);
				request.getSession().setAttribute("sitenm", sitenm);
			}
			if (DempFpo.validsite(cussite) >= 1)
				request.getSession().setAttribute("cuSite", cussite);
			log.info("cussite is " + cussite);
			String empName = DempFpo.getempname(
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString());
			log.info("Employee Name is " + empName);
			List<DUTY_CALC_DETAILS> dutyCalc = dutyCalcDetailsRepo.findAll();
			if (!dutyCalc.isEmpty()) {
				// LoginController.dutyCalc = dutyCalc.get(0);
				request.getSession().setAttribute("dutyCalc_assval", dutyCalc.get(0).getAssval_Amt());
				request.getSession().setAttribute("dutyCalc_bcdrta", dutyCalc.get(0).getBcd_Rta());
				request.getSession().setAttribute("dutyCalc_bcdrta_gift", dutyCalc.get(0).getBcdRta_gift());
				request.getSession().setAttribute("dutyCalc_bcdrta_eff", dutyCalc.get(0).getBcdRta_eff());
				request.getSession().setAttribute("dutyCalc_catassval", dutyCalc.get(0).getCatAssval_Amt());
				request.getSession().setAttribute("dutyCalc_category", dutyCalc.get(0).getCategory());
				request.getSession().setAttribute("dutyCalc_igstrta", dutyCalc.get(0).getIgst_Rta());
				request.getSession().setAttribute("dutyCalc_notn", dutyCalc.get(0).getNotn());
				request.getSession().setAttribute("dutyCalc_slno", dutyCalc.get(0).getSlno());
				request.getSession().setAttribute("dutyCalc_swrta", dutyCalc.get(0).getSw_Rta());
				log.info("dutycalc is " + dutyCalc.get(0).getCategory());
			}

			List<D_EMP_ROLES> getcusSite = Demprole.getcusSite(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			if (!getcusSite.isEmpty()) {
				System.out.println("in loop");
				log.info("loop");
				String value1 = getcusSite.get(0).getId();
				log.info("value1 = " + value1);
				String value2 = empName;
				log.info("value2 = " + value2);
				String value3 = getcusSite.get(0).getRoleName();
				log.info("value3 = " + value3);
				String value4 = getcusSite.get(0).getCusSite();
				System.out.println("value4 is " + value4);
				log.info("value4 = " + value4);

				String NsmSiteNme = demproleNSM.getNsmsiteNme(value1);
				String Nsmsitestate = NsmSiteNme.split(",")[1];
				String Nsmsitecde = NsmSiteNme.split(",")[0];

				System.out.println("NSM site code is " + Nsmsitecde);
				log.info("NSM site code is " + Nsmsitecde);

				String lsmsite = fpositemgmtrepo.getLsmSitecde(value1);
				String Lsmsitename = "";
				if (lsmsite != null) {
					Lsmsitename = lsmsite.split(",")[1];
				}
				request.getSession().setAttribute("Lsmsitenme", Lsmsitename);
				request.getSession().setAttribute("LSMcuSite", demproleNSM.LsmSiteCde(value1));

				// offId = value1;
				request.getSession().setAttribute("offId", value1);
				// offName = value2;
				request.getSession().setAttribute("offName", value2);
				// role = value3;
				request.getSession().setAttribute("role", value3);
				// cuSite = value4;
				request.getSession().setAttribute("cuSite", value4);

				log.info("check here 1 in first page");

				request.getSession().setAttribute("data", request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
				request.getSession().setAttribute("data1", request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString());
				request.getSession().setAttribute("data2", request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
				request.getSession().setAttribute("data3", request.getSession().getAttribute("offName") == null ? null
						: request.getSession().getAttribute("offName").toString());
				request.getSession().setAttribute("data4", request.getSession().getAttribute("sitenm") == null ? null
						: request.getSession().getAttribute("sitenm").toString());
				log.info("check here 2 in first page");
				request.getSession().setAttribute("Nsmsitenme", Nsmsitestate);
				request.getSession().setAttribute("Nsmsitecde", Nsmsitecde);
			}
//		List<Collection> obj = fpoService.getloginName(empName);
			log.info("check here 3 in first page");
			fpoService.addLogintrackdetails(session, "login", request);
			log.info("check here 4 in first page");
//		return obj;
			ModelAndView modelAndView = new ModelAndView("redirect:/selrole");
			return modelAndView;
		}
	}

	/*
	 * @RequestMapping("/dash") public ModelAndView dash(Model model) { ModelAndView
	 * modelAndView = new ModelAndView("dash"); return modelAndView; }
	 */

	@RequestMapping("/logout")
	public ModelAndView logout(Model model, HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		System.out.println("attribute names " + request.getSession().getAttributeNames());
		System.out.println("HTTP SESSION IS " + request.getSession().getId());
		System.out.println("LOGIN TIME IS " + request.getSession().getCreationTime());
		System.out.println("Last ACCESSED TIME IS " + request.getSession().getLastAccessedTime());
		System.out.println("INACTIVE TIME IS " + request.getSession().getMaxInactiveInterval());
		log.info("attribute names " + request.getSession().getAttributeNames());
		log.info("HTTP SESSION IS " + request.getSession().getId());
		log.info("LOGIN TIME IS " + request.getSession().getCreationTime());
		log.info("Last ACCESSED TIME IS " + request.getSession().getLastAccessedTime());
		log.info("INACTIVE TIME IS " + request.getSession().getMaxInactiveInterval());
		fpoLoginTrackRepo.updateLogout(curdt, request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		//session.invalidate();
		ModelAndView modelAndView = new ModelAndView("logout");
		return modelAndView;
	}

	@RequestMapping("/Home")
	public ModelAndView menuba(Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("home");
		log.info("testing in dash module");
		session.removeAttribute("flag");
		String id = request.getSession().getId();
		String checkThreshold = "0";
		log.info("in dash module session id= " + id);
		String LastLoginetails = FPOrepost.getlogindetails(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		// model.addAttribute("LastLoginetails", LastLoginetails);
		request.getSession().setAttribute("LastLoginetails", LastLoginetails);
		if (request.getSession().getAttribute("role") == null) {
			fpoService.addLogintrackdetails(session, "login", request);
		}
		
		if(request.getParameter("role").equals("PNA")) {
			checkThreshold =  reportService.getdataFromaclassval();
			if(checkThreshold.equals("1")) {
				model.addAttribute("checkThreshold", checkThreshold);
			}
			else {
				model.addAttribute("checkThreshold", checkThreshold);
			}
			
		}
		model.addAttribute("checkThreshold", checkThreshold);
		
		System.out.println(request.getParameter("role"));
		List<String> CheckRoles = Demprole.getRoles(request.getSession().getAttribute("offId").toString());
		System.out.println("CheckRoles.get(0) "+CheckRoles);
		
		boolean headerpresent=false;
		String roleheader=request.getParameter("role");
		for(int i=0;i<CheckRoles.size();i++) {
			if(roleheader.equals(CheckRoles.get(i))) 
				headerpresent=true;		
		}  
		
		System.out.println(headerpresent);
		
		
		if (request.getParameter("role") != null && headerpresent) {
			String role = request.getParameter("role");
			log.info("role=" + role);
			request.getSession().setAttribute("data1", role);
			request.getSession().setAttribute("role", role);
		}
		else {
			System.out.println("No Authentication");
			 modelAndView = new ModelAndView("redirect:/error");
			return modelAndView;
		}
		

		fpoLoginTrackRepo.updateRolelogin(
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		model.addAttribute("home",1);
		request.getSession().setAttribute("columns", "1");
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		log.info("role = " + role);
		return modelAndView;
	}

	@RequestMapping("/dash")
	public ModelAndView dash(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("dash");
		log.info("testing in dash module");
		session.removeAttribute("flag");
		String id = request.getSession().getId();
		log.info("in dash module session id= " + id);
		String LastLoginetails = FPOrepost.getlogindetails(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		// model.addAttribute("LastLoginetails", LastLoginetails);
		request.getSession().setAttribute("LastLoginetails", LastLoginetails);
		if (request.getSession().getAttribute("role") == null) {
			fpoService.addLogintrackdetails(session, "login", request);
		}
		if (request.getParameter("role") != null) {
			String role = request.getParameter("role");
			log.info("role=" + role);
			request.getSession().setAttribute("data1", role);
			request.getSession().setAttribute("role", role);
		}

		fpoLoginTrackRepo.updateRolelogin(
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());

		request.getSession().setAttribute("columns", "1");
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		log.info("role = " + role);
		Long coultr = FPOrepost.getcoultr(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couems = FPOrepost.getcouems(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long coupar = FPOrepost.getcoupar(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couemp = FPOrepost.getcouemp(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		log.info("check 1");
		Long couass = 0L;
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			log.info("role=PAO comes in first");
			couass = FPOrepost.getcouassView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());
			log.info("in first couass=" + couass);
		}

		else if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAC")) {
			log.info("role=PAC comes in second");
			couass = FPOrepost.getcouassaclView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());
			log.info("in second couass=" + couass);
		}
		log.info("check 1aa");
		Long curcouass = FPOrepost.gettotcouass(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1ab");
		Long coupqry = FPOrepost.getcoupqry(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		log.info("check 1ac");
		Long coupexm = FPOrepost.getcoupexm(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString());
		log.info("check 1ad");
		Long coupooc = FPOrepost.getcoupooc(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1ae");
		Long coupedi = FPOrepost.getcoupedi(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString());

		log.info("check 1a");
		Long curcoudet = FPOrepost.gettotdetsite(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1b");
		Long totcoudet = FPOrepost.gettotdet(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString());
		log.info("check 1c");
		Long totooccancel = FPOrepost.gettotooccancel(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1d");
		Long curcouooccancel = 0L;

		log.info("check 1e");
		curcouooccancel = FPOrepost.gettotooccancel(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());

		log.info("check 1f");
		Long curcouexm = FPOrepost.gettotcouexm(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1g");
		Long curcouooc = FPOrepost.gettotcouooc(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check 1h");
		Long curcouqry = FPOrepost.gettotcouQuery(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());

		log.info("check 2");
		// adding on jan 23rd
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		log.info("cuSite while assigning value is " + cuSite);
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		log.info("offid while assigning value is " + offId);
		Long cusiteCount = FPOrepost.getCountcusite(cuSite, offId);
		log.info("count is " + cusiteCount);
		// Long totgift = FPOrepost.getcougift(cuSite, offId);
		// Long cougiftapr = FPOrepost.getcougiftapr(cuSite, offId);
		// Long cougiftacl = FPOrepost.getcougiftacl(cuSite, offId);
		// Long cousgapr = FPOrepost.getcousgapr(cuSite, offId);
		// Long cousgacl = FPOrepost.getcousgacl(cuSite, offId);
		// Long courgapr = FPOrepost.getcourgapr(cuSite, offId);
		// Long courgacl = FPOrepost.getcourgacl(cuSite, offId);
		// Long coucsapr = FPOrepost.getcoucsapr(cuSite, offId);
		// Long coucsacl = FPOrepost.getcoucsacl(cuSite, offId);
//		Long couothapr = FPOrepost.getcouothapr(cuSite, offId);
		// Long couothacl = FPOrepost.getcouothacl(cuSite, offId);
		// Long coudocapr = FPOrepost.getcoudocapr(cuSite, offId);
		// Long coudocacl = FPOrepost.getcoudocacl(cuSite, offId);
		// Long totsg = FPOrepost.getcousg(cuSite, offId);
		////////////////////////////////////////////////////////////////////////////////// Long
		// totrg = FPOrepost.getcourg(cuSite, offId);
		// Long totcs = FPOrepost.getcoucs(cuSite, offId);
		// Long tototh = FPOrepost.getcouoth(cuSite, offId);
		// Long totdoc = FPOrepost.getcoudoc(cuSite, offId);
		/*
		 * Long coultr = FPOrepost.getcoultr(cuSite, offId); Long couems =
		 * FPOrepost.getcouems(cuSite, offId); Long coupar = FPOrepost.getcoupar(cuSite,
		 * offId); Long couemp = FPOrepost.getcouemp(cuSite, offId);
		 */
		Long aprCusite = FPOrepost.getcouAPR(cuSite, offId);
		Long coultrapr = FPOrepost.getcouAPRltr(cuSite, offId);
		Long couemsapr = FPOrepost.getcouAPRems(cuSite, offId);
		Long couparapr = FPOrepost.getcouAPRpar(cuSite, offId);
		Long couempapr = FPOrepost.getcouAPRemp(cuSite, offId);
		Long aclCusite = FPOrepost.getcouACL(cuSite, offId);
		// Long totacl = FPOrepost.gettotcouACL(cuSite, offId);
		Long coultracl = FPOrepost.getcouACLltr(cuSite, offId);
		Long couemsacl = FPOrepost.getcouACLems(cuSite, offId);
		Long couparacl = FPOrepost.getcouACLpar(cuSite, offId);
		Long couempacl = FPOrepost.getcouACLemp(cuSite, offId);

		
		
		List<DcallHistory3> dcallhistory = reportService.getDcallHistoryPendingPrint(session, request);
		long pendprintcount = dcallhistory.stream().map(p -> p.getItem_id()).count();

		List<DcallHistory3> dcallhistorycot = reportService.getDcallHistoryspeedpostrecordcount(session);
		
		
		String[][] totartcou = null;
		if ((role.equalsIgnoreCase("PNA") || role.equalsIgnoreCase("NRP")
				|| (role.equalsIgnoreCase("ARP") && cuSite.equalsIgnoreCase("INNSA5"))) && role != null) {
			log.info("cms here in PNA");
			TotalArticlesCount totalArticlesCount = fpoDashboardService.getTotalArticleCounts(cuSite, role);
			model.addAttribute("eadcount", totalArticlesCount.getEad());
			model.addAttribute("aaacount", totalArticlesCount.getAaa());
			model.addAttribute("aafcount", totalArticlesCount.getAaf());
		} else {
			totartcou = FPOrepost.gettotartcou(cuSite);
			model.addAttribute("eadcount", totartcou[0][0]);
			model.addAttribute("aaacount", totartcou[0][1]);
			model.addAttribute("aafcount", totartcou[0][2]);
			log.info("cms here in others");
		}

		/*
		 * TotalIcCount totalIcCount =
		 * fpoDashboardService.getItemcategoryCount(cuSite,role);
		 * model.addAttribute("totgift", totalIcCount.getGift());
		 * model.addAttribute("totsg", totalIcCount.getSaleOfGoods());
		 * model.addAttribute("totcs", totalIcCount.getCommercialSample());
		 * model.addAttribute("totrg", totalIcCount.getReturnedGoods());
		 * model.addAttribute("totdoc", totalIcCount.getDocuments());
		 * model.addAttribute("tototh", totalIcCount.getOthers());
		 */

		/*
		 * TotalCountMailClass totalCountMailClass =
		 * fpoDashboardService.gettotcoultr(cuSite,role);
		 * model.addAttribute("totcoultr", totalCountMailClass.getTotcoultr());
		 * model.addAttribute("totcouems", totalCountMailClass.getTotcouems());
		 * model.addAttribute("totcoupar", totalCountMailClass.getTotcoupar());
		 * model.addAttribute("totcouemp", totalCountMailClass.getTotcouemp());
		 */

		String[][] totmcsite = null;
		TotalCountMailClass totalCountMailClass = new TotalCountMailClass();
		if ((role.equalsIgnoreCase("PNA") || role.equalsIgnoreCase("NRP")
				|| (role.equalsIgnoreCase("ARP") && cuSite.equalsIgnoreCase("INNSA5"))) && role != null) {
			totalCountMailClass = fpoDashboardService.gettotcoultr(cuSite, role);
			model.addAttribute("totcoultr", totalCountMailClass.getTotcoultr());
			model.addAttribute("totcouems", totalCountMailClass.getTotcouems());
			model.addAttribute("totcoupar", totalCountMailClass.getTotcoupar());
			model.addAttribute("totcouemp", totalCountMailClass.getTotcouemp());
			log.info("cms here2 in PNA");
		} else {
			totmcsite = FPOrepost.gettotcoumcsite(cuSite);
			model.addAttribute("totcoultr", totmcsite[0][0]);
			model.addAttribute("totcouems", totmcsite[0][1]);
			model.addAttribute("totcoupar", totmcsite[0][2]);
			model.addAttribute("totcouemp", totmcsite[0][3]);
			log.info("cms here in others 1");
		}

		TotalIcCount totalIcCount = new TotalIcCount();
		String[][] toticsite = null;
		if ((role.equalsIgnoreCase("PNA") || role.equalsIgnoreCase("NRP")
				|| (role.equalsIgnoreCase("ARP") && cuSite.equalsIgnoreCase("INNSA5"))) && role != null) {
			totalIcCount = fpoDashboardService.getItemcategoryCount(cuSite, role);
			model.addAttribute("totgift", totalIcCount.getGift());
			model.addAttribute("totsg", totalIcCount.getSaleOfGoods());
			model.addAttribute("totcs", totalIcCount.getCommercialSample());
			model.addAttribute("totrg", totalIcCount.getReturnedGoods());
			model.addAttribute("totdoc", totalIcCount.getDocuments());
			model.addAttribute("tototh", totalIcCount.getOthers());
			log.info("cms here3 in PNA");
		} else {
			toticsite = FPOrepost.gettotcouicsite(cuSite);
			model.addAttribute("totgift", toticsite[0][0]);
			model.addAttribute("totsg", toticsite[0][1]);
			model.addAttribute("totcs", toticsite[0][5]);
			model.addAttribute("totrg", toticsite[0][3]);
			model.addAttribute("totdoc", toticsite[0][2]);
			model.addAttribute("tototh", toticsite[0][4]);
			log.info("cms here in others 2");
		}
		model.addAttribute("curcouooccancel", curcouooccancel);
		model.addAttribute("curcouass", curcouass);
		model.addAttribute("curcouexm", curcouexm);
		model.addAttribute("curcouooc", curcouooc);
		model.addAttribute("curcouqry", curcouqry);
		log.info("cms here in others 3");

		Long recpcou = FPOrepost.getrecpcou(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		Long bagcou = FPOrepost.getbagcou(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		System.out.println("recpcou is " + recpcou);
		System.out.println("bagcou is " + bagcou);
		log.info("recpcou is " + recpcou);
		log.info("bagcou is " + bagcou);
		model.addAttribute("recpcou", recpcou);
		model.addAttribute("bagcou", bagcou);

		String rolenm = "";
		if (role.equals("PAO") | role.equals("PAC"))
			rolenm = "Art.IDs in Assessment";
		else if (role.equals("PSU"))
			rolenm = "Art.IDs in OOC";
		else if (role.equals("PIN"))
			rolenm = "Art.IDs in EXAMINATION";
		else if (role.equals("PBS"))
			rolenm = "Receptacle ID / Bags in Bag Arrival Confirmation / Scan";

		log.info("rolenm=" + rolenm);

		/*
		 * TotalImportCounts totalImportCounts =
		 * fpoDashboardService.getTotalImportCount(cuSite,role);
		 * model.addAttribute("personalImports",
		 * totalImportCounts.getPersonalImports()); model.addAttribute("detained",
		 * totalImportCounts.getDetained()); model.addAttribute("commercialImports",
		 * totalImportCounts.getCommercialImports());
		 * model.addAttribute("quenm",rolenm);
		 */

		TotalImportCounts totalImportCounts = null;
		String totimpcou[][] = null;
		if ((role.equalsIgnoreCase("PNA") || role.equalsIgnoreCase("NRP")
				|| (role.equalsIgnoreCase("ARP") && cuSite.equalsIgnoreCase("INNSA5"))) && role != null) {
			totalImportCounts = fpoDashboardService.getTotalImportCount(cuSite, role);
			model.addAttribute("personalImports", totalImportCounts.getPersonalImports());
			model.addAttribute("detained", totalImportCounts.getDetained());
			model.addAttribute("commercialImports", totalImportCounts.getCommercialImports());
			log.info("cms here4 in PNA");
		} else {
			totimpcou = FPOrepost.getTotalImportCount(cuSite);
			model.addAttribute("personalImports", totimpcou[0][0]);
			model.addAttribute("detained", totimpcou[0][1]);
			model.addAttribute("commercialImports", totimpcou[0][2]);
			log.info("cms here4 in others");
		}
		model.addAttribute("quenm", rolenm);

		List<PincodeCount> pincodeCount = fpoDashboardService.getWpPincode();
		for (PincodeCount pCount : pincodeCount) {
			if (pCount.getCus_site() == null) {
				pCount.setCus_site("Art ID's Pending for Mapping(W/O-Pincode)");
			}
		}
		log.info("pincodeCount is " + pincodeCount);
		model.addAttribute("pincodeCount", pincodeCount);

		log.info("cuSite is " + cuSite);
		// List<EADInboundArticles> eadInboundArticles =
		// fpoDashboardService.getCountryWiseArticles(cuSite);
		List<Collection> eadInboundArticles = FPOrepost.getCountryWiseArticles(cuSite);
		model.addAttribute("eadInboundArticles", eadInboundArticles);
		log.info("check between");
		List<CountrywiseOOCPending> countrywisePendingArticles = fpoDashboardService
				.getCountryWisePendingArticles(cuSite);
		model.addAttribute("countrywisePendingArticles", countrywisePendingArticles);
		log.info("check now 1");
		OocPercentage oocPercentage = fpoDashboardService.getOOCPercentage();

		model.addAttribute("withDutyArticles", oocPercentage.getWithDutyArticles());
		// model.addAttribute("noDutyArticles", oocPercentage.getNoDutyArticles());
		model.addAttribute("noArticles", oocPercentage.getNoArticles());
		model.addAttribute("dutyPayable", oocPercentage.getDutyPayable());
		model.addAttribute("withDutyPercentage", oocPercentage.getWithDutyPercentage());
		// model.addAttribute("noDutyPercentage", oocPercentage.getNoDutyPercentage());
		model.addAttribute("lastMonth", oocPercentage.getLastMonth());
		model.addAttribute("noDutyConcession", oocPercentage.getNoDutyConcession());
		model.addAttribute("noDutyConcessionPercentage", oocPercentage.getNoDutyConcessionPercentage());
		model.addAttribute("totAmount", oocPercentage.getTotAmount());
		// model.addAttribute("noDutyPayable", oocPercentage.getNoDutyPayable());

		log.info("check now 2");

		List<MailclassCountryWise> countryWiseMailClass = fpoDashboardService.getCountryWiseMailClass(cuSite);
		model.addAttribute("countryWiseMailClass", countryWiseMailClass);

		List<CtryWsPercentage> countryWisePercentage = fpoDashboardService.getCountryWisePercentage(cuSite, role);
		model.addAttribute("countryWisePercentage", countryWisePercentage);

		CtryWsMidPercentage ctryWsMidPercentage = fpoDashboardService.getCtryWsMidPercentage(cuSite, role);
		model.addAttribute("ctryWsMidPercentage", ctryWsMidPercentage);

		CtryWsInnerPercentage ctryWsInnerPercentage = fpoDashboardService.getCtryWsInnerPercentage(cuSite, role);
		model.addAttribute("ctryWsInnerPercentage", ctryWsInnerPercentage);
		log.info("check now 3");
		List<CtryWsAssPercentage> countryWiseAssValPercentage = fpoDashboardService
				.getCountryWiseAssValPercentage(cuSite, role);
		model.addAttribute("countryWiseAssValPercentage", countryWiseAssValPercentage);

		CtryWsMidPercentage ctryWsMidAssValPercentage = fpoDashboardService.getCtryWsMidAssValPercentage(cuSite, role);
		model.addAttribute("ctryWsMidAssValPercentage", ctryWsMidAssValPercentage);

		CtryWsInnerPercentage ctryWsInnerAssValPercentage = fpoDashboardService.getCtryWsInnerAssValPercentage(cuSite,
				role);
		model.addAttribute("ctryWsInnerAssValPercentage", ctryWsInnerAssValPercentage);
		log.info("check now 4");
		TotalArticlesCount userWiseArticlesCount = fpoDashboardService.getUserWiseTotalArticleCounts(cuSite, offId,
				role);
		model.addAttribute("userWiseArticlesCount", userWiseArticlesCount);
		String allotmailcat = FPOrepost.getallotmailcat(cuSite, role, offId);
		TotalCountMailClass userwiseMc = fpoDashboardService.getUserwiseMc(cuSite, offId, role, allotmailcat);
		model.addAttribute("userwiseMc", userwiseMc);
		TotalIcCount totalUsWsIcCount = fpoDashboardService.getUsWsItemcategoryCount(cuSite, offId, role);
		model.addAttribute("totalUsWsIcCount", totalUsWsIcCount);

		List<CtryWsAssCountPercentage> allCountryWiseAssValPercentage = fpoDashboardService
				.getAllCountryWiseAssValPercentage(cuSite, role);
		model.addAttribute("allCountryWiseAssValPercentage", allCountryWiseAssValPercentage);
		log.info("check now 5");
		OOCDuty oocTotalDutyDetails = fpoDashboardService.getOOCTotalDutyDetails();
		model.addAttribute("oocTotalDutyDetails", oocTotalDutyDetails);

		// adding on jan 23rd

		/*
		 * String gettotalcuntQQ =
		 * FPOrepost.gettotalcuntQQ(request.getSession().getAttribute("cuSite") == null
		 * ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntEQ =
		 * FPOrepost.gettotalcuntEQ(request.getSession().getAttribute("cuSite") == null
		 * ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntOOCQ =
		 * FPOrepost.gettotalcuntOOCQ(request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntEDIQ =
		 * FPOrepost.gettotalcuntEDIQ(request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString());
		 */

		Long couexm = FPOrepost.getcouexmView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couooc = FPOrepost.getcouoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long coudet = FPOrepost.getcoudetView(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		log.info("check now 6");
		model.addAttribute("couass", couass);
		model.addAttribute("couexm", couexm);
		model.addAttribute("couooc", couooc);
		model.addAttribute("coudet", coudet);
		model.addAttribute("curcoudet", curcoudet);
		model.addAttribute("totcoudet", totcoudet);
		model.addAttribute("totooccancel", totooccancel);
		
		model.addAttribute("pendingprint", pendprintcount);
		model.addAttribute("size", dcallhistorycot.size());

		model.addAttribute("coupqry", coupqry);
		model.addAttribute("coupexm", coupexm);
		model.addAttribute("coupooc", coupooc);
		model.addAttribute("coupedi", coupedi);

		model.addAttribute("coultr", coultr);
		model.addAttribute("couems", couems);
		model.addAttribute("coupar", coupar);
		model.addAttribute("couemp", couemp);

		String mapcdeCS = FPOrepost.getmapcde(cuSite);
		BigDecimal totalCountWp = fpoDashboardService.getTotalCountForWP(cuSite, mapcdeCS);
		model.addAttribute("totalCountWp", totalCountWp);

		if (request.getSession().getAttribute("role").equals("NAL")) {
			modelAndView = new ModelAndView("alertdash");
		} else if (request.getSession().getAttribute("role").equals("PNA")) {
			/* modelAndView = new ModelAndView("admin/nsm/nsm_module"); */
			log.info("redirecting to dashboardpna");
			modelAndView = new ModelAndView("redirect:/dashboardpna");
		} else if (request.getSession().getAttribute("role").equals("PLA")
				|| request.getSession().getAttribute("role").equals("PAA")) {
			modelAndView = new ModelAndView("admin/lsm/Lsm_module");
			totartcou = FPOrepost.gettotartcou(cuSite);
			model.addAttribute("eadcount", totartcou[0][0]);
			model.addAttribute("aaacount", totartcou[0][1]);
			model.addAttribute("aafcount", totartcou[0][2]);
			log.info("check now 7");

			model.addAttribute("totgift", totalIcCount.getGift());
			model.addAttribute("totsg", totalIcCount.getSaleOfGoods());
			model.addAttribute("totcs", totalIcCount.getCommercialSample());
			model.addAttribute("totrg", totalIcCount.getReturnedGoods());
			model.addAttribute("totdoc", totalIcCount.getDocuments());
			model.addAttribute("tototh", totalIcCount.getOthers());

			model.addAttribute("totcoultr", totalCountMailClass.getTotcoultr());
			model.addAttribute("totcouems", totalCountMailClass.getTotcouems());
			model.addAttribute("totcoupar", totalCountMailClass.getTotcoupar());
			model.addAttribute("totcouemp", totalCountMailClass.getTotcouemp());

			model.addAttribute("couass", couass);
			model.addAttribute("couexm", couexm);
			model.addAttribute("couooc", couooc);
			model.addAttribute("coudet", coudet);
			model.addAttribute("curcoudet", curcoudet);
			model.addAttribute("totooccancel", totooccancel);
			model.addAttribute("curcouass", curcouass);

			model.addAttribute("curcouooccancel", curcouooccancel);
			model.addAttribute("curcouexm", curcouexm);
			model.addAttribute("curcouooc", curcouooc);
			model.addAttribute("curcouass", curcouass);
			model.addAttribute("curcouqry", curcouqry);

			for (PincodeCount pCount : pincodeCount) {
				if (pCount.getCus_site() == null) {
					pCount.setCus_site("Art ID's Pending for Mapping(W/O-Pincode)");
				}
			}
			model.addAttribute("pincodeCount", pincodeCount);
			model.addAttribute("eadInboundArticles", eadInboundArticles);
			// model.addAttribute("countrywisePendingArticles", countrywisePendingArticles);

			model.addAttribute("withDutyArticles", oocPercentage.getWithDutyArticles());
			// model.addAttribute("noDutyArticles", oocPercentage.getNoDutyArticles());
			model.addAttribute("noArticles", oocPercentage.getNoArticles());
			model.addAttribute("dutyPayable", oocPercentage.getDutyPayable());
			model.addAttribute("withDutyPercentage", oocPercentage.getWithDutyPercentage());
			// model.addAttribute("noDutyPercentage", oocPercentage.getNoDutyPercentage());
			model.addAttribute("lastMonth", oocPercentage.getLastMonth());
			model.addAttribute("noDutyConcession", oocPercentage.getNoDutyConcession());
			model.addAttribute("noDutyConcessionPercentage", oocPercentage.getNoDutyConcessionPercentage());
			model.addAttribute("totAmount", oocPercentage.getTotAmount());
			// model.addAttribute("noDutyPayable", oocPercentage.getNoDutyPayable());

			model.addAttribute("countryWiseMailClass", countryWiseMailClass);
			model.addAttribute("countryWisePercentage", countryWisePercentage);
			model.addAttribute("ctryWsMidPercentage", ctryWsMidPercentage);
			model.addAttribute("ctryWsInnerPercentage", ctryWsInnerPercentage);
			model.addAttribute("countryWiseAssValPercentage", countryWiseAssValPercentage);
			model.addAttribute("ctryWsMidAssValPercentage", ctryWsMidAssValPercentage);
			model.addAttribute("ctryWsInnerAssValPercentage", ctryWsInnerAssValPercentage);

			model.addAttribute("userWiseArticlesCount", userWiseArticlesCount);
			model.addAttribute("userwiseMc", userwiseMc);
			model.addAttribute("totalUsWsIcCount", totalUsWsIcCount);
			log.info("check now 8");

			model.addAttribute("totgift", toticsite[0][0]);
			model.addAttribute("totsg", toticsite[0][1]);
			model.addAttribute("totcs", toticsite[0][2]);
			model.addAttribute("totrg", toticsite[0][3]);
			model.addAttribute("totdoc", toticsite[0][4]);
			model.addAttribute("tototh", toticsite[0][5]);

			model.addAttribute("totcoultr", totmcsite[0][0]);
			model.addAttribute("totcouems", totmcsite[0][1]);
			model.addAttribute("totcoupar", totmcsite[0][2]);
			model.addAttribute("totcouemp", totmcsite[0][3]);

			model.addAttribute("countryWiseMailClass", countryWiseMailClass);
			model.addAttribute("countryWisePercentage", countryWisePercentage);
			model.addAttribute("ctryWsInnerPercentage", ctryWsInnerPercentage);
			model.addAttribute("ctryWsMidPercentage", ctryWsMidPercentage);
			model.addAttribute("countryWiseAssValPercentage", countryWiseAssValPercentage);
			model.addAttribute("ctryWsMidAssValPercentage", ctryWsMidAssValPercentage);
			model.addAttribute("ctryWsInnerAssValPercentage", ctryWsInnerAssValPercentage);
			model.addAttribute("userWiseArticlesCount", userWiseArticlesCount);
			model.addAttribute("userwiseMc", userwiseMc);
			model.addAttribute("totalUsWsIcCount", totalUsWsIcCount);
			model.addAttribute("allCountryWiseAssValPercentage", allCountryWiseAssValPercentage);
			model.addAttribute("oocTotalDutyDetails", oocTotalDutyDetails);
			model.addAttribute("allCountryWiseAssValPercentage", allCountryWiseAssValPercentage);
			model.addAttribute("oocTotalDutyDetails", oocTotalDutyDetails);

			BigDecimal totalActiveUsersCount = fpoDashboardService.getTotalActiveUsersCount(cuSite, offId);
			model.addAttribute("totalActiveUsersCount", totalActiveUsersCount);

			// List<ActiveuserLSM>
			// activeUserLSM=fpoDashboardService.getActiveUserLSM(cuSite,offId);
			// model.addAttribute("activeUserLSM", activeUserLSM);
			log.info("check now 9");
			BigDecimal totalActiveUsersNoRoleCount = fpoDashboardService.getTotalActiveUsersNoRoleCount(cuSite, offId);
			model.addAttribute("totalActiveUsersNoRoleCount", totalActiveUsersNoRoleCount);
			log.info("check now 10");
		}

		return modelAndView;
	}

	@RequestMapping("/header")
	public ModelAndView header(Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("header");

		model.addAttribute("logoutimg", FPOrepost.getimagespath() + "logout_fpo.jpg");
		String LastLoginetails = FPOrepost.getlogindetails(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		// model.addAttribute("LastLoginetails", LastLoginetails);
		request.getSession().setAttribute("LastLoginetails", LastLoginetails);
		return modelAndView;
	}

	@RequestMapping("/order")
	public ModelAndView order(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		System.out.println("cinNo is " + cinNo);
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.examOrder(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();

			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				model.addAttribute("getOrder", fpoorder);

			} else {

				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					model.addAttribute("getOrder", fpoorder);
				}
			}

		} else {

			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		String acloffid = fpoMain.get(0).getACL_OFFID();
		System.out.println("item id is " + fpoMain.get(0).getITEM_ID());
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E3", session, request);
		fpoCurQueService.updateUSerEnterStatus(cinNo, "E3", session, "E1", request);
		fpoService.setFpoMainValues(fpoMain);
		model.addAttribute("qrycou", FPOrepost.getqrycou(cinNo));
		model.addAttribute("ordcou", FPOrepost.getexmordcou(cinNo));
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("acloffid", acloffid);
		ModelAndView modelAndView = new ModelAndView("EAD/order");
		return modelAndView;

	}

	@RequestMapping("/imp_order")
	public ModelAndView imp_order(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		System.out.println("cinNo is " + cinNo);
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.examOrder(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();

			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				model.addAttribute("getOrder", fpoorder);

			} else {

				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					model.addAttribute("getOrder", fpoorder);
				}
			}

		} else {

			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		String acloffid = fpoMain.get(0).getACL_OFFID();
		System.out.println("item id is " + fpoMain.get(0).getITEM_ID());
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "P9", session, request);
		fpoCurQueService.updateUSerEnterStatus(cinNo, "P9", session, "P1", request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		ModelAndView modelAndView = new ModelAndView("import/imp_order");
		String asscomp;
		if (FPO_ITEMrepost.getasscompCount(cinNo) > 0)
			asscomp = "NO";
		else
			asscomp = "YES";
		model.addAttribute("asscomp", asscomp);
		model.addAttribute("head", (fpoMain.get(0)));
		//model.addAttribute("acloffid", acloffid);
		Long counoqry = FPOrepost.getcounoqry(cinNo);
		model.addAttribute("qryraised", counoqry > 0 ? true : false);

		Long examFindings = fpoExamFindingsRepost.findCountByCinNo(cinNo);
		model.addAttribute("examFindings", examFindings > 0 ? true : false);
		Long lastDetained = fpomvmntrepo.getDetainedCount(cinNo);
		model.addAttribute("lastDetained", lastDetained > 0);
		model.addAttribute("lastDetainedNo", fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.get(0).getITEM_ID()));
		Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
		model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
		return modelAndView;

	}

	@RequestMapping("/pen_order")
	public ModelAndView penorder(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.examOrder(cinNo);

		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();

			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				model.addAttribute("getOrder", fpoorder);

			} else {

				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					model.addAttribute("getOrder", fpoorder);
				}
			}

		} else {

			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		Boolean back = Boolean.valueOf(request.getParameter("back"));
		if (!back)
			fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "N3", session, request);
		fpoCurQueService.updateUSerEnterStatus(cinNo, "E3", session, "E1", request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("hasOrder", fpoorder);
	//	model.addAttribute("curque",
	//			fpoCurQueRepo.getcurqueCin(cinNo, request.getSession().getAttribute("cuSite") == null ? null
	//					: request.getSession().getAttribute("cuSite").toString()));
		model.addAttribute("curque",fpoCurQueRepo.getcurqueCin(cinNo));
		ModelAndView modelAndView = new ModelAndView("EAD/pen_order");
		return modelAndView;

	}

	@RequestMapping(value = "/setaside", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_SETASIDE setaside(@RequestBody FPO_SETASIDE fposetaside, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		System.out.println("FPOSETASIDE: cin no" + fposetaside.getCin_No());
		System.out.println("FPOSETASIDE: reas" + fposetaside.getReas());
		return fpoService.inssetaside(fposetaside, session, request);
	}

	@RequestMapping("/first_check")
	public ModelAndView first_check(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.firstCheck(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				model.addAttribute("getOrder", fpoorder);

			} else {

				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					model.addAttribute("getOrder", fpoorder);
				}
			}

		} else {

			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E3", session, request);
		fpoCurQueService.updateUSerEnterStatus(cinNo, "E3", session, "E1", request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		ModelAndView modelAndView = new ModelAndView("EAD/first_check");
		return modelAndView;

	}

	@RequestMapping("/pfirst_check")
	public ModelAndView pfirst_check(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.firstCheck(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				model.addAttribute("getOrder", fpoorder);

			} else {

				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
					model.addAttribute("getOrder", fpoorder);
				}

				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					model.addAttribute("getOrder", fpoorder);
				}
			}

		} else {

			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E3", session, request);
		fpoCurQueService.updateUSerEnterStatus(cinNo, "E3", session, "E1", request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		ModelAndView modelAndView = new ModelAndView("EAD/pen_first_check");
		return modelAndView;
	}

//	@RequestMapping("/ead_callletter")
//	public ModelAndView ead_callletter(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
//			HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session)
//			throws DocumentException, IOException {
//
//		fpoService.insertFpoQuery(fpo_item_det, "E", session,request);
//
//		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpo_item_det.getCinNo());
//
//		System.out.println("ACL ASS VALUE IS " + FPOrepost.getaclassval());
//
//		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval()
//				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAO"))) {
//
//			FPOrepost.updateRole(fpoMain.get(0).getId());
//			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:ead_list");
//
//			return modelAndView;
//
//		}
//		/*
//		 * else {
//		 * 
//		 * if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() &&
//		 * fpoMain.get(0).getROLE().equals("PAO")) {
//		 * 
//		 * org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
//		 * "redirect:/ead_query?id=" + fpo_item_det.getCinNo());
//		 * 
//		 * return modelAndView;
//		 * 
//		 * }
//		 */
//		else {
//			System.out.println("cms here");
//			log.info("before invoking dcall");
//
//			String cinNo = fpo_item_det.getCinNo();
//			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
//			String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNo());
//			String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNo());
//			List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery);
//			List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
//			List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
//log.info("before calling service qry pdf");
//			fpoService.fpo_qry_crpdf(fpo_item_det.getCinNo(), "E", fpo_item_det.getDin(), session,request);
//			log.info("after calling service qry pdf");
//			ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
//			model.addAttribute("fpoMainData", fpoMainData.get(0));
//			model.addAttribute("callMemo", getAllFpoQuery);
//			model.addAttribute("getRemarks", getRemarks);
//			model.addAttribute("defualtQueryList", defualtQueryList);
//			model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_item_det.getCinNo()).get(0));
//			String filename = reportService.getFileName(fpoMain.get(0).getITEM_ID());
//			String pathfilename = FPOrepost.getdcallqryviewpath();
//			model.addAttribute("filename", filename);
//			model.addAttribute("succ", 0);
//			model.addAttribute("pathname",pathfilename);
//			model.addAttribute("dcallno", reportService.getDcallNo(fpoMain.get(0).getITEM_ID()));
//			return modelAndView;
//		}
//	}

	@RequestMapping("/ead_callletter")
	public ModelAndView ead_callletter(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
			@ModelAttribute("fpoquery") FpoQuery Fpoquery, HttpServletRequest request, Model model,
			HttpServletResponse response, HttpSession session) throws DocumentException, IOException {
		String cinNo = fpo_item_det.getCinNo();
		FpoQuery offDel = fpoQueryRepo.getOffDet(cinNo); 
		String others = offDel.getMark();
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		fpoService.insertFpoQuery(fpo_item_det, "E", others, session, request);
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpo_item_det.getCinNo());
		System.out.println("ACL ASS VALUE IS " + FPOrepost.getaclassval());
		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval()
				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAO"))) {
			FPOrepost.updateRole(fpoMain.get(0).getId());
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:ead_list");
			return modelAndView;
		}
		/*
		 * else {
		 * 
		 * if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() &&
		 * fpoMain.get(0).getROLE().equals("PAO")) {
		 * 
		 * org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
		 * "redirect:/ead_query?id=" + fpo_item_det.getCinNo());
		 * 
		 * return modelAndView;
		 * 
		 * }
		 */
		else {
			System.out.println("cms here");
			log.info("before invoking dcall");

			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
			String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNo());
			String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNo());
			String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());
			List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
			List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
			List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
			log.info("before calling service qry pdf");
			fpoService.fpo_qry_crpdf(fpo_item_det.getCinNo(), "E", fpo_item_det.getDin(), others, session, request);
			log.info("after calling service qry pdf");
			ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
			model.addAttribute("fpoMainData", fpoMainData.get(0));
			model.addAttribute("callMemo", getAllFpoQuery);
			model.addAttribute("getRemarks", getRemarks);
			model.addAttribute("defualtQueryList", defualtQueryList);
			model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_item_det.getCinNo()).get(0));
			String filename = reportService.getFileName(fpoMain.get(0).getITEM_ID());
			String pathfilename = FPOrepost.getdcallqryviewpath();
			model.addAttribute("filename", filename);
			model.addAttribute("succ", 0);
			model.addAttribute("pathname", pathfilename);
			model.addAttribute("dcallno", reportService.getDcallNo(fpoMain.get(0).getITEM_ID(), cuSite));
			return modelAndView;
		}
	}

	/*
	 * @RequestMapping(value = "/Downloadfile", method =RequestMethod.GET)
	 * 
	 * @ResponseBody public FileSystemResource getFile(HttpServletRequest
	 * request,HttpServletResponse response) { String filename =
	 * request.getParameter("filename"); final File file = new File(filename);
	 * response.setContentType("application/octet-stream");
	 * response.setHeader("Content-Disposition", "attachment;filename="+"file.txt");
	 * return new FileSystemResource(file); }
	 */

//	  @RequestMapping("/feedback1")
//		public ModelAndView feedback(Model model, HttpSession session, HttpServletRequest request) {
//			ModelAndView modelAndView = new ModelAndView("feedback");
//			
//			
//			return modelAndView;
//		}
//
//	  
//	  
//		@RequestMapping(value = "/savefeedback")
//		@ResponseBody
//		public String saveFeedback(HttpServletRequest request, HttpSession session)throws Exception {
//				
//			
//			String fdText = request.getParameter("text");
//			String fdType = request.getParameter("type");
//			
//			String fdDt  = request.getParameter("date");
//			String offId= request.getSession().getAttribute("offId").toString();
//			
//			
//			System.out.println("Feedback Text"+ fdText);
//			System.out.println("Feedback Type "+ fdType);
//			System.out.println("feedback Date " +  fdDt);
//			System.out.println("officer ID" +offId);
//			
//			Feedback fdback = new Feedback();
//			fdback.setFeedback_Text(fdText);
//			fdback.setFeedback_type(fdType);
//			fdback.setOff_id(Long.parseLong(offId));
//			fdback.setFeedback_Dt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fdDt));
//			//fdback.setOffId(Long.parseLong(offId));
//			//fdback.setfeedback_Dt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fdDt)); 
//			
//			
//			 feedbcRepo.save(fdback); 
//			
//			return null;
//		}
	
	
	  @RequestMapping("/feedback1")
			public ModelAndView feedback(Model model, HttpSession session, HttpServletRequest request) {
			  String officerId = request.getParameter("offid1");
			  model.addAttribute("officerId", officerId);  
			  session.invalidate();
			  ModelAndView modelAndView = new ModelAndView("feedback");	
			  return modelAndView;
			}

		  
		  
			@RequestMapping(value = "/savefeedback")
			@ResponseBody
			public String saveFeedback(HttpServletRequest request, HttpSession session)throws Exception {
					
				
				String fdText = request.getParameter("text");
				String fdType = request.getParameter("type");
				
				String fdDt  = request.getParameter("date");
				
				String offId  = request.getParameter("offid");
				
				/* String offId= request.getSession().getAttribute("offId").toString(); */
				
				
				System.out.println("Feedback Text"+ fdText);
				System.out.println("Feedback Type "+ fdType);
				System.out.println("feedback Date " +  fdDt);
				System.out.println("officer ID" +offId);
				
				Feedback fdback = new Feedback();
				fdback.setFeedback_Text(fdText);
				fdback.setFeedback_type(fdType);
				fdback.setOff_id(Long.parseLong(offId));
				fdback.setFeedback_Dt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fdDt));
				//fdback.setOffId(Long.parseLong(offId));
				//fdback.setfeedback_Dt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fdDt)); 
				
				
				 feedbcRepo.save(fdback); 
				
				return null;
			}





	
	
	@GetMapping(value = "/viewfile")
	public ResponseEntity<InputStreamResource> getTermsConditions(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = request.getParameter("filename");
		// fileName="d:\\fpodata\\pdf\\DCALL_QRY_VIEW\\UT003015083CZ25082022190320.pdf";

		fileName = "/fpodata/pdf/DCALL_QRY_VIEW/UW269778617NZ25082022200915.pdf";
		File file = new File(fileName);
		HttpHeaders headers = new HttpHeaders();
		// headers.add("content-disposition", "inline;filename=" +fileName);

		InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/viewBEInfo")
	public @ResponseBody ModelAndView viewBEInfo(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView models = new ModelAndView("import/edi_decl_disp");

//			String itemId =  request.getSession().getAttribute("itemid") == null ? null : request.getSession().getAttribute("itemid").toString();

		String itemId = request.getParameter("itemId");

		System.out.println(itemId);
		List<Collection> viewBEInfo = FPOrepost.getViewBEInfo(itemId);
//			for (Collection collection : viewBEInfo) {
//				System.out.println(collection);
//			}

		model.addAttribute("viewBEInfo", viewBEInfo);

		return models;
	}

//	@RequestMapping("/aaf_callletter")
//	public ModelAndView aaf_callletter(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
//			HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session)
//			throws DocumentException, IOException {
//
//		fpoService.insertFpoQuery(fpo_item_det, "P", session,request);
//
//		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpo_item_det.getCinNo());
//
//		System.out.println("ACL ASS VALUE IS " + FPOrepost.getaclassval());
//
//		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval()
//				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAO"))) {
//
//			FPOrepost.updateRole(fpoMain.get(0).getId());
//			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/import_list");
//
//			return modelAndView;
//
//		}
//		/*
//		 * else {
//		 * 
//		 * if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() &&
//		 * fpoMain.get(0).getROLE().equals("PAO")) {
//		 * 
//		 * org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
//		 * "redirect:/ead_query?id=" + fpo_item_det.getCinNo());
//		 * 
//		 * return modelAndView;
//		 * 
//		 * }
//		 */
//		else {
//			System.out.println("cms here");
//
//			String cinNo = fpo_item_det.getCinNo();
//			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
//			String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNo());
//			String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNo());
//			List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery);
//			List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
//			List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
//
//			fpoService.fpo_qry_crpdf(fpo_item_det.getCinNo(), "P", fpo_item_det.getDin(), session,request);
//			int succ = 0;
//			succ = fpoService.sendmail(
//					request.getSession().getAttribute("itemid") == null ? null : request.getSession().getAttribute("itemid").toString(),
//					request.getSession().getAttribute("dcallno") == null ? null : request.getSession().getAttribute("dcallno").toString(),
//					session, cinNo,request);
//			ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
//			model.addAttribute("fpoMainData", fpoMainData.get(0));
//			model.addAttribute("callMemo", getAllFpoQuery);
//			model.addAttribute("getRemarks", getRemarks);
//			model.addAttribute("defualtQueryList", defualtQueryList);
//			model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_item_det.getCinNo()).get(0));
//
//			String filename = reportService.getFileName(fpoMain.get(0).getITEM_ID());
//			model.addAttribute("filename", filename);
//			model.addAttribute("succ", succ);
//			model.addAttribute("dcallno", reportService.getDcallNo(fpoMain.get(0).getITEM_ID()));
//			return modelAndView;
//		}
//	}

	@RequestMapping("/aaf_callletter")
	public ModelAndView aaf_callletter(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
			HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session)
			throws DocumentException, IOException {
		String others = request.getParameter("others");
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		System.out.println(others);
		fpoService.insertFpoQuery(fpo_item_det, "P", others, session, request);

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpo_item_det.getCinNo());

		System.out.println("ACL ASS VALUE IS " + FPOrepost.getaclassval());

		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval()
				&& (null == fpoMain.get(0).getROLE() || fpoMain.get(0).getROLE().equals("PAO"))) {

			FPOrepost.updateRole(fpoMain.get(0).getId());
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/import_list");

			return modelAndView;

		}
		/*
		 * else {
		 * 
		 * if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() &&
		 * fpoMain.get(0).getROLE().equals("PAO")) {
		 * 
		 * org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
		 * "redirect:/ead_query?id=" + fpo_item_det.getCinNo());
		 * 
		 * return modelAndView;
		 * 
		 * }
		 */
		else {
			System.out.println("cms here");

			String cinNo = fpo_item_det.getCinNo();
			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
			String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNo());
			String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNo());
			String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());
			List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
			List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
			List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);

			fpoService.fpo_qry_crpdf(fpo_item_det.getCinNo(), "P", fpo_item_det.getDin(), others, session, request);
			int succ = 0;
			succ = fpoService.sendmail(
					request.getSession().getAttribute("itemid") == null ? null
							: request.getSession().getAttribute("itemid").toString(),
					request.getSession().getAttribute("dcallno") == null ? null
							: request.getSession().getAttribute("dcallno").toString(),
					session, cinNo, request);
			ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
			model.addAttribute("fpoMainData", fpoMainData.get(0));
			model.addAttribute("callMemo", getAllFpoQuery);
			model.addAttribute("getRemarks", getRemarks);
			model.addAttribute("defualtQueryList", defualtQueryList);
			model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(fpo_item_det.getCinNo()).get(0));

			String filename = reportService.getFileName(fpoMain.get(0).getITEM_ID());
			model.addAttribute("filename", filename);
			model.addAttribute("succ", succ);
			model.addAttribute("dcallno", reportService.getDcallNo(fpoMain.get(0).getITEM_ID(), cuSite));
			return modelAndView;
		}
	}

	@Autowired
	PropertyFile propertyFile;

	@RequestMapping("/getDefualtQuery")
	public @ResponseBody List<String> getDefualtQuery() {
		return fpoService.getDefualtQuery();
	}

	/*
	 * @RequestMapping(value = "/edi_decl_add") public ModelAndView
	 * edi_decl_add(@ModelAttribute("FPO_MANUAL_COMMERCIAL") FPO_MANUAL_COMMERCIAL
	 * fpomancomm, Model model, HttpSession session, HttpServletRequest request)
	 * throws java.text.ParseException { ModelAndView modelAndView = new
	 * ModelAndView("redirect:/edi_decl");
	 * //---------------------------------------------------- //changed for calender
	 * issue -----------------------------------
	 * 
	 * String bedt = fpomancomm.getBEDT(); Date date2 = new
	 * SimpleDateFormat("dd/MM/yyyy").parse(bedt);
	 * 
	 * String chldt = fpomancomm.getCHLDT(); Date date3 = new
	 * SimpleDateFormat("dd/MM/yyyy").parse(chldt);
	 * 
	 * // --------------------------------------------------- end
	 * -----------------------------------------------------------
	 * 
	 * java.util.Date curdt = new java.util.Date(); if (fpomancomm.getIEC() == null)
	 * modelAndView = new ModelAndView("redirect:/edi_decl?id=" +
	 * fpomancomm.getCIN_NO()); else { SimpleDateFormat time = new
	 * SimpleDateFormat("dd/MM/yyy hh:mm:ss"); String offId =
	 * request.getSession().getAttribute("offId") == null ? null :
	 * request.getSession().getAttribute("offId").toString(); String role =
	 * request.getSession().getAttribute("role") == null ? null :
	 * request.getSession().getAttribute("role").toString(); String cuSite =
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString(); FPO_MANUAL_COMMERCIAL
	 * fpomanins = new FPO_MANUAL_COMMERCIAL(); fpomanins.setENTERED_BY(offId);
	 * fpomanins.setENTERED_DT(curdt); fpomanins.setENTERED_DT(curdt);
	 * fpomanins.setBE_NO(fpomancomm.getBE_NO()); fpomanins.setBE_DT(date2); //
	 * -------------------------------- //changed for calender issue
	 * ---------------------------------------
	 * fpomanins.setIEC(fpomancomm.getIEC());
	 * fpomanins.setCIN_NO(fpomancomm.getCIN_NO());
	 * fpomanins.setCIN_DT(FPOrepost.getcinDt(fpomancomm.getITEM_ID()));
	 * fpomanins.setEDI_SITE(fpomancomm.getEDI_SITE());
	 * fpomanins.setCHALLAN_NO(fpomancomm.getCHALLAN_NO());
	 * fpomanins.setCHALLAN_DT(date3); // -------------------------------- //changed
	 * for calender issue ---------------------------------------
	 * fpomanins.setADCODE(fpomancomm.getADCODE());
	 * fpomanins.setLICENSE_NO(fpomancomm.getLICENSE_NO());
	 * fpomanins.setPERMISSION_NO(fpomancomm.getPERMISSION_NO());
	 * fpomanins.setSCHEME_CD(fpomancomm.getSCHEME_CD());
	 * fpomanins.setGSTIN_ID(fpomancomm.getGSTIN_ID());
	 * fpomanins.setITEM_ID(fpomancomm.getITEM_ID()); //
	 * -------------------------------- //changed for calender issue
	 * ---------------------------------------
	 * 
	 * 
	 * String date1 = time.format(fpomancomm.getBEDT()); try { Date date =
	 * time.parse(date1); fpomanins.setBE_DT(date); } catch
	 * (java.text.ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * // --------------------------------------------------- end
	 * -----------------------------------------------------------
	 * 
	 * String date1 = time.format(fpomancomm.getCINDT()); try { Date date =
	 * time.parse(date1); fpomanins.setCIN_DT(date); } catch
	 * (java.text.ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * // -------------------------------- //changed for calender issue
	 * ---------------------------------------
	 * 
	 * 
	 * date1 = time.format(fpomancomm.getCHLDT()); try { Date date =
	 * time.parse(date1); fpomanins.setCHALLAN_DT(date); } catch
	 * (java.text.ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * // --------------------------------------------------- end
	 * ----------------------------------------------------------- String utilDate =
	 * new
	 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime
	 * ()); fpoManCommRepo.save(fpomanins);
	 * FPOrepost.updcommarrscan(fpomancomm.getCIN_NO());
	 * fpomvmntrepo.updextdtstr(fpomancomm.getCIN_NO(), utilDate); Long slno =
	 * fpomvmntrepo.getMaxSlOnCin(fpomancomm.getCIN_NO()); if (null == slno) slno =
	 * Long.valueOf(0); fpoService.insertIntofpoMvmntDb(null,
	 * fpomancomm.getCIN_NO(), fpomanins.getCIN_DT(), new java.util.Date(), slno,
	 * role, "P6", session,request);
	 * fpoCurQueService.addUserQue(fpomancomm.getCIN_NO(), fpomancomm.getITEM_ID(),
	 * "P1", session,request); FPOrepost.updatFpoQryDeci(fpomancomm.getITEM_ID(),
	 * cuSite, role, offId, "P1"); modelAndView = new
	 * ModelAndView("redirect:/import_main?id=" + fpomancomm.getCIN_NO());} return
	 * modelAndView; }
	 */
	
//	@RequestMapping(value = "/edi_decl_add")
//	public ModelAndView edi_decl_add(@ModelAttribute("FPO_MANUAL_COMMERCIAL") FPO_MANUAL_COMMERCIAL fpomancomm, Model model,
//			HttpSession session, HttpServletRequest request) throws java.text.ParseException, IllegalStateException, IOException {
//		ModelAndView modelAndView = new ModelAndView("redirect:/edi_decl");
//		//---------------------------------------------------- //changed for calender issue -----------------------------------
//		
//				String bedt = fpomancomm.getBEDT();
//				Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(bedt);
//				
//				String chldt = fpomancomm.getCHLDT();
//				Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(chldt);
//				
//		// ---------------------------------------------------  end -----------------------------------------------------------
//				
//		java.util.Date curdt = new java.util.Date();
//		if (fpomancomm.getIEC() == null)
//			 modelAndView = new ModelAndView("redirect:/edi_decl?id=" + fpomancomm.getCIN_NO());
//		else
//		{
//			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
//			String offId = request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString();
//			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
//			String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
//			FPO_MANUAL_COMMERCIAL fpomanins = new FPO_MANUAL_COMMERCIAL();
//			fpomanins.setENTERED_BY(offId);
//			fpomanins.setENTERED_DT(curdt);
//			fpomanins.setENTERED_DT(curdt);
//			fpomanins.setBE_NO(fpomancomm.getBE_NO());
//			fpomanins.setBE_DT(date2); // -------------------------------- //changed for calender issue ---------------------------------------
//			fpomanins.setIEC(fpomancomm.getIEC());
//			fpomanins.setCIN_NO(fpomancomm.getCIN_NO());
//			fpomanins.setCIN_DT(FPOrepost.getcinDt(fpomancomm.getITEM_ID()));
//			fpomanins.setEDI_SITE(fpomancomm.getEDI_SITE());
//			fpomanins.setCHALLAN_NO(fpomancomm.getCHALLAN_NO());
//			fpomanins.setCHALLAN_DT(date3); // -------------------------------- //changed for calender issue ---------------------------------------
//			fpomanins.setADCODE(fpomancomm.getADCODE());
//			fpomanins.setLICENSE_NO(fpomancomm.getLICENSE_NO());
//			fpomanins.setPERMISSION_NO(fpomancomm.getPERMISSION_NO());
//			fpomanins.setSCHEME_CD(fpomancomm.getSCHEME_CD());
//			fpomanins.setGSTIN_ID(fpomancomm.getGSTIN_ID());
//			fpomanins.setITEM_ID(fpomancomm.getITEM_ID());
//			String qryreplypath = ospathrepo.getothPath();
//			Date sysdt = new Date();
//			SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			String date4 = time1.format(sysdt);
//			String date5=date4.replace("/", "");
//			String date6=date5.replace(":", "");
//			String date7=date6.replace(" ", "");
//			
//			String bedocname = request.getParameter("bedocname").replace(".pdf","") + date7 +"com.pdf";
//			String invdocname = request.getParameter("invdocname").replace(".pdf","") + date7 +"com.pdf";
//			String challandocname = request.getParameter("challandocname").replace(".pdf","") + date7 +"com.pdf";
//			File destination = new File(qryreplypath + bedocname);
//			File destination1 = new File(qryreplypath + invdocname);
//			File destination2 = new File(qryreplypath + challandocname);
//			String bedocname1 = request.getParameter("bedocname");
//			String invdocname1 = request.getParameter("invdocname");
//			String challandocname1 = request.getParameter("challandocname");
//			if(bedocname1 != "") {
//				fpomanins.setBE_DOC(bedocname);
//				fpomancomm.getFilename1().transferTo(destination);
//			}
//			if(invdocname1 != "") {
//				fpomanins.setINV_DOC(invdocname);
//				
//				fpomancomm.getFilename2().transferTo(destination1);
//			}
//			if(challandocname1 != "") {
//				fpomanins.setCHL_DOC(challandocname);
//				fpomancomm.getFilename3().transferTo(destination2);
//			}
//			
//			
//			
//			// -------------------------------- //changed for calender issue ---------------------------------------
//			
//						/*
//						 * String date1 = time.format(fpomancomm.getBEDT()); try { Date date =
//						 * time.parse(date1); fpomanins.setBE_DT(date); } catch
//						 * (java.text.ParseException e) { // TODO Auto-generated catch block
//						 * e.printStackTrace(); }
//						 */
//						
//						// ---------------------------------------------------  end -----------------------------------------------------------
//						
//						String date1 =  time.format(fpomancomm.getCINDT());
//						try {
//							Date date = time.parse(date1);
//							fpomanins.setCIN_DT(date);
//						} catch (java.text.ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//						// -------------------------------- //changed for calender issue ---------------------------------------
//						
//						/*
//						 * date1 = time.format(fpomancomm.getCHLDT()); try { Date date =
//						 * time.parse(date1); fpomanins.setCHALLAN_DT(date); } catch
//						 * (java.text.ParseException e) { // TODO Auto-generated catch block
//						 * e.printStackTrace(); }
//						 */
//						
//						// ---------------------------------------------------  end -----------------------------------------------------------
//			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
//			
//			
//			
//			
//			fpoManCommRepo.save(fpomanins);
//			FPOrepost.updcommarrscan(fpomancomm.getCIN_NO());
//			fpomvmntrepo.updextdtstr(fpomancomm.getCIN_NO(), utilDate);
//			Long slno = fpomvmntrepo.getMaxSlOnCin(fpomancomm.getCIN_NO());
//			if (null == slno)
//				slno = Long.valueOf(0);
//			fpoService.insertIntofpoMvmntDb(null, fpomancomm.getCIN_NO(), fpomanins.getCIN_DT(), new java.util.Date(), slno,
//					role, "P6", session,request);
//			fpoCurQueService.addUserQue(fpomancomm.getCIN_NO(), fpomancomm.getITEM_ID(), "P1", session,request);
//			FPOrepost.updatFpoQryDeci(fpomancomm.getITEM_ID(), cuSite, role, offId, "P1");
//		    modelAndView = new ModelAndView("redirect:/import_main?id=" + fpomancomm.getCIN_NO());}
//		return modelAndView;
//	}
	
	
	
	@RequestMapping(value = "/edi_decl_add")
	public ModelAndView edi_decl_add(@ModelAttribute("FPO_MANUAL_COMMERCIAL") FPO_MANUAL_COMMERCIAL fpomancomm, Model model,
			HttpSession session, HttpServletRequest request) throws java.text.ParseException, IllegalStateException, IOException {
		ModelAndView modelAndView = new ModelAndView("redirect:/edi_decl");
		//---------------------------------------------------- //changed for calender issue -----------------------------------
		
				String bedt = fpomancomm.getBEDT();
				Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(bedt);
				
				String chldt = fpomancomm.getCHLDT();
				Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(chldt);
				
		// ---------------------------------------------------  end -----------------------------------------------------------
				
		java.util.Date curdt = new java.util.Date();
		if (fpomancomm.getIEC() == null)
			 modelAndView = new ModelAndView("redirect:/edi_decl?id=" + fpomancomm.getCIN_NO());
		else
		{
			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
			String offId = request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString();
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
			FPO_MANUAL_COMMERCIAL fpomanins = new FPO_MANUAL_COMMERCIAL();
			fpomanins.setENTERED_BY(offId);
			fpomanins.setENTERED_DT(curdt);
			fpomanins.setENTERED_DT(curdt);
			fpomanins.setBE_NO(fpomancomm.getBE_NO());
			fpomanins.setBE_DT(date2); // -------------------------------- //changed for calender issue ---------------------------------------
			fpomanins.setIEC(fpomancomm.getIEC());
			fpomanins.setCIN_NO(fpomancomm.getCIN_NO());
			fpomanins.setCIN_DT(FPOrepost.getcinDt(fpomancomm.getITEM_ID()));
			fpomanins.setEDI_SITE(fpomancomm.getEDI_SITE());
			fpomanins.setCHALLAN_NO(fpomancomm.getCHALLAN_NO());
			fpomanins.setCHALLAN_DT(date3); // -------------------------------- //changed for calender issue ---------------------------------------
			fpomanins.setADCODE(fpomancomm.getADCODE());
			fpomanins.setLICENSE_NO(fpomancomm.getLICENSE_NO());
			fpomanins.setPERMISSION_NO(fpomancomm.getPERMISSION_NO());
			fpomanins.setSCHEME_CD(fpomancomm.getSCHEME_CD());
			fpomanins.setGSTIN_ID(fpomancomm.getGSTIN_ID());
			fpomanins.setITEM_ID(fpomancomm.getITEM_ID());
			String qryreplypath = ospathrepo.getothPath();
			Date sysdt = new Date();
			SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date4 = time1.format(sysdt);
			String date5=date4.replace("/", "");
			String date6=date5.replace(":", "");
			String date7=date6.replace(" ", "");
			String bedoc = fpomancomm.getBE_DOC().replace(".pdf","") + date7 +"com.pdf";
			String invdoc = fpomancomm.getINV_DOC().replace(".pdf","") + date7 +"com.pdf";
			String chl = fpomancomm.getCHL_DOC().replace(".pdf","") + date7 +"com.pdf";
			String bedoc1 = request.getParameter("bedoc1");
		
			String invdoc1 = request.getParameter("invdoc1");
			
			String chl1 = request.getParameter("chldoc1");
			
			File destination = new File(qryreplypath + bedoc);
			File destination1 = new File(qryreplypath + invdoc);
			File destination2 = new File(qryreplypath + chl);
			if(Integer.parseInt(bedoc1)!=1) {
				fpomanins.setBE_DOC(bedoc);
				fpomancomm.getFilename1().transferTo(destination);
			}
			if(Integer.parseInt(invdoc1)!= 1) {
				fpomanins.setINV_DOC(invdoc);
				
				fpomancomm.getFilename2().transferTo(destination1);
			}
			if(Integer.parseInt(chl1)!= 1) {
				fpomanins.setCHL_DOC(chl);
				fpomancomm.getFilename3().transferTo(destination2);
			}
			
			
			
			// -------------------------------- //changed for calender issue ---------------------------------------
			
						/*
						 * String date1 = time.format(fpomancomm.getBEDT()); try { Date date =
						 * time.parse(date1); fpomanins.setBE_DT(date); } catch
						 * (java.text.ParseException e) { // TODO Auto-generated catch block
						 * e.printStackTrace(); }
						 */
						
						// ---------------------------------------------------  end -----------------------------------------------------------
						
						String date1 =  time.format(fpomancomm.getCINDT());
						try {
							Date date = time.parse(date1);
							fpomanins.setCIN_DT(date);
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// -------------------------------- //changed for calender issue ---------------------------------------
						
						/*
						 * date1 = time.format(fpomancomm.getCHLDT()); try { Date date =
						 * time.parse(date1); fpomanins.setCHALLAN_DT(date); } catch
						 * (java.text.ParseException e) { // TODO Auto-generated catch block
						 * e.printStackTrace(); }
						 */
						
						// ---------------------------------------------------  end -----------------------------------------------------------
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			
			
			
			
			fpoManCommRepo.save(fpomanins);
			FPOrepost.updcommarrscan(fpomancomm.getCIN_NO());
			fpomvmntrepo.updextdtstr(fpomancomm.getCIN_NO(), utilDate);
			Long slno = fpomvmntrepo.getMaxSlOnCin(fpomancomm.getCIN_NO());
			if (null == slno)
				slno = Long.valueOf(0);
			fpoService.insertIntofpoMvmntDb(null, fpomancomm.getCIN_NO(), fpomanins.getCIN_DT(), new java.util.Date(), slno,
					role, "P6", session,request);
			fpoCurQueService.addUserQue(fpomancomm.getCIN_NO(), fpomancomm.getITEM_ID(), "P1", session,request);
			FPOrepost.updatFpoQryDeci(fpomancomm.getITEM_ID(), cuSite, role, offId, "P1");
		    modelAndView = new ModelAndView("redirect:/import_main?id=" + fpomancomm.getCIN_NO());}
		return modelAndView;
	}

	@RequestMapping(value = "/exam_order_add")
	public ModelAndView exam_order_add(@ModelAttribute("FPO_ORDER") FPO_ORDER fpoOrder, Model model,
			HttpSession session, HttpServletRequest request) {

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoOrder.getCIN_NO());
		java.util.Date curdt = new java.util.Date();
		System.out.println("ACL ASS VALUE IS " + FPOrepost.getaclassval());

		if ((fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval()) && (null == fpoMain.get(0).getROLE()
				|| (fpoMain.get(0).getROLE().equals("PAO") && fpoMain.get(0).getACL_OFFID() == null))) {
			fpoOrderService.updateRole(fpoOrder, session, request);
			fpomvmntrepo.updextdt(fpoOrder.getCIN_NO(), curdt);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/ead_list");
			return modelAndView;
		} else if ((fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval())
				&& (fpoMain.get(0).getROLE().equals("PAO") && fpoMain.get(0).getACL_OFFID() != null)) {
			fpoOrderService.updateRole(fpoOrder, session, request);
			fpomvmntrepo.updextdt(fpoOrder.getCIN_NO(), curdt);
			// org.springframework.web.servlet.ModelAndView modelAndView = new
			// ModelAndView("redirect:/order?id=" + fpoOrder.getCIN_NO());
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/ead_list");
			return modelAndView;
		} else {
			fpoOrderService.saveOrder(fpoOrder, session, request);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
					"redirect:/ead_submit?id=" + fpoOrder.getCIN_NO());
			return modelAndView;
		}
	}

	@RequestMapping(value = "/pen_exam_order_add")
	public ModelAndView pen_exam_order_add(@ModelAttribute("FPO_ORDER") FPO_ORDER fpoOrder, Model model,
			HttpSession session, HttpServletRequest request) {

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoOrder.getCIN_NO());

		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && null == fpoMain.get(0).getROLE()) {
			fpoOrderService.updateRole(fpoOrder, session, request);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/process_ead");
			return modelAndView;
		} else {
			if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && fpoMain.get(0).getROLE().equals("PAO")) {
				fpoOrderService.updateRole(fpoOrder, session, request);
				fpoOrderService.updateQueryType(fpoOrder, session, request);
				org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/process_ead");
				return modelAndView;
			} else {
				fpoOrderService.saveOrder(fpoOrder, session, request);
				FPOrepost.updateRoleApr(fpoOrder.getCIN_NO());
				org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
						"redirect:/process_ead?id=" + fpoOrder.getCIN_NO());
				return modelAndView;
			}
		}
	}
	
	
	
	@RequestMapping(value = "/final_exam_order_add")
	public ModelAndView final_exam_order_add(@ModelAttribute("FPO_ORDER") FPO_ORDER fpoOrder, Model model,
			HttpSession session, HttpServletRequest request) {

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoOrder.getCIN_NO());

		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && null == fpoMain.get(0).getROLE()) {
			fpoOrderService.updateRole(fpoOrder, session, request);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/import_list");
			return modelAndView;
		} else {
			if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && fpoMain.get(0).getROLE().equals("PAO")) {
				fpoOrderService.updateRole(fpoOrder, session, request);
				fpoOrderService.updateQueryType(fpoOrder, session, request);
				org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/import_list");
				return modelAndView;
			} else {
				fpoOrderService.saveOrder(fpoOrder, session, request);
				org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
						"redirect:/import_list?id=" + fpoOrder.getCIN_NO());
				return modelAndView;
			}
		}
	}


	@RequestMapping(value = "/first_order_add")
	public ModelAndView first_order_add(@ModelAttribute("FPO_ORDER") FPO_ORDER fpoOrder, Model model,
			HttpSession session, HttpServletRequest request) {
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoOrder.getCIN_NO());
		// if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && null ==
		// fpoMain.get(0).getROLE()) {
		// fpoOrderService.updateFirstOrder(fpoOrder, session);
		// org.springframework.web.servlet.ModelAndView modelAndView = new
		// ModelAndView("redirect:/ead_list");
		// return modelAndView;
		// } else {
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			fpoOrderService.updateFirstOrder(fpoOrder, session, request);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView("redirect:/ead_list");
			return modelAndView;
		} else {
			fpoOrderService.savefirstOrder(fpoOrder, session, request);
			org.springframework.web.servlet.ModelAndView modelAndView = new ModelAndView(
					"redirect:/ead_submit?id=" + fpoOrder.getCIN_NO());
			return modelAndView;
		}
	}

	@RequestMapping(value = "/allotoffid", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN allotoffid(@RequestBody FPO_MAIN fpomain, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		// fpoCurQueService.updateUSerEnterStatus(fpomain.getId(),
		// request.getSession().getAttribute("role") == null ? null :
		// request.getSession().getAttribute("role").toString());
		return fpoDeclaredService.allotoffid(fpomain, session, request);
	}

	@RequestMapping("/process_ead")
	public ModelAndView process_ead(Model model, HttpSession session, HttpServletRequest request) {
		// Count in Query queue
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		ModelAndView modelAndView = new ModelAndView("process_ead");
		if (role.equals("PAO"))
		{
		
		/*
		 * Dividing Mail Category List<Collection> getmailcatofU =
		 * FPOrepost.getmailcatofU(request.getSession().getAttribute("cuSite") == null ?
		 * null : request.getSession().getAttribute("cuSite").toString());
		 * List<Collection> getmailcatofE =
		 * FPOrepost.getmailcatofE(request.getSession().getAttribute("cuSite") == null ?
		 * null : request.getSession().getAttribute("cuSite").toString());
		 * List<Collection> getmailcatofC =
		 * FPOrepost.getmailcatofC(request.getSession().getAttribute("cuSite") == null ?
		 * null : request.getSession().getAttribute("cuSite").toString());
		 * List<Collection> getmailcatofT =
		 * FPOrepost.getmailcatofT(request.getSession().getAttribute("cuSite") == null ?
		 * null : request.getSession().getAttribute("cuSite").toString());
		 */
		List<Collection> getQueryPendingView = FPOrepost.getQueryPendingView(cuSite, role, offId);
		

		model.addAttribute("getQueryPendingView", getQueryPendingView);
		
		int coupqry =  getQueryPendingView.size();
		
		model.addAttribute("coupqry", coupqry);
		// MailCategory - Count
	 }
		
		else if  (role.equals("PAC"))
		{
			List<Collection> getQueryPendingViewacl = FPOrepost.getQueryPendingViewacl(cuSite, role, offId);
			System.out.println(" testing for size"+getQueryPendingViewacl.size());
			model.addAttribute("getQueryPendingViewacl", getQueryPendingViewacl);
			model.addAttribute("QueryPendingViewaclcounts", getQueryPendingViewacl.size());
		}
		model.addAttribute("session", session);
		List<Collection> getOOCPendingView = FPOrepost.getOOCPendingView(cuSite);
		List<Collection> getExamPendingView = FPOrepost.getExamPendingView(cuSite);
		List<Collection> getEDIPendingView = FPOrepost.getEDIPendingView(cuSite);
		model.addAttribute("getOOCPendingView", getOOCPendingView);
		model.addAttribute("getExamPendingView", getExamPendingView);
		model.addAttribute("getEDIPendingView", getEDIPendingView);
		
		// Count in Exam-Queue
				Long coupexm = FPOrepost.getcoupexm(cuSite, role);
				Long coupexmltr = FPOrepost.getcoupexmltr(cuSite);
				Long coupexmems = FPOrepost.getcoupexmems(cuSite);
				Long coupexmpar = FPOrepost.getcoupexmpar(cuSite);
				Long coupexmemp = FPOrepost.getcoupexmemp(cuSite);
				// Count in OOC-Queue
				Long coupooc = FPOrepost.getcoupooc(cuSite);
				Long coupoocltr = FPOrepost.getcoupoocltr(cuSite);
				Long coupoocems = FPOrepost.getcoupoocems(cuSite);
				Long coupoocpar = FPOrepost.getcoupoocpar(cuSite);
				Long coupoocemp = FPOrepost.getcoupoocemp(cuSite);
				// Count in EDI-Queue
				Long coupedi = FPOrepost.getcoupedi(cuSite, role);
				Long coupediltr = FPOrepost.getcoupediltr(cuSite);
				Long coupediems = FPOrepost.getcoupediems(cuSite);
				Long coupedipar = FPOrepost.getcoupedipar(cuSite);
				Long coupediemp = FPOrepost.getcoupediemp(cuSite);

		List<Collection> coupltrview = FPOrepost.getcoupltrview(cuSite, role, offId);
		List<Collection> coupemsview = FPOrepost.getcoupemsview(cuSite, role, offId);
		List<Collection> coupparview = FPOrepost.getcoupparview(cuSite, role, offId);
		List<Collection> coupempview = FPOrepost.getcoupempview(cuSite, role, offId);
        
		int coupqryltr = 0;
		if (coupltrview.size() > 0)
			  coupqryltr = coupltrview.size() ;
		int coupqryems = 0;
		if (coupemsview.size() > 0)
			  coupqryems=coupemsview.size();
		int coupqrypar=0;
		if (coupparview.size() > 0)
		     coupqrypar = coupparview.size();
		int coupqryemp = 0;
		if (coupempview.size() > 0)
			 coupqryemp = coupempview.size();
		
		
		model.addAttribute("coupqryltr", coupqryltr);
		model.addAttribute("coupqryems", coupqryems);
		model.addAttribute("coupqrypar", coupqrypar);
		model.addAttribute("coupqryemp", coupqryemp);
		
		// Count in Exam_queue
				model.addAttribute("coupexm", coupexm);
				model.addAttribute("coupexmltr", coupexmltr);
				model.addAttribute("coupexmems", coupexmems);
				model.addAttribute("coupexmpar", coupexmpar);
				model.addAttribute("coupexmemp", coupexmemp);
				// Count in OOC_Queue
				model.addAttribute("coupooc", coupooc);
				model.addAttribute("coupoocltr", coupoocltr);
				model.addAttribute("coupoocems", coupoocems);
				model.addAttribute("coupoocpar", coupoocpar);
				model.addAttribute("coupoocemp", coupoocemp);
				// Count in EDI-Queue
				model.addAttribute("coupedi", coupedi);
				model.addAttribute("coupediltr", coupediltr);
				model.addAttribute("coupediems", coupediems);
				model.addAttribute("coupedipar", coupedipar);
				model.addAttribute("coupediemp", coupediemp);
				// Dividing Mail-Category
				model.addAttribute("coupltrview", coupltrview);
				model.addAttribute("coupemsview", coupemsview);
				model.addAttribute("coupparview", coupparview);
				model.addAttribute("coupempview", coupempview);
				
		String dispmailcat = "";

		String dispitcat = "";

		Long fou = 0l;
		Long coucurapr = 0l;
		Long allotparapr = 0l;
		Long allotemsapr = 0l;
		Long allotempapr = 0l;
		Long allotltrapr = 0l;
		Long allotpar = 0l;
		Long allotltr = 0l;
		Long allotems = 0l;
		Long allotemp = 0l;
		String allotmailcat = FPOrepost.getallotmailcat(cuSite, request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString(), offId);

		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			if (allotmailcat.indexOf("U") >= 0) {
				dispmailcat = " U - Letters ";
				fou = 1l;
				// coucurapr = coucurapr + coultr;
				// allotltr = coultr;
				// allotltrapr = coultrapr;
			} else {
				allotltr = 0l;
				allotltrapr = 0l;
			}
			if (allotmailcat.indexOf("E") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " E - EMS ";
				// coucurapr = coucurapr + couems;
				fou = 1l;
				// allotemsapr = couemsapr;
				// allotems = couems;
			} else {
				allotemsapr = 0l;
				allotems = 0l;
			}
			if (allotmailcat.indexOf("C") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " C - Parcels ";
				// coucurapr = coucurapr + coupar;
				fou = 1l;
				// allotparapr = couparapr;
				// allotpar = coupar;
			} else {
				allotparapr = 0l;
				allotpar = 0l;
			}
			if (allotmailcat.indexOf("T") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " T - Emp.Recep.,";
				fou = 1l;
				// coucurapr = coucurapr + couemp;
				// allotemp = couemp;
				// allotempapr = couempapr;
			} else {
				allotempapr = 0l;
				allotemp = 0l;
			}

		}
		model.addAttribute("allotmailcat", allotmailcat);
		model.addAttribute("dispmailcat", dispmailcat);
		return modelAndView;
	}

	@RequestMapping("/import_role")
	public ModelAndView import_role(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("Import_role invoker");
		int couass = 0;
		int courply = 0;
		Long couasssetaside = 0L;
		Long couasssetasideacl = 0L;
		int couassacl = 0;
		int qrycou = 0;
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO"))
			qrycou = FPOrepost.getQueryView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString())
					.size();
		System.out.println("cms here");
		System.out.println("offid is " + request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		System.out.println("role is " + request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			couass = FPOrepost.getPassView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString())
					.size();
			/*
			 * FPOrepost.getcouassView( request.getSession().getAttribute("cuSite") == null
			 * ? null : request.getSession().getAttribute("cuSite").toString(),
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString(),
			 * request.getSession().getAttribute("role") == null ? null :
			 * request.getSession().getAttribute("role").toString());
			 */
			courply = FPOrepost.getQueryreplyView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString())
					.size();
			couass = couass + courply;
			couasssetaside = FPOrepost.getcousetasideimp(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());
		} else if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAC")) {
			System.out.println("in ACL");
			couassacl = FPOrepost.getPassaclView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString())
					.size();
			/*
			 * FPOrepost.getcouassaclView( request.getSession().getAttribute("cuSite") ==
			 * null ? null : request.getSession().getAttribute("cuSite").toString(),
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString(),
			 * request.getSession().getAttribute("role") == null ? null :
			 * request.getSession().getAttribute("role").toString());
			 */
			couasssetasideacl = FPOrepost.getcousetasideaclimp(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());
		}
		System.out.println("cms here over");
	//	Long couexm = FPOrepost.getcouexmView(
	//			request.getSession().getAttribute("cuSite") == null ? null
//						: request.getSession().getAttribute("cuSite").toString(),
//				request.getSession().getAttribute("offId") == null ? null
//						: request.getSession().getAttribute("offId").toString());
		int couexm = FPOrepost
		.getPexmView(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString()).size();
		/*Long couooc = FPOrepost.getcouoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());*/
		int couooc = FPOrepost.getQoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString()).size();
		Long coudet = FPOrepost.getcoudetView(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		ModelAndView modelAndView = new ModelAndView("import/import_role");
		model.addAttribute("couass", couass);
		model.addAttribute("qrycou", qrycou);
		model.addAttribute("couassacl", couassacl);
		model.addAttribute("couexm", couexm);
		model.addAttribute("couooc", couooc);
		model.addAttribute("coudet", coudet);
		model.addAttribute("cousetasideimppao", couasssetaside);
		model.addAttribute("cousetasideimpacl", couasssetasideacl);
		return modelAndView;
	}

	@RequestMapping("/import_search")
	public ModelAndView import_search(Model model, HttpSession session, HttpServletRequest request) {
		List<Collection> s = FPOrepost.getPexmView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		ModelAndView modelAndView = new ModelAndView("import/import_search");
		Long couexm = FPOrepost.getcouexmView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		model.addAttribute("check", s);
		model.addAttribute("couexm", couexm);
		return modelAndView;
	}

	/* ---------------------------------commented by veeman for adding mail class ------------------------------- */
	
//	@RequestMapping("/import_exm_search")
//	public ModelAndView import_exm_search(Model model, HttpSession session, HttpServletRequest request) {
//		Long dupcou = FPOrepost.getdupcouexm(
//				request.getSession().getAttribute("cuSite") == null ? null
//						: request.getSession().getAttribute("cuSite").toString(),
//				request.getSession().getAttribute("offId") == null ? null
//						: request.getSession().getAttribute("offId").toString());
//		System.out.println("count is" + dupcou);
//		if (dupcou > 1)
//			fpomvmntrepo.deldupexm(
//					request.getSession().getAttribute("cuSite") == null ? null
//							: request.getSession().getAttribute("cuSite").toString(),
//					request.getSession().getAttribute("offId") == null ? null
//							: request.getSession().getAttribute("offId").toString());
//		List<Collection> s = FPOrepost.getPexmView(
//				request.getSession().getAttribute("cuSite") == null ? null
//						: request.getSession().getAttribute("cuSite").toString(),
//				request.getSession().getAttribute("offId") == null ? null
//						: request.getSession().getAttribute("offId").toString());
//		Long couexm = FPOrepost.getcouexmView(
//				request.getSession().getAttribute("cuSite") == null ? null
//						: request.getSession().getAttribute("cuSite").toString(),
//				request.getSession().getAttribute("offId") == null ? null
//						: request.getSession().getAttribute("offId").toString());
//		ModelAndView modelAndView = new ModelAndView("import/import_exm_search");
//		model.addAttribute("check", s);
//		model.addAttribute("couexm", couexm);
//		return modelAndView;
//	}
	/* --------------------------------------------------------------------------------------------------------- */
	
	@RequestMapping("/import_exm_search")
	public ModelAndView import_exm_search(Model model, HttpSession session, HttpServletRequest request) {
		Long dupcou = FPOrepost.getdupcouexm(
				request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		System.out.println("count is" + dupcou);
		String dispmailcat = "";
//		if ( dupcou > 1)
		fpomvmntrepo.deldupexm(
				request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		List<Collection> s = FPOrepost
				.getPexmView(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	//	Long couexm = FPOrepost.getcouexmView(
	//			request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		int couexm = s.size();
		String allotmailcat = FPOrepost.getallotmailcat(
				request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
		System.out.println(s);
		dispmailcat = quick_det_common_ooc(allotmailcat);
		ModelAndView modelAndView = new ModelAndView("import/import_exm_search");
		model.addAttribute("check", s);
		model.addAttribute("couexm", couexm);
		model.addAttribute("dispmailcat", dispmailcat);
		return modelAndView;
	}

	@RequestMapping("/import_det_search")
	public ModelAndView import_det_search(Model model, HttpSession session, HttpServletRequest request) {
		List<Collection> s = FPOrepost.getPdetView(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		Long coudet = FPOrepost.getcoudetView(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		ModelAndView modelAndView = new ModelAndView("import/import_det_search");
		model.addAttribute("check", s);
		model.addAttribute("coudet", coudet);
		return modelAndView;
	}

	
	

	@RequestMapping("/ead_list")
	public ModelAndView ead_list(Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("EAD/ead_list");
		//System.out.println("Param value: " + param);
		String flag = (String)session.getAttribute("flag");
		String back=request.getParameter("ead_list_page");
		model.addAttribute("back", back);
		System.out.println(back);
		System.out.println(flag);
		if (null != flag) {
			model.addAttribute("flag", flag); 
			
		}
		String prodoc = request.getParameter("showprodoc");
		String bulk = request.getParameter("showbulk");
		String bulkexp = request.getParameter("showbulkexp");
		System.out.println(prodoc+bulk+bulkexp);
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		List<Collection> s = FPOrepost.getFiltered(cuSite, offId);
		List<Collection> setasideAPRview = FPOrepost.getsetasideaprview(cuSite, offId);
		List<Collection> setasideACLview = FPOrepost.getsetasideaclview(cuSite, offId);
		int cousetasideapr = setasideAPRview.size();
		int cousetasideacl = setasideACLview.size();
		List<Collection> allotview=null;
		List<Collection> ltrview=null;
		List<Collection> emsview=null;
		List<Collection> parview=null;
		List<Collection> empview=null;
		int coultr = 0;
		int couems = 0;
		int coupar = 0;
		int couemp = 0;
//		if(back.equals(null))
//			back="false";;
		if (role.equals("PAO")) {
		//	List<Collection> view = FPOrepost.getview(cuSite, offId);
		//List<Collection> allotview = FPOrepost.getallotview(cuSite, offId);
			
			//before 15/06/2023
//			  ltrview = FPOrepost.getltrview(cuSite, offId);
//				 emsview = FPOrepost.getemsview(cuSite, offId);
//				 parview = FPOrepost.getparview(cuSite, offId);
//				 empview = FPOrepost.getempview(cuSite, offId);
//				allotview = FPOrepost.getallotview(cuSite, offId);
			
		       	//after 15/06/2023
			if (null == flag || back!=null) {
			    ltrview = FPOrepost.getltrview(cuSite, offId);
				 emsview = FPOrepost.getemsview(cuSite, offId);
				 parview = FPOrepost.getparview(cuSite, offId);
				 empview = FPOrepost.getempview(cuSite, offId);
				 if (ltrview.size() > 0)
					coultr = ltrview.size();
				 if (emsview.size()>0)
					couems = emsview.size();
				 if (parview.size() > 0)
					coupar = parview.size();
				 if (empview.size() > 0)
					couemp = empview.size();
				 
				allotview = FPOrepost.getallotview(cuSite, offId);
				model.addAttribute("ltrview", ltrview);
				model.addAttribute("emsview", emsview);
				model.addAttribute("parview", parview);
				model.addAttribute("empview", empview);
				model.addAttribute("allotview", allotview);

			}
			else {
				System.out.println("notgoneinside");
			}
			
			//List<Collection> docview = FPOrepost.getdocview(cuSite, offId,Float.parseFloat(request.getSession().getAttribute("dutyCalc_assval").toString()));
			//List<Collection> bulkview = FPOrepost.getbulkview(cuSite, offId,Float.parseFloat(request.getSession().getAttribute("dutyCalc_assval").toString()));
			
			//Express Assessment button
			//List<Collection> bulkexpview = FPOrepost.getbulkexpview(cuSite, offId,FPOrepost.getaclassval());
			List<Collection> APRview = null;
			APRview = FPOrepost.getAPRview(cuSite, offId);
			List<Collection> APRltrview = null;
			APRltrview = FPOrepost.getAPRltrview(cuSite, offId);
			List<Collection> APRemsview = null;
			APRemsview = FPOrepost.getAPRemsview(cuSite, offId);
			List<Collection> APRparview = null;
			APRparview = FPOrepost.getAPRparview(cuSite, offId);
			List<Collection> APRempview = null;
			APRempview = FPOrepost.getAPRempview(cuSite, offId);
//			model.addAttribute("ltrview", ltrview);
//			model.addAttribute("emsview", emsview);
//			model.addAttribute("parview", parview);
//			model.addAttribute("empview", empview);
//			model.addAttribute("allotview", allotview);
			model.addAttribute("check", s);
			//model.addAttribute("docview", docview);
			//model.addAttribute("bulkview", bulkview);
			//Express Assessment button
			//model.addAttribute("bulkexpview", bulkexpview);
			model.addAttribute("APRview", APRview);
			model.addAttribute("APRltrview", APRltrview);
			model.addAttribute("APRemsview", APRemsview);
			model.addAttribute("APRparview", APRparview);
			model.addAttribute("APRempview", APRempview);
			
		
			int coultrapr = 0;
			int couemsapr = 0;
			int couparapr = 0;
			int couempapr = 0;
			int aprCusite = 0;
			// if (role.equals("PAO")) {
			/*coultr = ltrview.size();
			couems = emsview.size();
			coupar = parview.size();
			couemp = empview.size();*/
			aprCusite = APRview.size();
			model.addAttribute("aprCusite", aprCusite);
			if (APRltrview.size() > 0)
			  coultrapr = APRltrview.size();
			if (APRemsview.size() > 0)
			  couemsapr = APRemsview.size();
			if (APRparview.size() > 0)
			  couparapr = APRparview.size();
			if (APRempview.size() > 0)
			  couempapr = APRempview.size();
			
			String allotmailcat = FPOrepost.getallotmailcat(cuSite, request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString(), offId);
			System.out.println("allotted mail class is " + allotmailcat);
			String dispmailcat = "";

			String dispitcat = "";
			Long fou = 0l;
			int coucurapr = 0;
			int allotparapr = 0;
			int allotemsapr = 0;
			int allotempapr = 0;
			int allotltrapr = 0;
			int allotpar = 0;
			int allotltr = 0;
			int allotems = 0;
			int allotemp = 0;

			if (request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString().equals("PAO")) {
				if (allotmailcat.indexOf("U") >= 0) {
					dispmailcat = " U - Letters ";
					fou = 1l;
					coucurapr = coucurapr + coultr;
					allotltr = coultr;
					allotltrapr = coultrapr;
				} else {
					allotltr = 0;
					allotltrapr = 0;
				}
				if (allotmailcat.indexOf("E") >= 0) {
					if (fou == 1)
						dispmailcat = dispmailcat + ",";
					dispmailcat = dispmailcat + " E - EMS ";
					coucurapr = coucurapr + couems;
					fou = 1l;
					allotemsapr = couemsapr;
					allotems = couems;
				} else {
					allotemsapr = 0;
					allotems = 0;
				}
				if (allotmailcat.indexOf("C") >= 0) {
					if (fou == 1)
						dispmailcat = dispmailcat + ",";
					dispmailcat = dispmailcat + " C - Parcels ";
					coucurapr = coucurapr + coupar;
					fou = 1l;
					allotparapr = couparapr;
					allotpar = coupar;
				} else {
					allotparapr = 0;
					allotpar = 0;
				}
				if (allotmailcat.indexOf("T") >= 0) {
					if (fou == 1)
						dispmailcat = dispmailcat + ",";
					dispmailcat = dispmailcat + " T - Emp.Recep.,";
					fou = 1l;
					coucurapr = coucurapr + couemp;
					allotemp = couemp;
					allotempapr = couempapr;
				} else {
					allotempapr = 0;
					allotemp = 0;
				}
				model.addAttribute("dispmailcat", dispmailcat);
				model.addAttribute("allotmailcat", allotmailcat);
				model.addAttribute("coucurapr", coucurapr);
			}
		/*	int totcoucussite = cusiteCount + aprCusite + aclCusite;
			int totltr = coultr + coultrapr + coultracl;
			int totems = couems + couemsapr + couemsacl;
			int totpar = coupar + couparapr + couparacl;
			int totemp = couemp + couempapr + couempacl;*/
		//	int totapr = cusiteCount + aprCusite;
			int totltrapr = coultr + coultrapr;
			int totemsapr = couems + couemsapr;
			int totparapr = coupar + couparapr;
			int totempapr = couemp + couempapr;
		//	model.addAttribute("totcou", totcoucussite);
		//	model.addAttribute("totapr", totapr);
//			model.addAttribute("totacl", totacl);
			model.addAttribute("totltrapr", totltrapr);
			model.addAttribute("totemsapr", totemsapr);
			model.addAttribute("totparapr", totparapr);
			model.addAttribute("totempapr", totempapr);
		//	model.addAttribute("totltr", totltr);
		//	model.addAttribute("totems", totems);
		//	model.addAttribute("totpar", totpar);
		//	model.addAttribute("totemp", totemp);
			model.addAttribute("cusite", cuSite);
			model.addAttribute("coultrapr", coultrapr);
			model.addAttribute("couemsapr", couemsapr);
			model.addAttribute("couparapr", couparapr);
			model.addAttribute("couempapr", couempapr);
		//	model.addAttribute("cusiteCount", cusiteCount);
			model.addAttribute("coultr", coultr);
			model.addAttribute("couems", couems);
			model.addAttribute("coupar", coupar);
			model.addAttribute("couemp", couemp);
			model.addAttribute("allotltr", allotltr);
			model.addAttribute("allotems", allotems);
			model.addAttribute("allotpar", allotpar);
			model.addAttribute("allotemp", allotemp);
			model.addAttribute("allotltrapr", allotltrapr);
			model.addAttribute("allotemsapr", allotemsapr);
			model.addAttribute("allotparapr", allotparapr);
			model.addAttribute("allotempapr", allotempapr);
			
		}
		if (role.equals("PAC")) {
			List<Collection> ACLview = FPOrepost.getACLview(cuSite, offId);
			List<Collection> ACLltrview = FPOrepost.getACLltrview(cuSite, offId);
			List<Collection> ACLemsview = FPOrepost.getACLemsview(cuSite, offId);
			List<Collection> ACLparview = FPOrepost.getACLparview(cuSite, offId);
			List<Collection> ACLempview = FPOrepost.getACLempview(cuSite, offId);
			model.addAttribute("ACLview", ACLview);
			model.addAttribute("ACLltrview", ACLltrview);
			model.addAttribute("ACLemsview", ACLemsview);
			model.addAttribute("ACLparview", ACLparview);
			model.addAttribute("ACLempview", ACLempview);
		}
		Long cusiteCount = FPOrepost.getCountcusite(cuSite, offId);
		// Long totgift = FPOrepost.getcougift(cuSite, offId);
		// Long cougiftapr = FPOrepost.getcougiftapr(cuSite, offId);
		// Long cougiftacl = FPOrepost.getcougiftacl(cuSite, offId);
		// Long cousgapr = FPOrepost.getcousgapr(cuSite, offId);
		// Long cousgacl = FPOrepost.getcousgacl(cuSite, offId);
		// Long courgapr = FPOrepost.getcourgapr(cuSite, offId);
		// Long courgacl = FPOrepost.getcourgacl(cuSite, offId);
		// Long coucsapr = FPOrepost.getcoucsapr(cuSite, offId);
		// Long coucsacl = FPOrepost.getcoucsacl(cuSite, offId);
		// Long couothapr = FPOrepost.getcouothapr(cuSite, offId);
		// Long couothacl = FPOrepost.getcouothacl(cuSite, offId);
		// Long coudocapr = FPOrepost.getcoudocapr(cuSite, offId);
		// Long coudocacl = FPOrepost.getcoudocacl(cuSite, offId);
		Long cougiftmc = FPOrepost.getcougiftmc(cuSite, offId, role);
		Long cousgmc = FPOrepost.getcousgmc(cuSite, offId, role);
		Long courgmc = FPOrepost.getcourgmc(cuSite, offId, role);
		Long coucsmc = FPOrepost.getcoucsmc(cuSite, offId, role);
		Long couothmc = FPOrepost.getcouothmc(cuSite, offId, role);
		Long coudocmc = FPOrepost.getcoudocmc(cuSite, offId, role);

		// Long totsg = FPOrepost.getcousg(cuSite, offId);
		// Long totrg = FPOrepost.getcourg(cuSite, offId);
		// Long totcs = FPOrepost.getcoucs(cuSite, offId);
		// Long tototh = FPOrepost.getcouoth(cuSite, offId);
		// Long totdoc = FPOrepost.getcoudoc(cuSite, offId);
		// }
//		if (role.equals("PAC")) {
		Long aclCusite = FPOrepost.getcouACL(cuSite, offId);
		// Long totacl = FPOrepost.gettotcouACL(cuSite, offId);

		Long coultracl = FPOrepost.getcouACLltr(cuSite, offId);
		Long couemsacl = FPOrepost.getcouACLems(cuSite, offId);
		Long couparacl = FPOrepost.getcouACLpar(cuSite, offId);
		Long couempacl = FPOrepost.getcouACLemp(cuSite, offId);// }


		model.addAttribute("cousetasideapr", cousetasideapr);
		model.addAttribute("cousetasideacl", cousetasideacl);
		model.addAttribute("setasideaprview", setasideAPRview);
		model.addAttribute("setasideaclview", setasideACLview);
	

		model.addAttribute("prodoc", prodoc);
		model.addAttribute("bulk", bulk);
		model.addAttribute("bulkexp", bulkexp);

		
		// model.addAttribute("totgift", totgift);
		// model.addAttribute("cougiftapr", cougiftapr);
		// model.addAttribute("cougiftacl", cougiftacl);
		// model.addAttribute("totsg", totsg);
		// model.addAttribute("cousgapr", cousgapr);
		// model.addAttribute("cousgacl", cousgacl);
		// model.addAttribute("totrg", totrg);
		// model.addAttribute("courgapr", courgapr);
		// model.addAttribute("courgacl", courgacl);
		// model.addAttribute("totcs", totcs);
		// model.addAttribute("coucsapr", coucsapr);
		// model.addAttribute("coucsacl", coucsacl);
		// model.addAttribute("tototh", tototh);
		// model.addAttribute("couothapr", couothapr);
		// model.addAttribute("couothacl", couothacl);
		// model.addAttribute("totdoc", totdoc);
		// model.addAttribute("coudocapr", coudocapr);
		// model.addAttribute("coudocacl", coudocacl);

	
		model.addAttribute("cougiftmc", cougiftmc);
		model.addAttribute("cousgmc", cousgmc);
		model.addAttribute("courgmc", courgmc);
		model.addAttribute("coucsmc", coucsmc);
		model.addAttribute("couothmc", couothmc);
		model.addAttribute("coudocmc", coudocmc);
		
		
		model.addAttribute("aclCusite", aclCusite);
		model.addAttribute("coultracl", coultracl);
		model.addAttribute("couemsacl", couemsacl);
		model.addAttribute("couparacl", couparacl);
		model.addAttribute("couempacl", couempacl);
		
		return modelAndView;
	}
	

	@RequestMapping("/ead_list_express")
	public ResponseEntity<List<Collection>>ead_list_express(HttpSession session, HttpServletRequest request) {
	
		String flagset = request.getParameter("flagset");
		request.getSession().setAttribute("flag", flagset);
		
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();

		 List<Collection>bulkexpview = FPOrepost.getbulkexpview(cuSite, offId,FPOrepost.getaclassval());
		  	System.out.println("testing"+bulkexpview);
			return ResponseEntity.ok(bulkexpview);
	
		}
	@RequestMapping("/ead_list_deminimis")
	public ResponseEntity<List<Collection>>ead_list_deminimis(HttpSession session, HttpServletRequest request) {
		
		String flagset = request.getParameter("flagset");
		request.getSession().setAttribute("flag", flagset);
		
		
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		 List<Collection> bulkview = FPOrepost.getbulkview(cuSite, offId,Float.parseFloat(request.getSession().getAttribute("dutyCalc_assval").toString()));
			System.out.println("testing"+bulkview);
			return ResponseEntity.ok(bulkview);
		}
	@RequestMapping("/ead_list_documents")
	public ResponseEntity<List<Collection>>ead_list_documents(HttpSession session, HttpServletRequest request) {
		
		
		String flagset = request.getParameter("flagset");
		request.getSession().setAttribute("flag", flagset	);
	
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();

		 List<Collection> docview = FPOrepost.getdocview(cuSite, offId,Float.parseFloat(request.getSession().getAttribute("dutyCalc_assval").toString()));
		 	System.out.println("testing"+docview);
			return ResponseEntity.ok(docview);
	
		}
	


	@RequestMapping("/import_list")
	public ModelAndView import_list(Model model, HttpSession session, HttpServletRequest request) {
    
		//by premkumar
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		
		List<Collection> s = null;
		int qrycou = 0;
		qrycou = FPOrepost.getQueryView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString())
				.size();
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO"))
			s = FPOrepost.getPassView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());
		else if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAC"))
			s = FPOrepost.getPassaclView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());

		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			s.addAll(FPOrepost.getQueryreplyView(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString()));
		}

		/*
		 * Long couass=0L; if (request.getSession().getAttribute("role") == null ? null
		 * : request.getSession().getAttribute("role").toString().equals("PAO")) couass
		 * = FPOrepost.getcouassView( request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString(),
		 * request.getSession().getAttribute("offId") == null ? null :
		 * request.getSession().getAttribute("offId").toString(),
		 * request.getSession().getAttribute("role") == null ? null :
		 * request.getSession().getAttribute("role").toString()); else if
		 * (request.getSession().getAttribute("offId") == null ? null :
		 * request.getSession().getAttribute("offId").toString().equals("PAC")) couass =
		 * FPOrepost.getcouassaclView( request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString(),
		 * request.getSession().getAttribute("offId") == null ? null :
		 * request.getSession().getAttribute("offId").toString(),
		 * request.getSession().getAttribute("role") == null ? null :
		 * request.getSession().getAttribute("role").toString());
		 */
		
		
		//by premkumar
		
		Long allotltrapr = 0l;
		Long allotltr = 0l;
		Long coultr = 0L;
		String dispmailcat = "";
		Long coucurapr = 0l;
		Long fou = 0l;
		Long couemp = 0L;
		Long allotpar = 0l;
		Long couparapr = 0L;
		Long allotparapr = 0l;
		Long allotems = 0l;
		Long couemsapr = 0L;
		Long coultrapr = 0L;
		Long couems = 0L;
		Long allotemsapr = 0l;
		Long coupar = 0L;
		Long allotemp = 0l;
		Long allotempapr = 0l;
		Long couempapr = 0L;
		Long aprCusite = 0L;
		
		
		String allotmailcat = FPOrepost.getallotmailcat(cuSite, request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString(), offId);
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			if (allotmailcat.indexOf("U") >= 0) {
				dispmailcat = " U - Letters ";
				fou = 1l;
				coucurapr = coucurapr + coultr;
				allotltr = coultr;
				allotltrapr = coultrapr;
			} else {
				allotltr = 0l;
				allotltrapr = 0l;
			}
			if (allotmailcat.indexOf("E") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " E - EMS ";
				coucurapr = coucurapr + couems;
				fou = 1l;
				allotemsapr = couemsapr;
				allotems = couems;
			} else {
				allotemsapr = 0l;
				allotems = 0l;
			}
			if (allotmailcat.indexOf("C") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " C - Parcels ";
				coucurapr = coucurapr + coupar;
				fou = 1l;
				allotparapr = couparapr;
				allotpar = coupar;
			} else {
				allotparapr = 0l;
				allotpar = 0l;
			}
			if (allotmailcat.indexOf("T") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " T - Emp.Recep.,";
				fou = 1l;
				coucurapr = coucurapr + couemp;
				allotemp = couemp;
				allotempapr = couempapr;
			} else {
				allotempapr = 0l;
				allotemp = 0l;
			}

		}
		
		if (role.equals("PAO")) {
			//	List<Collection> APRview = FPOrepost.getAPRview(cuSite, offId);
				List<Collection> APRview = FPOrepost.getAPRview(cuSite, offId);
				List<Collection> APRltrview = FPOrepost.getAPRltrview(cuSite, offId);
				List<Collection> APRemsview = FPOrepost.getAPRemsview(cuSite, offId);
				List<Collection> APRparview = FPOrepost.getAPRparview(cuSite, offId);
				List<Collection> APRempview = FPOrepost.getAPRempview(cuSite, offId);
				aprCusite = FPOrepost.getcouAPR(cuSite, offId);
				/*
				 * model.addAttribute("APRview", APRview); model.addAttribute("APRltrview",
				 * APRltrview); model.addAttribute("APRemsview", APRemsview);
				 * model.addAttribute("APRparview", APRparview);
				 * model.addAttribute("APRempview", APRempview); model.addAttribute("aprCusite",
				 * aprCusite);
				 */
				
				}

		
		ModelAndView modelAndView = new ModelAndView("import/import_list");
		model.addAttribute("dispmailcat", dispmailcat);
		model.addAttribute("check", s);
		model.addAttribute("couass", s.size());
		model.addAttribute("qrycou", qrycou);
		return modelAndView;
	}

	@PostMapping(value = "/getdisphead")
	public ModelAndView getSummary(HttpServletRequest request, Model model) {
		ModelAndView models = new ModelAndView("disphead");
		try {
			String id = request.getParameter("id");
			
			int breadCrumbCount=fpoDcallQryRepo.getBreadcrumbDetails(id);	
			if(breadCrumbCount > 0)	
			model.addAttribute("queryraised", true);	
			else	
				model.addAttribute("queryraised", false);
			
			List<FPO_MAIN> s = FPOrepost.getmain(id);
			String itcat = FPOrepost.getitcat(id, s.get(0).getNATURE_TYPE_CD());
			String postdt = FPOrepost.getpostdt(id);
			String mc = FPOrepost.getmc(id, s.get(0).getMAIL_CLASS_CD());
			String cindt = FPOrepost.getrecdt(id);
			String orgpost = FPOrepost.getorgpost(s.get(0).getORIGPOST_ORG_CD());
			fpoService.setFpoMainValues(s);
			s = fpoQueryService.getAllFpoMainData(s);
			model.addAttribute("check", s.get(0));
			model.addAttribute("postdt", postdt);
			model.addAttribute("itcat", itcat);
			model.addAttribute("mc", mc);
			model.addAttribute("cindt", cindt);
			model.addAttribute("orgpost", orgpost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}
	
	
	@RequestMapping("/ead_main_direct")
	public @ResponseBody String ead_main_direct(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("EAD/ead_main");
		try {
			String cussite =  request.getSession().getAttribute("cuSite").toString();
			String offid = request.getSession().getAttribute("offId").toString();
			String id = "";
			id=FPOrepost.getmincinno(cussite,offid);
			model.addAttribute("id",id);
			return id;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}

	@RequestMapping("/ead_main")
	public ModelAndView ead_main(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("EAD/ead_main");
		try {
			String id = request.getParameter("id");
			List<FPO_MAIN> s = FPOrepost.getmain(id);
			String itcat = FPOrepost.getitcat(id, s.get(0).getNATURE_TYPE_CD());
			String postdt = FPOrepost.getpostdt(id);
			String mc = FPOrepost.getmc(id, s.get(0).getMAIL_CLASS_CD());
			String cindt = FPOrepost.getrecdt(id);
			String orgpost = FPOrepost.getorgpost(s.get(0).getORIGPOST_ORG_CD());
			List<FPO_ITEM_DET> itemlist = FPO_ITEMrepost.getItem(id);
			List<FPO_AC_CMTS> acCmts = fpoAcCmts.getAcseqNo(id);
			List<FPO_APR_CMTS> aprCmts = fpoAprCmts.getAprseqNo(id);
			List<FPO_ORDER> fpoorder = FPO_ORDERrepost.firstCheck(id);
			Float maxaclassval = FPOrepost.getaclassval();
			// fpoService.setFpoMainValues(s);
			s = fpoQueryService.getAllFpoMainData(s);
			// model.addAttribute("check", fpoService.getPojoMain(s.get(0)));
			System.out.println("itemid is " + s.get(0).getITEM_ID());
			model.addAttribute("check", s.get(0));
			model.addAttribute("itemlist", itemlist);
			model.addAttribute("acCmts", acCmts);
			model.addAttribute("assvalacl", maxaclassval);
			model.addAttribute("aprCmts", aprCmts);
			model.addAttribute("getOrder", fpoorder);
			model.addAttribute("postdt", postdt);
			model.addAttribute("itcat", itcat);
			model.addAttribute("mc", mc);
			model.addAttribute("cindt", cindt);
			model.addAttribute("orgpost", orgpost);
			// model.addAttribute("updstatus",FPOrepost.updassstatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
/*
	@RequestMapping("/addlqryprocess")
	public @ResponseBody void addlqryprocses(HttpServletRequest request, Model model, HttpSession session) {

		try {
			String id = request.getParameter("id");
			String que = request.getParameter("que");
			System.out.println("id is " + id);
			System.out.println("que is " + que);
			fpoCurQueService.updateQueryEnterStatus(id, "IMP", que, session, request);
			fpoService.movtoaddlqry(id, que, session, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
*/
	
	
	@RequestMapping("/addlqryprocess")
	public @ResponseBody void addlqryprocses(HttpServletRequest request, Model model, HttpSession session) {

		try {
			String id = request.getParameter("id");
			String que = request.getParameter("que");
			System.out.println("id is " + id);
			System.out.println("que is " + que);
			fpoService.movtoaddlqry(id, que, session, request);
			
			List<FPO_MAIN> fpoMain = FPOrepost.getmain(id);
			
			String stage=FPOrepost.stages(id);
			System.out.println(fpoMain.get(0).getROLE());
			System.out.println("checking whether adddcallgenerator or not"+stage);
			Float maxaclassval = FPOrepost.getaclassval();
			Float totassval = fpoMain.get(0).getTOT_ASS_VAL();
			System.out.println("checking whether"+maxaclassval);
			System.out.println("checking whether ad"+totassval);
			if(totassval>maxaclassval) {
				
			if(fpoMain.get(0).getROLE().equals("PAO")){
				
				if(stage.equals("AAA")) {
				fpoCurQueService.updateQueryEnterStatus(id, "PAC", que, session, request);
				fpoService.movtoaddlqry(id, que, session, request);
				fpoCurQueService.updateQueryEnterStatus(id, "IMP", que, session, request);
				}
				else if(stage.equals("EAD")) {
				fpoCurQueService.updateQueryEnterStatus(id, "PAC", que, session, request);
				}
				else {
					fpoCurQueService.updateQueryEnterStatus(id, "PAO", que, session, request);
				}
			}
			
			else if(fpoMain.get(0).getROLE().equals("PAC")){
				fpoCurQueService.updateQueryEnterStatus(id, "PAO", que, session, request);
				//fpoService.movtoaddlqry(id, que, session, request);
				//fpoCurQueService.updateQueryEnterStatus(id, "PAC", que, session, request);
			}
				
		}
			
			else {
				fpoCurQueService.updateQueryEnterStatus(id, "IMP", que, session, request);
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@RequestMapping("/pen_main")
	public ModelAndView pen_main(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("EAD/pen_main");
		try {
			String id = request.getParameter("id");
			List<FPO_MAIN> s = FPOrepost.getmain(id);
			List<FPO_ITEM_DET> itemlist = FPO_ITEMrepost.getItem(id);
			List<FPO_AC_CMTS> acCmts = fpoAcCmts.getAcseqNo(id);
			List<FPO_APR_CMTS> aprCmts = fpoAprCmts.getAprseqNo(id);
			Float maxaclassval = FPOrepost.getaclassval();	
			model.addAttribute("assvalacl", maxaclassval);
			List<FPO_ORDER> fpoorder = FPO_ORDERrepost.firstCheck(id);
			fpoService.setFpoMainValues(s);
			s = fpoQueryService.getAllFpoMainData(s);
			model.addAttribute("check", s.get(0));
			model.addAttribute("itemlist", itemlist);
			model.addAttribute("acCmts", acCmts);
			model.addAttribute("aprCmts", aprCmts);
			model.addAttribute("getOrder", fpoorder);
		//	model.addAttribute("curque",
		//			fpoCurQueRepo.getcurqueCin(id, request.getSession().getAttribute("cuSite") == null ? null
		//					: request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("curque",fpoCurQueRepo.getcurqueCin(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("/import_main")
	public ModelAndView import_main(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("import/import_main");
		try {
			String id = request.getParameter("id");
			
			List<FPO_AC_CMTS> acCmts = fpoAcCmts.getAcseqNo(id);
			List<FPO_APR_CMTS> aprCmts = fpoAprCmts.getAprseqNo(id);
			Float maxaclassval = FPOrepost.getaclassval();	
			model.addAttribute("assvalacl", maxaclassval);
			
			List<FPO_MAIN> s = FPOrepost.getmain(id);
			fpoService.setFpoMainValues(s);
			s = fpoQueryService.getAllFpoMainData(s);
			System.out.println("id is " + id);
			String eaddeci = FPOrepost.geteaddeci(id) + "   ...";
			int qrycou = FPOrepost.getqrycou(id);
			int exmordcou = FPOrepost.getexmordcou(id);
			model.addAttribute("eaddeci", eaddeci);
			if (qrycou > 0)
				model.addAttribute("qry", "Query Raised  ");
			else
				model.addAttribute("qry", "Query Not Raised  ");
			if (exmordcou > 0)
				model.addAttribute("exmord", FPO_ORDERrepost.getOrder(id) + " Given  ");
			else
				model.addAttribute("exmord", "Not Given  ");
			System.out.println("Country code is " + s.get(0).getSEND_CNTRY_CD());
			model.addAttribute("check", s.get(0));
			Long counoqry = FPOrepost.getcounoqry(id);
			model.addAttribute("qryraised", counoqry > 0 ? true : false);
			
			model.addAttribute("acCmts", acCmts);
			model.addAttribute("aprCmts", aprCmts);

			Long examRaised = FPO_ORDERrepost.getOrderCinNo(id);
			model.addAttribute("examraised", examRaised > 0);
			Long lastDetained = fpomvmntrepo.getDetainedCount(id);
			model.addAttribute("lastDetained", lastDetained > 0);
			model.addAttribute("lastDetainedNo", fpoDetainedInfoRepo.getMaxDetainedNo(s.get(0).getITEM_ID()));
			Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(s.get(0).getITEM_ID());
			model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
			model.addAttribute("oocdt", FPOrepost.getoocdt(id));
			model.addAttribute("oocinfo", FPOrepost.getooccancel(id) > 0 ? true : false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("/movpers")
	public @ResponseBody String movpers(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView modelAndView;
		String itemid = request.getParameter("itemid");
		String cusite = request.getSession().getAttribute("cuSite").toString();
		String role = request.getSession().getAttribute("role").toString();
		String offid = request.getSession().getAttribute("offId").toString();
		String Cinno = FPOrepost.getCinIdByItemId(itemid);
		FPOrepost.updcommpers(Cinno);
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpomvmntrepo.updextdtstr(Cinno, utilDate);
		Long slno = fpomvmntrepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		fpoService.insertIntofpoMvmntDb(null, Cinno, FPOrepost.getcinDt(itemid), new java.util.Date(), slno, "PAO",
				"R6", session, request);
		fpoCurQueService.addUserQue(Cinno, itemid, "P1", session, request);
		FPOrepost.updatFpoQryDeci(itemid, cusite, role, offid, "P1");
		String strval = "";
		if (FPOrepost.getarrfpocou(itemid) > 0)
			strval = "success";
		return strval;
	}

	@RequestMapping("/edi_decl")
	public ModelAndView edi_decl(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView("import/edi_decl");
		try {
			String id = request.getParameter("id");
			List<FPO_MAIN> s = FPOrepost.getmain(id);
			fpoService.setFpoMainValues(s);
			s = fpoQueryService.getAllFpoMainData(s);
			System.out.println("id is " + id);
			String eaddeci = FPOrepost.geteaddeci(id) + "   ...";
			int qrycou = FPOrepost.getqrycou(id);
			int exmordcou = FPOrepost.getexmordcou(id);
			model.addAttribute("eaddeci", eaddeci);
			if (qrycou > 0)
				model.addAttribute("qry", "Query Raised  ");
			else
				model.addAttribute("qry", "Query Not Raised  ");
			if (exmordcou > 0)
				model.addAttribute("exmord", FPO_ORDERrepost.getOrder(id) + " Given  ");
			else
				model.addAttribute("exmord", "Not Given  ");
			System.out.println("Country code is " + s.get(0).getSEND_CNTRY_CD());
			System.out.println("itemid is " + s.get(0).getITEM_ID());
			System.out.println("itemid from pojomain is " + s.get(0).getITEM_ID());
			model.addAttribute("check", s.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("/ead_item")
	public ModelAndView ead_item(HttpServletRequest request, Model model) {
		try {
			String cinNo = request.getParameter("id");
			List<FPO_ITEM_DET> itemModYes = FPO_ITEMrepost.getItemByModYes(cinNo);
			List<FPO_ITEM_DET> itemAmend = FPO_ITEMrepost.getItemOnCin(cinNo, 1l);
			FPO_ITEM_DET itemDeclared = fpoAmendService.getFirstAmendOnCinAndItemNo(cinNo, 1l);

			List<Object> genCth = FPO_ITEMrepost.getGENDROP();

			List<FpoItemDetOthDuty> othitemDecl = fpoItemDetOthDutyRepo.getothitemDecl(cinNo, 1l);
			List<FpoItemDetOthDutyAmend> OthItemAmend = fpoItemDetOthDutyAmendRepo.getOthItemAmend(cinNo, 1l);
			model.addAttribute("check", (itemDeclared));
			model.addAttribute("amend", fpoService.getListPojo(itemAmend).get(0));
			List<FPO_MAIN> s1 = FPOrepost.getmain(cinNo);
			fpoService.setFpoMainValues(s1);
			if (!((s1 == null) || s1.isEmpty()))
				if (!(null == itemModYes) && !(itemModYes.isEmpty()))
					s1.get(0).setMODIFIED("Y");
				else
					s1.get(0).setMODIFIED("N");

			s1 = fpoQueryService.getAllFpoMainData(s1);

			model.addAttribute("head", s1.get(0));
			model.addAttribute("genCth", genCth);
			model.addAttribute("OthItemAmend", OthItemAmend);
			model.addAttribute("othitemDecl", othitemDecl);
		} catch (Exception e) {

			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("EAD/ead_item");
		return modelAndView;
	}

	@RequestMapping("/declared")
	public ModelAndView declared(HttpServletRequest request, Model model, HttpSession session)

	{
		ModelAndView modelAndView = null;
		try {
			String cinNo = request.getParameter("id");
			List<Fpo_Item_Query> itemlist = reportService.getItemquery(cinNo);
			model.addAttribute("itemlist", itemlist);
			model.addAttribute("totitem", itemlist.size());
			Boolean back = Boolean.valueOf(request.getParameter("back"));
			List<FPO_ITEM_DET> fpoItem = FPO_ITEMrepost.getItemOnCin(cinNo, 1L);
			List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
			
			model.addAttribute("arrFpo", fpoMain.get(0).getARR_FPO());

			List<SelectTag> fpoalert = reportService.getAlerts(fpoMain.get(0));
			List<String> alert = new ArrayList<String>();
			if (fpoalert.size() > 0) {
				for (SelectTag selectTag : fpoalert) {
					Date d = new Date();
					Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
					if (d.compareTo(d1Date) >= 0)
						alert.add(selectTag.getValue());
				}

				if (alert.size() > 0) {
					model.addAttribute("alerts", alert);
				} else {
					model.addAttribute("alerts", null);
				}

			} else {
				model.addAttribute("alerts", null);
			}

			List<SelectTag> sitefpoalert = reportService.getSiteAlerts(fpoMain.get(0));
			List<String> sitealert = new ArrayList<String>();
			if (sitefpoalert.size() > 0) {
				for (SelectTag selectTag : sitefpoalert) {
					Date d = new Date();
					Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
					if (d.compareTo(d1Date) >= 0)
						sitealert.add(selectTag.getValue());
				}

				if (sitealert.size() > 0) {
					model.addAttribute("sitealert", sitealert);
				} else {
					model.addAttribute("sitealert", null);
				}

			} else {
				model.addAttribute("sitealert", null);
			}
			Long ACLfirsttime = fpomvmntrepo.getACLfirsttime(cinNo);
			System.out.println("ACLfirsttime is " + ACLfirsttime);
			fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E1", session, request);
			// fpoCurQueService.updateUSerEnterStatus(cinNo, "E1");
			List<FpoQuery> qryList = fpoQueryRepo.getQryOnCinAdItem(cinNo, 1l);
			List<FpoFine> allFine = fpoFineRepo.getAllFine(cinNo);
			List<FpoPenal> allPenalty = fpoPenalRepo.getAllPenalty(cinNo);
			Long queryNo = fpoQueryRepo.getCountQueryNoOnCinNo(cinNo);
			Long assNo = FPOrepost.getassCount(cinNo);
			List<FpoItemDetOthDuty> fpoItemDetOthDuty = fpoItemDetOthDutyRepo.getFpoOthAssDutyOnCinItmNo(cinNo, 1l);
			Float maxaclassval = FPOrepost.getaclassval();
			Float totassval = fpoMain.get(0).getTOT_ASS_VAL();
			Float totpayable = fpoMain.get(0).getTOT_AMT_PAYABLE();
			List<FpoItemDetOthDuty> allothlist = new ArrayList<FpoItemDetOthDuty>();
			FpoItemDetOthDuty othcth = new FpoItemDetOthDuty();
			othcth.setDUTY_CD(0l);
			othcth.setDUTY_RTA(0f);
			othcth.setDUTY_NOTN("");
			othcth.setDUTY_SLNO("");
			othcth.setRATE(0f);
			othcth.setDUTY_AMT(0f);
			othcth.setDUTY_FG(0f);
			allothlist.add(othcth);
			
			 FpoQuery offDel = fpoQueryRepo.getOffDet(cinNo); 

			 if (offDel != null) {
			 	model.addAttribute("mark",offDel.getMark());
			 }


			List<FpoFine> allFineList = new ArrayList<FpoFine>();
			FpoFine fpoFine = new FpoFine();

			fpoFine.setFineAmt(0f);
			fpoFine.setFineUs("Sec. ");

			allFineList.add(fpoFine);

			System.out.println(fpoFine);

			List<FpoPenal> allPenalList = new ArrayList<FpoPenal>();
			FpoPenal fpoPenal = new FpoPenal();
			fpoPenal.setPenalAmt(0f);
			fpoPenal.setPenalUs("Sec. ");
			allPenalList.add(fpoPenal);

			if (null != qryList && !qryList.isEmpty()) {
				fpoItem.get(0).setQuery(qryList.get(0).getQRY());
				fpoItem.get(0).setMark(qryList.get(0).getMark());
			}
			if (back) {
				model.addAttribute("back", true);
			}

			model.addAttribute("check", fpoItem.get(0));
			model.addAttribute("assvalacl", maxaclassval);
			model.addAttribute("ACLfirsttime", ACLfirsttime);

			if (null != fpoItemDetOthDuty && !fpoItemDetOthDuty.isEmpty())
				model.addAttribute("othersDuty", fpoItemDetOthDuty);
			else
				model.addAttribute("othersDuty", allothlist);

			if (null != allFine && !allFine.isEmpty())
				model.addAttribute("allFine", allFine);
			else
				model.addAttribute("allFine", allFineList);

			if (null != allPenalty && !allPenalty.isEmpty())
				model.addAttribute("allPenalty", allPenalty);
			else
				model.addAttribute("allPenalty", allPenalList);

			List<Object> genCth = FPO_ITEMrepost.getGENDROP();
			List<Object> duty = FPO_ITEMrepost.getDUTY();
			List<Object> genNOTN = FPO_ITEMrepost.getBcdNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> genIGSTNOTN = FPO_ITEMrepost.getIgstNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> othersTwoDigi = FPO_ITEMrepost.getOtherTwoDigi();
			model.addAttribute("fpocat", fpoMain.get(0).getNATURE_TYPE_CD());
			fpoMain = fpoService.setFpoMainValues(fpoMain);

			if (!((fpoMain == null) || fpoMain.isEmpty()))
				if (!(null == fpoItem) && !(fpoItem.isEmpty()))
					if (null != fpoItem.get(0).getAMEND_SERAIL_NO() && fpoItem.get(0).getAMEND_SERAIL_NO() > 0) {
						fpoMain.get(0).setMODIFIED("Y");
					} else {
						fpoMain.get(0).setMODIFIED("N");
					}

			fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
			if (!(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString().equals("PAC")))
			fpoMain.get(0).setOFF_ID(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			// fpoMain.get(0).setROLE(request.getSession().getAttribute("role") == null ?
			// null :
			// request.getSession().getAttribute("role").toString());
			fpoMain.get(0).setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			model.addAttribute("paodepcmts",
					FPOrepost.getpaodepcmts(cinNo, "EAD", request.getSession().getAttribute("cuSite").toString()));
			int upd;
			if (totassval > 0)
				upd = 1;
			else
				upd = 0;
			System.out.println("upd is" + upd);
			model.addAttribute("head", fpoMain.get(0));
			model.addAttribute("genCth", genCth);
			model.addAttribute("duty", duty);
			model.addAttribute("updenable", upd);
			model.addAttribute("totassval", totassval);
			model.addAttribute("qryNo", queryNo);
			model.addAttribute("assNo", assNo);
			model.addAttribute("genNOTN", genNOTN);
			model.addAttribute("genIGSTNOTN", genIGSTNOTN);
			model.addAttribute("othersTwoDigi", othersTwoDigi);
			model.addAttribute("dutycat", request.getSession().getAttribute("dutyCalc_category").toString());
			model.addAttribute("duty0limit", request.getSession().getAttribute("dutyCalc_assval").toString());
			model.addAttribute("dutycatlimit", request.getSession().getAttribute("dutyCalc_catassval").toString());
			List<String> getDefQry = fpoService.getDefualtQuery();
			model.addAttribute("getDefQry", getDefQry);
			model.addAttribute("getDefQrycou", getDefQry.size());

			model.addAttribute("totpayable", totpayable);

			List<FpoQuery> getDefQrys = fpoQueryRepo.getDefQry(cinNo);
			if (getDefQrys.size() > 0) {
				if (null != getDefQrys.get(0).getDEFUALT_QUERY()) {
					model.addAttribute("checkqry", getDefQrys.get(0).getDEFUALT_QUERY());
				} else {
					model.addAttribute("checkqry", "");
				}
			} else {
				model.addAttribute("checkqry", "");
			}

			List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);

			List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
			FpoQueryDin fpoQueryDin = new FpoQueryDin();
			fpoQueryDin.setRemarks("");
			fpoQueryDin.setDepComments("");
			AllcmtsList.add(fpoQueryDin);

			model.addAttribute("isqry", getDefQrys.size());

			if (null != getAllcmts && !getAllcmts.isEmpty())
				model.addAttribute("getAllcmts", getAllcmts);
			else
				model.addAttribute("getAllcmts", AllcmtsList);

			String itcat = FPOrepost.getitcat(cinNo, fpoMain.get(0).getNATURE_TYPE_CD());
			model.addAttribute("itemid", fpoMain.get(0).getITEM_ID());
			model.addAttribute("cinno", cinNo);
			model.addAttribute("itcat", itcat);
			model.addAttribute("currole", request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			modelAndView = new ModelAndView("EAD/declared");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/defaultquery", method = RequestMethod.POST)
	public ModelAndView defaultquery(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("EAD/defaultquery");

		try {

			String item_id = request.getParameter("item_id").toString();

			// List<FpoQuery> getDefQry = fpoQueryRepo.getDefQryByitemid(item_id);
			List<String> getDefQry = fpoService.getDefualtQuery();
			models.addObject("getDefQry", getDefQry);
			models.addObject("getDefQrycou", getDefQry.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/generalremark", method = RequestMethod.POST)
	public ModelAndView generalremark(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("EAD/remarks");

		try {

			String item_id = request.getParameter("item_id").toString();

			List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(item_id);

			List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
			FpoQueryDin fpoQueryDin = new FpoQueryDin();
			fpoQueryDin.setRemarks("");
			fpoQueryDin.setDepComments("");
			AllcmtsList.add(fpoQueryDin);

			if (null != getAllcmts && !getAllcmts.isEmpty())
				models.addObject("getAllcmts", getAllcmts);
			else
				models.addObject("getAllcmts", AllcmtsList);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping("/pen_declared")
	public ModelAndView pendeclared(HttpServletRequest request, Model model, HttpSession session)

	{
		ModelAndView modelAndView = null;
		try {
			String cinNo = request.getParameter("id");
			
			//santhosh,veeem,kani
			Long queryNo = fpoQueryRepo.getCountQueryNoOnCinNo(cinNo);
			model.addAttribute("qryNo", queryNo);
			
			List<FPO_ITEM_DET> fpoItem = FPO_ITEMrepost.getItemOnCin(cinNo, 1L);
			List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
			model.addAttribute("clrsite",fpoMain.get(0).getClrSite());
			Boolean back = Boolean.valueOf(request.getParameter("back"));
			model.addAttribute("back", back);
			if (!back)
				fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "N1", session, request);
			// fpoCurQueService.updateUSerEnterStatus(cinNo, "E1");
			List<FpoQuery> qryList = fpoQueryRepo.getQryOnCinAdItem(cinNo, 1l);
			Float totassval = fpoMain.get(0).getTOT_ASS_VAL();
			Float totpayable = fpoMain.get(0).getTOT_AMT_PAYABLE();
			List<FpoFine> allFine = fpoFineRepo.getAllFine(cinNo);
			Float maxaclassval = FPOrepost.getaclassval();

			List<FpoPenal> allPenalty = fpoPenalRepo.getAllPenalty(cinNo);
			List<FpoItemDetOthDuty> fpoItemDetOthDuty = fpoItemDetOthDutyRepo.getFpoOthAssDutyOnCinItmNo(cinNo, 1l);

			List<FpoItemDetOthDuty> allothlist = new ArrayList<FpoItemDetOthDuty>();
			FpoItemDetOthDuty othcth = new FpoItemDetOthDuty();
			othcth.setDUTY_CD(0l);
			othcth.setDUTY_RTA(0f);
			othcth.setDUTY_NOTN("");
			othcth.setDUTY_SLNO("");
			othcth.setRATE(0f);
			othcth.setDUTY_AMT(0f);
			othcth.setDUTY_FG(0f);
			allothlist.add(othcth);

			List<FpoFine> allFineList = new ArrayList<FpoFine>();
			FpoFine fpoFine = new FpoFine();

			fpoFine.setFineAmt(0f);
			fpoFine.setFineUs("Sec. ");

			allFineList.add(fpoFine);

			System.out.println(fpoFine);

			List<FpoPenal> allPenalList = new ArrayList<FpoPenal>();
			FpoPenal fpoPenal = new FpoPenal();
			fpoPenal.setPenalAmt(0f);
			fpoPenal.setPenalUs("Sec. ");
			allPenalList.add(fpoPenal);

			if (null != qryList && !qryList.isEmpty()) {
				fpoItem.get(0).setQuery(qryList.get(0).getQRY());
				fpoItem.get(0).setMark(qryList.get(0).getMark());
			}

			model.addAttribute("check", fpoItem.get(0));

			if (null != fpoItemDetOthDuty && !fpoItemDetOthDuty.isEmpty())
				model.addAttribute("othersDuty", fpoItemDetOthDuty);
			else
				model.addAttribute("othersDuty", allothlist);

			if (null != allFine && !allFine.isEmpty())
				model.addAttribute("allFine", allFine);
			else
				model.addAttribute("allFine", allFineList);

			if (null != allPenalty && !allPenalty.isEmpty())
				model.addAttribute("allPenalty", allPenalty);
			else
				model.addAttribute("allPenalty", allPenalList);

			List<Object> genCth = FPO_ITEMrepost.getGENDROP();
			List<Object> duty = FPO_ITEMrepost.getDUTY();
			List<Object> genNOTN = FPO_ITEMrepost.getBcdNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> genIGSTNOTN = FPO_ITEMrepost.getIgstNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> othersTwoDigi = FPO_ITEMrepost.getOtherTwoDigi();

			model.addAttribute("fpocat", fpoMain.get(0).getNATURE_TYPE_CD());
			fpoMain = fpoService.setFpoMainValues(fpoMain);

			if (!((fpoMain == null) || fpoMain.isEmpty()))
				if (!(null == fpoItem) && !(fpoItem.isEmpty()))
					if (null != fpoItem.get(0).getAMEND_SERAIL_NO() && fpoItem.get(0).getAMEND_SERAIL_NO() > 0) {
						fpoMain.get(0).setMODIFIED("Y");
					} else {
						fpoMain.get(0).setMODIFIED("N");
					}

			fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
			
			//santhosh,veeem,kani	
			if(!(request.getSession().getAttribute("role").toString().equals("PAC"))) {

				fpoMain.get(0).setOFF_ID(request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
				}

if (!(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString().equals("PAC")))

			// fpoMain.get(0).setROLE(request.getSession().getAttribute("role") == null ?
			// null :
			// request.getSession().getAttribute("role").toString());
			fpoMain.get(0).setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			Long ACLfirsttime = fpomvmntrepo.getACLfirsttime(cinNo);
			System.out.println("cmts is "
					+ FPOrepost.getpaodepcmts(cinNo, "EAD", request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("depcmtsaaa",
					FPOrepost.getpaodepcmts(cinNo, "AAA", request.getSession().getAttribute("cuSite").toString()));

			model.addAttribute("depcmtsead",
					FPOrepost.getpaodepcmts(cinNo, "EAD", request.getSession().getAttribute("cuSite").toString()));

			model.addAttribute("head", fpoMain.get(0));
			model.addAttribute("assvalacl", maxaclassval);
			model.addAttribute("ACLfirsttime", ACLfirsttime);
			model.addAttribute("genCth", genCth);
			model.addAttribute("duty", duty);
			model.addAttribute("genNOTN", genNOTN);
			model.addAttribute("genIGSTNOTN", genIGSTNOTN);
			model.addAttribute("totpayable", totpayable);
			model.addAttribute("totassval", totassval);
			model.addAttribute("othersTwoDigi", othersTwoDigi);
			model.addAttribute("dutycat", request.getSession().getAttribute("dutyCalc_category"));
			model.addAttribute("duty0limit", request.getSession().getAttribute("dutyCalc_assval"));
			model.addAttribute("dutycatlimit", request.getSession().getAttribute("dutyCalc_catassval"));
		//	model.addAttribute("curque",
		//			fpoCurQueRepo.getcurqueCin(cinNo, request.getSession().getAttribute("cuSite") == null ? null
		//					: request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("curque",fpoCurQueRepo.getcurqueCin(cinNo));
			List<Fpo_Item_Query> itemlist = reportService.getItemquery(cinNo);
			model.addAttribute("totitem", itemlist.size());
			modelAndView = new ModelAndView("EAD/pen_declared");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/final_declared")
	public ModelAndView finaldeclared(HttpServletRequest request, Model model, HttpSession session)

	{
		ModelAndView modelAndView = null;
		try {
			
			
			String cinNo = request.getParameter("id");
			
			
			System.out.println("checking cinno whether it is correct or not "+cinNo);
			Long counoqry = FPOrepost.getcounoqry(cinNo);

			Long counoqryreply = FPOrepost.getcounoqryreply(cinNo);

			model.addAttribute("qryraised", counoqry > 0 && counoqry == counoqryreply ? true : false);
			
			Boolean back = Boolean.valueOf(request.getParameter("back"));
			model.addAttribute("back", back);
			List<Fpo_Item_Query> itemlist = reportService.getItemquery(cinNo);
			model.addAttribute("itemlist", itemlist);
				
			List<FPO_ITEM_DET> fpoItem = FPO_ITEMrepost.getItemOnCin(cinNo, 1L);
			List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
			Float maxaclassval = FPOrepost.getaclassval();
			List<SelectTag> fpoalert = reportService.getAlerts(fpoMain.get(0));
			List<String> alert = new ArrayList<String>();
			if (fpoalert.size() > 0) {
				for (SelectTag selectTag : fpoalert) {
					Date d = new Date();
					Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
					if (d.compareTo(d1Date) >= 0)
						alert.add(selectTag.getValue());
				}

				if (alert.size() > 0) {
					model.addAttribute("alerts", alert);
				} else {
					model.addAttribute("alerts", null);
				}

			} else {
				model.addAttribute("alerts", null);
			}

			List<SelectTag> sitefpoalert = reportService.getSiteAlerts(fpoMain.get(0));
			List<String> sitealert = new ArrayList<String>();
			if (sitefpoalert.size() > 0) {
				for (SelectTag selectTag : sitefpoalert) {
					Date d = new Date();
					Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
					if (d.compareTo(d1Date) >= 0)
						sitealert.add(selectTag.getValue());
				}

				if (sitealert.size() > 0) {
					model.addAttribute("sitealert", sitealert);
				} else {
					model.addAttribute("sitealert", null);
				}

			} else {
				model.addAttribute("sitealert", null);
			}

			fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "P1", session, request);
			Float totassval = fpoMain.get(0).getTOT_ASS_VAL();

			
			model.addAttribute("counoqry", counoqry);

			String quedisp = FPOrepost.getquedisp(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString(), cinNo);
			System.out.println("cinno is  " + cinNo);
			System.out.println("cusite is " + request.getSession().getAttribute("cuSite"));
			System.out.println("quedisp" + quedisp);
			// fpoCurQueService.updateUSerEnterStatus(cinNo, "E1");
			List<FpoQuery> qryList = fpoQueryRepo.getQryOnCinAdItem(cinNo, 1l);

			List<FpoFine> allFine = fpoFineRepo.getAllFine(cinNo);

			List<FpoPenal> allPenalty = fpoPenalRepo.getAllPenalty(cinNo);
			List<FpoItemDetOthDuty> fpoItemDetOthDuty = fpoItemDetOthDutyRepo.getFpoOthAssDutyOnCinItmNo(cinNo, 1l);

			List<FpoItemDetOthDuty> allothlist = new ArrayList<FpoItemDetOthDuty>();
			FpoItemDetOthDuty othcth = new FpoItemDetOthDuty();
			othcth.setDUTY_CD(0l);
			othcth.setDUTY_RTA(0f);
			othcth.setDUTY_NOTN("");
			othcth.setDUTY_SLNO("");
			othcth.setRATE(0f);
			othcth.setDUTY_AMT(0f);
			othcth.setDUTY_FG(0f);
			allothlist.add(othcth);

			List<FpoFine> allFineList = new ArrayList<FpoFine>();
			FpoFine fpoFine = new FpoFine();

			fpoFine.setFineAmt(0f);
			fpoFine.setFineUs("Sec. ");

			allFineList.add(fpoFine);

			System.out.println(fpoFine);

			List<FpoPenal> allPenalList = new ArrayList<FpoPenal>();
			FpoPenal fpoPenal = new FpoPenal();
			fpoPenal.setPenalAmt(0f);
			fpoPenal.setPenalUs("Sec. ");
			allPenalList.add(fpoPenal);

			if (null != qryList && !qryList.isEmpty()) {
				fpoItem.get(0).setQuery(qryList.get(0).getQRY());
				fpoItem.get(0).setMark(qryList.get(0).getMark());
			}
			String itcat = FPOrepost.getitcat(cinNo, fpoMain.get(0).getNATURE_TYPE_CD());
			model.addAttribute("itcat", itcat);
			model.addAttribute("check", fpoItem.get(0));
			model.addAttribute("check", fpoItem.get(0));

			System.out.println("cmts is "
					+ FPOrepost.getpaodepcmts(cinNo, "EAD", request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("depcmtsaaa",
					FPOrepost.getpaodepcmts(cinNo, "AAA", request.getSession().getAttribute("cuSite").toString()));

			model.addAttribute("depcmtsead",
					FPOrepost.getpaodepcmts(cinNo, "EAD", request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("depcmtsaaf",
					FPOrepost.getpaodepcmts(cinNo, "AAF", request.getSession().getAttribute("cuSite").toString()));

			model.addAttribute("oocdepcmts",
					FPOrepost.getoocdepcmts(cinNo, request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("examdepcmts",
					FPOrepost.getexamdepcmts(cinNo));
			model.addAttribute("oocdepcmtsid",
					FPOrepost.getoocdepcmtsid(cinNo, request.getSession().getAttribute("cuSite").toString()));
			model.addAttribute("oocdepcmtsdt",
					FPOrepost.getoocdepcmtsdt(cinNo, request.getSession().getAttribute("cuSite").toString()));

			if (null != fpoItemDetOthDuty && !fpoItemDetOthDuty.isEmpty())
				model.addAttribute("othersDuty", fpoItemDetOthDuty);
			else
				model.addAttribute("othersDuty", allothlist);

			if (null != allFine && !allFine.isEmpty())
				model.addAttribute("allFine", allFine);
			else
				model.addAttribute("allFine", allFineList);

			if (null != allPenalty && !allPenalty.isEmpty())
				model.addAttribute("allPenalty", allPenalty);
			else
				model.addAttribute("allPenalty", allPenalList);

			List<Object> genCth = FPO_ITEMrepost.getGENDROP();
			List<Object> duty = FPO_ITEMrepost.getDUTY();
			List<Object> genNOTN = FPO_ITEMrepost.getBcdNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> genIGSTNOTN = FPO_ITEMrepost.getIgstNotification(fpoItem.get(0).getGEN_CTH());
			List<Object> othersTwoDigi = FPO_ITEMrepost.getOtherTwoDigi();

			model.addAttribute("fpocat", fpoMain.get(0).getNATURE_TYPE_CD());
			fpoMain = fpoService.setFpoMainValues(fpoMain);

			if (!((fpoMain == null) || fpoMain.isEmpty()))
				if (!(null == fpoItem) && !(fpoItem.isEmpty()))
					if (null != fpoItem.get(0).getAMEND_SERAIL_NO() && fpoItem.get(0).getAMEND_SERAIL_NO() > 0) {
						fpoMain.get(0).setMODIFIED("Y");
					} else {
						fpoMain.get(0).setMODIFIED("N");
					}

			fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
			
			if(!(request.getSession().getAttribute("role").toString().equals("PAC"))) {

				fpoMain.get(0).setOFF_ID(request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
				}
			

			// fpoMain.get(0).setROLE(request.getSession().getAttribute("role") == null ?
			// null :
			// request.getSession().getAttribute("role").toString());
			fpoMain.get(0).setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());

			Float totpayable = fpoMain.get(0).getTOT_AMT_PAYABLE();
			model.addAttribute("head", fpoMain.get(0));
			model.addAttribute("genCth", genCth);
			model.addAttribute("duty", duty);
			model.addAttribute("genNOTN", genNOTN);
			model.addAttribute("assvalacl", maxaclassval);
			model.addAttribute("genIGSTNOTN", genIGSTNOTN);
			model.addAttribute("othersTwoDigi", othersTwoDigi);
			model.addAttribute("quedisp", quedisp);
			model.addAttribute("currcd", FPOrepost.getcurrcd(cinNo));
			model.addAttribute("cntrycd", FPOrepost.getcntrycd(cinNo));
			model.addAttribute("exrate", FPOrepost.getexrate(FPOrepost.getcurrcd(cinNo)));
			model.addAttribute("newitemno", FPOrepost.getnewitemno(cinNo));
			model.addAttribute("dutycat", request.getSession().getAttribute("dutyCalc_category"));
			model.addAttribute("duty0limit", request.getSession().getAttribute("dutyCalc_assval"));
			model.addAttribute("dutycatlimit", request.getSession().getAttribute("dutyCalc_catassval"));
			model.addAttribute("totpayable", totpayable);
			model.addAttribute("totassval", totassval);

			List<String> getDefQry = fpoService.getDefualtQuery();
			model.addAttribute("getDefQry", getDefQry);
			model.addAttribute("getDefQrycou", getDefQry.size());

			List<FpoSecondDefaultQuery> getScndDefQry = fpoService.getSecondDefualtQuery();
			model.addAttribute("getScndDefQry", getScndDefQry);

			model.addAttribute("getcouqry", fpoQueryRepo.getCountQueryNoOnCinNo(cinNo));
			List<FpoQuery> getDefQrys = fpoQueryRepo.getDefQry(cinNo);
			if (getDefQrys.size() > 0) {
				if (null != getDefQrys.get(0).getDEFUALT_QUERY()) {
					model.addAttribute("checkqry", getDefQrys.get(0).getDEFUALT_QUERY());
				} else {
					model.addAttribute("checkqry", "");
				}
			} else {
				model.addAttribute("checkqry", "");
			}

			List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);

			List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
			FpoQueryDin fpoQueryDin = new FpoQueryDin();
			fpoQueryDin.setRemarks("");
			fpoQueryDin.setDepComments("");
			AllcmtsList.add(fpoQueryDin);

			if (null != getAllcmts && !getAllcmts.isEmpty())
				model.addAttribute("getAllcmts", getAllcmts);
			else
				model.addAttribute("getAllcmts", AllcmtsList);

			model.addAttribute("itemid", fpoMain.get(0).getITEM_ID());
			model.addAttribute("cinno", cinNo);
			String qryType = FPOrepost.getQryType(cinNo);
			if (counoqry > 0) {
				if (qryType.equalsIgnoreCase("E")) {
					String filename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("AAFAdditionQueryFileName", filename);
					model.addAttribute("AAFAdditionQueryFileExist", filename.equalsIgnoreCase("") ? false : true);
				} else {
					model.addAttribute("AAFAdditionQueryFileExist",
							fpoAddlQryRepo.getArticleAwaitingQuery(cinNo, "P").size() > 0);
				}
			}

			//santhosh,kani,veem
			
			 model.addAttribute("isrepliedaddl", true); 
			 List<FpoAddlQry> getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
			 if(!(getAddlQry.isEmpty())) {
				 if(getAddlQry.get(0).getQRY_REPLY() == null)
					 model.addAttribute("isrepliedaddl", false);
				 else
					 model.addAttribute("isrepliedaddl", true); 
			
			 }
			 
			 
			 model.addAttribute("isrepliedqry", true); 
			 model.addAttribute("isqueryraised", false); 
			 
			 List<FpoQuery> getQry = fpoQueryRepo.getquerydetails(cinNo);
			 
			 //List<FpoAddlQry> getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
			 if(!(getQry.isEmpty())) {
				 model.addAttribute("isqueryraised", true); 
				 if(getQry.get(0).getRply()==null)
					 model.addAttribute("isrepliedqry", false);
				 else
					 model.addAttribute("isrepliedqry", true); 
			
			 }
			 
			
			 
			 
			
			
			Long examRaised = FPO_ORDERrepost.getOrderCinNo(cinNo);
			model.addAttribute("examraised", examRaised > 0);
			if(examRaised == 0) {
				model.addAttribute("examraisedtrue", 0);
			}else {	
				model.addAttribute("examraisedtrue", 1);
			}
			Long lastDetained = fpomvmntrepo.getDetainedCount(cinNo);
			model.addAttribute("lastDetained", lastDetained > 0);
			model.addAttribute("totitem", itemlist.size());
			model.addAttribute("lastDetainedNo", fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.get(0).getITEM_ID()));
			Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
			model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
			modelAndView = new ModelAndView("import/final_declared");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/addDutydetails")
	public ModelAndView addDutydetails(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
			HttpServletRequest request, Model model, HttpSession session) {

		FpoQuery fpoQuery = new FpoQuery();

		java.util.Date utilDate = new java.util.Date();
		fpo_item_det.setASS_DT(utilDate);
		fpo_item_det.setOFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpo_item_det.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpo_item_det.setROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fpo_item_det.setMODIFIED("N");

		if (fpo_item_det.getSW_NOTN().equals("Select NOTN"))
			fpo_item_det.setSW_NOTN(null);

		if (fpo_item_det.getBCD_NOTN().equals("Select NOTN"))
			fpo_item_det.setBCD_NOTN(null);

		if (fpo_item_det.getIGST_NOTN().equals("Select NOTN"))
			fpo_item_det.setIGST_NOTN(null);

		FPO_ITEMrepost.save(fpo_item_det);

		fpoQuery.setCinNo(fpo_item_det.getCinNo());
		fpoQuery.setITEM_ID(fpo_item_det.getITEM_ID());
		fpoQuery.setQRY_DATE(fpo_item_det.getASS_DT());
		fpoQuery.setQRY(fpo_item_det.getQuery());
		fpoQuery.setITEM_NO(fpo_item_det.getITEM_NO());
		fpoQuery.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpoQuery.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());

		fpoQueryRepo.save(fpoQuery);

		ModelAndView modelAndView = new ModelAndView("redirect:/EAD/declared?id=" + fpo_item_det.getCinNo());
		return modelAndView;
	}

	@RequestMapping(value = "/eadItemAssessmentUpdate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET eadItemAssessmentUpdate(@RequestBody FPO_ITEM_DET fpo_item_det,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		return fpoDeclaredService.eadItemAssessmentUpdate(fpo_item_det, session, request);
	}

	@RequestMapping(value = "/eadItemQryUpdate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET eadItemQryUpdate(@RequestBody FPO_ITEM_DET fpo_item_det,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		return fpoDeclaredService.eadItemQryUpdate(fpo_item_det, session, request);
	}

	@RequestMapping(value = "/sendBackApr", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_AC_CMTS sendBackApr(@RequestBody FPO_AC_CMTS fpoCmts, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		return fpoOrderService.sendBackApr(fpoCmts, session, request);

	}

	@RequestMapping(value = "/sendBackAcl", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_APR_CMTS sendBackAcl(@RequestBody FPO_APR_CMTS aprCmts, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		return fpoOrderService.sendBackAcl(aprCmts, session, request);

	}

	@RequestMapping(value = "/deleteFirstCheckApr", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ORDER deleteFirstCheckApr(@RequestBody FPO_ORDER fpoOrder, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoOrderService.deleteCheck(fpoOrder);

	}

	@RequestMapping(value = "/deletequery", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQuery deletequery(@RequestBody FpoQuery fpoquery, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.deletequery(fpoquery);
	}

	@RequestMapping(value = "/chkquery", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody Boolean chkquery(@RequestBody FpoQuery fpoquery, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		System.out.println("fpoquery cinno is " + fpoquery.getCinNo());
		System.out.println("value is " + fpoQueryRepo.getcouqry(fpoquery.getCinNo()));
		if (fpoQueryRepo.getcouqry(fpoquery.getCinNo()) > 0) {
			System.out.println("1");
			return true;
		} else {
			System.out.println("0");
			return false;
		}
	}

	@RequestMapping(value = "/updpaocmts", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ASS_PAO_CMTS updpaocmts(@RequestBody FPO_ASS_PAO_CMTS fpoasspaocmts,
			HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) {
		String cusite = request.getSession().getAttribute("cuSite").toString();
		String role = request.getSession().getAttribute("role").toString();
		String offid = request.getSession().getAttribute("offId").toString();
		return fpoDeclaredService.updatepaocmts(fpoasspaocmts, cusite, role, offid);
	}

	@RequestMapping(value = "/updateMainRole", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ORDER updateMainRole(@RequestBody FPO_ORDER fpoOrder, HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model) {
		return fpoOrderService.updateMainRole(fpoOrder,session,request);

	}

	@RequestMapping(value = "/updatedSpecificItemValue", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET updatedSpecificItemValue(@RequestBody FPO_ITEM_DET fpo_item_det,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.updatedSpecificItemValue(fpo_item_det);
	}

	@RequestMapping(value = "/nextItem", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET nextItem(@RequestBody FPO_ITEM_DET fpo_item_det, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.nextItem(fpo_item_det);
	}

	@RequestMapping(value = "/currItem", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET currItem(@RequestBody FPO_ITEM_DET fpo_item_det, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.currItem(fpo_item_det);
	}

	@RequestMapping(value = "/previousItem", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET previousItem(@RequestBody FPO_ITEM_DET fpo_item_det, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.previousItem(fpo_item_det);
	}

	@RequestMapping("/aafItemUpdate")
	public ModelAndView aafItemUpdate(@RequestBody FPO_ITEM_DET fpo_item_det, HttpServletRequest request, Model model,
			HttpSession session) {
		java.util.Date utilDate = new java.util.Date();
		System.out.println("cinno is " + fpo_item_det.getCinNo() + " and item no is " + fpo_item_det.getITEM_NO());
		List<FPO_AMEND> fpoAmend = fpoService.getFlag(fpo_item_det.getCinNo());
		System.out.println("fpoamend is " + fpoAmend);
		List<FPO_ITEM_DET> fpoItemDET = FPO_ITEMrepost.getItemByIdNo(fpo_item_det.getCinNo(),
				fpo_item_det.getITEM_NO());
		List<FPO_MAIN> s1 = FPOrepost.getmain(fpo_item_det.getCinNo());
		if (!((fpoAmend == null) || fpoAmend.isEmpty())) {
			System.out.println("cms here");
			System.out.println("size is " + fpoAmend.size() + 1l);
			fpoService.moveItemToAmend(fpoItemDET.get(0), fpoAmend.size() + 1l, session, request);
		} else {
			System.out.println("no here");
			fpoService.moveItemToAmend(fpoItemDET.get(0), 1l, session, request);
		}
		java.util.Date amddt = new java.util.Date();
		Long amdsno = FPO_ITEMrepost.getamendsno(fpo_item_det.getCinNo());
		System.out.println(fpo_item_det.getORIGCNTRYCD());
		if (fpo_item_det.getORIGCNTRYCD().equals("-NIL-"))
			fpo_item_det.setORIGCNTRYCD(fpo_item_det.getITEM_ID().substring(11, 2));
		if (fpo_item_det.getORIGCNTRYCD().length() > 2)
			fpo_item_det.setORIGCNTRYCD(fpo_item_det.getITEM_ID().substring(11, 2));
		FPO_ITEMrepost.updatechanges(fpo_item_det.getCinNo(), fpo_item_det.getITEM_NO(), fpo_item_det.getITEM_DESC(),
				fpo_item_det.getITEM_REVISEDDESC(), fpo_item_det.getNETWT(), fpo_item_det.getORIGCNTRYCD(),
				fpo_item_det.getCTH(), fpo_item_det.getCTH_REVISED(), fpo_item_det.getDECL_VAL(),
				fpo_item_det.getASSESS_VAL(), amdsno, fpo_item_det.getBCD_AMT(), fpo_item_det.getIGST_AMT(),
				fpo_item_det.getSW_AMT(), fpo_item_det.getNOU(), fpo_item_det.getCURRCD(), fpo_item_det.getRate(),
				amddt,
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				"U");
		// }
		fpoService.moveMainToAmend(s1.get(0), session);
		AMEND_ADD_REAS amendadd = new AMEND_ADD_REAS();
		amendadd.setCin_no(fpo_item_det.getCinNo());
		amendadd.setItem_Id(fpo_item_det.getITEM_ID());
		amendadd.setItem_No(fpo_item_det.getITEM_NO());
		amendadd.setAmend_Item("Y");
		amendadd.setReasons(fpo_item_det.getReasons());
		amendadd.setTask_dt(amddt);
		amendadd.setRole(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		amendadd.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		amdaddRepo.save(amendadd);
		float totdutypay;
		float totduty = s1.get(0).getTOT_DUTY();
		float totassval = s1.get(0).getTOT_ASS_VAL();
		float insval = s1.get(0).getINS_CALC_VAL();
		float frtval = s1.get(0).getFRT_CALC_VAL();
		float totasscalcval = s1.get(0).getTOTASS_CALC_VAL();
		float assval = fpo_item_det.getDECL_VAL() * fpo_item_det.getRate();
		if (totassval > (float) request.getSession().getAttribute("dutyCalc_assval"))
			totdutypay = totduty;
		else if (s1.get(0).getNATURE_TYPE_CD()
				.equals(request.getSession().getAttribute("dutyCalc_category").toString()))
			totdutypay = totduty;
		else
			totdutypay = 0f;
		float newtotasscalcval = totasscalcval + assval;
		float newtotassval = totassval + assval;
		float newassval = (newtotassval * assval / newtotasscalcval);
		FPOrepost.updtotasscalcval(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updtotassval(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updassvalinsfrtitmid(fpo_item_det.getCinNo(),
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updbcditmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updswitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updigstitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.upddutyitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updtotduty(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.upddutypay(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString(), totdutypay);
		fpoAmend = fpoService.getFlag(fpo_item_det.getCinNo());
		model.addAttribute("totassval", totassval);
		model.addAttribute("totduty", totduty);
		model.addAttribute("check", fpoAmend.get(0));
		model.addAttribute("amend", fpoItemDET);
		model.addAttribute("head", s1.get(0));
		ModelAndView modelAndView = new ModelAndView("redirect:/final_declared?id=" + fpo_item_det.getCinNo());
		return modelAndView;
	}

	@RequestMapping("/aafItemAdd")
	public ModelAndView aafItemAdd(@RequestBody FPO_ITEM_DET fpo_item_det, HttpServletRequest request, Model model,
			HttpSession session) {
		System.out.println("in aafitemadd");
		java.util.Date utilDate = new java.util.Date();
		System.out.println("cinno is " + fpo_item_det.getCinNo() + " and item no is " + fpo_item_det.getITEM_NO());
		System.out.println("cth is " + fpo_item_det.getCTH());
		List<FPO_AMEND> fpoAmend = fpoService.getFlag(fpo_item_det.getCinNo());

		List<FPO_MAIN> s1 = FPOrepost.getmain(fpo_item_det.getCinNo());

		/*
		 * List<FPO_ITEM_DET> fpoItemDET =
		 * FPO_ITEMrepost.getItemByIdNo(fpo_item_det.getCinNo(),
		 * fpo_item_det.getITEM_NO());
		 */
		List<FPO_ITEM_DET> fpoItemDET = FPO_ITEMrepost.getItemByIdNo(fpo_item_det.getCinNo());

		System.out.println("no here");

		if (!((fpoAmend == null) || fpoAmend.isEmpty())) {
			System.out.println("cms here");
			System.out.println("size is " + fpoAmend.size() + 1l);
			fpoService.moveItemToAmend(fpoItemDET.get(0), fpoAmend.size() + 1l, session, request);
		} else {
			System.out.println("no here");
			fpoService.moveItemToAmend(fpoItemDET.get(0), 1l, session, request);
		}
		java.util.Date amddt = new java.util.Date();
		Long amdsno = FPO_ITEMrepost.getamendsno(fpo_item_det.getCinNo());

		fpoService.moveMainToAmend(s1.get(0), session);
		// as item is newly added it will not go to amendment table//
		System.out.println(fpo_item_det.getORIGCNTRYCD());
		float totdutypay;
		float totduty = s1.get(0).getTOT_DUTY();
		float totassval = s1.get(0).getTOT_ASS_VAL();
		float insval = s1.get(0).getINS_CALC_VAL();
		float frtval = s1.get(0).getFRT_CALC_VAL();
		float totasscalcval = s1.get(0).getTOTASS_CALC_VAL();
		if (fpo_item_det.getORIGCNTRYCD().equals("-NIL-"))
			fpo_item_det.setORIGCNTRYCD(null);
		if (fpo_item_det.getORIGCNTRYCD().length() > 2)
			fpo_item_det.setORIGCNTRYCD(null);
		FPO_ITEM_DET fpoadditem = new FPO_ITEM_DET();
		fpoadditem.setCinNo(fpo_item_det.getCinNo());
		fpoadditem.setCIN_DT(s1.get(0).getCIN_DT());
		fpoadditem.setPOSTINGDT(s1.get(0).getPOSTINGDT());
		fpoadditem.setMESG_TYPE_CD(s1.get(0).getMESG_TYPE_CD());
		fpoadditem.setITEM_NO(fpo_item_det.getITEM_NO());
		fpoadditem.setJOB_DT(utilDate);
		fpoadditem.setITEM_ID(s1.get(0).getITEM_ID());
		fpoadditem.setNOU(fpo_item_det.getNOU());
		Float assval = fpo_item_det.getDECL_VAL() * fpo_item_det.getRate();
		fpoadditem.setASSESS_VAL(assval);
		float newtotasscalcval = totasscalcval + assval;
		float newtotassval = totassval + assval;
		float newassval = (newtotassval * assval / newtotasscalcval);
		fpoadditem.setGEN_CTH("98049000");
		Float bcd = (((float) request.getSession().getAttribute("dutyCalc_bcdrta")) * newassval) / 100;
		fpoadditem.setBCD_AMT(bcd);
		Float sw = (((float) request.getSession().getAttribute("dutyCalc_swrta")) * bcd) / 100;
		fpoadditem.setSW_AMT(sw);
		Float igst = (((float) request.getSession().getAttribute("dutyCalc_igstrta")) * (newassval + bcd + sw)) / 100;
		fpoadditem.setIGST_AMT(igst);
		fpoadditem.setDUTY(bcd + igst + sw);
		fpoadditem.setMODIFIED("A");
		fpoadditem.setBCD_RTA((float) request.getSession().getAttribute("dutyCalc_bcdrta"));
		fpoadditem.setIGST_RTA((float) request.getSession().getAttribute("dutyCalc_igstrta"));
		fpoadditem.setSW_RTA((float) request.getSession().getAttribute("dutyCalc_swrta"));
		fpoadditem.setITEM_DESC(fpo_item_det.getITEM_DESC());
		fpoadditem.setITEM_REVISEDDESC(fpo_item_det.getITEM_REVISEDDESC());
		fpoadditem.setNETWT(fpo_item_det.getNETWT());
		fpoadditem.setORIGCNTRYCD(fpo_item_det.getORIGCNTRYCD());
		fpoadditem.setCTH(fpo_item_det.getCTH());
		fpoadditem.setCTH_REVISED(fpo_item_det.getCTH_REVISED());
		fpoadditem.setORIGCNTRYCD(fpo_item_det.getORIGCNTRYCD());
		fpoadditem.setDECL_VAL(fpo_item_det.getDECL_VAL());
		fpoadditem.setASSESS_VAL(assval);
		fpoadditem.setASSVAL_INSFRT(newassval);
		fpoadditem.setBCD_AMT(bcd);
		fpoadditem.setIGST_AMT(igst);
		fpoadditem.setSW_AMT(sw);
		fpoadditem.setDUTY(bcd + igst + sw);
		totduty = totduty + bcd + igst + sw;
		fpoadditem.setAMEND_FLAG("A");
		fpoadditem.setOFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpoadditem.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpoadditem.setROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fpoadditem.setCURRCD(fpo_item_det.getCURRCD());
		fpoadditem.setRate(fpo_item_det.getRate());
		fpoadditem.setAMEND_DT(amddt);
		System.out.println("cms here");
		System.out.println(fpo_item_det.getReasons());
		FPO_ITEMrepost.save(fpoadditem);
		AMEND_ADD_REAS amendadd = new AMEND_ADD_REAS();
		amendadd.setCin_no(fpo_item_det.getCinNo());
		amendadd.setItem_Id(fpo_item_det.getITEM_ID());
		amendadd.setItem_No(fpo_item_det.getITEM_NO());
		amendadd.setAdd_Item("Y");
		amendadd.setReasons(fpo_item_det.getReasons());
		amendadd.setTask_dt(amddt);
		amendadd.setRole(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		amendadd.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		amdaddRepo.save(amendadd);
		fpoService.moveMainToAmend(s1.get(0), session);
		if (totassval > (float) request.getSession().getAttribute("dutyCalc_assval"))
			totdutypay = totduty;
		else if (s1.get(0).getNATURE_TYPE_CD()
				.equals(request.getSession().getAttribute("dutyCalc_category").toString()))
			totdutypay = totduty;
		else
			totdutypay = 0f;
		FPOrepost.updtotasscalcval(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updtotassval(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updassvalinsfrtitmid(fpo_item_det.getCinNo(),
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updbcditmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updswitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updigstitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.upddutyitmid(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.updtotduty(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		FPOrepost.upddutypay(fpo_item_det.getCinNo(), request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString(), totdutypay);

		model.addAttribute("head", s1.get(0));
		model.addAttribute("newitemno", fpo_item_det.getITEM_NO() + 1);
		ModelAndView modelAndView = new ModelAndView("redirect:/final_declared?id=" + fpo_item_det.getCinNo());
		return modelAndView;
	}

	@RequestMapping(value = "/eadItemDeclaredUpdate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET eadItemDeclaredUpdate(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		return fpoAmendService.amendItems(fpoItemDet, session, request).get(0);
	}

	/*
	 * @RequestMapping("/addNewItem") public ModelAndView
	 * addNewItem(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
	 * HttpServletRequest request, Model model) { java.util.Date utilDate = new
	 * java.util.Date(); fpo_item_det.setAMEND_FLAG("A");
	 * fpo_item_det.setAMEND_DT(utilDate); fpo_item_det.setAMEND_SERAIL_NO(0l);
	 * fpo_item_det.setOFF_ID(offId); fpo_item_det.setROLE(role);
	 * fpo_item_det.setMODIFIED("N");
	 * fpo_item_det.setITEM_NO(FPO_ITEMrepost.getNoOfYesNoItems(fpo_item_det.
	 * getCinNo()) + 1l); FPO_ITEMrepost.save(fpo_item_det); List<FPO_AMEND>
	 * fpoAmend = fpoService.getFlag(fpo_item_det.getCinNo()); List<FPO_ITEM_DET>
	 * fpoItemDET = FPO_ITEMrepost.getitem(fpo_item_det.getCinNo()); List<FPO_MAIN>
	 * s1 = FPOrepost.getmain(fpo_item_det.getCinNo());
	 * 
	 * if (!((fpoAmend == null) || fpoAmend.isEmpty())) model.addAttribute("check",
	 * fpoAmend.get(0)); model.addAttribute("amend", fpoItemDET);
	 * model.addAttribute("head", s1.get(0));
	 * 
	 * ModelAndView modelAndView = new ModelAndView("redirect:/EAD/ead_item?id=" +
	 * fpo_item_det.getCinNo()); return modelAndView; }
	 */

	@RequestMapping(value = "/changeItem", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET changeItem(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		List<FPO_ITEM_DET> fpoItemDET = fpoService
				.getListPojo(fpoDeclaredService.getItemOnCin(fPO_ITEM_DET.getCinNo(), fPO_ITEM_DET.getITEM_NO()));
		fpoItemDET.get(0)
				.setQuery(fpoQueryRepo.getQueryOnCinAndItemNo(fPO_ITEM_DET.getCinNo(), fPO_ITEM_DET.getITEM_NO()));
		model.addAttribute("check", fpoItemDET.get(0));
		return fpoItemDET.get(0);
	}

	@RequestMapping(value = "/addNewItemDetails", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET addNewItemDetails(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		String cinNo = request.getParameter("id");
		String itemNo = request.getParameter("itemNo");
		List<FPO_AMEND> amend = fpoService.getFlag(cinNo);

		if (null == amend || amend.isEmpty()) {
			List<FPO_ITEM_DET> fpoItemDET = FPO_ITEMrepost.getItemByIdNo(cinNo, Long.valueOf(itemNo));
			return fpoItemDET.get(0);
		} else {
			return fpoService.moveAmendToItem(amend.get(0));
		}
	}

	@RequestMapping(value = "/getBcdNotification", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getBcdNotification(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		String cth = request.getParameter("cth");
		List<Object> getBcdNotification = FPO_ITEMrepost.getBcdNotification(cth);
		model.addAttribute("getBcdNotification", getBcdNotification);
		return getBcdNotification;
	}

	@RequestMapping(value = "/getBcdSerialNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getBcdSerialNo(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String notificationNo = request.getParameter("notificationNo");
		String cth = request.getParameter("cth");
		List<Object> getBcdSlNo = null;
		 getBcdSlNo = FPO_ITEMrepost.getBcdSlNo(notificationNo, cth);
		  model.addAttribute("getBcdSlNo", getBcdSlNo);

		return getBcdSlNo;
	}

	@RequestMapping(value = "/getBcdRate", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getBcdRate(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String cinNo = request.getParameter("cinNo");
		String getBcdSlNo = request.getParameter("getBcdSlNo");
		String cth = request.getParameter("cth");
		List<Object> getBcdRate = FPO_ITEMrepost.getBcdRate(getBcdSlNo, cth);
		List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
		List<FPO_ITEM_DET> fpoItemDET = new ArrayList<FPO_ITEM_DET>();
		fpoItemDET.add(fPO_ITEM_DET);
		List<Object> dataItemObject = null; 
		dataItemObject = fpoService.bcdCalculation(getBcdRate, fpoMainData, fpoItemDET);
		return dataItemObject;
	}
	
	@RequestMapping(value = "/getbasiceffrt", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getbasiceffrt(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String cinNo = request.getParameter("cinNo");
		List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
		List<FPO_ITEM_DET> fpoItemDET = new ArrayList<FPO_ITEM_DET>();
		fpoItemDET.add(fPO_ITEM_DET);
		List<Object> dataItemObject = new ArrayList<Object>(); 
		float BcdRate,IGSTRate,SWRate;
		System.out.println("duty calc details is " + dutyCalcDetailsRepo.findAll().get(0).getBcdRta_eff());
		System.out.println("duty calc details is " + request.getSession().getAttribute("dutyCalc_bcdrta_eff").toString());
		String bcdrt = request.getSession().getAttribute("dutyCalc_bcdrta_eff").toString();
		BcdRate = Float.parseFloat(request.getSession().getAttribute("dutyCalc_bcdrta_eff").toString());
		IGSTRate = Float.parseFloat(request.getSession().getAttribute("dutyCalc_igstrta").toString());
		SWRate = Float.parseFloat(request.getSession().getAttribute("dutyCalc_swrta").toString());
		Float basicValue = 0.01f;
		float assvalinsfrt = fPO_ITEM_DET.getASSVAL_INSFRT();
		float bcdduty = assvalinsfrt*BcdRate*basicValue;
		float swduty = bcdduty * SWRate * basicValue;
		float igstduty = (bcdduty+swduty+assvalinsfrt)*IGSTRate * basicValue;
		float totduty = bcdduty+swduty+igstduty;
        dataItemObject.add(0, Float.valueOf(df2.format(BcdRate)));
        dataItemObject.add(1, Float.valueOf(df2.format(bcdduty)));
        dataItemObject.add(2, Float.valueOf(df2.format(0.0f)));
        dataItemObject.add(3, Float.valueOf(df2.format(totduty)));
        dataItemObject.add(4, Float.valueOf(df2.format(0.0f)));
        dataItemObject.add(5, Float.valueOf(df2.format(bcdduty)));
        dataItemObject.add(6, Float.valueOf(df2.format(0.0f)));

        dataItemObject.add(7, Float.valueOf(df2.format(IGSTRate)));
        dataItemObject.add(8, Float.valueOf(df2.format(igstduty)));
        dataItemObject.add(9, Float.valueOf(df2.format(0.0f)));

        dataItemObject.add(10, Float.valueOf(df2.format(SWRate)));
        dataItemObject.add(11, Float.valueOf(df2.format(swduty)));
        dataItemObject.add(12, Float.valueOf(df2.format(0.0f)));
		return dataItemObject;
	}
	

	@RequestMapping(value = "/getIgstNotification", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getIgstNotification(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String cth = request.getParameter("cth");
		List<Object> getIgstNotification = FPO_ITEMrepost.getIgstNotification(cth);
		model.addAttribute("getIgstNotification", getIgstNotification);
		return getIgstNotification;
	}

	@RequestMapping(value = "/getIgstSerialNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getIgstSerialNo(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		String notificationNo = request.getParameter("notificationNo");
		String cth = request.getParameter("cth");
		List<Object> getIgstSerialNo = FPO_ITEMrepost.getIgstSerialNo(notificationNo, cth);
		model.addAttribute("getIgstSerialNo", getIgstSerialNo);
		return getIgstSerialNo;
	}

	@RequestMapping(value = "/getIgstRate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getIgstRate(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String cinNo = request.getParameter("cinNo");
		String getSlNo = request.getParameter("getSlNo");
		String cth = request.getParameter("cth");
		List<Object> getIgstRate = FPO_ITEMrepost.getIgstRate(getSlNo, cth);
		List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
		model.addAttribute("getIgstRate", getIgstRate);
		List<FPO_ITEM_DET> fpoItemDET = new ArrayList<FPO_ITEM_DET>();
		fpoItemDET.add(fPO_ITEM_DET);
		List<Object> dataItemObject = fpoService.igstCalculation(getIgstRate, fpoMainData, fpoItemDET);
		return dataItemObject;
	}

	@RequestMapping(value = "/getSwNotification", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getSwNotification(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		String cth = request.getParameter("cth");
		List<Object> getSwNotification = FPO_ITEMrepost.getSwNotification(cth);
		model.addAttribute("getSwNotification", getSwNotification);
		return getSwNotification;
	}

	@RequestMapping(value = "/getSwSerialNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getSwSerialNo(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String notificationNo = request.getParameter("notificationNo");
		String cth = request.getParameter("cth");
		List<Object> getSwSerialNo = FPO_ITEMrepost.getSwSerialNo(notificationNo, cth);
		model.addAttribute("getSwSerialNo", getSwSerialNo);
		return getSwSerialNo;
	}

	@RequestMapping(value = "/getSwRate", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getSwRate(@RequestBody FPO_ITEM_DET fPO_ITEM_DET, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String cinNo = request.getParameter("cinNo");
		String getSlNo = request.getParameter("getSlNo");
		String cth = request.getParameter("cth");
		List<Object> getSwRate = FPO_ITEMrepost.getSwRate(getSlNo, cth);
		List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
		model.addAttribute("getSwRate", getSwRate);
		List<FPO_ITEM_DET> fpoItemDET = new ArrayList<FPO_ITEM_DET>();
		fpoItemDET.add(fPO_ITEM_DET);
		List<Object> dataItemObject = fpoService.swCalculation(getSwRate, fpoMainData, fpoItemDET);
		return dataItemObject;
	}

	@RequestMapping(value = "/getTotalNoItems", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody String getTotalNoItems(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("here in gettotalitems cinno is " + request.getParameter("cinNo"));
		return FPO_ITEMrepost.maxOfItemNumber(request.getParameter("cinNo")).toString();
	}

	@RequestMapping("/deleteItem")
	public ModelAndView deleteItem(HttpServletRequest request, Model model) {
		String cinNo = request.getParameter("id");
		String itemNo = request.getParameter("itemNo");

		List<FPO_ITEM_DET> fpoItemDET = FPO_ITEMrepost.getitem(cinNo);

		if (fpoItemDET.size() > 1)
			FPO_ITEMrepost.deleteFpoItem("D", cinNo, itemNo);

		List<FPO_ITEM_DET> s = FPO_ITEMrepost.getitem(cinNo);
		if (!((s == null) || s.isEmpty()) && !(null == s.get(0).getMODIFIED() || s.get(0).getMODIFIED().isEmpty()))
			model.addAttribute("amend", s);
		else
			model.addAttribute("check", s.get(0));

		if (!model.containsAttribute("check")) {
			List<FPO_AMEND> s2 = fpoService.getFlag(cinNo);
			if (!((s2 == null) || s2.isEmpty()))
				model.addAttribute("check", s2.get(0));
		}

		List<FPO_MAIN> s1 = FPOrepost.getmain(cinNo);
		if (!((s1 == null) || s1.isEmpty()))
			s1.get(0).setMODIFIED(s.get(0).getMODIFIED());
		model.addAttribute("head", s1.get(0));

		ModelAndView modelAndView = new ModelAndView("redirect:/ead_item?id=" + cinNo);
		return modelAndView;
	}

	@RequestMapping("/import_item")
	public ModelAndView import_item(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		List<FPO_ITEM_DET> s = FPO_ITEMrepost.getitem(id);
		model.addAttribute("check", s);
		String head = request.getParameter("id");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(head);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		ModelAndView modelAndView = new ModelAndView("import/import_item");
		return modelAndView;
	}

	@RequestMapping("/ead_submit")
	public ModelAndView ead_submit(HttpServletRequest request, Model model, HttpSession session)

	{
		
		
		String cinNo = request.getParameter("id");
		String cat = request.getParameter("cat");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E4", session, request);
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.firstCheck(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		// fpoCurQueService.updateUSerEnterStatus(cinNo, "E4");
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		List<DECI_REAS> reasons = DECI_REASrepost.getREAS();
		model.addAttribute("reason", reasons);
		model.addAttribute("getOrder", fpoorder);
		model.addAttribute("assvalacl", maxaclassval);
		model.addAttribute("cat", cat);
		String flag = (String)session.getAttribute("flag");
		model.addAttribute("flag", flag);
		ModelAndView modelAndView = new ModelAndView("EAD/ead_submit");
		return modelAndView;
	}

	@RequestMapping("/import_submit")
	public ModelAndView import_submit(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("in import submit");
		String cinNo = request.getParameter("id");
		String det, ass, fchk, exm;
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpomvmntrepo.getMaxSlOnCin(cinNo);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + cinNo);
		Long counoitems = FPOrepost.getnoitems(cinNo);
		Long detstatus = 0L;
		if (FPOrepost.getcouexmfind(cinNo) > 0)
			detstatus = FPOrepost.getdetstatus(cinNo);
		Long assstatus = FPO_ITEMrepost.getassCount(cinNo);
		Long exmstatus = FPO_ORDERrepost.getOrderCinNo(cinNo);
		Long fchkstatus = FPOrepost.getfchkstatus(cinNo);
		Long oocenbl = FPOrepost.getoocenbl(cinNo);
//		if (fpomvmntrepo.getOOCfirsttime(cinNo) == 0)
		fpoService.insertIntofpoMvmntDb(fpomvmnt, cinNo, fpomvmntrepo.getcindtmvmnt(cinNo), curdt, slno, "PSU", "P3",
				session, request);
		System.out.println("oocenbl is" + oocenbl);
		if (detstatus > 0)
			det = "Yes";
		else
			det = "No";
		if (fchkstatus > 0)
			fchk = "Yes";
		else
			fchk = "No";
		if (assstatus > 0)
			ass = "Yes";
		else
			ass = "No";
		if (exmstatus > 0)
			exm = "Yes";
		else
			exm = "No";
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		DecimalFormat df = new DecimalFormat("#####.##");
		// System.out.println(df.format(PI));
		System.out.println("just testing " + String.format("%.02f", fpoMain.get(0).getTOT_ASS_VAL()));
		System.out.println("Value with 3 digits after decimal point " + df.format(fpoMain.get(0).getTOT_ASS_VAL()));
		System.out.println("TOTDUTY IS %0.2f" + fpoMain.get(0).getTOT_DUTY());
		System.out.println("TOTASSVAL IS %0.02f" + fpoMain.get(0).getTOT_ASS_VAL());
		List<Collection> fpoItem = FPOrepost.getitem(cinNo);
		List<Collection> grandtot = new ArrayList<>();
		// grandtot = reportService.getgrandtot(cinNo);
		grandtot = FPOrepost.getgrandtot(cinNo);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("item", fpoItem);
		model.addAttribute("counoitems", counoitems);
		model.addAttribute("det", det);
		model.addAttribute("ass", ass);
		model.addAttribute("exm", exm);
		model.addAttribute("fchk", fchk);
		model.addAttribute("oocenbl", oocenbl);
		model.addAttribute("gtot", grandtot);
		model.addAttribute("examdepcmts",
				FPOrepost.getexamdepcmts(cinNo));
		Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
		model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
		ModelAndView modelAndView = new ModelAndView("import/import_submit");
		System.out.println("Import Submit invoker");
		return modelAndView;
	}

	@RequestMapping("/ead_query")
	public ModelAndView ead_query(HttpServletRequest request, Model model, HttpSession session) {
		String cinNo = request.getParameter("id");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);

		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo);

		Float maxaclassval = FPOrepost.getaclassval();

		if (null != getDefQry.get(0).getDEFUALT_QUERY()) {
			String getQuery = getDefQry.get(0).getDEFUALT_QUERY();
			String[] array1 = getQuery.split(";");
			getDefQry.get(0).setDefqry1(array1[0]);
			getDefQry.get(0).setDefqry2(array1[1]);
			getDefQry.get(0).setDefqry3(array1[2]);
			getDefQry.get(0).setDefqry4(array1[3]);
		}

		List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);

		List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
		FpoQueryDin fpoQueryDin = new FpoQueryDin();
		fpoQueryDin.setRemarks("");
		fpoQueryDin.setDepComments("");
		AllcmtsList.add(fpoQueryDin);

		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "E2", session, request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("getAllFpoQuery", getAllFpoQuery);
		model.addAttribute("getDefQry", getDefQry);
		model.addAttribute("assvalacl", maxaclassval);
		model.addAttribute("emailid", getDefQry.get(0).getEmail_Id());
		model.addAttribute("phoneno", getDefQry.get(0).getMobile_No());

		if (null != getAllcmts && !getAllcmts.isEmpty())
			model.addAttribute("getAllcmts", getAllcmts);
		else
			model.addAttribute("getAllcmts", AllcmtsList);

		ModelAndView modelAndView = new ModelAndView("EAD/ead_query");
		return modelAndView;

	}

	
	
	@RequestMapping(value = "/deleteAddquery", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoAddlQry deleteAddquery(@RequestBody FpoAddlQry FpoAddlQry, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.deleteAddlquery(FpoAddlQry);
	}
	
	@RequestMapping("/pen_ead_query")
	public ModelAndView penead_query(HttpServletRequest request, Model model, HttpSession session)
			throws java.text.ParseException {
		
		
		String cinNo = request.getParameter("id");
		
		List<FpoAddlQry> fpoAddlQry = fpoAddlQryRepo.getAddQryDetails(cinNo);
		
		
		
		
		//santhosh,kani,veem
		
		
		Long maxId = fpomvmntrepo.getIdOnCin(cinNo);
		model.addAttribute("cancelquery", false);
		model.addAttribute("notShowIfNotReply", true);
	//kani,veem,santhosh
		
		if (maxId != null) {
			FpoMvmnt fpoMvmnt = fpomvmntrepo.findById(maxId).orElse(null);
			if (fpoMvmnt != null) {
				String stage = fpoMvmnt.getStage();
				System.out.println(stage);
				String role = fpoMvmnt.getRole();
				System.out.println(role);
				
				if(role.equals("PAC")&&stage.equals("A2")) 
					{
					if(!fpoAddlQry.isEmpty())
					if(!fpoAddlQry.get(0).getQRY_TYPE().equals("D"))
					model.addAttribute("cancelquery", true);
					}
				}
			}
		
		
	/*	//santhosh,veem,kani
	
	    List<FpoAddlQry> getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
	
		List<FPO_ITEM_DET> fpoItem = FPO_ITEMrepost.getItemOnCin(cinNo, 1L);
		
		
	
		
		if(!(getAddlQry.isEmpty())) {
			 model.addAttribute("addqrygenerated", true);
		if(getAddlQry.get(0).getQRY_REPLY() == null) {
			model.addAttribute("notShowIfNotReply", false);
			 model.addAttribute("replystatus", false); 
		 } else {
			 model.addAttribute("notShowIfNotReply", true);
			 model.addAttribute("replystatus", true); 
		 }
		}
		else
			 model.addAttribute("addqrygenerated", false);
		
		*/
		//santhosh,veem,kani
		
		
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);

		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		
		  List<FpoAddlQry> getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
		  if(!(getAddlQry.isEmpty()))
		  { model.addAttribute("addqrygenerated", true);
		  if(getAddlQry.get(0).getQRY_REPLY() == null)
		  {
		  model.addAttribute("notShowIfNotReply", false);
		  model.addAttribute("replystatus", false); 
		  } 
		  else 
		  {
			  model.addAttribute("notShowIfNotReply", true);
		  model.addAttribute("replystatus", true); 
		  } 
		  
		  } else
		  model.addAttribute("addqrygenerated", false);
		 
		
		
		List<FPO_ITEM_DET> fpoItem = FPO_ITEMrepost.getItemOnCin(cinNo, 1L);
		
		List<SelectTag> fpoalert = reportService.getAlerts(fpoMain.get(0));
		List<String> alert = new ArrayList<String>();
		if (fpoalert.size() > 0) {
			for (SelectTag selectTag : fpoalert) {
				Date d = new Date();
				Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
				if (d.compareTo(d1Date) >= 0)
					alert.add(selectTag.getValue());
			}

			if (alert.size() > 0) {
				model.addAttribute("alerts", alert);
			} else {
				model.addAttribute("alerts", null);
			}

		} else {
			model.addAttribute("alerts", null);
		}
		
		

		List<SelectTag> sitefpoalert = reportService.getSiteAlerts(fpoMain.get(0));
		List<String> sitealert = new ArrayList<String>();
		if (sitefpoalert.size() > 0) {
			for (SelectTag selectTag : sitefpoalert) {
				Date d = new Date();
				Date d1Date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(selectTag.getData());
				if (d.compareTo(d1Date) >= 0)
					sitealert.add(selectTag.getValue());
			}

			if (sitealert.size() > 0) {
				model.addAttribute("sitealert", sitealert);
			} else {
				model.addAttribute("sitealert", null);
			}

		} else {
			model.addAttribute("sitealert", null);
		}

		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo);
		String getDepcmts = fpoQueryRepo.getDepcmts(cinNo);
		/*
		 * List<FpoQuery> reply = fpoQueryRepo.getreply(cinNo);
		 * System.out.println(reply.get(0).getItem_no());
		 */
		/* System.out.println(reply.get(0).getRPLY()); */
		if (null != getDefQry.get(0).getDEFUALT_QUERY()) {
			String getQuery = getDefQry.get(0).getDEFUALT_QUERY();
			String[] array1 = getQuery.split(";");
			getDefQry.get(0).setDefqry1(array1[0]);
			getDefQry.get(0).setDefqry2(array1[1]);
			getDefQry.get(0).setDefqry3(array1[2]);
			getDefQry.get(0).setDefqry4(array1[3]);
		}

		List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);

		List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
		FpoQueryDin fpoQueryDin = new FpoQueryDin();
		fpoQueryDin.setRemarks("");
		fpoQueryDin.setDepComments("");
		AllcmtsList.add(fpoQueryDin);

		Boolean back = Boolean.valueOf(request.getParameter("back"));
		if (!back) {
			fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "N2", session, request);
		} else {
			model.addAttribute("back", back);
		}
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("getAllFpoQuery", getAllFpoQuery);
		model.addAttribute("getDefQry", getDefQry);
		model.addAttribute("getDepcmts", getDepcmts);
	//	model.addAttribute("curque",
	//			fpoCurQueRepo.getcurqueCin(cinNo, request.getSession().getAttribute("cuSite") == null ? null
	//					: request.getSession().getAttribute("cuSite").toString()));
		model.addAttribute("curque",fpoCurQueRepo.getcurqueCin(cinNo));
		if (null != getAllcmts && !getAllcmts.isEmpty())
			model.addAttribute("getAllcmts", getAllcmts);
		else
			model.addAttribute("getAllcmts", AllcmtsList);

		List<FpoSecondDefaultQuery> getScndDefQry = fpoService.getSecondDefualtQuery();
		model.addAttribute("getScndDefQry", getScndDefQry);

		String filename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA" , session, request);
		model.addAttribute("AAAAdditionQueryFileName", filename);
		model.addAttribute("AAAAdditionQueryFileExist", filename.equalsIgnoreCase("") ? false : true);

		ModelAndView modelAndView = new ModelAndView("EAD/pen_ead_query");
		return modelAndView;

	}

	
	
	
	
	
	@RequestMapping(value = "/getpdf", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody int raiseqryreply(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) {

		String cinNo = request.getParameter("cinNo");
		String que = request.getParameter("que");
		String din = request.getParameter("din");
		fpoService.fpo_addl_qry_crpdf(cinNo, que, session, request);
		String toMobileNumber = request.getSession().getAttribute("toMobileNumber") == null ? null
				: request.getSession().getAttribute("toMobileNumber").toString();
		String toEnteredMobileNumber = request.getSession().getAttribute("toEnteredMobileNumber") == null ? null
				: request.getSession().getAttribute("toEnteredMobileNumber").toString();
		String smsText = "INDIAN CUSTOMS - raised query - Ref: Postal Art.ID - "
				+ request.getSession().getAttribute("itemid") + " / DCALL- "
				+ request.getSession().getAttribute("dcallno") + " Reply via Post/Visit the URL: "
				+ request.getSession().getAttribute("smsurl") + " -CBIC";
		if (toMobileNumber != null && !toMobileNumber.equalsIgnoreCase("") && toMobileNumber.length() >= 10) {
			if (toMobileNumber.length() > 10) {
				toMobileNumber = toMobileNumber.substring(toMobileNumber.length() - 10);
			}
			// later on this is to be changed during launch
			if (toMobileNumber != null) {
				// fpoService.sendSms("9740388057", smsText, "1107163548035935781",
				// request.getSession().getAttribute("dcallno") == null ? null :
				// request.getSession().getAttribute("dcallno").toString(),
				// cinNo, session,request);
				fpoService
						.sendSms("9740388057", smsText, "1107167637041176783",
								request.getSession().getAttribute("dcallno") == null ? null
										: request.getSession().getAttribute("dcallno").toString(),
								cinNo, session, request);
			}
		}
		if (toEnteredMobileNumber != null && !toEnteredMobileNumber.equalsIgnoreCase("")
				&& (toEnteredMobileNumber.length() >= 10)) {
			if (toEnteredMobileNumber.length() > 10) {
				toEnteredMobileNumber = toEnteredMobileNumber.substring(toEnteredMobileNumber.length() - 10);
			}
			if (toEnteredMobileNumber != null) {
				// fpoService.sendSms(toEnteredMobileNumber, smsText, "1107163548035935781",
				// request.getSession().getAttribute("dcallno") == null ? null :
				// request.getSession().getAttribute("dcallno").toString(),
				// cinNo, session,request);
				fpoService
						.sendSms(toEnteredMobileNumber, smsText, "1107167637041176783",
								request.getSession().getAttribute("dcallno") == null ? null
										: request.getSession().getAttribute("dcallno").toString(),
								cinNo, session, request);
			}
		}
		// LoginController.toMobileNumber = null;
		// LoginController.toEnteredMobileNumber = null;
		// request.getSession().setAttribute("toEnteredMobileNumber",
		// toEnteredMobileNumber);
		// request.getSession().setAttribute("toMobileNumber", null);
		return fpoService.sendaddlqrymail(cinNo,
				request.getSession().getAttribute("itemid") == null ? null
						: request.getSession().getAttribute("itemid").toString(),
				request.getSession().getAttribute("dcallno") == null ? null
						: request.getSession().getAttribute("dcallno").toString(),
				session, request);

	}
	
// --------------------------------------------------------- commented BY VEEMAN on 22/04/2023 -------------------------------------------- 

//	@RequestMapping("/import_query")
//	public ModelAndView import_query(HttpServletRequest request, Model model, HttpSession session) {
//
//		String cinNo = request.getParameter("id");
//		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
//
//		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo);
//
//		Float maxaclassval = FPOrepost.getaclassval();
//
//		if (null != getDefQry.get(0).getDEFUALT_QUERY()) {
//			String getQuery = getDefQry.get(0).getDEFUALT_QUERY();
//			String[] array1 = getQuery.split(";");
//			getDefQry.get(0).setDefqry1(array1[0]);
//			getDefQry.get(0).setDefqry2(array1[1]);
//			getDefQry.get(0).setDefqry3(array1[2]);
//			getDefQry.get(0).setDefqry4(array1[3]);
//		}
//
//		List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
//
//		List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
//		FpoQueryDin fpoQueryDin = new FpoQueryDin();
//		fpoQueryDin.setRemarks("");
//		fpoQueryDin.setDepComments("");
//		AllcmtsList.add(fpoQueryDin);
//
//		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "P2", session, request);
//		fpoService.setFpoMainValues(fpoMain);
//		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
//		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
//		model.addAttribute("head", fpoMain.get(0));
//		model.addAttribute("getAllFpoQuery", getAllFpoQuery);
//		model.addAttribute("getDefQry", getDefQry);
//		model.addAttribute("assvalacl", maxaclassval);
//		model.addAttribute("emailid", getDefQry.get(0).getEmail_Id());
//		model.addAttribute("phoneno", getDefQry.get(0).getMobile_No());
//
//		if (null != getAllcmts && !getAllcmts.isEmpty())
//			model.addAttribute("getAllcmts", getAllcmts);
//		else
//			model.addAttribute("getAllcmts", AllcmtsList);
//
//		Long counoqry = FPOrepost.getcounoqry(cinNo);
//		Long counoqryreply = FPOrepost.getcounoqryreply(cinNo);
//		model.addAttribute("qryraised", counoqry > 0 && counoqry == counoqryreply ? true : false);
//		if (counoqry > 0 && counoqry == counoqryreply) {
//			String qryType = FPOrepost.getQryType(cinNo);
//			boolean aafFirstQueryRaised = false;
//			if (qryType.equalsIgnoreCase("E")) {
//				model.addAttribute("eadFirstQueryRaised", true);
//			} else {
//				model.addAttribute("aafFirstQueryRaised", true);
//				String aaffirstfilename = "";
//				aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
//				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
//				aafFirstQueryRaised = true;
//			}
//			if (aafFirstQueryRaised) {
//				if (reportService.getAAFCountQuery(fpoMain.get(0).getITEM_ID(), "AAF")) {
//					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
//					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
//					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
//				}
//			} else {
//				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
//				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
//				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
//			}
//			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA");
//			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
//			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
//		}
//
//		Long examRaised = FPO_ORDERrepost.getOrderCinNo(cinNo);
//		model.addAttribute("examraised", examRaised > 0);
//		Long lastDetained = fpomvmntrepo.getDetainedCount(cinNo);
//		model.addAttribute("lastDetained", lastDetained > 0);
//		model.addAttribute("lastDetainedNo", fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.get(0).getITEM_ID()));
//		Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
//		model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
//
//		ModelAndView modelAndView = new ModelAndView("import/import_query");
//		return modelAndView;
//
//	}
	
// --------------------------------------------------------- ---------------------- -------------------------------------------- 
	
// --------------------------------------------------------- added BY VEEMAN on 22/04/2023 -------------------------------------------- 
	

	/*
	 * @RequestMapping("/import_query") public ModelAndView
	 * import_query(HttpServletRequest request, Model model, HttpSession session) {
	 * 
	 * String cinNo = request.getParameter("id"); List<FPO_MAIN> fpoMain =
	 * FPOrepost.getmain(cinNo);
	 * 
	 * List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo); List<FpoAddlQry>
	 * getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
	 * System.out.println(getAddlQry.isEmpty()); System.out.println("sdhsdhsdhbs "+
	 * getAddlQry); System.out.println("sdhsdhsdhbs "+getDefQry.get(0).getRply());
	 * Float maxaclassval = FPOrepost.getaclassval();
	 * 
	 * 
	 * if (null != getDefQry.get(0).getDEFUALT_QUERY()) { String getQuery =
	 * getDefQry.get(0).getDEFUALT_QUERY(); String[] array1 = getQuery.split(";");
	 * getDefQry.get(0).setDefqry1(array1[0]);
	 * getDefQry.get(0).setDefqry2(array1[1]);
	 * getDefQry.get(0).setDefqry3(array1[2]);
	 * getDefQry.get(0).setDefqry4(array1[3]); }
	 * 
	 * List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
	 * 
	 * List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>(); FpoQueryDin
	 * fpoQueryDin = new FpoQueryDin(); fpoQueryDin.setRemarks("");
	 * fpoQueryDin.setDepComments(""); AllcmtsList.add(fpoQueryDin);
	 * 
	 * 
	 * Long aafgeninfo = FPOrepost.getaaagenInaaf(cinNo);
	 * model.addAttribute("aafgeninfo", aafgeninfo);
	 * 
	 * fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "P2",
	 * session, request); fpoService.setFpoMainValues(fpoMain); fpoMain =
	 * fpoQueryService.getAllFpoMainData(fpoMain); List<FpoQuery> getAllFpoQuery =
	 * fpoQueryService.getAllFpoQuery(cinNo); model.addAttribute("head",
	 * fpoMain.get(0)); model.addAttribute("getAllFpoQuery", getAllFpoQuery);
	 * model.addAttribute("getDefQry", getDefQry); model.addAttribute("assvalacl",
	 * maxaclassval); model.addAttribute("emailid", getDefQry.get(0).getEmail_Id());
	 * model.addAttribute("phoneno", getDefQry.get(0).getMobile_No());
	 * 
	 * if (null != getAllcmts && !getAllcmts.isEmpty())
	 * model.addAttribute("getAllcmts", getAllcmts); else
	 * model.addAttribute("getAllcmts", AllcmtsList);
	 * 
	 * Long counoqry = FPOrepost.getcounoqry(cinNo); Long counoqryreply =
	 * FPOrepost.getcounoqryreply(cinNo); Long value =
	 * FPOrepost.getAddlQryRlyCount(cinNo); model.addAttribute("qryraised", counoqry
	 * > 0 && counoqry == counoqryreply ? true : false);
	 * model.addAttribute("notToShowForInAAFAddlRly" , false);
	 * model.addAttribute("AAFAdditionQueryFileExist", false); String qryType =
	 * FPOrepost.getQryType(cinNo); if (counoqry > 0 && counoqry == counoqryreply) {
	 * boolean aafFirstQueryRaised = false; if (qryType.equalsIgnoreCase("E")) {
	 * model.addAttribute("eadFirstQueryRaised", true);
	 * model.addAttribute("aafFirstQueryRaised", false);
	 * 
	 * model.addAttribute("notToShowForInAAF" , false); } else {
	 * model.addAttribute("aafFirstQueryRaised", true);
	 * model.addAttribute("eadFirstQueryRaised", false);
	 * model.addAttribute("notToShowForQuery" , 0);
	 * model.addAttribute("notToShowForInAAF", true); String aaffirstfilename = "";
	 * aaffirstfilename =
	 * reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
	 * aaffirstfilename =
	 * reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
	 * aafFirstQueryRaised = true; } if (aafFirstQueryRaised) { if
	 * (reportService.getAAFCountQuery(fpoMain.get(0).getITEM_ID(), "AAF")) { String
	 * aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(),
	 * "AAF"); model.addAttribute("notToShowForQuery" , 1);
	 * model.addAttribute("notToShowForInAAFAddlRly" , true );
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); }
	 * 
	 * else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) { String
	 * aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(),
	 * "AAF"); model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , true);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); } else if (value != 0 &&
	 * getAddlQry.get(0).getQRY_REPLY() == null ) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , false);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); }
	 * 
	 * else if (value == 0) {
	 * System.out.println("hi veemantha welcome to disney world");
	 * 
	 * } } String aaafilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA");
	 * model.addAttribute("AAAAdditionQueryFileName", aaafilename);
	 * model.addAttribute("AAAAdditionQueryFileExist",
	 * aaafilename.equalsIgnoreCase("") ? false : true); } else
	 * if(qryType.equalsIgnoreCase("U") && aafgeninfo == 0) {
	 * model.addAttribute("eadFirstQueryRaised", true);
	 * model.addAttribute("aafFirstQueryRaised", false);
	 * model.addAttribute("notToShowForQuery" , 0);
	 * model.addAttribute("notToShowForInAAF" , false);
	 * model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ?
	 * true : false); if(counoqry > 0 && counoqry != counoqryreply) { String
	 * aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(),
	 * "AAF"); model.addAttribute("AAFAdditionQueryFileName", aaffilename); } if
	 * (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) { String
	 * aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(),
	 * "AAF"); model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , true);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); } else if (value != 0 &&
	 * getAddlQry.get(0).getQRY_REPLY() == null ) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , false);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); }
	 * 
	 * else if (value == 0) {
	 * System.out.println("hi veemantha welcome to disney world"); } String
	 * aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(),
	 * "AAA"); model.addAttribute("AAAAdditionQueryFileName", aaafilename);
	 * model.addAttribute("AAAAdditionQueryFileExist",
	 * aaafilename.equalsIgnoreCase("") ? false : true); } else
	 * if(qryType.equalsIgnoreCase("U") && aafgeninfo != 0) {
	 * model.addAttribute("eadFirstQueryRaised", false);
	 * model.addAttribute("aafFirstQueryRaised", true);
	 * model.addAttribute("notToShowForQuery" , 0); if(qryType.equalsIgnoreCase("U")
	 * && aafgeninfo != 0 && getDefQry.get(0).getRply() != null ) {
	 * model.addAttribute("notToShowForInAAF" , true); } else {
	 * model.addAttribute("notToShowForInAAF" , false); }
	 * model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ?
	 * true : false); String aaffirstfilename = ""; aaffirstfilename =
	 * reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFFirstQueryFileName", aaffirstfilename); if(counoqry >
	 * 0 && counoqry != counoqryreply) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename); } if (value != 0
	 * && getAddlQry.get(0).getQRY_REPLY() != null ) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , true);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); } else if (value != 0 &&
	 * getAddlQry.get(0).getQRY_REPLY() == null ) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename);
	 * model.addAttribute("notToShowForInAAFAddlRly" , false);
	 * model.addAttribute("AAFAdditionQueryFileExist",
	 * aaffilename.equalsIgnoreCase("") ? false : true); } String aaafilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA");
	 * model.addAttribute("AAAAdditionQueryFileName", aaafilename);
	 * model.addAttribute("AAAAdditionQueryFileExist",
	 * aaafilename.equalsIgnoreCase("") ? false : true); } else
	 * if(qryType.equalsIgnoreCase("P") && aafgeninfo == 1) {
	 * model.addAttribute("eadFirstQueryRaised", false);
	 * model.addAttribute("aafFirstQueryRaised", true);
	 * model.addAttribute("notToShowForQuery" , 0);
	 * model.addAttribute("notToShowForInAAF" , false);
	 * model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ?
	 * true : false); String aaffirstfilename = ""; aaffirstfilename =
	 * reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFFirstQueryFileName", aaffirstfilename); if(counoqry >
	 * 0 && counoqry != counoqryreply) { String aaffilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF");
	 * model.addAttribute("AAFAdditionQueryFileName", aaffilename); } if
	 * (getDefQry.get(0).getRply() != null) { model.addAttribute("notToShowForInAAF"
	 * , true); } else if (getDefQry.get(0).getRply() == null) {
	 * model.addAttribute("notToShowForInAAF" , false); } String aaafilename =
	 * reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA");
	 * model.addAttribute("AAAAdditionQueryFileName", aaafilename);
	 * model.addAttribute("AAAAdditionQueryFileExist",
	 * aaafilename.equalsIgnoreCase("") ? false : true); }
	 * if(getDefQry.get(0).getRply() != null) { model.addAttribute("notToShowForAAF"
	 * , true); }else if (getDefQry.get(0).getRply() == null && aafgeninfo != 1 &&
	 * getAddlQry.isEmpty() == false) { model.addAttribute("notToShowForAAF" ,
	 * false); model.addAttribute("qryRlyNotDone" , false);
	 * model.addAttribute("firstQryRlyNotDone" , true); }else
	 * if(getDefQry.get(0).getRply() == null && aafgeninfo != 1 && value != 2) {
	 * model.addAttribute("notToShowForAAF" , false);
	 * model.addAttribute("qryRlyNotDone" , true); } else if
	 * (getDefQry.get(0).getRply() == null && aafgeninfo == 1) {
	 * model.addAttribute("notToShowForAAF" , false);
	 * model.addAttribute("firstQryRlyNotDone" , true); }
	 * 
	 * Long examRaised = FPO_ORDERrepost.getOrderCinNo(cinNo);
	 * model.addAttribute("examraised", examRaised > 0); Long lastDetained =
	 * fpomvmntrepo.getDetainedCount(cinNo); model.addAttribute("lastDetained",
	 * lastDetained > 0); model.addAttribute("lastDetainedNo",
	 * fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.get(0).getITEM_ID())); Long
	 * scanReportExist =
	 * fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
	 * model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
	 * 
	 * ModelAndView modelAndView = new ModelAndView("import/import_query"); return
	 * modelAndView;
	 * 
	 * }
	 */


// --------------------------------------------------------- ---------------------- -------------------------------------------- 
	// --------------------------------------------------------- added BY VEEMAN on 29/04/2023 -------------------------------------------- 
	@RequestMapping("/import_query")
	public ModelAndView import_query(HttpServletRequest request, Model model, HttpSession session) {

		String cinNo = request.getParameter("id");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);

		int count=fpoDcallQryRepo.countdcallletter(cinNo);
		model.addAttribute("DcallLetterCount", count);
		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo);
		List<FpoAddlQry> getAddlQry = fpoAddlQryRepo.getAddQry(cinNo);
		System.out.println(getAddlQry.isEmpty());
		//System.out.println("sdhsdhsdhbs "+ getAddlQry);
		//System.out.println("sdhsdhsdhbs "+getDefQry.get(0).getRply());
		Float maxaclassval = FPOrepost.getaclassval();
		
		
		
		
		List<FpoAddlQry> fpoAddlQry = fpoAddlQryRepo.getAddQryDetails(cinNo);
		model.addAttribute("cancelquery", false);
		
			if(!fpoAddlQry.isEmpty())
			if(!fpoAddlQry.get(0).getQRY_TYPE().equals("D"))
			model.addAttribute("cancelquery", true);
			
		
		
		
		
		List<FpoSecondDefaultQuery> getScndDefQry = fpoService.getSecondDefualtQuery();
		model.addAttribute("getScndDefQry", getScndDefQry);

		if (null != getDefQry.get(0).getDEFUALT_QUERY()) {
			String getQuery = getDefQry.get(0).getDEFUALT_QUERY();
			String[] array1 = getQuery.split(";");
			getDefQry.get(0).setDefqry1(array1[0]);
			getDefQry.get(0).setDefqry2(array1[1]);
			getDefQry.get(0).setDefqry3(array1[2]);
			getDefQry.get(0).setDefqry4(array1[3]);
		}

		List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
		
		List<FpoQueryDin> AllcmtsList = new ArrayList<FpoQueryDin>();
		FpoQueryDin fpoQueryDin = new FpoQueryDin();
		fpoQueryDin.setRemarks("");
		fpoQueryDin.setDepComments("");
		AllcmtsList.add(fpoQueryDin);

		
		Long aafgeninfo =  FPOrepost.getaaagenInaaf(cinNo);
		model.addAttribute("aafgeninfo", aafgeninfo);
		
		fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "P2", session, request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("getAllFpoQuery", getAllFpoQuery);
		model.addAttribute("getDefQry", getDefQry);
		model.addAttribute("assvalacl", maxaclassval);
		model.addAttribute("emailid", getDefQry.get(0).getEmail_Id());
		model.addAttribute("phoneno", getDefQry.get(0).getMobile_No());

		if (null != getAllcmts && !getAllcmts.isEmpty())
			model.addAttribute("getAllcmts", getAllcmts);
		else
			model.addAttribute("getAllcmts", AllcmtsList);

		Long counoqry = FPOrepost.getcounoqry(cinNo);
		Long counoqryreply = FPOrepost.getcounoqryreply(cinNo);
		Long value = FPOrepost.getAddlQryRlyCount(cinNo);
		Long valuepac = FPOrepost.getAddlQryRlyCountforpac(cinNo);
		if (valuepac > 0) 
			 model.addAttribute("Additionalqrynotraisedpac", true);
		else
			 model.addAttribute("Additionalqrynotraisedpac", false);
		
		model.addAttribute("qryraised", counoqry > 0 && counoqry == counoqryreply ? true : false);
		model.addAttribute("notToShowForInAAFAddlRly" , false);
		model.addAttribute("AAFAdditionQueryFileExist", false);
		model.addAttribute("AAFAddlQryRlyNotDone", false);
		String qryType = FPOrepost.getQryType(cinNo);
		boolean aafFirstQueryRaised, eadFirstQueryRaised;
		if (counoqry > 0 && counoqry == counoqryreply) {
			 aafFirstQueryRaised = false;
			if (qryType.equalsIgnoreCase("E")) {
				model.addAttribute("eadFirstQueryRaised", true);
				model.addAttribute("aafFirstQueryRaised", false);
				
				model.addAttribute("notToShowForInAAF" , false);
				aafFirstQueryRaised = false;
				eadFirstQueryRaised = true;
			} else {
				aafFirstQueryRaised = true;
				eadFirstQueryRaised = false;
				model.addAttribute("aafFirstQueryRaised", true);
				model.addAttribute("eadFirstQueryRaised", false);
				model.addAttribute("notToShowForQuery" , 0);
				model.addAttribute("notToShowForInAAF", true);
				String aaffirstfilename = "";
				aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
				aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
				//if(!qryType.equals("P"))
				//aafFirstQueryRaised = true;
			}
			if (aafFirstQueryRaised==true) {
				if (reportService.getAAFCountQuery(fpoMain.get(0).getITEM_ID(), "AAF")) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("notToShowForQuery" , 1);
					model.addAttribute("notToShowForInAAFAddlRly" , true );
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				}
				
			 else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("notToShowForInAAFAddlRly" , true);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			 }  else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() == null ) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("notToShowForInAAFAddlRly" , false);
					model.addAttribute("AAFAddlQryRlyNotDone", true);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				 } 
			 
			 else if (value == 0) {
				 System.out.println("hi veemantha welcome to disney world");
				
				
			 }
			}
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
		} else if(qryType.equalsIgnoreCase("U") && aafgeninfo == 0) {
			model.addAttribute("eadFirstQueryRaised", true);
			model.addAttribute("aafFirstQueryRaised", false);
			model.addAttribute("notToShowForQuery" , 0);
			model.addAttribute("notToShowForInAAF" , false);
			model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ? true : false);
			if(counoqry > 0 && counoqry != counoqryreply) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
			}  if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("notToShowForInAAFAddlRly" , true);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			 }  else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() == null ) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("notToShowForInAAFAddlRly" , false);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				 } 
			 
			 else if (value == 0) {
				 System.out.println("hi veemantha welcome to disney world");
			 }
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
		} 
		else if(counoqry == 1 && counoqryreply == 0 && qryType.equalsIgnoreCase("U") && aafgeninfo == 1) {
		 if(qryType.equalsIgnoreCase("U") && aafgeninfo == 1) {
			model.addAttribute("eadFirstQueryRaised", true);
			model.addAttribute("aafFirstQueryRaised", false);
			model.addAttribute("notToShowForQuery" , 0);
			model.addAttribute("notToShowForInAAF" , false);
			model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ? true : false);
			if(counoqry > 0 && counoqry != counoqryreply) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
			}  if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("notToShowForInAAFAddlRly" , true);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			 }  else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() == null ) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("notToShowForInAAFAddlRly" , false);
					model.addAttribute("AAFAddlQryRlyNotDone", true);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				 } 
			 
			 else if (value == 0) {
				 System.out.println("hi veemantha welcome to disney world");
			 }
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
			}
		}
		
		
		else if(qryType.equalsIgnoreCase("U") && aafgeninfo != 0) {
			model.addAttribute("eadFirstQueryRaised", false);
			model.addAttribute("aafFirstQueryRaised", true);
			model.addAttribute("notToShowForQuery" , 0);
			if(qryType.equalsIgnoreCase("U") && aafgeninfo != 0 && getDefQry.get(0).getRply() != null ) {
			model.addAttribute("notToShowForInAAF" , true);
			} else {
				model.addAttribute("notToShowForInAAF" , false);
			}
			model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ? true : false);
			String aaffirstfilename = "";
			aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
			model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
			if(counoqry > 0 && counoqry != counoqryreply) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
			}  if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("notToShowForInAAFAddlRly" , true);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			 }  else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() == null ) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("notToShowForInAAFAddlRly" , false);
					model.addAttribute("AAFAddlQryRlyNotDone", true);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				 } 
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
		} 
		else if(qryType.equalsIgnoreCase("P") && aafgeninfo == 1) {
			model.addAttribute("eadFirstQueryRaised", false);
			model.addAttribute("aafFirstQueryRaised", true);
			model.addAttribute("notToShowForQuery" , 0);
			model.addAttribute("notToShowForInAAF" , false);
			model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ? true : false);
			String aaffirstfilename = "";
			aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
			model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
			if(counoqry > 0 && counoqry != counoqryreply) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
			} if (getDefQry.get(0).getRply() != null) {
				model.addAttribute("notToShowForInAAF" , true);
			} else if (getDefQry.get(0).getRply() == null) {
				model.addAttribute("notToShowForInAAF" , false);
			}
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
		} 
		else if(qryType.equalsIgnoreCase("P") && aafgeninfo == 2) {
			if(counoqry == 1 && counoqryreply == 0) {
				
			model.addAttribute("eadFirstQueryRaised", false);
			model.addAttribute("aafFirstQueryRaised", true);
			model.addAttribute("notToShowForQuery" , 0);
			model.addAttribute("notToShowForInAAF" , false);
			model.addAttribute("qryraised", counoqry > 0 || counoqry == counoqryreply ? true : false);
			String aaffirstfilename = "";
			aaffirstfilename = reportService.getFileNameForAAFRly(fpoMain.get(0).getITEM_ID(), "AAF");
			model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
			if(counoqry > 0 && counoqry != counoqryreply) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
			} if (getDefQry.get(0).getRply() != null) {
				model.addAttribute("notToShowForInAAF" , true);
			} else if (getDefQry.get(0).getRply() == null) {
				model.addAttribute("notToShowForInAAF" , false);
			}
			String aaafilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAA",session,request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
			}
				if (reportService.getAAFCountQuery(fpoMain.get(0).getITEM_ID(), "AAF")) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
					model.addAttribute("notToShowForQuery" , 1);
					model.addAttribute("notToShowForInAAFAddlRly" , true );
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				}
				
			  if (value != 0 && getAddlQry.get(0).getQRY_REPLY() != null ) {
				String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("notToShowForInAAFAddlRly" , true);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			 }  else if (value != 0 && getAddlQry.get(0).getQRY_REPLY() == null ) {
					String aaffilename = reportService.getFileNameForRly(fpoMain.get(0).getITEM_ID(), "AAF",session,request	);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("notToShowForInAAFAddlRly" , false);
					model.addAttribute("AAFAddlQryRlyNotDone", true);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				 } 
			 
			 else if (value == 0) {
				 System.out.println("hi veemantha welcome to disney world");
			 }
			}
		if(getDefQry.get(0).getRply() != null) {
			model.addAttribute("notToShowForAAF" , true);
		}else if (getDefQry.get(0).getRply() == null && aafgeninfo != 1 && getAddlQry.isEmpty() == false)  {
			model.addAttribute("notToShowForAAF" , false);
			model.addAttribute("qryRlyNotDone" , false);
			model.addAttribute("firstQryRlyNotDone" , true);
		}else if(getDefQry.get(0).getRply() == null && aafgeninfo != 1 && value != 2) {
				model.addAttribute("notToShowForAAF" , false);
				model.addAttribute("qryRlyNotDone" , true);
		} else if (getDefQry.get(0).getRply() == null && aafgeninfo == 1 && value != 2) {
			model.addAttribute("notToShowForAAF" , false);
			model.addAttribute("firstQryRlyNotDone" , true);
		}else if (getDefQry.get(0).getRply() == null && aafgeninfo == 1 && getAddlQry.isEmpty() == false && value == 2)  {
			model.addAttribute("notToShowForAAF" , false);
			model.addAttribute("qryRlyNotDone" , true);
		}
		
		Long examRaised = FPO_ORDERrepost.getOrderCinNo(cinNo);
		model.addAttribute("examraised", examRaised > 0);
		Long lastDetained = fpomvmntrepo.getDetainedCount(cinNo);
		model.addAttribute("lastDetained", lastDetained > 0);
		model.addAttribute("lastDetainedNo", fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.get(0).getITEM_ID()));
		Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
		model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);

		ModelAndView modelAndView = new ModelAndView("import/import_query");
		return modelAndView;

	}
	// --------------------------------------------------------- ---------------------- -------------------------------------------- 

	@RequestMapping("/import_query_search")
	public ModelAndView import_query_search(Model model, HttpSession session, HttpServletRequest request) {
		String cuSite=request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		String role=request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		List<Collection> s = FPOrepost.getQueryView(cuSite,offId,role);
		// Long couqry = FPOrepost.getcouqryView(
		// request.getSession().getAttribute("cuSite") == null ? null :
		// request.getSession().getAttribute("cuSite").toString(),
		// request.getSession().getAttribute("offId") == null ? null :
		// request.getSession().getAttribute("offId").toString());
		String allotmailcat="";
		String dispmailcat="";
		allotmailcat = FPOrepost.getallotmailcat(cuSite, role, offId);
		Long fou,allotltr,allotltrapr,allotems,allotemsapr,allotpar,allotparapr,allotemp,allotempapr;
		fou=0l;
		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO")) {
			if (allotmailcat.indexOf("U") >= 0) {
				dispmailcat = " U - Letters ";
				fou = 1l;
				// coucurapr = coucurapr + coultr;
				// allotltr = coultr;
				// allotltrapr = coultrapr;
			} else {
				allotltr = 0l;
				allotltrapr = 0l;
			}
			if (allotmailcat.indexOf("E") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " E - EMS ";
				// coucurapr = coucurapr + couems;
				fou = 1l;
				// allotemsapr = couemsapr;
				// allotems = couems;
			} else {
				allotemsapr = 0l;
				allotems = 0l;
			}
			if (allotmailcat.indexOf("C") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " C - Parcels ";
				// coucurapr = coucurapr + coupar;
				fou = 1l;
				// allotparapr = couparapr;
				// allotpar = coupar;
			} else {
				allotparapr = 0l;
				allotpar = 0l;
			}
			if (allotmailcat.indexOf("T") >= 0) {
				if (fou == 1)
					dispmailcat = dispmailcat + ",";
				dispmailcat = dispmailcat + " T - Emp.Recep.,";
				fou = 1l;
				// coucurapr = coucurapr + couemp;
				// allotemp = couemp;
				// allotempapr = couempapr;
			} else {
				allotempapr = 0l;
				allotemp = 0l;
			}

		}
		ModelAndView modelAndView = new ModelAndView("import/import_query_search");
		model.addAttribute("check", s);
		model.addAttribute("couqry", s.size());
		model.addAttribute("allotmailcat", allotmailcat);
		model.addAttribute("dispmailcat", dispmailcat);
		return modelAndView;
	}

	@RequestMapping("/import_ooc_search")
	public ModelAndView import_ooc_search(Model model, HttpSession session, HttpServletRequest request) {
		Long dupcou = FPOrepost.getdupcousup(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println("count is" + dupcou);
		if (dupcou > 1)
			fpomvmntrepo.deldupsup(
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString());
		List<Collection> s = FPOrepost.getPoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couooc = FPOrepost.getcouoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println(s);
		ModelAndView modelAndView = new ModelAndView("import/import_ooc_search");
		model.addAttribute("check", s);
		model.addAttribute("couooc", couooc);
		return modelAndView;
	}

	@RequestMapping("/import_quick_ooc")
	public ModelAndView import_quick_ooc(Model model, HttpSession session, HttpServletRequest request) {
		Long dupcou = FPOrepost.getdupcousup(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println("count is" + dupcou);
		String dispmailcat = "";
//		if ( dupcou > 1)
		fpomvmntrepo.deldupsup(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		List<Collection> s = FPOrepost.getQoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		/*Long couooc = FPOrepost.getcouoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());*/
		int couooc = s.size();
		String allotmailcat = FPOrepost.getallotmailcat(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println(s);
		dispmailcat = quick_det_common_ooc(allotmailcat);
		ModelAndView modelAndView = new ModelAndView("import/import_quick_ooc");
		model.addAttribute("check", s);
		model.addAttribute("couooc", couooc);
		model.addAttribute("dispmailcat", dispmailcat);
		return modelAndView;
	}

	@RequestMapping("/import_det_ooc")
	public ModelAndView import_det_ooc(Model model, HttpSession session, HttpServletRequest request) {
		Long dupcou = FPOrepost.getdupcousup(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println("count is" + dupcou);
		String dispmailcat = "";
//		if ( dupcou > 1)
		fpomvmntrepo.deldupsup(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		List<Collection> s = FPOrepost.getQoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couooc = FPOrepost.getcouoocView(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		String allotmailcat = FPOrepost.getallotmailcat(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		System.out.println(s);
		dispmailcat = quick_det_common_ooc(allotmailcat);
		ModelAndView modelAndView = new ModelAndView("import/import_det_ooc");
		model.addAttribute("check", s);
		model.addAttribute("couooc", couooc);
		model.addAttribute("dispmailcat", dispmailcat);
		return modelAndView;
	}

	public String quick_det_common_ooc(String allotmailcat) {
		String dispmailcat = "";
		long fou = 0l;
		if (allotmailcat.indexOf("U") >= 0) {
			dispmailcat = " U - Letters ";
			fou = 1l;
		}
		if (allotmailcat.indexOf("E") >= 0) {
			if (fou == 1)
				dispmailcat = dispmailcat + ",";
			dispmailcat = dispmailcat + " E - EMS ";
			fou = 1l;
		}
		if (allotmailcat.indexOf("C") >= 0) {
			if (fou == 1)
				dispmailcat = dispmailcat + ",";
			dispmailcat = dispmailcat + " C - Parcels ";
			fou = 1l;
		}
		if (allotmailcat.indexOf("T") >= 0) {
			if (fou == 1)
				dispmailcat = dispmailcat + ",";
			dispmailcat = dispmailcat + " T - Emp. Receptacles ";
			fou = 1l;
		}
		return dispmailcat;
	}

	@RequestMapping("/import_order")
	public ModelAndView import_order(HttpServletRequest request, Model model) {
		String cinNo = request.getParameter("id");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		Float maxaclassval = FPOrepost.getaclassval();
		model.addAttribute("assvalacl", maxaclassval);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		ModelAndView modelAndView = new ModelAndView("import/import_order");
		return modelAndView;
	}

	@RequestMapping("/import_ooc_order")
	public ModelAndView import_ooc_order(HttpServletRequest request, Model model) {
		String cinNo = request.getParameter("id");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		ModelAndView modelAndView = new ModelAndView("import/import_ooc_order");
		return modelAndView;
	}

	@RequestMapping("/import_exam_order")
	public ModelAndView import_exam_order(HttpServletRequest request, Model model, HttpSession session) {
		String cinNo = request.getParameter("id");
		System.out.println("in import exam order");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		List<Collection> fpoitmdet = FPOrepost.getitminfo(cinNo);
		List<FPO_ORDER> fpoorder = FPO_ORDERrepost.examOrder(cinNo);
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpomvmntrepo.deldupexm(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long slno = fpomvmntrepo.getMaxSlOnCin(cinNo);
		if (null == slno)
			slno = Long.valueOf(0);
		// System.out.println("Exam order is " + fpoorder.get(0).getEXAM_ORDER());
		// System.out.println("order remark is " + fpoorder.get(0).getORDER_REMARK());
		fpoService.insertIntofpoMvmntDb(fpomvmnt, cinNo, fpomvmntrepo.getcindtmvmnt(cinNo), curdt, slno, "PIN", "P4",
				session, request);
		fpoService.setFpoMainValues(fpoMain);
		int choice = fpoOrderService.getchoice(fpoorder.get(0).getEXAM_ORDER());
		System.out.println("choice is" + choice);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", fpoMain.get(0));
		model.addAttribute("examorder", fpoorder.get(0).getORDER_REMARK());
		model.addAttribute("fpoitmdet", fpoitmdet);
		model.addAttribute("choice", choice);
		Long scanReportExist = fpoScanInfoRepo.getCountOfScanReportById(fpoMain.get(0).getITEM_ID());
		model.addAttribute("scanReportExist", scanReportExist > 0 ? true : false);
		ModelAndView modelAndView = new ModelAndView("import/import_exam_order");
		return modelAndView;
	}

	@RequestMapping("/decisionSubmit")
	public ModelAndView decisionSubmit(@ModelAttribute("fpoQueryDecision") FpoQueryDecision fpoQueryDecision,
			HttpServletRequest request, Model model, HttpSession session) {
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoQueryDecision.getCIN_NO());
		System.out.println(
				"matching is " + fpoMain.get(0).getITEM_ID().equals(request.getSession().getAttribute("itemid")));
		if (fpoMain.get(0).getITEM_ID().equals(request.getSession().getAttribute("itemid")))
			fpoService.sendmail(
					request.getSession().getAttribute("itemid") == null ? null
							: request.getSession().getAttribute("itemid").toString(),
					fpoQueryDinRepo.getdcallno(request.getSession().getAttribute("itemid") == null ? null
							: request.getSession().getAttribute("itemid").toString()),
					session, fpoQueryDecision.getCIN_NO(), request);
		model.addAttribute("check", fpoMain);
		ModelAndView modelAndView = new ModelAndView(
				fpoService.decisionSubmitService(fpoQueryDecision, session, request, null));
		fpoCurQueService.updateStatus(fpoMain.get(0), session, request);
		return modelAndView;
	}

	@RequestMapping("/decisionSubmitResponse")
	public @ResponseBody void decisionSubmitResponse(
			@ModelAttribute("fpoQueryDecision") FpoQueryDecision fpoQueryDecision, HttpServletRequest request,
			Model model, HttpServletResponse response, HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String assstop = request.getParameter("cat");
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoQueryDecision.getCIN_NO());
		System.out.println(
				"matching is " + fpoMain.get(0).getITEM_ID().equals(request.getSession().getAttribute("itemid")));
		int send = 0;
		if (assstop.equals("ASSSTOP")) {
			fpoQueryDecision.setQRY_TYPE("P4");
		} else {
			if (fpoMain.get(0).getITEM_ID().equals(request.getSession().getAttribute("itemid") == null ? null
					: request.getSession().getAttribute("itemid").toString())) {
				String toMobileNumber = request.getSession().getAttribute("toMobileNumber") == null ? null
						: request.getSession().getAttribute("toMobileNumber").toString();
				String toEnteredMobileNumber = request.getSession().getAttribute("toEnteredMobileNumber") == null ? null
						: request.getSession().getAttribute("toEnteredMobileNumber").toString();
				String smsText = "INDIAN CUSTOMS - raised query - Ref: Postal Art.ID - " + fpoMain.get(0).getITEM_ID()
						+ " / DCALL- " + request.getSession().getAttribute("dcallno")
						+ " Reply via Post/Visit the URL: " + request.getSession().getAttribute("smsurl") + " -CBIC";
				log.info("smstext=" + smsText);
				send = fpoService.sendmail(
						request.getSession().getAttribute("itemid") == null ? null
								: request.getSession().getAttribute("itemid").toString(),
						request.getSession().getAttribute("dcallno") == null ? null
								: request.getSession().getAttribute("dcallno").toString(),
						session, fpoQueryDecision.getCIN_NO(), request);
				if (toMobileNumber != null && !toMobileNumber.equalsIgnoreCase("") && toMobileNumber.length() >= 10) {
					if (toMobileNumber.length() > 10) {
						toMobileNumber = toMobileNumber.substring(toMobileNumber.length() - 10);
					}
					if (toMobileNumber != null) {
						// fpoService.sendSms("9740388057", smsText, "1107163548035935781",
						// request.getSession().getAttribute("dcallno") == null ? null :
						// request.getSession().getAttribute("dcallno").toString(),
						// fpoQueryDecision.getCIN_NO(), session,request);
						fpoService.sendSms("9740388057", smsText, "1107167637041176783",
								request.getSession().getAttribute("dcallno") == null ? null
										: request.getSession().getAttribute("dcallno").toString(),
								fpoQueryDecision.getCIN_NO(), session, request);
					}
				}
				if (toEnteredMobileNumber != null && !toEnteredMobileNumber.equalsIgnoreCase("")
						&& (toEnteredMobileNumber.length() >= 10)) {
					if (toEnteredMobileNumber.length() > 10) {
						toEnteredMobileNumber = toEnteredMobileNumber.substring(toEnteredMobileNumber.length() - 10);
					}
					if (toEnteredMobileNumber != null) {
						// fpoService.sendSms(toEnteredMobileNumber, smsText, "1107163548035935781",
						// request.getSession().getAttribute("dcallno") == null ? null :
						// request.getSession().getAttribute("dcallno").toString(),
						// fpoQueryDecision.getCIN_NO(), session,request);
						fpoService.sendSms(toEnteredMobileNumber, smsText, "1107167637041176783",
								request.getSession().getAttribute("dcallno") == null ? null
										: request.getSession().getAttribute("dcallno").toString(),
								fpoQueryDecision.getCIN_NO(), session, request);
					}
				}
				// LoginController.toMobileNumber = null;
				// LoginController.toEnteredMobileNumber = null;
				request.getSession().setAttribute("toEnteredMobileNumber", null);
				request.getSession().setAttribute("toMobileNumber", null);
			}
		}
		model.addAttribute("check", fpoMain);
		String page = fpoService.decisionSubmitService(fpoQueryDecision, session, request, assstop);
		fpoCurQueService.updateStatus(fpoMain.get(0), session, request);
		if (request.getSession().getAttribute("role").equals("PAC"))
		{FPOrepost.updateRoleApr(fpoQueryDecision.getCIN_NO());
		  FPOrepost.updateRoleAprqrydeci(fpoQueryDecision.getCIN_NO(),fpoMain.get(0).getOFF_ID());}
		jsonObj.put("mailSent", send == 1 ? true : false);
		jsonObj.put("page", page);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@RequestMapping("/decisionSubmitStatus")
	public @ResponseBody String[] decisionSubmitStatus(HttpServletRequest request) {
		String cinNo = request.getParameter("cinNo");
		String[] decisionArray = new String[6];
		decisionArray[0] = fpoOrderService.getOrderCinNo(cinNo);
		decisionArray[1] = fpoQueryService.getFpoQuery(cinNo);
		decisionArray[2] = fpoOrderService.getExamOrder(cinNo);
		decisionArray[3] = fpoService.getAssCinNo(cinNo);
		decisionArray[4] = fpoOrderService.getfirstOrder(cinNo);
		decisionArray[5] = fpoService.gettotassval(cinNo);
		return decisionArray;
	}

	@RequestMapping("/getPageStatus")
	public @ResponseBody String getPageStatus(HttpServletRequest request) {
		return fpoService.getStatusOfAllPages(request.getParameter("cinNo"));
	}

	@RequestMapping("/getTotalNoItemsPagination")
	public @ResponseBody List<Object> getTotalNoItemsPagination(HttpServletRequest request) {
		return fpoService.getTotalNoItemsPagination(request.getParameter("cinNo"));
	}

	@RequestMapping("/getItemAllCth")
	public @ResponseBody List<String> getItemAllCth(HttpServletRequest request) {
		return fpoService.getItemAllCth(request.getParameter("cinNo"));
	}

	@RequestMapping("/getCustomDropDown")
	public @ResponseBody List<Object> getCustomDropDown(HttpServletRequest request) {
		return FPO_ITEMrepost.getDUTY();
	}

	@RequestMapping(value = "/getLatestAmendOnCinAndItemNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET getLatestAmendOnCinAndItemNo(@RequestBody FPO_ITEM_DET fPO_ITEM_DET,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoAmendService.getLatestAmendOnCinAndItemNo(fPO_ITEM_DET);
	}

	@RequestMapping(value = "/getOrderCinNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody Long getOrderCinNo(@RequestBody FPO_ORDER fpoOrder, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return FPO_ORDERrepost.getOrderCinNo(fpoOrder.getCIN_NO());
	}

	@RequestMapping(value = "/nextPageRedirect", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET nextPageRedirect(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.getAssessementSatus(fpoItemDet);
	}

	@RequestMapping(value = "/getOtherFourDigi", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getOtherFourDigi(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.getOtherFourDigi(fpoItemDet.getCTH());
	}

	@RequestMapping(value = "/getOtherEightDigi", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getOtherEightDigi(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getOtherEightDigi(fpoItemDet.getCTH());
	}

	@RequestMapping(value = "/getCountryName", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<String> getCountryName(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.getCountryName(fpoItemDet.getCURRCD());
	}

	@RequestMapping(value = "/updateExchangeRate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET updateExchangeRate(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		return fpoDeclaredService.updateExchangeRate(fpoItemDet, session, request);
	}

	@RequestMapping(value = "/getDutyOthersDet", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET getDutyOthersDet(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.getDutyOthersDet(fpoItemDet);
	}

	@RequestMapping(value = "/getDutyOthersSlNo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET getDutyOthersSlNo(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getDutyOthersSlNo(fpoItemDet);
	}

	@RequestMapping(value = "/getFinePenal", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoFine> getFinePenal(@RequestBody List<FpoFine> fpoFine, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoFineService.saveFineData(fpoFine);
	}

	@RequestMapping(value = "/getAmendFinePenal", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoFine> getAmendFinePenal(@RequestBody List<FpoFine> fpoFine, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoFineService.saveAmendFineData(fpoFine);
	}

	@RequestMapping(value = "/getEachRate", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET getEachRate(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.getEachRate(fpoItemDet);
	}

	@RequestMapping(value = "/getFpoItemOthersList", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN getFpoItemOthersList(@RequestBody List<FpoItemDetOthDuty> fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		FPO_MAIN status = new FPO_MAIN();
		status.setStringval(fpoDeclaredService.getFpoItemOthersList(fpoItemDetOthDuty, session, request));
		return status;
	}

	@RequestMapping(value = "/saveOthersAmendList", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN saveOthersAmendList(@RequestBody List<FpoItemDetOthDuty> fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		FPO_MAIN status = new FPO_MAIN();
		status.setStringval(fpoDeclaredService.saveOthersAmendList(fpoItemDetOthDuty, session, request));
		return status;
	}

	@RequestMapping(value = "/saveFpoItemNinEgtOthrList", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoItemDetOthDuty> saveFpoItemNinEgtOthrList(
			@RequestBody List<FpoItemDetOthDuty> fpoItemDetOthDuty, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoDeclaredService.saveFpoItemNinEgtOthrList(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getOthThanNintyEight", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoItemDetOthDuty> getOthThanNintyEight(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getOthThanNintyEight(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getOthrOnCinNor", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoItemDetOthDuty> getOthrOnCinNor(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getOthrOnCinNor(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getOthrOnCinNorItem", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoItemDetOthDuty> getOthrOnCinNorItem(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getOthrOnCinNorItem(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getAllOthrOnCinNor", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoItemDetOthDuty> getAllOthrOnCinNor(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getAllOthrOnCinNor(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getDUTYOnCd", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoItemDetOthDuty getDUTYOnCd(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getDUTYOnCd(fpoItemDetOthDuty);
	}

	@RequestMapping(value = "/getDUTYOnCdoth", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Object> getDUTYOnCdoth(@RequestBody FpoItemDetOthDuty fpoItemDetOthDuty,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String dutycd = request.getParameter("dutycd");
		Long cd = Long.parseLong(dutycd);
		List<Object> result = fpoItemDetRepo.getDUTYOnCdoth(cd);
		return result;
	}

	@RequestMapping(value = "/getDeclaredStatus", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET getDeclaredStatus(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.getDeclaredStatus(fpoItemDet);
	}

	@RequestMapping(value = "/updateUSerExitStatus", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt updateUSerExitStatus(@RequestBody FpoMvmnt fpoMvmnt, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		fpoCurQueService.updateUSerExitStatus(fpoMvmnt.getCinNo(), fpoMvmnt.getRole());
		return fpoMvmnt;
	}

	@RequestMapping(value = "/pnextPageRedirect", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET pnextPageRedirect(@RequestBody FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		return fpoDeclaredService.pgetAssessementSatus(fpoItemDet);
	}

	@RequestMapping(value = "/updateUSerEnterStatus", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt updateUSerEnterStatus(@RequestBody FpoMvmnt fpoMvmnt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		String flag = fpoMvmnt.getFlag();
		request.getSession().setAttribute("flag", flag);
		fpoCurQueService.updateUSerEnterStatus(fpoMvmnt.getCinNo(), fpoMvmnt.getRole(), session, "E1", request);
		return fpoMvmnt;
	}

	@RequestMapping(value = "/qrytoassdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt queryToAssess(@RequestBody FpoMvmnt fpoMvmnt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpomvmntrepo.delduppao(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		fpomvmntrepo.updextdtstr(fpoMvmnt.getCinNo(), utilDate);
		fpoCurQueService.updateUSerEnterStatus(fpoMvmnt.getCinNo(), fpoMvmnt.getRole(), session, "P1", request);
		return fpoMvmnt;
	}

	@RequestMapping(value = "/revokesetaside", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN updateUSerEnterStatus(@RequestBody FPO_MAIN fpomain, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		System.out.println("cinno is " + fpomain.getId());
		session.removeAttribute("flag");
		fpoService.revokesetaside(fpomain.getId(), request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		return fpomain;
	}

	/*
	 * @RequestMapping(value = "/loginUser", produces = {
	 * MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	 * public @ResponseBody List<Collection> loginUser(@RequestBody D_EMP_FPO
	 * empName, HttpServletRequest request, HttpServletResponse response, Model
	 * model, HttpSession session) { List<D_EMP_FPO> getrole =
	 * DempFpo.getrole(empName.getId(), empName.getPwd()); List<D_EMP_ROLES>
	 * getcusSite = Demprole.getcusSite(empName.getId(), empName.getPwd());
	 * 
	 * if (!getcusSite.isEmpty()) { String value1 = getrole.get(0).getId(); String
	 * value2 = getrole.get(0).getEmpName(); String value3 =
	 * getcusSite.get(0).getRoleName(); String value4 =
	 * getcusSite.get(0).getCusSite();
	 * 
	 * 
	 * String NsmSiteNme = demproleNSM.getNsmsiteNme(value1); String Nsmsitestate =
	 * NsmSiteNme.split(",")[1]; String Nsmsitecde = NsmSiteNme.split(",")[0];
	 * 
	 * String lsmsite = fpositemgmtrepo.getLsmSitecde(value1); String Lsmsitename =
	 * ""; if (lsmsite != null) { Lsmsitename = lsmsite.split(",")[1]; }
	 * request.getSession().setAttribute("Lsmsitenme", Lsmsitename);
	 * request.getSession().setAttribute("LSMcuSite",
	 * demproleNSM.LsmSiteCde(value1));
	 * 
	 * // offId = value1; request.getSession().setAttribute("offId", value1); //
	 * offName = value2; request.getSession().setAttribute("offName", value2); //
	 * role = value3; request.getSession().setAttribute("role", value3); // cuSite =
	 * value4; request.getSession().setAttribute("cuSite", value4);
	 * 
	 * request.getSession().setAttribute("data",
	 * request.getSession().getAttribute("offId") == null ? null :
	 * request.getSession().getAttribute("offId").toString());
	 * request.getSession().setAttribute("data1",
	 * request.getSession().getAttribute("role") == null ? null :
	 * request.getSession().getAttribute("role").toString());
	 * request.getSession().setAttribute("data2",
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString());
	 * request.getSession().setAttribute("data3",
	 * request.getSession().getAttribute("offName") == null ? null :
	 * request.getSession().getAttribute("offName").toString());
	 * 
	 * 
	 * request.getSession().setAttribute("Nsmsitenme", Nsmsitestate);
	 * request.getSession().setAttribute("Nsmsitecde", Nsmsitecde); }
	 * List<Collection> obj = fpoService.getloginName(empName);
	 * fpoService.addLogintrackdetails(session, "login"); return obj; }
	 */

	@RequestMapping(value = "/selrolenxt", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody String selrolenxt(@RequestBody D_EMP_ROLES emproles, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		// role=emproles.getRoleName();
		request.getSession().setAttribute("data1", emproles.getRoleName());
		return "success";
	}

	@RequestMapping("/selrole")
	public ModelAndView selrole(HttpServletRequest request, Model model, HttpSession session) {
		// request.getHeader("OAM_REMOTE_USER");
		// log.info("entered in selrole");
		// log.info("offid is " +
		// request.getSession().getAttribute("offId").toString());
		// model.addAttribute("roledata",
		// fpoService.getmulrole(request.getSession().getAttribute("offId") == null ?
		// null : request.getSession().getAttribute("offId").toString()));

		// request.getHeader("OAM_REMOTE_USER");
		log.info("entered in selrole");
		log.info("offid is " + request.getSession().getAttribute("offId").toString());
		model.addAttribute("roledata", fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString()));
		List<String> getmulrole2 = fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		String string = "";
		if (getmulrole2.size() > 0)
			string = getmulrole2.get(0);
		System.out.println(string + "hello");
		model.addAttribute("roledata1", string);

//		 List<String> getmulrole = fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
//		 String string = getmulrole.toString();
//		 string.
////		 System.out.println(getmulrole+"this is")

		model.addAttribute("final_count",
				fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString()).size());

//		String contfina = fpoService.getmulrole1(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
//		System.out.println(contfina+"this is answer");
//		model.addAttribute("roledata",contfina);

		int sts = fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString()).size();
		System.out.println(sts + "sdsdsd");
		if (sts <= 1)
			model.addAttribute("final_countsts1", 1);
		else
			model.addAttribute("final_countsts2", 0);

		/*
		 * List<String> roleview =
		 * fpoService.getViewRole(request.getSession().getAttribute("offId") == null ?
		 * null : request.getSession().getAttribute("offId").toString());
		 * model.addAttribute("roleview", roleview);
		 */

		/*
		 * List<String> roleview = DempFpo.getViewRole();
		 * model.addAttribute("roleview",roleview);
		 */

		List<String> roleviewa = DempFpo.getViewRolea();
		model.addAttribute("roleviewa", roleviewa);

		List<String> roleviewb = DempFpo.getViewRoleb();
		model.addAttribute("roleviewb", roleviewb);

		List<String> roleviewc = DempFpo.getViewRolec();
		model.addAttribute("roleviewc", roleviewc);

		System.out.println(roleviewa);
		String offid = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		System.out.println("offid is " + offid);
		log.info("offid is " + offid);
		System.out.println(fpoService.getmulrole(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString()));
		String cussite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		System.out.println("cussite is " + cussite);
		log.info("cuSite is " + cussite);
		request.getSession().setAttribute("data2", cussite);
		String empname = DempFpo.getempname(offid, cussite);
		request.getSession().setAttribute("data3", empname);
		List<String> rolestype = DempFpo.getroletype(offid, cussite);
		model.addAttribute("rolestype", rolestype);

		List<Collection> listrolesa = DempFpo.getallroles_a(offid, cussite);
		int coua = listrolesa.size();

		log.info("rolesizea is " + coua);
		model.addAttribute("coua", coua);
		model.addAttribute("rolesa", listrolesa);

		List<Collection> listrolesb = DempFpo.getallroles_b(offid, cussite);
		int coub = listrolesb.size();

		log.info("rolesizeb is " + coub);
		model.addAttribute("coub", coub);
		model.addAttribute("rolesb", listrolesb);

		List<Collection> listrolesc = DempFpo.getallroles_c(offid, cussite);

		model.addAttribute("rolesc", listrolesc);

		int couc = listrolesc.size();
		log.info("rolesizec is " + couc);
		model.addAttribute("couc", couc);
		ModelAndView modelAndView = new ModelAndView("selrole");
		return modelAndView;
	}

	@RequestMapping("/assdonernot")
	public @ResponseBody String assdonernot(HttpServletRequest request) {
		String cinNo = request.getParameter("id");
		String decisiondata = new String();
		decisiondata = FPOrepost.getAssdate(cinNo);
		if (decisiondata.length() > 0)
		     return decisiondata;
		else
			 return null;

	}

//	@RequestMapping("/querydate")
//	public @ResponseBody FpoQuery getfpodate(HttpServletRequest request) {
//		String cinNo = request.getParameter("id");
//		FpoQuery fpoQuery1 = new FpoQuery();
//		List<FpoQuery> fpoquery = fpoQueryRepo.getfpoqueryandDin(cinNo);
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		String strDate = formatter.format(fpoquery.get(0).getQRY_DATE());
//		fpoQuery1.setDate(strDate);
//		System.out.println(strDate);
//		return fpoQuery1;
//	}

	@RequestMapping("/pmovedi")
	public @ResponseBody String pmovedi(@RequestBody FpoMvmnt fpomvmt, HttpServletRequest request,
			HttpSession session) {
		String cinNo = request.getParameter("id");
		fpoDeclaredService.movedi(fpomvmt, session, request);
		return "success";
	}

	@RequestMapping("/fmovedi")
	public @ResponseBody String fmovedi(@RequestBody FpoMvmnt fpomvmt, HttpServletRequest request,
			HttpSession session) {
		String cinNo = request.getParameter("id");
		System.out.println("in fmovedi id is " + cinNo);
		fpoDeclaredService.movedi(fpomvmt, session, request);
		return "success";
	}

	@RequestMapping(value = "/pendingdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> getfpoQuery(@RequestBody FpoQuery fpoqueryno, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		List<Collection> fpoquery1 = fpoQueryRepo.getfpoqryandDesc(fpoqueryno.getCinNo());
		return fpoquery1;
	}

	@RequestMapping(value = "/fpoquerydin", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<FpoQueryDin> getQueryDin(@RequestBody FpoQueryDin fpoquerydin, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<FpoQueryDin> fpoqueryDin = fpoquerydinrepo.getfpouniqueno(fpoquerydin.getCinNo());
		return fpoqueryDin;
	}

//	@RequestMapping(value = "/fpodefaultQry", produces = {
//			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody List<String> getDefQuery(@RequestBody FpoQuery fpoqueryno, HttpServletRequest request,
//			HttpServletResponse response, Model model) throws ParseException {
//		String DefualtQuery = fpoQueryRepo.getfpoDefQry(fpoqueryno.getCinNo());
//		System.out.println(DefualtQuery);
//		List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery);
//		System.out.println(defualtQueryList);
//		return defualtQueryList;
//	}

	@RequestMapping(value = "/fpodefaultQry", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<String> getDefQuery(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,
			@RequestBody FpoQuery fpoqueryno, HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException {
		String DefualtQuery = fpoQueryRepo.getfpoDefQry(fpoqueryno.getCinNo());
		System.out.println(DefualtQuery);
		String cinNo = fpo_item_det.getCinNo();
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());
		List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
		System.out.println(defualtQueryList);
		return defualtQueryList;
	}

	@RequestMapping(value = "/insertpendingdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public void saveresponse(@RequestBody FpoQuery fpoqry, HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws java.text.ParseException {
		fpoQueryService.updateRespQry(fpoqry,null,null,null, session, request);

	}

	@RequestMapping(value = "/defqryresp", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public void defQryresp(@RequestBody FpoQuery fpoqry, HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws java.text.ParseException {
		fpoQueryService.updateRespQry(fpoqry,null,null,null, session, request);

	}

	@RequestMapping(value = "/updatetoexamqueue", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public void qrytype(@RequestBody FpoQueryDecision fpoqrydec, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		System.out.println(fpoqrydec.getPen_cin());
		fpoquerydecisionrepo.updateqrytype(fpoqrydec.getPen_cin());
		//long l=FPOrepost.getmaxidrspsent();
		CUSRSP_SENT cusSent = new CUSRSP_SENT();
		cusSent.setCin_NO(fpoqrydec.getPen_cin());
		cusSent.setCategory("PEN");
		//cusSent.setSent_ID(l);
		cusSentRepo.save(cusSent);

	}

	// ----------------------------IMPORTED POSTAL
	// ARTCILES--------------------------------------------------------------------------//

	@RequestMapping(value = "/getsearchOOCdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> getsearchOOCdata(@RequestBody FPO_MAIN fpoMain, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.getsearchOOCdata(fpoMain);
	}

	@RequestMapping(value = "/getsearchEXMdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> getsearchEXMdata(@RequestBody FPO_MAIN fpoMain, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.getsearchEXMdata(fpoMain);
	}

	@RequestMapping(value = "/getsearchDETdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> getsearchDETdata(@RequestBody FPO_MAIN fpoMain, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.getsearchDETdata(fpoMain);
	}

	@RequestMapping(value = "/getsearchQRYdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> getsearchQRYdata(@RequestBody FPO_MAIN fpoMain, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return fpoService.getsearchQRYdata(fpoMain);
	}

	@RequestMapping(value = "/movqrytoassdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQueryDecision movqrytoassdata(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		fpomvmnt.setCinNo(fpoqryDec.getCIN_NO());
		return fpoService.movqrytoassdata(fpoqryDec, fpomvmnt, session, request);
	}
	
	// --------------------------------------------------------- commented BY VEEMAN on 22/04/2023 -------------------------------------------- 
	
//	@RequestMapping(value = "/updnorply", produces = {
//			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody FpoQueryDecision updnorply(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
//			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
//		String cinno=fpoqryDec.getCIN_NO();
//		fpoQueryRepo.updnorply(cinno);
//		fpoQueryRepo.updnorplydcall(FPOrepost.getitemid(cinno));
//		return fpoService.movqrytoassdata(fpoqryDec, fpomvmnt, session, request);
//	}

	// --------------------------------------------------------- ---------------------- -------------------------------------------- 

	// --------------------------------------------------------- added BY VEEMAN on 22/04/2023 --------------------------------------------
	
	@RequestMapping(value = "/updnorply", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQueryDecision updnorply(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		String cinno=fpoqryDec.getCIN_NO();
		if (fpoQueryRepo.chkaddlqryvalid(cinno) == 0 ) {
		fpoQueryRepo.updnorply(cinno);
		fpoQueryRepo.updnorplydcall(FPOrepost.getitemid(cinno));
		return fpoService.movqrytoassdata(fpoqryDec, fpomvmnt, session, request);
	}	
	else {
		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinno);
		if(getDefQry.get(0).getRply() != null) {
			fpoQueryRepo.updnorply(cinno);
			fpoQueryRepo.updnorplydcall(FPOrepost.getitemid(cinno));
			fpoQueryRepo.updaddnorply(cinno);
			fpoQueryRepo.updnorplydcall(FPOrepost.getitemid(cinno));
			return fpoService.movqrytoassdata(fpoqryDec, fpomvmnt, session, request);
		}else {
			fpoQueryRepo.updaddnorply(cinno);
			fpoQueryRepo.updnorplydcall(FPOrepost.getitemid(cinno));
			return fpoService.movqrytoassdata(fpoqryDec, fpomvmnt, session, request);
		}
	 } 
	}
	
	// --------------------------------------------------------- ---------------------- -------------------------------------------- 


//	@RequestMapping(value = "/movasstoqrydata", produces = {
//			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody FpoQueryDecision movasstoqrydata(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
//			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
//		fpomvmnt.setCinNo(fpoqryDec.getCIN_NO());
//		return fpoService.movasstoqrydata(fpoqryDec, fpomvmnt, session, request);
//	}
	
	//modified by santhosh veem 	
	@RequestMapping(value = "/movasstoqrydata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQueryDecision movasstoqrydata(@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det, @RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		fpomvmnt.setCinNo(fpoqryDec.getCIN_NO());
		return fpoService.movasstoqrydata(fpoqryDec, fpomvmnt, session, request);
	}
	
	
	@RequestMapping(value = "/movqrytoedidata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQueryDecision movqrytoedidata(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		fpomvmnt.setCinNo(fpoqryDec.getCIN_NO());
		return fpoService.movqrytoedidata(fpoqryDec, fpomvmnt, session, request);
	}

	@RequestMapping(value = "/oocsubmit", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoQueryDecision oocsubmit(@RequestBody FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,
			CUSRSP_SENT cusrsp, HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) throws IOException {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpomvmnt.setCinNo(fpoqryDec.getCIN_NO());
		System.out.println("fpoqrydec cinno is" + fpoqryDec.getCIN_NO());
		System.out.println("fpomvmvnt cinno is" + fpomvmnt.getCinNo());
		fpoCurQueRepo.updfpostatussup(fpoqryDec.getCIN_NO(), request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString(), utilDate);
		return fpoService.oocsubmit(fpoqryDec, fpomvmnt, cusrsp, session, request);
	}

	@RequestMapping(value = "/asssubmit", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN asssubmit(@RequestBody FPO_MAIN fpomain, FPO_ITEM_DET fpoItemDet,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

		// fpoCurQueRepo.updfpostatussup(fpoqryDec.getCIN_NO(),
		// request.getSession().getAttribute("offId") == null ? null :
		// request.getSession().getAttribute("offId").toString(), utilDate);
		return fpoService.asssubmit(fpomain, fpoItemDet, session, request);
	}

	@RequestMapping(value = "/movbulkasstoooc", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody void movbulkasstoooc(@RequestBody FpoQueryDecision fpoQueryDecision,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		String cinNo = fpoQueryDecision.getCIN_NO();
		String cat = "";  // Kanishkar in this function added this empty string on 21/04/2023
		System.out.println("cinno is " + cinNo);
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		fpoCurQueService.addUserQue(cinNo, FPOrepost.getitemid(cinNo), "E4", session, request);
		fpoQueryDecision.setITEM_ID(fpoMain.get(0).getITEM_ID());
		fpoCurQueService.updateUSerEnterStatus(cinNo, "E4", session, "E1", request);
		fpoService.insertIntoDecisionsDb(fpoQueryDecision, "E4", session, "E1", request);
		fpoService.decisionSubmitService(fpoQueryDecision, session, request, cat);
		fpoCurQueService.updateStatus(fpoMain.get(0), session, request);
		return;
	}

	@RequestMapping(value = "/MOVQRYTOOOC", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt MOVQRYTOOOC(@RequestBody FpoMvmnt fpomvmnt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		System.out.println("FpoMvmnt: cin no" + fpomvmnt.getCinNo());
		return fpoService.MOVQRYTOOOC(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/MOVQRYTOEXM", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt MOVQRYTOEXM(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {
		fpomvmnt.setCinNo(fpoexam.getCinNo());
		System.out.println("FpoMvmnt: cin no in IN MOVQRYTEXM" + fpomvmnt.getCinNo());
		return fpoService.MOVQRYTOEXM(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/exmoocdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt exmoocdata(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {
		fpomvmnt.setCinNo(fpoexam.getCinNo());
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpoCurQueRepo.updfpostatusexm(fpoexam.getCinNo(), request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString(), utilDate);
		return fpoService.MOVQRYTOOOC(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/passoocdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt asmoocdata(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {
		fpomvmnt.setCinNo(fpoexam.getCinNo());
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO"))
			fpoCurQueRepo.updfpostatusapr(fpoexam.getCinNo(), utilDate,
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString());
		else if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAC"))
			fpoCurQueRepo.updfpostatusacl(fpoexam.getCinNo(), utilDate,
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString());
		return fpoService.MOVQRYTOOOC(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/passexmdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt asmexmdata(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {

		fpomvmnt.setCinNo(fpoexam.getCinNo());

		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

		if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAO"))
			fpoCurQueRepo.updfpostatusapr(fpoexam.getCinNo(), utilDate,
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString());
		else if (request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString().equals("PAC"))
			fpoCurQueRepo.updfpostatusacl(fpoexam.getCinNo(), utilDate,
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString());
		//FPOrepost.updateRoleApr(fpoexam.getCinNo());
		return fpoService.MOVQRYTOEXM(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/oocdispmaindata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> oocdispmaindata(@RequestBody FPO_MAIN fpomain, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		String cinno = fpomain.getId();
		List<Collection> ooclist = FPOrepost.getoocmainitmdata(cinno);
		// ((DUTY_CALC_DETAILS)
		// request.getSession().getAttribute("dutyCalc")).getAssval_Amt());
		return ooclist;
	}

	@RequestMapping(value = "/oocdispdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody List<Collection> oocdispdata(@RequestBody FPO_ITEM_DET fpoItemDet, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		return fpoService.getoocdispdata(fpoItemDet);
	}

	@RequestMapping(value = "/exmassdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt exmassdata(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {
		fpomvmnt.setCinNo(fpoexam.getCinNo());
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpoCurQueRepo.updfpostatusexm(fpoexam.getCinNo(), request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString(), utilDate);
		return fpoService.MOVEXMTOASS(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/oocassdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt oocassdata(@RequestBody FPO_OOC fpoooc, FpoMvmnt fpomvmnt, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		fpomvmnt.setCinNo(fpoooc.getCinNo());
		// fpoCurQueRepo.updfpostatusooc(fpoexam.getCinNo(),request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		return fpoService.MOVEXMTOASS(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/oocdetdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FpoMvmnt oocdetdata(@RequestBody FPO_EXAM fpoexam, FpoMvmnt fpomvmnt,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws ParseException {
		fpomvmnt.setCinNo(fpoexam.getCinNo());
		// fpoCurQueRepo.updfpostatusooc(fpoexam.getCinNo(),request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		fpoCurQueRepo.updfpostatusdet(fpoexam.getCinNo(), request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString(), utilDate);
		fpoDetainedInfoRepo.updateDetainCompletedEmpty(FPOrepost.getitemid(fpoexam.getCinNo()));
		return fpoService.MOVOOCTODET(fpomvmnt, session, request);
	}

	@RequestMapping(value = "/insoocdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_OOC insoocdata(@RequestBody FPO_OOC fpoooc, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		System.out.println("FPOOOC: cin no" + fpoooc.getCinNo());
		return fpoService.insdboocfindings(fpoooc, session, request);
	}

	@RequestMapping(value = "/insexmdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_EXAM insexmdata(@RequestBody FPO_EXAM fpoexam, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		System.out.println("FPOEXAM: cin no" + fpoexam.getCinNo());
		System.out.println("FPOEXAM: cin no" + fpoexam.getRmks());
		return fpoService.insdbexmfindings(fpoexam, session, request);
	}

	// ----------------------------added on May 12th by Sasi
	// ........................................................................
	@RequestMapping(value = "/exmview", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody ModelAndView exmview(@RequestBody FPO_EXAM fpoexam, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) throws ParseException {
		System.out.println("FPOEXAM: cin no" + fpoexam.getCinNo());
		System.out.println("FPOEXAM: cin no" + fpoexam.getRmks());
		return fpoService.exam_view(fpoexam, request, response, model, session);
		// ----------------------------added on May 12th by Sasi
		// ........................................................................
	}

	// ----------------------------IMPORTED POSTAL
	// ARTCILES--------------------------------------------------------------------------/

	// ----------------------------added on May 23rd by Sasi ...dash
	// search.....................................................................

	@RequestMapping(value = "/getschdata", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAIN getschdata(@RequestBody FPO_MAIN fpoMain, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		FPO_MAIN val1 = new FPO_MAIN();
		val1.setStringval(fpoService.getpos(fpoMain.getITEM_ID(), session, request));
		//System.out.println("in controller" + val1.getStringval());

		/*
		 * if (curque != null) return curque; else return null;
		 */
		return val1;
	}

	/*
	 * @RequestMapping(value = "/raiseqry", produces = {
	 * MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	 * public @ResponseBody FPO_ITEM_DET raiseqry(@ModelAttribute Fpo_qry_raise
	 * fpo_qry_raise, HttpServletRequest request, HttpServletResponse response,
	 * Model model, HttpSession session) { // return
	 * fpoDeclaredService.eadItemQryUpdate(null); String que =
	 * request.getParameter("que"); List<FPO_ITEM_DET> fpoItemDET =
	 * fpoItemDetRepo.getItemByIdNo(fpo_qry_raise.getCinNo(),
	 * Long.parseLong(fpo_qry_raise.getItemdet().get(0).getItemno()));
	 * fpoDeclaredService.movFPOqryamend(fpo_qry_raise);
	 * fpoDeclaredService.addFpoQuery(fpo_qry_raise, que, session,request);
	 * fpoDeclaredService.addFpoDocumentsAndGenaralQuery(fpo_qry_raise, que,
	 * session,request); return fpoItemDET.get(0); }
	 */

	@RequestMapping(value = "/raiseqry", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_ITEM_DET raiseqry(@ModelAttribute Fpo_qry_raise fpo_qry_raise, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		// return fpoDeclaredService.eadItemQryUpdate(null);
		String que = request.getParameter("que");
		System.out.println(que);
		String others = request.getParameter("others");
		List<FPO_ITEM_DET> fpoItemDET = fpoItemDetRepo.getItemByIdNo(fpo_qry_raise.getCinNo(),
				Long.parseLong(fpo_qry_raise.getItemdet().get(0).getItemno()));
		fpoDeclaredService.movFPOqryamend(fpo_qry_raise, others);
		fpoDeclaredService.addFpoQuery(fpo_qry_raise, que, others, session, request);
		fpoDeclaredService.addFpoDocumentsAndGenaralQuery(fpo_qry_raise, others, que, session, request);

		return fpoItemDET.get(0);
	}

	/*
	 * @RequestMapping(value = "/getschdata", produces = {
	 * MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	 * public @ResponseBody getschdata(@RequestBody FPO_MAIN fpoMain,
	 * HttpServletRequest request, HttpServletResponse response, Model model) {
	 * System.out.println(fpoMain.getId());
	 * System.out.println(fpoMain.getITEM_ID());
	 * FpoService.getcurque(fpoMain.getId(), fpoMain.getITEM_ID()); return fpoMain;
	 * }
	 */

	// ----------------------------added on May 23rd by Sasi ............dash
	// search............................................................

	// Priority

	@RequestMapping("/priority")
	public ModelAndView priority(Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("priorityqueue");
		String logincs = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		List<Collection> priorityHist = DempFpo.getPriorityHist();
		modelAndView.addObject("priorityhistory", priorityHist);
		return modelAndView;
	}

	@RequestMapping(value = "/getitemidstage")
	public @ResponseBody String getstsofitemid(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		String val = request.getParameter("getsts");
		String datachck = "0";
		String chckid = fpoCurQueRepo.chckitmid(val);
		String chkwithcuSite = fpoCurQueRepo.curqueueitem(val);
		String Cschk = request.getSession().getAttribute("cuSite").toString();
		System.out.println(Cschk);
		if (chckid != null && chckid != "") {
			if (!(chkwithcuSite.equalsIgnoreCase(Cschk))) {
				chkwithcuSite = fpoCurQueRepo.curqueueitem(val);
				datachck = chkwithcuSite;
				datachck = "2";

			} else {
				datachck = "1";

			}

		} else {
			datachck = "0";

		}
		return datachck;
	}

	@RequestMapping(value = "/updatepriority")
	public @ResponseBody String updateprio(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, Model model) throws java.text.ParseException {
		String desc = "";
		String val1 = request.getParameter("itmid");
		String Lgcs1 = request.getSession().getAttribute("cuSite").toString();
		/* String itmstg = request.getParameter("getitmstg"); */
		String resp = request.getParameter("resp");
		String pac = fpoCurQueRepo.getrole(val1);
		System.out.println(pac);
	//	String checkcrque = fpoCurQueRepo.checkcurque(val1, request.getSession().getAttribute("cuSite").toString());
		String checkcrque = fpoCurQueRepo.checkcurque(val1);
			if (pac == null || pac.equals("PAO")) {
			if (checkcrque != null && checkcrque != "") {
				String qrydescval = fpoCurQueRepo.checkinQrydesc(val1,
						request.getSession().getAttribute("cuSite").toString());

				if (qrydescval == null || qrydescval == "") {
					desc = "novalue";
				} else if (qrydescval.equals("P1") || qrydescval.equals("N1") || qrydescval.equals("P2")) {
					fpoCurQueRepo.updatepriority(val1, Lgcs1);
					fpoService.addfpopriority(val1, Lgcs1, resp, session, request);
				} else {
					desc = "novalue";
				}

			} else {
				fpoCurQueRepo.updatepriority(val1, Lgcs1);
				fpoService.addfpopriority(val1, Lgcs1, resp, session, request);
			}

		} else if (pac.equals("PAC")) {
			fpoCurQueRepo.updatepriority(val1, Lgcs1);
			fpoService.addfpopriority(val1, Lgcs1, resp, session, request);
		}
		return desc;
	}

	@RequestMapping(value = "/dcallhistory", method = RequestMethod.GET)
	public ModelAndView dcallhistory(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("dcallhistory");
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String todate = dateFormat.format(new Date());

			String fromdate = "01/";
			int year = Calendar.getInstance().get(Calendar.YEAR);

			SimpleDateFormat simpleformat = new SimpleDateFormat("MM");
			String strMonth = simpleformat.format(new Date());

			fromdate = fromdate.concat(strMonth).concat("/").concat(Integer.toString(year));

			models.addObject("fromdate", fromdate);
			models.addObject("todate", todate);

			List<DcallHistory2> dcallhistory = reportService.getDcallHistory2(todate, todate, session, request);

			long uniquecount = dcallhistory.stream().map(p -> p.getItem_id()).distinct().count();
			long addquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("ADDITIONAL QUERY"))
					.map(p -> p.getItem_id()).count();
			long firstquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("FIRST QUERY"))
					.map(p -> p.getItem_id()).count();
			long totalcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long pendprintcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long emailcount = dcallhistory.stream().filter(p -> p.getEmailcou() != null).map(p -> p.getItem_id()).count();
			long smscount = dcallhistory.stream().filter(p -> p.getSmscou() != null).map(p -> p.getItem_id()).count();
			long printcount = dcallhistory.stream().filter(p -> p.getPrintcou() != null).map(p -> p.getItem_id()).count();
			models.addObject("history", null);
			models.addObject("dcallhistory", dcallhistory);

			
			models.addObject("pendingprint", pendprintcount);
			models.addObject("firstquery",firstquerycount);
			models.addObject("addlquery",addquerycount);
			models.addObject("total", totalcount);
			models.addObject("totalarticles",uniquecount);
			models.addObject("printcount",printcount);
			models.addObject("smscount",smscount);
			models.addObject("emailcount",emailcount);
			/*
			 * models.addObject("dcallhistory", dcallhistory);
			 * 
			 * models.addObject("pendingprint", fpoDcallQryRepo.getpendingprintcount(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("firstquery", fpoDcallQryRepo.getfirstquerytoday(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("addlquery", fpoDcallQryRepo.getaddlquerytoday(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("total", dcallhistory.size());
			 * models.addObject("totalarticles",
			 * fpoDcallQryRepo.gettotalarticlestoday(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("printcount", fpoDcallQryRepo.getprintcount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("smscount", fpoDcallQryRepo.getsmscount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("emailcount", fpoDcallQryRepo.getemailcount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("history", null);
			 */
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return models;

	}

	@PostMapping(value = "/sendmail")
	public @ResponseBody void sendmail(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String filename = request.getParameter("filename");
			String item_id = request.getParameter("item_id");
			String dcallno = request.getParameter("dcallno");
			String cinNo = request.getParameter("cinno");
			String din1 = request.getParameter("din1");
			String email = request.getParameter("email") != null && !request.getParameter("email").equalsIgnoreCase("")
					? request.getParameter("email")
					: null;
			String recp_name = request.getParameter("recp_name");
			String mobile = request.getParameter("mobile") != null
					&& !request.getParameter("mobile").equalsIgnoreCase("") ? request.getParameter("mobile") : null;

			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
			String to = fpoMainData.get(0).getRECP_EMAILID();

			request.getSession().setAttribute("to", to);
			request.getSession().setAttribute("tomailid", email);
			request.getSession().setAttribute("filename", filename);
			request.getSession().setAttribute("itemid", din1);
			request.getSession().setAttribute("dinno", null);
			request.getSession().setAttribute("recpname", recp_name);

//			Random rnd = new Random();
//			 int rndno = rnd.nextInt(9999999);
//			 System.out.println(rndno);
				
//			 System.out.println("dcallno is" + dcallno);
//			 String dcall_part = dcallno == null ? null : dcallno.toString().substring(5, 12);

//			String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
			
			String smsurl = fpoDcallQryRepo.getsmsUrl(dcallno);
			request.getSession().setAttribute("url", smsurl);

			
			String smsText = "INDIAN CUSTOMS - raised query - Ref: Postal Art.ID - " + item_id + " / DCALL- " + dcallno + " Reply via Post/Visit the URL: " + smsurl + " -CBIC";
			log.info("smstext="+smsText);
			System.out.println(smsText);
			
			if (email != null) {
				fpoService.sendmail(item_id, dcallno, session, cinNo, request);
			}

			
//			String smsText = "INDIAN CUSTOMS -raised query - Ref: Postal Art.id- " + item_id + " / DCALL - " + dcallno;
			

			if (mobile != null) {
				fpoService.sendSms(mobile, smsText, "1107167637041176783", dcallno, cinNo, session, request);
			}

			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
			jsonObj = new JSONObject(dCALLQRY_GEN);

			jsonObj.put("status", "Success");

			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/viewcountupdate")
	public @ResponseBody void viewcountupdate(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinno = request.getParameter("cinno");
			String dcall_no = request.getParameter("dcall_no");

			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcall_no);
			int count = 1;

			if (dCALLQRY_GEN.getViewcou() != null) {
				count = Integer.parseInt(dCALLQRY_GEN.getViewcou()) + 1;
			}

			fpoDcallQryRepo.dcallqrypdfviewcountupdate(count, cinno, dcall_no);
			jsonObj.put("status", "Success");

			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/printcountupdate")
	public @ResponseBody void printcountupdate(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinno = request.getParameter("cinno");
			String dcall_no = request.getParameter("dcall_no");

			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcall_no);
			int count = 1;

			if (dCALLQRY_GEN.getPrintcou() != null) {
				count = Integer.parseInt(dCALLQRY_GEN.getPrintcou()) + 1;
			}

			fpoDcallQryRepo.dcallqrypdfprintcountupdate(count, cinno, dcall_no);
			jsonObj.put("status", "Success");
			dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcall_no);
			jsonObj = new JSONObject(dCALLQRY_GEN);

			jsonObj.put("status", "Success");
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/getdcallqrygendetail")
	public @ResponseBody void getdcallqrygendetail(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinno = request.getParameter("cinno");

			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCallNumberByCinNo(cinno);
			jsonObj = new JSONObject(dCALLQRY_GEN);

			jsonObj.put("status", "Success");

			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/pdfinfo")
	public @ResponseBody void pdfinfo(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinno = request.getParameter("cinno");

			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCallNumberByCinNo(cinno);
			jsonObj = new JSONObject(dCALLQRY_GEN);
			jsonObj.put("status", "Success");

			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/pushdcalltodayrecord")
	public ModelAndView pushdcalltodayrecord(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView models = new ModelAndView("pushdcalldatechange");
		try {
			// String todate = request.getParameter("todate");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String todate = dateFormat.format(new Date());

			List<DcallHistory2> dcallhistory = reportService.getDcallHistory2(todate, todate, session, request);
			long uniquecount = dcallhistory.stream().map(p -> p.getItem_id()).distinct().count();
			long addquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("ADDITIONAL QUERY"))
					.map(p -> p.getItem_id()).count();
			long firstquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("FIRST QUERY"))
					.map(p -> p.getItem_id()).count();
			long totalcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long pendprintcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long emailcount = dcallhistory.stream().filter(p -> p.getEmailcou() != null).map(p -> p.getItem_id()).count();
			long smscount = dcallhistory.stream().filter(p -> p.getSmscou() != null).map(p -> p.getItem_id()).count();
			long printcount = dcallhistory.stream().filter(p -> p.getPrintcou() != null).map(p -> p.getItem_id()).count();
			models.addObject("dcallhistory", dcallhistory);

			models.addObject("fromdate", null);
			models.addObject("todate", todate);
			models.addObject("history", null);
			

			models.addObject("pendingprint", pendprintcount);
			models.addObject("firstquery",firstquerycount);
			models.addObject("addlquery",addquerycount);
			models.addObject("total", totalcount);
			models.addObject("totalarticles",uniquecount);
			models.addObject("printcount",printcount);
			models.addObject("smscount",smscount);
			models.addObject("emailcount",emailcount);




			
			
			
			/*
			 * models.addObject("dcallhistory", dcallhistory);
			 * 
			 * models.addObject("fromdate", null); models.addObject("todate", todate);
			 * 
			 * models.addObject("pendingprint", fpoDcallQryRepo.getpendingprintcount(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("firstquery", fpoDcallQryRepo.getfirstquerytoday(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("addlquery", fpoDcallQryRepo.getaddlquerytoday(todate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("total", dcallhistory.size());
			 * models.addObject("totalarticles",
			 * fpoDcallQryRepo.gettotalarticlestoday(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("printcount", fpoDcallQryRepo.getprintcount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("smscount", fpoDcallQryRepo.getsmscount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("emailcount", fpoDcallQryRepo.getemailcount(todate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * 
			 * models.addObject("history", null);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@PostMapping(value = "/pushdcallfilterrecord")
	public ModelAndView pushdcallfilterrecord(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView models = new ModelAndView("pushdcalldatechange");
		try {
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<DcallHistory2> dcallhistory = reportService.getDcallHistoryPush(fromdate, todate, session, request);
			long uniquecount = dcallhistory.stream().map(p -> p.getItem_id()).distinct().count();
			long addquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("ADDITIONAL QUERY"))
					.map(p -> p.getItem_id()).count();
			long firstquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("FIRST QUERY"))
					.map(p -> p.getItem_id()).count();
			long totalcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long pendprintcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long emailcount = dcallhistory.stream().filter(p -> p.getEmailcou() != null).map(p -> p.getItem_id()).count();
			long smscount = dcallhistory.stream().filter(p -> p.getSmscou() != null).map(p -> p.getItem_id()).count();
			long printcount = dcallhistory.stream().filter(p -> p.getPrintcou() != null).map(p -> p.getItem_id()).count();
			models.addObject("dcallhistory", dcallhistory);
			models.addObject("fromdate", fromdate);
			models.addObject("todate", todate);
			models.addObject("history", null);
			models.addObject("pendingprint", "0");
			/*
			 * models.addObject("firstquery", fpoDcallQryRepo.getfirstqueryfilter(fromdate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("addlquery", fpoDcallQryRepo.getaddlqueryfilter(fromdate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("total", dcallhistory.size());
			 * models.addObject("totalarticles",
			 * fpoDcallQryRepo.gettotalarticlesfilter(fromdate, todate,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("printcount", fpoDcallQryRepo.getprintcountPush(fromdate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("smscount", fpoDcallQryRepo.getsmscountPush(fromdate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("emailcount", fpoDcallQryRepo.getemailcountPush(fromdate,
			 * todate, request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 */
			models.addObject("firstquery",firstquerycount);
			models.addObject("addlquery",addquerycount);
			models.addObject("total", totalcount);
			models.addObject("totalarticles",uniquecount);
			models.addObject("printcount",printcount);
			models.addObject("smscount",smscount);
			models.addObject("emailcount",emailcount);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@PostMapping(value = "/pendingprint")
	public ModelAndView pendingprint(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView models = new ModelAndView("pushdcalldatechange");
		try {
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<DcallHistory3> dcallhistory = reportService.getDcallHistoryPendingPrint(session, request);
			
			long uniquecount = dcallhistory.stream().map(p -> p.getItem_id()).distinct().count();
			long addquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("ADDITIONAL QUERY"))
					.map(p -> p.getItem_id()).count();
			long firstquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("FIRST QUERY"))
					.map(p -> p.getItem_id()).count();
			long totalcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long pendprintcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long emailcount = dcallhistory.stream().filter(p -> p.getEmailcou() != null).map(p -> p.getItem_id()).count();
			long smscount = dcallhistory.stream().filter(p -> p.getSmscou() != null).map(p -> p.getItem_id()).count();
			long printcount = dcallhistory.stream().filter(p -> p.getPrintcou() != null).map(p -> p.getItem_id()).count();

			/*
			 * long uniqueArticleCount = dcallhistory.stream() .map(DcallHistory3::item_id)
			 * .distinct() .count();
			 */
			
			models.addObject("dcallhistory", dcallhistory);
			models.addObject("fromdate", null);
			models.addObject("todate", null);
			models.addObject("history", null);

			
			models.addObject("pendingprint", pendprintcount);
			models.addObject("firstquery",firstquerycount);
			models.addObject("addlquery",addquerycount);
			models.addObject("total", totalcount);
			models.addObject("totalarticles",uniquecount);
			models.addObject("printcount",printcount);
			models.addObject("smscount",smscount);
			models.addObject("emailcount",emailcount);


			
			/*
			 * models.addObject("dcallhistory", dcallhistory);
			 * 
			 * models.addObject("fromdate", null); models.addObject("todate", null);
			 * models.addObject("history", null);
			 * 
			 * models.addObject("pendingprint", dcallhistory.size());
			 * models.addObject("firstquery",
			 * fpoDcallQryRepo.getpendingfirstquerytoday(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("addlquery",
			 * fpoDcallQryRepo.getpendingaddlquerytoday(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("total", dcallhistory.size());
			 * models.addObject("totalarticles",
			 * fpoDcallQryRepo.getpendingtotalarticles(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("printcount",
			 * fpoDcallQryRepo.getprintcountPendingPrint(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("smscount",
			 * fpoDcallQryRepo.getsmscountPendingPrint(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("emailcount",
			 * fpoDcallQryRepo.getemailcountPendingPrint(request.getSession().getAttribute(
			 * "offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@PostMapping(value = "/speedpostrecord")
	public ModelAndView speedpostrecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView models = new ModelAndView("speedpostrecord");
		try {
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<DcallHistory3> dcallhistory = reportService.getDcallHistoryspeedpostrecord(session, fromdate, todate);
			models.addObject("dcallhistory", dcallhistory);

			models.addObject("size", dcallhistory.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@PostMapping(value = "/speedpostrecordstatus")
	public ModelAndView speedpostrecordstatus(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView models = new ModelAndView("speedpostrecordhistory");
		try {
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<DcallHistory3> dcallhistory = reportService.getDcallHistoryspeedpostrecordStatus(fromdate,todate,session, request);
			models.addObject("dcallhistory", dcallhistory);

			models.addObject("totalpostdishis", dcallhistory.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@RequestMapping(value = "/updateSpeedPostNo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public @ResponseBody void updateSpeedPostNo(HttpServletRequest request, HttpServletResponse response,
			@RequestBody JsonNode inputjson) throws IOException {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = new JSONObject(inputjson);
			for (JsonNode json1 : inputjson) {
				String speedpostno = json1.get("speedpostno").textValue();
				String activesince = json1.get("activesince").textValue();

				/*
				 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date result =
				 * df.parse(activesince);
				 */
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				Date result = new SimpleDateFormat("dd/MM/yyyy").parse(activesince);
				// Date result=new Date(activesince);

				Long id = Long.parseLong(json1.get("id").toString().replaceAll("\"", ""));
				fpoDcallQryRepo.updateSpeedPostNo(id, speedpostno, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}

	@RequestMapping(value = "/editspeedpost", method = RequestMethod.POST)
	public @ResponseBody void editspeedpost(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		JSONObject jsonObj = new JSONObject();
		boolean status = false;

		try {

			String speedpostno = request.getParameter("speedpostno");
			String editdate = request.getParameter("editdate");
			String id = request.getParameter("id");

			Date result = new SimpleDateFormat("dd/MM/yyyy").parse(editdate);

			// Date result=new Date(editdate);

			fpoDcallQryRepo.updateSpeedPostNo(Long.parseLong(id.toString()), speedpostno, result);

			status = true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/dcallemailandsmsdetails")
	public ModelAndView dcallemailandsmsdetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView models = new ModelAndView("dcallemailandsmsdetails");
		try {
			String type = request.getParameter("type");
			String dcall_no = request.getParameter("dcall_no");

			List<Push_dcall> PushDcallHistory = new ArrayList();

			if (type.equalsIgnoreCase("EMAIL")) {
				PushDcallHistory = dcall_pushRepo.getPushDcallEmailHistory(dcall_no);
			} else if (type.equalsIgnoreCase("SMS")) {
				PushDcallHistory = dcall_pushRepo.getPushDcallSMSHistory(dcall_no);
			}

			models.addObject("PushDcallHistory", PushDcallHistory);

			models.addObject("type", type);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	@PostMapping(value = "/pushdcallfilterhistory")
	public ModelAndView pushdcallfilterhistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView models = new ModelAndView("OocNotgiven");
		try {
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			String month = request.getParameter("month");
			String order = request.getParameter("order");

			List<DcallHistory3> dcallhistory = reportService.getDcallHistory( fromdate,todate, session, order,request);
			long uniquecount = dcallhistory.stream().map(p -> p.getItem_id()).distinct().count();
			long addquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("ADDITIONAL QUERY"))
					.map(p -> p.getItem_id()).count();
			long firstquerycount = dcallhistory.stream().filter(p -> p.getQryname().equals("FIRST QUERY"))
					.map(p -> p.getItem_id()).count();
			long totalcount = dcallhistory.stream().map(p -> p.getItem_id()).count();
			long emailcount = dcallhistory.stream().filter(p -> p.getEmailcou() != null).map(p -> p.getItem_id()).count();
			long smscount = dcallhistory.stream().filter(p -> p.getSmscou() != null).map(p -> p.getItem_id()).count();
			long printcount = dcallhistory.stream().filter(p -> p.getPrintcou() != null).map(p -> p.getItem_id()).count();
			long pendingprint = totalcount - printcount;
			models.addObject("dcallhistory", dcallhistory);
			models.addObject("history", "history");
			models.addObject("fromdate", null);
			models.addObject("todate", null);
			/*
			 * models.addObject("pendingprint",
			 * fpoDcallQryRepo.getpendingprintcounthistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("firstquery",
			 * fpoDcallQryRepo.getpendingfirstqueryhistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("addlquery",
			 * fpoDcallQryRepo.getpendingaddlqueryhistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("total", dcallhistory.size());
			 * models.addObject("totalarticles",
			 * fpoDcallQryRepo.getpendingtotalarticles(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("printcount", fpoDcallQryRepo.getprintcounthistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("smscount", fpoDcallQryRepo.getsmscounthistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 * models.addObject("emailcount", fpoDcallQryRepo.getemailcounthistory(month,
			 * request.getSession().getAttribute("offId") == null ? null :
			 * request.getSession().getAttribute("offId").toString()));
			 */
			models.addObject("pendingprint", pendingprint);
			models.addObject("firstquery",firstquerycount);
			models.addObject("addlquery",addquerycount);
			models.addObject("total", totalcount);
			models.addObject("totalarticles",uniquecount);
			models.addObject("printcount",printcount);
			models.addObject("smscount",smscount);
			models.addObject("emailcount",emailcount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}

	// Hemanth Code -- 15-12

	// Re-allocation
	@RequestMapping(value = "/reallocation")
	public ModelAndView reallocation(Model model, HttpServletRequest request) {
		List<String> getresn = FPOrepost.getreallocationdesc();
		model.addAttribute("getresn", getresn);
		ModelAndView modelAndView = new ModelAndView("reallocation");
		return modelAndView;
	}

	@RequestMapping(value = "/delack")
	public ModelAndView acknowledge(Model model, HttpServletRequest request, HttpSession session) {
		String cusite = request.getSession().getAttribute("cuSite").toString();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String filter = "ooc_date";
		String fromdate = reportService.getFromDateForDelivery(filter, session, request);

		LocalDate now = LocalDate.now();
		LocalDate earlier = now.minusMonths(7);

		fromdate = earlier.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String todate = dateFormat.format(new Date());

		List<DeliveryArticlesColumns> oocsentarticles = reportService.getoocsentArticlesColumns(fromdate, todate,
				session, request);

		List<DeliveryArticlesColumns> deliveryarticles = reportService.getDeliveryArticlesColumns(fromdate, todate,
				session, request);

		List<DeliveryArticlesColumns> notdeliveryarticles = reportService.getDeliverynoArticlesColumns(fromdate, todate,
				session, request);

		ModelAndView models = new ModelAndView("delack");

		models.addObject("fromdate", fromdate);
		models.addObject("todate", todate);

		models.addObject("oocsentarticles", oocsentarticles);
		models.addObject("deliveryarticles", deliveryarticles);
		models.addObject("notdeliveryarticles", notdeliveryarticles);

		return models;
	}

	@RequestMapping(value = "/getreallocationdata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody REALLOCATION getreallocatevalue(@RequestBody REALLOCATION reallocate,
			HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model)
			throws ParseException, java.text.ParseException {

		String roles = request.getParameter("rolesassigned");
		String[] arryrol = request.getParameterValues("rolesassigned");
		reallocate.setRoles_assigned(roles);
		String frmssid = reallocate.getFrom_ssoid();
		String tossid = reallocate.getTo_ssoid();
		String mailcls = reallocate.getMail_class();
		String artid = reallocate.getArticle_id();
		fpoService.reallocateservice(reallocate, session, request);

		if (artid != "") {
			FPOrepost.specificid(roles, frmssid, tossid, mailcls, artid);
		} else {
			for (String role : arryrol) {
				fpoService.updatereallocation(role, frmssid, tossid, mailcls);
			}

		}

		return reallocate;
	}

	@RequestMapping(value = "/getassignedroles")
	public @ResponseBody List<String> getreallocatevalue(HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String usrid = request.getParameter("ssoid");
		List<String> getroles = FPOrepost.getrolename(usrid);
		return getroles;
	}

	@RequestMapping(value = "/tossoidroles")
	public @ResponseBody List<String> tossoidroles(HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		String usrid = request.getParameter("ssoid");
		List<String> getroles = FPOrepost.getrolename(usrid);
		return getroles;
	}

	@RequestMapping(value = "/getmailClas")
	public @ResponseBody List<String> Mailclas(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException {
		String usrid = request.getParameter("ssoid");
		String rolenme = request.getParameter("mailcls");
		List<String> getroles = FPOrepost.getmailclass(usrid, rolenme);
		return getroles;
	}

	// Allocation of fpo site
	@RequestMapping("/alloSite")
	public ModelAndView allofSite(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("allotfposite");
		String csval = request.getSession().getAttribute("cuSite").toString();
		String mapcdeCS = FPOrepost.getmapcde(csval);
		List<Collection> artwithoutpincodes = FPOrepost.getartid(mapcdeCS, csval);
		List<Collection> artwithoutpincodesnotmapped = FPOrepost.getartidnotmapped();
		// Without ead-data
		/* List<Collection> withoutEAD = FPOrepost.withouteaddata(csval); */
		model.addAttribute("artid", artwithoutpincodes);
		model.addAttribute("artidnotmapped", artwithoutpincodesnotmapped);
		/* model.addAttribute("withoutEAD", withoutEAD); */
		return modelAndView;
	}

	@RequestMapping("/viewarrinfo")
	public ModelAndView viewarrinfo(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("view_arrinfo");
		String csval = request.getSession().getAttribute("cuSite").toString();
		String mapcdeCS = FPOrepost.getmapcde(csval);
		List<ArticleArrInfo> artarr = null;
		artarr = reportService.getartarr_withead(csval, mapcdeCS);
		// artarr=FPOrepost.repgetartarr(mapcdeCS);
		// for ( ArticleArrInfo articlearrinfo : artarr) {
		// articlearrinfo.setCurrentStatus(fpoService.getpos("",
		// articlearrinfo.getArticleId(), session,request));
		// }
		// Without ead-data
		List<Collection> withoutEADarr = null;
		withoutEADarr = FPOrepost.recrdforwihtoutead(mapcdeCS);
		model.addAttribute("artid", artarr);
		model.addAttribute("session", session);
		model.addAttribute("withoutEAD", withoutEADarr);
		return modelAndView;
	}

	/*
	 * @GetMapping(value = "/viewarrinfo") public @ResponseBody void
	 * viewarrinfo(HttpServletRequest request, HttpServletResponse response,
	 * HttpSession session) { JSONObject jsonObj = new JSONObject(); String fromDate
	 * = request.getParameter("fromDate") != null &&
	 * !request.getParameter("fromDate").equalsIgnoreCase("") ?
	 * request.getParameter("fromDate") : null; String toDate =
	 * request.getParameter("toDate") != null &&
	 * !request.getParameter("toDate").equalsIgnoreCase("") ?
	 * request.getParameter("toDate") : null; String cusSite =
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString();
	 * List<DetainArticleHistoryBean> detainedArticlesHistory = new ArrayList<>();
	 * if (fromDate != null && toDate != null) { detainedArticlesHistory =
	 * detentionService.getDetainedArticlesHistory(fromDate, toDate, cusSite); for
	 * (DetainArticleHistoryBean detainArticleHistoryBean : detainedArticlesHistory)
	 * { detainArticleHistoryBean .setCurrentStatus(fpoService.getpos("",
	 * detainArticleHistoryBean.getArticleId(), session)); } } jsonObj.put("data",
	 * detainedArticlesHistory);
	 * response.setContentType("application/json;charset=UTF-8"); PrintWriter out =
	 * null; try { out = response.getWriter(); } catch (IOException e) {
	 * e.printStackTrace(); } out.write(jsonObj.toString()); }
	 */

	@PostMapping(value = "/viewoneart")
	public ModelAndView viewoneart(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String articleid = request.getParameter("artid");
		System.out.println(articleid);
		ModelAndView modelAndView = new ModelAndView("viewsingleart");
		String csval = request.getSession().getAttribute("cuSite").toString();
		List<Collection> viewsinglart = new ArrayList<>();
		String mapcdeCS = FPOrepost.getmapcde(csval);
		// viewsinglart=reportService.getviewsinglercd(csval, articleid, mapcdeCS);
		viewsinglart = FPOrepost.getviewsinglercd(articleid, mapcdeCS);
		// for (ArticleArrInfo ArticleArrInfo : viewsinglart) {
		// ArticleArrInfo
		// .setCurrentStatus(fpoService.getpos("", ArticleArrInfo.getArticleId(),
		// session,request));
		// }
		String currstatus = fpoService.getpos(articleid, session, request);
		model.addAttribute("currstatus", currstatus);
		List<Collection> getsinglercdnoEAD = FPOrepost.recrdforwihtoutead(articleid);
		String getdestfpo = FPOrepost.getsinglerecd(articleid);
		model.addAttribute("getitemdata", viewsinglart);
		model.addAttribute("getsinglercdnoEAD", getsinglercdnoEAD);
		String checkval = FPOrepost.returnval(articleid);
		List<String> getrecipentinfo = FPOrepost.getrecpinfrm(articleid);
		List<String> recpinfonotinEAD = FPOrepost.getntinEADrecpinfo(articleid);
		List<String> sendinfonotinEAD = FPOrepost.getntinEADsendinfo(articleid);
		List<String> getsenderinfo = FPOrepost.getsendinfrm(articleid);
		List<Collection> getstateNme = FPOrepost.getStateNme();
		List<Collection> artclests = FPOrepost.EADarticlArrinfo(articleid);
		if (artclests.size() == 0)
			artclests = FPOrepost.EADarticlArrinfofpo(articleid);
		model.addAttribute("artclests", artclests);
		if (checkval == null || checkval == "") {
			int log = 0;
			model.addAttribute("checkval", log);
			model.addAttribute("recpinfonotinEAD", recpinfonotinEAD);
			model.addAttribute("sendinfonotinEAD", sendinfonotinEAD);
		} else {
			int log = 1;
			model.addAttribute("checkval", log);
			model.addAttribute("recipentinfo", getrecipentinfo);
			model.addAttribute("getsenderinfo", getsenderinfo);
		}

		if (getdestfpo == null || getdestfpo == "") {
			model.addAttribute("getstateNme", getstateNme);
		}
		return modelAndView;
	}

	@PostMapping(value = "/particularid")
	public ModelAndView particulardata(HttpServletRequest request, HttpServletResponse response, Model model) {
		String articleid = request.getParameter("artid");
		ModelAndView modelAndView = new ModelAndView("fpoallotreplacetable");
		List<Collection> getitemdata = FPOrepost.getsingleartiddata(articleid);
		List<Collection> getsinglercdnoEAD = FPOrepost.recrdforwihtoutead(articleid);
		String getdestfpo = FPOrepost.getsinglerecd(articleid);
		model.addAttribute("getitemdata", getitemdata);
		model.addAttribute("getsinglercdnoEAD", getsinglercdnoEAD);
		String checkval = FPOrepost.returnval(articleid);
		List<String> getrecipentinfo = FPOrepost.getrecpinfrm(articleid);
		List<String> recpinfonotinEAD = FPOrepost.getntinEADrecpinfo(articleid);
		List<String> sendinfonotinEAD = FPOrepost.getntinEADsendinfo(articleid);
		List<String> getsenderinfo = FPOrepost.getsendinfrm(articleid);
		List<Collection> getstateNme = FPOrepost.getStateNme();
		List<Collection> artclests = FPOrepost.getarticlests(articleid);
		if (checkval == null || checkval == "") {
			int log = 0;
			model.addAttribute("checkval", log);
			model.addAttribute("recpinfonotinEAD", recpinfonotinEAD);
			model.addAttribute("sendinfonotinEAD", sendinfonotinEAD);
		} else {
			int log = 1;
			model.addAttribute("checkval", log);
			model.addAttribute("recipentinfo", getrecipentinfo);
			model.addAttribute("getsenderinfo", getsenderinfo);
		}
		model.addAttribute("artclests", artclests);
		if (getdestfpo == null || getdestfpo == "") {
			model.addAttribute("getstateNme", getstateNme);
		}
		return modelAndView;
	}

	// Get district from pdi_pincode

	@RequestMapping(value = "/getdistrict")
	public @ResponseBody List<String> getDistrict(HttpServletRequest request) {
		String stateNme = request.getParameter("state");
		List<String> district = FPOrepost.getDistNme(stateNme);
		return district;
	}

	@RequestMapping(value = "/getdestnationFpoState")
	public @ResponseBody List<Collection> destinationState(HttpServletRequest request) {
		String destSite = request.getParameter("destnFpo");
		String mapcdeCS = FPOrepost.getsitcde(destSite);
		List<Collection> getdestState = FPOrepost.getDesState(mapcdeCS);
		return getdestState;
	}

	// Save fpo-allot in table

	@RequestMapping(value = "/updtefpoallot", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_MAP_SITE savedata(@RequestBody FPO_MAP_SITE fpoallot, FpoMvmnt fpomvmnt,
			FpoQueryDecision fpoQueryDecision, HttpServletRequest request, HttpSession session)
			throws java.text.ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date dt = time.parse(date1);
		String Cinno = FPOrepost.getCinIdByItemId(fpoallot.getArticle_id());
		String reas = fpoallot.getReason();
		fpoQueryDecision.setITEM_ID(fpoallot.getArticle_id());
		fpoQueryDecision.setCIN_NO(Cinno);
		FPOrepost.updCusinFPOmain(fpoallot.getArticle_id(), fpoallot.getCus_site());
		FPOrepost.updCusinItmdet(fpoallot.getArticle_id(), fpoallot.getCus_site());
		// All these 3 below services to be executed when RMS enters into fpo
		// application
		fpoService.insertIntofpoMvmntDb(fpomvmnt, Cinno, fpomvmntrepo.getcindtmvmnt(Cinno), dt, 1L, "PAO", "C1",
				session, request);
		fpoCurQueService.addUserQue(Cinno, fpoallot.getArticle_id(), "C1", session, request);
		fpoService.insertIntoDecisionsDb(fpoQueryDecision, "P1", session, "C1", request);
		fpoallot.setAllot_dt(dt);
		fpoallot.setOfficer_id(request.getSession().getAttribute("offId").toString());
		fpoallot.setRole(request.getSession().getAttribute("role").toString());
		fpoallot.setReason(reas);
		fpoallotRepo.save(fpoallot);
		return fpoallot;

	}

	@PostMapping(value = "/checkexaminationraised")
	public @ResponseBody void checkExaminationRaised(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String cinNo = request.getParameter("cinNo");
		Long examRaised = FPO_ORDERrepost.getOrderCinNo(cinNo);
		Long examFindings = fpoExamFindingsRepost.findCountByCinNo(cinNo);
		jsonObj.put("examRaised", examRaised > 0 && examFindings == 0);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/saveexaminationaaf")
	public @ResponseBody void saveExaminationAAF(@ModelAttribute FPO_ORDER fpoOrder, HttpServletResponse response,
			HttpSession session, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		FPO_ORDERrepost.deleteOrder(fpoOrder.getCIN_NO());
		fpoOrderService.saveOrder(fpoOrder, session, request);
		jsonObj.put("examRaised", true);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	// Acl Functions

	@RequestMapping("/aclfunction")
	public ModelAndView aclfunction(Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("acl_functions");
		model.addAttribute("logoutimg", FPOrepost.getimagespath() + "logout_fpo.jpg");
		return modelAndView;
	}

	// Re-call
	@RequestMapping("/recallid")
	public ModelAndView recall(Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("re-call");
		return modelAndView;
	}

	// Get Article Stage

	@RequestMapping(value = "/getqrytypeStg")
	public @ResponseBody String getQrytype(HttpServletRequest request, HttpSession session)
			throws ParseException, java.text.ParseException {
		String itmID = request.getParameter("getartid");
		String cs = request.getSession().getAttribute("cuSite").toString();
		String getStage = FPOrepost.getqrytyp(itmID, cs);
		return getStage;
	}

	@RequestMapping(value = "/insertre-calldata", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public @ResponseBody FPO_RE_CALL insertRecall(@RequestBody FPO_RE_CALL fpoRecall, HttpServletRequest request,
			HttpSession session) throws ParseException, java.text.ParseException {
		String setrole = request.getParameter("setrole");
		System.out.println(setrole);
		fpoService.reCalldata(session, fpoRecall, request);
		fpoService.updateqrydesc(session, fpoRecall, request);
		fpoService.insertintoFpoMvmnt(session, fpoRecall, setrole, request);
		return fpoRecall;
	}

	@RequestMapping("/alertsitelevel")
	public ModelAndView alertsitelevel(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("alert/alertsitelevel");
		modelAndView.addObject("level", request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String todate = dateFormat.format(new Date());

		String fromdate = dateFormat.format(new Date());

		modelAndView.addObject("fromdate", fromdate);
		modelAndView.addObject("todate", todate);

		List<Fpo_Alert> fpo_Alert = fpo_AlertRepo
				.getActivefpo_Alert(request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
		modelAndView.addObject("fpo_Alert", fpo_Alert);

		List<SelectTag> CountryOfOrigin = reportService.getCountryOfOriginsAlert();
		modelAndView.addObject("CountryOfOrigin", CountryOfOrigin);

		List<SelectTag> mailclass = reportService.getMailClassAlert();
		modelAndView.addObject("mailclass", mailclass);

		List<SelectTag> MailCat = reportService.getMailCatAlert();
		modelAndView.addObject("MailCat", MailCat);

		/*
		 * List<SelectTag> OthersMailCat=fpo_AlertRepo.getOthersMailCatAlert();
		 * modelAndView.addObject("MailCat",MailCat);
		 */
		return modelAndView;
	}

	@RequestMapping(value = "/getalertdata", method = RequestMethod.POST)
	public ModelAndView getalertdata(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/sitealerttab");

		try {
			String alertname = request.getParameter("alertname");
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<Fpo_Alert> fpo_Alert = new ArrayList<Fpo_Alert>();
			if (alertname.equalsIgnoreCase("history")) {
				fpo_Alert = fpo_AlertRepo.getfpo_Alert(fromdate, todate,
						request.getSession().getAttribute("cuSite") == null ? null
								: request.getSession().getAttribute("cuSite").toString());
			} else {
				fpo_Alert = fpo_AlertRepo.getActivefpo_Alert(request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
			}
			models.addObject("fpo_Alert", fpo_Alert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/addgenericalertdetail", method = RequestMethod.POST)
	public ModelAndView addgenericalertdetail(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/sitealerttab");

		try {
			String country = request.getParameter("country");
			String mailclass = request.getParameter("mailclass");
			String itemcat = request.getParameter("itemcat");
			String alert = request.getParameter("alert");
			String reason = request.getParameter("reason");
			String active_since = request.getParameter("active_since");

			Fpo_Alert fpo_Alert = new Fpo_Alert();

			fpo_Alert.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert.setRole(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			fpo_Alert.setGen_dt(new Date());
			fpo_Alert.setAlert_type("Specific");
			fpo_Alert.setAlert(alert);
			fpo_Alert.setReason(reason);
			fpo_Alert.setAlert_level("Site");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Date result = df.parse(active_since);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			fpo_Alert.setActive_since(sdf.format(result));
			fpo_Alert.setStatus("Active");
			fpo_Alert.setCountry(country);
			fpo_Alert.setMail_class(mailclass);
			fpo_Alert.setItem_category(itemcat);

			List<String> site = fpo_AlertRepo.getsites(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			String sites = site.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			fpo_Alert.setCus_site(sites);
//			fpo_Alert.setCus_site(
//					request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpo_AlertRepo.save(fpo_Alert);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/addspecificalertdetail", method = RequestMethod.POST)
	public ModelAndView addspecificalertdetail(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/allsitealerttab");

		try {
			String alert = request.getParameter("alert");
			String reason = request.getParameter("reason");
			String active_since = request.getParameter("active_since");

			Fpo_Alert fpo_Alert = new Fpo_Alert();

			fpo_Alert.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert.setRole(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			fpo_Alert.setGen_dt(new Date());
			fpo_Alert.setAlert(alert);
			fpo_Alert.setAlert_type("Generic");

			List<String> site = fpo_AlertRepo.getsites(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			String sites = site.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			fpo_Alert.setCus_site(sites);
//			fpo_Alert.setCus_site(
//					request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			fpo_Alert.setReason(reason);
			fpo_Alert.setAlert_level("Site");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Date result = df.parse(active_since);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			fpo_Alert.setActive_since(sdf.format(result));
			fpo_Alert.setStatus("Active");

			fpo_AlertRepo.save(fpo_Alert);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/removealert", method = RequestMethod.POST)
	public ModelAndView removealert(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/sitealerttab");
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String removereason = request.getParameter("removereason");

			Fpo_Alert fpo_Alert = fpo_AlertRepo.getOne(id);
			fpo_Alert.setStatus("Removed");
			fpo_Alert.setRemoved_by(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert.setRemoved_dt(new Date());
			fpo_Alert.setRemove_reason(removereason);
			fpo_AlertRepo.save(fpo_Alert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	// Allocation of fpo site
	@RequestMapping("/alertalllevel")
	public ModelAndView alertalllevel(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("alert/allsitealerttab");
		modelAndView.addObject("level", "All India");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String todate = dateFormat.format(new Date());

		String fromdate = dateFormat.format(new Date());

		modelAndView.addObject("fromdate", fromdate);
		modelAndView.addObject("todate", todate);

		List<Fpo_Alert> fpo_Alert = fpo_AlertRepo.getActivefpo_AlertForAll();
		modelAndView.addObject("fpo_Alert", fpo_Alert);

		List<SelectTag> CountryOfOrigin = reportService.getCountryOfOriginsAlert();
		modelAndView.addObject("CountryOfOrigin", CountryOfOrigin);

		List<SelectTag> mailclass = reportService.getMailClassAlert();
		modelAndView.addObject("mailclass", mailclass);

		List<SelectTag> MailCat = reportService.getMailCatAlert();
		modelAndView.addObject("MailCat", MailCat);

		List<SelectTag> site = reportService.getSiteAlert();
		modelAndView.addObject("site", site);

		return modelAndView;
	}

	@RequestMapping(value = "/getallalertdata", method = RequestMethod.POST)
	public ModelAndView getallalertdata(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/sitealerttab");

		try {
			String alertname = request.getParameter("alertname");
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			List<Fpo_Alert> fpo_Alert = new ArrayList<Fpo_Alert>();
			if (alertname.equalsIgnoreCase("history")) {
				fpo_Alert = fpo_AlertRepo.getfpo_AlertForAll(fromdate, todate);
			} else {
				fpo_Alert = fpo_AlertRepo.getActivefpo_AlertForAll();
			}
			models.addObject("fpo_Alert", fpo_Alert);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/addallgeneralalertdetail", method = RequestMethod.POST)
	public ModelAndView addallgeneralalertdetail(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/allsitealerttab");

		try {
			String alert = request.getParameter("alert");
			String reason = request.getParameter("reason");
			String active_since = request.getParameter("active_since");
			String site = request.getParameter("site");

			Fpo_Alert fpo_Alert = new Fpo_Alert();

			fpo_Alert.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert.setRole(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			fpo_Alert.setGen_dt(new Date());
			fpo_Alert.setAlert(alert);
			fpo_Alert.setCus_site(site);
			fpo_Alert.setAlert_type("Generic");
			fpo_Alert.setReason(reason);
			fpo_Alert.setAlert_level("All India");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Date result = df.parse(active_since);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			fpo_Alert.setActive_since(sdf.format(result));
			fpo_Alert.setStatus("Active");

			fpo_AlertRepo.save(fpo_Alert);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/addallSpecificalertdetail", method = RequestMethod.POST)
	public ModelAndView addallSpecificalertdetail(HttpSession session, HttpServletRequest request) {
		ModelAndView models = new ModelAndView("alert/sitealerttab");

		try {
			String country = request.getParameter("country");
			String mailclass = request.getParameter("mailclass");
			String itemcat = request.getParameter("itemcat");
			String alert = request.getParameter("alert");
			String reason = request.getParameter("reason");
			String active_since = request.getParameter("active_since");
			String site = request.getParameter("site");

			Fpo_Alert fpo_Alert = new Fpo_Alert();

			fpo_Alert.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert.setRole(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			fpo_Alert.setGen_dt(new Date());
			fpo_Alert.setAlert_type("Specific");
			fpo_Alert.setAlert(alert);
			fpo_Alert.setReason(reason);
			fpo_Alert.setCus_site(site);
			fpo_Alert.setAlert_level("All India");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Date result = df.parse(active_since);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			fpo_Alert.setActive_since(sdf.format(result));
			fpo_Alert.setStatus("Active");
			fpo_Alert.setCountry(country);
			fpo_Alert.setMail_class(mailclass);
			fpo_Alert.setItem_category(itemcat);
			fpo_AlertRepo.save(fpo_Alert);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return models;

	}

	@RequestMapping(value = "/getalertdetails", method = RequestMethod.POST)
	public @ResponseBody void getalertdetails(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		JSONObject jsonObj = new JSONObject();
		boolean status = false;

		try {

			String id = request.getParameter("id").toString();

			List<String> MAIL_CLASS = reportService.getMAIL_CLASS(id);
			jsonObj.put("mailclass", MAIL_CLASS.toString());

			List<String> itemcat = reportService.getItemCategory(id);
			jsonObj.put("itemcat", itemcat.toString());

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}

	@RequestMapping(value = "/alertview", method = RequestMethod.POST)
	public @ResponseBody void alertview(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		JSONObject jsonObj = new JSONObject();
		boolean status = false;

		try {

			String itemid = request.getParameter("itemid");
			String cinno = request.getParameter("cinno");
			String level = request.getParameter("level");
			String alert = request.getParameter("alert");

			Fpo_Alert_View fpo_Alert_View = new Fpo_Alert_View();

			fpo_Alert_View.setCin_no(cinno);
			;
			fpo_Alert_View.setItem_id(itemid);
			fpo_Alert_View.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpo_Alert_View.setRole(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			fpo_Alert_View.setCus_site(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			fpo_Alert_View.setGen_dt(new Date());
			fpo_Alert_View.setProcess_lvl(level);
			fpo_Alert_View.setAlert_level(alert);

			fpoAlertViewRepo.save(fpo_Alert_View);

			status = true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}

	@RequestMapping("/dashboardpna")
	public ModelAndView dashboardPNA(HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("dashboard-PNA");
		String LastLoginetails = FPOrepost.getlogindetails(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		// model.addAttribute("LastLoginetails", LastLoginetails);
		request.getSession().setAttribute("LastLoginetails", LastLoginetails);
		if (request.getSession().getAttribute("role") == null) {
			fpoService.addLogintrackdetails(session, "login", request);
		}
		if (request.getParameter("role") != null) {
			String role = request.getParameter("role");
			request.getSession().setAttribute("data1", role);
			request.getSession().setAttribute("role", role);
		}
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		fpoLoginTrackRepo.updateRolelogin(
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());

		request.getSession().setAttribute("columns", "1");

		Long coultr = FPOrepost.getcoultr(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couems = FPOrepost.getcouems(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long coupar = FPOrepost.getcoupar(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couemp = FPOrepost.getcouemp(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		Long couass = 0L;
		if (request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString().equals("PAO"))
			couass = FPOrepost.gettotcouassView();
		Long couqry = FPOrepost.gettotcouqry();
		Long couexm = FPOrepost.gettotcouexm();
		Long couooc = FPOrepost.gettotcouooc();
		Long couedi = FPOrepost.gettotcouedi();

		Long curcoudet = FPOrepost.gettotdet(
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString(),
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString());

		Long totooccancel = FPOrepost.gettotooccancelall();
		Long curcouooccancel = 0L;

		// curcouooccancel =
		// FPOrepost.gettotooccancel(request.getSession().getAttribute("cuSite") == null
		// ? null : request.getSession().getAttribute("cuSite").toString(),
		// "PAO")+FPOrepost.gettotooccancel(request.getSession().getAttribute("cuSite")
		// == null ? null : request.getSession().getAttribute("cuSite").toString(),
		// "PAC");

		// Long curcouexm =
		// FPOrepost.gettotcouexm(request.getSession().getAttribute("cuSite") == null ?
		// null : request.getSession().getAttribute("cuSite").toString());

//		Long curcouooc = FPOrepost.gettotcouooc(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

//		Long curcouass = FPOrepost.gettotcouass(
//				request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

//		Long curcouqry = FPOrepost.gettotcouQuery(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

		model.addAttribute("curcouqry", couqry);

		model.addAttribute("curcouass", couass);
		model.addAttribute("curcouexm", couexm);
		model.addAttribute("curcouooc", couooc);
		model.addAttribute("curcoudet", curcoudet);
		model.addAttribute("totooccancel", totooccancel);

		model.addAttribute("curcouooccancel", totooccancel);
		
		
		
		
		/*
		 * model.addAttribute("curcouass",curcouass);
		 * model.addAttribute("curcouexm",curcouexm);
		 * model.addAttribute("curcouooc",curcouooc);
		 */

		// adding on jan 23rd
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String offId = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		Long cusiteCount = FPOrepost.getCountcusite(cuSite, offId);
		// Long totgift = FPOrepost.getcougift(cuSite, offId);
		// Long cougiftapr = FPOrepost.getcougiftapr(cuSite, offId);
		// Long cougiftacl = FPOrepost.getcougiftacl(cuSite, offId);
//		Long cousgapr = FPOrepost.getcousgapr(cuSite, offId);
		// Long cousgacl = FPOrepost.getcousgacl(cuSite, offId);
		// Long courgapr = FPOrepost.getcourgapr(cuSite, offId);
		// Long courgacl = FPOrepost.getcourgacl(cuSite, offId);
		// Long coucsapr = FPOrepost.getcoucsapr(cuSite, offId);
		// Long coucsacl = FPOrepost.getcoucsacl(cuSite, offId);
//		Long couothapr = FPOrepost.getcouothapr(cuSite, offId);
		// Long couothacl = FPOrepost.getcouothacl(cuSite, offId);
		// Long coudocapr = FPOrepost.getcoudocapr(cuSite, offId);
		// Long coudocacl = FPOrepost.getcoudocacl(cuSite, offId);
		// Long totsg = FPOrepost.getcousg(cuSite, offId);
		// Long totrg = FPOrepost.getcourg(cuSite, offId);
		// Long totcs = FPOrepost.getcoucs(cuSite, offId);
		// Long tototh = FPOrepost.getcouoth(cuSite, offId);
		// Long totdoc = FPOrepost.getcoudoc(cuSite, offId);
		/*
		 * Long coultr = FPOrepost.getcoultr(cuSite, offId); Long couems =
		 * FPOrepost.getcouems(cuSite, offId); Long coupar = FPOrepost.getcoupar(cuSite,
		 * offId); Long couemp = FPOrepost.getcouemp(cuSite, offId);
		 */
		Long aprCusite = FPOrepost.getcouAPR(cuSite, offId);
		Long coultrapr = FPOrepost.getcouAPRltr(cuSite, offId);
		Long couemsapr = FPOrepost.getcouAPRems(cuSite, offId);
		Long couparapr = FPOrepost.getcouAPRpar(cuSite, offId);
		Long couempapr = FPOrepost.getcouAPRemp(cuSite, offId);
		Long aclCusite = FPOrepost.getcouACL(cuSite, offId);
		// Long totacl = FPOrepost.gettotcouACL(cuSite, offId);
		Long coultracl = FPOrepost.getcouACLltr(cuSite, offId);
		Long couemsacl = FPOrepost.getcouACLems(cuSite, offId);
		Long couparacl = FPOrepost.getcouACLpar(cuSite, offId);
		Long couempacl = FPOrepost.getcouACLemp(cuSite, offId);

		TotalArticlesCount totalArticlesCount = fpoDashboardService.getTotalArticleCounts(cuSite, role);
		model.addAttribute("eadcount", totalArticlesCount.getEad());
		model.addAttribute("aaacount", totalArticlesCount.getAaa());
		model.addAttribute("aafcount", totalArticlesCount.getAaf());

		TotalIcCount totalIcCount = fpoDashboardService.getItemcategoryCount(cuSite, role);
		model.addAttribute("totgift", totalIcCount.getGift());
		model.addAttribute("totsg", totalIcCount.getSaleOfGoods());
		model.addAttribute("totcs", totalIcCount.getCommercialSample());
		model.addAttribute("totrg", totalIcCount.getReturnedGoods());
		model.addAttribute("totdoc", totalIcCount.getDocuments());
		model.addAttribute("tototh", totalIcCount.getOthers());

		TotalCountMailClass totalCountMailClass = fpoDashboardService.gettotcoultr(cuSite, role);
		model.addAttribute("totcoultr", totalCountMailClass.getTotcoultr());
		model.addAttribute("totcouems", totalCountMailClass.getTotcouems());
		model.addAttribute("totcoupar", totalCountMailClass.getTotcoupar());
		model.addAttribute("totcouemp", totalCountMailClass.getTotcouemp());

		TotalImportCounts totalImportCounts = fpoDashboardService.getTotalImportCount(cuSite, role);
		model.addAttribute("personalImports", totalImportCounts.getPersonalImports());
		model.addAttribute("detained", totalImportCounts.getDetained());
		model.addAttribute("commercialImports", totalImportCounts.getCommercialImports());

		List<PincodeCount> pincodeCount = fpoDashboardService.getWpPincode();
		for (PincodeCount pCount : pincodeCount) {
			if (pCount.getCus_site() == null) {
				pCount.setCus_site("Art ID's Pending for Mapping(W/O-Pincode)");
			}
		}
		model.addAttribute("pincodeCount", pincodeCount);

//		List<EADInboundArticles> eadInboundArticles = fpoDashboardService.getCountryWiseArticles(cuSite);
		List<Collection> eadInboundArticles = FPOrepost.getCountryWiseArticles(cuSite);
		model.addAttribute("eadInboundArticles", eadInboundArticles);

		List<CountrywiseOOCPending> countrywisePendingArticles = fpoDashboardService
				.getCountryWisePendingArticles(cuSite);

		model.addAttribute("countrywisePendingArticles", countrywisePendingArticles);

		OocPercentage oocPercentage = fpoDashboardService.getOOCPercentage();

		model.addAttribute("withDutyArticles", oocPercentage.getWithDutyArticles());
		// model.addAttribute("noDutyArticles", oocPercentage.getNoDutyArticles());
		model.addAttribute("noArticles", oocPercentage.getNoArticles());
		model.addAttribute("dutyPayable", oocPercentage.getDutyPayable());
		model.addAttribute("withDutyPercentage", oocPercentage.getWithDutyPercentage());
		// model.addAttribute("noDutyPercentage", oocPercentage.getNoDutyPercentage());
		model.addAttribute("lastMonth", oocPercentage.getLastMonth());
		model.addAttribute("noDutyConcession", oocPercentage.getNoDutyConcession());
		model.addAttribute("noDutyConcessionPercentage", oocPercentage.getNoDutyConcessionPercentage());
		model.addAttribute("totAmount", oocPercentage.getTotAmount());
		// model.addAttribute("noDutyPayable", oocPercentage.getNoDutyPayable());

		List<MailclassCountryWise> countryWiseMailClass = fpoDashboardService.getCountryWiseMailClass(cuSite);
		model.addAttribute("countryWiseMailClass", countryWiseMailClass);

		List<CtryWsPercentage> countryWisePercentage = fpoDashboardService.getCountryWisePercentage(cuSite, role);
		model.addAttribute("countryWisePercentage", countryWisePercentage);

		CtryWsMidPercentage ctryWsMidPercentage = fpoDashboardService.getCtryWsMidPercentage(cuSite, role);
		model.addAttribute("ctryWsMidPercentage", ctryWsMidPercentage);

		CtryWsInnerPercentage ctryWsInnerPercentage = fpoDashboardService.getCtryWsInnerPercentage(cuSite, role);
		model.addAttribute("ctryWsInnerPercentage", ctryWsInnerPercentage);

		List<CtryWsAssPercentage> countryWiseAssValPercentage = fpoDashboardService
				.getCountryWiseAssValPercentage(cuSite, role);
		model.addAttribute("countryWiseAssValPercentage", countryWiseAssValPercentage);

		CtryWsMidPercentage ctryWsMidAssValPercentage = fpoDashboardService.getCtryWsMidAssValPercentage(cuSite, role);
		model.addAttribute("ctryWsMidAssValPercentage", ctryWsMidAssValPercentage);

		CtryWsInnerPercentage ctryWsInnerAssValPercentage = fpoDashboardService.getCtryWsInnerAssValPercentage(cuSite,
				role);
		model.addAttribute("ctryWsInnerAssValPercentage", ctryWsInnerAssValPercentage);

		if (role.equalsIgnoreCase("PAL") && role != null) {
			AlertBean alerts = fpoDashboardService.getAlerts(role);
			model.addAttribute("palAlerts", alerts);
		} else if (role.equalsIgnoreCase("NAL") && role != null) {
			AlertBean alerts = fpoDashboardService.getAlerts(role);
			model.addAttribute("nalAlerts", alerts);
		}

		List<CtryWsAssCountPercentage> allCountryWiseAssValPercentage = fpoDashboardService
				.getAllCountryWiseAssValPercentage(cuSite, role);
		model.addAttribute("allCountryWiseAssValPercentage", allCountryWiseAssValPercentage);

		OOCDuty oocTotalDutyDetails = fpoDashboardService.getOOCTotalDutyDetails();
		model.addAttribute("oocTotalDutyDetails", oocTotalDutyDetails);

		BigDecimal totalActiveUsersAccrossIndia = fpoDashboardService.getTotalActiveUsersAccrossIndia(cuSite);
		model.addAttribute("totalActiveUsersAccrossIndia", totalActiveUsersAccrossIndia);

		BigDecimal totalActiveFpoSites = fpoDashboardService.getTotalActiveFpoSites();
		model.addAttribute("totalActiveFpoSites", totalActiveFpoSites);

		List<ActiveuserLSM> adminUsersCount = fpoDashboardService.getAdminUsersCount(cuSite);
		for (ActiveuserLSM result : adminUsersCount) {
			if (result.getRole().equalsIgnoreCase("PNA")) {
				model.addAttribute("PNA", result.getCounts());
			}
			if (result.getRole().equalsIgnoreCase("NRP")) {
				model.addAttribute("NRP", result.getCounts());
			}
			if (result.getRole().equalsIgnoreCase("ARP")) {
				model.addAttribute("ARP", result.getCounts());
			}
			if (result.getRole().equalsIgnoreCase("PLA")) {
				model.addAttribute("PLA", result.getCounts());
			}
		}

		// adding on jan 23rd

		/*
		 * String gettotalcuntQQ =
		 * FPOrepost.gettotalcuntQQ(request.getSession().getAttribute("cuSite") == null
		 * ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntEQ =
		 * FPOrepost.gettotalcuntEQ(request.getSession().getAttribute("cuSite") == null
		 * ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntOOCQ =
		 * FPOrepost.gettotalcuntOOCQ(request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString()); String
		 * gettotalcuntEDIQ =
		 * FPOrepost.gettotalcuntEDIQ(request.getSession().getAttribute("cuSite") ==
		 * null ? null : request.getSession().getAttribute("cuSite").toString());
		 */

		model.addAttribute("couass", couass);
		model.addAttribute("couexm", couexm);
		model.addAttribute("couooc", couooc);
		model.addAttribute("couqry", couqry);
		model.addAttribute("couedi", couedi);

		model.addAttribute("coultr", coultr);
		model.addAttribute("couems", couems);
		model.addAttribute("coupar", coupar);
		model.addAttribute("couemp", couemp);

		if (request.getSession().getAttribute("role").equals("NAL")) {
			modelAndView = new ModelAndView("alertdash");
		}

		return modelAndView;
	}

	@Scheduled(cron = "${cronjob.sendmsg}")
	public void sendmailmesg() throws Exception {

		int send = 0;
		List<FPO_MAIL> maillist = MailRepo.findAll();
		// String host = "smtp.icegate.gov.in";

		String host = maillist.get(0).getHost_Name();
		System.out.println("cms here in sending receipt of messages through mail");

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", maillist.get(0).getPort().toString());

		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.gmail.com")) {
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");
		}

		Session session;
		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.icegate.gov.in"))
			session = Session.getDefaultInstance(properties);
		else {
			session = Session.getInstance(properties, new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(maillist.get(0).getUser_Name(), maillist.get(0).getPwd());

				}

			});
		}

//	  List<Collection> mailmesg = FPOrepost.getmailmesgsend();
//	  System.out.println("cms here after fetching");
//	  System.out.println("mailmesg value is " + mailmesg);
		// System.out.println("mailmesg value is " + mailmesg.get(0));
		// System.out.println("mailmesg value is " + mailmesg.get(1));
		// if (FPOrepost.getmailmesgsend().get(0).size() == 0 )
		// System.out.println("tomailid length is "+0);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Recipient's email ID needs to be mentioned.

			// if (to != null)
			// {
			String to = "sasi@nic.in";
			String tomailid = "a.manimaran@gov.in";
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			String cc = "unnikrishnan.b@nic.in";
			// if (tomailid !=null)
			// { if (pattern.matcher(tomailid).matches())
			// message.addRecipient(Message.RecipientType.CC, new
			// InternetAddress(tomailid));}
			System.out.println("first");
			send = 1;
			// }

			// else if (tomailid != null) {
			// if (pattern.matcher(tomailid).matches() )
			// { //later this line is to be removed when it goes for launch....
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomailid));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("sreenivasm.c029001@gov.in"));
			// System.out.println("second");
			// send =1 ;}}
			List<String> values = new ArrayList<String>();
			List<Collection> cusitmmesg = FPOrepost.getcusitmmesg();
			List<Collection> cusrspmesg = FPOrepost.getcusrspmesg();
			List<Collection> cusrspmesg2 = FPOrepost.getcusrspmesg2();
			List<Collection> cusrspmesg3 = FPOrepost.getcusrspmesg3();			
			List<Collection> premesg = FPOrepost.getpredesmesg();
			List<Collection> arr1mesg = FPOrepost.getarrooemesg();
			List<Collection> arr2mesg = FPOrepost.getarrfpomesg();
			List<Collection> delimesg = FPOrepost.getdelimesg();
			int i = 1;
			String constr = "<TABLE align=center border=1 width=100%>";
			constr = constr
					+ "<TR border=1><td border=1 align=center>SLNO</td><td border=1 align=center> MESSAGE TYPE </td><td border=1 align=center> FROM DATE </td><td border=1 align=center> TO DATE </td> <td border=1 align=center> LAST EXECUTED DATE</td> <td border=1 align=center>NO. OF RECORDS FETCHED / SENT</td></tr>";
			for (Object column : cusitmmesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			for (Object column : cusrspmesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}
			
			for (Object column : cusrspmesg2) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}
			
			for (Object column : cusrspmesg3) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			for (Object column : premesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			for (Object column : arr1mesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			for (Object column : arr2mesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			for (Object column : delimesg) {
				Object[] stringArray = (Object[]) column;
				values.add(String.valueOf(i++));
				values.add(stringArray[0] == null ? "-NIL-" : stringArray[0].toString());
				values.add(stringArray[1] == null ? "-NIL-" : stringArray[1].toString());
				values.add(stringArray[2] == null ? "-NIL-" : stringArray[2].toString());
				values.add(stringArray[4] == null ? "-NIL-" : stringArray[4].toString());
				values.add(stringArray[3] == null ? "-NIL-" : stringArray[3].toString());
			}

			i = 0;

			constr = constr + "<tbody>";
			for (String header : values) {
				if (i == 0) {
					constr = constr + "<TR border=1><TD border=1 align=center >" + header + "</td>";
				} else
					constr = constr + "<tD border=1 align=center>" + header + "</td>";
				if (i == 5) {
					constr = constr + "</tr>";
					i = 0;
				} else
					i = i + 1;
			}
			constr = constr + "</tbody></table>";

			System.out.println("constr=" + constr);
			System.out.println("send is" + send);

			if (send == 1) {
				// Sender's email ID needs to be mentioned
				String from;
				if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.icegate.gov.in"))
					from = "noreply@icegate.gov.in";
				else
					from = "sasikumaresh@gmail.com";

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				String servername = "";
				if (InetAddress.getLocalHost().getHostName().equals("DDVDAPAP02.cbic.gov.in"))
					servername = "DEV";
				else if (InetAddress.getLocalHost().getHostName().equals("DDVRFPAP01.cbic.gov.in")
						|| InetAddress.getLocalHost().getHostName().equals("DDVRFPAP02.cbic.gov.in"))
					servername = "UAT";
				// else
				// servername = "PROD";
				log.info("server name " + InetAddress.getLocalHost().getHostName());
				// Set To: header field of the header.
				// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject(servername + " - FPOIA - ICES - API Batch Job Monitoring - reg.");

				Multipart multipart = new MimeMultipart();

				MimeBodyPart attachmentPart = new MimeBodyPart();

				MimeBodyPart textPart = new MimeBodyPart();

				// textPart.setText("This is just for testing purpose. Please ignore");
				/*
				 * String constr =
				 * "<TABLE align=center style=border:1px black solid; width=75%><tbody><TR border=0><tD border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font size=2>"
				 * ; constr = constr +
				 * "<p align=justify><i>Please refer to the above-mentioned import article addressed to you and it is expected to arrive at the Foreign Post Office (FPO) and is being taken up for assessment or have been received at the FPO and is being examined by the Indian Customs. In this regard, you are requested to clarify on the queries raised, to  process the article.</i></p>"
				 * ; constr = constr +
				 * "<p align=justify><i>The  details of Customs query,  time limit for reply, the documents to be attached if any etc., may be referred  in the Document Call letter (D-Call letter) sent to you via speed post or in the digital copy of the D-Call letter attached herewith, which is password protected.</i></p>"
				 * ; constr = constr +
				 * "<p align=justify><i>Password to open the D-Call letter  is the last five characters of the subject Article ID.</i></p>"
				 * ; // constr=constr +
				 * "<p align=justify><b><u><i>Instructions for replying over email to Customs query/D-Call letter:</i></u></b></p></font>"
				 * ; constr=constr +
				 * "<p align=justify><b><u><i><font color=blue>Please visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.</font></i></u></b></p> "
				 * ; // constr=constr +
				 * "<br>2.  Subject of the email should contain the following format same as in the mail received by you:<font color=blue> Foreign Post Import Article:   "
				 * ; /* constr=constr + itemid; constr=constr + " - Query from Customs -";
				 * constr=constr + request.getSession().getAttribute("cuSite") == null ? null :
				 * request.getSession().getAttribute("cuSite").toString(); constr=constr +
				 * "</font>"; constr=constr +
				 * "<br>3.  Body of the reply email should not exceed 1000 characters or 200 words. If reply exceeds this limit, the reply shall be made  in a PDF as specified below, with a document name as Reply  (Reply.pdf) and sent as attachment."
				 * ; constr=constr +
				 * "<br>4.	Provide your contact details like mobile number, email id etc while replying."
				 * ; constr=constr + "<br>5.  <b>Specification of the attachments:</b>";
				 * constr=constr +
				 * "<br>&nbsp;&nbsp;&nbsp;&nbsp;a. The attached document should be in PDF format, in black and white with resolution no less than 200 dpi."
				 * ; constr=constr +
				 * "<br>&nbsp;&nbsp;&nbsp;&nbsp;b. The attached PDF document should be of less than or equal to 1 MB size. If it exceeds, the same can be divided and uploaded as two documents."
				 * ; constr=constr +
				 * "<br>&nbsp;&nbsp;&nbsp;&nbsp;c. The name of all attached PDFs shall be mentioned while sending the reply (For Ex: KYC aadhar.pdf; Invoice.pdf, Certificate.pdf, payment statement.pdf etc.,)"
				 * ; constr=constr +
				 * "<br>6.  <b>Please check the above specification before sending reply and avoid sending multiple emails for the same articles.</b></p>"
				 * ;
				 */

				// constr=constr + "<p><b>Regards,</b><br>";

				// constr=constr + "<b>FPO - Indian Customs EDI,<br> CBIC</b></p>";
				// constr=constr + "</td><td border=0
				// width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
				// constr=constr + "<tr><td colspan=3><br><br><font size=1> * Visit
				// https://enquiry.icegate.gov.in/ to track Postal Import Article with Customs
				// under 'Document Status' </td></tr>";
				// constr=constr + "<TR><td colspan=3 align=center><font weight=50><hr><p
				// align=center style=font-weight:50><font size=1>The above email is from an
				// email address that can't receive emails.</p></font></td></tr>";;
				// constr=constr + "<br>Don't reply to this email. Please refer above on the
				// instructions to reply over email.</p></font></td></tr>";
				// constr=constr + "</tbody></table>";
				textPart.setContent(constr, "text/html");

				multipart.addBodyPart(textPart);
				// multipart.addBodyPart(attachmentPart);

				message.setContent(multipart);

				System.out.println("sending...");
				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");
				java.util.Date curdt = new java.util.Date();

				int count = 1;
				FPOrepost.updcusitmmail(curdt);
				FPOrepost.updcusrspmail(curdt);
				FPOrepost.updpredesmail(curdt);
				FPOrepost.updarrooemail(curdt);
				FPOrepost.updarrfpomail(curdt);
				FPOrepost.upddelimail(curdt);
			}
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	@RequestMapping(value = "/submitcomplnts", method = RequestMethod.POST)
	public ModelAndView getAssmaxval(@ModelAttribute RaiseComplaints risecom, HttpServletRequest request, Model model,
			HttpSession session) throws Exception {

		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/dash");
		String comments = request.getParameter("comments");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String filename;
		if (request.getParameter("filename") == null)
			filename = "";
		else
			filename = request.getParameter("filename");
		String srole = null;
		if (request.getParameter("s-role") != null) {
			srole = request.getParameter("s-role");
		} else {
			srole = request.getSession().getAttribute("role").toString();

		}
		if (srole.equalsIgnoreCase("select")) {
			srole = request.getSession().getAttribute("role").toString();
		}
		String modname = null;
		if (request.getParameter("modname") != null) {
			modname = request.getParameter("modname");
		}
		System.out.println(modname);
		String empId = request.getParameter("empId");
		String offid = request.getSession().getAttribute("offId").toString();

		Date currentdate = new Date();
		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time1.format(currentdate);
		Date date = null;
		try {
			date = time1.parse(date1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date + "date is this");
		System.out.println(srole);
		System.out.println(empId);
		System.out.println(offid);
		System.out.println(comments);
		System.out.println(name);
		System.out.println(email);
		System.out.println(mobile);
		System.out.println("filename is " + filename);
		String qryreplypath = ospathrepo.getRiseCompDocPath();
		File destination = new File(qryreplypath + filename);

		RaiseComplaints risComplts = new RaiseComplaints();

		risComplts.setCOMPL_TXT(comments);
		risComplts.setMOD_NAME(modname);
		risComplts.setEMAIL_ID(email);
		risComplts.setMOBILE_NO(mobile);
		risComplts.setROLE_NAME(srole);
		risComplts.setOFF_ID(offid);
		risComplts.setRAISE_DT(date);
		if (filename != null)
			risComplts.setFILE_NAME(filename);

		if (filename != null) //
			risecom.getFilename().transferTo(destination);

		risecomplantrepo.save(risComplts);
		String attmnt;

		attmnt = qryreplypath + filename;
		System.out.println(attmnt);
		fpoService.sendingEmail(comments, attmnt);

		return modelAndView;
	}

	@RequestMapping(value = "/submitcomplntsnull", method = RequestMethod.POST)
	public ModelAndView getAssmaxvalnull(HttpServletRequest request, Model model, HttpSession session)
			throws Exception {

		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/dash");
		String comments = request.getParameter("comments");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String filename;
		if (request.getParameter("filename") == null)
			filename = "";
		else
			filename = request.getParameter("filename");
		String srole = null;
		if (request.getParameter("s-role") != null) {
			srole = request.getParameter("s-role");
		} else {
			srole = request.getSession().getAttribute("role").toString();

		}
		if (srole.equalsIgnoreCase("select")) {
			srole = request.getSession().getAttribute("role").toString();
		}
		String modname = null;
		if (request.getParameter("modname") != null) {
			modname = request.getParameter("modname");
		}
		System.out.println(modname);
		String empId = request.getParameter("empId");
		String offid = request.getSession().getAttribute("offId").toString();

		Date currentdate = new Date();
		SimpleDateFormat time1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time1.format(currentdate);
		Date date = null;
		try {
			date = time1.parse(date1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date + "date is this");
		System.out.println(srole);
		System.out.println(empId);
		System.out.println(offid);
		System.out.println(comments);
		System.out.println(name);
		System.out.println(email);
		System.out.println(mobile);
		System.out.println("filename is " + filename);
		String qryreplypath = ospathrepo.getRiseCompDocPath();
		/*
		 * File destination = new File(qryreplypath + filename);
		 */

		RaiseComplaints risComplts = new RaiseComplaints();

		risComplts.setCOMPL_TXT(comments);
		risComplts.setMOD_NAME(modname);
		risComplts.setEMAIL_ID(email);
		risComplts.setMOBILE_NO(mobile);
		risComplts.setROLE_NAME(srole);
		risComplts.setOFF_ID(offid);
		risComplts.setRAISE_DT(date);
//			  if (filename!=null)
//			    risComplts.setFILE_NAME(filename);
//			  risComplts.setId(1l);
		// if (filename!=null)
		// risecom.getFilename().transferTo(destination);

		risComplts.setFILE_NAME("");
		risecomplantrepo.save(risComplts);
		String attmnt = null;

		System.out.println(attmnt);
		fpoService.sendingEmail(comments, attmnt);

		return modelAndView;
	}

}
