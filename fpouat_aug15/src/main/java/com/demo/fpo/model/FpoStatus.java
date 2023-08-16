package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_STATUS")
public class FpoStatus {

	@Id
	@GenericGenerator(name = "FPO_STATUS_SEQ", strategy = "com.seq.gen.FpoStatusIdGenerator")
	@GeneratedValue(generator = "FPO_STATUS_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "ACL_ID")
	private String aclId;
	
	@Column(name = "ACL_DT")
	private Date aclDt;
	
	@Column(name = "APR_ID")
	private String aprId;
	
	@Column(name = "APR_DT")
	private Date aprDt;
	
	@Column(name = "CIN_NO")
	private String cinNo;
	
	@Column(name = "ASS_DT")
	private Date assDt;
	
	@Column(name = "CLOSE_ID")
	private String closeId;
	
	@Column(name = "CLOSE_DT")
	private Date closeDt;
	
	@Column(name = "CIN_DT")
	private Date cinDt;
	
	@Column(name = "CUS_SITE")
	private String cuSite;
	
	@Column(name = "DELVY_DT")
	private Date delvyDt;
	
	@Column(name = "DELVY_ID")
	private String dlvryId;
	
	@Column(name = "ARR_INDIA_DT")
	private Date arrIndiaDt;
	
	@Column(name = "ARR_FPO_DT")
	private Date arrFPODt;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "OOC_DT")
	private Date oocDt;
	
	@Column(name = "MESG_TYPE_CD")
	private String msgTypeCd;
	
	@Column(name = "PAY_DT")
	private Date payDt;
	
	@Column(name = "INS_ID")
	private String insId;
	
	@Column(name = "POSTINGDT")
	private String pstDt;
	
	@Column(name = "REMARKS")
	private String rmks;
	
	@Column(name = "INS_DT")
	private Date insDt;
	
	@Column(name = "STATUS_CUS")
	private String statusCus;
	
	@Column(name = "STATUS_FPO")
	private String statusFpo;
	
	@Column(name = "SUP_DT")
	private Date supDt;
	
	@Column(name = "SUP_ID")
	private String supId;

	@Column(name = "CUSITM_DT") 
	private Date cusitmdt;
	
	
	

	public Date getCusitmdt() {
		return cusitmdt;
	}

	public void setCusitmdt(Date date) {
		this.cusitmdt = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAclId() {
		return aclId;
	}

	public void setAclId(String aclId) {
		this.aclId = aclId;
	}

	public Date getAclDt() {
		return aclDt;
	}

	public void setAclDt(Date aclDt) {
		this.aclDt = aclDt;
	}

	public String getAprId() {
		return aprId;
	}

	public void setAprId(String aprId) {
		this.aprId = aprId;
	}

	public Date getAprDt() {
		return aprDt;
	}

	public void setAprDt(Date aprDt) {
		this.aprDt = aprDt;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public Date getAssDt() {
		return assDt;
	}

	public void setAssDt(Date assDt) {
		this.assDt = assDt;
	}

	public String getCloseId() {
		return closeId;
	}

	public void setCloseId(String closeId) {
		this.closeId = closeId;
	}

	public Date getCloseDt() {
		return closeDt;
	}

	public void setCloseDt(Date closeDt) {
		this.closeDt = closeDt;
	}

	public Date getCinDt() {
		return cinDt;
	}

	public void setCinDt(Date cinDt) {
		this.cinDt = cinDt;
	}

	public String getCuSite() {
		return cuSite;
	}

	public void setCuSite(String cuSite) {
		this.cuSite = cuSite;
	}

	public Date getDelvyDt() {
		return delvyDt;
	}

	public void setDelvyDt(Date delvyDt) {
		this.delvyDt = delvyDt;
	}

	public String getDlvryId() {
		return dlvryId;
	}

	public void setDlvryId(String dlvryId) {
		this.dlvryId = dlvryId;
	}

	public Date getArrIndiaDt() {
		return arrIndiaDt;
	}

	public void setArrIndiaDt(Date arrIndiaDt) {
		this.arrIndiaDt = arrIndiaDt;
	}

	public Date getArrFPODt() {
		return arrFPODt;
	}

	public void setArrFPODt(Date arrFPODt) {
		this.arrFPODt = arrFPODt;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Date getOocDt() {
		return oocDt;
	}

	public void setOocDt(Date oocDt) {
		this.oocDt = oocDt;
	}

	public String getMsgTypeCd() {
		return msgTypeCd;
	}

	public void setMsgTypeCd(String msgTypeCd) {
		this.msgTypeCd = msgTypeCd;
	}

	public Date getPayDt() {
		return payDt;
	}

	public void setPayDt(Date payDt) {
		this.payDt = payDt;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getPstDt() {
		return pstDt;
	}

	public void setPstDt(String pstDt) {
		this.pstDt = pstDt;
	}

	public String getRmks() {
		return rmks;
	}

	public void setRmks(String rmks) {
		this.rmks = rmks;
	}

	public Date getInsDt() {
		return insDt;
	}

	public void setInsDt(Date insDt) {
		this.insDt = insDt;
	}

	public String getStatusCus() {
		return statusCus;
	}

	public void setStatusCus(String statusCus) {
		this.statusCus = statusCus;
	}

	public String getStatusFpo() {
		return statusFpo;
	}

	public void setStatusFpo(String statusFpo) {
		this.statusFpo = statusFpo;
	}

	public Date getSupDt() {
		return supDt;
	}

	public void setSupDt(Date supDt) {
		this.supDt = supDt;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}
	
}
