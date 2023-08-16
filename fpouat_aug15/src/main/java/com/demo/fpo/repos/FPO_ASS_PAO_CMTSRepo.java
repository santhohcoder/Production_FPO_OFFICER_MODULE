package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.demo.fpo.model.FPO_ASS_PAO_CMTS;

public interface FPO_ASS_PAO_CMTSRepo extends JpaRepository<FPO_ASS_PAO_CMTS, Long> {

	@Query(nativeQuery = true, value = "select * from FPO_ASS_PAO_CMTS  where CIN_NO = :cinNo ORDER BY SEQ_NO desc")
	List<FPO_ASS_PAO_CMTS> getAprseqNo(@Param("cinNo") String cinNo);

}
