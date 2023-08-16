package com.demo.fpo.utils;

public class QueryConstants {

	public static final String allCountries = "select row_number() over (order by cntry_nm) as id,cntry_cd as code,cntry_nm as name from ops$dir.d_cntry_cd";

	public static final String allMailCategories = "select row_number() over (order by code) as id,code,interpretation as name from ops$dir.pdi_mail_category_codes";

	public static final String allMailClasses = "select row_number() over (order by code) as id,code,interpretation as name from ops$dir.pdi_mail_class_codes";

	public static final String detainedCaseList = "select row_number() over (order by slno) as id,slNo,case_type as name from det_case_list";

	public static final String detainedMethodList = "select row_number() over (order by slno) as id,slNo,det_type as name from det_method_list";

	public static final String uqcList = "select row_number() over (order by uqc) as id,uqc as code,uqc_desc as name from ops$dir.d_uqc";
	
}
