package com.demo.fpo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class InputValidateController {

	
	
	public int ValidaString(String employee) throws Exception {
		
		
		int val =0;
		String reg ="/<(.|\n)*?>/g"; 
		if(employee != null)
		{
		
		if(employee.contains(">") || employee.contains("<")) 
			return 1;
		else if (!reg.equals(employee) == false) 
			 return 1;
		else  
			 return 0;
		}
		
		else 
			return 1;
	}
	



public int Validanum(String employee) throws Exception {
	
	
	int val =0;
	String reg ="[0-9]*"; 
	if(employee != null)
	{
	
	       Pattern pattern = Pattern.compile(reg);
	       if (!pattern.matcher(employee).matches()) 
	    	  return 1;	    	  	      
	       else if (employee.contains("<") || employee.contains(">")) 	        
	 		  return 1;	 	    
	       else  
	 		  return 0;
	 	}	
	  else 
		 return 1;
		
}


		
		public int Validadate(String employee)  {
		    if (employee == null) {
		        return 1;
		    }
		
		    String dateFormat = "dd/MM/yyyy";
		    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		    sdf.setLenient(false);		
		    try {
		    	Date date = sdf.parse(employee);
		    } catch (Exception e) {
		        return 1;
		    }		
		    if (employee.equals(">") || employee.equals("<")) {
		        return 1;
		    } else if (employee.matches(".*<(.|\n)*?>.*")) {
		        return 1;
		    }
		    return 0;
		}

		
		

	public int validaFile(MultipartFile documentFile) {
		        if (documentFile == null || documentFile.isEmpty()) {
		            return 1; 
		        }
		        		        
		        String originalFilename = documentFile.getOriginalFilename();
		        
		        if (originalFilename == null || originalFilename.isEmpty()) {
		            return 1; 
		        }

		        if (originalFilename.contains("<") || originalFilename.contains(">") || originalFilename.contains("*") || originalFilename.contains("?")) {
		            return 1; 
		        }
		        
		        
		        int dotIndex = originalFilename.lastIndexOf(".");
		        String fileExtension = "";
		        if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
		            fileExtension = originalFilename.substring(dotIndex + 1).toLowerCase();
		        }

		        if (!"pdf".equalsIgnoreCase(fileExtension)) {
		            return 1; 
		        }
		        
		        
		        long fileSize = documentFile.getSize();
		        if (fileSize <= 0) {
		            return 1; 
		        }
		        
		        File destination = new File(documentFile + documentFile.getOriginalFilename());
		        
		        
		        if (destination.isFile()) {
		            return 1; 
		        }
		        
		        
		        return 0; 
		    }
	
	
	public int ValidateEmail(String email) {
	    if (email == null || email.isEmpty()) {
	        return 1; 
	    }

	    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
	    Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

	    if (!pattern.matcher(email).matches()) {
	        return 1; 
	    }

	    return 0; // Valid email
	}
		

		
	    
	    
	    
	    
	








}