package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoDetainedCaseInfo;

@Repository
public interface FpoDetainedCaseInfoRepo extends JpaRepository<FpoDetainedCaseInfo, Long> {
	
	@Query(nativeQuery = true, value = "select * from fpo_detained_case_info where article_id=:itemId and detained_no=:detainedNo")
	List<FpoDetainedCaseInfo> getByItemId(@Param("itemId") String itemId, @Param("detainedNo")Long detainedNo);
}
