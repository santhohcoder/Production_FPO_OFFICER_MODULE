package com.demo.fpo.apimodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "deci_reas")
public class DeciReas {
	@Id
//	@GeneratedValue(generator = "idSequence", strategy=GenerationType.SEQUENCE)
//	@SequenceGenerator(schema = "ICES_FPO", name = "idSequence", sequenceName = "fpoqrydeciseq", initialValue = 5, allocationSize = 1)
	@GenericGenerator(name = "Deci_reas_ID", strategy = "com.seq.gen.Deci_reas_SEQIdGenerator")
	@GeneratedValue(generator = "Deci_reas_ID")
	@Column(name = "DECI_CD") 
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long DECI_CD;
	
	@Column(name = "DECI_NM")
	private String DECI_NM;

	public Long getDECI_CD() {
		return DECI_CD;
	}

	public void setDECI_CD(Long dECI_CD) {
		DECI_CD = dECI_CD;
	}

	public String getDECI_NM() {
		return DECI_NM;
	}

	public void setDECI_NM(String dECI_NM) {
		DECI_NM = dECI_NM;
	}
	
}
