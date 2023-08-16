package com.demo.fpo.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.el.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.bean.ArrivalHistoryBean;
import com.demo.fpo.bean.ArrivalHistoryBean2;
import com.demo.fpo.bean.ArticleDatas;
import com.demo.fpo.bean.BagArticleBean;
import com.demo.fpo.bean.BagScanBean;
import com.demo.fpo.bean.FpoMovement;
import com.demo.fpo.bean.FpoScanInfoBean;
import com.demo.fpo.bean.FpoScndQryData;
import com.demo.fpo.bean.NONARTARRINFO;
import com.demo.fpo.bean.RegulationRecptIDBean;
import com.demo.fpo.bean.ViewArticle;
import com.demo.fpo.model.DECI_REAS;
import com.demo.fpo.model.DeliveryArticlesColumns;
import com.demo.fpo.model.FPOSecondDefQry;
import com.demo.fpo.model.FPO_AMEND;
import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FPO_ORDER;
import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoFine;
import com.demo.fpo.model.FpoFineAmend;
import com.demo.fpo.model.FpoItemDetOthDuty;
import com.demo.fpo.model.FpoItemDetOthDutyAmend;
import com.demo.fpo.model.FpoMvmnt;
import com.demo.fpo.model.FpoPenal;
import com.demo.fpo.model.FpoPenalAmend;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryDecision;
import com.demo.fpo.model.FpoQueryDin;
import com.demo.fpo.model.FpoScanInfo;
import com.demo.fpo.model.FpoStatus;
import com.demo.fpo.model.FPO_MAP_SITE;
import com.demo.fpo.model.grandtotinfo;
import com.demo.fpo.bean.ooedata;
import com.demo.fpo.apiservice.PreDesRestService;
import com.demo.fpo.repos.*;
import com.demo.fpo.NsmLsmRepo.*;
import com.demo.fpo.service.FpoCurQueService;
import com.demo.fpo.service.FpoDeclaredService;
import com.demo.fpo.service.FpoQueryService;
import com.demo.fpo.service.FpoService;
import com.demo.fpo.service.ReportService;
import com.demo.fpo.service.ViewArticleService;
import com.demo.fpo.utils.QueryConstants;

@Controller
public class ViewArticleController { 
	
	@Autowired
	Fpoallot_repo fpoallotRepo;
	
	@Autowired
	PreDesRestService PREDESservice;
	
	@Autowired
	D_EMPrepo d_emprepo;

	@Autowired
	FPO_MAINrepost fpoRepost;
	
	@Autowired
	FPO_SITErepo fpositerepo;
	
	@Autowired
	FPO_ASS_PAO_CMTSRepo  fpoAssPaoCmtsRepo;
	
	@Autowired
	FpoMvmntRepo fpomvmntrepo;

	@Autowired
	FPO_ITEM_DETrepost fpoItemRepost;
	
	@Autowired
	FpoService fpoService;

	@Autowired
	FpoDeclaredService fpoDeclaredService;

	@Autowired
	FpoAddlQryRepo fpoAddlQryRepo;

	@Autowired
	FpoQueryService fpoQueryService;

	@Resource
	private ReportService reportService;

	@Autowired
	FPO_ORDERrepost fpoOrderRepost;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	FpoCurQueService fpoCurQueService;

	@Autowired
	ViewArticleService viewArticleService;

	@Autowired
	DECI_REASrepost deciReasonRepost;

	@Autowired
	FpoQueryDecisionRepo fpoQueryDecisionRepository;

	@Autowired
	FpoItemDetOthDutyRepo fpoItemDetOthDutyRepo;

	@Autowired
	FPO_AMENDrepost fpoAmendRepost;

	@Autowired
	FpoItemDetOthDutyAmendRepo fpoItemDetOthDutyAmendRepo;

	@Autowired
	FpoFineRepo fpoFineRepo;

	@Autowired
	FpoPenalRepo fpoPenalRepo;

	@Autowired
	FpoFineAmendRepo fpoFineAmendRepo;

	@Autowired
	FpoPenalAmendRepo fpoPenalAmendRepo;

	@Autowired
	FPO_EXAM_FINDINGSrepost fpoExamFindingsRepost;

	@Autowired
	FpoScanInfoRepo fpoScanInfoRepo;

	@Autowired
	FpoStatusRepo fpoStatusRepo;
	
	@Autowired
	ACL_ASSVALrepo aclrepo;

	@GetMapping(value = "/view-article")
	public ModelAndView viewArticle() {
		return new ModelAndView("article/view-article");
	}

	@GetMapping(value = "/article")
	public ModelAndView article(Model model) {
		model.addAttribute("countries", viewArticleService.fetchCustomQueries(QueryConstants.allCountries));
		model.addAttribute("mailCategories", viewArticleService.fetchCustomQueries(QueryConstants.allMailCategories));
		model.addAttribute("mailClasses", viewArticleService.fetchCustomQueries(QueryConstants.allMailClasses));
		return new ModelAndView("article/article");
	}

	
//	@GetMapping("/viewingthresholdletter")
//	public ModelAndView zthresholdletter(HttpServletRequest request, Model model) {
//		ModelAndView modelAndView = new ModelAndView("admin/nsm/Mise_changethreshold");
//		String from_date = request.getParameter("fromdate");
//		String zfilename = aclrepo.getpdf(from_date);
//		model.addAttribute("zfilename", zfilename);
//		return modelAndView;
//	}



	@GetMapping(value = "/article-details")
	public ModelAndView articleDetails(HttpServletRequest request, Model model) {
		model.addAttribute("itemId", request.getParameter("itemId"));
		String cinno= fpoRepost.cinno1(request.getParameter("itemId"));
		String cindt= fpoRepost.cindt1(request.getParameter("itemId"));
		model.addAttribute("cinno1", cinno);
		model.addAttribute("cindt1", cindt);
		return new ModelAndView("article/article-details");
	}

