package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FpoItemDetOthDuty;

@Repository
public interface FpoItemDetOthDutyRepo extends JpaRepository<FpoItemDetOthDuty, Long>{
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and ass_dt=(select max(ass_dt) from fpo_item_det_othduty)")
	List<FpoItemDetOthDuty> getFpoOthAssDuty(@Param("cinNo") String cinNo);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo and  (cth != '98049000' and cth != '98041000') and\r\n"
	 * +
	 * "ass_dt=(select max(ass_dt) from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo)"
	 * ) List<FpoItemDetOthDuty> getFpoOthAssDutyOnCinItmNo(String cinNo, Long
	 * itemNo);
	 */
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo  and\r\n"
			+ "ass_dt=(select max(ass_dt) from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo) order by id")
	List<FpoItemDetOthDuty> getFpoOthAssDutyOnCinItmNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo and\r\n"
			+ "ass_dt=(select max(ass_dt) from fpo_item_det_othduty where cin_no = :cinNo and item_no=:itemNo) order by id")
	List<FpoItemDetOthDuty> getOthrNextOnCinItmNo(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from fpo_item_det_othduty where cin_no = :cinNo and (cth='98049000' or cth = '98041000') and item_no = :itemNo"
	 * ) List<FpoItemDetOthDuty> getOthrOnCinNor(String cinNo, Long itemNo);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select * from fpo_item_det_othduty where cin_no = :cinNo and (cth !='98049000' and cth != '98041000') and item_no = :itemNo"
	 * ) List<FpoItemDetOthDuty> getOthrOnCinNorItem(String cinNo, Long itemNo);
	 */
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and  item_no = :itemNo order by id")
	List<FpoItemDetOthDuty> getOthrOnCinNor(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and  item_no = :itemNo order by id")
	List<FpoItemDetOthDuty> getOthrOnCinNorItem(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo and amend_serial_no = (select min(amend_serial_no) from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo)")
	List<FpoItemDetOthDuty> getothitemDecl (@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo order by duty_cd")
	List<FpoItemDetOthDuty> getAllOthrOnCinNor(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select count(cin_no) from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo ")
	Long othdutycou(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det_othduty where cin_no = :cinNo order by duty_cd")
	List<FpoItemDetOthDuty> getAllOthrOnCinNor(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select max(TO_CHAR(amend_dt,'DD-MON-YYYY HH24:MI:SS')) from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo")
	Date getOthrAmdDt(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select max(TO_CHAR(ass_dt,'DD-MON-YYYY HH24:MI:SS')) from fpo_item_det_othduty where cin_no = :cinNo and item_no = :itemNo")
	Date getOthrAssDt(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "DELETE from fpo_item_det_othduty WHERE CIN_NO = :cinNo AND ITEM_NO = :itemNo and (CTH = '98041000' or CTH = '98049000')")
	Long  deleteNinEght(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);	
	
	@Query(nativeQuery = true, value = "DELETE from fpo_item_det_othduty WHERE CIN_NO = :cinNo AND ITEM_NO = :itemNo and (CTH != '98041000' or CTH != '98049000')")
	Long  deleteOthr(@Param("cinNo") String cinNo,  @Param("itemNo") Long itemNo);
}
