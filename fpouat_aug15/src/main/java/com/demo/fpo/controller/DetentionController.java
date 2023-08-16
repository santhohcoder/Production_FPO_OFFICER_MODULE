package com.demo.fpo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.bean.AmountSectionBean;
import com.demo.fpo.bean.DepartmentCommentsBean;
import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.bean.DetainedArticleRemarkBean;
import com.demo.fpo.bean.DetainedCaseBean;
import com.demo.fpo.bean.DetainedDocumentBean;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoDetainedCaseInfo;
import com.demo.fpo.model.FpoDetainedComments;
import com.demo.fpo.model.FpoDetainedDocInfo;
import com.demo.fpo.model.FpoDetainedInfo;
import com.demo.fpo.model.FpoFine;
import com.demo.fpo.model.FpoPenal;
import com.demo.fpo.prop.StaticConstants;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FPO_OOC_FINDINGSrepost;
import com.demo.fpo.repos.FpoDcallQRYRepo;
import com.demo.fpo.repos.FpoDetainedCaseInfoRepo;
import com.demo.fpo.repos.FpoDetainedCommentsRepo;
import com.demo.fpo.repos.FpoDetainedDocInfoRepo;
import com.demo.fpo.repos.FpoDetainedInfoRepo;
import com.demo.fpo.repos.FpoFineRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoPenalRepo;
import com.demo.fpo.repos.FpoQueryDinRepo;
import com.demo.fpo.repos.FpoQueryRepo;
import com.demo.fpo.repos.OsPathFpoRepo;
import com.demo.fpo.service.DetentionService;
import com.demo.fpo.service.FpoFineService;
import com.demo.fpo.service.FpoPenalService;
import com.demo.fpo.service.FpoService;
import com.demo.fpo.utils.QueryConstants;

@Controller
public class DetentionController {

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FpoFineService fpoFineService;

	@Autowired
	FpoPenalService fpoPenalService;

	@Autowired
	FpoService fpoService;

	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	FpoDcallQRYRepo fpoDcallQryRepo;

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	OsPathFpoRepo osPathFpoRepo;

	@Autowired
	DetentionService detentionService;

	@Autowired
	FpoDetainedInfoRepo fpoDetainedInfoRepo;

	@Autowired
	FpoDetainedDocInfoRepo fpoDetainedDocInfoRepo;

	@Autowired
	FpoDetainedCommentsRepo fpoDetainedCommentsRepo;
	
	@Autowired
	FPO_OOC_FINDINGSrepost fpooocfindrepo;

	@Autowired
	FpoMvmntRepo fpoMvmntRepo;

	@Autowired
	FpoFineRepo fpoFineRepo;

	@Autowired
	FpoPenalRepo fpoPenalRepo;

	@Autowired
	FpoDetainedCaseInfoRepo fpoDetainedCaseInfoRepo;

	@GetMapping(value = "/detained")
	public ModelAndView detained(HttpServletRequest request, HttpSession session) {
		ModelAndView models = new ModelAndView("detention/detention");
		models.addObject("roleAppraiser", StaticConstants.APPRAISER);
		return models;
	}

