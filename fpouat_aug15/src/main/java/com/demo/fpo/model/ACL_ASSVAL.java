package com.demo.fpo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.demo.fpo.bean.DocumentSection;

@Entity
@Table(name = "ops$dir.ACL_ASSVAL")
public class ACL_ASSVAL {

	@Id

	@Column(name="MAXASSVAL")
	private int Maxassval;
	
	@Column(name="CREATED_BY")
	private String Createdby;
	
	@Column(name="FROM_DT")
	private Date Fromdt;
	
	@Column(name ="TO_DT")
	private Date Todt;
	
	@Column(name="REASON")
	private String Reason;
	
	@Column(name="DOC_NAME")
	private String Docname;
	
	public String getDocname() {
		return Docname;
	}

	public void setDocname(String docname) {
		Docname = docname;
	}

	public int getMaxassval() {
		return Maxassval;
	}

	public void setMaxassval(int maxassval) {
		Maxassval = maxassval;
	}


	public String getCreatedby() {
		return Createdby;
	}

	public void setCreatedby(String createdby) {
		Createdby = createdby;
	}

	public Date getFromdt() {
		return Fromdt;
	}

	public void setFromdt(Date date1) {
		Fromdt = date1;
	}

	public Date getTodt() {
		return Todt;
	}

	public void setTodt(Date date1) {
		Todt = date1;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}
	
	@Transient
	private MultipartFile filename;

	public MultipartFile getFilename() {
		return filename;
	}

	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}
	

	private String DOC_PATH;

	public String getDOC_PATH() {
		return DOC_PATH;
	}

	public void setDOC_PATH(String dOC_PATH) {
		DOC_PATH = dOC_PATH;
	}	
	
	
	
}
