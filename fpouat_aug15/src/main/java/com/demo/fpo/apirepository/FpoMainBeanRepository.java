package com.demo.fpo.apirepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.apibean.FpoMainBean;
import org.springframework.stereotype.Repository;

@Repository
public interface FpoMainBeanRepository extends JpaRepository<FpoMainBean, Long> {

	@Query(nativeQuery = true, value = "select * from fpo_main where rms_sent_dt is null")
	List<FpoMainBean> getRmsNotSentArticles();

	@Transactional
	@Modifying(clearAutomatically = true)	
    @Query(nativeQuery = true, value = "update FPO_MAIN set rms_sent_dt = sysdate where item_id in (:articleIds)")
	void updateRmsSent(@Param("articleIds") List<String> articleIds);

}
