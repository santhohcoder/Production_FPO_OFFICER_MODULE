package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoItemDetOthDutyAmend;

@Repository
public interface FpoItemDetOthDutyAmendRepo extends JpaRepository<FpoItemDetOthDutyAmend, Long>{
	
	@Query(nativeQuery = true, value = "select max(AMEND_SERIAL_NO) from fpo_AMEND_ITEM_DET_OTHDUTY where cin_no = :cinNo and item_no = :itemNo")
	Long getMaxSrlNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_amend_item_det_othduty where CIN_NO = :cinNo and ITEM_NO = :itemNo and amend_serial_no = (select min(amend_serial_no) from fpo_amend_item_det_othduty where CIN_NO = :cinNo and ITEM_NO = :itemNo) ")
	List<FpoItemDetOthDutyAmend> getOthItemAmend (@Param("cinNo") String cinNo,  @Param("itemNo")  Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_amend_item_det_othduty where CIN_NO = :cinNo and ITEM_NO = :itemNo")
	List<FpoItemDetOthDutyAmend> getOtherItemsAmend (@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
}
