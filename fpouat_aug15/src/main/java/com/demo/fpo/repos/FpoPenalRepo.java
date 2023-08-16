package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FpoPenal;

@Repository
public interface FpoPenalRepo extends JpaRepository<FpoPenal, Long> {
	
	@Query(nativeQuery = true, value = "select * from fpo_penal  where CIN_NO = :cinNo")
	List<FpoPenal> getAllPenalty(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from fpo_penal where item_id = :itemId and stage ='DET' and stage_no = :detainedNo")
	List<FpoPenal> getDetainedPenaltyList(@Param("itemId") String itemId, @Param("detainedNo") Long detainedNo);

}
