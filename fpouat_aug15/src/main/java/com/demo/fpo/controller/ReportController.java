package com.demo.fpo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.bean.MisFPOReceptacleProcess;
import com.demo.fpo.bean.MisOOEReceptacleProcess;
import com.demo.fpo.bean.MisReportArtcDetained;
import com.demo.fpo.bean.MisReportArticleStatus;
import com.demo.fpo.bean.MisReportByItemCategory;
import com.demo.fpo.bean.MisReportByMailClass;
import com.demo.fpo.bean.MisReportColumns;
import com.demo.fpo.bean.MisReportCommercialQueue;
import com.demo.fpo.bean.MisReportDCall;
import com.demo.fpo.bean.MisReportEAD;
import com.demo.fpo.bean.MisReportFPOArrival;
import com.demo.fpo.bean.MisReportFPOReceptacleProcess;
import com.demo.fpo.bean.MisReportOOCCancel;
import com.demo.fpo.bean.MisReportOOEReceptacleProcess;
import com.demo.fpo.bean.MisReportOfficersArticles;
import com.demo.fpo.bean.MisReportOocGivenCountries;
import com.demo.fpo.bean.MisReportOocGivenCountriesNotfWise;
import com.demo.fpo.bean.MisReportOocGivenCountriesdelstatus;
import com.demo.fpo.bean.MisReportOocGivenDeliStatus;
import com.demo.fpo.bean.MisReportOocGivenDeliStatusDetails;
import com.demo.fpo.bean.MisReportOocPending;
import com.demo.fpo.bean.MisReportQryRaised;
import com.demo.fpo.bean.MisReportQueryReplyStatus;
import com.demo.fpo.bean.MisReportWithoutPincode;
import com.demo.fpo.bean.Misreportcommercialunderprocess;
import com.demo.fpo.bean.ReportDateBean;
import com.demo.fpo.model.DeliveryArticlesColumns;
import com.demo.fpo.model.DeliveryArticlesPendingColumns;
import com.demo.fpo.model.DetainedColumns;
import com.demo.fpo.model.DetainedParcelClearence;
import com.demo.fpo.model.FPO_MANUALCOMMERCIALCOLUMNS;
import com.demo.fpo.model.ImportedParcelClearence;
import com.demo.fpo.model.ItemAmountDetails;
import com.demo.fpo.model.ItemDetails;
import com.demo.fpo.model.MISREP_SEL;
import com.demo.fpo.model.MonthNames;
import com.demo.fpo.model.OpeningReportColumns;
import com.demo.fpo.model.PostalediColumns;
import com.demo.fpo.model.ReportColumns;
import com.demo.fpo.model.SelectTag;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.service.FpoService;
import com.demo.fpo.service.ReportService;
import com.google.gson.Gson;

@Controller
public class ReportController {

	@Resource
	private ReportService reportService;

	@Autowired
	FPO_ITEM_DETrepost FPO_ITEMrepost;
	
	@Autowired
	FPO_MAINrepost FPOrepost;

	@Autowired
	FpoService fpoService;
	
