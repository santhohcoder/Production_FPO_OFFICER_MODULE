package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DCALLQRY_GEN")
public class DCALLQRY_GEN {
	@Id
	@GenericGenerator(name = "DCALLQRY_SEQ", strategy = "com.seq.gen.DCALL_SEQIdGenerator")
	@GeneratedValue(generator = "DCALLQRY_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "ITEM_ID")
	private String Item_ID;
	
	public String getItem_ID() {
		return Item_ID;
	}

	public void setItem_ID(String item_ID) {
		Item_ID = item_ID;
	}

	public String getCinno() {
		return Cinno;
	}

	public void setCinno(String cinno) {
		Cinno = cinno;
	}

	public String getCussite() {
		return Cussite;
	}

	public void setCussite(String cussite) {
		Cussite = cussite;
	}

	public String getDcallno() {
		return dcallno;
	}

	public void setDcallno(String dcallno) {
		this.dcallno = dcallno;
	}

	public String getGenurl() {
		return Genurl;
	}

	public void setGenurl(String genurl) {
		Genurl = genurl;
	}

	public String getRecp_name() {
		return Recp_name;
	}

	public void setRecp_name(String recp_name) {
		Recp_name = recp_name;
	}

    @Column(name="MAIL_TO")
    private String Mail_to;
    
    @Column(name="MAIL_CC")
    private String Mail_cc;
    
    @Column(name="MAIL_SENT_DT")
    private Date Mail_sentdt;

    @Column(name="GEN_FILENAME")
    private String Gen_filename;
    
    @Column(name="MOBILE_NO_1")
    private String Mobile_1;
    
    @Column(name="MOBILE_NO_2")
    private String Mobile_2;
    

    @Column(name="VIEWCOU")
    private String viewcou;
    

    @Column(name="PRINTCOU")
    private String printcou;
    

    @Column(name="SMSCOU")
    private String smscou;
    

    @Column(name="EMAILCOU")
    private String emailcou;
    

    @Column(name="SPEEDPOST_NO")
    private String speedpost_no;
    

    @Column(name="SPEEDPOST_DT")
    private Date speedpost_dt;
    

    @Column(name="SPEEDPOST_DELI_STATUS")
    private String speedpost_deli_status;
    
    
    

	public String getSpeedpost_deli_status() {
		return speedpost_deli_status;
	}

	public void setSpeedpost_deli_status(String speedpost_deli_status) {
		this.speedpost_deli_status = speedpost_deli_status;
	}

	public String getSpeedpost_no() {
		return speedpost_no;
	}

	public void setSpeedpost_no(String speedpost_no) {
		this.speedpost_no = speedpost_no;
	}

	public Date getSpeedpost_dt() {
		return speedpost_dt;
	}

	public void setSpeedpost_dt(Date speedpost_dt) {
		this.speedpost_dt = speedpost_dt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getViewcou() {
		return viewcou;
	}

	public void setViewcou(String viewcou) {
		this.viewcou = viewcou;
	}

	public String getPrintcou() {
		return printcou;
	}

	public void setPrintcou(String printcou) {
		this.printcou = printcou;
	}

	public String getSmscou() {
		return smscou;
	}

	public void setSmscou(String smscou) {
		this.smscou = smscou;
	}

	public String getEmailcou() {
		return emailcou;
	}

	public void setEmailcou(String emailcou) {
		this.emailcou = emailcou;
	}

	public String getMobile_1() {
		return Mobile_1;
	}

	public void setMobile_1(String mobile_1) {
		Mobile_1 = mobile_1;
	}

	public String getMobile_2() {
		return Mobile_2;
	}

	public void setMobile_2(String mobile_2) {
		Mobile_2 = mobile_2;
	}

	public String getMail_to() {
		return Mail_to;
	}

	public void setMail_to(String mail_to) {
		Mail_to = mail_to;
	}

	public String getMail_cc() {
		return Mail_cc;
	}

	public void setMail_cc(String mail_cc) {
		Mail_cc = mail_cc;
	}

	public Date getMail_sentdt() {
		return Mail_sentdt;
	}

	public void setMail_sentdt(Date mail_sentdt) {
		Mail_sentdt = mail_sentdt;
	}

	public String getGen_filename() {
		return Gen_filename;
	}

	public void setGen_filename(String gen_filename) {
		Gen_filename = gen_filename;
	}

	public Date getGen_dt() {
		return Gen_dt;
	}

	public void setGen_dt(Date gen_dt) {
		Gen_dt = gen_dt;
	}



	@Column(name = "CIN_NO")
	private String Cinno;
	
	@Column(name = "CUS_SITE")
	private String Cussite;
	
	@Column(name = "DCALL_NO")
	private String dcallno;
	
	@Column(name = "GENURL")
	private String Genurl;
	
	@Column(name = "RECP_NAME")
	private String Recp_name;
	
	@Column(name = "GEN_DT")
	private Date Gen_dt;
}