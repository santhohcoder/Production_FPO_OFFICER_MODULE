package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FPO_ORDER;

@Repository
public interface FPO_EXAM_FINDINGSrepost extends JpaRepository<FPO_EXAM, Long> {
	
	@Query(nativeQuery=true,value="SELECT * FROM FPO_EXAM_FINDINGS where cin_no=:cinIdByItemId order by item_no")
	List<FPO_EXAM> findByCinNo(@Param("cinIdByItemId") String cinIdByItemId);
	
	@Query(nativeQuery=true,value="SELECT * FROM FPO_EXAM_FINDINGS where id in (select max(id) FROM fpo_exam_findings where cin_no=:cinIdByItemId GROUP BY ITEM_no)")
	List<FPO_EXAM> findMaxByCinNo(@Param("cinIdByItemId")  String cinIdByItemId);
	
	@Query(nativeQuery=true,value="SELECT count(*) FROM FPO_EXAM_FINDINGS where ITEM_ID = :itemId")
	Long getFindingData(@Param("itemId") String itemId);
	
	@Query(nativeQuery=true,value="SELECT count(*) FROM FPO_EXAM_FINDINGS where cin_no=:cinId order by item_no")
	Long findCountByCinNo(@Param("cinId") String cinId);
	
	@Query(nativeQuery=true, value="select count(*) from fpo_order a left join fpo_status b on a.item_id=b.item_id where to_char(a.ORDER_DATE, 'dd-mm-yyyy hh12:mi:ss') < to_char(b.ARR_SCAN_DT, 'dd-mm-yyyy hh12:mi:ss') and a.item_id=:itemid")
	Long examorderarrscandt(@Param("itemid") String itemid);
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(nativeQuery=true,value="INSERT into FPO_ORDER values ('CIN_NO=?1')")
	 * void getORDER(String CIN_NO);
	 */
	
	//@Query(nativeQuery = true, value = "select count(*) from fpo_exam_findings where CIN_NO = ?1")
	//Long getOrderCinNo(String cinNo);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select max(ID) from fpo_order where CIN_NO = ?1") Long
	 * getMaxOrderOnCinNo(String cinNo);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select exam_order from fpo_order where cin_no=?1 and id=(select max(id) from fpo_order where cin_no=?1)"
	 * ) String getOrder(String cinNo);
	 * 
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT * FROM FPO_ORDER where cin_no=?1 and id=(select max(id) from fpo_order where cin_no=?1)"
	 * ) List<FPO_ORDER> examOrder(String cinNo);
	 */
	
}
 








