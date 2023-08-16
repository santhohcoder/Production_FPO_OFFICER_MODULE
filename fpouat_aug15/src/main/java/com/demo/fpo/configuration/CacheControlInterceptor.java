package com.demo.fpo.configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CacheControlInterceptor implements HandlerInterceptor {
	

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler, ModelAndView modelAndView) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Strict-Transport-Security","max-age=3153600000; includeSubDomains; preload");

    	
Cookie[] cookies = request.getCookies();
		
		StringBuilder cookieHeader = new StringBuilder();

		if (cookies != null) { // Check if cookies array is not null
		    for (Cookie cookie : cookies) {
		         // Check for the cookie with the name "JSESSIONID"
		           String  str = cookie.getValue();
		           String  key = cookie.getName();
//		           String path = cookie.getPath();
		           String path="/fpoimpoff/";
		           
		           if (str!=null && key!=null) {
		        	   
		        	   cookieHeader.append(key).append("=").append(str).append("; HttpOnly; SameSite=strict; Secure;");
//		        	   cookieHeader.append(key).append("=").append(str).append("; HttpOnly; SameSite=Lax;");
		        	   
		            // Assuming you only need the "SESSION" cookie, break the loop after finding it.
		       
	                  cookieHeader.append("Path=").append(path).append("; ");
	              
		        
		    }
		}
        
 
    
         if (cookieHeader.length() > 0)
        	 response.setHeader("Set-Cookie", cookieHeader.toString());
        // else
        //	 System.out.println("No Cookies");
    }

    	
    }


}
