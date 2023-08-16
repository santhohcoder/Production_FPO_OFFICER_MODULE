package com.demo.fpo.repos;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.NSM_procesdata;
import com.demo.fpo.bean.ArrivalHistoryBean;
import com.demo.fpo.bean.ArticleArrivalInfoBean;
import com.demo.fpo.bean.ArticleDatas;
import com.demo.fpo.bean.BagArticleBean;
import com.demo.fpo.bean.MisReportPBSOfficersArticles;
import com.demo.fpo.bean.BagScanBean;
import com.demo.fpo.bean.CountrywiseOOCPending;
import com.demo.fpo.bean.DepartmentCommentsBean;
import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.EADInboundArticles;
import com.demo.fpo.bean.FpoScndQryData;
import com.demo.fpo.bean.OocDeliveryBean;
import com.demo.fpo.bean.QueryReplyHistoryBean;
import com.demo.fpo.bean.TotalCountMailClass;
import com.demo.fpo.bean.TotalIcCount;
import com.demo.fpo.bean.TotalImportCounts;
import com.demo.fpo.bean.ViewArticle;
import com.demo.fpo.bean.ArticleArrInfo;
import com.demo.fpo.model.DUTY_CALC_DETAILS;
import com.demo.fpo.model.DeliveryArticlesPendingColumns;
import com.demo.fpo.model.FPO_MAIL;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.SelectTag;
import com.demo.fpo.model.grandtotinfo;


@Repository
public interface FPO_MAINrepost extends JpaRepository<FPO_MAIN, Long> {
	
	// --------------------------------------------------------- added BY VEEMAN on 22/04/2023 -------------------------------------------- 
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null ")
	Long getAddlQryRlyCount(@Param("cinNo") String cinNo);
	// --------------------------------------------------------- ---------------------- -------------------------------------------- 

	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null and qry_id = (select max(qry_id) from fpo_addl_qry where CIN_NO = :cinNo and qry_reply is not null) ")
	Long getAddlQryRlyCountforpac(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery=true, value="select remarks from fpo_exam_findings where  cin_no=:cinno  and item_no is null and id=(select max(id) from fpo_exam_findings where cin_no=:cinno and item_no is null)")
	String getexamdepcmts(@Param("cinno") String cinno);
	
	@Query(nativeQuery=true, value="select max(sent_id)+1 from cusrsp_sent")
    Long getmaxidrspsent();
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET off_id = :offid where CIN_NO = :id and off_id is null")
	void allotoffid(@Param("id") String id, @Param("offid") String offid);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_status set apr_dt=:curDate,apr_id=:offId where cin_no=:id and cus_site=:cussite")
	void updfpostatusapr(@Param("curDate") Date curDate, @Param("offId") String offId,@Param("id") String id, @Param("cussite") String cussite);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_status set acl_dt=:curDate,acl_id=:offId where cin_no=:id and cus_site=:cussite")
	void updfpostatusacl(@Param("curDate") Date curDate, @Param("offId") String offId,@Param("id") String id, @Param("cussite") String cussite);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update cusitm_load SET mail_sent_dt = :dt where mail_sent_dt is null and success='Y'")
	void updcusitmmail(@Param("dt") Date dt);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  ops$dir.d_iec_dgft where iec=:iec")
	int getcouiec(@Param("iec") String iec);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  ops$dir.d_gst_new where gstin_id=:gst")
	int getcougstin(@Param("gst") String gst);
	
