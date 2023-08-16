package com.demo.fpo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.*;

//import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

//import jdk.internal.org.jline.utils.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.parser.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.apimodel.Fpoqrydeci;
import com.demo.fpo.controller.LoginController;
import com.demo.fpo.model.CUSRSP_SENT;
import com.demo.fpo.model.C_CUSITM;
import com.demo.fpo.model.D_EMP;
import com.demo.fpo.model.DCALLQRY_GEN;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.model.FPO_AMEND;
import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FPO_OOC;
import com.demo.fpo.model.FPO_ORDER;
import com.demo.fpo.model.FPO_PRIORITY;
import com.demo.fpo.model.FPO_RE_CALL;
import com.demo.fpo.model.FPO_SETASIDE;
import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoDefualtQuery;
import com.demo.fpo.model.FpoLoginTrack;
import com.demo.fpo.model.FpoMainAmend;
import com.demo.fpo.model.FpoMvmnt;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryDecision;
import com.demo.fpo.model.FpoQueryDin;
import com.demo.fpo.model.FpoSecondDefaultQuery;
import com.demo.fpo.model.Push_dcall;
import com.demo.fpo.model.REALLOCATION;
import com.demo.fpo.prop.StaticConstants;
import com.demo.fpo.model.FPO_MAIL;
import com.demo.fpo.repos.CUSRSP_SENTRepo;
import com.demo.fpo.repos.D_EMPrepo;
import com.demo.fpo.repos.Dcall_pushRepo;
import com.demo.fpo.repos.FpoDcallQRYRepo;
import com.demo.fpo.repos.FpoLoginTrackRepo;
import com.demo.fpo.repos.FPO_AMENDrepost;
import com.demo.fpo.repos.FPO_EXAM_FINDINGSrepost;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FPO_OOC_FINDINGSrepost;
import com.demo.fpo.repos.FpoOocCancelInfoRepo;
import com.demo.fpo.repos.FPO_ORDERrepost;
import com.demo.fpo.repos.FPO_PRIORITY_REPO;
import com.demo.fpo.repos.FpoAddlQryRepo;
import com.demo.fpo.repos.FpoCurQueRepo;
import com.demo.fpo.repos.FpoMainAmendRepo;
import com.demo.fpo.repos.FpoMvmntRepo;
import com.demo.fpo.repos.FpoQueryDecisionRepo;
import com.demo.fpo.repos.FpoQueryDinRepo;
import com.demo.fpo.repos.FpoQueryRepo;
import com.demo.fpo.repos.FpoSecondDefualtQueryRepo;
import com.demo.fpo.repos.FpoSetasideRepo;
import com.demo.fpo.repos.Fpo_Re_Call_Repo;
import com.demo.fpo.repos.MailSeqRepo;
import com.demo.fpo.repos.Reallocation_repo;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Component
public class FpoService {

	private static final Logger Log = LogManager.getLogger(FpoService.class);

	@Autowired
	FPO_MAINrepost fpomainrepo;

	@Autowired
	FPO_AMENDrepost fpoAmendRpo;

	@Autowired
	FpoOocCancelInfoRepo fpooocancelRepo;

	@Autowired
	FpoDcallQRYRepo DcallQRYREPO;

	@Autowired
	CUSRSP_SENTRepo cusrspRepo;

	@Autowired
	FpoSetasideRepo fposetasiderepo;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FPO_ORDERrepost fpoOrderRepost;

	@Autowired
	FPO_ITEM_DETrepost fpoItemRepost;

	@Autowired
	FpoDeclaredService fpoDeclaredService;

	@Autowired
	FpoQueryService fpoQueryService;

	@Autowired
	FpoMvmntRepo fpoMvmntRepo;

	@Autowired
	FpoOrderService fpoorderService;

	@Autowired
	FpoCurQueRepo fpoCurQueRepo;

	@Autowired
	FpoQueryDinRepo fpoQueryDinRepo;

	@Autowired
	FPO_MAINrepost FPOrepost;

	@Autowired
	FpoAddlQryRepo fpoAddlQryRepo;

	@Autowired
	D_EMPrepo DempFpo;

	@Autowired
	FpoMainAmendRepo fpoMainAmendRepo;

	@Autowired
	FpoCurQueService poCurQueService;

	@Autowired
	CUSRSP_SENTRepo cusSentRepo;

	@Autowired
	FPO_PRIORITY_REPO priorityrepo;

	@Autowired
	FPO_ITEM_DETrepost fpoItemDetRepo;

	@Autowired
	MailSeqRepo MailRepo;

	@Autowired
	FpoSecondDefualtQueryRepo fpoSecondDefualtQueryRepo;

	@Autowired
	FpoDcallQRYRepo fpoDcallQryRepo;

	@Autowired
	Dcall_pushRepo dcall_pushRepo;

	@Autowired
	Reallocation_repo reallocaterepo;

	@Autowired
	Fpo_Re_Call_Repo fporecallrepo;

	@Autowired
	FpoLoginTrackRepo fpoLoginTrackRepo;

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public String cursite = "";

	public List<FPO_AMEND> getFlag(String cinNo) {
		return fpoAmendRpo.getFpoAmend(cinNo);
	}

	public List<FPO_AMEND> getFpoAmendOnCinItemNo(String cinNo, Long itemNo) {
		return fpoAmendRpo.getFpoAmendOnCinItemNo(cinNo, itemNo);
	}

	public Long getMaxFpoItemAmendOnCinItemNo(String cinNo, Long itemNo) {
		return fpoAmendRpo.getMaxFpoItemAmendOnCinItemNo(cinNo, itemNo);
	}

	public FPO_ITEM_DET getAmendByCinItem(String cinNo, Long itemNo) {
		return moveAmendToItem(fpoAmendRpo.getAmendByCinItem(cinNo, itemNo).get(0));
	}

	public void deleteAmendData(String cinNo, String itemNo) {
		fpoAmendRpo.deleteFpoItem("D", cinNo, itemNo);
	}

	@Autowired
	FpoQueryDecisionRepo fpoQueryDecisionRepo;

	@Autowired
	FPO_EXAM_FINDINGSrepost FPO_EXMrepost;

	@Autowired
	FPO_OOC_FINDINGSrepost FPO_OOCrepost;

	private static final Logger log = LogManager.getLogger(FpoService.class);

	public String decisionSubmitService(FpoQueryDecision fpoQueryDecision, HttpSession session,
			HttpServletRequest request, String cat) {
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String offid = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		java.util.Date utilDate = new java.util.Date();

		Long queryNo = fpoQueryRepo.getMaxQueryNoOnCinNo(fpoQueryDecision.getCIN_NO());
		if (fpoQueryDecisionRepo.chkitemiddeci(fpoQueryDecision.getITEM_ID()) == 0) {
			fpoQueryDecision.setQRY_NO(queryNo);
			fpoQueryDecision.setOFF_ID(offid);
			fpoQueryDecision.setROLE(role);
			fpoQueryDecision.setCUS_SITE(cusite);
			fpoQueryDecision.setDECI_CD(fpoQueryDecision.getDECI_CD());
			if (cat.equals("ASSSTOP"))
				fpoQueryDecision.setQRY_TYPE("P4");
			else
				fpoQueryDecision.setQRY_TYPE(fpoQueryDecision.getQRY_TYPE());
			fpoQueryDecisionRepo.save(fpoQueryDecision);
		} else
			fpoQueryDecisionRepo.updqrydeciblk(fpoQueryDecision.getITEM_ID(), fpoQueryDecision.getQRY_TYPE(), cusite,
					role, offid);
		Long orderNo = fpoOrderRepost.getOrderCinNo(fpoQueryDecision.getCIN_NO());
		if (orderNo <= 0) {
			orderNo = null;
		}

		long l = fpoQueryDecision.getDECI_CD();
		String str = Long.toString(l);

		//long t = FPOrepost.getmaxidrspsent();
		
		CUSRSP_SENT cusSent = new CUSRSP_SENT();
		cusSent.setCin_NO(fpoQueryDecision.getCIN_NO());
		cusSent.setCategory(str);
		cusSent.setIn_Date(utilDate);
		//cusSent.setSent_ID(t);
		cusSentRepo.save(cusSent);
		fpoMvmntRepo.updextdt(fpoQueryDecision.getCIN_NO(), utilDate);
		if (cat.equals("ASSSTOP"))
			return "redirect:/ead_list";
		else
			return reDirectToPage(fpoQueryDecision, orderNo, session, request);
	}

	/*
	 * public void fpo_addl_qry_crpdf(String cinNo, String que, HttpSession session,
	 * HttpServletRequest request) { List<FPO_MAIN> fpoMainData =
	 * FPOrepost.getmain(cinNo); // LoginController.recpname =
	 * fpoMainData.get(0).getRECP_NAME();
	 * request.getSession().setAttribute("recpname",
	 * fpoMainData.get(0).getRECP_NAME()); List<FpoAddlQry> AddlQry =
	 * fpoAddlQryRepo.getAllFpoaddlQuery(cinNo);
	 * 
	 * 
	 * boolean pacexist=false; for (int i = 0; i < AddlQry.size()-1 ; i++) {
	 * System.out.println("checking"+AddlQry.get(i).getQRY_ROLE());
	 * if(AddlQry.get(i).getQRY_ROLE().equals("PAC")) {
	 * System.out.println("pac exists"); pacexist=true; break; }
	 * 
	 * }
	 * 
	 * if(pacexist) AddlQry = fpoAddlQryRepo.getAllFpoaddlQueryonlyforPAC(cinNo);
	 * 
	 * 
	 * 
	 * 
	 * // LoginController.tomailid=AddlQry.get(AddlQry.size()-1).QRY_EMAILID;
	 * request.getSession().setAttribute("tomailid", AddlQry.get(AddlQry.size() -
	 * 1).QRY_EMAILID); log.info("cms 1 in pdf");
	 * 
	 * DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyyHHmmss"); String
	 * currentDateTime = dateFormatter.format(new Date()); DateFormat
	 * printdateFormatter = new SimpleDateFormat("dd/MM/yyyy"); String
	 * printcurrentDateTime = printdateFormatter.format(new Date()); String cusite =
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString(); String itemid =
	 * AddlQry.get(0).getITEM_ID(); // LoginController.itemid=itemid;
	 * request.getSession().setAttribute("itemid", itemid); // LoginController.dinno
	 * = din; // request.getSession().setAttribute("dinno", din);
	 * log.info("cms 2 in pdf"); String str; str = "";
	 * 
	 * if (que.equals("N")) str = "AAA"; else if (que.equals("P")) str = "AAF";
	 * log.info("cms 3 in pdf"); String pattern = "MM-dd-yyyy"; SimpleDateFormat
	 * simpleDateFormat = new SimpleDateFormat(pattern); String date =
	 * simpleDateFormat.format(new Date()); String[] dateArray = date.split("-");
	 * String dcallno = (str + (request.getSession().getAttribute("cuSite") == null
	 * ? null : request.getSession().getAttribute("cuSite").toString()) +
	 * dateArray[0] + dateArray[1] + dateArray[2] + gen()); //
	 * LoginController.dcallno = dcallno;
	 * request.getSession().setAttribute("dcallno", dcallno);
	 * log.info("cms 4 in pdf"); try {
	 * 
	 * // Create Document instance. Document document = new Document(); Document
	 * viewdoc = new Document(); log.info("cms 5 in pdf"); // Create OutputStream
	 * instance.
	 * 
	 * String filename = FPOrepost.getdcallqrypath() + itemid + currentDateTime +
	 * ".pdf"; String viewfilename = FPOrepost.getdcallqryviewpath() + itemid +
	 * currentDateTime + ".pdf"; log.info("cms 6 in pdf"); //
	 * LoginController.filename = filename;
	 * request.getSession().setAttribute("filename", filename); OutputStream
	 * outputStream = new FileOutputStream(new File(filename)); OutputStream
	 * viewStream = new FileOutputStream(new File(viewfilename)); String USER =
	 * itemid.substring(8, 13); // String OWNER = LoginController.dinno.substring(0,
	 * 5); log.info("cms 7 in pdf"); // Create PDFWriter instance. PdfWriter writer2
	 * = PdfWriter.getInstance(viewdoc, viewStream); PdfWriter writer =
	 * PdfWriter.getInstance(document, outputStream);
	 * writer.setEncryption(USER.getBytes(), "CBIC-".getBytes(),
	 * PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128 |
	 * PdfWriter.DO_NOT_ENCRYPT_METADATA); // Open the document.125
	 * log.info("cms 8 in pdf"); document.setPageSize(PageSize.A4);
	 * viewdoc.setPageSize(PageSize.A4);
	 * 
	 * document.open(); viewdoc.open(); log.info("cms 9 in pdf");
	 * 
	 * Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f); Font fonts = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 8.0f); Font fontb = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD); Font fontu = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.UNDERLINE); Font fontbi = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLDITALIC, new BaseColor(0, 0,
	 * 255)); Font fontbg = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD,
	 * new BaseColor(128, 128, 128)); Font fontbb = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, new BaseColor(0, 0, 255));
	 * Font fonturl = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLDITALIC,
	 * new BaseColor(28, 63, 227)); Font fonti = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.ITALIC); Font fontli = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.ITALIC);
	 * 
	 * // Paragraph dinno = new Paragraph("CBIC-DIN-" + //
	 * request.getSession().getAttribute("dinno"),fontb); //
	 * dinno.setAlignment(dinno.ALIGN_RIGHT); // document.add(dinno); //
	 * viewdoc.add(dinno); log.info("cms 10 in pdf");
	 * 
	 * Paragraph hd6 = new Paragraph("DCall Letter : " + dcallno, fontb);
	 * hd6.setAlignment(2); document.add(hd6); viewdoc.add(hd6);
	 * log.info("cms 11 in pdf");
	 * 
	 * // Create Image object // Image image = Image.getInstance("images/CBEC.png");
	 * Image image = Image.getInstance(FPOrepost.getimagespath() + "CBEC.png");
	 * 
	 * image.setAlignment(image.MIDDLE); image.scaleToFit(80, 75);
	 * 
	 * // recently added below
	 * 
	 * // String imageFile = "images/CBEC.png"; // ImageData data =
	 * ImageDataFactory.create(imageFile);
	 * 
	 * // Image image = new Image(data); // recenty added above
	 * 
	 * log.info("cms 12 in pdf"); // Add content to the document using Image object.
	 * document.add(image); viewdoc.add(image); String addr = ""; String siteemail =
	 * ""; String workhours = ""; log.info("cms 13 in pdf"); Paragraph hd = new
	 * Paragraph("INDIAN CUSTOMS", font); hd.setAlignment(1); document.add(hd);
	 * viewdoc.add(hd);
	 * 
	 * Paragraph hd1 = new Paragraph("POSTAL APPRAISING DEPARTMENT", font);
	 * hd1.setAlignment(1); document.add(hd1); viewdoc.add(hd1);
	 * 
	 * log.info("cms 14 in pdf");
	 * 
	 * Paragraph hd2 = new Paragraph("FOREIGN POST OFFICE", font);
	 * hd2.setAlignment(1); document.add(hd2); viewdoc.add(hd2);
	 * 
	 * Paragraph hd10 = new
	 * Paragraph("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ",
	 * fonts); hd10.setAlignment(1); document.add(hd10); viewdoc.add(hd10);
	 * log.info("cms 15 in pdf");
	 * 
	 * Paragraph hd3 = new Paragraph("Address : " +
	 * fpoQueryRepo.getfpositeaddr(cusite).toString(), font); hd3.setAlignment(1);
	 * document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 16 in pdf");
	 * 
	 * 
	 * Paragraph hd4=new
	 * Paragraph("Email : "+fpoQueryRepo.getfpositemail(cusite),font);
	 * hd4.setAlignment(1); document.add(hd4);
	 * 
	 * 
	 * Paragraph hd5 = new Paragraph("Contact No. : " +
	 * fpoQueryRepo.getfpositeph(cusite), font); hd5.setAlignment(1);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 17 in pdf");
	 * 
	 * hd5 = new Paragraph("Visiting Hours : " + fpoQueryRepo.getfpositewh(cusite),
	 * font); hd5.setAlignment(1); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * 
	 * Paragraph hd6=new Paragraph("Document Call Letter : " +
	 * DINList.get(0).getUniqueNo(),fontb); hd6.setAlignment(1); document.add(hd6);
	 * 
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 18 in pdf");
	 * 
	 * PdfPTable table = new PdfPTable(3); table.setWidthPercentage(100);
	 * table.setHorizontalAlignment(1);
	 * 
	 * PdfPCell cell = new PdfPCell(); cell.setPhrase(new Phrase("To", font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(2); cell.setBorder(0);
	 * table.addCell(cell);
	 * 
	 * log.info("cms 19 in pdf");
	 * 
	 * cell.setPhrase(new Phrase("ON INDIA GOVERNMENT SERVICE", font));
	 * cell.setHorizontalAlignment(2); cell.setColspan(2); cell.setBorder(0);
	 * table.addCell(cell);
	 * 
	 * log.info("cms 20 in pdf");
	 * 
	 * cell.setPhrase(new Phrase("    " + fpoMainData.get(0).getRECP_NAME(), font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(2); cell.setBorder(0);
	 * table.addCell(cell);
	 * 
	 * log.info("cms 21 in pdf");
	 * 
	 * cell.setPhrase(new Phrase("By SPEED POST", font));
	 * cell.setHorizontalAlignment(2); cell.setColspan(2); cell.setBorder(0);
	 * table.addCell(cell); cell.setPhrase(new Phrase("    " +
	 * fpoMainData.get(0).getRECP_ADD1(), font)); cell.setHorizontalAlignment(0);
	 * cell.setColspan(3); cell.setBorder(0); table.addCell(cell);
	 * 
	 * log.info("cms 22 in pdf");
	 * 
	 * if (fpoMainData.get(0).getRECP_ADD2() != null) { cell.setPhrase(new
	 * Phrase("    " + fpoMainData.get(0).getRECP_ADD2(), font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(3); cell.setBorder(0);
	 * table.addCell(cell); }
	 * 
	 * log.info("cms 23 in pdf");
	 * 
	 * String padcity =
	 * FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ?
	 * null : request.getSession().getAttribute("cuSite").toString());
	 * 
	 * if (padcity != null) { cell.setPhrase(new Phrase("    " +
	 * fpoMainData.get(0).getRECP_CITY(), font)); cell.setHorizontalAlignment(0);
	 * cell.setColspan(3); cell.setBorder(0); table.addCell(cell); }
	 * 
	 * log.info("cms 24 in pdf");
	 * 
	 * if (fpoMainData.get(0).getRECP_ZIP() != null) { cell.setPhrase(new
	 * Phrase("    " + fpoMainData.get(0).getRECP_ZIP(), font)); cell.setColspan(3);
	 * cell.setHorizontalAlignment(0); cell.setBorder(0); table.addCell(cell); }
	 * 
	 * log.info("cms 25 in pdf");
	 * 
	 * if (fpoMainData.get(0).getRECP_PHONE() != null) { cell.setPhrase(new
	 * Phrase("    Ph:" + fpoMainData.get(0).getRECP_PHONE(), font));
	 * cell.setColspan(3); cell.setHorizontalAlignment(0); cell.setBorder(0);
	 * table.addCell(cell); }
	 * 
	 * log.info("cms 26 in pdf"); document.add(table); viewdoc.add(table);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 27 in pdf");
	 * 
	 * document.add(new Paragraph("Sir/Madam,", font)); viewdoc.add(new
	 * Paragraph("Sir/Madam,", font));
	 * 
	 * log.info("cms 28 in pdf");
	 * 
	 * hd3 = new Paragraph( "     Sub: IMPORT - Foreign Post Article ID " +
	 * fpoMainData.get(0).getITEM_ID() + " - reg.", font); hd3.setAlignment(3);
	 * document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 29 in pdf");
	 * 
	 * hd3 = new Paragraph("     Ref: First D-CALL Letter No. " +
	 * fpoQueryRepo.getdcallno(cinNo, request.getSession().getAttribute("cuSite") ==
	 * null ? null : request.getSession().getAttribute("cuSite").toString()) +
	 * " and your reply dated " + fpoQueryRepo.getrplydt(cinNo,
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString()), font);
	 * hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 30 in pdf");
	 * 
	 * hd3 = new Paragraph("     Additional D-Call Letter No. : " + dcallno, font);
	 * hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 31 in pdf");
	 * 
	 * hd3 = new Paragraph("***", font); hd3.setAlignment(1); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * log.info("cms 32 in pdf");
	 * 
	 * hd3 = new Paragraph(
	 * "1.	The above mentioned Foreign Post Parcel has arrived at the FPO and was awaiting customs clearance for want of the reply on the D-Call Letter."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 33 in pdf");
	 * 
	 * hd3 = new
	 * Paragraph("2.	The reply and Documents furnished by you have been examined and it is seen that"
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * log.info("cms 34 in pdf");
	 * 
	 * List<FpoSecondDefaultQuery> secdefqry;
	 * 
	 * char ch = 'a'; int asciiValue = ch; System.out.println(asciiValue); // A=65
	 * for (int i = 91; i < 128; i++) { // Use Typecasting to get ASCII value char
	 * ch2 = (char) i; System.out.println(ch2); } int once5 = 0; int once6 = 0;
	 * String schr = ""; int prn = 97; char ch2 = (char) prn;
	 * 
	 * log.info("cms 35 in pdf"); for (int i = 0; i < AddlQry.size() - 1; i++) { ch2
	 * = (char) prn; if (AddlQry.get(i).getQRY_DEF_SLNO().equals("1")) { hd3 = new
	 * Paragraph("     " + ch2 + ")" + AddlQry.get(0).getQRY_DESC(), font);
	 * hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3); }
	 * 
	 * else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("2")) { secdefqry =
	 * fpoSecondDefualtQueryRepo.getsecdefQry("2"); hd3 = new Paragraph("     " +
	 * ch2 + ")" + secdefqry.get(0).getDESCRIPTION() + " " +
	 * AddlQry.get(i).getQRY_DESC(), font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3); }
	 * 
	 * else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("3")) { secdefqry =
	 * fpoSecondDefualtQueryRepo.getsecdefQry("3"); hd3 = new Paragraph("     " +
	 * ch2 + ")" + secdefqry.get(0).getDESCRIPTION(), font); hd3.setAlignment(3);
	 * document.add(hd3); viewdoc.add(hd3); } else if
	 * (AddlQry.get(i).getQRY_DEF_SLNO().equals("4")) { secdefqry =
	 * fpoSecondDefualtQueryRepo.getsecdefQry("4"); hd3 = new Paragraph("     " +
	 * ch2 + ")" + secdefqry.get(0).getDESCRIPTION() + " " +
	 * AddlQry.get(i).getQRY_DESC(), font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3); } else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("5")) {
	 * secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("5"); if (once5 == 0) {
	 * hd3 = new Paragraph("     " + ch2 + ")" + secdefqry.get(0).getDESCRIPTION(),
	 * font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3); } if (once5
	 * == 0) schr = "          i)"; else schr = "         ii)"; hd3 = new
	 * Paragraph(schr + AddlQry.get(i).getQRY_DESC(), font); hd3.setAlignment(3);
	 * document.add(hd3); viewdoc.add(hd3); once5 = once5 + 1; } else if
	 * (AddlQry.get(i).getQRY_DEF_SLNO().equals("6")) { secdefqry =
	 * fpoSecondDefualtQueryRepo.getsecdefQry("6"); if (once6 == 0) { hd3 = new
	 * Paragraph("     " + ch2 + ")" + secdefqry.get(0).getDESCRIPTION(), font);
	 * hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3); } if (once6 == 0)
	 * schr = "          i)"; else schr = "         ii)"; hd3 = new Paragraph(schr +
	 * AddlQry.get(i).getQRY_DESC(), font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3); once6 = once6 + 1; } else if
	 * (AddlQry.get(i).getQRY_DEF_SLNO().equals("7")) { secdefqry =
	 * fpoSecondDefualtQueryRepo.getsecdefQry("7"); hd3 = new Paragraph("     " +
	 * ch2 + ")" + secdefqry.get(0).getDESCRIPTION() + " " +
	 * AddlQry.get(i).getQRY_DESC(), font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3); } if ((once5 == 0 || once6 == 0) && once5 != 1) prn = prn +
	 * 1; }
	 * 
	 * 
	 * hd5=new Paragraph("(P.T.O)",font); hd5.setAlignment(2); document.add(hd5);
	 * viewdoc.add(hd5);
	 * 
	 * document.newPage(); viewdoc.newPage();
	 * 
	 * 
	 * // PdfPCell itcellgen = new PdfPCell(); // PdfPTable ittablegen = new
	 * PdfPTable(1); // ittablegen.setWidthPercentage(100); // //
	 * itcellgen.setPhrase(new Phrase("Customs Query",fontb)); //
	 * itcellgen.setBackgroundColor(new BaseColor(128,128,128)); //
	 * itcellgen.setHorizontalAlignment(1); // ittablegen.addCell(itcellgen); // //
	 * itcellgen.setPhrase(new
	 * Phrase("                      "+AddlQry.get(AddlQry.size()-1).getQRY_DESC(),
	 * font)); // itcellgen.setBackgroundColor(new BaseColor(255,255,255)); //
	 * itcellgen.setHorizontalAlignment(0); // ittablegen.addCell(itcellgen); // //
	 * document.add(ittablegen); // viewdoc.add(ittablegen); // // hd3=new
	 * Paragraph("       ",font); // hd3.setAlignment(3); // document.add(hd3); //
	 * viewdoc.add(hd3);
	 * 
	 * log.info("cms 36 in pdf"); hd3 = new Paragraph(
	 * "3.   The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the parcel. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the parcel on its arrival, will be liable to be assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * hd3 = new Paragraph(
	 * "4.    It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the date of  this Document Call letter), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the recipient/addressee consignee."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * hd3 = new Paragraph(
	 * "5.    This letter does not confer any right on the recipient/addressee for clearance of the parcel from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the parcel."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * hd3 = new Paragraph(
	 * "6.     Please quote the above Article ID (parcel number) in all your correspondence, if any to be made."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * hd5 = new Paragraph(
	 * " 7.    Please reply by post to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only."
	 * , fontbb); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 37 in pdf");
	 * 
	 * Random rnd = new Random(); int rndno = rnd.nextInt(9999999);
	 * System.out.println(rndno);
	 * 
	 * System.out.println("dcallno is" +
	 * request.getSession().getAttribute("dcallno")); String dcall_part =
	 * request.getSession().getAttribute("dcallno") == null ? null :
	 * request.getSession().getAttribute("dcallno").toString().substring(5, 12);
	 * 
	 * log.info("cms 38 in pdf");
	 * 
	 * // String url="https://www.postalimport.gov.in/"+dcall_part+"/"+rndno; String
	 * url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
	 * String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part +
	 * "/" + rndno; // LoginController.url=url;
	 * request.getSession().setAttribute("url", url); //
	 * LoginController.smsurl=smsurl; request.getSession().setAttribute("smsurl",
	 * smsurl);
	 * 
	 * log.info("cms 39 in pdf");
	 * 
	 * hd5 = new Paragraph(url, fontbi); hd5.setAlignment(3); document.add(hd5);
	 * viewdoc.add(hd5);
	 * 
	 * log.info("cms 40 in pdf");
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * log.info("cms 41 in pdf");
	 * 
	 * hd5 = new Paragraph("Yours sincerely, ", font); hd5.setAlignment(2);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * String cd = FPOrepost.getoffname( request.getSession().getAttribute("offId")
	 * == null ? null : request.getSession().getAttribute("offId").toString(),
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString(),
	 * request.getSession().getAttribute("role") == null ? null :
	 * request.getSession().getAttribute("role").toString());
	 * 
	 * log.info("cms 42 in pdf");
	 * 
	 * hd5 = new Paragraph(cd, fontb); hd5.setAlignment(2); document.add(hd5);
	 * viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("AO OF CUSTOMS", font); hd5.setAlignment(2);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 43 in pdf"); hd5 = new Paragraph("PAD, " + padcity, fontb);
	 * hd5.setAlignment(2); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("DATE : " + printcurrentDateTime, font);
	 * hd5.setAlignment(2); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * log.info("cms 44 in pdf");
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph(
	 * "     Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  "
	 * , fontli); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * log.info("cms 45 in pdf");
	 * 
	 * 
	 * hd5=new Paragraph("        ",font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5=new Paragraph("        ",font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("    This document is electronically auto-generated and does not require signature."
	 * ,fontli); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * 
	 * document.close(); viewdoc.close(); outputStream.close(); viewStream.close();
	 * 
	 * System.out.println("Pdf created successfully."); log.info("cms 46 in pdf");
	 * 
	 * String to = fpoMainData.get(0).getRECP_EMAILID(); // LoginController.to=to;
	 * request.getSession().setAttribute("to", to); // LoginController.tomailid =
	 * AddlQry.get(AddlQry.size()-1).QRY_EMAILID;
	 * request.getSession().setAttribute("tomailid", AddlQry.get(AddlQry.size() -
	 * 1).QRY_EMAILID);
	 * 
	 * String toEnteredMobileNumber = fpoQueryRepo.getMobileNumberaddlqry(cinNo);
	 * String toEnteredMailid = fpoQueryRepo.getemailaddlqry(cinNo); if
	 * (toEnteredMobileNumber == null) fpoQueryRepo.getMobileNumber(cinNo,
	 * fpoQueryRepo.getMaxQueryNo()); if (toEnteredMailid == null)
	 * fpoQueryRepo.getemail(cinNo, fpoQueryRepo.getMaxQueryNo()); //
	 * LoginController.toMobileNumber=fpoMainData.get(0).getRECP_PHONE(); //
	 * LoginController.toEnteredMobileNumber=AddlQry.get(AddlQry.size()-1).
	 * QRY_MOBILENO; request.getSession().setAttribute("toEnteredMobileNumber",
	 * toEnteredMobileNumber); request.getSession().setAttribute("toEnteredMailid",
	 * toEnteredMailid); request.getSession().setAttribute("toMobileNumber",
	 * fpoMainData.get(0).getRECP_PHONE());
	 * request.getSession().setAttribute("toMailiD",
	 * fpoMainData.get(0).getRECP_EMAILID()); log.info("cms 47 in pdf");
	 * DCALLQRY_GEN dcallqry = new DCALLQRY_GEN();
	 * dcallqry.setItem_ID(request.getSession().getAttribute("itemid") == null ?
	 * null : request.getSession().getAttribute("itemid").toString());
	 * dcallqry.setDcallno(request.getSession().getAttribute("dcallno") == null ?
	 * null : request.getSession().getAttribute("dcallno").toString());
	 * java.util.Date curdt = new java.util.Date(); dcallqry.setGen_dt(curdt);
	 * dcallqry.setGenurl(request.getSession().getAttribute("url") == null ? null :
	 * request.getSession().getAttribute("url").toString());
	 * dcallqry.setCussite(request.getSession().getAttribute("cuSite") == null ?
	 * null : request.getSession().getAttribute("cuSite").toString());
	 * dcallqry.setRecp_name(request.getSession().getAttribute("recpname") == null ?
	 * null : request.getSession().getAttribute("recpname").toString());
	 * dcallqry.setGen_filename(request.getSession().getAttribute("filename") ==
	 * null ? null : request.getSession().getAttribute("filename").toString());
	 * dcallqry.setCinno(cinNo);
	 * dcallqry.setMobile_1(request.getSession().getAttribute("toMobileNumber") ==
	 * null ? null :
	 * request.getSession().getAttribute("toMobileNumber").toString()); //
	 * dcallqry.setMobile_2(AddlQry.get(AddlQry.size()-1).QRY_MOBILENO);
	 * dcallqry.setMobile_2(toEnteredMobileNumber); DcallQRYREPO.save(dcallqry);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	 * public void fpo_qry_crpdf(String cinNo, String que, String din, String
	 * others, HttpSession session, HttpServletRequest request) {
	 * log.info("cms 1 in fpo_qry_crpdf"); List<FPO_MAIN> fpoMainData =
	 * FPOrepost.getmain(cinNo); String getRemarks = fpoQueryRepo.getRemarks(cinNo,
	 * fpoQueryRepo.getMaxQueryNo()); String tomailid = fpoQueryRepo.getemail(cinNo,
	 * fpoQueryRepo.getMaxQueryNo()); String DefualtQuery =
	 * fpoQueryRepo.getDefualtQuery(cinNo, fpoQueryRepo.getMaxQueryNo()); String
	 * DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());
	 * List<String> defualtQueryList = getSpecifiedDefualtQuery(DefualtQuery,
	 * DocName); log.info("cms 2 in fpo_qry_crpdf"); // LoginController.tomailid =
	 * tomailid; request.getSession().setAttribute("tomailid", tomailid); //
	 * LoginController.recpname = fpoMainData.get(0).getRECP_NAME();
	 * request.getSession().setAttribute("recpname",
	 * fpoMainData.get(0).getRECP_NAME());
	 * 
	 * log.info("cms 3 in fpo_qry_crpdf"); List<FpoQuery> getAllFpoQuery =
	 * fpoQueryService.getAllFpoQuery(cinNo); List<FpoQueryDin> DINList =
	 * fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
	 * log.info("cms 4 in fpo_qry_crpdf"); DateFormat dateFormatter = new
	 * SimpleDateFormat("ddMMyyyyHHmmss"); String currentDateTime =
	 * dateFormatter.format(new Date()); DateFormat printdateFormatter = new
	 * SimpleDateFormat("dd/MM/yyyy"); String printcurrentDateTime =
	 * printdateFormatter.format(new Date()); String cusite =
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString(); String itemid =
	 * DINList.get(0).getItemid(); // LoginController.itemid=itemid;
	 * request.getSession().setAttribute("itemid", itemid); // LoginController.dinno
	 * = din; request.getSession().setAttribute("dinno", din);
	 * log.info("cms 5 in fpo_qry_crpdf"); try {
	 * 
	 * // Create Document instance. Document document = new Document(); Document
	 * viewdoc = new Document(); log.info("cms 6 in fpo_qry_crpdf"); // Create
	 * OutputStream instance.
	 * 
	 * String filename = FPOrepost.getdcallqrypath() + itemid + currentDateTime +
	 * ".pdf"; String viewfilename = FPOrepost.getdcallqryviewpath() + itemid +
	 * currentDateTime + ".pdf"; String str; // LoginController.filename = filename;
	 * request.getSession().setAttribute("filename", filename); OutputStream
	 * outputStream = new FileOutputStream(new File(filename)); OutputStream
	 * viewStream = new FileOutputStream(new File(viewfilename)); String USER =
	 * itemid.substring(8, 13); // String OWNER = LoginController.dinno.substring(0,
	 * 5); log.info("cms 7 in fpo_qry_crpdf"); // Create PDFWriter instance.
	 * PdfWriter writer2 = PdfWriter.getInstance(viewdoc, viewStream); PdfWriter
	 * writer = PdfWriter.getInstance(document, outputStream);
	 * writer.setEncryption(USER.getBytes(), "CBEC-".getBytes(),
	 * PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128 |
	 * PdfWriter.DO_NOT_ENCRYPT_METADATA); // Open the document.125
	 * log.info("cms 8 in fpo_qry_crpdf"); document.setPageSize(PageSize.A4);
	 * viewdoc.setPageSize(PageSize.A4);
	 * 
	 * document.open(); viewdoc.open(); log.info("cms 9 in fpo_qry_crpdf"); Font
	 * font = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f); Font fonts = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 8.0f); Font fontb = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD); Font fontu = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.UNDERLINE); Font fontbi = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLDITALIC, new BaseColor(0, 0,
	 * 255)); Font fontbg = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD,
	 * new BaseColor(128, 128, 128)); Font fontbb = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, new BaseColor(0, 0, 255));
	 * Font fonturl = new Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.BOLDITALIC,
	 * new BaseColor(28, 63, 227)); Font fonti = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 9.0f, Font.ITALIC); Font fontli = new
	 * Font(Font.FontFamily.TIMES_ROMAN, 7.0f, Font.ITALIC);
	 * log.info("cms 10 in fpo_qry_crpdf");
	 * 
	 * Paragraph dinno = new Paragraph("CBIC-DIN-" +
	 * request.getSession().getAttribute("dinno"),fontb);
	 * dinno.setAlignment(dinno.ALIGN_RIGHT); document.add(dinno);
	 * viewdoc.add(dinno);
	 * 
	 * str = ""; log.info("cms 11 in fpo_qry_crpdf"); if (que.equals("E")) str =
	 * "EAD"; else if (que.equals("P")) str = "AAF";
	 * log.info("cms 12 in fpo_qry_crpdf"); log.info("str is " + str); String
	 * dcallno = str + DINList.get(0).getUniqueNo(); log.info("cms 12a in pdf");
	 * log.info("dallno is " + dcallno); // LoginController.dcallno =
	 * str+DINList.get(0).getUniqueNo();
	 * request.getSession().setAttribute("dcallno", str +
	 * DINList.get(0).getUniqueNo());
	 * 
	 * Paragraph hd6 = new Paragraph("DCall Letter : " + dcallno, fontb);
	 * hd6.setAlignment(2); document.add(hd6); viewdoc.add(hd6);
	 * log.info("cms 12b in pdf"); // Create Image object // Image image =
	 * Image.getInstance("/images/CBEC.png"); Image image =
	 * Image.getInstance(FPOrepost.getimagespath() + "CBEC.png");
	 * log.info("cms 13 in fpo_qry_crpdf"); image.setAlignment(image.MIDDLE);
	 * image.scaleToFit(80, 75);
	 * 
	 * // Add content to the document using Image object. document.add(image);
	 * viewdoc.add(image); String addr = ""; String siteemail = ""; String workhours
	 * = ""; log.info("cms 14 in fpo_qry_crpdf"); Paragraph hd = new
	 * Paragraph("INDIAN CUSTOMS", font); hd.setAlignment(1); document.add(hd);
	 * viewdoc.add(hd); log.info("cms 15 in fpo_qry_crpdf"); Paragraph hd1 = new
	 * Paragraph("POSTAL APPRAISING DEPARTMENT", font); hd1.setAlignment(1);
	 * document.add(hd1); viewdoc.add(hd1); log.info("cms 16 in fpo_qry_crpdf");
	 * Paragraph hd2 = new Paragraph("FOREIGN POST OFFICE", font);
	 * hd2.setAlignment(1); document.add(hd2); viewdoc.add(hd2);
	 * log.info("cms 17 in fpo_qry_crpdf");
	 * 
	 * Paragraph hd10 = new
	 * Paragraph("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ",
	 * fonts); hd10.setAlignment(1); document.add(hd10); viewdoc.add(hd10);
	 * log.info("cms 15 in pdf");
	 * 
	 * Paragraph hd3 = new Paragraph("Address : " +
	 * fpoQueryRepo.getfpositeaddr(cusite).toString(), font); hd3.setAlignment(1);
	 * document.add(hd3); viewdoc.add(hd3); log.info("cms 18 in fpo_qry_crpdf");
	 * 
	 * Paragraph hd4=new
	 * Paragraph("Email : "+fpoQueryRepo.getfpositemail(cusite),font);
	 * hd4.setAlignment(1); document.add(hd4);
	 * 
	 * log.info("cms 19 in fpo_qry_crpdf"); Paragraph hd5 = new
	 * Paragraph("Contact No. : " + fpoQueryRepo.getfpositeph(cusite), font);
	 * hd5.setAlignment(1); document.add(hd5); viewdoc.add(hd5);
	 * log.info("cms 20 in fpo_qry_crpdf"); hd5 = new Paragraph("Visiting Hours : "
	 * + fpoQueryRepo.getfpositewh(cusite), font); hd5.setAlignment(1);
	 * document.add(hd5); viewdoc.add(hd5); log.info("cms 21 in fpo_qry_crpdf");
	 * 
	 * Paragraph hd6=new Paragraph("Document Call Letter : " +
	 * DINList.get(0).getUniqueNo(),fontb); hd6.setAlignment(1); document.add(hd6);
	 * 
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * PdfPTable table = new PdfPTable(3); table.setWidthPercentage(100);
	 * table.setHorizontalAlignment(1); log.info("cms 22 in fpo_qry_crpdf");
	 * PdfPCell cell = new PdfPCell(); cell.setPhrase(new Phrase("To", font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(2); cell.setBorder(0);
	 * table.addCell(cell); log.info("cms 23 in fpo_qry_crpdf"); cell.setPhrase(new
	 * Phrase("ON INDIA GOVERNMENT SERVICE", font)); cell.setHorizontalAlignment(2);
	 * cell.setColspan(2); cell.setBorder(0); table.addCell(cell);
	 * log.info("cms 24 in fpo_qry_crpdf"); cell.setPhrase(new Phrase("    " +
	 * fpoMainData.get(0).getRECP_NAME(), font)); cell.setHorizontalAlignment(0);
	 * cell.setColspan(2); cell.setBorder(0); table.addCell(cell);
	 * log.info("cms 25 in fpo_qry_crpdf"); cell.setPhrase(new
	 * Phrase("By SPEED POST", font)); cell.setHorizontalAlignment(2);
	 * cell.setColspan(2); cell.setBorder(0); table.addCell(cell);
	 * cell.setPhrase(new Phrase("    " + fpoMainData.get(0).getRECP_ADD1(), font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(3); cell.setBorder(0);
	 * table.addCell(cell); log.info("cms 26 in fpo_qry_crpdf"); if
	 * (fpoMainData.get(0).getRECP_ADD2() != null) { cell.setPhrase(new
	 * Phrase("    " + fpoMainData.get(0).getRECP_ADD2(), font));
	 * cell.setHorizontalAlignment(0); cell.setColspan(3); cell.setBorder(0);
	 * table.addCell(cell); } log.info("cms 27 in fpo_qry_crpdf"); String padcity =
	 * FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ?
	 * null : request.getSession().getAttribute("cuSite").toString());
	 * 
	 * if (padcity != null) { cell.setPhrase(new Phrase("    " +
	 * fpoMainData.get(0).getRECP_CITY(), font)); cell.setHorizontalAlignment(0);
	 * cell.setColspan(3); cell.setBorder(0); table.addCell(cell); }
	 * 
	 * if (fpoMainData.get(0).getRECP_ZIP() != null) { cell.setPhrase(new
	 * Phrase("    " + fpoMainData.get(0).getRECP_ZIP(), font)); cell.setColspan(3);
	 * cell.setHorizontalAlignment(0); cell.setBorder(0); table.addCell(cell); }
	 * log.info("cms 28 in fpo_qry_crpdf"); if (fpoMainData.get(0).getRECP_PHONE()
	 * != null) { cell.setPhrase(new Phrase("    Ph:" +
	 * fpoMainData.get(0).getRECP_PHONE(), font)); cell.setColspan(3);
	 * cell.setHorizontalAlignment(0); cell.setBorder(0); table.addCell(cell); }
	 * log.info("cms 29 in fpo_qry_crpdf"); document.add(table); viewdoc.add(table);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * log.info("cms 30 in fpo_qry_crpdf"); document.add(new Paragraph("Sir/Madam,",
	 * font)); viewdoc.add(new Paragraph("Sir/Madam,", font));
	 * 
	 * hd3 = new Paragraph("Sub: IMPORT - Foreign Post Article ID " +
	 * fpoMainData.get(0).getITEM_ID() + "-reg.", font); hd3.setAlignment(1);
	 * document.add(hd3); viewdoc.add(hd3); log.info("cms 31 in fpo_qry_crpdf"); hd3
	 * = new Paragraph("*****", font); hd3.setAlignment(1); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * log.info("cms 32 in fpo_qry_crpdf"); if (que == "E" || que == "N") str =
	 * "1. The above mentioned Import Article addressed to you is expected to arrive at the "
	 * + padcity +
	 * " FPO and is being taken upfor assessment by the Indian Customs."; else if
	 * (que == "P") str =
	 * "1. The above mentioned Import Article addressed to you is received at the "
	 * + padcity + " FPO and is being examined by the Indian Customs.";
	 * 
	 * hd3 = new Paragraph(str, font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * hd3 = new Paragraph(
	 * "2. In this regard, it is requested to provide clarifications on the queries raised by the department and provide the documents called for, as mentioned in the following tables."
	 * , font); hd3.setAlignment(3); document.add(hd3); viewdoc.add(hd3);
	 * 
	 * if (!(DINList.get(0).getRemarks() == null)) {
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * PdfPCell itcellgen = new PdfPCell(); PdfPTable ittablegen = new PdfPTable(1);
	 * ittablegen.setWidthPercentage(100); log.info("cms 32 in fpo_qry_crpdf");
	 * itcellgen.setPhrase(new Phrase("Customs General Query", fontb));
	 * itcellgen.setBackgroundColor(new BaseColor(128, 128, 128));
	 * itcellgen.setHorizontalAlignment(1); ittablegen.addCell(itcellgen);
	 * 
	 * itcellgen.setPhrase(new Phrase("                      " +
	 * DINList.get(0).getRemarks(), font)); itcellgen.setBackgroundColor(new
	 * BaseColor(255, 255, 255)); itcellgen.setHorizontalAlignment(0);
	 * ittablegen.addCell(itcellgen);
	 * 
	 * document.add(ittablegen); viewdoc.add(ittablegen); }
	 * 
	 * System.out.println("size is " + getAllFpoQuery.size());
	 * 
	 * System.out.println("Itemno is " + getAllFpoQuery.get(0).getITEM_NO());
	 * 
	 * if (getAllFpoQuery.get(0).getQRY() != null) {
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * PdfPCell itcell = new PdfPCell(); PdfPTable ittable3 = new PdfPTable(3);
	 * ittable3.setWidthPercentage(100); log.info("cms 33 in fpo_qry_crpdf");
	 * PdfPCell itcellthree = new PdfPCell();
	 * 
	 * itcellthree.setPhrase(new Phrase("Itemwise Query", fontb));
	 * itcellthree.setBackgroundColor(new BaseColor(128, 128, 128));
	 * itcellthree.setColspan(3); itcellthree.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcellthree);
	 * 
	 * itcell.setPhrase(new Phrase("Item No.", fontb));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell);
	 * 
	 * itcell.setPhrase(new Phrase("Item Description", fontb));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell);
	 * 
	 * itcell.setPhrase(new Phrase("Query", fontb));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell); log.info("cms 34 in fpo_qry_crpdf"); for (int i =
	 * 0; i < getAllFpoQuery.size(); i++) {
	 * 
	 * if (getAllFpoQuery.get(i).getQRY() != null) { itcell.setPhrase(new
	 * Phrase(getAllFpoQuery.get(i).getITEM_NO().toString(), font));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell);
	 * 
	 * itcell.setPhrase(new Phrase(getAllFpoQuery.get(i).getItemDesc(), font));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell);
	 * 
	 * itcell.setPhrase(new Phrase(getAllFpoQuery.get(i).getQRY(), font));
	 * itcell.setHorizontalAlignment(1);
	 * 
	 * ittable3.addCell(itcell); } } log.info("cms 35 in fpo_qry_crpdf");
	 * document.add(ittable3); viewdoc.add(ittable3); }
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * PdfPTable qrytable = new PdfPTable(2); qrytable.setWidthPercentage(100);
	 * qrytable.setTotalWidth(500);
	 * 
	 * // Set Each Column Width - Make Sure Array is the same number specified in //
	 * constructor qrytable.setWidths(new int[] { 50, 450 });
	 * 
	 * PdfPCell qrycell = new PdfPCell();
	 * 
	 * qrycell.setPhrase(new Phrase("SlNo.", fontb)); qrycell.setBackgroundColor(new
	 * BaseColor(128, 128, 128)); qrycell.setHorizontalAlignment(1);
	 * qrytable.addCell(qrycell); log.info("cms 36 in fpo_qry_crpdf");
	 * qrycell.setPhrase(new Phrase("Document required from recipient", fontb));
	 * qrycell.setHorizontalAlignment(1); qrytable.addCell(qrycell);
	 * 
	 * PdfPCell defqrycell = new PdfPCell();
	 * 
	 * int k = 1; for (int i = 0; i < defualtQueryList.size(); i++) {
	 * System.out.println(" i is " + i + 1 + " and query is " +
	 * defualtQueryList.get(i));
	 * 
	 * defqrycell.setPhrase(new Phrase("   " + k, font));
	 * defqrycell.setHorizontalAlignment(1); qrytable.addCell(defqrycell);
	 * 
	 * defqrycell.setPhrase(new Phrase(defualtQueryList.get(i).toString(), font));
	 * defqrycell.setHorizontalAlignment(1); qrytable.addCell(defqrycell); k++; }
	 * log.info("cms 37 in fpo_qry_crpdf"); document.add(qrytable);
	 * viewdoc.add(qrytable);
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * 
	 * hd5=new Paragraph("General Remarks : " + DINList.get(0).getRemarks(),fontb);
	 * hd5.setAlignment(0); document.add(hd5);
	 * 
	 * 
	 * 
	 * hd5=new Paragraph("(P.T.O)",font); hd5.setAlignment(2); document.add(hd5);
	 * viewdoc.add(hd5);
	 * 
	 * document.newPage(); viewdoc.newPage();
	 * 
	 * 
	 * hd5 = new Paragraph(
	 * " 3. The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the Article. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the Article on its arrival, will be liable to be  assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you."
	 * , font); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * log.info("cms 38 in fpo_qry_crpdf"); hd5 = new Paragraph(
	 * " 4. It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the Document Call letter date), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the addressee consignee. "
	 * , font); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph(
	 * " 5. This letter does not confer any right on the addressee for clearance of the Article from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the Article. "
	 * , font); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph(
	 * " 6. The Department reserves its rights to initiate action as deemed fit against the consignee/recipient or the person who replies, in cases where any suppression of fact,wrong claims, submission of fake document or misinformation etc is noticed."
	 * , font); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph(
	 * " 7. Please reply by post to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only."
	 * , fontbb); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * Random rnd = new Random(); int rndno = rnd.nextInt(9999999);
	 * System.out.println(rndno); log.info("cms 39 in fpo_qry_crpdf");
	 * System.out.println("dcallno is" +
	 * request.getSession().getAttribute("dcallno")); String dcall_part =
	 * request.getSession().getAttribute("dcallno") == null ? null :
	 * request.getSession().getAttribute("dcallno").toString().substring(5, 12);
	 * 
	 * // String url="https://www.postalimport.gov.in/"+dcall_part+"/"+rndno; String
	 * url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
	 * String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part +
	 * "/" + rndno; // LoginController.url=url;
	 * request.getSession().setAttribute("url", url); //
	 * LoginController.smsurl=smsurl; request.getSession().setAttribute("smsurl",
	 * smsurl);
	 * 
	 * hd5 = new Paragraph(url, fontbi); hd5.setAlignment(3); document.add(hd5);
	 * viewdoc.add(hd5);
	 * 
	 * 
	 * hd5=new
	 * Paragraph(" 7. Instructions for replying over email may be referred below."
	 * ,font); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph(" 8. Please quote the above Article ID in all your correspondence. "
	 * ,font); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * 
	 * hd3 = new Paragraph("       ", font); hd3.setAlignment(3); document.add(hd3);
	 * viewdoc.add(hd3);
	 * 
	 * hd5 = new Paragraph("Yours sincerely, ", font); hd5.setAlignment(2);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * String cd = FPOrepost.getoffname( request.getSession().getAttribute("offId")
	 * == null ? null : request.getSession().getAttribute("offId").toString(),
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString(),
	 * request.getSession().getAttribute("role") == null ? null :
	 * request.getSession().getAttribute("role").toString());
	 * 
	 * log.info("cms 40 in fpo_qry_crpdf"); hd5 = new Paragraph(cd, fontb);
	 * hd5.setAlignment(2); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("AO OF CUSTOMS", font); hd5.setAlignment(2);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("PAD, " + padcity, fontb); hd5.setAlignment(2);
	 * document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("DATE : " + printcurrentDateTime, font);
	 * hd5.setAlignment(2); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * // document.newPage();
	 * 
	 * hd5=new
	 * Paragraph("Instructions for replying over email to Customs query/D-Call letter:"
	 * ,fontbi); hd5.setAlignment(0); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("1. The reply to be sent only to “abcd1234.fpo@icegate.gov.in”"
	 * ,fonti); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("2. Subject of the email should contain the following format: Foreign Post Import Article: "
	 * + itemid + " – Query from Customs - " +
	 * request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString() ,fonti);
	 * hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("3. Body of the reply email should not exceed 200 words or 1000 characters, which ever is maximum. If reply exceeds this limit, the reply shall be made  in a PDF as specified below, with a document name as “Reply”  (“Reply.pdf”) and sent as attachment."
	 * ,fonti); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("4. Provide your contact details like mobile number, email id etc while replying."
	 * ,fontbi); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new Paragraph("5. Specification of the attachments:",fontbi);
	 * hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("   a. The attached document should be in PDF format, in black and white with resolution no less than 200 dpi."
	 * ,fonti); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("   b. The attached PDF document should be of less than or equal to 1 MB size. If it exceeds, the same can be divided and uploaded as two documents."
	 * ,fonti); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("   c. The name of all attached PDFs shall be mentioned while sending the reply (For Ex: KYC aadhar.pdf; Invoice.pdf, Certificate.pdf, payment statement.pdf etc.,)"
	 * ,fonti); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("6. Please check the above specification before sending reply and avoid sending multiple emails for the same articles."
	 * ,fontbi); hd5.setAlignment(3); document.add(hd5);
	 * 
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph("        ", font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5 = new Paragraph(
	 * "       Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  "
	 * , fontli); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * log.info("cms 41 in fpo_qry_crpdf");
	 * 
	 * hd5=new Paragraph("        ",font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5=new Paragraph("        ",font); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * hd5=new
	 * Paragraph("    This document is electronically auto-generated and does not require signature."
	 * ,fontli); hd5.setAlignment(3); document.add(hd5); viewdoc.add(hd5);
	 * 
	 * 
	 * // Close document and outputStream. document.close(); viewdoc.close();
	 * outputStream.close(); viewStream.close();
	 * 
	 * System.out.println("Pdf created successfully.");
	 * 
	 * /// response.setContentType("application/pdf") ///
	 * response.setHeader("Content-Disposition", "inline; filename= .. " )
	 * 
	 * String to = fpoMainData.get(0).getRECP_EMAILID(); // LoginController.to=to;
	 * request.getSession().setAttribute("to", to); // LoginController.tomailid =
	 * tomailid; request.getSession().setAttribute("tomailid", tomailid);
	 * 
	 * String toEnteredMobileNumber = fpoQueryRepo.getMobileNumber(cinNo,
	 * fpoQueryRepo.getMaxQueryNo()); //
	 * LoginController.toMobileNumber=fpoMainData.get(0).getRECP_PHONE(); //
	 * LoginController.toEnteredMobileNumber=toEnteredMobileNumber;
	 * request.getSession().setAttribute("toEnteredMobileNumber",
	 * toEnteredMobileNumber); request.getSession().setAttribute("toMobileNumber",
	 * fpoMainData.get(0).getRECP_PHONE()); log.info("cms 42 in fpo_qry_crpdf");
	 * DCALLQRY_GEN dcallqry = new DCALLQRY_GEN();
	 * dcallqry.setItem_ID(request.getSession().getAttribute("itemid") == null ?
	 * null : request.getSession().getAttribute("itemid").toString());
	 * dcallqry.setDcallno(request.getSession().getAttribute("dcallno") == null ?
	 * null : request.getSession().getAttribute("dcallno").toString());
	 * java.util.Date curdt = new java.util.Date(); dcallqry.setGen_dt(curdt);
	 * dcallqry.setGenurl(request.getSession().getAttribute("url") == null ? null :
	 * request.getSession().getAttribute("url").toString());
	 * dcallqry.setCussite(request.getSession().getAttribute("cuSite") == null ?
	 * null : request.getSession().getAttribute("cuSite").toString());
	 * dcallqry.setRecp_name(request.getSession().getAttribute("recpname") == null ?
	 * null : request.getSession().getAttribute("recpname").toString());
	 * dcallqry.setGen_filename(request.getSession().getAttribute("filename") ==
	 * null ? null : request.getSession().getAttribute("filename").toString());
	 * dcallqry.setCinno(FPOrepost.getCinIdByItemId(request.getSession().
	 * getAttribute("itemid") == null ? null :
	 * request.getSession().getAttribute("itemid").toString()));
	 * log.info("cms 43 in fpo_qry_crpdf"); fpoQueryRepo.updQryReplyAsY(itemid);
	 * DcallQRYREPO.save(dcallqry); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/*
	 * public int sendaddlqrymail(String cinNo, String itemid, String dcallno,
	 * HttpSession session1) { int send = 0; String host = "smtp.icegate.gov.in";
	 * 
	 * 
	 * Properties properties = System.getProperties();
	 * 
	 * properties.put("mail.smtp.host", host); properties.put("mail.smtp.port",
	 * "25"); // properties.put("mail.smtp.ssl.enable", "true"); //
	 * properties.put("mail.debug", "true");
	 * 
	 * Session session = request.getSession().getDefaultInstance(properties);
	 * 
	 * // Session session = request.getSession().getInstance(properties, new
	 * javax.mail.Authenticator() { // // protected PasswordAuthentication
	 * getPasswordAuthentication() {
	 * 
	 * // return new PasswordAuthentication("sasikumaresh@gmail.com",
	 * "PremiMartha@123");
	 * 
	 * // }
	 * 
	 * // });
	 * 
	 * 
	 * String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // String tomailid =
	 * LoginController.tomailid; String tomailid = session1.getAttribute("tomailid")
	 * == null ? null : session1.getAttribute("tomailid").toString(); // String
	 * filename = LoginController.filename; String filename =
	 * session1.getAttribute("filename") == null ? null :
	 * session1.getAttribute("filename").toString(); // String
	 * to=LoginController.to; String to=session1.getAttribute("to") == null ? null :
	 * session1.getAttribute("to").toString();
	 * 
	 * 
	 * Pattern pattern = Pattern.compile(regex); Matcher matcher ;
	 * System.out.println(FPOrepost.mailvalid(to)); if (FPOrepost.mailvalid(to)==0 )
	 * {
	 * 
	 * System.out.println("tomailid is "+tomailid);
	 * 
	 * System.out.println("filename is "+filename);
	 * 
	 * System.out.println("to is "+to);
	 * 
	 * 
	 * if (to != null) { matcher = pattern.matcher(to);
	 * 
	 * 
	 * if (!(matcher.matches())) { System.out.println("INVALID CUSITM EMAIL ID...");
	 * to=null; }} } else to=null;
	 * 
	 * 
	 * try { // Create a default MimeMessage object. MimeMessage message = new
	 * MimeMessage(session);
	 * 
	 * // Recipient's email ID needs to be mentioned.
	 * 
	 * if (to != null) { to="sasikumaresh@gmail.com";
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); if
	 * (tomailid !=null) { if (pattern.matcher(tomailid).matches())
	 * message.addRecipient(Message.RecipientType.CC, new
	 * InternetAddress(tomailid));} System.out.println("first"); send = 1; }
	 * 
	 * else if (tomailid != null) { if (pattern.matcher(tomailid).matches() ) {
	 * //later this line is to be removed when it goes for launch....
	 * message.addRecipient(Message.RecipientType.TO, new
	 * InternetAddress(tomailid)); System.out.println("second"); send =1 ;}}
	 * 
	 * System.out.println("send is" + send);
	 * 
	 * if ( send == 1) { // Sender's email ID needs to be mentioned String from =
	 * "noreply@icegate.gov.in";
	 * 
	 * 
	 * // Set From: header field of the header. message.setFrom(new
	 * InternetAddress(from));
	 * 
	 * // Set To: header field of the header. //
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * 
	 * // Set Subject: header field
	 * message.setSubject("Foreign Post Import Article: " +
	 * session1.getAttribute("itemid") + " -  " + session1.getAttribute("cuSite") ==
	 * null ? null : session1.getAttribute("cuSite").toString() +
	 * " – Query from Customs - reg" );
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * 
	 * MimeBodyPart attachmentPart = new MimeBodyPart();
	 * 
	 * MimeBodyPart textPart = new MimeBodyPart();
	 * 
	 * 
	 * 
	 * try { DCALLQRY_GEN dcallQueryGen =
	 * DcallQRYREPO.getFirstDCallNumberByCinNo(cinNo);
	 * attachmentPart.attachFile(filename); //
	 * textPart.setText("This is just for testing purpose. Please ignore"); String
	 * constr =
	 * "<TABLE align=center style=border:1px black solid; width=75%><tbody><TR border=0><tD border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font size=2><i>Dear <b>"
	 * +session1.getAttribute("recpname")
	 * +"</b>,<br>Greetings from <u><style='text-decoration-color:Blue'>Indian Customs,</u> <h4>Reference:</h4><h4>Foreign Post Import - Article ID:  <font color=blue>"
	 * + itemid + "</font></h4>"; // constr = constr +
	 * "Foreign Post Import Article  ID: <style='text-decoration-color:Blue'>"
	 * +itemid+"</style>"; constr = constr +
	 * "<h4><i>Additional D-Call Letter No. : <font color=blue>" +
	 * fpoQueryRepo.getdcallno(cinNo,session1.getAttribute("cuSite") == null ? null
	 * : session1.getAttribute("cuSite").toString()) +"</font></i></h4>"; constr =
	 * constr +
	 * "<h4><i>Reply Dated: <font color=blue>"+fpoQueryRepo.getrplydt(cinNo,session1
	 * .getAttribute("cuSite") == null ? null :
	 * session1.getAttribute("cuSite").toString())
	 * +"</font>, the first D Call letter No. <font color=blue>" +
	 * dcallQueryGen.getDcallno() +"</font></i></h4>"; //constr = constr +
	 * "Additional D-Call Letter No. : "+dcallno; //constr = constr +
	 * "Reply Dated: "+fpoQueryRepo.getdcallno(cinNo,request.getSession().
	 * getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString())
	 * +", the first D Call letter No. "+fpoQueryRepo.getrplydt(cinNo,request.
	 * getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString()); constr = constr +
	 * "<p align=justify><i>Please refer to the above-mentioned import article and your reply on the D-Call Letter. Upon examination of your reply and the pdf documents uploaded by you, it is found necessary to call for additional information.  </i></p>"
	 * ; constr = constr +
	 * "<p align=justify><i>The  details of Customs query,  time limit for reply, the documents to be attached if any etc., may be referred  in the Additional Document Call letter (D-Call letter) sent to you via speed post or in the digital copy of the same attached herewith, which is password protected.</i></p>"
	 * ; constr = constr +
	 * "<p align=justify><i>Password to open the D-Call letter  is the last five characters of the subject Article ID.</i></p>"
	 * ; // constr=constr +
	 * "<p align=justify><b><u><i>Instructions for replying over email to Customs query/D-Call letter:</i></u></b></p></font>"
	 * ; constr=constr +
	 * "<p align=justify><b><u><i><font color=blue>Please visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.</font></i></u></b></p> "
	 * ; constr=constr + "<p align=justify><i><b>Click : <u><font color=blue>" +
	 * session1.getAttribute("url") +"</font></u></b></i>"; // constr=constr +
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
	/*
	 * constr=constr + "<p><b>Regards,</b><br>"; constr=constr +
	 * "<b>FPO - Indian Customs EDI,<br> CBIC</b></p>"; constr=constr +
	 * "</td><td border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	 * constr=constr +
	 * "<tr><td colspan=3><br><br><font size=1>    * Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  </td></tr>"
	 * ; constr=constr +
	 * "<TR><td colspan=3 align=center><font weight=50><hr><p align=center style=font-weight:50><font size=1>The above email is from an email address that can't receive emails.</p></font></td></tr>"
	 * ;; // constr=constr +
	 * "<br>Don't reply to this email. Please refer above on the instructions to reply over email.</p></font></td></tr>"
	 * ; constr=constr + "</tbody></table>";
	 * textPart.setContent(constr,"text/html");
	 * 
	 * multipart.addBodyPart(textPart); multipart.addBodyPart(attachmentPart);
	 * 
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * message.setContent(multipart);
	 * 
	 * System.out.println("sending..."); // Send message Transport.send(message);
	 * System.out.println("Sent message successfully...."); java.util.Date curdt =
	 * new java.util.Date();
	 * DcallQRYREPO.dcallqryupd(to,tomailid,curdt,session1.getAttribute("dcallno")
	 * == null ? null : session1.getAttribute("dcallno").toString());
	 * 
	 * DCALLQRY_GEN dCALLQRY_GEN= fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
	 * int count = 1;
	 * 
	 * if(dCALLQRY_GEN.getEmailcou()!=null) { count=
	 * Integer.parseInt(dCALLQRY_GEN.getEmailcou())+1; }
	 * fpoDcallQryRepo.updateDcallEmail(dcallno,tomailid,count); }
	 * 
	 * List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
	 * 
	 * insertpushDcall(tomailid,fpoMainData.get(0),"EMAIL",dcallno,session1);
	 * 
	 * } catch (MessagingException mex) { mex.printStackTrace(); }
	 * 
	 * // LoginController.to = null; session1.setAttribute("to", null); //
	 * LoginController.tomailid = null; session1.setAttribute("tomailid", null); //
	 * LoginController.filename = null; session1.setAttribute("filename", null); //
	 * LoginController.itemid = null; session1.setAttribute("itemid", null); //
	 * LoginController.dinno = null; session1.setAttribute("dinno", null); //
	 * LoginController.recpname = null; session1.setAttribute("recpname", null);
	 * 
	 * return send;
	 * 
	 * }
	 * 
	 */

	/*
	 * ****************************** local server additional qry mail sending
	 * ***********************************************
	 */

	
	
	public void fpo_addl_qry_crpdf(String cinNo, String que, HttpSession session, HttpServletRequest request) {
		
		Random rnd = new Random();
		int rndno = rnd.nextInt(9999999);
		System.out.println(rndno);
		
		List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
		// LoginController.recpname = fpoMainData.get(0).getRECP_NAME();
		request.getSession().setAttribute("recpname", fpoMainData.get(0).getRECP_NAME());
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
		
		
		// LoginController.tomailid=AddlQry.get(AddlQry.size()-1).QRY_EMAILID;
		request.getSession().setAttribute("tomailid", AddlQry.get(AddlQry.size() - 1).QRY_EMAILID);
		log.info("cms 1 in fpo_addl_qry_crpdf");

		DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = dateFormatter.format(new Date());
		DateFormat printdateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String printcurrentDateTime = printdateFormatter.format(new Date());
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String itemid = AddlQry.get(0).getITEM_ID();
		// LoginController.itemid=itemid;
		request.getSession().setAttribute("itemid", itemid);
		// LoginController.dinno = din;
		// request.getSession().setAttribute("dinno", din);
		log.info("cms 2 in fpo_addl_qry_crpdf");
		String str;
		str = "";

		if (que.equals("N"))
			str = "AAA";
		else if (que.equals("P"))
			str = "AAF";
		log.info("cms 3 in fpo_addl_qry_crpdf");
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String[] dateArray = date.split("-");
		String dcallno = (str
				+ (request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString())
				+ dateArray[0] + dateArray[1] + dateArray[2] + gen());
		// LoginController.dcallno = dcallno;
		request.getSession().setAttribute("dcallno", dcallno);
		

		System.out.println("dcallno is" + request.getSession().getAttribute("dcallno"));
		String dcall_part = request.getSession().getAttribute("dcallno") == null ? null
				: request.getSession().getAttribute("dcallno").toString().substring(5, 12);
		
		 String url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
			String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
			request.getSession().setAttribute("url", url);
			// LoginController.smsurl=smsurl;
			request.getSession().setAttribute("smsurl", smsurl);
		
	try {
						log.info("cms 4 in fpo_addl_qry_crpdf");
			
			String path1 = FPOrepost.getdcallqrypath()+itemid+currentDateTime+".pdf";
			File filename=new File(path1);
			request.getSession().setAttribute("filename", path1);
			OutputStream outputStream = 
			    new FileOutputStream(filename);

			PdfWriter pdfWriter=new PdfWriter(outputStream);
		  PdfDocument pdfDocument1 = new PdfDocument(pdfWriter);
	     
		  pdfDocument1.setDefaultPageSize(PageSize.A4);
		  pdfDocument1.addNewPage();
	        Document document = new Document(pdfDocument1);

	        log.info("cms 5 in fpo_addl_qry pdf");
	        //Here image path need to change according to localdrive
//	        String imgsrc = "D:\\VISHWANATH S G\\fpouat_jan10\\src\\main\\resources\\static\\images\\CBECnew.png";
//	        String imgsrc ="E:\\FPO\\fpouat_jul04\\src\\main\\resources\\static\\images";
	        
	        ImageData imageData = ImageDataFactory.create(FPOrepost.getimagespath() + "CBECnew.png");
	        Image image1 = new Image(imageData);
	        image1.setFixedPosition(pdfDocument1.getDefaultPageSize().getWidth()/2-140,pdfDocument1.getDefaultPageSize().getHeight()/2-130);
	        image1.setOpacity(0.10f);
	        document.add(image1);
	       
	        PdfFont fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	        PdfFont fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
	        PdfFont fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
	        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

		    	log.info("cms 6 in fpo_addl_qry_crpdf");
		    	Text text = new Text("DCall Letter : "+dcallno).setFont(fontb).setFontSize(10.0f);
		         	Paragraph hdi = new Paragraph(text);
		         		hdi.setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
		         		hdi.setFixedLeading(1.0f);
		         		document.add(hdi);
		         	      ImageData imageDataicon = ImageDataFactory.create(FPOrepost.getimagespath() + "CBEC.png");
		         	     Image image2 = new Image(imageDataicon);
		         	    image2.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		         	    image2.scaleToFit(80, 75);
		         	   document.add(image2);
		         	
		         	   log.info("cms 7 in fpo_addl_qry_crpdf");
				    	Text text1 = new Text("INDIAN CUSTOMS").setFont(font).setFontSize(10.0f);
			         	Paragraph hd1 = new Paragraph(text1);
			         		hd1.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
			         		hd1.setFixedLeading(1.0f);
			         		document.add(hd1);
			         		
			         		log.info("cms 8 in fpo_addl_qry_crpdf");
			         		Text textp = new Text("POSTAL APPRAISING DEPARTMENT").setFont(font).setFontSize(10.0f);
				         	Paragraph hdp = new Paragraph(textp);
				         		hdp.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
				         		hdp.setFixedLeading(1.0f);
				         		document.add(hdp);
				         	
				         		log.info("cms 9 in fpo_addl_qry_crpdf");
				         		Text textfpo = new Text("FOREIGN POST OFFICE").setFont(font).setFontSize(10.0f);
					         	Paragraph hdfpo = new Paragraph(textfpo);
					         		hdfpo.setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.CENTER);
					         		hdfpo.setFixedLeading(1.0f);
					         		document.add(hdfpo);
					         		
					         		log.info("cms 10 in fpo_addl_qry_crpdf");
					         		Text textcbi = new Text("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ").setFont(font).setFontSize(8.0f);
						         	Paragraph hdcbi = new Paragraph(textcbi);
						         		hdcbi.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		hdcbi.setFixedLeading(1.0f);
						         		document.add(hdcbi);
						         		
						         		log.info("cms 11 in fpo_addl_qry_crpdf");
						         		  Text text4 = new Text("Address : "+fpoQueryRepo.getfpositeaddr(cusite).toString()).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd3 =new Paragraph();
						         		  hd3.setFixedLeading(1.0f);
						         		  hd3.add(text4).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		  document.add(hd3);
						         		//  document2.add(hd3);
						         		
//						         		  log.info("cms 12 in fpo_addl_qry_crpdf");
//						         		  Text text5 = new Text("Email : "+fpoQueryRepo.getfpositemail(cusite)).setFont(font).setFontSize(10.0f);
//						         		  Paragraph hd4 =new Paragraph();
//						         		  hd4.setFixedLeading(1.0f);
//						         		  hd4.add(text5).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
//						         		  document.add(hd4);
						         		//  document2.add(hd4);
						         		  
						         		  log.info("cms 13 in fpo_addl_qry_crpdf");
						         		  Text text6 = new Text("Contact No. : " + fpoQueryRepo.getfpositeph(cusite)).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd5 =new Paragraph();
						         		  hd5.setFixedLeading(1.0f);
						         		  hd5.add(text6).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		  document.add(hd5);
						         		  
						         		  log.info("cms 14 in fpo_addl_qry_crpdf");
						         		  Text text7 = new Text("Visiting Hours : " + fpoQueryRepo.getfpositewh(cusite)).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd6 =new Paragraph();
						         		  hd6.setFixedLeading(1.0f);
						         		  hd6.add(text7).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		  document.add(hd6);
						         		  
						         		 log.info("cms 15 in fpo_addl_qry_crpdf");
						         		  Text text8 = new Text("  ").setFont(font).setFontSize(10.0f);
						         		  Paragraph hd7 = new Paragraph(text8);
						         		  hd7.add(text8);
						         		  document.add(hd7);
						         		
						         		  float width[]= {300,300};
						         		  Table table=new Table(width);
						         		
						         		 
						         		  log.info("cms 16 in fpo_addl_qry_crpdf");
						         		  Text textt1=new Text(" ").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp1=new Paragraph(textt1);
						         		  tp1.setFixedLeading(1.0f);
						         		
						         		  log.info("cms 17 in fpo_addl_qry_crpdf");
						         		  Text textt2 = new Text("ON INDIA GOVERNMENT SERVICE").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp2 = new Paragraph(textt2);
						         		  tp2.setFixedLeading(1.0f);
						         		
						         		  log.info("cms 18 in fpo_addl_qry_crpdf");
						         		  Text textt3 = new Text("To").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp3 = new Paragraph(textt3);
						         		  tp3.setFixedLeading(1.0f);
						         		
						         		  log.info("cms 19 in fpo_addl_qry_crpdf");
						         		  Text textt4 = new Text("By SPEED POST").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp4 = new Paragraph(textt4);
						         		  tp4.setFixedLeading(1.0f);
						         		  
						         		  
						         		 log.info("cms 20 in fpo_addl_qry_crpdf");
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp1).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp2).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp3).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp4).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
						         		  
						         		  document.add(table);
						         		  
						         		 log.info("cms 21 in fpo_addl_qry_crpdf");
						         		  float width1[]= {350,150};
						         		  Table table1=new Table(width1);
						         		  table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
						         		
						         		  log.info("cms 22 in fpo_addl_qry_crpdf");
						         		  String str1=fpoMainData.get(0).getRECP_NAME(); 
						         		  Text textt5 = new Text(str1);
						         		  textt5.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp5 = new Paragraph(textt5);
						         		  tp5.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp5).setBorder(Border.NO_BORDER));
						         		 
						         		  log.info("cms 23 in fpo_addl_qry_crpdf");
						         		  String strt="  ";
						         		  Text textt6 = new Text(strt);
						         		  textt6.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp6 = new Paragraph(textt6);
						         		  tp6.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp6).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 24 in fpo_addl_qry_crpdf");
						         		  String str2=fpoMainData.get(0).getRECP_ADD1();
						         		  Text textt7 = new Text(str2);
						         		  textt7.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp7 = new Paragraph(textt7);
						         		  tp7.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp7).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 25 in fpo_addl_qry_crpdf");
						         		  String strp="  ";
						         		  Text textt8 = new Text(strp);
						         		  textt8.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp8 = new Paragraph(textt8);
						         		  tp8.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp8).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 26 in fpo_addl_qry_crpdf");
						         		  if (fpoMainData.get(0).getRECP_ADD2()!=null) {
						         			

						         		  String str3=fpoMainData.get(0).getRECP_ADD2();
						         		  Text textt9 = new Text(str3);
						         		  textt9.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp9 = new Paragraph(textt9);
						         		  tp9.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp9).setBorder(Border.NO_BORDER));
						         	
						         		  log.info("cms 27 in fpo_addl_qry_crpdf");
						         		  
						         		  String str90="  ";
						         		  Text textt10 = new Text(str90);
						         		  textt10.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp10 = new Paragraph(textt10);
						         		  tp10.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp10).setBorder(Border.NO_BORDER));

						         		  }
						         		
						         		  log.info("cms 28 in fpo_addl_qry_crpdf");
						         		  String padcity = FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

						         		if (padcity!=null) {
						         		 String str4 =fpoMainData.get(0).getRECP_CITY();
						         		  Text textt11 = new Text(str4);
						         		  textt11.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp11 = new Paragraph(textt11);
						         		  tp11.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp11).setBorder(Border.NO_BORDER));
						         		
						         		  
						         		  String str09="  ";
						         		  Text textt12 = new Text(str09);
						         		  textt12.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp12 = new Paragraph(textt12);
						         		  tp12.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp12).setBorder(Border.NO_BORDER));
						         		}  
						         		
						         		log.info("cms 29 in fpo_addl_qry_crpdf");
						         		if (fpoMainData.get(0).getRECP_ZIP()!=null) {
						         			String str5=fpoMainData.get(0).getRECP_ZIP();
						         		
						         		  Text textt13 = new Text(str5);
						         		  textt13.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp13 = new Paragraph(textt13);
						         		  tp13.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp13).setBorder(Border.NO_BORDER));
						         		
						         		  String str89="   ";
						         		  Text textt14 = new Text(str89);
						         		  textt14.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp14 = new Paragraph(textt14);
						         		  tp14.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp14).setBorder(Border.NO_BORDER));
						         		}	  
						         		
						         		  log.info("cms 30 in fpo_addl_qry_crpdf");
						         		  if (fpoMainData.get(0).getRECP_PHONE()!=null) {
						         			

						         		  String str6=fpoMainData.get(0).getRECP_PHONE();
						         		  Text textt15 = new Text("Ph:"+str6);
						         		  textt15.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp15 = new Paragraph(textt15);
						         		  tp15.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp15).setBorder(Border.NO_BORDER));

						         		  }  
						         		    
						         		   
						         		   document.add(table1);
						         		   log.info("cms 31 in fpo_addl_qry_crpdf");
						         		
						         		   
						         		   Text text16=new Text("  ");
						         		   Paragraph tp16 = new Paragraph(text16);
						         		   document.add(tp16);
						         		 
						         		   log.info("cms 32 in fpo_addl_qry_crpdf");
						         		   Text text17=new Text("Sir/Madam");
						         		   text17.setFont(font).setFontSize(10.0f);
						         		   Paragraph tp17=new Paragraph (text17);
						         		   tp17.setFixedLeading(1.0f);
						         		   document.add(tp17);
						         		 
						         		   log.info("cms 33 in fpo_addl_qry_crpdf");
						         		  float widths[]= {450,50};
						         		  Table tables=new Table(widths);
						         		   Text texts1=new Text("Sub: IMPORT - Foreign Post Article ID "+fpoMainData.get(0).getITEM_ID()+"-reg.");
						         		   texts1.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps1=new Paragraph (texts1);
						         		   tps1.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps1).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 34 in fpo_addl_qry_crpdf");
						         		 Text texts2=new Text("  ");
						         		   texts2.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps2=new Paragraph (texts2);
						         		   tps2.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps2).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 35 in fpo_addl_qry_crpdf");
						         		 Text texts3=new Text("Ref: First D-CALL Letter No. "+ fpoQueryRepo.getdcallno(cinNo,
													request.getSession().getAttribute("cuSite") == null ? null		: request.getSession().getAttribute("cuSite").toString())
											+ " and your reply dated "+ fpoQueryRepo.getrplydt(cinNo, request.getSession().getAttribute("cuSite") == null ? null
													: request.getSession().getAttribute("cuSite").toString()));
						         		   texts3.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps3=new Paragraph (texts3);
						         		   tps3.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps3).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 36 in fpo_addl_qry_crpdf");
						         		 Text texts4=new Text("  ");
						         		   texts4.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps4=new Paragraph (texts4);
						         		   tps4.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps4).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 37 in fpo_addl_qry_crpdf");
						         		 Text texts5=new Text("Additional D-Call Letter No. : "+dcallno);
						         		   texts5.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps5=new Paragraph (texts5);
						         		   tps5.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps5).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 38 in fpo_addl_qry_crpdf");
						         		 Text texts6=new Text("  ");
						         		   texts6.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps6=new Paragraph (texts6);
						         		   tps6.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps6).setBorder(Border.NO_BORDER));
						         		 document.add(tables);
						         		
						         		 log.info("cms 39 in fpo_addl_qry_crpdf");
						         		 Text texts7=new Text("***");
						         		   texts7.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps7=new Paragraph (texts7);
						         		  tps7.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		   tps7.setFixedLeading(1.0f);
						         		   document.add(tps7);
						         		 
						         		   log.info("cms 40 in fpo_addl_qry_crpdf");
						         		  Text texts8=new Text("1.	  The above mentioned Foreign Post Parcel has arrived at the FPO and was awaiting customs clearance for want of the reply on the D-Call Letter.");
						         		   texts8.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps8=new Paragraph (texts8).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
//						         		   tps8.setFixedLeading(8.0f);
						         		   document.add(tps8);
						         		 
						         		   log.info("cms 41 in fpo_addl_qry_crpdf");
						         		  Text texts9=new Text("2.	  The reply and Documents furnished by you have been examined and it is seen that");
						         		   texts9.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps9=new Paragraph (texts9).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
//						         		   tps9.setFixedLeading(8.0f);
						         		   document.add(tps9);
						         		   
						         		  log.info("cms 42 in fpo_addl_qry_crpdf");
						      			List<FpoSecondDefaultQuery> secdefqry;
					
						      			char ch = 'a';
						      			int asciiValue = ch;
						      			System.out.println(asciiValue); // A=65
						      			for (int i = 91; i < 128; i++) { // Use Typecasting to get ASCII value
						      				char ch2 = (char) i;
						      				System.out.println(ch2);
						      			}
						      			int once5 = 0;
						      			int once6 = 0;
						      			String schr = "";
						      			int prn = 97;
						      			char ch2 = (char) prn;
						      			System.out.println(ch2);
						    			log.info("cms 43 in fpo_addl_qry_crpdf");
						    			for (int i = 0; i < AddlQry.size() - 1; i++) {
						    				ch2 = (char) prn;
						    				if (AddlQry.get(i).getQRY_DEF_SLNO().equals("1")) {
						    					Text texts10=new Text(ch2 + ") " + AddlQry.get(0).getQRY_DESC());
						    					texts10.setFont(font).setFontSize(10.0f);
						    					Paragraph tps10=new Paragraph (texts10).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
//						    					tps10.setFixedLeading(1.0f);
						    					document.add(tps10);
						    					
						    				}
						    				
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("2")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("2");
						    					Text texts11=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts11.setFont(font).setFontSize(10.0f);
						    					Paragraph tps11=new Paragraph (texts11).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
//						    					tps11.setFixedLeading(1.0f);
						    					document.add(tps11);
						    				
						    				}
						    				
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("3")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("3");
						    					Text texts12=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
						    					texts12.setFont(font).setFontSize(10.0f);
						    					Paragraph tps12=new Paragraph (texts12).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps12);
						    				}
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("4")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("4");
						    					Text texts13=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts13.setFont(font).setFontSize(10.0f);
						    					Paragraph tps13=new Paragraph (texts13).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps13);
						    				}
						    				
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("5")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("5");
						    					if (once5 == 0) {
						    					Text texts14=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
						    					texts14.setFont(font).setFontSize(10.0f);
						    					Paragraph tps14=new Paragraph (texts14).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps14);
						    				}
						    				
						    					log.info("cms 44 in fpo_addl_qry_crpdf");
						    					if (once5 == 0)
						    						schr = "i";
						    					else
						    						schr = "ii";
						    					
						    					Text texts15=new Text("  "+schr + ") " + AddlQry.get(i).getQRY_DESC());
						    					texts15.setFont(font).setFontSize(10.0f);
						    					Paragraph tps15=new Paragraph (texts15).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps15);
						    					once5 = once5 + 1;
						    				} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("6")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("6");
						    					if (once6 == 0) {
						    						Text texts16=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
							    					texts16.setFont(font).setFontSize(10.0f);
							    					Paragraph tps16=new Paragraph (texts16).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
							    					document.add(tps16);
						    					}
						    					if (once6 == 0)
						    						schr = "i";
						    					else
						    						schr = "ii";
						    					Text texts17=new Text("  "+schr + ") " + AddlQry.get(i).getQRY_DESC());
						    					texts17.setFont(font).setFontSize(10.0f);
						    					Paragraph tps17=new Paragraph (texts17).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps17);
//						    					tables2.addCell(new Cell().setHeight(13.0f).add(tps17).setBorder(Border.NO_BORDER));
						    					once6 = once6 + 1;
						    				} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("7")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("7");
						    					Text texts17=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts17.setFont(font).setFontSize(10.0f);
						    					Paragraph tps17=new Paragraph (texts17).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					document.add(tps17);
						    				}
						    				if ((once5 == 0 || once6 == 0) && once5 != 1)
						    					prn = prn + 1;
						    			}
						    			log.info("cms 45 in fpo_addl_qry_crpdf");
//						    			document.add(tables2);
						    			
						    			Text texts18=new Text("3.    The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the parcel. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the parcel on its arrival, will be liable to be assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you.");
				    					texts18.setFont(font).setFontSize(10.0f);
				    					Paragraph tps18=new Paragraph (texts18).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps18);		
				    					
				    					log.info("cms 46 in fpo_addl_qry_crpdf");
				    					Text texts19=new Text("4.    It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the date of  this Document Call letter), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the recipient/addressee consignee.");
				    					texts19.setFont(font).setFontSize(10.0f);
				    					Paragraph tps19=new Paragraph (texts19).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps19);
				    				
				    					log.info("cms 47 in fpo_addl_qry_crpdf");
				    					Text texts20=new Text("5.    This letter does not confer any right on the recipient/addressee for clearance of the parcel from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the parcel.");
				    					texts20.setFont(font).setFontSize(10.0f);
				    					Paragraph tps20=new Paragraph (texts20).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps20);	
				    					
				    					log.info("cms 48 in fpo_addl_qry_crpdf");
				    					Text texts21=new Text("6.    Please quote the above Article ID (parcel number) in all your correspondence, if any to be made.");
				    					texts21.setFont(font).setFontSize(10.0f);
				    					Paragraph tps21=new Paragraph (texts21).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps21);	
				    					
				    					log.info("cms 49 in fpo_addl_qry_crpdf");
				    				//	Text texts22=new Text(" 7.    Please reply by post / courier / any other mode to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.");  to be uncommented for Email and SmS 
				    					Text texts22=new Text(" 7.    Please reply by post / courier / any other mode to the above mentioned postal address in the header.");
				    					texts22.setFont(fontb).setFontSize(10.0f).setFontColor(Color.BLUE);
				    					Paragraph tps22=new Paragraph (texts22).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps22);	
				    					
				    					log.info("cms 50 in fpo_addl_qry_crpdf");

				    					

//				    					System.out.println("dcallno is" + request.getSession().getAttribute("dcallno"));
//				    					String dcall_part = request.getSession().getAttribute("dcallno") == null ? null
//				    							: request.getSession().getAttribute("dcallno").toString().substring(5, 12);

				    					log.info("cms 51 in fpo_addl_qry_crpdf");

				    					// String url="https://www.postalimport.gov.in/"+dcall_part+"/"+rndno;
//				    					String url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
//				    					String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
				    					// LoginController.url=url;
//				    					request.getSession().setAttribute("url", url);
//				    					// LoginController.smsurl=smsurl;
//				    					request.getSession().setAttribute("smsurl", smsurl);

				    					// to be uncommented for Email and SmS 
				    				/*	log.info("cms 52 in fpo_addl_qry_crpdf");
				    					Text texts23=new Text(url);
				    					texts23.setFont(fontbi).setFontSize(9.0f);
				    					Paragraph tps23=new Paragraph (texts23).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLUE);;
				    					document.add(tps23);	*/
				    					
				    					log.info("cms 53 in fpo_addl_qry_crpdf");
				    					Text texts24=new Text("  ");
				    					texts24.setFont(font).setFontSize(10.0f);
				    					Paragraph tps24=new Paragraph (texts24).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps24);
				    					
				    					log.info("cms 54 in fpo_addl_qry_crpdf");
				    					Text texts25=new Text("  Yours sincerely, ");
				    					texts25.setFont(font).setFontSize(10.0f);
				    					Paragraph tps25=new Paragraph (texts25).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
				    					document.add(tps25);
				    					
				    					log.info("cms 55 in fpo_addl_qry_crpdf");
				    					Text texts26=new Text("  ");
				    					texts26.setFont(font).setFontSize(10.0f);
				    					Paragraph tps26=new Paragraph (texts26).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps26);
				    					
				    					log.info("cms 56 in fpo_addl_qry_crpdf");
				    					Text texts27=new Text("  ");
				    					texts27.setFont(font).setFontSize(10.0f);
				    					Paragraph tps27=new Paragraph (texts27).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps27);
				    					
				    					log.info("cms 57 in fpo_addl_qry_crpdf");
				    					String cd = FPOrepost.getoffname(
				    							request.getSession().getAttribute("offId") == null ? null
				    									: request.getSession().getAttribute("offId").toString(),
				    							request.getSession().getAttribute("cuSite") == null ? null
				    									: request.getSession().getAttribute("cuSite").toString(),
				    							request.getSession().getAttribute("role") == null ? null
				    									: request.getSession().getAttribute("role").toString());
				    					
				    					log.info("cms 58 in fpo_addl_qry_crpdf");
				    					Text texts28=new Text(cd);
				    					texts28.setFont(fontb).setFontSize(10.0f);
				    					Paragraph tps28=new Paragraph (texts28).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps28.setFixedLeading(1.0f);
				    					document.add(tps28);
				    					log.info("cms 59 in fpo_addl_qry_crpdf");
				    					Text texts29=new Text("AO OF CUSTOMS");
				    					texts29.setFont(font).setFontSize(10.0f);
				    					Paragraph tps29=new Paragraph (texts29).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps29.setFixedLeading(1.0f);

				    					document.add(tps29);
				    					
				    					log.info("cms 60 in fpo_addl_qry_crpdf");
				    					Text texts30=new Text("PAD, " + padcity);
				    					texts30.setFont(fontb).setFontSize(10.0f);
				    					Paragraph tps30=new Paragraph (texts30).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps30.setFixedLeading(1.0f);
				    					document.add(tps30);
				    					
				    					log.info("cms 61 in fpo_addl_qry_crpdf");
				    					Text texts31=new Text("DATE : " + printcurrentDateTime);
				    					texts31.setFont(font).setFontSize(10.0f);
				    					Paragraph tps31=new Paragraph (texts31).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps31.setFixedLeading(1.0f);
				    					document.add(tps31);
				    					
				    					log.info("cms 62 in fpo_addl_qry_crpdf");
				    					Text texts32=new Text("  ");
				    					texts32.setFont(font).setFontSize(10.0f);
				    					Paragraph tps32=new Paragraph (texts32).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps32);
				    					
				    					log.info("cms 63 in fpo_addl_qry_crpdf");
				    					Text texts33=new Text("  ");
				    					texts33.setFont(font).setFontSize(10.0f);
				    					Paragraph tps33=new Paragraph (texts33).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					document.add(tps33);
				    					
				    					//to be uncommented for Email and SmS 
				    					/*log.info("cms 64 in fpo_addl_qry_crpdf");
				    					Text texts34=new Text(	"     Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  ");
				    					texts34.setFont(fontli).setFontSize(9.0f);
				    					Paragraph tps34=new Paragraph (texts34).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.LEFT);
				    					document.add(tps34);
				    					document.add(image1); */
				    					
				    					document.close();
				    					outputStream.close();
						         		  
				    					log.info("cms 65 in fpo_addl_qry_crpdf");
				    					 String USER = itemid.substring(8, 13);
				    					 PDDocument pdd = PDDocument.load(filename);
				    					  
				    					 
				    					 AccessPermission ap = new AccessPermission();
				    					 StandardProtectionPolicy stpp = new StandardProtectionPolicy(USER ,USER , ap);
				    					 stpp.setEncryptionKeyLength(128);
				    					 stpp.setPermissions(ap);
				    					 pdd.protect(stpp);
				    					 pdd.save(filename); 	 // save the document
				    					 pdd.close();
			System.out.println("Pdf created successfully.");
			log.info("cms 66 in fpo_addl_qry_crpdf");
 
		

			
		}
	catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			log.info("cms 67 in fpo_addl_qry_crpdf");
			// Create Document instance.

			String path1 = FPOrepost.getdcallqryviewpath() + itemid + currentDateTime + ".pdf";
			File viewfilename=new File(path1);
	// request.getSession().setAttribute("filename", viewfilename);
 OutputStream viewStream = 
			    new FileOutputStream(viewfilename);

 PdfWriter pdfWriter=new PdfWriter(viewStream);
		  PdfDocument pdfDocument1 = new PdfDocument(pdfWriter);
	     
		  pdfDocument1.setDefaultPageSize(PageSize.A4);
      pdfDocument1.addNewPage();
	        Document viewdoc = new Document(pdfDocument1);

//image path need to be given according to localstorage
// String imgsrc = "D:\\VISHWANATH S G\\APIS\\fpouat_jan07\\src\\main\\resources\\static\\images\\CBECnew.png";
//      ImageData imageData = ImageDataFactory.create(imgsrc);
	        ImageData imageData = ImageDataFactory.create(FPOrepost.getimagespath() + "CBECnew.png");
      Image image1 = new Image(imageData);
      image1.setFixedPosition(pdfDocument1.getDefaultPageSize().getWidth()/2-140,pdfDocument1.getDefaultPageSize().getHeight()/2-130);
      image1.setOpacity(0.10f);
 viewdoc.add(image1);
 PdfFont fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	PdfFont fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
	PdfFont fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
	PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

		    	log.info("cms 68 in fpo_addl_qry_crpdf");
		    	Text text = new Text("DCall Letter : "+dcallno).setFont(fontb).setFontSize(10.0f);
		         	Paragraph hdi = new Paragraph(text);
		         		hdi.setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
		         		hdi.setFixedLeading(1.0f);
		         		viewdoc.add(hdi);
		         		log.info("cms 69 in fpo_addl_qry_crpdf");
		         		ImageData imageDataicon = ImageDataFactory.create(FPOrepost.getimagespath() + "CBEC.png");
		         	     Image image2 = new Image(imageDataicon);
		         	    image2.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		         	    image2.scaleToFit(80, 75);
		         	   viewdoc.add(image2);
		         		log.info("cms 70 in fpo_addl_qry_crpdf");
				    	Text text1 = new Text("INDIAN CUSTOMS").setFont(font).setFontSize(10.0f);
			         	Paragraph hd1 = new Paragraph(text1);
			         		hd1.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
			         		hd1.setFixedLeading(1.0f);
			         		viewdoc.add(hd1);
			         		log.info("cms 71 in fpo_addl_qry_crpdf");
			         		Text textp = new Text("POSTAL APPRAISING DEPARTMENT").setFont(font).setFontSize(10.0f);
				         	Paragraph hdp = new Paragraph(textp);
				         		hdp.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
				         		hdp.setFixedLeading(1.0f);
				         		viewdoc.add(hdp);
				         		log.info("cms 72 in fpo_addl_qry_crpdf");
				         		Text textfpo = new Text("FOREIGN POST OFFICE").setFont(font).setFontSize(10.0f);
					         	Paragraph hdfpo = new Paragraph(textfpo);
					         		hdfpo.setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.CENTER);
					         		hdfpo.setFixedLeading(1.0f);
					         		viewdoc.add(hdfpo);
					         		log.info("cms 73 in fpo_addl_qry_crpdf");
					         		Text textcbi = new Text("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ").setFont(font).setFontSize(8.0f);
						         	Paragraph hdcbi = new Paragraph(textcbi);
						         		hdcbi.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		hdcbi.setFixedLeading(1.0f);
						         		viewdoc.add(hdcbi);
						         		
						         		log.info("cms 74 in fpo_addl_qry_crpdf");
						         		  Text text4 = new Text("Address : "+fpoQueryRepo.getfpositeaddr(cusite).toString()).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd3 =new Paragraph();
						         		  hd3.setFixedLeading(1.0f);
						         		  hd3.add(text4).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		 viewdoc.add(hd3);

						         		 log.info("cms 75 in fpo_addl_qry_crpdf");
//						         		  Text text5 = new Text("Email : "+fpoQueryRepo.getfpositemail(cusite)).setFont(font).setFontSize(10.0f);
//						         		  Paragraph hd4 =new Paragraph();
//						         		  hd4.setFixedLeading(1.0f);
//						         		  hd4.add(text5).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
//						         		 viewdoc.add(hd4);
						         		  
						         		  log.info("cms 76 in fpo_addl_qry_crpdf");
						         		  Text text6 = new Text("Contact No. : " + fpoQueryRepo.getfpositeph(cusite)).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd5 =new Paragraph();
						         		  hd5.setFixedLeading(1.0f);
						         		  hd5.add(text6).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		 viewdoc.add(hd5);
						         		  
						         		  log.info("cms 77 in fpo_addl_qry_crpdf");
						         		  Text text7 = new Text("Visiting Hours : " + fpoQueryRepo.getfpositewh(cusite)).setFont(font).setFontSize(10.0f);
						         		  Paragraph hd6 =new Paragraph();
						         		  hd6.setFixedLeading(1.0f);
						         		  hd6.add(text7).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		 viewdoc.add(hd6);
						         		  
						         		 log.info("cms 78 in fpo_addl_qry_crpdf");
						         		  Text text8 = new Text("  ").setFont(font).setFontSize(10.0f);
						         		  Paragraph hd7 = new Paragraph(text8);
						         		  hd7.add(text8);
						         		 viewdoc.add(hd7);
						         		
						         		  float width[]= {300,300};
						         		  Table table=new Table(width);
						         	
						         		 
						         		  log.info("cms 79 in fpo_addl_qry_crpdf");
						         		  Text textt1=new Text(" ").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp1=new Paragraph(textt1);
						         		  tp1.setFixedLeading(1.0f);
						         		  
						         		  Text textt2 = new Text("ON INDIA GOVERNMENT SERVICE").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp2 = new Paragraph(textt2);
						         		  tp2.setFixedLeading(1.0f);
						         		  
						         		  Text textt3 = new Text("To").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp3 = new Paragraph(textt3);
						         		  tp3.setFixedLeading(1.0f);
						         		  
						         		  Text textt4 = new Text("By SPEED POST").setFont(font).setFontSize(10.0f);
						         		  Paragraph tp4 = new Paragraph(textt4);
						         		  tp4.setFixedLeading(1.0f);
						         		  
						         		  
						         		  
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp1).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp2).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp3).setBorder(Border.NO_BORDER));
						         		  table.addCell(new Cell().setHeight(11.0f).add(tp4).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
						         		  
						         		
						         		 viewdoc.add(table);
						         		
						         		 log.info("cms 80 in fpo_addl_qry_crpdf");
						         		  
						         		  
						         		  float width1[]= {350,150};
						         		  Table table1=new Table(width1);
						         		  table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
						         		  
						         		  String str1=fpoMainData.get(0).getRECP_NAME(); 
						         		  Text textt5 = new Text(str1);
						         		  textt5.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp5 = new Paragraph(textt5);
						         		  tp5.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp5).setBorder(Border.NO_BORDER));
						         		  
						         		 log.info("cms 81 in fpo_addl_qry_crpdf");
						         		  
						         		  String strt="  ";
						         		  Text textt6 = new Text(strt);
						         		  textt6.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp6 = new Paragraph(textt6);
						         		  tp6.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp6).setBorder(Border.NO_BORDER));
						         		  
						         		 log.info("cms 82 in fpo_addl_qry_crpdf");
						         		  String str2=fpoMainData.get(0).getRECP_ADD1();
						         		  Text textt7 = new Text(str2);
						         		  textt7.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp7 = new Paragraph(textt7);
						         		  tp7.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp7).setBorder(Border.NO_BORDER));
						         		  
						         		 log.info("cms 83 in fpo_addl_qry_crpdf");
						         		  String strp="  ";
						         		  Text textt8 = new Text(strp);
						         		  textt8.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp8 = new Paragraph(textt8);
						         		  tp8.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp8).setBorder(Border.NO_BORDER));

						         		 log.info("cms 84 in fpo_addl_qry_crpdf");
						         		  if (fpoMainData.get(0).getRECP_ADD2()!=null) {
						         			

						         		  String str3=fpoMainData.get(0).getRECP_ADD2();
						         		  Text textt9 = new Text(str3);
						         		  textt9.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp9 = new Paragraph(textt9);
						         		  tp9.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp9).setBorder(Border.NO_BORDER));
						         		 log.info("cms 85 in fpo_addl_qry_crpdf");
						         		  
						         		  String str90="  ";
						         		  Text textt10 = new Text(str90);
						         		  textt10.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp10 = new Paragraph(textt10);
						         		  tp10.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp10).setBorder(Border.NO_BORDER));
						         		 log.info("cms 86 in fpo_addl_qry_crpdf");
						         		  }
						         		  
						         		  String padcity = FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

						         		if (padcity!=null) {
						         		 String str4 =fpoMainData.get(0).getRECP_CITY();
						         		  Text textt11 = new Text(str4);
						         		  textt11.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp11 = new Paragraph(textt11);
						         		  tp11.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp11).setBorder(Border.NO_BORDER));
						         		 log.info("cms 87 in fpo_addl_qry_crpdf");
						         		  
						         		  String str09="  ";
						         		  Text textt12 = new Text(str09);
						         		  textt12.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp12 = new Paragraph(textt12);
						         		  tp12.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp12).setBorder(Border.NO_BORDER));
						         		 
						         		}  
						         		
						         		log.info("cms 88 in fpo_addl_qry_crpdf");
						         		  
						         		if (fpoMainData.get(0).getRECP_ZIP()!=null) {
						         		String str5=fpoMainData.get(0).getRECP_ZIP();
						         		  Text textt13 = new Text(str5);
						         		  textt13.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp13 = new Paragraph(textt13);
						         		  tp13.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp13).setBorder(Border.NO_BORDER));
						         		
						         		  String str89="   ";
						         		  Text textt14 = new Text(str89);
						         		  textt14.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp14 = new Paragraph(textt14);
						         		  tp14.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp14).setBorder(Border.NO_BORDER));
						         		  
						         		}		 
						         		  if (fpoMainData.get(0).getRECP_PHONE()!=null) {
						         			

						         		  String str6=fpoMainData.get(0).getRECP_PHONE();
						         		  Text textt15 = new Text("Ph:"+str6);
						         		  textt15.setFont(font).setFontSize(10.0f);
						         		  Paragraph tp15 = new Paragraph(textt15);
						         		  tp15.setFixedLeading(1.0f);
						         		  table1.addCell(new Cell().setHeight(11.0f).add(tp15).setBorder(Border.NO_BORDER));

						         		  }  
						         		    
						         		   
						         		 viewdoc.add(table1);
						         		
						         		   log.info("cms 89 in fpo_addl_qry_crpdf");
						         	
						         		   
						         		   Text text16=new Text("  ");
						         		   Paragraph tp16 = new Paragraph(text16);
						         		  viewdoc.add(tp16);
						         		   log.info("cms 90 in fpo_addl_qry_crpdf");
						         		   
						         		   Text text17=new Text("Sir/Madam");
						         		   text17.setFont(font).setFontSize(10.0f);
						         		   Paragraph tp17=new Paragraph (text17);
						         		   tp17.setFixedLeading(1.0f);
						         		  viewdoc.add(tp17);
						         		
						         		   log.info("cms 91 in fpo_addl_qry_crpdf");
						         		  float widths[]= {450,50};
						         		  Table tables=new Table(widths);
						         		   Text texts1=new Text("Sub: IMPORT - Foreign Post Article ID "+fpoMainData.get(0).getITEM_ID()+"-reg.");
						         		   texts1.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps1=new Paragraph (texts1);
						         		   tps1.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps1).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 92 in fpo_addl_qry_crpdf");
						         		 Text texts2=new Text("  ");
						         		   texts2.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps2=new Paragraph (texts2);
						         		   tps2.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps2).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 93 in fpo_addl_qry_crpdf");
						         		 Text texts3=new Text("Ref: First D-CALL Letter No. "+ fpoQueryRepo.getdcallno(cinNo,
													request.getSession().getAttribute("cuSite") == null ? null		: request.getSession().getAttribute("cuSite").toString())
											+ " and your reply dated "+ fpoQueryRepo.getrplydt(cinNo, request.getSession().getAttribute("cuSite") == null ? null
													: request.getSession().getAttribute("cuSite").toString()));
						         		   texts3.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps3=new Paragraph (texts3);
						         		   tps3.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps3).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 94 in fpo_addl_qry_crpdf");
						         		 Text texts4=new Text("  ");
						         		   texts4.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps4=new Paragraph (texts4);
						         		   tps4.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps4).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 95 in fpo_addl_qry_crpdf");
						         		 Text texts5=new Text("Additional D-Call Letter No. : "+dcallno);
						         		   texts5.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps5=new Paragraph (texts5);
						         		   tps5.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps5).setBorder(Border.NO_BORDER));
						         		
						         		  log.info("cms 96 in fpo_addl_qry_crpdf");
						         		 Text texts6=new Text("  ");
						         		   texts6.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps6=new Paragraph (texts6);
						         		   tps6.setFixedLeading(1.0f);
						         		  tables.addCell(new Cell().setHeight(11.0f).add(tps6).setBorder(Border.NO_BORDER));
						         		 viewdoc.add(tables);
						         		 
						         		 log.info("cms 97 in fpo_addl_qry_crpdf");
						         		 Text texts7=new Text("***");
						         		   texts7.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps7=new Paragraph (texts7);
						         		  tps7.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
						         		   tps7.setFixedLeading(1.0f);
						         		  viewdoc.add(tps7);
						         		
						         		  log.info("cms 98 in fpo_addl_qry_crpdf");
						         		  Text texts8=new Text("1.	   The above mentioned Foreign Post Parcel has arrived at the FPO and was awaiting customs clearance for want of the reply on the D-Call Letter.");
						         		   texts8.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps8=new Paragraph (texts8).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
//						         		   tps8.setFixedLeading(8.0f);
						         		  viewdoc.add(tps8);
						         		
						         		  log.info("cms 99 in fpo_addl_qry_crpdf");
						         		  Text texts9=new Text("2.	   The reply and Documents furnished by you have been examined and it is seen that");
						         		   texts9.setFont(font).setFontSize(10.0f);
						         		   Paragraph tps9=new Paragraph (texts9).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
//						         		   tps9.setFixedLeading(8.0f);
						         		  viewdoc.add(tps9);
						         		   
						         		   log.info("cms 100 in fpo_addl_qry_crpdf");

						      			List<FpoSecondDefaultQuery> secdefqry;
						      			
						      			char ch = 'a';
						      			int asciiValue = ch;
						      			System.out.println(asciiValue); // A=65
						      			for (int i = 91; i < 128; i++) { // Use Typecasting to get ASCII value
						      				char ch2 = (char) i;
						      				System.out.println(ch2);
						      			}
						      			int once5 = 0;
						      			int once6 = 0;
						      			String schr = "";
						      			int prn = 97;
						      			char ch2 = (char) prn;
						      			
						      		   log.info("cms 101 in fpo_addl_qry_crpdf");
						    			for (int i = 0; i < AddlQry.size() - 1; i++) {
						    				ch2 = (char) prn;
						    				if (AddlQry.get(i).getQRY_DEF_SLNO().equals("1")) {
						    					Text texts10=new Text(ch2 + ") " + AddlQry.get(0).getQRY_DESC());
						    					texts10.setFont(font).setFontSize(10.0f);
						    					Paragraph tps10=new Paragraph (texts10).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
//						    					tps10.setFixedLeading(1.0f);
						    					viewdoc.add(tps10);
						    					
						    				}
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("2")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("2");
						    					Text texts11=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts11.setFont(font).setFontSize(10.0f);
						    					Paragraph tps11=new Paragraph (texts11).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
//						    					tps11.setFixedLeading(1.0f);
						    					viewdoc.add(tps11);
						    				
						    				}
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("3")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("3");
						    					Text texts12=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
						    					texts12.setFont(font).setFontSize(10.0f);
						    					Paragraph tps12=new Paragraph (texts12).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps12);
						    				}
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("4")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("4");
						    					Text texts13=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts13.setFont(font).setFontSize(10.0f);
						    					Paragraph tps13=new Paragraph (texts13).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps13);
						    				}
						    				
						    				else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("5")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("5");
						    					if (once5 == 0) {
						    					Text texts14=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
						    					texts14.setFont(font).setFontSize(10.0f);
						    					Paragraph tps14=new Paragraph (texts14).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps14);
						    				}
						    					if (once5 == 0)
						    						schr = "i";
						    					else
						    						schr = "ii";
						    					
						    					Text texts15=new Text("  "+schr + ") "  + AddlQry.get(i).getQRY_DESC());
						    					texts15.setFont(font).setFontSize(10.0f);
						    					Paragraph tps15=new Paragraph (texts15).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps15);
						    					once5 = once5 + 1;
						    				} else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("6")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("6");
						    					if (once6 == 0) {
						    						Text texts16=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION());
							    					texts16.setFont(font).setFontSize(10.0f);
							    					Paragraph tps16=new Paragraph (texts16).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
							    					viewdoc.add(tps16);
						    					}
						    					if (once6 == 0)
						    						schr = "i";
						    					else
						    						schr = "ii";
						    					Text texts17=new Text("  "+schr + ") "  + AddlQry.get(i).getQRY_DESC());
						    					texts17.setFont(font).setFontSize(10.0f);
						    					Paragraph tps17=new Paragraph (texts17).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps17);
						    					once6 = once6 + 1;
						    				}else if (AddlQry.get(i).getQRY_DEF_SLNO().equals("7")) {
						    					secdefqry = fpoSecondDefualtQueryRepo.getsecdefQry("7");
						    					Text texts17=new Text(ch2 + ") " + secdefqry.get(0).getDESCRIPTION() + " "
						    							+ AddlQry.get(i).getQRY_DESC());
						    					texts17.setFont(font).setFontSize(10.0f);
						    					Paragraph tps17=new Paragraph (texts17).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
						    					viewdoc.add(tps17);
						    				}
						    				if ((once5 == 0 || once6 == 0) && once5 != 1)
						    					prn = prn + 1;
						    			}

						    			
						    			log.info("cms 102 in fpo_addl_qry_crpdf");
						    			Text texts18=new Text("3.    The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the parcel. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the parcel on its arrival, will be liable to be assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you.");
				    					texts18.setFont(font).setFontSize(10.0f);
				    					Paragraph tps18=new Paragraph (texts18).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps18);		
				    					
				    					log.info("cms 103 in fpo_addl_qry_crpdf");
				    					Text texts19=new Text("4.    It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the date of  this Document Call letter), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the recipient/addressee consignee.");
				    					texts19.setFont(font).setFontSize(10.0f);
				    					Paragraph tps19=new Paragraph (texts19).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps19);		
				    					
				    					log.info("cms 104 in fpo_addl_qry_crpdf");
				    					Text texts20=new Text("5.    This letter does not confer any right on the recipient/addressee for clearance of the parcel from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the parcel.");
				    					texts20.setFont(font).setFontSize(10.0f);
				    					Paragraph tps20=new Paragraph (texts20).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps20);	
				    					 
				    					log.info("cms 105 in fpo_addl_qry_crpdf");
				    					Text texts21=new Text("6.    Please quote the above Article ID (parcel number) in all your correspondence, if any to be made.");
				    					texts21.setFont(font).setFontSize(10.0f);
				    					Paragraph tps21=new Paragraph (texts21).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps21);	
				    					
				    					log.info("cms 106 in fpo_addl_qry_crpdf");
				    				//	Text texts22=new Text(" 7.    Please reply by post to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.");
				    					Text texts22=new Text(" 7.    Please reply by post / courier / any other mode  to the above mentioned postal address in the header.");
				    					texts22.setFont(fontb).setFontSize(10.0f).setFontColor(Color.BLUE);
				    					Paragraph tps22=new Paragraph (texts22).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps22);	
				    					
				    					  
				    					log.info("cms 107 in fpo_addl_qry_crpdf");

				    					

//				    					System.out.println("dcallno is" + request.getSession().getAttribute("dcallno"));
//				    					String dcall_part = request.getSession().getAttribute("dcallno") == null ? null
//				    							: request.getSession().getAttribute("dcallno").toString().substring(5, 12);

				    					 log.info("cms 108 in fpo_addl_qry_crpdf");

				    					// String url="https://www.postalimport.gov.in/"+dcall_part+"/"+rndno;
//				    					 String url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
//				    						String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
				    					// LoginController.url=url;
//				    					request.getSession().setAttribute("url", url);
//				    					// LoginController.smsurl=smsurl;
//				    					request.getSession().setAttribute("smsurl", smsurl);
				    					 
				    					 
				    					 //to be uncommented for Email and SmS
				    					 
				    					/* log.info("cms 109 in fpo_addl_qry_crpdf");
				    					Text texts23=new Text(url);
				    					texts23.setFont(fontbi).setFontSize(9.0f);
				    					Paragraph tps23=new Paragraph (texts23).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.LEFT).setFontColor(Color.BLUE);;
				    					viewdoc.add(tps23);	*/
				    					
				    					 log.info("cms 110 in fpo_addl_qry_crpdf");
				    					Text texts24=new Text("  ");
				    					texts24.setFont(font).setFontSize(10.0f);
				    					Paragraph tps24=new Paragraph (texts24).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps24);
				    					
				    					 log.info("cms 111 in fpo_addl_qry_crpdf");
				    					Text texts25=new Text("  Yours sincerely, ");
				    					texts25.setFont(font).setFontSize(10.0f);
				    					Paragraph tps25=new Paragraph (texts25).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
				    					viewdoc.add(tps25);
				    					
				    					log.info("cms 112 in fpo_addl_qry_crpdf");
				    					Text texts26=new Text("  ");
				    					texts26.setFont(font).setFontSize(10.0f);
				    					Paragraph tps26=new Paragraph (texts26).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps26);
				    					
				    					log.info("cms 113 in fpo_addl_qry_crpdf");
				    					Text texts27=new Text("  ");
				    					texts27.setFont(font).setFontSize(10.0f);
				    					Paragraph tps27=new Paragraph (texts27).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps27);
				    					
				    					log.info("cms 114 in fpo_addl_qry_crpdf");
				    					String cd = FPOrepost.getoffname(
				    							request.getSession().getAttribute("offId") == null ? null
				    									: request.getSession().getAttribute("offId").toString(),
				    							request.getSession().getAttribute("cuSite") == null ? null
				    									: request.getSession().getAttribute("cuSite").toString(),
				    							request.getSession().getAttribute("role") == null ? null
				    									: request.getSession().getAttribute("role").toString());
				    					
				    					log.info("cms 115 in fpo_addl_qry_crpdf");

				    					Text texts28=new Text(cd);
				    					texts28.setFont(fontb).setFontSize(10.0f);
				    					Paragraph tps28=new Paragraph (texts28).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps28.setFixedLeading(1.0f);
				    					viewdoc.add(tps28);
				    					
				    					log.info("cms 116 in fpo_addl_qry_crpdf");
				    					Text texts29=new Text("AO OF CUSTOMS");
				    					texts29.setFont(font).setFontSize(10.0f);
				    					Paragraph tps29=new Paragraph (texts29).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps29.setFixedLeading(1.0f);

				    					viewdoc.add(tps29);
				    					 log.info("cms 117 in fpo_addl_qry_crpdf");
				    					

				    					Text texts30=new Text("PAD, " + padcity);
				    					texts30.setFont(fontb).setFontSize(10.0f);
				    					Paragraph tps30=new Paragraph (texts30).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps30.setFixedLeading(1.0f);
				    					viewdoc.add(tps30);
				    					
				    					log.info("cms 118 in fpo_addl_qry_crpdf");

				    					Text texts31=new Text("DATE : " + printcurrentDateTime);
				    					texts31.setFont(font).setFontSize(10.0f);
				    					Paragraph tps31=new Paragraph (texts31).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
//				    					tps31.setFixedLeading(1.0f);
				    					viewdoc.add(tps31);
				    					
				    					log.info("cms 119 in fpo_addl_qry_crpdf");
				    					Text texts32=new Text("  ");
				    					texts32.setFont(font).setFontSize(10.0f);
				    					Paragraph tps32=new Paragraph (texts32).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps32);
				    					
				    					log.info("cms 120 in fpo_addl_qry_crpdf");
				    					Text texts33=new Text("  ");
				    					texts33.setFont(font).setFontSize(10.0f);
				    					Paragraph tps33=new Paragraph (texts33).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED);
				    					viewdoc.add(tps33);
				    					
				    					
				    					//to be uncommented for Email and SmS
				    				/*	log.info("cms 121 in fpo_addl_qry_crpdf");
				    					Text texts34=new Text(	"     Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  ");
				    					texts34.setFont(fontli).setFontSize(9.0f);
				    					Paragraph tps34=new Paragraph (texts34).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.LEFT);
				    					viewdoc.add(tps34);
				    					viewdoc.add(image1); */
				    					
				    					log.info("cms 122 in fpo_addl_qry_crpdf");
				    					viewdoc.close();
				    					viewStream.close();
				    					
				    					System.out.println("Pdf created successfully.");
				    					
				    					log.info("cms 123 in fpo_addl_qry_crpdf");
				    					String to = fpoMainData.get(0).getRECP_EMAILID();
				    					// LoginController.to=to;
				    					request.getSession().setAttribute("to", to);
				    					// LoginController.tomailid = AddlQry.get(AddlQry.size()-1).QRY_EMAILID;
				    					request.getSession().setAttribute("tomailid", AddlQry.get(AddlQry.size() - 1).QRY_EMAILID);

				    					String toEnteredMobileNumber = fpoQueryRepo.getMobileNumberaddlqry(cinNo);
				    					String toEnteredMailid = fpoQueryRepo.getemailaddlqry(cinNo);
				    					if (toEnteredMobileNumber == null)
				    						fpoQueryRepo.getMobileNumber(cinNo, fpoQueryRepo.getMaxQueryNo());
				    					if (toEnteredMailid == null)
				    						fpoQueryRepo.getemail(cinNo, fpoQueryRepo.getMaxQueryNo());
				    					// LoginController.toMobileNumber=fpoMainData.get(0).getRECP_PHONE();
				    					// LoginController.toEnteredMobileNumber=AddlQry.get(AddlQry.size()-1).QRY_MOBILENO;
				    					request.getSession().setAttribute("toEnteredMobileNumber", toEnteredMobileNumber);
				    					request.getSession().setAttribute("toEnteredMailid", toEnteredMailid);
				    					request.getSession().setAttribute("toMobileNumber", fpoMainData.get(0).getRECP_PHONE());
				    					request.getSession().setAttribute("toMailiD", fpoMainData.get(0).getRECP_EMAILID());
				    					 log.info("cms 89 in fpo_addl_qry_crpdf");
				    					DCALLQRY_GEN dcallqry = new DCALLQRY_GEN();
				    					dcallqry.setItem_ID(request.getSession().getAttribute("itemid") == null ? null
				    							: request.getSession().getAttribute("itemid").toString());
				    					dcallqry.setDcallno(request.getSession().getAttribute("dcallno") == null ? null
				    							: request.getSession().getAttribute("dcallno").toString());
				    					java.util.Date curdt = new java.util.Date();
				    					dcallqry.setGen_dt(curdt);
				    					dcallqry.setGenurl(request.getSession().getAttribute("url") == null ? null
				    							: request.getSession().getAttribute("url").toString());
				    					dcallqry.setCussite(request.getSession().getAttribute("cuSite") == null ? null
				    							: request.getSession().getAttribute("cuSite").toString());
				    					dcallqry.setRecp_name(request.getSession().getAttribute("recpname") == null ? null
				    							: request.getSession().getAttribute("recpname").toString());
				    					dcallqry.setGen_filename(request.getSession().getAttribute("filename") == null ? null
				    							: request.getSession().getAttribute("filename").toString());
				    					dcallqry.setCinno(cinNo);
				    					dcallqry.setMobile_1(request.getSession().getAttribute("toMobileNumber") == null ? null
				    							: request.getSession().getAttribute("toMobileNumber").toString());
				    					// dcallqry.setMobile_2(AddlQry.get(AddlQry.size()-1).QRY_MOBILENO);
				    					dcallqry.setMobile_2(toEnteredMobileNumber);
				    					DcallQRYREPO.save(dcallqry);	
				}
		catch (Exception e) {
	e.printStackTrace();	
						}
	}
	
	public void fpo_qry_crpdf(String cinNo, String que, String din,String others, HttpSession session, HttpServletRequest request) {
	
		String str="";
	    log.info("cms 9 in fpo_qry_crpdf");
	    	 	if (que.equals("E"))
	    	 		str="EAD";
	    	 	else if (que.equals("P"))
	    	 		str="AAF";
	    log.info("cms 10 in fpo_qry_crpdf");
	    log.info("str is " + str);
	    	 	
		
		  Random rnd = new Random();
			int rndno = rnd.nextInt(9999999);
			System.out.println(rndno);
			log.info("cms 1 in fpo_qry_crpdf");
		

			// String url="https://www.postalimport.gov.in/"+dcall_part+"/"+rndno;
			
			// LoginController.url=url;
			
		
			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
			String getRemarks = fpoQueryRepo.getRemarks(cinNo, fpoQueryRepo.getMaxQueryNo());
			String tomailid = fpoQueryRepo.getemail(cinNo, fpoQueryRepo.getMaxQueryNo());
			String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo,
					fpoQueryRepo.getMaxQueryNo());
			String DocName = fpoQueryRepo.getOthDocName(cinNo, fpoQueryRepo.getMaxQueryNo());	

			List<String> defualtQueryList = getSpecifiedDefualtQuery(DefualtQuery,DocName);
			log.info("cms 2 in fpo_qry_crpdf");
//		    LoginController.tomailid = tomailid;
			 request.getSession().setAttribute("tomailid", tomailid);
//		    LoginController.recpname = fpoMainData.get(0).getRECP_NAME();
			 request.getSession().setAttribute("recpname", fpoMainData.get(0).getRECP_NAME());
//			
			 log.info("cms 3 in fpo_qry_crpdf");
			List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo);
			List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo);
			log.info("cms 4 in fpo_qry_crpdf");
		    DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		    String currentDateTime = dateFormatter.format(new Date());
		    DateFormat printdateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		    String printcurrentDateTime = printdateFormatter.format(new Date());
		    String cusite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		    String itemid = DINList.get(0).getItemid();
		    request.getSession().setAttribute("itemid", itemid);
	    	 request.getSession().setAttribute("dinno", din);
	    	 String dcallno = str+DINList.get(0).getUniqueNo();
	    		request.getSession().setAttribute("dcallno", str+DINList.get(0).getUniqueNo());

	    		System.out.println("dcallno is" + request.getSession().getAttribute("dcallno"));
				String dcall_part = request.getSession().getAttribute("dcallno") == null ? null
						: request.getSession().getAttribute("dcallno").toString().substring(5, 12);
				
				String url = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
				String smsurl = "https://postimpuat.cbic.gov.in/dcall/qry/" + dcall_part + "/" + rndno;
				
				request.getSession().setAttribute("url", url);
				// LoginController.smsurl=smsurl;
				request.getSession().setAttribute("smsurl", smsurl);
			try {
			  log.info("cms 5 in fpo_qry_crpdf");
			

			    String path1 = FPOrepost.getdcallqrypath() + itemid + currentDateTime + ".pdf";
				File filename=new File(path1);

			 	//LoginController.filename = filename;
		    	 request.getSession().setAttribute("filename", path1);
		    	 
		    	 OutputStream outputStream = new FileOutputStream(filename);
			    
		    	
		    	 log.info("cms 6 in fpo_qry_crpdf");

		
		    	 PdfWriter pdfWriter1=new PdfWriter(outputStream);
		    	 PdfDocument pdfDocument1 = new PdfDocument(pdfWriter1);
		    	 pdfDocument1.setDefaultPageSize(PageSize.A4);
		    	 pdfDocument1.addNewPage();
		    	 Document document = new Document(pdfDocument1);
	       

		    log.info("cms 7 in fpo_qry_crpdf");
		    	 //code for adding image CBECnew as wateremark
//		    	 	String imgsrc = "D:\\VISHWANATH S G\\APIS\\fpouat_jan07\\src\\main\\resources\\static\\images\\CBECnew.png";
//		    	 	ImageData imageData = ImageDataFactory.create(imgsrc);
		    ImageData imageData = ImageDataFactory.create(FPOrepost.getimagespath() + "CBECnew.png");	 
		    Image image1 = new Image(imageData);
		    	 	image1.setFixedPosition(pdfDocument1.getDefaultPageSize().getWidth()/2-140,pdfDocument1.getDefaultPageSize().getHeight()/2-130);
		    	 	image1.setOpacity(0.10f);
  
		    	 	document.add(image1);
		    	 	
		    log.info("cms 8 in fpo_qry_crpdf");
		    	 

		    	 	 
		    	    log.info("cms 11 in fpo_qry_crpdf");
		    log.info("dallno is " + dcallno);	        
		    	 	//  LoginController.dcallno = str+DINList.get(0).getUniqueNo();
		    	 	request.getSession().setAttribute("dcallno", str+DINList.get(0).getUniqueNo());


		    	 	 log.info("cms 12 in fpo_qry_crpdf");

		    	 	PdfFont fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
		    	 	PdfFont fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
		    	 	PdfFont fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
	
	
		    	 	Text text = new Text("DCall Letter : "+dcallno).setFont(fontb).setFontSize(10.0f);
		    	 	Paragraph hdi = new Paragraph();
		    	 	hdi.add(new Tab());
		    	 	hdi.addTabStops(new TabStop(1000,TabAlignment.RIGHT));
		    	 	hdi.add(text);
		    	 	document.add(hdi);
		    	 	
		    	 	log.info("cms 13 in fpo_qry_crpdf");
		    	 	
		    	 	ImageData imageDataicon = ImageDataFactory.create(FPOrepost.getimagespath() + "CBEC.png");
		    	 	Image image2 = new Image(imageDataicon);
		    	 	image2.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	image2.scaleToFit(80, 75);
		    	 	document.add(image2);
		    	 	
		    	 	log.info("cms 14 in fpo_qry_crpdf");
		    	 	
		    	 	String addr="";
		    	 	String siteemail="";
		    	 	String workhours="";
		    	 	

		    	 	PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		    	 	Text text1 = new Text("INDIAN CUSTOMS").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd = new Paragraph();
		    	 	hd.setFixedLeading(1.0f);

		    	 	//hd.setMultipliedLeading(1.0f);
		    	 	hd.add(text1).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd);
		    	 	
		    	 	log.info("cms 15 in fpo_qry_crpdf");
		    	 	Text text2 = new Text("POSTAL APPRAISING DEPARTMENT").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd1 =new Paragraph();
		    	 	hd1.setFixedLeading(1.0f);
		    	 	hd1.add(text2).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd1);
		    	 	
		    	 	//document2.add(hd1);


		    	 	log.info("cms 16 in fpo_qry_crpdf");
		    	 	Text text3 = new Text("FOREIGN POST OFFICE").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd2 =new Paragraph();
		    	 	hd2.setFixedLeading(1.0f);
		    	 	hd2.add(text3).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd2);
		    	 	
		    	 	Text text3a = new Text("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ").setFont(font1).setFontSize(8.0f);
		    	 	Paragraph hd3a=new Paragraph();
		    	 	hd3a.setFixedLeading(1.0f);
		    	 	hd3a.add(text3a).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd3a);
	
		    	 	log.info("cms 17 in fpo_qry_crpdf");
		    	 	Text text4 = new Text("Address : "+fpoQueryRepo.getfpositeaddr(cusite).toString()).setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd3 =new Paragraph();
		    	 	hd3.setFixedLeading(1.0f);
		    	 	hd3.add(text4).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd3);

		    	 	
		    	 	log.info("cms 18 in fpo_qry_crpdf");
//		    	 	Text text5 = new Text("Email : "+fpoQueryRepo.getfpositemail(cusite)).setFont(font1).setFontSize(10.0f);
//		    	 	Paragraph hd4 =new Paragraph();
//		    	 	hd4.setFixedLeading(1.0f);
//		    	 	hd4.add(text5).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
//		    	 	document.add(hd4);

		    	 	log.info("cms 19 in fpo_qry_crpdf");
		    	 	Text text6 = new Text("Contact No. : " + fpoQueryRepo.getfpositeph(cusite)).setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd5 =new Paragraph();
		    	 	hd5.setFixedLeading(1.0f);
		    	 	hd5.add(text6).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd5);

		    	 	log.info("cms 20 in fpo_qry_crpdf");
		    	 	Text text7 = new Text("Visiting Hours : " + fpoQueryRepo.getfpositewh(cusite)).setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd6 =new Paragraph();
		    	 	hd6.setFixedLeading(1.0f);
		    	 	hd6.add(text7).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(hd6);

		    	 	log.info("cms 21 in fpo_qry_crpdf");
		    	 	Text text8 = new Text("  ").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph hd7 = new Paragraph(text8);
		    	 	hd7.add(text8);
		    	 	document.add(hd7);

		    	 	float width[]= {300,300};
		    	 	Table table=new Table(width);


		    	 	log.info("cms 22 in fpo_qry_crpdf");
		    	 	Text textt1=new Text(" ").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp1=new Paragraph(textt1);
		    	 	tp1.setFixedLeading(1.0f);

		    	 	Text textt2 = new Text("ON INDIA GOVERNMENT SERVICE").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp2 = new Paragraph(textt2);
		    	 	tp2.setFixedLeading(1.0f);

		    	 	Text textt3 = new Text("To").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp3 = new Paragraph(textt3);
		    	 	tp3.setFixedLeading(1.0f);

		    	 	Text textt4 = new Text("By SPEED POST").setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp4 = new Paragraph(textt4);
		    	 	tp4.setFixedLeading(1.0f);



		    	 	table.addCell(new Cell().setHeight(11.0f).add(tp1).setBorder(Border.NO_BORDER));
		    	 	table.addCell(new Cell().setHeight(11.0f).add(tp2).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
		    	 	table.addCell(new Cell().setHeight(11.0f).add(tp3).setBorder(Border.NO_BORDER));
		    	 	table.addCell(new Cell().setHeight(11.0f).add(tp4).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

		    	 	document.add(table);

		    	 	 log.info("cms 23 in fpo_qry_crpdf");
		    	 	float width1[]= {350,150};
		    	 	Table table1=new Table(width1);
		    	 	table1.setHorizontalAlignment(HorizontalAlignment.CENTER);

		    	 
		
		    	 	String str1=fpoMainData.get(0).getRECP_NAME(); 
		    	 	Text textt5 = new Text(str1);
		    	 	textt5.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp5 = new Paragraph(textt5);
		    	 	tp5.setFixedLeading(1.0f);
		    	 	table1.addCell(new Cell().setHeight(11.0f).add(tp5).setBorder(Border.NO_BORDER));
		    	 	 
		    	 	log.info("cms 24 in fpo_qry_crpdf");
		    	 	 
		    	 	String strt="  ";
		    	 	Text textt6 = new Text(strt);
		    	 	textt6.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp6 = new Paragraph(textt6);
		    	 	tp6.setFixedLeading(1.0f);
		    	 	table1.addCell(new Cell().setHeight(11.0f).add(tp6).setBorder(Border.NO_BORDER));
		    	 	
		    	 	log.info("cms 25 in fpo_qry_crpdf");
		    	 
						
					
		    	 	String str2=fpoMainData.get(0).getRECP_ADD1();
		    	 	Text textt7 = new Text(str2);
		    	 	textt7.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp7 = new Paragraph(textt7);
		    	 	tp7.setFixedLeading(1.0f);
		    	 	table1.addCell(new Cell().setHeight(11.0f).add(tp7).setBorder(Border.NO_BORDER));
		    	 	
		    	 	 log.info("cms 26 in fpo_qry_crpdf");
		    	 	String strp="  ";
		    	 	Text textt8 = new Text(strp);
		    	 	textt8.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp8 = new Paragraph(textt8);
		    	 	tp8.setFixedLeading(1.0f);
		    	 	table1.addCell(new Cell().setHeight(11.0f).add(tp8).setBorder(Border.NO_BORDER));
		    	 	
		    	 	 log.info("cms 27 in fpo_qry_crpdf");
		    	 	if (fpoMainData.get(0).getRECP_ADD2()!=null) {
	
		    	 		String str3=fpoMainData.get(0).getRECP_ADD2();
		    	 		Text textt9 = new Text(str3);
		    	 		textt9.setFont(font1).setFontSize(10.0f);
		    	 			Paragraph tp9 = new Paragraph(textt9);
		    	 			tp9.setFixedLeading(1.0f);
		    	 			table1.addCell(new Cell().setHeight(11.0f).add(tp9).setBorder(Border.NO_BORDER));


		    	 			String str90="  ";
		    	 			Text textt10 = new Text(str90);
		    	 			textt10.setFont(font1).setFontSize(10.0f);
		    	 			Paragraph tp10 = new Paragraph(textt10);
		    	 			tp10.setFixedLeading(1.0f);
		    	 			table1.addCell(new Cell().setHeight(11.0f).add(tp10).setBorder(Border.NO_BORDER));

		    	 	}
		    	 	 log.info("cms 28 in fpo_qry_crpdf");
		    	 	 
		    	 String padcity = FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

		    	 if (padcity!=null) {
		    		 String str4 =fpoMainData.get(0).getRECP_CITY();
		    		 Text textt11 = new Text(str4);
		    		 textt11.setFont(font1).setFontSize(10.0f);
		    		 Paragraph tp11 = new Paragraph(textt11);
		    		 tp11.setFixedLeading(1.0f);
		    		 table1.addCell(new Cell().setHeight(11.0f).add(tp11).setBorder(Border.NO_BORDER));
		    	 
		    	 
		    	 log.info("cms 29 in fpo_qry_crpdf");
		    	 String str09="  ";
		    	 Text textt12 = new Text(str09);
		    	 textt12.setFont(font1).setFontSize(10.0f);
		    	 Paragraph tp12 = new Paragraph(textt12);
		    	 tp12.setFixedLeading(1.0f);
		    	 table1.addCell(new Cell().setHeight(11.0f).add(tp12).setBorder(Border.NO_BORDER));
		    	 } 
		    	 log.info("cms 30 in fpo_qry_crpdf");
		    	 if (fpoMainData.get(0).getRECP_ZIP()!=null) {
					
				
		    	 String str5=fpoMainData.get(0).getRECP_ZIP();
		    	 Text textt13 = new Text(str5);
		    	 textt13.setFont(font1).setFontSize(10.0f);
		    	 Paragraph tp13 = new Paragraph(textt13);
		    	 tp13.setFixedLeading(1.0f);
		    	 table1.addCell(new Cell().setHeight(11.0f).add(tp13).setBorder(Border.NO_BORDER));
		    	   	 
		    	 log.info("cms 31 in fpo_qry_crpdf");
		    	 String str89="   ";
		    	 Text textt14 = new Text(str89);
		    	 textt14.setFont(font1).setFontSize(10.0f);
		    	 Paragraph tp14 = new Paragraph(textt14);
		    	 tp14.setFixedLeading(1.0f);
		    	 	table1.addCell(new Cell().setHeight(11.0f).add(tp14).setBorder(Border.NO_BORDER));
		    	 }	
		    	 	 log.info("cms 32 in fpo_qry_crpdf");

		    	 	if (fpoMainData.get(0).getRECP_PHONE()!=null) {
	

		    	 		String str6=fpoMainData.get(0).getRECP_PHONE();
		    	 		Text textt15 = new Text("Ph:"+str6);
		    	 		textt15.setFont(font1).setFontSize(10.0f);
		    	 		Paragraph tp15 = new Paragraph(textt15);
		    	 		tp15.setFixedLeading(1.0f);
		    	 		table1.addCell(new Cell().setHeight(11.0f).add(tp15).setBorder(Border.NO_BORDER));

		    	 	}  

		    	 	 log.info("cms 33 in fpo_qry_crpdf");
		    	 	document.add(table1);

		    	 	log.info("cms 34 in fpo_qry_crpdf");
		    	 	Text text16=new Text("  ");
		    	 	Paragraph tp16 = new Paragraph(text16);
		    	 	document.add(tp16);

		    	 	log.info("cms 35 in fpo_qry_crpdf");
		    	 	Text text17=new Text("Sir/Madam");
		    	 	text17.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp17=new Paragraph (text17);
		    	 	tp17.setFixedLeading(1.0f);
		    	 	document.add(tp17);

		    	 	log.info("cms 36 in fpo_qry_crpdf");
		    	 	Text text18=new Text("Sub: IMPORT - Foreign Post Article ID "+fpoMainData.get(0).getITEM_ID()+"-reg.");
		    	 	text18.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp18=new Paragraph (text18);
		    	 	tp18.setFixedLeading(1.0f);
		    	 	tp18.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 	document.add(tp18);

		    	 	log.info("cms 37 in fpo_qry_crpdf");
		    	 	Text text19=new Text("*****");
		    	 	text19.setFont(font1).setFontSize(10.0f);
		    	 	Paragraph tp19=new Paragraph (text19);
		    	 	tp19.setFixedLeading(1.0f);
		    	 		tp19.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		    	 		document.add(tp19);

		    	 		log.info("cms 38 in fpo_qry_crpdf");

		    	 		if (que=="E" || que == "N") 
		    	 				str="1. The  above  mentioned  Import  Article  addressed to you is expected to arrive at the "+ padcity + " FPO and is being taken up for \n"+"\n"+ " assessment by the Indian Customs.";
		    	 		else if (que == "P") 
		    	 			str="1. The  above  mentioned  Import  Article  addressed to you is received at the "+ padcity + " FPO and is being examined by the Indian Customs.";
	
		    	 		Text text20=new Text(str);
		    	 		text20.setFont(font1).setFontSize(10.0f);
		    	 		Paragraph tp20=new Paragraph (text20).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
		    	 		tp20.setFixedLeading(8.0f);
		    	 		document.add(tp20);

		    	 		log.info("cms 39 in fpo_qry_crpdf");
		    	 		Text text21=new Text("2. In this regard, it is requested to provide clarifications on the queries raised by the department and provide the documents called \n"+"\n"+ "for, as mentioned in the following tables.");
		    	 		text21.setFont(font1).setFontSize(10.0f);
		    	 		Paragraph tp21=new Paragraph (text21).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
		    	 		tp21.setFixedLeading(8.0f);
		    	 		document.add(tp21);
		    	 		
		    	 		if (!(DINList.get(0).getRemarks() == null)) {
		    	 		
		    	 		log.info("cms 40 in fpo_qry_crpdf");
		    	 		float width2[]= {600};
		    	 		Table table2 = new Table(width2);
		    	 		Text textt22 = new Text("Customs General Query").setFont(fontb).setFontSize(10.0f);
		    	 		Paragraph tp22 = new Paragraph(textt22);	   
		    	 		table2.addCell(new Cell().setHeight(13.0f).add(tp22).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
		   //		   tp3.setFixedLeading(1.0f);
	
		    	 		log.info("cms 41 in fpo_qry_crpdf");
		    	 	//	System.out.println(DINList.get(0).getRemarks());
		    	
					
				
		    	 		Text textt23 = new Text(DINList.get(0).getRemarks()).setFont(font1).setFontSize(10.0f);
		    	 		Paragraph tp23 = new Paragraph(textt23);
		    	 		table2.addCell(new Cell().setHeight(13.0f).add(tp23).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		    	 		document.add(table2);
		    	  
		    	 		}
					
		    	 		log.info("cms 42 in fpo_qry_crpdf");
		    	 		Text textt24=new Text("   ").setFont(font1).setFontSize(10.0f);
		    	 		Paragraph tp24 = new Paragraph(textt24);
		    	 		document.add(tp24);
		    	 		
		    	 		if (getAllFpoQuery.get(0).getQRY() != null) {
		    	 		
		    	 		log.info("cms 43 in fpo_qry_crpdf");
		    	 		float width3[]= {200,200,200};
		    	 		Table table3 = new Table(width3);
		    	 		Text textt31 = new Text("Itemwise Query").setFont(fontb).setFontSize(10.0f);
		    	 		Paragraph tp31 = new Paragraph(textt31);	   
		    	 		table3.addCell(new Cell(0,3).setHeight(13.0f).add(tp31).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
		   //		   tp3.setFixedLeading(1.0f);
		    	 		
		    	 		log.info("cms 44 in fpo_qry_crpdf");
		    	 		Text textt32 = new Text("Item No").setFont(fontb).setFontSize(10.0f);
		    	 		Paragraph tp32 = new Paragraph(textt32);
		    	 		table3.addCell(new Cell().setHeight(13.0f).add(tp32).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		    	 		
		    	 		log.info("cms 45 in fpo_qry_crpdf");
		    	 		Text textt33 = new Text("Item Description").setFont(fontb).setFontSize(10.0f);
		    	 		Paragraph tp33 = new Paragraph(textt33);
		    	 		table3.addCell(new Cell().setHeight(13.0f).add(tp33).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		   
		    	 		log.info("cms 46 in fpo_qry_crpdf");
		    	 		Text textt34 = new Text("Query").setFont(fontb).setFontSize(10.0f);
		    	 		Paragraph tp34 = new Paragraph(textt34);
		    	 		table3.addCell(new Cell().setHeight(13.0f).add(tp34).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		   
		    	 		log.info("cms 47 in fpo_qry_crpdf");
		    	 		for (int i = 0; i < getAllFpoQuery.size(); i++)  
		    	 		{
		    	 			if (getAllFpoQuery.get(i).getQRY() != null)
		    	 			{
		    	 				Text textt35 = new Text(getAllFpoQuery.get(i).getITEM_NO().toString()).setFont(font1).setFontSize(10.0f);
		    	 				Paragraph tp35 = new Paragraph(textt35);
		    	 				table3.addCell(new Cell().setHeight(13.0f).add(tp35).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
	              

		    	 				Text textt36 = new Text(getAllFpoQuery.get(i).getItemDesc()).setFont(font1).setFontSize(10.0f);
		    	 				Paragraph tp36 = new Paragraph(textt36);
		    	 				table3.addCell(new Cell().setHeight(13.0f).add(tp36).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
          
		    	 				Text textt37 = new Text(getAllFpoQuery.get(i).getQRY()).setFont(font1).setFontSize(10.0f);
		    	 				Paragraph tp37 = new Paragraph(textt37);
		    	 				table3.addCell(new Cell().setHeight(13.0f).add(tp37).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
          
	              }
		   }
		   document.add(table3);
	}
		   log.info("cms 48 in fpo_qry_crpdf");
		   
		   Text textt25=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp25 = new Paragraph(textt25);
		   document.add(tp25);
		   log.info("cms 49 in fpo_qry_crpdf");
		   
		   float width4[]= {50,550};
		   Table table4 =new Table(width4);
		   Text textt41=new Text("SlNo.").setFont(fontb).setFontSize(10.0f);
		   Paragraph tp41=new Paragraph(textt41);
		   table4.addCell(new Cell().setHeight(13.0f).add(tp41).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
		   
		   log.info("cms 50 in fpo_qry_crpdf");
		   Text textt42=new Text("Document required from recipient").setFont(fontb).setFontSize(10.0f);
		   Paragraph tp42=new Paragraph(textt42);
		   table4.addCell(new Cell().setHeight(13.0f).add(tp42).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
		   
		   log.info("cms 51 in fpo_qry_crpdf");
		   int k=1;
		   for (int i = 0; i < defualtQueryList.size(); i++) {
			   
		   
		   Text textt43=new Text(" "+k+" ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp43=new Paragraph(textt43);
		   table4.addCell(new Cell().setHeight(13.0f).add(tp43).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		  
		   log.info("cms 52 in fpo_qry_crpdf");
		   if (defualtQueryList.get(i).toString()!=null) {
		   Text textt44=new Text(defualtQueryList.get(i).toString()).setFont(font1).setFontSize(10.0f);
		   Paragraph tp44=new Paragraph(textt44);
		   table4.addCell(new Cell().setHeight(28.0f).add(tp44).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		   
		   k++;
		   		}
		   }
		   log.info("cms 53 in fpo_qry_crpdf");
		   document.add(table4);

		   log.info("cms 54 in fpo_qry_crpdf");
		   Text textt45=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp45 = new Paragraph(textt45);
		   document.add(tp45);
		  
		   log.info("cms 55 in fpo_qry_crpdf");		   
		   Text textt46=new Text(" 3. The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the Article. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the Article on its arrival, will be liable to be  assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you.").setFont(font1).setFontSize(10.0f);
		   Paragraph tp46 = new Paragraph(textt46).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
		   document.add(tp46);

		   log.info("cms 56 in fpo_qry_crpdf");
		   Text textt47=new Text(" 4. It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the Document Call letter date), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the addressee consignee. ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp47 = new Paragraph(textt47).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
//		  tp47.setMargin(2.0f);
		   document.add(tp47);

		   log.info("cms 57 in fpo_qry_crpdf");
		   Text textt48=new Text(" 5. This letter does not confer any right on the addressee for clearance of the Article from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the Article. ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp48 = new Paragraph(textt48).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
		   document.add(tp48);
		   
		   log.info("cms 58 in fpo_qry_crpdf");
		   Text textt49=new Text(" 6. The Department reserves its rights to initiate action as deemed fit against the consignee/recipient or the person who replies, in cases where any suppression of fact,wrong claims, submission of fake document or misinformation etc is noticed.").setFont(font1).setFontSize(10.0f);
		   Paragraph tp49 = new Paragraph(textt49).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
		   document.add(tp49);
		  
		   log.info("cms 59 in fpo_qry_crpdf");
		   Text textt50=new Text(" 7. Please reply by post to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only. ").setFont(font1).setFontSize(10.0f).setFontColor(Color.BLUE);
		   Paragraph tp50 = new Paragraph(textt50).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
		   document.add(tp50);
		   
		   log.info("cms 60 in fpo_qry_crpdf");
        Text textt51=new Text(url).setFont(fontbi).setFontSize(9.0f).setFontColor(Color.BLUE);
		   Paragraph tp51 = new Paragraph(textt51);
		   document.add(tp51);
		   log.info("cms 61 in fpo_qry_crpdf");
		   
		  Text textt52=new Text("Yours sincerely, ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp52 = new Paragraph(textt52).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
		   document.add(tp52);

		   log.info("cms 62 in fpo_qry_crpdf");
		   Text textt53=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp53 = new Paragraph(textt53);
		   document.add(tp53);

		   log.info("cms 63 in fpo_qry_crpdf");
		   Text textt54=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp54 = new Paragraph(textt54);
		   document.add(tp54);
		   
		   log.info("cms 64 in fpo_qry_crpdf");
		   
		   String cd = FPOrepost.getoffname(
					request.getSession().getAttribute("offId") == null ? null
							: request.getSession().getAttribute("offId").toString(),
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString(),
					request.getSession().getAttribute("role") == null ? null
							: request.getSession().getAttribute("role").toString());

		   log.info("cms 65 in fpo_qry_crpdf");
		   Text textt55=new Text(cd).setFont(fontb).setFontSize(10.0f);
		   Paragraph tp55 = new Paragraph(textt55).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
		   document.add(tp55);

		   log.info("cms 66 in fpo_qry_crpdf");
		   Text textt56=new Text("AO OF CUSTOMS").setFont(font1).setFontSize(10.0f);
		   Paragraph tp56 = new Paragraph(textt56).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
		   document.add(tp56);

		   log.info("cms 67 in fpo_qry_crpdf");
		   Text textt57=new Text("PAD,"+padcity).setFont(fontb).setFontSize(10.0f);
		   Paragraph tp57 = new Paragraph(textt57).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
		   document.add(tp57);
		   
		   log.info("cms 68 in fpo_qry_crpdf");
		   Text textt58=new Text(" DATE : "+printcurrentDateTime).setFont(font1).setFontSize(10.0f);
		   Paragraph tp58 = new Paragraph(textt58).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
		   document.add(tp58);

		   log.info("cms 69 in fpo_qry_crpdf");
		   Text textt59=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp59 = new Paragraph(textt59);
		   document.add(tp59);

		   log.info("cms 70 in fpo_qry_crpdf");
		   Text textt60=new Text("   ").setFont(font1).setFontSize(10.0f);
		   Paragraph tp60 = new Paragraph(textt60);
		   document.add(tp60);

		   log.info("cms 71 in fpo_qry_crpdf");
		   Text textt61=new Text("       Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  ").setFont(fontli).setFontSize(7.0f);
		   Paragraph tp61 = new Paragraph(textt61);
		   document.add(tp61);

		   log.info("cms 72 in fpo_qry_crpdf");
		   document.add(image1);
	
		   log.info("cms 73 in fpo_qry_crpdf");
		 
		   document.close();
		   outputStream.close();
		   
		   log.info("cms 74 in fpo_qry_crpdf");
		   String USER = itemid.substring(8, 13);
		   PDDocument pdd = PDDocument.load(filename);
	  
		   log.info("cms 75 in fpo_qry_crpdf");
		   AccessPermission ap = new AccessPermission();
		   StandardProtectionPolicy stpp = new StandardProtectionPolicy(USER ,USER , ap);
		   stpp.setEncryptionKeyLength(128);
		   stpp.setPermissions(ap);
		   pdd.protect(stpp);
		   pdd.save(filename); 	 // save the document
		   pdd.close();
		   log.info("cms 76 in fpo_qry_crpdf");
	 
		} 
			
    catch (Exception e) {
	e.printStackTrace();  
}
		
		try {
			 log.info("cms 77 in fpo_qry_crpdf");
		
			    String path2 = FPOrepost.getdcallqryviewpath()+itemid+currentDateTime+".pdf";
			    File viewfilename=new File(path2);

			    OutputStream viewStream = 	new FileOutputStream(viewfilename);
			    			

			    PdfWriter pdfWriter2=new PdfWriter(viewStream);
			    PdfDocument pdfDocument2 = new PdfDocument(pdfWriter2);
			    pdfDocument2.setDefaultPageSize(PageSize.A4);
			    pdfDocument2.addNewPage();
			    Document viewdoc = new Document(pdfDocument2);
	   	 log.info("cms 78 in fpo_qry_crpdf");
	   	 //image path need to be given according to local storage
	   	 	//	ImageData imageData = ImageDataFactory.create(imgsrc);
	     ImageData imageData = ImageDataFactory.create(FPOrepost.getimagespath() + "CBECnew.png");		
	   	 Image image2 = new Image(imageData);

	   	 				image2.setFixedPosition(pdfDocument2.getDefaultPageSize().getWidth()/2-140,pdfDocument2.getDefaultPageSize().getHeight()/2-130);
	   	 				image2.setOpacity(0.10f);
	   	 				viewdoc.add(image2);
		 log.info("cms 79 in fpo_qry_crpdf");
		
//		String str="";
//		    log.info("cms 80 in fpo_qry_crpdf");
//		    if (que.equals("E"))
//		       str="EAD";
//		    else if (que.equals("P"))
//		       str="AAF";
//		    log.info("cms 81 in fpo_qry_crpdf");
//		    log.info("str is " + str);
//		    String dcallno = str+DINList.get(0).getUniqueNo();	 
		    log.info("cms 82 in fpo_qry_crpdf");
//		    log.info("dallno is " + dcallno);	        
//		     request.getSession().setAttribute("dcallno", str+DINList.get(0).getUniqueNo());

		PdfFont fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
		 	PdfFont fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
		 	PdfFont fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
		 	
			 log.info("cms 83 in fpo_qry_crpdf");
		 	Text text = new Text("DCall Letter : "+dcallno).setFont(fontb).setFontSize(10.0f);
		 	Paragraph hdi = new Paragraph();
		  	hdi.add(new Tab());
		  	hdi.addTabStops(new TabStop(1000,TabAlignment.RIGHT));
		  	hdi.add(text);
		  	viewdoc.add(hdi);
			
		  	log.info("cms 84 in fpo_qry_crpdf");
		  		ImageData imageDataicon = ImageDataFactory.create(FPOrepost.getimagespath() + "CBEC.png");
		  		Image image3 = new Image(imageDataicon);
		  		image3.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  		image3.scaleToFit(80, 75);
		  		viewdoc.add(image3);
		  		
		  	log.info("cms 85 in fpo_qry_crpdf");
		  	String addr="";
		  	String siteemail="";
		  	String workhours="";
		  	
		  log.info("cms 86 in fpo_qry_crpdf");
		  PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		  Text text1 = new Text("INDIAN CUSTOMS").setFont(font1).setFontSize(10.0f);
		  Paragraph hd = new Paragraph();
		  hd.setFixedLeading(1.0f);
		  hd.add(text1).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd);
		  
		  log.info("cms 87 in fpo_qry_crpdf");
		  Text text2 = new Text("POSTAL APPRAISING DEPARTMENT").setFont(font1).setFontSize(10.0f);
		  Paragraph hd1 =new Paragraph();
		  hd1.setFixedLeading(1.0f);
		  hd1.add(text2).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd1);

		  
		  log.info("cms 88 in fpo_qry_crpdf");
		  Text text3 = new Text("FOREIGN POST OFFICE").setFont(font1).setFontSize(10.0f);
		  Paragraph hd2 =new Paragraph();
		  hd2.setFixedLeading(1.0f);
		  hd2.add(text3).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd2);
		  
		  Text text3a = new Text("CBIC, Dept. of Revenue, Ministry of Finance, Govt. of India. ").setFont(font1).setFontSize(8.0f);
	  	 	Paragraph hd3a=new Paragraph();
	  	 	hd3a.setFixedLeading(1.0f);
	  	 	hd3a.add(text3a).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
	  	 	viewdoc.add(hd3a);
			
		  log.info("cms 89 in fpo_qry_crpdf");
		  Text text4 = new Text("Address : " + fpoQueryRepo.getfpositeaddr(cusite).toString()).setFont(font1).setFontSize(10.0f);
		  Paragraph hd3 =new Paragraph();
		  hd3.setFixedLeading(1.0f);
		  hd3.add(text4).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd3);
		
		  log.info("cms 90 in fpo_qry_crpdf");
			/*
			 * Text text5 = new
			 * Text("Email : "+fpoQueryRepo.getfpositemail(cusite)).setFont(font1).
			 * setFontSize(10.0f); Paragraph hd4 =new Paragraph();
			 * hd4.setFixedLeading(1.0f);
			 * hd4.add(text5).setHorizontalAlignment(HorizontalAlignment.CENTER).
			 * setTextAlignment(TextAlignment.CENTER); viewdoc.add(hd4);
			 */
		  
		  log.info("cms 91 in fpo_qry_crpdf");
		  Text text6 = new Text("Contact No. : " + fpoQueryRepo.getfpositeph(cusite)).setFont(font1).setFontSize(10.0f);
		  Paragraph hd5 =new Paragraph();
		  hd5.setFixedLeading(1.0f);
		  hd5.add(text6).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd5);
		  
		  log.info("cms 92 in fpo_qry_crpdf");
		  Text text7 = new Text("Visiting Hours : " + fpoQueryRepo.getfpositewh(cusite)).setFont(font1).setFontSize(10.0f);
		  Paragraph hd6 =new Paragraph();
		  hd6.setFixedLeading(1.0f);
		  hd6.add(text7).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		  viewdoc.add(hd6);
		  
		  log.info("cms 93 in fpo_qry_crpdf");
		  Text text8 = new Text("  ").setFont(font1).setFontSize(10.0f);
		  Paragraph hd7 = new Paragraph(text8);
		  hd7.add(text8);
		  viewdoc.add(hd7);
		
		  float width[]= {300,300};
		  Table table=new Table(width);
	
		 
		  log.info("cms 94 in fpo_qry_crpdf");
		  Text textt1=new Text(" ").setFont(font1).setFontSize(10.0f);
		  Paragraph tp1=new Paragraph(textt1);
		  tp1.setFixedLeading(1.0f);
		  
		  Text textt2 = new Text("ON INDIA GOVERNMENT SERVICE").setFont(font1).setFontSize(10.0f);
		  Paragraph tp2 = new Paragraph(textt2);
		  tp2.setFixedLeading(1.0f);
		  
		  Text textt3 = new Text("To").setFont(font1).setFontSize(10.0f);
		  Paragraph tp3 = new Paragraph(textt3);
		  tp3.setFixedLeading(1.0f);
		  
		  Text textt4 = new Text("By SPEED POST").setFont(font1).setFontSize(10.0f);
		  Paragraph tp4 = new Paragraph(textt4);
		  tp4.setFixedLeading(1.0f);
		  
		  log.info("cms 95 in fpo_qry_crpdf");
		  
		  table.addCell(new Cell().setHeight(11.0f).add(tp1).setBorder(Border.NO_BORDER));
		  table.addCell(new Cell().setHeight(11.0f).add(tp2).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
		  table.addCell(new Cell().setHeight(11.0f).add(tp3).setBorder(Border.NO_BORDER));
		  table.addCell(new Cell().setHeight(11.0f).add(tp4).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
		  
		  viewdoc.add(table);
		 
		  log.info("cms 96 in fpo_qry_crpdf");		  
		  
		  float width1[]= {350,150};
		  Table table1=new Table(width1);
		  table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
		 
		  
			
		  String str1=fpoMainData.get(0).getRECP_NAME(); 
		  Text textt5 = new Text(str1);
		  textt5.setFont(font1).setFontSize(10.0f);
		  Paragraph tp5 = new Paragraph(textt5);
		  tp5.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp5).setBorder(Border.NO_BORDER));
		
		  
		  log.info("cms 97 in fpo_qry_crpdf");
		  String strt="  ";
		  Text textt6 = new Text(strt);
		  textt6.setFont(font1).setFontSize(10.0f);
		  Paragraph tp6 = new Paragraph(textt6);
		  tp6.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp6).setBorder(Border.NO_BORDER));
		  
		  log.info("cms 98 in fpo_qry_crpdf");
		  
		  
			
		  String str2=fpoMainData.get(0).getRECP_ADD1();
		  Text textt7 = new Text(str2);
		  textt7.setFont(font1).setFontSize(10.0f);
		  Paragraph tp7 = new Paragraph(textt7);
		  tp7.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp7).setBorder(Border.NO_BORDER));
		  
		  log.info("cms 99 in fpo_qry_crpdf");
		  
		  String strp="  ";
		  Text textt8 = new Text(strp);
		  textt8.setFont(font1).setFontSize(10.0f);
		  Paragraph tp8 = new Paragraph(textt8);
		  tp8.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp8).setBorder(Border.NO_BORDER));
		  
		  log.info("cms 101 in fpo_qry_crpdf");

		  if (fpoMainData.get(0).getRECP_ADD2()!=null) {
			

		  String str3=fpoMainData.get(0).getRECP_ADD2();
		  Text textt9 = new Text(str3);
		  textt9.setFont(font1).setFontSize(10.0f);
		  Paragraph tp9 = new Paragraph(textt9);
		  tp9.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp9).setBorder(Border.NO_BORDER));

		  
		  String str90="  ";
		  Text textt10 = new Text(str90);
		  textt10.setFont(font1).setFontSize(10.0f);
		  Paragraph tp10 = new Paragraph(textt10);
		  tp10.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp10).setBorder(Border.NO_BORDER));

		  }
		  
		  log.info("cms 102 in fpo_qry_crpdf");
		  
		  String padcity = FPOrepost.getsitecitynm(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());

		if (padcity!=null) {
		 String str4 =fpoMainData.get(0).getRECP_CITY();
		  Text textt11 = new Text(str4);
		  textt11.setFont(font1).setFontSize(10.0f);
		  Paragraph tp11 = new Paragraph(textt11);
		  tp11.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp11).setBorder(Border.NO_BORDER));
		
		  log.info("cms 103 in fpo_qry_crpdf");
		  String str09="  ";
		  Text textt12 = new Text(str09);
		  textt12.setFont(font1).setFontSize(10.0f);
		  Paragraph tp12 = new Paragraph(textt12);
		  tp12.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp12).setBorder(Border.NO_BORDER));
		}
		  log.info("cms 104 in fpo_qry_crpdf");
		  if (fpoMainData.get(0).getRECP_ZIP()!=null) {
		
		  String str5=fpoMainData.get(0).getRECP_ZIP();
		  Text textt13 = new Text(str5);
		  textt13.setFont(font1).setFontSize(10.0f);
		  Paragraph tp13 = new Paragraph(textt13);
		  tp13.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp13).setBorder(Border.NO_BORDER));
		   
		  log.info("cms 105 in fpo_qry_crpdf");
		  
		  String str89="   ";
		  Text textt14 = new Text(str89);
		  textt14.setFont(font1).setFontSize(10.0f);
		  Paragraph tp14 = new Paragraph(textt14);
		  tp14.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp14).setBorder(Border.NO_BORDER));
		  }
		  log.info("cms 106 in fpo_qry_crpdf");
		 
		  if (fpoMainData.get(0).getRECP_PHONE()!=null) {
			

		  String str6=fpoMainData.get(0).getRECP_PHONE();
		  Text textt15 = new Text("Ph:"+str6);
		  textt15.setFont(font1).setFontSize(10.0f);
		  Paragraph tp15 = new Paragraph(textt15);
		  tp15.setFixedLeading(1.0f);
		  table1.addCell(new Cell().setHeight(11.0f).add(tp15).setBorder(Border.NO_BORDER));

		  }  

		  viewdoc.add(table1);
		  log.info("cms 107 in fpo_qry_crpdf");
		   
		   Text text16=new Text("  ");
		   Paragraph tp16 = new Paragraph(text16);
		   viewdoc.add(tp16);
		   log.info("cms 108 in fpo_qry_crpdf");
		   
		   Text text17=new Text("Sir/Madam");
		   text17.setFont(font1).setFontSize(10.0f);
		   Paragraph tp17=new Paragraph (text17);
		   tp17.setFixedLeading(1.0f);
		   viewdoc.add(tp17);

		   log.info("cms 109 in fpo_qry_crpdf");
		   
		   Text text18=new Text("Sub: IMPORT - Foreign Post Article ID "+fpoMainData.get(0).getITEM_ID()+"-reg.");
		   text18.setFont(font1).setFontSize(10.0f);
		   Paragraph tp18=new Paragraph (text18);
		   tp18.setFixedLeading(1.0f);
		   tp18.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		   viewdoc.add(tp18);

		   
		   
		   log.info("cms 110 in fpo_qry_crpdf");
		   Text text19=new Text("*****");
		   text19.setFont(font1).setFontSize(10.0f);
		   Paragraph tp19=new Paragraph (text19);
		   tp19.setFixedLeading(1.0f);
		   tp19.setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
		   viewdoc.add(tp19);

		   log.info("cms 111 in fpo_qry_crpdf");

		   if (que=="E" || que == "N") 
		 	str="1. The  above  mentioned  Import  Article  addressed to you is expected to arrive at the "+ padcity + " FPO and is being taken up for \n"+"\n"+ " assessment by the Indian Customs.";
		 else if (que == "P") 
		 	str="1. The  above  mentioned  Import  Article  addressed to you is received at the "+ padcity + " FPO and is being examined by the Indian Customs.";
			
		   Text text20=new Text(str);
		   text20.setFont(font1).setFontSize(10.0f);
		   Paragraph tp20=new Paragraph (text20).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
		  tp20.setFixedLeading(8.0f);
		  viewdoc.add(tp20);

		  log.info("cms 112 in fpo_qry_crpdf");
		   
		   Text text21=new Text("2. In this regard, it is requested to provide clarifications on the queries raised by the department and provide the documents called \n"+"\n"+ "for, as mentioned in the following tables.");
				   text21.setFont(font1).setFontSize(10.0f);
				   Paragraph tp21=new Paragraph (text21).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				   tp21.setFixedLeading(8.0f);
				   viewdoc.add(tp21);

				   log.info("cms 113 in fpo_qry_crpdf");
				   
				   if (!(DINList.get(0).getRemarks() == null)) {
				   
				   float width2[]= {600};
				   Table table2 = new Table(width2);
				   Text textt22 = new Text("Customs General Query").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp22 = new Paragraph(textt22);	   
				   table2.addCell(new Cell().setHeight(13.0f).add(tp22).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));

				   System.out.println(" this is"+  DINList.get(0).getRemarks());
				   log.info("cms 114 in fpo_qry_crpdf");
				  
					
				
				   Text textt23 = new Text(DINList.get(0).getRemarks()).setFont(font1).setFontSize(10.0f);
				   Paragraph tp23 = new Paragraph(textt23);
				   table2.addCell(new Cell().setHeight(13.0f).add(tp23).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   viewdoc.add(table2);
				   
				   }
				   
				   log.info("cms 115 in fpo_qry_crpdf");
				   
				   Text textt24=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp24 = new Paragraph(textt24);
				   viewdoc.add(tp24);
				 
				   log.info("cms 116 in fpo_qry_crpdf");
				   if (getAllFpoQuery.get(0).getQRY() != null) {

				   float width3[]= {200,200,200};
				   Table table3 = new Table(width3);
				   
				   Text textt31 = new Text("Itemwise Query").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp31 = new Paragraph(textt31);	   
				   table3.addCell(new Cell(0,3).setHeight(13.0f).add(tp31).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
				   //		   tp3.setFixedLeading(1.0f);
				   
				   log.info("cms 117 in fpo_qry_crpdf");
				   
				   Text textt32 = new Text("Item No").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp32 = new Paragraph(textt32);
				   table3.addCell(new Cell().setHeight(13.0f).add(tp32).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 118 in fpo_qry_crpdf");

				   Text textt33 = new Text("Item Description").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp33 = new Paragraph(textt33);
				   table3.addCell(new Cell().setHeight(13.0f).add(tp33).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				  
				   log.info("cms 119 in fpo_qry_crpdf");

				   Text textt34 = new Text("Query").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp34 = new Paragraph(textt34);
				   table3.addCell(new Cell().setHeight(13.0f).add(tp34).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 120 in fpo_qry_crpdf");
				   for (int i = 0; i < getAllFpoQuery.size(); i++)  
				   {
				   if (getAllFpoQuery.get(i).getQRY() != null)
			              {
					   	Text textt35 = new Text(getAllFpoQuery.get(i).getITEM_NO().toString()).setFont(font1).setFontSize(10.0f);
					   	Paragraph tp35 = new Paragraph(textt35);
					   	table3.addCell(new Cell().setHeight(13.0f).add(tp35).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			              

					   Text textt36 = new Text(getAllFpoQuery.get(i).getItemDesc()).setFont(font1).setFontSize(10.0f);
					   Paragraph tp36 = new Paragraph(textt36);
					   table3.addCell(new Cell().setHeight(13.0f).add(tp36).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		              
					   Text textt37 = new Text(getAllFpoQuery.get(i).getQRY()).setFont(font1).setFontSize(10.0f);
					   Paragraph tp37 = new Paragraph(textt37);
					   table3.addCell(new Cell().setHeight(13.0f).add(tp37).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
		              
			              }
				   }
				   
				   log.info("cms 121 in fpo_qry_crpdf");
				   viewdoc.add(table3);
			}
				   log.info("cms 122 in fpo_qry_crpdf");
				   
				   Text textt25=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp25 = new Paragraph(textt25);
				   viewdoc.add(tp25);

				   log.info("cms 123 in fpo_qry_crpdf");
				   
				   float width4[]= {50,550};
				   Table table4 =new Table(width4);
				   Text textt41=new Text("SlNo.").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp41=new Paragraph(textt41);
				   table4.addCell(new Cell().setHeight(13.0f).add(tp41).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
				   
				   log.info("cms 124 in fpo_qry_crpdf");
				   Text textt42=new Text("Document required from recipient").setFont(fontb).setFontSize(10.0f);
				   Paragraph tp42=new Paragraph(textt42);
				   table4.addCell(new Cell().setHeight(13.0f).add(tp42).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY));
				   
				   int k=1;
				   for (int i = 0; i < defualtQueryList.size(); i++) {
					   
				   
				   Text textt43=new Text(" "+k+" ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp43=new Paragraph(textt43);
				   table4.addCell(new Cell().setHeight(13.0f).add(tp43).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				  
				   if (defualtQueryList.get(i).toString()!=null) {
				   Text textt44=new Text(defualtQueryList.get(i).toString()).setFont(font1).setFontSize(10.0f);
				   Paragraph tp44=new Paragraph(textt44);
				   table4.addCell(new Cell().setHeight(28.0f).add(tp44).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				     
				   k++;
				   	}
				   }
				   log.info("cms 125 in fpo_qry_crpdf");
				   viewdoc.add(table4);

				   log.info("cms 126 in fpo_qry_crpdf");
				   
				   Text textt45=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp45 = new Paragraph(textt45);
				   viewdoc.add(tp45);

				   log.info("cms 127 in fpo_qry_crpdf");
				   
				   Text textt46=new Text(" 3. The addressee is requested to clarify the queries and submit the required supporting documents sought above along with the copy of the KYC documents and enable Indian Customs to process for further assessment and levy of duties, if any, subject to provisions of Foreign Trade Policy or any other Act for the time being in force as applicable to the goods contained in the Article. If no reply is received within 15days (Fifteen DAYS) from the date of receipt of this communication, the Article on its arrival, will be liable to be  assessed on merits or confiscated or returned to the sender with costs, as per the provisions of the Customs Act, 1962 without any further intimation to you.").setFont(font1).setFontSize(10.0f);
				   Paragraph tp46 = new Paragraph(textt46).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
				   viewdoc.add(tp46);

				   log.info("cms 128 in fpo_qry_crpdf");
				   
				   Text textt47=new Text(" 4. It is also clarified that in case of undue delay in receipt of reply (i.e. beyond 30 days from the Document Call letter date), the assessment of the goods will be completed based on the documents and information available on record with the department without any further reference to the addressee consignee. ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp47 = new Paragraph(textt47).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
//				  tp47.setMargin(2.0f);
				   viewdoc.add(tp47);
				   
				   log.info("cms 129 in fpo_qry_crpdf");
				   
				   
				   Text textt48=new Text(" 5. This letter does not confer any right on the addressee for clearance of the Article from the Foreign Post Office. Further, Indian Customs is not responsible for any loss or shortage of goods found at the time of examination or delivery of the Article. ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp48 = new Paragraph(textt48).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
//				   tp48.setFixedPosition(50f, 0f, 400f);
				   viewdoc.add(tp48);

				   log.info("cms 130 in fpo_qry_crpdf");
				   
				   Text textt49=new Text(" 6. The Department reserves its rights to initiate action as deemed fit against the consignee/recipient or the person who replies, in cases where any suppression of fact,wrong claims, submission of fake document or misinformation etc is noticed.").setFont(font1).setFontSize(10.0f);
				   Paragraph tp49 = new Paragraph(textt49).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);
				   viewdoc.add(tp49);

				   log.info("cms 131 in fpo_qry_crpdf");
				   
				   Text textt50=new Text(" 7. Please reply by post to the above mentioned postal address in the header, (or),  visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only. ").setFont(fontb).setFontSize(10.0f).setFontColor(Color.BLUE);
				   Paragraph tp50 = new Paragraph(textt50).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.JUSTIFIED);;
				   viewdoc.add(tp50);

				   log.info("cms 132 in fpo_qry_crpdf");

				   Text textt51=new Text(url).setFont(fontbi).setFontSize(9.0f).setFontColor(Color.BLUE);
		 		   Paragraph tp51 = new Paragraph(textt51);
		 		  viewdoc.add(tp51);

		 		  log.info("cms 133 in fpo_qry_crpdf");
		 		  
		 		  Text textt52=new Text("Yours sincerely, ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp52 = new Paragraph(textt52).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
				   viewdoc.add(tp52);

				   log.info("cms 134 in fpo_qry_crpdf");
				   
				   viewdoc.add(image2);
				   
				   log.info("cms 135 in fpo_qry_crpdf");
				   Text textt53=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp53 = new Paragraph(textt53);
				   viewdoc.add(tp53);

				   log.info("cms 136 in fpo_qry_crpdf");
				   
				   Text textt54=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp54 = new Paragraph(textt54);
				   viewdoc.add(tp54);
				   String cd=FPOrepost.getoffname(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString(), request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString(), request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
		           
				   log.info("cms 137 in fpo_qry_crpdf");
				   Text textt55=new Text(cd).setFont(fontb).setFontSize(10.0f);
				   Paragraph tp55 = new Paragraph(textt55).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);
				   viewdoc.add(tp55);

				   log.info("cms 138 in fpo_qry_crpdf");
				   
				   Text textt56=new Text("AO OF CUSTOMS").setFont(font1).setFontSize(10.0f);
				   Paragraph tp56 = new Paragraph(textt56).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
				   viewdoc.add(tp56);

				   log.info("cms 139 in fpo_qry_crpdf");
				   Text textt57=new Text("PAD,"+padcity).setFont(fontb).setFontSize(10.0f);
				   Paragraph tp57 = new Paragraph(textt57).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
				   viewdoc.add(tp57);

				   log.info("cms 140 in fpo_qry_crpdf");
				   
				   Text textt58=new Text(" DATE : "+printcurrentDateTime).setFont(font1).setFontSize(10.0f);
				   Paragraph tp58 = new Paragraph(textt58).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT);;
				   viewdoc.add(tp58);

				   log.info("cms 141 in fpo_qry_crpdf");
				   Text textt59=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp59 = new Paragraph(textt59);
				   viewdoc.add(tp59);

				   log.info("cms 142 in fpo_qry_crpdf");
				   
				   Text textt60=new Text("   ").setFont(font1).setFontSize(10.0f);
				   Paragraph tp60 = new Paragraph(textt60);
				   viewdoc.add(tp60);

				   log.info("cms 143 in fpo_qry_crpdf");
				   
				   Text textt61=new Text("       Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  ").setFont(fontli).setFontSize(7.0f);
				   Paragraph tp61 = new Paragraph(textt61);
				   viewdoc.add(tp61);
		
				   log.info("cms 144 in fpo_qry_crpdf");
		
				   viewdoc.close();
				   viewStream.close();
					System.out.println("Pdf created successfully.");
		
				   log.info("cms 145 in fpo_qry_crpdf");
				   String to = fpoMainData.get(0).getRECP_EMAILID();
				   request.getSession().setAttribute("to", to);
				   request.getSession().setAttribute("tomailid", tomailid);

				   log.info("cms 146 in fpo_qry_crpdf");
				   String toEnteredMobileNumber=fpoQueryRepo.getMobileNumber(cinNo, fpoQueryRepo.getMaxQueryNo());
			//LoginController.toMobileNumber=fpoMainData.get(0).getRECP_PHONE();
			//LoginController.toEnteredMobileNumber=toEnteredMobileNumber;
			 request.getSession().setAttribute("toEnteredMobileNumber", toEnteredMobileNumber);
			 request.getSession().setAttribute("toMobileNumber", fpoMainData.get(0).getRECP_PHONE());
			 log.info("cms 147 in fpo_qry_crpdf");
			 DCALLQRY_GEN dcallqry = new DCALLQRY_GEN();
			 dcallqry.setItem_ID(request.getSession().getAttribute("itemid") == null ? null : request.getSession().getAttribute("itemid").toString());
			 dcallqry.setDcallno(request.getSession().getAttribute("dcallno") == null ? null : request.getSession().getAttribute("dcallno").toString());
			 java.util.Date curdt = new java.util.Date();
			 dcallqry.setGen_dt(curdt);
			 dcallqry.setGenurl(request.getSession().getAttribute("url") == null ? null : request.getSession().getAttribute("url").toString());
			 dcallqry.setCussite(request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString());
			 dcallqry.setRecp_name(request.getSession().getAttribute("recpname") == null ? null : request.getSession().getAttribute("recpname").toString());
			 dcallqry.setGen_filename(request.getSession().getAttribute("filename") == null ? null : request.getSession().getAttribute("filename").toString());
			 dcallqry.setCinno(FPOrepost.getCinIdByItemId(request.getSession().getAttribute("itemid") == null ? null : request.getSession().getAttribute("itemid").toString()));
			 log.info("cms 148 in fpo_qry_crpdf");
			 fpoQueryRepo.updQryReplyAsY(itemid);
			 DcallQRYREPO.save(dcallqry);
			 
		log.info("cms 149 in fpo_qry_crpdf");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public int sendaddlqrymail(String cinNo, String itemid, String dcallno, HttpSession session1,
			HttpServletRequest request) {
		int send = 0;
		List<FPO_MAIL> maillist = MailRepo.findAll();
		// String host = "smtp.icegate.gov.in";

		String tomailid = request.getSession().getAttribute("toMailiD") == null ? null
				: request.getSession().getAttribute("toMailiD").toString();
		String toEnteredMailiD = request.getSession().getAttribute("toEnteredMailid") == null ? null
				: request.getSession().getAttribute("toEnteredMailid").toString();
		log.info("tomailid is " + tomailid);
		log.info("toenteredmailid is " + toEnteredMailiD);
		String host = maillist.get(0).getHost_Name();

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

		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		// String tomailid = LoginController.tomailid;
		// String tomailid = session1.getAttribute("tomailid") == null ? null :
		// session1.getAttribute("tomailid").toString();
		// String filename = LoginController.filename;
		String filename = session1.getAttribute("filename") == null ? null
				: session1.getAttribute("filename").toString();
		// String to=LoginController.to;
		String to = session1.getAttribute("to") == null ? null : session1.getAttribute("to").toString();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		System.out.println(FPOrepost.mailvalid(to));
		if (FPOrepost.mailvalid(to) == 0) {

			System.out.println("tomailid is " + tomailid);

			System.out.println("filename is " + filename);

			System.out.println("to is " + to);

			if (to != null) {
				matcher = pattern.matcher(to);

				if (!(matcher.matches())) {
					System.out.println("INVALID CUSITM EMAIL ID...");
					to = null;
				}
			}
		} else
			to = null;

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Recipient's email ID needs to be mentioned.

			if (to != null) {
				to = "sasikumaresh@gmail.com";
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				if (toEnteredMailiD != null) {
					if (pattern.matcher(toEnteredMailiD).matches())
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(toEnteredMailiD));
				}
				log.info("first");
				System.out.println("first");
				send = 1;
			}

			else if (toEnteredMailiD != null) {
				if (pattern.matcher(toEnteredMailiD).matches()) { // later this line is to be removed when it goes for
																	// launch....
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEnteredMailiD));
					message.addRecipient(Message.RecipientType.CC, new InternetAddress("sasikumaresh@gmail.com"));
					log.info("second");
					System.out.println("second");
					send = 1;
				}
			}

			System.out.println("send is" + send);
			log.info("send is" + send);

			if (send == 1) {
				// Sender's email ID needs to be mentioned
				String from;
				// if (maillist.get(0).getHost_Name() == "smtp.icegate.gov.in")
				from = "noreply@icegate.gov.in";
				// else
				// from = "sasikumaresh@gmail.com";

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Foreign Post Import Article: " + session1.getAttribute("itemid") + " -  "
						+ session1.getAttribute("cuSite") == null ? null
								: session1.getAttribute("cuSite").toString() + " – Query from Customs - reg");
				Multipart multipart = new MimeMultipart();

				MimeBodyPart attachmentPart = new MimeBodyPart();

				MimeBodyPart textPart = new MimeBodyPart();

				try {
					DCALLQRY_GEN dcallQueryGen = DcallQRYREPO.getFirstDCallNumberByCinNo(cinNo);
					attachmentPart.attachFile(filename);
					// textPart.setText("This is just for testing purpose. Please ignore");
					// String constr = "<TABLE align=center style=border:1px black solid;
					// width=75%><tbody><TR border=0><tD border=0
					// width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font
					// size=2><i>Dear <b>"+session1.getAttribute("recpname")+"</b>,<br>Greetings
					// from <u><style='text-decoration-color:Blue'>Indian Customs,</u>
					// <h4>Reference:</h4><h4>Foreign Post Import - Article ID: <font color=blue>" +
					// itemid + "</font>;
					String constr = "<TABLE align=center style=border:1px black solid; width=75%><tbody><TR border=0><tD border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font size=2><i>Dear <b>"
							+ session1.getAttribute("recpname")
							+ "</b>,<br>Greetings from <u><style='text-decoration-color:Blue'>Indian Customs,</u> <h4>Reference:</h4><h4>Foreign Post Import - Article ID:  <font color=blue>"
							+ itemid + "</font></h4>";
//                constr = constr + "Foreign Post Import Article  ID: <style='text-decoration-color:Blue'>"+itemid+"</style>";
					constr = constr + "<h4><i>Additional D-Call Letter No. : <font color=blue>" + dcallno
							+ "</font></i></h4>";
					constr = constr + "<h4><i>Reply Dated: <font color=blue>"
							+ fpoQueryRepo.getrplydt(cinNo,
									session1.getAttribute("cuSite") == null ? null
											: session1.getAttribute("cuSite").toString())
							+ "</font>, the first D Call letter No. <font color=blue>" + dcallQueryGen.getDcallno()
							+ "</font></i></h4>";
					// constr = constr + "Additional D-Call Letter No. : "+dcallno;
					// constr = constr + "Reply Dated:
					// "+fpoQueryRepo.getdcallno(cinNo,request.getSession().getAttribute("cuSite")
					// == null ? null : request.getSession().getAttribute("cuSite").toString())+",
					// the first D Call letter No.
					// "+fpoQueryRepo.getrplydt(cinNo,request.getSession().getAttribute("cuSite") ==
					// null ? null : request.getSession().getAttribute("cuSite").toString());
					constr = constr
							+ "<p align=justify><i>Please refer to the above-mentioned import article and your reply on the D-Call Letter. Upon examination of your reply and the pdf documents uploaded by you, it is found necessary to call for additional information.  </i></p>";
					constr = constr
							+ "<p align=justify><i>The  details of Customs query,  time limit for reply, the documents to be attached if any etc., may be referred  in the Additional Document Call letter (D-Call letter) sent to you via speed post or in the digital copy of the same attached herewith, which is password protected.</i></p>";
					constr = constr
							+ "<p align=justify><i>Password to open the D-Call letter  is the last five characters of the subject Article ID.</i></p>";
					// constr=constr + "<p align=justify><b><u><i>Instructions for replying over
					// email to Customs query/D-Call letter:</i></u></b></p></font>";
					constr = constr
							+ "<p align=justify><b><u><i><font color=blue>Please visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.</font></i></u></b></p> ";
					constr = constr + "<p align=justify><i><b>Click : <u><font color=blue>"
							+ session1.getAttribute("url") + "</font></u></b></i>";
					// constr=constr + "<br>2. Subject of the email should contain the following
					// format same as in the mail received by you:<font color=blue> Foreign Post
					// Import Article: " ;
					/*
					 * constr=constr + itemid; constr=constr + " - Query from Customs -";
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
					constr = constr + "<p><b>Regards,</b><br>";
					constr = constr + "<b>FPO - Indian Customs EDI,<br> CBIC</b></p>";
					constr = constr + "</td><td border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
					constr = constr
							+ "<tr><td colspan=3><br><br><font size=1>    Visit <b>https://enquiry.icegate.gov.in/ </b> to track Postal Import Article  with Customs  under 'Document Status'  </td></tr>";
					constr = constr
							+ "<TR><td colspan=3 align=center><font weight=50><hr><p align=center style=font-weight:50><font size=1>The above email is from an email address that can't receive emails.</p></font></td></tr>";
					;
					// constr=constr + "<br>Don't reply to this email. Please refer above on the
					// instructions to reply over email.</p></font></td></tr>";
					constr = constr + "</tbody></table>";
					textPart.setContent(constr, "text/html");

					multipart.addBodyPart(textPart);
					multipart.addBodyPart(attachmentPart);

				} catch (IOException e) {

					e.printStackTrace();

				}

				message.setContent(multipart);

				System.out.println("sending...");
				log.info("sending...");
				// Send message
				//Transport.send(message); to be uncommented for Email and SmS
				System.out.println("Sent message successfully....");
				java.util.Date curdt = new java.util.Date();
				DcallQRYREPO.dcallqryupd(to, toEnteredMailiD, curdt,
						session1.getAttribute("dcallno") == null ? null : session1.getAttribute("dcallno").toString());

				DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
				int count = 1;

				if (dCALLQRY_GEN.getEmailcou() != null) {
					count = Integer.parseInt(dCALLQRY_GEN.getEmailcou()) + 1;
				}
				fpoDcallQryRepo.updateDcallEmail(dcallno, tomailid, count);
			}

			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);

			insertpushDcall(tomailid, fpoMainData.get(0), "EMAIL", dcallno, session1, request);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		// LoginController.to = null;
		session1.setAttribute("to", null);
		// LoginController.tomailid = null;
		session1.setAttribute("tomailid", null);
		// LoginController.filename = null;
		session1.setAttribute("filename", null);
		// LoginController.itemid = null;
		session1.setAttribute("itemid", null);
		// LoginController.dinno = null;
		session1.setAttribute("dinno", null);
		// LoginController.recpname = null;
		session1.setAttribute("recpname", null);

		return send;

	}

	/*
	 * ********************** local server mail sending
	 * ***************************************************************
	 */

	// by santhosh, veem, kani

	public FpoAddlQry deleteAddlquery(FpoAddlQry fpoaddquery) {
		String cinNo = fpoaddquery.getCIN_NO();

		System.out.println("cinno is " + cinNo);
		fpoAddlQryRepo.updateaddlqryStus(cinNo);
		fpomainrepo.updateRoleApr(cinNo);
		return fpoaddquery;
	}

	public int sendmail(String itemid, String dcallno, HttpSession session1, String cinNo, HttpServletRequest request) {

		int send = 0;
		List<FPO_MAIL> maillist = MailRepo.findAll();
		// String host = "smtp.icegate.gov.in";

		String host = maillist.get(0).getHost_Name();

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

		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		String tomailid = session1.getAttribute("tomailid") == null ? null
				: session1.getAttribute("tomailid").toString();
		String filename = session1.getAttribute("filename") == null ? null
				: session1.getAttribute("filename").toString();
		// String to=LoginController.to;
		String to = session1.getAttribute("to") == null ? null : session1.getAttribute("to").toString();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		System.out.println(FPOrepost.mailvalid(to));
		if (FPOrepost.mailvalid(to) == 0) {

			System.out.println("tomailid is " + tomailid);

			System.out.println("filename is " + filename);

			System.out.println("to is " + to);

			if (to != null) {
				matcher = pattern.matcher(to);

				if (!(matcher.matches())) {
					System.out.println("INVALID CUSITM EMAIL ID...");
					to = null;
				}
			}
		} else
			to = null;

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Recipient's email ID needs to be mentioned.

			if (to != null) {
				to = "sasikumaresh@gmail.com";
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				if (tomailid != null) {
					if (pattern.matcher(tomailid).matches())
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(tomailid));
				}
				System.out.println("first");
				send = 1;
			}

			else if (tomailid != null) {
				if (pattern.matcher(tomailid).matches()) { // later this line is to be removed when it goes for
															// launch....
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomailid));
					System.out.println("second");
					send = 1;
				}
			}

			System.out.println("send is" + send);

			if (send == 1) {
				// Sender's email ID needs to be mentioned
				String from;
				log.info(maillist.get(0).getHost_Name());
				// if
				// (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.icegate.gov.in"))
				from = "noreply@icegate.gov.in";
				// else
				// from = "sasikumaresh@gmail.com";
				log.info("from=" + from);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Foreign Post Import Article: " + session1.getAttribute("itemid") + " -  "
						+ session1.getAttribute("cuSite") == null ? null
								: session1.getAttribute("cuSite").toString() + " – Query from Customs - reg");

				Multipart multipart = new MimeMultipart();

				MimeBodyPart attachmentPart = new MimeBodyPart();

				MimeBodyPart textPart = new MimeBodyPart();

				try {
					attachmentPart.attachFile(filename);
					// textPart.setText("This is just for testing purpose. Please ignore");
					String constr = "<TABLE align=center style=border:1px black solid; width=75%><tbody><TR border=0><tD border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font size=2><i>Dear <b>"
							+ session1.getAttribute("recpname")
							+ "</b>,<br>Greetings from <u><style='text-decoration-color:Blue'>Indian Customs,</u> <h4>Reference:</h4><h4>Foreign Post Import - Article ID:  <font color=blue>"
							+ itemid + "</font></h4>";
					constr = constr
							+ "<p align=justify><i>Please refer to the above-mentioned import article addressed to you and it is expected to arrive at the Foreign Post Office (FPO) and is being taken up for assessment or have been received at the FPO and is being examined by the Indian Customs. In this regard, you are requested to clarify on the queries raised, to  process the article.</i></p>";
					constr = constr
							+ "<p align=justify><i>The  details of Customs query,  time limit for reply, the documents to be attached if any etc., may be referred  in the Document Call letter (D-Call letter) sent to you via speed post or in the digital copy of the D-Call letter attached herewith, which is password protected.</i></p>";
					constr = constr
							+ "<p align=justify><i>Password to open the D-Call letter  is the last five characters of the subject Article ID.</i></p>";
					// constr=constr + "<p align=justify><b><u><i>Instructions for replying over
					// email to Customs query/D-Call letter:</i></u></b></p></font>";
					constr = constr
							+ "<p align=justify><b><u><i><font color=blue>Please visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.</font></i></u></b></p> ";
					constr = constr + "<p align=justify><i><b>Click : <u><font color=blue>"
							+ session1.getAttribute("url") + "</font></u></b></i>";
					// constr=constr + "<br>2. Subject of the email should contain the following
					// format same as in the mail received by you:<font color=blue> Foreign Post
					// Import Article: " ;
					/*
					 * constr=constr + itemid; constr=constr + " - Query from Customs -";
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

					constr = constr + "<p><b>Regards,</b><br>";

					constr = constr + "<b>FPO - Indian Customs EDI,<br> CBIC</b></p>";
					constr = constr + "</td><td border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
					constr = constr
							+ "<tr><td colspan=3><br><br><font size=1>    * Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  </td></tr>";
					constr = constr
							+ "<TR><td colspan=3 align=center><font weight=50><hr><p align=center style=font-weight:50><font size=1>The above email is from an email address that can't receive emails.</p></font></td></tr>";
					;
					// constr=constr + "<br>Don't reply to this email. Please refer above on the
					// instructions to reply over email.</p></font></td></tr>";
					constr = constr + "</tbody></table>";
					textPart.setContent(constr, "text/html");

					multipart.addBodyPart(textPart);
					multipart.addBodyPart(attachmentPart);

				} catch (IOException e) {

					e.printStackTrace();

				}

				message.setContent(multipart);

				System.out.println("sending...");
				// Send message
			//	Transport.send(message); to be uncommented for Email and SmS
				System.out.println("Sent message successfully....");
				java.util.Date curdt = new java.util.Date();
				DcallQRYREPO.dcallqryupd(to, tomailid, curdt,
						session1.getAttribute("dcallno") == null ? null : session1.getAttribute("dcallno").toString());
				System.out.println("dcallno is " + dcallno);
				DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
				int count = 1;

				if (dCALLQRY_GEN.getEmailcou() != null) {
					count = Integer.parseInt(dCALLQRY_GEN.getEmailcou()) + 1;
				}
				fpoDcallQryRepo.updateDcallEmail(dcallno, tomailid, count);
			}

			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);

			insertpushDcall(tomailid, fpoMainData.get(0), "EMAIL", dcallno, session1, request);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		// LoginController.to = null;
		session1.setAttribute("to", null);
		// LoginController.tomailid = null;
		session1.setAttribute("tomailid", null);
		// LoginController.filename = null;
		session1.setAttribute("filename", null);
		// LoginController.itemid = null;
		session1.setAttribute("itemid", null);
		// LoginController.dinno = null;
		session1.setAttribute("dinno", null);
		// LoginController.recpname = null;
		session1.setAttribute("recpname", null);
		return send;

	}

	/*
	 * ********************** local server mail sending
	 * ***************************************************************
	 */

	/*
	 * **************** dev server mail sending public int sendmail(String itemid,
	 * String dcallno, HttpSession session1,String cinNo) { int send = 0; String
	 * host = "smtp.icegate.gov.in";
	 * 
	 * 
	 * Properties properties = System.getProperties();
	 * 
	 * properties.put("mail.smtp.host", host); properties.put("mail.smtp.port",
	 * "25"); // properties.put("mail.smtp.ssl.enable", "true"); //
	 * properties.put("mail.debug", "true");
	 * 
	 * Session session = request.getSession().getDefaultInstance(properties);
	 * 
	 * // Session session = request.getSession().getInstance(properties, new
	 * javax.mail.Authenticator() { // // protected PasswordAuthentication
	 * getPasswordAuthentication() {
	 * 
	 * // return new PasswordAuthentication("sasikumaresh@gmail.com",
	 * "PremiMartha@123");
	 * 
	 * // }
	 * 
	 * // });
	 * 
	 * 
	 * String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; String tomailid =
	 * session1.getAttribute("tomailid") == null ? null :
	 * session1.getAttribute("tomailid").toString(); String filename =
	 * session1.getAttribute("filename") == null ? null :
	 * session1.getAttribute("filename").toString(); // String
	 * to=LoginController.to; String to=session1.getAttribute("to") == null ? null :
	 * session1.getAttribute("to").toString();
	 * 
	 * 
	 * Pattern pattern = Pattern.compile(regex); Matcher matcher ;
	 * System.out.println(FPOrepost.mailvalid(to)); if (FPOrepost.mailvalid(to)==0 )
	 * {
	 * 
	 * System.out.println("tomailid is "+tomailid);
	 * 
	 * System.out.println("filename is "+filename);
	 * 
	 * System.out.println("to is "+to);
	 * 
	 * 
	 * if (to != null) { matcher = pattern.matcher(to);
	 * 
	 * 
	 * if (!(matcher.matches())) { System.out.println("INVALID CUSITM EMAIL ID...");
	 * to=null; }} } else to=null;
	 * 
	 * 
	 * try { // Create a default MimeMessage object. MimeMessage message = new
	 * MimeMessage(session);
	 * 
	 * // Recipient's email ID needs to be mentioned.
	 * 
	 * if (to != null) { to="sasikumaresh@gmail.com";
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); if
	 * (tomailid !=null) { if (pattern.matcher(tomailid).matches())
	 * message.addRecipient(Message.RecipientType.CC, new
	 * InternetAddress(tomailid));} System.out.println("first"); send = 1; }
	 * 
	 * else if (tomailid != null) { if (pattern.matcher(tomailid).matches() ) {
	 * //later this line is to be removed when it goes for launch....
	 * message.addRecipient(Message.RecipientType.TO, new
	 * InternetAddress(tomailid)); System.out.println("second"); send =1 ;}}
	 * 
	 * System.out.println("send is" + send);
	 * 
	 * if ( send == 1) { // Sender's email ID needs to be mentioned String from =
	 * "noreply@icegate.gov.in";
	 * 
	 * 
	 * // Set From: header field of the header. message.setFrom(new
	 * InternetAddress(from));
	 * 
	 * // Set To: header field of the header. //
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * 
	 * // Set Subject: header field
	 * message.setSubject("Foreign Post Import Article: " +
	 * session1.getAttribute("itemid") + " -  " + session1.getAttribute("cuSite") ==
	 * null ? null : session1.getAttribute("cuSite").toString() +
	 * " – Query from Customs - reg" );
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * 
	 * MimeBodyPart attachmentPart = new MimeBodyPart();
	 * 
	 * MimeBodyPart textPart = new MimeBodyPart();
	 * 
	 * 
	 * 
	 * try { attachmentPart.attachFile(filename); //
	 * textPart.setText("This is just for testing purpose. Please ignore"); String
	 * constr =
	 * "<TABLE align=center style=border:1px black solid; width=75%><tbody><TR border=0><tD border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<TD border=0 width=65%><font size=2><i>Dear <b>"
	 * +session1.getAttribute("recpname")
	 * +"</b>,<br>Greetings from <u><style='text-decoration-color:Blue'>Indian Customs,</u> <h4>Reference:</h4><h4>Foreign Post Import - Article Id:  <font color=blue>"
	 * + itemid + "</font></h4>"; constr = constr +
	 * "<p align=justify><i>Please refer to the above-mentioned import article addressed to you and it is expected to arrive at the Foreign Post Office (FPO) and is being taken up for assessment or have been received at the FPO and is being examined by the Indian Customs. In this regard, you are requested to clarify on the queries raised, to  process the article.</i></p>"
	 * ; constr = constr +
	 * "<p align=justify><i>The  details of Customs query,  time limit for reply, the documents to be attached if any etc., may be referred  in the Document Call letter (D-Call letter) sent to you via speed post or in the digital copy of the D-Call letter attached herewith, which is password protected.</i></p>"
	 * ; constr = constr +
	 * "<p align=justify><i>Password to open the D-Call letter  is the last five characters of the subject Article ID.</i></p>"
	 * ; // constr=constr +
	 * "<p align=justify><b><u><i>Instructions for replying over email to Customs query/D-Call letter:</i></u></b></p></font>"
	 * ; constr=constr +
	 * "<p align=justify><b><u><i><font color=blue>Please visit the following one time link for replying to the Customs query and upload valid KYC document of the recipient / importer and relevant supporting documents. This link will be valid for 30 days only.</font></i></u></b></p> "
	 * ; constr=constr + "<p align=justify><i><b>Click : <u><font color=blue>" +
	 * session1.getAttribute("url") +"</font></u></b></i>"; // constr=constr +
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

	/*
	 * ********** dev server mail sending
	 * 
	 * constr=constr + "<p><b>Regards,</b><br>";
	 * 
	 * constr=constr + "<b>FPO - Indian Customs EDI,<br> CBIC</b></p>";
	 * constr=constr +
	 * "</td><td border=0 width=5%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	 * constr=constr +
	 * "<tr><td colspan=3><br><br><font size=1>    * Visit https://enquiry.icegate.gov.in/ to track Postal Import Article  with Customs  under 'Document Status'  </td></tr>"
	 * ; constr=constr +
	 * "<TR><td colspan=3 align=center><font weight=50><hr><p align=center style=font-weight:50><font size=1>The above email is from an email address that can't receive emails.</p></font></td></tr>"
	 * ;; // constr=constr +
	 * "<br>Don't reply to this email. Please refer above on the instructions to reply over email.</p></font></td></tr>"
	 * ; constr=constr + "</tbody></table>";
	 * textPart.setContent(constr,"text/html");
	 * 
	 * multipart.addBodyPart(textPart); multipart.addBodyPart(attachmentPart);
	 * 
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * message.setContent(multipart);
	 * 
	 * System.out.println("sending..."); // Send message Transport.send(message);
	 * System.out.println("Sent message successfully...."); java.util.Date curdt =
	 * new java.util.Date();
	 * DcallQRYREPO.dcallqryupd(to,tomailid,curdt,session1.getAttribute("dcallno")
	 * == null ? null : session1.getAttribute("dcallno").toString());
	 * 
	 * DCALLQRY_GEN dCALLQRY_GEN= fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
	 * int count = 1;
	 * 
	 * if(dCALLQRY_GEN.getEmailcou()!=null) { count=
	 * Integer.parseInt(dCALLQRY_GEN.getEmailcou())+1; }
	 * fpoDcallQryRepo.updateDcallEmail(dcallno,tomailid,count); }
	 * 
	 * List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);
	 * 
	 * insertpushDcall(tomailid,fpoMainData.get(0),"EMAIL",dcallno, session1);
	 * 
	 * } catch (MessagingException mex) { mex.printStackTrace(); }
	 * 
	 * 
	 * // LoginController.to = null; session1.setAttribute("to", null); //
	 * LoginController.tomailid = null; session1.setAttribute("tomailid", null); //
	 * LoginController.filename = null; session1.setAttribute("filename", null); //
	 * LoginController.itemid = null; session1.setAttribute("itemid", null); //
	 * LoginController.dinno = null; session1.setAttribute("dinno", null); //
	 * LoginController.recpname = null; session1.setAttribute("recpname", null);
	 * return send; }
	 * 
	 * /**** dev server mail sending over
	 ******/

	public FpoQuery deletequery(FpoQuery fpoquery) {
		String cinNo = fpoquery.getCinNo();
		System.out.println("cinno is " + cinNo);
		fpoQueryRepo.updateqryStus(cinNo);
		fpomainrepo.updateRoleApr(cinNo);
		return fpoquery;
	}

	public void updateStatusOfAllPages(String cinNo, String itemId, String qryType, HttpSession session,
			HttpServletRequest request) {

		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String offid = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();

		java.util.Date utilDate = new java.util.Date();
		if (fpoQueryDecisionRepo.chkitemiddeci(itemId) == 0) {
			FpoQueryDecision fpoQueryDecision = new FpoQueryDecision();

			fpoQueryDecision.setCIN_NO(cinNo);
			fpoQueryDecision.setITEM_ID(itemId);
			fpoQueryDecision.setQRY_TYPE(qryType);
			fpoQueryDecision.setOFF_ID(offid);
			fpoQueryDecision.setROLE(role);
			fpoQueryDecision.setCUS_SITE(cusite);
			fpoQueryDecisionRepo.save(fpoQueryDecision);
		} else
			fpoQueryDecisionRepo.updqrydeciblk(itemId, qryType, cusite, role, offid);
	}

	public String getStatusOfAllPages(String cinNo) {
		List<String> pageStatus = fpoQueryDecisionRepo.getPageStatus(cinNo);

		if (pageStatus.get(0).equals("E1"))
			return "order";

		if (pageStatus.get(0).equals("E2"))
			return "ead_query";

		if (pageStatus.get(0).equals("E3"))
			return "ead_submit";
		return "ead_item";
	}

	public List<String> getItemAllCth(String cinNo) {
		List<String> getItemAllCth = fpoItemRepost.getItemAllCth(cinNo);
		return getItemAllCth;
	}

	public List<Object> getTotalNoItemsPagination(String cinNo) {
		List<Object> getTotalNoItemsPagination = fpoItemRepost.getTotalNoItemsPagination(cinNo);
		List<Object> getTotalNoItemsPaginationYes = fpoItemRepost.getTotalNoItemsPaginationYes(cinNo);
		if (null != getTotalNoItemsPaginationYes)
			return getTotalNoItemsPaginationYes;
		else
			return getTotalNoItemsPagination;
	}

	// updated on 22nd february, 2022
	public void insertIntoDecisionsDb(FpoQueryDecision fpoQueryDecision, String qryType, HttpSession session,
			String Stage, HttpServletRequest request) {
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		String offid = request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString();
		if (Stage == null)
			Stage = "X";
		if (qryType.equals("P4"))
			role = "PIN";
		else if (qryType.equals("P3"))
			role = "PSU";
		else if (Stage.equals("C1") || Stage.equals("C2")) {
			role = "PAO";
			// fpoQueryDecisions.setOFF_ID(request.getSession().getAttribute("offId") ==
			// null ? null : request.getSession().getAttribute("offId").toString());
		}
		// fpoQueryDecisions.setOFF_ID(request.getSession().getAttribute("offId") ==
		// null ? null : request.getSession().getAttribute("offId").toString());
		if (fpoQueryDecisionRepo.chkitemid(fpoQueryDecision.getITEM_ID()) == 0) {
			FpoQueryDecision fpoQueryDecisions = new FpoQueryDecision();
			fpoQueryDecisions.setROLE(role);
			fpoQueryDecisions.setCUS_SITE(cusite);
			fpoQueryDecisions.setQRY_TYPE(qryType);
			fpoQueryDecisions.setDECI_CD(fpoQueryDecision.getDECI_CD());
			fpoQueryDecisions.setCIN_NO(fpoQueryDecision.getCIN_NO());
			fpoQueryDecisions.setITEM_ID(fpoQueryDecision.getITEM_ID());
			fpoQueryDecisionRepo.save(fpoQueryDecisions);
		} else
			fpoQueryDecisionRepo.updqrydecioth(fpoQueryDecision.getITEM_ID(), qryType, cusite, role, offid);

	}// updated on 22nd february, 2022

	private String reDirectToPage(FpoQueryDecision fpoQueryDecision, Long orderNo, HttpSession session,
			HttpServletRequest request) {

		if (null != fpoQueryDecision.getQRY_NO() && !fpoQueryDecision.getQRY_NO().toString().isEmpty()
				&& null != orderNo && !orderNo.toString().isEmpty()) {
			if (null != fpoQueryDecision.getDECI_CD() && fpoQueryDecision.getDECI_CD() != 9) {
				insertIntoDecisionsDb(fpoQueryDecision, "P2", session, null, request);
				// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
				// poCurQueService.updateUSerEnterStatus(fpoQueryDecision.getCIN_NO(), "IMP");
				poCurQueService.updateQueryEnterStatus(fpoQueryDecision.getCIN_NO(), "IMP", "P2", session, request);
				return "redirect:/ead_list";
			}
		} else if (null == fpoQueryDecision.getQRY_NO() && null == orderNo) {
			if (null != fpoQueryDecision.getDECI_CD() && fpoQueryDecision.getDECI_CD() != 9) {
				insertIntoDecisionsDb(fpoQueryDecision, "P3", session, null, request);
				// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
				return "redirect:/ead_list";
			} else {
				insertIntoDecisionsDb(fpoQueryDecision, "P6", session, null, request);
				// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
				return "redirect:/ead_list";
			}
		} else if (null != fpoQueryDecision.getQRY_NO() && !fpoQueryDecision.getQRY_NO().toString().isEmpty()
				&& null == orderNo) {
			if (null != fpoQueryDecision.getDECI_CD() && validateDec(fpoQueryDecision.getDECI_CD())) {
				insertIntoDecisionsDb(fpoQueryDecision, "P2", session, null, request);
				// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
				// poCurQueService.updateUSerEnterStatus(fpoQueryDecision.getCIN_NO(), "IMP");
				poCurQueService.updateQueryEnterStatus(fpoQueryDecision.getCIN_NO(), "IMP", "P2", session, request);
				return "redirect:/ead_list";
			} else {
				if (fpoQueryDecision.getDECI_CD() != 9) {
					insertIntoDecisionsDb(fpoQueryDecision, "P4", session, null, request);
					// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
					return "redirect:/ead_list";
				} else {
					insertIntoDecisionsDb(fpoQueryDecision, "P7", session, null, request);
					// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
					return "redirect:/ead_list";
				}
			}
		} else if (null == fpoQueryDecision.getQRY_NO() && null != orderNo && !orderNo.toString().isEmpty()) {
			if (null != fpoQueryDecision.getDECI_CD() && fpoQueryDecision.getDECI_CD() == 1) {
				insertIntoDecisionsDb(fpoQueryDecision, "P4", session, null, request);
				// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
				return "redirect:/ead_list";
			} else {
				if (fpoQueryDecision.getDECI_CD() != 9) {
					insertIntoDecisionsDb(fpoQueryDecision, "P4", session, null, request);
					// poCurQueService.updateUSerExitStatus(fpoQueryDecision.getCIN_NO(), null);
					return "redirect:/ead_list";
				}
			}
		}
		return "redirect:/ead_list";
	}

	public boolean validateDec(Long decCd) {

		if (decCd == 1 || decCd == 5 || decCd == 6 || decCd == 7 || decCd == 8 || decCd == 9)
			return true;

		return false;
	}

	public FPO_SETASIDE inssetaside(FPO_SETASIDE fposetaside, HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		FPOrepost.setaside(fposetaside.getCin_No());
		System.out.println("curdt is " + curdt);
		FPO_SETASIDE fposets = new FPO_SETASIDE();
		fposets.setCin_No(fposetaside.getCin_No());
		fposets.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fposets.setReas(fposetaside.getReas());
		fposets.setOFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fposets.setROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fposets.setITEM_ID(FPOrepost.getitemid(fposetaside.getCin_No()));
		fposets.setASIDE_DATE(curdt);
		fposetasiderepo.save(fposets);
		if (fposets != null) {
			return fposets;
		} else {
			return null;
		}
	}

	public List<FPO_MAIN> setFpoMainValues(List<FPO_MAIN> fpoMain) {

		/*
		 * if (null != fpoMain.get(0).getHANDLING_CLASS_CD()) { String shortValue =
		 * FPOrepost.getShortValue(fpoMain.get(0).getId());
		 * fpoMain.get(0).setHANDLING_CLASS_CD(fpoMain.get(0).getHANDLING_CLASS_CD() +
		 * "-" + shortValue); }
		 * 
		 * if (null != fpoMain.get(0).getNATURE_TYPE_CD()) { String shortValue =
		 * FPOrepost.getNatureValue(fpoMain.get(0).getId());
		 * fpoMain.get(0).setNATURE_TYPE_CD(shortValue); }
		 */

		// if (null != fpoMain.get(0).getFpoAllocatedValue()) {
		// String shortValue = FPOrepost.getFpoAllocatedValue(fpoMain.get(0).getId());
		// fpoMain.get(0).setFPO_CODE(shortValue);
		// }

		return fpoMain;
	}

	public void insertFpoQuery(FPO_ITEM_DET fpoItemDet, String que, String others, HttpSession session,
			HttpServletRequest request) {
		int index;
		Long cou = fpoQueryRepo.getcou(fpoItemDet.getCinNo(), request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		/*
		 * if (cou > 1)
		 * fpoQueryRepo.delrec(fpoItemDet.getCinNo(),request.getSession().getAttribute(
		 * "offId") == null ? null :
		 * request.getSession().getAttribute("offId").toString());
		 */
		List<FpoQuery> fpoQueryRepoList = new ArrayList<FpoQuery>();
		List<FPO_ITEM_DET> fpoItemList = fpoDeclaredService.getAssesItem(fpoItemDet.getCinNo());
		int dinSerialNumber = 0;
		List<FpoQueryDin> fpoQueryDinList = fpoQueryDinRepo.getFpoQueryDIN(fpoItemDet.getCinNo());
		String fpoQueryData = fpoItemDet.getQuery();
		String[] fpoQueryList = null;
		if (null != fpoQueryData)
			fpoQueryList = fpoQueryData.split(",");
		Long maxQueryNo = fpoQueryRepo.getMaxQueryNo();
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
			fpoQuery.setMark(others);
			fpoQuery.setQRY_NO(maxQueryNo + 1l);
			fpoQuery.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());

			Long maxid = fpoItemDetRepo.getMaxIdOfFPOQUERY(fpoItemDet.getCinNo(), fpoItem.getITEM_NO(), que);
			if (maxid != null && maxid > 0) {
				fpoQuery.setId(maxid);
			}

			if (null != fpoQueryList) {
				if (index > fpoQueryList.length) {
					fpoQuery.setQRY("");
				} else if (!(null == fpoQueryList[index - 1]) && !(fpoQueryList[index - 1].isEmpty())) {
					fpoQuery.setQRY(fpoQueryList[index - 1]);
					fpoQueryRepoList.add(fpoQuery);
				}
			}
		}

		FpoQuery fpoQueryRemarks = new FpoQuery();
		fpoQueryRemarks.setCinNo(fpoItemDet.getCinNo());
		fpoQueryRemarks.setCUS_SITE(fpoItemDet.getCUS_SITE());
		fpoQueryRemarks.setITEM_ID(fpoItemDet.getITEM_ID());
//		fpoQueryRemarks.setPOSTINGDT(fpoItemDet.getPOSTINGDT());
		fpoQueryRemarks.setDEFUALT_QUERY(fpoItemDet.getDefualtQueryOne() + ";" + fpoItemDet.getDefualtQueryTwo() + ";"
				+ fpoItemDet.getDefualtQueryThree() + ";" + fpoItemDet.getDefualtQueryFour());
		fpoQueryRemarks.setQRY_DATE(new Date());
		fpoQueryRemarks.setQRY_OFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpoQueryRemarks.setQRY_ROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fpoQueryRemarks.setQRY_TYP(que);
		fpoQueryRemarks.setMark(others);
		fpoQueryRemarks.setEmail_Id(fpoItemDet.getEmailid());
		fpoQueryRemarks.setMobile_No(fpoItemDet.getMobileno());
		fpoQueryRemarks.setQRY_NO(maxQueryNo + 1l);
		fpoQueryRemarks.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());

		Long maxid = fpoItemDetRepo.getMaxIdOfFPOQUERY(fpoItemDet.getCinNo(), que);
		if (maxid != null && maxid > 0) {
			fpoQueryRemarks.setId(maxid);
		}

		String defqry = fpoItemDetRepo.getDEFUALT_QUERYbyId(maxid);
		fpoQueryRemarks.setDEFUALT_QUERY(defqry);

		fpoQueryRepoList.add(fpoQueryRemarks);
		fpoQueryRepo.saveAll(fpoQueryRepoList);

		if (fpoItemDet.getQueryRemarks() != null) {

			if (null != fpoQueryDinList && !fpoQueryDinList.isEmpty()) {
				dinSerialNumber = fpoQueryDinList.size();
			}

			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			String[] dateArray = date.split("-");
			String str = "";
			if (que == "E")
				str = "EAD";
			else if (que == "N")
				str = "N";
			else if (que == "P")
				str = "P";
			Long slno = fpoQueryDinRepo.getdincou(fpoItemDet.getCinNo(),
					request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString());
			if (slno == null) {
				FpoQueryDin fpoQueryDin = new FpoQueryDin();
				fpoQueryDin.setCinNo(fpoItemDet.getCinNo());
				fpoQueryDin.setCusSite(request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
				fpoQueryDin.setItemid(fpoItemDet.getITEM_ID());
				fpoQueryDin.setRemarks(fpoItemDet.getGeneralQuery());
				fpoQueryDin.setDepComments(fpoItemDet.getQueryRemarks());
				fpoQueryDin.setDin1(fpoItemDet.getDin());
				fpoQueryDin.setDinSerialNumber(dinSerialNumber + 1l);
				fpoQueryDin.setUniqueNo(str + request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString() + dateArray[0] + dateArray[1]
								+ dateArray[2] + gen());

				maxid = fpoItemDetRepo.getMaxIdOfFpoQueryDin(fpoItemDet.getCinNo());
				if (maxid != null && maxid > 0) {
					fpoQueryDin.setId(maxid);
				}
				fpoQueryDinRepo.save(fpoQueryDin);
			} else
				fpoQueryDinRepo.updateAprCmts(fpoItemDet.getCinNo(), fpoItemDet.getQueryRemarks(), fpoItemDet.getDin(),
						slno);
		} else {
			fpoQueryDinRepo.updateAclCmts(fpoItemDet.getCinNo(), fpoItemDet.getQueryRemarks1());
		}
	}

	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

	@Autowired
	com.demo.fpo.repos.FpoDefualtQueryRepo fpoDefualtQueryRepo;

	public List<String> getDefualtQuery() {
		List<String> listQuery = new ArrayList<String>();
		List<FpoDefualtQuery> fpoDefualtQueryList = fpoDefualtQueryRepo.getDefQryData();

		Collections.sort(fpoDefualtQueryList);

		for (FpoDefualtQuery fpoDefualtQuery : fpoDefualtQueryList) {
			listQuery.add(fpoDefualtQuery.getDEFAULT_QUERY());
		}
		return listQuery;
	}

//	public List<String> getSpecifiedDefualtQuery(String serilNo) {
//
//		String[] arrySerialNo = serilNo.split(";");
//		List<String> listQuery = new ArrayList<String>();
//		List<FpoDefualtQuery> fpoDefualtQueryList = fpoDefualtQueryRepo.findAll();
//		for (FpoDefualtQuery fpoDefualtQuery : fpoDefualtQueryList) {
//			if (null != arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1]
//					&& !(arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1].toString().isEmpty()))
//				if (fpoDefualtQuery.getSERIAL_NO().toString()
//						.equals(arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1])) {
//					listQuery.add(fpoDefualtQuery.getDEFAULT_QUERY());
//				}
//		}
//		return listQuery;
//	}

	public List<String> getSpecifiedDefualtQuery(String serilNo, String DocName) {

		String[] arrySerialNo = serilNo.split(";");

		String last = arrySerialNo[arrySerialNo.length - 1];
//		arrySerialNo[6] = "7";
//		System.out.println(arrySerialNo);
		List<String> listQuery = new ArrayList<String>();

		List<FpoDefualtQuery> fpoDefualtQueryList = fpoDefualtQueryRepo.getDefQryData();
		for (FpoDefualtQuery fpoDefualtQuery : fpoDefualtQueryList) {
			if (null != arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1]
					&& !(arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1].toString().isEmpty()))
				if (fpoDefualtQuery.getSERIAL_NO().toString()
						.equals(arrySerialNo[fpoDefualtQuery.getSERIAL_NO().intValue() - 1])) {
					listQuery.add(fpoDefualtQuery.getDEFAULT_QUERY());
				}

		}
		System.out.println(listQuery);
		// listQuery.set(6, last);
		for (int i = 0; i < listQuery.size(); i++) {
			if (listQuery.get(i).equals("Other Documents")) {
				listQuery.set(i, DocName);
			}
		}
		return listQuery;
	}

	public FPO_ITEM_DET moveAmendToItem(FPO_AMEND fpoAmendEntity) {

		FPO_ITEM_DET fpoItemDet = new FPO_ITEM_DET();
		fpoItemDet.setAMEND_DT(fpoAmendEntity.getAMEND_DT());
		fpoItemDet.setAMEND_FLAG(fpoAmendEntity.getAMEND_FLAG());
		fpoItemDet.setAMEND_SERAIL_NO(fpoAmendEntity.getAMEND_SERIAL_NO());
		fpoItemDet.setBCD_AMT(fpoAmendEntity.getBCD_AMT());
		fpoItemDet.setBCD_AMT_FG(fpoAmendEntity.getBCD_AMT_FG());
		fpoItemDet.setBCD_NOTN(fpoAmendEntity.getBCD_NOTN());
		fpoItemDet.setBCD_NSNO(fpoAmendEntity.getBCD_NSNO());
		fpoItemDet.setBCD_RTA(fpoAmendEntity.getBCD_RTA());
		fpoItemDet.setCIN_DT(fpoAmendEntity.getAMEND_DT());
		fpoItemDet.setCinNo(fpoAmendEntity.getCIN_NO());
		fpoItemDet.setCTH(fpoAmendEntity.getCTH());
		fpoItemDet.setCTH_REVISED(fpoAmendEntity.getCTH());
		fpoItemDet.setCURRCD(fpoAmendEntity.getCURRCD());
		fpoItemDet.setCUS_SITE(fpoAmendEntity.getCUS_SITE());
		fpoItemDet.setDECL_VAL(fpoAmendEntity.getDECL_VAL());
		fpoItemDet.setDUTY(fpoAmendEntity.getDUTY());
		fpoItemDet.setDUTY_FG(fpoAmendEntity.getDUTY_FG());

		fpoItemDet.setGEN_CTH(fpoAmendEntity.getCTH());
		fpoItemDet.setIGST_AMT(fpoAmendEntity.getIGST_AMT());
		fpoItemDet.setIGST_AMT_FG(fpoAmendEntity.getIGST_AMT_FG());
		fpoItemDet.setIGST_NOTN(fpoAmendEntity.getIGST_NOTN());
		fpoItemDet.setIGST_NSNO(fpoAmendEntity.getIGST_NSNO());
		fpoItemDet.setIGST_RTA(fpoAmendEntity.getIGST_RTA());

		fpoItemDet.setITEM_DESC(fpoAmendEntity.getITEM_DESC());
		fpoItemDet.setITEM_ID(fpoAmendEntity.getITEM_ID());
		fpoItemDet.setITEM_NO(fpoAmendEntity.getITEM_NO());
		fpoItemDet.setITEM_REVISEDDESC(fpoAmendEntity.getITEM_DESC());
		// fpoItemDet.setJOB_DT(fpoAmendEntity.getD);
		// fpoItemDet.setJOB_NO(jOB_NO);
		fpoItemDet.setMESG_TYPE_CD(fpoAmendEntity.getMESG_TYPE_CD());
		fpoItemDet.setMODIFIED(fpoAmendEntity.getMODIFIED());
		fpoItemDet.setNETWT(fpoAmendEntity.getNETWT());
		fpoItemDet.setNOU(fpoAmendEntity.getNOU());
		fpoItemDet.setOFF_ID(fpoAmendEntity.getOFF_ID());
		fpoItemDet.setORIGCNTRYCD(fpoAmendEntity.getORIGCNTRYCD());
		fpoItemDet.setPOSTINGDT(fpoAmendEntity.getPOSTINGDT());
		fpoItemDet.setRate(fpoAmendEntity.getCURR_RATE());
		fpoItemDet.setROLE(fpoAmendEntity.getROLE());
		fpoItemDet.setSW_AMT(fpoAmendEntity.getSW_AMT());
		fpoItemDet.setSW_AMT_FG(fpoAmendEntity.getSW_AMT_FG());
		fpoItemDet.setSW_NOTN(fpoAmendEntity.getSW_NOTN());
		fpoItemDet.setSW_NSNO(fpoAmendEntity.getSW_NSNO());
		fpoItemDet.setSW_RTA(fpoAmendEntity.getSW_RTA());
		fpoItemDet.setUNIQUE_ID(fpoAmendEntity.getUNIQUE_ID());
		return fpoItemDet;
	}

	public void moveItemToAmend(FPO_ITEM_DET fpoItemDet, Long amendSerialvalue, HttpSession session,
			HttpServletRequest request) {
		java.util.Date utilDate = new java.util.Date();

		FPO_AMEND fpoAmendEntity = new FPO_AMEND();
		fpoAmendEntity.setAMEND_DT(utilDate);
		fpoAmendEntity.setAMEND_FLAG("U");

		fpoAmendEntity.setCURR_RATE(fpoItemDet.getRate());
		fpoAmendEntity.setCURRCD(fpoItemDet.getCURRCD());
		fpoAmendEntity.setCUS_SITE(fpoItemDet.getCUS_SITE());
		fpoAmendEntity.setDECL_VAL(fpoItemDet.getDECL_VAL());

		fpoAmendEntity.setBCD_AMT(fpoItemDet.getBCD_AMT());
		fpoAmendEntity.setBCD_AMT_FG(fpoItemDet.getBCD_AMT_FG());

		fpoAmendEntity.setIGST_AMT_FG(fpoItemDet.getIGST_AMT());
		fpoAmendEntity.setIGST_AMT(fpoItemDet.getIGST_AMT());

		fpoAmendEntity.setSW_AMT(fpoItemDet.getSW_AMT());
		fpoAmendEntity.setSW_AMT_FG(fpoItemDet.getSW_AMT_FG());

		fpoAmendEntity.setDUTY(fpoItemDet.getDUTY());
		fpoAmendEntity.setDUTY_FG(fpoItemDet.getDUTY_FG());

		fpoAmendEntity.setCIN_NO(fpoItemDet.getCinNo());
		fpoAmendEntity.setCin_DT(fpoItemDet.getCIN_DT());
		fpoAmendEntity.setCTH(fpoItemDet.getCTH());
		fpoAmendEntity.setCTH_REVISED(fpoItemDet.getCTH_REVISED());
		fpoAmendEntity.setGEN_CTH(fpoItemDet.getGEN_CTH());

		fpoAmendEntity.setASSESS_VAL(fpoItemDet.getASSESS_VAL());
		fpoAmendEntity.setASSVAL_INSFRT(fpoItemDet.getASSVAL_INSFRT());

		fpoAmendEntity.setITEM_DESC(fpoItemDet.getITEM_DESC());
		fpoAmendEntity.setITEM_REVISEDDESC(fpoItemDet.getITEM_REVISEDDESC());
		fpoAmendEntity.setITEM_ID(fpoItemDet.getITEM_ID());
		fpoAmendEntity.setITEM_NO(fpoItemDet.getITEM_NO());
		fpoAmendEntity.setITEM_UNIQUE(1l);
		fpoAmendEntity.setMESG_TYPE_CD(fpoItemDet.getMESG_TYPE_CD());
		fpoAmendEntity.setMODIFIED("Y");// fpoItemDet.getMODIFIED()
		fpoAmendEntity.setAMEND_FLAG(fpoItemDet.getAMEND_FLAG());
		fpoAmendEntity.setNETWT(fpoItemDet.getNETWT());
		fpoAmendEntity.setNOU(fpoItemDet.getNOU());
		fpoAmendEntity.setOFF_ID(fpoItemDet.getOFF_ID());
		fpoAmendEntity.setCURR_RATE(fpoItemDet.getRate());
		fpoAmendEntity.setORIGCNTRYCD(fpoItemDet.getORIGCNTRYCD());
		fpoAmendEntity.setPOSTINGDT(fpoItemDet.getPOSTINGDT());
		fpoAmendEntity.setROLE(fpoItemDet.getROLE());
		fpoAmendEntity.setUNIQUE_ID(fpoItemDet.getUNIQUE_ID());
		fpoAmendEntity.setOFF_ID(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpoAmendEntity.setROLE(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		fpoAmendEntity.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpoAmendEntity.setAMEND_SERIAL_NO(amendSerialvalue);

		if (null != fpoItemDet.getBCD_NOTN() && !fpoItemDet.getBCD_NSNO().isEmpty()
				&& !fpoItemDet.getBCD_NOTN().equals("Select NOTN"))
			fpoAmendEntity.setBCD_NOTN(fpoItemDet.getBCD_NOTN());
		else
			fpoAmendEntity.setBCD_NOTN(null);

		if (null != fpoItemDet.getBCD_NSNO() && !fpoItemDet.getBCD_NSNO().isEmpty()
				&& !fpoItemDet.getBCD_NSNO().equals("Select NSNO"))
			fpoAmendEntity.setBCD_NSNO(fpoItemDet.getBCD_NSNO());
		else
			fpoAmendEntity.setBCD_NSNO(null);

		if (null != fpoItemDet.getIGST_NOTN() && !fpoItemDet.getIGST_NOTN().isEmpty()
				&& !fpoItemDet.getIGST_NOTN().equals("Select NOTN"))
			fpoAmendEntity.setIGST_NOTN(fpoItemDet.getIGST_NOTN());
		else
			fpoAmendEntity.setIGST_NOTN(null);

		if (null != fpoItemDet.getIGST_NSNO() && !fpoItemDet.getIGST_NSNO().isEmpty()
				&& !fpoItemDet.getIGST_NSNO().equals("Select NSNO"))
			fpoAmendEntity.setIGST_NSNO(fpoItemDet.getIGST_NSNO());
		else
			fpoAmendEntity.setIGST_NSNO(null);

		fpoAmendEntity.setBCD_RTA(fpoItemDet.getBCD_RTA());
		fpoAmendEntity.setIGST_RTA(fpoItemDet.getIGST_RTA());
		fpoAmendEntity.setSW_RTA(fpoItemDet.getSW_RTA());

		if (null != fpoItemDet.getSW_NOTN() && !fpoItemDet.getSW_NOTN().isEmpty()
				&& !fpoItemDet.getSW_NOTN().equals("Select NOTN"))
			fpoAmendEntity.setSW_NOTN(fpoItemDet.getSW_NOTN());
		else
			fpoAmendEntity.setSW_NOTN(null);

		if (null != fpoItemDet.getSW_NSNO() && !fpoItemDet.getSW_NSNO().isEmpty()
				&& !fpoItemDet.getSW_NSNO().equals("Select NSNO"))
			fpoAmendEntity.setSW_NSNO(fpoItemDet.getSW_NSNO());
		else
			fpoAmendEntity.setSW_NSNO(null);

		fpoAmendRpo.save(fpoAmendEntity);
	}

	public void moveMainToAmend(FPO_MAIN fpoMain, HttpSession session) {
		java.util.Date utilDate = new java.util.Date();

		FpoMainAmend fpoMainAmend = new FpoMainAmend();

		Long amendSerialNor;

		if (fpoMainAmendRepo.getcouOnCin(fpoMain.getId()) > 0)
			amendSerialNor = fpoMainAmendRepo.getFpoAmendOnCinItemNo(fpoMain.getId()) + 1;
		else
			amendSerialNor = 1l;

		/*
		 * if (null != amendSerialNor) { amendSerialNor = amendSerialNor + 1; } else {
		 * amendSerialNor = 1L; }
		 */

		fpoMainAmend.setAMEND_SERIAL_NO(amendSerialNor);
		fpoMainAmend.setAMEND_FLAG("U");
		fpoMainAmend.setAMEND_DT(utilDate);
		fpoMainAmend.setCinNo(fpoMain.getId());
		fpoMainAmend.setCIN_DT(fpoMain.getCIN_DT());
		fpoMainAmend.setITEM_ID(fpoMain.getITEM_ID());
		fpoMainAmend.setPOSTINGDT(fpoMain.getPOSTINGDT());
		fpoMainAmend.setCUS_SITE(fpoMain.getCUS_SITE());
		fpoMainAmend.setTOT_DUTY(fpoMain.getTOT_DUTY());
		fpoMainAmend.setAMEND_FLAG(fpoMain.getAMEND_FLAG());
		/*
		 * float f2=((DUTY_CALC_DETAILS)
		 * request.getSession().getAttribute("dutyCalc")).getAssval_Amt(); float
		 * f1=fpoMain.getTOT_AMT_PAYABLE(); if (((DUTY_CALC_DETAILS)
		 * request.getSession().getAttribute("dutyCalc")).getCategory().equals(fpoMain.
		 * getNATURE_TYPE_CD().trim()))
		 * fpoMainAmend.setTOT_AMT_PAYABLE(fpoMain.getTOT_AMT_PAYABLE()); else if
		 * (Float.compare(f1, f2) > 0) {System.out.println("coms in greater");
		 * fpoMainAmend.setTOT_AMT_PAYABLE(fpoMain.getTOT_AMT_PAYABLE());} else {
		 * System.out.println("coms in lesser"); fpoMainAmend.setTOT_AMT_PAYABLE(0f);}
		 */
		fpoMainAmend.setTotalDutyFg(fpoMain.getTotalDutyFg());
		fpoMainAmend.setTOT_ASS_VAL(fpoMain.getTOT_ASS_VAL());
		fpoMainAmend.setTOTASS_CALC_VAL(fpoMain.getTOTASS_CALC_VAL());
		fpoMainAmend.setTOT_DECL_VAL(fpoMain.getTOT_DECL_VAL());
		fpoMainAmend.setTOT_AMT_PAYABLE(fpoMain.getTOT_AMT_PAYABLE());
		fpoMainAmend.setINS_CALC_VAL(fpoMain.getINS_CALC_VAL());
		fpoMainAmend.setFRT_CALC_VAL(fpoMain.getFRT_CALC_VAL());
		if (fpoMain.getINS_CURR_RATE() != null)
			fpoMainAmend.setINS_CURR_RATE(fpoMain.getINS_CURR_RATE());
		if (fpoMain.getFRT_CURR_RATE() != null)
			fpoMainAmend.setFRT_CURR_RATE(fpoMain.getFRT_CURR_RATE());
		if (fpoMain.getINS_VAL() != null)
			fpoMainAmend.setINS_VAL(fpoMain.getINS_VAL());
		if (fpoMain.getPOSTAGE_AMT() != null)
			fpoMainAmend.setPOSTAGE_AMT(fpoMain.getPOSTAGE_AMT());
		fpoMainAmend.setOFF_ID(fpoMain.getOFF_ID());
		fpoMainAmend.setROLE(fpoMain.getROLE());
		fpoMainAmendRepo.save(fpoMainAmend);
	}

	public FPO_MAIN getPojoMain(FPO_MAIN t) {

		/*
		 * if (null == t.getCUS_SITE() || t.getCUS_SITE().isEmpty())
		 * t.setCUS_SITE("- NIL -");
		 * 
		 * if (null == t.getCUST_ORG_CD() || t.getCUST_ORG_CD().isEmpty())
		 * t.setCUST_ORG_CD("- NIL -");
		 * 
		 * if (null == t.getDECLARATION_PID() || t.getDECLARATION_PID().isEmpty())
		 * t.setDECLARATION_PID("- NIL -");
		 * 
		 * if (null == t.getDECLARATION_PID() || t.getDECLARATION_PID().isEmpty())
		 * t.setDECLARATION_PID("- NIL -");
		 * 
		 * if (null == t.getDESTPOST_ORG_CD() || t.getDESTPOST_ORG_CD().isEmpty())
		 * t.setDESTPOST_ORG_CD("- NIL -");
		 * 
		 * if (null == t.getDOCUMENTS() || t.getDOCUMENTS().isEmpty())
		 * t.setDOCUMENTS("- NIL -");
		 * 
		 * if (null == t.getFPO_CODE() || t.getFPO_CODE().isEmpty())
		 * t.setFPO_CODE("- NIL -");
		 * 
		 * if (null == t.getGROSS_WT().toString() ||
		 * t.getGROSS_WT().toString().isEmpty()) t.setGROSS_WT(0f);
		 * 
		 * if (null == t.getHANDLING_CLASS_CD() || t.getHANDLING_CLASS_CD().isEmpty())
		 * t.setHANDLING_CLASS_CD("-");
		 * 
		 * if (null == t.getId() || t.getId().isEmpty()) t.setId("- NIL -");
		 * 
		 * /* if (null == t.getINS_VAL() || t.getINS_VAL().toString().isEmpty())
		 * t.setINS_VAL(0l);
		 * 
		 * if (null == t.getINS_VAL_CURRCD() || t.getINS_VAL_CURRCD().isEmpty())
		 * t.setINS_VAL_CURRCD("- NIL -");
		 */

		/*
		 * if (null == t.getINTERPRETATION() || t.getINTERPRETATION().isEmpty())
		 * t.setINTERPRETATION("- NIL -");
		 * 
		 * if (null == t.getITEM_ID() || t.getITEM_ID().isEmpty())
		 * t.setITEM_ID("- NIL -");
		 * 
		 * if (null == t.getJOB_NO() || t.getJOB_NO().toString().isEmpty())
		 * t.setJOB_NO(0l);
		 * 
		 * if (null == t.getLOCALID() || t.getLOCALID().isEmpty())
		 * t.setLOCALID("- NIL -");
		 * 
		 * if (null == t.getLOCALID2() || t.getLOCALID2().isEmpty())
		 * t.setLOCALID2("- NIL -");
		 * 
		 * if (null == t.getMAIL_CATEGORY_CD() || t.getMAIL_CATEGORY_CD().isEmpty())
		 * t.setMAIL_CATEGORY_CD("- NIL -");
		 * 
		 * if (null == t.getMAIL_CLASS_CD() || t.getMAIL_CLASS_CD().isEmpty())
		 * t.setMAIL_CLASS_CD("- NIL -");
		 * 
		 * if (null == t.getMAIL_PID() || t.getMAIL_PID().isEmpty())
		 * t.setMAIL_PID("- NIL -");
		 * 
		 * if (null == t.getMAIL_STATE_CD() || t.getMAIL_STATE_CD().isEmpty())
		 * t.setMAIL_STATE_CD("- NIL -");
		 * 
		 * if (null == t.getMAIL_STATE_REMARKS() || t.getMAIL_STATE_REMARKS().isEmpty())
		 * t.setMAIL_STATE_REMARKS("- NIL -");
		 * 
		 * if (null == t.getMEASURED_GROSS_WT() ||
		 * t.getMEASURED_GROSS_WT().toString().isEmpty()) t.setMEASURED_GROSS_WT(0l);
		 * 
		 * if (null == t.getMESG_TYPE_CD() || t.getMESG_TYPE_CD().isEmpty())
		 * t.setMESG_TYPE_CD("- NIL -");
		 * 
		 * if (null == t.getMODIFIED() || t.getMODIFIED().isEmpty())
		 * t.setMODIFIED("- NIL -");
		 * 
		 * if (null == t.getNATURE_TYPE_CD() || t.getNATURE_TYPE_CD().isEmpty())
		 * t.setNATURE_TYPE_CD("- NIL -");
		 * 
		 * if (null == t.getORIGPOST_ORG_CD() || t.getORIGPOST_ORG_CD().isEmpty())
		 * t.setORIGPOST_ORG_CD("- NIL -");
		 * 
		 * if (null == t.getPINCODE() || t.getPINCODE().isEmpty())
		 * t.setPINCODE("- NIL -");
		 * 
		 * if (null == t.getPOST_ORG_CD() || t.getPOST_ORG_CD().isEmpty())
		 * t.setPOST_ORG_CD("- NIL -");
		 * 
		 * if (null == t.getPOSTAGE_AMT() || t.getPOSTAGE_AMT().toString().isEmpty())
		 * t.setPOSTAGE_AMT(0l);
		 * 
		 * if (null == t.getPOSTAGE_CURR_CD() || t.getPOSTAGE_CURR_CD().isEmpty())
		 * t.setPOSTAGE_CURR_CD("- NIL -");
		 * 
		 * if (null == t.getPOSTINGDT() || t.getPOSTINGDT().isEmpty())
		 * t.setPOSTINGDT("- NIL -");
		 * 
		 * if (null == t.getRECP_ADD1() || t.getRECP_ADD1().isEmpty())
		 * t.setRECP_ADD1("- NIL -");
		 * 
		 * if (null == t.getRECP_ADD2() || t.getRECP_ADD2().isEmpty())
		 * t.setRECP_ADD2("- NIL -");
		 * 
		 * if (null == t.getRECP_CITY() || t.getRECP_CITY().isEmpty())
		 * t.setRECP_CITY("- NIL -");
		 * 
		 * if (null == t.getRECP_CNTRY_CD() || t.getRECP_CNTRY_CD().isEmpty())
		 * t.setRECP_CNTRY_CD("- NIL -");
		 * 
		 * if (null == t.getRECP_EMAILID() || t.getRECP_EMAILID().isEmpty())
		 * t.setRECP_EMAILID("- NIL -");
		 * 
		 * if (null == t.getRECP_FAX() || t.getRECP_FAX().isEmpty())
		 * t.setRECP_FAX("- NIL -");
		 * 
		 * if (null == t.getRECP_FNAME() || t.getRECP_FNAME().isEmpty())
		 * t.setRECP_FNAME("- NIL -");
		 * 
		 * if (null == t.getRECP_IDREF() || t.getRECP_IDREF().isEmpty())
		 * t.setRECP_IDREF("- NIL -");
		 * 
		 * if (null == t.getRECP_LNAME() || t.getRECP_LNAME().isEmpty())
		 * t.setRECP_LNAME("- NIL -");
		 * 
		 * if (null == t.getRECP_NAME() || t.getRECP_NAME().isEmpty())
		 * t.setRECP_NAME("- NIL -");
		 * 
		 * if (null == t.getRECP_PHONE() || t.getRECP_PHONE().isEmpty())
		 * t.setRECP_PHONE("- NIL -");
		 * 
		 * if (null == t.getRECP_STATE() || t.getRECP_STATE().isEmpty())
		 * t.setRECP_STATE("- NIL -");
		 * 
		 * if (null == t.getRECP_ZIP() || t.getRECP_ZIP().isEmpty())
		 * t.setRECP_ZIP("- NIL -");
		 * 
		 * if (null == t.getSEND_ADD1() || t.getSEND_ADD1().isEmpty())
		 * t.setSEND_ADD1("- NIL -");
		 * 
		 * if (null == t.getSEND_ADD2() || t.getSEND_ADD2().isEmpty())
		 * t.setSEND_ADD2("- NIL -");
		 * 
		 * if (null == t.getSEND_CITY() || t.getSEND_CITY().isEmpty())
		 * t.setSEND_CITY("- NIL -");
		 * 
		 * if (null == t.getSEND_CNTRY_CD() || t.getSEND_CNTRY_CD().isEmpty())
		 * t.setSEND_CNTRY_CD("- NIL -");
		 * 
		 * if (null == t.getSEND_EMAILID() || t.getSEND_EMAILID().isEmpty())
		 * t.setSEND_EMAILID("- NIL -");
		 * 
		 * if (null == t.getSEND_FNAME() || t.getSEND_FNAME().isEmpty())
		 * t.setSEND_FNAME("- NIL -");
		 * 
		 * if (null == t.getSEND_IDREF() || t.getSEND_IDREF().isEmpty())
		 * t.setSEND_IDREF("- NIL -");
		 * 
		 * if (null == t.getSEND_LNAME() || t.getSEND_LNAME().isEmpty())
		 * t.setSEND_LNAME("- NIL -");
		 * 
		 * if (null == t.getSEND_NAME() || t.getSEND_NAME().isEmpty())
		 * t.setSEND_NAME("- NIL -");
		 * 
		 * if (null == t.getSEND_PHONE() || t.getSEND_PHONE().isEmpty())
		 * t.setSEND_PHONE("- NIL -");
		 * 
		 * if (null == t.getSEND_STATE() || t.getSEND_STATE().isEmpty())
		 * t.setSEND_STATE("- NIL -");
		 * 
		 * if (null == t.getSEND_ZIP() || t.getSEND_ZIP().isEmpty())
		 * t.setSEND_ZIP("- NIL -");
		 * 
		 * if (null == t.getTOT_ASS_VAL() || t.getTOT_ASS_VAL().toString().isEmpty())
		 * t.setTOT_ASS_VAL(0F);
		 * 
		 * if (null == t.getTOT_DECL_VAL() || t.getTOT_DECL_VAL().toString().isEmpty())
		 * t.setTOT_DECL_VAL(0l);
		 * 
		 * /* if (null == t.getTOT_DUTY() || t.getTOT_DUTY().toString().isEmpty())
		 * t.setTOT_DUTY(0l);
		 */

		/*
		 * if (null == t.getTOT_DUTY() || t.getTOT_DUTY().toString().isEmpty())
		 * t.setTOT_DUTY(0f);
		 * 
		 * if (null == t.getTotalDutyFg() || t.getTotalDutyFg().toString().isEmpty())
		 * t.setTotalDutyFg(0f);
		 * 
		 * if (null == t.getTRANS_MODE() || t.getTRANS_MODE().isEmpty())
		 * t.setTRANS_MODE("- NIL -");
		 * 
		 * if (null == t.getUNIQUE_ID() || t.getUNIQUE_ID().isEmpty())
		 * t.setUNIQUE_ID("- NIL -");
		 */

		return t;
	}

	public List<FPO_ITEM_DET> getListPojo(List<FPO_ITEM_DET> fpoList) {

		for (FPO_ITEM_DET t : fpoList) {
			if (null == t.getGEN_CTH() || t.getGEN_CTH().isEmpty())
				t.setGEN_CTH("- NIL -");

			if (null == t.getNETWT() || t.getNETWT().toString().isEmpty())
				t.setNETWT((float) 0l);

			if (null == t.getNOU() || t.getNOU().toString().isEmpty())
				t.setNOU(0l);

			if (null == t.getDECL_VAL() || t.getDECL_VAL().toString().isEmpty())
				t.setDECL_VAL(0f);

			if (null == t.getCURRCD() || t.getCURRCD().isEmpty())
				t.setCURRCD("- NIL -");

			if (null == t.getCTH() || t.getCTH().isEmpty())
				t.setCTH("- NIL -");

			if (null == t.getCTH_REVISED() || t.getCTH_REVISED().isEmpty())
				t.setCTH_REVISED("- NIL -");

			if (null == t.getORIGCNTRYCD() || t.getORIGCNTRYCD().isEmpty())
				t.setORIGCNTRYCD("- NIL -");
		}
		return fpoList;
	}

	public FPO_ITEM_DET getPojo(FPO_ITEM_DET t) {

		if (null == t.getGEN_CTH() || t.getGEN_CTH().isEmpty())
			t.setGEN_CTH("- NIL -");

		if (null == t.getNETWT() || t.getNETWT().toString().isEmpty())
			t.setNETWT((float) 0l);

		if (null == t.getNOU() || t.getNOU().toString().isEmpty())
			t.setNOU(0l);

		if (null == t.getDECL_VAL() || t.getDECL_VAL().toString().isEmpty())
			t.setDECL_VAL(0f);

		if (null == t.getCURRCD() || t.getCURRCD().isEmpty())
			t.setCURRCD("-");

		if (null == t.getCTH() || t.getCTH().isEmpty())
			t.setCTH("- NIL -");

		if (null == t.getCTH_REVISED() || t.getCTH_REVISED().isEmpty())
			t.setCTH_REVISED("- NIL -");

		if (null == t.getORIGCNTRYCD() || t.getORIGCNTRYCD().isEmpty())
			t.setORIGCNTRYCD("-");

		return t;
	}

	public C_CUSITM getPojo(C_CUSITM t) {

		if (null == t.getCUS_SITE() || t.getCUS_SITE().isEmpty())
			t.setCUS_SITE("- NIL -");

		if (null == t.getCUST_ORG_CD() || t.getCUST_ORG_CD().isEmpty())
			t.setCUST_ORG_CD("- NIL -");

		if (null == t.getCUST_ORG_CD() || t.getCUST_ORG_CD().isEmpty())
			t.setCUST_ORG_CD("- NIL -");

		if (null == t.getDECLARATION_PID() || t.getDECLARATION_PID().isEmpty())
			t.setDECLARATION_PID("- NIL -");

		if (null == t.getDESTPOST_ORG_CD() || t.getDESTPOST_ORG_CD().isEmpty())
			t.setDESTPOST_ORG_CD("- NIL -");

		if (null == t.getDOCUMENTS() || t.getDOCUMENTS().isEmpty())
			t.setDOCUMENTS("- NIL -");

		if (null == t.getGROSS_WT() || t.getGROSS_WT().toString().isEmpty())
			t.setGROSS_WT(0l);

		if (null == t.getHANDLING_CLASS_CD() || t.getHANDLING_CLASS_CD().isEmpty())
			t.setHANDLING_CLASS_CD("- NIL -");

		if (null == t.getId() || t.getId().isEmpty())
			t.setId("- NIL -");

		if (null == t.getINS_VAL() || t.getINS_VAL().toString().isEmpty())
			t.setINS_VAL(0l);

		if (null == t.getINS_VAL_CURRCD() || t.getINS_VAL_CURRCD().isEmpty())
			t.setINS_VAL_CURRCD("- NIL -");

		if (null == t.getJOB_NO() || t.getJOB_NO().toString().isEmpty())
			t.setJOB_NO(0l);

		if (null == t.getLOCALID() || t.getLOCALID().isEmpty())
			t.setLOCALID("- NIL -");

		if (null == t.getLOCALID2() || t.getLOCALID2().isEmpty())
			t.setLOCALID2("- NIL -");

		if (null == t.getMAIL_CATEGORY_CD() || t.getMAIL_CATEGORY_CD().isEmpty())
			t.setMAIL_CATEGORY_CD("- NIL -");

		if (null == t.getMAIL_STATE_REMARKS() || t.getMAIL_STATE_REMARKS().isEmpty())
			t.setMAIL_STATE_REMARKS("- NIL -");

		if (null == t.getMAIL_STATE_CD() || t.getMAIL_STATE_CD().isEmpty())
			t.setMAIL_STATE_CD("- NIL -");

		if (null == t.getMESG_TYPE_CD() || t.getMESG_TYPE_CD().isEmpty())
			t.setMESG_TYPE_CD("- NIL -");

		if (null == t.getNATURE_TYPE_CD() || t.getNATURE_TYPE_CD().isEmpty())
			t.setNATURE_TYPE_CD("- NIL -");

		if (null == t.getNATURE_TYPE_CD() || t.getNATURE_TYPE_CD().isEmpty())
			t.setNATURE_TYPE_CD("- NIL -");

		if (null == t.getORIGPOST_ORGCD() || t.getORIGPOST_ORGCD().isEmpty())
			t.setORIGPOST_ORGCD("- NIL -");

		if (null == t.getPOST_ORG_CD() || t.getPOST_ORG_CD().isEmpty())
			t.setPOST_ORG_CD("- NIL -");

		if (null == t.getPOSTAGE_AMT() || t.getPOSTAGE_AMT().toString().isEmpty())
			t.setPOSTAGE_AMT(0l);

		if (null == t.getPOSTAGE_CURR_CD() || t.getPOSTAGE_CURR_CD().isEmpty())
			t.setPOSTAGE_CURR_CD("- NIL -");

		if (null == t.getPOSTINGDT() || t.getPOSTINGDT().isEmpty())
			t.setORIGPOST_ORGCD("- NIL -");

		if (null == t.getRECP_ADD1() || t.getRECP_ADD1().isEmpty())
			t.setRECP_ADD1("- NIL -");

		if (null == t.getRECP_ADD2() || t.getRECP_ADD2().isEmpty())
			t.setRECP_ADD2("- NIL -");

		if (null == t.getRECP_CITY() || t.getRECP_CITY().isEmpty())
			t.setRECP_CITY("- NIL -");

		if (null == t.getRECP_CNTRY_CD() || t.getRECP_CNTRY_CD().isEmpty())
			t.setRECP_CNTRY_CD("- NIL -");

		if (null == t.getRECP_EMAILID() || t.getRECP_EMAILID().isEmpty())
			t.setRECP_EMAILID("- NIL -");

		if (null == t.getRECP_FAX() || t.getRECP_FAX().isEmpty())
			t.setRECP_FAX("- NIL -");

		if (null == t.getRECP_FNAME() || t.getRECP_FNAME().isEmpty())
			t.setRECP_FNAME("- NIL -");

		if (null == t.getRECP_FNAME() || t.getRECP_FNAME().isEmpty())
			t.setRECP_FNAME("- NIL -");

		if (null == t.getRECP_IDREF() || t.getRECP_IDREF().isEmpty())
			t.setRECP_IDREF("- NIL -");

		if (null == t.getRECP_LNAME() || t.getRECP_LNAME().isEmpty())
			t.setRECP_LNAME("- NIL -");

		if (null == t.getRECP_PHONE() || t.getRECP_PHONE().isEmpty())
			t.setRECP_PHONE("- NIL -");

		if (null == t.getRECP_STATE() || t.getRECP_STATE().isEmpty())
			t.setRECP_STATE("- NIL -");

		if (null == t.getRECP_ZIP() || t.getRECP_ZIP().isEmpty())
			t.setRECP_ZIP("- NIL -");

		if (null == t.getSEND_ADD1() || t.getSEND_ADD1().isEmpty())
			t.setSEND_ADD1("- NIL -");

		if (null == t.getSEND_ADD2() || t.getSEND_ADD2().isEmpty())
			t.setSEND_ADD2("- NIL -");

		if (null == t.getSEND_CITY() || t.getSEND_CITY().isEmpty())
			t.setSEND_CITY("- NIL -");

		if (null == t.getSEND_CNTRY_CD() || t.getSEND_CNTRY_CD().isEmpty())
			t.setSEND_CNTRY_CD("- NIL -");

		if (null == t.getSEND_CNTRY_CD() || t.getSEND_CNTRY_CD().isEmpty())
			t.setSEND_CNTRY_CD("- NIL -");

		if (null == t.getSEND_EMAILID() || t.getSEND_EMAILID().isEmpty())
			t.setSEND_EMAILID("- NIL -");

		if (null == t.getSEND_FNAME() || t.getSEND_FNAME().isEmpty())
			t.setSEND_FNAME("- NIL -");

		if (null == t.getSEND_IDREF() || t.getSEND_IDREF().isEmpty())
			t.setSEND_IDREF("- NIL -");

		if (null == t.getSEND_LNAME() || t.getSEND_LNAME().isEmpty())
			t.setSEND_LNAME("- NIL -");

		if (null == t.getSEND_NAME() || t.getSEND_NAME().isEmpty())
			t.setSEND_NAME("- NIL -");

		if (null == t.getSEND_PHONE() || t.getSEND_PHONE().isEmpty())
			t.setSEND_PHONE("- NIL -");

		if (null == t.getSEND_STATE() || t.getSEND_STATE().isEmpty())
			t.setSEND_STATE("- NIL -");

		if (null == t.getSEND_ZIP() || t.getSEND_ZIP().isEmpty())
			t.setSEND_ZIP("- NIL -");

		if (null == t.getTRANS_MODE() || t.getTRANS_MODE().isEmpty())
			t.setTRANS_MODE("- NIL -");

		if (null == t.getTYPE_CD() || t.getTYPE_CD().isEmpty())
			t.setTYPE_CD("- NIL -");

		if (null == t.getUNIQUE_ID() || t.getUNIQUE_ID().isEmpty())
			t.setUNIQUE_ID("- NIL -");

		return t;
	}

	public List<Object> bcdCalculation(List<Object> getBcdRate, List<FPO_MAIN> fpoMainData,
			List<FPO_ITEM_DET> fpoItems) {

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);

		List<FPO_ITEM_DET> fpoItemDET = fpoDeclaredService.getItemOnCin(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());

		FPO_AMEND fpoAmend = fpoAmendRpo.getLatestAmendOnCinAndItemNo(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());

		Float basicValue = 0.01f;
		Float oneItemTotalDutyFg = null;
		Float assessVal = fpoItems.get(0).getASSVAL_INSFRT();
		System.out.println(assessVal);
		Float bcdRate = Float.parseFloat(getBcdRate.get(0).toString());
		Float previousBcdDuty = 0.0F;

		if (null != fpoAmend)
			previousBcdDuty = fpoAmend.getBCD_AMT();
		else
			previousBcdDuty = fpoItemDET.get(0).getBCD_AMT();

		Float currentBcdDuty = assessVal * bcdRate * basicValue;
		Float currentBcdDutyFg = previousBcdDuty - currentBcdDuty;

		Float previousSwDuty = 0.0F;
		Float previousIgstDuty = 0.0F;

		if (null != fpoAmend)
			previousSwDuty = fpoAmend.getSW_AMT();
		else
			previousSwDuty = fpoItemDET.get(0).getSW_AMT();

		Float currentSwDuty = currentBcdDuty * fpoItemDET.get(0).getSW_RTA() * basicValue;
		Float currentSwDutyFg = previousSwDuty - currentSwDuty;

		if (null != fpoAmend)
			previousIgstDuty = fpoAmend.getIGST_AMT();
		else
			previousIgstDuty = fpoItemDET.get(0).getIGST_AMT();

		Float currentIgstDuty = (assessVal + currentBcdDuty + currentSwDuty) * fpoItemDET.get(0).getIGST_RTA()
				* basicValue;
		Float currentIgstDutyFg = previousIgstDuty - currentIgstDuty;

		Float oneItemtotalDuty = currentBcdDuty + currentIgstDuty + currentSwDuty;

		/*
		 * if (null != fpoItems.get(0).getIGST_AMT_FG() && null !=
		 * fpoItems.get(0).getSW_AMT_FG()) oneItemTotalDutyFg = currentBcdDutyFg +
		 * fpoItems.get(0).getIGST_AMT_FG() + fpoItems.get(0).getSW_AMT_FG(); else if
		 * (null != fpoItems.get(0).getIGST_AMT_FG() && null ==
		 * fpoItems.get(0).getSW_AMT_FG()) oneItemTotalDutyFg = currentBcdDutyFg +
		 * fpoItems.get(0).getIGST_AMT_FG(); else if (null ==
		 * fpoItems.get(0).getIGST_AMT_FG() && null != fpoItems.get(0).getSW_AMT_FG())
		 * oneItemTotalDutyFg = currentBcdDutyFg + fpoItems.get(0).getSW_AMT_FG(); else
		 * oneItemTotalDutyFg = currentBcdDutyFg;
		 */

		oneItemTotalDutyFg = currentBcdDutyFg + currentSwDutyFg + currentIgstDutyFg;

		Float[] bcdToTalDuty = getSumOfAllDuty(fpoItems, oneItemtotalDuty, oneItemTotalDutyFg);

		getBcdRate.add(0, Float.valueOf(df2.format(bcdRate)));
		getBcdRate.add(1, Float.valueOf(df2.format(currentBcdDuty)));
		getBcdRate.add(2, Float.valueOf(df2.format(currentBcdDutyFg)));
		getBcdRate.add(3, Float.valueOf(df2.format(oneItemtotalDuty)));
		getBcdRate.add(4, Float.valueOf(df2.format(oneItemTotalDutyFg)));
		getBcdRate.add(5, Float.valueOf(df2.format(bcdToTalDuty[0])));
		getBcdRate.add(6, Float.valueOf(df2.format(bcdToTalDuty[1])));
		System.out.println(df2.format(bcdRate));
		System.out.println(df2.format(currentBcdDuty));
		System.out.println(df2.format(currentBcdDutyFg));
		System.out.println(df2.format(oneItemtotalDuty));
		System.out.println(df2.format(oneItemTotalDutyFg));
		System.out.println(df2.format(bcdToTalDuty[0]));
		System.out.println(df2.format(bcdToTalDuty[1]));

		// Float[] igstToTalDuty = getSumOfAllDuty(fpoItems, oneItemtotalDuty,
		// oneItemTotalDutyFg);

		getBcdRate.add(7, Float.valueOf(df2.format(fpoItemDET.get(0).getIGST_RTA())));
		getBcdRate.add(8, Float.valueOf(df2.format(currentIgstDuty)));
		getBcdRate.add(9, Float.valueOf(df2.format(currentIgstDutyFg)));
//		getBcdRate.add(10, Float.valueOf(df2.format(igstToTalDuty[0])));
//		getBcdRate.add(11, Float.valueOf(df2.format(igstToTalDuty[1])));

//		Float[] swToTalDuty = getSumOfAllDuty(fpoItems, oneItemtotalDuty, oneItemTotalDutyFg);

		getBcdRate.add(10, Float.valueOf(df2.format(fpoItemDET.get(0).getSW_RTA())));
		getBcdRate.add(11, Float.valueOf(df2.format(currentSwDuty)));
		getBcdRate.add(12, Float.valueOf(df2.format(currentSwDutyFg)));
//		getBcdRate.add(15, Float.valueOf(df2.format(swToTalDuty[0])));
//		getBcdRate.add(16, Float.valueOf(df2.format(swToTalDuty[1])));

		return getBcdRate;

	}

	public FpoQueryDecision movqrytoassdata(FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt, HttpSession session,
			HttpServletRequest request) {
		System.out.println("fpoqryDec.getCIN_NO()=" + fpoqryDec.getCIN_NO());
		System.out.println("fpomvmnt.getCinNo()=" + fpomvmnt.getCinNo());
		String Cinno = fpoqryDec.getCIN_NO();
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		String offid = fpoMvmntRepo.getoffid(Cinno);
		String role = fpoMvmntRepo.getrole(Cinno);
		System.out.println("cinno=" + Cinno + "offid=" + offid + "role=" + role);
		System.out.println("Logincontroller cinno=" + request.getSession().getAttribute("cinno") + "offid="
				+ request.getSession().getAttribute("offId") + "role=" + request.getSession().getAttribute("role"));
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
//		    insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "IMP", "A3");
		updfpocurque(Cinno, "P1", request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString(), utilDate, session, request);
		offid = fpoQueryDecisionRepo.getoffidcin(Cinno);
		fpoQueryDecisionRepo.updqryASS(Cinno, utilDate, offid);
		return fpoqryDec;
	}

	public FpoQueryDecision movqrytoedidata(FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt, HttpSession session,
			HttpServletRequest request) {
		System.out.println("fpoqryDec.getCIN_NO()=" + fpoqryDec.getCIN_NO());
		System.out.println("fpomvmnt.getCinNo()=" + fpomvmnt.getCinNo());
		String Cinno = fpoqryDec.getCIN_NO();
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		String offid = fpoMvmntRepo.getoffid(Cinno);
		String role = fpoMvmntRepo.getrole(Cinno);
		System.out.println("cinno=" + Cinno + "offid=" + offid + "role=" + role);
		System.out.println("Logincontroller cinno=" + request.getSession().getAttribute("cinno") + "offid="
				+ request.getSession().getAttribute("offId") + "role=" + request.getSession().getAttribute("role"));
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno,
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				"P6", session, request);
		fpoQueryDecisionRepo.updqrydecioffid(fpoqryDec.getCIN_NO(),
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString());
		updfpocurque(Cinno, "P6", "EDI", utilDate, session, request);
		fpoQueryDecisionRepo.updqryEDI(fpoqryDec.getCIN_NO(), utilDate);
		return fpoqryDec;
	}

	public String getAssCinNo(String cinNo) {
		Long assNo = FPOrepost.getassCount(cinNo);
		if (null != assNo) {
			return assNo.toString();
		}
		return null;
	}

	public String gettotassval(String cinNo) {
		Long assval = FPOrepost.gettotassval(cinNo);
		if (null != assval) {
			return assval.toString();
		}
		return null;
	}

	// updated on 22nd February 2022
	public void insertIntofpoMvmntDb(FpoMvmnt fpomvmnt, String Cinno, Date Cindt, Date curdt, Long slno, String Role,
			String Stage, HttpSession session, HttpServletRequest request) {
		long now = System.currentTimeMillis();
		Timestamp sqlTimestamp = new Timestamp(now);
		// fpoQueryDecisionRepo.updoffidqrydec(Cinno,request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),request.getSession().getAttribute("role")
		// == null ? null : request.getSession().getAttribute("role").toString());
		FpoMvmnt fpomvmnts = new FpoMvmnt();
		fpomvmnts.setCinNo(Cinno);
		fpomvmnts.setCinDt(Cindt);
		fpomvmnts.setCusSite(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpomvmnts.setEndDt(curdt);
		fpomvmnts.setRole(Role);
		fpomvmnts.setOffId(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpomvmnts.setSlNo(slno + 1);
		fpomvmnts.setItemid(FPOrepost.getitemid(Cinno));
//		if (slno == 1 || Stage.contains("C1") || Stage.contains("P5") || Stage.contains("O1") || Stage.contains("P6")
//				|| Stage.contains("R6") || Stage.contains("A2") || Stage.contains("A3"))
//			fpomvmnts.setStage(Stage);

		if (Role == "PSU")
			fpomvmnts.setStage(Stage);

		else if (Role == "OOC")
			fpomvmnts.setStage(Stage);

		else if (Role == "PIN")
			fpomvmnts.setStage(Stage);

		else if (slno == 1 || Stage.contains("C1") || Stage.contains("P5") || Stage.contains("O1")
				|| Stage.contains("P6") || Stage.contains("R6") || Stage.contains("A2") || Stage.contains("A3"))
			fpomvmnts.setStage(Stage);

		fpoMvmntRepo.save(fpomvmnts);
	}// updated on 22nd February 2022

	// ----------------------------added on May 12th by Sasi
	// ........................................................................
	public ModelAndView exam_view(@RequestBody FPO_EXAM fpoexam, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {
		System.out.println(request.getSession().getAttribute("cinno"));
		String cinNo = fpoexam.getCinNo();
		request.getSession().setAttribute("cinno", cinNo);
		System.out.println("CINNO IS " + cinNo);
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(cinNo);
		List<Collection> fpoitmdet = FPOrepost.getexminfo(cinNo);
		String Genrem = FPOrepost.getgenrem(cinNo);
		List<FPO_ORDER> fpoorder = fpoOrderRepost.examOrder(cinNo);
//		System.out.println("Exam order is " + fpoorder.get(0).getEXAM_ORDER());
//		System.out.println("order remark is " + fpoorder.get(0).getORDER_REMARK());
		setFpoMainValues(fpoMain);
		int choice = fpoorderService.getchoice(fpoorder.get(0).getEXAM_ORDER());
		System.out.println("choice is" + choice);
		fpoMain = fpoQueryService.getAllFpoMainData(fpoMain);
		model.addAttribute("head", getPojoMain(fpoMain.get(0)));
		model.addAttribute("examorder", fpoorder.get(0).getORDER_REMARK());
		model.addAttribute("fpoitmdet", fpoitmdet);
		model.addAttribute("choice", choice);
		model.addAttribute("Genrem", Genrem);
		ModelAndView modelAndView = new ModelAndView("import/import_exam_view");
		return modelAndView;
		// ----------------------------added on May 12th by Sasi
		// ........................................................................
	}

	public List<Object> igstCalculation(List<Object> getIgstRate, List<FPO_MAIN> fpoMainData,
			List<FPO_ITEM_DET> fpoItems) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		List<FPO_ITEM_DET> fpoItemDET = fpoDeclaredService.getItemOnCin(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());
		FPO_AMEND fpoAmend = fpoAmendRpo.getLatestAmendOnCinAndItemNo(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());
		Float basicValue = 0.01f;
		Float oneItemTotalDutyFg = null;
		Float assessVal = fpoItems.get(0).getASSVAL_INSFRT();
		Float igstRate = Float.parseFloat(getIgstRate.get(0).toString());
		Float previousIgstDuty = 0.0F;

		/*
		 * if (null != fpoAmend) previousBcdDuty = fpoAmend.getBCD_AMT(); else
		 * previousBcdDuty = fpoItemDET.get(0).getBCD_AMT();
		 * 
		 * Float currentBcdDuty = assessVal * bcdRate * basicValue; Float
		 * currentBcdDutyFg = previousBcdDuty - currentBcdDuty;
		 * 
		 * Float previousSwDuty = 0.0F; Float previousIgstDuty = 0.0F;
		 * 
		 * if (null != fpoAmend) previousSwDuty = fpoAmend.getSW_AMT(); else
		 * previousSwDuty = fpoItemDET.get(0).getSW_AMT();
		 * 
		 * 
		 * 
		 * 
		 */

		if (null != fpoAmend)
			previousIgstDuty = fpoAmend.getIGST_AMT();
		else
			previousIgstDuty = fpoItemDET.get(0).getIGST_AMT();

		Float currentIgstDuty = (fpoItems.get(0).getBCD_AMT() + fpoItems.get(0).getSW_AMT() + assessVal) * igstRate
				* basicValue;
		Float currentIgstDutyFg = previousIgstDuty - currentIgstDuty;

		Float oneItemtotalDuty = fpoItems.get(0).getBCD_AMT() + currentIgstDuty + fpoItemDET.get(0).getIGST_AMT();

		if (null != fpoItems.get(0).getBCD_AMT_FG() && null != fpoItems.get(0).getSW_AMT_FG())
			oneItemTotalDutyFg = currentIgstDutyFg + fpoItems.get(0).getBCD_AMT_FG() + fpoItems.get(0).getSW_AMT_FG();
		else if (null != fpoItems.get(0).getBCD_AMT_FG() && null == fpoItems.get(0).getSW_AMT_FG())
			oneItemTotalDutyFg = currentIgstDutyFg + fpoItems.get(0).getBCD_AMT_FG();
		else if (null == fpoItems.get(0).getBCD_AMT_FG() && null != fpoItems.get(0).getSW_AMT_FG())
			oneItemTotalDutyFg = currentIgstDutyFg + fpoItems.get(0).getSW_AMT_FG();
		else
			oneItemTotalDutyFg = currentIgstDutyFg;

		Float[] igstToTalDuty = getSumOfAllDuty(fpoItems, oneItemtotalDuty, oneItemTotalDutyFg);

		getIgstRate.add(0, Float.valueOf(df2.format(igstRate)));
		getIgstRate.add(1, Float.valueOf(df2.format(currentIgstDuty)));
		getIgstRate.add(2, Float.valueOf(df2.format(currentIgstDutyFg)));
		getIgstRate.add(3, Float.valueOf(df2.format(oneItemtotalDuty)));
		getIgstRate.add(4, Float.valueOf(df2.format(oneItemTotalDutyFg)));
		getIgstRate.add(5, Float.valueOf(df2.format(igstToTalDuty[0])));
		getIgstRate.add(6, Float.valueOf(df2.format(igstToTalDuty[1])));

		return getIgstRate;
	}

	public List<Object> swCalculation(List<Object> getSwRate, List<FPO_MAIN> fpoMainData, List<FPO_ITEM_DET> fpoItems) {

		List<FPO_ITEM_DET> fpoItemDET = fpoDeclaredService.getItemOnCin(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());
		FPO_AMEND fpoAmend = fpoAmendRpo.getLatestAmendOnCinAndItemNo(fpoItems.get(0).getCinNo(),
				fpoItems.get(0).getITEM_NO());
		Float basicValue = 0.01f;
		Float oneItemTotalDutyFg = null;
		Float assessVal = fpoItems.get(0).getASSVAL_INSFRT();
		Float swRate = Float.parseFloat(getSwRate.get(0).toString());
		Float previousSwDuty = 0.0F;
		Float currentBcdDuty = 0.0F;
		Float previousBcdDuty = 0.0F;

		Float currentIgstDuty = 0.0F;
		Float previousIgstDuty = 0.0F;

		if (null != fpoAmend)
			previousSwDuty = fpoAmend.getSW_AMT();
		else
			previousSwDuty = fpoItemDET.get(0).getSW_AMT();

		Float currentSwDuty = (((fpoItemDET.get(0).getBCD_AMT() * basicValue) * fpoItems.get(0).getBCD_RTA())
				* basicValue) * swRate;

		if (null != fpoAmend) {
			previousBcdDuty = fpoAmend.getBCD_AMT_FG();
			currentBcdDuty = fpoAmend.getBCD_AMT();
			previousIgstDuty = fpoAmend.getIGST_AMT();
		} else {
			previousBcdDuty = fpoItemDET.get(0).getBCD_AMT_FG();
			currentBcdDuty = fpoItemDET.get(0).getBCD_AMT();
			previousIgstDuty = fpoItemDET.get(0).getIGST_AMT();
		}

		currentIgstDuty = (assessVal + currentBcdDuty + currentSwDuty) * fpoItemDET.get(0).getIGST_RTA() * basicValue;
		Float currentIgstDutyFg = previousIgstDuty - currentIgstDuty;

		Float currentSwDutyFg = previousSwDuty - currentSwDuty;

		Float oneItemtotalDuty = currentSwDuty + fpoItems.get(0).getBCD_AMT() + fpoItems.get(0).getIGST_AMT();

		if (null != fpoItems.get(0).getBCD_AMT_FG() && null != fpoItems.get(0).getIGST_AMT_FG())
			oneItemTotalDutyFg = currentSwDutyFg + fpoItems.get(0).getBCD_AMT_FG() + fpoItems.get(0).getIGST_AMT_FG();
		else if (null != fpoItems.get(0).getBCD_AMT_FG() && null == fpoItems.get(0).getIGST_AMT_FG())
			oneItemTotalDutyFg = currentSwDutyFg + fpoItems.get(0).getBCD_AMT_FG();
		else if (null == fpoItems.get(0).getBCD_AMT_FG() && null != fpoItems.get(0).getIGST_AMT_FG())
			oneItemTotalDutyFg = currentSwDutyFg + fpoItems.get(0).getIGST_AMT_FG();
		else
			oneItemTotalDutyFg = currentSwDutyFg;

		Float[] swToTalDuty = getSumOfAllDuty(fpoItems, oneItemtotalDuty, oneItemTotalDutyFg);

		getSwRate.add(0, Float.valueOf(df2.format(swRate)));
		getSwRate.add(1, Float.valueOf(df2.format(currentSwDuty)));
		getSwRate.add(2, Float.valueOf(df2.format(currentSwDutyFg)));
		getSwRate.add(3, Float.valueOf(df2.format(oneItemtotalDuty)));
		getSwRate.add(4, Float.valueOf(df2.format(oneItemTotalDutyFg)));
		getSwRate.add(5, Float.valueOf(df2.format(swToTalDuty[0])));
		getSwRate.add(6, Float.valueOf(df2.format(swToTalDuty[1])));
		getSwRate.add(7, Float.valueOf(df2.format(currentIgstDuty)));
		getSwRate.add(8, Float.valueOf(df2.format(currentIgstDutyFg)));
		return getSwRate;
	}

	public Float[] getSumOfAllDuty(List<FPO_ITEM_DET> fpoItems, Float oneItemtotalDuty, Float oneItemTotalDutyFg) {

		Float sumOfAllItemDuty = oneItemtotalDuty;
		Float sumOfAllItemDutyFg = oneItemTotalDutyFg;
		Float[] sumData = new Float[2];

		List<FPO_ITEM_DET> fpoAllItemsFilterd = null;
		List<FPO_ITEM_DET> fpoAllItems = fpoDeclaredService.getAssesItem(fpoItems.get(0).getCinNo());

		FpoMainAmend fpoMainAmend = fpoMainAmendRepo.getAmendOnCinItemNo(fpoItems.get(0).getCinNo());

		List<FPO_MAIN> fpoMain = FPOrepost.getmain(fpoItems.get(0).getCinNo());

		for (FPO_ITEM_DET fpoItem : fpoAllItems) {
			fpoAllItemsFilterd = fpoDeclaredService.getOneAssesItem(fpoItem.getCinNo(), fpoItem.getITEM_NO());
			if (!fpoItems.get(0).getITEM_NO().equals(fpoItem.getITEM_NO())) {
				if (null != fpoAllItemsFilterd.get(0).getDUTY() && null != fpoAllItemsFilterd.get(0).getDUTY_FG()) {
					sumOfAllItemDuty = sumOfAllItemDuty + fpoAllItemsFilterd.get(0).getDUTY();
					sumOfAllItemDutyFg = sumOfAllItemDutyFg + fpoAllItemsFilterd.get(0).getDUTY_FG();
				} else if (null != fpoAllItemsFilterd.get(0).getDUTY()
						&& null == fpoAllItemsFilterd.get(0).getDUTY_FG()) {
					sumOfAllItemDuty = sumOfAllItemDuty + fpoAllItemsFilterd.get(0).getDUTY();
				} else if (null == fpoAllItemsFilterd.get(0).getDUTY()
						&& null != fpoAllItemsFilterd.get(0).getDUTY_FG()) {
					sumOfAllItemDutyFg = sumOfAllItemDutyFg + fpoAllItemsFilterd.get(0).getDUTY_FG();
				}
			} /*
				 * else { if (null != fpoAllItemsFilterd.get(0).getDUTY() && null !=
				 * fpoAllItemsFilterd.get(0).getDUTY_FG()) { sumOfAllItemDuty = sumOfAllItemDuty
				 * + fpoItems.get(0).getDUTY(); sumOfAllItemDutyFg = sumOfAllItemDutyFg +
				 * fpoItems.get(0).getDUTY_FG(); } else if (null !=
				 * fpoAllItemsFilterd.get(0).getDUTY() && null ==
				 * fpoAllItemsFilterd.get(0).getDUTY_FG()) { sumOfAllItemDuty = sumOfAllItemDuty
				 * + fpoAllItemsFilterd.get(0).getDUTY(); } else if (null ==
				 * fpoAllItemsFilterd.get(0).getDUTY() && null !=
				 * fpoAllItemsFilterd.get(0).getDUTY_FG()) { sumOfAllItemDutyFg =
				 * sumOfAllItemDutyFg + fpoAllItemsFilterd.get(0).getDUTY_FG(); } }
				 */
		}
		if (null != fpoItems && fpoItems.get(0).getRate() != null && fpoItems.get(0).getRate() == 0.0) {
			if (null != fpoMainAmend) {
				if (null != fpoMainAmend && fpoMainAmend.getTotalDutyFg() >= 0)
					sumOfAllItemDutyFg = fpoMainAmend.getTotalDutyFg() - sumOfAllItemDutyFg;
				else
					sumOfAllItemDutyFg = 0F;
			} else if (null != fpoMain) {
				if (null != fpoMain && fpoMain.get(0).getTotalDutyFg() >= 0)
					sumOfAllItemDutyFg = fpoMain.get(0).getTotalDutyFg() - sumOfAllItemDutyFg;
				else
					sumOfAllItemDutyFg = 0F;
			}
		}

		sumData[0] = Float.valueOf(df2.format(sumOfAllItemDuty));
		sumData[1] = Float.valueOf(df2.format(sumOfAllItemDutyFg));
		return sumData;

	}

	public void calDuty(List<Object> getBcdRate, List<Object> getIgstRate, List<Object> getSwRate,
			List<FPO_MAIN> fpoMainData, List<FPO_ITEM_DET> fpoItemDET) {
		if (null != getBcdRate && Float.parseFloat(getBcdRate.get(0).toString()) > 0)
			bcdCalculation(getBcdRate, fpoMainData, fpoItemDET);
		if (null != getIgstRate && Float.parseFloat(getIgstRate.get(0).toString()) > 0)
			igstCalculation(getIgstRate, fpoMainData, fpoItemDET);
		if (null != getSwRate && Float.parseFloat(getSwRate.get(0).toString()) > 0)
			swCalculation(getSwRate, fpoMainData, fpoItemDET);
	}

	public List<Collection> getloginName(D_EMP empName) {
		return DempFpo.getempName(empName.getId());
	}

	public List<String> getmulrole(String offid) {
		return DempFpo.getmulrole(offid);
	}

	public FPO_MAIN revokesetaside(String cinno, String role) {
		java.util.Date curdt = new java.util.Date();
		FPOrepost.updrevokesetaside(cinno, curdt, role);
		FPOrepost.updsetasidenull(cinno);
		return null;
	}

	public List<Collection> getsearchOOCdata(FPO_MAIN fpoMain) {
		if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoitemidpostdtorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoView(fpoMain.getId());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocitemidView(fpoMain.getITEM_ID());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocpostdtView(fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocorgcdView(fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoitemidView(fpoMain.getId(), fpoMain.getITEM_ID());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnopostdtView(fpoMain.getId(), fpoMain.getQRY_DT());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoorgcdView(fpoMain.getId(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocpostdtorgcdView(fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoitemidpostdtView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpoocitemidpostdtorgcdView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT().isEmpty())
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnopostdtorgcdView(fpoMain.getId(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpooccinnoitemidorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getSEND_CNTRY_CD());
		else
			return null;
	}

	public List<Collection> getsearchEXMdata(FPO_MAIN fpoMain) {
		if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoitemidpostdtorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoView(fpoMain.getId());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmitemidView(fpoMain.getITEM_ID());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT() == null)
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmpostdtView(fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmorgcdView(fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoitemidView(fpoMain.getId(), fpoMain.getITEM_ID());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnopostdtView(fpoMain.getId(), fpoMain.getQRY_DT());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoorgcdView(fpoMain.getId(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmpostdtorgcdView(fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoitemidpostdtView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmitemidpostdtorgcdView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnopostdtorgcdView(fpoMain.getId(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpexmcinnoitemidorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getSEND_CNTRY_CD());
		else
			return null;
	}

	public List<Collection> getsearchDETdata(FPO_MAIN fpoMain) {
		if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoitemidpostdtorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoView(fpoMain.getId());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetitemidView(fpoMain.getITEM_ID());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT() == null)
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetpostdtView(fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetorgcdView(fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoitemidView(fpoMain.getId(), fpoMain.getITEM_ID());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnopostdtView(fpoMain.getId(), fpoMain.getQRY_DT());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoorgcdView(fpoMain.getId(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetpostdtorgcdView(fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoitemidpostdtView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetitemidpostdtorgcdView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnopostdtorgcdView(fpoMain.getId(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpdetcinnoitemidorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getSEND_CNTRY_CD());
		else
			return null;
	}

	public List<Collection> getsearchQRYdata(FPO_MAIN fpoMain) {
		if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && !fpoMain.getQRY_DT().isEmpty()
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoitemidpostdtorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoView(fpoMain.getId());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqryitemidView(fpoMain.getITEM_ID());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && !(fpoMain.getQRY_DT() == null)
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrypostdtView(fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqryorgcdView(fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoitemidView(fpoMain.getId(), fpoMain.getITEM_ID());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnopostdtView(fpoMain.getId(), fpoMain.getQRY_DT());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoorgcdView(fpoMain.getId(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqryitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqryitemidpostdtView(fpoMain.getITEM_ID(), fpoMain.getSEND_CNTRY_CD());
		else if (fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrypostdtorgcdView(fpoMain.getQRY_DT(), fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoitemidpostdtView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getQRY_DT());
		else if (fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqryitemidpostdtorgcdView(fpoMain.getITEM_ID(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() != null
				&& fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnopostdtorgcdView(fpoMain.getId(), fpoMain.getQRY_DT(),
					fpoMain.getSEND_CNTRY_CD());
		else if (!fpoMain.getId().isEmpty() && !fpoMain.getITEM_ID().isEmpty() && fpoMain.getQRY_DT() == null
				&& !fpoMain.getSEND_CNTRY_CD().isEmpty())
			return FPOrepost.getsearchimpqrycinnoitemidorgcdView(fpoMain.getId(), fpoMain.getITEM_ID(),
					fpoMain.getSEND_CNTRY_CD());
		else
			return null;
	}

	// ----------------------------IMPORTED POSTAL
	// ARTCILES--------------------------------------------------------------------------//

	public FpoQueryDecision oocsubmit(FpoQueryDecision fpoqryDec, FpoMvmnt fpoMvmnt, CUSRSP_SENT cusrsp,
			HttpSession session, HttpServletRequest request) throws IOException {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		java.util.Date curdt = new java.util.Date();
		String Cinno = fpoqryDec.getCIN_NO();
		fpoMvmnt.setCinNo(Cinno);
		inscusrsp(Cinno, "OOC");
		System.out.println("cinno=" + Cinno);
		fpoQueryDecisionRepo.updqryOOCsub(Cinno, utilDate, request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("cinno=" + Cinno);
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		insertIntofpoMvmntDb(fpoMvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "OOC", "P8", session,
				request);
		updfpocurque(Cinno, "P8", "OOC", utilDate, session, request);
		fpoCurQueRepo.updfpostatusooc(Cinno, utilDate);
		List<FPO_MAIN> fpoMain = fpomainrepo.getmain(Cinno);
		if (!fpoMain.isEmpty()) {
			String mobileNumber = fpoMain.get(0).getRECP_PHONE();
			if (mobileNumber != null && !mobileNumber.equalsIgnoreCase("") && mobileNumber.length() >= 10) {
				if (mobileNumber.length() > 10) {
					mobileNumber = mobileNumber.substring(mobileNumber.length() - 10);
				}
				String smsText = "INDIAN CUSTOMS cleared your postal Art.ID " + fpoMain.get(0).getITEM_ID()
						+ ". Visit www.enquiry.icegate.gov.in  to know duty details. -CBIC";
				sendSmsOnly("9740388057", smsText, "1107163548047002273");
			}
		}
		
		genOOCpdf(Cinno,request);
		return fpoqryDec;
	}
	
	
	public void genOOCpdf(String Cinno, HttpServletRequest request) throws IOException {
		log.info("cms 01 in ooc_submit_crpdf");
	 	PdfFont fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	 	PdfFont fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
	 	PdfFont fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
	 	PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		
		//generating ooc pdf from here for  the corresponding cin no and item id
		
	 	log.info("cms 02 in ooc_submit_crpdf");
		try { 
//			23MCPI04100004748300 EE216035025ID
//			Cinno= "23MCPI04100004748300";
			String cinNo1=Cinno;
			 String [][] oocItemDet = fpoQueryRepo.getOocItemDet(cinNo1);
			   
			   int length = oocItemDet.length;
			   int totItems=length;
			   int count=0;
			   if ((length>5)&&(length%5==0)) {
				   int j= length/5;
				   count=j;
			   }else {
				  int k= length%5;
				 int l= length-k;
			  int p= l/5;
			  count=p;
			  count=count+1;
			   }


			   log.info("cms 03 in ooc_submit_crpdf");
			  System.out.println("total no of pages"+count);
			  
			  
//			Cinno= "22MCPI11100000266700";
//			String itemIdtouse = fpoQueryDecisionRepo.getItemId(Cinno); 
//			  String cinNo1 = "22MCPI07040000452900";
//			  String cinNo1=Cinno;

			  
					List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo1);
					
					String cusite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
					System.out.println("the obtained cusstie is"+cusite);
					log.info("cms 04 in ooc_submit_crpdf");
					String getpostdt = FPOrepost.getpostdt(cinNo1);
//					String getRemarks = fpoQueryRepo.getRemarks(cinNo1, fpoQueryRepo.getMaxQueryNo());
					String tomailid = fpoQueryRepo.getemail(cinNo1, fpoQueryRepo.getMaxQueryNo());
					Date cinDate = fpoMainData.get(0).getCIN_DT();
					String cinnDate = cinDate.toString();
					System.out.println("CINDATE ***************"+cinDate);
					String mail_CLASS_CD = fpoMainData.get(0).getMAIL_CLASS_CD();

					log.info("cms 05 in ooc_submit_crpdf");
					
					System.out.println("mainclass898888"+mail_CLASS_CD);
					String getmailDesc = fpoQueryRepo.getmailDescptn(mail_CLASS_CD);
					System.out.println(getmailDesc);
					String origpost_ORG_CD = fpoMainData.get(0).getORIGPOST_ORG_CD();
					String item_ID = fpoMainData.get(0).getITEM_ID();
					System.out.println(origpost_ORG_CD);
					System.out.println(item_ID);
					String originPost = fpoQueryRepo.getOriginPost(origpost_ORG_CD);
					
					log.info("cms 06 in ooc_submit_crpdf");
					
					System.out.println(originPost);
					
					String mail_CATEGORY_CD = fpoMainData.get(0).getNATURE_TYPE_CD();
					System.out.println(mail_CATEGORY_CD);
					String catgry = fpoQueryRepo.getCategoryByCode(mail_CATEGORY_CD);
					System.out.println(catgry);
					
//					String DefualtQuery = fpoQueryRepo.getDefualtQuery(cinNo1,
//							fpoQueryRepo.getMaxQueryNo());
//					
					
//					List<String> defualtQueryList = getSpecifiedDefualtQuery(DefualtQuery);
					log.info("cms 07 in ooc_submit_crpdf");
//				    LoginController.tomailid = tomailid;
					 request.getSession().setAttribute("tomailid", tomailid);
//				    LoginController.recpname = fpoMainData.get(0).getRECP_NAME();
					 request.getSession().setAttribute("recpname", fpoMainData.get(0).getRECP_NAME());
//					
					 
//					List<FpoQuery> getAllFpoQuery = fpoQueryService.getAllFpoQuery(cinNo1);
//					List<FpoQueryDin> DINList = fpoQueryDinRepo.getFpoQueryDINSerialNo(cinNo1);
					 log.info("cms 08 in ooc_submit_crpdf");
				    DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyyHHmmss");
				    String currentDateTime = dateFormatter.format(new Date());
				    DateFormat printdateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				    String printcurrentDateTime = printdateFormatter.format(new Date());
				
				  String fpoSite = fpoQueryRepo.getSiteNameByCusite(cusite);
				  
				  log.info("cms 09 in ooc_submit_crpdf");
//				  String itemid=  "EE216035025ID"; 
				  
//				  String itemid=itemIdtouse;
//				  String itemid = DINList.get(0).getItemid();
				    String oocQueryDt = fpoQueryRepo.oocQueryDt(cinNo1);
				    String oocaddQueryDt = fpoQueryRepo.oocaddQueryDt(cinNo1);
				    String oocRplDt = fpoQueryRepo.oocRplDt(cinNo1);
				   
				    log.info("cms 10 in ooc_submit_crpdf");
				   String [] [] allView=fpoQueryRepo.getOocMain(cinNo1, cusite);
				   System.out.println(allView);
				   String string = allView[0][1];
				   System.out.println(string);
				   
				   log.info("cms 11 in ooc_submit_crpdf");
					
				   
				   String[][] oocTotDuties = fpoQueryRepo.getOocTotDuties(cinNo1);
				   System.out.println(oocTotDuties);
				    
				   log.info("cms 12 in ooc_submit_crpdf");
				   
				   String[][] oocDtandId = fpoQueryRepo.getOocDtandId(cinNo1);
				   System.out.println(oocDtandId);
				   
				   log.info("cms 13 in ooc_submit_crpdf");
				   
			 String path3 = FPOrepost.getoocpdfqrypath()+"ooc"+item_ID+".pdf";
				File viewfilename=new File(path3);
				
	OutputStream viewStream = 
			    new FileOutputStream(viewfilename);
	//request.getSession().setAttribute("itemid", itemid);

	PdfWriter pdfWriter2=new PdfWriter(viewStream);
		        PdfDocument pdfDocument2 = new PdfDocument(pdfWriter2);
		     
		        pdfDocument2.setDefaultPageSize(PageSize.A4.rotate());
	  pdfDocument2.addNewPage();
		        Document viewdoc = new Document(pdfDocument2);
		        
		        log.info("cms 14 in ooc_submit_crpdf");
		        
		        for (int i = 0; i < count; i++) {

//		        ImageData imageData = ImageDataFactory.create(imgsrc);
		        	  ImageData imageData = ImageDataFactory.create(FPOrepost.getimagespath() + "CBECnew.png");
		        	Image image1 = new Image(imageData);
		        image1.setFixedPosition(pdfDocument2.getDefaultPageSize().getWidth()/2-140,pdfDocument2.getDefaultPageSize().getHeight()/2-130);
		        image1.setOpacity(0.10f);
		        viewdoc.add(image1);
//		        ImageData imageData1 = ImageDataFactory.create(imgsrc);
		        Image image2 = new Image(imageData);
		        image2.setHeight(40f);
		        image2.setWidth(40f);
		        image2.setMarginLeft(90f);
//		        image2.setMarginRight(30f);
//		        viewdoc.add(image2);
		      
		        log.info("cms 15 in ooc_submit_crpdf");
		
			 

			 fontb = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
			 	 fontbi = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
			 	 fontli = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
			 	 font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);


			 	 log.info("cms 16 in ooc_submit_crpdf");
					
	 	 
			 	  Text textt31a = new Text(" ").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp31a = new Paragraph(textt31a).setMarginTop(5f);
				   viewdoc.add(tp31a);
				   
				   log.info("cms 17 in ooc_submit_crpdf");
					
				   
				   float width3[]= {70,140,100,100,100,100,130};//680
				   Table table3 = new Table(width3).setHorizontalAlignment(HorizontalAlignment.CENTER);
				   Text textt31 = new Text("\n").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp31 = new Paragraph(textt31);
				   Cell cell = new Cell(7,0);
				   Text textt31a1 = new Text("A.").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp3 = new Paragraph(textt31a1).setVerticalAlignment(VerticalAlignment.MIDDLE);
				   Text textt312 = new Text("HEADER").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp377 = new Paragraph(textt312);
				   table3.addCell(cell.add(tp31).add(tp3).add(tp377).setHorizontalAlignment(HorizontalAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY));


				   log.info("cms 18 in ooc_submit_crpdf");
					
				   Cell cel=new Cell(3,2).add(image2).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER);
//				   cel.setPaddingLeft(240f/2-25f); // Center the image horizontally

				   table3.addCell(cel);
				   
				   log.info("cms 19 in ooc_submit_crpdf");
					

				   Text textt33 = new Text("FPO Site Code").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp33 = new Paragraph(textt33);
				 
				   table3.addCell(new Cell().add(tp33).setPadding(1f).setVerticalAlignment(VerticalAlignment.TOP).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
//				   cell.setRotationAngle(Math.PI/2f)

				   log.info("cms 20 in ooc_submit_crpdf");
					
				   
				   Text textt34 = new Text("Item / Article ID").setFont(fontb).setFontSize(8.0f);
				   Paragraph tp34 = new Paragraph(textt34);
				 
				   table3.addCell(new Cell().add(tp34).setPadding(1f).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.TOP).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
				   
				   log.info("cms 21 in fpo_qry_crpdf");
				
				 
			       
		    
			   Text textt3A = new Text("Posting Date").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp3A = new Paragraph(textt3A);
			
			   table3.addCell(new Cell().setPadding(1f).add(tp3A).setVerticalAlignment(VerticalAlignment.TOP).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
			   Text textt3B = new Text("PERSONAL IMPORTS").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp3B = new Paragraph(textt3B);
			
			   log.info("cms 22 in ooc_submit_crpdf");
				
			   table3.addCell(new Cell(2,0).add(tp3B).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
			   Text textt3C = new Text(cusite).setFont(font).setFontSize(8.0f);
			   Paragraph tp3C = new Paragraph(textt3C);
			System.out.println(cusite);
			System.out.println(item_ID);
			System.out.println(getpostdt);
			   table3.addCell(new Cell().add(tp3C).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.10f));
			  
			   log.info("cms 23 in ooc_submit_crpdf");
				
			   
			   Text textt3A1 = new Text(item_ID).setFont(font).setFontSize(8.0f);
			   Paragraph tp3A1 = new Paragraph(textt3A1);
			   table3.addCell(new Cell().add(tp3A1).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.10f));

			   log.info("cms 24 in ooc_submit_crpdf");
				
			   
			   Text textt3B1 = new Text(getpostdt).setFont(font).setFontSize(8.0f);
			   Paragraph tp3B1 = new Paragraph(textt3B1);
			   table3.addCell(new Cell().add(tp3B1).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.10f));
			   
			   log.info("cms 25 in ooc_submit_crpdf");
				
			   
			   Text textt3C1 = new Text(" ").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp3C1 = new Paragraph(textt3C1);
			   table3.addCell(new Cell(0,3).add(tp3C1).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.8f));
			   
			   log.info("cms 26 in ooc_submit_crpdf");
				
			   
			   Text textt3A2 = new Text("OOC COPY").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp3A2 = new Paragraph(textt3A2);
			   table3.addCell(new Cell().add(tp3A2).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
			   Text textt3B3 = new Text("INDIAN CUSTOMS EDI SYSTEM").setFont(fontb).setFontSize(10.0f);
			   Paragraph tp3B3 = new Paragraph(textt3B3);
			   table3.addCell(new Cell(0,2).add(tp3B3).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
			   
			   log.info("cms 27 in ooc_submit_crpdf");
				
			   
			   Text textt31C = new Text("CIN").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp31C = new Paragraph(textt31C);
			   table3.addCell(new Cell().add(tp31C).setPadding(1f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   Text textt3B31 = new Text(cinNo1).setFont(font).setFontSize(9.0f);
			   Paragraph tp3B31 = new Paragraph(textt3B31);
			   table3.addCell(new Cell(0,2).setPadding(1f).add(tp3B31).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   Text textt31C1 = new Text(" ").setFont(fontb).setFontSize(5.0f);
			   Paragraph tp31C1 = new Paragraph(textt31C1);
			   table3.addCell(new Cell(5,0).add(tp31C1).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   log.info("cms 28 in ooc_submit_crpdf");
			   
			   Text textt31Cc = new Text("FOREIGN POST OFFICE : ").setFont(fontb).setFontSize(7.0f);
			   Paragraph tp31Cc = new Paragraph(textt31Cc);
			   table3.addCell(new Cell().add(tp31Cc).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));

			   Text textt3B3c = new Text(fpoSite).setFont(font).setFontSize(7.0f);
			   Paragraph tp3B3c = new Paragraph(textt3B3c);
			   table3.addCell(new Cell().add(tp3B3c).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   Text textt31Cv = new Text("CIN DATE").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp31Cv = new Paragraph(textt31Cv);
			   table3.addCell(new Cell().add(tp31Cv).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   log.info("cms 29 in ooc_submit_crpdf");
			 
			   Text textt31v = new Text(cinnDate).setFont(font).setFontSize(8.0f);
			   Paragraph tp31v = new Paragraph(textt31v);
			   table3.addCell(new Cell(0,2).add(tp31v).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
			   Text textt3B32 = new Text("POSTAL IMPORT CLEARANCES").setFont(fontb).setFontSize(7.0f);
			   Paragraph tp3B32 = new Paragraph(textt3B32);
			   table3.addCell(new Cell(0,2).add(tp3B32).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

			   Text textt31Cp = new Text(" ").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp31Cp = new Paragraph(textt31Cp);
			   Cell cell1=new Cell(2,3);
			   cell1.setHeight(8f).setBackgroundColor(Color.LIGHT_GRAY);
			   table3.addCell(cell1.add(tp31Cp));

			   log.info("cms 30 in ooc_submit_crpdf");
			   
			   Text textt31Crp3 = new Text(" ").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp31Crp3 = new Paragraph(textt31Crp3);
			   Cell cell9=new Cell(0,2);
			   cell9.setHeight(8f).setBackgroundColor(Color.LIGHT_GRAY);
					   table3.addCell(cell9.add(tp31Crp3).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			   
					   Text textt3qw= new Text("DECLARATION INFO SUMMARY").setFont(fontb).setFontSize(8.0f);
			   Paragraph tp3qw = new Paragraph(textt3qw);
			   table3.addCell(new Cell(0,7).setPadding(1f).add(tp3qw).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
			          		   
				   viewdoc.add(table3);
				  
				   log.info("cms 31 in ooc_submit_crpdf");
					
				   float width4[]= {70,100,100,100,140,100,65,65};//680//740
				   Table table4 = new Table(width4).setHorizontalAlignment(HorizontalAlignment.CENTER);
				   Text textta1 = new Text("B.\r\n"
				   		+ "CN 22/23\r\n"
				   		+ "SUMMARY").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa1 = new Paragraph(textta1).setVerticalAlignment(VerticalAlignment.TOP).setTextAlignment(TextAlignment.CENTER);
				   Cell cell456=new Cell(3,0);
				   cell456.add(tpa1);
				   table4.addCell((cell456).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY));
//				   .setRotationAngle(Math.PI/2f)
				   Text textta2 = new Text("MAIL CLASS").setFont(fontb).setFontSize(8.0f);
					   Paragraph tpa2 = new Paragraph(textta2);
				   table4.addCell(new Cell(1,1).add(tpa2).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				  
				   log.info("cms 32 in ooc_submit_crpdf");
					
				   
				   Text textta3 = new Text("ORIGIN POST").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa3 = new Paragraph(textta3);
				   table4.addCell(new Cell(1,1).add(tpa3).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 33 in ooc_submit_crpdf");
					
				   
				   Text textta4 = new Text("ITEM CATEGORY").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa4 = new Paragraph(textta4);
				   table4.addCell(new Cell(1,1).add(tpa4).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 34 in ooc_submit_crpdf");
					
				   
				   Text textta5 = new Text("TOTAL NO OF ITEMS").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa5 = new Paragraph(textta5);
				   table4.addCell(new Cell(1,1).add(tpa5).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				  
				   log.info("cms 35 in ooc_submit_crpdf");
					
				   Text textta6 = new Text("TOTAL VALUE").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa6 = new Paragraph(textta6);
				   table4.addCell(new Cell(1,1).add(tpa6).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				  
				   log.info("cms 36 in ooc_submit_crpdf");
					
				   Text textta7 = new Text("CUR").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa7 = new Paragraph(textta7);
				   table4.addCell(new Cell(1,1).add(tpa7).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 37 in ooc_submit_crpdf");
				   
				   Text textta8 = new Text("TOT WT").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa8 = new Paragraph(textta8);
				   table4.addCell(new Cell(1,1).add(tpa8).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 38 in ooc_submit_crpdf");
					
				   Text textta9 = new Text(allView[0][5].toUpperCase()).setFont(font).setFontSize(8.0f);
				   Paragraph tpa9 = new Paragraph(textta9);
				   table4.addCell(new Cell(1,1).add(tpa9).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				
				   log.info("cms 39 in ooc_submit_crpdf");
					
				   Text textta12 = new Text(originPost.toUpperCase()).setFont(font).setFontSize(8.0f);
				   Paragraph tpa12 = new Paragraph(textta12);
				   table4.addCell(new Cell(1,1).add(tpa12).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 40 in ooc_submit_crpdf");
					
				   Text textta13 = new Text(allView[0][6]).setFont(font).setFontSize(8.0f);
				   Paragraph tpa13 = new Paragraph(textta13);
				   table4.addCell(new Cell(1,1).add(tpa13).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 41 in ooc_submit_crpdf");
					
				   Text texttaq1 = new Text(allView[0][8]).setFont(font).setFontSize(8.0f);
				   Paragraph tpaq1 = new Paragraph(texttaq1);
				   table4.addCell(new Cell(1,1).add(tpaq1).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 42 in ooc_submit_crpdf");
					
				   Text texttw1 = new Text(allView[0][7]).setFont(font).setFontSize(8.0f);
				   Paragraph tpaw1 = new Paragraph(texttw1);
				   table4.addCell(new Cell(1,1).add(tpaw1).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 43 in ooc_submit_crpdf");
					
				   Text textta21 = new Text(allView[0][10]).setFont(font).setFontSize(8.0f);
				   Paragraph tpa21 = new Paragraph(textta21);
				   table4.addCell(new Cell(1,1).add(tpa21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 44 in ooc_submit_crpdf");
					
				   Text textta212 = new Text(allView[0][9]).setFont(font).setFontSize(8.0f);
				   Paragraph tpa212 = new Paragraph(textta212);
				   table4.addCell(new Cell(1,1).add(tpa212).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				   
				   log.info("cms 45 in ooc_submit_crpdf");
					
				   Text textta122 = new Text(" ").setFont(fontb).setFontSize(8.0f);
				   Paragraph tpa122 = new Paragraph(textta122).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.LEFT);
				   Cell cell123 =new Cell(1,7);
				   cell123.add(tpa122);
				   table4.addCell((cell123).setPadding(1f).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
					
				   log.info("cms 46 in ooc_submit_crpdf");
					
				   viewdoc.add(table4);
				  
				   log.info("cms 47 in ooc_submit_crpdf");
				   
				   float width5[]= {70,100,200,370};//680//740
				   Table table5 = new Table(width5).setHorizontalAlignment(HorizontalAlignment.CENTER);
				   Text txt1 =new Text(" ").setFont(fontb).setFontSize(8.0f);
				   Paragraph ptxt1 = new Paragraph(txt1);
				   Cell cellq1=new Cell();
				   cellq1.setBorderBottom(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
				   table5.addCell(cellq1.add(ptxt1).setPadding(1f).setBorderBottom(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
				 
				   log.info("cms 48 in ooc_submit_crpdf");
					
				   Text txt2 =new Text("RECIPIENT NAME & ADDRESS").setFont(fontb).setFontSize(8.0f);
					   Paragraph ptxt2 = new Paragraph(txt2);
					   table5.addCell(new Cell(1,2).add(ptxt2).setBackgroundColor(Color.LIGHT_GRAY,0.5f).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));

					   log.info("cms 49 in ooc_submit_crpdf");
						
						   Text txt3 =new Text("SENDER'S NAME & ADDRESS").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt3 = new Paragraph(txt3);
						   table5.addCell(new Cell(1,1).add(ptxt3).setBackgroundColor(Color.LIGHT_GRAY,0.5f).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						 
						   log.info("cms 50 in ooc_submit_crpdf");
							
						   Text txt4 =new Text("C."
						   		).setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt4 = new Paragraph(txt4);
						   Cell cellq2=new Cell();
						   cellq2.setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table5.addCell(cellq2.add(ptxt4).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   log.info("cms 51 in ooc_submit_crpdf");
							
						   Text txt5 =new Text(fpoMainData.get(0).getRECP_NAME()+"\n"+fpoMainData.get(0).getRECP_ADD1()+"\n"+fpoMainData.get(0).getRECP_CITY()+"-"+fpoMainData.get(0).getRECP_ZIP()).setFont(font).setFontSize(8.0f);
						   Paragraph ptxt5 = new Paragraph(txt5);
						   table5.addCell(new Cell(3,2).add(ptxt5).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   log.info("cms 52 in ooc_submit_crpdf");
							
						   Text txt6 =new Text(fpoMainData.get(0).getSEND_NAME()+"\n"+fpoMainData.get(0).getSEND_ADD1().toUpperCase()+"\n"+fpoMainData.get(0).getSEND_CITY()+"-"+fpoMainData.get(0).getSEND_ZIP()).setFont(font).setFontSize(8.0f);
						   Paragraph ptxt6 = new Paragraph(txt6);
						   table5.addCell(new Cell(3,1).add(ptxt6).setPadding(1f).setBorderBottom(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   log.info("cms 53 in ooc_submit_crpdf");
							
						   Text txt7 =new Text("RECEIVER").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt7 = new Paragraph(txt7);
						   Cell cellq3=new Cell();
						   cellq3.setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table5.addCell(cellq3.add(ptxt7).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   log.info("cms 54 in ooc_submit_crpdf");
							
						   Text txt8 =new Text("SENDER INFO").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt8 = new Paragraph(txt8);
						   Cell cellq4=new Cell();
						   cellq4.setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table5.addCell(cellq4.add(ptxt8).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   log.info("cms 55 in ooc_submit_crpdf");
							
						   Text txt9 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						  Paragraph ptxt9 = new Paragraph(txt9);
						  Cell cellq5=new Cell();
						   cellq5.setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table5.addCell(cellq5.add(ptxt9).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 56 in ooc_submit_crpdf");
							
						   Text txt10 =new Text("STATE").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt10 = new Paragraph(txt10);
						   table5.addCell(new Cell(1,1).add(ptxt10).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY, 0.5f));
						   
						   if(fpoMainData.get(0).getRECP_STATE()!=null) {
							   Text txt11 =new Text(fpoMainData.get(0).getRECP_STATE()).setFont(font).setFontSize(8.0f);
						   
						   Paragraph ptxt11 = new Paragraph(txt11);
						   table5.addCell(new Cell(1,1).add(ptxt11).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text txt11 =new Text("-").setFont(font).setFontSize(8.0f);
							   
							   Paragraph ptxt11 = new Paragraph(txt11);
							   table5.addCell(new Cell(1,1).add(ptxt11).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }
						   
						   log.info("cms 57 in ooc_submit_crpdf");
							
						   Text txt12 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptxt12 = new Paragraph(txt12);
						   table5.addCell(new Cell(1,1).add(ptxt12).setBorderTop(Border.NO_BORDER).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 58 in ooc_submit_crpdf");
							
						   viewdoc.add(table5);

						   log.info("cms 59 in ooc_submit_crpdf");
							
						   float width6[]= {70,50,100,50,50,50,100,40,50,50,65,65};
						   Table table6=new Table(width6).setHorizontalAlignment(HorizontalAlignment.CENTER);;
						   Text txte1 =new Text(" ").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte1 = new Paragraph(txte1);
						   Cell cellq12=new Cell();
						   cellq12.setBorderBottom(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table6.addCell(cellq12.add(ptxte1).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 60 in ooc_submit_crpdf");
						   
						   Text txte2 =new Text("ITEM NO").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte2 = new Paragraph(txte2);
						   table6.addCell(new Cell(1,1).add(ptxte2).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						  
						   Text txte3 =new Text("DESCRIPTION").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte3 = new Paragraph(txte3);
						   table6.addCell(new Cell(1,1).add(ptxte3).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						   
						   Text txte4 =new Text("HSC").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte4 = new Paragraph(txte4);
						   table6.addCell(new Cell(1,1).add(ptxte4).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						   
						   log.info("cms 61 in ooc_submit_crpdf");
						   
						   Text txte5 =new Text("CTH").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte5 = new Paragraph(txte5);
						   table6.addCell(new Cell(1,1).add(ptxte5).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						   
						   Text txte6 =new Text("QTY").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte6 = new Paragraph(txte6);
						   table6.addCell(new Cell(1,1).add(ptxte6).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						   
						   Text txte7 =new Text("ASS.VALUE*").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte7 = new Paragraph(txte7);
						   table6.addCell(new Cell(1,1).add(ptxte7).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));

						   Text txte8 =new Text("BCD").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte8 = new Paragraph(txte8);
						   table6.addCell(new Cell(1,1).add(ptxte8).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));

						   log.info("cms 62 in ooc_submit_crpdf");
						   
						   Text txte9 =new Text("IGST").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte9 = new Paragraph(txte9);
						   table6.addCell(new Cell(1,1).add(ptxte9).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));

						   Text txte10 =new Text("SWS").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte10 = new Paragraph(txte10);
						   table6.addCell(new Cell(1,1).add(ptxte10).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));

						   Text txte11 =new Text("OTHER DUTY").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte11 = new Paragraph(txte11);
						   table6.addCell(new Cell(1,1).add(ptxte11).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));

						   Text txte12 =new Text("TOTAL DUTY").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptxte12 = new Paragraph(txte12);
						   table6.addCell(new Cell(1,1).add(ptxte12).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY,0.5f));
						  
						   log.info("cms 63 in ooc_submit_crpdf");
						   
						   
						   int startValue = (i * 5) + 1;
						    int endValue = (i + 1) * 5;
						    if (endValue > totItems) {
						        endValue = totItems;
						    }

						    log.info("cms 64 in ooc_submit_crpdf");
						    
						    for (int i1 = startValue-1; i1 < endValue; i1++) {
							  
								
							  String str1=" ";
							   
							
							   Text txte13 =new Text(str1).setFont(font).setFontSize(7.0f);
							   Paragraph ptxte13 = new Paragraph(txte13);
							   Cell cellqq=new Cell();
							   cellqq.setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
							   table6.addCell(cellqq.add(ptxte13).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
							   
						   Text txte14 =new Text(oocItemDet[i1][0]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte14 = new Paragraph(txte14);              
						   table6.addCell(new Cell().add(ptxte14).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

						   Text txte15 =new Text(oocItemDet[i1][1].toUpperCase()).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte15 = new Paragraph(txte15);
						   table6.addCell(new Cell().add(ptxte15).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 65 in ooc_submit_crpdf");
						   
						   if(oocItemDet[0][2]!=null) {
						   Text txte16 =new Text(oocItemDet[i1][2]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte16 = new Paragraph(txte16);
						   table6.addCell(new Cell().add(ptxte16).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else if (oocItemDet[i1][3]!=null) {
							   Text txte17 =new Text(oocItemDet[i1][3]).setFont(font).setFontSize(7.0f);
							   Paragraph ptxte17 = new Paragraph(txte17);
							   table6.addCell(new Cell().add(ptxte17).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));  
						}else {
							Text txte17 =new Text("-").setFont(font).setFontSize(7.0f);
							   Paragraph ptxte17 = new Paragraph(txte17);
							   table6.addCell(new Cell().add(ptxte17).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));  
				
						}
						   
						   log.info("cms 66 in ooc_submit_crpdf");
						   
						   Text txte18 =new Text(oocItemDet[i1][4]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte18 = new Paragraph(txte18);
						   table6.addCell(new Cell().add(ptxte18).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   
						   Text txte19 =new Text(oocItemDet[i1][5]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte19 = new Paragraph(txte19);
						   table6.addCell(new Cell().add(ptxte19).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

						   
						   log.info("cms 67 in ooc_submit_crpdf");
						   
						   Text txte20 =new Text(oocItemDet[i1][6]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte20 = new Paragraph(txte20);
						   table6.addCell(new Cell().add(ptxte20).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

						   Text txte21 =new Text(oocItemDet[i1][7]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte21 = new Paragraph(txte21);
						   table6.addCell(new Cell().add(ptxte21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

						   Text txte22 =new Text(oocItemDet[i1][8]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte22 = new Paragraph(txte22);
						   table6.addCell(new Cell().add(ptxte22).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));

						   Text txte23 =new Text(oocItemDet[i1][9]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte23 = new Paragraph(txte23);
						   table6.addCell(new Cell().add(ptxte23).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						 
						   log.info("cms 68 in ooc_submit_crpdf");
						   
						  if (oocItemDet[i1][10]!=null) {
						
						
						   Text txte25=new Text(oocItemDet[i1][10]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte25 = new Paragraph(txte25);
						   table6.addCell(new Cell().add(ptxte25).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						  }else {
							  Text txte25=new Text("-").setFont(font).setFontSize(7.0f);
							   Paragraph ptxte25 = new Paragraph(txte25);
							   table6.addCell(new Cell().add(ptxte25).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						}
						  
						   Text txte24=new Text(oocItemDet[i1][11]).setFont(font).setFontSize(7.0f);
						   Paragraph ptxte24 = new Paragraph(txte24);
						   table6.addCell(new Cell().add(ptxte24).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						 	  
							
						   }
						   
						   log.info("cms 69 in ooc_submit_crpdf");
						   

						   Text t10 =new Text("D. \r\n"
						   		+ " ITEM \r\n "
						   		+ "INFORMATION \r\n"
						   		+ "AND CUSTOMS \r\n"
						   		+ "IMPORT DUTIES").setFont(fontb).setFontSize(8.0f);
						   Paragraph p10 = new Paragraph(t10);
						   Cell cell7t18=new Cell(5,0);
						   cell7t18.setBorderTop(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);
						   table6.addCell(cell7t18.add(p10).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 70 in ooc_submit_crpdf");
						  
						   
						   Text t3 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph p3 = new Paragraph(t3);
						   Cell cell7t3=new Cell(0,4);
						   cell7t3.setBorderBottom(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);;
						   table6.addCell(cell7t3.add(p3).setVerticalAlignment(VerticalAlignment.BOTTOM).setTextAlignment(TextAlignment.CENTER));
					
						   Text t12 =new Text("TOTAL").setFont(fontb).setFontSize(8.0f);
						   Paragraph p12 = new Paragraph(t12);
						   Cell cell7t12=new Cell();
						   table6.addCell(cell7t12.add(p12).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   Text t13 =new Text(oocTotDuties[0][0]).setFont(font).setFontSize(8.0f);
						   Paragraph p13 = new Paragraph(t13);
						   Cell cell7t13=new Cell();
						   table6.addCell(cell7t13.add(p13).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 71 in ooc_submit_crpdf");
					  
						   Text t14 =new Text(oocTotDuties[0][1]).setFont(font).setFontSize(8.0f);
						   Paragraph p14 = new Paragraph(t14);
						   Cell cell7t14=new Cell();
						   table6.addCell(cell7t14.add(p14).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   Text t15 =new Text(oocTotDuties[0][2]).setFont(font).setFontSize(8.0f);
						   Paragraph p15 = new Paragraph(t15);
						   Cell cell7t15=new Cell();
						   table6.addCell(cell7t15.add(p15).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   Text t16 =new Text(oocTotDuties[0][3]).setFont(font).setFontSize(8.0f);
						   Paragraph p16 = new Paragraph(t16);
						   Cell cell7t16=new Cell();
						   table6.addCell(cell7t16.add(p16).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 72 in ooc_submit_crpdf");
					  
						   if (oocTotDuties[0][4]!=null) {
							
						
						   Text t17 =new Text(oocTotDuties[0][4]).setFont(font).setFontSize(8.0f);
						   Paragraph p17 = new Paragraph(t17);
						   Cell cell7t17=new Cell();
						   table6.addCell(cell7t17.add(p17).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }
						   else {
							   Text t17 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph p17 = new Paragraph(t17);
							   Cell cell7t17=new Cell();
							   table6.addCell(cell7t17.add(p17).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
							
						}
						   Text t18 =new Text(oocTotDuties[0][5]).setFont(font).setFontSize(8.0f);
						   Paragraph p18 = new Paragraph(t18);
						   Cell cell6t18=new Cell();
						   table6.addCell(cell6t18.add(p18).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   Text t19 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph p19 = new Paragraph(t19);
						   Cell cell7t19=new Cell(0,4);
						   cell7t19.setBorderTop(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBackgroundColor(Color.LIGHT_GRAY);;
						   table6.addCell(cell7t19.add(p19).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 73 in ooc_submit_crpdf");
					  
						   Text t20 =new Text("FINE").setFont(fontb).setFontSize(8.0f);
						   Paragraph p20 = new Paragraph(t20);
						   Cell cell7t20=new Cell(0,6).setBackgroundColor(Color.LIGHT_GRAY, 0.5f);
						   table6.addCell(cell7t20.add(p20).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT));
					  
						   if (fpoMainData.get(0).getTOT_FINE()!=null) {
							
						
						   Text t21 =new Text(fpoMainData.get(0).getTOT_FINE().toString()).setFont(font).setFontSize(8.0f);
						   Paragraph p21 = new Paragraph(t21);
						   Cell cell7t21=new Cell();
						   table6.addCell(cell7t21.add(p21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   }else {
							   Text t21 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph p21 = new Paragraph(t21);
							   Cell cell7t21=new Cell();
							   table6.addCell(cell7t21.add(p21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						  
						}
						   
						   log.info("cms 74 in ooc_submit_crpdf");
						   
						   Text t22 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph p22 = new Paragraph(t22);
						   Cell cell7t22=new Cell(0,4).setBackgroundColor(Color.LIGHT_GRAY);;
						   cell7t22.setBorderTop(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER);
						   table6.addCell(cell7t22.add(p22).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
					  
						   Text t23 =new Text("PENALTY").setFont(fontb).setFontSize(8.0f);
						   Paragraph p23 = new Paragraph(t23);
						   Cell cell7t23=new Cell(0,6).setBackgroundColor(Color.LIGHT_GRAY, 0.5f);
						   table6.addCell(cell7t23.add(p23).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT));
						   
						   log.info("cms 75 in ooc_submit_crpdf");
					  
						   if (fpoMainData.get(0).getTOT_PENAL()!=null) {
							
					
						   Text t24 =new Text(fpoMainData.get(0).getTOT_PENAL().toString()).setFont(font).setFontSize(8.0f);
						   Paragraph p24 = new Paragraph(t24);
						   Cell cell7t24=new Cell();
						   table6.addCell(cell7t24.add(p24).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text t24 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph p24 = new Paragraph(t24);
							   Cell cell7t24=new Cell();
							   table6.addCell(cell7t24.add(p24).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
							 
						}
						   Text t25 =new Text("* CIF Asssesable Value for Customs purposes is arrived after considering the").setFont(font).setFontSize(7.0f);
						   Paragraph p25 = new Paragraph(t25);
						   Cell cell7t25=new Cell(0,4).setBackgroundColor(Color.LIGHT_GRAY);;
						   cell7t25.setBorderTop(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER);

						   table6.addCell(cell7t25.add(p25).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED));
					  
						   Text t26 =new Text("GRAND TOTAL AMOUNT").setFont(fontb).setFontSize(8.0f);
						   Paragraph p26 = new Paragraph(t26);
						   Cell cell7t26=new Cell(0,6).setBackgroundColor(Color.LIGHT_GRAY, 0.5f);
						   table6.addCell(cell7t26.add(p26).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT));
						   
						   log.info("cms 76 in ooc_submit_crpdf");
					  
						   if (fpoMainData.get(0).getTOT_DUTY()!=null) {
							
						
						   Text t27 =new Text(fpoMainData.get(0).getTOT_DUTY().toString()).setFont(font).setFontSize(8.0f);
						   Paragraph p27 = new Paragraph(t27);
						   Cell cell7t27=new Cell();
						   table6.addCell(cell7t27.add(p27).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }
						   else {
							   Text t27 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph p27 = new Paragraph(t27);
							   Cell cell7t27=new Cell();
							   table6.addCell(cell7t27.add(p27).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
							
							
						}
						   Text t28 =new Text("notional freight (20%) and insurance (1.125%).").setFont(font).setFontSize(7.0f);
						   Paragraph p28 = new Paragraph(t28);
						   Cell cell7t28=new Cell(0,4).setBackgroundColor(Color.LIGHT_GRAY);;
						   cell7t28.setBorderTop(Border.NO_BORDER);

						   table6.addCell(cell7t28.add(p28).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED));
					  
						   log.info("cms 77 in ooc_submit_crpdf");
						   
						   Text t29 =new Text("TOTAL AMOUNT CHARGEABLE BY POST AT THE TIME OF DELIVERY").setFont(fontb).setFontSize(8.0f);
						   Paragraph p29 = new Paragraph(t29);
						   Cell cell7t29=new Cell(0,6).setBackgroundColor(Color.LIGHT_GRAY, 0.5f);
						   table6.addCell(cell7t29.add(p29).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setTextAlignment(TextAlignment.RIGHT));
					  
						   Text t30 =new Text(fpoMainData.get(0).getTOT_AMT_PAYABLE().toString()).setFont(fontb).setFontSize(8.0f);
						   Paragraph p30 = new Paragraph(t30);
						   Cell cell7t30=new Cell();
						   table6.addCell(cell7t30.add(p30).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   viewdoc.add(table6);
						   
						   log.info("cms 78 in ooc_submit_crpdf");
						 
						   float width8[]= {70,105,45,50,50,50,370};//680//740
						   Table table8 = new Table(width8).setHorizontalAlignment(HorizontalAlignment.CENTER);
						   Text tt81 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptt81 = new Paragraph( tt81);
						   Cell celltt81=new Cell(2,1).setBackgroundColor(Color.LIGHT_GRAY).setBorderBottom(Border.NO_BORDER);
						   table8.addCell(celltt81.add(ptt81).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   
						   Text tt82 =new Text("EXCHANGE RATE :").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptt82 = new Paragraph( tt82);
						   Cell celltt82=new Cell(2,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltt82.add(ptt82).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   log.info("cms 79 in ooc_submit_crpdf");
						   
						   if (oocItemDet[0][12]!=null) {
							
						   Text tt83 =new Text(oocItemDet[0][12]).setFont(font).setFontSize(8.0f);
						   Paragraph ptt83 = new Paragraph( tt83);
						   Cell celltt83=new Cell(2,4);
						   table8.addCell(celltt83.add(ptt83).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));

						   }else {
							   Text tt83 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptt83 = new Paragraph( tt83);
							   Cell celltt83=new Cell(2,4);
							   table8.addCell(celltt83.add(ptt83).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));

						}
						   Text tt84 =new Text("DIGITAL SIGNATURE").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptt84 = new Paragraph( tt84);
						   Cell celltt84=new Cell(2,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltt84.add(ptt84).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   log.info("cms 80 in ooc_submit_crpdf");
						   
						   Text tt85 =new Text("E.").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptt85 = new Paragraph( tt85);
						   Cell celltt85=new Cell().setBackgroundColor(Color.LIGHT_GRAY).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER);
						   table8.addCell(celltt85.add(ptt85).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM).setTextAlignment(TextAlignment.CENTER));
						
						   Text tt86 =new Text("QUERY RAISED :").setFont(fontb).setFontSize(9.0f);
						   Paragraph ptt86 = new Paragraph( tt86);
						   Cell celltt86=new Cell().setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltt86.add(ptt86).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.LEFT));
						
						   log.info("cms 81 in ooc_submit_crpdf");
						   if (oocQueryDt!=null||oocaddQueryDt!=null) {
							
					
						   Text tt87 =new Text("YES").setFont(font).setFontSize(9.0f);
						   Paragraph ptt87 = new Paragraph( tt87);
						   Cell celltt87=new Cell(0,2);
						   table8.addCell(celltt87.add(ptt87).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tt87 =new Text("NO").setFont(font).setFontSize(9.0f);
							   Paragraph ptt87 = new Paragraph( tt87);
							   Cell celltt87=new Cell(0,2);
							   table8.addCell(celltt87.add(ptt87).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						}
						   Text tt94 =new Text("DATE :").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptt94 = new Paragraph( tt94);
						   Cell celltt94=new Cell().setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltt94.add(ptt94).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.LEFT));
						   
						   log.info("cms 82 in ooc_submit_crpdf");
						   
						   if (oocQueryDt!=null) {
							
						
						   Text tq85 =new Text(oocQueryDt).setFont(font).setFontSize(8.0f);
						   Paragraph ptq85 = new Paragraph( tq85);
						   Cell celltq85=new Cell();
						   table8.addCell(celltq85.add(ptq85).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else if (oocaddQueryDt!=null) {
							
						
							   Text tq85 =new Text(oocaddQueryDt).setFont(font).setFontSize(8.0f);
							   Paragraph ptq85 = new Paragraph( tq85);
							   Cell celltq85=new Cell();
							   table8.addCell(celltq85.add(ptq85).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						}else {
							 Text tq85 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq85 = new Paragraph( tq85);
							   Cell celltq85=new Cell();
							   table8.addCell(celltq85.add(ptq85).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
				
						}
						   

						   log.info("cms 83 in ooc_submit_crpdf");
						   
						   Text tq8q =new Text("SIGNATURE").setFont(fontli).setFontSize(13.0f);
						   Paragraph ptq8q= new Paragraph(tq8q);
						   Cell celltq8q=new Cell().setBorderBottom(Border.NO_BORDER);
						   table8.addCell(celltq8q.add(ptq8q).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM).setTextAlignment(TextAlignment.CENTER));

						   log.info("cms 84 in ooc_submit_crpdf");
						   
						   Text tq8q1 =new Text("OTHER INFO").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q1= new Paragraph( tq8q1);
						   Cell celltq8q1=new Cell().setBackgroundColor(Color.LIGHT_GRAY).setBorderTop(Border.NO_BORDER);
						   table8.addCell(celltq8q1.add(ptq8q1).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
//						
						   Text tq8q2 =new Text("QUERY REPLIED :").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q2= new Paragraph(tq8q2);
						   Cell celltq8q2=new Cell().setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltq8q2.add(ptq8q2).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.LEFT));
						  
						   log.info("cms 85 in ooc_submit_crpdf");
						   
						   if (oocRplDt!=null) {
						   Text tq8q3 =new Text("YES").setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q3= new Paragraph(tq8q3);
						   Cell celltq8q3=new Cell(0,2);
						   table8.addCell(celltq8q3.add(ptq8q3).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tq8q3 =new Text("NO").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q3= new Paragraph(tq8q3);
							   Cell celltq8q3=new Cell(0,2);
							   table8.addCell(celltq8q3.add(ptq8q3).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
							 	
						}
						   Text tq8q3 =new Text("DATE :").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q3= new Paragraph(tq8q3);
						   Cell celltq8q3=new Cell().setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table8.addCell(celltq8q3.add(ptq8q3).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.LEFT));
						
						   log.info("cms 86 in ooc_submit_crpdf");
						   
						   if (oocRplDt!=null) {
						   Text tq8q4 =new Text(oocRplDt).setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q4= new Paragraph(tq8q4);
						   Cell celltq8q4=new Cell();
						   table8.addCell(celltq8q4.add(ptq8q4).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tq8q4 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q4= new Paragraph(tq8q4);
							   Cell celltq8q4=new Cell();
							   table8.addCell(celltq8q4.add(ptq8q4).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						}
						   
						   log.info("cms 87 in ooc_submit_crpdf");
						   
						   Text tq8q11 =new Text(" ").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q11= new Paragraph( tq8q11);
						   Cell celltq8q11=new Cell().setBorderTop(Border.NO_BORDER);
						   table8.addCell(celltq8q11.add(ptq8q11).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 88 in ooc_submit_crpdf"); 
						   
						   viewdoc.add(table8);
						   
						   log.info("cms 89 in ooc_submit_crpdf");
						   
						 float width9[]= {70,105,45,50,100,370};//680//740
						   Table table9 = new Table(width9).setHorizontalAlignment(HorizontalAlignment.CENTER);
						   
						   Text tq8q12 =new Text("F.").setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q12= new Paragraph( tq8q12);
						   Cell celltq8q12=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY).setBorderBottom(Border.NO_BORDER);
						   table9.addCell(celltq8q12.add(ptq8q12).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						  
						   log.info("cms 90 in ooc_submit_crpdf");
						   
						   Text tq8q13 =new Text(" SSO ID OF OOC OFFICER").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptq8q13= new Paragraph( tq8q13);
						   Cell celltq8q13=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table9.addCell(celltq8q13.add(ptq8q13).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   Text tq8q14 =new Text("OOC DT").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptq8q14= new Paragraph( tq8q14);
						   Cell celltq8q14=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table9.addCell(celltq8q14.add(ptq8q14).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						  
						   log.info("cms 91 in ooc_submit_crpdf");
						  
						   Text tq8q15 =new Text("OOC TIME").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptq8q15= new Paragraph( tq8q15);
						   Cell celltq8q15=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table9.addCell(celltq8q15.add(ptq8q15).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   Text tq8q16 =new Text("OFFICER NAME").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptq8q16= new Paragraph( tq8q16);
						   Cell celltq8q16=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table9.addCell(celltq8q16.add(ptq8q16).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   Text tq8q17 =new Text(" ").setFont(fontb).setFontSize(7.0f);
						   Paragraph ptq8q17= new Paragraph( tq8q17);
						   Cell celltq8q7=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY,0.5f);
						   table9.addCell(celltq8q7.add(ptq8q17).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						 
						   log.info("cms 92 in ooc_submit_crpdf");
						   
						   Text tq8q18 =new Text("OOC DETAILS"
						   		
						   		).setFont(fontb).setFontSize(8.0f);
						   Paragraph ptq8q18= new Paragraph( tq8q18);
						   Cell celltq8q8=new Cell(1,1).setBackgroundColor(Color.LIGHT_GRAY).setBorderTop(Border.NO_BORDER);
						   table9.addCell(celltq8q8.add(ptq8q18).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER));
						
						   if (oocDtandId[0][0]!=null) {
							
						
						   Text tq8q19 =new Text(oocDtandId[0][0].toString()).setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q19= new Paragraph(tq8q19);
						   Cell celltq8q9=new Cell(1,1);
						   table9.addCell(celltq8q9.add(ptq8q19).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tq8q19 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q19= new Paragraph(tq8q19);
							   Cell celltq8q9=new Cell(1,1);
							   table9.addCell(celltq8q9.add(ptq8q19).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						
						}
						   log.info("cms 93 in ooc_submit_crpdf");
						   
						   if (oocDtandId[0][1]!=null) {
								
						   Text tq8q20 =new Text(oocDtandId[0][1].toString()).setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q20= new Paragraph(tq8q20);
						   Cell celltq8q20=new Cell(1,1);
						   table9.addCell(celltq8q20.add(ptq8q20).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tq8q20 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q20= new Paragraph(tq8q20);
							   Cell celltq8q20=new Cell(1,1);
							   table9.addCell(celltq8q20.add(ptq8q20).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
							 
						}
						   log.info("cms 94 in ooc_submit_crpdf");
							
						   if (oocDtandId[0][2]!=null) {
						   Text tq8q21 =new Text(oocDtandId[0][2].toString()).setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q21= new Paragraph(tq8q21);
						   Cell celltq8q21=new Cell(1,1);
						   table9.addCell(celltq8q21.add(ptq8q21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }else {
							   Text tq8q21 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q21= new Paragraph(tq8q21);
							   Cell celltq8q21=new Cell(1,1);
							   table9.addCell(celltq8q21.add(ptq8q21).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
							 
						} 
						   log.info("cms 95 in ooc_submit_crpdf");
							
						   if (oocDtandId[0][3]!=null) {

						   Text tq8q22 =new Text(oocDtandId[0][3].toString()).setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q22= new Paragraph(tq8q22);
						   Cell celltq8q22=new Cell(1,1);
						   table9.addCell(celltq8q22.add(ptq8q22).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   }  else {
							   Text tq8q22 =new Text("-").setFont(font).setFontSize(8.0f);
							   Paragraph ptq8q22= new Paragraph(tq8q22);
							   Cell celltq8q22=new Cell(1,1);
							   table9.addCell(celltq8q22.add(ptq8q22).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
								
						}
						   log.info("cms 96 in ooc_submit_crpdf");
							
						   Text tq8q23 =new Text(" ").setFont(font).setFontSize(8.0f);
						   Paragraph ptq8q23= new Paragraph(tq8q23);
						   Cell celltq8q23=new Cell(1,1);
						   table9.addCell(celltq8q23.add(ptq8q23).setPadding(1f).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER));
						   
						   log.info("cms 97 in ooc_submit_crpdf");
							
						   
						   viewdoc.add(table9);
						   if (i<count-1) {
							   viewdoc.add(new AreaBreak());
						   		}
						   }
		        
		        float width9[]= {740};//680//740
				   Table table5 = new Table(width9).setHorizontalAlignment(HorizontalAlignment.CENTER);
				   Text txt1 =new Text("Please verify in enquiry.icegate.gov.in").setFont(fontb).setFontSize(8.0f).setFontColor(Color.BLUE);
				   Paragraph ptxt1 = new Paragraph(txt1);
				   Cell cellq1=new Cell();
				   cellq1.setBorderBottom(Border.NO_BORDER);
				   table5.addCell(cellq1.add(ptxt1).setPadding(1f).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED));
				   viewdoc.add(table5);
		        
		        log.info("cms 98 in ooc_submit_crpdf");
				
				   viewdoc.close();
				   viewStream.close();
		
				   System.out.println("Pdf created successfully.");
				   
		 log.info("cms 99 in ooc_submit_crpdf");
		
		} 
				 catch (Exception e) {
			// TODO: handle exception
					 e.printStackTrace();
		}
		
	}

		
		
	

	public FPO_MAIN asssubmit(FPO_MAIN fpomain, FPO_ITEM_DET fpoitemdet, HttpSession session,
			HttpServletRequest request) {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		java.util.Date curdt = new java.util.Date();
		String Cinno = fpomain.getId();
		System.out.println("cinno=" + Cinno);
		System.out.println("cinno=" + Cinno);
		// poCurQueService.addUserQue(Cinno,FPOrepost.getitemid(Cinno), "E4", session);
		// updfpocurque(Cinno, "E1", "ASS", utilDate, session);
		fpoCurQueRepo.updfpostatusass(Cinno, utilDate);
		fpomainrepo.updateAssdate(Cinno, curdt, "PAO");
		fpoItemRepost.updateAssDateall(Cinno, curdt, request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString(), "PAO");
		return fpomain;
	}

	public void inscusrsp(String Cinno, String cat) {
		java.util.Date curdt = new java.util.Date();
		System.out.println("here");
	//	Long l = FPOrepost.getmaxidrspsent();
		CUSRSP_SENT cusrsp = new CUSRSP_SENT();
		cusrsp.setCin_NO(Cinno);
		cusrsp.setCategory(cat);
		cusrsp.setIn_Date(curdt);
		cusrsp.setSent_Dt(null);
	//	cusrsp.setSent_ID(l);
		cusrspRepo.save(cusrsp);
		System.out.println("check now");
	}

	public void updfpocurque(String Cinno, String curque, String Role, String Quedt, HttpSession session,
			HttpServletRequest request) {
		/*
		 * Long queno = fpoCurQueRepo.getMaxQnoOnCinNo(Cinno,
		 * request.getSession().getAttribute("cuSite") == null ? null :
		 * request.getSession().getAttribute("cuSite").toString());
		 */
		// changed for clearance site
		Long queno = fpoCurQueRepo.getMaxQnoOnCinNo(Cinno);
		if (queno == null)
			queno = 1L;
		else
			queno = queno + 1L;
		// Long queid = fpoCurQueRepo.getIdOnCin(Cinno,
		// request.getSession().getAttribute("cuSite") == null ? null
		// : request.getSession().getAttribute("cuSite").toString());
		Long queid = fpoCurQueRepo.getIdOnCin(Cinno);
		if (queid == null)
			queid = 1L;
		else
			queid = queid + 1L;
		System.out.println("queno is " + queno);
		System.out.println("queid is " + queid);
		System.out.println("quedt is " + Quedt);
		System.out.println("Cinno is " + Cinno);
		fpoCurQueRepo.updatefpocurque(Cinno, curque,
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				queno, Quedt,
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString(),
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
	}

	public FpoMvmnt MOVQRYTOOOC(FpoMvmnt fpoMvmnt, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String Cinno = fpoMvmnt.getCinNo();
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(Cinno);
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		String offid = fpoMvmntRepo.getoffid(Cinno);
		String role = fpoMvmntRepo.getrole(Cinno);
		System.out.println("cinno=" + Cinno + "offid=" + offid + "role=" + role);
		System.out.println("Logincontroller cinno=" + request.getSession().getAttribute("cinno") + "offid="
				+ request.getSession().getAttribute("offId") + "role=" + request.getSession().getAttribute("role"));
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		// insertIntofpoMvmntDb(fpoMvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno),
		// curdt, slno, "SUP", "P3");
		updfpocurque(Cinno, "P3", "SUP", utilDate, session, request);
		// fpoQueryDecisionRepo.updqrydecioffid(Cinno,request.getSession().getAttribute("offId")
		// == null ? null : request.getSession().getAttribute("offId").toString());
		fpoQueryDecisionRepo.updqryOOC(Cinno, utilDate);
		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && fpoMain.get(0).getROLE().equals("PAO")) {
			FPOrepost.updateRoleApr(Cinno);
		}
		// fpoCurQueRepo.updfpostatussup(Cinno,request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		return fpoMvmnt;
	}

	public List<Collection> getoocdispdata(FPO_ITEM_DET fpoitemdet) throws ParseException {
		String Cinno = fpoitemdet.getCinNo();
		List<Collection> value1 = FPOrepost.getoocitmdata(fpoitemdet.getCinNo());
		return value1;
	}

	public void movtoaddlqry(String cinNo, String que, HttpSession session, HttpServletRequest request) {
		System.out.println("inside fposervice movtoaddlqry que is" + que);
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(cinNo);
		if (null == slno)
			slno = Long.valueOf(0);
		fpoMvmntRepo.updextdtstr(cinNo, utilDate);
		updfpocurque(cinNo, que, "IMP", utilDate, session, request);
		fpoQueryDecisionRepo.updaddlqry(cinNo, utilDate, request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString(), que);
	}

	public FpoMvmnt MOVEXMTOASS(FpoMvmnt fpoMvmnt, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String Cinno = fpoMvmnt.getCinNo();
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		// insertIntofpoMvmntDb(fpoMvmnt,
		// Cinno,fpoMvmntRepo.getcindtmvmnt(Cinno),curdt,slno,"APR","P1");
		updfpocurque(Cinno, "P1", "PAO", utilDate, session, request);
		System.out.println("upto here ok");
		String offid = fpoQueryDecisionRepo.getoffidcin(Cinno);
		fpoQueryDecisionRepo.updqryASS(Cinno, utilDate, offid);
		System.out.println("now error");
		// fpoCurQueRepo.updfpostatussup(Cinno,request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		return fpoMvmnt;
	}

	public FpoMvmnt MOVOOCTODET(FpoMvmnt fpoMvmnt, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String Cinno = fpoMvmnt.getCinNo();
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		// insertIntofpoMvmntDb(fpoMvmnt,
		// Cinno,fpoMvmntRepo.getcindtmvmnt(Cinno),curdt,slno,"APR","P7");
		updfpocurque(Cinno, "P5", "PAO", utilDate, session, request);
		fpoQueryDecisionRepo.updqryDET(Cinno, utilDate);
		inscusrsp(Cinno, "DET");
		// fpoCurQueRepo.updfpostatussup(Cinno,request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		return fpoMvmnt;
	}

	public FpoMvmnt MOVQRYTOEXM(FpoMvmnt fpoMvmnt, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String Cinno = fpoMvmnt.getCinNo();
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(Cinno);
		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
//			long now = System.currentTimeMillis();
//			Timestamp sqlTimestamp = new Timestamp(now);
//			System.out.println(sqlTimestamp);
		// fpoMvmntRepo.MOVQRYTOOOC(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));
		// insertIntofpoMvmntDb(fpoMvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno),
		// curdt, slno, "INS", "P4");
		updfpocurque(Cinno, "P4", "PIN", utilDate, session, request);
		if (fpoMain.get(0).getTOT_ASS_VAL() > FPOrepost.getaclassval() && fpoMain.get(0).getROLE().equals("PAO")) {
			System.out.println("Moved to PAC");
		} else {
			fpoQueryDecisionRepo.updqryEXM(Cinno, utilDate);
		}
		// fpoCurQueRepo.updfpostatussup(Cinno,request.getSession().getAttribute("offId")
		// == null ? null :
		// request.getSession().getAttribute("offId").toString(),utilDate);
		return fpoMvmnt;
	}

	public FPO_EXAM insdbexmfindings(FPO_EXAM fpoexam, HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		FPO_EXAM fpoexms = new FPO_EXAM();
		fpoexms.setCinNo(fpoexam.getCinNo());
		fpoexms.setCus_site(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpoexms.setItem_no(fpoexam.getItem_no());
		fpoexms.setItem_fou(fpoexam.getItem_fou());
		fpoexms.setRmks(fpoexam.getRmks());
		fpoexms.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpoexms.setRole("PIN");
		fpoexms.setIns_dt(curdt);
		fpoexms.setITEM_ID(fpomainrepo.getitemid(fpoexam.getCinNo()));
		FPO_EXMrepost.save(fpoexms);
		if (fpoexam.getGenrmks() != null && !(fpoexam.getGenrmks().isEmpty())) {
			FPO_EXAM fpoexmg = new FPO_EXAM();
			fpoexmg.setCinNo(fpoexam.getCinNo());
			fpoexmg.setCus_site(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			fpoexmg.setItem_no(null);
			fpoexmg.setItem_fou(null);
			fpoexmg.setRmks(fpoexam.getGenrmks());
			fpoexmg.setOff_id(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			fpoexmg.setRole("PIN");
			fpoexmg.setIns_dt(curdt);
			fpoexmg.setDetain(fpoexam.getDetain());
			fpoexmg.setITEM_ID(fpomainrepo.getitemid(fpoexam.getCinNo()));
			fpoexms = FPO_EXMrepost.save(fpoexmg);
		}
		System.out.println("value is " + fpoexms);
		if (fpoexms != null) {
			return fpoexms;
		} else {
			return null;
		}
	}

	public FPO_OOC insdboocfindings(FPO_OOC fpoooc, HttpSession session, HttpServletRequest request) {
		java.util.Date curdt = new java.util.Date();
		FPO_OOC fpooocs = new FPO_OOC();
		fpooocs.setCinNo(fpoooc.getCinNo());
		fpooocs.setItemId(fpoooc.getItemId());
		fpooocs.setCus_site(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		fpooocs.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpooocs.setRole("PSU");
		fpooocs.setEnt_dt(curdt);
		fpooocs.setDepcmts(fpoooc.getDepcmts());
		fpooocs.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		fpooocs = FPO_OOCrepost.save(fpooocs);
		System.out.println("value is " + fpooocs);
		if (fpooocs != null) {
			return fpooocs;
		} else {
			return null;
		}
	}

	// ----------------------------IMPORTED POSTAL
	// ARTCILES--------------------------------------------------------------------------//
	// ----------------------------Modified on Sep 6th by Sasi ...dash
	// search.....................................................................
	// ----------------------------added on May 23rd by Sasi ...dash
	// search.....................................................................

	/*
	 * public String getpos(String cinNo, String itemid, HttpSession session,
	 * HttpServletRequest request) { String curque; //String cursite; curque=""; int
	 * opt=0; String curpos=""; String curpos1=""; String initcusite=""; String
	 * cusite=session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString(); initcusite=cusite; if
	 * (cusite.equals("INNSA5")) {cusite=FPOrepost.getCusite(itemid);
	 * curpos1=" - Belongs to "+cusite+""; }
	 * System.out.println("in fposervice cinno is " +cinNo);
	 * System.out.println("in fposervice itemid is " +itemid); if
	 * (cinNo.equals("ENTER CIN_NO")) cinNo=""; else if
	 * (itemid.equals("ENTER ITEM_ID")) itemid=""; if (!(cinNo.isEmpty())) {
	 * cursite=fpoCurQueRepo.getcusitecin(cinNo); System.out.println(cursite); if
	 * (cursite==null) opt=6; else {if (!(cursite.equals(cusite)))
	 * {System.out.println("no"); opt=4;} else { opt=1; curque =
	 * fpoCurQueRepo.getcurqueCin(cinNo,cusite);
	 * System.out.println("curque is"+curque); if (curque==null) { curque =
	 * fpoQueryDecisionRepo.getinitqueCin(cinNo,cusite); opt=3; } else if
	 * (curque.equals("E4")) {System.out.println("s"); curque =
	 * fpoQueryDecisionRepo.getabsqueCin(cinNo,cusite); if (curque == null)
	 * curque="E4"; } System.out.println("curque is"+curque); }}} else if
	 * (!(itemid.isEmpty())) { cursite=fpoCurQueRepo.getcusiteItm(itemid);
	 * System.out.println(cursite); if (cursite==null) opt=7; else {if
	 * (!(cursite.equals(cusite))) {System.out.println("no"); opt=4;} else { opt=2;
	 * curque = fpoCurQueRepo.getcurqueItm(itemid,cusite);
	 * System.out.println("curque is"+curque); if (curque==null) { curque =
	 * fpoQueryDecisionRepo.getinitqueItm(itemid,cusite); opt=3; } else if
	 * (curque.equals("E4")) {System.out.println("s"); curque =
	 * fpoQueryDecisionRepo.getabsqueItm(itemid,cusite); if (curque == null)
	 * curque="E4"; } System.out.println("curque is"+curque); }}} if (opt==4)
	 * {curpos = "BELONGS TO FPO SITE " + cursite; return curpos; } else { if
	 * (curque==null) { if (opt == 1 || opt == 6) curpos="NO SUCH CIN FOUND.."; else
	 * if ( opt == 2 || opt == 7) curpos="NO SUCH ITEM ID FOUND..";} else { if
	 * ((FPOrepost.getscanfpo(itemid, cusite) > 0)) { curpos=
	 * "Arrived at FPO / Bag Scan Entry Queue -> " ;
	 * curpos=curpos+fpoQueryDecisionRepo.getnxtque(curque);} else
	 * curpos=fpoQueryDecisionRepo.getcurque(curque);} if (opt==3) curpos="WAITING "
	 * + curpos + " / "+ FPOrepost.getroleitm(itemid,cusite) + " QUEUE";
	 * System.out.println("curpos="+curpos+",opt="+opt); if (curque.equals("E3") &&
	 * opt==2) curpos="WAITING "+curpos; if (curpos!=null) { if
	 * (!(curpos.contains("EAD")) && !(curpos.startsWith("NO SUCH")) &&
	 * !(curpos.contains("GIVEN"))) { if (opt == 1) { if
	 * (FPOrepost.getcouarrcin(cinNo) > 0) curpos = "Articles " + curpos; else
	 * curpos = "Articles not " + curpos;} else if (opt == 2) { if
	 * (FPOrepost.getcouarritm(itemid) > 0) curpos = "Articles " + curpos; else
	 * curpos = "Articles not " + curpos;} } if (curpos.contains("EAD")) { if (opt
	 * == 1) curpos=curpos + " / " + FPOrepost.getrolecin(cinNo,cusite) + " QUEUE";
	 * else if (opt == 2) curpos=curpos + " / " +
	 * FPOrepost.getroleitm(itemid,cusite) + " QUEUE"; if
	 * (FPOrepost.getarrfpo(itemid,cusite) > 0) { System.out.println("here 1");
	 * curpos=curpos + " ;  Articles Arrived at Destination FPO"; } else if
	 * (FPOrepost.getarr(itemid,cusite) > 0) { System.out.println("here 2");
	 * curpos=curpos + " ;  Articles Entered India -  in Transit to FPO"; } } if
	 * (curpos.contains("OOC GIVEN")) { if (opt == 1) curpos = curpos + " DATE " +
	 * FPOrepost.getoocdtcin(cinNo,cusite); else if (opt == 2) curpos = curpos +
	 * " DATE " + FPOrepost.getoocdtitm(itemid,cusite); } if (curpos==null)
	 * curpos="INVALID ENTRY"; else if (initcusite.equals("INNSA5"))
	 * curpos=curpos+curpos1; return curpos; } if (curpos==null) { if
	 * (FPOrepost.getitmcou(itemid) > 0) { curpos =
	 * "Pending in WITHOUT PINCODE for FPO SITE Mapping "; if
	 * ((FPOrepost.getarrfpocou(itemid) > 0) || (FPOrepost.getarrfposcancou(itemid)
	 * > 0)) curpos = curpos + " ;  Articles Arrived and mapped"; else if
	 * ((FPOrepost.getarrcou(itemid) > 0) || (FPOrepost.getarrscancou(itemid) > 0))
	 * curpos = curpos + " ;  Articles arrived and not yet mapped "; if
	 * (initcusite.equals("INNSA5")) curpos=curpos+curpos1; return curpos; } else
	 * return "INVALID ENTRY"; } } return "INVALID ENTRY"; }
	 */

	// ----------------------------added on May 23rd by Sasi ...dash
	// search.....................................................................
	// ----------------------------Modified on Sep 6th by Sasi ...dash
	// search.....................................................................

	// --- written new getposcur to be replaced by getpos

	/*
	 * public String getpos(String itemid, HttpSession session, HttpServletRequest
	 * request) { String curque; // String cursite; curque = ""; String role =
	 * request.getSession().getAttribute("role") == null ? null :
	 * request.getSession().getAttribute("role").toString(); int opt = 0; String
	 * curpos2 = ""; String curpos = ""; String curpos1 = ""; String curposb = "";
	 * String cusite = request.getSession().getAttribute("cuSite") == null ? null :
	 * request.getSession().getAttribute("cuSite").toString();
	 * System.out.println("in fposervice itemid is " + itemid); String arrscan = "";
	 * if (itemid.equals("ENTER ITEM_ID")) itemid = ""; else if
	 * (!(itemid.isEmpty())) { if
	 * (!(fpoCurQueRepo.chckitmid(itemid).equals(itemid))) return "INVALID ENTRY";
	 * String cursite = fpoCurQueRepo.curqueueitem(itemid);
	 * System.out.println(cursite); if (cursite != null) { if
	 * (!(cursite.equals(request.getSession().getAttribute("cuSite") == null ? null
	 * : request.getSession().getAttribute("cuSite").toString())) ||
	 * role.equals("PNA") || role.equals("NRP") || role.equals("NAL")) { curposb =
	 * " Belongs to " + cursite; } } else return
	 * " in 'W/O Pincode & Pending for Mapping' "; if
	 * (fpooocancelRepo.getPendingByarticleId(itemid) > 0) { return "OOC GIVEN; " +
	 * " DATE " + FPOrepost.getoocdtmvmnt(itemid, session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()) +
	 * "; IN OOC CANCEL Queue;" + curposb; } else if
	 * (fpooocancelRepo.getooccancelarticleId(itemid) > 0) { curpos1 = "OOC GIVEN; "
	 * + " DATE " + FPOrepost.getmaxoocdt(itemid, session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()) +
	 * "; OOC CANCELLED  -  "; arrscan = "Y"; } else { String arrindia = "", arrfpo
	 * = ""; if (FPOrepost.getcouarrfpoitm(itemid) > 0) { curpos1 =
	 * "Articles arrived at Destination FPO;"; arrfpo = "Y"; } else if
	 * (FPOrepost.getcouarrooeitm(itemid) > 0) { curpos1 =
	 * "Articles arrived at India;"; arrindia = "Y"; } if (arrindia.equals("Y") &&
	 * arrfpo.equals("")) curpos1 = "Articles Entered India -  in Transit to FPO;";
	 * else if (arrindia.equals("") && arrfpo.equals("")) curpos1 =
	 * "Articles not Arrived;"; if (FPOrepost.getarr(itemid, cursite) > 0) { arrscan
	 * = "Y"; if (arrfpo.equals("Y")) curpos1 = curpos1 + ""; else if
	 * (curpos1.equals("Articles not Arrived;")) curpos1 =
	 * "Articles Physically arrived at destination FPO and confirmed;"; else curpos1
	 * =
	 * "Articles Entered India; Physically arrived at destination FPO and confirmed;"
	 * ; } else if (curpos1.equals("Articles not Entered INDIA;") ||
	 * curpos1.equals("Articles Entered India -  in Transit to FPO;")) curpos1 =
	 * curpos1; else curpos1 = curpos1 + " Waiting in BAG SCAN QUEUE;"; } if
	 * (curposb.contains("Belongs")) curque =
	 * fpoQueryDecisionRepo.getabsqueItm(itemid, cursite); else curque =
	 * fpoQueryDecisionRepo.getabsqueItm(itemid, cusite); if (curque == null ) { if
	 * (FPOrepost.getcurroleead(itemid).equals("PAC")) return
	 * "WAITING IN EAD/PAC QUEUE; " + curpos1 + curpos+ curposb; else return
	 * "WAITING IN EAD/PAO QUEUE; " + curpos1 + curpos+ curposb; } curpos2 =
	 * fpoQueryDecisionRepo.getcurque(curque); if (curpos2.contains("AAF") &&
	 * (curpos1.equals("Articles not Arrived;") ||
	 * curpos1.equals("Articles Entered India -  in Transit to FPO;") ||
	 * curpos1.contains("Waiting in BAG SCAN QUEUE;"))) curpos2 =
	 * curpos2.replace("F", "A"); if (curpos2.contains("OOC GIVEN")) return
	 * "OOC GIVEN; " + " DATE " + FPOrepost.getoocdtitm(itemid,
	 * session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()) + curpos + curposb; else if
	 * (curque.equals("C1") || curque.equals("C2")) return curpos2 + ";" +
	 * "Waiting IN AAF / PAO QEUEUE " + curpos + curposb; else if
	 * (curque.equals("A2") && arrscan.equals("Y")) return curpos1 +
	 * " in AAF QUERY QUEUE; " + curposb; else if (curque.equals("A2") &&
	 * arrscan.equals("")) return curpos1 + " in AAA QUERY QUEUE; " + curposb; else
	 * if (curque.equals("P2") && arrscan.equals("")) return curpos1 +
	 * " in AAA QUERY QUEUE; " + curposb; else if (curque.equals("P2") &&
	 * arrscan.equals("Y")) return curpos1 + " in AAF QUERY QUEUE; " + curposb; else
	 * return curpos1 + " " + curpos2 + "; " + curpos + curposb; } return null; }
	 */

	// Changed on May 20th 2023
	public String getpos(String itemid, HttpSession session, HttpServletRequest request) {
		String curque;
		// String cursite;
		curque = "";
		String role = request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString();
		int opt = 0;
		String curpos2 = "";
		String curpos = "";
		String curpos1 = "";
		String curposb = "";
		String ret = "";
		String oocgiven = "";
		String clrsite = FPOrepost.getclrsite(itemid);
		String cusite = request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString();
		System.out.println("in fposervice itemid is " + itemid);
		String arrscan = "";
		String mailcl = "";
		mailcl = fpomainrepo.getmailcl(itemid, cusite);
		if (itemid.equals("ENTER ITEM_ID"))
			itemid = "";
		else if (!(itemid.isEmpty())) {
			if (!(fpoCurQueRepo.chckitmid(itemid).equals(itemid)))
				return "INVALID ENTRY";
			String cursite = fpoCurQueRepo.curqueueitem(itemid);
			System.out.println(cursite);
			if (cursite != null) {
				if (clrsite != null && clrsite != cursite && (clrsite.equals(cusite) || cursite.equals(cusite))) {
					if (!(clrsite.equals(request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString())) || role.equals("PNA")
							|| role.equals("NRP") || role.equals("NAL"))
						curposb = " Clearance at " + clrsite;
				} else {
					if (!(cursite.equals(request.getSession().getAttribute("cuSite") == null ? null
							: request.getSession().getAttribute("cuSite").toString())) || role.equals("PNA")
							|| role.equals("NRP") || role.equals("NAL"))
						curposb = " Belongs to " + cursite;
				}
			} else
				return " in 'W/O Pincode & Pending for Mapping' ";
			if (fpooocancelRepo.getPendingByarticleId(itemid) > 0) {
				if (clrsite != cusite) {
					curpos1 = "OOC GIVEN; " + " DATE " + FPOrepost.getoocdtmvmnt(itemid, clrsite)
							+ "; IN OOC CANCEL Queue;" + curposb;
					oocgiven = "OOC CANCEL queue;";
				} else {
					curpos1 = "OOC GIVEN; " + " DATE "
							+ FPOrepost.getoocdtmvmnt(itemid,
									session.getAttribute("cuSite") == null ? null
											: session.getAttribute("cuSite").toString())
							+ "; IN OOC CANCEL Queue;" + curposb;
					oocgiven = "OOC CANCEL queue;";
				}
			} else if (fpooocancelRepo.getooccancelarticleId(itemid) > 0) {
				if (clrsite != cusite && clrsite != null) // changed by prem (&& clrsite!=null)
				{
					curpos1 = "OOC GIVEN; " + " DATE " + FPOrepost.getmaxoocdt(itemid, clrsite)
							+ "; OOC CANCELLED  -  ";
					oocgiven = "OOC CANCELLED;";
				} else {
					curpos1 = "OOC GIVEN; " + " DATE " + FPOrepost.getmaxoocdt(itemid,
							session.getAttribute("cuSite") == null ? null : session.getAttribute("cuSite").toString())
							+ "; OOC CANCELLED  -  ";
					oocgiven = "OOC CANCELLED;";
				}
				arrscan = "Y";
			} else {
				String arrindia = "", arrfpo = "";
				if (FPOrepost.getcouarrfpoitm(itemid) > 0) {
					curpos1 = "Articles arrived at Destination FPO;";
					arrfpo = "Y";
				} else if (FPOrepost.getcouarrooeitm(itemid) > 0) {
					curpos1 = "Articles arrived at India;";
					arrindia = "Y";
				}
				if (arrfpo.equals("Y") && arrindia.equals(""))
					curpos1 = curpos1;
				else if (arrindia.equals("Y") && arrfpo.equals(""))
					curpos1 = "Articles Entered India -  in Transit to FPO;";
				else if (arrindia.equals("") && arrfpo.equals(""))
					curpos1 = "Articles not Arrived;";
				if (FPOrepost.getarr(itemid, cursite, clrsite) > 0) {
					arrscan = "Y";
					if (arrfpo.equals("Y"))
						curpos1 = curpos1 + "";
					else if (curpos1.equals("Articles not Arrived;"))
						curpos1 = "Articles Physically arrived at destination FPO and confirmed;";
					else
						curpos1 = "Articles Entered India; Physically arrived at destination FPO and confirmed;";
				} else if (curpos1.equals("Articles not Entered INDIA;")
						|| curpos1.equals("Articles Entered India -  in Transit to FPO;"))
					curpos1 = curpos1;
				else
					curpos1 = curpos1 + " Waiting in BAG SCAN QUEUE;";
			}
			if (curpos1.contains("OOC CANCELLED") && clrsite != null) // changed by prem && clrsite!=null
				curque = fpoQueryDecisionRepo.getabsqueItm(itemid, clrsite);
			else if (curposb.contains("Belongs"))
				curque = fpoQueryDecisionRepo.getabsqueItm(itemid, cursite);
			else
				curque = fpoQueryDecisionRepo.getabsqueItmw(itemid);
			if (curque == null) {
				if (FPOrepost.getcurroleead(itemid).equals("PAC"))
					ret = "WAITING IN EAD/PAC QUEUE; " + curpos1 + curpos;
				else
					ret = "WAITING IN EAD/PAO QUEUE; " + curpos1 + curpos;
			}
			if (curque != null)
				curpos2 = fpoQueryDecisionRepo.getcurque(curque);
			if (curpos2 != null) {
				if (curpos2.contains("AAF") && (curpos1.equals("Articles not Arrived;")
						|| curpos1.equals("Articles Entered India -  in Transit to FPO;")
						|| curpos1.contains("Waiting in BAG SCAN QUEUE;")))
					curpos2 = curpos2.replace("F", "A");
			}
			if (FPOrepost.getoocdtitm(itemid,
					session.getAttribute("cuSite") == null ? null : session.getAttribute("cuSite").toString()) != null
					&& oocgiven == null) {
				oocgiven = "OOC GIVEN";
				curpos2 = "OOC GIVEN";
			}
			if (curpos2 != null) {
				if (curpos2.contains("OOC GIVEN") || oocgiven.contains("OOC GIVEN"))
					return "OOC GIVEN; " + " DATE " + FPOrepost.getoocdtitm(itemid,
							session.getAttribute("cuSite") == null ? null : session.getAttribute("cuSite").toString())
							+ curpos + curposb;
				else if (curque != null) {
					if (curque.equals("C1") || curque.equals("C2"))
						return mailcl + ";  " + curpos2 + ";" + "Waiting IN AAF / PAO QEUEUE " + curpos + curposb;
					else if (curque.equals("A2") && arrscan.equals("Y"))
						return mailcl + ";  " + curpos1 + " in AAF QUERY QUEUE; " + curposb;
					else if (curque.equals("A2") && arrscan.equals(""))
						return mailcl + ";  " + curpos1 + " in AAA QUERY QUEUE; " + curposb;
					else if (curque.equals("P2") && arrscan.equals(""))
						return mailcl + ";  " + curpos1 + " in AAA QUERY QUEUE; " + curposb;
					else if (curque.equals("P2") && arrscan.equals("Y"))
						return mailcl + ";  " + curpos1 + " in AAF QUERY QUEUE; " + curposb;
					else
						return mailcl + ";  " + curpos1 + " " + curpos2 + "; " + curpos + curposb;
				} else
					return mailcl + ";  " + ret + " " + curpos + " " + curposb;
			}
		}
		return null;
	}

	// ---- changed on Nov 17th
	/*
	 * public String getposold(String cinNo, String itemid, HttpSession session,
	 * HttpServletRequest request) { String curque; //String cursite; curque=""; int
	 * opt=0; String curpos=""; System.out.println("in fposervice cinno is "
	 * +cinNo); System.out.println("in fposervice itemid is " +itemid); if
	 * (cinNo.equals("ENTER CIN_NO")) cinNo=""; else if
	 * (itemid.equals("ENTER ITEM_ID")) itemid=""; if (!(cinNo.isEmpty())) {
	 * cursite=fpoCurQueRepo.getcusitecin(cinNo); System.out.println(cursite); if
	 * (cursite==null) opt=6; else {if
	 * (!(cursite.equals(session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()))) {System.out.println("no");
	 * opt=4;} else { opt=1; curque =
	 * fpoCurQueRepo.getcurqueCin(cinNo,session.getAttribute("cuSite") == null ?
	 * null : session.getAttribute("cuSite").toString());
	 * System.out.println("curque is"+curque); if (curque==null) { curque =
	 * fpoQueryDecisionRepo.getinitqueCin(cinNo,session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()); opt=3; } else if
	 * (curque.equals("E4")) {System.out.println("s"); curque =
	 * fpoQueryDecisionRepo.getabsqueCin(cinNo,session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()); if (curque == null)
	 * curque="E4"; } System.out.println("curque is"+curque); }}} else if
	 * (!(itemid.isEmpty())) { cursite=fpoCurQueRepo.getcusiteItm(itemid);
	 * System.out.println(cursite); if (cursite==null) opt=7; else {if
	 * (!(cursite.equals(session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()))) {System.out.println("no");
	 * opt=4;} else { opt=2; curque =
	 * fpoCurQueRepo.getcurqueItm(itemid,session.getAttribute("cuSite") == null ?
	 * null : session.getAttribute("cuSite").toString());
	 * System.out.println("curque is"+curque); if (curque==null) { curque =
	 * fpoQueryDecisionRepo.getinitqueItm(itemid,session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()); opt=3; } else if
	 * (curque.equals("E4")) {System.out.println("s"); curque =
	 * fpoQueryDecisionRepo.getabsqueItm(itemid,session.getAttribute("cuSite") ==
	 * null ? null : session.getAttribute("cuSite").toString()); if (curque == null)
	 * curque="E4"; } System.out.println("curque is"+curque); }}} if (opt==4)
	 * {curpos = "BELONGS TO FPO SITE " + cursite; return curpos; } else { if
	 * (curque==null) { if (opt == 1 || opt == 6) curpos="NO SUCH CIN FOUND.."; else
	 * if ( opt == 2 || opt == 7) curpos="NO SUCH ITEM ID FOUND..";} else { if
	 * ((FPOrepost.getscanfpo(itemid, session.getAttribute("cuSite") == null ? null
	 * : session.getAttribute("cuSite").toString()) > 0)) { curpos=
	 * "Arrived at FPO / Bag Scan Entry Queue -> " ;
	 * curpos=curpos+fpoQueryDecisionRepo.getnxtque(curque);} else
	 * curpos=fpoQueryDecisionRepo.getcurque(curque);} if (opt==3) curpos="WAITING "
	 * + curpos + " / "+ FPOrepost.getroleitm(itemid,session.getAttribute("cuSite")
	 * == null ? null : session.getAttribute("cuSite").toString()) + " QUEUE";
	 * System.out.println("curpos="+curpos+",opt="+opt); if (curque.equals("E3") &&
	 * opt==2) curpos="WAITING "+curpos; if (curpos!=null) { if
	 * (!(curpos.contains("EAD")) && !(curpos.startsWith("NO SUCH")) &&
	 * !(curpos.contains("GIVEN"))) { if (opt == 1) { //if
	 * (FPOrepost.getcouarrcin(cinNo) > 0) // curpos = "Articles " + curpos; //else
	 * // curpos = "Articles not " + curpos;} //else if (opt == 2) //{ if
	 * (FPOrepost.getcouarritm(itemid) > 0) // curpos = "Articles " + curpos; //
	 * else // curpos = "Articles not " + curpos;} } if (curpos.contains("EAD")) {
	 * if (opt == 1) curpos=curpos + " / " +
	 * FPOrepost.getrolecin(cinNo,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()) + " QUEUE"; else if (opt == 2)
	 * curpos=curpos + " / " +
	 * FPOrepost.getroleitm(itemid,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()) + " QUEUE"; if
	 * (FPOrepost.getarrfpo(itemid,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()) > 0) {
	 * System.out.println("here 1"); curpos=curpos +
	 * " ;  Articles Arrived at Destination FPO"; } else if
	 * (FPOrepost.getarr(itemid,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()) > 0) {
	 * System.out.println("here 2"); curpos=curpos +
	 * " ;  Articles Entered India -  in Transit to FPO"; } } if
	 * (curpos.contains("OOC GIVEN")) { if (opt == 1) curpos = curpos + " DATE " +
	 * FPOrepost.getoocdtcin(cinNo,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()); else if (opt == 2) curpos =
	 * curpos + " DATE " +
	 * FPOrepost.getoocdtitm(itemid,session.getAttribute("cuSite") == null ? null :
	 * session.getAttribute("cuSite").toString()); } if (curpos==null)
	 * curpos="INVALID ENTRY"; return curpos; } if (curpos==null) { if
	 * (FPOrepost.getitmcou(itemid) > 0) { curpos =
	 * "Pending in WITHOUT PINCODE for FPO SITE Mapping "; if
	 * ((FPOrepost.getarrfpocou(itemid) > 0) || (FPOrepost.getarrcou(itemid) > 0))
	 * curpos = curpos + " ;  Articles Arrived "; return curpos; } else return
	 * "INVALID ENTRY"; } } return "INVALID ENTRY"; }
	 */

	// changed on Nov 17th
	// Priority

	public String addfpopriority(String itmid, String stecde, String reason, HttpSession session,
			HttpServletRequest request) throws java.text.ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		String cin_No = fpoCurQueRepo.getcurque(itmid, request.getSession().getAttribute("cuSite").toString());
		System.out.println("cinno is " + cin_No);
		FPO_PRIORITY priority = new FPO_PRIORITY();
		priority.setCin_no(cin_No);
		priority.setItem_id(itmid);
		priority.setRole(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		priority.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		/* priority.setArtclestg(""); */
		priority.setPriority_dt(date);
		priority.setReason(reason);
		priorityrepo.save(priority);
		return null;
	}

	public FpoQueryDecision movasstoqrydata(FpoQueryDecision fpoqryDec, FpoMvmnt fpomvmnt, HttpSession session,
			HttpServletRequest request) {
		System.out.println("fpoqryDec.getCIN_NO()=" + fpoqryDec.getCIN_NO());
		System.out.println("fpoqryDec.getCIN_NO()=" + fpoqryDec.getQRY_TYPE());
		System.out.println("fpomvmnt.getCinNo()=" + fpomvmnt.getCinNo());

		String Cinno = fpoqryDec.getCIN_NO();

		String offID = request.getSession().getAttribute("role").toString();
		List<FPO_MAIN> fpoMain = FPOrepost.getmain(Cinno);
		Float maxaclassval = FPOrepost.getaclassval();

		String QryType = FPOrepost.getQryTypeDetails(Cinno);

		String depcmts = fpoqryDec.getDep_comments();
		fpoQueryDinRepo.updateCmts(fpomvmnt.getCinNo(), depcmts);

		System.out.println("threashold value" + maxaclassval);
		System.out.println("article value value" + fpoMain.get(0).getTOT_ASS_VAL());
		System.out.println("Role" + fpoMain.get(0).getROLE());

		java.util.Date curdt = new java.util.Date();
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Long slno = fpoMvmntRepo.getMaxSlOnCin(Cinno);
		if (null == slno)
			slno = Long.valueOf(0);
		System.out.println("fpoMvmnt.getCIN_NO()=" + Cinno);
		String offid = fpoMvmntRepo.getoffid(Cinno);
		String role = fpoMvmntRepo.getrole(Cinno);
		System.out.println("cinno=" + Cinno + "offid=" + offid + "role=" + role);
		System.out.println("Logincontroller cinno=" + request.getSession().getAttribute("cinno") + "offid="
				+ request.getSession().getAttribute("offId") + "role=" + request.getSession().getAttribute("role"));
		fpoMvmntRepo.updextdtstr(Cinno, utilDate);
		System.out.println(request.getSession().getAttribute("cuSite"));

		if (!(QryType.equals("A3"))) {

			if ((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAO")) {
				insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "PAO", "P2",
						session, request);
				// fpoQueryDecisionRepo.updateForAOtoAC(fpoqryDec.getCIN_NO(), utilDate,
				// fpoMain.get(0).getACL_OFFID(), "PAC");
				fpoQueryDecisionRepo.updassqry(fpoqryDec.getCIN_NO(), utilDate, "PAC");
			}
			// for approval only
			else if ((fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval) && fpoMain.get(0).getROLE().equals("PAC")) {
				insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "IMP", "P2",
						session, request);
				// fpoQueryDecisionRepo.updateForAOtoAC(fpoqryDec.getCIN_NO(),
				// utilDate,fpoMain.get(0).getACL_OFFID(), "IMP");
				fpoQueryDecisionRepo.updassqry(fpoqryDec.getCIN_NO(), utilDate, "IMP");
			} else {
				insertIntofpoMvmntDb(fpomvmnt, Cinno, fpoMvmntRepo.getcindtmvmnt(Cinno), curdt, slno, "IMP", "P2",
						session, request);
				fpoQueryDecisionRepo.updassqry(fpoqryDec.getCIN_NO(), utilDate, "IMP");
			}
			updfpocurque(Cinno, "P2", "IMP", utilDate, session, request);

			if (fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval && fpoMain.get(0).getROLE().equals("PAO"))
				fpomainrepo.updateRole(Cinno);
			else if (fpoMain.get(0).getTOT_ASS_VAL() > maxaclassval && fpoMain.get(0).getROLE().equals("PAC"))
				fpomainrepo.updatingRole(Cinno);

		}

		return fpoqryDec;
	}

	public List<FpoSecondDefaultQuery> getSecondDefualtQuery() {
		// TODO Auto-generated method stub
		List<FpoSecondDefaultQuery> fpoDefualtQueryList = fpoSecondDefualtQueryRepo.getsecdefQry1();
		return fpoDefualtQueryList;
	}

	public void sendSmsOnly(String mobileNumber, String smsText, String tmpId) {
	//	try {
			System.out.println("in sendsmsOnly");
			System.clearProperty("http.proxyHost");
			System.clearProperty("http.proxyPort");
			System.clearProperty("https.proxyHost");
			System.clearProperty("https.proxyPort");
			String targetURL = "http://sms.cbec.gov.in:8080/sms/send?mob=" + mobileNumber + "&txtmsg=" + smsText
					+ "&tmpid='" + tmpId + "'";
			System.out.println(targetURL);
	/*		RestTemplate Rest = new RestTemplate();
			Rest.getForObject(targetURL, String.class);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString()); to be uncommented for Email and SmS
			System.out.println("Sending Msg Failed"); to be uncommented for Email and SmS
		}*/ //to be uncommented for Email and SmS
	}

	public void sendSms(String mobileNumber, String smsText, String tmpId, String dcallno, String cinNo,
			HttpSession session, HttpServletRequest request) {
		int fou = 0;
	//	try { to be uncommented for Email and SmS
			System.out.println("in sendsms");
			System.clearProperty("http.proxyHost");
			System.clearProperty("http.proxyPort");
			System.clearProperty("https.proxyHost");
			System.clearProperty("https.proxyPort");
			String targetURL = "http://sms.cbec.gov.in:8080/sms/send?mob=" + mobileNumber + "&txtmsg=" + smsText
					+ "&tmpid='" + tmpId + "'";
			System.out.println(targetURL);
		/*	RestTemplate Rest = new RestTemplate();    to be uncommented for Email and SmS
			Rest.getForObject(targetURL, String.class);   to be uncommented for Email and SmS
			fou = 1; to be uncommented for Email and SmS
		} catch (Exception e) { to be uncommented for Email and SmS
			System.out.println(e.getMessage().toString()); to be uncommented for Email and SmS
			System.out.println("Sending Msg Failed"); to be uncommented for Email and SmS
		}*/ // to be uncommented for Email and SmS
		if (fou == 1) {
			DCALLQRY_GEN dCALLQRY_GEN = fpoDcallQryRepo.getdCALLQRY_GENBydcallno(dcallno);
			int count = 1;

			if (dCALLQRY_GEN.getSmscou() != null) {
				count = Integer.parseInt(dCALLQRY_GEN.getSmscou()) + 1;
			}

			fpoDcallQryRepo.updateDcallMobileNO(dcallno, mobileNumber, count);

			List<FPO_MAIN> fpoMainData = FPOrepost.getmain(cinNo);

			insertpushDcall(mobileNumber, fpoMainData.get(0), "SMS", dcallno, session, request);
		}
	}

	public void insertpushDcall(String input, FPO_MAIN fpo_MAIN, String type, String dcallno, HttpSession session,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Push_dcall push_dcall = new Push_dcall();
		push_dcall.setItem_id(fpo_MAIN.getITEM_ID());
		push_dcall.setCin_no(fpo_MAIN.getId());
		push_dcall.setCin_dt(fpo_MAIN.getCIN_DT());
		push_dcall.setMobile_no_1(fpo_MAIN.getRECP_PHONE());
		push_dcall.setEmail_id_1(fpo_MAIN.getRECP_EMAILID());
		push_dcall.setGen_dt(new Date());
		push_dcall.setOff_id(request.getSession().getAttribute("offId") == null ? null
				: request.getSession().getAttribute("offId").toString());
		push_dcall.setRole(request.getSession().getAttribute("role") == null ? null
				: request.getSession().getAttribute("role").toString());
		push_dcall.setCus_site(request.getSession().getAttribute("cuSite") == null ? null
				: request.getSession().getAttribute("cuSite").toString());
		if (type.equalsIgnoreCase("SMS")) {
			push_dcall.setMobile_no_2(input);
		} else if (type.equalsIgnoreCase("EMAIL")) {
			push_dcall.setEmail_id_2(input);
		}
		push_dcall.setDcallno(dcallno);

		dcall_pushRepo.save(push_dcall);
	}

	// Re-allocate Service

	public REALLOCATION reallocateservice(REALLOCATION reallocateval, HttpSession session, HttpServletRequest request)
			throws ParseException, java.text.ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		reallocateval.setReallocation_dt(date);
		reallocateval.setOff_id(request.getSession().getAttribute("offId").toString());
		reallocateval.setRole(request.getSession().getAttribute("role").toString());
		reallocateval.setCus_site(request.getSession().getAttribute("cuSite").toString());
		reallocaterepo.save(reallocateval);
		return null;
	}

	public String updatereallocation(String roles, String frmssid, String tossid, String mailcls) {
		FPOrepost.replaceoffid(roles, frmssid, tossid, mailcls);
		return null;
	}

	public void moveDetToAss(String cinNo, HttpSession session, HttpServletRequest request) {
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		updfpocurque(cinNo, "P1", StaticConstants.APPRAISER, utilDate, session, request);
		String offid = fpoQueryDecisionRepo.getoffidcin(cinNo);
		fpoQueryDecisionRepo.updqryASS(cinNo, utilDate, offid);
	}

	public void moveDetToAssWithClrSite(String cinNo, HttpSession session, FPO_MAIN fpoMain,
			HttpServletRequest request) {
		fpoMvmntRepo.updclrSite(cinNo, fpoMain.getClrSite());
		String utilDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		String clrsite = fpoMain.getCUS_SITE();
		if (fpoMain.getClrSite() != null)
			clrsite = fpoMain.getClrSite();
		updfpocurqueWithClrSite(cinNo, "P1", StaticConstants.APPRAISER, utilDate, session, clrsite, request);
		String offid = fpoQueryDecisionRepo.getoffidcin(cinNo);
		fpoQueryDecisionRepo.updqryASSClrSite(cinNo, utilDate, offid, clrsite);
	}

	public void updfpocurqueWithClrSite(String Cinno, String curque, String Role, String Quedt, HttpSession session,
			String clrsite, HttpServletRequest request) {
		/*
		 * Long queno = fpoCurQueRepo.getMaxQnoOnCinNo(Cinno,
		 * request.getSession().getAttribute("cuSite") == null ? null :
		 * request.getSession().getAttribute("cuSite").toString());
		 */
		// changed for clearance site
		Long queno = fpoCurQueRepo.getMaxQnoOnCinNo(Cinno);
		if (queno == null)
			queno = 1L;
		else
			queno = queno + 1L;
		// Long queid = fpoCurQueRepo.getIdOnCin(Cinno,
		// request.getSession().getAttribute("cuSite") == null ? null
		// : request.getSession().getAttribute("cuSite").toString());
		Long queid = fpoCurQueRepo.getIdOnCin(Cinno);
		if (queid == null)
			queid = 1L;
		else
			queid = queid + 1L;
		fpoCurQueRepo.updatefpocurque(Cinno, curque,
				request.getSession().getAttribute("role") == null ? null
						: request.getSession().getAttribute("role").toString(),
				queno, Quedt,
				request.getSession().getAttribute("offId") == null ? null
						: request.getSession().getAttribute("offId").toString(),
				request.getSession().getAttribute("cuSite") == null ? null
						: request.getSession().getAttribute("cuSite").toString());
	}
	// FPO-Recall

	public FPO_RE_CALL reCalldata(HttpSession session, FPO_RE_CALL recallValue, HttpServletRequest request)
			throws ParseException, java.text.ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		String cinval = FPOrepost.getcin_no(recallValue.getItem_id());
		recallValue.setCinNO(cinval);
		recallValue.setRecall_dt(date);
		recallValue.setOff_id(request.getSession().getAttribute("offId").toString());
		recallValue.setCusSite(request.getSession().getAttribute("cuSite").toString());
		recallValue.setRole(request.getSession().getAttribute("role").toString());
		return fporecallrepo.save(recallValue);
	}

	public void updateqrydesc(HttpSession session, FPO_RE_CALL recalldata, HttpServletRequest request) {
		String offID = request.getSession().getAttribute("offId").toString();
		String Csval = request.getSession().getAttribute("cuSite").toString();
		String role = request.getSession().getAttribute("role").toString();
		String itmID = recalldata.getItem_id();
		String qryStge = recalldata.getArtclestg();
		FPOrepost.updatFpoQryDeci(itmID, Csval, role, offID, qryStge);
	}

	public FpoMvmnt insertintoFpoMvmnt(HttpSession session, FPO_RE_CALL fporecall, String setrole,
			HttpServletRequest request) throws java.text.ParseException {
		Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		System.out.println(fporecall.getRole());
		String Csval = request.getSession().getAttribute("cuSite").toString();
		Long getslnoval = fpoMvmntRepo.getfpomvmntSlno(Csval, fporecall.getCinNO());
		FpoMvmnt fpomvmnt = new FpoMvmnt();
		fpomvmnt.setCinNo(fporecall.getCinNO());
//		fpomvmnt.setExtDt(date);
		fpomvmnt.setEndDt(date);
		fpomvmnt.setCusSite(Csval);
		fpomvmnt.setItemid(fporecall.getItem_id());
		fpomvmnt.setStage(fporecall.getArtclestg());
		fpomvmnt.setSlNo(getslnoval + 1);
		fpomvmnt.setRole(setrole);
		Date dt = FPOrepost.getcinDt(fporecall.getItem_id());
		System.out.println(dt);
		fpomvmnt.setCinDt(dt);
		return fpoMvmntRepo.save(fpomvmnt);

	}

	public void addLogintrackdetails(HttpSession session, String type, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {

			FpoLoginTrack fpoLoginTrack = new FpoLoginTrack();

			log.info("test 1 in fposervice");
			fpoLoginTrack.setCUS_SITE(request.getSession().getAttribute("cuSite") == null ? null
					: request.getSession().getAttribute("cuSite").toString());
			log.info("test 2 in fposervice");
			fpoLoginTrack.setOFF_ID(request.getSession().getAttribute("offId") == null ? null
					: request.getSession().getAttribute("offId").toString());
			log.info("test 3 in fposervice");
			fpoLoginTrack.setROLE(request.getSession().getAttribute("role") == null ? null
					: request.getSession().getAttribute("role").toString());
			log.info("test 4 in fposervice");
			fpoLoginTrack.setGendt(new Date());
			log.info("test 5 in fposervice");

			if (type.equalsIgnoreCase("login")) {
				fpoLoginTrack.setLogintime(new java.util.Date());
			} else if (type.equalsIgnoreCase("logout")) {
				fpoLoginTrack.setLogouttime(new java.util.Date());
			}
			log.info("test 6 in fposervice");
			fpoLoginTrackRepo.save(fpoLoginTrack);
			log.info("test 7 in fposervice");

		} catch (Exception e) {
			System.out.println("Adding Track Failed");
		}

	}

	public void sendingEmail(String message123, String attachment) {

		List<FPO_MAIL> maillist = MailRepo.findAll();
		String host_Name = maillist.get(0).getHost_Name();
		Float port = maillist.get(0).getPort();
		String pwd = maillist.get(0).getPwd();
		String user_Name = maillist.get(0).getUser_Name();
		String support_MAIL1 = maillist.get(0).getSUPPORT_MAIL1();
		System.out.println(host_Name);
		System.out.println(port);
		System.out.println(pwd);
		System.out.println(user_Name);
		System.out.println(support_MAIL1);

		System.out.println(attachment);
		String to = null;
		to = support_MAIL1;
//			subject="this is";
//			message="hello";

		System.out.println("started");
		String username = user_Name;
//					 "vishwasg230450@gmail.com";  

		// change accordingly
		String password = pwd;
//	        		 "kboferahqaxketmg";

		// or IP address
		final String host = "localhost";

		// Get system properties
		Properties props = new Properties();

		// Setup mail server
//	        props.put("mail.smtp.host", host_Name);    

		// TLS Port
		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.gmail.com")) {

			props.put("mail.smtp.host", host_Name);
			props.put("mail.smtp.port", "587");
		}

		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.icegate.gov.in")) {

			props.put("mail.smtp.host", host_Name);
			props.put("mail.smtp.port", port.toString());
		}

		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.gmail.com")) {

			// enable authentication

			props.put("mail.smtp.auth", "true");

			// enable STARTTLS
			props.put("mail.smtp.starttls.enable", "true");
		}

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		Session session;
		if (maillist.get(0).getHost_Name().trim().equalsIgnoreCase("smtp.icegate.gov.in"))
			session = Session.getDefaultInstance(props);
		else {

			session = Session.getInstance(props, new javax.mail.Authenticator() {

				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(username, password);

				}
			});
		}
		try {

			// compose the message
			// javax.mail.internet.MimeMessage class is
			// mostly used for abstraction.
			Message message1 = new MimeMessage(session);

			// header field of the header.
			message1.setFrom(new InternetAddress(username));

			message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message1.setSubject("HELP REQUIRED");
//	            message1.setText("Yo it has been sent");

//	            BodyPart messageBodyPart2 = new MimeBodyPart();
//	            messageBodyPart2.setText("text");
			Multipart multipart = new MimeMultipart();

			if (attachment != null) {

				MimeBodyPart attachmentPart = new MimeBodyPart();

				attachmentPart.attachFile(attachment);

				multipart.addBodyPart(attachmentPart);

			}
			MimeBodyPart textPart = new MimeBodyPart();

//	       	  "D:\\oocnew.pdf"
			//

			textPart.setText(message123);

			multipart.addBodyPart(textPart);

			message1.setContent(multipart);

	//		Transport.send(message1); // send to be uncommented for Email and SmS

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
