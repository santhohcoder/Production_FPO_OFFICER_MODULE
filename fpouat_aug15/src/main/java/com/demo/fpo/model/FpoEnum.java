package com.demo.fpo.model;

import java.util.stream.Stream;

public enum FpoEnum {

	Register("R"),Normal("N"),Insured("V"),Gift("31"),Sale_of_goods("11"),Mixed_content("991"),Returned_goods("21");   	  	
	
	private String typeOfFpo;	 
	FpoEnum(String typeOfFpo) {
        this.setTypeOfFpo(typeOfFpo);
    }	
    // standard getters and setters 
 
    public static Stream<FpoEnum> stream() {
        return Stream.of(FpoEnum.values()); 
    }

	public String getTypeOfFpo() {
		return typeOfFpo;
	}

	public void setTypeOfFpo(String typeOfFpo) {
		this.typeOfFpo = typeOfFpo;
	}
	
	

	
}
