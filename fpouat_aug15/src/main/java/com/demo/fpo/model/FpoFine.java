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
@Table(name = "FPO_FINE")
public class FpoFine {

	@Id
	@GenericGenerator(name = "FPO_FINE_SEQ", strategy = "com.seq.gen.FPO_FINE_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_FINE_SEQ")
	@Column(name = "ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "CIN_DT")
	private Date cinDT;

	@Column(name = "ITEM_ID")
	private String ITEM_ID;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "POSTINGDT")
	private String POSTINGDT;

	@Column(name = "FINE_AMT")
	public Float fineAmt;

	@Column(name = "FINE_US")
	private String fineUs;

	@Column(name = "OFF_ID")
	private String offId;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "stage")
	private String stage;

	@Column(name = "stage_no")
	private Long stageNo;

	@Transient
	List<FpoPenal> fpoPenalList;

	@Transient
	public Long penalAmt;

	@Transient
	public String Comments;

	@Transient
	public Float totFine;

	@Transient
	public Float totPenalty;

	@Transient
	private String penalUs;

	@Transient
	private Float totalDutyPayable;

	public Float getTotFine() {
		return totFine;
	}

	public void setTotFine(Float totFine) {
		this.totFine = totFine;
	}

	public Float getTotPenalty() {
		return totPenalty;
	}

	public void setTotPenalty(Float totPenalty) {
		this.totPenalty = totPenalty;
	}

	public Float getTotalDutyPayable() {
		return totalDutyPayable;
	}

	public void setTotalDutyPayable(Float totalDutyPayable) {
		this.totalDutyPayable = totalDutyPayable;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public List<FpoPenal> getFpoPenalList() {
		return fpoPenalList;
	}

	public void setFpoPenalList(List<FpoPenal> fpoPenalList) {
		this.fpoPenalList = fpoPenalList;
	}

	public Long getPenalAmt() {
		return penalAmt;
	}

	public void setPenalAmt(Long penalAmt) {
		this.penalAmt = penalAmt;
	}

	public String getPenalUs() {
		return penalUs;
	}

	public void setPenalUs(String penalUs) {
		this.penalUs = penalUs;
	}

	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public Date getCinDT() {
		return cinDT;
	}

	public void setCinDT(Date cinDT) {
		this.cinDT = cinDT;
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

	public Float getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(Float fineAmt) {
		this.fineAmt = fineAmt;
	}

	public String getFineUs() {
		return fineUs;
	}

	public void setFineUs(String fineUs) {
		this.fineUs = fineUs;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Long getStageNo() {
		return stageNo;
	}

	public void setStageNo(Long stageNo) {
		this.stageNo = stageNo;
	}
}
