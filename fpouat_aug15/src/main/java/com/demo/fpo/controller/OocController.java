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

import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.bean.DetainedDocumentBean;
import com.demo.fpo.bean.OocCancelRemarkBean;
import com.demo.fpo.bean.OocDeliveryBean;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoOocCancelComments;
import com.demo.fpo.model.FpoOocCancelDocInfo;
import com.demo.fpo.model.FpoOocCancelInfo;
import com.demo.fpo.prop.StaticConstants;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoOocCancelCommentsRepo;
import com.demo.fpo.repos.FpoOocCancelDocInfoRepo;
import com.demo.fpo.repos.FpoOocCancelInfoRepo;
import com.demo.fpo.repos.OsPathFpoRepo;
import com.demo.fpo.service.FpoService;
import com.demo.fpo.service.OocCancelService;

@Controller
public class OocController {

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	OsPathFpoRepo osPathFpoRepo;

	@Autowired
	FpoOocCancelInfoRepo fpoOocCancelInfoRepo;

	@Autowired
	FpoOocCancelDocInfoRepo fpoOocCancelDocInfoRepo;

	@Autowired
	FpoMvmntRepo fpoMvmntRepo;

	@Autowired
	FpoService fpoService;

	@Autowired
	OocCancelService oocCancelService;

	@Autowired
	FpoOocCancelCommentsRepo fpoOocCancelCommentsRepo;

	@GetMapping(value = "/ooccancellation")
	public ModelAndView oocCancellation() {
		ModelAndView models = new ModelAndView("ooc/ooccancel");
		models.addObject("roleAppraiser", StaticConstants.APPRAISER);
		return models;
	}

	@GetMapping(value = "/processooccancellation")
	public @ResponseBody void processOocCancellation(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		List<DetainedArticleBean> oocCancelProcessArticles = oocCancelService.getOocCancelProcessArticles(session,request);
		jsonObj.put("data", oocCancelProcessArticles);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}
	
	

