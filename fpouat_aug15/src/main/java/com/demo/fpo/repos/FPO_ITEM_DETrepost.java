package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;

@Repository
public interface FPO_ITEM_DETrepost extends JpaRepository<FPO_ITEM_DET, Long> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET det set det.DUTY =:duty, det.DUTY_FG =:dutyFg where det.id =:id")
	void markEntryAsRead(@Param("id") Long id, @Param("duty") Float duty, @Param("dutyFg") Float dutyFg);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set ASS_DT =:date, off_id = :offid, role = :role  where cin_no =:id and item_no=:itmno")
	void updateAssDate(@Param("id") String id, @Param("itmno") Long itmno, @Param("date") Date date, @Param("offid") String offid, @Param("role") String role);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set ASS_DT =:date, UPDASS_DT = :date, off_id = :offid, role = :role  where cin_no =:id ")
	void updateAssDateall(@Param("id") String id, @Param("date") Date date, @Param("offid") String offid, @Param("role") String role);


	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set item_desc=:itdesc, item_reviseddesc=:itrevdesc, nou=:nou, netwt=:wt, origcntrycd=:cntrycd, cth=:cth, cth_revised=:cthrev, decl_val=:decl, assess_val=:assval, modified=:modify, amend_serial_no=:amdsno, amend_dt=:amddt, amend_flag='U', bcd_amt=:bcd, igst_amt=:igst, sw_amt=:sw,  currcd=:currcd, curr_rate=:curr_rate, role=:role, off_id=:offid where cin_no =:id and item_no=:itmno")
	void updatechanges(@Param("id") String id, @Param("itmno") Long itmno,@Param("itdesc") String itdesc,@Param("itrevdesc") String itrevdesc,@Param("wt") Float wt,@Param("cntrycd") String cntrycd,@Param("cth") String cth,@Param("cthrev") String cthrev,@Param("decl") Float decl,@Param("assval") Float assval,@Param("amdsno") Long amdsno,@Param("bcd") Float bcd,@Param("igst") Float igst,@Param("sw") Float sw,@Param("nou") Long nou,@Param("currcd") String currcd,@Param("curr_rate") Float curr_rate, @Param("amddt") Date amddt, @Param("offid") String offid, @Param("role") String role,@Param("modify") String modify);	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET  set ASS_DT =:date, UPDASS_DT = :date, DUTY =:totdutyamt, off_id = :offid, role = :role  where cin_no =:id and item_no = :itemno")
	void updAssItm(@Param("id") String id, @Param("date") Date date, @Param("itemno") Long itemno, @Param("totdutyamt") Float totdutyamt,  @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "select max(TO_CHAR(amend_dt,'DD-MON-YYYY HH24:MI:SS')) from fpo_item_det where cin_no = :cinNo and item_no = :itemNumber")
	Date getMaxAmdDt(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery=true, value="select *  from fpo_main where cin_no=:id and cus_site=:cusite")
	List<FPO_MAIN> getfpomainbyidsite(@Param("id") String id, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select max(TO_CHAR(updass_dt,'DD-MON-YYYY HH24:MI:SS')) from fpo_item_det where cin_no = :cinNo and item_no = :itemNumber")
	Date getMaxAssDt(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);

	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getitem(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByCinNoAndItemNo(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber")
	List<FPO_ITEM_DET> getItemOnCin(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);

	@Query(nativeQuery = true, value = "select COUNT(*) from fpo_item_det where cin_no = :cinNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	Long getNoOfYesNoItems(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and modified = 'Y' and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByModYes(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ASS_DT is not null and amend_serial_no is null")
	List<FPO_ITEM_DET> getYESDATE(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItem(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ASS_DT is null and amend_serial_no is null")
	List<FPO_ITEM_DET> getNODATE(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ASS_DT is null and amend_serial_no is not null")
	List<FPO_ITEM_DET> getAmendDATE(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select max(item_no) from fpo_item_det where CIN_NO = :cinNo")
	Long maxOfItemNumber(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select max(amend_serial_no)+1 from fpo_amend_item_det where CIN_NO = :cinNo")
	Long getamendsno(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_item_det where cin_no=:cinNo and (ass_dt is not null or updass_dt is not null)")
	Long getMaxAssess(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT  count(*) from fpo_item_det where cin_no=:cinNo and  updass_dt is null ")
	Long getasscompCount(@Param("cinNo") String cinNo);	
	
	@Query(nativeQuery = true, value = "SELECT  count(*) from fpo_main where cin_no=:cinNo and  ass_dt is not null ")
	Long getassCount(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_item_det where cin_no = :cinNo and item_no = :itemNumber and amend_serial_no =  (select max(amend_serial_no) from fpo_item_det where cin_no = :cinNo and item_no = :itemNumber \r\n"
			+ ") and amend_dt is not null and amend_flag= 'U' and modified = 'Y'")
	List<FPO_ITEM_DET> maxOfAmendDet(String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber and ASS_DT is not null and amend_serial_no is null")
	List<FPO_ITEM_DET> getYESDATEOne(String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber and ASS_DT is null and amend_serial_no is not null")
	List<FPO_ITEM_DET> getYESAmendDATEOne(String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber and ASS_DT is null and amend_serial_no is null")
	List<FPO_ITEM_DET> getNODATEOne(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber and ASS_DT is not null and amend_serial_no is null")
	List<FPO_ITEM_DET> getYESDATEOnCinNo(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNumber  and ASS_DT is null and amend_serial_no is null")
	List<FPO_ITEM_DET> getNODATEOnCinNo(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and (modified is null or modified = 'N') and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByModNo(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByIdNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByIdNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO = :itemNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') and ITEM_UNIQUE = (select max(ITEM_UNIQUE) from fpo_ITEM_DET where CIN_NO = :cinNo and ITEM_NO =:itemNo) ORDER BY ITEM_NO")
	List<FPO_ITEM_DET> getItemByIdNoMaxId(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);

	@Query(nativeQuery = true, value = "select distinct(CTH) from ops$dir.dt_notn_slno where notn_endt is null and substr(CTH,1,4)='9804'")
	List<Object> getGENDROP();	
	
	@Cacheable("getDUTY")	
	@Query(nativeQuery = true, value = "select DUTY_CD,DUTY_DESC from CTH_OTH_DUTY WHERE active='Y' ORDER BY duty_cd ")	
	List<Object> getDUTY();	
	
	@Query(nativeQuery = true, value = "delete from fpo_item_Det_othduty where cin_no=:cinno  and item_no=:itemno and cus_site=:cussite")
	void delitemdetothduty(@Param("cinno") String cinno, @Param("itemno") Long itemno, @Param("cussite") String cussite);

	@Query(nativeQuery = true, value = "select count(*) from fpo_item_Det_othduty where cin_no=:cinno and item_no=:itemno and cus_site=:cussite")
	int getcouitemdetothduty(@Param("cinno") String cinno, @Param("itemno") Long itemno, @Param("cussite") String cussite);
	
	
	@Query(nativeQuery = true, value = "select DUTY_DESC from CTH_OTH_DUTY WHERE active='Y' and DUTY_CD=:dutyCd order by duty_cd")	
	String getDUTYOnCd(@Param("dutyCd") Long dutyCd);
	
	@Query(nativeQuery = true, value = "select DUTY_CD,DUTY_DESC from CTH_OTH_DUTY WHERE active='Y' and DUTY_CD!=:dutyCd order by duty_cd")	
	List<Object> getDUTYOnCdoth(@Param("dutyCd") Long dutyCd);
	
	@Query(nativeQuery = true, value = "select DUTY_RATE from CTH_OTH_DUTY WHERE active='Y'  and DUTY_CD=:dutyCd ORDER BY duty_cd ")	
	Float getDUTYRate(@Param("dutyCd") Long dutyCd);	
	
	@Query(nativeQuery = true, value = "select  distinct notn,slno,rta,amts,uqc,flg,cvd_rta,nvl(cond,'Y'),cntry from ops$dir.dt_n_notn_slno  where ad_flg is null and  (nvl(cond,'Y') = 'Y' or (nvl(cond,'Y') = 'R' and cth=:dutyCthOthers)) and notn_type='C' and nvl(cntry,'N')='N' and nvl(notn_endt,trunc(sysdate))>=trunc(sysdate)\r\n"
			+ "union all select  distinct notn,slno,rta,amts,uqc,flg,cvd_rta,nvl(cond,'Y'),cntry from ops$dir.dt_n_notn_slno  where ad_flg is null and  (nvl(cond,'Y') = 'Y' or (nvl(cond,'Y') = 'R' and cth=:dutyCthOthers)) and notn_type='C' and nvl(cntry,'N')='Y' and nvl(notn_endt,trunc(sysdate))>=trunc(sysdate)\r\n"
			+ "and notn in (select distinct notn  from ops$dir.dt_notn_cntry where cntry_cd =null and  nvl(end_dt,trunc(sysdate))>=trunc(sysdate))  order by 7 ; ")	
	List<Object> getDUTYOtherDet(@Param("dutyCthOthers") String dutyCthOthers);
	
	@Query(nativeQuery = true, value = "select DISTINCT (SUBSTR(CTH, 1, 2)) from ops$dir.dt_bcd where nvl(end_dt,trunc(sysdate))>=trunc(sysdate) order by SUBSTR(CTH, 1, 2) ASC")	
	List<Object> getOtherTwoDigi();	
	
	@Query(nativeQuery = true, value = "select DISTINCT (SUBSTR(CTH, 1, 4)) from ops$dir.dt_bcd where substr(CTH,1,2)=:value and LENGTH(CTH) >=8 and \r\n"
			+ "nvl(end_dt,trunc(sysdate))>=trunc(sysdate) order by SUBSTR(CTH, 1, 4) ASC")	
	List<Object> getOtherFourDigi(@Param("value") String value);
	
	@Query(nativeQuery = true, value = "select DISTINCT (SUBSTR(CTH, 1, 8)) from ops$dir.dt_bcd where substr(CTH,1,4)=:value and LENGTH(CTH) >=8 and \r\n"
			+ "nvl(end_dt,trunc(sysdate))>=trunc(sysdate) order by SUBSTR(CTH, 1, 8) ASC")	
	List<Object> getOtherEightDigi(@Param("value") String value);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where notn_endt is null and cth = :cth AND notn_type='C' and ad_flg is null")
	List<Object> getBcdNotification(@Param("cth")  String cth);

	@Cacheable("getBcdSlNoId")
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_notn_slno where notn_endt is null and cth = :cth and notn = :notn AND notn_type='C' and ad_flg is null")
	List<Object> getBcdSlNo(@Param("notn") String notn, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select DISTINCT(rta) from ops$dir.dt_notn_slno where notn_endt is null and cth = :cth and slno = :slno AND notn_type='C' and ad_flg is null")
	List<Object> getBcdRate(@Param("slno") String slno, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='G' and cth = :cth and ad_flg = 'I'")
	List<Object> getIgstNotification(@Param("cth") String cth);

	@Cacheable("getIgstSerialNoId")
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='G' and cth = :cth and notn = :notn and ad_flg = 'I'")
	List<Object> getIgstSerialNo(@Param("notn") String notn, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select DISTINCT(rta) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='G' and cth = :cth and slno = :slno and ad_flg = 'I'")
	List<Object> getIgstRate(@Param("slno") String slno, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='C' and cth = :cth and ad_flg = 'C'")
	List<Object> getSwNotification(@Param("cth") String cth);

	@Cacheable("getSwSerialNoId")
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='C' and cth = :cth and notn = :notn and ad_flg = 'C'")
	List<Object> getSwSerialNo(@Param("notn") String notn, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select DISTINCT(rta) from ops$dir.dt_notn_slno where notn_endt is null and notn_type='C' and cth = :cth and slno = :slno and ad_flg = 'C'")
	List<Object> getSwRate(@Param("slno") String slno, @Param("cth") String cth);

	@Query(nativeQuery = true, value = "select COUNT(ITEM_NO) from fpo_item_det where cin_no = :cin")
	List<Object> getTotalNoItems(@Param("cin") String cin);

	@Query(nativeQuery = true, value = "select ITEM_NO from fpo_item_det where cin_no = :cinNo  and (AMEND_FLAG is null or AMEND_FLAG != 'D') and ASS_DT is null ORDER BY ITEM_NO")
	List<Object> getTotalNoItemsPagination(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select ITEM_NO from fpo_item_det where cin_no = :cinNo and (AMEND_FLAG is null or AMEND_FLAG != 'D') and ASS_DT is not null ORDER BY ITEM_NO")
	List<Object> getTotalNoItemsPaginationYes(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select gen_cth from fpo_item_det where cin_no = :cin and (AMEND_FLAG is null or AMEND_FLAG != 'D') ORDER BY ITEM_NO")
	List<String> getItemAllCth(@Param("cin") String cin);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_item_det SET ITEM_DESC=:itemdesc,NETWT=:netwt where CIN_NO = :cinNo")
	void updateFpoItemDesc(@Param("netwt") float netwt, @Param("itemdesc")  String itemdesc, @Param("cinNo") String cinNo);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_item_det SET amend_flag = :amendFlag WHERE cin_no = :cinNo and item_no = :itemNo")
	void deleteFpoItem(@Param("amendFlag") String amendFlag, @Param("cinNo") String cinNo, @Param("itemNo") String itemNo);
	
	@Query(nativeQuery = true, value = "select cntry_nm from ops$dir.d_curr_cd a, ops$dir.d_cntry_cd  b where a.curr_cd=:currCode  and a.cntry_cd=b.cntry_cd")
	List<String> getCountryName(@Param("currCode") String currCode);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and notn_type='C' and ad_flg is null")
	List<Object> getOthersBcdNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select slno  from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and notn_type='C' and ad_flg is null and notn=:notn")
	List<Object> getOthersBcdSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select max(rta) from ops$dir.dt_bcd where end_dt is null and cth=:cth")
	Float getOthersBcdRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select notn from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and ad_flg='P'")
	List<Object> getOthersSaptaNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select slno  from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and ad_flg='P' and notn=:notn")
	List<Object> getOthersSaptaSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select max(rta) from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and ad_flg='P' ")
	Float getOthersSaptaRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select max(rta) from ops$dir.dt_notn_slno where cth=:cth and slno =:slNo and notn_endt is null and ad_flg='P' ")
	Float getOthersSaptaSlnoRate(@Param("cth") String cth, String slNo);

	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and notn_type='G'")
	List<Object> getOthersIgstNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(slno)  from ops$dir.dt_notn_slno where cth=:cth and notn_endt is null and notn_type='G' and notn=:notn")
	List<Object> getOthersIgstSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select max(rta) from ops$dir.dt_notn_slno where cth=:cth and notn_type='G' and notn_endt is null")
	Float getOthersIgstRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and ad_flg in ('P','3') and notn_type in ('G','C') and notn_endt is null")
	List<Object> getOthersCCNNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select slno from ops$dir.dt_notn_slno where cth=:cth and ad_flg in ('P','3') and notn_type in ('G','C') and notn_endt is null  and notn=:notn")
	List<Object> getOthersCCNSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_notn_slno  where ad_flg in ('P','3') and notn_type in ('G','C') and cth=:cth  and notn_endt is null")
	Float getOthersCCNRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_notn_slno  where ad_flg in ('P','3') and notn_type in ('G','C') and cth=:cth and slno =:slNo  and notn_endt is null")
	Float getOthersCCNSlnoRate(@Param("cth") String cth, String slNo);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and ad_flg='V'  and notn_endt is null")
	List<Object> getOthersSDANotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_notn_slno where cth=:cth and ad_flg='V' and notn_endt is null and notn=:notn")
	List<Object> getOthersSDASlNo(String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_bcd  where cth=:cth and end_dt is null")
	Float getOthersSDARate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_notn_slno  where cth=:cth and slno=:slNo and end_dt is null")
	Float getOthersSDASlnoRate(@Param("cth") String cth, String slNo);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and ad_flg='S' and notn_type='C' and notn_endt is null")
	List<Object> getOthersSGDNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_notn_slno where cth=:cth and ad_flg='S' and notn_type='C' and notn_endt is null and notn=:notn")
	List<Object> getOthersSGDSlNo(String notn, String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_bcd  where cth=:cth and end_dt is null")
	Float getOthersSGDRate(String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_bcd  where cth=:cth and slno=:slno and end_dt is null")
	Float getOthersSGDSlnoRate(String cth, String slno);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_antidump where cth=:cth and DTY_TYP='C' and notn_endt is null")
	List<Object> getOthersADDNotification(String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_antidump where cth=:cth and DTY_TYP='C' and notn_endt is null and notn=:notn")
	List<Object> getOthersADDSlNo(String notn, String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from dir.dt_bcd  where cth=:cth  and end_dt is null")
	Float getOthersADDRate(String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from dir.dt_bcd  where cth=:cth and slno=:slno and end_dt is null")
	Float getOthersADDSlnoRate(@Param("cth") String cth, @Param("slno") String slno);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_antidump where cth=:cth and DTY_TYP='C' and notn_endt is null")
	List<Object> getOthersCTDNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select DISTINCT(slno) from ops$dir.dt_antidump where cth=:cth and DTY_TYP='C' and notn_endt is null and notn=:notn")
	List<Object> getOthersCTDSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from dir.dt_bcd  where cth=:cth  and end_dt is null")
	Float getOthersCTDRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from dir.dt_bcd  where cth=:cth and slno=:slno  and end_dt is null")
	Float getOthersCTDSlnoRate(String cth, @Param("slno")String slno);
	
	@Query(nativeQuery = true, value = "select DISTINCT(notn) from ops$dir.dt_notn_slno where cth=:cth and notn_type='E' and notn_endt is null")
	List<Object> getOthersCVDNotification(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select slno from ops$dir.dt_notn_slno where cth=:cth and notn_type='E' and notn_endt is null and notn=:notn")
	List<Object> getOthersCVDSlNo(@Param("notn") String notn, @Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_acd  where ceth=:cth  and CVD_DTY_TYP='CVD' and end_dt is null;")
	Float getOthersCVDRate(@Param("cth") String cth);
	
	@Query(nativeQuery = true, value = "select  max(rta) from ops$dir.dt_acd  where ceth=:cth and slno=:slno  and CVD_DTY_TYP='CVD' and end_dt is null;")
	Float getOthersCVDSlnoRate(@Param("cth") String cth, @Param("slno") String slno);
	
	@Query(nativeQuery = true, value = "select  max(id) from fpo_query  where cin_no=:cinNo and item_no=:parseLong  and QRY_TYP=:que")
	Long getMaxIdOfFPOQUERY(@Param("cinNo") String cinNo, @Param("parseLong") long parseLong, @Param("que")  String que);

	@Query(nativeQuery = true, value = "select  max(id) from fpo_query  where cin_no=:cinNo and item_no is null and QRY_TYP=:que")
	Long getMaxIdOfFPOQUERY(@Param("cinNo") String cinNo, @Param("que") String que);

	@Query(nativeQuery = true, value = "select  max(FPO_QRY_ID) from fpo_QRY_DIN  where cin_no=:cinNo")
	Long getMaxIdOfFpoQueryDin(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select  DEF_QUERY from fpo_query  where id=:maxid and qry_typ !='D'")
	String getDEFUALT_QUERYbyId(@Param("maxid") Long maxid);

}