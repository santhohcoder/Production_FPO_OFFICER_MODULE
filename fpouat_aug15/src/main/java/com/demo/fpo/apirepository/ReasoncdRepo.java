package com.demo.fpo.apirepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Reasoncd;






	@Repository
	public interface ReasoncdRepo extends JpaRepository<Reasoncd, Long> {
		@Transactional
		@Query(nativeQuery = true, value = "SELECT REAS_CD,REAS_NM FROM REASON_CD where REAS_CD=:reascd")
		List<Reasoncd> getAllData(@Param("reascd") Long reascd);
}