	@PostMapping(value = "/getFpoMovement")
	public ModelAndView getFpoMovement(HttpServletRequest request, Model model,HttpSession session) {
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<Collection> fpoMovements = fpomvmntrepo.getFpoMovementsByCinId(id);
		model.addAttribute("fpoMovements", fpoMovements);
		List<Collection> fpoMovementfull = fpomvmntrepo.getfpoMovementfull(itemId, request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		model.addAttribute("fpoMovementmodule", fpoMovementfull);
		return new ModelAndView("article/fpo-movement");
	}

	@PostMapping(value = "/getArticles")
	public @ResponseBody void getArticleIds(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		Boolean isReceiptOfcusItm = Boolean.valueOf(request.getParameter("receiptOfCusItm"));
		Boolean eadDate = Boolean.valueOf(request.getParameter("eadDate"));
		Boolean entryDate = Boolean.valueOf(request.getParameter("entryDate"));
		Boolean exitDate = Boolean.valueOf(request.getParameter("exitDate"));
		Boolean queryDate = Boolean.valueOf(request.getParameter("queryDate"));
		Boolean orderDate = Boolean.valueOf(request.getParameter("orderDate"));
		Boolean oocDate = Boolean.valueOf(request.getParameter("oocDate"));
		List<String> articles = null;
		if (fromDate != null && toDate != null) {
			articles = viewArticleService.getArticleIdByFilteredDates(isReceiptOfcusItm, eadDate, entryDate, exitDate,
					queryDate, orderDate, oocDate, fromDate, toDate, session,request);
		} else {
			articles = fpoRepost.getArticleIds(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		}
		jsonObj.put("articles", articles);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@GetMapping(value = "/getArticleDatas")
	public @ResponseBody void getArticleDatas(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
	/*	String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		Boolean itemPosted = Boolean.valueOf(request.getParameter("itemPosted"));
		Boolean eadArrival = Boolean.valueOf(request.getParameter("eadArrival"));
		Boolean queryRaised = Boolean.valueOf(request.getParameter("queryRaised"));
		Boolean queryReplied = Boolean.valueOf(request.getParameter("queryReplied"));
		Boolean articleArrivalOOE = Boolean.valueOf(request.getParameter("articleArrivalOOE"));
		Boolean oocGiven = Boolean.valueOf(request.getParameter("oocGiven"));
		String senderCountry = request.getParameter("senderCountry") != null
				&& !request.getParameter("senderCountry").equalsIgnoreCase("") ? request.getParameter("senderCountry")
						: null;
		String mailCategory = request.getParameter("mailCategory") != null
				&& !request.getParameter("mailCategory").equalsIgnoreCase("") ? request.getParameter("mailCategory")
						: null;
		String mailClass = request.getParameter("mailClass") != null
				&& !request.getParameter("mailClass").equalsIgnoreCase("") ? request.getParameter("mailClass") : null;*/
		String articleId = request.getParameter("articleId") != null
				&& !request.getParameter("articleId").equalsIgnoreCase("") ? request.getParameter("articleId") : null;
		
        String sitecode =request.getSession().getAttribute("LSMcuSite").toString();
		
		boolean checkArtID=fpoRepost.checkArtId(articleId)>0;
		boolean checkArtID1=fpoRepost.checkArtId1(articleId, sitecode)>0;
		
		jsonObj.put("checkArtID", checkArtID);
		jsonObj.put("checkArtID1", checkArtID1);


		List<ArticleDatas> articleDatas = new ArrayList<ArticleDatas>();
	//	if (fromDate != null || toDate != null || senderCountry != null || mailCategory != null || mailClass != null
	//			|| articleId != null) {
		/*	articleDatas = viewArticleService.getArticleDatas(fromDate, toDate, itemPosted, eadArrival, queryRaised,
					queryReplied, articleArrivalOOE, oocGiven, senderCountry, mailCategory, mailClass, articleId,
					session,request);*/
		articleDatas = viewArticleService.getArticleDatas(articleId,session,request);
	//	}
		articleDatas.forEach(articles -> {
			try {
				articles.setStatus(fpoService.getpos( articles.getItemId(), session,request));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
		if (articleId == null)
			jsonObj.put("", articleDatas);
		else
		    jsonObj.put("data", articleDatas);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/getArticleDates")
	public @ResponseBody void getArticleDates(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		ViewArticle viewarticle = fpoRepost.getArticleDates(id);
		viewarticle.setStatus(fpoService.getpos(itemId, session,request));
		jsonObj.put("viewarticle", new JSONObject(viewarticle));
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}
	
	
	@RequestMapping(value = "/getbagdata")
	public @ResponseBody ModelAndView getbagdata(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("article/bag-articles_itemid");
		String itemid = request.getParameter("itemId");
		String cusite = request.getSession().getAttribute("cuSite").toString();
		List<Collection> arrinfo = null;
		arrinfo = fpoRepost.getbaginfo(itemid,cusite);
		String currstatus=fpoService.getpos(itemid, session, request);
		models.addObject("arrinfo",arrinfo);
		models.addObject("currstatus",currstatus);
		return models;
	}

	@PostMapping(value = "/getSummary")
	public ModelAndView getSummary(HttpServletRequest request, Model model) {
		ModelAndView models = new ModelAndView("article/summary");
		try {
			String itemId = request.getParameter("id");
			String id = fpoRepost.getCinIdByItemId(itemId);
			List<FPO_MAIN> fpoMain = fpoRepost.getmain(id);
			System.out.println(fpoMain.get(0).getNATURE_TYPE_CD());
			System.out.println(id);
			String itcat = fpoRepost.getitcat(id, fpoMain.get(0).getNATURE_TYPE_CD());
			String fpomain1 = fpoRepost.getpostdt1(id);
			List<FPO_ITEM_DET> itemlist = fpoItemRepost.getItem(id);
		//	fpoService.setFpoMainValues(fpoMain);
			fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
	//		model.addAttribute("check", fpoService.getPojoMain(fpoMain.get(0)));
			model.addAttribute("check", fpoMain.get(0));
			model.addAttribute("itcat", itcat);
			model.addAttribute("fpomain1", fpomain1);
			model.addAttribute("itemlist", itemlist);
			model.addAttribute("asscou", fpoItemRepost.getasscompCount(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}


	/*@PostMapping(value = "/getExamOrder")
	public ModelAndView getExamOrder(HttpServletRequest request, Model model) throws Exception {
		ModelAndView models = new ModelAndView("article/exam-order");
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
			} else {
				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
				}
				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
				}
				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
				}
			}
		} else {
			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}
		model.addAttribute("getOrder", fpoorder);
		return models;
	}*/
	
	/*@PostMapping(value = "/getExamOrder")
	public ModelAndView getExamOrder(HttpServletRequest request, Model model) throws Exception {
		ModelAndView models = new ModelAndView("article/exam-order");
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
			} else {
				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
				}
				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
				}
				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
				}model.addAttribute("order", 1);
			}
		} else {
			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
			model.addAttribute("order", 0);
		}
		model.addAttribute("getOrder", fpoorder);
		return models;
	}*/
	
	@PostMapping(value = "/getExamOrder")
	public ModelAndView getExamOrder(HttpServletRequest request, Model model) throws Exception {
		ModelAndView models = new ModelAndView("article/exam-order");
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		String qrylevel = request.getParameter("qrylevel");
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		model.addAttribute("qrylevel",qrylevel);
		Long examorder = fpoOrderRepost.examOrder1(itemId);
		Long examorderead = fpoOrderRepost.geteadgen(itemId, qrylevel);
		System.out.println(examorderead);
		Long examorder1 = fpoOrderRepost.examOrder2(itemId);
		Long examorderaaa = fpoOrderRepost.getaaagen(itemId, qrylevel);
			System.out.println(examorderaaa);
			if(!fpoorder.isEmpty()) {
				String data = fpoorder.get(0).getEXAM_ORDER();
				if (null != data && data.equals("Detain,Examination")) {
					String exam = data;
					String array2[] = exam.split(",");
					fpoorder.get(0).setExam2(array2[0]);
					fpoorder.get(0).setExam3(array2[1]);
				} else{
					if (null != data && data.equals("Scan")) {
						fpoorder.get(0).setExam1(data);
					}
					if (null != data && data.equals("Detain")) {
						fpoorder.get(0).setExam2(data);
					}
					if (null != data && data.equals("Examination")) {
						fpoorder.get(0).setExam3(data);
					}
				}
			}else {
				FPO_ORDER fpoQueryOrder = new FPO_ORDER();
				fpoQueryOrder.setORDER_REMARK("");
				model.addAttribute("getOrder", fpoQueryOrder);
				model.addAttribute("order", 0);
			}
				
			

		
		
		if(qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA") ) {
				 if(examorder > 0 && !fpoorder.isEmpty()) {
				model.addAttribute("getOrder", fpoorder);
				model.addAttribute("order",1);
				
			}else {
				model.addAttribute("order1",0);
			}
		}else if(qrylevel.equalsIgnoreCase("AAA") && !qrylevel.equalsIgnoreCase("EAD") ) {
			 if(examorder1 > 0 && !fpoorder.isEmpty()) {
					model.addAttribute("getOrder", fpoorder);
					model.addAttribute("order",1);
					
				}else {
					model.addAttribute("order1",0);
				}
		}
		
		return models;
	}


	/*@PostMapping("/getqueryandreply")
	public ModelAndView getQueryAndReply(HttpServletRequest request, Model model, HttpSession session) {
		String itemId = request.getParameter("id");
		String cinNo = fpoRepost.getcin_no(itemId);
		String qrylevel = request.getParameter("qrylevel");
		model.addAttribute("qrylevel", qrylevel);
		model.addAttribute("cinNo", cinNo);
		Long counoqry = fpoRepost.getcounoqry(cinNo);
		model.addAttribute("qryraised", counoqry > 0);
		if (counoqry > 0) {
			String qryType = fpoRepost.getQryType(cinNo);
			if (qryType.equalsIgnoreCase("E") && !qrylevel.equalsIgnoreCase("AAA") && !qrylevel.equalsIgnoreCase("AAF") ) {
				model.addAttribute("eadFirstQueryRaised", true);
				Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
				model.addAttribute("eadFirstQueryReplied", counoqry == counoqryreply);
				String eadfilename = reportService.getFileNameForWithoutRly(itemId, "EAD", "max");
				model.addAttribute("eadFirstQueryFileName", eadfilename);
			} else {
				model.addAttribute("aafFirstQueryRaised",
						true && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
				String aaffirstfilename = reportService.getFileNameForWithoutRly(itemId, "AAF", "min");
				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
				model.addAttribute("aafFirstQueryReplied", counoqry == counoqryreply
						&& !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
			}
			Long addaaacounoqry = fpoAddlQryRepo.getaddcounoqry(cinNo, "N");
			if (addaaacounoqry > 0) {
				model.addAttribute("aaaAdditionalQueryRaised", true && !qrylevel.equalsIgnoreCase("EAD"));
				String aaffilename = reportService.getFileNameForWithoutRly(itemId, "AAA", "max");
				model.addAttribute("AAAAdditionQueryFileName", aaffilename);
				Long addaaacounoqryreply = fpoAddlQryRepo.getcounoqryreply(cinNo, "N");
				model.addAttribute("aaaAdditionalQueryReplied",
						addaaacounoqry == addaaacounoqryreply && !qrylevel.equalsIgnoreCase("EAD"));
			}
			Long addaafcounoqry = fpoAddlQryRepo.getaddcounoqry(cinNo, "P");
			if (addaafcounoqry > 0) {
				model.addAttribute("aafAdditionalQueryRaised",
						true && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				String aaffilename = reportService.getFileNameForWithoutRly(itemId, "AAF", "max");
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				Long addcounoqryreply = fpoAddlQryRepo.getcounoqryreply(cinNo, "P");
				model.addAttribute("aafAdditionalQueryReplied", addaafcounoqry == addcounoqryreply
						&& !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
			}
		}
		return new ModelAndView("article/ead-query");
	}*/
	
	
	@PostMapping("/getqueryandreply")
	public ModelAndView getQueryAndReply(HttpServletRequest request, Model model, HttpSession session) {
		String itemId = request.getParameter("id");
		String cinNo = fpoRepost.getcin_no(itemId);
		String qrylevel = request.getParameter("qrylevel");
		System.out.println("djkbsdkjvbkj   " + qrylevel);
		model.addAttribute("qrylevel", qrylevel);
		model.addAttribute("cinNo", cinNo);
		Long counoqry = fpoRepost.getcounoqry(cinNo);
		model.addAttribute("qryraised", counoqry > 0);
		Long arrscaninfo = fpoRepost.getarrinfo(itemId);
		model.addAttribute("arrscaninfo", arrscaninfo);
		Long aaageninfo =  fpoRepost.getaaagen(itemId);
		model.addAttribute("aaageninfo", aaageninfo);
		Long aafgeninfo =  fpoRepost.getaaagenaaf(itemId);
		model.addAttribute("aafgeninfo", aafgeninfo);
		String cuSite =  request.getSession().getAttribute("cuSite").toString();
//		System.out.println("dsdsjbkjsdbk     " + arrscaninfo);
		
		if (counoqry > 0) {
			String qryType = fpoRepost.getQryType(cinNo);
			if (qryType.equalsIgnoreCase("E") && !qrylevel.equalsIgnoreCase("AAA") && !qrylevel.equalsIgnoreCase("AAF") ) {
				model.addAttribute("eadFirstQueryRaised", true);
				Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
				model.addAttribute("eadFirstQueryReplied", counoqry == counoqryreply);
				String eadfilename = reportService.getFileNameForWithoutRly(itemId, "EAD", "max",cuSite);
				model.addAttribute("eadFirstQueryFileName", eadfilename);
				List<FpoQuery> getDefQry = fpoQueryRepo.getoffidQry(itemId);
				model.addAttribute("offid", getDefQry.size() > 0 ? getDefQry.get(0): null);
				
			} else if(arrscaninfo == 1) {
				model.addAttribute("aafFirstQueryRaised",
						true && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
				String aaffirstfilename = reportService.getFileNameForWithoutRly(itemId, "AAF", "min",cuSite);
				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
				model.addAttribute("aafFirstQueryReplied", counoqry == counoqryreply
						&& !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				model.addAttribute("isY" , 1);
			}
			else if(qrylevel.equalsIgnoreCase("AAF") && arrscaninfo == 0) {
				model.addAttribute("isY" , 0);
			}
			Long addaaacounoqry = fpoAddlQryRepo.getaddcounoqry(cinNo, "N");
			Long addaaacounoqry1 = fpoAddlQryRepo.getaddcounoqry1(cinNo, "N");
			if (addaaacounoqry > 0  && aaageninfo > 0) {
				model.addAttribute("aaaAdditionalQueryRaised", true && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAF"));
				String aaffilename = reportService.getFileNameForWithoutRly(itemId, "AAA", "max",cuSite);
				model.addAttribute("AAAAdditionQueryFileName", aaffilename);
				Long addaaacounoqryreply = fpoAddlQryRepo.getcounoqryreply(cinNo, "N");
				Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
				model.addAttribute("eadFirstQueryReplied", counoqry == counoqryreply && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAF"));
				if(addaaacounoqry1 > 0) {
					model.addAttribute("aaaAdditionalQueryReplied",
							addaaacounoqry1 == addaaacounoqryreply && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAF"));
				}else {
					model.addAttribute("aaaAdditionalQueryReplied",
							addaaacounoqry == addaaacounoqryreply && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAF"));
				}

			}
			Long addaafcounoqry = fpoAddlQryRepo.getaddcounoqry(cinNo, "P");
			Long addaafcounoqry1 = fpoAddlQryRepo.getaddcounoqry2(cinNo, "P");
			if (addaafcounoqry > 0  && arrscaninfo == 1 && aafgeninfo > 0) {
				model.addAttribute("aafAdditionalQueryRaised",
						true && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				String aaffilename = reportService.getFileNameForWithoutRly(itemId, "AAF", "max",cuSite);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				Long addcounoqryreply = fpoAddlQryRepo.getcounoqryreply(cinNo, "P");
				if(addaafcounoqry1 > 0) {
					model.addAttribute("aafAdditionalQueryReplied", addaafcounoqry1 == addcounoqryreply
							&& !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				}else {
					model.addAttribute("aafAdditionalQueryReplied", addaafcounoqry == addcounoqryreply
							&& !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAA"));
				}
			}
		}
		return new ModelAndView("article/ead-query");
	}

	
	/*@PostMapping("/getAssessment")
	public ModelAndView getAssessment(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		Boolean ead = Boolean.parseBoolean(request.getParameter("ead"));
		model.addAttribute("ead", ead);
		if (ead) {
			String cinNo = fpoRepost.getCinIdByItemId(itemId);
			List<FpoStatus> fpostatus = fpoStatusRepo.geteadDetail(itemId);
			model.addAttribute("eadDetail", fpostatus.size() > 0 ? fpostatus.get(0) : null);
			List<Collection> grandtot =  new ArrayList<>();
			grandtot = fpoRepost.getgrandtot(cinNo);
			model.addAttribute("gtot", grandtot);
			model.addAttribute("fpoItems", fpoItemRepost.getItemByIdNo(fpoRepost.getCinIdByItemId(itemId)));
			model.addAttribute("itemId", itemId);
		}
		return new ModelAndView("article/fpo-assessment");
	}*/
	
//	@PostMapping("/getAssessment")
//	public ModelAndView getAssessment(HttpServletRequest request, Model model) throws Exception {
//		String itemId = request.getParameter("id");
//		Boolean ead = Boolean.parseBoolean(request.getParameter("ead"));
//		model.addAttribute("ead", ead);
//		if (ead) {
//			String cinNo = fpoRepost.getCinIdByItemId(itemId);
//			List<FpoStatus> fpostatus = fpoStatusRepo.geteadDetail(itemId);
//			model.addAttribute("eadDetail", fpostatus.size() > 0 ? fpostatus.get(0) : null);
//			model.addAttribute("fpoItems", fpoItemRepost.getItemByIdNo(fpoRepost.getCinIdByItemId(itemId)));
//			model.addAttribute("itemId", itemId);
//		}
//		return new ModelAndView("article/fpo-assessment");
//	}

	
	@PostMapping("/getAssessment")
	public ModelAndView getAssessment(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		Boolean ead = Boolean.parseBoolean(request.getParameter("ead"));
		model.addAttribute("ead", ead);
		if (ead) {
			String cinNo = fpoRepost.getCinIdByItemId(itemId);
			List<FpoStatus> fpostatus = fpoStatusRepo.geteadDetail(itemId);
			
			if(fpostatus.isEmpty()) {
				fpostatus = fpoStatusRepo.geteadDetailacl(itemId);
				model.addAttribute("eadDetail", fpostatus.size() > 0 ? fpostatus.get(0) : null);
				model.addAttribute("eaddetailacl",1);
			}else {
				model.addAttribute("eadDetail", fpostatus.size() > 0 ? fpostatus.get(0) : null);
				model.addAttribute("eaddetailacl",0);
			}
			
			model.addAttribute("fpoItems", fpoItemRepost.getItemByIdNo(fpoRepost.getCinIdByItemId(itemId)));
			model.addAttribute("itemId", itemId);
		}
		return new ModelAndView("article/fpo-assessment");
	}
	
	@PostMapping("/getEadQuery")
	public ModelAndView getEadQuery(HttpServletRequest request, Model model, HttpSession session) {
		String itemId = request.getParameter("id");
		boolean qryReply = Boolean.parseBoolean(request.getParameter("reply"));
		String cinNo = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_MAIN> fpoMain = fpoRepost.getmain(cinNo);
		List<FpoQuery> getDefQry = fpoQueryRepo.getDefQry(cinNo);
		String getDepcmts = fpoQueryRepo.getDepcmts(cinNo);
		Float maxaclassval = fpoRepost.getaclassval();
		if (null != getDefQry.get(0).getDEFUALT_QUERY()) {
			String getQuery = getDefQry.get(0).getDEFUALT_QUERY();
			String[] array1 = getQuery.split(";");
			getDefQry.get(0).setDefqry1(array1[0]);
			getDefQry.get(0).setDefqry2(array1[1]);
			getDefQry.get(0).setDefqry3(array1[2]);
			getDefQry.get(0).setDefqry4(array1[3]);
		}
		List<FpoQueryDin> getAllcmts = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
		List<FpoQueryDin> allcmtsList = new ArrayList<>();
		FpoQueryDin fpoQueryDin = new FpoQueryDin();
		fpoQueryDin.setRemarks("");
		fpoQueryDin.setDepComments("");
		allcmtsList.add(fpoQueryDin);
		Boolean back = Boolean.valueOf(request.getParameter("back"));
		if (!back)
			fpoCurQueService.addUserQue(cinNo, fpoMain.get(0).getITEM_ID(), "N2", session,request);
		fpoService.setFpoMainValues(fpoMain);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
		model.addAttribute("head", (fpoMain.get(0)));
		model.addAttribute("getAllFpoQuery", getAllFpoQuery);
		model.addAttribute("getDefQry", getDefQry);
		model.addAttribute("assvalacl", maxaclassval);
		model.addAttribute("emailid", getDefQry.get(0).getEmail_Id());
		model.addAttribute("phoneno", getDefQry.get(0).getMobile_No());
		if (qryReply) {
			model.addAttribute("getDepcmts", getDepcmts);
		}

		if (null != getAllcmts && !getAllcmts.isEmpty())
			model.addAttribute("getAllcmts", getAllcmts);
		else
			model.addAttribute("getAllcmts", allcmtsList);

		return new ModelAndView("article/ead-query");
	}

	@PostMapping("/getEadDecision")
	public ModelAndView getEadDecision(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		String cinNo = fpoRepost.getCinIdByItemId(itemId);
		List<DECI_REAS> reasons = deciReasonRepost.getREAS();
		model.addAttribute("cinNo", cinNo);
		model.addAttribute("reason", reasons);
		String decisionNo = fpoQueryDecisionRepository.getDecision(cinNo);
		if (decisionNo == null) {
			throw new Exception();
		}
		model.addAttribute("decisionNo", decisionNo);
		return new ModelAndView("article/ead-decision");
	}

	/*@PostMapping("/getAssessment")
	public ModelAndView getAssessment(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		model.addAttribute("fpoItems", fpoItemRepost.getItemByIdNo(fpoRepost.getCinIdByItemId(itemId)));
		model.addAttribute("itemId", itemId);
		return new ModelAndView("article/fpo-assessment");
	}*/

	@PostMapping("/getDetentionOrOOC")
	public ModelAndView getDetentionOrOOC(HttpServletRequest request, Model model) {
		String itemId = request.getParameter("id");
		Boolean ooc = Boolean.parseBoolean(request.getParameter("ooc"));
		model.addAttribute("ooc", ooc);
		if (ooc) {
			String cinNo = fpoRepost.getCinIdByItemId(itemId);
			List<FpoStatus> fpostatus = fpoStatusRepo.getOocDetail(itemId);
			model.addAttribute("oocDetail", fpostatus.size() > 0 ? fpostatus.get(0) : null);
	//		model.addAttribute("gtot", reportService.getgrandtot(cinNo));
			List<Collection> grandtot =  new ArrayList<>();
			//grandtot = reportService.getgrandtot(cinNo);
			grandtot = fpoRepost.getgrandtot(cinNo);
			model.addAttribute("gtot", grandtot);
			model.addAttribute("item", fpoRepost.getitem(cinNo));
		} else {
			model.addAttribute("detentionTrack", reportService.getDetainDetail(itemId));
		}
		return new ModelAndView("article/detention-ooc");
	}

	@PostMapping("/getDeliveryAcknowledgementOfPost")
	public ModelAndView getDeliveryAcknowledgementOfPost(HttpServletRequest request, Model model) {
		String itemId = request.getParameter("id");
		String cinNo = fpoRepost.getCinIdByItemId(itemId);
		List<FpoStatus> fpostatus = fpoStatusRepo.getOocDetail(itemId);
		if (!fpostatus.isEmpty()) {
			List<DeliveryArticlesColumns> deliveryarticles = reportService.getDeliveryArticlesColumns(itemId);
			model.addAttribute("deliveryarticles", deliveryarticles);
		}
		model.addAttribute("ooc", fpostatus.size()>0);
		return new ModelAndView("Report/deliveryacknowledgement");
	}
	
	
	
	
	
/*	@PostMapping("/getExamFindings")
	public ModelAndView getExamFindings(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		long something = fpoExamFindingsRepost.getFindingData(itemId);
		model.addAttribute("something", something);
		if(something > 0) {
		if (!fpoorder.isEmpty() ) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				
			} else {
				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
				}
				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
				}
				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					
				}model.addAttribute("order", 1);
				
			}
			
		} else {
			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
			model.addAttribute("order", 0);
			
		}
		List<FPO_EXAM> examFindings = fpoExamFindingsRepost.findMaxByCinNo(fpoRepost.getCinIdByItemId(itemId));
		List<FPO_EXAM> examFindingsForItems = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() != null && findings.getItem_no() != null;
		}).collect(Collectors.toList());
		List<FPO_EXAM> examFindingsResponse = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() == null && findings.getItem_no() == null;
		}).collect(Collectors.toList());
		if (!examFindingsResponse.isEmpty()) {
			model.addAttribute("officerId", examFindingsResponse.get(0).getOff_id());
			model.addAttribute("responseMsg", examFindingsResponse.get(0).getRmks());
			model.addAttribute("detain", examFindingsResponse.get(0).getDetain());
		}
		
		model.addAttribute("getOrder", fpoorder);
		model.addAttribute("examFindingsForItems", examFindingsForItems);
		model.addAttribute("noExamFindings", 1);
		}
		else {
			model.addAttribute("noExamFindings", 0);
		}
		return new ModelAndView("article/exam-findings");
		
	}*/
	
	
	@PostMapping("/getExamFindings")
	public ModelAndView getExamFindings(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		Long something = fpoExamFindingsRepost.getFindingData(itemId);
		model.addAttribute("something", something);
		Long arrscandt =  fpoExamFindingsRepost.examorderarrscandt(itemId);
		if(something > 0 || arrscandt > 0) {
		if (!fpoorder.isEmpty() ) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
				
			} else {
				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
				}
				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
				}
				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
					
				}model.addAttribute("order", 1);
				
			}
			
		} else {
			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
			model.addAttribute("order", 0);
			
		}
		List<FPO_EXAM> examFindings = fpoExamFindingsRepost.findMaxByCinNo(fpoRepost.getCinIdByItemId(itemId));
		List<FPO_EXAM> examFindingsForItems = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() != null && findings.getItem_no() != null;
		}).collect(Collectors.toList());
		List<FPO_EXAM> examFindingsResponse = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() == null && findings.getItem_no() == null;
		}).collect(Collectors.toList());
		if (!examFindingsResponse.isEmpty()) {
			model.addAttribute("officerId", examFindingsResponse.get(0).getOff_id());
			model.addAttribute("responseMsg", examFindingsResponse.get(0).getRmks());
			model.addAttribute("detain", examFindingsResponse.get(0).getDetain());
		}
		
		model.addAttribute("getOrder", fpoorder);
		model.addAttribute("examFindingsForItems", examFindingsForItems);
		model.addAttribute("noExamFindings", 1);
		}
		else {
			model.addAttribute("noExamFindings", 0);
		}
		return new ModelAndView("article/exam-findings");
		
	}


/*	@PostMapping("/getExamFindings")
	public ModelAndView getExamFindings(HttpServletRequest request, Model model) throws Exception {
		String itemId = request.getParameter("id");
		String id = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(id);
		if (!fpoorder.isEmpty()) {
			String data = fpoorder.get(0).getEXAM_ORDER();
			if (null != data && data.equals("Detain,Examination")) {
				String exam = data;
				String array2[] = exam.split(",");
				fpoorder.get(0).setExam2(array2[0]);
				fpoorder.get(0).setExam3(array2[1]);
			} else {
				if (null != data && data.equals("Scan")) {
					fpoorder.get(0).setExam1(data);
				}
				if (null != data && data.equals("Detain")) {
					fpoorder.get(0).setExam2(data);
				}
				if (null != data && data.equals("Examination")) {
					fpoorder.get(0).setExam3(data);
				}
			}
		} else {
			FPO_ORDER fpoQueryOrder = new FPO_ORDER();
			fpoQueryOrder.setORDER_REMARK("");
			model.addAttribute("getOrder", fpoQueryOrder);
		}
		List<FPO_EXAM> examFindings = fpoExamFindingsRepost.findMaxByCinNo(fpoRepost.getCinIdByItemId(itemId));
		List<FPO_EXAM> examFindingsForItems = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() != null && findings.getItem_no() != null;
		}).collect(Collectors.toList());
		List<FPO_EXAM> examFindingsResponse = examFindings.stream().filter(findings -> {
			return findings.getItem_fou() == null && findings.getItem_no() == null;
		}).collect(Collectors.toList());
		if (!examFindingsResponse.isEmpty()) {
			model.addAttribute("officerId", examFindingsResponse.get(0).getOff_id());
			model.addAttribute("responseMsg", examFindingsResponse.get(0).getRmks());
			model.addAttribute("detain", examFindingsResponse.get(0).getDetain());
		}
		model.addAttribute("getOrder", fpoorder);
		model.addAttribute("examFindingsForItems", examFindingsForItems);
		return new ModelAndView("article/exam-findings");
	}*/
	
	
	
	
	

	@PostMapping("/viewItemDetails")
	public ModelAndView viewItemDetails(HttpServletRequest request, Model model) {
		String itemId = request.getParameter("itemId");
		Long itemNo = Long.valueOf(request.getParameter("itemNo"));
		String cinNo = fpoRepost.getCinIdByItemId(itemId);
		List<FPO_ITEM_DET> fpoItemsCth = fpoItemRepost.getItemByIdNo(cinNo, itemNo);
		List<FpoItemDetOthDuty> fpoItemOthers = fpoItemDetOthDutyRepo.getOthrOnCinNorItem(cinNo, itemNo);
		List<FpoItemDetOthDuty> fpoItemsCthManual = fpoItemOthers.stream().filter(otherItem -> {
			return otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804");
		}).collect(Collectors.toList());
		List<FpoItemDetOthDuty> fpoItemsCthOthers = fpoItemOthers.stream().filter(otherItem -> {
			return (!otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804") && otherItem.getDUTY_DESC() == null
					&& otherItem.getDUTY_CD() != null);
		}).collect(Collectors.toList());
		fpoItemsCthOthers.forEach(fpoItemsCthOther -> {
			fpoItemsCthOther.setDUTY_DESC(fpoItemRepost.getDUTYOnCd(fpoItemsCthOther.getDUTY_CD()));
		});
		List<FpoItemDetOthDuty> fpoItemsCthOtherManual = fpoItemOthers.stream().filter(otherItem -> {
			return (!otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804") && otherItem.getDUTY_DESC() != null
					&& otherItem.getDUTY_CD() == null);
		}).collect(Collectors.toList());
		List<FpoFine> fpoItemFines = fpoFineRepo.getAllFine(cinNo);
		List<FpoPenal> fpoItemPenalties = fpoPenalRepo.getAllPenalty(cinNo);
		model.addAttribute("fpoItemsCth", fpoItemsCth);
		model.addAttribute("fpoItemsCthManual", fpoItemsCthManual);
		model.addAttribute("fpoItemsCthOthers", fpoItemsCthOthers);
		model.addAttribute("fpoItemsCthOtherManual", fpoItemsCthOtherManual);
		model.addAttribute("fpoItemFines", fpoItemFines);
		model.addAttribute("fpoItemPenalties", fpoItemPenalties);

		List<FPO_AMEND> fpoAmendItemsCth = fpoAmendRepost.latestAmendOnCinAndItemNo(cinNo, itemNo);
		List<FpoItemDetOthDutyAmend> fpoAmendItemOthers = fpoItemDetOthDutyAmendRepo.getOtherItemsAmend(cinNo, itemNo);
		List<FpoItemDetOthDutyAmend> fpoAmendItemsCthManual = fpoAmendItemOthers.stream().filter(otherItem -> {
			return otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804");
		}).collect(Collectors.toList());
		List<FpoItemDetOthDutyAmend> fpoAmendItemsCthOthers = fpoAmendItemOthers.stream().filter(otherItem -> {
			return (!otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804") && otherItem.getDUTY_DESC() == null
					&& otherItem.getDUTY_CD() != null);
		}).collect(Collectors.toList());
		fpoAmendItemsCthOthers.forEach(fpoAmendItemsCthOther -> {
			fpoAmendItemsCthOther.setDUTY_DESC(fpoItemRepost.getDUTYOnCd(fpoAmendItemsCthOther.getDUTY_CD()));
		});
		List<FpoItemDetOthDutyAmend> fpoAmendItemsCthOtherManual = fpoAmendItemOthers.stream().filter(otherItem -> {
			return (!otherItem.getCTH().substring(0, 4).equalsIgnoreCase("9804") && otherItem.getDUTY_DESC() != null
					&& otherItem.getDUTY_CD() == null);
		}).collect(Collectors.toList());
		List<FpoFineAmend> fpoAmendItemFines = fpoFineAmendRepo.getAllFine(cinNo);
		List<FpoPenalAmend> fpoAmendItemPenalties = fpoPenalAmendRepo.getAllPenalty(cinNo);
		model.addAttribute("fpoAmendItemsCth", fpoAmendItemsCth);
		model.addAttribute("fpoAmendItemsCthManual", fpoAmendItemsCthManual);
		model.addAttribute("fpoAmendItemsCthOthers", fpoAmendItemsCthOthers);
		model.addAttribute("fpoAmendItemsCthOtherManual", fpoAmendItemsCthOtherManual);
		model.addAttribute("fpoAmendItemFines", fpoAmendItemFines);
		model.addAttribute("fpoAmendItemPenalties", fpoAmendItemPenalties);
		model.addAttribute("amended", (!fpoAmendItemsCth.isEmpty() || !fpoAmendItemOthers.isEmpty()));
		return new ModelAndView("article/item-details");
	}

	@PostMapping(value = "/getDcallLetter")
	public @ResponseBody void getDcallLetter(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		String itemId = request.getParameter("itemId");
		File folder = new File(
				"D:\\Karthick\\source\\fpoassess_aug12\\fpoassess_jun11\\src\\main\\resources\\static\\pdf\\");
		File[] fileNames = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.startsWith(itemId);
			}
		});
		if (fileNames.length > 0) {
			jsonObj.put("fileName", fileNames[0].getName());
			jsonObj.put("status", "Success");
		} else {
			jsonObj.put("filePath", "");
			jsonObj.put("status", "Failure");
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/getdcallLetter")
	public ModelAndView getdcallLetter(HttpServletRequest request, Model model, HttpServletResponse response,HttpSession session
			) {
		String itemId = request.getParameter("id");
		String cinNo = fpoRepost.getCinIdByItemId(itemId);
		Long counoqry = fpoRepost.getcounoqry(cinNo);
		Long counoqryreply = fpoRepost.getcounoqryreply(cinNo);
		model.addAttribute("qryraised", counoqry > 0 && counoqry == counoqryreply ? true : false);
		if (counoqry > 0 && counoqry == counoqryreply) {
			String qryType = fpoRepost.getQryType(cinNo);
			boolean aafFirstQueryRaised = false;
			if (qryType.equalsIgnoreCase("E")) {
				model.addAttribute("eadFirstQueryRaised", true);
			} else {
				model.addAttribute("aafFirstQueryRaised", true);
				String aaffirstfilename = reportService.getFileNameForAAFRly(itemId, "AAF");
				model.addAttribute("AAFFirstQueryFileName", aaffirstfilename);
				aafFirstQueryRaised = true;
			}
			if (aafFirstQueryRaised) {
				if (reportService.getAAFCountQuery(itemId, "AAF")) {
					String aaffilename = reportService.getFileNameForRly(itemId, "AAF", session, request);
					model.addAttribute("AAFAdditionQueryFileName", aaffilename);
					model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
				}
			} else {
				String aaffilename = reportService.getFileNameForRly(itemId, "AAF", session, request);
				model.addAttribute("AAFAdditionQueryFileName", aaffilename);
				model.addAttribute("AAFAdditionQueryFileExist", aaffilename.equalsIgnoreCase("") ? false : true);
			}
			String aaafilename = reportService.getFileNameForRly(itemId, "AAA", session, request);
			model.addAttribute("AAAAdditionQueryFileName", aaafilename);
			model.addAttribute("AAAAdditionQueryFileExist", aaafilename.equalsIgnoreCase("") ? false : true);
		}

		ModelAndView modelAndView = new ModelAndView("import/dcallletter");
		return modelAndView;
	}

	@PostMapping(value = "/saveAdditionalQuery")
	public @ResponseBody void saveAdditionalQuery(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinNo = request.getParameter("cinNo");
			String query = request.getParameter("query");
			String dinNo = request.getParameter("dinNo");
			String email = request.getParameter("email") != null && !request.getParameter("email").equalsIgnoreCase("")
					? request.getParameter("email")
					: null;
			String mobile = request.getParameter("mobile") != null
					&& !request.getParameter("mobile").equalsIgnoreCase("") ? request.getParameter("mobile") : null;
			List<FPO_MAIN> fpoMainList = fpoRepost.getmain(cinNo);
			if (!fpoMainList.isEmpty()) {
				FPO_MAIN fpoMain = fpoMainList.get(0);
				List<FpoQuery> articleAwaitingQuery = fpoQueryRepo.getArticleAwaitingQuery(cinNo);
				FpoQuery fpoQuery = new FpoQuery();
				fpoQuery.setITEM_ID(fpoMain.getITEM_ID());
				fpoQuery.setCUS_SITE(
						request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
				fpoQuery.setQRY(query);
				fpoQuery.setQRY_TYP("P");
				fpoQuery.setQRY_DATE(new Date());
				fpoQuery.setQRY_OFF_ID(
						request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
				fpoQuery.setQRY_ROLE(
						request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
				fpoQuery.setCinNo(cinNo);
				if (email != null)
					fpoQuery.setEmail_Id(email);
				if (mobile != null)
					fpoQuery.setMobile_No(mobile);
				if (!articleAwaitingQuery.isEmpty()) {
					fpoQuery.setId(articleAwaitingQuery.get(0).getId());
				}
				fpoQuery.setQRY_NO(fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo) + 1);
				fpoQueryRepo.save(fpoQuery);
			//	fpoQueryDinRepo.updateAdditionQueryDinNo(cinNo, dinNo);
				jsonObj.put("status", "Success");
			}
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

//	@RequestMapping(value = "/raiseqryreply", produces = {
//			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
//	public @ResponseBody void raiseqryreply(@ModelAttribute FPOSecondDefQry fPOSecondDefQry,@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det, HttpServletRequest request,
//			HttpServletResponse response, Model model, HttpSession session) {
//		fpoDeclaredService.addFpoAddQry(fPOSecondDefQry,fpo_item_det, session,request);
//	}
	
	@RequestMapping(value = "/raiseqryreply", produces = {	
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)	
	public @ResponseBody void raiseqryreply(@ModelAttribute FPOSecondDefQry fPOSecondDefQry,@ModelAttribute("fpo_item_det") FPO_ITEM_DET fpo_item_det,	
			FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt,HttpServletRequest request,HttpServletResponse response, Model model, HttpSession session) {	
		fpoDeclaredService.updatemvmtandqrydeci(fpoqryDec, fpomvmnt, session, request);	
		fpoDeclaredService.addFpoAddQry(fPOSecondDefQry,fpo_item_det, session,request);	
	}	


	@GetMapping(value = "/getArticleAwaitingQuery")
	public @ResponseBody void getArticleAwaitingQuery(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinNo = request.getParameter("cinNo");
			String qrytype = request.getParameter("qrytype");
			// List<FpoQuery> articleAwaitingQuery =
			// fpoQueryRepo.getArticleAwaitingQuery(cinNo);

			List<FpoAddlQry> articleAwaitingQuery = fpoAddlQryRepo.getArticleAwaitingQuery(cinNo, qrytype);
			if (!articleAwaitingQuery.isEmpty()) {
				// List<FpoQueryDin> articleAwaitingQueryDin =
				// fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
				jsonObj.put("status", "Success");
				jsonObj.put("fpoQuery", new JSONObject(articleAwaitingQuery.get(0)));
				jsonObj.put("dinNo", articleAwaitingQuery.get(0).getDIN1());
			} else {
				jsonObj.put("status", "Failure");
			}
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@GetMapping(value = "/getaddlqrydetail")
	public @ResponseBody void getaddlqrydetail(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String cinNo = request.getParameter("cinNo");

			List<FpoScndQryData> viewarticle = viewArticleService.getFpoScndQryDataDoc(cinNo);

			if (!viewarticle.isEmpty()) {
				// List<FpoQueryDin> articleAwaitingQueryDin =
				// fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
				jsonObj.put("status", "Success");
				jsonObj.put("fpoaddlQuery", new JSONArray(viewarticle));
			} else {
				jsonObj.put("status", "Failure");
			}
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@GetMapping("/getArticleAwaitingDCallLetter")
	public ModelAndView getArticleAwaitingDCallLetter(HttpServletRequest request, Model model) {
		String cinNo = request.getParameter("cinNo");
		List<FPO_MAIN> fpoMainData = fpoRepost.getmain(cinNo);
		String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String additionalQuery = fpoQueryRepo.getAdditionalQuery(cinNo);
		String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);

//		fpoService.fpo_qry_crpdf(fpo_item_det.getCinNo());

		ModelAndView modelAndView = new ModelAndView("articleawaiting/call-letter");
		model.addAttribute("fpoMainData", fpoMainData.get(0));
		model.addAttribute("callMemo", getAllFpoQuery);
		model.addAttribute("getRemarks", getRemarks);
		model.addAttribute("additionalQuery", additionalQuery);
		model.addAttribute("defualtQueryList", defualtQueryList);
		model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo).get(0));
		return modelAndView;
	}

	@GetMapping("/getEadDCallLetter")
	public ModelAndView getEadDCallLetter(HttpServletRequest request, Model model) {
		String cinNo = request.getParameter("cinNo");
		String cuSite =  request.getSession().getAttribute("cuSite").toString();
		List<FPO_MAIN> fpoMainData = fpoRepost.getmain(cinNo);
		String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);

		ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
		model.addAttribute("fpoMainData", fpoMainData.get(0));
		model.addAttribute("callMemo", getAllFpoQuery);
		model.addAttribute("getRemarks", getRemarks);
		model.addAttribute("defualtQueryList", defualtQueryList);
		model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo).get(0));
		String filename = reportService.getFileName(fpoMainData.get(0).getITEM_ID());
		model.addAttribute("filename", filename);
		model.addAttribute("succ", 0);
		model.addAttribute("dcallno", reportService.getDcallNo(fpoMainData.get(0).getITEM_ID(),cuSite));
		return modelAndView;
	}

	@GetMapping("/getrlyEadDCallLetter")
	public ModelAndView getrlyEadDCallLetter(HttpServletRequest request, Model model,HttpSession session) {
		String cinNo = request.getParameter("cinNo");
		List<FPO_MAIN> fpoMain = fpoRepost.getmain(cinNo);
		String cuSite =  request.getSession().getAttribute("cuSite").toString();
		List<FPO_MAIN> fpoMainData = fpoRepost.getmain(cinNo);
		String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNoOnCinNo(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		List<String> defualtQueryList = fpoService.getSpecifiedDefualtQuery(DefualtQuery, DocName);
		List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
		String stage = reportService.getStage(fpoMain.get(0).getITEM_ID());
		ModelAndView modelAndView = new ModelAndView("EAD/ead_callletter");
		model.addAttribute("fpoMainData", fpoMainData.get(0));
		model.addAttribute("callMemo", getAllFpoQuery);
		model.addAttribute("getRemarks", getRemarks);
		model.addAttribute("defualtQueryList", defualtQueryList);
		model.addAttribute("dinList", fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo).get(0));
		String filename = reportService.getFileNameForRly(fpoMainData.get(0).getITEM_ID(), stage,session,request);
		model.addAttribute("filename", filename);
		model.addAttribute("succ", 0);
		model.addAttribute("dcallno", reportService.getDcallNo(fpoMainData.get(0).getITEM_ID(), cuSite));
		return modelAndView;
	}

	/*@RequestMapping(value = "/bagscan")
	public ModelAndView bagScan(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("article/bag-scan");
	//	List<Collection> bagScans = fpoRepost
	//			.getBagScan(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		List<BagScanBean> bagScans= viewArticleService.getBagScan(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		models.addObject("bagScans", bagScans);
		//List<Collection> getpen1data = fpoRepost.getpen1data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		//List<Collection> getpen2data = fpoRepost.getpen2data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> getpen3data = fpoRepost.getpen3data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> pendataooe = fpoRepost.getpenartarrscanooe(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> pendatafpo = fpoRepost.getpenartarrscanfpo(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		List<Collection> getcntrylist = fpoRepost.getcntrylist();
		List<Collection> getfpolist = fpoRepost.getfpolist();
		List<Collection> getmclist = fpoRepost.getmclist();
//		models.addObject("pen1data",getpen1data);
		//models.addObject("pen2data",getpen1data);
//		models.addObject("penartarrscanooe",pendataooe);
//		models.addObject("penartarrscanfpo",pendatafpo);
		models.addObject("getcntrylist",getcntrylist);
		models.addObject("getfpolist",getfpolist);
		models.addObject("getmclist",getmclist);
		return models;
	}*/
	
	
	/*@RequestMapping(value = "/bagscan")
	public ModelAndView bagScan(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("article/bag-scan");
	//	List<Collection> bagScans = fpoRepost
	//			.getBagScan(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		System.out.println(fromDate);
		System.out.println(toDate);
		List<BagScanBean> bagScans= viewArticleService.getBagScan(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<Collection> confirmHistB = d_emprepo.getConformHistB(cusSite);
		
		//List<Collection> getpen1data = fpoRepost.getpen1data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		//List<Collection> getpen2data = fpoRepost.getpen2data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> getpen3data = fpoRepost.getpen3data(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> pendataooe = fpoRepost.getpenartarrscanooe(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
	//	List<Collection> pendatafpo = fpoRepost.getpenartarrscanfpo(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
		List<Collection> getcntrylist = fpoRepost.getcntrylist();
		List<Collection> getfpolist = fpoRepost.getfpolist();
		List<Collection> getmclist = fpoRepost.getmclist();
//		models.addObject("pen1data",getpen1data);
		//models.addObject("pen2data",getpen1data);
//		models.addObject("penartarrscanooe",pendataooe);
//		models.addObject("penartarrscanfpo",pendatafpo);
	//	System.out.println("hello"+confirmHistB);
		models.addObject("bagScans", bagScans);
		models.addObject("ConfrmHstry", confirmHistB);
		models.addObject("getcntrylist",getcntrylist);
		models.addObject("getfpolist",getfpolist);
		models.addObject("getmclist",getmclist);
		return models;
	}*/
	
	@RequestMapping(value = "/bagscan")
	public ModelAndView bagScan(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView models = new ModelAndView("article/bag-scan");
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		String fromDate =null ;
	 String toDate = null  ;
	 if (request.getParameter("fromDate")==null && request.getParameter("toDate")==null) {

		 LocalDate toDate1 = LocalDate.now();
		    LocalDate fromDate1 = toDate1.minusMonths(1);
		    DateTimeFormatter mytoDatefromDateObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		  toDate = mytoDatefromDateObj.format(toDate1);
		  fromDate=mytoDatefromDateObj.format(fromDate1);
	 }
	    System.out.println("To Date: " + toDate);
	    System.out.println("From Date: " + fromDate);
		
	    Boolean isRangeSelect=true;
	    //add by santhosh
	    models.addObject("fromdate", fromDate);
		models.addObject("todate", toDate);
	
		
	// List<Collection> confirmHistB=  d_emprepo.getDefltConfirmHistBa(cusSite,toDate,fromDate);
	 //System.out.println("hellow"+confirmHistB);
	    List<ArrivalHistoryBean2> arrivalHistoryBean2 = new ArrayList<>();
		  
		   arrivalHistoryBean2 =viewArticleService.getArrivalHistoryb(fromDate, toDate, cusSite);

	 List<ArrivalHistoryBean> arrivalHistoryBean = new ArrayList<>();
			arrivalHistoryBean = viewArticleService.getArrivalHistory(fromDate, toDate, isRangeSelect, cusSite);
			
			List<ooedata> recpdata = viewArticleService.getooedata(cusSite);
			System.out.println(" value of this "+ recpdata);
			if (recpdata!=null)
		//	for(ooedata curr : recpdata)
		//	{
		//		System.out.println(curr.getItem_id());
	//			PREDESservice.updateClearanceSite(curr.getRecp_id(),curr.getItem_id());
			
	//		}
			System.out.println("after assigning");
			
			List<RegulationRecptIDBean> recpIdRegulation  = new ArrayList<>();
			recpIdRegulation	= viewArticleService.getRecpIdRegulation();
			
			/*
			 * List<RegulationRecptIDBean> getRecpIdReglsnHistry = new ArrayList<>();
			 * getRecpIdReglsnHistry = viewArticleService.getRecpIdReglsnHistry();
			 */
			
			
			System.out.println(recpIdRegulation.size());
			
			List<Collection> BagNoRegulation = fpoRepost.getBagNORegulation();
			
			/*
			 * List<Collection> bagNORegulationHstry = fpoRepost.getBagNORegulationHstry();
			 */
			
			//for(int i=0;i<recpdata.size();i++) {
			//	System.out.println(recpdata.get(i).getItem_id());
			//}
		List<BagScanBean> bagScans= viewArticleService.getBagScan(cusSite);
		List<Collection> getcntrylist = fpoRepost.getcntrylist();
		List<Collection> getfpolist = fpoRepost.getfpolist();
		List<Collection> getmclist = fpoRepost.getmclist();
		models.addObject("bagScans", bagScans);
	//	models.addObject("ConfrmHstry", confirmHistB);
		models.addObject("ConfrmHstry", arrivalHistoryBean2);
		models.addObject("arrivalnHistoryBean", arrivalHistoryBean);
		models.addObject("getcntrylist",getcntrylist);
		models.addObject("getfpolist",getfpolist);
		models.addObject("getmclist",getmclist);
		models.addObject("recpIdReglstion",recpIdRegulation);
		models.addObject("BagNoRegulation",BagNoRegulation);
	//	models.addObject("getRecpIdReglsnHistry",getRecpIdReglsnHistry);
		//models.addObject("bagNORegulationHstry",bagNORegulationHstry);
		return models;
	}

	@GetMapping(value = "/bagNORegulationHstry")
	public @ResponseBody List<Collection> getbagNoHistory(HttpServletRequest request, HttpSession session) {
	List<Collection> bagNORegulationHstry = fpoRepost.getBagNORegulationHstry();	 	 
	return bagNORegulationHstry;
}
	@GetMapping(value = "/getRecpIdReglsnHistry")
	public @ResponseBody List<RegulationRecptIDBean> getRecpIdReglsnHistry(HttpServletRequest request, HttpSession session) {
		List<RegulationRecptIDBean> getRecpIdReglsnHistry  = new ArrayList<>();
		getRecpIdReglsnHistry	= viewArticleService.getRecpIdReglsnHistry();
		return getRecpIdReglsnHistry;
}


	@RequestMapping(value = "/bagarticles")
	public @ResponseBody ModelAndView bagArticles(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("article/bag-articles");
		String bagNoOrRecpId = request.getParameter("bagNoOrRecpId");
		boolean bagFlag = request.getParameter("bagFlag") != null
				&& request.getParameter("bagFlag").equalsIgnoreCase("Y") ? true : false;
		boolean view = request.getParameter("view") != null && request.getParameter("view").equalsIgnoreCase("Y") ? true
				: false;
		List<BagArticleBean> bagArticles = null;
	/*	if (bagFlag)
			fpoRepost.getBagArticlesfpo(bagNoOrRecpId);
		else
			fpoRepost.getBagArticlesooe(bagNoOrRecpId);*/
		bagArticles = viewArticleService.getBagArticles(bagNoOrRecpId,bagFlag);
		if (bagFlag) {
			for (BagArticleBean bagArticle : bagArticles) {
				bagArticle.setArticleArrivalInfo(viewArticleService.getArticleArrivalInfo(bagArticle.getArticleId()));
			//	bagArticle.setArticleArrivalInfo(fpoRepost.getArticleArrivalInfo(bagArticle.getArticleId()));
			}
		}
		if (view)
		{//	models.addObject("viewTextReport", viewArticleService.getScanReportText(bagNoOrRecpId));
		    models.addObject("viewTextReport", fpoScanInfoRepo.getScanReportText(bagNoOrRecpId));}
		models.addObject("bagArticles", bagArticles);
		models.addObject("bagFlag", bagFlag);
		models.addObject("view", !view);
		return models;
	}
	
	
	@PostMapping("/getDepartmentalComment")
	public ModelAndView getDepartmentalComment(HttpServletRequest request, Model model)throws Exception {
		String itemId= request.getParameter("id");
		String qrylevel= request.getParameter("qrylevel");
		String cinNo= request.getParameter("cinNo");
		System.out.println(itemId);
		System.out.println(qrylevel);
		if( qrylevel.equalsIgnoreCase("EAD") && fpoAssPaoCmtsRepo.getAprseqNo(cinNo) != null && !qrylevel.equalsIgnoreCase("AAA") && !qrylevel.equalsIgnoreCase("AAF")) {
			List<Collection> deptCmnts = fpoRepost.getDeptCmnts(itemId, qrylevel);
			
			if(!deptCmnts.isEmpty()) {
				model.addAttribute("deptCmnts", deptCmnts);
				model.addAttribute("deptCmts", 1);
			}
			
			else {
				model.addAttribute("deptCmts", 0);
			}
		} 
		
		else if(qrylevel.equalsIgnoreCase("AAA") && fpoAssPaoCmtsRepo.getAprseqNo(cinNo) != null && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("AAF")) {
			List<Collection> deptCmnts = fpoRepost.getDeptCmnts(itemId, qrylevel);
			
			if(!deptCmnts.isEmpty()) {
				model.addAttribute("deptCmnts", deptCmnts);
				model.addAttribute("deptCmts", 1);
			}
			
			else {
				model.addAttribute("deptCmts", 0);
			}
		} 

		else if(qrylevel.equalsIgnoreCase("AAF") && fpoAssPaoCmtsRepo.getAprseqNo(cinNo) != null && !qrylevel.equalsIgnoreCase("EAD") && !qrylevel.equalsIgnoreCase("EAD")) {
			List<Collection> deptCmnts = fpoRepost.getDeptCmnts(itemId, qrylevel);
			
			if(!deptCmnts.isEmpty()) {
				model.addAttribute("deptCmnts", deptCmnts);
				model.addAttribute("deptCmts", 1);
			}
			
			else {
				model.addAttribute("deptCmts", 0);
			}
			
			
		}
		
		 return new ModelAndView("article/dept-cmnts"); 
	}



	@PostMapping(value = "/savescannedbag")
	public @ResponseBody void saveScannedBag(@ModelAttribute FpoScanInfoBean fpoScanInfoBean,
			HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		try {
			List<String> articleArrivedIds = new ArrayList<String>();
			List<String> articleIds=null;
			String suffix = "";
			int once=0;
			for (FpoScanInfo fposcan : fpoScanInfoBean.getFpoScansInfo()) {
			//	Set<String> articleIds = viewArticleService.getArticleIds(fposcan);
				System.out.println("length is " + fposcan.getScanned());
				System.out.println("length is " + fposcan.getScanned()==null);
				if (fposcan.getBagType().equalsIgnoreCase("B"))
				    if (fpoRepost.chksitenull(fposcan.getArticleId())>0)
				    {   fpoRepost.updCusinFPOmain(fposcan.getArticleId(),request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
				        fpoRepost.updCusinItmdet(fposcan.getArticleId(),request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());   
				        FpoQueryDecision fpoqrydeci = new FpoQueryDecision();
				        fpoqrydeci.setCIN_NO(fpoRepost.getCinIdByItemId(fposcan.getArticleId()));
				        fpoqrydeci.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
				        fpoqrydeci.setITEM_ID(fposcan.getArticleId());
				        fpoqrydeci.setQRY_TYPE("P1");
				        fpoqrydeci.setROLE("PAO");
				        fpoqrydeci.setQRY_DT(new Date());
				        fpoQueryDecisionRepository.save(fpoqrydeci);
				        FpoMvmnt fpomvmnts = new FpoMvmnt();
						fpomvmnts.setCinNo(fpoRepost.getCinIdByItemId(fposcan.getArticleId()));
						fpomvmnts.setCinDt(fpoRepost.getcinDt(fposcan.getArticleId()));
						fpomvmnts.setCusSite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
						fpomvmnts.setEndDt(new Date());
						fpomvmnts.setRole("PAO");
						fpomvmnts.setOffId(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
						fpomvmnts.setSlNo(1L);
						fpomvmnts.setItemid(fposcan.getArticleId());
						fpomvmnts.setStage("C2");
						fpomvmntrepo.save(fpomvmnts);
						fpoCurQueService.addUserQue(fpoRepost.getCinIdByItemId(fposcan.getArticleId()),fposcan.getArticleId(), "C2", session,request);
						FPO_MAP_SITE fpoallot = new FPO_MAP_SITE();
						fpoallot.setAllot_dt(new Date());
						fpoallot.setCus_site(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
						fpoallot.setArticle_id(fposcan.getArticleId());
						fpoallot.setOfficer_id(request.getSession().getAttribute("offId").toString());
						fpoallot.setRole(request.getSession().getAttribute("role").toString());
						fpoallot.setReason("BAG SCAN MAPPING");
						fpoallotRepo.save(fpoallot);
				    }
			if (fposcan.getScanned()==null)
			{	if (fposcan.getBagType().equalsIgnoreCase("R")) 
				    articleIds = fpoRepost.getArticleIdsooe(fposcan.getBagNo());
				 else if (fposcan.getBagType().equalsIgnoreCase("B")) 
					articleIds = fpoRepost.getArticleIdsfpo(fposcan.getBagNo());
				for (String articleId : articleIds) {
				//	String mapCode = viewArticleService.getMapCode(articleId);
					String mapCode = fpositerepo.getMapCode(articleId);
					if (mapCode != null
							&& mapCode.substring(0, 5).equalsIgnoreCase(fposcan.getSite().substring(0, 5))) {
						articleArrivedIds.add(articleId);
					}
				}
			}
			else
			{	articleArrivedIds.add(fposcan.getArticleId());
			    String s = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
			    if (fposcan.getBagNo().equals("NEFR") || fposcan.getBagNo().equals("NEFB"))
			    {
			    	String val = fposcan.getBagNo();
			    	if (once == 0){
			    	String seqno=fpoRepost.getnefbagseq().toString();
			    	if (Integer.parseInt(seqno) < 99999999 && Integer.parseInt(seqno) > 0)
						suffix = String.format("%08d", Integer.parseInt(seqno) + 1);
					else
						suffix = String.format("%08d", Integer.parseInt("1"));
			    	fpoRepost.updbagseqinc();
			    	once=1;}
			    	fposcan.setBagNo(val.substring(0,4)+s+suffix);
			    }}
				fposcan.setScanDate(new Date());
				fposcan.setCusSite(
						request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
				fposcan.setOfficerId(
						request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
				fposcan.setRole(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
			}
			
			if (!articleArrivedIds.isEmpty()) {
				articleArrivedIds = articleArrivedIds.stream().distinct().collect(Collectors.toList());
				int partitionSize = 1000;
				List<List<String>> partitions = new LinkedList<>();
				for (int i = 0; i < articleArrivedIds.size(); i += partitionSize) {
					partitions.add(articleArrivedIds.subList(i, Math.min(i + partitionSize, articleArrivedIds.size())));
				}
				for (List<String> list : partitions) {
					fpoRepost.updateArrivalScan(list);
					fpoRepost.updarrscandt(list,new Date());
				}
			}
			fpoScanInfoRepo.saveAll(fpoScanInfoBean.getFpoScansInfo());
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			System.out.println("Issue in saving scanned bag");
		}
	}
	
	@GetMapping(value = "/getdelackartnotrece")
	public @ResponseBody List<DeliveryArticlesColumns> getdelackartnotrece(HttpServletRequest request, HttpServletResponse response,  HttpSession session)  throws ParseException {
		List<DeliveryArticlesColumns> getdelacknotrec = null;
		String frdt = request.getParameter("frdt");
		String todt = request.getParameter("todt");
		System.out.println(frdt);
		System.out.println(todt);
		//getDeliveryArticlesColumnsgetDeliveryArticlesColumns
		getdelacknotrec= reportService.getDeliverynoArticlesColumns(frdt,todt,session, request);
		return getdelacknotrec;
	}
	
	
	
	
	
	@GetMapping(value = "/getdelackartrece")
	public @ResponseBody List<DeliveryArticlesColumns> getdelackartrece(HttpServletRequest request, HttpServletResponse response,  HttpSession session)  throws ParseException {
		List<DeliveryArticlesColumns> getdelackart = null;
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		System.out.println(fromdate);
		System.out.println(todate);
		//getDeliveryArticlesColumnsgetDeliveryArticlesColumns
		getdelackart= reportService.getDeliveryArticlesColumns(fromdate,todate,session, request);
		return getdelackart;
	}
	
	

	@GetMapping(value = "/getoocf")
	public @ResponseBody List<DeliveryArticlesColumns> getoocf(HttpServletRequest request, HttpServletResponse response,  HttpSession session)  throws ParseException {
		List<DeliveryArticlesColumns> getdelackooc = null;
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		System.out.println(fromdate);
		System.out.println(todate);
		//getDeliveryArticlesColumnsgetDeliveryArticlesColumns
		getdelackooc= reportService.getoocsentArticlesColumns(fromdate,todate,session, request);
		return getdelackooc;
	}


	@GetMapping(value = "/getarrivalhistory")
	public @ResponseBody List<Collection> getArrivalHistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
			String fromDate = request.getParameter("fromDate") != null
					&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
			String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
					? request.getParameter("toDate")
					: null;
			Boolean isRangeSelect = true;
			System.out.println(isRangeSelect);
			System.out.println(fromDate);
			System.out.println(toDate);
			String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
			List<Collection> arrivalHistoryBean = new ArrayList<>();
			if (fromDate != null && toDate != null) {
				arrivalHistoryBean =  d_emprepo.getConfirmA(fromDate, toDate, cusSite);
			//	arrivalHistoryBean = fpoRepost.getArrivalHistory(fromDate, toDate, cusSite);
			}
			System.out.println("this is okay  "+arrivalHistoryBean);
			return arrivalHistoryBean;
	}
	
	@RequestMapping(value="/getcnfirmhistrytdata")
	public @ResponseBody ModelAndView  viewArticleHistory(HttpServletRequest request, HttpServletResponse response,  HttpSession session){
		ModelAndView models = new ModelAndView("article/view-Article-Hstry");

		String bagNo = request.getParameter("bagNo");
		String bagType ;
		if (bagNo.charAt(3) == 'B') {
			
			bagType="BAG -";
		}
		else {
			bagType="RID -";
		}
//		System.out.println(bagType);
	List<Collection> viewArticleHistry =  d_emprepo.getListOfArtclHistry(bagNo);
	String statusRprt =  d_emprepo.getReportStatus(bagNo);
	/*
	 * String bagType = d_emprepo.getBagType(bagNo); System.out.println(bagType);
	 */
	System.out.println(statusRprt);
	System.out.println("hi"+viewArticleHistry);
	models.addObject("viewArticleHistry", viewArticleHistry);
	
	models.addObject("statusRprt", statusRprt);
	models.addObject("bagNo", bagNo);
	models.addObject("bagType" , bagType);
//	System.out.println(string);
	//here i should get report contents as string scanned 'o'and write one more function in below ajex function 
		return models;
		
	}
	
	@GetMapping(value = "/confirmationHistotyB")
	public @ResponseBody List<ArrivalHistoryBean2> getConfirmReportHistory(HttpServletRequest request, HttpSession session) {
	String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
	String fromDate = request.getParameter("fromDate");
//	
	 String toDate = request.getParameter("toDate");
	 System.out.println(fromDate);
	 System.out.println(toDate);
	 System.out.println(cusSite);
	 
	 
	 List<ArrivalHistoryBean2> arrivalHistoryBean2 = new ArrayList<>();
	    arrivalHistoryBean2 =viewArticleService.getArrivalHistoryb(fromDate, toDate, cusSite);

	for (ArrivalHistoryBean2 arrivalHistoryBean22 : arrivalHistoryBean2) {
		System.out.println(arrivalHistoryBean22.getBagNo());
	}
	
	System.out.println(arrivalHistoryBean2);
	System.out.println("hello"+arrivalHistoryBean2);
	
	return arrivalHistoryBean2;
}
	
	
	@GetMapping(value = "/getarrivalnhistory")
	public @ResponseBody void getArrivalnHistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		Boolean isRangeSelect = Boolean.valueOf(request.getParameter("isRangeSelect"));
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<ArrivalHistoryBean> arrivalnHistoryBean = new ArrayList<>();
		
		
		if (fromDate != null && toDate != null) {
			arrivalnHistoryBean = viewArticleService.getArrivalnHistory(fromDate, toDate,  cusSite);
		//	arrivalHistoryBean = fpoRepost.getArrivalHistory(fromDate, toDate, cusSite);
		}
		
		jsonObj.put("data", arrivalnHistoryBean);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}
	
	
	@GetMapping(value = "/getcntrymcfiltdata")
	public @ResponseBody List<NONARTARRINFO> getcntrydata(HttpServletRequest request, HttpServletResponse response,  HttpSession session)  throws ParseException {
     String cntry = request.getParameter("cntry");
     String mc = request.getParameter("mc");
     String frdt = request.getParameter("frdt");
     String todt = request.getParameter("todt");
     String bagtype = request.getParameter("bagtype");
     String fpo = request.getParameter("fpo");
  //   String fpo = request.getParameter("fpo");
     if (frdt.length()==0 && todt.length()==0)
     {frdt="";todt="";}
     else if (frdt.length()==0 && todt.length()!=0)
    	 frdt=todt;
     else if (frdt.length()!=0 && todt.length()==0)
    	 todt=frdt;
     String cusite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
     System.out.println("mc is "+mc);
     System.out.println("cntry is "+cntry);
     System.out.println("cusite is "+cusite);
     System.out.println("fromdt is " +frdt);
     System.out.println("todt is " + todt);
     System.out.println("fpo is " + fpo);
     List<NONARTARRINFO> getfiltdata=null;
    /* if (mc.length()!=0 && cntry.length()==0 &&  frdt.length()==0 && todt.length()==0)
    	 getfiltdata = fpoRepost.getfiltpendatamc(mc,cusite);
     else if (mc.length()==0 && cntry.length()!=0 &&  frdt.length()==0 && todt.length()==0)
    	 getfiltdata = fpoRepost.getfiltpendatacntry(cntry,cusite);
     else if (mc.length()!=0 && cntry.length()!=0 &&  frdt.length()==0 && todt.length()==0)
    	 getfiltdata = fpoRepost.getfiltpendatamccntry(mc,cntry,cusite);
     else if (mc.length()!=0 && cntry.length()==0 &&  frdt.length()!=0 && todt.length()!=0)
    	 getfiltdata = fpoRepost.getfiltpendatamcdt(mc,cusite,frdt,todt);  
     else if (mc.length()==0 && cntry.length()!=0 &&  frdt.length()!=0 && todt.length()!=0)
    	 getfiltdata = fpoRepost.getfiltpendatacntrydt(cntry,cusite,frdt,todt);
     else if (mc.length()==0 && cntry.length()==0 &&  frdt.length()!=0 && todt.length()!=0)
    	 getfiltdata = fpoRepost.getfiltpendatadt(cusite,frdt,todt);
     else if (mc.length()!=0 && cntry.length()!=0 &&  frdt.length()!=0 && todt.length()!=0)
          getfiltdata = fpoRepost.getfiltpendataall(cntry,mc,cusite,frdt,todt);*/
     getfiltdata = viewArticleService.getfiltdata(mc,cntry,cusite,frdt,todt,bagtype,fpo);
	 return getfiltdata;
	}

	@PostMapping(value = "/articlereportscan")
	public @ResponseBody ModelAndView articleReportScan(HttpServletRequest request, Model model) {
		ModelAndView models = new ModelAndView("article/article-report-scan");
		String articleId = request.getParameter("articleId");
		System.out.println("itemid in scan report is "+ articleId);
		List<Collection> articleStatus = fpoRepost.EADarticlArrinfo(articleId);
		String scanOOE = fpoScanInfoRepo.getScanReportTextOOEA(articleId);
		if (scanOOE==null)
			scanOOE = fpoScanInfoRepo.getScanReportTextOOEB(articleId);
		String scanFPO = fpoScanInfoRepo.getScanReportTextFPOA(articleId);
		if (scanFPO==null)
			scanFPO = fpoScanInfoRepo.getScanReportTextFPOB(articleId);
		if (articleStatus.size()==0)
			 articleStatus = fpoRepost.EADarticlArrinfofpo(articleId);
		List<FpoScanInfo> fpoScan = fpoScanInfoRepo.getScanReportById(articleId);
		model.addAttribute("articleStatus", articleStatus);
		model.addAttribute("fposcanooe", fpoScan.stream().filter(fposcan -> {
			return fposcan.getBagType().equalsIgnoreCase("R");
		}).collect(Collectors.toList()));
		model.addAttribute("fposcanfpo", fpoScan.stream().filter(fposcan -> {
			return fposcan.getBagType().equalsIgnoreCase("B");
		}).collect(Collectors.toList()));
		List<FpoScanInfo> scanDateOOE = fpoScanInfoRepo.getScanReportDateOfOOE(articleId);
		List<FpoScanInfo> scanDateFpo = fpoScanInfoRepo.getScanReportDateOfFpo(articleId);
		model.addAttribute("scanDateOOE", scanDateOOE);
		model.addAttribute("scanDateFpo", scanDateFpo);
		if (scanOOE==null)
			scanOOE="-NIL-";
		if (scanFPO==null)
			scanFPO="-NIL-";
		model.addAttribute("scanOOE", scanOOE);
		model.addAttribute("scanFPO", scanFPO);
		model.addAttribute("Scantype", fpoScanInfoRepo.getScantype(articleId));
		System.out.println(fpoScanInfoRepo.getScantype(articleId));
		if (articleStatus.size() == 0)
		 model.addAttribute("report","RID/Bag No. related to this Article ID was entered MANUALLY, as the Article arrival information was not received, or, partially received. Refer Confirmation History Category 'B' in PBS role.");
		else
	     model.addAttribute("report","NO");
		return models;
	}


	@PostMapping("/sendregulationBag")
	@ResponseBody
	public List<Collection> handleSendRegulationRequestBAG(@RequestBody List<Map<String, String>> data,HttpServletRequest request) {
		
		
		int count = 0;
	  for (Map<String, String> row : data) {
	    String articleId = row.get("article_id");
	    String bagNo = row.get("bag_no");
	    String receptId = row.get("recept_id");
	    System.out.println("article_id: " + articleId);
	    System.out.println("bag_no: " + bagNo);
	    System.out.println("recept_id: " + receptId);
	    Date date = new Date();
	    
	    fpoRepost.updatabagforR(bagNo, receptId,articleId,date,request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	    count++;
	  }
	  List<Collection> BagNoRegulation  = new ArrayList<>(); 
	  BagNoRegulation= fpoRepost.getBagNORegulation();	
	  System.out.println(count);
	  
	  return BagNoRegulation;
	}
	
	@PostMapping("/sendregulationRid")
	@ResponseBody
	public List<RegulationRecptIDBean> handleSendRegulationRequestRID(@RequestBody List<Map<String, String>> data,HttpServletRequest request) {
		
		
		int count = 0;
	  for (Map<String, String> row : data) {
	    String articleId = row.get("article_id");
	    String bagNo = row.get("bag_no");
	    String receptId = row.get("recept_id");
	    System.out.println("article_id: " + articleId);
	    System.out.println("bag_no: " + bagNo);
	    System.out.println("recept_id: " + receptId);
	    Date date = new Date();
	    
	    fpoRepost.updatabagforR(bagNo, receptId,articleId,date,request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
	    count++;
	  }
	  List<RegulationRecptIDBean> recpIdRegulation  = new ArrayList<>();
		recpIdRegulation	= viewArticleService.getRecpIdRegulation();
		
	  System.out.println(count);
	  
	  return recpIdRegulation;
	}
	/*
	 * @PostMapping("/sendregulation")
	 * 
	 * @ResponseBody public String handleSendRegulationRequest(@RequestBody
	 * List<Map<String, String>> data,HttpServletRequest request) {
	 * 
	 * 
	 * int count = 0; for (Map<String, String> row : data) { String articleId =
	 * row.get("article_id"); String bagNo = row.get("bag_no"); String receptId =
	 * row.get("recept_id"); System.out.println("article_id: " + articleId);
	 * System.out.println("bag_no: " + bagNo); System.out.println("recept_id: " +
	 * receptId); Date date = new Date();
	 * 
	 * fpoRepost.updatabagforR(bagNo,
	 * receptId,articleId,date,request.getSession().getAttribute("offId") == null ?
	 * null : request.getSession().getAttribute("offId").toString()); count++; }
	 * 
	 * System.out.println(count);
	 * 
	 * return "OK"; }
	 */
}
