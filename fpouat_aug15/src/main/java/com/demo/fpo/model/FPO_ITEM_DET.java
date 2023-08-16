package com.demo.fpo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ITEM_DET")
public class FPO_ITEM_DET {
	
	@Id
	@GenericGenerator(name = "ITEM_ID", strategy = "com.seq.gen.ITEM_SEQIdGenerator")
	@GeneratedValue(generator = "ITEM_ID")
	@Column(name = "ITEM_UNIQUE")
	public Long id;
	
	@Column(name = "CIN_DT")
	private Date CIN_DT;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Transient
	private String itemId;
	
	@Transient
	private String reasons;

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;

	@Column(name = "MESG_TYPE_CD")
	private String MESG_TYPE_CD;

	@Column(name = "JOB_NO")
	private Long JOB_NO;

	@Column(name = "JOB_DT")
	private Date JOB_DT;

	@Column(name = "ITEM_NO")
	private Long ITEM_NO;

	@Transient
	private Long itemNumber;

	@Column(name = "NOU")
	private Long NOU;

	@Column(name = "NETWT")
	private Float NETWT;

	@Column(name = "ASSESS_VAL")
	private Float ASSESS_VAL;

	@Column(name = "DECL_VAL")
	private Float DECL_VAL;

	@Column(name = "CURRCD")
	private String CURRCD;

	@Column(name = "ITEM_DESC")
	private String ITEM_DESC;

	@Column(name = "CTH")
	private String CTH;

	@Column(name = "ORIGCNTRYCD")
	private String ORIGCNTRYCD;

	@Column(name = "ITEM_REVISEDDESC")
	private String ITEM_REVISEDDESC;

	@Column(name = "CTH_REVISED")
	private String CTH_REVISED;

	@Column(name = "BCD_NOTN")
	private String BCD_NOTN;

	@Transient
	private String bcdNt;

	@Column(name = "BCD_NSNO")
	private String BCD_NSNO;

	@Transient
	private String bcdNtNo;

	@Column(name = "DUTY")
	private Float DUTY;

	@Transient
	private Float singleItemDuty;

	@Column(name = "DUTY_FG")
	private Float DUTY_FG;

	@Transient
	private Float dutyFg;

	@Column(name = "BCD_RTA")
	private Float BCD_RTA;

	@Transient
	private Float bcdRate;

	@Column(name = "BCD_AMT")
	private Float BCD_AMT;

	@Transient
	private Float bcdAmt;

	@Column(name = "BCD_AMT_FG")
	private Float BCD_AMT_FG;

	@Transient
	private Float bcdAmtFg;

	@Column(name = "IGST_NOTN")
	private String IGST_NOTN;

	@Transient
	private String igstNt;

	@Column(name = "IGST_NSNO")
	private String IGST_NSNO;

	@Transient
	private String igstNtNo;

	@Column(name = "IGST_RTA")
	private Float IGST_RTA;

	@Transient
	private Float igstRate;

	@Column(name = "IGST_AMT")
	private Float IGST_AMT;

	@Transient
	private Float igstAmt;

	@Column(name = "IGST_AMT_FG")
	private Float IGST_AMT_FG;

	@Transient
	private Float igstAmtFg;

	@Column(name = "SW_NOTN")
	private String SW_NOTN;

	@Transient
	private String swNt;

	@Column(name = "SW_NSNO")
	private String SW_NSNO;

	@Transient
	private String swNtNo;

	@Column(name = "SW_AMT")
	private Float SW_AMT;

	@Transient
	private Float swAmt;

	@Column(name = "SW_RTA")
	private Float SW_RTA;

	@Transient
	private Float swRate;

	@Column(name = "SW_AMT_FG")
	private Float SW_AMT_FG;

	@Transient
	private Float swAmtFg;

	@Transient
	private Float allItemDuty;

	@Transient
	private Float allItemDutyFg;

	@Transient
	private Float dutyPayable;
	
	@Transient
	private String generalQuery;
	
	@Transient
	private String din;
	
	@Transient
	private String mark;
	
