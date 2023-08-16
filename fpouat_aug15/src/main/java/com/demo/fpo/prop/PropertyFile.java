package com.demo.fpo.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyFile {
	
	@Value("${com.demo.fpo.main.table.PDI_HANDLING_CLASS_CODES}")
	public String PDI_HANDLING_CLASS_CODES;
	
	@Value("${com.demo.fpo.main.table.PDI_NATURE_TRANS_CODES}")
	public String PDI_NATURE_TRANS_CODES;
	
	@Value("${com.fpo.defualt.query.one}")
	public String queryOne;
	
	@Value("${com.fpo.defualt.query.two}")
	public String queryTwo;
	
	@Value("${com.fpo.defualt.query.three}")
	public String queryThree;
	
	public String getQueryOne() {
		return queryOne;
	}

	public void setQueryOne(String queryOne) {
		this.queryOne = queryOne;
	}

	public String getQueryTwo() {
		return queryTwo;
	}

	public void setQueryTwo(String queryTwo) {
		this.queryTwo = queryTwo;
	}

	public String getQueryThree() {
		return queryThree;
	}

	public void setQueryThree(String queryThree) {
		this.queryThree = queryThree;
	}

	public String getPDI_NATURE_TRANS_CODES() {
		return PDI_NATURE_TRANS_CODES;
	}

	public void setPDI_NATURE_TRANS_CODES(String pDI_NATURE_TRANS_CODES) {
		PDI_NATURE_TRANS_CODES = pDI_NATURE_TRANS_CODES;
	}

	@Value("${com.demo.fpo.main.column.interpretation}")
	public String interpretation;

	public String getPDI_HANDLING_CLASS_CODES() {
		return PDI_HANDLING_CLASS_CODES;
	}

	public void setPDI_HANDLING_CLASS_CODES(String pDI_HANDLING_CLASS_CODES) {
		PDI_HANDLING_CLASS_CODES = pDI_HANDLING_CLASS_CODES;
	}

	public String getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}
}
