package com.demo.fpo.apirepository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.DeciReas;






	@Repository
	public interface DeciReasRepo extends JpaRepository<DeciReas, Long> {
		@Transactional
		@Query(nativeQuery = true, value = "SELECT DECI_CD,DECI_NM FROM DECI_REAS where DECI_CD=:decicd")
		List<DeciReas> getAllData(@Param("decicd") Long decicd);
}

	
	