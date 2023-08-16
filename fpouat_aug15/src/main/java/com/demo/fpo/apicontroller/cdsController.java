package com.demo.fpo.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.fpo.apiConfig.SoapClient;
import com.demo.fpo.apidatafetcher.stub.ArrayOfCDSView;
import com.demo.fpo.apidatafetcher.stub.ArrayOfResponseResponseDataTax;
import com.demo.fpo.apidatafetcher.stub.CDSView;
import com.demo.fpo.apidatafetcher.stub.Load;
import com.demo.fpo.apidatafetcher.stub.CreateOrUpdateResponses;
import com.demo.fpo.apidatafetcher.stub.DeclarationDeclarationDataContentPiece;
import com.demo.fpo.apidatafetcher.stub.DeclarationDeclarationDataDocument;
import com.demo.fpo.apidatafetcher.stub.LoadResponse;
import com.demo.fpo.apidatafetcher.stub.MailObject;
import com.demo.fpo.apidatafetcher.stub.ObjectFactory;
import com.demo.fpo.apidatafetcher.stub.Response;
import com.demo.fpo.apidatafetcher.stub.ResponseResponseData;
import com.demo.fpo.apidatafetcher.stub.ResponseResponseDataTax;
import com.demo.fpo.apimodel.Cmisseditem;
import com.demo.fpo.model.CIN_INFO;
import com.demo.fpo.apimodel.Cusrspsent;
import com.demo.fpo.apimodel.Loadmodel;
import com.demo.fpo.apimodel.PostDataServecds;
import com.demo.fpo.apimodel.FpoItemDetothduty;
import com.demo.fpo.apirepository.Article_Predes_Info_repo;
import com.demo.fpo.apirepository.CusrspsentRepo;
import com.demo.fpo.apirepository.DutyCalcDetailsRepo;
import com.demo.fpo.apirepository.FpoMainBeanRepository;
import com.demo.fpo.apirepository.FpoStatusRepo;
import com.demo.fpo.apirepository.FpoitemdetothdutyRepo;
import com.demo.fpo.apirepository.FpomaininsRepo;
import com.demo.fpo.apirepository.FposubmitRepo2;
import com.demo.fpo.apirepository.FposubmitRepoImpl;
import com.demo.fpo.apirepository.LoadFpoRepo;
import com.demo.fpo.apirepository.fposubmititemRepo2;
import com.demo.fpo.apirepository.fposubmititemRepoImpl;
import com.demo.fpo.apiservice.CmisseditemService;
import com.demo.fpo.apiservice.CusrspsentService;
import com.demo.fpo.apiservice.DeciReasService;
import com.demo.fpo.apiservice.FpocusdocService;
import com.demo.fpo.apiservice.FpodeciqryService;
import com.demo.fpo.apiservice.FpodetdocService;
import com.demo.fpo.apiservice.FpoitemService;
import com.demo.fpo.apiservice.FpomaininsService;
import com.demo.fpo.apiservice.FpostatusService;
import com.demo.fpo.apiservice.FposubmitService;
import com.demo.fpo.apiservice.LoadService;
import com.demo.fpo.apiservice.PostService;
import com.demo.fpo.apiservice.PreDesRestService;
import com.demo.fpo.apiservice.ReasoncdService;
import com.demo.fpo.apiservice.cusrspitemdetService;
import com.demo.fpo.apiservice.fposubmititemService;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.apimodel.Fposubmit;
import com.demo.fpo.model.FpoStatus;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoCurQueRepo;
import com.demo.fpo.repos.CININFO_repo;
import com.demo.fpo.apirepository.DutyCalcDetailsRepo;
import com.demo.fpo.apiservice.cusrspitemdetService;
import com.demo.fpo.apimodel.Reasoncd;
import com.demo.fpo.apimodel.cusrsp;
import com.demo.fpo.apimodel.cusrspitemdet;
import com.demo.fpo.apimodel.cusrspitemtaxes;
import com.demo.fpo.apimodel.errinsert;
import com.demo.fpo.apimodel.fposubmititem;
import com.demo.fpo.apimodel.DeciReas;
import com.demo.fpo.apimodel.Fpocusitmdoc;
import com.demo.fpo.apimodel.Fpodocdet;
import com.demo.fpo.apimodel.Fpoitemins;
import com.demo.fpo.apimodel.Fpomainins;
import com.demo.fpo.apiservice.cusrspitemtaxService;
import com.demo.fpo.apiservice.fpocusrspService;
import com.demo.fpo.apiservice.errService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fpo.idgen.FpoIdGenerator;

import java.math.BigDecimal;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Comparator;
//import java.util.Collections;
import java.util.Date;
//import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBElement;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@RestController
//@EnableAutoConfiguration

public class cdsController {

	@Autowired
	PostService postService;
	
	@Value("${maxNoOfCUSITMRec}")
	private Integer maxNoOfCUSITMRec;
	
	@Value("${maxNoOfMin}")
	private Integer maxNoOfMin;
	
	@Value("${CDSresponse}")
	private Boolean CDSresponse;

	@Autowired
	FposubmitRepoImpl fposubmitRepoImpl;
	
	@Autowired
	LoadFpoRepo loadFpoRepo;

	@Autowired
	private fposubmititemRepo2 FposubmititemRepo2;

	@Autowired
	FpomaininsRepo fpomaininsRepo;

	@Autowired
	FpoitemdetothdutyRepo fpoOthdutyrepo;

	@Autowired
	private CusrspsentRepo cusrspsentrepo;
	
	@Autowired
	private CININFO_repo cininfoREPO;

//	@Autowired
//	private CmisseditemRepo cmisseditemrepo;
	@Autowired
	private FpoCurQueRepo fpoCurQueRepo;
//	@Autowired
//	private FpodeciqryRepo fpodeciqryrepo;
//	
//	@Autowired
//	private DeciReasRepo decireasRepo;

	@Autowired
	private LoadFpoRepo loadfpoRepo1;

	@Autowired
	DutyCalcDetailsRepo dutyCalcDetailsRepo;

	@Autowired
	Article_Predes_Info_repo article_Predes_Info_repo;

    
    @Autowired
    private PreDesRestService restServicepredes;

	private LoadService loadService;
	private SoapClient soapClient;
	private FpomaininsService fpomaininsservice;
	private FpoitemService fpoitemService;
	private FposubmitService fposubmitService;
	private fposubmititemService fpoSubmititemService;
	private cusrspitemdetService cusrspitemdetService;
	private cusrspitemtaxService cusrspitemtaxService;
	private fpocusrspService fpocusrspService;
	private FpostatusService fpostatusService;
	private errService errService;
	private DeciReasService decireasService;
	private ReasoncdService reasoncdService;
	private CusrspsentService cusrspsentService;
	private FpocusdocService fpocusdocService;
	private FpodetdocService fpodetdocService;
	private CmisseditemService misseditemService;
	public Long itemno;
	public Long id;
	public Date frdt;
	public Date cdate;
	public java.sql.Date sentdt;
	public String othdesc;
	public Date todt;
	public String todt1;
	// public int loadid1=0;
	public int cntr = 0;
	public int fou = 0;
	public int itno = 0;
	public int itno1 = 0;
	public int pincodefou = 0;
	public int cityfou = 0;
	public int statefou = 0;
	public int othrta = 0;
	public String citynm;
	public String statenm;
	public String cinno;
	public String errstr = "";
	public String cintmp = "";
	public String cth = "";
	public String cth1;
	public String cth2;
	public String bcdgftrt1;
	public String bcdrt1;
	public String bcdgftrt2;
	public String othrt1;
	public String bcdrt2;
	public String igstrt1;
	public String igstrt2;
	public String swsrt1;
	public String swsrt2;
	public String pincode;
	public String fpocode;
	public String currcd;
	public String currrate;
	public float bcdeffrt;
	public BigDecimal cmp;
	public BigDecimal totdeclval;
	public BigDecimal totdutypay1;
	public BigDecimal declvalitm;
	public BigDecimal totassval;
	public BigDecimal assval;
	public BigDecimal dutyitm;
	public BigDecimal totduty;
	public float totdutyfg;
	public BigDecimal bcdduty;
	public BigDecimal igstduty;
	public BigDecimal swsduty;
	public float bcddutyfg;
	public float igstdutyfg;
	public float swsdutyfg;
	public float dutyitmfg;
	public BigDecimal assvalinsfrt;
	public BigDecimal ic1;
	public BigDecimal tcalassval;
	public long cdsviewrec;

	public static String fpoSeqNo;
	public static String cinSeqNo;
	public static String uniqId;
	public long decicd;
	public long reascd;
	public long id1;
	public String usercd;
	public String reasnm;
	public String qrytyp;
	public String assdt;
	public String clrdt;
	public String postingdt;
	private String category;
	public int couitm;
	public String itmid;
	// public String itemid;
	public Long loadid1;
	public int loadtimep;
	public Long sentid;
	
	

	int bombcount=1;
	int chncount=1;
	
	public static DUTY_CALC_DETAILS dutyCalc = new DUTY_CALC_DETAILS();

	
	
	public cdsController(SoapClient soapClient, FpomaininsService fpomaininsservice, FpoitemService fpoitemService,
			errService errService, FposubmitService fposubmitService, fposubmititemService fpoSubmititemService,
			fpocusrspService fpocusrspService, cusrspitemdetService cusrspitemdetService,
			DeciReasService decireasService, CusrspsentService cusrspsentService, ReasoncdService reasoncdService,
			cusrspitemtaxService cusrspitemtaxService, LoadService loadService, FpodeciqryService fpodeciqryService,
			FpostatusService fpostatusService, FpocusdocService fpocusdocService, FpodetdocService fpodetdocService,
			CmisseditemService misseditemService) {
		
		this.soapClient = soapClient;

		this.fpomaininsservice = fpomaininsservice;
		this.fpoitemService = fpoitemService;
		this.errService = errService;
		this.fposubmitService = fposubmitService;
		this.fpoSubmititemService = fpoSubmititemService;
		this.fpocusrspService = fpocusrspService;
		this.cusrspitemdetService = cusrspitemdetService;
		this.cusrspitemtaxService = cusrspitemtaxService;
		this.loadService = loadService;
		this.fpostatusService = fpostatusService;
		this.decireasService = decireasService;
		this.cusrspsentService = cusrspsentService;
		this.reasoncdService = reasoncdService;
		this.fpocusdocService = fpocusdocService;
		this.fpodetdocService = fpodetdocService;
		this.misseditemService = misseditemService;
   
		
	}
	
	
//	@Scheduled(cron = "0 0/30 * * * *")
//	@Scheduled(cron = "0 0/1 * * * *")
	@Scheduled(cron = "${cronjob.cusitmval}")
	@GetMapping(path = "/Load")
	// @PostMapping("/Load")

