package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FpoFineAmend;

@Repository
public interface FpoFineAmendRepo extends JpaRepository<FpoFineAmend, Long> {
	
	@Query(nativeQuery = true, value = "select * from FPO_AMEND_FINE where CIN_NO = :cinNo")
	List<FpoFineAmend> getAllFine(@Param("cinNo") String cinNo);
}
