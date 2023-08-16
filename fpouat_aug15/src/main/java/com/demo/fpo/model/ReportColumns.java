package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportColumns {
	@Id
	private String id;
	private String cusite;
	private String item_id;
	private String post_dt;
	private String coo;
	private String sender_name;
	private String sender_addr;
	private String recp_name;
	private String rec_addr;
	private String recp_state;
	private String recp_zip;
	private String deci_nm;
	private String mail_class;
	private String mail_category;
	private String duty;
	private String tot_amt_payable;
	private String fine_amt;
	private String penal_amt;
	private String cth;
	private String item_desc;
	private String item_vlue;
	private String item_no;
	private String qry;
	private String rply;
	private String arriaval_date;
	private String examination;
	private String ooc_dt;
	private String delivery_date;
	private String QRY_DT;
	private String replyip;
	private String destfpo;
	private String respedi;
	private String job_no;
	private String detain_dt;
	private String gross_wt;
	private String examfind;
	private String cn23;
	private String del_dt;
	private String arr_dt;
	private String exam_dt;
	private String deliverystatus;
	private String rms;
	private String assessedval;
	private String revised_code;
	private String curr_cd;
	private String ex_rate;
	private String recp_id;
	private String destfpo_dt;
	private String bag_no;
	private String rlytime;
	private String dcallno;
	private String dcall_dt;
	private String speedpostno;
	private String speedpostdelstatus;
	
	
	
	
	
	
	public String getCusite() {
		return cusite;
	}
	public void setCusite(String cusite) {
		this.cusite = cusite;
	}
	
	
	public String getRecp_id() {
		return recp_id;
	}
	public void setRecp_id(String recp_id) {
		this.recp_id = recp_id;
	}
	public String getDestfpo_dt() {
		return destfpo_dt;
	}
	public void setDestfpo_dt(String destfpo_dt) {
		this.destfpo_dt = destfpo_dt;
	}
	public String getBag_no() {
		return bag_no;
	}
	public void setBag_no(String bag_no) {
		this.bag_no = bag_no;
	}
	public String getRlytime() {
		return rlytime;
	}
	public void setRlytime(String rlytime) {
		this.rlytime = rlytime;
	}
	public String getDcallno() {
		return dcallno;
	}
	public void setDcallno(String dcallno) {
		this.dcallno = dcallno;
	}
	public String getDcall_dt() {
		return dcall_dt;
	}
	public void setDcall_dt(String dcall_dt) {
		this.dcall_dt = dcall_dt;
	}
	public String getSpeedpostno() {
		return speedpostno;
	}
	public void setSpeedpostno(String speedpostno) {
		this.speedpostno = speedpostno;
	}
	public String getSpeedpostdelstatus() {
		return speedpostdelstatus;
	}
	public void setSpeedpostdelstatus(String speedpostdelstatus) {
		this.speedpostdelstatus = speedpostdelstatus;
	}
	/**
	 * @return the curr_cd
	 */
	public String getCurr_cd() {
		return curr_cd;
	}
	/**
	 * @param curr_cd the curr_cd to set
	 */
	public void setCurr_cd(String curr_cd) {
		this.curr_cd = curr_cd;
	}
	/**
	 * @return the ex_rate
	 */
	public String getEx_rate() {
		return ex_rate;
	}
	/**
	 * @param ex_rate the ex_rate to set
	 */
	public void setEx_rate(String ex_rate) {
		this.ex_rate = ex_rate;
	}
	/**
	 * @return the assessedval
	 */
	public String getAssessedval() {
		return assessedval;
	}
	/**
	 * @param assessedval the assessedval to set
	 */
	public void setAssessedval(String assessedval) {
		this.assessedval = assessedval;
	}
	/**
	 * @return the revised_code
	 */
	public String getRevised_code() {
		return revised_code;
	}
	/**
	 * @param revised_code the revised_code to set
	 */
	public void setRevised_code(String revised_code) {
		this.revised_code = revised_code;
	}
	/**
	 * @return the rms
	 */
	public String getRms() {
		return rms;
	}
	/**
	 * @param rms the rms to set
	 */
	public void setRms(String rms) {
		this.rms = rms;
	}
	/**
	 * @return the deliverystatus
	 */
	public String getDeliverystatus() {
		return deliverystatus;
	}
	/**
	 * @param deliverystatus the deliverystatus to set
	 */
	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}
	/**
	 * @return the del_dt
	 */
	public String getDel_dt() {
		return del_dt;
	}
	/**
	 * @param del_dt the del_dt to set
	 */
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	/**
	 * @return the arr_dt
	 */
	public String getArr_dt() {
		return arr_dt;
	}
	/**
	 * @param arr_dt the arr_dt to set
	 */
	public void setArr_dt(String arr_dt) {
		this.arr_dt = arr_dt;
	}
	/**
	 * @return the exam_dt
	 */
	public String getExam_dt() {
		return exam_dt;
	}
	/**
	 * @param exam_dt the exam_dt to set
	 */
	public void setExam_dt(String exam_dt) {
		this.exam_dt = exam_dt;
	}
	/**
	 * @return the examfind
	 */
	public String getExamfind() {
		return examfind;
	}
	/**
	 * @param examfind the examfind to set
	 */
	public void setExamfind(String examfind) {
		this.examfind = examfind;
	}
	/**
	 * @return the cn23
	 */
	public String getCn23() {
		return cn23;
	}
	/**
	 * @param cn23 the cn23 to set
	 */
	public void setCn23(String cn23) {
		this.cn23 = cn23;
	}
	/**
	 * @return the gross_wt
	 */
	public String getGross_wt() {
		return gross_wt;
	}
	/**
	 * @param gross_wt the gross_wt to set
	 */
	public void setGross_wt(String gross_wt) {
		this.gross_wt = gross_wt;
	}
	/**
	 * @return the detain_dt
	 */
	public String getDetain_dt() {
		return detain_dt;
	}
	/**
	 * @param detain_dt the detain_dt to set
	 */
	public void setDetain_dt(String detain_dt) {
		this.detain_dt = detain_dt;
	}
	/**
	 * @return the job_no
	 */
	public String getJob_no() {
		return job_no;
	}
	/**
	 * @param job_no the job_no to set
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	/**
	 * @return the respedi
	 */
	public String getRespedi() {
		return respedi;
	}
	/**
	 * @param respedi the respedi to set
	 */
	public void setRespedi(String respedi) {
		this.respedi = respedi;
	}
	/**
	 * @return the destfpo
	 */
	public String getDestfpo() {
		return destfpo;
	}
	/**
	 * @param destfpo the destfpo to set
	 */
	public void setDestfpo(String destfpo) {
		this.destfpo = destfpo;
	}
	/**
	 * @return the replyip
	 */
	public String getReplyip() {
		return replyip;
	}
	/**
	 * @param replyip the replyip to set
	 */
	public void setReplyip(String replyip) {
		this.replyip = replyip;
	}
	/**
	 * @return the arriaval_date
	 */
	public String getArriaval_date() {
		return arriaval_date;
	}
	/**
	 * @param arriaval_date the arriaval_date to set
	 */
	public void setArriaval_date(String arriaval_date) {
		this.arriaval_date = arriaval_date;
	}
	/**
	 * @return the delivery_date
	 */
	public String getDelivery_date() {
		return delivery_date;
	}
	/**
	 * @param delivery_date the delivery_date to set
	 */
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	/**
	 * @return the item_no
	 */
	public String getItem_no() {
		return item_no;
	}
	/**
	 * @param item_no the item_no to set
	 */
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}
	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	/**
	 * @return the post_dt
	 */
	public String getPost_dt() {
		return post_dt;
	}
	/**
	 * @param post_dt the post_dt to set
	 */
	public void setPost_dt(String post_dt) {
		this.post_dt = post_dt;
	}
	/**
	 * @return the coo
	 */
	public String getCoo() {
		return coo;
	}
	/**
	 * @param coo the coo to set
	 */
	public void setCoo(String coo) {
		this.coo = coo;
	}
	/**
	 * @return the sender_name
	 */
	public String getSender_name() {
		return sender_name;
	}
	/**
	 * @param sender_name the sender_name to set
	 */
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	/**
	 * @return the sender_addr
	 */
	public String getSender_addr() {
		return sender_addr;
	}
	/**
	 * @param sender_addr the sender_addr to set
	 */
	public void setSender_addr(String sender_addr) {
		this.sender_addr = sender_addr;
	}
	/**
	 * @return the recp_name
	 */
	public String getRecp_name() {
		return recp_name;
	}
	/**
	 * @param recp_name the recp_name to set
	 */
	public void setRecp_name(String recp_name) {
		this.recp_name = recp_name;
	}
	/**
	 * @return the rec_addr
	 */
	public String getRec_addr() {
		return rec_addr;
	}
	/**
	 * @param rec_addr the rec_addr to set
	 */
	public void setRec_addr(String rec_addr) {
		this.rec_addr = rec_addr;
	}
	/**
	 * @return the recp_state
	 */
	public String getRecp_state() {
		return recp_state;
	}
	/**
	 * @param recp_state the recp_state to set
	 */
	public void setRecp_state(String recp_state) {
		this.recp_state = recp_state;
	}
	/**
	 * @return the recp_zip
	 */
	public String getRecp_zip() {
		return recp_zip;
	}
	/**
	 * @param recp_zip the recp_zip to set
	 */
	public void setRecp_zip(String recp_zip) {
		this.recp_zip = recp_zip;
	}
	/**
	 * @return the deci_nm
	 */
	public String getDeci_nm() {
		return deci_nm;
	}
	/**
	 * @param deci_nm the deci_nm to set
	 */
	public void setDeci_nm(String deci_nm) {
		this.deci_nm = deci_nm;
	}
	/**
	 * @return the mail_class
	 */
	public String getMail_class() {
		return mail_class;
	}
	/**
	 * @param mail_class the mail_class to set
	 */
	public void setMail_class(String mail_class) {
		this.mail_class = mail_class;
	}
	/**
	 * @return the mail_category
	 */
	public String getMail_category() {
		return mail_category;
	}
	/**
	 * @param mail_category the mail_category to set
	 */
	public void setMail_category(String mail_category) {
		this.mail_category = mail_category;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * @return the tot_amt_payable
	 */
	public String getTot_amt_payable() {
		return tot_amt_payable;
	}
	/**
	 * @param tot_amt_payable the tot_amt_payable to set
	 */
	public void setTot_amt_payable(String tot_amt_payable) {
		this.tot_amt_payable = tot_amt_payable;
	}
	/**
	 * @return the fine_amt
	 */
	public String getFine_amt() {
		return fine_amt;
	}
	/**
	 * @param fine_amt the fine_amt to set
	 */
	public void setFine_amt(String fine_amt) {
		this.fine_amt = fine_amt;
	}
	/**
	 * @return the penal_amt
	 */
	public String getPenal_amt() {
		return penal_amt;
	}
	/**
	 * @param penal_amt the penal_amt to set
	 */
	public void setPenal_amt(String penal_amt) {
		this.penal_amt = penal_amt;
	}
	/**
	 * @return the cth
	 */
	public String getCth() {
		return cth;
	}
	/**
	 * @param cth the cth to set
	 */
	public void setCth(String cth) {
		this.cth = cth;
	}
	/**
	 * @return the item_desc
	 */
	public String getItem_desc() {
		return item_desc;
	}
	/**
	 * @param item_desc the item_desc to set
	 */
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	/**
	 * @return the item_vlue
	 */
	public String getItem_vlue() {
		return item_vlue;
	}
	/**
	 * @param item_vlue the item_vlue to set
	 */
	public void setItem_vlue(String item_vlue) {
		this.item_vlue = item_vlue;
	}
	/**
	 * @return the qry
	 */
	public String getQry() {
		return qry;
	}
	/**
	 * @param qry the qry to set
	 */
	public void setQry(String qry) {
		this.qry = qry;
	}
	/**
	 * @return the rply
	 */
	public String getRply() {
		return rply;
	}
	/**
	 * @param rply the rply to set
	 */
	public void setRply(String rply) {
		this.rply = rply;
	}
	/**
	 * @return the examination
	 */
	public String getExamination() {
		return examination;
	}
	/**
	 * @param examination the examination to set
	 */
	public void setExamination(String examination) {
		this.examination = examination;
	}
	/**
	 * @return the ooc_dt
	 */
	public String getOoc_dt() {
		return ooc_dt;
	}
	/**
	 * @param ooc_dt the ooc_dt to set
	 */
	public void setOoc_dt(String ooc_dt) {
		this.ooc_dt = ooc_dt;
	}
	/**
	 * @return the qRY_DT
	 */
	public String getQRY_DT() {
		return QRY_DT;
	}
	/**
	 * @param qRY_DT the qRY_DT to set
	 */
	public void setQRY_DT(String qRY_DT) {
		QRY_DT = qRY_DT;
	}
	
	
	
	
	
	

}