	public String getdata() {
		// List<String> itemidList = loadsampleservice.getAllData();
		// System.out.println("see what"+itemidList.size());
//		couitm=itemidList.size();

		List<DUTY_CALC_DETAILS> dutyCalc = dutyCalcDetailsRepo.findAll();
		
		if (!dutyCalc.isEmpty()) {
			cdsController.dutyCalc = dutyCalc.get(0);
		}

		// List<Loadmodel> loadModelList = new ArrayList<Loadmodel>();
		// List<Loadmodel> loadModelList = loadService.getAllData();
		// List<Loadmodel> loadModelList = loadfpoRepo1.getAllData();
		List<Loadmodel> loadModelList = loadfpoRepo1.getAllData();

		System.out.println(loadModelList.size());
		// int i=0;
		if (loadModelList.size() > 0) {
			loadModelList.stream().forEach(loadstat -> {
				// String token, postorgcd, custorgcd, flow,itemid;
				Loadmodel loadmodelfet = new Loadmodel();
				loadmodelfet.setTOKEN(loadstat.getTOKEN());
				loadmodelfet.setPOST_ORG_CD(loadstat.getPOST_ORG_CD());
				loadmodelfet.setCUST_ORG_CD(loadstat.getCUST_ORG_CD());
				loadmodelfet.setFLOW(loadstat.getFLOW());
				loadmodelfet.setFROMDT(loadstat.getFROMDT());
				loadmodelfet.setTODT(loadstat.getTODT());
				loadmodelfet.setITEM_ID(loadstat.getITEM_ID());
				loadmodelfet.setCNTRYCD(loadstat.getCNTRYCD());
				loadmodelfet.setDESTPOST_ORG_CD(loadstat.getDESTPOST_ORG_CD());
				loadmodelfet.setFROMZIP(loadstat.getFROMZIP());
				loadmodelfet.setORIGPOST_ORGCD(loadstat.getORIGPOST_ORGCD());
				loadmodelfet.setMAIL_CLASS_CD(loadstat.getMAIL_CLASS_CD());

				String token, postorgcd, custorgcd, flow;
				token = loadstat.getTOKEN();
				postorgcd = loadstat.getPOST_ORG_CD();
				custorgcd = loadstat.getCUST_ORG_CD();
				flow = loadstat.getFLOW();
				// itemid=loadstat.getITEM_ID();
				// loadid1=loadstat.getId();

				System.out.println("in the load loop");

//				token = loadstat.getTOKEN();
//				postorgcd = loadstat.getPOST_ORG_CD();
//				custorgcd = loadstat.getCUST_ORG_CD();
//				flow = loadstat.getFLOW();
//				itemid=loadstat.getITEM_ID();
				frdt = loadstat.getFROMDT();
				todt = loadstat.getTODT();
				loadtimep = (int) loadstat.getTimelapse();
				System.out.println(frdt);
				System.out.println(todt);
				loadid1 = loadstat.Id;
				System.out.println(token);
				System.out.println(postorgcd);
				System.out.println(custorgcd);
				System.out.println(loadstat.getITEM_ID());
				// System.out.println(itemid);
				java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				sentdt = currentDate1;
				// System.out.println(flow);
				// Load(token, postorgcd, custorgcd, itemid,flow);
				Load(loadmodelfet);
				// Load(token, postorgcd, custorgcd,flow);
				// });
				loadService.findById(loadid1, sentdt, cdsviewrec);
				System.out.println("load updated");
				Loadmodel loadmodel = new Loadmodel();
				loadmodel.setTOKEN(token);
				loadmodel.setPOST_ORG_CD(postorgcd);
				loadmodel.setCUST_ORG_CD(custorgcd);
				loadmodel.setFLOW(flow);
				// loadmodel.setCdsrecords(cdsviewrec);
				System.out.println("before adding from date:" + frdt);
				System.out.println("before adding todate:" + todt);
				java.util.Date frmdt;
				java.util.Date todte;
				//frdt = todt;
			
				frmdt =  loadFpoRepo.getfrdt();
				
			//	if (loadtimep > maxNoOfMin)
					frmdt = loadFpoRepo.getfrdt();
			//	else
			//		frmdt = loadstat.getFROMDT();
				
				todte = loadFpoRepo.gettodt();

				System.out.println("after adding from date:" + frdt);
				
				if (loadstat.getFROMDT() == null) {
					loadmodel.setFROMDT(null);
					loadmodel.setTODT(null);
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(frmdt);
				//	calendar.add(Calendar.SECOND, 1);

					frmdt = calendar.getTime();
					loadmodel.setFROMDT(frmdt);
				
					calendar.setTime(todt);
					calendar.add(Calendar.HOUR_OF_DAY, loadtimep);
					calendar.add(Calendar.MINUTE, loadtimep);
					// calendar.add(Calendar.HOUR_OF_DAY, 24);
					//todt = calendar.getTime();
					System.out.println("after adding from date:" + frmdt);
					System.out.println("after adding to date:" + todte);
					loadmodel.setTODT(todt);
				}
				loadmodel.setSUCCESS("N");
				
				if (loadtimep > 2850)
					loadtimep = 1440;
				else
				    loadtimep = loadtimep + 30;
				
				loadmodel.setId(loadid1 + 1);
				loadmodel.setLAST_EXECUTED(null);
				loadmodel.setTimelapse(loadtimep);
				java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				System.out.println("Current Date:" + currentDate);
				// loadmodel.setLAST_EXECUTED(currentDate);
				loadmodel = loadService.updateLoadmodelEntity(loadmodel);
				upddata();
				// getfpoqrydata() ;
				// System.out.println("getqrydeci loaded");
			});

		}
		return null;
	}
	
//	 @Scheduled(cron = "0 0/1 * * * *")
	@Scheduled(cron = "${cronjob.cusrspval}")
	@GetMapping(path = "/Response")
	public String getfpoqrydata() {
		java.sql.Date currentDate1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		sentdt = currentDate1;
		List<Cusrspsent> cusrspsentlst = cusrspsentrepo.getcusrsp();
		if (cusrspsentlst.size() > 0) {
			java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			System.out.println("Current Date:" + currentDate);
			cdate = currentDate;
			cusrspsentlst.stream().forEach(sent -> {

				id1 = sent.getId().longValue();
				cinno = sent.getCINNO();
				category = sent.getCATEGORY();
				cntr = cntr + 1;
//			System.out.println("cusrsp_sent count " + cusrspsentlst.size());
//			System.out.println("Cinno cusrspsent =" + cinno);
//			System.out.println("Category in cusrspsent=" + category);

				// System.out.println("cusrsp_sent counter " + cntr);
				// if (cusrspsentlst.size() > 0) {

				Fposubmit fpomainlist = fposubmitService.findById(cinno);

				// System.out.println("Ci no " + fpomainlist.getCIN_NO());
				// System.out.println("Item_id " + fpomainlist.getITEM_ID());

				reascd = 2;
				if (category.equals("OOC")) {
					decicd = 10; // to differentiate between EAD decisions and final decisions 10,11,12 is added
					reascd = 3;

					List<Reasoncd> reascdlst = reasoncdService.getAllData(reascd);
					reasnm = reascdlst.get(0).getREAS_NM();

				}
				if (category.equals("DET")) {
					decicd = 11; // to differentiate between EAD decisions and final decisions 10,11,12 is added
					reascd = 4;
					List<Reasoncd> reascdlst = reasoncdService.getAllData(reascd);
					reasnm = reascdlst.get(0).getREAS_NM();
				}
				if (category.equals("REV")) {
					decicd = 12; // to differentiate between EAD decisions and final decisions 10,11,12 is added
					reascd = 5;
					List<Reasoncd> reascdlst = reasoncdService.getAllData(reascd);
					reasnm = reascdlst.get(0).getREAS_NM();
				} else {
					if (decicd != 10 && decicd != 11 && decicd != 12) {
						decicd = Long.parseLong(category);
					}
					if (decicd != 8 && decicd != 7 && decicd != 10 && decicd != 11 && decicd != 12) {
						// changed on 14/12/2021 as per decision by team
						decicd = 1;
					}
					if (decicd == 10)  // to differentiate between EAD decisions and final decisions 10,11,12 is added
						decicd=2;
					else if (decicd == 11) // to differentiate between EAD decisions and final decisions 10,11,12 is added
						decicd=7;
					else if (decicd == 12) // to differentiate between EAD decisions and final decisions 10,11,12 is added
						decicd=3;

					// reasnm = "EAD Submission Duty Provisional";
					// System.out.println("Decision CD " + decicd);
					List<DeciReas> decireas = decireasService.getAllData(decicd);
					List<Reasoncd> reascdlst1 = reasoncdService.getAllData(reascd);
					reasnm = reascdlst1.get(0).getREAS_NM();

					reasnm = reasnm + " " + decireas.get(0).getDECI_NM();
					// System.out.println("Decision CD " + decicd);
					System.out.println("reason name " + "DECINM: " + decireas.get(0).getDECI_NM());
				}
				inscusrspfpo(fpomainlist);

				ObjectFactory objectFactoryrsp = new ObjectFactory();
				ArrayOfCDSView cdsviewsArrayrsp = new ArrayOfCDSView();
				List<CDSView> cdsViewListrsp = new ArrayList<CDSView>();
				CDSView cdsViewObjectrsp = new CDSView();
				MailObject mobj = new MailObject();
				Response cusresponse = new Response();
				ResponseResponseData responseResponseData = new ResponseResponseData();
				ResponseResponseDataTax responseResponseDataTax = new ResponseResponseDataTax();
				ResponseResponseDataTax responseResponseDataTaxIgst = new ResponseResponseDataTax();
				ResponseResponseDataTax responseResponseDataTaxSw = new ResponseResponseDataTax();
				List<ResponseResponseDataTax> responseResponseDataTaxList = new ArrayList<ResponseResponseDataTax>();
				CreateOrUpdateResponses response = objectFactoryrsp.createCreateOrUpdateResponses();

				cdsViewObjectrsp.setEntityState(1);

				mobj.setEntityState(1);
				mobj.setDestPostOrgCd(objectFactoryrsp.createMailObjectDestPostOrgCd(fpomainlist.getDESTPOST_ORG_CD()));
				mobj.setId(objectFactoryrsp.createMailObjectId((fpomainlist.getITEM_ID())));
				System.out.println("Cinno response  " + fpomainlist.getCIN_NO());
				System.out.println("Item ID response  " + fpomainlist.getITEM_ID());
				mobj.setMailClassCd(objectFactoryrsp.createMailObjectMailClassCd(fpomainlist.getMAIL_CLASS_CD()));
				mobj.setOrigPostOrgCd(objectFactoryrsp.createMailObjectOrigPostOrgCd(fpomainlist.getORIGPOST_ORG_CD()));
				mobj.setPId(objectFactoryrsp.createMailObjectPId(fpomainlist.getMAIL_PID()));
				XMLGregorianCalendar date2;
				try {
					// System.out.println(clrdt.toString().substring(0,
					date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(fpomainlist.getPOSTINGDT().trim());
//				date2 = DatatypeFactory.newInstance()
//						.newXMLGregorianCalendar(clrdt.substring(0, 10) + 'T' + clrdt.substring(11, 19));
					mobj.setPostingDt(date2);
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mobj.setTypeCd(objectFactoryrsp.createMailObjectTypeCd(fpomainlist.getTYPE_CD()));
				JAXBElement<MailObject> mailObject = objectFactoryrsp.createMailObject(mobj);

				List<fposubmititem> fpoitemlist = FposubmititemRepo2.getAllData(cinno);

				System.out.println(fpoitemlist);

				fpoitemlist.stream().forEach(fpoitemlst -> {
					cinno = fpoitemlst.getCIN_NO();
					itemno = fpoitemlst.getITEM_NO();
//					System.out.println("Cin no in fpo_item_det " + fpoitemlst.getCIN_NO());
//					System.out.println("Itemno in fpo_item_det " + fpoitemlst.getITEM_NO());
//					System.out.println("Item_id in fpo_item_det " + fpoitemlst.getITEM_ID());

					inscusrspitemfpo(fpoitemlst);
					inscusrspittaxfpo(fpoitemlst.getCIN_NO(), fpoitemlst.getITEM_ID(), fpoitemlst.getITEM_NO(),
							fpoitemlst.getCURRCD(), fpoitemlst.getBCD_AMT(), fpoitemlst.getBCD_RTA(), "BCD",
							fpoitemlst.getCUS_SITE());
					inscusrspittaxfpo(fpoitemlst.getCIN_NO(), fpoitemlst.getITEM_ID(), fpoitemlst.getITEM_NO(),
							fpoitemlst.getCURRCD(), fpoitemlst.getIGST_AMT(), fpoitemlst.getIGST_RTA(), "IGST",
							fpoitemlst.getCUS_SITE());
					inscusrspittaxfpo(fpoitemlst.getCIN_NO(), fpoitemlst.getITEM_ID(), fpoitemlst.getITEM_NO(),
							fpoitemlst.getCURRCD(), fpoitemlst.getSWS_AMT(), fpoitemlst.getSWS_RTA(), "SWS",
							fpoitemlst.getCUS_SITE());

					List<FpoItemDetothduty> fpoothdutylst = fpoOthdutyrepo.getAllData(itemno, cinno);

					System.out.println("size othduty " + fpoothdutylst.size());
					if (fpoothdutylst.size() > 0) {
						fpoothdutylst.stream().forEach(othdutylst -> {
							cinno = othdutylst.getId();
							itemno = othdutylst.getITEM_NO();
							if (othdutylst.getDUTY_RTA() == null) {

								othrt1 = "0";
								othrta = 0;
								Long rta1;
								rta1 = (long) othrta;
								othdutylst.setDUTY_RTA(rta1);
							} else {
								othrt1 = othdutylst.getDUTY_RTA().toString();
							}
							if (othdutylst.getDUTY_DESC() == null) {
								othdesc = "NIL";
							} else {
								othdesc = othdutylst.getDUTY_DESC();
							}

							System.out.println("othdesc1 " + othdesc);
							System.out.println("othdutyrta " + othdutylst.getDUTY_RTA());
							System.out.println("othdutyrtaass " + othrt1);
							// othrt1=othdutylst.getDUTY_RTA().toString();
							System.out.println("Cin no in othduty " + othdutylst.getId());
							System.out.println("Itemno in othduty " + fpoitemlst.getITEM_NO());
							System.out.println("Item_id in othduty " + othdutylst.getITEM_ID());
							// System.out.println("dutyrta in othduty " +
							// othdutylst.getDUTY_RTA().toString());
							inscusrspittaxfpooth(othdutylst.getId(), othdutylst.getITEM_ID(), othdutylst.getITEM_NO(),
									othdutylst.getDUTY_AMT(), othrt1, othdutylst.getDUTY_DESC(),
									othdutylst.getCUS_SITE());
							responseResponseDataTax.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
									othdutylst.getDUTY_AMT().setScale(2, BigDecimal.ROUND_DOWN)));
							responseResponseDataTax
									.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//							responseResponseDataTax.setDescription(objectFactoryrsp
//									.createResponseResponseDataTaxDescription(fpoitemlst.getITEM_DESC()));
//							responseResponseDataTax
//									.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fpoitemlst.getCTH())));
//							responseResponseDataTax.setNetWeight(
//									objectFactoryrsp.createResponseResponseDataTaxNetWeight(fpoitemlst.getNETWT()));
//							responseResponseDataTax.setNumber(objectFactoryrsp
//									.createResponseResponseDataTaxNumber((int) (long) fpoitemlst.getNOU()));
							responseResponseDataTax.setRate(objectFactoryrsp
									.createResponseResponseDataTaxRate(new BigDecimal(othdutylst.getDUTY_RTA())));
							responseResponseDataTax.setType(
									objectFactoryrsp.createResponseResponseDataTaxType(othdesc.substring(0, 2)));
							responseResponseDataTaxList.add(responseResponseDataTax);
//						System.out.println("othdesc1 "+othdesc);
//						System.out.println("othdutyrta "+othdutylst.getDUTY_RTA());
//						System.out.println("othdutyrtaass "+othrt1);
						});
					}

					BigDecimal bcdamnt=FposubmititemRepo2.getBCDAmnt(cinno);
					String BCD_RTA=FposubmititemRepo2.getBCD_RTA(cinno);
					responseResponseDataTax.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
							bcdamnt.setScale(2, BigDecimal.ROUND_DOWN)));
					responseResponseDataTax
							.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//					responseResponseDataTax.setDescription(
//							objectFactoryrsp.createResponseResponseDataTaxDescription(fpoitemlst.getITEM_DESC()));
//					responseResponseDataTax
//							.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fpoitemlst.getCTH())));
//					responseResponseDataTax.setNetWeight(
//							objectFactoryrsp.createResponseResponseDataTaxNetWeight(fpoitemlst.getNETWT()));
//					responseResponseDataTax.setNumber(
//							objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fpoitemlst.getNOU()));
					responseResponseDataTax.setRate(objectFactoryrsp
							.createResponseResponseDataTaxRate(new BigDecimal(BCD_RTA)));
					responseResponseDataTax.setType(objectFactoryrsp.createResponseResponseDataTaxType("BC"));
					responseResponseDataTaxList.add(responseResponseDataTax);
					// }
					// if (counti == 2) {
					BigDecimal IGST_AMT=FposubmititemRepo2.getIGST_AMT(cinno);
					String IGST_RTA=FposubmititemRepo2.getIGST_RTA(cinno);
					responseResponseDataTaxIgst.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
							IGST_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
					responseResponseDataTaxIgst
							.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//					responseResponseDataTaxIgst.setDescription(
//							objectFactoryrsp.createResponseResponseDataTaxDescription(fpoitemlst.getITEM_DESC()));
//					responseResponseDataTaxIgst
//							.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fpoitemlst.getCTH())));
//					responseResponseDataTaxIgst.setNetWeight(
//							objectFactoryrsp.createResponseResponseDataTaxNetWeight(fpoitemlst.getNETWT()));
//					responseResponseDataTaxIgst.setNumber(
//							objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fpoitemlst.getNOU()));
					responseResponseDataTaxIgst.setRate(objectFactoryrsp
							.createResponseResponseDataTaxRate(new BigDecimal(IGST_RTA)));
					responseResponseDataTaxIgst.setType(objectFactoryrsp.createResponseResponseDataTaxType("IG"));
					responseResponseDataTaxList.add(responseResponseDataTaxIgst);
					// }
					// if (counti == 3) {
					BigDecimal SW_AMT=FposubmititemRepo2.getSW_AMT(cinno);
					String SW_RTA=FposubmititemRepo2.getSW_RTA(cinno);
					responseResponseDataTaxSw.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
							SW_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
					responseResponseDataTaxSw
							.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//					responseResponseDataTaxSw.setDescription(
//							objectFactoryrsp.createResponseResponseDataTaxDescription(fpoitemlst.getITEM_DESC()));
//					responseResponseDataTaxSw
//							.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fpoitemlst.getCTH())));
//					responseResponseDataTaxSw.setNetWeight(
//							objectFactoryrsp.createResponseResponseDataTaxNetWeight(fpoitemlst.getNETWT()));
//					responseResponseDataTaxSw.setNumber(
//							objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fpoitemlst.getNOU()));
					responseResponseDataTaxSw.setRate(objectFactoryrsp
							.createResponseResponseDataTaxRate(new BigDecimal(SW_RTA)));
					responseResponseDataTaxSw.setType(objectFactoryrsp.createResponseResponseDataTaxType("SW"));
					responseResponseDataTaxList.add(responseResponseDataTaxSw);
					// }
					// counti = counti + 1;
					// -----------------------------------responseResponseDataTax------------------/
					ArrayOfResponseResponseDataTax responsetax = new ArrayOfResponseResponseDataTax();
					// if (totduty.compareTo(BigDecimal.ZERO) == 1)
					System.out.println("reason code " + reascd + "reason name " + reasnm);
					// if (reascd == 2||reascd==4) {
					
					

					ResponseResponseDataTax responseResponseDataTaxFN = new ResponseResponseDataTax();
					ResponseResponseDataTax responseResponseDataTaxPL = new ResponseResponseDataTax();

					
					BigDecimal FN_AMT=fpoCurQueRepo.getFN_AMT(cinno);
					BigDecimal PL_AMT=fpoCurQueRepo.getPL_AMT(cinno);
					
					if(FN_AMT == null)
						FN_AMT=new BigDecimal("0");
					if(PL_AMT == null)
						PL_AMT=new BigDecimal("0");
					
					BigDecimal fn = new BigDecimal("0");
					
					int res = FN_AMT.compareTo(fn);
					
					if( res == 1 ) {
						
						responseResponseDataTaxFN.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
								FN_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
						responseResponseDataTaxFN
								.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
						responseResponseDataTaxFN.setRate(objectFactoryrsp
								.createResponseResponseDataTaxRate(new BigDecimal("0")));
						responseResponseDataTaxFN.setType(
								objectFactoryrsp.createResponseResponseDataTaxType("FN"));
						responseResponseDataTaxList.add(responseResponseDataTaxFN);
					}

					int res1 = PL_AMT.compareTo(fn);
					

					
					if( res1 == 1 ) {
						
						responseResponseDataTaxPL.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
								PL_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
						responseResponseDataTaxPL
								.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
						responseResponseDataTaxPL.setRate(objectFactoryrsp
								.createResponseResponseDataTaxRate(new BigDecimal("0")));
						responseResponseDataTaxPL.setType(
								objectFactoryrsp.createResponseResponseDataTaxType("PL"));
						responseResponseDataTaxList.add(responseResponseDataTaxPL);
					}
					if (reascd == 2) {
					} else {
						responsetax.getResponseResponseDataTax().add(responseResponseDataTax);
						responsetax.getResponseResponseDataTax().add(responseResponseDataTaxIgst);
						responsetax.getResponseResponseDataTax().add(responseResponseDataTaxSw);
						
						if( res == 1 ) {
							responsetax.getResponseResponseDataTax().add(responseResponseDataTaxFN);
						}
						if( res1 == 1 ) {
							responsetax.getResponseResponseDataTax().add(responseResponseDataTaxPL);
						}
					}
					JAXBElement<ArrayOfResponseResponseDataTax> arrayOfResponseResponseDataTaxes = objectFactoryrsp
							.createResponseResponseDataTaxes(responsetax);
					// if (totduty.compareTo(BigDecimal.ZERO) == 1)

					arrayOfResponseResponseDataTaxes.setValue(responsetax);

					cusresponse.setEntityState(1);
					cusresponse.setCDSStateCd(55);
					cusresponse.setCustOrganizationCd(
							objectFactoryrsp.createCDSObjectCustOrganizationCd(fpomainlist.getCUST_ORG_CD()));
					cusresponse.setPostOrganizationCd(
							objectFactoryrsp.createCDSObjectPostOrganizationCd(fpomainlist.getPOST_ORG_CD()));
					// cusresponse.setPId(objectFactoryrsp.createCDSObjectPId(fposubmit.getMAIL_PID()));

					JAXBElement<Response> responseJaxEle = objectFactoryrsp.createResponse(cusresponse);
					XMLGregorianCalendar date3;
					try {
						date3 = DatatypeFactory.newInstance()
								// .newXMLGregorianCalendar("2015-04-28T14:23:38.521Z");
								// .newXMLGregorianCalendar("2020-01-20T15:29:01");
								.newXMLGregorianCalendar(clrdt.substring(0, 10) + 'T' + clrdt.substring(11, 19));
						responseResponseData
								.setClearanceDt(objectFactoryrsp.createResponseResponseDataClearanceDt(date3));
					} catch (DatatypeConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					responseResponseData.setDecisionCd(
							objectFactoryrsp.createResponseResponseDataDecisionCd(String.valueOf(decicd)));
					responseResponseData.setDecisionReasonCd(
							objectFactoryrsp.createResponseResponseDataDecisionReasonCd(String.valueOf(reascd)));
					responseResponseData
							.setDecisionReasonNm(objectFactoryrsp.createResponseResponseDataDecisionReasonNm(reasnm));
					Boolean dt2 = false;
					Boolean dt5 = true;

					// if (totduty.compareTo(BigDecimal.ZERO) == 1)
					// if (reascd == 2|| reascd==4) {
					
					BigDecimal TOT_AMT_PAYABLE=fpoCurQueRepo.getTOT_AMT_PAYABLE(cinno);
					
					if(TOT_AMT_PAYABLE == null)
						TOT_AMT_PAYABLE=new BigDecimal("0");
					int res2 = PL_AMT.compareTo(fn);
					if (reascd == 2 && res2 == 0) {
						System.out.println("Duty is :  " + dt2);
						responseResponseData.setDutiable(dt2);
						responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);

					}else {
						System.out.println("Duty is :  " + dt5);
						responseResponseData.setDutiable(dt5);
						responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);
						responseResponseData
								.setTotalFee(objectFactoryrsp.createResponseResponseDataTotalFee(new BigDecimal(0)));
						responseResponseData.setTotalFeeCurrencyCd(
								objectFactoryrsp.createResponseResponseDataTotalFeeCurrencyCd("INR"));
					}

					JAXBElement<ResponseResponseData> responseResponseDataJaxEle = objectFactoryrsp
							.createResponseData(responseResponseData);
					cusresponse.setData(responseResponseDataJaxEle);

					cdsViewObjectrsp.setMailObject(mailObject);
					cdsViewObjectrsp.setResponse(responseJaxEle);

					cdsViewListrsp.add(cdsViewObjectrsp);

					JAXBElement<CDSView> array1 = objectFactoryrsp.createCDSView((CDSView) cdsViewListrsp.get(0));

					array1.setValue(cdsViewObjectrsp);
					cdsviewsArrayrsp.getCDSView().addAll(cdsViewListrsp);

					JAXBElement<ArrayOfCDSView> cdsViews = objectFactoryrsp
							.createCreateOrUpdateDeclarationsCdsViews(cdsviewsArrayrsp);
					cdsViews.setValue(cdsviewsArrayrsp);

					PostDataServecds postDataServe = postService.fetchPostData();
					// response.setSecurityToken("ad990024-fd05-498c-9f21-dfb0ed4ce032");
					response.setSecurityToken(postDataServe.getCusrspToken());
					response.setCdsViews(cdsViews);

					// usercd=postDataServe.getCusrspusercd();
					usercd = fpoCurQueRepo.getOffId(cinno);
					response.setUserCd(objectFactoryrsp.createCreateOrUpdateResponsesUserCd(usercd));
					System.out.println("CUSRSPDECIoth XML");

					soapClient.LoadResponse(response);

					System.out.println("CUSRSPDECIoth XML done");

					/// Response Ended
					//  buildcusrsp(fpomainlist,fpoitemlst);

				});
				sentid = sent.getId().longValue();
				System.out.println("Sent id" + sentid);
				System.out.println("Sent dt updated " + sentdt);
				cusrspsentService.findById(sentid, sentdt,cinno);

