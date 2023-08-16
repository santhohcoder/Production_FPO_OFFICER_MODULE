package com.demo.fpo.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FPO_ORDER;

@Repository
public interface FPO_ORDERrepost extends JpaRepository<FPO_ORDER, Long> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE FPO_ORDER SET FIRST_CHECK = 'D' where CIN_NO = :id and id=(select max(id) from fpo_ORDER where cin_no=:id)")
	void updateStus(@Param("id") String id);

	@Query(nativeQuery = true, value = "select count(*) from fpo_order where CIN_NO = :cinNo")
	Long getOrderCinNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_order where CIN_NO = :cinNo and first_check = 'Y'")
	Long getfirstOrder(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select max(ID) from fpo_order where CIN_NO = :cinNo")
	Long getMaxOrderOnCinNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select exam_order from fpo_order where cin_no=:cinNo and id=(select max(id) from fpo_order where cin_no=:cinNo)")
	String getOrder(@Param("cinNo") String cinNo);
	
	
//	@Query(nativeQuery = true, value = "SELECT * from fpo_ORDER where cin_no=cinNo and first_check is null and id=(select max(id) from fpo_order where cin_no=cinNo)")
//	List<FPO_ORDER> examOrder(String cinNo);
	
	//modified on 18th Jan, 2022 removed and first_check is null//
	@Query(nativeQuery = true, value = "SELECT * from fpo_ORDER where cin_no=:cinNo  and id=(select max(id) from fpo_order where cin_no=:cinNo)")
	List<FPO_ORDER> examOrder(@Param("cinNo") String cinNo);
	
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ORDER where cin_no=:cinNo and first_check != 'D' and id=(select max(id) from fpo_order where cin_no=:cinNo)")
	List<FPO_ORDER> firstCheck(@Param("cinNo") String cinNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from fpo_ORDER where cin_no=:cinNo")
	void deleteOrder(@Param("cinNo") String cinNo);
	
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_order b left join fpo_qry_deci a on a.item_id=b.item_id where to_char(b.ORDER_DATE, 'dd-mm-yyyy hh12:mi:ss') < to_char(a.QRY_DT, 'dd-mm-yyyy hh12:mi:ss') and a.QRY_TYPE='E4' and a.item_id =:itemid")
	Long examOrder1(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select  count(*) from fpo_order b left join fpo_qry_deci a on a.item_id=b.item_id where to_char(b.ORDER_DATE, 'dd-mm-yyyy hh12:mi:ss') > to_char(a.QRY_DT, 'dd-mm-yyyy hh12:mi:ss') and a.QRY_TYPE='E4' and a.item_id =:itemid")
	Long examOrder2(@Param("itemid") String itemid);
	
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where substr(dcall_no,1,3)=:dcallno AND item_id=:itemid")
	Long getaaagen(@Param("itemid") String itemid, @Param("dcallno") String dcallno);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where substr(dcall_no,1,3)=:dcallno AND item_id=:itemid")
	Long geteadgen(@Param("itemid") String itemid, @Param("dcallno") String dcallno);

	
}
 








