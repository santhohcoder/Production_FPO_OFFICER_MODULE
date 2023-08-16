package com.demo.fpo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.bean.DocumentSection;
import com.demo.fpo.bean.QueryReply;
import com.demo.fpo.bean.QueryReplyHistoryBean;
import com.demo.fpo.model.DCALLQRY_GEN;
import com.demo.fpo.model.DCallDocInfo;
import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryDin;
import com.demo.fpo.model.FpoSecondDefaultQuery;
import com.demo.fpo.repos.DCallDocInfoRepo;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoAddlQryRepo;
import com.demo.fpo.repos.FpoDcallQRYRepo;
import com.demo.fpo.repos.FpoDefualtQueryRepo;
import com.demo.fpo.repos.FpoQueryDinRepo;
import com.demo.fpo.repos.FpoQueryRepo;
import com.demo.fpo.repos.FpoSecondDefualtQueryRepo;
import com.demo.fpo.repos.OsPathFpoRepo;
import com.demo.fpo.service.FpoQueryService;
import com.demo.fpo.service.FpoService;
//import com.itextpdf.text.Paragraph;

@Controller
public class QueryController {
	
	private static final Logger log = LogManager.getLogger(QueryController.class); 

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FpoService fpoService;

	@Autowired
	FpoDefualtQueryRepo fpoDcallQueryRepo;

	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	OsPathFpoRepo osPathFpoRepo;

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	DCallDocInfoRepo dCallDocInfoRepo;

	@Autowired
	FpoQueryService fpoQueryService;

	@Autowired
	FpoDcallQRYRepo fpoDcallQryRepo;

	@Autowired
	FpoAddlQryRepo fpoAddlQryRepo;

	@Autowired
	FpoSecondDefualtQueryRepo fpoSecondDefualtQueryRepo;

