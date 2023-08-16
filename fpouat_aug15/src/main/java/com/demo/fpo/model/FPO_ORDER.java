package com.demo.fpo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_ORDER")
public class FPO_ORDER {


	@Id
	@GenericGenerator(name = "PRIORITY_ID", strategy = "com.seq.gen.ORDER_SEQIdGenerator")
	@GeneratedValue(generator = "PRIORITY_ID")
	@Column(name = "ID")
	public Long id;
	

	@Column(name = "CIN_NO")
	private String CIN_NO;

	@Column(name = "CUS_SITE")
	private String CUS_SITE;

	@Column(name = "OFF_ID")
	private String OFF_ID;

	@Column(name = "ROLE")
	private String ROLE;

	@Column(name = "ORDER_DATE")
	private Date ORDER_DATE;

	@Column(name = "EXAM_ORDER")
	private String EXAM_ORDER;

	@Column(name = "ORDER_REMARK")
	private String ORDER_REMARK;
	
	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	@Column(name = "FIRST_CHECK")
	private String FIRST_CHECK;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Transient
	private String exam1;

	@Transient
	private String exam2;

	@Transient
	private String exam3;
	

	public String getFIRST_CHECK() {
		return FIRST_CHECK;
	}

	public void setFIRST_CHECK(String fIRST_CHECK) {
		FIRST_CHECK = fIRST_CHECK;
	}

	public String getExam1() {
		return exam1;
	}

	public void setExam1(String exam1) {
		this.exam1 = exam1;
	}

	public String getExam2() {
		return exam2;
	}

	public void setExam2(String exam2) {
		this.exam2 = exam2;
	}

	public String getExam3() {
		return exam3;
	}

	public void setExam3(String exam3) {
		this.exam3 = exam3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
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

	public Date getORDER_DATE() {
		return ORDER_DATE;
	}

	public void setORDER_DATE(Date oRDER_DATE) {
		ORDER_DATE = oRDER_DATE;
	}

	public String getEXAM_ORDER() {
		return EXAM_ORDER;
	}

	public void setEXAM_ORDER(String eXAM_ORDER) {
		EXAM_ORDER = eXAM_ORDER;
	}

	public String getORDER_REMARK() {
		return ORDER_REMARK;
	}

	public void setORDER_REMARK(String oRDER_REMARK) {
		ORDER_REMARK = oRDER_REMARK;
	}

	
	
}
