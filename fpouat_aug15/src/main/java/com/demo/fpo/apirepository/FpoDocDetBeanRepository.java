package com.demo.fpo.apirepository;

import java.util.List;

//import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apibean.FpoDocDetBean;

@Repository
public interface FpoDocDetBeanRepository extends JpaRepository<FpoDocDetBean, Long> {

	@Query(nativeQuery = true, value = "select  * from fpo_doc_det where item_id =:itemId")
	List<FpoDocDetBean> getDocDetailsByItemId(@Param("itemId") String itemId);

}
