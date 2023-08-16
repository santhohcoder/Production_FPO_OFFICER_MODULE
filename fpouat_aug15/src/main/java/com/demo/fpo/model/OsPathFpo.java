package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ospath_fpo")
public class OsPathFpo {
	@Id
	@GenericGenerator(name = "ospathfpo_seq", strategy = "com.seq.gen.OsPathFpoSeqIdGenerator")
	@GeneratedValue(generator = "ospathfpo_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "dcall_qry")
	private String dcallQry;

	@Column(name = "dcall_qry_reply")
	private String dcallQryReply;

	@Column(name = "ooc_info")
	private String oocInfo;

	@Column(name = "images_path")
	private String imagesPath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDcallQry() {
		return dcallQry;
	}

	public void setDcallQry(String dcallQry) {
		this.dcallQry = dcallQry;
	}

	public String getDcallQryReply() {
		return dcallQryReply;
	}

	public void setDcallQryReply(String dcallQryReply) {
		this.dcallQryReply = dcallQryReply;
	}

	public String getOocInfo() {
		return oocInfo;
	}

	public void setOocInfo(String oocInfo) {
		this.oocInfo = oocInfo;
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

}