	@GetMapping(value = "/queryreply")
	public @ResponseBody ModelAndView queryReply(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String cinNo = request.getParameter("cinNo");		
		FpoQuery offDel = fpoQueryRepo.getOffDet(cinNo);
		model.addAttribute("raiqrydt", offDel.getQRY_DATE());		
		log.info("in queryreply cinno is " + cinNo);
		DCALLQRY_GEN dcallQueryGen = fpoDcallQryRepo.getFirstDCallNumberByCinNo(cinNo);
		log.info("in queryreply dcallquerygen  url is " + dcallQueryGen.getGenurl());
		List<FpoQueryDin> fpoQueryDin = fpoQueryDinRepo.getfpouniqueno(cinNo);
		log.info("in queryreply din uniqueno is " + fpoQueryDin.get(0).getUniqueNo());
		if (!fpoQueryDin.isEmpty()) {
			model.addAttribute("queryRemarks", fpoQueryDin.get(0).getRemarks());
		}
		log.info("before assigning to model attributes in query controller");
		model.addAttribute("queryItems", fpoQueryRepo.getfpoqryandDesc(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		model.addAttribute("defaultQueries", fpoService.getSpecifiedDefualtQuery(fpoQueryRepo.getfpoDefQry(cinNo),DocName));
		model.addAttribute("dcallQueryGen", dcallQueryGen);
		model.addAttribute("cinNo", cinNo);
		log.info("after assigning to model attributes in query controller");
		return new ModelAndView("queryreply/dashboard");
	}

	@GetMapping(value = "/additionalqueryreply")
	public @ResponseBody ModelAndView additionalQueryReply(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String cinNo = request.getParameter("cinNo");		
		FpoAddlQry fpoAddQry = fpoAddlQryRepo.getFpoaddQry(cinNo);
		model.addAttribute("raiqrydt", fpoAddQry.getQRY_DATE());		
		String articleStage = request.getParameter("articleStage");
		DCALLQRY_GEN dcallQueryGen = fpoDcallQryRepo.getFirstDCallNumberByCinNo(cinNo);
		DCALLQRY_GEN additionalDcallQueryGen = fpoDcallQryRepo.getAdditionalDCallNumberByCinNo(cinNo);
		List<FpoQueryDin> fpoQueryDin = fpoQueryDinRepo.getfpouniqueno(cinNo);
		if (!fpoQueryDin.isEmpty()) {
			model.addAttribute("queryRemarks", fpoQueryDin.get(0).getRemarks());
			model.addAttribute("generalQueryReply", fpoQueryDin.get(0).getGeneralQryReply());
		}
		model.addAttribute("queryItems", fpoQueryRepo.getfpoqryandDesc(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		model.addAttribute("defaultQueries", fpoService.getSpecifiedDefualtQuery(fpoQueryRepo.getfpoDefQry(cinNo),DocName));
		model.addAttribute("defaultQueryReply", fpoQueryRepo.getDefaultQueryReply(cinNo));
		model.addAttribute("dcallQueryGen", dcallQueryGen);
		model.addAttribute("additionalDcallQueryGen", additionalDcallQueryGen);
		model.addAttribute("cinNo", cinNo);
	//	List<FpoAddlQry> additionalQry = fpoAddlQryRepo.getArticleAwaitingQuery(cinNo, articleStage);
		List<FpoAddlQry> additionalQry = fpoAddlQryRepo.getArticlenostage(cinNo);
		model.addAttribute("additionalQry", additionalQry.get(0));
		List<FpoAddlQry> AddlQry = fpoAddlQryRepo.getAllFpoaddlQuery(cinNo);
		
		
		boolean pacexist=false;	
		for (int i = 0; i < AddlQry.size()-1 ; i++) {	
			System.out.println("checking"+AddlQry.get(i).getQRY_ROLE());	
			if(AddlQry.get(i).getQRY_ROLE().equals("PAC")) {	
				System.out.println("pac exists");	
				pacexist=true;	
				break;	
			}	
				
		}	
			
		if(pacexist)	
			 AddlQry = fpoAddlQryRepo.getAllFpoaddlQueryonlyforPAC(cinNo);
		
		List<String> addDefQry = new ArrayList<>();
		int once5 = 0;
		int once6 = 0;
		String schr = "";
		int prn = 97;
		char ch2 = (char) prn;
		for (int i = 0; i < AddlQry.size() - 1; i++) {
			ch2 = (char) prn;
			List<FpoSecondDefaultQuery> secdefqry;
			if (AddlQry.get(i).getQRY_DEF_SLNO().equals("1")) {
				addDefQry.add(ch2 + ") " + AddlQry.get(0).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("2")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("2");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("3")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("3");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("4")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("4");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("5")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("5");
				if (once5 == 0) {
					addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
				}
				if (once5 == 0)
					schr = String.format("%1$15s", "i)");
				else
					schr = String.format("%1$15s", "i)");
				addDefQry.add(schr + AddlQry.get(i).getQRY_DESC());
				once5 = once5 + 1;
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("6")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("6");
				if (once6 == 0) {
					addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
				}
				if (once6 == 0)
					schr = String.format("%1$15s", "i)");
				else
					schr = String.format("%1$15s", "i)");
				addDefQry.add(schr + AddlQry.get(i).getQRY_DESC());
				once6 = once6 + 1;
			}
			else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("7")) { /// changed	
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("7");	
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			}

			
			if ((once5 == 0 || once6 == 0) && once5 != 1)
				prn = prn + 1;
		}
		model.addAttribute("addDefQry", addDefQry);
		return new ModelAndView("queryreply/additional-dashboard");
	}

	@GetMapping(value = "/viewadditionalqueryreply")
	public @ResponseBody ModelAndView viewAdditionalQueryReply(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String cinNo = request.getParameter("cinNo");
		String articleStage = request.getParameter("articleStage");
		DCALLQRY_GEN dcallQueryGen = fpoDcallQryRepo.getAdditionalDCallNumberByCinNo(cinNo);
		
		
		
		FpoAddlQry fpoAddQry = fpoAddlQryRepo.getFpoaddQry(cinNo);
		String fpoupdt = fpoAddlQryRepo.getupdt(cinNo);
		String replydt = fpoAddlQryRepo.getreplydt(cinNo);
		model.addAttribute("replydt",replydt);
		
		String[][] queryRply= fpoAddlQryRepo.getUserDe(dcallQueryGen.getDcallno());
		if(queryRply.length>0) {
		String mobino = queryRply[0][0]; 
		String mailId = queryRply[0][1];
		System.out.println(mobino);
		System.out.println(mailId);
		 model.addAttribute("mobino",mobino);
		 model.addAttribute("mailId",mailId);
		 model.addAttribute("name"," ");
		 model.addAttribute("enable", true);
		}
		else {
			
			model.addAttribute("mobino",fpoAddQry.getQRY_MOBILENO());
			 model.addAttribute("mailId",fpoAddQry.getQRY_EMAILID());
			 model.addAttribute("enable", false);
			 String usernme = fpoQueryRepo.getusrname(fpoAddQry.getQRY_OFF_ID());			 
			 model.addAttribute("officername",usernme ); //
			 model.addAttribute("name", fpoAddQry.getEnt_name());  //
			 model.addAttribute("updatedt",fpoupdt);   //
			
		}

		
		
		
		
		
		
		
		
		List<FpoQueryDin> fpoQueryDin = fpoQueryDinRepo.getfpouniqueno(cinNo);
		if (!fpoQueryDin.isEmpty()) {
			model.addAttribute("queryRemarks", fpoQueryDin.get(0).getRemarks());
			model.addAttribute("generalQueryReply", fpoQueryDin.get(0).getGeneralQryReply());
		}
		model.addAttribute("queryItems", fpoQueryRepo.getfpoqryandDesc(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		model.addAttribute("defaultQueries", fpoService.getSpecifiedDefualtQuery(fpoQueryRepo.getfpoDefQry(cinNo),DocName));
		model.addAttribute("defaultQueryReply", fpoQueryRepo.getDefaultQueryReply(cinNo));
		model.addAttribute("dcallQueryGen", dcallQueryGen);
		model.addAttribute("cinNo", cinNo);
		List<FpoAddlQry> additionalQry = fpoAddlQryRepo.getArticleAwaitingQuery(cinNo, articleStage);
		model.addAttribute("additionalQry", additionalQry.get(0));
		List<FpoAddlQry> AddlQry = fpoAddlQryRepo.getAllFpoaddlQuery(cinNo);
		
		int length=AddlQry.size()-1;
		
		boolean pacexist=false;	
		for (int i = 0; i < AddlQry.size()-1 ; i++) {	
			System.out.println("checking"+AddlQry.get(i).getQRY_ROLE());	
			if(AddlQry.get(i).getQRY_ROLE().equals("PAC")) {	
				System.out.println("pac exists "+i);	
				pacexist=true;	
				break;	
			}	
				
		}	
		System.out.println(AddlQry.size());
			
		if(pacexist) {	
			 AddlQry = fpoAddlQryRepo.getAllFpoaddlQueryonlyforPAC(cinNo);
			 length=AddlQry.size();
		}
		
		List<String> addDefQry = new ArrayList<>();
		int once5 = 0;
		int once6 = 0;
		String schr = "";
		int prn = 97;
		char ch2 = (char) prn;
		for (int i = 0; i < length; i++) {
			ch2 = (char) prn;
			List<FpoSecondDefaultQuery> secdefqry;
			if (AddlQry.get(i).getQRY_DEF_SLNO().equals("1")) {
				addDefQry.add(ch2 + ") " + AddlQry.get(0).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("2")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("2");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("3")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("3");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("4")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("4");
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("5")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("5");
				if (once5 == 0) {
					addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
				}
				if (once5 == 0)
					schr = String.format("%1$15s", "i)");
				else
					schr = String.format("%1$15s", "i)");
				addDefQry.add(schr + AddlQry.get(i).getQRY_DESC());
				once5 = once5 + 1;
			} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("6")) {
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("6");
				if (once6 == 0) {
					addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
				}
				if (once6 == 0)
					schr = String.format("%1$15s", "i)");
				else
					schr = String.format("%1$15s", "i)");
				addDefQry.add(schr + AddlQry.get(i).getQRY_DESC());
				once6 = once6 + 1;
			}
			else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("7")) { /// changed	
				secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("7");	
				addDefQry.add(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " " + AddlQry.get(i).getQRY_DESC());
			}	
			if ((once5 == 0 || once6 == 0) && once5 != 1)
				prn = prn + 1;
		}
		model.addAttribute("addDefQry", addDefQry);
		List<DCallDocInfo> dcallDocInfo = dCallDocInfoRepo.findByDcallNumber(dcallQueryGen.getDcallno());
		List<DCallDocInfo> kycDcallDoc = dcallDocInfo.stream().filter(doc -> {
			return doc.getIsKyc() != null && doc.getIsKyc().equalsIgnoreCase("Y");
		}).collect(Collectors.toList());
		List<DCallDocInfo> supportDcallDoc = dcallDocInfo.stream().filter(doc -> {
			return !(doc.getIsKyc() != null && doc.getIsKyc().equalsIgnoreCase("Y"));
		}).collect(Collectors.toList());
		model.addAttribute("kycList", kycDcallDoc);
		model.addAttribute("supportList", supportDcallDoc);
		return new ModelAndView("queryreply/view-additional-dashboard");
	}

	@GetMapping(value = "/viewqueryreply")
	public @ResponseBody ModelAndView viewQueryReply(HttpServletRequest request, HttpServletResponse response,
			Model model) {
	
		
		String cinNo = request.getParameter("cinNo");
		DCALLQRY_GEN dcallQueryGen = fpoDcallQryRepo.getFirstDCallNumberByCinNo(cinNo);
		
		
		
		String rplyDt = fpoQueryRepo.getreplydt(cinNo);
		model.addAttribute("fpoqery",rplyDt);
		String fpoupdt = fpoQueryRepo.getupdt(cinNo);

		String[][] userDet = fpoQueryRepo.getuserde(dcallQueryGen.getDcallno());  
				if(userDet.length>0) {
				String mobino = userDet[0][0]; 
				String mailId = userDet[0][1];
				System.out.println(mobino);
				System.out.println(mailId);
				 model.addAttribute("mobino1",mobino);
				 model.addAttribute("mailId1",mailId);
				 model.addAttribute("name"," ");
				 model.addAttribute("enable", true);
				}
				else {
					FpoQuery offDel = fpoQueryRepo.getOffDet(cinNo);  //fpoquery
					 model.addAttribute("mobino",offDel.getMobile_No());
					 model.addAttribute("mailId", offDel.getEmail_Id());
					 model.addAttribute("name", offDel.getEnt_Name());
					 model.addAttribute("updatedt",fpoupdt);
					 model.addAttribute("enable", false);
					 String usernme = fpoQueryRepo.getusrname(offDel.getQRY_OFF_ID());			 
					 model.addAttribute("officername",usernme );
				
					 
				}
		
		
		
		
		
		List<FpoQueryDin> fpoQueryDin = fpoQueryDinRepo.getfpouniqueno(cinNo);
		if (!fpoQueryDin.isEmpty()) {
			model.addAttribute("queryRemarks", fpoQueryDin.get(0).getRemarks());
			model.addAttribute("generalQueryReply", fpoQueryDin.get(0).getGeneralQryReply());
		}
		model.addAttribute("queryItems", fpoQueryRepo.getfpoqryandDesc(cinNo));
		String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	
		model.addAttribute("defaultQueries", fpoService.getSpecifiedDefualtQuery(fpoQueryRepo.getfpoDefQry(cinNo),DocName));
		model.addAttribute("defaultQueryReply", fpoQueryRepo.getDefaultQueryReply(cinNo));
		model.addAttribute("dcallQueryGen", dcallQueryGen);
		List<DCallDocInfo> dcallDocInfo = dCallDocInfoRepo.findByDcallNumber(dcallQueryGen.getDcallno());
		List<DCallDocInfo> kycDcallDoc = dcallDocInfo.stream().filter(doc -> {
			return doc.getIsKyc() != null && doc.getIsKyc().equalsIgnoreCase("Y");
		}).collect(Collectors.toList());
		List<DCallDocInfo> supportDcallDoc = dcallDocInfo.stream().filter(doc -> {
			return !(doc.getIsKyc() != null && doc.getIsKyc().equalsIgnoreCase("Y"));
		}).collect(Collectors.toList());
		model.addAttribute("kycList", kycDcallDoc);
		model.addAttribute("supportList", supportDcallDoc);
		model.addAttribute("cinNo", cinNo);
		return new ModelAndView("queryreply/view-dashboard");
	}

	@PostMapping(value = "/savereply")
	public @ResponseBody void saveReply(@ModelAttribute QueryReply queryReply, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
	log.info("in savereply");
		String qryReplyPath = osPathFpoRepo.getQryReplyPath();
		log.info("in savereply qryReplyPath "+ qryReplyPath);
		String cinNo = fpoMainRepost.getCinIdByItemId(queryReply.getArticleId());
		log.info("in savereply cinno "+ cinNo);
		String articleStage = queryReply.getDcallLetterNumber().substring(0, 3);
		log.info("articleStage is" + articleStage);
		int index = 1;
		log.info("before writign into document section");
		
		
		String replydt = queryReply.getReplydt();///
		request.getSession().setAttribute("replydt", replydt);
		String entname = queryReply.getEnt_name();
		String mobile = queryReply.getMobileno();
		String mailid = queryReply.getMailid();
		
		
		
		
		
		
		
		
		for (DocumentSection kycSection : queryReply.getKycSections()) {
			File destination = new File(
					qryReplyPath + queryReply.getArticleId() + "_" + articleStage + "_" + index + ".pdf");
			try {
				kycSection.getFileAttachment().transferTo(destination);
				index++;
				DCallDocInfo dcallDocInfo = new DCallDocInfo();
				dcallDocInfo.setIsKyc("Y");
				dcallDocInfo.setArticleStage(articleStage);
				dcallDocInfo.setDcallNumber(queryReply.getDcallLetterNumber());
				dcallDocInfo.setDocumentDescription(kycSection.getDescription());
				dcallDocInfo.setDocumentName(kycSection.getFileAttachment().getOriginalFilename());
				dcallDocInfo.setDocumentPhysicalFile(destination.getPath());
				dcallDocInfo.setDocumentReferenceNumber(kycSection.getDocumentNumber());
				dcallDocInfo.setDocumentType(kycSection.getTypeOfDocument());
				dCallDocInfoRepo.save(dcallDocInfo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		log.info("after writign into document section");
		log.info("before writign into support section");
		if (queryReply.getSupportSections() != null) {
			for (DocumentSection supportSection : queryReply.getSupportSections()) {
				File destination = new File(
						qryReplyPath + queryReply.getArticleId() + "_" + articleStage + "_" + index + ".pdf");
				try {
					supportSection.getFileAttachment().transferTo(destination);
					index++;
					DCallDocInfo dcallDocInfo = new DCallDocInfo();
					dcallDocInfo.setArticleStage(articleStage);
					dcallDocInfo.setDcallNumber(queryReply.getDcallLetterNumber());
					dcallDocInfo.setDocumentDescription(supportSection.getDescription());
					dcallDocInfo.setDocumentName(supportSection.getFileAttachment().getOriginalFilename());
					dcallDocInfo.setDocumentPhysicalFile(destination.getPath());
					dcallDocInfo.setDocumentReferenceNumber(supportSection.getDocumentNumber());
					dcallDocInfo.setDocumentType(supportSection.getTypeOfDocument());
					dCallDocInfoRepo.save(dcallDocInfo);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		log.info("after writign into support section");
		if (queryReply.getItemQueryResponse() != null) {
			for (FpoQuery fpoQry : queryReply.getItemQueryResponse()) {
				try {
					fpoQry.setCinNo(cinNo);
					fpoQueryService.updateRespQry(fpoQry,entname,mobile,mailid, session, request);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		log.info("before queryresponse");
		queryReply.getDefaultQueryResponse().setCinNo(cinNo);
		try {
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			fpoQueryService.updateRespQry(queryReply.getDefaultQueryResponse(),entname,mobile,mailid,  session, request);
			fpoQueryDinRepo.updateGeneralQueryReply(cinNo, queryReply.getGeneralQueryResponse());
			String ipAddress = null;
			try (final DatagramSocket socket = new DatagramSocket()) {
				socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
				ipAddress = socket.getLocalAddress().getHostAddress();
			}
			fpoDcallQueryRepo.updateQryReplyByDCallNo(queryReply.getDcallLetterNumber(), ipAddress);
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("after queryresponse");
	}

	@PostMapping(value = "/saveadditionalqryreply")
	public @ResponseBody void saveAdditionalQryReply(@ModelAttribute QueryReply queryReply,
			HttpServletResponse response, HttpSession session, HttpServletRequest request) throws ParseException {
		
		
		String replydt = queryReply.getReplydt();
		String entname = queryReply.getEnt_name();
		String mobile = queryReply.getMobileno();
		String mailid = queryReply.getMailid();

		SimpleDateFormat datere = new SimpleDateFormat("dd/MM/yyyy");
		Date replyDt = datere.parse(replydt);
		
		
		
		
		
		
		
		String qryReplyPath = osPathFpoRepo.getQryReplyPath();
		String cinNo = fpoMainRepost.getCinIdByItemId(queryReply.getArticleId());
		String articleStage = queryReply.getDcallLetterNumber().substring(0, 3);
		int index = 1;
		for (DocumentSection kycSection : queryReply.getKycSections()) {
			File destination = new File(
					qryReplyPath + queryReply.getArticleId() + "_" + articleStage + "_" + index + ".pdf");
			try {
				kycSection.getFileAttachment().transferTo(destination);
				index++;
				DCallDocInfo dcallDocInfo = new DCallDocInfo();
				dcallDocInfo.setIsKyc("Y");
				dcallDocInfo.setArticleStage(articleStage);
				dcallDocInfo.setDcallNumber(queryReply.getDcallLetterNumber());
				dcallDocInfo.setDocumentDescription(kycSection.getDescription());
				dcallDocInfo.setDocumentName(kycSection.getFileAttachment().getOriginalFilename());
				dcallDocInfo.setDocumentPhysicalFile(destination.getPath());
				dcallDocInfo.setDocumentReferenceNumber(kycSection.getDocumentNumber());
				dcallDocInfo.setDocumentType(kycSection.getTypeOfDocument());
				dCallDocInfoRepo.save(dcallDocInfo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		if (queryReply.getSupportSections() != null) {
			for (DocumentSection supportSection : queryReply.getSupportSections()) {
				File destination = new File(
						qryReplyPath + queryReply.getArticleId() + "_" + articleStage + "_" + index + ".pdf");
				try {
					supportSection.getFileAttachment().transferTo(destination);
					index++;
					DCallDocInfo dcallDocInfo = new DCallDocInfo();
					dcallDocInfo.setArticleStage(articleStage);
					dcallDocInfo.setDcallNumber(queryReply.getDcallLetterNumber());
					dcallDocInfo.setDocumentDescription(supportSection.getDescription());
					dcallDocInfo.setDocumentName(supportSection.getFileAttachment().getOriginalFilename());
					dcallDocInfo.setDocumentPhysicalFile(destination.getPath());
					dcallDocInfo.setDocumentReferenceNumber(supportSection.getDocumentNumber());
					dcallDocInfo.setDocumentType(supportSection.getTypeOfDocument());
					dCallDocInfoRepo.save(dcallDocInfo);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			PrintWriter out = null;
			JSONObject jsonObj = new JSONObject();
			Date currentdate = new Date();
			SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date1 = time.format(currentdate);
			Date date = time.parse(date1);
			fpoAddlQryRepo.updateAdditionalRespQry(cinNo, queryReply.getGeneralQueryResponse(), request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(),
					date, request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString(),replyDt,entname,mobile,mailid);
			String ipAddress = null;
			try (final DatagramSocket socket = new DatagramSocket()) {
				socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
				ipAddress = socket.getLocalAddress().getHostAddress();
			}
			fpoDcallQueryRepo.updateQryReplyByDCallNo(queryReply.getDcallLetterNumber(), ipAddress);
			jsonObj.put("status", true);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/scanningDocuments")
	public @ResponseBody void scanningDocuments(@ModelAttribute QueryReply queryReply, HttpServletResponse response)
			throws InterruptedException {
		String qryReplyPath = osPathFpoRepo.getQryReplyPath();
		List<String> maliciousFiles = new ArrayList<>();
		for (DocumentSection kycSection : queryReply.getKycSections()) {
			File destination = new File(qryReplyPath + queryReply.getDcallLetterNumber()
					+ kycSection.getFileAttachment().getOriginalFilename());
			try {
				kycSection.getFileAttachment().transferTo(destination);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		if (queryReply.getSupportSections() != null) {
			for (DocumentSection supportSection : queryReply.getSupportSections()) {
				File destination = new File(qryReplyPath + queryReply.getDcallLetterNumber()
						+ supportSection.getFileAttachment().getOriginalFilename());
				try {
					supportSection.getFileAttachment().transferTo(destination);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		TimeUnit.SECONDS.sleep(1);
		for (DocumentSection kycSection : queryReply.getKycSections()) {
			File destination = new File(qryReplyPath + queryReply.getDcallLetterNumber()
					+ kycSection.getFileAttachment().getOriginalFilename());
			if (destination.isFile()) {
				destination.delete();
			} else {
				maliciousFiles.add(kycSection.getFileAttachment().getOriginalFilename());
			}
		}
		if (queryReply.getSupportSections() != null) {
			for (DocumentSection supportSection : queryReply.getSupportSections()) {
				File destination = new File(qryReplyPath + queryReply.getDcallLetterNumber()
						+ supportSection.getFileAttachment().getOriginalFilename());
				if (destination.isFile()) {
					destination.delete();
				} else {
					maliciousFiles.add(supportSection.getFileAttachment().getOriginalFilename());
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

	@GetMapping(value = "/queryreplyofficer")
	public ModelAndView oocCancellation() {
		ModelAndView models = new ModelAndView("queryreply/queryreplyofficer");
		return models;
	}

	@GetMapping(value = "/getqryreplyarticleshistory")
	public @ResponseBody void getQryReplyArticlesHistory(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		String fromDate = request.getParameter("fromDate") != null
				&& !request.getParameter("fromDate").equalsIgnoreCase("") ? request.getParameter("fromDate") : null;
		String toDate = request.getParameter("toDate") != null && !request.getParameter("toDate").equalsIgnoreCase("")
				? request.getParameter("toDate")
				: null;
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<QueryReplyHistoryBean> qryReplyArticlesHistory = new ArrayList<>();
		if (fromDate != null && toDate != null) {
			qryReplyArticlesHistory = fpoQueryService.getQryReplyArticlesHistory(fromDate, toDate, cusSite);
			for (QueryReplyHistoryBean qryReplyArticlesHistoryBean : qryReplyArticlesHistory) {
				qryReplyArticlesHistoryBean.setCurrentStatus(
						fpoService.getpos(qryReplyArticlesHistoryBean.getArticleId(), session,request));
			}
		}
		jsonObj.put("data", qryReplyArticlesHistory);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}

	@GetMapping(value = "/checkqueryraised")
	public @ResponseBody void checkQueryRaised(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		String itemId = request.getParameter("itemId");
		String cussite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		String mainSite = fpoMainRepost.getCusite(itemId);
		if (mainSite == null) {
			jsonObj.put("articleNotFound", true);
		} else {
			if (cussite.equalsIgnoreCase(mainSite)) {
			//boolean queryRaised = fpoQueryService.checkQueryRaised(itemId, false);
				int queryRaised = fpoQueryRepo.checkQueryRaisedf(itemId);
				int queryRaisedReplied = fpoQueryRepo.checkQueryRaisedt(itemId);
		        int addtionalqueryRaised = fpoQueryRepo.checkAdditionQueryRaisedf(itemId);
				int addtionalqueryRaisedReplied = fpoQueryRepo.checkAdditionQueryRaisedt(itemId);
				if(addtionalqueryRaised > 0)
					jsonObj.put("qryType", fpoQueryService.getQryType(itemId));
				jsonObj.put("queryRaised", queryRaised);
				jsonObj.put("queryRaisedReplied", queryRaisedReplied);
				jsonObj.put("addtionalqueryRaised", addtionalqueryRaised);
				jsonObj.put("addtionalqueryRaisedReplied", addtionalqueryRaisedReplied);
				jsonObj.put("cinNo", fpoMainRepost.getCinIdByItemId(itemId));
				jsonObj.put("siteMatch", true);
			} else {
				jsonObj.put("siteMatch", false);
			}
			jsonObj.put("articleNotFound", false);
		}
		response.setContentType("application/json;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonObj.toString());
	}
}
