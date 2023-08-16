package com.demo.fpo.apirepository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.fpo.apimodel.Cusitmrspurl;





 
public interface CusitmrspRepo extends JpaRepository<Cusitmrspurl, Long> {
	
	//@Query(nativeQuery = true, value = "SELECT ID,URL,METHODURL FROM CUSITM_URL")
	@Query(nativeQuery = true, value = "SELECT * FROM CUSITMRSP_URL")
 	List<Cusitmrspurl> getcusitm();
}
