package com.demo.fpo.apimodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "C_CUSRSP_ITEM_DET")
public class cusrspitemdet {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
//	@GeneratedValue(generator = "idSequence4", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "idSequence4", sequenceName = "CUSRSP_ITEM_ID", initialValue = 5, allocationSize = 1)
	@GenericGenerator(name = "C_CUSRSP_ITEM_DET_ID", strategy = "com.seq.gen.C_CUSRSP_ITEM_DET_IdGenerator")
	@GeneratedValue(generator = "C_CUSRSP_ITEM_DET_ID")
	@Column(name="CUSRSP_ID")
	public Long CUSRSP_ID;
	
	@Column(name = "CIN_NO")
	private String CIN_NO;
	
	@Column(name = "ITEM_ID")
	private String ITEM_ID;
	
	@Column(name = "ITEM_NO")
	private Long ITEM_NO;
	
	@Column(name = "CUS_SITE")
	private String CUS_SITE;
	
	@Column(name = "SENT_DT")
	private java.sql.Date sqlDate;
	
	public Long getCUSRSP_ID() {
		return CUSRSP_ID;
	}

	public void setCUSRSP_ID(Long cUSRSP_ID) {
		CUSRSP_ID = cUSRSP_ID;
	}

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(java.sql.Date sqlDate) {
		this.sqlDate = sqlDate;
	}

	public String getCUS_SITE() {
		return CUS_SITE;
	}

	public void setCUS_SITE(String cUS_SITE) {
		CUS_SITE = cUS_SITE;
	}

	public Long getNOU() {
		return NOU;
	}

	public Long getITEM_NO() {
		return ITEM_NO;
	}

	public void setITEM_NO(Long iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}

	@Column(name = "POSTINGDT")
	private String POSTINGDT;
	
	@Column(name = "ENTITY_STATE")
	private int ENTITY_STATE;
	
	@Column(name = "DECL_VAL")
	private BigDecimal DECL_VAL;
	
	@Column(name = "CP_CURRCD")
	private String CP_CURRCD;
	
	@Column(name = "CP_DESC")
	private String CP_DESC;
	
	@Column(name = "CP_HS")
	private String CP_HS;
	
	@Column(name = "NETWT")
	private BigDecimal NET_WT;
	
	@Column(name = "NOU")
	private Long NOU;

	
	public void setNOU(Long nOU) {
		NOU = nOU;
	}

	public String getCIN_NO() {
		return CIN_NO;
	}

	public void setCIN_NO(String cIN_NO) {
		CIN_NO = cIN_NO;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getPOSTINGDT() {
		return POSTINGDT;
	}

	public void setPOSTINGDT(String pOSTINGDT) {
		POSTINGDT = pOSTINGDT;
	}

	public int getENTITY_STATE() {
		return ENTITY_STATE;
	}

	public void setENTITY_STATE(int eNTITY_STATE) {
		ENTITY_STATE = eNTITY_STATE;
	}

	public BigDecimal getDECL_VAL() {
		return DECL_VAL;
	}

	public void setDECL_VAL(BigDecimal dECL_VAL) {
		DECL_VAL = dECL_VAL;
	}

	public String getCP_CURRCD() {
		return CP_CURRCD;
	}

	public void setCP_CURRCD(String cP_CURRCD) {
		CP_CURRCD = cP_CURRCD;
	}

	public String getCP_DESC() {
		return CP_DESC;
	}

	public void setCP_DESC(String cP_DESC) {
		CP_DESC = cP_DESC;
	}

	public String getCP_HS() {
		return CP_HS;
	}

	public void setCP_HS(String cP_HS) {
		CP_HS = cP_HS;
	}

	public BigDecimal getNET_WT() {
		return NET_WT;
	}

	public void setNET_WT(BigDecimal nET_WT) {
		NET_WT = nET_WT;
	}

	
}
