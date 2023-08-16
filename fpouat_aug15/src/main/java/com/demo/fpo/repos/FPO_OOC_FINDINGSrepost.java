package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.bean.DepartmentCommentsBean;
import com.demo.fpo.model.FPO_EXAM;
import com.demo.fpo.model.FPO_OOC;
import com.demo.fpo.model.FPO_ORDER;

@Repository
public interface FPO_OOC_FINDINGSrepost extends JpaRepository<FPO_OOC, Long> {
	
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
	 * "SELECT * from fpo_ORDER where cin_no=?1 and id=(select max(id) from fpo_order where cin_no=?1)"
	 * ) List<FPO_ORDER> examOrder(String cinNo);
	 */
	
}
 








