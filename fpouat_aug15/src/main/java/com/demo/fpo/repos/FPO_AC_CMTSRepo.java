package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.FPO_AC_CMTS;
import com.demo.fpo.model.FPO_MAIN;

public interface FPO_AC_CMTSRepo extends JpaRepository<FPO_AC_CMTS, Long> {

	@Query(nativeQuery = true, value = "select * from FPO_AC_CMTS  where CIN_NO = :cinNo ORDER BY SEQ_NO desc")
	List<FPO_AC_CMTS> getAcseqNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select substr(DCALL_NO, 1,3) filename from dcallqry_gen where id = (select MAX(id) from dcallqry_gen where cin_no=:id)")
	String stages(@Param("id") String id);
}