				System.out.println("Sent dt updated " + sentdt);
			});

		}

		return null;
	}

	// public void buildcusrsp(Deciqryfpostatus fpo,Fposubmit
	// fposubmit,fposubmititem fposubitm) {
	public void buildcusrsp(Fposubmit fposubmit, fposubmititem fposubitm) {
		// Response Started for cusrsp_sent records

		ObjectFactory objectFactoryrsp = new ObjectFactory();
		ArrayOfCDSView cdsviewsArrayrsp = new ArrayOfCDSView();
		List<CDSView> cdsViewListrsp = new ArrayList<CDSView>();
		CDSView cdsViewObjectrsp = new CDSView();
		MailObject mobj = new MailObject();
		Response cusresponse = new Response();
		ResponseResponseData responseResponseData = new ResponseResponseData();
		ResponseResponseDataTax responseResponseDataTax = new ResponseResponseDataTax();
		ResponseResponseDataTax responseResponseDataTaxIgst = new ResponseResponseDataTax();
		ResponseResponseDataTax responseResponseDataTaxSw = new ResponseResponseDataTax();
		List<ResponseResponseDataTax> responseResponseDataTaxList = new ArrayList<ResponseResponseDataTax>();

		CreateOrUpdateResponses response = objectFactoryrsp.createCreateOrUpdateResponses();

		cdsViewObjectrsp.setEntityState(1);

		mobj.setEntityState(1);
		mobj.setDestPostOrgCd(objectFactoryrsp.createMailObjectDestPostOrgCd(fposubmit.getDESTPOST_ORG_CD()));
		mobj.setId(objectFactoryrsp.createMailObjectId((fposubmit.getITEM_ID())));
		System.out.println("Cinno response  " + fposubmit.getCIN_NO());
		System.out.println("Item ID response  " + fposubmit.getITEM_ID());
		mobj.setMailClassCd(objectFactoryrsp.createMailObjectMailClassCd(fposubmit.getMAIL_CLASS_CD()));
		mobj.setOrigPostOrgCd(objectFactoryrsp.createMailObjectOrigPostOrgCd(fposubmit.getORIGPOST_ORG_CD()));
		mobj.setPId(objectFactoryrsp.createMailObjectPId(fposubmit.getMAIL_PID()));
		XMLGregorianCalendar date2;
		try {
			// System.out.println(clrdt.toString().substring(0,
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(fposubmit.getPOSTINGDT().trim());
//			date2 = DatatypeFactory.newInstance()
//					.newXMLGregorianCalendar(clrdt.substring(0, 10) + 'T' + clrdt.substring(11, 19));
			mobj.setPostingDt(date2);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mobj.setTypeCd(objectFactoryrsp.createMailObjectTypeCd(fposubmit.getTYPE_CD()));
		JAXBElement<MailObject> mailObject = objectFactoryrsp.createMailObject(mobj);
		// -----------------------------------responseResponseDataTax------------------/
		// if (counti == 1) {

		BigDecimal bcdamnt=FposubmititemRepo2.getBCDAmnt(fposubmit.getCIN_NO());
		String BCD_RTA=FposubmititemRepo2.getBCD_RTA(fposubmit.getCIN_NO());
		responseResponseDataTax.setAmount(objectFactoryrsp
				.createResponseResponseDataTaxAmount(bcdamnt.setScale(2, BigDecimal.ROUND_DOWN)));
		responseResponseDataTax.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//		responseResponseDataTax
//				.setDescription(objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//		responseResponseDataTax.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//		responseResponseDataTax
//				.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//		responseResponseDataTax
//				.setNumber(objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
		responseResponseDataTax
				.setRate(objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(BCD_RTA)));
		responseResponseDataTax.setType(objectFactoryrsp.createResponseResponseDataTaxType("BC"));
		responseResponseDataTaxList.add(responseResponseDataTax);
		// }
		// if (counti == 2) {
		BigDecimal IGST_AMT=FposubmititemRepo2.getIGST_AMT(fposubmit.getCIN_NO());
		String IGST_RTA=FposubmititemRepo2.getIGST_RTA(fposubmit.getCIN_NO());
		responseResponseDataTaxIgst.setAmount(objectFactoryrsp
				.createResponseResponseDataTaxAmount(IGST_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
		responseResponseDataTaxIgst.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//		responseResponseDataTaxIgst
//				.setDescription(objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//		responseResponseDataTaxIgst.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//		responseResponseDataTaxIgst
//				.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//		responseResponseDataTaxIgst
//				.setNumber(objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
		responseResponseDataTaxIgst
				.setRate(objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(IGST_RTA)));
		responseResponseDataTaxIgst.setType(objectFactoryrsp.createResponseResponseDataTaxType("IG"));
		responseResponseDataTaxList.add(responseResponseDataTaxIgst);
		// }
		// if (counti == 3) {
		BigDecimal SW_AMT=FposubmititemRepo2.getSW_AMT(fposubmit.getCIN_NO());
		String SW_RTA=FposubmititemRepo2.getSW_RTA(fposubmit.getCIN_NO());
		responseResponseDataTaxSw.setAmount(objectFactoryrsp
				.createResponseResponseDataTaxAmount(SW_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
		responseResponseDataTaxSw.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//		responseResponseDataTaxSw
//				.setDescription(objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//		responseResponseDataTaxSw.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//		responseResponseDataTaxSw
//				.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//		responseResponseDataTaxSw
//				.setNumber(objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
		responseResponseDataTaxSw
				.setRate(objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(SW_RTA)));
		responseResponseDataTaxSw.setType(objectFactoryrsp.createResponseResponseDataTaxType("SW"));
		responseResponseDataTaxList.add(responseResponseDataTaxSw);
		// }
		// counti = counti + 1;
		// -----------------------------------responseResponseDataTax------------------/
		ArrayOfResponseResponseDataTax responsetax = new ArrayOfResponseResponseDataTax();
		// if (totduty.compareTo(BigDecimal.ZERO) == 1)
		System.out.println("reason code " + reascd + "reason name " + reasnm);
		// if (reascd == 2||reascd==4) {

		
		

		ResponseResponseDataTax responseResponseDataTaxFN = new ResponseResponseDataTax();
		ResponseResponseDataTax responseResponseDataTaxPL = new ResponseResponseDataTax();

		
		BigDecimal FN_AMT=fpoCurQueRepo.getFN_AMT(fposubmit.getCIN_NO());
		BigDecimal PL_AMT=fpoCurQueRepo.getPL_AMT(fposubmit.getCIN_NO());
		
		if(FN_AMT == null)
			FN_AMT=new BigDecimal("0");
		if(PL_AMT == null)
			PL_AMT=new BigDecimal("0");
		
		BigDecimal fn = new BigDecimal("0");
		
		int res = FN_AMT.compareTo(fn);
		
		if( res == 1 ) {
			
			responseResponseDataTaxFN.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
					FN_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
			responseResponseDataTaxFN
					.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
			responseResponseDataTaxFN.setRate(objectFactoryrsp
					.createResponseResponseDataTaxRate(new BigDecimal("0")));
			responseResponseDataTaxFN.setType(
					objectFactoryrsp.createResponseResponseDataTaxType("FN"));
			responseResponseDataTaxList.add(responseResponseDataTaxFN);
		}

		int res1 = PL_AMT.compareTo(fn);
		

		
		if( res1 == 1 ) {
			
			responseResponseDataTaxPL.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
					PL_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
			responseResponseDataTaxPL
					.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
			responseResponseDataTaxPL.setRate(objectFactoryrsp
					.createResponseResponseDataTaxRate(new BigDecimal("0")));
			responseResponseDataTaxPL.setType(
					objectFactoryrsp.createResponseResponseDataTaxType("PL"));
			responseResponseDataTaxList.add(responseResponseDataTaxPL);
		}
		if (reascd == 2) {
		} else {
			responsetax.getResponseResponseDataTax().add(responseResponseDataTax);
			responsetax.getResponseResponseDataTax().add(responseResponseDataTaxIgst);
			responsetax.getResponseResponseDataTax().add(responseResponseDataTaxSw);
			
			if( res == 1 ) {
				responsetax.getResponseResponseDataTax().add(responseResponseDataTaxFN);
			}
			if( res1 == 1 ) {
				responsetax.getResponseResponseDataTax().add(responseResponseDataTaxPL);
			}
		}
		JAXBElement<ArrayOfResponseResponseDataTax> arrayOfResponseResponseDataTaxes = objectFactoryrsp
				.createResponseResponseDataTaxes(responsetax);
		// if (totduty.compareTo(BigDecimal.ZERO) == 1)

		arrayOfResponseResponseDataTaxes.setValue(responsetax);

		cusresponse.setEntityState(1);
		cusresponse.setCDSStateCd(55);
		cusresponse
				.setCustOrganizationCd(objectFactoryrsp.createCDSObjectCustOrganizationCd(fposubmit.getCUST_ORG_CD()));
		cusresponse
				.setPostOrganizationCd(objectFactoryrsp.createCDSObjectPostOrganizationCd(fposubmit.getPOST_ORG_CD()));
		// cusresponse.setPId(objectFactoryrsp.createCDSObjectPId(fposubmit.getMAIL_PID()));

		JAXBElement<Response> responseJaxEle = objectFactoryrsp.createResponse(cusresponse);
		try {
			date2 = DatatypeFactory.newInstance()
					// .newXMLGregorianCalendar("2015-04-28T14:23:38.521Z");
					// .newXMLGregorianCalendar("2020-01-20T15:29:01");
					.newXMLGregorianCalendar(clrdt.substring(0, 10) + 'T' + clrdt.substring(11, 19));
			responseResponseData.setClearanceDt(objectFactoryrsp.createResponseResponseDataClearanceDt(date2));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseResponseData
				.setDecisionCd(objectFactoryrsp.createResponseResponseDataDecisionCd(String.valueOf(decicd)));
//		responseResponseData.setDecisionReasonCd(
//				objectFactoryrsp.createResponseResponseDataDecisionReasonCd(String.valueOf(reascd)));
		responseResponseData.setDecisionReasonNm(objectFactoryrsp.createResponseResponseDataDecisionReasonNm(reasnm));
		Boolean dt2 = false;
		Boolean dt5 = true;

		// if (totduty.compareTo(BigDecimal.ZERO) == 1)
		// if (reascd == 2|| reascd==4) {
		
		BigDecimal TOT_AMT_PAYABLE=fpoCurQueRepo.getTOT_AMT_PAYABLE(cinno);
		
		if(TOT_AMT_PAYABLE == null)
			TOT_AMT_PAYABLE=new BigDecimal("0");
		int res2 = TOT_AMT_PAYABLE.compareTo(fn);
		if (reascd == 2 && res2 == 0) {
			System.out.println("Duty is :  " + dt2);
			responseResponseData.setDutiable(dt2);
			responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);

		}else {
			System.out.println("Duty is :  " + dt5);
			responseResponseData.setDutiable(dt5);
			responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);
			responseResponseData.setTotalFee(objectFactoryrsp.createResponseResponseDataTotalFee(new BigDecimal(0)));
			responseResponseData
					.setTotalFeeCurrencyCd(objectFactoryrsp.createResponseResponseDataTotalFeeCurrencyCd("INR"));
		}

		JAXBElement<ResponseResponseData> responseResponseDataJaxEle = objectFactoryrsp
				.createResponseData(responseResponseData);
		cusresponse.setData(responseResponseDataJaxEle);

		cdsViewObjectrsp.setMailObject(mailObject);
		cdsViewObjectrsp.setResponse(responseJaxEle);

		cdsViewListrsp.add(cdsViewObjectrsp);

		JAXBElement<CDSView> array1 = objectFactoryrsp.createCDSView((CDSView) cdsViewListrsp.get(0));

		array1.setValue(cdsViewObjectrsp);
		cdsviewsArrayrsp.getCDSView().addAll(cdsViewListrsp);

		JAXBElement<ArrayOfCDSView> cdsViews = objectFactoryrsp
				.createCreateOrUpdateDeclarationsCdsViews(cdsviewsArrayrsp);
		cdsViews.setValue(cdsviewsArrayrsp);

		PostDataServecds postDataServe = postService.fetchPostData();

		response.setSecurityToken(postDataServe.getCusrspToken());
		response.setCdsViews(cdsViews);

		// usercd=postDataServe.getCusrspusercd();
		usercd = fpoCurQueRepo.getOffId(fposubmit.getCIN_NO());
		response.setUserCd(objectFactoryrsp.createCreateOrUpdateResponsesUserCd(usercd));

		System.out.println("CUSRSPDECI XML");

		soapClient.LoadResponse(response);
		System.out.println("CUSRSPDECI XML done");

		return;
	}

	// public LoadResponse Load(String token, String postalOrgCd, String
	// customsOrgCd, String itemid,String flow) {

	public LoadResponse Load(Loadmodel loadmodelfet) {
		// public LoadResponse Load(String token, String postalOrgCd, String
		// customsOrgCd, String flow) {

		ObjectFactory objectFactory = new ObjectFactory();
		Load request = objectFactory.createLoad();
		request.setToken(loadmodelfet.getTOKEN());
		// request.setToken(token);
		// JAXBElement<String> postalOrgCdval =
		// objectFactory.createLoadPostalOrgCd(postalOrgCd);
		JAXBElement<String> postalOrgCdval = objectFactory.createLoadPostalOrgCd(loadmodelfet.getPOST_ORG_CD());
		request.setPostalOrgCd(postalOrgCdval);
		JAXBElement<String> idval = objectFactory.createLoadId(loadmodelfet.getITEM_ID());
		request.setId(idval);
		// JAXBElement<String> customsOrgCdval =
		// objectFactory.createLoadCustomsOrgCd(customsOrgCd);
		JAXBElement<String> customsOrgCdval = objectFactory.createLoadCustomsOrgCd(loadmodelfet.getCUST_ORG_CD());
		request.setCustomsOrgCd(customsOrgCdval);

		// JAXBElement<String> flowval = objectFactory.createLoadFlow(flow);
		JAXBElement<String> flowval = objectFactory.createLoadFlow(loadmodelfet.getFLOW());
		request.setFlow(flowval);
		
		
		JAXBElement<Integer> maxRecords = objectFactory.createLoadRecordsLimit(maxNoOfCUSITMRec);
		request.setRecordsLimit(maxRecords);
		request.setOnlyWithoutResponse(CDSresponse);
		request.setOnlyWithDeclarationOrResponse(false);
		
		
		XMLGregorianCalendar date2 = null;
		try {

			GregorianCalendar gc1 = new GregorianCalendar();
			// gc1.setTime(loadmodelfet.getFROMDT());
			if ((loadmodelfet.getFROMDT()) != null) {
				gc1.setTime(loadmodelfet.getFROMDT());
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc1);
				request.setDtFrom(objectFactory.createLoadDtFrom(date2));
				// gc1.setTime(frdt);
				// date2 =
				// DatatypeFactory.newInstance().newXMLGregorianCalendar("2020-11-25T13:00:01");
			} else {
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc1);
			}
			System.out.println("FromDT->" + date2);

			// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			// LocalDateTime now = LocalDateTime.now();
			GregorianCalendar gc2 = new GregorianCalendar();
			// gc2.setTime(loadmodelfet.getTODT());
			if ((loadmodelfet.getTODT()) != null) {
				// gc1.setTime(frdt);
				// date2 =
				// DatatypeFactory.newInstance().newXMLGregorianCalendar("2020-11-25T13:00:01");
				gc2.setTime(loadmodelfet.getTODT());
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc2);
				request.setDtTo(objectFactory.createLoadDtTo(date2));
			} else {
				date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc2);
			}

			System.out.println("ToDT->" + date2);
			// gc2.setTime(todt);
