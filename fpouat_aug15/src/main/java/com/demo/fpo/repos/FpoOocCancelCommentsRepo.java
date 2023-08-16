package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoOocCancelComments;

@Repository
public interface FpoOocCancelCommentsRepo extends JpaRepository<FpoOocCancelComments, Long> {
	
	@Query(nativeQuery = true, value = "select max(seq_no) from fpo_ooc_cancel_comments where item_id=:itemId and ooc_cancel_no=:oocCancelNo")
	Long getMaxSeqNo(@Param("itemId") String itemId, @Param("oocCancelNo") Long oocCancelNo);

	@Query(nativeQuery = true, value = "select * from fpo_ooc_cancel_comments where item_id=:itemId and ooc_cancel_no=:oocCancelNo order by seq_no")
	List<FpoOocCancelComments> getByItemIdandOocCancelNo(@Param("itemId") String itemId, @Param("oocCancelNo") Long oocCancelNo);

}
