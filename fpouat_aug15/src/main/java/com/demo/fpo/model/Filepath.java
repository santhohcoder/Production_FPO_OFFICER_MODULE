package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Filepath {

	@Id
	private long id;
	private String dcall_qry;
	private String dcall_qry_rply;
	private String images_path;
	private String ooc_info;
	private String dcall_qry_view;
	private String examination_queue;
	private String detained_queue;
	private String ooc_cancel;
	
	private String oth_doc;

	public String getOth_doc() {
		return oth_doc;
	}

	public void setOth_doc(String oth_doc) {
		this.oth_doc = oth_doc;
	}



	public String getOoc_cancel() {
		return ooc_cancel;
	}

	public void setOoc_cancel(String ooc_cancel) {
		this.ooc_cancel = ooc_cancel;
	}

	public String getDetained_queue() {
		return detained_queue;
	}

	public void setDetained_queue(String detained_queue) {
		this.detained_queue = detained_queue;
	}

	public String getExamination_queue() {
		return examination_queue;
	}

	public void setExamination_queue(String examination_queue) {
		this.examination_queue = examination_queue;
	}

	public String getDcall_qry_view() {
		return dcall_qry_view;
	}

	public void setDcall_qry_view(String dcall_qry_view) {
		this.dcall_qry_view = dcall_qry_view;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDcall_qry() {
		return dcall_qry;
	}

	public void setDcall_qry(String dcall_qry) {
		this.dcall_qry = dcall_qry;
	}

	public String getDcall_qry_rply() {
		return dcall_qry_rply;
	}

	public void setDcall_qry_rply(String dcall_qry_rply) {
		this.dcall_qry_rply = dcall_qry_rply;
	}

	public String getImages_path() {
		return images_path;
	}

	public void setImages_path(String images_path) {
		this.images_path = images_path;
	}

	public String getOoc_info() {
		return ooc_info;
	}

	public void setOoc_info(String ooc_info) {
		this.ooc_info = ooc_info;
	}

}
