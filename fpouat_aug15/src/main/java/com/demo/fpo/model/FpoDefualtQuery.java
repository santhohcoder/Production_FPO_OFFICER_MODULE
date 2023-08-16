package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FPO_DEFAULT_QUERY")
public class FpoDefualtQuery implements Comparable<FpoDefualtQuery> {

	
	@Id
	@GenericGenerator(name = "DEFAULT_QUERY_SEQ", strategy = "com.seq.gen.DEFAULT_QUERY_SEQIdGenerator")
	@GeneratedValue(generator = "DEFAULT_QUERY_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@Column(name = "SERIAL_NO")
	private Long SERIAL_NO;

	@Column(name = "DEFAULT_QUERY")
	private String DEFAULT_QUERY;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSERIAL_NO() {
		return SERIAL_NO;
	}

	public void setSERIAL_NO(Long sERIAL_NO) {
		SERIAL_NO = sERIAL_NO;
	}

	public String getDEFAULT_QUERY() {
		return DEFAULT_QUERY;
	}

	public void setDEFAULT_QUERY(String dEFAULT_QUERY) {
		DEFAULT_QUERY = dEFAULT_QUERY;
	}
	

	  @Override
	  public int compareTo(FpoDefualtQuery u) {
	    if (getId() == null || u.getId() == null) {
	      return 0;
	    }
	    return getId().compareTo(u.getId());
	  }
	
	
}
