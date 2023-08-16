package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Fpo_Item_Query {

	@Id
	private String id;
	private String item_no;
	private String item_id;
	private String item_desc;
	private String cin_no;
	private String gen_cth;
	private String decl_val;
	private String assess_val;
	private String qry;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public String getCin_no() {
		return cin_no;
	}
	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}
	public String getGen_cth() {
		return gen_cth;
	}
	public void setGen_cth(String gen_cth) {
		this.gen_cth = gen_cth;
	}
	public String getDecl_val() {
		return decl_val;
	}
	public void setDecl_val(String decl_val) {
		this.decl_val = decl_val;
	}
	public String getAssess_val() {
		return assess_val;
	}
	public void setAssess_val(String assess_val) {
		this.assess_val = assess_val;
	}
	public String getQry() {
		return qry;
	}
	public void setQry(String qry) {
		this.qry = qry;
	}  
	
	
	
}
