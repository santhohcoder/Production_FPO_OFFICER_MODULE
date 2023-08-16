package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoQueryDecision;

@Repository
public interface FpoQueryDecisionRepo extends JpaRepository<FpoQueryDecision, Long> {
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P1' where ID = (select max(id) from fpo_QRY_DECI where CIN_NO=:id)")
	void updateQryTypeP1(@Param("id") String id);

	
	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI where cin_no=:cinNo ORDER BY qry_dt  desc")
	List<String> getPageStatus(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI where cin_no=:cinNo and deci_cd is not null and qry_no is null ORDER BY qry_dt  desc")
	List<String> getQueueStatus(@Param("cinNo") String cinNo);
	
	
	@Query(nativeQuery = true, value = "select count(*) from FPO_QRY_DECI where item_id=:itemid and qry_type<>'E4'")
	int chkitemid(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select count(*) from FPO_QRY_DECI where item_id=:itemid and qry_type='E4'")
	int chkitemiddeci(@Param("itemid") String itemid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update FPO_QRY_DECI set qry_type=:qryType, role=:role,off_id=:offid,deci_cd=:decicd,qry_no=:qryno where cus_site=:cusite and item_id=:itemid and id = (select max(id) from fpo_qry_deci where item_id=:itemid and cus_site=:cusite)")
	void updqrydeci(@Param("itemid") String itemid, @Param("qryType") String qryType,@Param("cusite") String cusite,@Param("role") String role,@Param("offid") String offid,@Param("decicd") Long decicd,@Param("qryno") Long qryno);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update FPO_QRY_DECI set qry_type=:qryType, role=:role,off_id=:offid,deci_cd=1 where cus_site=:cusite and item_id=:itemid and id = (select max(id) from fpo_qry_deci where item_id=:itemid and cus_site=:cusite)")
	void updqrydeciblk(@Param("itemid") String itemid, @Param("qryType") String qryType,@Param("cusite") String cusite,@Param("role") String role,@Param("offid") String offid);
	
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update FPO_QRY_DECI set qry_type=:qryType, role=:role,off_id=:offid where cus_site=:cusite and item_id=:itemid and id = (select max(id) from fpo_qry_deci where item_id=:itemid and cus_site=:cusite)")
	void updqrydecioth(@Param("itemid") String itemid, @Param("qryType") String qryType,@Param("cusite") String cusite,@Param("role") String role,@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI where CIN_NO = :cinNo and id=(select max(id) from fpo_QRY_DECI where cin_no=:cinNo and cus_site=:cusite ) and cus_site=:cusite")
	String getabsqueCin(@Param("cinNo") String cinNo, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select init_que from fpo_MAIN where CIN_NO = :cinNo  and cus_site=:cusite")
	String getinitqueCin(@Param("cinNo") String cinNo,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select init_que from fpo_MAIN where ITEM_ID = :itemid  and cus_site=:cusite")
	String getinitqueItm(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI a, fpo_main b  where a.ITEM_ID = :itemid  and  a.item_id=b.item_id and (b.cus_site=:cusite or b.clr_site=:cusite) and id=(select max(id) from fpo_QRY_DECI where ITEM_ID=:itemid  and (cus_site=:cusite or clr_site=:cusite))")
	String getabsqueItm(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI a where a.ITEM_ID = :itemid  and id=(select max(id) from fpo_QRY_DECI where ITEM_ID=:itemid )")
	String getabsqueItmw(@Param("itemid") String itemid);

	@Query(nativeQuery = true, value = "select QRY_TYPE from fpo_QRY_DECI where ITEM_ID = :itemid  and cus_site=:cusite and id=(select max(id) from fpo_QRY_DECI where ITEM_ID=:itemid  and decode(clr_site,null,cus_site,clr_site)=:cusite)")
	String getclrabsqueItm(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	
	@Query(nativeQuery = true, value = "select disp_name from fpo_stages where stage_code = :stage")
	String getcurque(@Param("stage") String stage);
	
	@Query(nativeQuery = true, value = "select disp_name from fpo_stages where stage_code = :stage")
	String getnxtque(@Param("stage") String stage);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P1' where CIN_NO =:CINNO and id =(select max(id) from fpo_qry_deci where  CIN_NO =:CINNO)")
	void updqrytoass(@Param("CINNO") String CINNO);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P2', ROLE=:role, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updassqry(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("role") String role);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P8', ROLE='OOC', off_id=:offid, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO  and qry_type<>'E4'")
	void updqryOOCsub(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("offid") String offid);
	
	/*@Transactional
	@Modifying
	@Query("update fpo_QRY_DECI SET QRY_TYPE = 'E1', ROLE='PAO', QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO  and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryASSsub(@Param("CINNO") String CINNO, @Param("dt") String dt);*/
	
	//added on Feb 22nd, 2022
		@Modifying
		@Transactional
		@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET off_id = :offid where CIN_NO = :cinno and role=:role and id =(select max(id) from fpo_qry_deci where  CIN_NO =:cinno)")
		void updoffidqrydec(@Param("cinno") String cinno, @Param("offid") String offid,@Param("role")String role);
		//added on Feb 22nd, 2022
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P6', ROLE='EDI', QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO  and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryEDI(@Param("CINNO") String CINNO, @Param("dt") String dt);
	
	
	
	@Transactional	
	@Modifying	
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = :stage, ROLE=:role, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")	
	void updassaddlqry(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("role") String role,  @Param("stage") String stage);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET off_id= :offid where CIN_NO = :id and id =(select max(id) from fpo_qry_deci where  CIN_NO =:CINNO)")
	void updqrydecioffid(@Param("id")String id, @Param("offid") String offid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P3', ROLE='PSU', QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryOOC(@Param("CINNO") String CINNO, @Param("dt") String dt);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P1', ROLE='PAO', off_id = :offid, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryASS(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("offid") String offid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P1', ROLE='PAO', off_id = :offid, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS'),cus_site = :clrsite where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryASSClrSite(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("offid") String offid, @Param("clrsite") String clrSite);
	
	@Query(nativeQuery = true, value = "select off_id from fpo_main where cin_no=:id")
	String getoffidcin(String id);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = :que, ROLE=:role, QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updaddlqry(@Param("CINNO") String CINNO, @Param("dt") String dt, @Param("role") String role, @Param("que") String que);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P5', ROLE='PAO', QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryDET(@Param("CINNO") String CINNO, @Param("dt") String dt);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P4', ROLE='PIN', QRY_DT=to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where CIN_NO =:CINNO and qry_type<>'E4' and ID in (select max(id) from fpo_QRY_DECI where CIN_NO=:CINNO)")
	void updqryEXM(@Param("CINNO") String CINNO, @Param("dt") String dt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P4' where CIN_NO = :pen_cin and ID=(select max(ID) from fpo_QRY_DECI where CIN_NO = :pen_cin)")
	void updateqrytype(@Param("pen_cin") String pen_cin);
	
	@Query(nativeQuery = true, value = "select stage_name from fpo_qry_deci a, fpo_stages b where cin_no=:cinno  and a.id=(select max(id) from fpo_QRY_DECI where cin_no=a.cin_no) and a.qry_type=b.stage_code")
	List<FpoQueryDecision> getcurquecin(@Param("cinno") String cinno);
	
	@Query(nativeQuery = true, value = "select stage_name from fpo_qry_deci a, fpo_stages b where item_id=:itemid and a.id=(select max(id) from fpo_QRY_DECI where item_id=a.item_id) and a.qry_type=b.stage_code")
	List<FpoQueryDecision> getcurqueitm(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select deci_cd from fpo_QRY_DECI where cin_no = :cinId and (qry_type='E4' or qry_type='P3' or qry_type='P8' or qry_type='P1' or qry_type='P5') and id=(select max(id) from fpo_QRY_DECI where cin_no = :cinId and (qry_type='E4' or qry_type='P3' or qry_type='P8' or qry_type='P1' or qry_type='P5'))")
	String getDecision(@Param("cinId") String cinId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET QRY_TYPE = 'P1', QRY_DT = :date, ROLE = 'PAO' where CIN_NO = :cinno and ID=(select max(ID) from fpo_QRY_DECI where CIN_NO = :cinno)")
	void updateSpeedPosttoAssforFPO_QRY_DECI(@Param("cinno") String cinno, @Param("date") Date date);
}
