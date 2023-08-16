package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.FpoScanInfo;

public interface FpoScanInfoRepo extends JpaRepository<FpoScanInfo, Long> {

	@Query(nativeQuery = true, value = "select * from fpo_scan_info where article_id = :articleId and  (scanned='M' or scanned is null)")
	List<FpoScanInfo> getScanReportById(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select count(*) from fpo_scan_info where article_id = :articleId and scan_report is not null")
	Long getCountOfScanReportById(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select * from fpo_scan_info where bag_no=(select distinct(bag_no) from articlearr_fpo_info where article_id=:articleId and status is null)")
	List<FpoScanInfo> getScanReportDateOfFpo(@Param("articleId") String articleId);

	@Query(nativeQuery = true, value = "select * from fpo_scan_info where bag_no=(select distinct(recp_id) from article_arr_info where article_id=:articleId and status is null)")
	List<FpoScanInfo> getScanReportDateOfOOE(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select scan_report  from fpo_scan_info where article_id is null and scan_report is not null and bag_no= :bagNoOrRecpId")
	String getScanReportText(String bagNoOrRecpId);
	
	
	@Query(nativeQuery = true, value = "select scan_report  from fpo_scan_info where article_id is null and bag_type='B' and scan_report is not null and bag_no= (select distinct(bag_no) from articlearr_fpo_info where article_id=:articleId and status is null)")
	String getScanReportTextFPOA(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select scan_report  from fpo_scan_info where article_id is null and bag_type='R' and scan_report is not null and bag_no= (select distinct(recp_id) from article_arr_info where article_id=:articleId and status is null)")
	String getScanReportTextOOEA(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select scan_report  from fpo_scan_info where article_id is  not null and article_id=:articleId and scanned='O' and bag_no is not null and bag_type='R'")
	String getScanReportTextOOEB(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select scan_report  from fpo_scan_info where article_id is not null and article_id=:articleId and scanned='O' and bag_no is not null and bag_type='B'")
	String getScanReportTextFPOB(@Param("articleId") String articleId);
	
	@Query(nativeQuery = true, value = "select scanned  from fpo_scan_info where article_id is not null and article_id=:articleId and (scanned = 'M' or  scanned is null)")
	String getScantype(@Param("articleId") String articleId);
	
}
