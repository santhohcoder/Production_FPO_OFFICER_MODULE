package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FpoPenalAmend;

@Repository
public interface FpoPenalAmendRepo extends JpaRepository<FpoPenalAmend, Long> {
	
	@Query(nativeQuery = true, value = "select * from FPO_AMEND_PENAL  where CIN_NO = :cinNo")
	List<FpoPenalAmend> getAllPenalty(@Param("cinNo") String cinNo);
}
