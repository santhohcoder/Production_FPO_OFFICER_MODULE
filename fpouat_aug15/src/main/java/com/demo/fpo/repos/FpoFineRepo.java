package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoFine;

@Repository
public interface FpoFineRepo extends JpaRepository<FpoFine, Long> {

	@Query(nativeQuery = true, value = "select * from fpo_fine  where CIN_NO = :cinNo")
	List<FpoFine> getAllFine(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from fpo_fine where item_id = :itemId and stage ='DET' and stage_no = :detainedNo")
	List<FpoFine> getDetainedFineList(@Param("itemId") String itemId, @Param("detainedNo") Long detainedNo);
	
}
