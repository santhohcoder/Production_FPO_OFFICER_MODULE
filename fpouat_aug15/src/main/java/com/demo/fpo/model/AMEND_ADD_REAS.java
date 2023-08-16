package com.demo.fpo.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AMEND_ADD_REAS")
public class AMEND_ADD_REAS {
	
	@Id
	@GenericGenerator(name = "AMEND_ADD_SEQ", strategy = "com.seq.gen.Amend_Add_Reas_IDGenerator")
	@GeneratedValue(generator = "AMEND_ADD_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "CIN_NO")
	private String Cin_no;
	
	@Column(name = "ITEM_ID")
	private String Item_Id;
	
	@Column(name = "REASONS")
	private String Reasons;

	@Column(name = "AMEND_ITEM")
	private String Amend_Item;
	
	@Column(name = "ADD_ITEM")
	private String Add_Item;
	
	@Column(name = "ITEM_NO")
	private Long Item_No;

	@Column(name = "TASK_DT")
	private Date Task_dt;

	@Column(name = "ROLE")
	private String role;
	
	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "OFF_ID")
	private String off_id;

	public String getCin_no() {
		return Cin_no;
	}

	public void setCin_no(String cin_no) {
		Cin_no = cin_no;
	}

	public String getItem_Id() {
		return Item_Id;
	}

	public void setItem_Id(String item_Id) {
		Item_Id = item_Id;
	}

	public String getReasons() {
		return Reasons;
	}

	public void setReasons(String reasons) {
		Reasons = reasons;
	}

	public String getAmend_Item() {
		return Amend_Item;
	}

	public void setAmend_Item(String amend_Item) {
		Amend_Item = amend_Item;
	}

	public String getAdd_Item() {
		return Add_Item;
	}

	public void setAdd_Item(String add_Item) {
		Add_Item = add_Item;
	}

	public Long getItem_No() {
		return Item_No;
	}

	public void setItem_No(Long item_No) {
		Item_No = item_No;
	}

	public Date getTask_dt() {
		return Task_dt;
	}

	public void setTask_dt(Date task_dt) {
		Task_dt = task_dt;
	}

	
	public String getOff_id() {
		return off_id;
	}

	public void setOff_id(String off_id) {
		this.off_id = off_id;
	}


	
}