//			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(
//					dtf.format(now).toString().substring(0, 10) + 'T' + dtf.format(now).toString().substring(11, 19));
			// date2 =
			// DatatypeFactory.newInstance().newXMLGregorianCalendar("2020-11-25T19:00:01");

			// date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc2);
			// request.setDtTo(objectFactory.createLoadDtTo(date2));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("LOAD XML");
		try {
			System.out.println(new ObjectMapper().writeValueAsString(request));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LoadResponse response = soapClient.Load(request);
		System.out.println("response assigned");
		cth1 = "98049000";
		cth2 = "98041000";
		bcdrt1 = FposubmititemRepoImpl.getBCD(cth1);
		bcdrt2 = FposubmititemRepoImpl.getBCD(cth2);
		igstrt1 = FposubmititemRepoImpl.getIGST(cth1);
		igstrt2 = FposubmititemRepoImpl.getIGST(cth2);
		swsrt1 = FposubmititemRepoImpl.getSWS(cth1);
		swsrt2 = FposubmititemRepoImpl.getSWS(cth2);
		bcdgftrt1 =  FposubmititemRepoImpl.getBCD_GIFT(cth1);
		bcdgftrt2 =  FposubmititemRepoImpl.getBCD_GIFT(cth1);

		List<CDSView> cdsview = response.getLoadResult().getValue().getCDSView();

		System.out.println("dd->" + cdsview.size());
		cdsviewrec = cdsview.size();

		/*
		 * List<CDSView> sortedList = cdsview.stream()
		 * .sorted(Comparator.comparing(CDSView::getValue()))
		 * .collect(Collectors.toList());
		 */

		cdsview.sort((CDSView s1, CDSView s2) -> s1.getMailObject().getValue().getPostingDt().toString()
				.compareTo(s2.getMailObject().getValue().getPostingDt().toString()));

		cdsview.stream()
				// System.out.println("itemid is " +cdsview.size()));
				.forEach(mapper -> {

					try {
						if(mapper.getDeclaration().getValue() != null) {
							
						Fpomainins fpomainins = new Fpomainins();
						fpomainins.setITEM_ID(mapper.getMailObject().getValue().getId().getValue());
						itmid = mapper.getMailObject().getValue().getId().getValue().toString();
						System.out.println("itemid is " + mapper.getMailObject().getValue().getId().getValue());
						fpomainins.setPOSTINGDT(mapper.getMailObject().getValue().getPostingDt().toString());
						postingdt = mapper.getMailObject().getValue().getPostingDt().toString();
						if (FposubmititemRepo2.chkrecfou(itmid,postingdt) > 0)
						   System.out.println("Record already exists");
						else 
						{
						fpomainins
								.setCUST_ORG_CD(mapper.getDeclaration().getValue().getCustOrganizationCd().getValue());
						fpomainins.setMAIL_PID(mapper.getDeclaration().getValue().getMailObjectPId());
						fpomainins.setDECLARATION_PID(mapper.getDeclaration().getValue().getPId().getValue());
						fpomainins
								.setPOST_ORG_CD(mapper.getDeclaration().getValue().getPostOrganizationCd().getValue());
						// fpomainins.setDOCUMENTS(mapper.getDeclaration().getValue().getData().getValue().getDocuments().getValue());
						fpomainins.setGROSS_WT(
								mapper.getDeclaration().getValue().getData().getValue().getGrossWeight().getValue());
						fpomainins.setHANDLING_CLASS_CD(mapper.getDeclaration().getValue().getData().getValue()
								.getHandlingClassCd().getValue());
						fpomainins.setINS_VAL(
								mapper.getDeclaration().getValue().getData().getValue().getInsuredValue().getValue());
						fpomainins.setIns_VAL_CURRCD(mapper.getDeclaration().getValue().getData().getValue()
								.getInsuredValueCurrencyCd().getValue());
						fpomainins.setNATURE_TYPE_CD(
								mapper.getDeclaration().getValue().getData().getValue().getNatureTypeCd().getValue());
						fpomainins.setPOSTAGE_AMT(
								mapper.getDeclaration().getValue().getData().getValue().getPostage().getValue());
						fpomainins.setPOSTAGE_CURR_CD(mapper.getDeclaration().getValue().getData().getValue()
								.getPostageCurrencyCd().getValue());
						fpomainins.setRECP_ADD1(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientAddressLine1().getValue());
						fpomainins.setRECP_ADD2(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientAddressLine2().getValue());
						fpomainins.setRECP_CITY(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientCity().getValue());
						fpomainins.setRECP_CNTRY_CD(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientCountryCd().getValue());
						fpomainins.setRECP_EMAILID(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientEmail().getValue());
						fpomainins.setRECP_FAX(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientFax().getValue());
						fpomainins.setRECP_FNAME(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientFirstName().getValue());
						fpomainins.setRECP_IDREF(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientIdRef().getValue());
						fpomainins.setRECP_LNAME(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientLastName().getValue());
						fpomainins.setRECP_NAME(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientName().getValue());
						fpomainins.setRECP_STATE(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientState().getValue());
						fpomainins.setRECP_PHONE(mapper.getDeclaration().getValue().getData().getValue()
								.getRecipientTelephone().getValue());
						fpomainins.setRECP_ZIP(
								mapper.getDeclaration().getValue().getData().getValue().getRecipientZIP().getValue());
						fpomainins.setSEND_ADD1(mapper.getDeclaration().getValue().getData().getValue()
								.getSenderAddressLine1().getValue());
						fpomainins.setSEND_ADD2(mapper.getDeclaration().getValue().getData().getValue()
								.getSenderAddressLine2().getValue());
						fpomainins.setSEND_CITY(
								mapper.getDeclaration().getValue().getData().getValue().getSenderCity().getValue());
						fpomainins.setSEND_CNTRY_CD(mapper.getDeclaration().getValue().getData().getValue()
								.getSenderCountryCd().getValue());
						fpomainins.setSEND_EMAILID(
								mapper.getDeclaration().getValue().getData().getValue().getSenderEmail().getValue());
						fpomainins.setSEND_FNAME(mapper.getDeclaration().getValue().getData().getValue()
								.getSenderFirstName().getValue());
						fpomainins.setSEND_IDREF(
								mapper.getDeclaration().getValue().getData().getValue().getSenderIdRef().getValue());
						fpomainins.setSEND_LNAME(
								mapper.getDeclaration().getValue().getData().getValue().getSenderLastName().getValue());
						fpomainins.setSEND_NAME(
								mapper.getDeclaration().getValue().getData().getValue().getSenderName().getValue());
						fpomainins.setSEND_STATE(
								mapper.getDeclaration().getValue().getData().getValue().getSenderState().getValue());
						fpomainins.setSEND_PHONE(mapper.getDeclaration().getValue().getData().getValue()
								.getSenderTelephone().getValue());
						fpomainins.setSEND_ZIP(
								mapper.getDeclaration().getValue().getData().getValue().getSenderZIP().getValue());
						fpomainins.setTRANS_MODE(
								mapper.getDeclaration().getValue().getData().getValue().getTransportMode().getValue());
						// fpomainins.setTRANS_DATE(mapper.getDeclaration().getValue().getData().getValue().getTransportDate().getValue().toString());
						fpomainins.setDESTPOST_ORG_CD(mapper.getMailObject().getValue().getDestPostOrgCd().getValue());
						fpomainins.setITEM_ID(mapper.getMailObject().getValue().getId().getValue());
						fpomainins.setLOCALID(mapper.getMailObject().getValue().getLocalId().getValue());
						fpomainins.setLOCALID2(mapper.getMailObject().getValue().getLocalId2().getValue());
						fpomainins
								.setMAIL_CATEGORY_CD(mapper.getMailObject().getValue().getMailCategoryCd().getValue());
						fpomainins.setMAIL_CLASS_CD(mapper.getMailObject().getValue().getMailClassCd().getValue());
						// fpomainins.setMAIL_STATE_CD(mapper.getMailObject().getValue().getMailStateCd().getValue().toString());
						fpomainins.setMAIL_STATE_REMARKS(
								mapper.getMailObject().getValue().getMailStateRemarks().getValue());
						fpomainins.setORIGPOST_ORGCD(mapper.getMailObject().getValue().getOrigPostOrgCd().getValue());
						fpomainins.setUNIQUE_ID(mapper.getMailObject().getValue().getPId().getValue());
						fpomainins.setMESG_TYPE_CD(mapper.getMailObject().getValue().getTypeCd().getValue());
						fpomainins.setTYPE_CD(mapper.getMailObject().getValue().getTypeCd().getValue());
						java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						System.out.println("Current Date:" + currentDate);
						cdate = currentDate;
						fpomainins.setSqlDate(currentDate);
						// Date date = new Date();
						// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						// String strDate = formatter.format(date);
						// fpomainins.setJOB_DT(strDate);
						// jobno=fpomainins.getJOB_NO().longValue();
						// System.out.println(jobno);
						int countOfItem = fpomaininsRepo.getCountByItemId(fpomainins.getITEM_ID(),
								fpomainins.getPOSTINGDT());
						if (countOfItem == 0) {
							fpomainins = fpomaininsservice.updateFpomaininsEntity(fpomainins);
							// }
							System.out.println("inserted in c_cusitm");
							errstr = "";
							fou = 0;
							if ((fpomainins.getITEM_ID().length()) < 13) {
								errstr = "INVALID ITEM ID...LENGTH < 13...";
								fou = 1;
							}
							if ((fpomainins.getPOSTINGDT().length()) != 19) {
								errstr = "INVALID POSTING DATE...LENGTH < 19...";
								fou = 1;
							}
							if ((fpomainins.getGROSS_WT().compareTo(BigDecimal.ZERO)) == 0) {
								errstr = "INVALID GROSS WEIGHT...EQUALS 0...";
								fou = 1;
							}
							if (fpomainins.getPOSTAGE_AMT() == null) {
								errstr = "INVALID POSTAGE AMOUNT...NULL VALUE...";
								fou = 1;
							} else if ((fpomainins.getPOSTAGE_AMT().compareTo(BigDecimal.ZERO)) == 0) {
								errstr = "INVALID POSTAGE AMOUNT...EQUALS 0...";
								fou = 1;
							}
							if (fpomainins.getRECP_ADD1() == null) {
								errstr = "INVALID RECPADD1...NULL VALUE...";
								fou = 1;
							} else if (fpomainins.getRECP_ADD1().length() < 5) {
								errstr = errstr + " " + "INVALID RECEPIENT ADDRESS...LENGTH < 5...";
								fou = 1;
							}
							// if (fpomainins.getRECP_CITY().length() < 5)
							// { errstr=errstr+" "+"INVALID RECEPIENT CITY...LENGTH < 5..."; fou=1; }
							if (fpomainins.getRECP_NAME().length() < 5) {
								errstr = errstr + " " + "INVALID RECEPIENT NAME...LENGTH < 5...";
								fou = 1;
							}
							if (fpomainins.getRECP_ZIP().length() < 5) {
								errstr = errstr + " " + "INVALID RECEPIENT ZIP...LENGTH < 5...";
								fou = 1;
							}
							// if (fpomainins.getRECP_PHONE().length() < 5)
							// { System.out.println("INVALID RECEPIENT PHONE...LENGTH < 5..."); fou=1; }
							if (fpomainins.getRECP_CNTRY_CD().length() != 2) {
								errstr = errstr + " " + "INVALID RECIPIENT COUNTRY CODE...LENGTH <> 2...";
								fou = 1;
							}
							if (fpomainins.getRECP_CNTRY_CD() != null)
								if (fposubmitRepoImpl.insertWithQuery(fpomainins.getRECP_CNTRY_CD(), "CNTRY_NM",
										"OPS$DIR.D_CNTRY_CD", "CNTRY_CD") == null) {
									// "DIR.D_CNTRY_CD@icesdev_imports", "CNTRY_CD") == null) {
									errstr = errstr + " " + "INVALID RECIPIENT COUNTRY CODE...";
									fou = 1;
								}
							if (fpomainins.getHANDLING_CLASS_CD() != null)
								if (fposubmitRepoImpl.insertWithQuery(fpomainins.getHANDLING_CLASS_CD(),
										"INTERPRETATION", "OPS$DIR.PDI_HANDLING_CLASS_CODES", "CODE") == null) {
									errstr = errstr + " " + "INVALID HANDLING CLASS CODE...";
									fou = 1;
								}
							if (fpomainins.getINS_VAL_CURRCD() != null)
								if (fposubmitRepoImpl.currencyQuery(fpomainins.getINS_VAL_CURRCD()) == null) {
									System.out.println("null inscurcd1");
									errstr = errstr + " " + "INVALID INSURANCE CURRENCY CODE...";
									fou = 1;
								}

							System.out.println("chkpostcurcd");
							if (fpomainins.getPOSTAGE_CURR_CD() != null)
								if (fposubmitRepoImpl.currencyQuery(fpomainins.getPOSTAGE_CURR_CD()) == null) {
									errstr = errstr + " " + "INVALID POSTAGE CURRENCY CODE...";
									fou = 1;
								}

							if (fpomainins.getORIGPOST_ORGCD() != null)
								// if (fposubmitRepoImpl.insertWithQuery(fpomainins.getORIGPOST_ORGCD(),
								// "POST_ORG_NAME",
								if (fposubmitRepoImpl.insertWithQuery(fpomainins.getORIGPOST_ORGCD(), "IMPC_CODE_SHORT",
										"OPS$DIR.PDI_OOE_CODES", "IMPC_CODE_SHORT") == null) {
									System.out.println("null orgcd");
									errstr = errstr + " " + "INVALID ORIGINAL POST ORG CODE...";
									fou = 1;
								}
							System.out.println("not null orgcd: " + fou);

							if (fpomainins.getDESTPOST_ORG_CD() != null)
								// if (fposubmitRepoImpl.insertWithQuery(fpomainins.getDESTPOST_ORG_CD(),
								// "POST_ORG_NAME",
								if (fposubmitRepoImpl.insertWithQuery(fpomainins.getDESTPOST_ORG_CD(),
										"IMPC_CODE_SHORT", "OPS$DIR.PDI_OOE_CODES", "IMPC_CODE_SHORT") == null) {
									errstr = errstr + " " + "INVALID DESTINATION POST ORG CODE...";
									fou = 1;
								}
							System.out.println("not null destorgcd: " + fou);

							if (fou == 1) {
								errinsert errins = new errinsert();
								errins.setITEM_ID(mapper.getMailObject().getValue().getId().getValue());
								errins.setPOSTINGDT(mapper.getMailObject().getValue().getPostingDt().toString());
								errins.setJOB_NO(fpomainins.getJOB_NO());
								errins.setSqlDate(fpomainins.getSqlDate());
								errins.setUNIQUE_ID(mapper.getMailObject().getValue().getPId().getValue());
								errins.setERR(errstr);
								errins = errService.updateFpomaininsEntity(errins);
								System.out.println("inserted in fpo_cusitm_err");
							}
							// getFpoiteminsList(mapper, fpomainins);
							cintmp = mapper.getMailObject().getValue().getPostingDt().toString();
							fpoSeqNo = fposubmitRepoImpl.getFpoSeq()+1;
							uniqId = cintmp.substring(2, 4) + "MCPI" + cintmp.substring(5, 7) + cintmp.substring(8, 10);
							Fposubmit fposubmit = new Fposubmit();

							fposubmit.setJOB_NO(fpomainins.getJOB_NO());
							// fpomaininsRepo.getOne();
							fposubmit.setSqlDate(fpomainins.getSqlDate());
							fposubmit.setCindt(fpomainins.getSqlDate());
							fposubmit.setITEM_ID(mapper.getMailObject().getValue().getId().getValue());
							fposubmit.setCUST_ORG_CD(
									mapper.getDeclaration().getValue().getCustOrganizationCd().getValue());
							fposubmit.setMAIL_PID(mapper.getDeclaration().getValue().getMailObjectPId());
							fposubmit.setDECLARATION_PID(mapper.getDeclaration().getValue().getPId().getValue());
							fposubmit.setPOST_ORG_CD(
									mapper.getDeclaration().getValue().getPostOrganizationCd().getValue());
							// fposubmit.setDOCUMENTS(mapper.getDeclaration().getValue().getData().getValue().getDocuments().getValue());
							fposubmit.setGROSS_WT(mapper.getDeclaration().getValue().getData().getValue()
									.getGrossWeight().getValue());
							fposubmit.setHANDLING_CLASS_CD(mapper.getDeclaration().getValue().getData().getValue()
									.getHandlingClassCd().getValue());
							fposubmit.setINS_VAL(mapper.getDeclaration().getValue().getData().getValue()
									.getInsuredValue().getValue());
							// fposubmit.setINS_VAL_CURRCD(mapper.getDeclaration().getValue().getData().getValue().getInsuredValueCurrencyCd().getValue());
							fposubmit.setNATURE_TYPE_CD(mapper.getDeclaration().getValue().getData().getValue()
									.getNatureTypeCd().getValue());
							fposubmit.setPOSTAGE_AMT(
									mapper.getDeclaration().getValue().getData().getValue().getPostage().getValue());
							fposubmit.setPOSTAGE_CURR_CD(mapper.getDeclaration().getValue().getData().getValue()
									.getPostageCurrencyCd().getValue());
							fposubmit.setRECP_ADD1(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientAddressLine1().getValue());
							fposubmit.setRECP_ADD2(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientAddressLine2().getValue());
							fposubmit.setRECP_CITY(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientCity().getValue());
							fposubmit.setRECP_CNTRY_CD(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientCountryCd().getValue());
							fposubmit.setRECP_EMAILID(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientEmail().getValue());
							fposubmit.setRECP_FAX(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientFax().getValue());
							fposubmit.setRECP_FNAME(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientFirstName().getValue());
							fposubmit.setRECP_IDREF(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientIdRef().getValue());
							fposubmit.setRECP_LNAME(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientLastName().getValue());
							fposubmit.setRECP_NAME(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientName().getValue());
							fposubmit.setRECP_STATE(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientState().getValue());
							fposubmit.setRECP_PHONE(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientTelephone().getValue());
							fposubmit.setRECP_ZIP(mapper.getDeclaration().getValue().getData().getValue()
									.getRecipientZIP().getValue());
							fposubmit.setSEND_ADD1(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderAddressLine1().getValue());
							fposubmit.setSEND_ADD2(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderAddressLine2().getValue());
							fposubmit.setSEND_CITY(
									mapper.getDeclaration().getValue().getData().getValue().getSenderCity().getValue());
							fposubmit.setSEND_CNTRY_CD(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderCountryCd().getValue());
							fposubmit.setSEND_EMAILID(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderEmail().getValue());
							fposubmit.setSEND_FNAME(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderFirstName().getValue());
							fposubmit.setSEND_IDREF(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderIdRef().getValue());
							fposubmit.setSEND_LNAME(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderLastName().getValue());
							fposubmit.setSEND_NAME(
									mapper.getDeclaration().getValue().getData().getValue().getSenderName().getValue());
							fposubmit.setSEND_STATE(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderState().getValue());
							fposubmit.setSEND_PHONE(mapper.getDeclaration().getValue().getData().getValue()
									.getSenderTelephone().getValue());
							fposubmit.setSEND_ZIP(
									mapper.getDeclaration().getValue().getData().getValue().getSenderZIP().getValue());
							fposubmit.setTRANS_MODE(mapper.getDeclaration().getValue().getData().getValue()
									.getTransportMode().getValue());
							// fposubmit.setTRANS_DATE(mapper.getDeclaration().getValue().getData().getValue().getTransportDate().getValue().toString());
							fposubmit.setDESTPOST_ORG_CD(
									mapper.getMailObject().getValue().getDestPostOrgCd().getValue());
							fposubmit.setITEM_ID(mapper.getMailObject().getValue().getId().getValue());
							fposubmit.setLOCALID(mapper.getMailObject().getValue().getLocalId().getValue());
							fposubmit.setLOCALID2(mapper.getMailObject().getValue().getLocalId2().getValue());
							fposubmit.setMAIL_CATEGORY_CD(
									mapper.getMailObject().getValue().getMailCategoryCd().getValue());
							fposubmit.setMAIL_CLASS_CD(mapper.getMailObject().getValue().getMailClassCd().getValue());
							// fposubmit.setMAIL_STATE_CD(mapper.getMailObject().getValue().getMailStateCd().getValue().toString());
							fposubmit.setMAIL_STATE_REMARKS(
									mapper.getMailObject().getValue().getMailStateRemarks().getValue());
							fposubmit.setORIGPOST_ORG_CD(
									mapper.getMailObject().getValue().getOrigPostOrgCd().getValue());
							fposubmit.setUNIQUE_ID(mapper.getMailObject().getValue().getPId().getValue());
							fposubmit.setPOSTINGDT(mapper.getMailObject().getValue().getPostingDt().toString());
							fposubmit.setMESG_TYPE_CD(mapper.getMailObject().getValue().getTypeCd().getValue());
							fposubmit.setTYPE_CD(mapper.getMailObject().getValue().getTypeCd().getValue());
							int i=0;
							do
							{
							cinSeqNo = cininfoREPO.getcinseq().toString();	
							cinno = getCinNor();
							if (fpomaininsRepo.chkcinfou(cinno)>0)
								{System.out.println("Cinno already exists...");
								 
								}
							else
								{
								    i=1;
								    CIN_INFO cininfo = new CIN_INFO();
								    cininfo.setCinNO(cinno);
								    cininfo.setCinDt(fposubmit.getCindt());
								    cininfo.setItemID(fposubmit.getITEM_ID());
								    cininfo.setPostingDT(fposubmit.getPOSTINGDT());
								    cininfoREPO.save(cininfo);
								}
							}
							while (i==0);
							fposubmit.setCIN_NO(cinno);
							pincode = mapper.getDeclaration().getValue().getData().getValue().getRecipientZIP()
									.getValue().trim();
							System.out.println("pincode=" + pincode.length());
							if (pincode == ".") {
								System.out.println("pincode=" + pincode.length());
								pincode = "";
							}
							System.out.println("pincode=" + pincode);
//						if (pincode.length() > 4) {
//						System.out.println("pincode=" + pincode + ",pincode length=" + pincode.length()+"is space"+pincode.substring(3, 4)+"Y");
//						}
							pincodefou = 0;
							cityfou = 0;
							statefou = 0;
							fpocode = "";
							
							if (pincode != null) {
								pincode = pincode.replaceAll("\\s+", "");
								pincode = pincode.trim();
								if (pincode.length() == 7 && pincode.substring(3, 4) == " ") {

									System.out.println("cms here in space between pincodes" + pincode);
									pincode = pincode.substring(0, 3) + pincode.substring(4, 7);
									pincode = pincode.replaceAll("\\s+", "");
									pincode = pincode.trim();

								}
								System.out.println("isdigit outcome is " + IsDigit(pincode));
								if (IsDigit(pincode))
									if (pincode.length() == 6) {
										pincode = pincode.substring(0, 6);
										System.out.println("pincodes=" + pincode);
										fpocode = fposubmitRepoImpl.getfponame(pincode, "PINCODE");
										System.out.println("fpocode=" + fpocode);
										if (fpocode != null) {
											fposubmit.setPINCODE(pincode);
											fposubmit.setFPO_CODE(fpocode);
											String Cussite=fposubmitRepoImpl.getfposite(fpocode);
											
											
											if(Cussite.equalsIgnoreCase("INBOM5")) {
												if(bombcount==1) {
													bombcount++;
												}else {
													bombcount = 1;
													Cussite="INBPS5";
												}
												
											}else if(Cussite.equalsIgnoreCase("INBPS5")) {
												if(bombcount==2) {
													bombcount = 1;
												}else {
													bombcount++;
													Cussite="INBOM5";
												}
												
											}
											if(Cussite.equalsIgnoreCase("INMAA5")) {
												if(chncount==1) {
													chncount++;
												}else {
													chncount = 1;
													Cussite="INFCH5";
												}
											}else if(Cussite.equalsIgnoreCase("INFCH5")) {
												if(chncount==2) {
													chncount = 1;
												}else {
													chncount++;
													Cussite="INMAA5";
												}
												
											}
											fposubmit.setCUS_SITE(Cussite);
											//fposubmit.setCLR_SITE(Cussite);
											pincodefou = 1;
										} else {
											fpocode = fposubmitRepoImpl.getfponamemapping(pincode);
											if (fpocode != null) {
												fposubmit.setPINCODE(pincode);
												fposubmit.setFPO_CODE(fpocode);
												String Cussite=fposubmitRepoImpl.getfposite(fpocode);
												
												
												if(Cussite.equalsIgnoreCase("INBOM5")) {
													if(bombcount==1) {
														bombcount++;
													}else {
														bombcount = 1;
														Cussite="INBPS5";
													}
													
												}else if(Cussite.equalsIgnoreCase("INBPS5")) {
													if(bombcount==2) {
														bombcount = 1;
													}else {
														bombcount++;
														Cussite="INBOM5";
													}
													
												}
												if(Cussite.equalsIgnoreCase("INMAA5")) {
													if(chncount==1) {
														chncount++;
													}else {
														chncount = 1;
														Cussite="INFCH5";
													}
												}else if(Cussite.equalsIgnoreCase("INFCH5")) {
													if(chncount==2) {
														chncount = 1;
													}else {
														chncount++;
														Cussite="INMAA5";
													}
													
												}
												fposubmit.setCUS_SITE(Cussite);
												//fposubmit.setCLR_SITE(Cussite);
												pincodefou = 1;
											}
										}
									}
							}
							if (pincodefou == 0) {
								citynm = mapper.getDeclaration().getValue().getData().getValue().getRecipientCity()
										.getValue();
								if (citynm != null) {
									fpocode = fposubmitRepoImpl.getfponame(citynm, "DISTRICT");
									if (fpocode != null) {
										fposubmit.setFPO_CODE(fpocode);
										String Cussite=fposubmitRepoImpl.getfposite(fpocode);
										
										
										if(Cussite.equalsIgnoreCase("INBOM5")) {
											if(bombcount==1) {
												bombcount++;
											}else {
												bombcount = 1;
												Cussite="INBPS5";
											}
											
										}else if(Cussite.equalsIgnoreCase("INBPS5")) {
											if(bombcount==2) {
												bombcount = 1;
											}else {
												bombcount++;
												Cussite="INBOM5";
											}
											
										}
										if(Cussite.equalsIgnoreCase("INMAA5")) {
											if(chncount==1) {
												chncount++;
											}else {
												chncount = 1;
												Cussite="INFCH5";
											}
										}else if(Cussite.equalsIgnoreCase("INFCH5")) {
											if(chncount==2) {
												chncount = 1;
											}else {
												chncount++;
												Cussite="INMAA5";
											}
											
										}
										fposubmit.setCUS_SITE(Cussite);
									//	fposubmit.setCLR_SITE(Cussite);
										cityfou = 1;
									}
								}
							}
							if (pincodefou == 0 && cityfou == 0) {
								statenm = mapper.getDeclaration().getValue().getData().getValue().getRecipientState()
										.getValue();
								if (statenm != null) {
									fpocode = fposubmitRepoImpl.getfponame(statenm, "STATE_NAME");
									if (fpocode != null) {
										fposubmit.setFPO_CODE(fpocode);
										String Cussite=fposubmitRepoImpl.getfposite(fpocode);
										
										
										if(Cussite.equalsIgnoreCase("INBOM5")) {
											if(bombcount==1) {
												bombcount++;
											}else {
												bombcount = 1;
												Cussite="INBPS5";
											}
											
										}else if(Cussite.equalsIgnoreCase("INBPS5")) {
											if(bombcount==2) {
												bombcount = 1;
											}else {
												bombcount++;
												Cussite="INBOM5";
											}
											
										}
										if(Cussite.equalsIgnoreCase("INMAA5")) {
											if(chncount==1) {
												chncount++;
											}else {
												chncount = 1;
												Cussite="INFCH5";
											}
										}else if(Cussite.equalsIgnoreCase("INFCH5")) {
											if(chncount==2) {
												chncount = 1;
											}else {
												chncount++;
												Cussite="INMAA5";
											}
											
										}
										fposubmit.setCUS_SITE(Cussite);
									//	fposubmit.setCLR_SITE(Cussite);
										statefou = 1;
									}
								}
							}
							if (pincodefou == 0 && cityfou == 0 & statefou == 0) {
								citynm = mapper.getDeclaration().getValue().getData().getValue().getRecipientCity()
										.getValue();
								statenm = mapper.getDeclaration().getValue().getData().getValue().getRecipientState()
										.getValue();
								System.out.println("citynm=" + citynm);
								if (citynm != null && statenm != null) {
									System.out.println("cms here2");
									fpocode = fposubmitRepoImpl.getfpolikename(citynm, statenm);
									fposubmit.setFPO_CODE(fpocode);
									System.out.println("fpocode=" + fpocode);
									if (fpocode != null) {

										String Cussite=fposubmitRepoImpl.getfposite(fpocode);
										
										
										if(Cussite.equalsIgnoreCase("INBOM5")) {
											if(bombcount==1) {
												bombcount++;
											}else {
												bombcount = 1;
												Cussite="INBPS5";
											}
											
										}else if(Cussite.equalsIgnoreCase("INBPS5")) {
											if(bombcount==2) {
												bombcount = 1;
											}else {
												bombcount++;
												Cussite="INBOM5";
											}
											
										}
										if(Cussite.equalsIgnoreCase("INMAA5")) {
											if(chncount==1) {
												chncount++;
											}else {
												chncount = 1;
												Cussite="INFCH5";
											}
										}else if(Cussite.equalsIgnoreCase("INFCH5")) {
											if(chncount==2) {
												chncount = 1;
											}else {
												chncount++;
												Cussite="INMAA5";
											}
											
										}
											fposubmit.setCUS_SITE(Cussite);
										//	fposubmit.setCLR_SITE(Cussite);
									}
								} else if (citynm != null & statenm == null) {
									System.out.println("cms here");
									fpocode = fposubmitRepoImpl.getfpolikecitystname(citynm);
									fposubmit.setFPO_CODE(fpocode);
									if (fpocode != null) {

										String Cussite=fposubmitRepoImpl.getfposite(fpocode);
										
										
										if(Cussite.equalsIgnoreCase("INBOM5")) {
											if(bombcount==1) {
												bombcount++;
											}else {
												bombcount = 1;
												Cussite="INBPS5";
											}
											
										}else if(Cussite.equalsIgnoreCase("INBPS5")) {
											if(bombcount==2) {
												bombcount = 1;
											}else {
												bombcount++;
												Cussite="INBOM5";
											}
											
										}
										if(Cussite.equalsIgnoreCase("INMAA5")) {
											if(chncount==1) {
												chncount++;
											}else {
												chncount = 1;
												Cussite="INFCH5";
											}
										}else if(Cussite.equalsIgnoreCase("INFCH5")) {
											if(chncount==2) {
												chncount = 1;
											}else {
												chncount++;
												Cussite="INMAA5";
											}
											
										}
										fposubmit.setCUS_SITE(Cussite);
									//	fposubmit.setCLR_SITE(Cussite);
									}
										
								} else if (statenm != null & citynm == null) {
									fpocode = fposubmitRepoImpl.getfpolikecitystname(statenm);
									fposubmit.setFPO_CODE(fpocode);
									if (fpocode != null) {


										String Cussite=fposubmitRepoImpl.getfposite(fpocode);
										
										
										if(Cussite.equalsIgnoreCase("INBOM5")) {
											if(bombcount==1) {
												bombcount++;
											}else {
												bombcount = 1;
												Cussite="INBPS5";
											}
											
										}else if(Cussite.equalsIgnoreCase("INBPS5")) {
											if(bombcount==2) {
												bombcount = 1;
											}else {
												bombcount++;
												Cussite="INBOM5";
											}
											
										}
										if(Cussite.equalsIgnoreCase("INMAA5")) {
											if(chncount==1) {
												chncount++;
											}else {
												chncount = 1;
												Cussite="INFCH5";
											}
										}else if(Cussite.equalsIgnoreCase("INFCH5")) {
											if(chncount==2) {
												chncount = 1;
											}else {
												chncount++;
												Cussite="INMAA5";
											}
											
										}
										fposubmit.setCUS_SITE(Cussite);
									//	fposubmit.setCLR_SITE(Cussite);
									}
										
								}
							}
							totdeclval = new BigDecimal(0.00);
							totassval = new BigDecimal(0.00);
							float totcalcassval, inscalcval, frtcalcval;
							float inscurrt, frtcurrrt;
							float insval, frtval;
							totduty = new BigDecimal(0.00);
							totdutyfg = 0.0f;
							// int countOfItem=fpomaininsRepo.getCountByItemId(fpomainins.getITEM_ID(),
							// fpomainins.getPOSTINGDT());
							// if(countOfItem==0) {
							// fpomainins = fpomaininsservice.updateFpomaininsEntity(fpomainins);
							// }
							// System.out.println("inserted in c_cusitm");
							// inscusrsp(mapper, fposubmit);
							getFpoiteminsList(mapper, fpomainins, fposubmit);
							System.out.println("inserted in fpo_items");
							// insdocList(mapper, fpomainins, fposubmit);

							System.out.println("inserted in fpo_items and doc");
							totcalcassval = totassval.floatValue();
							if (fposubmit.getINS_VAL_CURRCD() == null || fposubmit.getINS_VAL() == null) {
								inscalcval = 0;
							} else if (FposubmititemRepoImpl.getcurrt(fposubmit.getINS_VAL_CURRCD()) != null) {
								inscurrt = Float
										.parseFloat(FposubmititemRepoImpl.getcurrt(fposubmit.getINS_VAL_CURRCD()));
								fposubmit.setINS_currrt(inscurrt);
								inscalcval = fposubmit.getINS_VAL().floatValue() * inscurrt;
							} else {
								inscurrt = 0;
								inscalcval = 0;
							}
							System.out.println("INScalcval :" + inscalcval);
							if (fposubmit.getPOSTAGE_CURR_CD() == null || fposubmit.getPOSTAGE_AMT() == null)
								frtcalcval = 0;
							else if (FposubmititemRepoImpl.getcurrt(fposubmit.getPOSTAGE_CURR_CD()) != null) {
								frtcurrrt = Float
										.parseFloat(FposubmititemRepoImpl.getcurrt(fposubmit.getPOSTAGE_CURR_CD()));
								fposubmit.setFRT_currrt(frtcurrrt);
								frtcalcval = fposubmit.getPOSTAGE_AMT().floatValue() * frtcurrrt;
							} else {
								frtcurrrt = 0;
								frtcalcval = 0;
							}
							System.out.println("FRTcalcval :" + frtcalcval);

							if (inscalcval == 0 || inscalcval > (float) (totcalcassval * 0.01125)) {
								insval = (float) (totcalcassval * 0.01125);
								System.out.println("INSval :" + insval);
								inscalcval = insval;
								ic1 = new BigDecimal(Float.toString(insval));
								ic1 = ic1.setScale(2, BigDecimal.ROUND_FLOOR);
								// fposubmit.setINS_calc_val(parseFloat(totcalcassval * 0.01125));
								// fposubmit.setINS_calc_val(insval);
								fposubmit.setINS_calc_val(ic1.floatValue());
								System.out.println("INSval :" + insval);
								System.out.println("INSvaldeci :" + ic1);
							} else {
								fposubmit.setINS_calc_val(inscalcval);
								System.out.println("INScalcval :" + inscalcval);
							}

							if (frtcalcval == 0 || frtcalcval > (float) (totcalcassval * 0.20)) {
								frtval = (float) (totcalcassval * 0.20);
								System.out.println("FRTval :" + frtval);
								frtcalcval = frtval;
								// frtcalcval=format("%.2f",frtcalcval);
								BigDecimal frt1 = new BigDecimal(Float.toString(frtcalcval));
								frt1 = frt1.setScale(2, BigDecimal.ROUND_FLOOR);
								float frt2;
								frt2 = frt1.floatValue();

								fposubmit.setFRT_calc_val(frt2);
								// fposubmit.setFRT_calc_val(parseFloat(totcalcassval * 0.0020));}
							} else {
								fposubmit.setFRT_calc_val(frtcalcval);
							}
							totassval = new BigDecimal(totcalcassval + inscalcval + frtcalcval);
							BigDecimal totcalcassval1 = new BigDecimal(Float.toString(totcalcassval));
							totcalcassval1 = totcalcassval1.setScale(2, BigDecimal.ROUND_FLOOR);
							float totcalcassval2;
							totcalcassval2 = totcalcassval1.floatValue();
							fposubmit.setTotass_calc_val(totcalcassval2);
							fposubmit.setTOT_DECL_VAL(totdeclval);
							fposubmit.setTOT_ASS_VAL(totassval);

							List<fposubmititem> fpoitemlist = FposubmititemRepo2.getAllData(cinno);

							System.out.println(fpoitemlist);
							totduty = new BigDecimal(0.00);
							totdutyfg = 0.0f;
							fpoitemlist.stream().forEach(fpoitemlst -> {
								float assvalinsfrt1, bcd, sws, igst;
								String bcdrtitm;
								String igstrtitm;
								String swsrtitm;
								Long itemuniq;
								itemuniq = fpoitemlst.getITEM_UNIQUE();
								cinno = fpoitemlst.getCIN_NO();
								itemno = fpoitemlst.getITEM_NO();
								assval = fpoitemlst.getASSESS_VAL();
								bcdduty = fpoitemlst.getBCD_AMT();
								swsduty = fpoitemlst.getSWS_AMT();
								igstduty = fpoitemlst.getIGST_AMT();
								if (fpoitemlst.getBCD_AMT_FG() == null)
									bcddutyfg=0.0f;
								else
									bcddutyfg = fpoitemlst.getBCD_AMT_FG().floatValue();
								if (fpoitemlst.getIGST_AMT_FG() == null)
									igstdutyfg=0.0f;
								else
								    igstdutyfg = fpoitemlst.getIGST_AMT_FG().floatValue();
								if (fpoitemlst.getSW_AMT_FG() == null)
									swsdutyfg=0.0f;
								else
								    swsdutyfg = fpoitemlst.getSW_AMT_FG().floatValue();
								tcalassval = new BigDecimal(totcalcassval);
								bcdrtitm = fpoitemlst.getBCD_RTA();
								igstrtitm = fpoitemlst.getIGST_RTA();
								swsrtitm = fpoitemlst.getSWS_RTA();
								// BigDecimal hundred = new BigDecimal("100");
								System.out.println(cinno + " " + itemno + " " + assval + " " + tcalassval + " ");
							    System.out.println(bcdrtitm + " " + igstrtitm + " " + swsrtitm);
								assvalinsfrt = BigDecimal.ZERO;
								dutyitm = BigDecimal.ZERO;
								dutyitmfg = bcddutyfg+igstdutyfg+swsdutyfg;
								if (assval.intValue() > 0) {

									assvalinsfrt1 = (assval.floatValue())
											* ((totassval.floatValue()) / (tcalassval.floatValue()));

									bcd = (assval.floatValue()) * ((totassval.floatValue()) / (tcalassval.floatValue()))
											* Float.parseFloat(bcdrtitm) / 100;

									sws = bcd * Float.parseFloat(swsrtitm) / 100;

									igst = ((assval.floatValue())
											* ((totassval.floatValue()) / (tcalassval.floatValue())) + bcd + sws)
											* Float.parseFloat(igstrtitm) / 100;

									assvalinsfrt = new BigDecimal(Float.toString(assvalinsfrt1));
									bcdduty = new BigDecimal(Float.toString(bcd));
									swsduty = new BigDecimal(Float.toString(sws));
									igstduty = new BigDecimal(Float.toString(igst));
									dutyitm = bcdduty.add(swsduty).add(igstduty);
									
									System.out.println(assvalinsfrt + " " + bcdduty + " " + swsduty + " " + igstduty
											+ " " + totduty);

								}

								fpoSubmititemService.findById(itemuniq, assvalinsfrt, bcdduty, swsduty, igstduty,
										dutyitm);
//							
								totduty = totduty.add(dutyitm);
								totdutyfg = totdutyfg+dutyitmfg;
								System.out.println(
										assvalinsfrt + " " + bcdduty + " " + swsduty + " " + igstduty + " " + totduty);

							});

							fposubmit.setTOT_DUTY(totduty);
							fposubmit.setTOT_DUTY_FG(totdutyfg);
							// if (totduty.compareTo(new BigDecimal(1000.00)) == -1)
							float f2 = cdsController.dutyCalc.getAssval_Amt();
							float f1 = totassval.floatValue();
							if (cdsController.dutyCalc.getCategory().equals(fposubmit.getNATURE_TYPE_CD().trim()))
								fposubmit.setTOT_AMT_PAYABLE(totduty.floatValue());
							else if (Float.compare(f1, f2) > 0) {
								System.out.println("coms in greater");
								fposubmit.setTOT_AMT_PAYABLE(totduty.floatValue());
							} else {
								System.out.println("coms in lesser");
								fposubmit.setTOT_AMT_PAYABLE(0f);
							}

							fposubmit.setInit_que("E1");
							fposubmit.setUPD_CIF("Y");
							// else
							// fposubmit.setInit_que("P1");
							// getFpoiteminsList(mapper, fpomainins, fposubmit);
							insfpostatus(fposubmit);
							inscusrsp(mapper, fposubmit);
							System.out.println("inserted in fpostatus and cusrsp");
							fposubmit = fposubmitService.updateFpomaininsEntity(fposubmit);
							System.out.println("inserted in fpo_main");

							System.out.println("fpo_main cinno" + fposubmit.getCIN_NO() + " tot_ass_val "
									+ fposubmit.getTOT_ASS_VAL());

						}
						}
						}
					} catch (Exception e) {
						if (null == e.getMessage())
							System.out.println("Null Pointer Exception" + e.getMessage());

						System.out.println("Exception " + e.getMessage());
						Cmisseditem misseditem = new Cmisseditem();
						misseditem.setITEM_ID(itmid);
						misseditem.setPOSTINGDT(postingdt);
						misseditem.setCrDate((java.sql.Date) cdate);
						misseditem = misseditemService.saveEntity(misseditem);
					}

				});
		// getfpoqrydata() ;
		// System.out.println("getqrydeci loaded");
		return response;

	}

	public void inscusrsp(CDSView cdsMapper, Fposubmit fposubmit) {

		reascd = 1;
		decicd = 99;
		
		String itemid = fposubmit.getITEM_ID();
		String cusite = fposubmit.getCUS_SITE();
		
		String arrfpo="";
		
	    if (loadFpoRepo.getcoufpopreead(itemid, cusite) > 0)
	    	fposubmit.setARR_FPO("Y");
	    else if (loadFpoRepo.getcouooepreead(itemid, cusite) > 0)
	    	fposubmit.setARR_FPO("Y");
		if (fposubmit.getCUS_SITE() != null && !fposubmit.getCUS_SITE().equals("")) {
			reasnm = "SUBMISSION SUCCESSFUL ALLOTTED TO ".concat(fposubmit.getCUS_SITE());
			usercd = "System";
			if (fposubmit.getARR_FPO()!=null && fposubmit.getARR_FPO().equals("Y"))
				{reasnm="SUBMISSION SUCCESSFUL / ALLOTTED TO FPOSITE " + fposubmit.getCUS_SITE() + " / EAD RCVD AFTER ART ARR MESG";
				 decicd = 110;
				}
			else
			   decicd = 99;
		} else {
			reasnm = "SUBMISSION NOT SUCCESSFUL AS PINCODE IS NOT MAPPED TO ANY FPO SITE";
			if (fposubmit.getARR_FPO()!=null && fposubmit.getARR_FPO().equals("Y"))
			{reasnm="SUBMISSION NOT SUCCESSFUL AS PINCODE IS NOT MAPPED TO ANY FPOSITE / EAD RCVD AFTER ART ARR MESG";
			 decicd = 111;
			}
			else
			   decicd = 105;
			usercd = "System";
		}

		cusrsp cusrsp = new cusrsp();
		cusrsp.setCIN_NO(fposubmit.getCIN_NO());
		clrdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		cusrsp.setITEM_ID(fposubmit.getITEM_ID());
		cusrsp.setCUS_SITE(fposubmit.getCUS_SITE());
		cusrsp.setMESG_TYPE_CD(fposubmit.getMESG_TYPE_CD());
		cusrsp.setCUST_ORG_CD(fposubmit.getCUST_ORG_CD());
		cusrsp.setCLEARANCE_DT(clrdt);
		cusrsp.setDESTPOST_ORGCD(fposubmit.getDESTPOST_ORG_CD());
		cusrsp.setORIGPOST_ORGCD(fposubmit.getORIGPOST_ORG_CD());
		cusrsp.setPOSTINGDT(fposubmit.getPOSTINGDT());
		cusrsp.setDECI_CD(decicd);
		cusrsp.setDECI_REAS_CD(reascd);
		cusrsp.setDECI_REAS_NM(reasnm);
		cusrsp.setMAIL_CLASS_CD(fposubmit.getMAIL_CLASS_CD());
		cusrsp.setENTITY_STATE(1l);
		cusrsp.setMAIL_PID(fposubmit.getMAIL_PID());
		cusrsp.setMAIL_STATE_CD(fposubmit.getMAIL_STATE_CD());
		cusrsp.setNATURE_TYPE_CD(fposubmit.getNATURE_TYPE_CD());
		cusrsp.setSqlDate((java.sql.Date) cdate);
		// cusrsp.setTOT_DUTY(totduty);
		totdutypay1 = new BigDecimal(0.00);
		totdutypay1 = BigDecimal.valueOf(fposubmit.getTOT_AMT_PAYABLE());
		cusrsp.setTOT_DUTY(totdutypay1);
		cusrsp.setCUS_SITE(fposubmit.getCUS_SITE());
		System.out.println("cinno in cusrsp=" + fposubmit.getCIN_NO());
		cusrsp = fpocusrspService.updateFpomaininsEntity(cusrsp);
	}

	public void insfpostatus(Fposubmit fposubmit) {
		FpoStatus fpostatus = new FpoStatus();
		fpostatus.setCinNo(fposubmit.getCIN_NO());
		fpostatus.setCinDt(fposubmit.getCindt());
		System.out.println("cinno in cusrsp=" + fposubmit.getCIN_NO());
		clrdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		fpostatus.setItemId(fposubmit.getITEM_ID());
		fpostatus.setCuSite(fposubmit.getCUS_SITE());
		fpostatus.setMsgTypeCd(fposubmit.getMESG_TYPE_CD());
		fpostatus.setPstDt(fposubmit.getPOSTINGDT());
		fpostatus.setCusitmdt(fposubmit.getSqlDate());
		fpostatus = fpostatusService.updateFpostatinsEntity(fpostatus);
	}

