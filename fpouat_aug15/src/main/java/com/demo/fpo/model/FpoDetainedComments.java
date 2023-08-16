package com.demo.fpo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fpo_detained_comments")
public class FpoDetainedComments {
	@Id
	@GenericGenerator(name = "fpo_detained_comments_seq", strategy = "com.seq.gen.FpoDetainedCommentsSeqIdGenerator")
	@GeneratedValue(generator = "fpo_detained_comments_seq")
	@Column(name = "id")
	public Long id;

	@Column(name = "cin_no")
	private String cinNo;

	@Column(name = "seq_no")
	private Long seqNo;

	@Column(name = "item_id")
	private String itemId;

	@Column(name = "role")
	private String role;

	@Column(name = "off_id")
	private String officerId;

	@Column(name = "cmts")
	private String comments;

	@Column(name = "entry_dt")
	private Date entryDate;

	@Column(name = "detained_no")
	private Long detainedNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCinNo() {
		return cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Long getDetainedNo() {
		return detainedNo;
	}

	public void setDetainedNo(Long detainedNo) {
		this.detainedNo = detainedNo;
	}

}
