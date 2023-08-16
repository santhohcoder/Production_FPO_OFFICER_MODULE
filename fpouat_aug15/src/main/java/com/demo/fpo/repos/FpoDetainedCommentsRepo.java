package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoDetainedComments;

@Repository
public interface FpoDetainedCommentsRepo extends JpaRepository<FpoDetainedComments, Long> {

	@Query(nativeQuery = true, value = "select max(seq_no) from fpo_detained_comments where item_id=:itemId and detained_no=:detainedNo")
	Long getMaxSeqNo(@Param("itemId") String itemId, @Param("detainedNo") Long detainedNo);

	@Query(nativeQuery = true, value = "select * from fpo_detained_comments where item_id=:itemId and detained_no=:detainedNo order by seq_no")
	List<FpoDetainedComments> getByItemIdandDetainedNo(@Param("itemId") String itemId, @Param("detainedNo") Long detainedNo);

}
