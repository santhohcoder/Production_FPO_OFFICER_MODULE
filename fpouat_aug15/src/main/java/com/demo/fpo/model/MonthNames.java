package com.demo.fpo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MonthNames {
	
	@Id
	private String monthno;
	private String monthname;
	
	
	/**
	 * @return the monthno
	 */
	public String getMonthno() {
		return monthno;
	}
	/**
	 * @param monthno the monthno to set
	 */
	public void setMonthno(String monthno) {
		this.monthno = monthno;
	}
	/**
	 * @return the monthname
	 */
	public String getMonthname() {
		return monthname;
	}
	/**
	 * @param monthname the monthname to set
	 */
	public void setMonthname(String monthname) {
		this.monthname = monthname;
	}
	
	
	

}
