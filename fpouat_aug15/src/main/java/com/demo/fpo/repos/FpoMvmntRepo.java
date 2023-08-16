package com.demo.fpo.repos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.bean.FpoMovement;
import com.demo.fpo.model.FpoMvmnt;

@Repository
public interface FpoMvmntRepo extends JpaRepository<FpoMvmnt, Long>{
	
	@Query(nativeQuery = true, value = "select max(id) from fpo_MVMNT where cin_no = :cinNo")
	Long getIdOnCin(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_MVMNT where cin_no = :cinNo  and role = :role and ext_dt is  null")
	Long getRecordOnCin(@Param("cinNo") String cinNo, @Param("role") String role);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update FPO_MVMNT det set det.EXT_DT =:date where det.id =:id")
	void updateUSerExitStatus(@Param("id") Long id, @Param("date") Date date);
	
	@Query(nativeQuery = true, value = "select max(slno) from fpo_MVMNT where cin_no = :cinNo")
	Long getMaxSlOnCin(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_mvmnt where cin_no=:cinNo and ext_dt is null and slno = ( select min(slno) from fpo_mvmnt where role='PAC' and cin_no=:cinNo) and role='PAC'")
    Long getACLfirsttime(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_mvmnt where cin_no=:cinNo and ext_dt is null and slno = ( select max(slno) from fpo_mvmnt where role='PIN' and cin_no=:cinNo) and role='PIN'")
    Long getEXMfirsttime(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select count(*) from fpo_mvmnt where cin_no=:cinNo  and slno = ( select max(slno) from fpo_mvmnt where role='PSU' and cin_no=:cinNo) and role='PSU'")
    Long getOOCfirsttime(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select officer_id from fpo_MVMNT where cin_no = :cinNo and slno=(select max(slno) from fpo_mvmnt where ext_dt is null and cin_no=:cinNo)")
	String getoffid(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select role from fpo_MVMNT where cin_no = :cinNo and slno=(select max(slno) from fpo_mvmnt where ext_dt is null and cin_no=:cinNo)")
	String getrole(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select cin_dt from fpo_MAIN where cin_no = :cinNo")
	Date getcindtmvmnt(@Param("cinNo") String cinNo);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE FPO_MVMNT det set det.EXT_DT = to_date(:dt,'DD/MM/YYYY HH24:MI:SS') where det.cin_no = :CINNO and slno=(select max(slno) from fpo_MVMNT where cin_no=:CINNO and ext_dt is null) and ext_dt is null ")
	void updextdtstr(@Param("CINNO") String CINNO, @Param("dt") String  dt);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE FPO_MVMNT det set cus_site = :clrsite where slno=(select max(slno) from fpo_MVMNT where cin_no=:CINNO)")
	void updclrSite(@Param("CINNO") String CINNO,@Param("clrsite") String clrSite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE FPO_MVMNT det set det.stage = :stage where det.cin_no = :CINNO and slno=(select max(slno) from fpo_MVMNT where cin_no=:CINNO and ext_dt is null) and ext_dt is null ")
	void updextStage(@Param("CINNO") String CINNO,@Param("stage") String  stage);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE FPO_MVMNT det set det.EXT_DT =:dt where det.cin_no = :CINNO and slno=(select max(slno) from fpo_MVMNT where cin_no=:CINNO and ext_dt is null) ")
	void updextdt(@Param("CINNO") String CINNO,  @Param("dt") Date dt);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "DELETE  FPO_MVMNT where cus_site = :cussite and  officer_id=:offid and  role='PIN' and ent_dt is not null and ext_dt is null")
	void deldupexm(@Param("cussite") String cussite, @Param("offid") String  offid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "DELETE  FPO_MVMNT where cus_site = :cussite and  officer_id=:offid and  role='PSU' and ent_dt is not null and ext_dt is null")
	void deldupsup(@Param("cussite") String cussite, @Param("offid") String  offid);
	
//	@Query(nativeQuery = true, value = "select distinct slno from fpo_mvmnt where cus_site= :cs and cin_no= :cinno and slno is not null")
//	Long getfpomvmntSlno(@Param("cs") String cs, @Param("cinno") String cinno);
	
	//changed for getting max value slno in recall
	@Query(nativeQuery = true, value = "select max(slno) as max_slno  from fpo_mvmnt where cus_site= :cs and cin_no= :cinno and slno is not null")
	Long getfpomvmntSlno(@Param("cs") String cs, @Param("cinno") String cinno);
	
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_mvmnt where cin_no= :id and stage='P5'")
	Long getDetainedCount(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "DELETE  FPO_MVMNT where cus_site = :cussite and  officer_id=:offid and  role='PAO' and ent_dt is not null and ext_dt is null")
	void delduppao(@Param("cussite") String cussite, @Param("offid") String  offid);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE FPO_MVMNT set EXT_DT = :date , STAGE = 'P1' where  id = (select max(id) from fpo_MVMNT where CIN_NO = :cinno)")
	void updateSpeedPosttoAssforFPO_MVMNT(@Param("cinno") String cinno, @Param("date") Date date);
	
	
	@Query(nativeQuery = true, value =  "select sno,module,task,entry_dt,exit_dt,actby,actid,entdt from"
			+ "(select 1 sno,'EAD' module ,'SUBMISSION'  task, to_char(cin_dt,'DD/MM/YYYY HH24:MI:SS')  entry_dt,'-' exit_dt,'SYSTEM' actby,'SYSTEM' actid,cin_dt entdt  from fpo_main where item_id=:itemid \r\n"
			+ " union \r\n"
			+ " select 2 sno,'EAD' module,'CUSRSP1 - EAD ACKNOWLEDGEMENT TO INDIA POST' task,'' entry_dt,  to_char(sent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'SYSTEM' actby,'SYSTEM' actid, sent_dt entdt from c_cusrsp where item_id=:itemid and sent_dt=(select min(sent_dt) from c_cusrsp where item_id=:itemid)\r\n"
			+ " union \r\n"
			+ " select 3 sno,'EAD' module,'PAO / ASSESSMENT ' task,to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id \r\n"
			+ " and item_id=:itemid and a.role='PAO' and slno=(select min(slno) from fpo_mvmnt where item_id=:itemid) \r\n"
			+ " union \r\n"
			+ " select 4 sno,'EAD' module,'EAD DECISION ' task,to_char(qry_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'-' exit_dt,emp_name actby,off_id actid,qry_dt entdt from fpo_qry_deci  a, ops$dir.d_emp b where a.off_id=b.user_id \r\n"
			+ " and item_id=:itemid and a.role='PAO' and qry_type='E4'\r\n"
			+ " union \r\n"
			+ " select 5 sno,'EAD' module, 'CUSRSP2 - EAD CUSTOMS DECISION TO INDIA POST' task,'' ent_dt,to_char(max(sent_dt),'DD/MM/YYYY HH24:MI:SS') exit_dt,'SYSTEM' actby,'SYSTEM' actid, max(sent_dt) entdt from cusrsp_sent a , fpo_main b  where a.cin_no=b.cin_no and b.item_id=:itemid and length(category)=1 \r\n"
			+ " union \r\n"
			+ " select 6 sno,substr(c.dcall_no,1,3) module,'PAO / QUERY RAISED ' task,'' ent_dt,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id  actid,gen_dt entdt from fpo_mvmnt a, ops$dir.d_emp b,dcallqry_gen c where a.officer_id=b.user_id \r\n"
			+ " and a.item_id=:itemid and a.role='IMP' and stage='P2' and a.item_id=c.item_id and c.id = ( select max(id) from dcallqry_gen where item_id=a.item_id)"
			+ " union \r\n"
			+ " select 7 sno,substr(dcall_no,1,3) module,'DCALL LETTER SPEEDPOST DELIVERY TO THE IMPORTER' task,'' ent_dt,decode(to_char(speedpost_dt,'DD/MM/YYYY HH24:MI:SS'),null,'-',to_char(speedpost_dt,'DD/MM/YYYY HH24:MI:SS')) exit_dt,'SYSTEM' actby,'SYSTEM' actid, speedpost_dt entdt from dcallqry_gen  where item_id='RG065685619UA' \r\n"
			+ " union \r\n"
			+ " select 8 sno,substr(b.dcall_no,1,3) module,decode(rply_off_id,null,'QUERY REPLIED','QUERY REPLY RECEIVED THROUGH POST') task, to_char(rply_date,'DD/MM/YYYY HH24:MI:SS') entry_dt, '-' exit_dt,decode(rply_off_id,null,'Importer',emp_name) actby,decode(rply_off_id,null,'Importer',qry_off_id) actid,rply_date entdt \r\n"
			+ " from fpo_query a left join dcallqry_gen b on a.item_id=b.item_id left join dcall_doc_info c on b.dcall_no=c.dcall_no, ops$dir.d_emp k where a.item_id=:itemid  and a.qry_off_id=k.user_id and rply_date is not null and qry_reply='Y' \r\n"
			+ " union \r\n"
			+ " select 9 sno,'AAA' module,'PAO / ASSESSMENT ' task,to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt, to_char(ext_dt,'DD/MM/YYYY') exit_dt,emp_name actby,officer_id  actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id \r\n"
			+ " and item_id=:itemid and a.role='PAO' and slno=(select max(slno) from fpo_mvmnt where item_id=:itemid and role='PAO' and stage='N1') \r\n"
			+ " union \r\n"
			+ " select 10 sno,'AAF' module,'ARTICLE REACHED INDIA - OOE' task,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'-' exit_dt,'SYSTEM' actby,'SYSTEM' actid, recd_event_dt entdt \r\n"
			+ " from article_arr_info a where article_id=:itemid and status is null "
			+ " union \r\n"
			+ " select 11 sno,'AAF' module,'ARTICLE REACHED DESITNATION FPO' task,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'-' exit_dt,'SYSTEM' actby,'SYSTEM'  actid, recd_event_dt entdt\r\n"
			+ " from article_arr_info a where article_id=:itemid and status is null and substr(ooe,1,5)=:cussite \r\n"
			+ " union \r\n"
			+ " select 12 sno,'AAF' module,'ARTICLE REACHED DESITNATION FPO' task,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'-' exit_dt,'SYSTEM' actby,'SYSTEM' actid, recd_dt entdt \r\n"
			+ " from articlearr_fpo_info a where article_id=:itemid and status is null and recd_fpo= :cussite \r\n"
			+ " union \r\n"
			+ " select 13 sno,'AAF' module,'PBS / BAG SCAN OR ARTICLE ARRIVAL CONFIRMATION',to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')entry_dt,'-' exit_dt,emp_name actby,off_id  actid,scan_dt entdt from fpo_scan_info a , ops$dir.d_emp b, articlearr_fpo_info c where a.off_id=b.user_id and  c.article_id=:itemid and c.bag_no=a.bag_no \r\n"
			+ " union \r\n"
			+ " select 14 sno,'AAF' module,'PAO / ASSESSMENT ' task,to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id  actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id \r\n"
			+ " and item_id=:itemid and a.role='PAO' and slno=(select min(slno) from fpo_mvmnt  where item_id=:itemid and role='PAO' and stage<>'E1' and stage<>'N1' and stage<>'O1') \r\n"
			+ " union \r\n"
			+ " select 15 sno,'AAF' module,'PIN / EXAMINATION' task,  to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id  actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id and item_id=:itemid and a.role='PIN' and slno=(select max(slno) from fpo_mvmnt where item_id=:itemid and role='PIN') \r\n"
			+ " union \r\n"
			+ " select 15 sno,'AAF' module,'PSU / OOC QUEUE' task,  to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id  actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id and item_id=:itemid and a.role='PSU' and slno=(select max(slno) from fpo_mvmnt where item_id=:itemid and role='PSU') \r\n"
			+ " union \r\n"
			+ " select 16 sno,'AAF' module,'DETAINED' task,  to_char(det_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,'-' exit_dt,emp_name actby,det_id actid,det_dt entdt from fpo_status a, ops$dir.d_emp b where a.det_id=b.user_id and item_id=:itemid and det_dt is not null \r\n"
			+ " union \r\n"
			+ " select 17 sno,'AAF' module,'PAO / ASSESSMENT ' task,to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id  actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id  \r\n"
			+ " and item_id=:itemid and a.role='PAO' and slno=(select max(slno) from fpo_mvmnt  where item_id=:itemid and role='PAO' and stage='P1' and slno <> 1) \r\n"
			+ " union \r\n"
			+ " select 18 sno,'AAF' module,'OOC' task,  to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id and item_id=:itemid and a.role='OOC' and slno=(select min(slno) from fpo_mvmnt where item_id=:itemid and role='OOC') and ext_dt is not null \r\n"
			+ " union \r\n"
			+ " select 19 sno,'AAF' module,a.role || ' OOC CANCELLATION ' task,to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id actid,ent_dt entdt \r\n"
			+ "  from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id \r\n"
			+ " and item_id=:itemid  and stage='O1' and (a.role='PAO' or a.role='PAC') \r\n"
			+ " union \r\n"
			+ " select 24 sno,'AAF' module,'OOC' task,  to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') entry_dt,to_char(ext_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,emp_name actby,officer_id actid,ent_dt entdt from fpo_mvmnt a, ops$dir.d_emp b where a.officer_id=b.user_id and item_id=:itemid and a.role='OOC' and slno=(select max(slno) from fpo_mvmnt where item_id=:itemid and role='OOC')   and ext_dt is  null \r\n"
			+ " union \r\n"
			+ " select 26 sno,'AAF' module,'CUSRSP3 - FINAL CUSTOMS MESSAGE TO INDIA POST' task,'' entry_dt,to_char(sent_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,'SYSTEM' actby,'SYSTEM' actid,sent_dt entdt from cusrsp_sent a , fpo_main b  where a.cin_no=b.cin_no and b.item_id=:itemid and category='OOC' and sent_dt is not null and sent_dt=(select max(sent_dt) from cusrsp_sent where cin_no=b.cin_no and category='OOC' and sent_dt is not null)\r\n"
			+ " union \r\n"
			+ " select 25 sno,'AAF' module,'CUSRSP3 - DETENTION MESSAGE TO INDIA POST' task,'' entry_dt,to_char(sent_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,'SYSTEM' actby,'SYSTEM' actid,sent_dt entdt from cusrsp_sent a , fpo_main b  where a.cin_no=b.cin_no and b.item_id=:itemid and category='DET' and sent_dt is not null and sent_dt=(select max(sent_dt) from cusrsp_sent where cin_no=b.cin_no and category='DET'  and sent_dt is not null)\r\n"
			+ " union \r\n"
			+ " select 27 sno,'DEL' module,'DELIVERY ACKNOWLEDGEMENT' task,'-' entry_dt,to_char(deli_dt,'DD/MM/YYYY HH24:MI:SS') exit_dt,'SYSTEM' actby,'SYSTEM' actid,deli_dt entdt from fpo_delivery  where item_id=:itemid) order by  entdt" )
	List<Collection>  getfpoMovementfull(@Param("itemid") String itemid, @Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select fm.slno as sNo,to_char(fm.ent_dt,'dd/MM/yyyy HH:MI:SS AM') as entryDate,to_char(fm.ext_dt,'dd/MM/yyyy HH:MI:SS AM') as exitDate,fm.officer_id as officerId,fm.role as roleName,fs.view_action as status from fpo_mvmnt fm left join fpo_stages fs on fm.stage=fs.stage_code where fm.cin_no=:cinId and fm.officer_id is not null and fm.role is not null order by fm.slno")
	List<Collection> getFpoMovementsByCinId(@Param("cinId") String cinId);
}