	@Transient
	private Float dutyRateOthers;
	
	@Transient
	private Long dutyCdOthers;
	
	@Transient
	private List<Object> listObjects;
	
	@Transient
	private String flagRequest;
	
	@Transient
	private String flagRequestQry;
	
	@Transient
	private String emailid;
	
	@Transient 
	private String mobileno;
	
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	@Transient
	List<FpoItemDetOthDuty> fpoItemDetOthDuty;
	
	public String getFlagRequestQry() {
		return flagRequestQry;
	}

	public void setFlagRequestQry(String flagRequestQry) {
		this.flagRequestQry = flagRequestQry;
	}

	public String getFlagRequest() {
		return flagRequest;
	}

	public void setFlagRequest(String flagRequest) {
		this.flagRequest = flagRequest;
	}

	public List<FpoItemDetOthDuty> getFpoItemDetOthDuty() {
		return fpoItemDetOthDuty;
	}

	public void setFpoItemDetOthDuty(List<FpoItemDetOthDuty> fpoItemDetOthDuty) {
		this.fpoItemDetOthDuty = fpoItemDetOthDuty;
	}
	
	public List<Object> getListObjects() {
		return listObjects;
	}

	public void setListObjects(List<Object> listObjects) {
		this.listObjects = listObjects;
	}

	public Long getDutyCdOthers() {
		return dutyCdOthers;
	}

	public void setDutyCdOthers(Long dutyCdOthers) {
		this.dutyCdOthers = dutyCdOthers;
	}

	public Float getDutyRateOthers() {
		return dutyRateOthers;
	}

