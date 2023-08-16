package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoMainAmend;

@Repository
public interface FpoMainAmendRepo extends JpaRepository<FpoMainAmend, Long>{
	
	@Query(nativeQuery = true, value = "SELECT MAX(AMEND_SERIAL_NO) from fpo_AMEND_MAIN where CIN_NO = :cinNo")
	Long getFpoAmendOnCinItemNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from fpo_AMEND_MAIN where CIN_NO = :cinNo")
	Long getcouOnCin(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_amend_main where cin_no=:cinNo and amend_serial_no = ( select min(amend_serial_no) from fpo_amend_main where cin_no=:cinNo)")
	FpoMainAmend getAmendOnCinItemNo(@Param("cinNo") String cinNo);
}
