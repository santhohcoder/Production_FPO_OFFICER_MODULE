package com.demo.fpo.repos;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoCurQue;

@Repository
public interface FpoCurQueRepo extends JpaRepository<FpoCurQue, Long>{

	@Query(nativeQuery = true, value = "select max(que_no) from fpo_CURR_QUE where CIN_NO = :cinNo")
	Long getMaxQnoOnCinNo(@Param("cinNo")  String cinNo);
	
	@Query(nativeQuery = true, value = "select CURR_QUE_ID from fpo_CURR_QUE where CIN_NO = :cinNo ")
	Long getIdOnCin(@Param("cinNo")  String cinNo);
	
	@Query(nativeQuery = true, value = "select CURR_QUE from fpo_CURR_QUE where CIN_NO = :cinNo ")
	String getcurqueCin(@Param("cinNo")  String cinNo);
	
	@Query(nativeQuery = true, value = "select CUS_SITE from fpo_MAIN where CIN_NO = :cinNo")
	String getcusitecin(@Param("cinNo")  String cinNo);
	
	@Query(nativeQuery = true, value = "select CUS_SITE from fpo_MAIN where ITEM_ID = :itemid")
	String getcusiteItm(@Param("itemid")  String itemid);
	
	@Query(nativeQuery = true, value = "select CURR_QUE from fpo_CURR_QUE where ITEM_ID = :itemid  ")
	String getcurqueItm(@Param("itemid")  String itemid);
	
	//Priority
	
	@Query(nativeQuery = true, value = "select item_id from fpo_main where item_id = :itmid")
	String chckitmid(@Param("itmid")  String itmid);
	
	@Query(nativeQuery = true, value = "select cus_site from fpo_main  where item_id = :itemid ")
	String curqueueitem(@Param("itemid")  String itemid);
	

	@Query(nativeQuery = true, value = "select cin_no from fpo_main where item_id = :itmid and cus_site = :stecde")
	String getcurque(@Param("itmid")  String itmid, @Param("stecde") String stecde);
	
	@Query(nativeQuery = true, value = "select item_id from fpo_curr_que where item_id = :itmid ")
	String checkcurque(@Param("itmid")  String itmide);
	
	@Query(nativeQuery = true, value = "select role from fpo_main where item_id = :itmid")
	String getrole(@Param("itmid")  String itmid);
	
	@Query(nativeQuery = true, value = "select qry_type from fpo_qry_deci where item_id= :itmid and cus_site= :stecde and (qry_type in 'P1' or qry_type in 'N1' or qry_type in 'P2') and qry_no is null and id=(select max(id) from fpo_qry_deci where item_id=:itmid and cus_site = :stecde)") 
	String checkinQrydesc(@Param("itmid")  String itmid,  @Param("stecde") String stecde);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set priority = 'P' where item_id = :itemid and cus_site=:cusite")
	void updatepriority(@Param("itemid")  String itemid, @Param("cusite")  String cusite); //
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_CURR_QUE det set CURR_QUE = :CURQUE, role=:role, que_no=:slno,  que_dt=to_date(:quedt,'DD/MM/YYYY HH24:MI:SS'),OFF_ID=:OFFID,CUS_SITE=:CUSSITE  where det.cin_no=:CINNO")
	void updatefpocurque(@Param("CINNO") String CINNO, @Param("CURQUE") String CURQUE, @Param("role") String role, @Param("slno") Long slno,  @Param("quedt") String quedt, @Param("OFFID") String OFFID,@Param("CUSSITE") String CUSSITE);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set APR_DT=to_date(:aprdt,'DD/MM/YYYY HH24:MI:SS'), ASS_DT=to_date(:aprdt,'DD/MM/YYYY HH24:MI:SS'),APR_ID=:offid  where det.cin_no=:CINNO")
	void updfpostatusapr(@Param("CINNO") String CINNO,@Param("aprdt") String aprdt,@Param("offid") String offid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set ACL_DT=to_date(:acldt,'DD/MM/YYYY HH24:MI:SS'), ASS_DT=to_date(:acldt,'DD/MM/YYYY HH24:MI:SS'),ACL_ID=:offid  where det.cin_no=:CINNO")
	void updfpostatusacl(@Param("CINNO") String CINNO,@Param("acldt") String acldt,@Param("offid") String offid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set SUP_ID=:SUPID, SUP_DT=to_date(:supdt,'DD/MM/YYYY HH24:MI:SS')  where det.cin_no=:CINNO")
	void updfpostatussup(@Param("CINNO") String CINNO,@Param("SUPID") String SUPID,@Param("supdt") String supdt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set INS_ID=:INSID, INS_DT=to_date(:insdt,'DD/MM/YYYY HH24:MI:SS')  where det.cin_no=:CINNO")
	void updfpostatusexm(@Param("CINNO") String CINNO,@Param("INSID") String INSID,@Param("insdt") String insdt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set OOC_DT=to_date(:oocdt,'DD/MM/YYYY HH24:MI:SS')  where det.cin_no=:CINNO")
	void updfpostatusooc(@Param("CINNO") String CINNO,@Param("oocdt") String oocdt);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set ASS_DT=to_date(:assdt,'DD/MM/YYYY HH24:MI:SS')  where det.cin_no=:CINNO")
	void updfpostatusass(@Param("CINNO") String CINNO,@Param("assdt") String assdt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_STATUS det set DET_ID=:DETID,DET_DT=to_date(:detdt,'DD/MM/YYYY HH24:MI:SS')  where det.cin_no=:CINNO")
	void updfpostatusdet(@Param("CINNO") String CINNO,@Param("DETID") String DETID,@Param("detdt") String detdt);

	
	@Query(nativeQuery = true, value = "select OFF_ID from fpo_CURR_QUE where CURR_QUE_ID = (select max(CURR_QUE_ID) from fpo_CURR_QUE where cin_no = :cinNo)") 
	String getOffId(@Param("cinNo")  String cinNo);


	@Query(nativeQuery = true, value = "select  TOT_FINE from fpo_main where cin_no = :cinNo")
	BigDecimal getFN_AMT(@Param("cinNo")  String cinNo);

	@Query(nativeQuery = true, value = "select  TOT_PENAL from fpo_main where cin_no = :cinNo")
	BigDecimal getPL_AMT(@Param("cinNo")  String cinNo);


	@Query(nativeQuery = true, value = "select  TOT_AMT_PAYABLE from fpo_main where cin_no = :cinNo")
	BigDecimal getTOT_AMT_PAYABLE(@Param("cinNo")  String cinNo);
	
	
}
