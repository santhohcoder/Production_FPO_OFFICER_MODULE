package com.demo.fpo.apimodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Reason_cd")
public class Reasoncd  {
	@Id
//	@Column(name = "REAS_CD",columnDefinition="serial") 
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "REAS_CD")
	@GenericGenerator(name = "Reason_cd_ID", strategy = "com.seq.gen.Reason_cdIdGenerator")
	@GeneratedValue(generator = "Reason_cd_ID")
	private Long REAS_CD;
	
	public Long getREAS_CD() {
		return REAS_CD;
	}

	public void setREAS_CD(Long rEAS_CD) {
		REAS_CD = rEAS_CD;
	}

	public String getREAS_NM() {
		return REAS_NM;
	}

	public void setREAS_NM(String rEAS_NM) {
		REAS_NM = rEAS_NM;
	}

	@Column(name = "REAS_NM")
	private String REAS_NM;
}