	//santhosh
	@Autowired
	MISREP_SEL_repo misrepselrepo;
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView report( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/misreport");
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			String filter = "job_date";
			String fromdate = reportService.getFromDateForReport(filter,  session,request);
			LocalDate now = LocalDate.now();
			LocalDate earlier = now.minusMonths(1);
			
			fromdate = earlier.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			System.out.println(request.getSession().getAttribute("cuSite"));
			
			String todate = dateFormat.format(new Date());
			
			String columns="";
			
			if(request.getSession().getAttribute("columns") != null) {
				columns=request.getSession().getAttribute("columns").toString();
			}
			
			
	           ArrayList<String> selectedList = new ArrayList<String>(
	               Arrays.asList(columns.split(",")));
			
//			if(request.getSession().getAttribute("fromdate") != null && request.getSession().getAttribute("todate") != null) {
//				fromdate = request.getSession().getAttribute("fromdate").toString();
//				todate = request.getSession().getAttribute("todate").toString();
//			}else {
//				 request.getSession().setAttribute("fromdate", fromdate);
//				 request.getSession().setAttribute("todate", todate);
//			}
			
			List<ReportColumns> reportcolumns=reportService.getReportColumns(fromdate,todate,"",selectedList,  session,request);
			models.addObject("reportcolumns",reportcolumns);
			
			List<String> CountryOfOrigin=reportService.getCountryOfOrigins();
			models.addObject("CountryOfOrigin",CountryOfOrigin);
			List<String> state=reportService.getStates();
			models.addObject("state",state);
			List<String> Pincode=reportService.getPincodes();
			models.addObject("Pincode",Pincode);
			List<String> EADDecision=reportService.getEADDecisions();
			models.addObject("EADDecision",EADDecision);
			

			List<String> mailclass=reportService.getMailClass();
			models.addObject("mailclass",mailclass);
			

			List<String> MailCat=reportService.getMailCat();
			models.addObject("MailCat",MailCat);

			List<String> cth=reportService.getCTH();
			models.addObject("cth",cth);

			List<String> ItemCounts=reportService.getItemCounts();
			models.addObject("ItemCounts",ItemCounts);
			
//			List<ReportCounts> reportcounts=reportService.getReportCounts(fromdate,todate,filter,"custitm");
//			models.addObject("reportcounts",reportcounts.get(0));
			
			models.addObject("fromdate",fromdate);
			models.addObject("todate",todate);
			
			
			List<String> othersTwoDigi = reportService.getOtherTwoDigi();
			List<String> othersfourDigi = reportService.getOtherFourDigi(othersTwoDigi.get(0));
			models.addObject("othersTwoDigi",othersTwoDigi);
			models.addObject("othersfourDigi",othersfourDigi);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	////created by santhosh
	@RequestMapping(value="/savereportdetails")
	public @ResponseBody void savereportdetails( Model model,HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		MISREP_SEL misrep=new MISREP_SEL();
		
		String reportcategory=request.getParameter("category");
		misrep.setReportCategory(reportcategory);
		String reportnumber=request.getParameter("number");
		String reporttype=request.getParameter("type");
		String reportrole=request.getParameter("role");
		String reportname=request.getParameter("name");
		String reportsite=request.getParameter("site");
		String reportrolename=request.getParameter("rolename");
	
		Date currentdate = new Date();
		SimpleDateFormat time1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date1 = time1.format(currentdate);
		Date parsedDate = null;
		try {
			    parsedDate = time1.parse(date1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		misrep.setReportnumber(reportnumber);
		misrep.setReportname(reportname);
		misrep.setDateandtime(parsedDate);
		misrep.setType(reporttype);
		misrep.setOof_id(reportrole);
		misrep.setCus_site(reportsite);
		misrep.setRole(reportrolename);
		misrepselrepo.save(misrep);
	
	}
	
	
	@RequestMapping(value = "/reportdatechange", method = RequestMethod.POST)
	public ModelAndView reportdatechange(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/reportdatechange");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			String country = request.getParameter("country").toString();
			String state = request.getParameter("state").toString();			
			String pincode = request.getParameter("pincode").toString();		
			String pincode1 = request.getParameter("pincode1").toString();
			String ead = request.getParameter("ead").toString();			
			String mailclass = request.getParameter("mailclass").toString();
			String mailcategory = request.getParameter("mailcategory").toString();
			String totalduty = request.getParameter("totalduty").toString();
			String amount = request.getParameter("amount").toString();			
			String fine = request.getParameter("fine").toString();
			String penalty = request.getParameter("penalty").toString();			
			String cth = request.getParameter("cth").toString();
			String itemvalue = request.getParameter("itemvalue").toString();
			String itemno = request.getParameter("itemno").toString();
			String query = request.getParameter("query").toString();			
			String reply = request.getParameter("reply").toString();
			String arrival = request.getParameter("arrival").toString();			
			String destination = request.getParameter("destination").toString();
			String examination = request.getParameter("examination").toString();
			String ooc = request.getParameter("ooc").toString();			
			String ediqueue = request.getParameter("ediqueue").toString();
			String delivery = request.getParameter("delivery").toString();

			String YN = request.getParameter("YN").toString();
			String Doc = request.getParameter("Doc").toString();
			String Columns = request.getParameter("Columns").toString();
			String detain = request.getParameter("detain").toString();
			String weight = request.getParameter("weight").toString();
			

			String sendername = request.getParameter("sendername").toString();
			String recname = request.getParameter("recname").toString();
			String itemdesc = request.getParameter("itemdesc").toString();
			

			String arrdt = request.getParameter("arrdt").toString();
			String examdt = request.getParameter("examdt").toString();
			String deldt = request.getParameter("deldt").toString();
			

			String cn23 = request.getParameter("cn23").toString();
			String revcn23 = request.getParameter("revcn23").toString();
			String examfind = request.getParameter("examfind").toString();
			String assvalue = request.getParameter("assvalue").toString();
			

			String delistatus = request.getParameter("delistatus").toString();
			
			 request.getSession().setAttribute("columns", Columns);
			
			/*
			 * if(Columns.equals("[]") || Columns.isEmpty()) { Columns="0"; }
			 */
			// split string by no space
	        String[] strSplit = Columns.split(",");
	  
	        // Now convert string into ArrayList
	        ArrayList<String> selectedList = new ArrayList<String>(
	            Arrays.asList(strSplit));
			
//			 request.getSession().setAttribute("fromdate", fromdate);
//			 request.getSession().setAttribute("todate", todate);
			
	        
	        
	        
			/*
			 * String WhereClasuses=reportService.getWhereClauses(country,state,pincode,ead,
			 * mailclass,mailcategory,totalduty,amount
			 * ,fine,penalty,cth,itemvalue,itemno,query,reply,arrival,destination,
			 * examination,ooc,ediqueue,delivery,YN,Doc,selectedList,pincode1,detain,weight,
			 * examfind,cn23
			 * ,sendername,recname,itemdesc,arrdt,examdt,deldt,delistatus,revcn23,assvalue);
			 * 
			 * List<ReportColumns>
			 * reportcolumns=reportService.getReportColumns(fromdate,todate,WhereClasuses,
			 * selectedList, session,request);
			 * models.addObject("reportcolumns",reportcolumns);
			 */

	        List<ReportColumns> reportcolumns=reportService.getWhereClauses(country,state,pincode,ead,mailclass,mailcategory,totalduty,amount,fine,penalty,cth,itemvalue,itemno,query,reply,arrival,destination,examination,ooc,ediqueue,delivery,YN,Doc,selectedList,pincode1,detain,weight,examfind,cn23
					,sendername,recname,itemdesc,arrdt,examdt,deldt,delistatus,revcn23,assvalue,fromdate,todate, session,request);
		
	        
	        models.addObject("reportcolumns",reportcolumns);
	        
	        
	        
	        
			
//			List<ReportCounts> reportcounts=reportService.getReportCounts(report.getFromdate(),report.getTodate(),report.getFilter(),report.getTab());
//			models.addObject("reportcounts",reportcounts.get(0));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	 @RequestMapping(value = "/chkiec")
	 public @ResponseBody String processcomrepo(HttpServletRequest request, HttpServletResponse responce) {
	
		 String  strval= "";
		 String iecc = request.getParameter("iec");
		 	int cou=FPOrepost.getcouiec(iecc);
		 	if(cou>0) {
		 		strval="success";
		 	}
		return strval;
		
	 }
	 
	 @RequestMapping(value = "/chkgst")
	 public @ResponseBody String processcomgst(HttpServletRequest request, HttpServletResponse responce) {
		
		 String  strval= "";
		 String gst = request.getParameter("gst");
		 	int cou=FPOrepost.getcougstin(gst);
		 	if(cou>0) {
		 		strval="success";
		 	}
		return strval;
		
	 }
	 
	 
	 @RequestMapping(value = "/chkad")
		public @ResponseBody String processcomadc(HttpServletRequest request, HttpServletResponse response) {
		
			String adcod = "";
			String adc = request.getParameter("adcod");
			int count = FPOrepost.getadcode1(adc);
			if(count>0) {
				adcod = "success";
			}
			return adcod;
		}

	
	@RequestMapping(value = "/report1", method = RequestMethod.GET)
	public ModelAndView report1( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/misreportnew");
		try {
			

			String financialdateandyear="01/04/";
			int year = Calendar.getInstance().get(Calendar.YEAR);
			financialdateandyear=financialdateandyear.concat(Integer.toString(year));
			
			 List<Integer> yearlist = new LinkedList<Integer>();
			 int cur = 2020;
			 while (cur <= year) {
				 yearlist.add(cur++);
			    }
			 
			// models.addObject("yearlist",yearlist);
			
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.MONTH, -1);
			
			int res =cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String enddate=Integer.toString(res).concat("/"); 
			DecimalFormat dmFormat= new DecimalFormat("00");
			String month=dmFormat.format(Double.valueOf(localDate.getMonthValue()));
			
			Long Mnth=(Long.parseLong(month)-1l); 
			 month=new DecimalFormat("00").format(Double.valueOf(Mnth.toString()));

			 if(Mnth<4) {
				 yearlist.remove(Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
			 }
			 models.addObject("yearlist",yearlist);
			 
			 if(Mnth==00L) {
				 month="12";
				 year= Calendar.getInstance().get(Calendar.YEAR) -1;
			 }
			 
			 
			models.addObject("month",month);
			models.addObject("year",year);
			
			List<MonthNames> monthlist = reportService.getMonthSelect(month,year);
			models.addObject("monthlist",monthlist);
			enddate=enddate.concat(month).concat("/");
			
			
			

			
			if(Long.parseLong(month)<4) {
				year=year+1;
			}

			models.addObject("nextyear",year);
			String startdate="01/".concat(month).concat("/").concat(Integer.toString(year));
			
			enddate=enddate.concat(Integer.toString(year));
			
			

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			String filter = "job_date";
			String fromdate = reportService.getFromDateForReport(filter,  session,request);
			
			String todate = dateFormat.format(new Date());
			
			List<ImportedParcelClearence> ImportedParcelClearencecolumns=reportService.getImportedParcelClearencecolumnsColumns(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
			models.addObject("ImportedParcelClearencecolumns",ImportedParcelClearencecolumns);
			
			
			
//			List<OpeningReportColumns> ReportColumns=reportService.getReportColumnsForOpening(startdate,financialdateandyear,enddate,month,Integer.toString(year));
//			models.addObject("ReportColumns",ReportColumns);
//
//			List<OpeningReportColumns> ReceiptForMonth=reportService.getReceiptForMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year));
//			models.addObject("ReceiptForMonth",ReceiptForMonth);
			
			models.addObject("filter",filter);
			models.addObject("fromdate",fromdate);
			models.addObject("todate",todate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/reportmonthchange", method = RequestMethod.POST)
	public ModelAndView reportmonthchange(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/reportmonthchange");

		try {

			String financialdateandyear="01/04/";
			//int year = Calendar.getInstance().get(Calendar.YEAR);
			int year=Integer.parseInt(report.getYear());
			financialdateandyear=financialdateandyear.concat(Integer.toString(year));
			
			if(Long.parseLong(report.getMonth())<4) {
				year=year+1;
			}
			
			String date = "01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			convertedDate = convertedDate.withDayOfMonth(
			                                convertedDate.getMonth().length(convertedDate.isLeapYear()));
			String enddate = convertedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			

			String startdate="01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			
			List<ImportedParcelClearence> ImportedParcelClearencecolumns=reportService.getImportedParcelClearencecolumnsColumns(startdate,financialdateandyear,enddate,report.getMonth(),Integer.toString(year),  session,request);
			models.addObject("ImportedParcelClearencecolumns",ImportedParcelClearencecolumns);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/importedparcel", method = RequestMethod.POST)
	public ModelAndView importedparcel(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/importedparcel");

		try {
			/*
			 *  request.getSession().setAttribute("fromdate", report.getFromdate());
			 *  request.getSession().setAttribute("todate", report.getTodate());
			 *  request.getSession().setAttribute("filter", report.getFilter());
			 */
			
			String financialdateandyear="01/04/";
			//int year = Calendar.getInstance().get(Calendar.YEAR);
			int year=Integer.parseInt(report.getYear());
			financialdateandyear=financialdateandyear.concat(Integer.toString(year));
			
			if(Long.parseLong(report.getMonth())<4) {
				year=year+1;
			}
			
			String date = "01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			convertedDate = convertedDate.withDayOfMonth(
			                                convertedDate.getMonth().length(convertedDate.isLeapYear()));
			String enddate = convertedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			String startdate="01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			
			List<ImportedParcelClearence> ImportedParcelClearencecolumns=reportService.getImportedParcelClearencecolumnsColumns(startdate,financialdateandyear,enddate,report.getMonth(),Integer.toString(year),  session,request);
			models.addObject("ImportedParcelClearencecolumns",ImportedParcelClearencecolumns);
			
			
			
			List<OpeningReportColumns> ReportColumns=reportService.getReportColumnsForOpening(startdate,financialdateandyear,enddate,report.getMonth(),Integer.toString(year),  session,request);
			models.addObject("ReportColumns",ReportColumns);

			List<OpeningReportColumns> ReceiptForMonth=reportService.getReceiptForMonth(startdate,financialdateandyear,enddate,report.getMonth(),Integer.toString(year),  session,request);
			models.addObject("ReceiptForMonth",ReceiptForMonth);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/detainedparcel", method = RequestMethod.POST)
	public ModelAndView detainedparcel(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/detainedparcel");

		try {
			/*
			 *  request.getSession().setAttribute("fromdate", report.getFromdate());
			 *  request.getSession().setAttribute("todate", report.getTodate());
			 *  request.getSession().setAttribute("filter", report.getFilter());
			 */
			
			String financialdateandyear="01/04/";
			//int year = Calendar.getInstance().get(Calendar.YEAR);
			int year=Integer.parseInt(report.getYear());
			financialdateandyear=financialdateandyear.concat(Integer.toString(year));
			
			if(Long.parseLong(report.getMonth())<4) {
				year=year+1;
			}
			
			String date = "01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			convertedDate = convertedDate.withDayOfMonth(
			                                convertedDate.getMonth().length(convertedDate.isLeapYear()));
			String enddate = convertedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			String startdate="01/".concat(report.getMonth()).concat("/").concat(Integer.toString(year));
			
			
			List<DetainedParcelClearence> DetainedParcelClearencecolumns=reportService.getDetainedParcelClearencecolumnsColumns(startdate,financialdateandyear,enddate,report.getMonth(),Integer.toString(year),  session,request);
			models.addObject("DetainedParcelClearencecolumns",DetainedParcelClearencecolumns);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	

	
	@RequestMapping(value = "/boereport", method = RequestMethod.POST)
	public ModelAndView boereport(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/boereport");

		try {
			/*
			 *  request.getSession().setAttribute("fromdate", report.getFromdate());
			 *  request.getSession().setAttribute("todate", report.getTodate());
			 *  request.getSession().setAttribute("filter", report.getFilter());
			 */
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/seizurereport", method = RequestMethod.POST)
	public ModelAndView seizurereport(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/seizurereport");

		try {
			/*
			 *  request.getSession().setAttribute("fromdate", report.getFromdate());
			 *  request.getSession().setAttribute("todate", report.getTodate());
			 *  request.getSession().setAttribute("filter", report.getFilter());
			 */
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/customdutyreport", method = RequestMethod.POST)
	public ModelAndView customdutyreport(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/customdutyreport");

		try {
			/*
			 *  request.getSession().setAttribute("fromdate", report.getFromdate());
			 *  request.getSession().setAttribute("todate", report.getTodate());
			 *  request.getSession().setAttribute("filter", report.getFilter());
			 */
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	
	
	
	/*
	 * @RequestMapping(value = "/postaledi", method = RequestMethod.GET) public
	 * ModelAndView postaledi( HttpServletRequest request,HttpSession session) {
	 * ModelAndView models = new ModelAndView("Report/postaledi"); try {
	 * List<PostalediColumns> postaledi=reportService.getPostalEdiColumns(
	 * session,request); //List<FPO_MANUAL_COMMERCIAL> postalediq =
	 * FPOrepost.getpostalediq(); List<FPO_MANUAL_COMMERCIAL> postalediq =
	 * reportService.getPostalediqucolumns(session,request); for
	 * (FPO_MANUAL_COMMERCIAL manualcommercial : postalediq) {
	 * 
	 * manualcommercial.setCurrentStatus(fpoService.getpos(manualcommercial.
	 * getITEM_ID(), session, request));
	 * 
	 * } models.addObject("postaledi",postaledi); models.addObject("postalediq",
	 * postalediq); System.out.println(postalediq); } catch (Exception e) { // TODO:
	 * handle exception e.printStackTrace(); }
	 * 
	 * return models;
	 * 
	 * }
	 */	
	
	@RequestMapping(value = "/postaledi", method = RequestMethod.GET)
	public ModelAndView postaledi(Model model, HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/postaledi");
		try {
			List<PostalediColumns> postaledi=reportService.getPostalEdiColumns( session,request);
			models.addObject("postaledi",postaledi);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	@RequestMapping("/postaledi1")
	public ModelAndView postaledi1(Model model, HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/processedcommercial");
		
		
		//List<FPO_MANUAL_COMMERCIAL> postalediq = FPOrepost.getpostalediq(); 
		List<FPO_MANUALCOMMERCIALCOLUMNS> postalediq = reportService.getPostalediqucolumns(session,request);
		String id = request.getParameter("id");
		String id1 = request.getParameter("id1");
		String id2 = request.getParameter("id2");
		String bedoc = request.getParameter("bedoc");
		String invdoc = request.getParameter("invdoc");
		String challandoc = request.getParameter("challandoc");
		String bedocfilename = FPOrepost.getbedocpdf(id,bedoc);
		String invdocfilename = FPOrepost.getinvdocpdf(id1,invdoc);
		String challandocfilename = FPOrepost.getchallandocpdf(id2,challandoc);
		for (FPO_MANUALCOMMERCIALCOLUMNS manualcommercial : postalediq) {
			
			manualcommercial.setCurrentStatus(fpoService.getpos(manualcommercial.getITEM_ID(), session, request));
			
		}
		models.addObject("postalediq", postalediq);
		model.addAttribute("bedocfilename", bedocfilename);
		model.addAttribute("invdocfilename", invdocfilename);
		model.addAttribute("challandocfilename", challandocfilename);
		System.out.println(postalediq);
	
	return models;
	}
	
	
//	List<FPO_MANUAL_COMMERCIAL> postalediq =reportService.getPostalediqucolumns(session, request);
//	models.addObject("postalediq",postalediq);	
	
	@RequestMapping(value = "/detention", method = RequestMethod.GET)
	public ModelAndView detention( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/detained");
		try {
			List<DetainedColumns> detained=reportService.getDetainedColumns( session,request);
			models.addObject("detained",detained);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/deliveryarticles", method = RequestMethod.GET)
	public ModelAndView deliveryarticles( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/deliveryarticles");
		try {
			String cusite = request.getSession().getAttribute("Cusite").toString();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			String filter = "ooc_date";
			String fromdate = reportService.getFromDateForDelivery(filter,  session,request);
			
			
			LocalDate now = LocalDate.now();
			LocalDate earlier = now.minusMonths(1);
			
			 fromdate = earlier.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String todate = dateFormat.format(new Date());
			
			models.addObject("fromdate",fromdate);
			models.addObject("todate",todate);
			
	//		BigDecimal ooccount = reportService.getOocCountForDeliveryArticles(fromdate,todate,filter,  session);
			BigDecimal ooccount = FPOrepost.getOocCountForDeliveryArticles(fromdate,todate,  cusite);
	//		BigDecimal Deliverycount = reportService.getDeliveryCountForDeliveryArticles(fromdate,todate,filter, session);
			BigDecimal Deliverycount = FPOrepost.getDeliveryCountForDeliveryArticles(fromdate,todate,cusite);
			
			models.addObject("ooccount",ooccount);
			models.addObject("Deliverycount",Deliverycount);
			
			List<DeliveryArticlesColumns> deliveryarticles=reportService.getDeliveryArticlesColumns(fromdate,todate,session,request);
			models.addObject("deliveryarticles",deliveryarticles);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/deliveryarticledatechange", method = RequestMethod.POST)
	public ModelAndView deliveryarticlesdatechange(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/deliveryarticledatechange");

		try {
			
			String cusite = request.getSession().getAttribute("Cusite").toString();
			List<DeliveryArticlesColumns> deliveryarticles=reportService.getDeliveryArticlesColumns(report.getFromdate(),report.getTodate(),session,request);
			models.addObject("deliveryarticles",deliveryarticles);
			

			
		//	BigDecimal ooccount = reportService.getOocCountForDeliveryArticles(report.getFromdate(),report.getTodate(),"ooc_date",  session);
			BigDecimal ooccount = FPOrepost.getOocCountForDeliveryArticles(report.getFromdate(),report.getTodate(),  cusite);
		//	BigDecimal Deliverycount = reportService.getDeliveryCountForDeliveryArticles(report.getFromdate(),report.getTodate(),"ooc_date", session);
			BigDecimal Deliverycount = FPOrepost.getDeliveryCountForDeliveryArticles(report.getFromdate(),report.getTodate(),cusite);
			
			models.addObject("ooccount",ooccount);
			models.addObject("Deliverycount",Deliverycount);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/deliveryarticlepending", method = RequestMethod.POST)
	public ModelAndView deliveryarticlepending(@ModelAttribute("report") ReportDateBean report,  HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/deliveryarticlepending");

		try {
			
			String cusite = request.getSession().getAttribute("Cusite").toString();
	//		List<DeliveryArticlesPendingColumns> deliveryarticles=reportService.getDeliveryArticlesPendingColumns(report.getFromdate(),report.getTodate(),  session);
			List<DeliveryArticlesPendingColumns>  deliveryarticles = FPOrepost.getDeliveryArticlesPendingColumns(report.getFromdate(),report.getTodate(),cusite);
			models.addObject("deliveryarticles",deliveryarticles);
			

			
		//	BigDecimal ooccount = reportService.getOocCountForDeliveryArticles(report.getFromdate(),report.getTodate(),"ooc_date",  session);
			BigDecimal ooccount = FPOrepost.getOocCountForDeliveryArticles(report.getFromdate(),report.getTodate(),  cusite);
		//	BigDecimal Deliverycount = reportService.getDeliveryCountForDeliveryArticles(report.getFromdate(),report.getTodate(),"ooc_date",session);
			BigDecimal Deliverycount = FPOrepost.getDeliveryCountForDeliveryArticles(report.getFromdate(),report.getTodate(),cusite);
			
			models.addObject("ooccount",ooccount);
			models.addObject("Deliverycount",Deliverycount);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/itemdetails", method = RequestMethod.POST)
	public ModelAndView itemdetails( HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/itemdet");

		try {
			
			String itemid = request.getParameter("itemid").toString();
			List<ItemDetails> itemdetails=reportService.getItemDetails(itemid);
			models.addObject("itemdetails",itemdetails);
			
			
			ItemAmountDetails itemamountdetails=reportService.getItemAmountDetails(itemid);
			models.addObject("itemamountdetails",itemamountdetails);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/statechange", method = RequestMethod.POST)
	public @ResponseBody void statechange( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		boolean status = false;
		
		try {
			
			String state = request.getParameter("state").toString();
			
			
	//		List<SelectTag> selecttag=reportService.getSelectTag(state);
			List<SelectTag> selecttag=FPOrepost.getSelectTag(state);
			String relationslistbean = new Gson().toJson(selecttag);
			JSONArray list = new JSONArray(relationslistbean);
			    jsonObj.put("list",list );
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
	
	@RequestMapping(value = "/yearchange", method = RequestMethod.POST)
	public @ResponseBody void yearchange( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		boolean status = false;
		
		try {
			
			String year = request.getParameter("year").toString();
			String month = request.getParameter("month").toString();
			
			
			List<MonthNames> selecttag=reportService.getMonthSelect(month,Integer.parseInt(year));
			String relationslistbean = new Gson().toJson(selecttag);
			JSONArray list = new JSONArray(relationslistbean);
			    jsonObj.put("list",list );
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
		

	
	@RequestMapping(value = "/cthchange", method = RequestMethod.POST)
	public @ResponseBody void cthchange( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		boolean status = false;
		
		try {
			
			String twodigit = request.getParameter("twodigit").toString();
			
			
			List<String> selecttag=reportService.getOtherFourDigi(twodigit);
			String relationslistbean = new Gson().toJson(selecttag);
			JSONArray list = new JSONArray(relationslistbean);
			    jsonObj.put("list",list );
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
	

	@RequestMapping(value = "/importpopup", method = RequestMethod.POST)
	public ModelAndView importopening(HttpSession session, HttpServletRequest request)
			{
		ModelAndView models = new ModelAndView("Report/importedopening");

		try {
			
			String month = request.getParameter("month").toString();
			int year = Integer.parseInt(request.getParameter("year").toString());
			
			String tab = request.getParameter("tab").toString();
			
			if(Long.parseLong(month)<4) {
				year=year+1;
			}
			
			String financialdateandyear="01/04/".concat(Integer.toString(year));

			String startdate="01/".concat(month).concat("/").concat(Integer.toString(year));
			
			String date = "01/".concat(month).concat("/").concat(Integer.toString(year));
			LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			convertedDate = convertedDate.withDayOfMonth(
			                                convertedDate.getMonth().length(convertedDate.isLeapYear()));
			String enddate = convertedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			List<OpeningReportColumns> ReportColumns=new LinkedList<OpeningReportColumns>();
			
			if(tab.equalsIgnoreCase("importopening")) {
				ReportColumns=reportService.getReportColumnsForOpening(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Opening Balance Record");
			}else if(tab.equalsIgnoreCase("importmonthreceipt")) {
				ReportColumns=reportService.getReceiptForMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Receipt For The Month Record");
			}else if(tab.equalsIgnoreCase("importuptomonthreceipt")) {
				ReportColumns=reportService.getReceiptuptoMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Receipt Upto The Month Record");
			}else if(tab.equalsIgnoreCase("importwithoutexamass")) {
				ReportColumns=reportService.getReceiptWithoutOOCandExam(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","OOC Without Examination/Assessment Record");
			}else if(tab.equalsIgnoreCase("importoocexam")) {
				ReportColumns=reportService.getReceiptWithExam(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","OOC After Examination Only Record");
			}else if(tab.equalsIgnoreCase("importoocassexam")) {
				ReportColumns=reportService.getReceiptWithASSandExam(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","OOC After Examination and Assessment Record");
			}else if(tab.equalsIgnoreCase("importdetained")) {
				ReportColumns=reportService.getDetainedParcel(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Detained Parcel Record");
			}else if(tab.equalsIgnoreCase("importtotal")) {
				ReportColumns=reportService.getDisposalParcelMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Disposal For The Month Record");
			}else if(tab.equalsIgnoreCase("importdisposal")) {
				ReportColumns=reportService.getDisposalParcel(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Disposal Upto Month Record");
			}else if(tab.equalsIgnoreCase("importclosingbalance")) {
				ReportColumns=reportService.getClosingBalance(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Closing Balance Record");
			}else if(tab.equalsIgnoreCase("importmonthduty")) {
				ReportColumns=reportService.getCustomDutyMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Custom Duty For Month Record");
			}else if(tab.equalsIgnoreCase("importdutyuptomonth")) {
				ReportColumns=reportService.getCustomDutyUptoMonth(startdate,financialdateandyear,enddate,month,Integer.toString(year),  session,request);
				models.addObject("heading","Custom Duty Upto Month Record");
			}
			
			models.addObject("ReportColumns",ReportColumns);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/deliveryackchange", method = RequestMethod.POST)
	public @ResponseBody void deliveryackchange( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		boolean status = false;
		
		try {
			
			String deliery = request.getParameter("deliery").toString();
			
			
			List<SelectTag> selecttag=reportService.getNotDeliveryStatus(deliery);
			String relationslistbean = new Gson().toJson(selecttag);
			JSONArray list = new JSONArray(relationslistbean);
			    jsonObj.put("list",list );
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
	
	@RequestMapping(value = "/misreport", method = RequestMethod.GET)
	public ModelAndView misreport( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/misreportmain");
		try {
			
			List<String> mailclass = reportService.getMailClass();
			models.addObject("mailclass",mailclass);
			
			List<String> itemcat = reportService.getMailCat();
			models.addObject("itemcat",itemcat);
			
			LocalDate todaydate = LocalDate.now();
			
			System.out.println("SHORT format: " + todaydate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
			
			String mailclasses = mailclass.toString();
			String[] mailclass1=mailclasses.split(",");
			
			/*
			 * String fpositestotal = request.getParameter("fposite").toString(); String[]
			 * fposites1=fpositestotal.split(",");
			 */
			
            String fromdate = todaydate.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  
            String todate = todaydate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            String processed,cleared;
            if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			List<MisReportColumns> reportcolumns=reportService.getMisReportColumns(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("reportcolumns",reportcolumns);
			 processed=reportService.getMisReportprocessed(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			 cleared=reportService.getMisReportcleared(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			}
		
			else {
				
			String[] fposites1 = {""};
			List<MisReportColumns> reportcolumns=reportService.getMisReportColumns(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,0,request);
			models.addObject("reportcolumns",reportcolumns);
			 processed=reportService.getMisReportprocessed(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			 cleared=reportService.getMisReportcleared(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			}
            
			models.addObject("processed",processed);
            
			models.addObject("cleared",cleared);

			
			models.addObject("fromdate",fromdate);
			models.addObject("todate",todate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	
	@RequestMapping(value = "/NSMmisreport", method = RequestMethod.GET)
	public ModelAndView NSMmisreport( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/NSMmisreportmain");
		try {
			
			List<String> mailclass = reportService.getMailClass();
			models.addObject("mailclass",mailclass);
			System.out.println(mailclass);
			List<String> itemcat = reportService.getMailCat();
			models.addObject("itemcat",itemcat);
			
			List<String>fpositecat=reportService.getFpositeCat();
			models.addObject("fpositecat",fpositecat);
			
			LocalDate todaydate = LocalDate.now();
			
			System.out.println("SHORT format: " + todaydate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
			
            String fromdate = todaydate.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  
            String todate = todaydate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
          String mailclasses = mailclass.toString();
		String[] mailclass1=mailclasses.split(",");
			String fpositestotal = mailclass.toString();
			String[] fposites1=fpositestotal.split(",");
			
            
			List<MisReportColumns> reportcolumns=reportService.getMisReportColumns(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("reportcolumns",reportcolumns);
			
			String processed=reportService.getMisReportprocessed(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("processed",processed);
            

			String cleared=reportService.getMisReportcleared(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("cleared",cleared);

			
			models.addObject("fromdate",fromdate);
			models.addObject("todate",todate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportdatechange", method = RequestMethod.POST)
	public ModelAndView misreportdatechange(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportdatechange");

		try {
			
			String processed,cleared;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			List<MisReportColumns> reportcolumns=reportService.getMisReportColumns(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("reportcolumns",reportcolumns);
			 processed=reportService.getMisReportprocessed(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			 cleared=reportService.getMisReportcleared(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			}
		
			else {
				
			String[] fposites1 = {""};
			List<MisReportColumns> reportcolumns=reportService.getMisReportColumns(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,0,request);
			models.addObject("reportcolumns",reportcolumns);
			 processed=reportService.getMisReportprocessed(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			 cleared=reportService.getMisReportcleared(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			}
            

			
			models.addObject("processed",processed);
			models.addObject("cleared",cleared);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportoocpending", method = RequestMethod.POST)
	public ModelAndView misreportoocpending(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocpending");
		
		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			List<MisReportOocPending> reportcolumns=reportService.getMisReportOocPending(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("reportcolumns",reportcolumns);
			models.addObject("count",reportcolumns.size());
			}
		
			else {
			String[] fposites1 = {""};
			List<MisReportOocPending> reportcolumns=reportService.getMisReportOocPending(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			models.addObject("reportcolumns",reportcolumns);
			models.addObject("count",reportcolumns.size());
			}
            
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

		return models;
		
	}
	
	@RequestMapping(value = "/misreportwithoutpincode", method = RequestMethod.POST)
	public ModelAndView misreportwithoutpincode(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportwithoutpincode");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String siteco = request.getParameter("fposite").toString();
			System.out.println("siteco in String" + siteco);
			System.out.println(mailclass);
			String[] mailclass1=mailclass.split(",");
			String[] siteco1 = siteco.split(",");
			System.out.println("siteco1 in array" + siteco1);
			System.out.println(mailclass1);
			
			List<MisReportWithoutPincode> reportcolumns=reportService.getMisReportWithoutPincode(fromdate,todate,mailclass1,mailclass1.length,siteco1,siteco1.length,session,request);
			
		//	List<MisReportWithoutPincode> reportcolumns=reportService.getMisReportWithoutPincode(fromdate,todate,session,request);



			for (MisReportWithoutPincode reportcolumn : reportcolumns) {
				reportcolumn
						.setStatus(fpoService.getpos( reportcolumn.getItem_id(), session,request));
			} 
			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportqryraised", method = RequestMethod.POST)
	public ModelAndView misreportqryraised(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportqryraised");

		try {
			List<MisReportQryRaised> reportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
		System.out.println("mailclass in controller is " + mailclass); 
		String[] mailclass1=mailclass.split(","); 
		
		if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
				|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
		String fpositestotal = request.getParameter("fposite").toString();
		String[] fposites1=fpositestotal.split(","); 
		 reportcolumns=reportService.getMisReportQryRaised(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);

		}
	
		else {
		String[] fposites1 = {""};
		 reportcolumns=reportService.getMisReportQryRaised(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			
		}

			for (MisReportQryRaised reportcolumn : reportcolumns) {
				reportcolumn
						.setCurrentque(fpoService.getpos( reportcolumn.getItem_id(), session,request));
			}
			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportaddlqryraised", method = RequestMethod.POST)
	public ModelAndView misreportaddlqryraised(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportaddlqryraised");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			String fposite = request.getParameter("fposite").toString();
			String[] fposite1=fposite.split(",");
			
			
			List<MisReportQryRaised> reportcolumns=reportService.getMisAddlReportQryRaised(fromdate,todate,session,mailclass1,mailclass1.length,fposite1,fposite1.length,request);




			for (MisReportQryRaised reportcolumn : reportcolumns) {
				reportcolumn
						.setCurrentque(fpoService.getpos( reportcolumn.getItem_id(), session,request));
			}
			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportbymailclass", method = RequestMethod.POST)
	public ModelAndView misreportbymailclass(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportbymailclass");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			String fposite = request.getParameter("fposite").toString();
			String[] fposite1=fposite.split(",");
			
			List<MisReportByMailClass> reportcolumns=reportService.getMisReportByMailClass(fromdate,todate,session,mailclass1,mailclass1.length,fposite1,fposite1.length,request);

			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportbyitemcat", method = RequestMethod.POST)
	public ModelAndView misreportbyitemcat(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportbyitemcat");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			
			
	//		mailclass = mailclass.replaceAll(",", "','");
			
			String[] mailclass1=mailclass.split(",");
			String itemcat = request.getParameter("itemcat").toString();
	
			String[] itemcat1 = itemcat.split(",");

			String fpositestotal1 = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal1.split(","); 
			List<MisReportByItemCategory> reportcolumns=reportService.getMisReportByItemCategory(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);

			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportoocgivencountries", method = RequestMethod.POST)
	public ModelAndView misreportoocgivencountries(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocgivencountries");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String fpositestotal1 = request.getParameter("fposite").toString();
			String[] fposites=fpositestotal1.split(",");
			List<MisReportOocGivenCountries> reportcolumns=reportService.getMisReportOocGivenCountries(fromdate,todate,session,fposites,fposites.length,request);

			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportoocgivencountriesnotfwise", method = RequestMethod.POST)
	public ModelAndView misreportoocgivencountriesnotfwise(HttpSession session, HttpServletRequest request,Model model )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocgivencountriesnotfwise");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String fpositestotal1 = request.getParameter("fposite").toString();
			String[] fposites=fpositestotal1.split(",");
			
			List<String> notn = reportService.getNotncategory(fromdate,todate);
		
			models.addObject("notn",notn);
			
			System.out.println(notn);
			
			List<MisReportOocGivenCountriesNotfWise> reportcolumns=reportService.getMisReportOocGivenCountriesNotfWisewithoutfilter(fromdate,todate,session,fposites,fposites.length,request);

			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			models.addObject("fposts",fpositestotal1);
			models.addObject("from",fromdate);
			models.addObject("to",todate);
			models.addObject("sites",fpositestotal1);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportoocgivencountriesnotfwisedata", method = RequestMethod.POST)
	public ModelAndView misreportoocgivencountriesnotfwisedata(HttpSession session, HttpServletRequest request,Model model )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocgivencountriesnotfwise");

		try {
			
			String fromdate = request.getParameter("from");
			String todate = request.getParameter("to");
			
			String slno=request.getParameter("slno");
			String notn=request.getParameter("notn");
			String[]notnlist=notn.split(",");
			String[]slnolist=slno.split(",");
			String fpositestotal1 = request.getParameter("fposite");
			String[] fposites=fpositestotal1.split(",");
			
			
			String notnl=request.getParameter("notnval");
			String nottnl=notnl.substring(1, notnl.length()-1);
			String notnlis[]=nottnl.split(",");
			List<String> stringList = Arrays.stream(notnlis)
                    .map(String::trim)
                    .collect(Collectors.toList());
			
			System.out.println("santhosh testing");
			System.out.println(stringList);
			
			List<MisReportOocGivenCountriesNotfWise> reportcolumns=reportService.getMisReportOocGivenCountriesNotfWise(fromdate,todate,session,fposites,fposites.length,request,notnlist,notnlist.length,slnolist,slnolist.length);
			System.out.println("fetcheddata"+reportcolumns);
			models.addObject("reportcolumns",reportcolumns);
			
			//List<String> notnlis = reportService.getNotncategory(fromdate,todate);
			models.addObject("count",reportcolumns.size());
			models.addObject("notn",stringList);
			models.addObject("from",fromdate);
			models.addObject("to",todate);
			models.addObject("sites",fpositestotal1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	


	@RequestMapping(value = "/slnoFetching")
	@ResponseBody
	public  List<String> slnofetch( HttpServletRequest request,HttpSession session) {
		ModelAndView models = new ModelAndView("Report/misreportoocgivencountriesnotfwise");
	List<String> slno= new ArrayList<>();
	try {
			
		String dataSLno = request.getParameter("data");
			
			 slno = reportService.getSlnoCategory(dataSLno);
			
			models.addObject("slno",slno);
			System.out.println("checking "+slno);
			
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return slno;
		
	}

	
	
	
	
	
	
	@RequestMapping(value = "/mismailclassreportoocgivencountries", method = RequestMethod.POST)
	public ModelAndView mismailclassreportoocgivencountries(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/mismailclassreportoocgivencountries");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			List<MisReportOocGivenCountries> reportcolumns;
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			reportcolumns=reportService.getMisReportMailClassOocGivenCountries(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			}
		
			else {
				
			String[] fposites1 = {""};
			reportcolumns=reportService.getMisReportMailClassOocGivenCountries(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,0,request);
	
			}
			
			
			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	/*
	 * @RequestMapping(value = "/mismailclassreportcountriesead", method =
	 * RequestMethod.POST) public ModelAndView
	 * mismailclassreportcountriesead(HttpSession session, HttpServletRequest
	 * request ) { ModelAndView models = new
	 * ModelAndView("Report/mismailclassreportoocgivencountries");
	 * 
	 * try {
	 * 
	 * String fromdate = request.getParameter("fromdate").toString(); String todate
	 * = request.getParameter("todate").toString();
	 * 
	 * List<MisReportOocGivenCountries>
	 * reportcolumns=reportService.getMisReportMailClassOocGivenCountries(fromdate,
	 * todate,session,request);
	 * 
	 * models.addObject("reportcolumns",reportcolumns);
	 * 
	 * models.addObject("count",reportcolumns.size());
	 * 
	 * } catch (Exception e) { // TODO: handle exception
	 * System.out.println(e.getMessage()); }
	 * 
	 * return models;
	 * 
	 * }
	 */
	
	
	@RequestMapping(value = "/misreportoocgivendelistatus", method = RequestMethod.POST)
	public ModelAndView misreportoocgivendelistatus(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocgivendelistatus");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String siteco = request.getParameter("fposite").toString();
			
			String[] siteco1 =siteco.split(",");
			System.out.println("siteco1 from array "+ siteco1);
			
			List<MisReportOocGivenDeliStatus> reportcolumns=reportService.getMisReportOocGivenDeliStatus(fromdate,todate,siteco1,siteco1.length,session,request);

			models.addObject("reportcolumns",reportcolumns);
			models.addObject("count",reportcolumns.size());
			

			String processed=reportService.getMisReportOocprocessed(fromdate,todate,session,request);
			models.addObject("processed",processed);
			


			String delistatus=reportService.getMisReportdelivered(fromdate,todate,session,request);
			models.addObject("delistatus",delistatus);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportreceptacleprocess", method = RequestMethod.POST)
	public ModelAndView misreportreceptacleprocess(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportreceptacleprocess");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String siteco = request.getParameter("fposite").toString();
			System.out.println("siteco from String " + siteco);
			String[] siteco1 =siteco.split(",");
			
			List<MisOOEReceptacleProcess> ooereportcolumns=reportService.getMisReportOOEReceptacleProcess(fromdate,todate,siteco1,siteco1.length,session,request);

			models.addObject("ooereportcolumns",ooereportcolumns);

			models.addObject("count",ooereportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	//13
	@RequestMapping(value = "/misreportooereceptacleprocessdetails", method = RequestMethod.POST)
	public ModelAndView misreportooereceptacleprocessdetails(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportooereceptacleprocessdetails");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String siteco = request.getParameter("fposite").toString();
			System.out.println("siteco from String " + siteco);
			String[] siteco1 =siteco.split(",");
			
			List<MisReportOOEReceptacleProcess> ooereportcolumns=reportService.getMisReportOOEReceptacleProcessDetails(fromdate,todate,siteco1,siteco1.length,session,request);

			models.addObject("ooereportcolumns",ooereportcolumns);

			models.addObject("count",ooereportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	
	//for report 25 by santhosh
	
		@RequestMapping(value = "/queryreplystatuss", method = RequestMethod.POST)
		public ModelAndView queryreplystatuss(HttpSession session, HttpServletRequest request )
				{
			ModelAndView models = new ModelAndView("Report/queryreplystatuss");

			try {
				
				String fromdate = request.getParameter("fromdate").toString();
				String todate = request.getParameter("todate").toString();
				String siteco = request.getParameter("fposite").toString();
				System.out.println("siteco from String " + siteco);
				String[] siteco1 =siteco.split(",");
				
				//List<MisReportQueryReplyStatus> reportcolumns=reportService.getMisReportOOEReceptacleProcessDetails(fromdate,todate,siteco1,siteco1.length,session,request);
				List<MisReportQueryReplyStatus> reportcolumns=reportService.getMisReportQueryReplyStatus(fromdate,todate,siteco1,siteco1.length,session,request);
				System.out.println("to check wheather obtained data is correct "+reportcolumns);
				models.addObject("reportcolumns",reportcolumns);

				//models.addObject("count",reportcolumns.size());
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			return models;
			
		}
	
	
	
	
	
	
	@RequestMapping(value = "/misreportfporeceptacleprocess", method = RequestMethod.POST)
	public ModelAndView misreportfporeceptacleprocess(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportfporeceptacleprocess");

		try {
			
			List<MisFPOReceptacleProcess> fporeportcolumns;
		
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			 fporeportcolumns=reportService.getMisReportFPOReceptacleProcess(fromdate,todate,session,fposites1,fposites1.length,request);
			
			}
		
			else {
			String[] fposites1 = {""};
			 fporeportcolumns=reportService.getMisReportFPOReceptacleProcess(fromdate,todate,session,fposites1,fposites1.length,request);
			}
			models.addObject("fporeportcolumns",fporeportcolumns);

			models.addObject("count",fporeportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}


	
	
	@RequestMapping(value = "/misreportfporeceptacleprocessdetails", method = RequestMethod.POST)
	public ModelAndView misreportfporeceptacleprocessdetails(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportfporeceptacleprocessdetails");

		try {
			List<MisReportFPOReceptacleProcess> fporeportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			 fporeportcolumns=reportService.getMisReportFPOReceptacleProcessDetails(fromdate,todate,session,fposites1,fposites1.length,request);

			}
		
			else {
				
			String[] fposites1 = {""};
			 fporeportcolumns=reportService.getMisReportFPOReceptacleProcessDetails(fromdate,todate,session,fposites1,fposites1.length,request);
	
			}

			
			models.addObject("fporeportcolumns",fporeportcolumns);

			models.addObject("count",fporeportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportartcdetained", method = RequestMethod.POST)
	public ModelAndView misreportartcdetained(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportartcdetained");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(",");

			
			List<MisReportArtcDetained> reportcolumns=reportService.getMisReportArtcDetained(fromdate,todate,session,fposites1,fposites1.length,request);
				


			for (MisReportArtcDetained reportcolumn : reportcolumns) {
				reportcolumn
						.setStatus(fpoService.getpos(reportcolumn.getItem_id(), session,request));
			}

			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportoocgivendelistatusDetails", method = RequestMethod.POST)
	public ModelAndView misreportoocgivendelistatusDetails(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportoocgivendelistatusdetails");

		try {
			List<MisReportOocGivenDeliStatusDetails> reportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			 reportcolumns=reportService.getMisReportOocGivenDeliStatusDetails(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
	}
		
			else {
				
			String[] fposites1 = {""};
			 reportcolumns=reportService.getMisReportOocGivenDeliStatusDetails(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);

			}
			
			
			models.addObject("reportcolumns",reportcolumns);
			


			String processed=reportService.getMisReportOocprocessed(fromdate,todate,session,request);
			models.addObject("processed",processed);
			


			String delistatus=reportService.getMisReportdelivered(fromdate,todate,session,request);
			models.addObject("delistatus",delistatus);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}

	
	@RequestMapping(value = "/misreportarticlestatus", method = RequestMethod.POST)
	public ModelAndView misreportarticlestatus(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportarticlestatus");

		try {
			
//			String fromdate = request.getParameter("fromdate").toString();
//			String todate = request.getParameter("todate").toString();
			String itemid = request.getParameter("itemid").toString();
			System.out.println(itemid);
			List<MisReportArticleStatus> reportcolumns=reportService.getMisReportArticleStatus(session,itemid,request);
			System.out.println(reportcolumns);
			for (MisReportArticleStatus reportcolumn : reportcolumns) {
				reportcolumn
						.setCurque(fpoService.getpos( reportcolumn.getItem_id(), session,request));
				System.out.println(reportcolumn);
			}

			
			models.addObject("reportcolumns",reportcolumns);
			models.addObject("itemid",itemid);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreportooccancel", method = RequestMethod.POST)
	public ModelAndView misreportooccancel(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportooccancel");

		try {
			List<MisReportOOCCancel> reportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			 reportcolumns=reportService.getMisReportOOCCancel(fromdate,todate,session,fposites1,fposites1.length,request);
				models.addObject("reportcolumns",reportcolumns);
			}
			else {
				
				String[] fposites1 = {""};
				 reportcolumns=reportService.getMisReportOOCCancel(fromdate,todate,session,fposites1,fposites1.length,request);
					models.addObject("reportcolumns",reportcolumns);
				}
	
			 for (MisReportOOCCancel reportcolumn : reportcolumns) { reportcolumn
			 .setStatus(fpoService.getpos( reportcolumn.getItem_id(), session,request)); }
				models.addObject("reportcolumns",reportcolumns);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	//Report 26
	
		@RequestMapping(value = "/misreportoocgivencountriesdelstatus", method = RequestMethod.POST)
		public ModelAndView misreportoocgivencountriesdelstatus(HttpSession session, HttpServletRequest request )
				{
			ModelAndView models = new ModelAndView("Report/misreportoocgivencountriesdelstatus");

			try {
				
				String fromdate = request.getParameter("fromdate").toString();
				String todate = request.getParameter("todate").toString();
				String fpositestotal1 = request.getParameter("fposite").toString();
				String[] fposites=fpositestotal1.split(",");
				List<MisReportOocGivenCountriesdelstatus> reportcolumns=reportService.getMisReportOocGivenCountriesdelistats(fromdate,todate,session,fposites,fposites.length,request);

				models.addObject("reportcolumns",reportcolumns);

				models.addObject("count",reportcolumns.size());
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			return models;
			
		}
		
	
	
	//report 27
		@RequestMapping(value = "/mismailclassreportoocgivencountriesdelstatus", method = RequestMethod.POST)
		public ModelAndView mismailclassreportoocgivencountriesdelstatus(HttpSession session, HttpServletRequest request )
				{
			ModelAndView models = new ModelAndView("Report/mismailclassreportoocgivencountriesdelstatus");

			try {
				
				String fromdate = request.getParameter("fromdate").toString();
				String todate = request.getParameter("todate").toString();
				String mailclass = request.getParameter("mailclass").toString();
				String[] mailclass1=mailclass.split(",");
				List<MisReportOocGivenCountriesdelstatus> reportcolumns;
				
				if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
						|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				String fpositestotal = request.getParameter("fposite").toString();
				String[] fposites1=fpositestotal.split(","); 
				reportcolumns=reportService.getMisReportMailClassOocGivenCountriesdelstatus(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
				}
			
				else {
					
				String[] fposites1 = {""};
				reportcolumns=reportService.getMisReportMailClassOocGivenCountriesdelstatus(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,0,request);
		
				}
				
				
				models.addObject("reportcolumns",reportcolumns);

				models.addObject("count",reportcolumns.size());
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			return models;
			
		}
	
	
	
	@RequestMapping(value = "/misreportooficerarticle", method = RequestMethod.POST)
	public ModelAndView misreportooficerarticle(HttpSession session, HttpServletRequest request, Model model )
			{
		ModelAndView models = new ModelAndView("Report/misreportooficerarticle");
		
		

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			String ssoid = request.getParameter("ssoid").toString();
			String roles = request.getParameter("roles").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1 = mailclass.split(",");
			String siteco = request.getParameter("fposite").toString();
			String[] siteco1 = siteco.split(",");
			
			models.addObject("ssoid",ssoid);
			models.addObject("roles",roles);
			

			
			List<MisReportOfficersArticles> reportcolumns=reportService.getMisReportOfficersArticles(ssoid,roles,fromdate,todate,mailclass1,mailclass1.length,siteco1,siteco1.length,session,request);
			
			List<String>rolesbyssoid=reportService.getRolesbySsoid(ssoid);
			String role1="";
			String role2="";
			List<String>otherrolesbyssoid=new ArrayList<>();
			for (String str : rolesbyssoid) {
				
			    if(str.equals("PBS"))
			    	role1="PBS";
			    
			    else if(str.equals("PIN")||str.equals("PAO")||str.equals("PAC")||str.equals("PSU")) {
			    	  otherrolesbyssoid.add(str);
			    		role2="OtherRoles";
			    	}
			}
	
			if(rolesbyssoid.isEmpty()) {
				String totRoles = "0";
			
				model.addAttribute("order", totRoles);
			} else {
				
				String totRoles2 = "1";
				model.addAttribute("order", totRoles2);
				model.addAttribute("rolesassigned",otherrolesbyssoid);
				model.addAttribute("roleone",role1);
				model.addAttribute("roletwo",role2);
				
			}

			models.addObject("reportcolumns",reportcolumns);
			
			models.addObject("totalroles",rolesbyssoid);
			String cuSite = request.getSession().getAttribute("cuSite").toString();	
		
			List<Object> pbscolumns=null;
		
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") || request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
					List<String>cussites=Arrays.asList(siteco1);
				pbscolumns=FPOrepost.getpbsdatasite1(fromdate,todate,cussites,ssoid);
				
			}
			
			else
				
			    pbscolumns=FPOrepost.getpbsdatasite(fromdate,todate,cuSite,ssoid);
			
  
			System.out.println("resut obtained successfully");
			System.out.println(pbscolumns);
			System.out.println(reportcolumns);
			models.addObject("pbsreportcolumns",pbscolumns);
			models.addObject("pbsreportcolumnssize",pbscolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	} 
	@RequestMapping(value = "/misreportarrivefpo", method = RequestMethod.POST)
	public ModelAndView misreportarrivefpo(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportarrivefpo");

		try {
			
		//	String fromdate = request.getParameter("fromdate").toString();
		//	String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			List<MisReportFPOArrival> reportcolumns=reportService.getMisReportFPOArrival(session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
				models.addObject("reportcolumns",reportcolumns);
			}
		
			else {
				
			String[] fposites1 = {""};
			List<MisReportFPOArrival> reportcolumns=reportService.getMisReportFPOArrival(session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
				models.addObject("reportcolumns",reportcolumns);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/misreporttoead", method = RequestMethod.POST)
	public ModelAndView misreporttoead(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreporttoead");

		try {
			String processed;
			String cleared;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
			String[] mailclass1=mailclass.split(",");
			String itemcat = request.getParameter("itemcat").toString();
			
			String[] itemcat1 = itemcat.split(",");
			
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			String fpositestotal = request.getParameter("fposite").toString();
			String[] fposites1=fpositestotal.split(","); 
			List<MisReportEAD> reportcolumns=reportService.getMisReportEAD(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				models.addObject("reportcolumns",reportcolumns);
				processed=reportService.getMisReportprocessedead(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				cleared=reportService.getMisReportclearedead(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				
			}
		
			else {

			String[] fposites1 = {""};
			List<MisReportEAD> reportcolumns=reportService.getMisReportEAD(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				models.addObject("reportcolumns",reportcolumns);
				processed=reportService.getMisReportprocessedead(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				cleared=reportService.getMisReportclearedead(fromdate,todate,session,mailclass1,mailclass1.length,itemcat1,itemcat1.length,fposites1,fposites1.length,request);
				
			}
			
			
			models.addObject("processed",processed);
			models.addObject("cleared",cleared);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}

@RequestMapping(value = "/misreportcommercial", method = RequestMethod.POST)
	public ModelAndView misreportcommercialpending(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportcommercialpending");

		try {
			List<MisReportCommercialQueue> reportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			String mailclass = request.getParameter("mailclass").toString();
		System.out.println("mailclass in controller is " + mailclass); 
		String[] mailclass1=mailclass.split(","); 
		
		if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
				|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
		String fpositestotal = request.getParameter("fposite").toString();
		String[] fposites1=fpositestotal.split(","); 
		 reportcolumns=reportService.getmisreportcommercial(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);

		}
	
		else {
		String[] fposites1 = {""};
		 reportcolumns=reportService.getmisreportcommercial(fromdate,todate,session,mailclass1,mailclass1.length,fposites1,fposites1.length,request);
			
		}

			for (MisReportCommercialQueue reportcolumn : reportcolumns) {
				reportcolumn
						.setCurrentque(fpoService.getpos( reportcolumn.getItem_id(), session,request));
			}
			models.addObject("reportcolumns",reportcolumns);

			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}






	
@RequestMapping(value = "/misreportcommercialunderprocess", method = RequestMethod.POST)
	public ModelAndView misreportcommercialunderprocess(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportcommercialunderprocess");

		try {
			List<Misreportcommercialunderprocess> reportcolumns;
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString(); 
		 
		
		if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
				|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
		String fpositestotal = request.getParameter("fposite").toString();
		String[] fposites1=fpositestotal.split(","); 
		 reportcolumns=reportService.getMisReportcommercialunderprocess(fromdate,todate,session,fposites1,fposites1.length,request);

		}
	
		else {
		String[] fposites1 = {""};
		 reportcolumns=reportService.getMisReportcommercialunderprocess(fromdate,todate,session,fposites1,fposites1.length,request);
			
		}

		
			models.addObject("reportcolumns",reportcolumns);
			System.out.println(reportcolumns);
			models.addObject("count",reportcolumns.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}





	
	
	
	
	
	
	@RequestMapping(value = "/misreportdcall", method = RequestMethod.POST)
	public ModelAndView misreportdcall(HttpSession session, HttpServletRequest request )
			{
		ModelAndView models = new ModelAndView("Report/misreportdcall");

		try {
			
			String fromdate = request.getParameter("fromdate").toString();
			String todate = request.getParameter("todate").toString();
			
			List<MisReportDCall> reportcolumns=reportService.getMisReportDCall(fromdate,todate,session,request);


			models.addObject("reportcolumns",reportcolumns);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return models;
		
	}
	
	@RequestMapping(value = "/getrole", method = RequestMethod.POST)
	public @ResponseBody void getrole( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		boolean status = false;
		
		try {
			
			String ssoid = request.getParameter("ssoid").toString();
			
			
			List<String> roles=reportService.getRolesbySsoid(ssoid);
			String relationslistbean = new Gson().toJson(roles);
			JSONArray list = new JSONArray(relationslistbean);
			    jsonObj.put("list",list );
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			status = false;
		}
		jsonObj.put("status", status);	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
}
