package com.demo.fpo.apirepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apibean.FpoItemDetBean;

@Repository
public interface FpoItemDetBeanRepository extends JpaRepository<FpoItemDetBean, Long> {

	@Query(nativeQuery = true, value = "select  * from fpo_item_det where item_id =:itemId")
	List<FpoItemDetBean> getItemsDetailsByItemId(@Param("itemId")String itemId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update FPO_ITEM_DET set rms_sent_dt = sysdate where item_id  in (:articleIds)")
	void updateRmsSent(@Param("articleIds") List<String> articleIds);
}
