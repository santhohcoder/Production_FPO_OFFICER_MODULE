package com.demo.fpo.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.model.FpoOocCancelInfo;

@Repository
public interface FpoOocCancelInfoRepo extends JpaRepository<FpoOocCancelInfo, Long> {
	
	@Query(nativeQuery = true, value = "select max(ooc_cancel_no) from fpo_ooc_cancel_info where article_id=:articleId")
	Long getMaxOocCancelNo(@Param("articleId") String articleId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_ooc_cancel_info set ooc_cancel_completed='Y' where article_id=:articleId")
	void updateOocCancelCompleted(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select * from fpo_ooc_cancel_info where id=(select max(id) from fpo_ooc_cancel_info where article_id=:itemId) and article_id=:itemId")
	List<FpoOocCancelInfo> getMaxDetailsByarticleId(@Param("itemId") String itemId);

	@Query(nativeQuery = true, value = "select * from fpo_ooc_cancel_info where article_id=:itemId order by ooc_cancel_no")
	List<FpoOocCancelInfo> getDetailsByarticleId(@Param("itemId") String itemId);

	@Query(nativeQuery = true, value = "select count(*) from fpo_ooc_cancel_info where article_id=:itemId and ooc_cancel_completed is null")
	Long getPendingByarticleId(@Param("itemId") String itemId);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_ooc_cancel_info where article_id=:itemId and ooc_cancel_completed='Y' and ooc_cancel_status='ACCEPTED'")
	Long getooccancelarticleId(@Param("itemId") String itemId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_ooc_cancel_info set cur_off_role=:assistantcommissioner where article_id=:itemId and ooc_cancel_no=:oocCancelNo")
	void updateCurrentOfficerRole(@Param("assistantcommissioner") String ac, @Param("itemId") String itemId, @Param("oocCancelNo") Long oocCancelNo);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_ooc_cancel_info set ooc_cancel_completed='Y',ac_remarks = :acremarks,ooc_cancel_status = :oocCancelStatus,ooc_cancel_dt = sysdate where article_id=:itemId and ooc_cancel_no=:maxOocCancelNo")
	void updateOocCancelCompleted(@Param("itemId") String itemId, @Param("acremarks") String acremarks, @Param("oocCancelStatus") String oocCancelStatus, @Param("maxOocCancelNo") Long maxOocCancelNo);

	@Query(nativeQuery = true, value = "select * from fpo_ooc_cancel_info where article_id=:itemId and ooc_cancel_no=:oocCancelNo")
	List<FpoOocCancelInfo> getDetailsByarticleIdAndDetainedNo(@Param("itemId") String itemId, @Param("oocCancelNo") Long oocCancelNo);
	
	
	@Query(nativeQuery = true, value = "select foci.id,fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
			+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
			+ "afi.recd_dt arrivalFPODate,fs.ooc_dt detentionDate,foci.ooc_cancel_status detainDecision,  \r\n"
			+ "foci.ooc_cancel_no detainedNo,'' currentStatus,\r\n"
			+ "foci.ooc_cancel_dt detainDecisionDate from fpo_main fm\r\n"
			+ "join fpo_status fs on  (fm.item_id=fs.item_id) \r\n"
			+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
			+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
			+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
			+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
			+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
			+ "left join fpo_ooc_cancel_info foci on (fm.item_id=foci.article_id) where fm.cus_site=:cuSite \r\n"
			+ "and aai.status is null and afi.status is null and foci.ooc_cancel_completed is not null and \r\n"
			+ "trunc(foci.ooc_cancel_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND to_date (:toDate, 'dd/mm/yyyy') order by foci.ooc_cancel_dt desc")
	List<DetainArticleHistoryBean> getOocCancelledArticlesHistory(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cuSite") String cuSite);
	
	
	
	@Query(nativeQuery = true, value = "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
			+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
			+ "afi.recd_dt arrivalFPODate,fs.ooc_dt detentionDate,cur_off_role currentOfficerRole from fpo_main fm\r\n"
			+ "join fpo_status fs on  (fm.item_id=fs.item_id) \r\n"
			+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
			+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
			+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
			+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
			+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
			+ "left join fpo_ooc_cancel_info foci on (fm.item_id=foci.article_id) where fm.cus_site= :cuSite \r\n"
			+ "and aai.status is null and afi.status is null and foci.ooc_cancel_completed is null \r\n"
			+ "and foci.cur_off_role= :role ")
	List<DetainedArticleBean> getOocCancelProcessArticles(@Param("cuSite") String cuSite, @Param("role") String role);
}
