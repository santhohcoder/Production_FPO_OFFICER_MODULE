package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Feedback {
	@Id
	@GenericGenerator(name = "FEEDBACK_ID", strategy = "com.seq.gen.FEEDBACK_SEQIdGenerator")
	@GeneratedValue(generator = "FEEDBACK_ID")
	@Column(name = "ID")
	public Long id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private long off_id;
	private String feedback_type;
	private Date feedback_Dt;
	private String feedback_Text;
	private String name;
	private String dcall_no;
	public long getOff_id() {
		return off_id;
	}
	public void setOff_id(long off_id) {
		this.off_id = off_id;
	}
	public String getFeedback_type() {
		return feedback_type;
	}
	public void setFeedback_type(String feedback_type) {
		this.feedback_type = feedback_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDcall_no() {
		return dcall_no;
	}
	public void setDcall_no(String dcall_no) {
		this.dcall_no = dcall_no;
	}
	public Date getFeedback_Dt() {
		return feedback_Dt;
	}
	public void setFeedback_Dt(Date feedback_Dt) {
		this.feedback_Dt = feedback_Dt;
	}
	public String getFeedback_Text() {
		return feedback_Text;
	}
	public void setFeedback_Text(String feedback_Text) {
		this.feedback_Text = feedback_Text;
	}
	
	

}
