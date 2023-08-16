package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoOocCancelDocInfo;

@Repository
public interface FpoOocCancelDocInfoRepo extends JpaRepository<FpoOocCancelDocInfo, Long> {
	
	@Query(nativeQuery = true, value = "select * from fpo_ooc_cancel_doc_info where article_id=:itemId and ooc_cancel_no=:oocCancelNo")
	List<FpoOocCancelDocInfo> getByItemId(@Param("itemId") String itemId, @Param("oocCancelNo") Long oocCancelNo);

}
