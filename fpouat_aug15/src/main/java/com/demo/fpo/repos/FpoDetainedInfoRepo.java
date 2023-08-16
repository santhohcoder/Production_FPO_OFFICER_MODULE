package com.demo.fpo.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoDetainedInfo;

@Repository
public interface FpoDetainedInfoRepo extends JpaRepository<FpoDetainedInfo, Long> {

	@Query(nativeQuery = true, value = "select decode(max(detained_no),null,0,max(detained_no)) from fpo_detained_info where article_id=:articleId")
	Long getMaxDetainedNo(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select * from fpo_detained_info where article_id=:articleId order by detained_no")
	List<FpoDetainedInfo> getDetailsByarticleId(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select * from fpo_detained_info where id=(select max(id) from fpo_detained_info where article_id=:articleId)")
	List<FpoDetainedInfo> getMaxDetailsByarticleId(@Param("articleId") String articleId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_detained_info set cur_off_role=:appraiser where article_id=:articleId and detained_no=:detainedNo")
	void updateCurrentOfficerRole(String appraiser, @Param("articleId") String articleId, @Param("detainedNo") Long detainedNo);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_detained_info set detain_completed='Y',detain_decision_dt = sysdate where article_id=:articleId and detained_no=:detainedNo")
	void updateDetainCompleted(@Param("articleId") String articleId, @Param("detainedNo") Long detainedNo);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_detained_info set detain_completed='Y' where article_id=:articleId")
	void updateDetainCompleted(@Param("articleId") String articleId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_detained_info set detain_completed=null,cur_off_role=null where id=(select max(id) from fpo_detained_info where article_id=:articleId)")
	void updateDetainCompletedEmpty(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select * from fpo_detained_info where article_id=:articleId and detained_no=:detainedNo")
	List<FpoDetainedInfo> getDetailsByarticleIdAndDetainedNo(@Param("articleId") String articleId, @Param("detainedNo") Long detainedNo);

}