//public void  inscusrspfpo(Deciqryfpostatus fpo,Fposubmit fposubmit) {
	public void inscusrspfpo(Fposubmit fposubmit) {
		cusrsp cusrsp = new cusrsp();
		cusrsp.setCIN_NO(fposubmit.getCIN_NO());
		System.out.println("cinno in cusrsp=" + fposubmit.getCIN_NO());
		clrdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		cusrsp.setITEM_ID(fposubmit.getITEM_ID());
		cusrsp.setCUS_SITE(fposubmit.getCUS_SITE());
		cusrsp.setMESG_TYPE_CD(fposubmit.getMESG_TYPE_CD());
		cusrsp.setCUST_ORG_CD(fposubmit.getCUST_ORG_CD());
		cusrsp.setCLEARANCE_DT(clrdt);
		cusrsp.setDESTPOST_ORGCD(fposubmit.getDESTPOST_ORG_CD());
		cusrsp.setORIGPOST_ORGCD(fposubmit.getORIGPOST_ORG_CD());
		cusrsp.setPOSTINGDT(fposubmit.getPOSTINGDT());
		cusrsp.setDECI_CD(decicd);
		cusrsp.setDECI_REAS_CD(reascd);
		cusrsp.setDECI_REAS_NM(reasnm);
		cusrsp.setMAIL_CLASS_CD(fposubmit.getMAIL_CLASS_CD());
		cusrsp.setENTITY_STATE(1l);
		cusrsp.setMAIL_PID(fposubmit.getMAIL_PID());
		cusrsp.setMAIL_STATE_CD(fposubmit.getMAIL_STATE_CD());
		cusrsp.setNATURE_TYPE_CD(fposubmit.getNATURE_TYPE_CD());
		cusrsp.setTOT_DUTY(fposubmit.getTOT_DUTY());
		cusrsp.setCUS_SITE(fposubmit.getCUS_SITE());
		cusrsp.setSqlDate((java.sql.Date) cdate);
		System.out.println("cinno in cusrsp=" + fposubmit.getCIN_NO());
		// System.out.println("qrytyp in cusrsp=" + qrytyp);
		// System.out.println("Assdt in cusrsp=" + assdt);
		cusrsp = fpocusrspService.updateFpomaininsEntity(cusrsp);
	}

	public void inscusrspitem(CDSView cdsMapper, fposubmititem fposubitm) {
		cusrspitemdet cusrspitm = new cusrspitemdet();
		cusrspitm.setCIN_NO(fposubitm.getCIN_NO());
		cusrspitm.setITEM_ID(fposubitm.getITEM_ID());
		cusrspitm.setITEM_NO(fposubitm.getITEM_NO());
		cusrspitm.setPOSTINGDT(fposubitm.getPOSTINGDT());
		cusrspitm.setENTITY_STATE(1);
		cusrspitm.setDECL_VAL(fposubitm.getDECL_VAL());
		// cusrspitm.setCP_CURRCD("INR");
		cusrspitm.setCP_CURRCD(fposubitm.getCURRCD());
		cusrspitm.setCP_DESC(fposubitm.getITEM_DESC());
		cusrspitm.setCP_HS(fposubitm.getCTH());
		cusrspitm.setNET_WT(fposubitm.getNETWT());
		cusrspitm.setNOU(fposubitm.getNOU());
		cusrspitm.setCUS_SITE(fposubitm.getCUS_SITE());
		cusrspitm.setSqlDate((java.sql.Date) cdate);
		System.out.println("In inscusrspitem");
		cusrspitm = cusrspitemdetService.updateFpomaininsEntity(cusrspitm);
		System.out.println("Inserted into  inscusrspitem");
	}

	// public void inscusrspitemfpo(Deciqryfpostatus fpo,fposubmititem fposubitm) {
	public void inscusrspitemfpo(fposubmititem fposubitm) {
		cusrspitemdet cusrspitm = new cusrspitemdet();
		cusrspitm.setCIN_NO(fposubitm.getCIN_NO());
		cusrspitm.setITEM_ID(fposubitm.getITEM_ID());
		cusrspitm.setITEM_NO(fposubitm.getITEM_NO());
		cusrspitm.setPOSTINGDT(fposubitm.getPOSTINGDT());
		cusrspitm.setENTITY_STATE(1);
		cusrspitm.setDECL_VAL(fposubitm.getDECL_VAL());
		// cusrspitm.setCP_CURRCD("INR");
		cusrspitm.setCP_CURRCD(fposubitm.getCURRCD());
		cusrspitm.setCP_DESC(fposubitm.getITEM_DESC());
		cusrspitm.setCP_HS(fposubitm.getCTH());
		cusrspitm.setNET_WT(fposubitm.getNETWT());
		cusrspitm.setNOU(fposubitm.getNOU());
		cusrspitm.setCUS_SITE(fposubitm.getCUS_SITE());
		cusrspitm.setSqlDate((java.sql.Date) cdate);
		System.out.println("cinno in cusrspitemfpo=" + fposubitm.getCIN_NO());
		System.out.println("itemno in cusrspitemfpo=" + fposubitm.getITEM_NO());
		cusrspitm = cusrspitemdetService.updateFpomaininsEntity(cusrspitm);
	}

	public void inscusrspittax(String cinno, String itemid, Long itemno, String currcd, BigDecimal dutyamt, String rt,
			String typ, String cussite) {
		cusrspitemtaxes cusrsptax = new cusrspitemtaxes();
		cusrsptax.setCIN_NO(cinno);
		cusrsptax.setITEM_ID(itemid);
		cusrsptax.setITEM_NO(itemno);
		cusrsptax.setDUTY_AMT(dutyamt);
		// cusrsptax.setCURRCD(currcd);
		cusrsptax.setCURRCD("INR");
		cusrsptax.setTAX_RT(new BigDecimal(rt));
		cusrsptax.setTAX_TYPE(typ);
		cusrsptax.setCUS_SITE(cussite);
		cusrsptax.setSqlDate((java.sql.Date) cdate);
		System.out.println("In cusrspitemtaxService");
		cusrsptax = cusrspitemtaxService.updateFpomaininsEntity(cusrsptax);
		System.out.println("INSERTed in cusrspitm_item_tax");
	}

	public void inscusrspittaxfpo(String cinno, String itemid, Long itemno, String currcd, BigDecimal dutyamt,
			String rt, String typ, String cussite) {
		cusrspitemtaxes cusrsptax = new cusrspitemtaxes();
		cusrsptax.setCIN_NO(cinno);
		cusrsptax.setITEM_ID(itemid);
		cusrsptax.setITEM_NO(itemno);
		cusrsptax.setDUTY_AMT(dutyamt);
		// cusrsptax.setCURRCD(currcd);
		cusrsptax.setCURRCD("INR");
		cusrsptax.setTAX_RT(new BigDecimal(rt));
		cusrsptax.setTAX_TYPE(typ);
		cusrsptax.setCUS_SITE(cussite);
		cusrsptax.setSqlDate((java.sql.Date) cdate);
		cusrsptax = cusrspitemtaxService.updateFpomaininsEntity(cusrsptax);
	}

	public void inscusrspittaxfpooth(String cinno, String itemid, Long itemno, BigDecimal dutyamt, String rt,
			String typ, String cussite) {
		cusrspitemtaxes cusrsptax = new cusrspitemtaxes();
		cusrsptax.setCIN_NO(cinno);
		cusrsptax.setITEM_ID(itemid);
		cusrsptax.setITEM_NO(itemno);
		cusrsptax.setDUTY_AMT(dutyamt);
		// cusrsptax.setCURRCD(currcd);
		cusrsptax.setCURRCD("INR");
		cusrsptax.setTAX_RT(new BigDecimal(rt));
		cusrsptax.setTAX_TYPE(typ);
		cusrsptax.setCUS_SITE(cussite);
		cusrsptax.setSqlDate((java.sql.Date) cdate);
		cusrsptax = cusrspitemtaxService.updateFpomaininsEntity(cusrsptax);
	}

	public final boolean IsDigit(String s) {
		boolean IsDigit = true;

		if (s != null && !s.isEmpty()) {
			for (char c : s.toCharArray()) {
				if (!(Character.isDigit(c))) {
					return false;
				}
			}
		}

		return IsDigit;
	}

	public static String getCinNor() {

		String prefix = uniqId;
		String suffix = "00";
		String beforeSuffix = cinSeqNo;

		if (Integer.parseInt(beforeSuffix) < 99999999 && Integer.parseInt(beforeSuffix) > 0)
			beforeSuffix = String.format("%08d", Integer.parseInt(beforeSuffix) + 2);
		else
			beforeSuffix = String.format("%08d", Integer.parseInt("1"));
		
		String cinno = prefix + suffix + beforeSuffix;

	    
		// generatedSequenceValue = prefix + beforeSuffix + suffix;

		return prefix + beforeSuffix + suffix;

	}

	@Autowired
	fposubmititemRepoImpl FposubmititemRepoImpl;

	private void getFpoiteminsList(CDSView cdsMapper, Fpomainins fpomainins, Fposubmit fposubmit) {

//	    	   			int itemCounter = 1;
		AtomicInteger counter = new AtomicInteger(1);
		List<DeclarationDeclarationDataContentPiece> decContentPiece = cdsMapper.getDeclaration().getValue().getData()
				.getValue().getContentPieces().getValue().getDeclarationDeclarationDataContentPiece();
//						if (decContentPiece.size() > 1) {
//							System.out.println("More than 1");
//						}
		System.out.println("items=" + decContentPiece.size());
		decContentPiece.forEach(mapper -> {
			try {

				Fpoitemins fpoitemins = new Fpoitemins();
				fpoitemins.setJOB_NO(fpomainins.getJOB_NO());
				fpoitemins.setSqlDate(fpomainins.getSqlDate());
				fpoitemins.setCP_AMT(mapper.getAmount().getValue());
				fpoitemins.setCP_ORIGCNTRYCD(mapper.getOrigCountryCd().getValue());
				fpoitemins.setITEM_ID(cdsMapper.getMailObject().getValue().getId().getValue());
				fpoitemins.setMESG_TYP(cdsMapper.getMailObject().getValue().getTypeCd().getValue());
				fpoitemins.setPOSTINGDT(postingdt);
				fpoitemins.setCP_NETWT(mapper.getNetWeight().getValue());
				fpoitemins.setCP_CURRCD(mapper.getCurrencyCd().getValue());
				fpoitemins.setCP_HS(mapper.getHS().getValue());
				fpoitemins.setCP_REVISEDHS(mapper.getRevisedHS().getValue());
				fpoitemins.setCP_NOU(mapper.getNumber().getValue());
//							fpoitemins.setITEM_NO(mapper.getNumber().getValue());
				itno = counter.getAndIncrement();
				fpoitemins.setITEM_NO(itno);
				fpoitemins.setUNIQUE_ID(cdsMapper.getMailObject().getValue().getPId().getValue());
				fpoitemins.setCP_DESC(mapper.getDescription().getValue());
				String desc;
				desc = fpoitemins.getCP_DESC();
				if (desc == null) {
					fpoitemins.setCP_DESC("-");
				}

				System.out.println("DESC: " + desc);
				errstr = "";
				cth = "";
				fou = 0;
				if (fpoitemins.getCP_AMT() == null) {
					errstr = "INVALID CONTENT PIECE AMOUNT...Item " + String.valueOf(counter) + " ...NULL VALUE...";
					fou = 1;
				} else if (fpoitemins.getCP_AMT().compareTo(new BigDecimal("0")) == 0) {
					errstr = "INVALID CONTENT PIECE AMOUNT...Item " + String.valueOf(counter) + " ...EQUALS 0...";
					fou = 1;
				}
				if (fpoitemins.getCP_NOU() == 0) {
					errstr = "INVALID CONTENT PIECE NO. OF UNITS...Item " + String.valueOf(counter) + " ...EQUALS 0...";
					fou = 1;
				}
				if (fpoitemins.getCP_NETWT() == null) {
					errstr = "INVALID CONTENT PIECE NO. OF UNITS...Item " + String.valueOf(counter)
							+ " ...NULL VALUE...";
					fou = 1;
				} else if (fpoitemins.getCP_NETWT().compareTo(new BigDecimal("0")) == 0) {
					errstr = "INVALID CONTENT PIECE NET WEIGHT...Item " + String.valueOf(counter) + " ...EQUALS 0...";
					fou = 1;
				}
				if (fpoitemins.getCP_CURRCD() != null)
					if (fposubmitRepoImpl.currencyQuery(fpoitemins.getCP_CURRCD()) == null) {
						errstr = errstr + " " + "INVALID CONTENT PIECE CURRENCY CODE...Item " + String.valueOf(counter)
								+ "...NULL VALUE...";
						fou = 1;
					}
				if (fpoitemins.getCP_DESC() == null) {
					errstr = errstr + " " + "INVALID CONTENT PIECE DECLARATION...Item " + String.valueOf(counter)
							+ "...NULL VALUE...";
					fou = 1;
				} else if (fpoitemins.getCP_DESC().length() < 5) {
					errstr = errstr + " " + "INVALID CONTENT PIECE DECLARATION...Item " + String.valueOf(counter)
							+ "...LENGTH < 5...";
					fou = 1;
				}
				if (fpoitemins.getCP_HS() != null && fpoitemins.getCP_REVISEDHS() == null) {
					if (fpoitemins.getCP_HS().length() < 2) {
						errstr = errstr + " " + "INVALID CONTENT PIECE HS...LENGTH < 2...";
						fou = 1;
						cth = "98049000";
					} else if (fpoitemins.getCP_HS().substring(0, 2) == "30"
							|| fpoitemins.getCP_HS().substring(0, 2) == "38")
						cth = "98041000";
					else if (fpoitemins.getCP_HS().substring(0, 2) != "30"
							&& fpoitemins.getCP_HS().substring(0, 2) != "38")
						cth = "98049000";
				} else if (fpoitemins.getCP_HS() == null && fpoitemins.getCP_REVISEDHS() == null) {
					errstr = errstr + " " + "INVALID CONTENT PIECE HS AND CONTENT PIECE REVISED  HS...Item "
							+ String.valueOf(counter) + "...NULL VALUE...";
					fou = 1;
					cth = "98049000";
				} else if (fpoitemins.getCP_REVISEDHS() != null && fpoitemins.getCP_HS() == null) {
					if (fpoitemins.getCP_REVISEDHS().length() < 2) {
						errstr = errstr + " " + "INVALID CONTENT PIECE REVISED HS...LENGTH < 2...";
						fou = 1;
						cth = "98049000";
					} else if (fpoitemins.getCP_REVISEDHS().substring(0, 2) == "30"
							|| fpoitemins.getCP_REVISEDHS().substring(0, 2) == "38")
						cth = "98041000";
					else if (fpoitemins.getCP_REVISEDHS().substring(0, 2) != "30"
							&& fpoitemins.getCP_REVISEDHS().substring(0, 2) != "38")
						cth = "98049000";
				} else if (fpoitemins.getCP_REVISEDHS() != null && fpoitemins.getCP_HS() != null) {
					if (fpoitemins.getCP_REVISEDHS().length() < 2 && fpoitemins.getCP_HS().length() < 2) {
						errstr = errstr + " "
								+ "INVALID CONTENT PIECE HS AND CONTENT PIECE REVISED HS ...LENGTH < 2...";
						fou = 1;
						cth = "98049000";
					}
					if (fpoitemins.getCP_REVISEDHS().substring(0, 2) == "30"
							|| fpoitemins.getCP_REVISEDHS().substring(0, 2) == "38")
						cth = "98041000";
					else if (fpoitemins.getCP_REVISEDHS().substring(0, 2) != "30"
							&& fpoitemins.getCP_REVISEDHS().substring(0, 2) != "38")
						cth = "98049000";
				}
				System.out.println(
						"cth=" + cth + ",hs=" + fpoitemins.getCP_HS() + ",revisedhs=" + fpoitemins.getCP_REVISEDHS());
				System.out.println("CUSRSP_CP_DET");
				fpoitemins = fpoitemService.updateFpomaininsEntity(fpoitemins);
				System.out.println("CUSRSP_CP_DET data inserted");
				if (fpoitemins.getCP_ORIGCNTRYCD() != null)
					if (fposubmitRepoImpl.insertWithQuery(fpoitemins.getCP_ORIGCNTRYCD(), "CNTRY_NM",
							"OPS$DIR.D_CNTRY_CD", "CNTRY_CD") == null) {
						// "DIR.D_CNTRY_CD@icesdev_imports", "CNTRY_CD") == null) {
						errstr = errstr + " " + "INVALID CONTENT PIECE COUNTRY CODE...";
						fou = 1;
					}
				if (fou == 1) {
					errinsert errins = new errinsert();
					errins.setITEM_ID(cdsMapper.getMailObject().getValue().getId().getValue());
					errins.setCUS_SITE(fposubmit.getCUS_SITE());
					errins.setPOSTINGDT(cdsMapper.getMailObject().getValue().getPostingDt().toString());
					errins.setJOB_NO(fpoitemins.getJOB_NO());
					errins.setSqlDate(fpoitemins.getSqlDate());
					errins.setITEM_NO(itno);
					errins.setUNIQUE_ID(cdsMapper.getMailObject().getValue().getPId().getValue());
					errins.setERR(errstr);
					errins = errService.updateFpomaininsEntity(errins);
					// errins = errService.updateFpomaininsEntity(errins);
				}
				// Item_Counter(itemCounter);
//							itemCounter = itemCounter +1 ;
				// Fpoitemins fpoitemins = new Fpoitemins();
				fposubmititem fposubitm = new fposubmititem();
				fposubitm.setITEM_NO(itno);
				fposubitm.setUNIQUE_ID(fpoitemins.getUNIQUE_ID());
				fposubitm.setPOSTINGDT(fpoitemins.getPOSTINGDT());
				fposubitm.setMESG_TYPE_CD(fpoitemins.getMESG_TYP());
				fposubitm.setJOB_NO(fpoitemins.getJOB_NO());
				currcd = mapper.getCurrencyCd().getValue();
				System.out.println("currcd=" + currcd);
				if (!(fpomainins.getNATURE_TYPE_CD().equals("31")))
				{	fposubitm.setBcdnotn(dutyCalc.getNotn());
				    fposubitm.setBcdnsno(dutyCalc.getSlno());
				}
				if (FposubmititemRepoImpl.getcurrt(currcd) == null)
					currrate = "0";
				else

					currrate = FposubmititemRepoImpl.getcurrt(currcd);
				// fpomaininsRepo.getOne();
				fposubitm.setCindt(fpomainins.getSqlDate());
				fposubitm.setSqlDate(fpomainins.getSqlDate());
				fposubitm.setITEM_ID(fpoitemins.getITEM_ID());
				fposubitm.setITEM_DESC(fpoitemins.getCP_DESC());
				fposubitm.setCTH(fpoitemins.getCP_HS());
				fposubitm.setCTH_REVISED(fpoitemins.getCP_REVISEDHS());
				fposubitm.setDECL_VAL(fpoitemins.getCP_AMT());
				declvalitm = fpoitemins.getCP_AMT();
				totdeclval = totdeclval.add(declvalitm);
				assval = declvalitm.multiply(new BigDecimal(currrate));
				totassval = totassval.add(assval);
				System.out.println("totdeclval=" + totdeclval);
				fposubitm.setNETWT(fpoitemins.getCP_NETWT());
				fposubitm.setNOU(fpoitemins.getCP_NOU());
				fposubitm.setORIGCNTRYCD(fpoitemins.getCP_ORIGCNTRYCD());
				fposubitm.setGEN_CTH(cth);
				fposubitm.setCIN_NO(cinno);
				// System.out.println(FposubmititemRepoImpl.getBCD(cth));
				fposubitm.setCURRCD(currcd);
				fposubitm.setCURR_RATE(currrate);
				fposubitm.setASSESS_VAL(assval);
				// fposubitm.set
				float BcdRate,IGSTRate,SWRate;
				BcdRate = Float.parseFloat(dutyCalcDetailsRepo.findAll().get(0).getBcdRta_eff().toString());
				IGSTRate = Float.parseFloat(dutyCalcDetailsRepo.findAll().get(0).getIgst_Rta().toString());
				SWRate = Float.parseFloat(dutyCalcDetailsRepo.findAll().get(0).getSw_Rta().toString());
				Float basicValue = 0.01f;
				//float assvalinsfrt = fPO_ITEM_DET.getASSVAL_INSFRT();
				float assvalue = assval.floatValue();
				float bcddutyo = assvalue*BcdRate*basicValue;
				float swdutyo = bcddutyo * SWRate * basicValue;
				float igstdutyo = (bcddutyo+swdutyo+assvalue)*IGSTRate * basicValue;
				float totdutyo = bcddutyo+swdutyo+igstdutyo;
				String bcdrt;
				String igstrt;
				String swsrt;
				if (cth == cth1) {
					if (fpomainins.getNATURE_TYPE_CD().equals("31"))
						bcdrt = bcdgftrt1;
					else
					    bcdrt = bcdrt1;
					igstrt = igstrt1;
					swsrt = swsrt1;
				} else {
					if (fpomainins.getNATURE_TYPE_CD().equals("31"))
						bcdrt = bcdgftrt1;
					else
					  bcdrt = bcdrt2;
					igstrt = igstrt2;
					swsrt = swsrt2;
				}
				bcdduty = assval.multiply(new BigDecimal(bcdrt)).multiply(new BigDecimal(0.01));
				swsduty = assval.multiply(new BigDecimal(0.01)).multiply(new BigDecimal(bcdrt))
						.multiply(new BigDecimal(0.01)).multiply(new BigDecimal(swsrt));
				igstduty = (bcdduty.add(swsduty).add(assval)).multiply(new BigDecimal(igstrt))
						.multiply(new BigDecimal(0.01));
				float bcdfg,swfg,igfg;
				bcdfg = bcddutyo - bcdduty.floatValue();
				swfg = swdutyo - swsduty.floatValue();
				igfg = igstdutyo - igstduty.floatValue();
				float dutyfg = bcdfg+swfg+igfg;
				fposubitm.setBCD_AMT_FG(bcdfg);
				fposubitm.setIGST_AMT_FG(igfg);
				fposubitm.setSW_AMT_FG(swfg);
				fposubitm.setBCD_RTA(bcdrt);
				fposubitm.setIGST_RTA(igstrt);
				fposubitm.setSWS_RTA(swsrt);		
				fposubitm.setDUTY_FG(dutyfg);
				inscusrspittax(fposubitm.getCIN_NO(), fposubitm.getITEM_ID(), fposubitm.getITEM_NO(),
						fposubitm.getCURRCD(), bcdduty, bcdrt, "BCD", fposubitm.getCUS_SITE());
				inscusrspittax(fposubitm.getCIN_NO(), fposubitm.getITEM_ID(), fposubitm.getITEM_NO(),
						fposubitm.getCURRCD(), igstduty, igstrt, "IGST", fposubitm.getCUS_SITE());
				inscusrspittax(fposubitm.getCIN_NO(), fposubitm.getITEM_ID(), fposubitm.getITEM_NO(),
						fposubitm.getCURRCD(), swsduty, swsrt, "SWS", fposubitm.getCUS_SITE());
//			} else {
//				bcdduty = assval.multiply(new BigDecimal(bcdrt2)).multiply(new BigDecimal(0.01));
//				swsduty = assval.multiply(new BigDecimal(0.01)).multiply(new BigDecimal(bcdrt2))
//						.multiply(new BigDecimal(0.01)).multiply(new BigDecimal(swsrt2));
//				igstduty = (bcdduty.add(swsduty).add(assval)).multiply(new BigDecimal(igstrt2))
//						.multiply(new BigDecimal(0.01));
//				fposubitm.setBCD_RTA(bcdrt2);
//				fposubitm.setIGST_RTA(igstrt2);
//				fposubitm.setSWS_RTA(swsrt2);
//			}
				dutyitm = bcdduty.add(swsduty).add(igstduty);
				totduty = totduty.add(dutyitm);
				totdutyfg=totdutyfg + dutyfg;
				fposubitm.setDUTY(dutyitm);
				fposubitm.setBCD_AMT(bcdduty);
				fposubitm.setSWS_AMT(swsduty);
				fposubitm.setIGST_AMT(igstduty);
				fposubitm.setCUS_SITE(fposubmit.getCUS_SITE());
				fposubitm.setMODIFIED("N");
				inscusrspitem(cdsMapper, fposubitm);
				System.out.println("FPOITEM INSERT");
				fposubitm = fpoSubmititemService.updateFpomaininsEntity(fposubitm);

				System.out.println("FPOITEM INSERTED");

				// ------------------------------------------Response Started
				// CdsController-----------//

				ObjectFactory objectFactoryrsp = new ObjectFactory();
				ArrayOfCDSView cdsviewsArrayrsp = new ArrayOfCDSView();
				List<CDSView> cdsViewListrsp = new ArrayList<CDSView>();
				CDSView cdsViewObjectrsp = new CDSView();
				MailObject mobj = new MailObject();
				Response cusresponse = new Response();
				ResponseResponseData responseResponseData = new ResponseResponseData();
				ResponseResponseDataTax responseResponseDataTax = new ResponseResponseDataTax();
				ResponseResponseDataTax responseResponseDataTaxIgst = new ResponseResponseDataTax();
				ResponseResponseDataTax responseResponseDataTaxSw = new ResponseResponseDataTax();
				List<ResponseResponseDataTax> responseResponseDataTaxList = new ArrayList<ResponseResponseDataTax>();

				CreateOrUpdateResponses response = objectFactoryrsp.createCreateOrUpdateResponses();

				cdsViewObjectrsp.setEntityState(1);

				mobj.setEntityState(1);
				mobj.setDestPostOrgCd(objectFactoryrsp.createMailObjectDestPostOrgCd(fposubmit.getDESTPOST_ORG_CD()));
				mobj.setId(objectFactoryrsp.createMailObjectId((fposubmit.getITEM_ID())));
				mobj.setMailClassCd(objectFactoryrsp.createMailObjectMailClassCd(fposubmit.getMAIL_CLASS_CD()));
				mobj.setOrigPostOrgCd(objectFactoryrsp.createMailObjectOrigPostOrgCd(fposubmit.getORIGPOST_ORG_CD()));
				mobj.setPId(objectFactoryrsp.createMailObjectPId(fposubmit.getMAIL_PID()));
				XMLGregorianCalendar date2;
				try {
					// System.out.println(clrdt.toString().substring(0,
					date2 =

							DatatypeFactory.newInstance().newXMLGregorianCalendar(fposubmit.getPOSTINGDT().trim());

					mobj.setPostingDt(date2);
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mobj.setTypeCd(objectFactoryrsp.createMailObjectTypeCd(fposubmit.getTYPE_CD()));
				JAXBElement<MailObject> mailObject = objectFactoryrsp.createMailObject(mobj);
				// -----------------------------------responseResponseDataTax------------------/
				// if (counti == 1) {
				
				BigDecimal bcdamnt=FposubmititemRepo2.getBCDAmnt(fposubmit.getCIN_NO());
				String BCD_RTA=FposubmititemRepo2.getBCD_RTA(fposubmit.getCIN_NO());
				responseResponseDataTax.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
						bcdamnt.setScale(2, BigDecimal.ROUND_DOWN)));
				responseResponseDataTax.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//				responseResponseDataTax.setDescription(
//						objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//				responseResponseDataTax.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//				responseResponseDataTax
//						.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//				responseResponseDataTax.setNumber(
//						objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
				responseResponseDataTax.setRate(
						objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(BCD_RTA)));
				responseResponseDataTax.setType(objectFactoryrsp.createResponseResponseDataTaxType("BC"));
				responseResponseDataTaxList.add(responseResponseDataTax);
				// }
				// if (counti == 2) {

				BigDecimal IGST_AMT=FposubmititemRepo2.getIGST_AMT(fposubmit.getCIN_NO());
				String IGST_RTA=FposubmititemRepo2.getIGST_RTA(fposubmit.getCIN_NO());
				responseResponseDataTaxIgst.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
						IGST_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
				responseResponseDataTaxIgst
						.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//				responseResponseDataTaxIgst.setDescription(
//						objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//				responseResponseDataTaxIgst
//						.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//				responseResponseDataTaxIgst
//						.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//				responseResponseDataTaxIgst.setNumber(
//						objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
				responseResponseDataTaxIgst.setRate(
						objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(IGST_RTA)));
				responseResponseDataTaxIgst.setType(objectFactoryrsp.createResponseResponseDataTaxType("IG"));
				responseResponseDataTaxList.add(responseResponseDataTaxIgst);
				// }
				// if (counti == 3) {
				BigDecimal SW_AMT=FposubmititemRepo2.getSW_AMT(fposubmit.getCIN_NO());
				String SW_RTA=FposubmititemRepo2.getSW_RTA(fposubmit.getCIN_NO());
				responseResponseDataTaxSw.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
						SW_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
				responseResponseDataTaxSw
						.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
//				responseResponseDataTaxSw.setDescription(
//						objectFactoryrsp.createResponseResponseDataTaxDescription(fposubitm.getITEM_DESC()));
//				responseResponseDataTaxSw.setHS((objectFactoryrsp.createResponseResponseDataTaxHS(fposubitm.getCTH())));
//				responseResponseDataTaxSw
//						.setNetWeight(objectFactoryrsp.createResponseResponseDataTaxNetWeight(fposubitm.getNETWT()));
//				responseResponseDataTaxSw.setNumber(
//						objectFactoryrsp.createResponseResponseDataTaxNumber((int) (long) fposubitm.getNOU()));
				responseResponseDataTaxSw.setRate(
						objectFactoryrsp.createResponseResponseDataTaxRate(new BigDecimal(SW_RTA)));
				responseResponseDataTaxSw.setType(objectFactoryrsp.createResponseResponseDataTaxType("SW"));
				responseResponseDataTaxList.add(responseResponseDataTaxSw);
				// }
				// counti = counti + 1;
				// -----------------------------------responseResponseDataTax------------------/
				ArrayOfResponseResponseDataTax responsetax = new ArrayOfResponseResponseDataTax();
				// if (totduty.compareTo(BigDecimal.ZERO) == 1)
				

				
				

				ResponseResponseDataTax responseResponseDataTaxFN = new ResponseResponseDataTax();
				ResponseResponseDataTax responseResponseDataTaxPL = new ResponseResponseDataTax();

				
				BigDecimal FN_AMT=fpoCurQueRepo.getFN_AMT(fposubmit.getCIN_NO());
				BigDecimal PL_AMT=fpoCurQueRepo.getPL_AMT(fposubmit.getCIN_NO());
				
				if(FN_AMT == null)
					FN_AMT=new BigDecimal("0");
				if(PL_AMT == null)
					PL_AMT=new BigDecimal("0");
				
				BigDecimal fn = new BigDecimal("0");
				
				int res = FN_AMT.compareTo(fn);
				
				if( res == 1 ) {
					
					responseResponseDataTaxFN.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
							FN_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
					responseResponseDataTaxFN
							.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
					responseResponseDataTaxFN.setRate(objectFactoryrsp
							.createResponseResponseDataTaxRate(new BigDecimal("0")));
					responseResponseDataTaxFN.setType(
							objectFactoryrsp.createResponseResponseDataTaxType("FN"));
					responseResponseDataTaxList.add(responseResponseDataTaxFN);
				}

				int res1 = PL_AMT.compareTo(fn);
				

				
				if( res1 == 1 ) {
					
					responseResponseDataTaxPL.setAmount(objectFactoryrsp.createResponseResponseDataTaxAmount(
							PL_AMT.setScale(2, BigDecimal.ROUND_DOWN)));
					responseResponseDataTaxPL
							.setCurrencyCd(objectFactoryrsp.createResponseResponseDataTaxCurrencyCd("INR"));
					responseResponseDataTaxPL.setRate(objectFactoryrsp
							.createResponseResponseDataTaxRate(new BigDecimal("0")));
					responseResponseDataTaxPL.setType(
							objectFactoryrsp.createResponseResponseDataTaxType("PL"));
					responseResponseDataTaxList.add(responseResponseDataTaxPL);
				}
				if (decicd == 99) {
				} else {
					responsetax.getResponseResponseDataTax().add(responseResponseDataTax);
					responsetax.getResponseResponseDataTax().add(responseResponseDataTaxIgst);
					responsetax.getResponseResponseDataTax().add(responseResponseDataTaxSw);
					
					if( res == 1 ) {
						responsetax.getResponseResponseDataTax().add(responseResponseDataTaxFN);
					}
					if( res1 == 1 ) {
						responsetax.getResponseResponseDataTax().add(responseResponseDataTaxPL);
					}
				}
				JAXBElement<ArrayOfResponseResponseDataTax> arrayOfResponseResponseDataTaxes = objectFactoryrsp
						.createResponseResponseDataTaxes(responsetax);
				// if (totduty.compareTo(BigDecimal.ZERO) == 1)

				arrayOfResponseResponseDataTaxes.setValue(responsetax);

				cusresponse.setEntityState(1);
				cusresponse.setCDSStateCd(55);
				cusresponse.setCustOrganizationCd(
						objectFactoryrsp.createCDSObjectCustOrganizationCd(fposubmit.getCUST_ORG_CD()));
				cusresponse.setPostOrganizationCd(
						objectFactoryrsp.createCDSObjectPostOrganizationCd(fposubmit.getPOST_ORG_CD()));
				// cusresponse.setPId(objectFactoryrsp.createCDSObjectPId(fposubmit.getMAIL_PID()));

				JAXBElement<Response> responseJaxEle = objectFactoryrsp.createResponse(cusresponse);
				try {
					date2 = DatatypeFactory.newInstance()
							// .newXMLGregorianCalendar("2015-04-28T14:23:38.521Z");
							// .newXMLGregorianCalendar("2020-01-20T15:29:01");
							.newXMLGregorianCalendar(clrdt.substring(0, 10) + 'T' + clrdt.substring(11, 19));
					responseResponseData.setClearanceDt(objectFactoryrsp.createResponseResponseDataClearanceDt(date2));
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				responseResponseData
						.setDecisionCd(objectFactoryrsp.createResponseResponseDataDecisionCd(String.valueOf(decicd)));
//				responseResponseData.setDecisionReasonCd(
//						objectFactoryrsp.createResponseResponseDataDecisionReasonCd(String.valueOf(reascd)));
				responseResponseData
						.setDecisionReasonNm(objectFactoryrsp.createResponseResponseDataDecisionReasonNm(reasnm));
				Boolean dt2 = false;
				Boolean dt5 = true;

				// if (totduty.compareTo(BigDecimal.ZERO) == 1)
				
				BigDecimal TOT_AMT_PAYABLE=fpoCurQueRepo.getTOT_AMT_PAYABLE(cinno);
				
				if(TOT_AMT_PAYABLE == null)
					TOT_AMT_PAYABLE=new BigDecimal("0");
				int res2 = TOT_AMT_PAYABLE.compareTo(fn);
				
				
				if (decicd == 99 && res2 == 0) {
					responseResponseData.setDutiable(dt2);
					responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);
				}else {
					responseResponseData.setDutiable(dt5);
					responseResponseData.setTaxes(arrayOfResponseResponseDataTaxes);
					responseResponseData
							.setTotalFee(objectFactoryrsp.createResponseResponseDataTotalFee(new BigDecimal(0)));
					responseResponseData.setTotalFeeCurrencyCd(
							objectFactoryrsp.createResponseResponseDataTotalFeeCurrencyCd("INR"));
				}

				JAXBElement<ResponseResponseData> responseResponseDataJaxEle = objectFactoryrsp
						.createResponseData(responseResponseData);
				cusresponse.setData(responseResponseDataJaxEle);

				cdsViewObjectrsp.setMailObject(mailObject);
				cdsViewObjectrsp.setResponse(responseJaxEle);

				cdsViewListrsp.add(cdsViewObjectrsp);

				JAXBElement<CDSView> array1 = objectFactoryrsp.createCDSView((CDSView) cdsViewListrsp.get(0));

				array1.setValue(cdsViewObjectrsp);
				cdsviewsArrayrsp.getCDSView().addAll(cdsViewListrsp);

				JAXBElement<ArrayOfCDSView> cdsViews = objectFactoryrsp
						.createCreateOrUpdateDeclarationsCdsViews(cdsviewsArrayrsp);
				cdsViews.setValue(cdsviewsArrayrsp);
				response.setSecurityToken("ad990024-fd05-498c-9f21-dfb0ed4ce032");
				response.setCdsViews(cdsViews);
				// response.setUserCd(objectFactoryrsp.createCreateOrUpdateResponsesUserCd("cuscoko"));
				response.setUserCd(objectFactoryrsp.createCreateOrUpdateResponsesUserCd(usercd));
				System.out.println("CUSRSP XML");
				soapClient.LoadResponse(response);

			} catch (Exception e) {
				errstr = e.getMessage();
				errinsert errins = new errinsert();
				errins.setERR(errstr);

			}
			// ------------------------------------------Response Ended
			// CdsController-----------//

		});

		AtomicInteger counter1 = new AtomicInteger(1);
		List<DeclarationDeclarationDataDocument> decDocument = cdsMapper.getDeclaration().getValue().getData()
				.getValue().getDocuments().getValue().getDeclarationDeclarationDataDocument();

		System.out.println("document size  " + decDocument.size());

		if (decDocument != null) {
			// if ((decDocument.size() > 1) || (decDocument.size() == 1)) {
			// decDocument.stream()

//			Predicate<DeclarationDeclarationDataDocument> docPredicate = doc -> (doc.getDocumentName() !=null);
//			decDocument.stream()
//			.peek(c -> { 
//			   if (c.getDocumentName()== null) {
//				   System.out.println("document name is null");
//			   }
//			}).filter(docPredicate).collect(Collectors.toList());

			decDocument.stream().// filter(Objects::nonNull).
					forEach(doc -> {
//			  itno1 = counter1.getAndIncrement();
						try {
							itno1 = counter1.getAndIncrement();
							Fpodocdet fpodocdet = new Fpodocdet();
							fpodocdet.setJOB_NO(fpomainins.getJOB_NO());
							fpodocdet.setJobdt(fpomainins.getSqlDate());
							fpodocdet.setCIN_NO(fposubmit.getCIN_NO());
							fpodocdet.setCindt(fpomainins.getSqlDate());
							fpodocdet.setITEM_ID(cdsMapper.getMailObject().getValue().getId().getValue());
							fpodocdet.setMESG_TYP(cdsMapper.getMailObject().getValue().getTypeCd().getValue());
							fpodocdet.setPOSTINGDT(postingdt);
							fpodocdet.setDocid(doc.getDocumentId().getValue());
							fpodocdet.setDocname(doc.getDocumentName().getValue());
							fpodocdet.setDoctyp(doc.getDocumentType().getValue());
							fpodocdet.setDocslno(itno1);
							fpodocdet = fpodetdocService.saveEntity(fpodocdet);

							Fpocusitmdoc fpocusdoc = new Fpocusitmdoc();
							fpocusdoc.setJOB_NO(fpomainins.getJOB_NO());
							fpocusdoc.setJobdt(fpomainins.getSqlDate());
							fpocusdoc.setITEM_ID(cdsMapper.getMailObject().getValue().getId().getValue());
							fpocusdoc.setMESG_TYP(cdsMapper.getMailObject().getValue().getTypeCd().getValue());
							fpocusdoc.setPOSTINGDT(postingdt);
							fpocusdoc.setDocid(doc.getDocumentId().getValue());
							fpocusdoc.setDocname(doc.getDocumentName().getValue());
							fpocusdoc.setDoctyp(doc.getDocumentType().getValue());

							fpocusdoc.setDocslno(itno1);
							fpocusdoc = fpocusdocService.saveEntity(fpocusdoc);

						} catch (Exception e) {
							if (null == e.getMessage())
								System.out.println("Null Pointer Exception");
						}
					});
		}
	}

	////// updation at the end ////////
	public void upddata() {

//		fpomaininsRepo.updfrtcurrrt();
//		fpomaininsRepo.updinscurrrt();
//		fpomaininsRepo.updtotassitm();
//		fpomaininsRepo.updfrtcalcval();
//		fpomaininsRepo.updinscalcval();
//		fpomaininsRepo.updfrtcalcval2();
//		fpomaininsRepo.updinscalcval2();
//		fpomaininsRepo.updtotassval();
//		FposubmititemRepo2.updassvalinsfrt();
//		FposubmititemRepo2.updbcdamt();
//		FposubmititemRepo2.updswamt();
//		FposubmititemRepo2.updigstamt();
//		fpomaininsRepo.updtotduty();
//		fpomaininsRepo.updtotdutypay();
//		fpomaininsRepo.updtotdutypay2();
		fpomaininsRepo.updmaincif();
		// FposubmititemRepo2.upitmcif();
	}

	////// updation at the end ////////
}