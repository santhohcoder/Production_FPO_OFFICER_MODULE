package com.demo.fpo.apibean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FromandToDate {
	

	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date FromDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ToDate;
	public Date getFromDate() {
		return FromDate;
	}
	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}
	public Date getToDate() {
		return ToDate;
	}
	public void setToDate(Date toDate) {
		ToDate = toDate;
	}
	
	

}
