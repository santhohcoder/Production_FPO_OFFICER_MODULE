package com.demo.fpo.apimodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DELIresponseData {
	@JsonProperty("Count")
	int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	List<DELIfpodata> data;
	
		
	public List<DELIfpodata> getData() {
						return data;
	}

	public void setData(List<DELIfpodata> data) {
				this.data = data;
	}

}