	@Query(nativeQuery = true, value = "SELECT clr_site from  fpo_main where item_id=:itemid")
	String getclrsite(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "SELECT count (*) FROM ops$dir.d_bank where part1cd=:adcod")
	int getadcode1(@Param("adcod") String adcod);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update c_cusrsp SET mail_sent_dt = :dt where mail_sent_dt is null and sent_dt is not null")
	void updcusrspmail(@Param("dt") Date dt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update articlearr_ooe_req SET mail_sent_dt = :dt where mail_sent_dt is null and success='Y'")
	void updarrooemail(@Param("dt") Date dt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update articlearr_fpo_req SET mail_sent_dt = :dt where mail_sent_dt is null and success='Y'")
	void updarrfpomail(@Param("dt") Date dt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update predes_fpo_req SET mail_sent_dt = :dt where mail_sent_dt is null and success='Y'")
	void updpredesmail(@Param("dt") Date dt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_deli_req SET mail_sent_dt = :dt where mail_sent_dt is null and success='Y'")
	void upddelimail(@Param("dt") Date dt);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET set_aside = 'Y' where CIN_NO = :id")
	void setaside(@Param("id") String id);
	
	
	@Query(nativeQuery = true, value = "select to_char(sysdate,'DD-MM-YYYY HH24:MI:SS') from dual")
	String getstdt();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det c set assval_insfrt = (select  round((tot_ass_val/totass_calc_val)*assess_val,2) from  fpo_main a, fpo_item_det b where a.item_id=b.item_id and b.item_no=c.item_no and a.cin_no=:id and a.cus_site=:cusite) where c.cin_no=:id and c.cus_site=:cusite")
	void updassvalinsfrtitmid(@Param("id") String id, @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set bcd_amt=(bcd_rta*assval_insfrt)*0.01  where cin_no=:id and cus_site=:cusite ")
	void updbcditmid(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'CDS API - CUSITM' MESSAGE_TYPE, to_char(fromdt,'DD/MM/YYYY HH24:MI:SS') FROMDT ,to_char(todt,'DD/MM/YYYY HH24:MI:SS') TODT,cdsrecords NO_RECORDS,to_char(last_executed,'DD/MM/YYYY HH24:MI:SS') EXECUTED_DATE from cusitm_load where success='Y'  and mail_sent_dt is null order by id ")
	List<Collection> getcusitmmesg();
	
	//santhosh,veem
	@Query(nativeQuery = true, value = "SELECT QRY_TYPE from  fpo_qry_deci where id=(select max(id) from  fpo_qry_deci where cin_no=:id)")	
	String getQryTypeDetails(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select 'CDS API - CUSRSP1' MESSAGE_TYPE,'-' FROMDT,'-'  TODT , count(*)  NO_RECORDS, to_char(sent_dt,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from c_cusrsp where sent_dt is not null and  mail_sent_dt is null and deci_reas_nm like '%SUBMISSION%SUCCESSFUL%' group by to_char(sent_dt,'DD/MM/YYYY HH24:MI')  order by to_char(sent_dt,'DD/MM/YYYY HH24:MI')")
	List<Collection> getcusrspmesg();
	
	@Query(nativeQuery = true, value = "select 'CDS API - CUSRSP2' MESSAGE_TYPE,'-' FROMDT,'-'  TODT , count(*)  NO_RECORDS, to_char(sent_dt,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from c_cusrsp where sent_dt is not null and  mail_sent_dt is null and deci_reas_nm like '%EAD%Decision%' group by to_char(sent_dt,'DD/MM/YYYY HH24:MI')  order by to_char(sent_dt,'DD/MM/YYYY HH24:MI')")
	List<Collection> getcusrspmesg2();
	
	@Query(nativeQuery = true, value = "select 'CDS API - CUSRSP3' MESSAGE_TYPE,'-' FROMDT,'-'  TODT , count(*)  NO_RECORDS, to_char(sent_dt,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from c_cusrsp where sent_dt is not null and  mail_sent_dt is null and deci_reas_nm like '%Final%Decision%' group by to_char(sent_dt,'DD/MM/YYYY HH24:MI')  order by to_char(sent_dt,'DD/MM/YYYY HH24:MI')")
	List<Collection> getcusrspmesg3();
	
	@Query(nativeQuery = true, value = "select 'ARTICLE ARRIVAL MESSAGE I - OOE' MESSAGE_TYPE , to_char(fromdt,'DD/MM/YYYY HH24:MI') FROMDT,to_char(todt,'DD/MM/YYYY HH24:MI')  TODT, recordcount  NO_RECORDS,to_char(updatedtime,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from articlearr_ooe_req where success='Y' and mail_sent_dt is null order by id")
	List<Collection> getarrooemesg();
	
	@Query(nativeQuery = true, value = "select 'ARTICLE ARRIVAL MESSAGE II - FPO' MESSAGE_TYPE , to_char(fromdt,'DD/MM/YYYY HH24:MI') FROMDT,to_char(todt,'DD/MM/YYYY HH24:MI')  TODT, recordcount  NO_RECORDS,to_char(updatedtime,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from articlearr_fpo_req where success='Y'  and mail_sent_dt is null order by id")
	List<Collection> getarrfpomesg(); 
	
	@Query(nativeQuery = true, value = "select 'DELIVERY BY INDIA POST' MESSAGE_TYPE , to_char(fromdt,'DD/MM/YYYY HH24:MI') FROMDT,to_char(todt,'DD/MM/YYYY HH24:MI')  TODT, recordcount  NO_RECORDS,to_char(updatedtime,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from fpo_deli_req where success='Y'  and mail_sent_dt is null order by id")
	List<Collection> getdelimesg();
	
	@Query(nativeQuery = true, value = "select 'PREDES MESSAGE' MESSAGE_TYPE , to_char(fromdt,'DD/MM/YYYY HH24:MI') FROMDT,to_char(todt,'DD/MM/YYYY HH24:MI')  TODT, recordcount  NO_RECORDS,to_char(updatedtime,'DD/MM/YYYY HH24:MI')  EXECUTED_DATE from predes_fpo_req where success='Y'  and mail_sent_dt is null order by id")
	List<Collection> getpredesmesg();	
	
	@Query(nativeQuery = true, value = "sELECT user_id,fpo_cus_site from ops$dir.d_emp a where (user_account_control='D' or fpo_cus_site is null) and user_id in (select user_id from ops$dir.d_emp_roles where user_id=a.user_id)")
	List<Collection> getmodempdeldata();
	
	//@Query(nativeQuery = true, value = "sELECT user_id,fpo_cus_site from ops$dir.d_emp a where user_account_control='A' and user_id in (select user_id from ops$dir.d_emp_roles where user_id=a.user_id and end_dt is null) and fpo_cus_site not in ( select cus_site from ops$dir.d_emp_roles where end_dt is null ) ")
	@Query(nativeQuery = true, value = "sELECT a.user_id,fpo_cus_site,b.cus_site,b.end_dt from ops$dir.d_emp a , ops$dir.d_emp_roles b where user_account_control='A' and a.fpo_cus_site != decode(b.cus_site,null,a.fpo_cus_site,b.cus_site) and a.user_id=b.user_id and b.end_dt is null group by a.user_id,fpo_cus_site,b.cus_site,b.end_dt")
	List<Collection> getmodempchgdata();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set sw_amt=(sw_rta*bcd_amt)*0.01 where cin_no=:id and cus_site=:cusite ")
	void updswitmid(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set igst_amt=igst_rta*(bcd_amt+sw_amt+assval_insfrt)*0.01 where cin_no=:id and cus_site=:cusite ")
	void updigstitmid(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_ITEM_DET set  duty= round(bcd_amt+igst_amt+sw_amt,2) where cin_no=:id and cus_site=:cusite ")
	void upddutyitmid(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_MAIN set  totass_calc_val = (select sum(assess_val) from  fpo_item_det where  cin_no=:id and cus_site=:cusite) where cin_no=:id and cus_site=:cusite")
	void updtotasscalcval(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_MAIN set  tot_ass_val =  totass_calc_val+frt_calc_val+ins_calc_val  where cin_no=:id and cus_site=:cusite")
	void updtotassval(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_MAIN set  tot_duty=(select round(sum(duty),2) from  fpo_item_det where cin_no=:id and cus_site=:cusite ) where cin_no=:id and cus_site=:cusite")
	void updtotduty(@Param("id") String id,  @Param("cusite") String cusite);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_MAIN set  tot_amt_payable=:totdutypay where cin_no=:id and cus_site=:cusite")
	void upddutypay(@Param("id") String id,  @Param("cusite") String cusite, Float totdutypay);

	@Query(nativeQuery = true, value = "SELECT EMP_NAME FROM ops$dir.d_emp a, ops$dir.d_emp_roles b where a.emp_no=b.user_id and b.user_id=:offid and b.role_name=:role and b.cus_site=:cusite  and user_account_control='A'")
	String getoffname(@Param("offid") String offid,  @Param("cusite") String cusite, @Param("role") String role);
	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),decode(p.recp_id,null,a.recp_id,p.recp_id),  recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code and decode(p.recp_id,null,a.recp_id,p.recp_id) is null and arr_fpo is  null and ooe is not null and a.recp_id is null  and substr(ooe,1,5)= substr(:cusite,1,5) and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null")
	List<Collection> getpen1data( @Param("cusite") String cusite);	
	
	@Query(nativeQuery = true, value = "select a.id, a.BE_NO, to_char(a.BE_DT,'dd/mm/yyyy') as BE_DT ,to_char(a.CIN_DT,'dd/mm/yyyy') as CIN_DT, to_char(a.CHALLAN_DT,'dd/mm/yyyy') as CHALLAN_DT,a.IEC,a.GSTIN_ID, a.ADCODE,a.SCHEME_CD,a.LICENSE_NO,a.PERMISSION_NO,a.EDI_SITE,a.CHALLAN_NO,a.CIN_NO,a.ITEM_ID,a.ENTERED_BY,to_char(a.ENTERED_DT,'dd/mm/yyyy') as ENTERED_DT,b.EMP_NAME from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on (b.user_id=a.entered_by) where user_account_control='A'")
	List<Collection> getpostalediq();

	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
	+"t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
	+"FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
	+"articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
	+"and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
	+"and (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5)) and arr_fpo is null order by t1.job_no")
	List<Collection> getpenartarrscanfpo( @Param("cuSite") String cuSite);	
	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+"t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+"FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+"articlearr_fpo_info f on t1.item_id=f.article_id left join ops$dir.d_cntry_cd y on  t1.send_cntry_cd=y.cntry_cd where t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and arr_india is null and arr_fpo is null and t1.cus_site is not null order by t1.cus_site, t1.job_no")
			List<Collection> getpenartarrscanooe( @Param("cuSite") String cuSite);	
	
	
	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+" and t1.mail_class_cd=:mc and t1.send_cntry_cd=:cntry and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatamccntry(@Param("mc") String mc,@Param("cntry") String cntry, @Param("cuSite") String cuSite);	
	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+"  and t1.send_cntry_cd=:cntry and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatacntry(@Param("cntry") String cntry, @Param("cuSite") String cuSite);	
	
	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+" and t1.mail_class_cd=:mc and t1.send_cntry_cd=:cntry and \r\n"
			+" to_date(substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),'DD/MM/YYYY') between to_date(:frdt,'DD/MM/YYYY') and to_date(:todt, 'DD/MM/YYYY') and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendataall(@Param("mc") String mc,@Param("cntry") String cntry, @Param("cuSite") String cuSite,@Param("frdt") String frdt,@Param("todt") String todt);	
	
	

	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+" and t1.mail_class_cd=:mc and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatamc(@Param("mc") String mc, @Param("cuSite") String cuSite);	

	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+"  and t1.send_cntry_cd=:cntry and \r\n"
			+" to_date(substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),'DD/MM/YYYY') between to_date(:frdt,'DD/MM/YYYY') and to_date(:todt, 'DD/MM/YYYY') and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatacntrydt(@Param("cntry") String cntry, @Param("cuSite") String cuSite,@Param("frdt") String frdt,@Param("todt") String todt);	

	
	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+" and t1.mail_class_cd=:mc and \r\n"
			+" to_date(substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),'DD/MM/YYYY') between to_date(:frdt,'DD/MM/YYYY') and to_date(:todt, 'DD/MM/YYYY') and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatamcdt(@Param("mc") String mc, @Param("cuSite") String cuSite,@Param("frdt") String frdt,@Param("todt") String todt);	

	@Query(nativeQuery = true, value = "select distinct article_id articleId, bag_no bagno,bag_type,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS'),off_id,scan_report,cus_site from fpo_scan_info where article_id=:itemid and cus_site=:cusite and bag_type='R' and (scanned='M' or scanned='O') \r\n"
       		+ " union \r\n"
       		+ "select distinct article_id, bag_no,bag_type,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS'),off_id,scan_report,cus_site from fpo_scan_info where article_id=:itemid and cus_site=:cusite and bag_type='B' and (scanned='M' or scanned='O')")
	List<Collection> getbaginfo( @Param("itemid") String itemid, @Param("cusite") String cusite);

	@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t3.category,\r\n"
			+" t5.codedesc,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),p.recp_id predesrecp_id, a.recp_id artarrrecp_id,   recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no\r\n"
			+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
			+" articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null\r\n" 
			+" and c.site_code=:cuSite and decode(clr_site,null,t1.cus_site,clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null \r\n"
			+"  and \r\n"
			+" to_date(substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),'DD/MM/YYYY') between to_date(:frdt,'DD/MM/YYYY') and to_date(:todt, 'DD/MM/YYYY') and \r\n"
			+" (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no")
	List<Collection> getfiltpendatadt(@Param("cuSite") String cuSite,@Param("frdt") String frdt,@Param("todt") String todt);	

	
	
	
	//@Query(nativeQuery=true, value="SELECT t1.item_id, t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd,t5.codedesc,t3.category,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),decode(p.recp_id,null,a.recp_id,p.recp_id), recd_fpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS'),bag_no FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_predes_info p on t1.item_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join articlearr_fpo_info f on t1.item_id=f.article_id where  t1.send_cntry_cd=?1 and t1.mail_class_cd=?2 and t1.cus_site=?3 and t1.mail_class_cd=t5.code and decode(p.recp_id,null,a.recp_id,p.recp_id) is null and ((arr_india is null and arr_fpo is not null) or (arr_india is not null and arr_fpo is null)) and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null ")
	@Query(nativeQuery=true, value="select a.article_id,substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),origcntrycd||' - '||cntry_nm,mail_class,c.cus_site,a.ooe,to_char(a.recd_event_dt,'DD/MM/YYYY'),b.recp_id from article_arr_info a left join  article_predes_info b on a.article_id=b.article_id, fpo_main c,ops$dir.d_cntry_cd y  where c.send_cntry_cd=y.cntry_cd and a.article_id=c.item_id and a.recp_id is null and b.recp_id is not null and substr(OOE,1,5)=substr(:cusite,1,5) and origcntrycd=:cntry and mail_class=:mc and arr_scan is null and arr_fpo is null ")
	List<Collection> getfiltpen1predesooedata(@Param("cntry") String cntry, @Param("mc") String mc,  @Param("cusite") String cusite);	
	
	@Query(nativeQuery=true, value="select a.article_id,substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),origcntrycd||' - '||cntry_nm,mail_class,c.cus_site,a.ooe,to_char(a.recd_event_dt,'DD/MM/YYYY'),a.recp_id from article_arr_info a left join  article_predes_info b on a.article_id=b.article_id, fpo_main c , ops$dir.d_cntry_cd y  where c.send_cntry_cd=y.cntry_cd and  a.article_id=c.item_id and decode(a.recp_id,null,b.recp_id,a.recp_id) is null and substr(OOE,1,5)=substr(:cusite,1,5) and origcntrycd=:cntry and mail_class=:mc and arr_scan is null and arr_fpo is null and ooe is not null ")
	List<Collection> getfiltpen1arrooedata(@Param("cntry") String cntry, @Param("mc") String mc,  @Param("cusite") String cusite);	
	
	
	//@Query(nativeQuery=true, value="SELECT t1.item_id,t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd,t5.codedesc,t3.category,decode(clr_site,null,t1.cus_site,clr_site), OOE,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS'),recp_id FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join article_arr_info a on t1.item_id=a.article_id where t1.mail_class_cd=t5.code and  t1.arr_fpo is null and arr_india is not null and t1.cus_site= ?1 and a.status is null and t1.nature_type_cd=t3.code and arr_scan is null")
	//List<Collection> getpen2data( @Param("cusite") String cusite);	
	
	@Query(nativeQuery=true, value="SELECT t1.item_id,t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.send_cntry_cd||' - '||cntry_nm,t5.codedesc,t3.category,decode(clr_site,null,t1.cus_site,clr_site) FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1  ,ops$dir.d_cntry_cd y  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code and  arr_india is null and arr_fpo is null and t1.cus_site= :cusite and t1.nature_type_cd=t3.code and arr_scan is null")
	List<Collection> getpen3data( @Param("cusite") String cusite);	
	
	
	@Query(nativeQuery=true, value= "select 1 id,CUSCount,Av,duty,stscunt,assAv,assDuty,assAmtchrg,assdutyfg,ooccunt,oocAv,oocDuty,oocAmtchrg,oocdutyfg,detcunt,detAv,detDuty,detAmtchrg,detdutyfg,delvycunt,delvyAv,delvyDuty,delvyAmtchrg,delvydutyfg,undelycunt,undelyAv,undelyDuty,undelyAmtchrg,undelydutyfg  from (select (Select count(*) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as CUSCount,\r\n"
					+ "(Select sum(tot_ass_val) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as Av, (Select sum(tot_amt_payable) from fpo_main where trunc(job_dt) between :frmdt and :todt and cus_site= :getsite) as duty, \r\n"
					+ "(Select count(*) from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite) as stscunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ass_dt) between :frmdt and :todt and cus_site= :getsite)) as assdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite) as ooccunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(ooc_dt) between :frmdt and :todt and cus_site= :getsite)) as oocdutyfg,\r\n"
					+ "(Select count(*) from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite) as detcunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(det_dt) between :frmdt and :todt and cus_site= :getsite)) as detdutyfg, \r\n"
					+ "(Select count(*) from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite) as delvycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_status where trunc(delvy_dt) between :frmdt and :todt and cus_site= :getsite)) as delvydutyfg, \r\n"
					+ "(select count(*) from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')as undelycunt,\r\n"
					+ "(select sum(assess_val) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyAv, \r\n"
					+ "(select sum(duty) from fpo_item_det where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyDuty,\r\n"
					+ "(select sum(tot_amt_payable) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelyAmtchrg,\r\n"
					+ "(select sum(tot_duty_fg) from fpo_main where item_id in (select item_id from fpo_delivery where trunc(deli_dt) between :frmdt and :todt and deli_status not in 'DL')) as undelydutyfg \r\n"
					+ "from dual)")
	List<NSM_procesdata> getprecedata(@Param("frmdt") String frmdt, @Param("todt") String todt, @Param("getsite") String getsite);

	
	
//	@Query(nativeQuery=true, value="select cntry_cd,cntry_cd || ' - ' || cntry_nm from ops$dir.d_cntry_cd where cntry_cd in (select send_cntry_cd from fpo_main where arr_scan is null) order by cntry_cd")
	@Query(nativeQuery=true, value="select cntry_cd,cntry_cd || ' - ' || cntry_nm from ops$dir.d_cntry_cd  order by cntry_cd")
	List<Collection> getcntrylist();
	
	@Query(nativeQuery=true, value="select site_code,site_code || ' - ' || site_name from ops$dir.ooe_sites where site_active='Y' and site_code!='INNSA5'  order by site_name")
	List<Collection> getfpolist();
	
	@Query(nativeQuery=true, value="select code,DISPBAGDETAIL from ops$dir.pdi_mail_class_codes where code in (select mail_class_cd from fpo_main where arr_scan is null group by mail_class_cd)")
	List<Collection> getmclist();
	
	@Query(nativeQuery=true, value="select iec,iec_name from ops$dir.d_iec_dgft")
	List<Collection> getieclist();
	
	@Query(nativeQuery = true, value = "select codedesc from fpo_main a left join ops$dir.pdi_mail_class_codes b on a.mail_class_cd=b.code  where a.item_id =:item and a.cus_site=:cuSite")	
	String getmailcl(@Param("item") String item,@Param("cuSite") String cuSite);
	
	@Query(nativeQuery=true, value="select count(*) from fpo_ass_pao_cmts where stage=:stage and cin_no=:cinno and cus_site=:cusite")
	int getcoupaoass(@Param("cinno") String cinno, @Param("stage") String stage,  @Param("cusite") String cusite);
	
	@Query(nativeQuery=true, value="select count(*) from fpo_ass_pao_cmts where  cin_no=:cinno and cus_site=:cusite")
	int getcoupaoass(@Param("cinno") String cinno,  @Param("cusite") String cusite);
	
	@Query(nativeQuery=true, value="select max(seq_no) from fpo_ass_pao_cmts where  cin_no=:cinno and cus_site=:cusite")
	int getseqnopaoass(@Param("cinno") String cinno,  @Param("cusite") String cusite);
	
	@Query(nativeQuery=true, value="select cmts from fpo_ass_pao_cmts where  cin_no=:cinno and stage=:stage and cus_site=:cusite")
	String getpaodepcmts(@Param("cinno") String cinno, @Param("stage") String stage,  @Param("cusite") String cusite);
	
//	@Query(nativeQuery=true, value="select dep_cmts,off_id,to_char(entered_dt,'DD/MM/YYYY HH24:MI:SS') from fpo_ooc_findings where  cin_no=:cinno and cus_site=:cusite")
	@Query(nativeQuery=true, value="select dep_cmts from fpo_ooc_findings where  cin_no=:cinno and cus_site=:cusite")
	String getoocdepcmts(@Param("cinno") String cinno,  @Param("cusite") String cusite);
	
	@Query(nativeQuery=true, value="select emp_name from fpo_ooc_findings a, ops$dir.d_emp b where  cin_no=:cinno and cus_site=:cusite and a.off_id=b.user_id  and user_account_control='A'")
	String getoocdepcmtsid(@Param("cinno") String cinno,  @Param("cusite") String cusite);

	@Query(nativeQuery=true, value="select to_char(entered_dt,'DD/MM/YYYY HH24:MI:SS') from fpo_ooc_findings where  cin_no=:cinno and cus_site=:cusite")
	String getoocdepcmtsdt(@Param("cinno") String cinno,  @Param("cusite") String cusite);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_ass_pao_cmts set cmts=:cmts, entry_dt=:dt where stage=:stage and cin_no=:cinno and cus_site=:cusite")
	void updcoupaoass(@Param("cinno") String cinno, @Param("stage") String stage,  @Param("cusite") String cusite, @Param("cmts") String cmts,@Param("dt") Date dt);
	
	@Query(nativeQuery=true, value="select BE_NO, BE_DT, IEC, GSTIN_ID, ADCODE, SCHEME_CD, LICENSE_NO, PERMISSION_NO, EDI_SITE, CHALLAN_NO, CHALLAN_DT from FPO_MANUAL_COMMERCIAL where item_id=:itemId ")
	List<Collection> getViewBEInfo(@Param("itemId") String itemId );
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET acl_offid = :offid where CIN_NO = :id and acl_offid is null and role='PAC'")
	void allotacloffid(@Param("id") String id, @Param("offid") String offid);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET role = 'PAO' where CIN_NO = :id")
	void updateRoleApr(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DECI SET role = 'PAO',off_id=:offid where CIN_NO = :id")
	void updateRoleAprqrydeci(@Param("id") String id,@Param("offid") String offid);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET role = 'PAC' where CIN_NO = :id")
	void updateRole(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET role = 'PAO' where CIN_NO = :id")
	void updatingRole(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET commercial = 'Y', arr_scan='Y'  where CIN_NO = :id")
	void updcommarrscan(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET commercial = null  where CIN_NO = :id")
	void updcommpers(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main WHERE cin_no=:id and ass_dt is not null")
	String getAssdate(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT max(item_no)+1 from  fpo_item_det WHERE cin_no=:id ")
	Long getnewitemno(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT max(currcd) from  fpo_item_det WHERE cin_no=:id ")
	String getcurrcd(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT send_cntry_cd from  fpo_main WHERE cin_no=:id ")
	String getcntrycd(@Param("id") String id);

	@Query(nativeQuery = true, value = "select rate_imp from ops$dir.d_exchange where curr_cd=:currcd and end_dt is  null and eff_dt = (select max(eff_dt) from ops$dir.d_exchange where curr_cd=:currcd)")
	Float getexrate(String currcd);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET ASS_DT = :utilDate, role = :role where CIN_NO = :id")
	void updateAssdate(@Param("id") String id, @Param("utilDate") Date utilDate, @Param("role") String role);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_SETASIDE SET REVO_DT = :utilDate where CIN_NO = :id and role = :role")
	void updrevokesetaside(@Param("id") String id, @Param("utilDate") Date utilDate, @Param("role") String role);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_MAIN SET SET_ASIDE = null where CIN_NO = :id")
	void updsetasidenull(@Param("id") String id);

	@Query(nativeQuery = true, value = "select x.cin_no,cindt,postdt,item_id,send_cntry_cd,codedesc,category,tot_ass_val,no_items,job_no,job_dt,priority from \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code and set_aside='Y' )x, \r\n"
	+ " (select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y  \r\n"
	+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getsetasideaprview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select x.cin_no,cindt,postdt,item_id,send_cntry_cd,codedesc,category,tot_ass_val,no_items,job_no,job_dt,priority from \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority  FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code and set_aside='Y')x,  \r\n"
	+ " (select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y  \r\n"
	+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
    List<Collection> getsetasideaclview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.code,t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or t1.role='PAO' and t1.acl_offid is null) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code  and set_aside is null order by  priority, job_no,job_dt ")
	List<Collection> getview( @Param("cusite") String cusite, @Param("offid") String offid);

	/*@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t4.user_id=?2 and instr(t4.mail_class_cd,'U') >0 and t1.mail_class_cd='U' and t1.mail_class_cd=t5.code and set_aside is null \r\n "
			+ "union "
			+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t4.user_id=?2 and instr(t4.mail_class_cd,'E') >0 and t1.mail_class_cd='E'  and t1.mail_class_cd=t5.code and set_aside is null \r\n "
			+ " union "
			+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t4.user_id=?2 and instr(t4.mail_class_cd,'C') >0 and t1.mail_class_cd='C' and t1.mail_class_cd=t5.code  and set_aside is null \r\n"
			+ " union "
			+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t4.user_id=?2 and instr(t4.mail_class_cd,'T') >0 and t1.mail_class_cd='T' and t1.mail_class_cd=t5.code  and set_aside is null order by 10,8,9 \r\n")
	List<Collection> getallotview( @Param("cusite") String cusite, @Param("offid") String offid);*/
	
	
	@Query(nativeQuery = true, value = "select x.cin_no,cindt,postdt,item_id,send_cntry_cd,codedesc,category,tot_ass_val,no_items,job_no,job_dt,priority from \r\n"
	+"(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'U') >0 and t1.mail_class_cd='U' and t1.mail_class_cd=t5.code and set_aside is null and t4.role_name='PAO' \r\n"
				+ "union \r\n"
				+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'E') >0 and t1.mail_class_cd='E'  and t1.mail_class_cd=t5.code and set_aside is null  and t4.role_name='PAO' \r\n"
				+ " union \r\n"
				+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'C') >0 and t1.mail_class_cd='C' and t1.mail_class_cd=t5.code  and set_aside is null   and t4.role_name='PAO' \r\n"
				+ " union \r\n"
				+ "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'T') >0 and t1.mail_class_cd='T' and t1.mail_class_cd=t5.code  and set_aside is null   and t4.role_name='PAO' order by 10,8,9)x, \r\n"
	+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n"
	+ "where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getallotview( @Param("cusite") String cusite, @Param("offid") String offid);


	
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, t5.codedesc, t3.category,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7,  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.mail_class_cd='U' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null   and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and   t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getltrview( @Param("cusite") String cusite, @Param("offid") String offid);
	
	//@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and mail_class_cd='U' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code and set_aside is null order by priority, job_no, job_dt ")
	//List<Collection> getltrview( @Param("cusite") String cusite, @Param("offid") String offid);
	//@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd, tot_ass_val, t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,  fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and mail_class_cd='U' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null  order by priority, job_no, job_dt ")
	//List<Collection> getltrview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT  maxassval from ops$dir.acl_assval where to_dt is null ")
	Float getaclassval();
	
	// --------------------------------------------------------- commented BY VEEMAN on 22/04/2023 ---------------------------------
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where substr(dcall_no,1,3)='AAF' AND cin_no=:cin_no")
	Long getaaagenInaaf(@Param("cin_no") String cin_no);
	// --------------------------------------------------------- ---------------------- -------------------------------------------- 


	/*@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and mail_class_cd='E' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code and set_aside is null order by priority, job_no, job_dt ")
	List<Collection> getemsview( @Param("cusite") String cusite, @Param("offid") String offid);*/
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, t5.codedesc, t3.category,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7,  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.mail_class_cd='E' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null   and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and   t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getemsview( @Param("cusite") String cusite, @Param("offid") String offid);

	/*@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and mail_class_cd='C' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= ?2)  and t1.nature_type_cd=t3.code  and t1.mail_class_cd=t5.code and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getparview( @Param("cusite") String cusite, @Param("offid") String offid);*/
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, t5.codedesc, t3.category,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,  ops$dir.d_emp_roles t7, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.mail_class_cd='C' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null  and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and   t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getparview( @Param("cusite") String cusite, @Param("offid") String offid);

	/*@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= ?1 and mail_class_cd='T' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= ?2) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getempview( @Param("cusite") String cusite, @Param("offid") String offid);*/
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, t5.codedesc, t3.category,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.mail_class_cd='T' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null   and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and   t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no  order by priority,job_no,job_dt ")
	List<Collection> getempview( @Param("cusite") String cusite, @Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,tot_duty,tot_amt_payable,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, arr_fpo,t5.codedesc, t3.category,tot_duty,tot_amt_payable,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and category='DOCUMENTS' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null  and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no  and tot_ass_val < :maxassval  and arr_fpo is null order by priority,job_no,job_dt ")
	List<Collection> getdocview( @Param("cusite") String cusite, @Param("offid") String offid, @Param("maxassval") float maxassval);
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,tot_ass_val,no_items,tot_duty,tot_amt_payable,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, arr_fpo,t5.codedesc, t3.category,tot_duty,tot_amt_payable,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and tot_ass_val < 1000 and tot_ass_val > 0 and category<>'GIFT' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null  and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n" 
			+ " where x.cin_no=y.cin_no and tot_ass_val < :maxassval  and arr_fpo is null  order by priority,job_no,job_dt ")
	List<Collection> getbulkview( @Param("cusite") String cusite, @Param("offid") String offid, @Param("maxassval") float maxassval);
	
	@Query(nativeQuery = true, value = "SELECT x.cin_no,  cindt, postdt,item_id,send_cntry_cd,codedesc, category,addr, no_items,itemdesc,tot_ass_val,tot_duty,tot_amt_payable,job_no,job_dt,priority FROM \r\n"
			+ "(SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, tot_ass_val, arr_fpo, t5.codedesc, t3.category,tot_duty,tot_amt_payable,t1.job_no,t1.job_dt,priority FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , ops$dir.d_emp_roles t7, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and category<>'GIFT' and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null))  and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code   and t1.mail_class_cd=t5.code  and set_aside is null  and instr(t7.mail_class_cd,t1.mail_class_cd)>0 and user_id=:offid and t7.role_name='PAO') x,\r\n"
			+ "(select count(*) no_items,LISTAGG(item_desc, '  ;  ') WITHIN GROUP (ORDER BY cin_no) itemdesc,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y, \r\n"
			+ " (select recp_name || ' ' || recp_add1 || ' ' || recp_add2 || ' ' || recp_city || ' ' || recp_zip addr,cin_no from fpo_main where cus_site=:cusite) z \r\n"
			+ " where x.cin_no=y.cin_no   and x.cin_no=z.cin_no  and tot_ass_val < :maxassval and tot_ass_val > 0 and arr_fpo is null order by priority,job_no,job_dt ")
	List<Collection> getbulkexpview( @Param("cusite") String cusite, @Param("offid") String offid, @Param("maxassval") float maxassval);


	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,decode(first_check,'Y','for First Check ','from AC')  FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO left join fpo_order t8 on t1.cin_no=t8.cin_no  WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role='PAO' and t1.acl_offid is not null) and t1.off_id=:offid and t1.nature_type_cd=t3.code  and t1.mail_class_cd=t5.code  and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getAPRview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,decode(first_check,'Y','for First Check ','from AC') FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO left join fpo_order t8 on t1.cin_no=t8.cin_no  WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='U' and (t1.role='PAO' and t1.acl_offid is not null) and t1.off_id=:offid  and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code  and set_aside is null order by priority, job_no, job_dt ")
	List<Collection> getAPRltrview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,decode(first_check,'Y','for First Check ','from AC') FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO left join fpo_order t8 on t1.cin_no=t8.cin_no  WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='E' and (t1.role='PAO' and t1.acl_offid is not null) and t1.off_id=:offid  and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code  and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getAPRemsview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,decode(first_check,'Y','for First Check ','from AC') FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO left join fpo_order t8 on t1.cin_no=t8.cin_no  WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='C' and (t1.role='PAO' and t1.acl_offid is not null) and t1.off_id=:offid  and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code  and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getAPRparview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,decode(first_check,'Y','for First Check ','from AC') FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO left join fpo_order t8 on t1.cin_no=t8.cin_no  WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='T' and (t1.role='PAO' and t1.acl_offid is not null) and t1.off_id=:offid  and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code   and set_aside is null  order by priority, job_no, job_dt ")
	List<Collection> getAPRempview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value =  "select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,t9.cou, \r\n"
			+ "decode(first_check,'Y','for First Check Approval',decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),decode(t4.cou,null,'for Query Approval','for Assessment / Query Approval'))) \r\n"
			+ "FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det group by cin_no) t9 on t1.cin_no=t9.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_query where qry_typ!='D' group by cin_no) t5 on t1.cin_no=t5.cin_no left join fpo_order t6 on t1.cin_no=t6.cin_no \r\n"
			+ "where t2.cin_no is null and t1.cus_site=:cusite and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code \r\n"
			+ "and set_aside is null   order by priority, job_no, job_dt")
	List<Collection> getACLview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value ="select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,t9.cou, \r\n"
			+ "decode(first_check,'Y','for First Check Approval',decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),decode(t4.cou,null,'for Query Approval','for Assessment / Query Approval'))) \r\n"
			+ "FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det group by cin_no) t9 on t1.cin_no=t9.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_query where qry_typ!='D' group by cin_no) t5 on t1.cin_no=t5.cin_no left join fpo_order t6 on t1.cin_no=t6.cin_no \r\n"
			+ "where t2.cin_no is null and t1.cus_site=:cusite and mail_class_cd='U'  and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code \r\n"
			+ "and set_aside is null   order by priority, job_no, job_dt")
	List<Collection> getACLltrview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,t9.cou, \r\n"
			+ "decode(first_check,'Y','for First Check Approval',decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),decode(t4.cou,null,'for Query Approval','for Assessment / Query Approval'))) \r\n"
			+ "FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det group by cin_no) t9 on t1.cin_no=t9.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_query where qry_typ!='D' group by cin_no) t5 on t1.cin_no=t5.cin_no left join fpo_order t6 on t1.cin_no=t6.cin_no \r\n"
			+ "where t2.cin_no is null and t1.cus_site=:cusite  and mail_class_cd='E'  and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code \r\n"
			+ "and set_aside is null   order by priority, job_no, job_dt")
	List<Collection> getACLemsview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,t9.cou, \r\n"
			+ "decode(first_check,'Y','for First Check Approval',decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),decode(t4.cou,null,'for Query Approval','for Assessment / Query Approval'))) \r\n"
			+ "FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det group by cin_no) t9 on t1.cin_no=t9.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_query where qry_typ!='D' group by cin_no) t5 on t1.cin_no=t5.cin_no left join fpo_order t6 on t1.cin_no=t6.cin_no \r\n"
			+ "where t2.cin_no is null and t1.cus_site=:cusite  and mail_class_cd='C'  and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code \r\n"
			+ "and set_aside is null   order by priority, job_no, job_dt")
	List<Collection> getACLparview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,t9.cou, \r\n"
			+ "decode(first_check,'Y','for First Check Approval',decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),decode(t4.cou,null,'for Query Approval','for Assessment / Query Approval'))) \r\n"
			+ "FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_item_det group by cin_no) t9 on t1.cin_no=t9.cin_no left join \r\n"
			+ "(select count(*) cou,cin_no from  fpo_query where qry_typ!='D' group by cin_no) t5 on t1.cin_no=t5.cin_no left join fpo_order t6 on t1.cin_no=t6.cin_no \r\n"
			+ "where t2.cin_no is null and t1.cus_site=:cusite  and mail_class_cd='T'  and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code \r\n"
			+ "and set_aside is null   order by priority, job_no, job_dt")
		List<Collection> getACLempview( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 \r\n"
			+ "LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or t1.role='PAO' and t1.acl_offid is null) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code  order by priority, job_no, job_dt ")
	List<Collection> getFiltered( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) from  fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or (t1.role ='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id=:offid)  and set_aside is null  ")
	Long getCountcusite( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select a.item_no,a.item_desc,decode(a.gen_cth,nvl(b.cth,a.gen_cth),a.gen_cth,b.cth),a.nou,a.assval_insfrt,a.duty,decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.bcd_amt,0),0),decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.igst_amt,0),0),decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.sw_amt,0),0),to_char(nvl(a.duty_amt_9804,0)+nvl(b.duty_amt_oth,0),'fm99999999990.90') from \r\n"
			+ "(select a.item_no,a.item_desc,a.gen_cth,a.nou,a.assval_insfrt,a.duty,a.bcd_amt,a.igst_amt,a.sw_amt,nvl(b.duty_amt,0) as duty_amt_9804 from  fpo_item_det  a left join  \r\n"
			+ "(select cin_no,sum(duty_amt) duty_amt,item_no from  fpo_item_det_othduty where substr(cth,1,4)='9804'  group by cin_no,item_no order by cin_no,item_no) b on   a.cin_no=b.cin_no \r\n"
			+ "where a.cin_no=:cinNo) a,\r\n"
			+ "(select a.item_no,a.item_desc,b.cth,a.nou,a.assval_insfrt,a.duty,nvl(b.duty_amt,0) as duty_amt_oth from  fpo_item_det  a left join  \r\n"
			+ "(select cin_no,sum(duty_amt) duty_amt,item_no,cth  from  fpo_item_det_othduty where substr(cth,1,4)!='9804'  group by cin_no,item_no,cth order by cin_no,item_no) b on  a.cin_no=b.cin_no \r\n"
			+ "where a.cin_no=:cinNo ) b \r\n" + "where a.item_no=b.item_no \r\n" + "order by 1")
	List<Collection> getoocitmdata(String cinNo);

	@Query(nativeQuery = true, value = "select recp_name,recp_add1||','|| recp_add2||','|| recp_city addrs,recp_emailid,recp_phone,gross_wt,to_char(nvl(tot_amt_payable,0),'fm99999999990.90'),to_char(nvl(b.penal_amt,0),'fm99999999990.90'),to_char(nvl(c.fine_amt,0),'fm99999999990.90') from  fpo_main a left join "
			+ "			(select cin_no,sum(penal_amt) penal_amt from  fpo_penal where cin_no=:cinNo group by cin_no)b on a.cin_no=b.cin_no left join"
			+ "			(select cin_no,sum(fine_amt) fine_amt from  fpo_fine where cin_no=:cinNo  group by cin_no)c  on a.cin_no=c.cin_no "
			+ "			where a.cin_no=:cinNo ")

	List<Collection> getoocmainitmdata(String cinNo);

	@Query(nativeQuery = true, value = "Select * from duty_calc_details ")
	List<DUTY_CALC_DETAILS> getdutycalcdetails();

	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and mail_class_cd='U' and (t1.role is null or t1.role ='PAO' ) and (t1.off_id is null or t1.off_id=:offid)  and set_aside is null  ")
	Long getcoultr( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and mail_class_cd='E' and (t1.role is null or t1.role ='PAO' ) and (t1.off_id is null or t1.off_id=:offid)  and set_aside is null ")
	Long getcouems( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and mail_class_cd='C' and (t1.role is null or t1.role ='PAO' ) and (t1.off_id is null or t1.off_id=:offid)  and set_aside is null  ")
	Long getcoupar( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and mail_class_cd='T' and (t1.role is null or t1.role ='PAO' ) and (t1.off_id is null or t1.off_id=:offid)  and set_aside is null  ")
	Long getcouemp( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and t1.role='PAO' and t1.off_id=:offid  and t1.acl_offid is not null and set_aside is null ")
	Long getcouAPR( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site=:cusite and mail_class_cd='U' and t1.role='PAO'  and t1.off_id=:offid  and t1.acl_offid is not null and set_aside is null ")
	Long getcouAPRltr( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='E' and t1.role='PAO' and t1.off_id=:offid  and t1.acl_offid is not null and set_aside is null ")
	Long getcouAPRems( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='C' and t1.role='PAO' and t1.off_id=:offid  and t1.acl_offid is not null and set_aside is null ")
	Long getcouAPRpar( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='T' and t1.role='PAO'  and t1.off_id=:offid  and t1.acl_offid is not null and set_aside is null ")
	Long getcouAPRemp( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.role='PAC' ")
//	Long gettotcouACL( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and set_aside is null  ")
	Long getcouACL( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='U' and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and set_aside is null ")
	Long getcouACLltr( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='E' and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and set_aside is null  ")
	Long getcouACLems( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='C' and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and set_aside is null  ")
	Long getcouACLpar( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) from  fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and mail_class_cd='T' and t1.role='PAC' and (t1.acl_offid=:offid or t1.acl_offid is null)  and set_aside is null ")
	Long getcouACLemp( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT mail_class_cd FROM ops$dir.d_emp_roles WHERE cus_site= :cusite and role_name=:role and user_id=:offid  ")
	String getallotmailcat( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and t3.code=t1.nature_type_cd and t3.category='GIFT'  and set_aside is null ")
	//Long getcougift( @Param("cusite") String cusite, @Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and t3.code=t1.nature_type_cd and t3.category='GIFT'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role")
	Long getcougiftmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and t3.code=t1.nature_type_cd and t3.category='SALE OF GOODS'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid  and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role")
	Long getcousgmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and t3.code=t1.nature_type_cd and t3.category='RETURNED GOODS'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid   and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role")
	Long getcourgmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role); 
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and t3.code=t1.nature_type_cd and t3.category='OTHERS'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid  and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role")
	Long getcouothmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and t3.code=t1.nature_type_cd and t3.category='COMMERCIAL SAMPLE'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid  and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role ")
	Long getcoucsmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.d_emp_roles t5,ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite   and t3.code=t1.nature_type_cd and t3.category='DOCUMENTS'  and set_aside is null and instr(t5.mail_class_cd,t1.mail_class_cd) > 0 and t5.user_id=:offid   and decode(t1.role,'PAO','PAO',null,'PAO','PAC','PAC')=:role")
	Long getcoudocmc( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	@Query(nativeQuery = true, value = "SELECT TO_CHAR(TO_DATE(SUBSTR(postingdt,9,2)||'/'||SUBSTR(postingdt,6,2)||'/'||SUBSTR(postingdt,0,4),'DD/MM/YYYY'),'DD/MM/YYYY') AS posting_date FROM fpo_MAIN WHERE CIN_NO = :id")
	String getpostdt1(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT CIN_NO from  fpo_MAIN where ITEM_ID = :id")
	String cinno1(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT to_char(CIN_DT,'DD/MM/YYYY') AS CIN_DT FROM fpo_MAIN WHERE ITEM_ID = :id")
	String cindt1(@Param("id") String id);
	

	   @Query(nativeQuery = true, value = "select count(*) from fpo_main where item_id = :itemId")
					Long checkArtId(@Param("itemId") String itemId);
	   
	   @Query(nativeQuery = true, value = "select count(*) from fpo_main where item_id = :itemId and (cus_site = :cussite or decode(clr_site,null,cus_site,clr_site)=:cussite)")
		Long checkArtId1(@Param("itemId") String itemId, @Param("cussite") String cussite );

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or t1.role='PAO')  and t3.code=t1.nature_type_cd and t3.category='GIFT'  and set_aside is null ")
//	Long getcougiftapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and t1.role='PAC' and  t3.code=t1.nature_type_cd and t3.category='GIFT'  and set_aside is null ")
//	Long getcougiftacl( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t3.code=t1.nature_type_cd and t3.category='SALE OF GOODS'  and set_aside is null ")
//	Long getcousg( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and (t1.role is null or t1.role='PAO') and  t3.code=t1.nature_type_cd and t3.category='SALE OF GOODS'  and set_aside is null ")
//	Long getcousgapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t1.role='PAC' and t3.code=t1.nature_type_cd and t3.category='SALE OF GOODS'  and set_aside is null ")
//	Long getcousgacl( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t3.code=t1.nature_type_cd and t3.category='RETURNED GOODS'  and set_aside is null ")
//	Long getcourg( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and (t1.role is null or t1.role='PAO') and t3.code=t1.nature_type_cd and t3.category='RETURNED GOODS'  and set_aside is null  ")
//	Long getcourgapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t1.role='PAC' and t3.code=t1.nature_type_cd and t3.category='RETURNED GOODS'  and set_aside is null ")
//	Long getcourgacl( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t3.code=t1.nature_type_cd and t3.category='COMMERCIAL SAMPLE'  and set_aside is null ")
//	Long getcoucs( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and (t1.role is null or t1.role='PAO') and t3.code=t1.nature_type_cd and t3.category='COMMERCIAL SAMPLE'  and set_aside is null  ")
//	Long getcoucsapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t1.role='PAC' and t3.code=t1.nature_type_cd and t3.category='COMMERCIAL SAMPLE'  and set_aside is null ")
//	Long getcoucsacl( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t3.code=t1.nature_type_cd and t3.category='OTHERS'  and set_aside is null ")
//	Long getcouoth( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and (t1.role is null or t1.role='PAO') and t3.code=t1.nature_type_cd and t3.category='OTHERS'  and set_aside is null ")
//	Long getcouothapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t1.role='PAC' and t3.code=t1.nature_type_cd and t3.category='OTHERS'  and set_aside is null ")
//	Long getcouothacl( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t3.code=t1.nature_type_cd and t3.category='DOCUMENTS'  and set_aside is null ")
//	Long getcoudoc( @Param("cusite") String cusite, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and (t1.role is null or t1.role='PAO') and t3.code=t1.nature_type_cd and t3.category='DOCUMENTS'  and set_aside is null ")
	//Long getcoudocapr( @Param("cusite") String cusite, @Param("offid") String offid);

//	@Query(nativeQuery = true, value = "SELECT COUNT(t1.cin_no) FROM ops$dir.pdi_nature_trans_codes t3, fpo_main t1 left join fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite    and t1.role='PAC' and  t3.code=t1.nature_type_cd and t3.category='DOCUMENTS'  and set_aside is null ")
//	Long getcoudocacl( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "SELECT deci_nm from  fpo_qry_deci a, deci_reas b where qry_type='E4' and a.deci_cd=b.deci_cd and CIN_NO = :id and id=(select max(id) from  fpo_qry_deci where cin_no=:id and qry_type='E4')")
	String geteaddeci(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_query where CIN_NO = :id")
	int getqrycou(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_order where CIN_NO = :id")
	int getexmordcou(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT * from  fpo_main where CIN_NO = :id")
	List<FPO_MAIN> getmain(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select substr(DCALL_NO, 1,3) filename from dcallqry_gen where id = (select MAX(id) from dcallqry_gen where cin_no=:id)")
    String stages(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_ooc_cancel_info where article_id=(select item_id from fpo_main where CIN_NO = :id)")
	int getooccancel(@Param("id") String id);
	
	
	@Query(nativeQuery = true, value = "SELECT to_char(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'),'DD/MM/YYYY') || ' ' || substr(postingdt,12,18)  from  fpo_MAIN where CIN_NO = :id")
	String getpostdt(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') oocdt from fpo_mvmnt where role='OOC' and cin_no=:id order by id")
	List<Collection> getoocdt(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select to_char(max(a.ent_dt),'DD/MM/YYYY HH24:MI:SS') oocdt from fpo_mvmnt a , ops$dir.fpo_sites s, fpo_main m  where a.role='OOC' and a.item_id=:id  and m.item_id=a.item_id and s.site_code=:cusite and decode(s.clustered,1,decode(m.clr_site,null,m.cus_site,m.clr_site),m.cus_site)=:cusite ")
		List<Collection> getmaxoocdt(@Param("id") String id, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select category from  fpo_main a,ops$dir.pdi_nature_trans_codes b where a.nature_type_cd=b.code and a.cin_no=:id and a.nature_type_cd=:natcd")
	String getitcat(@Param("id") String id,@Param("natcd") String natcd);
	
	@Query(nativeQuery = true, value = "select upper(codedesc) from  fpo_main a,ops$dir.pdi_mail_class_codes b where a.mail_class_cd=:mc and a.cin_no=:id and b.code=:mc ")
	String getmc(@Param("id") String id, @Param("mc") String mc);

	@Query(nativeQuery = true, value = "SELECT a.ITEM_NO,a.ITEM_DESC,a.GEN_CTH,NOU,assval_insfrt,BCD_AMT, IGST_AMT,SW_AMT,DUTY,nvl(oth_DUTY,0) from  fpo_ITEM_DET a left join (select sum(duty_amt) oth_duty,cin_no,item_no from  fpo_item_det_othduty where cin_no=:id group by cin_no,item_no) b  on a.cin_no=b.cin_no  and a.item_no=b.item_no where a.CIN_NO = :id  order by item_no")
	List<Collection> getitem(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT ITEM_ID from  fpo_MAIN where CIN_NO = :id")
	String getitemid(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT to_char(cin_dt,'DD/MM/YYYY HH24:MI:SS') from  fpo_MAIN where CIN_NO = :id")
	String getrecdt(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select distinct(post_org_name_short) from ops$dir.pdi_ooe_codes  where impc_code_short=:orgpost")
	String getorgpost(@Param("orgpost") String orgpost);

	@Query(nativeQuery = true, value = "SELECT ITEM_NO,ITEM_DESC,GEN_CTH from  fpo_ITEM_DET where CIN_NO = :id order by item_no ")
	List<Collection> getitminfo(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT a.ITEM_NO,ITEM_DESC,GEN_CTH,b.item_fou,b.remarks from  fpo_ITEM_DET a, fpo_exam_findings b where a.CIN_NO = :id and a.cin_no=b.cin_no and a.item_no=b.item_no and b.item_no is not null order by b.item_no ")
	List<Collection> getexminfo(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT remarks FROM  fpo_exam_findings where CIN_NO = :id  and item_no is  null")
	String getgenrem(@Param("id") String id);

	@Query(nativeQuery = true, value = "select count(item_no) from  fpo_item_det where cin_no = :id")
	Long getnoitems(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT COUNT(*)from  fpo_main WHERE cin_no=:cinNo and ass_dt is not null")
	Long getassCount(String cinNo);

//	@Query(nativeQuery = true, value = "select count(*) from  fpo_order where upper(exam_order) like '%DETAIN%' and cin_no= :id")
//	Long getdetstatus(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select detain from  FPO_EXAM_FINDINGS where id in (select max(id) FROM fpo_exam_findings where cin_no=:id) and cin_no=:id")
	Long getdetstatus(@Param("id") String id);

	
	@Query(nativeQuery = true, value = "select count(*) from  FPO_EXAM_FINDINGS where id in (select max(id) FROM fpo_exam_findings where cin_no=:id) and cin_no=:id")
	Long getcouexmfind(@Param("id") String id);

	@Query(nativeQuery = true, value = "select count(*) from  fpo_order where upper(first_check)='Y' and cin_no= :id")
	Long getfchkstatus(@Param("id") String id);

	@Query(nativeQuery = true, value = "select count(*) from  fpo_qry_deci where (deci_cd=5 or deci_cd=6 or deci_cd=7) and cin_no= :id and cin_no not in ( select cin_no from  fpo_mvmnt where ext_dt is not null and stage='P1' and cin_no=:id)")
	Long getoocenbl(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT f.interpretation FROM ops$dir.PDI_HANDLING_CLASS_CODES f INNER JOIN FPO_MAIN qa ON f.CODE = qa.HANDLING_CLASS_CD where CIN_NO = :id")
	String getShortValue(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT f.interpretation FROM ops$dir.PDI_NATURE_TRANS_CODES f INNER JOIN FPO_MAIN qa ON f.CODE = qa.NATURE_TYPE_CD where CIN_NO = :id")
	String getNatureValue(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT f.FPO_DESTN FROM ops$dir.PDI_PINCODE_MAPPING_INDIA f INNER JOIN FPO_MAIN qa ON f.FPO_CODE = qa.FPO_CODE where CIN_NO = :id")
	String getFpoAllocatedValue(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT tot_ass_val from  fpo_MAIN where CIN_NO = :id")
	Long gettotassval(@Param("id") String id);

	// sasi mam code//

//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and arr_fpo='Y' and arr_scan='Y'  order by priority, job_no, job_dt")
//	List<Collection> getPassView( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT k.disp_name from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k where q.cin_no=:cinno and  i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and arr_scan='Y' and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no ) and k.role=j.role ")
	String getquedisp( @Param("cusite") String cusite, String cinno);

	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k where  q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null order by priority, job_no, job_dt")
	/*@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on q.item_id=dc.item_id, fpo_qry_deci i, fpo_query r, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k where  q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.cin_no = dc.cin_no and q.role=?3 and q.off_id=?2 order by priority, job_no, job_dt")
	List<Collection> getPassView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);*/
	
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, ops$dir.d_emp_roles s,(select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and (i.off_id=?2 or i.off_id is null) and f.code=q.mail_class_cd  and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=?2 order by priority, job_no, job_dt")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,\r\n"
//			+ " to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',\r\n"
//			+ " decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)) \r\n"
//        + " from fpo_main q, fpo_qry_deci i, fpo_mvmnt j,ops$dir.que_disp k,ops$dir.d_emp_roles s, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f,(select count(*) cou,cin_no from  fpo_item_det group by cin_no) m \r\n"
//        + " where q.cin_no=i.cin_no and q.cin_no=j.cin_no and i.qry_type='P1' and i.qry_no is null and k.role=j.role and instr(s.mail_class_cd,q.mail_class_cd) > 0 and (s.user_id=?2 ) and q.nature_type_cd=t3.code \r\n"
//        + " and set_aside is null and f.code=q.mail_class_cd and q.cin_no=m.cin_no and q.cus_site=?1 and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null)))")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q , fpo_qry_deci i,fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, ops$dir.d_emp_roles s,(select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and (i.off_id=?2 or i.off_id is null) and f.code=q.mail_class_cd  and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=?2 order by priority, job_no, job_dt")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,(select max(item_no) FROM fpo_item_det where item_id=q.item_id) cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,'From (' || decode(j.stage,'P6','Commercial Imports','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'R6','Moved to Personal Imports','P5','Detention ', 'O1', 'OOC Cancellation', 'C1','w/o PINCODE - Mapped to site in Pincode Mapping Module.','C2','w/o PINCODE - Mapped to site in Bag Scan Module.','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) )||')' from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, ops$dir.d_emp_roles s where q.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code  and (i.off_id=:offid or i.off_id is null) and f.code=q.mail_class_cd  and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offid and s.role_name=:role order by priority, job_no, job_dt")
//changed for clearance site display for OOC Cancellation / detention / commercial imports / moved to personal imports
	
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,(select max(item_no) FROM fpo_item_det where item_id=q.item_id) cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,'From (' || decode(j.stage,'P6','Commercial Imports','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'R6','Moved to Personal Imports','P5','Detention ', 'O1', 'OOC Cancellation', 'C1','w/o PINCODE - Mapped to site in Pincode Mapping Module.','C2','w/o PINCODE - Mapped to site in Bag Scan Module.','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) )||')' from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code    and f.code=q.mail_class_cd  and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offid and s.role_name=:role order by priority, job_no, job_dt"
	 * ) List<Collection> getPassView( @Param("cusite") String
	 * cusite, @Param("offid") String offid, @Param("role") String role);
	 */
//	@Query(nativeQuery = true, value = " sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,(select max(item_no) \r\n"
//			+ "            FROM fpo_item_det where item_id=q.item_id) cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,CASE   \r\n"
//			+ "        WHEN dc.ASS_MVMNT IS NOT NULL and (SELECT COUNT(*) FROM dcallqry_gen dc LEFT JOIN fpo_query fq ON dc.cin_no = fq.cin_no WHERE dc.cin_no = i.cin_no  AND dc.QRY_REPLY IS NULL AND dc.ASS_MVMNT IS NOT NULL and fq.RPLY_DATE is null\r\n"
//			+ ") = 1 THEN 'From (First Query Without reply)'\r\n"
//			+ "        WHEN dc.ASS_MVMNT IS NOT NULL and (SELECT COUNT(*) FROM dcallqry_gen dc LEFT JOIN fpo_addl_qry fa ON dc.cin_no = fa.cin_no WHERE dc.cin_no = i.cin_no  AND dc.QRY_REPLY IS NULL AND dc.ASS_MVMNT IS NOT NULL and fa.RPLY_DATE is null) = 2 THEN 'From (Additional Query Without reply)'\r\n"
//			+ "        ELSE 'From (' || decode(j.stage,'P6','Commercial Imports','P1',\r\n"
//			+ "            decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered',\r\n"
//			+ "            'D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', \r\n"
//			+ "            decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'R6','Moved to Personal Imports','P5','Detention ', 'O1', 'OOC Cancellation', \r\n"
//			+ "            'C1','w/o PINCODE - Mapped to site in Pincode Mapping Module.','C2','w/o PINCODE - Mapped to site in Bag Scan Module.',\r\n"
//			+ "            'P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',\r\n"
//			+ "            decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) )||')' END AS queue_desc  from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
//			+ "            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, \r\n"
//			+ "            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
//			+ "            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
//			+ "            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
//			+ "            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code    and f.code=q.mail_class_cd \r\n"
//			+ "            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offid and s.role_name=:role order by priority, job_no, job_dt\r\n"
//			+ "            ")
//	List<Collection> getPassView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,(select max(item_no) \r\n"
			+ "					            FROM fpo_item_det where item_id=q.item_id) cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,CASE   \r\n"
			+ "					        WHEN dc.ASS_MVMNT IS NOT NULL and (SELECT COUNT(*) FROM dcallqry_gen dc LEFT JOIN fpo_query fq ON dc.cin_no = fq.cin_no WHERE dc.cin_no = i.cin_no AND  fq.id = (SELECT MAX(id)FROM fpo_query WHERE cin_no = dc.cin_no)  AND dc.QRY_REPLY IS NULL AND dc.ASS_MVMNT IS NOT NULL and fq.RPLY_DATE is null\r\n"
			+ "					) = 1 THEN decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','From (D-Call Letter Delivered, First Query Reply Not Received in Time / Complete Assessment)','Not Delivered',\r\n"
			+ "					            'From (D-Call Letter Not Delivered, First Query Reply Not Received in Time / Complete Assessment)')\r\n"
			+ "					        WHEN dc.ASS_MVMNT IS NOT NULL and (SELECT COUNT(*) FROM dcallqry_gen dc LEFT JOIN fpo_addl_qry fa ON dc.cin_no = fa.cin_no WHERE dc.cin_no = i.cin_no  AND  fa.qry_id = (SELECT MAX(id)FROM fpo_addl_qry WHERE cin_no = dc.cin_no) AND dc.QRY_REPLY IS NULL AND dc.ASS_MVMNT IS NOT NULL and fa.RPLY_DATE is null) = 2 THEN \r\n"
			+ "								decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','From (D-Call Letter Delivered, Additional Query Reply Not Received in Time / Complete Assessment)','Not Delivered',\r\n"
			+ "					            'From (D-Call Letter Not Delivered, Additional Query Reply Not Received in Time / Complete Assessment)')\r\n"
			+ "					        ELSE 'From (' || decode(j.stage,'P6','Commercial Imports','P1',\r\n"
			+ "					            decode(dc.SPEEDPOST_DELI_STATUS,\r\n"
			+ "					            null, 'AAF Assessment', \r\n"
			+ "					            decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'R6','Moved to Personal Imports','P5','Detention ', 'O1', 'OOC Cancellation', \r\n"
			+ "					            'C1','w/o PINCODE - Mapped to site in Pincode Mapping Module.','C2','w/o PINCODE - Mapped to site in Bag Scan Module.',\r\n"
			+ "					            'P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',\r\n"
			+ "					            decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) )||')' END AS queue_desc  from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
			+ "					            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, \r\n"
			+ "					            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
			+ "					            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
			+ "					            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
			+ "					            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) and k.role=j.role and q.nature_type_cd=t3.code    and f.code=q.mail_class_cd \r\n"
			+ "					            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offid and s.role_name=:role order by priority, job_no, job_dt\r\n"
			+ "")
	List<Collection> getPassView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
	
	
	
	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q , fpo_qry_deci i, fpo_query r, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k, (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc,ops$dir.pdi_mail_class_codes f, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and   q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.cin_no = dc.cin_no and q.role=?3 and (q.acl_offid=?2 or q.acl_offid is null) and f.code=q.mail_class_cd  order by priority, job_no, job_dt")
	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'P5','Detention ', 'C1','Mapping FPO Site - EAD w/o Pincode','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) from  fpo_main q , fpo_qry_deci i, fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k,ops$dir.pdi_mail_class_codes f, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and   q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.cin_no = dc.cin_no and q.role=?3 and (q.acl_offid=?2 or q.acl_offid is null) and f.code=q.mail_class_cd  order by priority, job_no, job_dt")
	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,'From (' || decode(j.stage,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment',null, 'AAF Assessment Approval', decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name)),'P5','Detention ', 'C1','w/o PINCODE - Mapped to site in Pincode Mapping Module.','C2','w/o PINCODE - Mapped to site in Bag Scan Module. ','P2','Query','A2','Additional Query in AAA', 'A3','Additional Query in AAF',decode(j.officer_id, null, k.disp_name || ' recall', k.disp_name) ) ||')' from  fpo_main q , fpo_qry_deci i, fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k,ops$dir.pdi_mail_class_codes f, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and   q.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and r.cin_no = dc.cin_no and q.role=:role and (q.acl_offid=:offid or q.acl_offid is null) and f.code=q.mail_class_cd  order by priority, job_no, job_dt")
	//List<Collection> getPassaclView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);

	
	
	//query for aclview by sasi mam
	
	@Query(nativeQuery = true, value = "select t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY'), substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),t1.item_id,t1.send_cntry_cd,t5.codedesc,t3.category,tot_ass_val,\r\n"
			+ "			decode(t6.cou,null,decode(t5.cou, null, decode(t4.cou,null,'','for Assessment Approval'),'for First Query Approval'),'For Additional Query Approval')\r\n"
			+ "		FROM ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5,fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no left join \r\n"
			+ "			(select count(*) cou,cin_no from  fpo_item_det where updass_dt is not null group by cin_no) t4 on t1.cin_no=t4.cin_no left join\r\n"
			+ "			(select count(*) cou,cin_no from  fpo_query where qry_typ='P' and rply_date is  null group by cin_no) t5 on t1.cin_no=t5.cin_no left join \r\n"
			+ "            (select count(*) cou,cin_no from  fpo_addl_qry where qry_type='P' and rply_date is  null group by cin_no) t6 on t1.cin_no=t6.cin_no \r\n"
			+ "			where  t1.cus_site=:cusite and t1.role=:role and (t1.acl_offid=:offid ) and t1.nature_type_cd=t3.code and t1.mail_class_cd=t5.code and arr_scan='Y' \r\n"
			+ "             and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role=:role and t1.acl_offid=:offid\r\n"
			+ "		  order by priority, job_no, job_dt")
	List<Collection> getPassaclView(@Param("cusite") String cusite, @Param("offid") String offid,@Param("role") String role);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no  LIKE '%' || :cinNo || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoView(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo ||'%' and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoitemidView(@Param("cinNo") String cinNo, @Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y'  and i.cin_no like '%' || :cinNo ||'%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnopostdtView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and  to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetpostdtView(@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and q.send_cntry_cd like '%' || :Orgcd | |'%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoorgcdView(@Param("cinNo") String cinNo, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetitemidView(@Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetitemidpostdtView(@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetpostdtorgcdView(@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and  i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetitemidorgcdView(@Param("itemID") String itemID, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoitemidpostdtView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoitemidpostdtorgcdView(@Param("cinNo") String cinNo,
			@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetitemidpostdtorgcdView(@Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnopostdtorgcdView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd ||'%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetcinnoitemidorgcdView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and q.send_cntry_cd like '%' || :Orgcd || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpdetorgcdView(@Param("Orgcd") String Orcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no  LIKE '%' || :cinNo || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoView(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo ||'%' and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoitemidView(@Param("cinNo") String cinNo, @Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y'  and i.cin_no like '%' || :cinNo ||'%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnopostdtView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and  to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrypostdtView(@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and q.send_cntry_cd like '%' || :Orgcd | |'%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoorgcdView(@Param("cinNo") String cinNo, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqryitemidView(@Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqryitemidpostdtView(@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqrypostdtorgcdView(@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and  i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqryitemidorgcdView(@Param("itemID") String itemID, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoitemidpostdtView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoitemidpostdtorgcdView(@Param("cinNo") String cinNo,
			@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqryitemidpostdtorgcdView(@Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnopostdtorgcdView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd ||'%'  order by job_no,job_dt")
	List<Collection> getsearchimpqrycinnoitemidorgcdView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and arr_scan='Y' and q.send_cntry_cd like '%' || :Orgcd || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpqryorgcdView(@Param("Orgcd") String Orcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no  LIKE '%' || :cinNo || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoView(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo ||'%' and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoitemidView(@Param("cinNo") String cinNo, @Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y'  and i.cin_no like '%' || :cinNo ||'%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnopostdtView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and  to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmpostdtView(@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and q.send_cntry_cd like '%' || :Orgcd | |'%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoorgcdView(@Param("cinNo") String cinNo, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmitemidView(@Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmitemidpostdtView(@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmpostdtorgcdView(@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and  i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmitemidorgcdView(@Param("itemID") String itemID, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoitemidpostdtView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoitemidpostdtorgcdView(@Param("cinNo") String cinNo,
			@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmitemidpostdtorgcdView(@Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnopostdtorgcdView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd ||'%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmcinnoitemidorgcdView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and arr_scan='Y' and q.send_cntry_cd like '%' || :Orgcd || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpexmorgcdView(@Param("Orgcd") String Orcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no  LIKE '%' || :cinNo || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoView(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo ||'%' and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoitemidView(@Param("cinNo") String cinNo, @Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y'  and i.cin_no like '%' || :cinNo ||'%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpooccinnopostdtView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and  to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpoocpostdtView(@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and q.send_cntry_cd like '%' || :Orgcd | |'%'  order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoorgcdView(@Param("cinNo") String cinNo, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpoocitemidView(@Param("itemID") String itemID);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpoocitemidpostdtView(@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpoocpostdtorgcdView(@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and  i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpoocitemidorgcdView(@Param("itemID") String itemID, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoitemidpostdtView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT || '%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoitemidpostdtorgcdView(@Param("cinNo") String cinNo,
			@Param("itemID") String itemID, @Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.item_id like '%' || :itemID || '%' and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpoocitemidpostdtorgcdView(@Param("itemID") String itemID,
			@Param("QRY_DT") String QRY_DT, @Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and to_char(qry_dt,'DD/MM/YYYY') like '%' || :QRY_DT ||'%' and q.send_cntry_cd like '%' || :Orgcd || '%'   order by job_no,job_dt")
	List<Collection> getsearchimpooccinnopostdtorgcdView(@Param("cinNo") String cinNo, @Param("QRY_DT") String QRY_DT,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no like '%' || :cinNo || '%'  and i.item_id like '%' || :itemID || '%'  and q.send_cntry_cd like '%' || :Orgcd ||'%'  order by job_no,job_dt")
	List<Collection> getsearchimpooccinnoitemidorgcdView(@Param("cinNo") String cinNo, @Param("itemID") String itemID,
			@Param("Orgcd") String Orgcd);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and q.send_cntry_cd like '%' || :Orgcd || '%'  order by job_no,job_dt")
	List<Collection> getsearchimpoocorgcdView(@Param("Orgcd") String Orcd);

//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO  and (i.qry_type='P4') and i.qry_no is null and arr_scan='Y' order by job_no,job_dt")
//	List<Collection> getPexmView();

	//changed on 22nd February, 2022
		@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(q.postingdt,9,2)||'/'||substr(q.postingdt,6,2)||'/'||substr(q.postingdt,0,4),q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,decode(j.stage,'E1','EAD Assessment','P1','AAF Assessment','N1', 'AAA Assessment', 'AAF Assessment' ) from  fpo_main q ,  ops$dir.pdi_nature_trans_codes t3,fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k,ops$dir.d_emp_roles x  where  decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P4') and i.qry_no is null and  arr_scan='Y' and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no ) and k.role=j.role and  q.nature_type_cd=t3.code and user_id=:offid and instr(x.mail_class_cd,q.mail_class_cd) > 0  and role_name='PIN' order by job_no,job_dt")
		List<Collection> getPexmView( @Param("cusite") String cusite, @Param("offid") String offid);
		//changed on 22nd February, 2022

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_mvmnt where cus_site=:cusite and officer_id=:offid and role='PIN' and ent_dt is not null and ext_dt is null")
	Long getdupcouexm( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_mvmnt where cus_site=:cusite and officer_id=:offid and role='PSU' and ent_dt is not null and ext_dt is null")
	Long getdupcousup( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "sELECT count(*) FROM article_arr_info where article_id = :itemid  ")
	Long getcouarrooeitm(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "sELECT decode(role,null,'PAO',role) FROM fpo_main where item_id = :itemid ")
	String getcurroleead(@Param("itemid") String itemid);

	@Query(nativeQuery = true, value = "sELECT count(*) FROM articlearr_fpo_info where article_id=:itemid ")
	Long getcouarrfpoitm(@Param("itemid") String itemid);

	@Query(nativeQuery = true, value = "sELECT decode(role,null,'PAO',role) from  fpo_main where item_id=:itemid and cus_site=:cusite")
	String getroleitm(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid and (cus_site=:cusite or clr_site=:clrsite) and arr_scan='Y'")
	Long getarr(@Param("itemid") String itemid,  @Param("cusite") String cusite,  @Param("clrsite") String clrsite);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid  and cus_site is null and arr_scan='Y'")
	Long getarrcou(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid  and cus_site is null")
	Long getitmcou(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid  and cus_site=:cusite  and arr_scan='Y'")
	Long getarrfpo(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid and cus_site=:cusite  and  arr_scan is null")
	Long getscanfpo(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid  and cus_site is not null and arr_scan='Y'")
	Long getarrfpocou(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid  and cus_site is null and arr_scan is null")
	Long getarrscancou(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid and cus_site is not null and arr_scan is null")
	Long getarrfposcancou(@Param("itemid") String itemid);

	@Query(nativeQuery = true, value = "sELECT role from  fpo_main where cin_no=:cinno and cus_site=:cusite ")
	String getrolecin(@Param("cinno") String cinno,  @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT to_char(ooc_dt,'DD/MM/YYYY HH24:MI:SS') from  fpo_status where cin_no=:cinno  and cus_site=:cusite ")
	String getoocdtcin(@Param("cinno") String cinno,  @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "select to_char(ent_dt,'DD/MM/YYYY HH24:MI:SS') from fpo_mvmnt a, ops$dir.fpo_sites s, fpo_main m where a.item_id=:itemid and a.role='OOC'  and a.item_id=m.item_id  and (a.cus_site=:cusite or decode(s.clustered,1,decode(m.clr_site,null,m.cus_site,m.clr_site),a.cus_site)=:cusite) and slno=(select max(slno) from fpo_mvmnt where stage='P8' and item_id=:itemid   and cus_site=:cusite)")
	String getoocdtmvmnt(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT to_char(ooc_dt,'DD/MM/YYYY HH24:MI:SS') from  fpo_status a, fpo_main m  where m.item_id=a.item_id and a.item_id=:itemid and (m.cus_site=:cusite or m.clr_site=:cusite)")
	String getoocdtitm(@Param("itemid") String itemid,  @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT to_char(ooc_dt,'DD/MM/YYYY HH24:MI:SS') from  fpo_status a  where item_id=:itemid ")
	String getoocdtitm(@Param("itemid") String itemid);

//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,k.disp_name from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j,que_disp k where  i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_fpo='Y' and arr_scan='Y' and i.cin_no=j.cin_no and j.sl_no in ( select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and ext_dt is null and role!='PSU') and j.role=k.role order by job_no,job_dt")
//	List<Collection> getPoocView();

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,k.disp_name from  fpo_main q , ops$dir.d_emp_roles x,  fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k where  i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P3') and i.qry_no is null and arr_scan='Y' and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no ) and k.role=j.role and x.user_id=:offid and instr(x.mail_class_cd,q.mail_class_cd) > 0 and x.role_name='PSU' order by job_no,job_dt")
	List<Collection> getPoocView( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select a.cin_no,a.item_id, a.cin_dt,a.send_cntry_cd,a.qry_dt,0,nvl(b.ass,0),nvl(c.exm,0),decode(nvl(e.det,0),0,nvl(f.det,0),nvl(e.det,0)) det,a.role,a.postingdt,a.tot_ass_val,a.tot_duty,a.mail_class_cd,a.category,a.job_no,a.job_dt,a.arr_dt,commercial from \r\n"
			+ "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY')cin_dt,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY')qry_dt,j.role,substr(q.postingdt,9,2)||'/'||substr(q.postingdt,6,2)||'/'||substr(q.postingdt,0,4) postingdt,to_char(q.tot_ass_val,'fm99999999999.90') tot_ass_val,to_char(q.tot_duty,'fm99999999999.90') tot_duty,q.mail_class_cd,k.category,q.job_no,q.job_dt,to_char(x.recd_dt,'Dd/MM/YYYY') arr_dt,commercial \r\n"
			+ " from  fpo_main q left join  articlearr_fpo_info x on q.item_id=x.article_id, fpo_qry_deci i, fpo_mvmnt j , ops$dir.d_emp_roles e, ops$dir.pdi_nature_trans_codes k where  decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO and  q.arr_scan='Y'\r\n"
			+ "and (i.qry_type = 'P3')  and i.id=(select max(id) from fpo_qry_deci where item_id=q.item_id) and i.qry_no is null and q.nature_type_cd=k.code  and e.user_id=:offid and instr(e.mail_class_cd,q.mail_class_cd) > 0  and e.role_name='PSU'\r\n"
			+ "and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and role!='PSU')  order by q.job_no,q.job_dt) a left join \r\n"
			+ "(select count(*) ass,x.cin_no from  fpo_item_det x, fpo_main y  where x.item_id=y.item_id and decode(y.clr_site,null,y.cus_site,y.clr_site)=:cusite and x.updass_dt is null group by x.cin_no order by 2) b on a.cin_no=b.cin_no left join \r\n"
			+ "(select count(*) exm,x.cin_no from  fpo_mvmnt x, fpo_main y  where x.item_id=y.item_id and decode(y.clr_site,null,y.cus_site,y.clr_site)=:cusite and x.role='PIN' group by x.cin_no order by 2) c on a.cin_no=c.cin_no left join \r\n"
			+ "(select count(*) fc, x.cin_no from  fpo_order x, fpo_main y  where x.item_id=y.item_id and decode(y.clr_site,null,y.cus_site,y.clr_site)=:cusite and first_check='Y' group by x.cin_no order by 2) d on a.cin_no=d.cin_no left join \r\n"
			+ "(select count(*) det, cin_no from  fpo_order where upper(exam_order) like '%DETAIN%' group by cin_no order by 2) e on a.cin_no=e.cin_no left join \r\n"
			+ "(select count(*) det, cin_no from  fpo_exam_findings where detain='1' group by cin_no order by 2) f on a.cin_no=f.cin_no \r\n"
			+ "group by a.cin_no,a.item_id,a.cin_dt,a.send_cntry_cd,a.qry_dt,a.role,b.ass,c.exm,e.det,f.det,a.postingdt,a.tot_ass_val,a.tot_duty,a.mail_class_cd,a.category,a.job_no,a.job_dt,a.arr_dt,commercial \r\n"
			+ "order by 15,16 ")
	List<Collection> getQoocView( @Param("cusite") String cusite, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),j.role,k.disp_name from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k where  i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' and i.cin_no=j.cin_no and j.role=(select role from  fpo_mvmnt where cin_no=i.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no) and k.role=j.role order by job_no,job_dt")
	List<Collection> getPdetView( @Param("cusite") String cusite);

	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where   i.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and arr_scan='Y' order by job_no,job_dt")
	//Long getcouassView( @Param("cusite") String cusite);
	/*@Query(nativeQuery = true, value = "select sum(cou) from (sELECT count(*) cou from  fpo_main q , fpo_qry_deci i where   i.cus_site=?1  and i.CIN_NO = q.CIN_NO  and \r\n"
			+ "(i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y')  \r\n"
	        + "union \r\n"
	        + "select count(*) cou from  fpo_main q, fpo_qry_deci i, fpo_query r where q.cus_site=?1 and i.cin_no=q.cin_no and (i.qry_type='P2' or i.qry_type='P7') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y') \r\n"
	        + "and q.cin_no=r.cin_no and r.cus_site=?1 and r.item_no is null and r.rply_date is not null \r\n"
	        + "union  \r\n"
	        + "select count(*) cou from  fpo_main q, fpo_qry_deci i, fpo_addl_qry r where i.cus_site=?1 and i.cin_no=q.cin_no and i.qry_type='A3' and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y')  \r\n"
	        + "and q.cin_no=r.cin_no and i.cus_site=?1 and r.rply_date is not null) ")
	Long getcouassView( @Param("cusite") String cusite);*/
	
	
	//Changed as it was giving error on Jan 20th,2022
	
	@Query(nativeQuery = true, value = "select coua+coub+couc from \r\n"
			+"  (sELECT count(*) coua from  fpo_main q , ops$dir.d_emp_roles s,fpo_qry_deci i, fpo_query r   left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no=dc.cin_no,  ops$dir.pdi_mail_class_codes f, ops$dir.pdi_nature_trans_codes t3 ,  \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cusite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P1') and i.qry_no is null and (arr_scan='Y') \r\n"
			+" and f.code=q.mail_class_cd and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name=:role and (i.off_id=:offid or i.off_id is null) and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and r.rply_date is not null and r.cin_no = dc.cin_no )a,\r\n"

			+" (sELECT count(*) coub from  fpo_main q , ops$dir.d_emp_roles s,fpo_qry_deci i, fpo_addl_qry r left join(select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no = dc.cin_no, ops$dir.pdi_nature_trans_codes t3 ,ops$dir.pdi_mail_class_codes f,  \r\n"
			+"  ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m \r\n"
			+"  where  m.cin_no=q.cin_no and  i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and q.arr_scan = 'Y' \r\n"
			+" and f.code=q.mail_class_cd and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and s.role_name=:role   and (i.off_id=:offid or i.off_id is null) and q.cin_no=r.cin_no and q.role='PAO' and q.off_id=:offid and qry_def_slno is null and r.rply_date is not null and r.cin_no = dc.cin_no)b,\r\n"

			+" (sELECT count(*) couc from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m,  ops$dir.d_emp_roles s where  m.cin_no=q.cin_no and q.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (arr_scan='Y') and i.cin_no=j.cin_no  \r\n"
			+" and j.slno in (select max(slno) from  fpo_mvmnt t where  instr(s.mail_class_cd,q.mail_class_cd) > 0   and s.role_name=:role  and s.user_id=:offid and s.role_name=:role and t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='C2' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and (i.off_id=:offid or i.off_id is null) and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=i.OFF_ID  \r\n"
			+" and f.code=q.mail_class_cd )c ")
	Long getcouassView( @Param("cusite") String cusite,@Param("offid") String offid, @Param("role") String role); 
	
	@Query(nativeQuery = true, value = "sELECT count(*) couc from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m,  ops$dir.d_emp_roles s where  m.cin_no=q.cin_no and q.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( arr_scan='Y') and i.cin_no=j.cin_no  \r\n"
			+" and j.slno in (select max(slno) from  fpo_mvmnt t where  instr(s.mail_class_cd,q.mail_class_cd) > 0   and s.role_name=:role  and s.user_id=:offid and s.role_name=:role and t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='C2' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside='Y' and (i.off_id=:offid or i.off_id is null) \r\n"
			+" and f.code=q.mail_class_cd  ")
	Long getcousetasideimp( @Param("cusite") String cusite,@Param("offid") String offid, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "select coua+coub+couc from \r\n"
			+"  (sELECT count(*) coua from  fpo_main q , fpo_qry_deci i, fpo_query r   left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no=dc.cin_no,  ops$dir.pdi_mail_class_codes f, ops$dir.pdi_nature_trans_codes t3 ,  \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no  and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (arr_scan='Y') \r\n"
			+" and f.code=q.mail_class_cd and q.cin_no=r.cin_no  and r.item_no is null and r.rply_date is not null and r.cin_no = dc.cin_no )a,\r\n"

			+" (sELECT count(*) coub from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r left join(select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no = dc.cin_no, ops$dir.pdi_nature_trans_codes t3 ,ops$dir.pdi_mail_class_codes f,  \r\n"
			+"  ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m \r\n"
			+"  where  m.cin_no=q.cin_no and   i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and q.arr_scan = 'Y' \r\n"
			+" and f.code=q.mail_class_cd and q.cin_no=r.cin_no and q.role='PAO' and qry_def_slno is null and r.rply_date is not null and r.cin_no = dc.cin_no)b,\r\n"

			+" (sELECT count(*) couc from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m,  ops$dir.d_emp_roles s where  m.cin_no=q.cin_no and  decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (arr_scan='Y') and i.cin_no=j.cin_no  \r\n"
			+" and j.slno in (select max(slno) from  fpo_mvmnt t where  instr(s.mail_class_cd,q.mail_class_cd) > 0   and s.role_name=:role and t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='C2' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null  \r\n"
			+" and f.code=q.mail_class_cd )c ")
	Long gettotcouassView(); 

	@Query(nativeQuery = true, value = "select coua+coub+couc from \r\n"
			+"  (sELECT count(*) coua from  fpo_main q , fpo_qry_deci i, fpo_query r   left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no=dc.cin_no,  ops$dir.pdi_mail_class_codes f, ops$dir.pdi_nature_trans_codes t3 ,  \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (arr_scan='Y') \r\n"
			+" and f.code=q.mail_class_cd and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and r.rply_date is not null and r.cin_no = dc.cin_no )a,\r\n"

			+" (sELECT count(*) coub from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r left join(select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on r.cin_no = dc.cin_no, ops$dir.pdi_nature_trans_codes t3 ,ops$dir.pdi_mail_class_codes f,  \r\n"
			+"  ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m \r\n"
			+"  where  m.cin_no=q.cin_no and   decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and q.arr_scan = 'Y' \r\n"
			+" and f.code=q.mail_class_cd and q.cin_no=r.cin_no and (q.role='PAO' or q.role='PAC') and qry_def_slno is null and r.rply_date is not null and r.cin_no = dc.cin_no)b,\r\n"

			+" (sELECT count(*) couc from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, \r\n"
			+" (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and (  arr_scan='Y') and i.cin_no=j.cin_no  \r\n"
			+" and j.slno in (select max(slno) from  fpo_mvmnt t where  t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='C2' and ext_dt is null) )) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null  \r\n"
			+" and f.code=q.mail_class_cd )c ")
	Long gettotcouass( @Param("cusite") String cusite);  
	
	/*
		@Query(nativeQuery = true, value = "select sum(cou) from (sELECT count(*) cou from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k where  q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y' ) \r\n"
	         + "and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and \r\n"
	         + "(ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role  and q.set_aside is null \r\n"
	         + "union \r\n"
     	     + "sELECT count(*)  cou \r\n"
			 + "									from  fpo_main q , fpo_qry_deci i, fpo_query r  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y') \r\n"
			 + "	and q.cin_no=r.cin_no and r.cus_site=?1 and r.item_no is null and r.rply_date is not null \r\n"
	         + "   union \r\n"
	         + "   sELECT count(*) cou \r\n"
			 + "	from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and i.qry_type = 'A3' and i.qry_no is null and q.arr_fpo = 'Y' \r\n"
			 + "	and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is not null) ")
	Long getcouassView( @Param("cusite") String cusite);   
	
	
	
	
	
	
	
	*/
	
	/*@Query(nativeQuery = true, value = "select sum(cou) from (sELECT count(*) cou from  fpo_main q , fpo_qry_deci i, fpo_mvmnt j, ops$dir.que_disp k where  q.cus_site=?1  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y' ) \r\n"
	         + "and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and \r\n"
	         + "(ext_dt is not null or (stage='C1' and ext_dt is null))) and k.role=j.role and q.role=?3 and  (q.acl_offid=?2 or q.acl_offid is null)  and q.set_aside is null \r\n"
	         + "union \r\n"
    	     + "sELECT count(*)  cou \r\n"
			 + "									from  fpo_main q , fpo_qry_deci i, fpo_query r  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y') \r\n"
			 + "	and q.cin_no=r.cin_no and r.cus_site=?1 and q.role=?3 and (q.acl_offid=?2 or q.acl_offid is null) and r.item_no is null and r.rply_date is not null \r\n"
	         + "   union \r\n"
	         + "   sELECT count(*) cou \r\n"
			 + "	from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and i.qry_type = 'A3' and i.qry_no is null and q.arr_fpo = 'Y' \r\n"
			 + "	and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is not null and  (q.acl_offid=?2 or q.acl_offid is null)  and q.role=?3) ")
	Long getcouassaclView( @Param("cusite") String cusite,@Param("offid") String offid, @Param("role") String role);   */
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i, fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k,ops$dir.pdi_mail_class_codes f, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and    decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or (stage='C2' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside is null and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and r.cin_no = dc.cin_no and q.role=:role and (q.acl_offid=:offid) and f.code=q.mail_class_cd  order by priority, job_no, job_dt")
    Long getcouassaclView( @Param("cusite") String cusite,@Param("offid") String offid, @Param("role") String role);   
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i, fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,ops$dir.que_disp k,ops$dir.pdi_mail_class_codes f, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and    decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') and i.qry_no is null and ( arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or  (stage='C2' and ext_dt is null))) and k.role=j.role and q.nature_type_cd=t3.code  and q.set_aside='Y' and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and r.cin_no = dc.cin_no and q.role=:role and (q.acl_offid=:offid) and f.code=q.mail_class_cd  order by priority, job_no, job_dt")
    Long getcousetasideaclimp( @Param("cusite") String cusite,@Param("offid") String offid, @Param("role") String role);   

	
	//changed on 22nd February, 2022
	@Query(nativeQuery = true, value = "sELECT count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles x  where   decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type='P4') and i.qry_no is null and  arr_scan='Y' and x.user_id=:offid and instr(x.mail_class_cd, q.mail_class_cd) > 0  and x.role_name='PIN' order by job_no,job_dt")
	Long getcouexmView( @Param("cusite") String cusite, @Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "sELECT count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i  where    decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type='P4') and i.qry_no is null and  arr_scan='Y'  ")
	Long gettotcouexm( @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i  where    decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type='P3') and i.qry_no is null and arr_scan='Y'    ")
	Long gettotcouooc( @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "Select cmts, off_id, entry_dt from FPO_ASS_PAO_CMTS where item_id= :item_ID AND stage=:stage")
	List<Collection> getDeptCmnts(@Param("item_ID") String item_ID, @Param("stage") String stage);
	
	/*@Query(nativeQuery=true, value="select count(distinct(bagNo)) recpcou from \r\n"
			+ " (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
			+"    (select count(*) tot_cou, recp_id  from article_arr_info b where status is null and substr(recp_id,7,5)=substr(:cusite,1,5)  group by recp_id  ) y  left join  \r\n"
			+"    (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and substr(a.clr_site,1,5)=substr(c.map_code,1,5) and c.site_code = :cusite    and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join \r\n"
			+"    (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n"
			+"   (select nvl(count(*),0) withEadOtherSite,recp_id from  article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and a.cus_site<>:cusite and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
			+"    (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(a.clr_site,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
			+"    (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=k.recp_id \r\n"
			+"   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  ) where bagNo not in (select distinct(bag_no) from fpo_scan_info)")
	+ " (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
	+ "   (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
	+ "   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))=substr(:cusSite,1,5)   and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n" 
	+ "   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n" 
	+ "   (select nvl(count(*),0) withEadOtherSite,recp_id from  ops$dir.fpo_sites c, article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))!=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
	+ "   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
	+ "   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
	+ "   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site   ) where bagNo not in (select distinct(bag_no) from fpo_scan_info)")
	Long getrecpcou( @Param("cusSite") String cusSite);*/
	
	
	/*@Query(nativeQuery=true, value="(select count(*) recpcou from \r\n"
	+"		  (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from  \r\n"  
	+"		   (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cuSite,1,5)  group by recp_id  ) y  left join  \r\n"
	+"		   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))=substr(:cuSite,1,5)   and substr(c.site_code,1,5)=substr(:cuSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join \r\n" 
	+"		   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n"
	+"		   (select nvl(count(*),0) withEadOtherSite,recp_id from  ops$dir.fpo_sites c, article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))!=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cuSite,1,5)  and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
	+"		   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cuSite and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cuSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join  \r\n" 
	+"		   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
	+"		   where y.recp_id is not null and clr_site=:cuSite group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site) where bagNo not in  (select distinct(bag_no)  from fpo_scan_info))")
	Long getrecpcou( @Param("cuSite") String cuSite);*/
	
	@Query(nativeQuery=true, value="(select count(*)recpcou from \r\n"
			+ "			  (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clrsite siteValue  from    \r\n"
			+ "            (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(ooe,1,5)),'INMAA',decode(:cusSite,'INFCH5','INFCH',substr(ooe,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  group by recp_id   ) y  left join \r\n"
			+ "				(select count(*) with_ead_thissite,recp_id,:cusSite clrsite from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(:cusSite,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(decode(a.clr_site,null,decode(a.cus_site,null,null,a.cus_site),a.clr_site),1,5)=substr(map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n"
			+ "			(select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join  \r\n"
			+ "			(sELECT count(*)  withEadOtherSite, recp_id from fpo_main a, article_arr_info b left join fpo_scan_info c on b.recp_id=c.bag_no where recp_id is not null and b.article_id=a.item_id and c.bag_no is null  and status is null and decode(clr_site,null,a.cus_site,clr_site)!=:cusSite group  by recp_id) l on y.recp_id=l.recp_id left join  \r\n"
			+ "				(select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
			+ "			(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
			+ "			 where y.recp_id is not null and clrsite=:cusSite group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clrsite) where bagNo not in (select distinct (bag_no) from fpo_scan_info)) ")
	Long getrecpcou( @Param("cusSite") String cusSite);
	
	@Query(nativeQuery=true, value=" select count(*) bagcou from \r\n"
			+ "( (select with_ead_thissite+withEadOtherSite with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  0 withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from  \r\n"
			+ "				 (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join \r\n"
			+ "				 (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join\r\n"
			+ "				 (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join\r\n"
			+ "				 (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join \r\n"
			+ "				 (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join \r\n"
			+ "				 (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no \r\n"
			+ "				 where x.bag_no is not null and x.bag_no not in (select distinct(bag_no)  from fpo_scan_info)\r\n"
			+ "				 group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  ) ")
	Long getbagcou( @Param("cusSite") String cusSite);
	
	@Query(nativeQuery = true, value = "sELECT count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles x  where   decode(q.clr_site,null,q.cus_site,q.clr_site)=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type='P3') and i.id=(select max(id) from fpo_qry_deci where item_id=q.item_id) and i.qry_no is null  and arr_scan='Y'  and x.user_id=:offid and instr(x.mail_class_cd, q.mail_class_cd) > 0 and x.role_name = 'PSU' order by job_no,job_dt")
	Long getcouoocView( @Param("cusite") String cusite, @Param("offid") String offid);
	//changed on 22nd February, 2022

	@Query(nativeQuery = true, value = "sELECT  count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i where   i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P5') and i.qry_no is null and arr_scan='Y' order by job_no,job_dt")
	Long getcoudetView( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT   count(distinct(q.cin_no)) from  fpo_main q , fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='A2') and i.qry_no is null and (q.arr_scan <>'Y') and q.off_id=:offid order by job_no,job_dt")
	Long getcouqryView( @Param("cusite") String cusite, @Param("offid") String offid);

	/*
	 * @Query(nativeQuery = true, value =
	 * "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.mail_class_cd,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and q.item_id  in (select article_id from article_arr_info x ) and q.off_id=?2  order by job_no,job_dt"
	 * ) List<Collection> getQueryView( @Param("cusite") String cusite, @Param("offid") String offid);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),q.mail_class_cd,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null) and q.off_id=?2  order by job_no,job_dt"
	 * ) List<Collection> getQueryPendingView( @Param("cusite") String cusite);
	 */

	@Query(nativeQuery = true, value = "sELECT city from ops$dir.fpo_site_info where fpo_site=:cusite")
	String getsitecitynm( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "select count(*) from ops$dir.dcall_restrictions  where  instr(upper(wordings), upper(:to)) > 0")
	Long mailvalid(String to);
	
	@Query(nativeQuery = true, value = "select mailto,mailcc1,mailcc2,mailcc3,mailcc4,mailcc5,mailcc6 from ops$dir.mesg_mailsend where id = (select max(id) from ops$dir.mesg_mailsend)")
    List<Collection> getmailmesgsend();


	/*
	 * sELECT i.cin_no,i.ITEM_ID,
	 * to_char(q.cin_dt,'DD/MM/YYYY'),q.mail_class_cd,q.SEND_CNTRY_CD,to_char(qry_dt
	 * ,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Query
	 * Reply',r.rply_date,'View Query Reply / Complete Assessment' ) from  fpo_main q
	 * , fpo_qry_deci i, fpo_query r where i.cus_site='INBOM5' and i.CIN_NO =
	 * q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and
	 * (q.arr_fpo is null) and q.cin_no=r.cin_no and
	 * r.cus_site= 'INBOM5' and r.item_no is null order by 7,job_no,job_dt
	 */

//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Query Reply',r.rply_date,'View Query Replied / Complete Assessment' ) \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=?1 and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Additional Query Reply',r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and i.qry_type = 'A2' and i.qry_no is null and (q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null  and set_aside is null \r\n" + "order by 7 ")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ) \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null and r.cin_no = dc.cin_no \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null  and set_aside is null and r.cin_no = dc.cin_no \r\n" + "order by 7 ")
	
	/*------------------------------ previously commented by veeman for adding d-call delivery status and reply pending days -------------------------------*/
	
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ) \r\n"
//			+ "									from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null and set_aside is null \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null \r\n" + "order by 7 ")
//	List<Collection> getQueryPendingView( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);

	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*---------------------------------------------------------------------previously commented for pac to pao movement------------------------------------------ */
	
	
//	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
//			+ "decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date) \r\n"
//			+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid \r\n"
//			+ "			and q.cin_no=r.cin_no and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
//			+ "            union \r\n"
//			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
//			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid  \r\n"
//			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null  \r\n"
//		/*	+"         union \r\n"
//		    +"                        sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,'-','Pending for BAG SCAN','-',0 \r\n"
//		    +"   from fpo_main q,  ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d,ops$dir.pdi_nature_trans_codes t3 where (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
//		    +"        and instr(s.mail_class_cd,q.mail_class_cd) > 0  and i.qry_type='P1' and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null \r\n"
//			+ "   \r\n"*/
//			+ "   ")
//	List<Collection> getQueryPendingView( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);
//	
//	
	
	//This one was working correct earlier instead of this we have added only one condition for below one comparing r.qry_type<>'D'
	/*
	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'for Assesssment Approval' ),\r\n"
			+ "decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date) \r\n"
			+ "			from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and q.role=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offid \r\n"
			+ "			and q.cin_no=r.cin_no and r.cus_site= :cusite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
			+ "            union \r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','for Additional Query Approval'),'for Additional Query Approval'),r.rply_date,'for Assessment Approval' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offid  \r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and q.mail_class_cd=d.code and set_aside is null  \r\n"
		/*	+"         union \r\n"
		    +"                        sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,'-','Pending for BAG SCAN','-',0 \r\n"
		    +"   from fpo_main q,  ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d,ops$dir.pdi_nature_trans_codes t3 where (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
		    +"        and instr(s.mail_class_cd,q.mail_class_cd) > 0  and i.qry_type='P1' and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null \r\n"
			+ "   \r\n"
			+ "   ")
	List<Collection> getQueryPendingViewacl( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);*/
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'for Assesssment Approval' ),\r\n"
			+ "decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date) \r\n"
			+ "			from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and q.role=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offid \r\n"
			+ "			and q.cin_no=r.cin_no and r.cus_site= :cusite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
			+ "         and substr(dc.dcall_no,1,3)='EAD'   union \r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','for Additional Query Approval'),'for Additional Query Approval'),r.rply_date,'for Assessment Approval' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offid  \r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
		/*	+"         union \r\n"
		    +"                        sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,'-','Pending for BAG SCAN','-',0 \r\n"
		    +"   from fpo_main q,  ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d,ops$dir.pdi_nature_trans_codes t3 where (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
		    +"        and instr(s.mail_class_cd,q.mail_class_cd) > 0  and i.qry_type='P1' and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null \r\n"
			+ "   \r\n"*/
			+ "   ")
	List<Collection> getQueryPendingViewacl( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);
	
	
	
	
	
	
	
	@Query(nativeQuery = true, value= "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
			+ "decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
			+ "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)\r\n"
			+ "||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),decode(m.role,'PAC',decode(m.stage,'A2','View Query Replied / Complete Assessment (from AC)'),\r\n"
			+ "decode(r.rply_date,null,decode(i.qry_type,'A2','Pending Additional Query Reply','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment',\r\n"
			+ "'Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply')),\r\n"
			+ "r.rply_date,'View Additional Query Replied / Complete Assessment' )),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r \r\n"
			+ "left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
			+ "left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
			+ "ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and \r\n"
			+ "(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
			+ "and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null")
	List<Collection> getQueryPendingView( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);
	
//	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'for Assesssment Approval' ),\r\n"
//			+ "decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date) \r\n"
//			+ "			from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and q.role=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offid \r\n"
//			+ "			and q.cin_no=r.cin_no and r.cus_site= :cusite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
//			+ "            union \r\n"
//			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','for Additional Query Approval'),'for Additional Query Approval'),r.rply_date,'for Assessment Approval' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
//			+ "			from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offid  \r\n"
//			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and q.mail_class_cd=d.code and set_aside is null  \r\n"
//		/*	+"         union \r\n"
//		    +"                        sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,'-','Pending for BAG SCAN','-',0 \r\n"
//		    +"   from fpo_main q,  ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d,ops$dir.pdi_nature_trans_codes t3 where (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
//		    +"        and instr(s.mail_class_cd,q.mail_class_cd) > 0  and i.qry_type='P1' and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null \r\n"
//			+ "   \r\n"*/
//			+ "   ")
//	List<Collection> getQueryPendingViewacl( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);


	
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Query Reply',r.rply_date,'View Query Replied / Complete Assessment' ) \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r, ops$dir.pdi_nature_trans_codes t3   where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y') \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.rply_date is null\r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Additional Query Reply',r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y') \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is null \r\n" + "order by 7 ")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),  decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(RECD_DT) \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r, ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS )   where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y' ) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1   and r.qry_no = (select max(qry_no) from  fpo_query where item_id=q.item_id)  and r.rply_date is null and r.cin_no = dc.cin_no\r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(RECD_DT) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3, (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and (q.arr_fpo='Y' ) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is null and r.cin_no = dc.cin_no \r\n" + "order by 7 ")
	/*@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),  decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(RECD_DT) \r\n"
			+ "									from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) , ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (arr_scan='Y') \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite  and s.user_id=:offid  and r.qry_no = (select max(qry_no) from  fpo_query where item_id=q.item_id) and q.off_id=:offid and r.rply_date is null\r\n" + "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(RECD_DT) \r\n"
			+ "from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and ( arr_scan='Y') \r\n"
			+ "and q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and qry_def_slno is null and r.rply_date is null\r\n" + "order by 7 ")
	List<Collection> getQueryView( @Param("cusite") String cusite, @Param("offid") String offid);*/
	
	
	
	@Query(nativeQuery = true, value = "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,m.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),  \r\n"
			+ "        decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), \r\n"
			+ "        trunc(sysdate) - TRUNC(qry_date),priority,job_no,job_dt \r\n"
			+ "		from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
			+ "        ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cusite and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
			+ "       and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
			+ "		and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
			+ " union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,m.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),  \r\n"
    +"        decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), \r\n"
    +"        trunc(sysdate) - TRUNC(qry_date),priority,job_no,job_dt \r\n"
	+"		from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
    +"        ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd=m.code  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3') \r\n"
    +"       and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
	+"		and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id)) ")
     List<Collection> getQueryView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);
    
	 
	/*@Query(nativeQuery = true, value = "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date),priority,job_no,job_dt \r\n"
			+ "												from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan='Y')   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "			and q.cin_no=r.cin_no and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and r.item_no is null and set_aside is null and r.rply_date is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)  "
			+ "         union \r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date),priority,job_no,job_dt\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P3') and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan='Y' )  and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.rply_date is null and set_aside is null and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id)) order by priority,job_no,job_dt ")
	List<Collection> getQueryView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);*/
	
	@Query(nativeQuery = true, value = "select sum(qrya+qryb) from (sELECT count(*) qrya \r\n"
			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) , ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (arr_scan='Y') \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite   and r.qry_no = (select max(qry_no) from  fpo_query where item_id=q.item_id)  and r.rply_date is null) x,"
			+ "(sELECT count(*) qryb \r\n"
			+ "from  fpo_main q ,  fpo_qry_deci i, fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and ( arr_scan='Y') \r\n"
			+ "and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is null) y ")
	Long gettotcouQuery( @Param("cusite") String cusite);
	
//	@Query(nativeQuery = true, value = "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),'PAO',\r\n"
//			+ " decode(r.rply_date,null,'Pending Query Reply',r.rply_date,'View Query Replied / Complete Assessment' ),priority,job_no,job_dt  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3   where i.cus_site=?1 and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( q.arr_fpo='Y' and arr_scan='Y') \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.rply_date is not null union "
//			+ " sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), 'PAO',decode(r.rply_date,null,'Pending Additional Query Reply',r.rply_date,'View Additional Query Replied / Complete Assessment' ),priority,job_no,job_dt \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3   where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and q.arr_fpo = 'Y' \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and r.rply_date is not null \r\n" + ")order by priority,job_no,job_dt ")
//	@Query(nativeQuery = true, value = "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),'PAO',\r\n"
//			+ " decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),priority,job_no,job_dt  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_mail_class_codes f, ops$dir.pdi_nature_trans_codes t3   , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc,(select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=?1 and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo='Y' and arr_scan='Y') \r\n"
//			+ "and f.code=q.mail_class_cd and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and r.rply_date is not null and r.cin_no = dc.cin_no union "
//			+ " sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), 'PAO',decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),priority,job_no,job_dt \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3  , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and i.qry_type = 'A3' and i.qry_no is null and q.arr_fpo = 'Y' \r\n"
//			+ "and f.code=q.mail_class_cd and q.cin_no=r.cin_no and q.role=?3 and q.off_id=?2 and qry_def_slno is null and r.rply_date is not null and r.cin_no = dc.cin_no \r\n" + ")order by priority,job_no,job_dt ")
	@Query(nativeQuery = true, value = "(sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),'PAO',\r\n"
			+ " decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),priority,job_no,job_dt  \r\n"
			+ "									from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_mail_class_codes f, ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cusite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
			+ "and f.code=q.mail_class_cd and q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and s.role_name = 'PAO' and r.cus_site= :cusite and r.item_no is null and r.rply_date is not null  union "
			+ " sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,tot_ass_val,cou,f.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), 'PAO',decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply'),'Pending Additional Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ),priority,job_no,job_dt \r\n"
			+ "from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  ,ops$dir.pdi_mail_class_codes f, ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
			+ "and f.code=q.mail_class_cd and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
			+ " and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid   and s.role_name = 'PAO' and (i.role=:role or i.role='IMP') and q.off_id=:offid and qry_def_slno is null and r.rply_date is not null) order by priority,job_no,job_dt ")
	List<Collection> getQueryreplyView( @Param("cusite") String cusite, @Param("offid") String offid, @Param("role") String role);

	
	@Query(nativeQuery = true, value = "select count(*) from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id left join fpo_detained_info c on a.item_id=c.article_id where qry_type='P5' and clr_site=:cusite and decode(cur_off_role,null,'PAO',cur_off_role)=:role and detain_completed is null ")
	Long gettotdet(@Param("cusite") String cusite, @Param("role") String role);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id left join fpo_detained_info c on a.item_id=c.article_id where qry_type='P5' and clr_site=:cusite  and detain_completed is null ")
	Long gettotdetsite(@Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_main a ,fpo_ooc_cancel_info c where a.item_id=c.article_id and  a.cus_site=:cusite  and ooc_cancel_completed is null")
	Long gettotooccancel(@Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_main a ,fpo_ooc_cancel_info c where a.item_id=c.article_id  and ooc_cancel_completed is null")
	Long gettotooccancelall();

	@Query(nativeQuery = true, value = "select count(*) from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id left join fpo_detained_info c on a.item_id=c.article_id where qry_type='P5'  and detain_completed is null ")
	Long gettotdetall();

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , ops$dir.pdi_mail_class_codes d, fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3   where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd=d.code  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and (i.qry_type = 'P3') and i.qry_no is null and (q.arr_scan is null) and q.nature_type_cd=t3.code  order by job_no,job_dt")
	List<Collection> getOOCPendingView( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , ops$dir.pdi_mail_class_codes d, fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and q.mail_class_cd=d.code  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and (i.qry_type = 'P4') and i.qry_no is null and (q.arr_scan is null) and q.nature_type_cd=t3.code  order by job_no,job_dt")
	List<Collection> getExamPendingView( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) , d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , ops$dir.pdi_mail_class_codes d, fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and q.mail_class_cd=d.code  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and (i.qry_type = 'P5') and i.qry_no is null and (q.arr_scan is null) and q.nature_type_cd=t3.code  order by job_no,job_dt")
	List<Collection> getDetentionPendingView( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "SELECT i.cin_no,i.ITEM_ID,to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) , d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , ops$dir.pdi_mail_class_codes d, fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO  and q.mail_class_cd=d.code  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null) and q.nature_type_cd=t3.code  order by job_no,job_dt")
	List<Collection> getEDIPendingView( @Param("cusite") String cusite);

	// TO SHOW IN PENDING QUEUES AND IN DASHBOARD PENDING QUEUE
	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='A2') and i.qry_no is null and (q.arr_fpo is null)  order by job_no,job_dt")
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0    and s.user_id=:offid  and s.role_name = 'PAO' and (q.arr_scan<>'Y' or q.arr_scan is null) and decode(q.role,null,'PAO',q.role)=:role and c.cin_no=q.cin_no and q.off_id=:offid order by job_no,job_dt")
	Long getcoupqry( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan is not null)  order by job_no,job_dt")
	Long gettotcouqry();


	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2') and i.qry_no is null and (q.arr_fpo is null)  order by job_no,job_dt")
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i , fpo_curr_que c where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null  and instr(s.mail_class_cd,q.mail_class_cd) > 0    and s.user_id=:offid  and s.role_name = 'PAO' and (q.arr_scan<>'Y' or q.arr_scan is null) and decode(q.role,null,'PAO',q.role)=:role  and c.cin_no=q.cin_no and q.off_id=:offid  order by job_no,job_dt")
	Long getcoupqryltr( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2') and i.qry_no is null  and (q.arr_fpo is null)  order by job_no,job_dt")
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_curr_que c  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null  and instr(s.mail_class_cd,q.mail_class_cd) > 0    and s.user_id=:offid   and s.role_name = 'PAO' and (q.arr_scan<>'Y' or q.arr_scan is null)  and decode(q.role,null,'PAO',q.role)=:role  and c.cin_no=q.cin_no and q.off_id=:offid  order by job_no,job_dt")
	Long getcoupqryems( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2') and i.qry_no is null  and (q.arr_fpo is null)   order by job_no,job_dt")
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_curr_que c  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null   and instr(s.mail_class_cd,q.mail_class_cd) > 0    and s.user_id=:offid  and s.role_name = 'PAO' and (q.arr_scan<>'Y' or q.arr_scan is null)  and decode(q.role,null,'PAO',q.role)=:role  and c.cin_no=q.cin_no and q.off_id=:offid  order by job_no,job_dt")
	Long getcoupqrypar( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);

	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main where item_id=:itemid and arr_scan='Y'")
	Long getarrinfo(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where substr(dcall_no,1,3)='AAA' AND item_id=:itemid")
	Long getaaagen(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where substr(dcall_no,1,3)='AAF' AND item_id=:itemid")
	Long getaaagenaaf(@Param("itemid") String itemid);
	
	
	
	//@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2') and i.qry_no is null and (q.arr_fpo is null)  order by job_no,job_dt")
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q, fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P2' or i.qry_type='P7'  or i.qry_type='A2' or i.qry_type='P1') and i.qry_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0    and s.user_id=:offid   and s.role_name = 'PAO' and (q.arr_scan<>'Y' or q.arr_scan is null)  and decode(q.role,null,'PAO',q.role)=:role   and c.cin_no=q.cin_no and q.off_id=:offid order by job_no,job_dt")
	Long getcoupqryemp( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P4') and i.qry_no is null  and (q.arr_scan is null) and q.role=:role  order by job_no,job_dt")
	Long getcoupexm( @Param("cusite") String cusite,@Param("role") String role);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where  i.CIN_NO = q.CIN_NO and (i.qry_type = 'P4') and i.qry_no is null and (q.arr_scan is not null)   order by job_no,job_dt")
	Long gettotcouexm();

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q,  fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO   and q.mail_class_cd='U' and (i.qry_type = 'P4') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupexmltr( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P4')    and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupexmems( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P4')  and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupexmpar( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P4')  and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupexmemp( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null) and q.role=:role  order by job_no,job_dt")
	Long getcoupedi( @Param("cusite") String cusite,@Param("role") String role);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.CIN_NO = q.CIN_NO and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long gettotcouedi();

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='U' and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupediltr( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='E' and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupediems( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO   and q.mail_class_cd='C' and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupedipar( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO   and q.mail_class_cd='T' and (i.qry_type = 'P6') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupediemp( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P3') and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and i.qry_no is null and (q.arr_scan is null)   order by job_no,job_dt")
	Long getcoupooc( @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q , fpo_qry_deci i where i.CIN_NO = q.CIN_NO and (i.qry_type = 'P3')  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and i.qry_no is null and (q.arr_scan is not null)   order by job_no,job_dt")
	Long gettotcouooc();

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q,fpo_qry_deci i where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U'  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no)and (i.qry_type = 'P3') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupoocltr( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite   and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E'  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no)and (i.qry_type = 'P3') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupoocems( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q ,fpo_qry_deci i where i.cus_site=:cusite  and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C'  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no)and (i.qry_type = 'P3') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupoocpar( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "sELECT count(*) from  fpo_main q,fpo_qry_deci i where i.cus_site=:cusite    and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T'  and i.id=(select max(id) from fpo_qry_deci where cin_no=q.cin_no) and (i.qry_type = 'P3') and i.qry_no is null and (q.arr_scan is null)  order by job_no,job_dt")
	Long getcoupoocemp( @Param("cusite") String cusite);

	@Query(nativeQuery = true, value = "select cin_no from  fpo_main where item_id = :itemId")
	String getCinIdByItemId(@Param("itemId") String itemId);

	/*
	 * //New code - total count of query queue
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select count(*) from  fpo_qry_deci where cus_site=?1 and qry_type = 'P2'")
	 * String gettotalcuntQQ( @Param("cusite") String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='U' and (i.qry_type = 'P2' or i.qry_type='P7')"
	 * ) String gettotalmailcatU(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P2' or i.qry_type='P7')"
	 * ) String gettotalmailcatE(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P2' or i.qry_type='P7')"
	 * ) String gettotalmailcatC(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P2' or i.qry_type='P7')"
	 * ) String gettotalmailcatT(String cusite);
	 * 
	 * //Count in Exam-Queue
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select count(*) from  fpo_qry_deci where cus_site=?1 and qry_type = 'P4'")
	 * String gettotalcuntEQ(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'P4')"
	 * ) String gettotalcuntEQU(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P4')"
	 * ) String gettotalcuntEQE(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P4')"
	 * ) String gettotalcuntEQC(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P4')"
	 * ) String gettotalcuntEQT(String cusite);
	 * 
	 * //Count in Exam-Queue
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select count(*) from  fpo_qry_deci where cus_site=?1 and qry_type = 'P3'")
	 * String gettotalcuntOOCQ(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'P3')"
	 * ) String gettotalcuntOOCQU(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P3')"
	 * ) String gettotalcuntOOCQE(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P3')"
	 * ) String gettotalcuntOOCQC(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P3')"
	 * ) String gettotalcuntOOCQT(String cusite);
	 * 
	 * // Count in EDI - Queue
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select count(*) from  fpo_qry_deci where cus_site=?1 and qry_type = 'P6'")
	 * String gettotalcuntEDIQ(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'P6')"
	 * ) String gettotalcuntEDIQU(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P6')"
	 * ) String gettotalcuntEDIQE(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P6')"
	 * ) String gettotalcuntEDIQC(String cusite);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT COUNT(q.mail_class_cd) from  fpo_main q , fpo_qry_deci i where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P6')"
	 * ) String gettotalcuntEDIQT(String cusite);
	 */

	// Dividing Mail-category
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Query Reply',r.rply_date,'View Query Replied / Complete Assessment' ) \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=?1 and mail_class_cd='U' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,'Pending Additional Query Reply',r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and mail_class_cd='U' and q.nature_type_cd=t3.code and i.qry_type = 'A2' and i.qry_no is null and (q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null  \r\n" + "order by 7 ")
	
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and mail_class_cd='U' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null and r.cin_no = dc.cin_no \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and mail_class_cd='U' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null and r.cin_no = dc.cin_no  \r\n" + "order by 7 ")
	
	/*@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
			+ "									from  fpo_main q , fpo_qry_deci i,  fpo_curr_que c , fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and q.mail_class_cd='U' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite and decode(q.role,null,'PAO',q.role)=:role and r.item_no is null and set_aside is null  \r\n" + "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
			+ "from  fpo_main q , fpo_qry_deci i, fpo_curr_que c , fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='U' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null  and decode(q.role,null,'PAO',q.role)=:role and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null  \r\n" + "order by 7 ")
	List<Collection> getcoupltrview( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);*/

	
	@Query(nativeQuery = true, value="select aaa1+aaa2 aaa from\r\n"
			+ "(sELECT count(*) aaa1\r\n"
			+ "		from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid \r\n"
			+ "		and q.cin_no=r.cin_no and r.cus_site= :cusite and q.mail_class_cd=:mc and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  \r\n"
			+ "        and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) x,\r\n"
			+ "			(sELECT count(*) aaa2\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r \r\n"
			+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
			+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  ,\r\n"
			+ "			ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and q.mail_class_cd=:mc and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and \r\n"
			+ "			(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
			+ "			and s.role_name = 'PAO' and q.mail_class_cd=d.code and set_aside is null)y ")
	Long getcoupendmc( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid,  @Param("mc") String mc);

	
	
	
	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
			+ "			decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid\r\n"
			+ "			and q.cin_no=r.cin_no and q.mail_class_cd='U' and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)\r\n"
			+ "			union\r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)\r\n"
			+ "			||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),decode(m.role,'PAC',decode(m.stage,'A2','View Query Replied / Complete Assessment (from AC)'),\r\n"
			+ "			decode(r.rply_date,null,decode(i.qry_type,'A2','Pending Additional Query Reply','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment',\r\n"
			+ "			'Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply')),\r\n"
			+ "			r.rply_date,'View Additional Query Replied / Complete Assessment' )),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r\r\n"
			+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
			+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  ,\r\n"
			+ "			ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and\r\n"
			+ "			(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='U' and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
			+ "			and s.role_name = 'PAO' and q.mail_class_cd=d.code ")
	List<Collection> getcoupltrview( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);


	
	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null)  and q.nature_type_cd=t3.code and set_aside is null  order by job_no,job_dt")

//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and mail_class_cd='E' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null and r.cin_no = dc.cin_no \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3  , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and mail_class_cd='E' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null and r.cin_no = dc.cin_no  \r\n" + "order by 7 ")

	/*@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
			+ "									from  fpo_main q , fpo_qry_deci i,  fpo_curr_que c , fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.mail_class_cd='E' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and set_aside is null \r\n" + "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' )   \r\n"
			+ "from  fpo_main q , fpo_qry_deci i,  fpo_curr_que c , fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='E' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and decode(q.role,null,'PAO',q.role)=:role and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null   \r\n" + "order by 7 ")
	List<Collection> getcoupemsview( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);*/
	
	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
			+ "			decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid\r\n"
			+ "			and q.cin_no=r.cin_no  and q.mail_class_cd='E'  and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)\r\n"
			+ "			union\r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)\r\n"
			+ "			||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),decode(m.role,'PAC',decode(m.stage,'A2','View Query Replied / Complete Assessment (from AC)'),\r\n"
			+ "			decode(r.rply_date,null,decode(i.qry_type,'A2','Pending Additional Query Reply','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment',\r\n"
			+ "			'Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply')),\r\n"
			+ "			r.rply_date,'View Additional Query Replied / Complete Assessment' )),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r\r\n"
			+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
			+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  ,\r\n"
			+ "			ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and\r\n"
			+ "			(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='E'  and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
			+ "			and s.role_name = 'PAO' and q.mail_class_cd=d.code ")
	List<Collection> getcoupemsview( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);

	
	@Query(nativeQuery = true, value = "select z.cin_no from\r\n"
			+ "(select x.cin_no,ROW_NUMBER() OVER (ORDER BY priority,job_no,job_dt) as slno  from \r\n"
			+ "	(SELECT  t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'U') >0 and t1.mail_class_cd='U' and t1.mail_class_cd=t5.code and set_aside is null and t4.role_name='PAO' \r\n"
			+ "				union \r\n"
			+ "				SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'E') >0 and t1.mail_class_cd='E'  and t1.mail_class_cd=t5.code and set_aside is null  and t4.role_name='PAO' \r\n"
			+ "				 union \r\n"
			+ "				SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd, t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'C') >0 and t1.mail_class_cd='C' and t1.mail_class_cd=t5.code  and set_aside is null   and t4.role_name='PAO' \r\n"
			+ "				 union \r\n"
			+ "				SELECT t1.cin_no, to_char(t1.cin_dt,'DD/MM/YYYY') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postdt,t1.item_id,t1.send_cntry_cd,t5.codedesc, t3.category,t1.tot_ass_val,t1.job_no,t1.job_dt,priority FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3,  ops$dir.pdi_mail_class_codes t5 , fpo_main t1 LEFT JOIN fpo_qry_deci t2 ON t1.CIN_NO = t2.CIN_NO WHERE t2.CIN_NO IS NULL  and t1.cus_site= :cusite  and (t1.role is null or (t1.role='PAO' and t1.acl_offid is null)) and (t1.off_id is null or t1.off_id= :offid) and t1.nature_type_cd=t3.code and t4.user_id=:offid and instr(t4.mail_class_cd,'T') >0 and t1.mail_class_cd='T' and t1.mail_class_cd=t5.code  and set_aside is null   and t4.role_name='PAO' order by 10,8,9)x,\r\n"
			+ "	(select count(*) no_items,cin_no from  fpo_item_det where cus_site=:cusite group by cin_no)y \r\n"
			+ "	where x.cin_no=y.cin_no  order by priority,job_no,job_dt)z where slno = 1 ")
			String getmincinno( @Param("cusite") String cusite, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null)  and q.nature_type_cd=t3.code and set_aside is null  order by job_no,job_dt")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and mail_class_cd='C' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null and r.cin_no = dc.cin_no \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and mail_class_cd='C' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null and r.cin_no = dc.cin_no  \r\n" + "order by 7 ")
	/*@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
			+ "									from  fpo_main q , fpo_qry_deci i,  fpo_curr_que c , fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.mail_class_cd='C' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite and r.item_no is null and set_aside is null \r\n" + "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' )  \r\n"
			+ "from  fpo_main q , fpo_qry_deci i,  fpo_curr_que c , fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc  on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='C' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and decode(q.role,null,'PAO',q.role)=:role and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null  \r\n" + "order by 7 ")
	List<Collection> getcoupparview( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);*/

	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
			+ "			decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid\r\n"
			+ "			and q.cin_no=r.cin_no  and q.mail_class_cd='C'  and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)\r\n"
			+ "			union\r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)\r\n"
			+ "			||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),decode(m.role,'PAC',decode(m.stage,'A2','View Query Replied / Complete Assessment (from AC)'),\r\n"
			+ "			decode(r.rply_date,null,decode(i.qry_type,'A2','Pending Additional Query Reply','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment',\r\n"
			+ "			'Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply')),\r\n"
			+ "			r.rply_date,'View Additional Query Replied / Complete Assessment' )),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r\r\n"
			+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
			+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  ,\r\n"
			+ "			ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and\r\n"
			+ "			(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='C' and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
			+ "			and s.role_name = 'PAO' and q.mail_class_cd=d.code ")
	List<Collection> getcoupparview( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);

	//@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY') from  fpo_main q , fpo_qry_deci i, ops$dir.pdi_nature_trans_codes t3  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and (q.arr_fpo is null)   and q.nature_type_cd=t3.code and set_aside is null  order by job_no,job_dt")
//	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
//			+ "									from  fpo_main q , fpo_qry_deci i, fpo_query r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc where i.cus_site=?1 and mail_class_cd='T' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and r.cus_site= ?1 and r.item_no is null and set_aside is null and r.cin_no = dc.cin_no \r\n" + "union \r\n"
//			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivery Confirmed','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' ) \r\n"
//			+ "from  fpo_main q , fpo_qry_deci i, fpo_addl_qry r,  ops$dir.pdi_nature_trans_codes t3 , (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc  where i.cus_site=?1 and i.CIN_NO = q.CIN_NO  and mail_class_cd='T' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and (q.arr_fpo<>'Y' or q.arr_fpo is null) \r\n"
//			+ "and q.cin_no=r.cin_no and qry_def_slno is null and set_aside is null and r.cin_no = dc.cin_no  \r\n" + "order by 7 ")
/*	@Query(nativeQuery = true, value = "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' )  \r\n"
			+ "									from  fpo_main q , fpo_qry_deci i,  ops$dir.d_emp_roles s, fpo_curr_que c , fpo_query r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.mail_class_cd='T' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and c.off_id=:offid \r\n"
			+ "and q.cin_no=r.cin_no and r.cus_site= :cusite  and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid   and s.role_name = 'PAO' and r.item_no is null and set_aside is null \r\n" + "union \r\n"
			+ "sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,q.mail_class_cd,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Additional Query Replied / Complete Assessment' )   \r\n"
			+ "from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c , fpo_addl_qry r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on ( r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.mail_class_cd='T' and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and i.qry_no is null and decode(q.role,null,'PAO',q.role)=:role and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and c.off_id=:offid  \r\n"
			+ "and q.cin_no=r.cin_no  and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid   and s.role_name = 'PAO' and qry_def_slno is null and set_aside is null  \r\n" + "order by 7 ")
	List<Collection> getcoupempview( @Param("cusite") String cusite, @Param("role") String role, @Param("offid") String offid);*/
	
	@Query(nativeQuery = true, value="sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'), decode(r.rply_date,null,decode(i.qry_type,'P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment','Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Query Reply'),'Pending Query Reply'),r.rply_date,'View Query Replied / Complete Assessment' ),\r\n"
			+ "			decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and decode(q.role,null,'PAO',q.role)=:role and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offid\r\n"
			+ "			and q.cin_no=r.cin_no  and q.mail_class_cd='T' and r.cus_site= :cusite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)\r\n"
			+ "			union\r\n"
			+ "			sELECT i.cin_no,i.ITEM_ID, to_char(q.cin_dt,'DD/MM/YYYY'),substr(postingdt,9,2)||'/'||substr(postingdt,6,2)\r\n"
			+ "			||'/'||substr(postingdt,0,4) ,d.codedesc,t3.category,q.SEND_CNTRY_CD,to_char(qry_dt,'DD/MM/YYYY'),decode(m.role,'PAC',decode(m.stage,'A2','View Query Replied / Complete Assessment (from AC)'),\r\n"
			+ "			decode(r.rply_date,null,decode(i.qry_type,'A2','Pending Additional Query Reply','P1',decode(dc.SPEEDPOST_DELI_STATUS,'Item Delivered','D-Call Letter Delivered, Reply Not Received in Time / Complete Assessment',\r\n"
			+ "			'Not Delivered','D-Call Letter Not Delivered, Reply Not Received in Time / Complete Assessment','Pending Additional Query Reply')),\r\n"
			+ "			r.rply_date,'View Additional Query Replied / Complete Assessment' )),decode (SPEEDPOST_DELI_STATUS,null,'Pending',SPEEDPOST_DELI_STATUS), trunc(sysdate) - TRUNC(qry_date)\r\n"
			+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r\r\n"
			+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
			+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no  ,\r\n"
			+ "			ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cusite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and\r\n"
			+ "			(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='T' and decode(q.role,null,'PAO',q.role)=:role and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offid and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
			+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offid  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
			+ "			and s.role_name = 'PAO' and q.mail_class_cd=d.code ")
	List<Collection> getcoupempview( @Param("cusite") String cusite,@Param("role") String role, @Param("offid") String offid);


	@Query(nativeQuery = true, value = "sELECT dcall_qry from ospath_fpo")
	String getdcallqrypath();

	@Query(nativeQuery = true, value = "sELECT dcall_qry_view from ospath_fpo")
	String getdcallqryviewpath();

	@Query(nativeQuery = true, value = "sELECT images_path from ospath_fpo")
	String getimagespath();

//	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_query where CIN_NO = :cinNo and QRY_DATE is not null")
//	Long getcounoqry(@Param("cinNo") String cinNo);
	
//	------------------ modified by veem ----------------------------------------
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_query where CIN_NO = :cinNo and ID=(select max(ID) from fpo_query where CIN_NO = :cinNo )  and QRY_DATE is not null")
	Long getcounoqry(@Param("cinNo") String cinNo);
	
//	---------------------------------------------------------------------------

	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_query where CIN_NO = :cinNo and QRY_DATE is not null and rply_date is not null")
	Long getcounoqryreply(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "SELECT QRY_TYP from  fpo_query where CIN_NO = :cinNo and item_no is null and qry is null and id=(select max(id) from fpo_query where cin_no=:cinNo and item_no is null and qry is null)")
	String getQryType(@Param("cinNo") String cinNo);

	// Reallocation

	@Query(nativeQuery = true, value = "select reas_cde, reas_desc from reallocation_reas")
	List<String> getreallocationdesc();

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_main a set a.off_id = :tossid where exists (select 1 from  fpo_status b where a.item_id = b.item_id and a.off_id = :frmssid and a.role= :role and a.mail_class_cd= :mailcls and a.item_id = :specificid and b.ooc_dt is null)")
	void specificid(@Param("role") String role, @Param("frmssid") String frmssid, @Param("tossid") String tossid, @Param("mailcls") String mailcls, @Param("specificid") String specificid);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_main a set a.off_id = :tossid where exists (select 1 from  fpo_status b where a.item_id = b.item_id and a.off_id = :frmssid and a.role= :role and a.mail_class_cd= :mailcls and b.ooc_dt is null)")
	void replaceoffid(@Param("role") String role, @Param("frmssid") String frmssid, @Param("tossid") String tossid, @Param("mailcls") String mailcls);

	@Query(nativeQuery = true, value = "select role_name from ops$dir.d_emp_roles where user_id = :ssoid")
	List<String> getrolename(String ssoid);

	@Query(nativeQuery = true, value = "select mail_class_cd from ops$dir.d_emp_roles where user_id = :ssoid and role_name = :role")
	List<String> getmailclass(String ssoid, @Param("role") String rolenme);

	@Query(nativeQuery = true, value = "select to_char(login_time,'dd/mm/yyyy hh24:mi:ss')  logintime FROM fpo_login_track where id=(select max(id) FROM fpo_login_track where off_id=:offid and id <> (select max(id) FROM fpo_login_track where off_id=:offid))")
	String getlogindetails(@Param("offid") String offid);
	
	// Allocation of FPO Site
	/*@Query(nativeQuery = true, value = "select a.article_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'), 2 ,\r\n"
			+ " to_char(recd_dt,'DD/MM/YYYY'), f.category,recp_name,recp_add1||' , '|| recp_add2,c.recp_id,a.bag_no from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code), ops$dir.fpo_sites j where b.cus_site is null  and a.status is null and c.status is null and j.map_code = concat(SUBSTR(a.recd_fpo, 1,5),5) and site_code = ?1 ")
	List<Collection> getartid(String CS, String csdisp);*/
	
	@Query(nativeQuery = true, value = "select a.article_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'), :csdisp ,\r\n"
			+ " to_char(recd_dt,'DD/MM/YYYY'), f.category,recp_name,recp_add1||' , '|| recp_add2,c.recp_id,a.bag_no from fpo_main b left join articlearr_fpo_info a  on b.item_id = a.article_id left join article_arr_info c on b.item_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code), ops$dir.fpo_sites j where b.cus_site is null  and a.status is null and c.status is null and concat(SUBSTR(a.recd_fpo, 1,5),5) = map_code and site_code=:CS ")
	List<Collection> getartid(String CS, String csdisp);
	
	@Query(nativeQuery = true, value = "select b.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'), \r\n"
			+ " to_char(recd_dt,'DD/MM/YYYY'), f.category,recp_name,recp_add1||' , '|| recp_add2,c.recp_id,a.bag_no from fpo_main b  left join articlearr_fpo_info a on b.item_id = a.article_id left join article_arr_info c on b.item_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) where b.cus_site is null  and a.status is null and c.status is null and a.article_id is null ")
	List<Collection> getartidnotmapped();
	
	@Query(nativeQuery = true, value = "select a.article_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'),concat(SUBSTR(recd_fpo, 1,5),5),\r\n"
			+ " to_char(recd_dt,'DD/MM/YYYY'), f.category from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) where a.status is null and c.status is null and concat(SUBSTR(a.recd_fpo, 1,5),5) = :CS and a.article_id = b.item_id")
	List<ArticleArrInfo> getartarr(@Param("CS") String CS);

	@Query(nativeQuery = true, value = "select a.article_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'),concat(SUBSTR(recd_fpo, 1,5),5),\r\n"
			+ " to_char(recd_dt,'DD/MM/YYYY'), f.category,recp_name,recp_add1||' , '|| recp_add2  from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code)  where b.cus_site is null  and a.status is null and c.status is null and a.article_id  = :artid")
	List<Collection> getsingleartiddata(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select a.article_id,to_char(c.booking_dt,'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',c.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,c.origcntrycd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe ,to_char(recd_event_dt,'DD/MM/YYYY'),concat(SUBSTR(recd_fpo, 1,5),5),\r\n"
			+ "to_char(recd_dt,'DD/MM/YYYY') from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (c.mail_class=dir.code) where b.cus_site is null and a.status is null and c.status is null and concat(SUBSTR(a.recd_fpo, 1,5),5) = :CS and b.item_id is null")
	List<Collection> withouteaddata(@Param("CS") String CS);
	
	@Query(nativeQuery = true, value = "select a.article_id,to_char(c.booking_dt,'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',c.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,c.origcntrycd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe ,to_char(recd_event_dt,'DD/MM/YYYY'),concat(SUBSTR(recd_fpo, 1,5),5),\r\n"
			+ "to_char(recd_dt,'DD/MM/YYYY'), f.category from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id left join fpo_delivery g on a.article_id=g.item_id\r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (c.mail_class=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) where  a.status is null and c.status is null and concat(SUBSTR(a.recd_fpo, 1,5),5) = :CS and b.item_id is null and g.item_id is null")
	List<Collection> withouteadarr(@Param("CS") String CS);
	
	@Query(nativeQuery = true, value = "select a.article_id,to_char(c.booking_dt,'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',c.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,c.origcntrycd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe ,to_char(recd_event_dt,'DD/MM/YYYY'),concat(SUBSTR(recd_fpo, 1,5),5),\r\n"
			+ "to_char(recd_dt,'DD/MM/YYYY') from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (c.mail_class=dir.code) where b.cus_site is null  and a.status is null and c.status is null and b.item_id is null and a.article_id = :CS")
	List<Collection> recrdforwihtoutead(@Param("CS") String CS);
	
	@Query(nativeQuery = true, value = "select item_id from  fpo_main where item_id = :artid")
	String returnval(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select a_name, a_addr1, a_addr2,destcntrycd,a_postcode from article_arr_info where article_id = :artid")
	List<String> getntinEADrecpinfo(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select s_nameaddr, s_postcode from article_arr_info where article_id = :artid  and status is null")
	List<String> getntinEADsendinfo(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select recp_name, recp_add1, recp_add2, recp_city, recp_state, recp_cntry_cd, recp_zip, recp_emailid, recp_phone, recp_fax from  fpo_main where item_id = :artid")
	List<String> getrecpinfrm(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select recp_id from  article_arr_info where article_id = :artid and status is null")
	String getrecpid(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = "select bag_no from  articlearr_fpo_info where article_id = :artid  and status is null")
	String getbagno(@Param("artid") String artid);


	@Query(nativeQuery = true, value = "select send_name, send_add1, send_add2, send_city, send_state, send_cntry_cd, send_zip, send_emailid, send_phone, send_fax from  fpo_main where item_id = :artid")
	List<String> getsendinfrm(@Param("artid") String artid);

	@Query(nativeQuery = true, value = "select distinct case when f1.arr_india is null and f1.arr_fpo is null then 'Article Not Yet Arrived' when f1.arr_india='Y' and f1.arr_fpo is null then 'Article Arrived in India - in Transit' when f1.arr_india='Y' and  arr_scan='Y' then 'Article Arrived at FPO Destination ' ||  f1.cus_site end as article_arr_info, arrinfo.ooe,to_char(arrinfo.recd_event_dt,'DD/MM/YYYY'), arrfpoinfo.recd_fpo,to_char(arrfpoinfo.recd_dt,'DD/MM/YYYY') from  fpo_main f1 left join article_arr_info arrinfo on (arrinfo.article_id = f1.item_id ) left join articlearr_fpo_info arrfpoinfo on (arrfpoinfo.article_id = f1.item_id )  where f1.item_id = :artid")
	List<Collection> getarticlests(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = " select case when arrfpoinfo.recd_fpo is null then 'Article Not Yet Arrived' else 'Article Arrived at FPO Destination - ' || arrfpoinfo.recd_fpo end as article_arr_info, arrinfo.ooe, to_char(arrinfo.recd_event_dt,'DD/MM/YYYY HH24:MI:SS'), \r\n"
			+ " arrfpoinfo.recd_fpo,to_char(arrfpoinfo.recd_dt,'DD/MM/YYYY HH24:MI:SS'), recp_id, bag_no from article_arr_info arrinfo left join articlearr_fpo_info arrfpoinfo on (arrfpoinfo.article_id = arrinfo.article_id ) where arrinfo.article_id = :artid and arrinfo.status is null and arrfpoinfo.status is null")
	List<Collection> EADarticlArrinfo(@Param("artid") String artid);
	
	@Query(nativeQuery = true, value = " select case when arrfpoinfo.recd_fpo is null then 'Article Not Yet Arrived' else 'Article Arrived at FPO Destination - ' || arrfpoinfo.recd_fpo end as article_arr_info, arrinfo.ooe, to_char(arrinfo.recd_event_dt,'DD/MM/YYYY HH24:MI:SS'), \r\n"
			+ " arrfpoinfo.recd_fpo,to_char(arrfpoinfo.recd_dt,'DD/MM/YYYY HH24:MI:SS'), recp_id, bag_no from  articlearr_fpo_info arrfpoinfo  left join  article_arr_info  arrinfo on (arrfpoinfo.article_id = arrinfo.article_id ) where arrfpoinfo.article_id = :artid and arrinfo.status is null and arrfpoinfo.status is null")
	List<Collection> EADarticlArrinfofpo(@Param("artid") String artid);
	

	@Query(nativeQuery = true, value = "select distinct (upper (state_name)), customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india where state_name not in 'Bihar +Jharkhand'")
	List<Collection> getStateNme();

	@Query(nativeQuery = true, value = "select distinct upper(district) from ops$dir.pdi_pincodes where upper(state_name) = :state")
	List<String> getDistNme(@Param("state") String state);

	// Update cusite in FPO_MIAN
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_main set cus_site =:CusSite where item_id= :artid")
	void updCusinFPOmain(@Param("artid") String artid, @Param("CusSite") String CusSite);

	// Update cusite in ITEM_DET
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update fpo_item_det set cus_site =:CusSite where item_id= :artid")
	void updCusinItmdet(@Param("artid") String artid, @Param("CusSite") String CusSite);

	// get destination fpo State
	@Query(nativeQuery = true, value = "select distinct (upper(state_name)), customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india where SUBSTR(customs_fpo_site_code,0,5) = :state and state_name not in 'Bihar +Jharkhand'")
	List<Collection> getDesState(@Param("state") String state);

	@Query(nativeQuery = true, value = "select recd_fpo from articlearr_fpo_info where article_id = :artid")
	String getsinglerecd(@Param("artid") String artid);

	// Re-call
	
				@Query(nativeQuery = true, value = "select arr_india, arr_fpo from  fpo_main where item_id =:itmId and cus_site =:Cs")
				List<Collection> getarticlestatus(@Param("itmId") String itmId, @Param("Cs") String Cs);
				
				@Query(nativeQuery = true, value = "select qry_type from  fpo_qry_deci where id=(select max(id) from  fpo_qry_deci where item_id = :itmId and cus_site = :Cs)")
				String getqrytyp(@Param("itmId") String itmId, @Param("Cs") String Cs);
				
				@Query(nativeQuery = true, value = "select distinct cin_no from  fpo_qry_deci where item_id = :itemId")
				String getcin_no(@Param("itemId") String itemId);
				
				@Transactional
				@Modifying 
				@Query(nativeQuery = true, value = "update fpo_qry_deci set role = :role , off_id = :offid, qry_type =:artStg where id =(select max(id) from  fpo_qry_deci where item_id=:artid and cus_site = :CusSite) and item_id = :artid and cus_site =:CusSite")
				void updatFpoQryDeci(@Param("artid") String artid, @Param("CusSite") String CusSite, @Param("role") String role, @Param("offid") String offid, String artStg);
				
				@Query(nativeQuery = true, value = "select cin_dt from  fpo_main where item_id = :itemId")
				Date getcinDt(@Param("itemId") String itemId);
				
				@Transactional
				@Modifying 
				@Query(nativeQuery = true, value = "update fpo_main set TOT_AMT_PAYABLE = :totalAmountPayable , TOT_FINE = :totalFine, TOT_PENAL =:totalPenalty where item_id=:item_ID")
				void updateTotalAmountPayable(@Param("item_ID") String item_ID, @Param("totalAmountPayable") float totalAmountPayable, @Param("totalFine") float totalFine, @Param("totalPenalty") float totalPenalty);
				
				@Query(nativeQuery = true, value = "select map_code from ops$dir.fpo_sites where site_code=:cs")
				String getmapcde(@Param("cs") String cs);
				
				@Query(nativeQuery = true, value = "select SUBSTR(site_code, 0, 5) as site_code from ops$dir.fpo_sites where SUBSTR(map_code,0,5) = :cs")
				String getsitcde(@Param("cs") String cs);

				@Transactional
				@Modifying(clearAutomatically = true)
				@Query(nativeQuery = true, value = "update FPO_MAIN set arr_scan = 'Y' where item_id in :list")
				void updateArrivalScan(@Param("list") List<String> list);
				
				@Transactional
				@Modifying(clearAutomatically = true)
				@Query(nativeQuery = true, value = "update FPO_STATUS set arr_scan_dt = :dt where item_id in :list")
				void updarrscandt(@Param("list") List<String> list, @Param("dt") Date dt);
				
				
			
				@Transactional
				@Modifying(clearAutomatically = true)
				@Query(nativeQuery = true, value = "update FPO_QRY_DECI set cus_site = :cuSite where item_id=:itemid")
				void updcussite_qrydeci(@Param("itemid") String itemid,@Param("cuSite") String site);
				
				@Query(nativeQuery = true, value = "select count(*) from fpo_main where item_id=:itemid and cus_site is null")
				int chksitenull(@Param("itemid") String itemid);
				
				@Query(nativeQuery = true, value = "SELECT decode(max(bag_nefseq),null,0,max(bag_nefseq)) FROM SEQ_TAB")
			 	Long getnefbagseq();
				
				@Query(nativeQuery = true, value = "SELECT count(*) FROM fpo_scan_info where scanned='M' and substr(bag_no,1,3)='NEF' and substr(bag_no,5,8)=:dt ")
				Long getbagseq(@Param("dt") String dt);
				
				
				@Transactional
				@Modifying(clearAutomatically = true)
				@Query(nativeQuery = true, value = "update seq_tab set bag_nefseq=bag_nefseq+1" )
				void updbagseqinc();
				
				@Query(nativeQuery = true, value = "select cus_site from  fpo_main where item_id = :itemId")
				String getCusite(@Param("itemId") String itemId);

				@Query(nativeQuery = true, value = "SELECT * from fpo_main WHERE cin_no=:cinNo and delivery is not null")
				List<FPO_MAIN> getDelivery(@Param("cinNo") String cinNo);
				
				@Query(nativeQuery = true, value = "select  ROW_NUMBER() over (order by (SELECT 0 from dual)) as id,history.* from (select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
						+ "afi.recd_dt arrivalFPODate,faq.rply_date replyDate,faq.rply_off_id || '-' || def.emp_name officerId,fm.cin_no cinNo,faq.qry_type articleStage,1 additionalQry,'' currentStatus \r\n"
						+ "from fpo_main fm join fpo_addl_qry faq on (fm.item_id=faq.item_id) \r\n"
						+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
						+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
						+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) \r\n"
						+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
						+ " join ops$dir.d_emp def on (def.user_id=faq.rply_off_id) \r\n"
						+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n" + "where fm.cus_site=:cusSite  and aai.status is null and afi.status is null \r\n"
						+ "and trunc(faq.rply_date) between to_date (:fromDate,'dd/mm/yyyy') AND to_date (:toDate , 'dd/mm/yyyy') \r\n" 
						+ "union \r\n"
						+ "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
						+ "afi.recd_dt arrivalFPODate,fq.rply_date replyDate,fq.rply_off_id || '-' || def.emp_name officerId,fm.cin_no cinNo,'' articleStage,0 additionalQry,'' currentStatus \r\n"
						+ "from fpo_main fm join fpo_query fq on (fm.item_id=fq.item_id) \r\n"
						+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
						+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
						+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) \r\n"
						+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
						+ "join ops$dir.d_emp def on (def.user_id=fq.rply_off_id) \r\n"
						+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fm.cus_site=:cusSite and aai.status is null and afi.status is null \r\n"
						+ "and trunc(fq.rply_date) between to_date (:fromDate, 'dd/mm/yyyy') AND to_date (:toDate,'dd/mm/yyyy')) history order by history.replyDate desc")
				List<QueryReplyHistoryBean> getQryReplyArticlesHistory(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);		
				
				@Query(nativeQuery = true, value = "select count(*) from fpo_status where item_id = :itemId  and ooc_dt is not null")
				Long checkOocGiven(@Param("itemId") String itemId);
				
				@Query(nativeQuery = true, value = "select fs.item_id articleId,status_desc deliveryStatus,fd.deli_dt  deliveryDate,fs.ooc_dt oocDate from fpo_status fs left join fpo_delivery fd on fs.item_id=fd.item_id left join deli_status_codes dsc on fd.deli_status=dsc.status_code where fs.item_id = :itemId ")
				List<OocDeliveryBean> getOocDeliveryDetails(@Param("itemId") String itemId)	;		
				
				@Query(nativeQuery = true, value = "select fm.cin_no as cinId,to_char(fm.job_dt,'dd/MM/yyyy') as receiptOfCusItm,\r\n"
						+ "to_char(fqd.qry_dt,'dd/MM/yyyy') as eadDecision,\r\n"
						+ "to_char(fpm.ent_dt,'dd/MM/yyyy') as entryDate,\r\n"
						+ "to_char(fpm.ext_dt,'dd/MM/yyyy') as exitDate,\r\n"
						+ "to_char(qry_date,'dd/MM/yyyy') as queryDate,\r\n"
						+ "to_char(order_date,'dd/MM/yyyy') as orderDate,to_char(ooc_dt,'dd/MM/yyyy') as oocDate\r\n"
						+ ",'' as status from fpo_main fm left join \r\n"
						+ "(select * from fpo_qry_deci where id in (select max(id) from fpo_qry_deci where qry_type='E4' group by cin_no)) fqd\r\n"
						+ "on fm.cin_no=fqd.cin_no left join \r\n"
						+ "(select * from fpo_mvmnt where id in (select max(id) from fpo_mvmnt group by cin_no)) fpm\r\n"
						+ "on fm.cin_no=fpm.cin_no left join \r\n"
						+ "(select * from fpo_query where id in (select max(id) from fpo_query group by cin_no)) fq\r\n"
						+ "on fm.cin_no=fq.cin_no left join \r\n"
						+ "(select * from fpo_order where id in (select max(id) from fpo_order group by cin_no)) fo\r\n"
						+ "on fm.cin_no=fo.cin_no left join \r\n"
						+ "(select * from fpo_status where id in (select max(id) from fpo_status group by cin_no)) fs\r\n"
						+ "on fm.cin_no=fs.cin_no where fm.cin_no=:id")
			 ViewArticle getArticleDates(@Param("id") String id);
				
				
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where cus_site=:cusSite")
			 List<String> getArticleIds(@Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where trunc(job_dt) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site=:cusSite")
			 List<String> getArticleIdsubdt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_qry_deci where id in (select max(id) from fpo_qry_deci group by cin_no) and qry_type='E4' and trunc(qry_dt) between to_date (:fromDate,'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site=:cusSite")
			 List<String> getArticleIdeaddt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_mvmnt where id in (select max(id) from fpo_mvmnt group by cin_no) and trunc(ent_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site = :cusSite ")
			 List<String> getArticleIdmvmentdt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_mvmnt where id in (select max(id) from fpo_mvmnt group by cin_no) and trunc(ext_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site = :cusSite ")
			 List<String> getArticleIdmvmextdt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_query where id in (select max(id) from fpo_query group by cin_no) and trunc(qry_date) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site = :cusSite")
			 List<String>getArticleIdqrydt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_order where id in (select max(id) from fpo_order group by cin_no) and trunc(order_date) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site = :cusSite ")
			 List<String> getArticleIdorddt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
				
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_status where id in (select max(id) from fpo_status group by cin_no) and trunc(ooc_dt) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site=:cusSite")
			 List<String> getArticleIdoocdt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_main where trunc(to_date(postingdt,'yyyy-mm-dd\"T\"hh24:mi:ss')) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site= :cusSite")
			 List<String> getArticleIdpostdt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select distinct(item_id) from fpo_query where id in (select max(id) from fpo_query group by cin_no) and trunc(rply_date) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site = :cusSite")
			 List<String> getArticleIdqryrplydt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			
			 @Query(nativeQuery = true, value="select distinct(article_id) from article_arr_info where id in (select max(id) from article_arr_info group by article_id) and trunc(recd_event_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and ooe=:cusSite")
			 List<String> getArticleIdarrooedt(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("cusSite") String cusSite);
			 
			 @Query(nativeQuery = true, value="select INP_REQ,QRY_DEF_SLNO,QRY_DESC,QRY_ID from fpo_second_defqry a join fpo_addl_qry b on (a.SLNO=b.QRY_DEF_SLNO) where b.CIN_NO= :cinNo order by QRY_ID")
			 List<FpoScndQryData> getFpoScndQryDataDoc(@Param("cinNo") String cinNo);
			 
			 @Query(nativeQuery = true, value="select distinct(article_id) from article_arr_info where recp_id=:Bagno and status is null")
			 List<String> getArticleIdsooe(@Param("Bagno") String Bagno);
			 
			 @Query(nativeQuery = true, value="select distinct(article_id) from articlearr_fpo_info where bag_no=:Bagno and status is null")
			 List<String> getArticleIdsfpo(@Param("Bagno") String Bagno);
			 
		/*	 @Query(nativeQuery = true, value="select arrival.*,fsi.scan_dt scannedDate  from (select bagNo,bagNoFlag,receivedDate,totalCount,withEadCurrSite,"
						+ "withEadOtherSite,withoutEad,nvl(notallotted,0) withEadSiteNotAllot  from "
						+ "(select x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and "
						+ "recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,withEadOtherSite,without_ead withoutEad,"
						+ "notallotted  from (select count(*) with_ead_thissite,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id "
						+ "and substr(b.ooe,1,5)=substr(a.clr_site,1,5) and a.clr_site = :cusSite and status is null group by recp_id) x left "
						+ "join (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and "
						+ "a.clr_site is null   and status is null group by recp_id) j on x.recp_id=j.recp_id,(select nvl(count(*),0) withEadOtherSite,"
						+ "recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)<>substr(a.clr_site,1,5) and "
						+ "a.clr_site <> :cusSite and status is null group by recp_id) l,(select count(*) with_ead_totcou,recp_id  from fpo_main a "
						+ ", article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.clr_site,1,5) and status is null group by "
						+ "recp_id)k,(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) "
						+ "and status is null group by recp_id) z,(select count(*) tot_cou, recp_id  from article_arr_info b where status is null "
						+ "group by recp_id  ) y where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id and x.recp_id=l.recp_id "
						+ "group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite,withEadOtherSite,without_ead,notallotted "
						+ "union select x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt)  from articlearr_fpo_info where status is null and bag_no=x.bag_no) "
						+ "receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,"
						+ "without_ead withoutEad,notallotted  from (select count(*) with_ead_thissite,bag_no  from fpo_main a , articlearr_fpo_info b "
						+ "where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and a.cus_site = :cusSite and status "
						+ "is null group by bag_no) x left join (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b "
						+ "where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no,"
						+ "(select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and "
						+ "substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and status is null group by bag_no) k,(select count(*) without_ead,bag_no "
						+ " from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z,"
						+ "(select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null group by bag_no  ) y where x.bag_no=y.bag_no "
						+ "and x.bag_no=z.bag_no and x.bag_no=k.bag_no group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite,without_ead,"
						+ "notallotted) order by 3, 2 desc, 5 desc) arrival join (select *  from fpo_scan_info where id in (select max(id)  from "
						+ "fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no where trunc(fsi.scan_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') order by fsi.scan_dt desc")
			 List<ArrivalHistoryBean> getArrivalHistory(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("cusSite") String cusSite);
			 
			 
			 @Query(nativeQuery = true, value="select afi.article_id articleId,case when (select count(*)  from fpo_main where item_id=afi.article_id)>0 then 'YES' else 'NO' end eadExist, \r\n"
				+ "substr(site_code,0,5) || 5 destinationFpo,case when (select count(*)  from fpo_order where item_id=afi.article_id)>0 then 'YES' else 'NO' end  \r\n"
				+ "ExamOrderExist,fsi.scan_report scanReport,country.cntry_nm originCountry  from articlearr_fpo_info afi left join fpo_scan_info fsi on (afi.article_id=fsi.article_id and fsi.bag_type = 'B') \r\n"
				+ "left join ops$dir.d_cntry_cd country on substr(afi.article_id,-2)=country.cntry_cd join ops$dir.fpo_sites on substr(recd_fpo,0,5)=substr(map_code,0,5) where afi.bag_no=:RecpId and afi.status is null order by afi.recd_dt desc")
			 List<BagArticleBean> getBagArticlesfpo(@Param("RecpId") String RecpId);
			 
			 @Query(nativeQuery = true, value="select aai.article_id articleId,case when (select count(*)  from fpo_main where item_id=aai.article_id)>0 then 'YES' else 'NO' end eadExist, \r\n"
						+ "coalesce((select site_code from fpo_main fm join ops$dir.fpo_sites on fm.cus_site=site_code where fm.item_id=aai.article_id),substr(site_code,0,5) || 5) \r\n"
						+ "destinationFpo,case when (select count(*)  from fpo_order where item_id=aai.article_id)>0 then 'YES' else 'NO' end ExamOrderExist,fsi.scan_report scanReport,country.cntry_nm originCountry \r\n"
						+ " from article_arr_info aai left join fpo_scan_info fsi on (aai.article_id=fsi.article_id and fsi.bag_type = 'R') \r\n"
						+ "left join ops$dir.d_cntry_cd country on aai.origcntrycd=country.cntry_cd join ops$dir.fpo_sites on substr(aai.ooe,0,5)=substr(map_code,0,5) where recp_id= :bagNo and aai.status is null order by aai.recd_event_dt desc")
			 List<BagArticleBean> getBagArticlesooe(@Param("bagNo") String bagNo);
			 
			 @Query(nativeQuery = true, value="select arr.article_id articleId,recp_id recptId,coalesce(arr.ooe,'-') ooe,recd_event_dt ooeDate,coalesce(fsi.scan_report,'-') scanReport  from article_arr_info arr left join fpo_scan_info fsi on (arr.article_id = fsi.article_id and fsi.bag_type ='R')  where arr.article_id=:articleId and arr.status is null")
			 ArticleArrivalInfoBean getArticleArrivalInfo(@Param("articleId") String articleId);*/
			 
			 @Query(nativeQuery = true, value="select *  from (select bagNo,bagNoFlag,receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
						+ " (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
						+ "   (select count(*) tot_cou, recp_id  from article_arr_info b where status is null and substr(ooe,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
						+ "   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(ooe,1,5)=substr(c.map_code,1,5) and decode(decode(a.clr_site,null,a.cus_site,a.clr_site) ,'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))=:cusSite  and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n" 
						+ "   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n" 
						+ "   (select nvl(count(*),0) withEadOtherSite,recp_id from  article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(decode(a.clr_site,null,a.cus_site,a.clr_site),'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))!=:cusSite and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
						+ "   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
						+ "   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
						+ "   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  \r\n"
						+ "  union  \r\n"
						+ "  select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
						+ "  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
						+ "  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
						+ "  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join \r\n"
						+ "  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
						+ "  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
						+ "  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no  \r\n"
						+ "  where x.bag_no is not null  \r\n"
						+ "  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
						+ "  order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info)  \r\n")
			 List<Collection> getBagScan(@Param("cusSite") String cusSite);
			 
			 
			 @Query(nativeQuery = true, value="select fs.id,fs.item_id,to_char(fs.ooc_dt , 'dd/mm/yyyy')as ooc_dt,CASE when (select count(*) FROM fpo_qry_deci fd where fd.item_id=fm.item_id and fd.qry_type='P6')>0 then 'Commercial' else 'Personal' end as status FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) join fpo_qry_deci fqd on  (fs.item_id=fqd.item_id)  where fm.cus_site=:cuSite and trunc(fs.OOC_DT) between to_date(:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and fqd.qry_type='P8' order by fs.ooc_dt desc")
			 List<DeliveryArticlesPendingColumns> getDeliveryArticlesPendingColumns(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("cuSite") String cuSite);
	
			 @Query(nativeQuery = true, value="select  Count(fs.OOC_DT)   FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) join fpo_qry_deci fqd on  (fs.item_id=fqd.item_id)  where fm.cus_site=:cuSite and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and fqd.qry_type='P8'")
			 BigDecimal getOocCountForDeliveryArticles(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("cuSite") String cuSite);
			 
			 @Query(nativeQuery = true, value="select  Count(fd.DELI_DT)  FROM fpo_delivery fd left join fpo_main a on fd.item_id=a.item_id  left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code) where a.cus_site=:cuSite and  trunc(DELI_DT) between to_date(:fromdate, 'dd/mm/yyyy') AND TO_DATE(:todate, 'dd/mm/yyyy') ")
			 BigDecimal getDeliveryCountForDeliveryArticles(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("cuSite") String cuSite);  
			 
			 @Query(nativeQuery = true, value="select fpo_code as id,''''||IN_START_PINCODE || ''' and ''' || IN_END_PINCODE||'''' as value,'From ' || IN_START_PINCODE || ' Up To ' || IN_END_PINCODE as data from OPS$DIR.pdi_pincode_mapping_india where STATE_NAME = :state and IN_START_PINCODE is not null and IN_END_PINCODE is not null")
			 List<SelectTag> getSelectTag(@Param("state") String state);
			 
			 @Query(nativeQuery = true, value="select ROW_NUMBER() OVER (ORDER BY POSTINGDT) id,a.article_id articleId,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
						+ "decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt,\r\n"
						+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY') recdeventDt, k.site_code recdfpo,\r\n"
						+ "to_char(recd_dt,'DD/MM/YYYY') recdDt,'' currentStatus, deci_nm eaddeci,recp_id recpid, bag_no bagno  from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join fpo_qry_deci h on b.item_id=h.item_id left join article_arr_info c \r\n"
						+ "on a.article_id=c.article_id left join fpo_curr_que g on (c.article_id=g.item_id)  left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd where a.status is null and c.status is null \r\n"
						+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode  or concat(SUBSTR(ooe, 1,5),5)=:mapcode ) and decode(curr_que,null,b.init_que,curr_que) <>'P8'")
			 List<ArticleArrInfo> repgetartarr(@Param("mapcode") String mapcode);
			 
			 
			 @Query(nativeQuery = true, value="select ROW_NUMBER() OVER (ORDER BY POSTINGDT) id,b.item_id articleId,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
						+ "b.cin_no cinno,to_char(b.cin_dt,'DD/MM/YYYY HH24:MI:SS') cindt,\r\n"
						+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,decode(recd_event_dt, null, '-',to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS')) recdeventDt, k.site_code recdfpo,\r\n"
						+ "to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,'' currentStatus,deci_nm eaddeci, recp_id recpid, bag_no bagno from fpo_qry_deci h,fpo_main b left join  articlearr_fpo_info a  on a.article_id=b.item_id left join article_arr_info c \r\n"
						+ "on b.item_id=c.article_id left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code)  left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code), deci_reas n where h.deci_cd=n.deci_cd and a.status is null and c.status is null \r\n"
						+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode or concat(SUBSTR(ooe, 1,5),5)=:mapcode)  and b.item_id = :itmid and a.status is null and c.status is null and b.item_id=h.item_id and h.id=(select max(id) from fpo_qry_deci where item_id=:itmid)  "
						+ " union "
						+ " select ROW_NUMBER() OVER (ORDER BY POSTINGDT) id,b.item_id articleId,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
								+ "b.cin_no cinno,to_char(b.cin_dt,'DD/MM/YYYY HH24:MI:SS') cindt,\r\n"
								+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,decode(recd_event_dt, null, '-',to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS')) recdeventDt, k.site_code recdfpo,\r\n"
								+ "to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,'' currentStatus,'-' eaddeci, recp_id recpid, bag_no bagno from fpo_main b left join  articlearr_fpo_info a  on a.article_id=b.item_id   left join article_arr_info c \r\n"
								+ "on b.item_id=c.article_id left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code)  left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) where a.status is null and c.status is null \r\n"
								+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode or concat(SUBSTR(ooe, 1,5),5)=:mapcode)  and b.item_id = :itmid and a.status is null and c.status is null and b.item_id not in (select item_id from fpo_qry_deci)")
					 List<Collection> getviewsinglercd(@Param("itmid") String itmid, @Param("mapcode") String mapcode);
			 
			 
			 @Query(nativeQuery = true, value="select x.cin_no, assval, bcd, igst,sws,oth,dutysum,totduty,nvl(tot_fine,0) fine,nvl(tot_penal,0) penalty,tot_amt_payable totpay from (select a.cin_no,sum(assval_insfrt) assval,sum(bcd_amt) bcd,sum(igst_amt) igst,sum(sw_amt) sws,sum(duty) dutysum,sum(nvl(oth_duty,0)) oth FROM fpo_item_det a \r\n"
						+ " left join (select sum(duty_amt) oth_duty,cin_no,item_no FROM fpo_item_det_othduty\r\n"
						+ " where cin_no=:cinno group by cin_no,item_no) b  on a.cin_no=b.cin_no  and a.item_no=b.item_no group by a.cin_no ) x, (select tot_duty totduty,tot_amt_payable,tot_fine,tot_penal,cin_no FROM fpo_main where CIN_NO = :cinno) y \r\n"
						+ " where x.cin_no=y.cin_no")
			 List<Collection> getgrandtot(@Param("cinno") String cinno);
			 
			 
			 @Query(nativeQuery = true, value="select bagno,scandt,arrdt, cussite from \r\n"
						+ "(select a.BAG_NO bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a left join articlearr_fpo_info b on a.article_id=b.article_id,  fpo_status c,fpo_main d \r\n"
						+ "where  a.OFF_ID=:ssoid  and decode(d.clr_site,null,d.cus_site,d.clr_site)=:cuSite  and a.role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') \r\n"
						+ "union \r\n"
						+ "select b.recp_id bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_event_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a left join  article_arr_info b on a.article_id=b.article_id, fpo_status c ,fpo_main d \r\n"
						+ "where a.OFF_ID=:ssoid    and decode(d.clr_site,null,d.cus_site,d.clr_site)=:cuSite  and a.role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy')) order by scandt desc ")
			 List<Object> getpbsdatasite(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("cuSite") String cuSite, @Param("ssoid") String ssoid);
			
			 
			 @Query(nativeQuery = true, value="select bagno,scandt,arrdt, cussite from \r\n"
						+ "(select a.BAG_NO bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, articlearr_fpo_info b, fpo_status c \r\n"
						+ "where  a.bag_no=b.bag_no  and a.OFF_ID=:ssoid  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') \r\n"
						+ "union \r\n"
						+ "select b.recp_id bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_event_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, article_arr_info b, fpo_status c \r\n"
						+ "where  a.bag_no=b.recp_id and a.OFF_ID=:ssoid  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy')) order by scandt desc ")
			 List<Object> getpbsdata(@Param("fromdate") String fromdate, @Param("todate") String todate,  @Param("ssoid") String ssoid);
			 
			 @Query(nativeQuery = true, value="select bagno,scandt,arrdt, cussite from \r\n"
						+ "(select a.BAG_NO bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a left join articlearr_fpo_info b on a.article_id=b.article_id, fpo_status c ,fpo_main d\r\n"
						+ "where   a.OFF_ID=:ssoid  and decode(d.clr_site,null,d.cus_site,d.clr_site) IN :cuSites  and a.role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') \r\n"
						+ "union \r\n"
						+ "select b.recp_id bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_event_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a left join  article_arr_info b on a.article_id=b.article_id, fpo_status c ,fpo_main d\r\n"
						+ "where  a.OFF_ID=:ssoid   and decode(d.clr_site,null,d.cus_site,d.clr_site) IN :cuSites  and a.role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy')) order by scandt desc ")
			 List<Object> getpbsdatasite1(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("cuSites") List<String> cussites, @Param("ssoid") String ssoid); 
			 
			 @Query(nativeQuery = true, value="select ead+aaa+aaf totcoultr, ead1+aaa1+aaf1 totcouems, ead2+aaa2+aaf2 totcoupar, ead3+aaa3+aaf3 totcouemp from\r\n"
						+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site=:cuSite and mail_class_cd='U')a,  \r\n"
						+"  (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite and mail_class_cd='U' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b,  \r\n"
						+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site=:cuSite and mail_class_cd='U' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y' or arr_scan is null))c,  \r\n"
						+"  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site=:cuSite and mail_class_cd='E')a1,  \r\n"
						+"  (select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite and mail_class_cd='E' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b1,  \r\n"
						+"  (select count(*) aaa1 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site=:cuSite and mail_class_cd='E' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c1,  \r\n"
						+"  (select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site=:cuSite and mail_class_cd='C')a2,   \r\n"
						+"  (select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite and mail_class_cd='C' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b2,  \r\n"
						+"  (select count(*) aaa2 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site=:cuSite and mail_class_cd='C' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c2,  \r\n"
						+"  (select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site=:cuSite and mail_class_cd='T')a3,   \r\n"
						+"  (select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite and mail_class_cd='T' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b3,  \r\n"
						+"  (select count(*) aaa3 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site=:cuSite and mail_class_cd='T' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c3")				
			 String[][] gettotcoumcsite(@Param("cuSite") String cuSite);
			 
		/*	 @Query(nativeQuery = true, value="select aaa+aaf+ead totcoultr, aaa1+aaf1+ead1 totcouems, aaa2+aaf2+ead2 totcoupar, aaa3+aaf3+ead3 totcouemp from\r\n"
						+"	(select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site is not null and mail_class_cd='U' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a,  \r\n"
						+"	(select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and mail_class_cd='U' and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'))b, \r\n"
						+"	(select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='U'  and a.cus_site is not null)c, \r\n"
						+"	(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site is not null and mail_class_cd='E'   and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a1,  \r\n"
						+"	(select count(*) aaa1 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and mail_class_cd='E'   and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null))b1, \r\n"
						+"	(select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='E' and a.cus_site is not null)c1, \r\n"
						+"	(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and mail_class_cd='C' and a.cin_no=c.cin_no and a.cus_site is not null and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a2,  \r\n"
						+"	(select count(*) aaa2 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no  and mail_class_cd='C' and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null))b2, \r\n"
						+"	(select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='C' and a.cus_site is not null)c2, \r\n"
						+"	(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and mail_class_cd='T' and a.cus_site is not null and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a3,  \r\n"
						+"	(select count(*) aaa3 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no  and mail_class_cd='T'  and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null))b3, \r\n"
						+"	(select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='T'  and a.cus_site is not null)c3")
			 TotalCountMailClass gettotcoumc();*/
			 @Query(nativeQuery = true, value="select ead+aaa+aaf gift, ead1+aaa1+aaf1 saleOfGoods, ead4+aaa4+aaf4 documents, ead2+aaa2+aaf2 returnedGoods, ead5+aaa5+aaf5 others, ead3+aaa3+aaf3 commercialSample from\r\n"
						+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and category='GIFT')a, \r\n"
						+" (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='GIFT' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b, \r\n"
						+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='GIFT' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c, \r\n"
						+"  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and category='SALE OF GOODS')a1,  \r\n"
						+"  (select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='SALE OF GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b1, \r\n"
						+"  (select count(*) aaa1 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='SALE OF GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c1, \r\n"
						+"  (select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code  and category='RETURNED GOODS')a2,  \r\n"
						+"  (select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='RETURNED GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b2, \r\n"
						+"  (select count(*) aaa2 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='RETURNED GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c2, \r\n"
						+"  (select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and category='COMMERCIAL SAMPLE')a3, \r\n"
						+"  (select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='COMMERCIAL SAMPLE' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b3, \r\n"
						+"  (select count(*) aaa3 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='COMMERCIAL SAMPLE' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c3, \r\n"
						+"   (select count(*) ead4 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and category='DOCUMENTS')a4,  \r\n"
						+"  (select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='DOCUMENTS' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b4, \r\n"
						+"  (select count(*) aaa4 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='DOCUMENTS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c4, \r\n"
						+"  (select count(*) ead5 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and category='OTHERS')a5,  \r\n"
						+"  (select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='OTHERS' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b5, \r\n"
						+"  (select count(*) aaa5 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='OTHERS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c5")
			 String[][] gettotcouicsite(@Param("cuSite") String cuSite);
		/*
		 * @Query(nativeQuery = true,
		 * value="select ead+aaa+aaf gift, ead1+aaa1+aaf1 saleOfGoods, ead4+aaa4+aaf4 documents, ead2+aaa2+aaf2 returnedGoods, ead5+aaa5+aaf5 others, ead3+aaa3+aaf3 commercialSample from\r\n"
		 * +" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code  and (a.role='PAO' or a.role is null) and category='GIFT')a, \r\n"
		 * +" (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='GIFT' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b, \r\n"
		 * +"  (select count(*) aaa from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='GIFT' and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c, \r\n"
		 * +"  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and (a.role='PAO' or a.role is null)  and category='SALE OF GOODS')a1,  \r\n"
		 * +"  (select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='SALE OF GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b1, \r\n"
		 * +"  (select count(*) aaa1 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='SALE OF GOODS'  and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c1, \r\n"
		 * +"  (select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and (a.role='PAO' or a.role is null)  and category='RETURNED GOODS')a2,  \r\n"
		 * +"  (select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='RETURNED GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b2, \r\n"
		 * +"  (select count(*) aaa2 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='RETURNED GOODS'  and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c2, \r\n"
		 * +"  (select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and (a.role='PAO' or a.role is null)  and category='COMMERCIAL SAMPLE')a3, \r\n"
		 * +"  (select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='COMMERCIAL SAMPLE' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b3, \r\n"
		 * +"  (select count(*) aaa3 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='COMMERCIAL SAMPLE'  and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c3, \r\n"
		 * +"   (select count(*) ead4 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and (a.role='PAO' or a.role is null)  and category='DOCUMENTS')a4,  \r\n"
		 * +"  (select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='DOCUMENTS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b4, \r\n"
		 * +"  (select count(*) aaa4 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='DOCUMENTS'  and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c4, \r\n"
		 * +"  (select count(*) ead5 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site=:cuSite  and nature_type_cd=code and (a.role='PAO' or a.role is null)  and category='OTHERS')a5,  \r\n"
		 * +"  (select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite   and nature_type_cd=code  and a.role='PAO' and category='OTHERS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b5, \r\n"
		 * +"  (select count(*) aaa5 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site=:cuSite   and nature_type_cd=code and category='OTHERS'  and a.role='PAO' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))c5"
		 * ) String[][] gettotcouicsite(@Param("cuSite") String cuSite);
		 */

/*			 @Query(nativeQuery = true, value= "select ead+aaa+aaf gift, ead1+aaa1+aaf1 saleOfGoods, ead4+aaa4+aaf4 documents, ead2+aaa2+aaf2 returnedGoods, ead5+aaa5+aaf5 others, ead3+aaa3+aaf3 commercialSample from\r\n"
						+"	(select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and code=nature_type_cd and category='GIFT' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a,  \r\n"
						+"	(select count(*) aaa from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes x where a.cin_no=b.cin_no and code=nature_type_cd and category='GIFT'   and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null )b, \r\n"
						+"	(select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='GIFT'   and a.cus_site is not null)c, \r\n"
						+"	(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='SALE OF GOODS'    and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a1,  \r\n"
						+"	(select count(*) aaa1 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and code=nature_type_cd and category='SALE OF GOODS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null )b1, \r\n"
						+"	(select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='SALE OF GOODS' and a.cus_site is not null)c1, \r\n"
						+"	(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no  and code=nature_type_cd and category='RETURNED GOODS'  and a.cin_no=c.cin_no  and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a2,  \r\n"
						+"	(select count(*) aaa2 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no  and code=nature_type_cd and category='RETURNED GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null )b2, \r\n"
						+"	(select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='RETURNED GOODS' and a.cus_site is not null)c2, \r\n"
						+"	(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='COMMERCIAL SAMPLE'  and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a3,  \r\n"
						+"	(select count(*) aaa3 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='COMMERCIAL SAMPLE'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null)b3, \r\n"
						+"	(select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='COMMERCIAL SAMPLE'   and a.cus_site is not null)c3, \r\n"
					      +"	(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='DOCUMENTS' and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a4,  \r\n"
						+"	(select count(*) aaa4 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='DOCUMENTS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null)b4, \r\n"
						+"	(select count(*) ead4 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='DOCUMENTS'   and a.cus_site is not null)c4, \r\n"
					      +"	(select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='OTHERS'   and qry_type  in ('P1','P2','P3','P4','A3','C1','P5','P6','P7') and (arr_fpo='Y' and arr_scan='Y') and ooc_dt is null)a5,  \r\n"
						+"	(select count(*) aaa5 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='OTHERS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','P5','P7','E4') and (arr_fpo is null)  and a.cus_site is not null)b5, \r\n"
						+"	(select count(*) ead5 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='OTHERS'   and a.cus_site is not null)c5 ")
			 TotalIcCount gettotcouic();*/
			 
			 @Query(nativeQuery = true, value= "select coua+coub personalImports,detained,commercialImports from\r\n"
						+ "(select count(*) Detained from fpo_main a,  fpo_curr_que b where a.cus_site=:cuSite and a.cin_no=b.cin_no  and b.curr_que='P5' ) a,\r\n"
						+ "(select count(*) commercialImports from fpo_main a,  fpo_curr_que b where a.cus_site=:cuSite and a.cin_no=b.cin_no  and b.curr_que='P6' )b,\r\n" + "\r\n"
						+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where a.cus_site=:cuSite and a.cin_no=b.cin_no and b.curr_que  in ('P1','P2','E1','E2','E3','E4','N1','N2','A1','A2','A3','P3','P4','P9','C1'))c,\r\n"
						+ "(select count(*) coub from (select cin_no from fpo_main a  where a.cus_site=:cuSite \r\n"
						+ "minus\r\n" 
						+ "select cin_no from fpo_curr_que b where b.cus_site=:cuSite ))d")
			String[][] getTotalImportCount(@Param("cuSite") String cuSite);
			 
			 @Query(nativeQuery = true, value= "select ead, aaa, aaf from \r\n"
						+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site=:cuSite)a,   \r\n"
						+" (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site=:cuSite and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b,  \r\n"
						+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site=:cuSite and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y' or arr_scan is null))c")
            String[][] gettotartcou (@Param("cuSite") String cuSite);
			 
			 
			 @Query(nativeQuery = true, value= "select * from (select send_cntry_cd country, count(*) noArticles, nvl(SUM(TOT_ASS_VAL),0) totAssVal from fpo_main where  trunc(to_date(substr(postingdt,9,2)  || '/' ||substr(postingdt,6,2)  || '/' ||substr(postingdt,0,4),'DD/MM/YYYY')) between TRUNC(ADD_MONTHS(SYSDATE, -3),'MM') and LAST_DAY(ADD_MONTHS(SYSDATE,-3)) and cus_site=:cuSite	group by  send_cntry_cd order by 2 desc	,3 desc ) where rownum <= 5")
			List<Collection> getCountryWiseArticles(@Param("cuSite") String cuSite);

			@Query(nativeQuery = true, value = "select off_id officerId,dep_cmts comments,entered_dt enteredDate from fpo_ooc_findings where cin_no= :cinNo and cus_site = :cuSite order by entered_dt desc")
			List<Object> getDeptComments(@Param("cinNo") String cinNo, @Param("cuSite") String cuSite);
			
			/* @Query("select fdi.id,fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
					+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
					+ "afi.recd_dt arrivalFPODate,fqd.qry_dt detentionDate,fdi.detain_decision detainDecision,"
					+ "fdi.detained_no detainedNo,'' currentStatus,\r\n"
					+ "fdi.detain_decision_dt detainDecisionDate from fpo_main fm\r\n"
					+ "join fpo_detained_info fdi on  (fm.item_id=fdi.article_id) \r\n"
					+ "left join fpo_qry_deci fqd on (fm.item_id=fqd.item_id) \r\n"
					+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
					+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
					+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
					+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
					+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fm.clr_site=:cuSite \r\n"
					+ " and qry_type='E4' and aai.status is null and afi.status is null and fdi.detain_completed ='Y' order by fdi.detain_decision_dt desc")
			 List<DetainArticleHistoryBean> getDetainedArticlesHistory(@Param("cuSite") String cuSite);*/
			
			
			@Query(nativeQuery = true, value = "select be_doc from fpo_manual_commercial where cin_no = :id and be_doc=:bedoc")
			String getbedocpdf(@Param("id") String id, @Param("bedoc") String bedoc);
			
			@Query(nativeQuery = true, value = "select inv_doc from fpo_manual_commercial where cin_no = :id and inv_doc=:invdoc")
			String getinvdocpdf(@Param("id") String id, @Param("invdoc") String invdoc);
			
			@Query(nativeQuery = true, value = "select chl_doc from fpo_manual_commercial where cin_no = :id and chl_doc=:challandoc")
			String getchallandocpdf(@Param("id") String id , @Param("challandoc") String challandoc);


			@Query(nativeQuery = true, value ="SELECT A.ARTICLE_ID article_id,\r\n"
					+ "       TO_CHAR(SCAN_DT,'DD/MM/YYYY HH24:MI:SS') scan_dt,\r\n"
					+ "       A.BAG_NO bag_no,\r\n"
					+ "       TO_CHAR(RECD_DT,'DD/MM/YYYY HH24:MI:SS') RECD_DT,\r\n"
					+ "       B.BAG_NO bag_no1 ,\r\n"
					+ "       BAG_TYPE,\r\n"
					+ "       TO_CHAR(F.OOC_DT,'DD/MM/YYYY HH24:MI:SS')ooc_dt\r\n"
					+ "FROM FPO_SCAN_INFO A\r\n"
					+ "JOIN ARTICLEARR_FPO_INFO B ON A.ARTICLE_ID=B.ARTICLE_ID\r\n"
					+ "JOIN FPO_STATUS F ON A.ARTICLE_ID=F.ITEM_ID\r\n"
					+ "WHERE (SCANNED='M' OR SCANNED='O') \r\n"
					+ "  AND BAG_TYPE='B' \r\n"
					+ "  AND A.OLD_BAG_NO IS NULL\r\n"
					+ "  AND A.ARTICLE_ID = F.ITEM_ID")
			List<Collection> getBagNORegulation();
			

			
			@Query(nativeQuery = true, value ="SELECT A.ARTICLE_ID,to_char(SCAN_DT,'DD/MM/YYYY HH24:MI:SS')SCAN_DT,A.OLD_BAG_NO,to_char(MAX(RECD_DT),'DD/MM/YYYY HH24:MI:SS')RECD_DT,B.BAG_NO,BAG_TYPE,\r\n"
					+ "to_char(A.REGULARISE_DT,'DD/MM/YYYY HH24:MI:SS')REGULARISE_DT\r\n"
					+ "FROM FPO_SCAN_INFO A, ARTICLEARR_FPO_INFO B WHERE A.ARTICLE_ID=B.ARTICLE_ID AND (SCANNED='M' OR SCANNED='O') \r\n"
					+ "AND BAG_TYPE='B' AND OLD_BAG_NO IS NOT NULL GROUP BY A.ARTICLE_ID,SCAN_DT,A.OLD_BAG_NO,B.BAG_NO,BAG_TYPE,REGULARISE_DT  ORDER BY REGULARISE_DT DESC \r\n"
					+ "")
			List<Collection> getBagNORegulationHstry();
			
			@Transactional
			@Modifying(clearAutomatically = true)
			@Query(nativeQuery = true, value = "UPDATE FPO_SCAN_INFO\r\n"
					+ "SET old_bag_no = :bagNo, bag_no = :receptId, REGULARISE_DT = :date, REGULARISE_OFFID = :regoffid \r\n"
					+ "WHERE article_id = :articleId\r\n"
					+ "  ")
			void updatabagforR(@Param("bagNo")String bagNo,@Param("receptId") String receptId,@Param("articleId") String articleId,@Param("date")Date date,@Param("regoffid")String regoffid);


			@Query(nativeQuery = true, value = "sELECT OOC_INFO from ospath_fpo")
			String getoocpdfqrypath();

	

}
