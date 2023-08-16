package com.demo.fpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DECI_REAS")
public class DECI_REAS {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REAS_SEQ")
	@SequenceGenerator(name = "REAS_SEQ", sequenceName = "REAS_SEQ", initialValue = 1)
	@Column(name = "DECI_CD")
	public Long id;

	@Column(name = "DECI_NM")
	private String DECI_NM;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDECI_NM() {
		return DECI_NM;
	}

	public void setDECI_NM(String dECI_NM) {
		DECI_NM = dECI_NM;
	}

}