	@GetMapping(value = "/checkoocgiven")
	public @ResponseBody void checkOocGiven(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		String itemId = request.getParameter("itemId");
		String cusite=request.getSession().getAttribute("cuSite").toString();
		boolean oocGiven = fpoMainRepost.checkOocGiven(itemId)>0;
		boolean oocCancelPending = fpoOocCancelInfoRepo.getPendingByarticleId(itemId) > 0;
		if (oocGiven) {
			OocDeliveryBean oocDelivery = oocCancelService.getOocDeliveryDetails(itemId,cusite);
			jsonObj.put("oocDelivery", new JSONObject(oocDelivery));
		}
		System.out.println("value is " + fpoMainRepost.getclrsite(itemId));
		jsonObj.put("clrsite",fpoMainRepost.getclrsite(itemId));
		jsonObj.put("oocGiven", oocGiven);
		jsonObj.put("ooccancelprocess", oocCancelPending);
		response.setContentType("application/json;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}
	
	
	@GetMapping(value = "/ooccancellationremarks")		
	public @ResponseBody ModelAndView oocCancellation(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		String itemId = request.getParameter("itemId");
		Boolean view = Boolean.valueOf(request.getParameter("view"));
		Boolean cmtflows = Boolean.valueOf(request.getParameter("cmtflows"));
		String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("cinNo", cinNo);
		System.out.println("view="+view);
		System.out.println("cmtflow="+cmtflows);
		List<FpoOocCancelInfo> oocCancelRemarks = new ArrayList<FpoOocCancelInfo>();
		if (cmtflows) {
			oocCancelRemarks = fpoOocCancelInfoRepo.getMaxDetailsByarticleId(itemId);
		} else {
			oocCancelRemarks = fpoOocCancelInfoRepo.getDetailsByarticleId(itemId);
		}
		if (!oocCancelRemarks.isEmpty() && view) {
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			for (FpoOocCancelInfo fpoOocCancelInfo : oocCancelRemarks) {
			fpoOocCancelInfo.setFpoOocCancelDocInfo(
						fpoOocCancelDocInfoRepo.getByItemId(itemId, fpoOocCancelInfo.getOocCancelNo()));
			}
			if (cmtflows) {
				List<FpoOocCancelComments> fpoOocCancelComments = fpoOocCancelCommentsRepo
						.getByItemIdandOocCancelNo(itemId, oocCancelRemarks.get(0).getOocCancelNo());
				List<FpoOocCancelComments> fpoOocCancelAppraiserComments = fpoOocCancelComments.stream()
						.filter(comments -> {
							return comments.getRole().equalsIgnoreCase(StaticConstants.APPRAISER);
						}).collect(Collectors.toList());
				List<FpoOocCancelComments> fpoOocCancelACComments = fpoOocCancelComments.stream().filter(comments -> {
					return comments.getRole().equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER);
				}).collect(Collectors.toList());
				model.addAttribute("appraiserComments", fpoOocCancelAppraiserComments);
				model.addAttribute("acComments", fpoOocCancelACComments);
				model.addAttribute("comments", !fpoOocCancelComments.isEmpty());
			}
			model.addAttribute("acrolematch", role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER));
			model.addAttribute("oocCancelRemarks", oocCancelRemarks);
			System.out.println("size is " + oocCancelRemarks.size());
			model.addAttribute("view", view);
			model.addAttribute("role",role);
			model.addAttribute("artrole",oocCancelRemarks.get(0).getCurrentOfficerRole());
			model.addAttribute("cmtflows", cmtflows);
			return new ModelAndView("ooc/view-ooccancelremarks");
		}
		boolean oocGiven = fpoMainRepost.checkOocGiven(itemId) > 0;
		boolean oocCancelPending = fpoOocCancelInfoRepo.getPendingByarticleId(itemId) > 0;
		if (oocGiven && !oocCancelPending) {
			return new ModelAndView("ooc/ooccancelremarks");
		}
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("oocGiven", oocGiven);
		jsonObj.put("ooccancelprocess", oocCancelPending);
		response.setContentType("application/json;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
		return null;
	}

	@GetMapping(value = "/getooccancelledarticleshistory")
	public @ResponseBody void getOocCancelledArticlesHistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<DetainArticleHistoryBean> oocCancelledArticlesHistory = new ArrayList<>();
		if (fromDate != null && toDate != null) {
			oocCancelledArticlesHistory = oocCancelService.getOocCancelledArticlesHistory(fromDate, toDate, cusSite);
			for (DetainArticleHistoryBean oocCancelledArticlesHistoryBean : oocCancelledArticlesHistory) {
				oocCancelledArticlesHistoryBean.setCurrentStatus(fpoService.getpos(oocCancelledArticlesHistoryBean.getArticleId(), session,request));
			}
		}
		jsonObj.put("data", oocCancelledArticlesHistory);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@PostMapping(value = "/ooccancelremarksbyarticleidanddetainedno")
	public @ResponseBody ModelAndView viewDetainedRemarksByArticleIdAndByDetainedNo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		String itemId = request.getParameter("itemId");
		Long oocCancelNo = Long.valueOf(request.getParameter("oocCancelNo"));
		String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("cinNo", cinNo);
		List<FpoOocCancelInfo> oocCancelRemarks = fpoOocCancelInfoRepo.getDetailsByarticleIdAndDetainedNo(itemId,
				oocCancelNo);
		String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
		for (FpoOocCancelInfo fpoOocCancelInfo : oocCancelRemarks) {
			fpoOocCancelInfo.setFpoOocCancelDocInfo(
					fpoOocCancelDocInfoRepo.getByItemId(itemId, fpoOocCancelInfo.getOocCancelNo()));
		}
		if (true) {
			List<FpoOocCancelComments> fpoOocCancelComments = fpoOocCancelCommentsRepo.getByItemIdandOocCancelNo(itemId,
					oocCancelRemarks.get(0).getOocCancelNo());
			List<FpoOocCancelComments> fpoOocCancelAppraiserComments = fpoOocCancelComments.stream()
					.filter(comments -> {
						return comments.getRole().equalsIgnoreCase(StaticConstants.APPRAISER);
					}).collect(Collectors.toList());
			List<FpoOocCancelComments> fpoOocCancelACComments = fpoOocCancelComments.stream().filter(comments -> {
				return comments.getRole().equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER);
			}).collect(Collectors.toList());
			model.addAttribute("appraiserComments", fpoOocCancelAppraiserComments);
			model.addAttribute("acComments", fpoOocCancelACComments);
			model.addAttribute("comments", !fpoOocCancelComments.isEmpty());
		}
		model.addAttribute("acrolematch", role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER));
		model.addAttribute("role",role);
		model.addAttribute("oocCancelRemarks", oocCancelRemarks);
		model.addAttribute("view", true);
		model.addAttribute("cmtflows", false);
		model.addAttribute("history", true);
		return new ModelAndView("ooc/view-ooccancelremarks");
	}

	@PostMapping(value = "/saveooccancelcomments")
	public @ResponseBody void saveOocCancelComments(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String itemId = request.getParameter("itemId");
			String comment = request.getParameter("comment");
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
			Long oocCancelNo = fpoOocCancelInfoRepo.getMaxOocCancelNo(itemId);
			Long maxSeqNo = fpoOocCancelCommentsRepo.getMaxSeqNo(itemId, oocCancelNo);
			if (null == maxSeqNo || maxSeqNo.toString().isEmpty()) {
				maxSeqNo = 0l;
			}
			maxSeqNo = maxSeqNo + 1l;
			FpoOocCancelComments fpoOocCancelComments = new FpoOocCancelComments();
			fpoOocCancelComments.setCinNo(cinNo);
			fpoOocCancelComments.setSeqNo(maxSeqNo);
			fpoOocCancelComments.setItemId(itemId);
			fpoOocCancelComments.setRole(role);
			fpoOocCancelComments.setOfficerId(
					request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
			fpoOocCancelComments.setComments(comment);
			fpoOocCancelComments.setEntryDate(new Date());
			fpoOocCancelComments.setOocCancelNo(oocCancelNo);
			fpoOocCancelCommentsRepo.save(fpoOocCancelComments);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
			if (null == slno)
				slno = Long.valueOf(0);
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			fpoService.insertIntofpoMvmntDb(null, cinNo, fpoMvmntRepo.getcindtmvmnt(cinNo), new java.util.Date(), slno,
					role, "O1", session,request);
			if (role.equalsIgnoreCase(StaticConstants.APPRAISER))
				fpoOocCancelInfoRepo.updateCurrentOfficerRole(StaticConstants.ASSISTANTCOMMISSIONER, itemId,
						oocCancelNo);
			if (role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER))
				fpoOocCancelInfoRepo.updateCurrentOfficerRole(StaticConstants.APPRAISER, itemId, oocCancelNo);
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

	@PostMapping(value = "/oocapproveorreject")
	public @ResponseBody void oocApproveToAssessment(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String itemId = request.getParameter("itemId");
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			String acremarks = request.getParameter("acremarks");
			Boolean oocAccept = Boolean.valueOf(request.getParameter("ooc"));
			String cinNo = fpoMainRepost.getCinIdByItemId(itemId);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
			if (null == slno)
				slno = Long.valueOf(0);
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			fpoService.insertIntofpoMvmntDb(null, cinNo, fpoMvmntRepo.getcindtmvmnt(cinNo), new java.util.Date(), slno,
					role, "O1", session,request);
			Long maxOocCancelNo = fpoOocCancelInfoRepo.getMaxOocCancelNo(itemId);
			fpoOocCancelInfoRepo.updateOocCancelCompleted(itemId, acremarks, (oocAccept ? "ACCEPTED" : "REJECTED"),
					maxOocCancelNo);
			if (oocAccept) {
				List<FPO_MAIN> fpoMain = fpoMainRepost.getDelivery(cinNo);
				if (fpoMain.isEmpty()) {
					fpoService.moveDetToAss(cinNo, session,request);
				}else {
					fpoService.moveDetToAssWithClrSite(cinNo, session,fpoMain.get(0),request);
				}
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

	@PostMapping(value = "/scanningooccanceldocuments")
	public @ResponseBody void scanningOocCancelDocuments(@ModelAttribute OocCancelRemarkBean oocCancelRemark,
			HttpServletResponse response) throws InterruptedException {
		String oocCancelPath = osPathFpoRepo.getOocCancelPath();
		List<String> maliciousFiles = new ArrayList<>();
		if (oocCancelRemark.getDocumentSections() != null) {
			for (DetainedDocumentBean detainDocumentSection : oocCancelRemark.getDocumentSections()) {
				File destination = new File(oocCancelPath + oocCancelRemark.getCinNo()
						+ detainDocumentSection.getDocumentFile().getOriginalFilename());
				try {
					detainDocumentSection.getDocumentFile().transferTo(destination);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		TimeUnit.SECONDS.sleep(1);
		if (oocCancelRemark.getDocumentSections() != null) {
			for (DetainedDocumentBean detainDocumentSection : oocCancelRemark.getDocumentSections()) {
				File destination = new File(oocCancelPath + oocCancelRemark.getCinNo()
						+ detainDocumentSection.getDocumentFile().getOriginalFilename());
				if (destination.isFile()) {
					destination.delete();
				} else {
					maliciousFiles.add(detainDocumentSection.getDocumentFile().getOriginalFilename());
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

	@PostMapping(value = "/saveooccancelreply")
	public @ResponseBody void saveOocCancelreply(@ModelAttribute OocCancelRemarkBean oocCancelRemark,
			HttpServletResponse response, HttpSession session, HttpServletRequest request) {
		try {
			String oocCancelPath = osPathFpoRepo.getOocCancelPath();
			String cinNo = oocCancelRemark.getCinNo();
			List<FPO_MAIN> fpoMains = fpoMainRepost.getmain(cinNo);
			FPO_MAIN fpoMain = fpoMains.isEmpty() ? new FPO_MAIN() : fpoMains.get(0);
			Long maxOocCancelNo = fpoOocCancelInfoRepo.getMaxOocCancelNo(fpoMain.getITEM_ID());
			String cussite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
			String officerId = request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString();
			String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
			if (null == maxOocCancelNo || maxOocCancelNo.toString().isEmpty()) {
				maxOocCancelNo = 0l;
			}
			maxOocCancelNo = maxOocCancelNo + 1l;
			int index = 1;
			if (oocCancelRemark.getDocumentSections() != null) {
				for (DetainedDocumentBean documentSection : oocCancelRemark.getDocumentSections()) {
					File destination = new File(oocCancelPath + fpoMain.getITEM_ID() + "_ooccancel-" + maxOocCancelNo
							+ "_" + index + ".pdf");
					try {
						documentSection.getDocumentFile().transferTo(destination);
						index++;
						FpoOocCancelDocInfo fpoOocCancelDocInfo = new FpoOocCancelDocInfo();
						fpoOocCancelDocInfo.setArticleId(fpoMain.getITEM_ID());
						fpoOocCancelDocInfo.setDocumentType(documentSection.getDocumentType());
						fpoOocCancelDocInfo.setDocumentTypeOtherValue(documentSection.getDocumentName());
						fpoOocCancelDocInfo.setDocumentDate(documentSection.getDocumentDate());
						fpoOocCancelDocInfo.setDocumentName(documentSection.getDocumentFile().getOriginalFilename());
						fpoOocCancelDocInfo.setDocumentPhysicalFile(destination.getPath());
						fpoOocCancelDocInfo.setOocCancelNo(maxOocCancelNo);
						fpoOocCancelDocInfoRepo.save(fpoOocCancelDocInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			FpoOocCancelInfo fpoOocCancelInfo = new FpoOocCancelInfo();
			fpoOocCancelInfo.setArticleId(fpoMain.getITEM_ID());
			fpoOocCancelInfo.setRemarks(oocCancelRemark.getOocCancelRemarks());
			fpoOocCancelInfo.setReason(oocCancelRemark.getOocCancelReason());
			fpoOocCancelInfo.setOocCancelNo(maxOocCancelNo);
			fpoOocCancelInfo.setRole(role);
			fpoOocCancelInfo.setOfficerId(officerId);
			fpoOocCancelInfo.setCussite(cussite);
			fpoOocCancelInfo.setCurrentOfficerRole(StaticConstants.ASSISTANTCOMMISSIONER);
			fpoOocCancelInfoRepo.updateOocCancelCompleted(fpoMain.getITEM_ID());
			fpoOocCancelInfoRepo.save(fpoOocCancelInfo);
			String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
			if (null == slno)
				slno = Long.valueOf(0);
			fpoMvmntRepo.updextdtstr(cinNo, utilDate);
			fpoService.insertIntofpoMvmntDb(null, cinNo, fpoMvmntRepo.getcindtmvmnt(cinNo), new java.util.Date(), slno,
					StaticConstants.APPRAISER, "O1", session,request);
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