	public void setDutyRateOthers(Float dutyRateOthers) {
		this.dutyRateOthers = dutyRateOthers;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public String getGeneralQuery() {
		return generalQuery;
	}

	public void setGeneralQuery(String generalQuery) {
		this.generalQuery = generalQuery;
	}

	public Float getASSESS_VAL() {
		return ASSESS_VAL;
	}

	public void setASSESS_VAL(Float aSSESS_VAL) {
		ASSESS_VAL = aSSESS_VAL;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBcdNt() {
		return bcdNt;
	}

	public void setBcdNt(String bcdNt) {
		this.bcdNt = bcdNt;
	}

	public String getBcdNtNo() {
		return bcdNtNo;
	}

	public void setBcdNtNo(String bcdNtNo) {
		this.bcdNtNo = bcdNtNo;
	}

	public Float getSingleItemDuty() {
		return singleItemDuty;
	}

	public void setSingleItemDuty(Float singleItemDuty) {
		this.singleItemDuty = singleItemDuty;
	}

	public Float getDutyFg() {
		return dutyFg;
	}

	public void setDutyFg(Float dutyFg) {
		this.dutyFg = dutyFg;
	}

	public Float getBcdRate() {
		return bcdRate;
	}

	public void setBcdRate(Float bcdRate) {
		this.bcdRate = bcdRate;
	}

	public Float getBcdAmt() {
		return bcdAmt;
	}

	public void setBcdAmt(Float bcdAmt) {
		this.bcdAmt = bcdAmt;
	}

	public Float getBcdAmtFg() {
		return bcdAmtFg;
	}

	public void setBcdAmtFg(Float bcdAmtFg) {
		this.bcdAmtFg = bcdAmtFg;
	}

	public String getIgstNt() {
		return igstNt;
	}

	public void setIgstNt(String igstNt) {
		this.igstNt = igstNt;
	}

	public String getIgstNtNo() {
		return igstNtNo;
	}

	public void setIgstNtNo(String igstNtNo) {
		this.igstNtNo = igstNtNo;
	}

	public Float getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(Float igstRate) {
		this.igstRate = igstRate;
	}

	public Float getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(Float igstAmt) {
		this.igstAmt = igstAmt;
	}

	public Float getIgstAmtFg() {
		return igstAmtFg;
	}

	public void setIgstAmtFg(Float igstAmtFg) {
		this.igstAmtFg = igstAmtFg;
	}

	public String getSwNt() {
		return swNt;
	}

	public void setSwNt(String swNt) {
		this.swNt = swNt;
	}

	public String getSwNtNo() {
		return swNtNo;
	}

	public void setSwNtNo(String swNtNo) {
		this.swNtNo = swNtNo;
	}

	public Float getSwAmt() {
		return swAmt;
	}

	public void setSwAmt(Float swAmt) {
		this.swAmt = swAmt;
	}

	public Float getSwRate() {
		return swRate;
	}

	public void setSwRate(Float swRate) {
		this.swRate = swRate;
	}

	public Float getSwAmtFg() {
		return swAmtFg;
	}

	public void setSwAmtFg(Float swAmtFg) {
		this.swAmtFg = swAmtFg;
	}

	public Float getAllItemDuty() {
		return allItemDuty;
	}

	public void setAllItemDuty(Float allItemDuty) {
		this.allItemDuty = allItemDuty;
	}

	public Float getAllItemDutyFg() {
		return allItemDutyFg;
	}

	public void setAllItemDutyFg(Float allItemDutyFg) {
		this.allItemDutyFg = allItemDutyFg;
	}

	public Float getDutyPayable() {
		return dutyPayable;
	}

	public void setDutyPayable(Float dutyPayable) {
		this.dutyPayable = dutyPayable;
	}

	@Column(name = "UNIQUE_ID")
	private String UNIQUE_ID;

	@Column(name = "GEN_CTH")
	private String GEN_CTH;

	@Column(name = "CURR_RATE")
	private Float rate;

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Date getASS_DT() {
		return ASS_DT;
	}

	public void setASS_DT(Date aSS_DT) {
		ASS_DT = aSS_DT;
	}

	@Column(name = "MODIFIED")
	private String MODIFIED;

	@Column(name = "AMEND_SERIAL_NO")
	private Long AMEND_SERAIL_NO;

	@Column(name = "AMEND_DT")
	private Date AMEND_DT;

	@Column(name = "ASS_DT")
	private Date ASS_DT;
	
	@Column(name = "UPDASS_DT")
	private Date UPDASS_DT;
	
	@Column(name = "ASSVAL_INSFRT")
	private Float ASSVAL_INSFRT;


	public Float getASSVAL_INSFRT() {
		return ASSVAL_INSFRT;
	}

	public void setASSVAL_INSFRT(Float aSSVAL_INSFRT) {
		ASSVAL_INSFRT = aSSVAL_INSFRT;
	}

	public Date getUPDASS_DT() {
		return UPDASS_DT;
	}

	public void setUPDASS_DT(Date uPDASS_DT) {
		UPDASS_DT = uPDASS_DT;
	}

	@Column(name = "AMEND_FLAG")
	private String AMEND_FLAG;

	@Column(name = "OFF_ID")
	private String OFF_ID;

	@Column(name = "ROLE")
	private String ROLE;

	@Transient
	private String query;

	@Transient
	private String queryRemarks;
	
	
	@Transient
	private String queryRemarks1;

	@Transient
	private String defualtQueryOne;

	@Transient
	private String defualtQueryTwo;
	
	@Transient
	private String calcFlag;
	
	@Transient
	private Long couupdass_dt;
	

	public Long getCouupdass_dt() {
		return couupdass_dt;
	}

	public void setCouupdass_dt(Long couupdass_dt) {
		this.couupdass_dt = couupdass_dt;
	}

	public String getQueryRemarks1() {
		return queryRemarks1;
	}

	public void setQueryRemarks1(String queryRemarks1) {
		this.queryRemarks1 = queryRemarks1;
	}

	public String getCalcFlag() {
		return calcFlag;
	}

	public void setCalcFlag(String calcFlag) {
		this.calcFlag = calcFlag;
	}

	public String getDefualtQueryFour() {
		return defualtQueryFour;
	}

	public void setDefualtQueryFour(String defualtQueryFour) {
		this.defualtQueryFour = defualtQueryFour;
	}

	@Transient
	private String defualtQueryThree;
	
	@Transient
	private String defualtQueryFour;
	
	@Transient
	private Boolean carousel = false;
	
	@Transient
	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Boolean getCarousel() {
		return carousel;
	}

	public void setCarousel(Boolean carousel) {
		this.carousel = carousel;
	}

	public String getDefualtQueryOne() {
		return defualtQueryOne;
	}

	public void setDefualtQueryOne(String defualtQueryOne) {
		this.defualtQueryOne = defualtQueryOne;
	}

	public String getDefualtQueryTwo() {
		return defualtQueryTwo;
	}

	public void setDefualtQueryTwo(String defualtQueryTwo) {
		this.defualtQueryTwo = defualtQueryTwo;
	}

	public String getDefualtQueryThree() {
		return defualtQueryThree;
	}

	public void setDefualtQueryThree(String defualtQueryThree) {
		this.defualtQueryThree = defualtQueryThree;
	}

	public String getQuery() {
		return query;
	}

	public String getQueryRemarks() {
		return queryRemarks;
	}

	public void setQueryRemarks(String queryRemarks) {
		this.queryRemarks = queryRemarks;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getOFF_ID() {
		return OFF_ID;
	}

	public void setOFF_ID(String oFF_ID) {
		OFF_ID = oFF_ID;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCIN_DT() {
		return CIN_DT;
	}

	public void setCIN_DT(Date cIN_DT) {
		CIN_DT = cIN_DT;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public String getMESG_TYPE_CD() {
		return MESG_TYPE_CD;
	}

	public void setMESG_TYPE_CD(String mESG_TYPE_CD) {
		MESG_TYPE_CD = mESG_TYPE_CD;
	}

	public Long getJOB_NO() {
		return JOB_NO;
	}

	public void setJOB_NO(Long jOB_NO) {
		JOB_NO = jOB_NO;
	}

	public Date getJOB_DT() {
		return JOB_DT;
	}

	public void setJOB_DT(Date jOB_DT) {
		JOB_DT = jOB_DT;
	}

	public Long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(Long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	public Long getNOU() {
		return NOU;
	}

	public void setNOU(Long nOU) {
		NOU = nOU;
	}

	public Float getNETWT() {
		return NETWT;
	}

	public void setNETWT(Float nETWT) {
		NETWT = nETWT;
	}

	public Float getDECL_VAL() {
		return DECL_VAL;
	}

	public void setDECL_VAL(Float dECL_VAL) {
		DECL_VAL = dECL_VAL;
	}

	public String getCURRCD() {
		return CURRCD;
	}

	public void setCURRCD(String cURRCD) {
		CURRCD = cURRCD;
	}

	public String getITEM_DESC() {
		return ITEM_DESC;
	}

	public void setITEM_DESC(String iTEM_DESC) {
		ITEM_DESC = iTEM_DESC;
	}

	public String getCTH() {
		return CTH;
	}

	public void setCTH(String cTH) {
		CTH = cTH;
	}

	public String getORIGCNTRYCD() {
		return ORIGCNTRYCD;
	}

	public void setORIGCNTRYCD(String oRIGCNTRYCD) {
		ORIGCNTRYCD = oRIGCNTRYCD;
	}

	public String getITEM_REVISEDDESC() {
		return ITEM_REVISEDDESC;
	}

	public void setITEM_REVISEDDESC(String iTEM_REVISEDDESC) {
		ITEM_REVISEDDESC = iTEM_REVISEDDESC;
	}

	public String getCTH_REVISED() {
		return CTH_REVISED;
	}

	public void setCTH_REVISED(String cTH_REVISED) {
		CTH_REVISED = cTH_REVISED;
	}

	public String getBCD_NOTN() {
		return BCD_NOTN;
	}

	public void setBCD_NOTN(String bCD_NOTN) {
		BCD_NOTN = bCD_NOTN;
	}

	public String getBCD_NSNO() {
		return BCD_NSNO;
	}

	public void setBCD_NSNO(String bCD_NSNO) {
		BCD_NSNO = bCD_NSNO;
	}

	public Float getDUTY() {
		return DUTY;
	}

	public void setDUTY(Float dUTY) {
		DUTY = dUTY;
	}

	public Float getDUTY_FG() {
		return DUTY_FG;
	}

	public void setDUTY_FG(Float dUTY_FG) {
		DUTY_FG = dUTY_FG;
	}

	public Float getBCD_RTA() {
		return BCD_RTA;
	}

	public void setBCD_RTA(Float bCD_RTA) {
		BCD_RTA = bCD_RTA;
	}

	public Float getBCD_AMT() {
		return BCD_AMT;
	}

	public void setBCD_AMT(Float bCD_AMT) {
		BCD_AMT = bCD_AMT;
	}

	public Float getBCD_AMT_FG() {
		return BCD_AMT_FG;
	}

	public void setBCD_AMT_FG(Float bCD_AMT_FG) {
		BCD_AMT_FG = bCD_AMT_FG;
	}

	public String getIGST_NOTN() {
		return IGST_NOTN;
	}

	public Long getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Long itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setIGST_NOTN(String iGST_NOTN) {
		IGST_NOTN = iGST_NOTN;
	}

	public String getIGST_NSNO() {
		return IGST_NSNO;
	}

	public void setIGST_NSNO(String iGST_NSNO) {
		IGST_NSNO = iGST_NSNO;
	}

	public Float getIGST_RTA() {
		return IGST_RTA;
	}

	public void setIGST_RTA(Float iGST_RTA) {
		IGST_RTA = iGST_RTA;
	}

	public Float getIGST_AMT() {
		return IGST_AMT;
	}

	public void setIGST_AMT(Float iGST_AMT) {
		IGST_AMT = iGST_AMT;
	}

	public Float getIGST_AMT_FG() {
		return IGST_AMT_FG;
	}

	public void setIGST_AMT_FG(Float iGST_AMT_FG) {
		IGST_AMT_FG = iGST_AMT_FG;
	}

	public String getSW_NOTN() {
		return SW_NOTN;
	}

	public void setSW_NOTN(String sW_NOTN) {
		SW_NOTN = sW_NOTN;
	}

	public String getSW_NSNO() {
		return SW_NSNO;
	}

	public void setSW_NSNO(String sW_NSNO) {
		SW_NSNO = sW_NSNO;
	}

	public Float getSW_AMT() {
		return SW_AMT;
	}

	public void setSW_AMT(Float sW_AMT) {
		SW_AMT = sW_AMT;
	}

	public Float getSW_RTA() {
		return SW_RTA;
	}

	public void setSW_RTA(Float sW_RTA) {
		SW_RTA = sW_RTA;
	}

	public Float getSW_AMT_FG() {
		return SW_AMT_FG;
	}

	public void setSW_AMT_FG(Float sW_AMT_FG) {
		SW_AMT_FG = sW_AMT_FG;
	}

	public String getUNIQUE_ID() {
		return UNIQUE_ID;
	}

	public void setUNIQUE_ID(String uNIQUE_ID) {
		UNIQUE_ID = uNIQUE_ID;
	}

	public String getGEN_CTH() {
		return GEN_CTH;
	}

	public void setGEN_CTH(String gEN_CTH) {
		GEN_CTH = gEN_CTH;
	}


	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public Long getAMEND_SERAIL_NO() {
		return AMEND_SERAIL_NO;
	}

	public void setAMEND_SERAIL_NO(Long aMEND_SERAIL_NO) {
		AMEND_SERAIL_NO = aMEND_SERAIL_NO;
	}

	public Date getAMEND_DT() {
		return AMEND_DT;
	}

	public void setAMEND_DT(Date aMEND_DT) {
		AMEND_DT = aMEND_DT;
	}

	public String getAMEND_FLAG() {
		return AMEND_FLAG;
	}

	public void setAMEND_FLAG(String aMEND_FLAG) {
		AMEND_FLAG = aMEND_FLAG;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FPO_ITEM_DET) {
            return ((FPO_ITEM_DET) obj).ITEM_NO == ITEM_NO;
        }
        return false;
    }

}