	@GetMapping(value = "/getdetainedarticles")
	public @ResponseBody void getDetainedArticles(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		List<DetainedArticleBean> detainedArticles = detentionService.getDetainedArticles(session,request);
		if (detainedArticles.size() > 0)
		   jsonObj.put("data", detainedArticles);
		else
			 jsonObj.put("data", "");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@GetMapping(value = "/getdetainedarticleshistory")
	public @ResponseBody void getDetainedArticlesHistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(); 
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<DetainArticleHistoryBean> detainedArticlesHistory = null;
		if (fromDate != null && toDate != null) {
		detainedArticlesHistory = detentionService.getDetainedArticlesHistory(fromDate, toDate, cuSite);}
		else
		detainedArticlesHistory = detentionService.getDetainedArticlesHistory(cuSite);
		if (detainedArticlesHistory.size() >0) {
			for (DetainArticleHistoryBean detainArticleHistoryBean : detainedArticlesHistory) {
				detainArticleHistoryBean
						.setCurrentStatus(fpoService.getpos( detainArticleHistoryBean.getArticleId(), session,request));
			}
	//	}
		jsonObj.put("data", detainedArticlesHistory);
		}
		else {
				 jsonObj.put("data", "");
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

	@PostMapping(value = "/getdeptcomments")
	public @ResponseBody ModelAndView getDeptComments(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		String itemId = request.getParameter("itemId");
		String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
		model.addAttribute("itemId", itemId);
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<Object> deptCmts = fpoMainRepost.getDeptComments(cinNo, cuSite);
		model.addAttribute("deptCmts", deptCmts);
		// model.addAttribute("officerId", deptCmts.isEmpty() ? null :
		// deptCmts.get(0).getOfficerId());
		return new ModelAndView("detention/department-comments");
	}

	@PostMapping(value = "/detremarksbyarticleidanddetainedno")
	public @ResponseBody ModelAndView viewDetainedRemarksByArticleIdAndByDetainedNo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		String itemId = request.getParameter("itemId");
		Long detainedNo = Long.valueOf(request.getParameter("detainedNo"));
		String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("cinNo", cinNo);
		List<FpoDetainedInfo> detainedRemarks = fpoDetainedInfoRepo.getDetailsByarticleIdAndDetainedNo(itemId,
				detainedNo);
		String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
		for (FpoDetainedInfo fpoDetainedInfo : detainedRemarks) {
			fpoDetainedInfo.setFpoDetainedCaseInfo(
					fpoDetainedCaseInfoRepo.getByItemId(itemId, fpoDetainedInfo.getDetainedNo()));
			fpoDetainedInfo
					.setFpoDetainedDocInfo(fpoDetainedDocInfoRepo.getByItemId(itemId, fpoDetainedInfo.getDetainedNo()));
			fpoDetainedInfo.setFpoFines(fpoFineRepo.getDetainedFineList(itemId, fpoDetainedInfo.getDetainedNo()));
			fpoDetainedInfo
					.setFpoPenalties(fpoPenalRepo.getDetainedPenaltyList(itemId, fpoDetainedInfo.getDetainedNo()));
		}
		if (true) {
			List<FpoDetainedComments> fpoDetainedComments = fpoDetainedCommentsRepo.getByItemIdandDetainedNo(itemId,
					detainedRemarks.get(0).getDetainedNo());
			List<FpoDetainedComments> fpoDetainedAppraiserComments = fpoDetainedComments.stream().filter(comments -> {
				return comments.getRole().equalsIgnoreCase(StaticConstants.APPRAISER);
			}).collect(Collectors.toList());
			List<FpoDetainedComments> fpoDetainedACComments = fpoDetainedComments.stream().filter(comments -> {
				return comments.getRole().equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER);
			}).collect(Collectors.toList());
			model.addAttribute("appraiserComments", fpoDetainedAppraiserComments);
			model.addAttribute("acComments", fpoDetainedACComments);
			model.addAttribute("comments", !fpoDetainedComments.isEmpty());
		}
		model.addAttribute("acrolematch", role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER));
		model.addAttribute("detainedRemarks", detainedRemarks);
		model.addAttribute("view", true);
		model.addAttribute("cmtflows", false);
		model.addAttribute("history", true);
		return new ModelAndView("detention/view-detainedremarks");
	}

	@GetMapping(value = "/detainedremarks")
	public @ResponseBody ModelAndView detainedremarks(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		String itemId = request.getParameter("itemId");
		Boolean view = Boolean.valueOf(request.getParameter("view"));
		Boolean cmtflows = Boolean.valueOf(request.getParameter("cmtflows"));
		String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("cinNo", cinNo);
		List<FpoDetainedInfo> detainedRemarks = new ArrayList<FpoDetainedInfo>();
		if (cmtflows) {
			detainedRemarks = fpoDetainedInfoRepo.getMaxDetailsByarticleId(itemId);
		} else {
			detainedRemarks = fpoDetainedInfoRepo.getDetailsByarticleId(itemId);
		}
		if (!detainedRemarks.isEmpty() && view) {
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			for (FpoDetainedInfo fpoDetainedInfo : detainedRemarks) {
				fpoDetainedInfo.setFpoDetainedCaseInfo(
						fpoDetainedCaseInfoRepo.getByItemId(itemId, fpoDetainedInfo.getDetainedNo()));
				fpoDetainedInfo.setFpoDetainedDocInfo(
						fpoDetainedDocInfoRepo.getByItemId(itemId, fpoDetainedInfo.getDetainedNo()));
				fpoDetainedInfo.setFpoFines(fpoFineRepo.getDetainedFineList(itemId, fpoDetainedInfo.getDetainedNo()));
				fpoDetainedInfo
						.setFpoPenalties(fpoPenalRepo.getDetainedPenaltyList(itemId, fpoDetainedInfo.getDetainedNo()));
			}
			if (cmtflows) {
				List<FpoDetainedComments> fpoDetainedComments = fpoDetainedCommentsRepo.getByItemIdandDetainedNo(itemId,
						detainedRemarks.get(0).getDetainedNo());
				List<FpoDetainedComments> fpoDetainedAppraiserComments = fpoDetainedComments.stream()
						.filter(comments -> {
							return comments.getRole().equalsIgnoreCase(StaticConstants.APPRAISER);
						}).collect(Collectors.toList());
				List<FpoDetainedComments> fpoDetainedACComments = fpoDetainedComments.stream().filter(comments -> {
					return comments.getRole().equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER);
				}).collect(Collectors.toList());
				model.addAttribute("appraiserComments", fpoDetainedAppraiserComments);
				model.addAttribute("acComments", fpoDetainedACComments);
				model.addAttribute("comments", !fpoDetainedComments.isEmpty());
			}
			model.addAttribute("acrolematch", role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER));
			model.addAttribute("detainedRemarks", detainedRemarks);
			model.addAttribute("view", view);
			model.addAttribute("cmtflows", cmtflows);
			return new ModelAndView("detention/view-detainedremarks");
		}
		model.addAttribute("scnDocuments", detentionService.getScnDocument());
		model.addAttribute("detainDecisions", detentionService.getDetainDecision());
		model.addAttribute("detainedCaseList",
				detentionService.getDetainedCaseMethodList(QueryConstants.detainedCaseList));
		model.addAttribute("detainedMethodList",
				detentionService.getDetainedCaseMethodList(QueryConstants.detainedMethodList));
		model.addAttribute("uqcList", detentionService.getUQCList(QueryConstants.uqcList));
		return new ModelAndView("detention/detainedremarks");
	}

	@PostMapping(value = "/scanningDetainDocuments")
	public @ResponseBody void scanningDetainDocuments(@ModelAttribute DetainedArticleRemarkBean detainedArticleRemark,
			HttpServletResponse response) throws InterruptedException {
		String detainedQueuePath = osPathFpoRepo.getDetainedQueuePath();
		List<String> maliciousFiles = new ArrayList<>();
		if (detainedArticleRemark.getDocumentSections() != null) {
			for (DetainedDocumentBean detainDocumentSection : detainedArticleRemark.getDocumentSections()) {
				File destination = new File(detainedQueuePath + detainedArticleRemark.getCinNo()
						+ detainDocumentSection.getDocumentFile().getOriginalFilename());
				try {
					detainDocumentSection.getDocumentFile().transferTo(destination);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (detainedArticleRemark.getDetainedCaseSections() != null) {
			for (DetainedCaseBean detainCaseSection : detainedArticleRemark.getDetainedCaseSections()) {
				if (detainCaseSection.getDetentionFile() != null) {
					File destination = new File(detainedQueuePath + detainedArticleRemark.getCinNo()
							+ detainCaseSection.getDetentionFile().getOriginalFilename());
					try {
						detainCaseSection.getDetentionFile().transferTo(destination);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		TimeUnit.SECONDS.sleep(1);
		if (detainedArticleRemark.getDocumentSections() != null) {
			for (DetainedDocumentBean detainDocumentSection : detainedArticleRemark.getDocumentSections()) {
				File destination = new File(detainedQueuePath + detainedArticleRemark.getCinNo()
						+ detainDocumentSection.getDocumentFile().getOriginalFilename());
				if (destination.isFile()) {
					destination.delete();
				} else {
					maliciousFiles.add(detainDocumentSection.getDocumentFile().getOriginalFilename());
				}
			}
		}
		if (detainedArticleRemark.getDetainedCaseSections() != null) {
			for (DetainedCaseBean detainCaseSection : detainedArticleRemark.getDetainedCaseSections()) {
				if (detainCaseSection.getDetentionFile() != null) {
					File destination = new File(detainedQueuePath + detainedArticleRemark.getCinNo()
							+ detainCaseSection.getDetentionFile().getOriginalFilename());
					if (destination.isFile()) {
						destination.delete();
					} else {
						maliciousFiles.add(detainCaseSection.getDetentionFile().getOriginalFilename());
					}
				}
			}
		}
		try {
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("maliciousFiles", maliciousFiles);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/savedetaincomments")
	public @ResponseBody void saveDetainComments(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String itemId = request.getParameter("itemId");
			String comment = request.getParameter("comment");
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
			Long detainedNo = fpoDetainedInfoRepo.getMaxDetainedNo(itemId);
			Long maxSeqNo = fpoDetainedCommentsRepo.getMaxSeqNo(itemId, detainedNo);
			if (null == maxSeqNo || maxSeqNo.toString().isEmpty()) {
				maxSeqNo = 0l;
			}
			maxSeqNo = maxSeqNo + 1l;
			FpoDetainedComments fpoDetainedComments = new FpoDetainedComments();
			fpoDetainedComments.setCinNo(cinNo);
			fpoDetainedComments.setSeqNo(maxSeqNo);
			fpoDetainedComments.setItemId(itemId);
			fpoDetainedComments.setRole(role);
			fpoDetainedComments.setOfficerId(
					request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoDetainedComments.setComments(comment);
			fpoDetainedComments.setEntryDate(new Date());
			fpoDetainedComments.setDetainedNo(detainedNo);
			fpoDetainedCommentsRepo.save(fpoDetainedComments);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
			if (null == slno)
				slno = Long.valueOf(0);
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			fpoService.insertIntofpoMvmntDb(null, cinNo, fpoMvmntRepo.getcindtmvmnt(cinNo), new java.util.Date(), slno,
					role, "P5", session,request);
			if (role.equalsIgnoreCase(StaticConstants.APPRAISER))
				fpoDetainedInfoRepo.updateCurrentOfficerRole(StaticConstants.ASSISTANTCOMMISSIONER, itemId, detainedNo);
			if (role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER))
				fpoDetainedInfoRepo.updateCurrentOfficerRole(StaticConstants.APPRAISER, itemId, detainedNo);
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/approvetoassessment")
	public @ResponseBody void approveToAssessment(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String itemId = request.getParameter("itemId");
			String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			Long maxDetainedNo = fpoDetainedInfoRepo.getMaxDetainedNo(itemId);
			fpoDetainedInfoRepo.updateDetainCompleted(itemId, maxDetainedNo);
			fpoService.moveDetToAss(cinNo, session,request);
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/savedetainreply")
	public @ResponseBody void saveDetainReply(@ModelAttribute DetainedArticleRemarkBean detainedArticleRemark,
			HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		try {
			String detainedQueuePath = osPathFpoRepo.getDetainedQueuePath();
			String cinNo = detainedArticleRemark.getCinNo();
			List<FPO_MAIN> fpoMains = fpoMainRepost.getmain(cinNo);
			FPO_MAIN fpoMain = fpoMains.isEmpty() ? new FPO_MAIN() : fpoMains.get(0);
			Long maxDetainedNo = fpoDetainedInfoRepo.getMaxDetainedNo(fpoMain.getITEM_ID());
			String cussite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
			String officerId = request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString();
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			if (null == maxDetainedNo || maxDetainedNo.toString().isEmpty()) {
				maxDetainedNo = 0l;
			}
			maxDetainedNo = maxDetainedNo + 1l;
			int index = 1;
			if (detainedArticleRemark.getDocumentSections() != null) {
				for (DetainedDocumentBean documentSection : detainedArticleRemark.getDocumentSections()) {
					File destination = new File(detainedQueuePath + fpoMain.getITEM_ID() + "_detain-" + maxDetainedNo
							+ "_" + index + ".pdf");
					try {
						documentSection.getDocumentFile().transferTo(destination);
						index++;
						FpoDetainedDocInfo fpoDetainedDocInfo = new FpoDetainedDocInfo();
						fpoDetainedDocInfo.setArticleId(fpoMain.getITEM_ID());
						fpoDetainedDocInfo.setDocumentType(documentSection.getDocumentType());
						fpoDetainedDocInfo.setDocumentDate(documentSection.getDocumentDate());
						fpoDetainedDocInfo.setDocumentName(documentSection.getDocumentFile().getOriginalFilename());
						fpoDetainedDocInfo.setDocumentPhysicalFile(destination.getPath());
						fpoDetainedDocInfo.setDetainedNo(maxDetainedNo);
						fpoDetainedDocInfoRepo.save(fpoDetainedDocInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (detainedArticleRemark.getDetainedCaseSections() != null) {
				for (DetainedCaseBean detainedCaseSection : detainedArticleRemark.getDetainedCaseSections()) {
					FpoDetainedCaseInfo fpoDetainedCaseInfo = new FpoDetainedCaseInfo();
					fpoDetainedCaseInfo.setArticleId(fpoMain.getITEM_ID());
					fpoDetainedCaseInfo.setItemDetail(detainedCaseSection.getSuspectedItem());
					fpoDetainedCaseInfo.setQuantity(Long.valueOf(detainedCaseSection.getQuantity()));
					fpoDetainedCaseInfo.setUqc(detainedCaseSection.getUqc());
					fpoDetainedCaseInfo.setValue(detainedCaseSection.getValue());
					fpoDetainedCaseInfo.setDetainedNo(maxDetainedNo);
					if (detainedCaseSection.getDetentionFile() != null) {
						File destination = new File(detainedQueuePath + fpoMain.getITEM_ID() + "_detain-"
								+ maxDetainedNo + "_" + index + ".pdf");
						try {
							detainedCaseSection.getDetentionFile().transferTo(destination);
							index++;
							fpoDetainedCaseInfo
									.setPhotoName(detainedCaseSection.getDetentionFile().getOriginalFilename());
							fpoDetainedCaseInfo.setPhotoPhysicalFile(destination.getPath());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					fpoDetainedCaseInfoRepo.save(fpoDetainedCaseInfo);
				}
			}
			float totalFine = 0f;
			List<FpoFine> existedFpoFineList = fpoFineRepo.getAllFine(cinNo);
			if (null != existedFpoFineList && !existedFpoFineList.isEmpty()) {
				fpoFineService.moveFineToAmend(existedFpoFineList);
				fpoFineRepo.deleteAll(existedFpoFineList);
			}
			if (detainedArticleRemark.getFineSections() != null) {
				List<FpoFine> fpoFineList = new ArrayList<>();
				for (AmountSectionBean fine : detainedArticleRemark.getFineSections()) {
					FpoFine fpoFine = new FpoFine();
					fpoFine.setCinNo(cinNo);
					fpoFine.setCinDT(fpoMain.getCIN_DT());
					fpoFine.setITEM_ID(fpoMain.getITEM_ID());
					fpoFine.setCUS_SITE(cussite);
					fpoFine.setPOSTINGDT(fpoMain.getPOSTINGDT());
					fpoFine.setFineAmt(Float.valueOf(fine.getAmount()));
					fpoFine.setFineUs(fine.getSection());
					fpoFine.setOffId(officerId);
					fpoFine.setRole(role);
					fpoFine.setStage("DET");
					fpoFine.setStageNo(maxDetainedNo);
					fpoFineList.add(fpoFine);
					totalFine += Float.valueOf(fine.getAmount());
				}
				fpoFineRepo.saveAll(fpoFineList);
			}
			float totalPenalty = 0f;
			List<FpoPenal> existedFpoPenaltyList = null;
			existedFpoPenaltyList = fpoPenalRepo.getAllPenalty(cinNo);
			if (null != existedFpoPenaltyList && !existedFpoPenaltyList.isEmpty()) {
				fpoPenalService.movePenalToAmend(existedFpoPenaltyList);
				fpoPenalRepo.deleteAll(existedFpoPenaltyList);
			}
			if (detainedArticleRemark.getPenaltySections() != null) {
				List<FpoPenal> fpoPenaltyList = new ArrayList<>();
				for (AmountSectionBean penalty : detainedArticleRemark.getPenaltySections()) {
					FpoPenal fpoPenalty = new FpoPenal();
					fpoPenalty.setCinNo(cinNo);
					fpoPenalty.setCinDT(fpoMain.getCIN_DT());
					fpoPenalty.setITEM_ID(fpoMain.getITEM_ID());
					fpoPenalty.setCUS_SITE(cussite);
					fpoPenalty.setPOSTINGDT(fpoMain.getPOSTINGDT());
					fpoPenalty.setPenalAmt(Float.valueOf(penalty.getAmount()));
					fpoPenalty.setPenalUs(penalty.getSection());
					fpoPenalty.setOffId(officerId);
					fpoPenalty.setRole(role);
					fpoPenalty.setASS_DT(new Date());
					fpoPenalty.setStage("DET");
					fpoPenalty.setStageNo(maxDetainedNo);
					fpoPenaltyList.add(fpoPenalty);
					totalPenalty += Float.valueOf(penalty.getAmount());
				}
				fpoPenalRepo.saveAll(fpoPenaltyList);
			}
			if (detainedArticleRemark.getFineSections() != null || detainedArticleRemark.getPenaltySections() != null) {
				fpoMainRepost.updateTotalAmountPayable(fpoMain.getITEM_ID(),
						(fpoMain.getTOT_DUTY() + totalFine + totalPenalty), totalFine, totalPenalty);
			} else {
				System.out.println("itemid is " + fpoMain.getITEM_ID());
				System.out.println("amtpayable is " + fpoMain.getTOT_DUTY());
				fpoMainRepost.updateTotalAmountPayable(fpoMain.getITEM_ID(), fpoMain.getTOT_DUTY(), 0f, 0f);
			}
			FpoDetainedInfo fpoDetainedInfo = new FpoDetainedInfo();
			fpoDetainedInfo.setArticleId(fpoMain.getITEM_ID());
			fpoDetainedInfo.setRemarks(detainedArticleRemark.getDetainRemarks());
			fpoDetainedInfo.setDetainDecision(detainedArticleRemark.getDetainDecision());
			if (detainedArticleRemark.getDecisionReason() != null
					&& !detainedArticleRemark.getDecisionReason().isEmpty())
				fpoDetainedInfo.setDecisionReason(detainedArticleRemark.getDecisionReason());
			fpoDetainedInfo.setDetainedNo(maxDetainedNo);
			fpoDetainedInfo.setRole(role);
			fpoDetainedInfo.setOfficerId(officerId);
			fpoDetainedInfo.setCussite(cussite);
			fpoDetainedInfo.setDetainCase(detainedArticleRemark.getDetainCase());
			fpoDetainedInfo.setDetainCaseOther(detainedArticleRemark.getDetainCaseOther());
			fpoDetainedInfo.setDetainMethod(detainedArticleRemark.getDetainMethod());
			fpoDetainedInfo.setDetainMethodOther(detainedArticleRemark.getDetainMethodOther());
			fpoDetainedInfoRepo.updateDetainCompleted(fpoMain.getITEM_ID());
			fpoDetainedInfoRepo.save(fpoDetainedInfo);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
			if (null == slno)
				slno = Long.valueOf(0);
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			fpoService.insertIntofpoMvmntDb(null, cinNo, fpoMvmntRepo.getcindtmvmnt(cinNo), new java.util.Date(), slno,
					StaticConstants.APPRAISER, "P5", session,request);
			if (detainedArticleRemark.getDetainDecision().equalsIgnoreCase(StaticConstants.DETAINDECISIONOPTION1)) {
				fpoDetainedInfo.setDetainCompleted("Y");
				fpoDetainedInfo.setDetainDecisionDate(new Date());
				fpoDetainedInfoRepo.save(fpoDetainedInfo);
				fpoService.moveDetToAss(cinNo, session,request);
			} else if (detainedArticleRemark.getDetainDecision()
					.equalsIgnoreCase(StaticConstants.DETAINDECISIONOPTION2)) {
				fpoDetainedInfo.setDetainCompleted("Y");
				fpoDetainedInfo.setDetainDecisionDate(new Date());
				fpoDetainedInfoRepo.save(fpoDetainedInfo);
			} else if (detainedArticleRemark.getDetainDecision().equalsIgnoreCase(StaticConstants.DETAINDECISIONOPTION3)
					|| detainedArticleRemark.getDetainDecision()
							.equalsIgnoreCase(StaticConstants.DETAINDECISIONOPTION4)) {
				fpoDetainedInfo.setCurrentOfficerRole(StaticConstants.ASSISTANTCOMMISSIONER);
				fpoDetainedInfoRepo.save(fpoDetainedInfo);
			}
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
