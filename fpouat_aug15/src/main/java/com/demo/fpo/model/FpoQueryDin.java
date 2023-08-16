package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_QRY_DIN")
public class FpoQueryDin {

	@Id
	@GenericGenerator(name = "FPO_QRY_DIN_SEQ", strategy = "com.seq.gen.FPO_QRY_DIN_SEQIdGenerator")
	@GeneratedValue(generator = "FPO_QRY_DIN_SEQ")
	@Column(name = "FPO_QRY_ID")
	public Long id;

	@Column(name = "CIN_NO")
	private String cinNo;

	@Column(name = "ITEM_ID")
	private String itemid;

	@Column(name = "CUS_SITE")
	private String cusSite;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "DIN1")
	private String din1;

	@Column(name = "DIN2")
	private String din2;

	@Column(name = "DEP_COMMENTS")
	private String depComments;

	@Column(name = "DEP_CMTS_ACL")
	private String depCmtsAcl;

	@Column(name = "DIN_SERIAL_NO")
	public Long dinSerialNumber;

	@Column(name = "UNIQUE_NO")
	public String uniqueNo;

	@Column(name = "GENERAL_QRY_REPLY")
	public String generalQryReply;

	public String getGeneralQryReply() {
		return generalQryReply;
	}

	public void setGeneralQryReply(String generalQryReply) {
		this.generalQryReply = generalQryReply;
	}

	public String getDepCmtsAcl() {
		return depCmtsAcl;
	}

	public void setDepCmtsAcl(String depCmtsAcl) {
		this.depCmtsAcl = depCmtsAcl;
	}

	public String getUniqueNo() {
		return uniqueNo;
	}

	public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public Long getDinSerialNumber() {
		return dinSerialNumber;
	}

	public void setDinSerialNumber(Long dinSerialNumber) {
		this.dinSerialNumber = dinSerialNumber;
	}

	public String getDepComments() {
		return depComments;
	}

	public void setDepComments(String depComments) {
		this.depComments = depComments;
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

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getCusSite() {
		return cusSite;
	}

	public void setCusSite(String cusSite) {
		this.cusSite = cusSite;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDin1() {
		return din1;
	}

	public void setDin1(String din1) {
		this.din1 = din1;
	}

	public String getDin2() {
		return din2;
	}

	public void setDin2(String din2) {
		this.din2 = din2;
	}

}
