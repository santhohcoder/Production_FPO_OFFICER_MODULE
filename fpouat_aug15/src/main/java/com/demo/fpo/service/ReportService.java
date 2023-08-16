 package com.demo.fpo.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.apibean.SpeedPostNo;
import com.demo.fpo.apimodel.FpoSite_Allot;
import com.demo.fpo.bean.ArticleArrInfo;
import com.demo.fpo.bean.DcallHistory2;
import com.demo.fpo.bean.DcallHistory3;
import com.demo.fpo.bean.DetentionTrack;
import com.demo.fpo.bean.ImportExam;
import com.demo.fpo.bean.MisFPOReceptacleProcess;
import com.demo.fpo.bean.MisOOEReceptacleProcess;
import com.demo.fpo.bean.MisReportArtcDetained;
import com.demo.fpo.bean.MisReportArticleStatus;
import com.demo.fpo.bean.MisReportByItemCategory;
import com.demo.fpo.bean.MisReportByMailClass;
import com.demo.fpo.bean.MisReportColumns;
import com.demo.fpo.bean.MisReportCommercialQueue;
import com.demo.fpo.bean.MisReportDCall;
import com.demo.fpo.bean.MisReportEAD;
import com.demo.fpo.bean.MisReportFPOArrival;
import com.demo.fpo.bean.MisReportFPOReceptacleProcess;
import com.demo.fpo.bean.MisReportOOCCancel;
import com.demo.fpo.bean.MisReportOOEReceptacleProcess;
import com.demo.fpo.bean.MisReportOfficersArticles;
import com.demo.fpo.bean.MisReportOocGivenCountries;
import com.demo.fpo.bean.MisReportOocGivenCountriesNotfWise;
import com.demo.fpo.bean.MisReportOocGivenCountriesdelstatus;
import com.demo.fpo.bean.MisReportOocGivenDeliStatus;
import com.demo.fpo.bean.MisReportOocGivenDeliStatusDetails;
import com.demo.fpo.bean.MisReportOocPending;
import com.demo.fpo.bean.MisReportPBSOfficersArticles;
import com.demo.fpo.bean.MisReportQryRaised;
import com.demo.fpo.bean.MisReportQueryReplyStatus;
import com.demo.fpo.bean.MisReportWithoutPincode;
import com.demo.fpo.bean.Misreportcommercialunderprocess;
import com.demo.fpo.model.DeliveryArticlesColumns;
import com.demo.fpo.model.DeliveryArticlesPendingColumns;
import com.demo.fpo.model.DetainedColumns;
import com.demo.fpo.model.DetainedParcelClearence;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FPO_MANUALCOMMERCIALCOLUMNS;
import com.demo.fpo.model.Filepath;
import com.demo.fpo.model.Fpo_Item_Query;
import com.demo.fpo.model.ImportedParcelClearence;
import com.demo.fpo.model.ItemAmountDetails;
import com.demo.fpo.model.ItemDetails;
import com.demo.fpo.model.MonthNames;
import com.demo.fpo.model.OpeningReportColumns;
import com.demo.fpo.model.PostalediColumns;
import com.demo.fpo.model.Recently_process;
import com.demo.fpo.model.ReportColumns;
import com.demo.fpo.model.ReportCounts;
import com.demo.fpo.model.SelectTag;


@Service
@Component
public class ReportService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Environment environment;

	public String getdataFromaclassval() {
	    String query = "SELECT CASE WHEN NOT EXISTS (SELECT * FROM ops$dir.acl_assval) THEN '1' ELSE '0' END AS result FROM dual";
	    System.out.println("Executing query: " + query);
	    javax.persistence.Query nativeQuery = entityManager.createNativeQuery(query);
	    String result = nativeQuery.getSingleResult().toString();
	    System.out.println("Query result: " + result);
	    return result;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Recently_process> getrecentproc(String offid, String role,String cusite) {
		// TODO Auto-generated method stub
		List<Recently_process> result=new ArrayList<Recently_process>();
		try {
			//String queryStr="select a.item_id , b.STAGE_NAME,a.QUE_DT from fpo_curr_que a left join fpo_stages b on a.CURR_QUE=b.STAGE_CODE where OFF_ID=:offid order by QUE_DT desc";
	//		String queryStr="select a.item_id , b.STAGE_NAME,TO_CHAR(a.que_dt, 'DD/MM/YYYY HH24:MI:SS') AS que_dt,CODEDESC as MAIL_CLASS_CD,SEND_CNTRY_CD,CATEGORY,arr.recp_id,fpo.bag_no from fpo_curr_que a left join article_arr_info arr on a.item_id=arr.article_id left join articlearr_fpo_info fpo on a.item_id=fpo.article_id left join fpo_stages b on a.CURR_QUE=b.STAGE_CODE left join  fpo_main c on c.ITEM_ID=a.ITEM_ID left join ops$dir.pdi_mail_class_codes d on c.MAIL_CLASS_CD=d.code left join ops$dir.pdi_nature_trans_codes e on e.code=c.NATURE_TYPE_CD  where a.OFF_ID=:offid order by a.QUE_DT desc";
			String queryStr="select a.item_id ,TO_CHAR(a.ent_dt, 'DD/MM/YYYY HH24:MI:SS') AS que_dt,decode(substr(a.stage,1,1),'E','EAD','N','AAA','A',decode(stage,'A2','AAA','A3','AAF'),decode(arr_scan_dt, null, 'AAA',decode(sign(arr_scan_dt-ent_dt),0,'AAA',1,'AAA',-1,'AAF'))) STAGE_NAME,CODEDESC as MAIL_CLASS_CD,SEND_CNTRY_CD,CATEGORY,arr.recp_id,fpo.bag_no from fpo_mvmnt a left join article_arr_info arr on a.item_id=arr.article_id left join articlearr_fpo_info fpo on a.item_id=fpo.article_id  left join  fpo_main c on c.ITEM_ID=a.ITEM_ID left join ops$dir.pdi_mail_class_codes d on c.MAIL_CLASS_CD=d.code left join ops$dir.pdi_nature_trans_codes e on e.code=c.NATURE_TYPE_CD,fpo_status f  where a.officer_ID=:offid and a.role=:role and a.id=(select max(id) from fpo_mvmnt where item_id=a.item_id and cus_site=:cusite and officer_id=:offid and role=:role) and arr.status is null and fpo.status is null and a.cus_site=:cusite and a.item_id=f.item_id order by a.ent_DT desc";
			Query query = entityManager.createNativeQuery(queryStr,Recently_process.class);
			query.setParameter("offid", offid);
			query.setParameter("role", role);
			query.setParameter("cusite", cusite);
			result = (List<Recently_process>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}  



	public List<ReportColumns> getReportColumns(String fromdate, String todate, String WhereClasuses, ArrayList<String> selectedList, HttpSession session, HttpServletRequest request) {
		List<ReportColumns> reportColumn=new ArrayList<ReportColumns>();
		try {
			
//			String queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			if(tab.equalsIgnoreCase("custitm")) {
//				queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			}else if(tab.equalsIgnoreCase("arrival")) {
//				queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			}else if(tab.equalsIgnoreCase("ooc")) {
//				queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c join fpo_status fs on (c.item_id=fs.item_id)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID)  left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			}else if(tab.equalsIgnoreCase("delivery")) {
//				queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c join fpo_delivery fd on (c.item_id=fd.item_id)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id)   left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			}else if(tab.equalsIgnoreCase("cusrsp")) {
//				queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id) where c.cus_site='"+request.getSession().getAttribute("cuSite")+"' and qry_type='E4'";
//			}
//			
//			 
//			queryStr = filterquery(queryStr, filter,fromdate,todate);
//			
//			Query query = entityManager.createNativeQuery(queryStr, ReportColumns.class);
//			reportColumn = (List<ReportColumns>) query.getResultList();
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="select  m.cus_site as cusite,extract(day from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Days - ' || extract(hour from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Hours - ' || extract(minute from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Minutes - ' || extract(second from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss')))) || ' Seconds' rlytime,dg.DCALL_NO dcallno,dg.GEN_DT dcall_dt,'' speedpostno,'' speedpostdelstatus,'' curr_cd,'' ex_rate,'' revised_code,'' as assessedval,'' rms,fd.DELI_DT del_dt,fo.ORDER_DATE exam_dt, to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy') arr_dt,'' examfind,'' cn23, ROW_NUMBER() OVER(order by m.job_no DESC) as id,m.GROSS_WT,m.job_no,m.item_id,to_char(to_date(substr(m.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as coo,m.send_name as sender_name,m.send_add1 as sender_addr,m.recp_name,m.recp_add1 as rec_addr,m.recp_state,m.recp_zip,dr.deci_nm,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,TOT_DUTY as duty,TOT_AMT_PAYABLE,ff.fine_amt,fp.penal_amt,'' as cth,'' as item_desc,'' as assess_val,'' as item_vlue,'' as ITEM_NO,QRY,RPLY,DG.IP_ADDRESS as replyip,fo.EXAM_ORDER || ' / ' || fo.order_remark examination,to_char(fs.ooc_dt , 'dd/mm/yyyy') ooc_dt,to_char(fqd1.QRY_DT , 'dd/mm/yyyy') QRY_DT,aai.ooe  arriaval_date,aai.recp_id,afi.RECD_FPO as destfpo,afi.RECD_DT destfpo_dt,afi.bag_no,'' respedi,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_date,case when dc.status_desc='DELIVERED' then dm.MODE_DESC else dc.status_desc end as  deliverystatus,to_char(fqd2.QRY_DT , 'dd/mm/yyyy') detain_dt FROM fpo_main m  left join OPS$DIR.d_cntry_cd dtc on (m.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='E4' group by cin_no)) fqd on (m.item_id=fqd.item_id) left join deci_reas dr on (dr.deci_cd=fqd.DECI_CD)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=m.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (m.MAIL_CLASS_CD=pdm.code) left join (SELECT Sum(fine_amt) AS fine_amt,item_id FROM fpo_fine GROUP  BY item_id) ff on (m.item_id=ff.item_id) left join (select sum(penal_amt) as penal_amt,item_id FROM fpo_penal group by item_id) fp on (fp.item_id=m.item_id)  left join (select * FROM fpo_query where  id in (select max(id) FROM fpo_query where qry is not null group by cin_no)) fq on (fq.item_id=m.item_id) left join (select * FROM fpo_order where  id in (select max(id) FROM fpo_order group by cin_no)) fo on (m.cin_no=fo.cin_no) left join (select * FROM fpo_status where  cusitm_dt is not null) fs on (m.item_id=fs.item_id) left join fpo_delivery fd on (m.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dm on (dm.mode_code=DELI_MODE) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P6' group by cin_no)) fqd1 on (m.item_id=fqd1.item_id) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=m.item_id) left join (select * from articlearr_fpo_info where status = 'd') afi on (afi.ARTICLE_ID=m.item_id) LEFT JOIN (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P5' group by cin_no)) fqd2  ON ( m.item_id = fqd2.item_id ) LEFT JOIN DCALLQRY_GEN DG ON (M.ITEM_ID=DG.ITEM_ID)";
			if (cuSite.equals("INNSA5"))
				 queryStr= queryStr + " where trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')"+WhereClasuses;
			else 
			queryStr= queryStr + " where  m.cus_site=:cuSite and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')"+WhereClasuses;
			
			if(selectedList.contains("41") ||selectedList.contains("14") ||selectedList.contains("15") ||selectedList.contains("16") ||selectedList.contains("17") ||selectedList.contains("18") ||selectedList.contains("19") ||selectedList.contains("20") ||selectedList.contains("21") ||selectedList.contains("22")) {			
				queryStr="select  m.cus_site as cusite,extract(day from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Days - ' || extract(hour from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Hours - ' || extract(minute from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Minutes - ' || extract(second from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss')))) || ' Seconds' rlytime,dg.DCALL_NO dcallno,dg.GEN_DT dcall_dt,'' speedpostno,'' speedpostdelstatus,fad.CURRCD curr_cd,fad.CURR_RATE ex_rate,fid.ITEM_REVISEDDESC revised_code,fid.assess_val as assessedval,'' rms,fd.DELI_DT del_dt,fo.ORDER_DATE exam_dt, to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy') arr_dt, case when fef.item_fou=1 then 'Item Found / ' || fef.remarks when fef.item_fou=0 then 'Item Not Found / ' || fef.remarks else '' end as examfind,fid.cth cn23, ROW_NUMBER() OVER(order by m.job_no DESC, fid.item_no ASC) as id,m.GROSS_WT,m.job_no,m.item_id,to_char(to_date(substr(m.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as coo,m.send_name as sender_name,m.send_add1 as sender_addr,m.recp_name,m.recp_add1 as rec_addr,m.recp_state,m.recp_zip,dr.deci_nm,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,TOT_DUTY as duty,TOT_AMT_PAYABLE,ff.fine_amt,fp.penal_amt,fid.gen_cth as cth,fid.item_desc,fad.DECL_VAL as item_vlue,fid.ITEM_NO,QRY,RPLY,DG.IP_ADDRESS as replyip,fo.EXAM_ORDER || ' / ' || fo.order_remark examination,to_char(fs.ooc_dt , 'dd/mm/yyyy') ooc_dt,to_char(fqd1.QRY_DT , 'dd/mm/yyyy') QRY_DT,aai.ooe  arriaval_date,aai.recp_id,afi.RECD_FPO as destfpo,afi.RECD_DT destfpo_dt,afi.bag_no,'' respedi,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as       delivery_date,case when dc.status_desc='DELIVERED' then dm.MODE_DESC else dc.status_desc end as       deliverystatus,to_char(fqd2.QRY_DT , 'dd/mm/yyyy') detain_dt FROM fpo_main m  left join OPS$DIR.d_cntry_cd dtc on (m.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='E4' group by cin_no)) fqd on (m.item_id=fqd.item_id) left join deci_reas dr on (dr.deci_cd=fqd.DECI_CD)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=m.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (m.MAIL_CLASS_CD=pdm.code) left join (SELECT Sum(fine_amt) AS fine_amt,item_id FROM fpo_fine GROUP  BY item_id) ff on (m.item_id=ff.item_id) left join (select sum(penal_amt) as penal_amt,item_id FROM fpo_penal group by item_id) fp on (fp.item_id=m.item_id) left join (select * FROM fpo_item_det order by JOB_NO,item_no) fid on (m.item_id=fid.item_id) left join (select * FROM fpo_amend_item_det where AMEND_SERIAL_NO=1 order by item_no) fad on (m.item_id=fad.item_id and fid.item_no=fad.item_no) left join fpo_exam_findings fef on (fid.cin_no=fef.cin_no and fid.item_no=fef.item_no and fef.item_no is not null) left join (select * FROM fpo_query where  id in (select max(id) FROM fpo_query where qry is not null group by cin_no)) fq on (fq.item_id=m.item_id) left join (select * FROM fpo_order where  id in (select max(id) FROM fpo_order group by cin_no)) fo on (m.cin_no=fo.cin_no) left join (select * FROM fpo_status where  cusitm_dt is not null) fs on (m.item_id=fs.item_id) left join fpo_delivery fd on (m.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dm on (dm.mode_code=DELI_MODE) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P6' group by cin_no)) fqd1 on (m.item_id=fqd1.item_id) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=m.item_id) left join (select * from articlearr_fpo_info where status = 'd') afi on (afi.ARTICLE_ID=m.item_id) LEFT JOIN (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P5' group by cin_no)) fqd2  ON ( m.item_id = fqd2.item_id ) LEFT JOIN DCALLQRY_GEN DG ON (M.ITEM_ID=DG.ITEM_ID)  where fid.ITEM_UNIQUE is not null";
				if (cuSite.equals("INNSA5"))
					 queryStr= queryStr + " and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')"+WhereClasuses;
				else
					 queryStr= queryStr + " and m.cus_site=:cuSite and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')"+WhereClasuses;				
			}
//			if(WhereClasuses.equalsIgnoreCase("") && (selectedList.contains("29") || selectedList.contains("14") || selectedList.contains("15") || selectedList.contains("15") || selectedList.contains("17") || selectedList.contains("14"))) {
//				queryStr=queryStr.concat("order by m.job_no DESC, fid.item_no ASC");
//			}else if(WhereClasuses.equalsIgnoreCase("")) {
//				queryStr=queryStr.concat("order by m.job_no DESC");
//			}
			if(WhereClasuses.equalsIgnoreCase("")) {
				queryStr=queryStr.concat(" order by id");
			}
			Query query = entityManager.createNativeQuery(queryStr, ReportColumns.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);
			if (!(cuSite.equals("INNSA5")))				
			query.setParameter("cuSite",cuSite);
			reportColumn = (List<ReportColumns>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public String getFromDateForReport(String filter, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date()); 
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		try {
			String queryStr="select  to_char(max(c.job_dt) , 'dd/mm/yyyy') as ead_date FROM fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cuSite and qry_type='E4'";
			
			  Query query = entityManager.createNativeQuery(queryStr);
			  query.setParameter("cuSite",cuSite);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				 // Object object = resultList.get(0);
				  fromdate = (String)resultList.get(0);
				  }
			  
			  if(fromdate==null) {
				  fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		}
		return fromdate;
	}
	
	public List<ReportCounts> getReportCounts(String fromdate, String todate, String filter, String string, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ReportCounts> reportcounts=new ArrayList<ReportCounts>();
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		BigDecimal count = BigDecimal.ZERO;
		try {
			String queryStr = "select  count(fid.item_unique) FROM fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cuSite and qry_type='E4'";
			//queryStr = filterquery(queryStr, filter,fromdate,todate);

			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}

			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);
			
			List < Object > resultList = query.getResultList();
			if (resultList.size() > 0) {
			    count = (BigDecimal) resultList.get(0);
			}
			ReportCounts counts = new ReportCounts();
			counts.setCustitm(count.longValue());

			queryStr = "select count(fid.item_unique)  FROM fpo_main c join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cuSite and qry_type='E4'";

			//queryStr = filterquery(queryStr, filter,fromdate,todate);

			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);
			query = entityManager.createNativeQuery(queryStr);
			resultList = query.getResultList();
			if (resultList.size() > 0) {
			    count = (BigDecimal) resultList.get(0);
			}
			counts.setArrival(count.longValue());


			queryStr = "select  count(fid.item_unique) FROM fpo_main c join fpo_status fs on (c.item_id=fs.item_id)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID)  left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cuSite and qry_type='E4'";

			//queryStr = filterquery(queryStr, filter,fromdate,todate);
			
			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}
			
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);

			query = entityManager.createNativeQuery(queryStr);
			resultList = query.getResultList();
			if (resultList.size() > 0) {
			    count = (BigDecimal) resultList.get(0);
			}
			counts.setOoc(count.longValue());
			
			queryStr = "select  count(fid.item_unique) FROM fpo_main c join fpo_delivery fd on (c.item_id=fd.item_id)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id)   left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cuSite and qry_type='E4'";
			//queryStr = filterquery(queryStr, filter,fromdate,todate);
			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}
			
			
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);

			query = entityManager.createNativeQuery(queryStr);
			resultList = query.getResultList();
			if (resultList.size() > 0) {
			    count = (BigDecimal) resultList.get(0);
			}

			counts.setDelivery(count.longValue());

			queryStr = "select  count(fid.item_unique) FROM fpo_main c join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID) left join fpo_status fs on (c.item_id=fs.item_id) left join fpo_delivery fd on (c.item_id=fd.item_id) where c.cus_site=:cuSite and qry_type='E4'";

			//queryStr = filterquery(queryStr, filter,fromdate,todate);
			
			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
				
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by fqd.QRY_DT";
			}
		
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate",todate);

			query = entityManager.createNativeQuery(queryStr);
			resultList = query.getResultList();
			if (resultList.size() > 0) {
			    count = (BigDecimal) resultList.get(0);
			}

			counts.setCusrsp(count.longValue());
			query.setParameter("cuSite",cuSite);
			//query.setParameter("fromdate", fromdate);

			reportcounts.add(counts);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportcounts;
	}

/*	private String filterquery(String queryStr,String filter,String fromdate, String todate) {
		// TODO Auto-generated method stub
		try {

			if(filter.equalsIgnoreCase("job_date")) {
				queryStr=queryStr+" and trunc(c.job_dt) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by fqd.QRY_DT";
				
			}else if(filter.equalsIgnoreCase("article_arrived_info")) {
				queryStr=queryStr+" and trunc(aai.RECD_EVENT_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("ooc_date")) {
				queryStr=queryStr+" and trunc(fs.OOC_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("delivery_date")) {
				queryStr=queryStr+" and trunc(fd.DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by fqd.QRY_DT";
			}else if(filter.equalsIgnoreCase("sent_date")) {
				queryStr=queryStr+" and trunc(cs.SENT_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by fqd.QRY_DT";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryStr;
	} */

	public List<PostalediColumns> getPostalEdiColumns( HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String offid = request.getSession().getAttribute("offId").toString();
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		List<PostalediColumns> postalediColumns=new ArrayList<PostalediColumns>();
		try {
		//	String queryStr="select  fid.item_unique as id,c.item_id,c.cin_no,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd FROM ops$dir.d_emp_roles r, fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) where c.cus_site=:cuSite and qry_type='P6' and r.user_id=:offid and instr(r.mail_class_cd,c.mail_class_cd) > 0  and r.role_name = 'PAO'";
			String queryStr="select  c.item_id,c.cin_no,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd FROM ops$dir.d_emp_roles r, fpo_main c  join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) where c.cus_site=:cuSite and qry_type='P6' and r.user_id=:offid and instr(r.mail_class_cd,c.mail_class_cd) > 0  and r.role_name = 'PAO'";
			Query query = entityManager.createNativeQuery(queryStr, PostalediColumns.class);
			query.setParameter("offid", offid);
			query.setParameter("cuSite",cuSite);
			postalediColumns = (List<PostalediColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return postalediColumns;
	}
	
	
	
	
//	public List<FPO_MANUAL_COMMERCIAL> getPostalediqucolumns( HttpSession session, HttpServletRequest request) {
//		List<FPO_MANUAL_COMMERCIAL> postalediqColumns = new ArrayList<FPO_MANUAL_COMMERCIAL>();
//		try {
//			// String queryStr = "select * from fpo_manual_commercial";
//			//String queryStr = "select a.be_no, a.be_dt, a.iec, a.gstin_id, a.adcode, a.scheme_cd, a.license_no, a.permission_no,a.edi_site,a.challan_no,a.challan_dt,a.cin_no,a.cin_dt,a.item_id,a.entered_dt,a.entered_by from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on (b.emp_no = a.entered_by)";
//		
//			
//			//String queryStr ="select a.id, a.BE_NO,a.BE_DT,a.CIN_DT,a.CHALLAN_DT,a.IEC,a.GSTIN_ID, a.ADCODE,a.SCHEME_CD,a.LICENSE_NO,a.PERMISSION_NO,a.EDI_SITE,a.CHALLAN_NO,a.CIN_NO,a.ITEM_ID,a.ENTERED_BY,a.ENTERED_DT,b.EMP_NAME from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on b.user_id=a.entered_by";
//			
//			String queryStr="select a.id, a.BE_NO, BE_DT, CIN_DT, CHALLAN_DT,a.IEC,a.GSTIN_ID, a.ADCODE,a.SCHEME_CD,a.LICENSE_NO,a.PERMISSION_NO,a.EDI_SITE,a.CHALLAN_NO,a.CIN_NO,a.ITEM_ID,a.ENTERED_BY, ENTERED_DT,b.EMP_NAME from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on (b.user_id=a.entered_by)";
//
//			
//			Query query = entityManager.createNativeQuery(queryStr, FPO_MANUAL_COMMERCIAL.class);
//			postalediqColumns = (List<FPO_MANUAL_COMMERCIAL>) query.getResultList();
//			System.out.println(queryStr);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return postalediqColumns;
//	}
	
	public List<FPO_MANUALCOMMERCIALCOLUMNS> getPostalediqucolumns( HttpSession session, HttpServletRequest request) {
		List<FPO_MANUALCOMMERCIALCOLUMNS> postalediqColumns = new ArrayList<FPO_MANUALCOMMERCIALCOLUMNS>();
		try {
			// String queryStr = "select * from fpo_manual_commercial";
			//String queryStr = "select a.be_no, a.be_dt, a.iec, a.gstin_id, a.adcode, a.scheme_cd, a.license_no, a.permission_no,a.edi_site,a.challan_no,a.challan_dt,a.cin_no,a.cin_dt,a.item_id,a.entered_dt,a.entered_by from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on (b.emp_no = a.entered_by)";
		
			
			//String queryStr ="select a.id, a.BE_NO,a.BE_DT,a.CIN_DT,a.CHALLAN_DT,a.IEC,a.GSTIN_ID, a.ADCODE,a.SCHEME_CD,a.LICENSE_NO,a.PERMISSION_NO,a.EDI_SITE,a.CHALLAN_NO,a.CIN_NO,a.ITEM_ID,a.ENTERED_BY,a.ENTERED_DT,b.EMP_NAME from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on b.user_id=a.entered_by";
			
			String queryStr="select a.id, a.BE_NO as beNO, to_char( a.BE_DT, 'DD/MM/YYYY') as beDT, to_char( a.CIN_DT, 'DD/MM/YYYY') as cinDt, to_char( a.CHALLAN_DT, 'DD/MM/YYYY') as challanDT,a.IEC,a.GSTIN_ID as gstINid, a.ADCODE,a.SCHEME_CD as schemeCd,a.LICENSE_NO as licenseNo,a.PERMISSION_NO as permissionNo,a.EDI_SITE as ediSite,a.CHALLAN_NO as challanNo,a.CIN_NO as cinNo,a.ITEM_ID,a.ENTERED_BY as enteredBy, to_char( a.ENTERED_DT, 'DD/MM/YYYY') as EnteredDt,a.be_doc as bedoc,a.inv_doc as invdoc,a.chl_doc as challandoc,b.EMP_NAME as empName from fpo_manual_commercial a LEFT JOIN OPS$DIR.d_emp b on (b.user_id=a.entered_by) order by a.ENTERED_DT desc";

			
			Query query = entityManager.createNativeQuery(queryStr, FPO_MANUALCOMMERCIALCOLUMNS.class);
			postalediqColumns = (List<FPO_MANUALCOMMERCIALCOLUMNS>) query.getResultList();
			System.out.println(queryStr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return postalediqColumns;
	}

	
	

	public List<DetainedColumns> getDetainedColumns( HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DetainedColumns> postalediColumns=new ArrayList<DetainedColumns>();
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		try {
			String queryStr="select  fid.item_unique as id,c.item_id,to_char(fqd.QRY_DT , 'dd/mm/yyyy') as ead_date,c.MAIL_CLASS_CD as mail_category,c.CUST_ORG_CD as org_cd,fid.item_no,fid.nou,fid.cth FROM fpo_main c  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) where c.cus_site=:cuSite and qry_type='P5'";
			Query query = entityManager.createNativeQuery(queryStr, DetainedColumns.class);
			query.setParameter("cuSite",cuSite);
			postalediColumns = (List<DetainedColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return postalediColumns;
	}

	public List<DeliveryArticlesColumns> getDeliveryArticlesColumns(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DeliveryArticlesColumns> deliveryarticles=new ArrayList<DeliveryArticlesColumns>();
		String cusite = request.getSession().getAttribute("cuSite").toString();
		try {
			//String queryStr="select fs.id,fs.ITEM_ID,fd.DELI_PO,to_char(fd.DELI_DT , 'dd/mm/yyyy') as DELI_DT,fd.DELI_STATUS,fd.DELI_MODE FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) left join fpo_delivery fd on (fm.ITEM_ID=fd.ITEM_ID)   where fm.cus_site='"+request.getSession().getAttribute("cuSite")+"'  and trunc(fd.DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy')";
	//		String queryStr="select fd.id,a.cus_site cussite,a.item_id,substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4) postingdt,decode(a.cus_site,null,'WITHOUT_EAD','WITH_EAD') eadstatus, ooc_dt oocdt,codedesc mailclass,DELI_PO,to_char(DELI_DT , 'dd/mm/yyyy') as DELI_DT,status_desc as DELI_STATUS,dc.mode_desc as DELI_MODE FROM fpo_delivery fd left join fpo_main a on fd.item_id=a.item_id  left join fpo_status s on a.item_id = s.item_id left join ops$dir.pdi_mail_class_codes j on a.mail_class_cd=j.code left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code) where trunc(DELI_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and a.cus_site=:cusite order by DELI_DT desc";
			String queryStr="select fd.id,a.cus_site cussite,a.item_id,substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4) postingdt,decode(a.cus_site,null,'WITHOUT_EAD','WITH_EAD') eadstatus,ooc_dt oocdt,codedesc mailclass,DELI_PO,to_char(DELI_DT , 'dd/mm/yyyy') as DELI_DT,status_desc as DELI_STATUS,dc.mode_desc as DELI_MODE FROM fpo_delivery fd left join fpo_main a on fd.item_id=a.item_id  left join fpo_status s on a.item_id = s.item_id left join ops$dir.pdi_mail_class_codes j on a.mail_class_cd=j.code left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code) where a.cus_site=:cusite  and  to_date(substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4),'DD/MM/YYYY') between to_date(:fromdate,'DD/MM/YYYY') and to_date(:todate, 'DD/MM/YYYY') and DELI_DT is NOT NULL order by DELI_DT desc";
			Query query = entityManager.createNativeQuery(queryStr, DeliveryArticlesColumns.class);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("cusite", cusite);
			deliveryarticles = (List<DeliveryArticlesColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deliveryarticles;
	}
	
	
	public List<DeliveryArticlesColumns> getDeliverynoArticlesColumns(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DeliveryArticlesColumns> deliveryarticles=new ArrayList<DeliveryArticlesColumns>();
		String cusite = request.getSession().getAttribute("cuSite").toString();
		try {
			//String queryStr="select fs.id,fs.ITEM_ID,fd.DELI_PO,to_char(fd.DELI_DT , 'dd/mm/yyyy') as DELI_DT,fd.DELI_STATUS,fd.DELI_MODE FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) left join fpo_delivery fd on (fm.ITEM_ID=fd.ITEM_ID)   where fm.cus_site='"+request.getSession().getAttribute("cuSite")+"'  and trunc(fd.DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy')";
			String queryStr="select fd.job_no id,fd.cus_site cussite,fd.item_id,substr(fd.postingdt,9,2)||'/'||substr(fd.postingdt,6,2)||'/'||substr(fd.postingdt,0,4) postingdt,decode(fd.cus_site,null,'WITHOUT_EAD','WITH_EAD') eadstatus, ooc_dt oocdt,codedesc mailclass,DELI_PO,to_char(DELI_DT , 'dd/mm/yyyy') as DELI_DT,status_desc as DELI_STATUS,dc.mode_desc as DELI_MODE FROM fpo_main fd left join fpo_delivery a on a.item_id=fd.item_id left join fpo_status s on a.item_id = s.item_id left join ops$dir.pdi_mail_class_codes j on fd.mail_class_cd=j.code left join deli_status_codes dc on (dc.status_code=deli_status)left join deli_mode_codes dc on (a.deli_mode=dc.mode_code) where  fd.cus_site=:cusite  and  to_date(substr(fd.postingdt,9,2)||'/'||substr(fd.postingdt,6,2)||'/'||substr(fd.postingdt,0,4),'DD/MM/YYYY') between to_date(:fromdate,'DD/MM/YYYY') and to_date(:todate, 'DD/MM/YYYY') and deli_dt is NULL order by job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, DeliveryArticlesColumns.class);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("cusite", cusite);
			deliveryarticles = (List<DeliveryArticlesColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deliveryarticles;
	}
	
	
	public List<DeliveryArticlesColumns> getoocsentArticlesColumns(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DeliveryArticlesColumns> oocsentarticles=new ArrayList<DeliveryArticlesColumns>();
		String cusite = request.getSession().getAttribute("cuSite").toString();
		try {
			//String queryStr="select fs.id,fs.ITEM_ID,fd.DELI_PO,to_char(fd.DELI_DT , 'dd/mm/yyyy') as DELI_DT,fd.DELI_STATUS,fd.DELI_MODE FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) left join fpo_delivery fd on (fm.ITEM_ID=fd.ITEM_ID)   where fm.cus_site='"+request.getSession().getAttribute("cuSite")+"'  and trunc(fd.DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy')";
			String queryStr="select s.id,a.cus_site cussite,a.item_id,substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4) postingdt,decode(a.cus_site,null,'WITHOUT_EAD','WITH_EAD') eadstatus, to_char(ooc_dt,'DD/MM/YYYY HH24:MI:SS') oocdt,codedesc mailclass,' - ' as DELI_PO,'-' as DELI_DT,'-' as DELI_STATUS,'-' as DELI_MODE FROM fpo_main a left join fpo_status s on a.item_id = s.item_id left join ops$dir.pdi_mail_class_codes j on a.mail_class_cd=j.code  where trunc(OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and a.cus_site=:cusite order by OOC_DT desc";
			Query query = entityManager.createNativeQuery(queryStr, DeliveryArticlesColumns.class);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("cusite", cusite);
			oocsentarticles = (List<DeliveryArticlesColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return oocsentarticles;
	}

	public String getFromDateForDelivery(String filter, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cusite = request.getSession().getAttribute("cuSite").toString();
		String fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());  
		try {
			String queryStr="select  to_char(max(fs.OOC_DT) , 'dd/mm/yyyy') as ooc_date FROM fpo_main c join fpo_status fs on (c.item_id=fs.item_id)  join fpo_item_det fid on (c.item_id=fid.item_id) join fpo_qry_deci fqd on  (c.item_id=fqd.item_id) left join article_arr_info aai on (c.item_id=aai.ARTICLE_ID)  left join fpo_delivery fd on (c.item_id=fd.item_id)  left join (select * from cusrsp_sent where  id in (select max(id) from cusrsp_sent group by cin_no)) cs on (c.cin_no=cs.cin_no) where c.cus_site=:cusite and qry_type='E4'";
			
			  Query query = entityManager.createNativeQuery(queryStr);
			  query.setParameter("cusite", cusite);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				 // Object object = resultList.get(0);
				  fromdate = (String)resultList.get(0);
				  }
			  
			  if(fromdate==null) {
				  fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fromdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		}
		return fromdate;
	}

/*	public BigDecimal getOocCountForDeliveryArticles(String fromdate, String todate, String filter, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		BigDecimal count = BigDecimal.ZERO;
		try {
			String queryStr="select  Count(fs.OOC_DT)   FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) join fpo_qry_deci fqd on  (fs.item_id=fqd.item_id)  where fm.cus_site='"+request.getSession().getAttribute("cuSite")+"'  and trunc(fs.OOC_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') and fqd.qry_type='P8'";
			
			  Query query = entityManager.createNativeQuery(queryStr);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				 // Object object = resultList.get(0);
				  count = (BigDecimal)resultList.get(0);
				  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			count = BigDecimal.ZERO;
		}
		return count;
	}*/

	/*public BigDecimal getDeliveryCountForDeliveryArticles(String fromdate, String todate, String filter, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		BigDecimal count = BigDecimal.ZERO; 
		try {
			String queryStr="select  Count(fd.DELI_DT)  FROM fpo_delivery fd left join fpo_main a on fd.item_id=a.item_id  left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code) where a.cus_site='"+request.getSession().getAttribute("cuSite")+"' and  trunc(DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy')";
			
			  Query query = entityManager.createNativeQuery(queryStr);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				 // Object object = resultList.get(0);
				  count = (BigDecimal)resultList.get(0);
				  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			count = BigDecimal.ZERO;
		}
		return count;
	}*/

	public List<ImportedParcelClearence> getImportedParcelClearencecolumnsColumns(String startdate,String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		
		/*
		 * Long Mnth=(Long.parseLong(month)-1l); month=new
		 * DecimalFormat("00").format(Double.valueOf(Mnth.toString()));
		 */
		// TODO Auto-generated method stub
		List<ImportedParcelClearence> result=new ArrayList<ImportedParcelClearence>();
		String cusite=request.getSession().getAttribute("cuSite").toString();
		try {
			String queryStr="SELECT 1                                                                 AS id,\r\n"
					+ "       opening_balance,\r\n"
					+ "       month_receipt,\r\n"
					+ "       uptomonth_receipt,\r\n"
					+ "       ooc_without_exam_ass,\r\n"
					+ "       ooc_exam,\r\n"
					+ "       ooc_ass_exam,\r\n"
					+ "       detained,\r\n"
					+ "       ( ooc_without_exam_ass + ooc_exam + ooc_ass_exam\r\n"
					+ "         + detained )                                                    AS\r\n"
					+ "       total,\r\n"
					+ "       disposal_upto_month,\r\n"
					+ "       ( ( opening_balance + month_receipt ) - ( disposal_upto_month ) ) AS\r\n"
					+ "       closing_balance,\r\n"
					+ "       customs_duty_month,\r\n"
					+ "       customs_duty_upto_month\r\n"
					+ "FROM   (SELECT (SELECT Count(*)\r\n"
					+ "                FROM fpo_main fm\r\n"
					+ "                WHERE  Trunc(job_dt) < To_date (:startdate, 'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site =:cusite\r\n"
					+ "                       AND 0 = (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci f\r\n"
					+ "                                WHERE  qry_type = 'P8'\r\n"
					+ "                                       AND f.item_id = fm.item_id))\r\n"
					+ "               opening_balance,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') =:month\r\n"
					+ "                       AND To_char(job_dt, 'yyyy') =:year\r\n"
					+ "                       AND cus_site =:cusite)\r\n"
					+ "               month_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') <=:month\r\n"
					+ "                       AND Trunc(job_dt) >= To_date (:financialdateandyear, 'dd/mm/yyyy')\r\n"
					+ "                       AND Trunc(job_dt) <= To_date (:enddate, 'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site = :cusite)\r\n"
					+ "               uptomonth_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate\r\n"
					+ "                       AND 0 = (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P4')\r\n"
					+ "                       AND c.cus_site =:cusite)\r\n"
					+ "                      ooc_without_exam_ass,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P4')\r\n"
					+ "                       AND 0 = (SELECT Count(*)\r\n"
					+ "                                FROM fpo_item_det ft\r\n"
					+ "                                WHERE  ft.item_id = c.item_id\r\n"
					+ "                                       AND ft.updass_dt IS NOT NULL)\r\n"
					+ "                       AND c.cus_site =:cusite)                   ooc_exam,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P4')\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_item_det ft\r\n"
					+ "                                WHERE  ft.item_id = c.item_id\r\n"
					+ "                                       AND ft.updass_dt IS NOT NULL)\r\n"
					+ "                       AND c.cus_site =:cusite)                   ooc_ass_exam\r\n"
					+ "               ,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')\r\n"
					+ "                       AND c.cus_site =:cusite)                   detained,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :financialdateandyear AND :enddate\r\n"
					+ "                       AND c.cus_site =:cusite\r\n"
					+ "               disposal_upto_month\r\n"
					+ "                      ,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  To_char(deli_dt, 'mm') =:month\r\n"
					+ "                       AND To_char(deli_dt, 'yyyy') =:year\r\n"
					+ "                       AND cus_site =:cusite)                     AS\r\n"
					+ "                      customs_duty_month,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  Trunc(fd.deli_dt) BETWEEN :financialdateandyear AND :enddate\r\n"
					+ "                       AND cus_site = :cusite)                     AS\r\n"
					+ "                      customs_duty_upto_month\r\n"
					+ "        FROM   dual) ";
			Query query = entityManager.createNativeQuery(queryStr, ImportedParcelClearence.class);
			query.setParameter("startdate",startdate);
			query.setParameter("cusite",cusite);
			query.setParameter("month", month);
			query.setParameter("year",year);
			
			query.setParameter("financialdateandyear",financialdateandyear);
			query.setParameter("enddate",enddate);
			result = (List<ImportedParcelClearence>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<DetainedParcelClearence> getDetainedParcelClearencecolumnsColumns(String startdate,String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DetainedParcelClearence> result=new ArrayList<DetainedParcelClearence>();
		String cusite=request.getSession().getAttribute("cuSite").toString();
		try {
		/*	String queryStr="SELECT 1                                                                 AS id,\r\n"
					+ "       opening_balance,\r\n"
					+ "       month_receipt,\r\n"
					+ "       uptomonth_receipt,\r\n"
					+ "       detained_for_month,\r\n"
					+ "       detained_upto_month,\r\n"
					+ "       disposal_for_month,\r\n"
					+ "       disposal_upto_month,\r\n"
					+ "       ( ( opening_balance + month_receipt ) - ( disposal_upto_month ) ) AS\r\n"
					+ "       closing_balance,\r\n"
					+ "       customs_duty_month,\r\n"
					+ "       customs_duty_upto_month\r\n"
					+ "FROM   (SELECT (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  Trunc(job_dt) < To_date ('"+startdate+"', 'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 = (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci f\r\n"
					+ "                                WHERE  qry_type = 'P8'\r\n"
					+ "                                       AND f.item_id = c.item_id)\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) opening_balance,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') = '"+month+"'\r\n"
					+ "                       AND To_char(job_dt, 'yyyy') = '"+year+"'\r\n"
					+ "                       AND cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) month_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') <= '"+month+"'\r\n"
					+ "                       AND Trunc(job_dt) >= To_date ('"+financialdateandyear+"', 'dd/mm/yyyy')\r\n"
					+ "                       AND Trunc(job_dt) <= To_date ('"+enddate+"', 'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               uptomonth_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main fm\r\n"
					+ "                       JOIN (SELECT *\r\n"
					+ "                             FROM fpo_qry_deci\r\n"
					+ "                             WHERE  id IN (SELECT Max(id)\r\n"
					+ "                                           FROM fpo_qry_deci\r\n"
					+ "                                           GROUP  BY item_id)) fq\r\n"
					+ "                         ON ( fm.item_id = fq.item_id )\r\n"
					+ "                WHERE  fq.qry_type = 'P5'\r\n"
					+ "                       AND To_char(fq.qry_dt, 'mm') = '"+month+"'\r\n"
					+ "                       AND To_char(fq.qry_dt, 'yyyy') = '"+year+"'\r\n"
					+ "                       AND fm.cus_site = '"+request.getSession().getAttribute("cuSite")+"')\r\n"
					+ "               detained_for_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main fm\r\n"
					+ "                       JOIN (SELECT *\r\n"
					+ "                             FROM fpo_qry_deci\r\n"
					+ "                             WHERE  id IN (SELECT Max(id)\r\n"
					+ "                                           FROM fpo_qry_deci\r\n"
					+ "                                           GROUP  BY item_id)) fq\r\n"
					+ "                         ON ( fm.item_id = fq.item_id )\r\n"
					+ "                WHERE  ( fq.qry_type = 'P5' )\r\n"
					+ "                       AND To_char(fq.qry_dt, 'mm') <= '"+month+"'\r\n"
					+ "                       AND Trunc(fq.qry_dt) >= To_date ('"+financialdateandyear+"',\r\n"
					+ "                                               'dd/mm/yyyy')\r\n"
					+ "                       AND Trunc(fq.qry_dt) <= To_date ('"+enddate+"',\r\n"
					+ "                                               'dd/mm/yyyy')\r\n"
					+ "                       AND fm.cus_site = '"+request.getSession().getAttribute("cuSite")+"')\r\n"
					+ "               detained_upto_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  To_char(fs.ooc_dt, 'mm') = '"+month+"'\r\n"
					+ "                       AND To_char(fs.ooc_dt, 'yyyy') = '"+year+"'\r\n"
					+ "                       AND c.cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               disposal_for_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN '"+financialdateandyear+"' AND '"+enddate+"'\r\n"
					+ "                       AND c.cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               disposal_upto_month,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  To_char(deli_dt, 'mm') = '"+month+"'\r\n"
					+ "                       AND To_char(deli_dt, 'yyyy') = '"+year+"'\r\n"
					+ "                       AND cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) AS\r\n"
					+ "               customs_duty_month,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  Trunc(fd.deli_dt) BETWEEN '"+financialdateandyear+"' AND '"+enddate+"'\r\n"
					+ "                       AND cus_site = '"+request.getSession().getAttribute("cuSite")+"'\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) AS\r\n"
					+ "                      customs_duty_upto_month\r\n"
					+ "        FROM   dual) ";
			Query query = entityManager.createNativeQuery(queryStr, DetainedParcelClearence.class);
			query.setParameter()
			result = (List<DetainedParcelClearence>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;*/
		
			String queryStr="SELECT 1 AS id,\r\n"
					+ "       opening_balance,\r\n"
					+ "       month_receipt,\r\n"
					+ "       uptomonth_receipt,\r\n"
					+ "       detained_for_month,\r\n"
					+ "       detained_upto_month,\r\n"
					+ "       disposal_for_month,\r\n"
					+ "       disposal_upto_month,\r\n"
					+ "       ( ( opening_balance + month_receipt ) - ( disposal_upto_month ) ) AS\r\n"
					+ "       closing_balance,\r\n"
					+ "       customs_duty_month,\r\n"
					+ "       customs_duty_upto_month\r\n"
					+ "FROM   (SELECT (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  Trunc(job_dt) < To_date (:startdate, 'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site = :cusite \r\n"
					+ "                       AND 0 = (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci f\r\n"
					+ "                                WHERE  qry_type = 'P8'\r\n"
					+ "                                       AND f.item_id = c.item_id)\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) opening_balance,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') = :month \r\n"
 					+ "                       AND To_char(job_dt, 'yyyy') = :year \r\n"
					+ "                       AND cus_site = :cusite \r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) month_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                WHERE  To_char(job_dt, 'mm') <= :month \r\n"
					+ "                       AND Trunc(job_dt) >= To_date ( :financialdateandyear, 'dd/mm/yyyy')\r\n"
					+ "                       AND Trunc(job_dt) <= To_date (:enddate,'dd/mm/yyyy')\r\n"
					+ "                       AND cus_site = :cusite \r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               uptomonth_receipt,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main fm\r\n"
					+ "                       JOIN (SELECT *\r\n"
					+ "                             FROM fpo_qry_deci\r\n"
					+ "                             WHERE  id IN (SELECT Max(id)\r\n"
					+ "                                           FROM fpo_qry_deci\r\n"
					+ "                                           GROUP  BY item_id)) fq\r\n"
					+ "                         ON ( fm.item_id = fq.item_id )\r\n"
					+ "                WHERE  fq.qry_type = 'P5'\r\n"
					+ "                       AND To_char(fq.qry_dt, 'mm') = :month \r\n"
					+ "                       AND To_char(fq.qry_dt, 'yyyy') = :year \r\n"
					+ "                       AND fm.cus_site = :cusite) \r\n"
					+ "               detained_for_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main fm\r\n"
					+ "                       JOIN (SELECT *\r\n"
					+ "                             FROM fpo_qry_deci\r\n"
					+ "                             WHERE  id IN (SELECT Max(id)\r\n"
					+ "                                           FROM fpo_qry_deci\r\n"
					+ "                                           GROUP  BY item_id)) fq\r\n"
					+ "                         ON ( fm.item_id = fq.item_id )\r\n"
					+ "                WHERE  ( fq.qry_type = 'P5' )\r\n"
					+ "                       AND To_char(fq.qry_dt, 'mm') <= :month \r\n"
					+ "                       AND Trunc(fq.qry_dt) >= To_date (:financialdateandyear, 'dd/mm/yyyy')\r\n"
					+ "                       AND Trunc(fq.qry_dt) <= To_date (:enddate, 'dd/mm/yyyy')\r\n"
					+ "                       AND fm.cus_site = :cusite)\r\n"
					+ "               detained_upto_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  To_char(fs.ooc_dt, 'mm') = :month \r\n"
					+ "                       AND To_char(fs.ooc_dt, 'yyyy') = :year \r\n"
					+ "                       AND c.cus_site = :cusite '\r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               disposal_for_month,\r\n"
					+ "               (SELECT Count(*)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_status fs\r\n"
					+ "                         ON ( c.item_id = fs.item_id )\r\n"
					+ "                WHERE  Trunc(fs.ooc_dt) BETWEEN :financialdateandyear AND :enddate \r\n"
					+ "                       AND c.cus_site = :cusite \r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5'))\r\n"
					+ "               disposal_upto_month,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  To_char(deli_dt, 'mm') = :month \r\n"
					+ "                       AND To_char(deli_dt, 'yyyy') = :year \r\n"
					+ "                       AND cus_site = :cusite \r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) AS\r\n"
					+ "               customs_duty_month,\r\n"
					+ "               (SELECT COALESCE(Sum(tot_amt_payable), 0)\r\n"
					+ "                FROM fpo_main c\r\n"
					+ "                       join fpo_delivery fd\r\n"
					+ "                         ON ( c.item_id = fd.item_id )\r\n"
					+ "                WHERE  Trunc(fd.deli_dt) BETWEEN :financialdateandyear AND :enddate \r\n"
					+ "                       AND cus_site = :cusite \r\n"
					+ "                       AND 0 < (SELECT Count(*)\r\n"
					+ "                                FROM fpo_qry_deci fq\r\n"
					+ "                                WHERE  fq.item_id = c.item_id\r\n"
					+ "                                       AND fq.qry_type = 'P5')) AS\r\n"
					+ "                      customs_duty_upto_month\r\n"
					+ "        FROM   dual) ";
			Query query = entityManager.createNativeQuery(queryStr, DetainedParcelClearence.class);
			query.setParameter("month", month);
			query.setParameter("year",year);
			query.setParameter("cusite",cusite);
			query.setParameter("financialdateandyear",financialdateandyear);
			query.setParameter("enddate",enddate);
			query.setParameter("startdate",startdate);
			result = (List<DetainedParcelClearence>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<DeliveryArticlesPendingColumns> getDeliveryArticlesPendingColumns(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cusite = request.getSession().getAttribute("cuSite").toString();
		List<DeliveryArticlesPendingColumns> deliveryarticles=new ArrayList<DeliveryArticlesPendingColumns>();
		try {
			String queryStr="select fs.id,fs.item_id,to_char(fs.ooc_dt , 'dd/mm/yyyy')as ooc_dt,CASE when (select count(*) FROM fpo_qry_deci fd where fd.item_id=fm.item_id and fd.qry_type='P6')>0 then 'Commercial' else 'Personal' end as status FROM fpo_main fm  join fpo_status fs on (fm.item_id=fs.item_id) join fpo_qry_deci fqd on  (fs.item_id=fqd.item_id)  where fm.cus_site=:cusite  and trunc(fs.OOC_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and fqd.qry_type='P8' order by fs.ooc_dt desc";
			//String queryStr="select id,item_id,DELI_PO,to_char(DELI_DT , 'dd/mm/yyyy') as DELI_DT,status_desc as DELI_STATUS,dc.mode_desc as DELI_MODE FROM fpo_delivery fd left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code) where trunc(DELI_DT) between to_date ('"+fromdate+"', 'dd/mm/yyyy') AND TO_DATE ('"+todate+"', 'dd/mm/yyyy') order by DELI_DT desc"; between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and a.cus_site=:cusite
			Query query = entityManager.createNativeQuery(queryStr, DeliveryArticlesPendingColumns.class);
			query.setParameter("cusite", cusite);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			deliveryarticles = (List<DeliveryArticlesPendingColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deliveryarticles;
	}

	public List<OpeningReportColumns> getReportColumnsForOpening(String startdate,String financialdateandyear, String enddate, String month,
			String year, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cusite = request.getSession().getAttribute("cuSite").toString();
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  trunc(c.job_dt) < To_date (:startdate, 'dd/mm/yyyy') and c.cus_site=:cusite and 0=(select count(*) FROM fpo_qry_deci f where qry_type='P8' and f.item_id=c.item_id) order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("cusite", cusite);
			query.setParameter("startdate", startdate);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getReceiptForMonth(String startdate,String financialdateandyear, String enddate, String month,
			String year, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		/*
		 * Long Mnth=(Long.parseLong(month)-1l); month=new
		 * DecimalFormat("00").format(Double.valueOf(Mnth.toString()));
		 */
		String cusite = request.getSession().getAttribute("cuSite").toString();
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  To_char(c.job_dt, 'mm') =:month AND To_char(c.job_dt, 'yyyy') =:year and c.cus_site=:cusite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("cusite", cusite);
			query.setParameter("month", month);
			query.setParameter("year", year);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ItemDetails> getItemDetails(String itemid) {
		// TODO Auto-generated method stub
		List<ItemDetails> result=new ArrayList<ItemDetails>();
		try {
			String queryStr="select a.item_no,a.item_desc,decode(a.gen_cth,nvl(b.cth,a.gen_cth),a.gen_cth,b.cth) cth,a.nou,a.assess_val,a.duty,decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.bcd_amt,0),0) bcd,decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.igst_amt,0),0) igst,decode(a.gen_cth,nvl(b.cth,a.gen_cth),nvl(a.sw_amt,0),0) sws,to_char(nvl(a.duty_amt_9804,0)+nvl(b.duty_amt_oth,0),'fm99999999990.90') other_duty from \r\n"
					+ "(select a.item_no,a.item_desc,a.gen_cth,a.nou,a.assess_val,a.duty,a.bcd_amt,a.igst_amt,a.sw_amt,nvl(b.duty_amt,0) as duty_amt_9804 FROM fpo_item_det  a left join  \r\n"
					+ "(select cin_no,sum(duty_amt) duty_amt,item_no FROM fpo_item_det_othduty where substr(cth,1,4)='9804'  group by cin_no,item_no order by cin_no,item_no) b on   a.cin_no=b.cin_no \r\n"
					+ "where a.item_id=:itemid) a,\r\n"
					+ "(select a.item_no,a.item_desc,b.cth,a.nou,a.assess_val,a.duty,nvl(b.duty_amt,0) as duty_amt_oth FROM fpo_item_det  a left join  \r\n"
					+ "(select cin_no,sum(duty_amt) duty_amt,item_no,cth  FROM fpo_item_det_othduty where substr(cth,1,4)!='9804'  group by cin_no,item_no,cth order by cin_no,item_no) b on  a.cin_no=b.cin_no \r\n"
					+ "where a.item_id=:itemid ) b \r\n"
					+ "where a.item_no=b.item_no \r\n"
					+ "order by 1";
			Query query = entityManager.createNativeQuery(queryStr, ItemDetails.class);
			query.setParameter("itemid", itemid);
			result = (List<ItemDetails>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ItemAmountDetails getItemAmountDetails(String itemid) {
		// TODO Auto-generated method stub
		List<ItemAmountDetails> result=new ArrayList<ItemAmountDetails>();
		try {
			String queryStr="select nvl(dc.status_desc,'Delivery Acknowledgement Pending') delivery_status,m.job_no id,m.item_id,m.recp_name,m.recp_add1||','|| m.recp_add2||','|| m.recp_city recp_addrs,m.gross_wt,m.tot_amt_payable,COALESCE(ff.fine_amt,0) fine,COALESCE(fp.penal_amt,0) penalty,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category FROM fpo_main m left join (SELECT Sum(fine_amt) AS fine_amt,item_id FROM fpo_fine GROUP  BY item_id) ff on (m.item_id=ff.item_id) left join (select sum(penal_amt) as penal_amt,item_id FROM fpo_penal group by item_id) fp on (fp.item_id=m.item_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=m.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (m.MAIL_CLASS_CD=pdm.code) left join fpo_delivery fd on (m.item_id=fd.item_id)  left join deli_status_codes dc on (dc.status_code=deli_status) where m.item_id=:itemid";
			Query query = entityManager.createNativeQuery(queryStr, ItemAmountDetails.class);
			query.setParameter("itemid", itemid);
			result = (List<ItemAmountDetails>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result.get(0);
	}

	public List<String> getCountryOfOrigins() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct CNTRY_NM from OPS$DIR.d_cntry_cd where CNTRY_NM is not null and CNTRY_NM<>' ' order by CNTRY_NM";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getStates() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct recp_state FROM fpo_main where recp_state is not null and recp_state<>' '";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getPincodes() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct state_name from OPS$DIR.pdi_pincode_mapping_india where state_name is not null and state_name <> ' ' order by state_name";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getEADDecisions() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct deci_nm from deci_reas where deci_nm is not null and deci_nm <> ' ' order by deci_nm";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getMailClass() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct codedesc from ops$dir.pdi_mail_class_codes where codedesc is not null and codedesc <> ' ' order by codedesc";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getMailCat() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct category from ops$dir.pdi_nature_trans_codes where category is not null and category <> ' ' order by category";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<String>getFpositeCat(){
		List<String>result=new ArrayList<String>();
		try {
			String queryStr = "select distinct (SITE_CODE) from ops$dir.fpo_sites where site_active='Y'  and site_code <>'INNSA5' ";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
			System.out.println("required list of fpo sites"+result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getCTH() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct gen_cth FROM fpo_item_det where gen_cth is not null and gen_cth <> ' ' and SUBSTR(gen_cth, 1, 2)='98' order by gen_cth";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getItemCounts() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct count(*) as counts FROM fpo_item_det group by item_id order by counts";
			Query query = entityManager.createNativeQuery(queryStr);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ReportColumns> getWhereClauses(String country, String state, String pincode, String ead, String mailclass,
			String mailcategory, String totalduty, String amount, String fine, String penalty, String cth,
			String itemvalue, String itemno, String query, String reply, String arrival, String destination,
			String examination_order, String ooc, String ediqueue, String delivery, String yN, String doc, 
			ArrayList<String> selectedList, String pincode1, String detain, String weight, String examfind, String cn23, 
			String sendername, String recname, String itemdesc, String arrdt, String examdt, String deldt,String delistatus, String revcn23, String assvalue,
			String fromdate, String todate,  HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("sendername"+ sendername);
		String result="";
	
		try {
			
			if(selectedList.contains("4") && !sendername.equalsIgnoreCase("") && !sendername.equalsIgnoreCase("All") && !sendername.equalsIgnoreCase("null")) {
				result=result.concat(" and lower(m.send_name) LIKE :sendername");
				//result=result.concat(" and lower(m.send_name) LIKE '%'+ sendername.toLowerCase()+'%'");
//				result=result.concat(" and lower(m.send_name) LIKE '%"+sendername.toLowerCase()+"%'");
			}
			System.out.println("sendername"+ sendername);
			System.out.println("sendername"+ itemdesc);
			if(selectedList.contains("6") && !recname.equalsIgnoreCase("") && !recname.equalsIgnoreCase("All") && !recname.equalsIgnoreCase("null")) {
				//result=result.concat(" and lower(m.recp_name) LIKE '%:recname%'");
				result=result.concat(" and lower(m.recp_name) LIKE :recname");
			}
			
			if(selectedList.contains("15") && !itemdesc.equalsIgnoreCase("") && !itemdesc.equalsIgnoreCase("All") && !itemdesc.equalsIgnoreCase("null")) {
				result=result.concat(" and lower(fid.item_desc) LIKE :itemdesc");
			}
			
			if(selectedList.contains("3") && !country.equalsIgnoreCase("All") && !country.equalsIgnoreCase("null")) {
				result=result.concat(" and dtc.CNTRY_NM=:country");
			}
			
			if(selectedList.contains("8") && !state.equalsIgnoreCase("All") && !state.equalsIgnoreCase("null")) {
				result=result.concat(" and m.recp_state=:state");
			}
			
			if(selectedList.contains("9") &&  !pincode.equalsIgnoreCase("All")&&  !pincode1.equalsIgnoreCase("All") && !pincode.equalsIgnoreCase("null")) {
				result=result.concat(" and m.recp_zip between :pincode1");
			}
			
			if(selectedList.contains("10") && !ead.equalsIgnoreCase("All") && !ead.equalsIgnoreCase("null")) {
				result=result.concat(" and dr.deci_nm=:ead");
			}
			
			if(selectedList.contains("11") && !mailclass.equalsIgnoreCase("All") && !mailclass.equalsIgnoreCase("null")) {
				result=result.concat(" and pdm.codedesc=:mailclass");
			}
			
			if(selectedList.contains("12") && !mailcategory.equalsIgnoreCase("All") && !mailcategory.equalsIgnoreCase("null")) {
				result=result.concat(" and pdi.category=:mailcategory");
			}
			
			if(selectedList.contains("18") && !cth.equalsIgnoreCase("All") && !cth.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.gen_cth=:cth");
			}
			
			if(selectedList.contains("16") && !cn23.equalsIgnoreCase("All") && !cn23.equalsIgnoreCase("null")) {
				result=result.concat(" and substr(fid.cth,1,4)=:cn23");
			}
			if(selectedList.contains("17") && !revcn23.equalsIgnoreCase("All") && !revcn23.equalsIgnoreCase("null")) {
				result=result.concat(" and substr(fid.ITEM_REVISEDDESC,1,4)=:cn23");
			}
			
			
			if(selectedList.contains("13") && weight.equalsIgnoreCase("Up To 500g") && !weight.equalsIgnoreCase("All") && !weight.equalsIgnoreCase("null")) {
				result=result.concat(" and m.GROSS_WT <= 0.5");
			}else if(selectedList.contains("13") && weight.equalsIgnoreCase("Up To 1kg") && !weight.equalsIgnoreCase("All") && !weight.equalsIgnoreCase("null")) {
				result=result.concat(" and m.GROSS_WT <= 1");
			}else if(selectedList.contains("13") && weight.equalsIgnoreCase("Up To 1.5kg") && !weight.equalsIgnoreCase("All") && !weight.equalsIgnoreCase("null")) {
				result=result.concat(" and m.GROSS_WT <= 1.5");
			}else if(selectedList.contains("13") && weight.equalsIgnoreCase("Up To 2kg") && !weight.equalsIgnoreCase("All") && !weight.equalsIgnoreCase("null")) {
				result=result.concat(" and m.GROSS_WT <=  2");
			}else if(selectedList.contains("13") && weight.equalsIgnoreCase("Up To 2kg") && !weight.equalsIgnoreCase("All") && !weight.equalsIgnoreCase("null")) {
				result=result.concat(" and m.GROSS_WT > 2");
			}
			
			if(selectedList.contains("14") && itemno.equalsIgnoreCase("Up To 10") && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) {
				result=result.concat(" and (select count(*) FROM fpo_item_det where item_id=m.item_id)<10");
			}else if(selectedList.contains("14") && itemno.equalsIgnoreCase("More Than 10 Up To 20") && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) {
				result=result.concat(" and (select count(*) FROM fpo_item_det where item_id=m.item_id) between 11 and 20");
			}else if(selectedList.contains("14") && itemno.equalsIgnoreCase("More Than 20 Up To 50") && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) {
				result=result.concat(" and (select count(*) FROM fpo_item_det where item_id=m.item_id) between 21 and 50");
			}else if(selectedList.contains("14") && itemno.equalsIgnoreCase("More Than 50") && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) {
				result=result.concat(" and (select count(*) FROM fpo_item_det where item_id=m.item_id)>50");
			}else if(selectedList.contains("14") && ( itemno.equalsIgnoreCase("1") || itemno.equalsIgnoreCase("2") || itemno.equalsIgnoreCase("3") || itemno.equalsIgnoreCase("4") || itemno.equalsIgnoreCase("5") )  && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) {
				result=result.concat(" and (select count(*) FROM fpo_item_det where item_id=m.item_id)=:itemno");
			}
			
			if(selectedList.contains("31") && totalduty.equalsIgnoreCase("More Than 0 Up To 1000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty between 1 and 1000");
			}else if(selectedList.contains("31") && totalduty.equalsIgnoreCase("More Than 1000 Up To 2000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty between 1001 and 2000");
			}else if(selectedList.contains("31") && totalduty.equalsIgnoreCase("More Than 2000 Up To 3000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty between 2001 and 3000");
			}else if(selectedList.contains("31") && totalduty.equalsIgnoreCase("More Than 3000 Up To 4000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty between 3001 and 4000");
			}else if(selectedList.contains("31") && totalduty.equalsIgnoreCase("More Than 4000 Up To 5000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty between 4001 and 5000");
			}else if(selectedList.contains("31") && totalduty.equalsIgnoreCase("Above 5000") && !totalduty.equalsIgnoreCase("All") && !totalduty.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_duty > 5000");
			}
			
			
			
			if(selectedList.contains("32") && amount.equalsIgnoreCase("More Than 0 Up To 1000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable between 1 and 1000");
			}else if(selectedList.contains("32") && amount.equalsIgnoreCase("More Than 1000 Up To 2000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable between 1001 and 2000");
			}else if(selectedList.contains("32") && amount.equalsIgnoreCase("More Than 2000 Up To 3000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable between 2001 and 3000");
			}else if(selectedList.contains("32") && amount.equalsIgnoreCase("More Than 3000 Up To 4000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable between 3001 and 4000");
			}else if(selectedList.contains("32") && amount.equalsIgnoreCase("More Than 4000 Up To 5000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable between 4001 and 5000");
			}else if(selectedList.contains("32") && amount.equalsIgnoreCase("Above 5000") && !amount.equalsIgnoreCase("All") && !amount.equalsIgnoreCase("null")) {
				result=result.concat(" and m.tot_amt_payable > 5000");
			}
			
			
			if(selectedList.contains("33") && fine.equalsIgnoreCase("More Than 0 Up To 1000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) between 1 and 1000");
			}else if(selectedList.contains("33") && fine.equalsIgnoreCase("More Than 1000 Up To 2000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) between 1001 and 2000");
			}else if(selectedList.contains("33") && fine.equalsIgnoreCase("More Than 2000 Up To 3000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) between 2001 and 3000");
			}else if(selectedList.contains("33") && fine.equalsIgnoreCase("More Than 3000 Up To 4000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) between 3001 and 4000");
			}else if(selectedList.contains("33") && fine.equalsIgnoreCase("More Than 4000 Up To 5000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) between 4001 and 5000");
			}else if(selectedList.contains("33") && fine.equalsIgnoreCase("Above 5000") && !fine.equalsIgnoreCase("All") && !fine.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(fine_amt) FROM fpo_fine item_id = m._item_id) > 5000");
			}
			
			

			
			
			if(selectedList.contains("34") && penalty.equalsIgnoreCase("More Than 0 Up To 1000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) between 1 and 1000");
			}else if(selectedList.contains("34") && penalty.equalsIgnoreCase("More Than 1000 Up To 2000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) between 1001 and 2000");
			}else if(selectedList.contains("34") && penalty.equalsIgnoreCase("More Than 2000 Up To 3000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) between 2001 and 3000");
			}else if(selectedList.contains("34") && penalty.equalsIgnoreCase("More Than 3000 Up To 4000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) between 3001 and 4000");
			}else if(selectedList.contains("34") && penalty.equalsIgnoreCase("More Than 4000 Up To 5000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) between 4001 and 5000");
			}else if(selectedList.contains("34") && penalty.equalsIgnoreCase("Above 5000") && !penalty.equalsIgnoreCase("All") && !penalty.equalsIgnoreCase("null")) {
				result=result.concat(" and (SELECT Sum(penal_amt) FROM fpo_penal item_id = m._item_id) > 5000");
			}
			
			
			if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("More Than 0 Up To 1000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL between 1 and 1000");
			}else if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("More Than 1000 Up To 2000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL between 1001 and 2000");
			}else if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("More Than 2000 Up To 3000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL between 2001 and 3000");
			}else if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("More Than 3000 Up To 4000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL between 3001 and 4000");
			}else if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("More Than 4000 Up To 5000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL between 4001 and 5000");
			}else if(selectedList.contains("19") && itemvalue.equalsIgnoreCase("Above 5000") && !itemvalue.equalsIgnoreCase("All") && !itemvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fad.DECL_VAL > 5000");
			}
			
			

			
			
			if(selectedList.contains("22") && assvalue.equalsIgnoreCase("More Than 0 Up To 1000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val between 1 and 1000");
			}else if(selectedList.contains("22") && assvalue.equalsIgnoreCase("More Than 1000 Up To 2000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val between 1001 and 2000");
			}else if(selectedList.contains("22") && assvalue.equalsIgnoreCase("More Than 2000 Up To 3000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val between 2001 and 3000");
			}else if(selectedList.contains("22") && assvalue.equalsIgnoreCase("More Than 3000 Up To 4000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val between 3001 and 4000");
			}else if(selectedList.contains("22") && assvalue.equalsIgnoreCase("More Than 4000 Up To 5000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val between 4001 and 5000");
			}else if(selectedList.contains("22") && assvalue.equalsIgnoreCase("Above 5000") && !assvalue.equalsIgnoreCase("All") && !assvalue.equalsIgnoreCase("null")) {
				result=result.concat(" and fid.assess_val > 5000");
			}
			
			if(selectedList.contains("23") && query.equalsIgnoreCase("yes") && !query.equalsIgnoreCase("All") && !query.equalsIgnoreCase("null")) {
				result=result.concat(" and (dg.DCALL_NO is not null and dg.DCALL_NO <> ' ')");
			}else if(selectedList.contains("23") && query.equalsIgnoreCase("no") && !query.equalsIgnoreCase("All") && !query.equalsIgnoreCase("null")) {
				result=result.concat(" and dg.DCALL_NO is null");
			}
			


			
			if(selectedList.contains("28") && reply.equalsIgnoreCase("E - Reply")  && !reply.equalsIgnoreCase("All") && !reply.equalsIgnoreCase("null")) {
				result=result.concat(" and (fq.RPLY is not null and RPLY_OFF_ID is null)");
			}else if(selectedList.contains("28") && reply.equalsIgnoreCase("Physical Reply")  && !reply.equalsIgnoreCase("All") && !reply.equalsIgnoreCase("null")) {
				result=result.concat(" and (fq.RPLY is not null and RPLY_OFF_ID is not null)");
			}else if(selectedList.contains("28") && reply.equalsIgnoreCase("Reply Pending")  && !reply.equalsIgnoreCase("All") && !reply.equalsIgnoreCase("null")) {
				result=result.concat(" and (fq.RPLY is null and RPLY_OFF_ID is null)");
			}
			
			
			if(selectedList.contains("46") && detain.equalsIgnoreCase("yes") && !detain.equalsIgnoreCase("All") && !detain.equalsIgnoreCase("null")) {
				result=result.concat(" and fqd2.qry_dt is not null");
			}else if(selectedList.contains("46") && detain.equalsIgnoreCase("no") && !detain.equalsIgnoreCase("All") && !detain.equalsIgnoreCase("null")) {
				result=result.concat(" and fqd2.qry_dt is null");
			}
			
			
			if(selectedList.contains("35") && arrival.equalsIgnoreCase("yes") && !arrival.equalsIgnoreCase("All") && !arrival.equalsIgnoreCase("null")) {
				result=result.concat(" and aai.recd_event_dt is not null");
			}else if(selectedList.contains("35") && arrival.equalsIgnoreCase("no") && !arrival.equalsIgnoreCase("All") && !arrival.equalsIgnoreCase("null")) {
				result=result.concat(" and aai.recd_event_dt is null");
			}
			
			
			/*if(selectedList.contains("35") && arrdt.equalsIgnoreCase("yes") && !arrdt.equalsIgnoreCase("All") && !arrdt.equalsIgnoreCase("null")) {
				result=result.concat(" and aai.recd_event_dt is not null");
			}else if(selectedList.contains("35") && arrdt.equalsIgnoreCase("no") && !arrdt.equalsIgnoreCase("All") && !arrdt.equalsIgnoreCase("null")) {
				result=result.concat(" and aai.recd_event_dt is null");
			}*/
			
			
			if(selectedList.contains("42") && examfind.equalsIgnoreCase("Item Found") && !examfind.equalsIgnoreCase("All") && !examfind.equalsIgnoreCase("null")) {
				result=result.concat(" and fef.item_fou=1");
			}else if(selectedList.contains("42") && examfind.equalsIgnoreCase("Item Not Found") && !examfind.equalsIgnoreCase("All") && !examfind.equalsIgnoreCase("null")) {
				result=result.concat(" and fef.item_fou=0");
			}
			
			
			
			if(selectedList.contains("41") && !examination_order.equalsIgnoreCase("All") && !examination_order.equalsIgnoreCase("null")) {
				result=result.concat(" and fo.EXAM_ORDER=:examination_order");
			}

			
			
			
			if(selectedList.contains("43") && examdt.equalsIgnoreCase("Yes")  && !examdt.equalsIgnoreCase("All") && !examdt.equalsIgnoreCase("null")) {
				result=result.concat(" and fo.ORDER_DATE is not null");
			}else if(selectedList.contains("43") && examdt.equalsIgnoreCase("NO")  && !examdt.equalsIgnoreCase("All") && !examdt.equalsIgnoreCase("null")) {
				result=result.concat(" and fo.ORDER_DATE is null");
			}
			
			
			
			if(selectedList.contains("44") && ooc.equalsIgnoreCase("Given") && !ooc.equalsIgnoreCase("All") && !ooc.equalsIgnoreCase("null")) {
				result=result.concat(" and fs.ooc_dt is not null");
			}else if(selectedList.contains("44") && ooc.equalsIgnoreCase("Pending") && !ooc.equalsIgnoreCase("All") && !ooc.equalsIgnoreCase("null")) {
				result=result.concat(" and fs.ooc_dt is null");
			}
			
			
			if(selectedList.contains("45") && ediqueue.equalsIgnoreCase("yes") && !ediqueue.equalsIgnoreCase("All") && !ediqueue.equalsIgnoreCase("null")) {
				result=result.concat(" and fqd1.qry_dt is not null");
			}else if(selectedList.contains("45") && ediqueue.equalsIgnoreCase("no") && !ediqueue.equalsIgnoreCase("All") && !ediqueue.equalsIgnoreCase("null")) {
				result=result.concat(" and fqd1.qry_dt is null");
			}
			
			
			if(selectedList.contains("48") && delivery.equalsIgnoreCase("Delivered") && !delivery.equalsIgnoreCase("All") && !delivery.equalsIgnoreCase("null")) {
				if(!delistatus.equalsIgnoreCase("All")) {
					result=result.concat(" and dm.MODE_DESC=:delistatus");
				}else {
					result=result.concat(" and dc.status_desc='DELIVERED'");
				}				
			}else if(selectedList.contains("48") && delivery.equalsIgnoreCase("Not Delivered") && !delivery.equalsIgnoreCase("All") && !delivery.equalsIgnoreCase("null")) {
				if(!delistatus.equalsIgnoreCase("All")) {
					result=result.concat(" and dc.status_desc=:delistatus");
				}else {
					result=result.concat(" and dc.status_desc!='DELIVERED'");
				}
			}else if(selectedList.contains("48") && delivery.equalsIgnoreCase("Delivery Acknowledgement Pending") && !delivery.equalsIgnoreCase("All") && !delivery.equalsIgnoreCase("null")) {
				result=result.concat(" and dc.status_desc is null");
			}
			

			
			
			if(selectedList.contains("47") && deldt.equalsIgnoreCase("yes") && !deldt.equalsIgnoreCase("All") && !deldt.equalsIgnoreCase("null")) {
				result=result.concat(" and fd.DELI_DT is not null");
			}else if(selectedList.contains("47") && delivery.equalsIgnoreCase("no") && !deldt.equalsIgnoreCase("All") && !!deldt.equalsIgnoreCase("null")) {
				result=result.concat(" and fd.DELI_DT is null");
			}
			
			result=result.concat(" order by id");	
			
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="select  m.cus_site as cusite,extract(day from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Days - ' || extract(hour from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Hours - ' || extract(minute from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Minutes - ' || extract(second from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss')))) || ' Seconds' rlytime,dg.DCALL_NO dcallno,dg.GEN_DT dcall_dt,'' speedpostno,'' speedpostdelstatus,'' curr_cd,'' ex_rate,'' revised_code,'' as assessedval,'' rms,fd.DELI_DT del_dt,fo.ORDER_DATE exam_dt, to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy') arr_dt,'' examfind,'' cn23, ROW_NUMBER() OVER(order by m.job_no DESC) as id,m.GROSS_WT,m.job_no,m.item_id,to_char(to_date(substr(m.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as coo,m.send_name as sender_name,m.send_add1 as sender_addr,m.recp_name,m.recp_add1 as rec_addr,m.recp_state,m.recp_zip,dr.deci_nm,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,TOT_DUTY as duty,TOT_AMT_PAYABLE,ff.fine_amt,fp.penal_amt,'' as cth,'' as item_desc,'' as assess_val,'' as item_vlue,'' as ITEM_NO,QRY,RPLY,DG.IP_ADDRESS as replyip,fo.EXAM_ORDER || ' / ' || fo.order_remark examination,to_char(fs.ooc_dt , 'dd/mm/yyyy') ooc_dt,to_char(fqd1.QRY_DT , 'dd/mm/yyyy') QRY_DT,aai.ooe  arriaval_date,aai.recp_id,afi.RECD_FPO as destfpo,afi.RECD_DT destfpo_dt,afi.bag_no,'' respedi,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_date,case when dc.status_desc='DELIVERED' then dm.MODE_DESC else dc.status_desc end as  deliverystatus,to_char(fqd2.QRY_DT , 'dd/mm/yyyy') detain_dt FROM fpo_main m  left join OPS$DIR.d_cntry_cd dtc on (m.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='E4' group by cin_no)) fqd on (m.item_id=fqd.item_id) left join deci_reas dr on (dr.deci_cd=fqd.DECI_CD)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=m.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (m.MAIL_CLASS_CD=pdm.code) left join (SELECT Sum(fine_amt) AS fine_amt,item_id FROM fpo_fine GROUP  BY item_id) ff on (m.item_id=ff.item_id) left join (select sum(penal_amt) as penal_amt,item_id FROM fpo_penal group by item_id) fp on (fp.item_id=m.item_id)  left join (select * FROM fpo_query where  id in (select max(id) FROM fpo_query where qry is not null group by cin_no)) fq on (fq.item_id=m.item_id) left join (select * FROM fpo_order where  id in (select max(id) FROM fpo_order group by cin_no)) fo on (m.cin_no=fo.cin_no) left join (select * FROM fpo_status where  cusitm_dt is not null) fs on (m.item_id=fs.item_id) left join fpo_delivery fd on (m.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dm on (dm.mode_code=DELI_MODE) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P6' group by cin_no)) fqd1 on (m.item_id=fqd1.item_id) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=m.item_id) left join (select * from articlearr_fpo_info where status = 'd') afi on (afi.ARTICLE_ID=m.item_id) LEFT JOIN (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P5' group by cin_no)) fqd2  ON ( m.item_id = fqd2.item_id ) LEFT JOIN DCALLQRY_GEN DG ON (M.ITEM_ID=DG.ITEM_ID)";
			if (cuSite.equals("INNSA5"))
				 queryStr= queryStr + " where trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') "+result;
			else 
			queryStr= queryStr + " where  m.cus_site=:cuSite and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') "+result;
			
			if(selectedList.contains("41") ||selectedList.contains("14") ||selectedList.contains("15") ||selectedList.contains("16") ||selectedList.contains("17") ||selectedList.contains("18") ||selectedList.contains("19") ||selectedList.contains("20") ||selectedList.contains("21") ||selectedList.contains("22")) {			
				queryStr="select  m.cus_site as cusite,extract(day from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Days - ' || extract(hour from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Hours - ' || extract(minute from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))))  || ' Minutes - ' || extract(second from ((to_timestamp(to_char(RPLY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss'))-(to_timestamp(to_char(QRY_DATE, 'dd-mm-yyyy hh24:mi:ss'), 'dd-mm-yyyy hh24:mi:ss')))) || ' Seconds' rlytime,dg.DCALL_NO dcallno,dg.GEN_DT dcall_dt,'' speedpostno,'' speedpostdelstatus,fad.CURRCD curr_cd,fad.CURR_RATE ex_rate,fid.ITEM_REVISEDDESC revised_code,fid.assess_val as assessedval,'' rms,fd.DELI_DT del_dt,fo.ORDER_DATE exam_dt, to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy') arr_dt, case when fef.item_fou=1 then 'Item Found / ' || fef.remarks when fef.item_fou=0 then 'Item Not Found / ' || fef.remarks else '' end as examfind,fid.cth cn23, ROW_NUMBER() OVER(order by m.job_no DESC, fid.item_no ASC) as id,m.GROSS_WT,m.job_no,m.item_id,to_char(to_date(substr(m.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as coo,m.send_name as sender_name,m.send_add1 as sender_addr,m.recp_name,m.recp_add1 as rec_addr,m.recp_state,m.recp_zip,dr.deci_nm,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,TOT_DUTY as duty,TOT_AMT_PAYABLE,ff.fine_amt,fp.penal_amt,fid.gen_cth as cth,fid.item_desc,fad.DECL_VAL as item_vlue,fid.ITEM_NO,QRY,RPLY,DG.IP_ADDRESS as replyip,fo.EXAM_ORDER || ' / ' || fo.order_remark examination,to_char(fs.ooc_dt , 'dd/mm/yyyy') ooc_dt,to_char(fqd1.QRY_DT , 'dd/mm/yyyy') QRY_DT,aai.ooe  arriaval_date,aai.recp_id,afi.RECD_FPO as destfpo,afi.RECD_DT destfpo_dt,afi.bag_no,'' respedi,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as       delivery_date,case when dc.status_desc='DELIVERED' then dm.MODE_DESC else dc.status_desc end as       deliverystatus,to_char(fqd2.QRY_DT , 'dd/mm/yyyy') detain_dt FROM fpo_main m  left join OPS$DIR.d_cntry_cd dtc on (m.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='E4' group by cin_no)) fqd on (m.item_id=fqd.item_id) left join deci_reas dr on (dr.deci_cd=fqd.DECI_CD)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=m.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (m.MAIL_CLASS_CD=pdm.code) left join (SELECT Sum(fine_amt) AS fine_amt,item_id FROM fpo_fine GROUP  BY item_id) ff on (m.item_id=ff.item_id) left join (select sum(penal_amt) as penal_amt,item_id FROM fpo_penal group by item_id) fp on (fp.item_id=m.item_id) left join (select * FROM fpo_item_det order by JOB_NO,item_no) fid on (m.item_id=fid.item_id) left join (select * FROM fpo_amend_item_det where AMEND_SERIAL_NO=1 order by item_no) fad on (m.item_id=fad.item_id and fid.item_no=fad.item_no) left join fpo_exam_findings fef on (fid.cin_no=fef.cin_no and fid.item_no=fef.item_no and fef.item_no is not null) left join (select * FROM fpo_query where  id in (select max(id) FROM fpo_query where qry is not null group by cin_no)) fq on (fq.item_id=m.item_id) left join (select * FROM fpo_order where  id in (select max(id) FROM fpo_order group by cin_no)) fo on (m.cin_no=fo.cin_no) left join (select * FROM fpo_status where  cusitm_dt is not null) fs on (m.item_id=fs.item_id) left join fpo_delivery fd on (m.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dm on (dm.mode_code=DELI_MODE) left join (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P6' group by cin_no)) fqd1 on (m.item_id=fqd1.item_id) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=m.item_id) left join (select * from articlearr_fpo_info where status = 'd') afi on (afi.ARTICLE_ID=m.item_id) LEFT JOIN (select * FROM fpo_qry_deci where  id in (select max(id) FROM fpo_qry_deci where qry_type='P5' group by cin_no)) fqd2  ON ( m.item_id = fqd2.item_id ) LEFT JOIN DCALLQRY_GEN DG ON (M.ITEM_ID=DG.ITEM_ID)  where fid.ITEM_UNIQUE is not null";
				if (cuSite.equals("INNSA5"))
					 queryStr= queryStr + " and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') "+result;
				else
					 queryStr= queryStr + " and m.cus_site=:cuSite and trunc(m.job_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')"+result;				
			}
//			if(WhereClasuses.equalsIgnoreCase("") && (selectedList.contains("29") || selectedList.contains("14") || selectedList.contains("15") || selectedList.contains("15") || selectedList.contains("17") || selectedList.contains("14"))) {
//				queryStr=queryStr.concat("order by m.job_no DESC, fid.item_no ASC");
//			}else if(WhereClasuses.equalsIgnoreCase("")) {
//				queryStr=queryStr.concat("order by m.job_no DESC");
//			}
			if(result.equalsIgnoreCase("")) {
				queryStr=queryStr.concat(" order by id");
			}
			List<ReportColumns> reportColumns=new ArrayList<ReportColumns>();
			Query qry = entityManager.createNativeQuery(queryStr, ReportColumns.class);
			qry.setParameter("fromdate",fromdate);
			qry.setParameter("todate",todate);
			if(selectedList.contains("4") && !sendername.equalsIgnoreCase("") && !sendername.equalsIgnoreCase("All") && !sendername.equalsIgnoreCase("null"))
			    qry.setParameter("sendername","%"+ sendername.toLowerCase()+"%");
			if(selectedList.contains("6") && !recname.equalsIgnoreCase("") && !recname.equalsIgnoreCase("All") && !recname.equalsIgnoreCase("null"))
				qry.setParameter("recname","%"+recname.toLowerCase()+"%");
			if(selectedList.contains("15") && !itemdesc.equalsIgnoreCase("") && !itemdesc.equalsIgnoreCase("All") && !itemdesc.equalsIgnoreCase("null"))
				qry.setParameter("itemdesc","%"+itemdesc.toLowerCase()+"%");
			if(selectedList.contains("3") && !country.equalsIgnoreCase("All") && !country.equalsIgnoreCase("null")) 
				qry.setParameter("country",country);
			if(selectedList.contains("8") && !state.equalsIgnoreCase("All") && !state.equalsIgnoreCase("null"))
				qry.setParameter("state",state);
			if(selectedList.contains("9") &&  !pincode.equalsIgnoreCase("All")&&  !pincode1.equalsIgnoreCase("All") && !pincode.equalsIgnoreCase("null"))
				qry.setParameter("pincode1",pincode1);
			if(selectedList.contains("10") && !ead.equalsIgnoreCase("All") && !ead.equalsIgnoreCase("null"))
				qry.setParameter("ead",ead);
			if(selectedList.contains("11") && !mailclass.equalsIgnoreCase("All") && !mailclass.equalsIgnoreCase("null"))
				qry.setParameter("mailclass",mailclass);
			if(selectedList.contains("12") && !mailcategory.equalsIgnoreCase("All") && !mailcategory.equalsIgnoreCase("null"))
				qry.setParameter("mailcategory",mailcategory);
			if(selectedList.contains("18") && !cth.equalsIgnoreCase("All") && !cth.equalsIgnoreCase("null"))
				qry.setParameter("cth",cth);
			if(selectedList.contains("16") && !cn23.equalsIgnoreCase("All") && !cn23.equalsIgnoreCase("null"))
				qry.setParameter("cn23",cn23);
			if(selectedList.contains("17") && !revcn23.equalsIgnoreCase("All") && !revcn23.equalsIgnoreCase("null"))
				qry.setParameter("cn23",cn23);
		    if(selectedList.contains("14") && ( itemno.equalsIgnoreCase("1") || itemno.equalsIgnoreCase("2") || itemno.equalsIgnoreCase("3") || itemno.equalsIgnoreCase("4") || itemno.equalsIgnoreCase("5") )  && !itemno.equalsIgnoreCase("All") && !itemno.equalsIgnoreCase("null")) 
		    	qry.setParameter("itemno",itemno);
		    if(selectedList.contains("41") && !examination_order.equalsIgnoreCase("All") && !examination_order.equalsIgnoreCase("null"))
		    	qry.setParameter("examination_order",examination_order);
		    if(selectedList.contains("48") && delivery.equalsIgnoreCase("Delivered") && !delivery.equalsIgnoreCase("All") && !delivery.equalsIgnoreCase("null"))	
		    	qry.setParameter("delistatus",delistatus);
		    if(selectedList.contains("48") && delivery.equalsIgnoreCase("Not Delivered") && !delivery.equalsIgnoreCase("All") && !delivery.equalsIgnoreCase("null"))	
		    	qry.setParameter("delistatus",delistatus);
			
			if (!(cuSite.equals("INNSA5")))				
			qry.setParameter("cuSite",cuSite);
			reportColumns = (List<ReportColumns>) qry.getResultList();
			return reportColumns;
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
//		if(selectedList.contains("29") || selectedList.contains("14") || selectedList.contains("15") || selectedList.contains("15") || selectedList.contains("17") || selectedList.contains("14")) {
//			result=result.concat(" order by m.job_no DESC, fid.item_no ASC");
//		}else {
//			result=result.concat(" order by m.job_no desc");
//		}
		
	return new ArrayList<ReportColumns>();
		
	}
	public List<SelectTag> getSelectTag(String state) {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select fpo_code as id,''''||IN_START_PINCODE || ''' and ''' || IN_END_PINCODE||'''' as value,'From ' || IN_START_PINCODE || ' Up To ' || IN_END_PINCODE as data from OPS$DIR.pdi_pincode_mapping_india where STATE_NAME =:state and IN_START_PINCODE is not null and IN_END_PINCODE is not null";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			query.setParameter("state", state);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<MonthNames> getMonthSelect(String month, int year) {
		// TODO Auto-generated method stub
		List<MonthNames> result=new LinkedList<MonthNames>();
		int curyear = Calendar.getInstance().get(Calendar.YEAR);
		try {
			
			if(year<curyear) {
				MonthNames names=new MonthNames();
				
				names.setMonthno("04");
				names.setMonthname("April");
				result.add(names);
				
				names=new MonthNames();
				names.setMonthno("05");
				names.setMonthname("May");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("06");
				names.setMonthname("June");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("07");
				names.setMonthname("July");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("08");
				names.setMonthname("August");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("09");
				names.setMonthname("September");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("10");
				names.setMonthname("October");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("11");
				names.setMonthname("November");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("12");
				names.setMonthname("December");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("01");
				names.setMonthname("January");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("02");
				names.setMonthname("February");
				result.add(names);

				
				names=new MonthNames();
				names.setMonthno("03");
				names.setMonthname("March");
				result.add(names);
			
			}else {
				
				Date date = new Date();
				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				DecimalFormat dmFormat= new DecimalFormat("00");
				 month=dmFormat.format(Double.valueOf(localDate.getMonthValue()));
				
				Long Mnth=(Long.parseLong(month)-1l); 
				 month=new DecimalFormat("00").format(Double.valueOf(Mnth.toString()));
			
			if(Integer.parseInt(month)>=04) {
				MonthNames names=new MonthNames();
				names.setMonthno("04");
				names.setMonthname("April");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=05) {
				MonthNames names=new MonthNames();
				names.setMonthno("05");
				names.setMonthname("May");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=06) {
				MonthNames names=new MonthNames();
				names.setMonthno("06");
				names.setMonthname("June");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=07) {
				MonthNames names=new MonthNames();
				names.setMonthno("07");
				names.setMonthname("July");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=8) {
				MonthNames names=new MonthNames();
				names.setMonthno("08");
				names.setMonthname("August");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=9) {
				MonthNames names=new MonthNames();
				names.setMonthno("09");
				names.setMonthname("September");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=10) {
				MonthNames names=new MonthNames();
				names.setMonthno("10");
				names.setMonthname("October");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=11) {
				MonthNames names=new MonthNames();
				names.setMonthno("11");
				names.setMonthname("November");
				result.add(names);
			}
			
			if(Integer.parseInt(month)>=12) {
				MonthNames names=new MonthNames();
				names.setMonthno("12");
				names.setMonthname("December");
				result.add(names);
			}
			
			
			if(curyear<year) {

				
				if(Integer.parseInt(month)>=01) {
					MonthNames names=new MonthNames();
					names.setMonthno("01");
					names.setMonthname("January");
					result.add(names);
				}
				
				if(Integer.parseInt(month)>=02) {
					MonthNames names=new MonthNames();
					names.setMonthno("02");
					names.setMonthname("February");
					result.add(names);
				}
				
				if(Integer.parseInt(month)>=03) {
					MonthNames names=new MonthNames();
					names.setMonthno("03");
					names.setMonthname("March");
					result.add(names);
				}
			}
			
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getOtherTwoDigi() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select DISTINCT (SUBSTR(CTH, 1, 2)) from ops$dir.dt_bcd where nvl(end_dt,trunc(sysdate))>=trunc(sysdate) and SUBSTR(CTH, 1, 2)!='98' order by SUBSTR(CTH, 1, 2) ASC";
			Query query = entityManager.createNativeQuery(queryStr);  
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getOtherFourDigi(String twodigit) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select DISTINCT (SUBSTR(CTH, 1, 4)) from ops$dir.dt_bcd where substr(CTH,1,2)=:twodigit and LENGTH(CTH) >=8 and nvl(end_dt,trunc(sysdate))>=trunc(sysdate) order by SUBSTR(CTH, 1, 4) ASC";
			Query query = entityManager.createNativeQuery(queryStr,String.class);  
			query.setParameter("twodigit", twodigit);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getReceiptuptoMonth(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
//			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  To_char(c.job_dt, 'mm') <= '"+month+"' AND Trunc(job_dt) >= To_date ('"+financialdateandyear+"', 'dd/mm/yyyy') AND Trunc(job_dt) <= To_date ('"+enddate+"', 'dd/mm/yyyy') and c.cus_site='"+request.getSession().getAttribute("cuSite")+"' order by c.job_no desc";
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  To_char(c.job_dt, 'mm') <= :month AND Trunc(job_dt) >= To_date (:financialdateandyear, 'dd/mm/yyyy') AND Trunc(job_dt) <= To_date (:enddate, 'dd/mm/yyyy') and c.cus_site=:cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("startdate",startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("month",month);
			query.setParameter("year",year);
			query.setParameter("financialdateandyear",financialdateandyear);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getReceiptWithoutOOCandExam(String startdate, String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate AND 0 = (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("startdate",startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getReceiptWithExam(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate AND 0 < (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') AND 0 = (SELECT Count(*) FROM fpo_item_det ft WHERE  ft.item_id = c.item_id AND ft.updass_dt IS NOT NULL) AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("startdate", startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getReceiptWithASSandExam(String startdate, String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate  AND 0 < (SELECT Count(*)  FROM fpo_qry_deci fq  WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') AND 0 < (SELECT Count(*) FROM fpo_item_det ft  WHERE  ft.item_id = c.item_id  AND ft.updass_dt IS NOT NULL) AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			result = (List<OpeningReportColumns>) query.getResultList();
			query.setParameter("startdate", startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getDetainedParcel(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate AND 0 < (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P5')  AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("startdate", startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getDisposalParcel(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fs.ooc_dt) BETWEEN :financialdateandyear AND :enddate AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("financialdateandyear",financialdateandyear);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getCustomDutyMonth(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_delivery fd ON ( c.item_id = fd.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  To_char(deli_dt, 'mm') = :month AND To_char(deli_dt, 'yyyy') = :year   AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setParameter("cuSite", cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getCustomDutyUptoMonth(String startdate, String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_delivery fd ON ( c.item_id = fd.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  Trunc(fd.deli_dt) BETWEEN :financialdateandyear AND :enddate AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("financialdateandyear",financialdateandyear);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getDisposalParcelMonth(String startdate, String financialdateandyear,
			String enddate, String month, String year, HttpSession session, HttpServletRequest request) {
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(c.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c join fpo_status fs ON ( c.item_id = fs.item_id ) left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE    To_char(fs.ooc_dt, 'mm') = :month AND Trunc(fs.ooc_dt) BETWEEN :startdate AND :enddate and (0 = (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') or ( 0 < (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') AND 0 = (SELECT Count(*) FROM fpo_item_det ft WHERE  ft.item_id = c.item_id AND ft.updass_dt IS NOT NULL)) or (0 < (SELECT Count(*)  FROM fpo_qry_deci fq  WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P4') AND 0 < (SELECT Count(*) FROM fpo_item_det ft  WHERE  ft.item_id = c.item_id  AND ft.updass_dt IS NOT NULL)) or 0 < (SELECT Count(*) FROM fpo_qry_deci fq WHERE  fq.item_id = c.item_id AND fq.qry_type = 'P5'))   AND c.cus_site = :cuSite order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("month", month);
			query.setParameter("startdate", startdate);
			query.setParameter("enddate",enddate);
			query.setParameter("cuSite",cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<OpeningReportColumns> getClosingBalance(String startdate, String financialdateandyear, String enddate,
			String month, String year, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<OpeningReportColumns> result=new ArrayList<OpeningReportColumns>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT  c.cin_no as id,c.item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,to_char(aa.recd_event_dt,'dd/mm/yyyy') as recd_event_dt ,c.CUST_ORG_CD as org_cd,pdm.CODEDESC as mail_class ,pdi.CATEGORY as mail_category,c.tot_ass_val as total_value,c.tot_duty as total_duty,(select count(*) FROM fpo_item_det dt where dt.CIN_NO=c.CIN_NO) as total_item FROM fpo_main c left join article_arr_info aa on (c.item_id=aa.article_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=c.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (c.MAIL_CLASS_CD=pdm.code)  WHERE  trunc(c.job_dt) < To_date (:startdate, 'dd/mm/yyyy') and c.cus_site=:cuSite and 0=(select count(*) FROM fpo_qry_deci f where qry_type='P8' and f.item_id=c.item_id) order by c.job_no desc";
			Query query = entityManager.createNativeQuery(queryStr, OpeningReportColumns.class);
			query.setParameter("startdate", startdate);
			query.setParameter("cuSite", cuSite);
			result = (List<OpeningReportColumns>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getNotDeliveryStatus(String deliveryackchange) {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select ROW_NUMBER() OVER(order by STATUS_DESC) as id,STATUS_DESC value,STATUS_CODE data from deli_status_codes where status_desc!='DELIVERED'";
			if(deliveryackchange.equalsIgnoreCase("Not Delivered")) {
				queryStr="select ROW_NUMBER() OVER(order by STATUS_DESC) as id,STATUS_DESC value,STATUS_CODE data from deli_status_codes where status_desc!='DELIVERED'";
			}else if(deliveryackchange.equalsIgnoreCase("Delivered")) {
				queryStr="select ROW_NUMBER() OVER(order by MODE_DESC) as id,MODE_DESC value,MODE_CODE data from deli_mode_codes";
			}
			
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<Fpo_Item_Query> getItemquery(String cinNo) {
		// TODO Auto-generated method stub
		List<Fpo_Item_Query> result=new ArrayList<Fpo_Item_Query>();
		try {
			String queryStr="SELECT fid.item_no id,fid.item_id,fid.item_desc,fid.item_no,fid.cin_no,fid.gen_cth,fid.decl_val,fid.assess_val,fq.qry FROM fpo_ITEM_DET fid left join (select * FROM fpo_query where id in (select max(id) FROM fpo_query fq1 where fq1.CIN_NO = :cinNo and id is not null group by item_no)) fq on (fid.cin_no=fq.cin_no and fid.item_no=fq.item_no)  where fid.CIN_NO = :cinNo ORDER BY fid.ITEM_NO";			
			Query query = entityManager.createNativeQuery(queryStr,Fpo_Item_Query.class);
			query.setParameter("cinNo", cinNo);
			result = (List<Fpo_Item_Query>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Filepath getFilePaths() {
		// TODO Auto-generated method stub
		List<Filepath> result=new ArrayList<Filepath>();
		try {
			
			String queryStr="select * from (SELECT 1 as id,dcall_qry,dcall_qry_rply,images_path,ooc_info,dcall_qry_view,examination_queue,detained_queue,ooc_cancel,oth_doc FROM   ospath_fpo ORDER BY DCALL_QRY) where ROWNUM=1";			
			Query query = entityManager.createNativeQuery(queryStr,Filepath.class);
			result = (List<Filepath>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result.get(0);
	}

	public String getFileName(String item_ID) {
		// TODO Auto-generated method stub
		String result="";
		try {
			String queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID)";
			
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID", item_ID);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}

	public String getFileNameForRly(String item_ID,String stage,HttpSession session, HttpServletRequest request) {
	// TODO Auto-generated method stub
	String result="";
	String queryStr="";
	try {
        stage=stage+"%";
     // --------------------------------------------------------- edited query BY VEEMAN on 18/05/2023 -------------------------------------------- 
		// queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID and (QRY_REPLY = 'Y' or QRY_REPLY ='U') and dcall_no like :stage)";			
    	if (!(request.getSession().getAttribute("role").toString().equals("PAC"))) {
    		queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID and (QRY_REPLY = 'Y' or QRY_REPLY ='U') and dcall_no like :stage)";			
    	} else {
    		queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID  and dcall_no like :stage)";
    	}
        
        Query query = entityManager.createNativeQuery(queryStr);
		query.setParameter("item_ID",item_ID);
		query.setParameter("stage", stage);
		  List<Object> resultList =query.getResultList();
		  if(resultList.size()>0) { 
			  result = (String)resultList.get(0);
			  }

		  if(result==null) {
			  result = "";  
		  }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		 result="";
	}
	return result;
}
	/* --------------------------------------------------------- added BY VEEMAN on 22/04/2023 -------------------------------------------- 
	
	public String getStage(String item_ID) {
		// TODO Auto-generated method stub
		String result="";
		try {
			String queryStr="select substr(DCALL_NO, 1,3) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID)";			
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID",item_ID);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}
	
	// --------------------------------------------------------- ---------------------- -------------------------------------------- */

// added by veemam,santhosh,kanish 18-05-2023
	
	
	public String getStage(String item_ID) {
		// TODO Auto-generated method stub
		String result="";
		try {
			String queryStr="select substr(DCALL_NO, 1,3) filename from dcallqry_gen where id = (select min(id) from dcallqry_gen where item_id=:item_ID)";			
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID",item_ID);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}

	
	
	public String getFileNameForWithoutRly(String item_ID,String stage,String range, String cuSite) {
		// TODO Auto-generated method stub
		String result="";
		
		try {
			stage=stage+"%";
			String queryStr="";
			if (range.equals("min"))
				queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select min(id) from dcallqry_gen where item_id=:item_ID  and cus_site=:cuSite and dcall_no like :stage)";
			else
				queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID  and cus_site=:cuSite and dcall_no like :stage)";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID",item_ID);
			query.setParameter("stage",stage);
			query.setParameter("cuSite",cuSite);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}

//	public List<DcallHistory> getDcallHistory(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String cuSite = request.getSession().getAttribute("cuSite").toString();
//		List<DcallHistory> result=new ArrayList<DcallHistory>();
//		try {
//			String queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id ) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus, recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,'' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id  and cus_site=:cuSite and curr_que='P8')  and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null and cus_site=:cuSite) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null ))  and b.cus_site=:cuSite  order by a.GEN_DT  desc";			
//			Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
//			query.setParameter("fromdate", fromdate);
//			query.setParameter("todate", todate);
//			query.setParameter("cuSite",cuSite);
//			result = (List<DcallHistory>) query.getResultList();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}
	public List<DcallHistory2> getDcallHistory(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		List<DcallHistory2> result=new ArrayList<DcallHistory2>();
		try {
			String queryStr=" \r\n"
					+ "                    select item_id,postingdt post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,printcou,smscou,emailcou,dcall_dt gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,id from\r\n"
					+ "										(select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') as postingdt,a.CIN_NO,'FIRST QUERY' qryname, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "								from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "									and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' or b.qry_reply='U') and a.item_id=f.item_id and d.qry_type<>'E4' and f.item_no is null and a.cus_site=:cuSite  \r\n"
					+ "									 and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y' ))\r\n"
					+ "										and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0 \r\n"
					+ "									union\r\n"
					+ "									select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd HH24:MI:SS'),'DD/MM/YYYY HH24:MI:SS') as postingdt,a.CIN_NO,'ADDITIONAL QUERY' qryname , dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "									from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "									and (b.qry_reply is null or b.qry_reply='Y'  or b.qry_reply='U' ) and a.item_id=f.item_id  and f.qry_def_slno is null and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id\r\n"
					+ "										and (qry_reply is null or qry_reply='Y'))\r\n"
					+ "									and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0)\r\n"
					+ "									where trunc(gen_dt)=trunc(sysdate)\r\n"
					+ "                     order by gen_dt desc";
			
			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory2.class);
			/*
			 * query.setParameter("fromdate", fromdate); query.setParameter("todate",
			 * todate);
			 */
			query.setParameter("cuSite",cuSite);
			result = (List<DcallHistory2>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public List<DcallHistory2> getDcallHistory2(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		List<DcallHistory2> result=new ArrayList<DcallHistory2>();
		try {
			String queryStr=" select item_id,to_char(to_date(substr(postingdt,0,10),'yyyy-mm-dd '),'dd/mm/yyyy ') as post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,printcou,smscou,emailcou,gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,id from\r\n"
					+ "					(select a.item_id,a.postingdt,a.CIN_NO,'FIRST QUERY' qryname, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "					and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' ) and a.item_id=f.item_id and d.qry_type<>'E4' and f.qry_typ!='D' and f.item_no is null and a.cus_site=:cuSite  \r\n"
					+ "					 and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y'))\r\n"
					+ "					and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0 \r\n"
					+ "					union\r\n"
					+ "					select a.item_id,a.postingdt,a.CIN_NO,'ADDITIONAL QUERY' qryname , dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "					and (b.qry_reply is null or b.qry_reply='Y'   ) and a.item_id=f.item_id  and f.qry_type!='D'   and  f.qry_def_slno is null and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id \r\n"
					+ "					and (qry_reply is null or qry_reply='Y'  ))\r\n"
					+ "					and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0)\r\n"
					+ "					where trunc(gen_dt)=trunc(sysdate)";			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory2.class);
//			query.setParameter("fromdate", fromdate);
//			query.setParameter("todate", todate);
			query.setParameter("cuSite",cuSite);
			result = (List<DcallHistory2>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
//	public List<DcallHistory> getDcallHistoryPendingPrint( HttpSession session, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String cuSite = request.getSession().getAttribute("cuSite").toString();
//		List<DcallHistory> result=new ArrayList<DcallHistory>();
//		try {
//			String queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id ) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and qry_def_slno is null and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and item_no is null and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,'' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8')  and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and item_no is null and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null and qry_def_slno is null)) and PRINTCOU is null and b.cus_site=:cuSite order by a.GEN_DT  desc";
//			Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
//			query.setParameter("cuSite", cuSite);
//			result = (List<DcallHistory>) query.getResultList();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	 public List<DcallHistory3> getDcallHistoryPendingPrint( HttpSession session,
			  HttpServletRequest request) { // TODO Auto-generated method stub 
				  String cuSite = request.getSession().getAttribute("cuSite").toString();
			  List<DcallHistory3> result=new ArrayList<DcallHistory3>(); 
			  try {
				  String queryStr=" select item_id,to_char(to_date(substr(postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,rly_dt,printcou,smscou,emailcou,gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,id,recp_add1,rly_dt,rlystatus,postingdt ooc  from\r\n"
			  +
			  "(select a.item_id,a.postingdt postingdt ,'FIRST QUERY' qryname,a.CIN_NO, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,f.RPLY_DATE rly_dt,\r\n"
			  + "decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus\r\n"
			  +
			  "from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
			  +
			  "and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' ) and a.item_id=f.item_id and d.qry_type<>'E4' and f.item_no is null and  f.qry_typ!='D' and a.cus_site=:cuSite  \r\n"
			  +
			  " and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y'))\r\n"
			  +
			  "and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0 \r\n"
			  + "union\r\n" +
			  "select a.item_id,a.postingdt postingdt,'ADDITIONAL QUERY' qryname,a.CIN_NO, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,f.RPLY_DATE rly_dt,\r\n"
			  + "decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus\r\n"
			  +
			  "from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
			  +
			  "and (b.qry_reply is null or b.qry_reply='Y') and a.item_id=f.item_id  and f.qry_def_slno is null and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id \r\n"
			  + "and (qry_reply is null or qry_reply='Y' ) )\r\n" +
			  "and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and  f.qry_type!='D' and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null and decode(printcou,null,0)=0)\r\n"
			  + "order by gen_dt desc"; 
			  Query query =  entityManager.createNativeQuery(queryStr,DcallHistory3.class);
			  query.setParameter("cuSite", cuSite); result = (List<DcallHistory3>)
			  query.getResultList(); 
			  } catch (Exception e) 
			  {
				  // TODO: handle exception
			  e.printStackTrace();
			  } 
			  return result;
			  }
	

	public String getDcallNo(String item_ID, String cuSite) {
		// TODO Auto-generated method stub
		String result="";
		try {
			String queryStr="select DCALL_NO from dcallqry_gen where id = (select max(id) from dcallqry_gen where item_id=:item_ID and cus_site=:cuSite)";
			
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID", item_ID);
			query.setParameter("cuSite", cuSite);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}

	
	public List<String> getSlnoCategory(String dataSLno) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		String[] slno = dataSLno.split(",");
		String bcdno1=slno[0];
		try {
			String queryStr="select distinct bcd_nsno from fpo_item_det  where bcd_notn is not null ";
			queryStr= queryStr + " and (";
				
			queryStr= queryStr + "bcd_notn =:bcdno1 ";
			for(int i=1;i<slno.length;i++) {
				queryStr=queryStr+" or bcd_notn=(:bcd"+i+")";
			}
			queryStr= queryStr + ")";
			System.out.println("printing written query"+queryStr);
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("bcdno1",bcdno1);
			
			for(int i=1;i<slno.length;i++) {
				String bcdnumber=slno[i];
				query.setParameter("bcd"+i,bcdnumber);
			}
			result = (List<String>) query.getResultList();
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
//	public List<DcallHistory> getDcallHistoryPush(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String cuSite = request.getSession().getAttribute("cuSite").toString();
//		List<DcallHistory> result=new ArrayList<DcallHistory>();
//		try {
//			String queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,'' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8')  and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0 and b.cus_site=:cuSite order by a.GEN_DT  desc";
//			
//			
//			Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
//			query.setParameter("fromdate", fromdate);
//			query.setParameter("todate", todate);
//			query.setParameter("cuSite", cuSite);
//			result = (List<DcallHistory>) query.getResultList();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}

	
	public List<DcallHistory2> getDcallHistoryPush(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		List<DcallHistory2> result=new ArrayList<DcallHistory2>();
		try {
			String queryStr=" select item_id, to_char(to_date(substr(postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,printcou,smscou,emailcou,gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,id from\r\n"
					+ "									(select a.item_id,a.postingdt,a.CIN_NO,'FIRST QUERY' qryname, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "									from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "										and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' ) and a.item_id=f.item_id and d.qry_type<>'E4'   and f.qry_typ!='D'  and f.item_no is null and a.cus_site=:cuSite  \r\n"
					+ "										 and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y'))\r\n"
					+ "									and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id and item_no is null) and a.item_id=g.item_id and  g.ooc_dt is null and PRINTCOU>0  \r\n"
					+ "										union\r\n"
					+ "										select a.item_id,a.postingdt,a.CIN_NO,'ADDITIONAL QUERY' qryname , dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,b.speedpost_dt,b.speedpost_deli_status,b.id\r\n"
					+ "									from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
					+ "										and (b.qry_reply is null or b.qry_reply='Y') and a.item_id=f.item_id  and f.qry_def_slno is null   and f.qry_type!='D'  and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id \r\n"
					+ "										and (qry_reply is null or qry_reply='Y'))\r\n"
					+ "										and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null and PRINTCOU>0)\r\n"
					+ "									where trunc(gen_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by gen_dt desc ";
			
			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory2.class);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("cuSite", cuSite);
			result = (List<DcallHistory2>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/*
	 * public List<DcallHistory> getDcallHistory(String month, HttpSession
	 * session,String order) { // TODO Auto-generated method stub List<DcallHistory>
	 * result=new ArrayList<DcallHistory>(); try { String
	 * queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,to_char(speedpost_dt , 'dd/mm/yyyy') speedpost_dt,speedpost_no,case when 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') then 'OOC Not Given' else 'OOC Given' end as ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where   to_char(a.GEN_DT , 'yyyy-mm') <= :month order by a.GEN_DT  desc"
	 * ;
	 * 
	 * Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
	 * query.setParameter("month", month); query.setParameter("order", order);
	 * result = (List<DcallHistory>) query.getResultList(); } catch (Exception e) {
	 * // TODO: handle exception e.printStackTrace(); } return result; }
	 */
	public List<DcallHistory3> getDcallHistory(String fromdate,String todate, HttpSession session,String order,HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DcallHistory3> result=new ArrayList<DcallHistory3>();
		String cuSite = request.getSession().getAttribute("cuSite").toString();
		String queryStr;
		try {
			if (order.equalsIgnoreCase("desc")) {
				
			 queryStr="select item_id,postingdt post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,printcou,smscou,emailcou,gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,ooc_dt ooc,id,rly_dt,rlystatus,rlystatus recp_add1 from\r\n"
			 		+ "					(select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') as postingdt,a.CIN_NO,'FIRST QUERY' qryname, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) \r\n"
			 		+ "                    GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,to_char(b.speedpost_dt,'DD/MM/YYYY HH24:MI:SS')speedpost_dt,b.speedpost_deli_status,decode(g.ooc_dt,null,'OOC NOT GIVEN','OOC GIVEN')ooc_dt,to_char(f.RPLY_DATE,'DD/MM/YYYY HH24:MI:SS') rly_dt,\r\n"
			 		+ "	     decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus, b.id\r\n"
			 		+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
			 		+ "					and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' ) and a.item_id=f.item_id and d.qry_type<>'E4' and f.item_no is null and a.cus_site=:cuSite \r\n"
			 		+ "					 and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y'))\r\n"
			 		+ "                    and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id) and a.item_id=g.item_id and  g.ooc_dt is null  \r\n"
			 		+ "					union\r\n"
			 		+ "					select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') as postingdt,a.CIN_NO,'ADDITIONAL QUERY' qryname , dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,to_char(b.speedpost_dt,'DD/MM/YYYY HH24:MI:SS')speedpost_dt,b.speedpost_deli_status,decode(g.ooc_dt,null,'OOC NOT GIVEN','OOC GIVEN')ooc_dt,to_char(f.RPLY_DATE,'DD/MM/YYYY HH24:MI:SS') rly_dt,\r\n"
			 		+ "	     decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus,b.id\r\n"
			 		+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
			 		+ "					and (b.qry_reply is null or b.qry_reply='Y'   ) and a.item_id=f.item_id  and f.qry_def_slno is null and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id \r\n"
			 		+ "					and (qry_reply is null or qry_reply='Y'  ))\r\n"
			 		+ "					and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null )\r\n"
			 		+ "                   where trunc(gen_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')order by gen_dt  desc";			
			}
			else {
				 queryStr="select item_id,postingdt post_dt,qryname,dcall_no,dcall_dt,codedesc mail_class ,cntry_nm origin_country,printcou,smscou,emailcou,gen_dt,cin_no,recp_name,gen_filename,full_path,mail_cc,mail_to,mobile_no_1,mobile_no_2,speedpost_no,speedpost_dt,speedpost_deli_status,ooc_dt ooc,id,rly_dt,rlystatus,rlystatus recp_add1 from\r\n"
				 		+ "					(select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') as postingdt,a.CIN_NO,'FIRST QUERY' qryname, dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) \r\n"
				 		+ "                    GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,to_char(b.speedpost_dt,'DD/MM/YYYY HH24:MI:SS')speedpost_dt,b.speedpost_deli_status,decode(g.ooc_dt,null,'OOC NOT GIVEN','OOC GIVEN')ooc_dt,to_char(f.RPLY_DATE,'DD/MM/YYYY HH24:MI:SS') rly_dt,\r\n"
				 		+ "	     decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus, b.id\r\n"
				 		+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_qry_deci d, fpo_query  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
				 		+ "					and a.item_id=d.item_id and (b.qry_reply is null or b.qry_reply='Y' ) and a.item_id=f.item_id and d.qry_type<>'E4' and f.item_no is null and a.cus_site=:cuSite \r\n"
				 		+ "					 and b.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (qry_reply is null or qry_reply='Y'))\r\n"
				 		+ "                    and f.qry_no = (select max(qry_no) from fpo_query where item_id=a.item_id) and a.item_id=g.item_id and  g.ooc_dt is null  \r\n"
				 		+ "					union\r\n"
				 		+ "					select a.item_id,to_char(to_date(substr(a.postingdt,0,10),'yyyy-mm-dd'),'DD/MM/YYYY') as postingdt,a.CIN_NO,'ADDITIONAL QUERY' qryname , dcall_no,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') dcall_dt,c.codedesc,e.cntry_nm,printcou,smscou,emailcou,gen_dt,b.recp_name,substr(b.GEN_FILENAME,-31) GEN_FILENAME,b.GEN_FILENAME full_path,b.mail_cc,b.mail_to,b.mobile_no_1,b.mobile_no_2,b.speedpost_no,to_char(b.speedpost_dt,'DD/MM/YYYY HH24:MI:SS')speedpost_dt,b.speedpost_deli_status,decode(g.ooc_dt,null,'OOC NOT GIVEN','OOC GIVEN')ooc_dt,to_char(f.RPLY_DATE,'DD/MM/YYYY HH24:MI:SS') rly_dt,\r\n"
				 		+ "	     decode(f.RPLY_DATE,null,'Reply Not Received','Reply Received')rlystatus,b.id\r\n"
				 		+ "					from fpo_main a left join ops$dir.d_cntry_cd e on e.cntry_cd=a.send_cntry_cd , dcallqry_gen b, ops$dir.pdi_mail_class_codes c, fpo_addl_qry  f, fpo_status g where a.item_id=b.item_id and a.mail_class_cd=c.code\r\n"
				 		+ "					and (b.qry_reply is null or b.qry_reply='Y'   ) and a.item_id=f.item_id  and f.qry_def_slno is null and a.cus_site=:cuSite and b.id=(select max(id) from dcallqry_gen where item_id=a.item_id \r\n"
				 		+ "					and (qry_reply is null or qry_reply='Y'  ))\r\n"
				 		+ "					and f.qry_id = (select max(qry_id) from fpo_query where item_id=a.item_id and f.qry_def_slno is null) and a.item_id=g.item_id and  g.ooc_dt is null )\r\n"
				 		+ "                   where trunc(gen_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by gen_dt  asc";			
				
			}
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory3.class);
//			query.setParameter("month", month);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			query.setParameter("cuSite", cuSite);
//			query.setParameter("order", order);
			result = (List<DcallHistory3>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<ImportExam> getImportedExaminecolumnsletters(HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ImportExam> result=new ArrayList<ImportExam>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT ROW_NUMBER() OVER(order by q.job_no) as id,i.ITEM_ID, to_char(to_date(substr(q.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') cin_dt,dtc.CNTRY_NM as coo,pdi.CATEGORY as item_category,aai.ooe arrival_ooe,to_char(aai.RECD_EVENT_DT,'DD/MM/YYYY') RECD_EVENT_DT,afi.RECD_FPO,to_char(afi.RECD_DT,'DD/MM/YYYY') arrivalfpo_date FROM fpo_main q join fpo_qry_deci i on (q.ITEM_ID=i.ITEM_ID) join fpo_mvmnt j on (i.cin_no=j.cin_no) join ops$dir.que_disp k on (k.role=j.role) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=q.item_id) left join articlearr_fpo_info afi on (afi.ARTICLE_ID=q.item_id) left join OPS$DIR.d_cntry_cd dtc on (q.SEND_CNTRY_CD = dtc.CNTRY_CD)  left join ops$dir.pdi_nature_trans_codes pdi on ( pdi.code=q.nature_type_cd) where i.cus_site=:cuSite and i.qry_type = 'P4' and i.qry_no is null and q.item_id in (select article_id from article_arr_info x )  and j.slno in (select max(slno) FROM fpo_mvmnt t where t.cin_no=i.cin_no ) and MAIL_CLASS_CD='U' order by q.job_no,q.job_dt";			
			
			Query query = entityManager.createNativeQuery(queryStr,ImportExam.class);
			query.setParameter("cuSite", cuSite);
			result = (List<ImportExam>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ImportExam> getImportedExaminecolumnsems(HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ImportExam> result=new ArrayList<ImportExam>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT ROW_NUMBER() OVER(order by q.job_no) as id,i.ITEM_ID, to_char(to_date(substr(q.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') cin_dt,dtc.CNTRY_NM as coo,pdi.CATEGORY as item_category,aai.ooe arrival_ooe,to_char(aai.RECD_EVENT_DT,'DD/MM/YYYY') RECD_EVENT_DT,afi.RECD_FPO,to_char(afi.RECD_DT,'DD/MM/YYYY') arrivalfpo_date FROM fpo_main q join fpo_qry_deci i on (q.ITEM_ID=i.ITEM_ID) join fpo_mvmnt j on (i.cin_no=j.cin_no) join ops$dir.que_disp k on (k.role=j.role) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=q.item_id) left join articlearr_fpo_info afi on (afi.ARTICLE_ID=q.item_id) left join OPS$DIR.d_cntry_cd dtc on (q.SEND_CNTRY_CD = dtc.CNTRY_CD)  left join ops$dir.pdi_nature_trans_codes pdi on ( pdi.code=q.nature_type_cd) where i.cus_site=:cuSite and i.qry_type = 'P4' and i.qry_no is null and q.item_id in (select article_id from article_arr_info x )  and j.slno in (select max(slno) FROM fpo_mvmnt t where t.cin_no=i.cin_no ) and MAIL_CLASS_CD='E' order by q.job_no,q.job_dt";
						
			Query query = entityManager.createNativeQuery(queryStr,ImportExam.class);
			query.setParameter("cuSite", cuSite);
			result = (List<ImportExam>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ImportExam> getImportedExaminecolumnsparcel(HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ImportExam> result=new ArrayList<ImportExam>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr="SELECT ROW_NUMBER() OVER(order by q.job_no) as id,i.ITEM_ID, to_char(to_date(substr(q.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') cin_dt,dtc.CNTRY_NM as coo,pdi.CATEGORY as item_category,aai.ooe arrival_ooe,to_char(aai.RECD_EVENT_DT,'DD/MM/YYYY') RECD_EVENT_DT,afi.RECD_FPO,to_char(afi.RECD_DT,'DD/MM/YYYY') arrivalfpo_date FROM fpo_main q join fpo_qry_deci i on (q.ITEM_ID=i.ITEM_ID) join fpo_mvmnt j on (i.cin_no=j.cin_no) join ops$dir.que_disp k on (k.role=j.role) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=q.item_id) left join articlearr_fpo_info afi on (afi.ARTICLE_ID=q.item_id) left join OPS$DIR.d_cntry_cd dtc on (q.SEND_CNTRY_CD = dtc.CNTRY_CD)  left join ops$dir.pdi_nature_trans_codes pdi on ( pdi.code=q.nature_type_cd) where i.cus_site=:cuSite and i.qry_type = 'P4' and i.qry_no is null and q.item_id in (select article_id from article_arr_info x )  and j.slno in (select max(slno) FROM fpo_mvmnt t where t.cin_no=i.cin_no ) and MAIL_CLASS_CD='C' order by q.job_no,q.job_dt";
				
			Query query = entityManager.createNativeQuery(queryStr,ImportExam.class);
			query.setParameter("cuSite", cuSite);
			result = (List<ImportExam>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ImportExam> getImportedExaminecolumnsempty(HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ImportExam> result=new ArrayList<ImportExam>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();			
			String queryStr="SELECT ROW_NUMBER() OVER(order by q.job_no) as id,i.ITEM_ID, to_char(to_date(substr(q.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') cin_dt,dtc.CNTRY_NM as coo,pdi.CATEGORY as item_category,aai.ooe arrival_ooe,to_char(aai.RECD_EVENT_DT,'DD/MM/YYYY') RECD_EVENT_DT,afi.RECD_FPO,to_char(afi.RECD_DT,'DD/MM/YYYY') arrivalfpo_date FROM fpo_main q join fpo_qry_deci i on (q.ITEM_ID=i.ITEM_ID) join fpo_mvmnt j on (i.cin_no=j.cin_no) join ops$dir.que_disp k on (k.role=j.role) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=q.item_id) left join articlearr_fpo_info afi on (afi.ARTICLE_ID=q.item_id) left join OPS$DIR.d_cntry_cd dtc on (q.SEND_CNTRY_CD = dtc.CNTRY_CD)  left join ops$dir.pdi_nature_trans_codes pdi on ( pdi.code=q.nature_type_cd) where i.cus_site=:cuSite and i.qry_type = 'P4' and i.qry_no is null and q.item_id in (select article_id from article_arr_info x )  and j.slno in (select max(slno) FROM fpo_mvmnt t where t.cin_no=i.cin_no ) and MAIL_CLASS_CD='T' order by q.job_no,q.job_dt";
			
			Query query = entityManager.createNativeQuery(queryStr,ImportExam.class);
			query.setParameter("cuSite", cuSite);

			result = (List<ImportExam>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<ImportExam> getImportedExaminecolumns(HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ImportExam> result=new ArrayList<ImportExam>();
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();			
			String queryStr="SELECT ROW_NUMBER() OVER(order by q.job_no) as id,i.ITEM_ID, to_char(to_date(substr(q.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') cin_dt,dtc.CNTRY_NM as coo,pdi.CATEGORY as item_category,aai.ooe arrival_ooe,to_char(aai.RECD_EVENT_DT,'DD/MM/YYYY') RECD_EVENT_DT,afi.RECD_FPO,to_char(afi.RECD_DT,'DD/MM/YYYY') arrivalfpo_date FROM fpo_main q join fpo_qry_deci i on (q.ITEM_ID=i.ITEM_ID) join fpo_mvmnt j on (i.cin_no=j.cin_no) join ops$dir.que_disp k on (k.role=j.role) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=q.item_id) left join articlearr_fpo_info afi on (afi.ARTICLE_ID=q.item_id) left join OPS$DIR.d_cntry_cd dtc on (q.SEND_CNTRY_CD = dtc.CNTRY_CD)  left join ops$dir.pdi_nature_trans_codes pdi on ( pdi.code=q.nature_type_cd) where i.cus_site=:cuSite and i.qry_type = 'P4' and i.qry_no is null and q.item_id in (select article_id from article_arr_info x )  and j.slno in (select max(slno) FROM fpo_mvmnt t where t.cin_no=i.cin_no ) order by q.job_no,q.job_dt";
						
			Query query = entityManager.createNativeQuery(queryStr,ImportExam.class);
			query.setParameter("cuSite", cuSite);
			result = (List<ImportExam>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean getAAFCountQuery(String itemId, String stage) {
		boolean result = false;
		try {
			stage=stage+"%";
			String queryStr = "select count(*) from dcallqry_gen where item_id = :itemId and QRY_REPLY = 'Y' and dcall_no like :stage ";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("itemId", itemId);
			query.setParameter("stage", stage);
			List<Object> resultList = query.getResultList();
			result = ((BigDecimal)resultList.get(0)).longValue() > 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getFileNameForAAFRly(String item_ID,String stage) {
		String result="";
		try {
			stage=stage+"%";
			String queryStr="select substr(GEN_FILENAME,-31) filename from dcallqry_gen where id = (select min(id) from dcallqry_gen where item_id=:item_ID and QRY_REPLY = 'Y' and dcall_no like :stage)";
			
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("item_ID", item_ID);
			query.setParameter("stage", stage);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = (String)resultList.get(0);
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	}



	public List<SelectTag> getCountryOfOriginsAlert() {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select distinct CNTRY_cd as id,CNTRY_cd as data,CNTRY_NM as value from OPS$DIR.d_cntry_cd where CNTRY_NM is not null and CNTRY_NM<>' ' order by CNTRY_NM";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getMailClassAlert() {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select distinct code as id,code as data,codedesc as value from ops$dir.pdi_mail_class_codes where codedesc is not null and codedesc <> ' ' order by codedesc";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getMailCatAlert() {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select distinct code as id,code as data,INTERPRETATION as value from ops$dir.pdi_nature_trans_codes where INTERPRETATION is not null and INTERPRETATION <> ' ' order by INTERPRETATION";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getSiteAlert() {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String queryStr="select distinct SITE_CODE as id,SITE_CODE as data,SITE_CODE as value from ops$dir.fpo_sites where SITE_CODE is not null and SITE_CODE <> ' ' order by SITE_CODE";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getMAIL_CLASS(String id) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select codedesc from ops$dir.pdi_mail_class_codes where code in (with rws as (\r\n"
					+ "  select MAIL_CLASS str FROM fpo_alert where id = :id) \r\n"
					+ "  select regexp_substr (\r\n"
					+ "           str,\r\n"
					+ "           '[^,]+',\r\n"
					+ "           1,\r\n"
					+ "           level\r\n"
					+ "         ) value\r\n"
					+ "  from   rws\r\n"
					+ "  connect by level <= \r\n"
					+ "    length ( str ) - length ( replace ( str, ',' ) ) + 1)";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("id", id);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getItemCategory(String id) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select INTERPRETATION from ops$dir.pdi_nature_trans_codes where code in (with rws as (\r\n"
					+ "  select ITEM_CATEGORY str FROM fpo_alert where id = :id)\r\n"
					+ "  select regexp_substr (\r\n"
					+ "           str,\r\n"
					+ "           '[^,]+',\r\n"
					+ "           1,\r\n"
					+ "           level\r\n"
					+ "         ) value\r\n"
					+ "  from   rws\r\n"
					+ "  connect by level <= \r\n"
					+ "    length ( str ) - length ( replace ( str, ',' ) ) + 1)";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("id", id);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getAlerts(FPO_MAIN fpoMain) {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			/*	String queryStr="select id,ACTIVE_SINCE data,ALERT value FROM fpo_alert where id in (WITH rws\r\n"
			+ "     AS (SELECT cus_site str,id FROM fpo_alert where STATUS='Active'),\r\n"
			+ "     op\r\n"
			+ "     AS (SELECT Regexp_substr (str, '[^,]+', 1, LEVEL) value,id FROM   rws CONNECT BY LEVEL <= Length (str) - Length (Replace (str, ',')) + 1),\r\n"
			+ "     cusid\r\n"
			+ "     AS (SELECT id FROM   op WHERE  value = '"+fpoMain.getCUS_SITE()+"' GROUP  BY id),\r\n"
			+ "     rws1\r\n"
			+ "     AS (SELECT country str,id FROM fpo_alert where STATUS='Active'),\r\n"
			+ "     op1\r\n"
			+ "     AS (SELECT Regexp_substr (str, '[^,]+', 1, LEVEL) value,id FROM   rws1 CONNECT BY LEVEL <= Length (str) - Length (Replace (str, ',')) + 1),\r\n"
			+ "     cntryid\r\n"
			+ "     AS (SELECT id FROM   op1 WHERE  value = '"+fpoMain.getSEND_CNTRY_CD()+"' GROUP  BY id),\r\n"
			+ "     rws2\r\n"
			+ "     AS (SELECT mail_class str, id FROM fpo_alert where STATUS='Active'),\r\n"
			+ "     op2\r\n"
			+ "     AS (SELECT Regexp_substr (str, '[^,]+', 1, LEVEL) value, id FROM   rws2 CONNECT BY LEVEL <= Length (str) - Length (Replace (str, ',')) + 1),\r\n"
			+ "     mailid\r\n"
			+ "     AS (SELECT id FROM   op2 WHERE  value = '"+fpoMain.getMAIL_CLASS_CD()+"' GROUP  BY id),\r\n"
			+ "     rws3\r\n"
			+ "     AS (SELECT item_category str, id FROM fpo_alert where STATUS='Active'),\r\n"
			+ "     op3\r\n"
			+ "     AS (SELECT Regexp_substr (str, '[^,]+', 1, LEVEL) value, id FROM   rws3 CONNECT BY LEVEL <= Length (str) - Length (Replace (str, ',')) + 1),\r\n"
			+ "     itemcatid\r\n"
			+ "     AS (SELECT id FROM op3 WHERE  value = '"+fpoMain.getNATURE_TYPE_CD()+"' GROUP  BY id),\r\n"
			+ "     final\r\n"
			+ "     AS (SELECT * FROM   cusid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   cntryid where id in (SELECT * FROM   cusid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   mailid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   cusid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   itemcatid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   cusid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT id FROM fpo_alert where ALERT_TYPE='Generic' and  STATUS='Active' and CUS_SITE = '"+fpoMain.getCUS_SITE()+"' )\r\n"
			+ "SELECT DISTINCT id FROM   final ) order by id desc"; */
			
			
			String sendcntrycd = fpoMain.getSEND_CNTRY_CD();
			String cuSite = fpoMain.getCUS_SITE();
			String nattypcd = fpoMain.getNATURE_TYPE_CD();
			String mailclasscd = fpoMain.getMAIL_CLASS_CD();
	
	String queryStr=" select id,ACTIVE_SINCE data,ALERT value FROM fpo_alert where id in (WITH op\r\n"
			+ "     AS (SELECT t.id,Trim(Regexp_substr(t.country, '[^,]+', 1, lines.column_value)) AS COUNTRY  FROM fpo_alert t,\r\n"
			+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.country, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
			+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='All India'\r\n"
			+ "         ORDER  BY id, lines.column_value),\r\n"
			+ "cntryid as(\r\n"
			+ "SELECT id FROM   op WHERE  country = :sendcntrycd GROUP  BY id ) ,\r\n"
			+ "op1 AS \r\n"
			+ "(SELECT t.id,Trim(Regexp_substr(t.CUS_SITE, '[^,]+', 1, lines.column_value)) AS CUS_SITE  FROM fpo_alert t,\r\n"
			+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.CUS_SITE, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
			+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='All India'\r\n"
			+ "         ORDER  BY id, lines.column_value),\r\n"
			+ "cusid as(\r\n"
			+ "SELECT id FROM   op1 WHERE  CUS_SITE = :cuSite GROUP  BY id ) ,\r\n"
			+ "op2 AS \r\n"
			+ "(SELECT t.id,Trim(Regexp_substr(t.item_category, '[^,]+', 1, lines.column_value)) AS item_category  FROM fpo_alert t,\r\n"
			+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.item_category, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
			+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='All India'\r\n"
			+ "         ORDER  BY id, lines.column_value),\r\n"
			+ "itemcatid as(\r\n"
			+ "SELECT id FROM   op2 WHERE  item_category = :nattypcd or item_category is null or item_category='null' GROUP  BY id ) ,\r\n"
			+ "op3 AS \r\n"
			+ "(SELECT t.id,Trim(Regexp_substr(t.mail_class, '[^,]+', 1, lines.column_value)) AS mail_class  FROM fpo_alert t,\r\n"
			+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.mail_class, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
			+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='All India'\r\n"
			+ "         ORDER  BY id, lines.column_value),\r\n"
			+ "mailid as(\r\n"
			+ "SELECT id FROM   op3 WHERE  mail_class = :mailclasscd or mail_class is null or mail_class='null' GROUP  BY id ),\r\n"
			+ "     final\r\n"
			+ "     AS (SELECT * FROM   cusid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   cntryid where id in (SELECT * FROM   cusid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   mailid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   cusid) and id in (SELECT * FROM   itemcatid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT * FROM   itemcatid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   cusid)\r\n"
			+ "         UNION ALL\r\n"
			+ "         SELECT id FROM fpo_alert where ALERT_TYPE='Generic' and  STATUS='Active' and ALERT_LEVEL='All India' and  id in (SELECT * FROM   cusid) )\r\n"
			+ "SELECT DISTINCT id FROM   final ) order by id desc";
	Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
	query.setParameter("sendcntrycd",sendcntrycd);
	query.setParameter("cuSite",cuSite);
	query.setParameter("mailclasscd", mailclasscd);
	query.setParameter("nattypcd", nattypcd);
	result = (List<SelectTag>) query.getResultList();
} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SelectTag> getSiteAlerts(FPO_MAIN fpoMain) {
		// TODO Auto-generated method stub
		List<SelectTag> result=new ArrayList<SelectTag>();
		try {
			String sendcntrycd = fpoMain.getSEND_CNTRY_CD();
			String cuSite = fpoMain.getCUS_SITE();
			String nattypcd = fpoMain.getNATURE_TYPE_CD();
			String mailclasscd = fpoMain.getMAIL_CLASS_CD();
			String queryStr=" select id,ACTIVE_SINCE data,ALERT value FROM fpo_alert where id in (WITH op\r\n"
					+ "     AS (SELECT t.id,Trim(Regexp_substr(t.country, '[^,]+', 1, lines.column_value)) AS COUNTRY  FROM fpo_alert t,\r\n"
					+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.country, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
					+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='Site'\r\n"
					+ "         ORDER  BY id, lines.column_value),\r\n"
					+ "cntryid as(\r\n"
					+ "SELECT id FROM   op WHERE  country = :sendcntrycd GROUP  BY id ) ,\r\n"
					+ "op1 AS \r\n"
					+ "(SELECT t.id,Trim(Regexp_substr(t.CUS_SITE, '[^,]+', 1, lines.column_value)) AS CUS_SITE  FROM fpo_alert t,\r\n"
					+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.CUS_SITE, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
					+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='Site'\r\n"
					+ "         ORDER  BY id, lines.column_value),\r\n"
					+ "cusid as(\r\n"
					+ "SELECT id FROM   op1 WHERE  CUS_SITE = :cuSite GROUP  BY id ) ,\r\n"
					+ "op2 AS \r\n"
					+ "(SELECT t.id,Trim(Regexp_substr(t.item_category, '[^,]+', 1, lines.column_value)) AS item_category  FROM fpo_alert t,\r\n"
					+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.item_category, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
					+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='Site'\r\n"
					+ "         ORDER  BY id, lines.column_value),\r\n"
					+ "itemcatid as(\r\n"
					+ "SELECT id FROM   op2 WHERE  item_category = :nattypcd or item_category is null or item_category='null' GROUP  BY id ) ,\r\n"
					+ "op3 AS \r\n"
					+ "(SELECT t.id,Trim(Regexp_substr(t.mail_class, '[^,]+', 1, lines.column_value)) AS mail_class  FROM fpo_alert t,\r\n"
					+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.mail_class, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
					+ "         WHERE  t.status = 'Active' and ALERT_LEVEL='Site'\r\n"
					+ "         ORDER  BY id, lines.column_value),\r\n"
					+ "mailid as(\r\n"
					+ "SELECT id FROM   op3 WHERE  mail_class = :mailclasscd or mail_class is null or mail_class='null' GROUP  BY id ),\r\n"
							+ "op4 AS \r\n"
							+ "(SELECT t.id,Trim(Regexp_substr(t.CUS_SITE, '[^,]+', 1, lines.column_value)) AS CUS_SITE  FROM fpo_alert t,\r\n"
							+ "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.mail_class, ',') + 1) AS sys.ODCINUMBERLIST) )lines\r\n"
							+ "         WHERE  t.status = 'Active' and t.ALERT_TYPE='Generic' and ALERT_LEVEL='Site'\r\n"
							+ "         ORDER  BY id, lines.column_value),\r\n"
							+ "genid as(\r\n"
							+ "SELECT id FROM   op4 WHERE   CUS_SITE = :cuSite GROUP  BY id ),\r\n"
					+ "     final\r\n"
					+ "     AS (SELECT * FROM   cusid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
					+ "         UNION ALL\r\n"
					+ "         SELECT * FROM   cntryid where id in (SELECT * FROM   cusid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   itemcatid)\r\n"
					+ "         UNION ALL\r\n"
					+ "         SELECT * FROM   mailid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   cusid) and id in (SELECT * FROM   itemcatid)\r\n"
					+ "         UNION ALL\r\n"
					+ "         SELECT * FROM   itemcatid where id in (SELECT * FROM   cntryid) and  id in (SELECT * FROM   mailid) and id in (SELECT * FROM   cusid)\r\n"
					+ "         UNION ALL\r\n"
					+ "         SELECT id FROM genid )\r\n"
					+ "SELECT DISTINCT id FROM   final ) order by id desc";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			query.setParameter("sendcntrycd",sendcntrycd);
			query.setParameter("cuSite",cuSite);
			query.setParameter("mailclasscd", mailclasscd);
			query.setParameter("nattypcd", nattypcd);
			result = (List<SelectTag>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/*public SelectTag getLastLoginetails(String offid) {
		// TODO Auto-generated method stub
		SelectTag result=new SelectTag();
		try {
			String queryStr="with login_time as(\r\n"
					+ "select to_char(login_time,'dd/mm/yyyy hh24:mi:ss')  logintime FROM fpo_login_track where id=(select max(id) FROM fpo_login_track where off_id='"+offid+"' and id <> (select max(id) FROM fpo_login_track where off_id='"+offid+"'))),\r\n"
					+ "logout_time as(\r\n"
					+ "select to_char(logout_time,'dd/mm/yyyy hh24:mi:ss')  logouttime FROM fpo_login_track where id=(select max(id) FROM fpo_login_track where off_id='"+offid+"' and logout_time is not null and login_time is not null))\r\n"
					+ "select '1' id,logintime as data,logouttime as value from logout_time,login_time";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			List<SelectTag> resultList =(List<SelectTag>) query.getResultList();
			if(resultList.size()>0) {
				result=resultList.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}*/
	
	
	public SelectTag getLastLoginetails(String offid) {
		// TODO Auto-generated method stub
		SelectTag result=new SelectTag();
		try {
			String queryStr="select '1' id, to_char(max(login_time),'DD/MM/YYYY HH24:MI:SS') as data from fpo_login_track";
			Query query = entityManager.createNativeQuery(queryStr,SelectTag.class);
			List<SelectTag> resultList =(List<SelectTag>) query.getResultList();
			if(resultList.size()>0) {
				result=resultList.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	

//	public List<DcallHistory> getDcallHistoryspeedpostrecord(HttpSession session, String fromdate, String todate) {
//		// TODO Auto-generated method stub
//		List<DcallHistory> result=new ArrayList<DcallHistory>();
//		try {
//			//String queryStr="select '' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MM:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID='"+request.getSession().getAttribute("offId")+"' and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null and SPEEDPOST_NO is null order by a.GEN_DT  desc";
//			String queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,'' speedpost_dt,'' speedpost_no,case when 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') then 'OOC Not Given' else 'OOC Given' end as ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where    SPEEDPOST_NO is null and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') order by a.GEN_DT  desc";
//			
//			Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
//			query.setParameter("fromdate",fromdate);
//			query.setParameter("todate", todate);
//			result = (List<DcallHistory>) query.getResultList();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}

	
	public List<DcallHistory3> getDcallHistoryspeedpostrecord(HttpSession session, String fromdate, String todate) {
		// TODO Auto-generated method stub
		List<DcallHistory3> result=new ArrayList<DcallHistory3>();
		try {
			//String queryStr="select '' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MM:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID='"+request.getSession().getAttribute("offId")+"' and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null and SPEEDPOST_NO is null order by a.GEN_DT  desc";
			String queryStr="select  item_id, qryname, id, recp_name, recp_add1 ,dcall_no, mail_to, mail_cc, mobile_no_1, mobile_no_2, full_path, gen_filename,gen_dt, post_dt,dcall_dt, rly_dt, rlystatus , \r\n"
					+ "                     mail_class ,speedpost_no, speedpost_dt , speedpost_deli_status  ,printcou,smscou,emailcou,cin_no, origin_country,origin_country ooc from(\r\n"
					+ "					(select a.item_id item_id,'FIRST QUERY' qryname,c.id id,c.recp_name recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1 ,dcall_no,c.mail_to mail_to,c.mail_cc mail_cc,c.mobile_no_1 mobile_no_1,c.mobile_no_2 mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt,gen_dt post_dt,gen_dt dcall_dt ,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "                    c.mail_to mail_class ,c.mail_to speedpost_no,c.mail_to speedpost_dt ,c.mail_to speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country from fpo_query a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and rply_date is null and a.item_id=c.item_id and speedpost_no is  null and item_no is null and\r\n"
					+ "					qry_no=(select max(qry_no) from fpo_query where item_id=a.item_id and item_no is null) and (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') \r\n"
					+ "					and c.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y'  or c.qry_reply='U') and trunc(gen_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')) \r\n"
					+ "					union\r\n"
					+ "					select a.item_id,'ADDITIONAL QUERY' qryname,c.id, c.recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1,dcall_no,c.mail_to mail_to ,\r\n"
					+ "                    c.mail_cc,c.mobile_no_1,c.mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt, gen_dt post_dt,gen_dt dcall_dt,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "                    c.mail_to mail_class ,c.mail_to speedpost_no,c.mail_to speedpost_dt ,c.mail_to speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country\r\n"
					+ "                    from fpo_addl_qry a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and  rply_date is null and a.item_id=c.item_id and\r\n"
					+ "                    speedpost_no is  null and qry_def_slno is null\r\n"
					+ "					and qry_id=(select max(qry_id) from fpo_query where item_id=a.item_id and qry_def_slno is null) and   (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') \r\n"
					+ "					and c.id=(select max(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y'  or c.qry_reply='U')and trunc(gen_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy'))))\r\n"
					+ "					order by gen_dt desc";
			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory3.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			result = (List<DcallHistory3>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public List<DcallHistory3> getDcallHistoryspeedpostrecordcount(HttpSession session) {
		// TODO Auto-generated method stub
		List<DcallHistory3> result=new ArrayList<DcallHistory3>();
		try {
			//String queryStr="select '' speedpost_dt,'' speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MM:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID='"+request.getSession().getAttribute("offId")+"' and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null and SPEEDPOST_NO is null order by a.GEN_DT  desc";
			String queryStr="select  item_id, qryname, id, recp_name, recp_add1 ,dcall_no, mail_to, mail_cc, mobile_no_1, mobile_no_2, full_path, gen_filename,gen_dt, post_dt,dcall_dt, rly_dt, rlystatus , \r\n"
					+ "					                  mail_class ,speedpost_no, speedpost_dt , speedpost_deli_status  ,printcou,smscou,emailcou,cin_no, origin_country,origin_country ooc from(\r\n"
					+ "									(select a.item_id item_id,'FIRST QUERY' qryname,c.id id,c.recp_name recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1 ,dcall_no,c.mail_to mail_to,c.mail_cc mail_cc,c.mobile_no_1 mobile_no_1,c.mobile_no_2 mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt,gen_dt post_dt,gen_dt dcall_dt ,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "					                    c.mail_to mail_class ,c.mail_to speedpost_no,c.mail_to speedpost_dt ,c.mail_to speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country from fpo_query a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and rply_date is null and a.item_id=c.item_id and speedpost_no is  null and item_no is null and\r\n"
					+ "										qry_no=(select max(qry_no) from fpo_query where item_id=a.item_id and item_no is null) and (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') \r\n"
					+ "									and c.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y'  or c.qry_reply='U') ) \r\n"
					+ "									union\r\n"
					+ "								select a.item_id,'ADDITIONAL QUERY' qryname,c.id, c.recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1,dcall_no,c.mail_to mail_to ,\r\n"
					+ "					                   c.mail_cc,c.mobile_no_1,c.mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt, gen_dt post_dt,gen_dt dcall_dt,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "					                   c.mail_to mail_class ,c.mail_to speedpost_no,c.mail_to speedpost_dt ,c.mail_to speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country\r\n"
					+ "					                   from fpo_addl_qry a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and  rply_date is null and a.item_id=c.item_id and\r\n"
					+ "					                   speedpost_no is  null and qry_def_slno is null\r\n"
					+ "									and qry_id=(select max(qry_id) from fpo_query where item_id=a.item_id and qry_def_slno is null) and   (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') \r\n"
					+ "									and c.id=(select max(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y'  or c.qry_reply='U'))))\r\n"
					+ "									order by gen_dt desc";
			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory3.class);
			result = (List<DcallHistory3>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	

	
	
//	public List<DcallHistory> getDcallHistoryspeedpostrecordStatus(HttpSession session, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		List<DcallHistory> result=new ArrayList<DcallHistory>();
//		try {
//			//String queryStr="select to_char(speedpost_dt , 'dd/mm/yyyy HH24:MM:ss') speedpost_dt,speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MM:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID='"+request.getSession().getAttribute("offId")+"' and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null and SPEEDPOST_NO is not null order by a.GEN_DT  desc";
//			String queryStr="select (select max(RPLY_DATE) FROM fpo_ADDL_QRY where item_id=a.item_id) addl_rly_dt,case when (select count(*) FROM fpo_ADDL_QRY where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as addl_rlystatus,(select max(RPLY_DATE) FROM fpo_query where item_id=a.item_id) rly_dt,case when (select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is not null)>0 then 'Reply Received' else 'Reply Not Received' end as rlystatus,recp_add1 || ' ' || recp_add2 || ' ' || RECP_CITY || ' ' || RECP_ZIP as recp_add1,speedpost_deli_status,to_char(speedpost_dt , 'dd/mm/yyyy') speedpost_dt,speedpost_no,case when 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') then 'OOC Not Given' else 'OOC Given' end as ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MI:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where  SPEEDPOST_NO is not null and a.speedpost_dt >= add_months(sysdate, -3) order by a.speedpost_dt  desc";
//			
//			
//			Query query = entityManager.createNativeQuery(queryStr,DcallHistory.class);
//			result = (List<DcallHistory>) query.getResultList();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}

	public List<DcallHistory3> getDcallHistoryspeedpostrecordStatus(String fromdate,String todate,HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<DcallHistory3> result=new ArrayList<DcallHistory3>();
		try {
			//String queryStr="select to_char(speedpost_dt , 'dd/mm/yyyy HH24:MM:ss') speedpost_dt,speedpost_no,'' ooc,case when ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) = 1 then 'First Query' else 'Additional Query' end AS qryname,a.ID,a.MOBILE_NO_1,a.MOBILE_NO_2,a.MAIL_CC,a.MAIL_TO,fqd.din1,a.CIN_NO,a.ITEM_ID,a.CUS_SITE,a.DCALL_NO,a.GENURL,a.RECP_NAME,to_char(a.GEN_DT , 'dd/mm/yyyy HH24:MM:ss') GEN_DT,substr(a.GEN_FILENAME,-31) GEN_FILENAME,a.GEN_FILENAME  full_path,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') as post_dt,dtc.CNTRY_NM as origin_country,pdm.CODEDESC as mail_class,pdi.CATEGORY as mail_category,a.VIEWCOU,a.PRINTCOU,a.SMSCOU,a.EMAILCOU from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) FROM fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID='"+request.getSession().getAttribute("offId")+"' and (1<=(select count(*) FROM fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) FROM fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null and SPEEDPOST_NO is not null order by a.GEN_DT  desc";
			String queryStr="     select  item_id, qryname, id, recp_name, recp_add1 ,dcall_no, mail_to, mail_cc, mobile_no_1, mobile_no_2, full_path, gen_filename,gen_dt, post_dt,dcall_dt, rly_dt, rlystatus , \r\n"
					+ "					                     mail_class ,speedpost_no, speedpost_dt , speedpost_deli_status  ,printcou,smscou,emailcou,cin_no, origin_country ,origin_country ooc from(\r\n"
					+ "										(select a.item_id item_id,'FIRST QUERY' qryname,c.id id,c.recp_name recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1 ,dcall_no,c.mail_to mail_to,c.mail_cc mail_cc,c.mobile_no_1 mobile_no_1,c.mobile_no_2 mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt,gen_dt post_dt,gen_dt dcall_dt ,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "					                    c.mail_to mail_class ,c.speedpost_no speedpost_no,to_char(c.speedpost_dt,'DD/MM/YYYY HH24:MI:SS') speedpost_dt ,c.speedpost_deli_status speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country from fpo_query a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and rply_date is null and a.item_id=c.item_id  and speedpost_no is not null  and item_no is null \r\n"
					+ "										and qry_no=(select max(qry_no) from fpo_query where item_id=a.item_id and item_no is null) and (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') and \r\n"
					+ "                                     c.id=(select min(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y'  ) ) \r\n"
					+ "										union\r\n"
					+ "									select a.item_id,'ADDITIONAL QUERY' qryname,c.id, c.recp_name,recp_add1||' '||recp_add2||' '||recp_city||' '||recp_state||' '||recp_zip AS recp_add1,dcall_no,c.mail_to mail_to ,\r\n"
					+ "					                    c.mail_cc,c.mobile_no_1,c.mobile_no_2,c.GEN_FILENAME full_path,substr(c.GEN_FILENAME,-31) gen_filename,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')gen_dt, gen_dt post_dt,gen_dt dcall_dt,gen_dt rly_dt,gen_dt rlystatus ,\r\n"
					+ "					                   c.mail_to mail_class ,c.speedpost_no speedpost_no,to_char(c.speedpost_dt,'DD/MM/YYYY HH24:MI:SS') speedpost_dt  ,c.speedpost_deli_status speedpost_deli_status  ,c.printcou,c.smscou,c.emailcou,a.cin_no,c.mail_to origin_country\r\n"
					+ "					                    from fpo_addl_qry a, fpo_main b, dcallqry_gen c where a.item_id=b.item_id and  rply_date is null and a.item_id=c.item_id \r\n"
					+ "					                    and speedpost_no is not null and qry_def_slno is null\r\n"
					+ "									and qry_id=(select max(qry_id) from fpo_query where item_id=a.item_id and qry_def_slno is null) and   (c.qry_reply is null or c.qry_reply='Y' or c.qry_reply='U') \r\n"
					+ "										and c.id=(select max(id) from dcallqry_gen where item_id=a.item_id and (c.qry_reply is null or c.qry_reply='Y' )\r\n"
					+ "                                        )))  \r\n"
					+ "                                         	where trunc(rly_dt) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy')   order by rly_dt desc";
			
			
			Query query = entityManager.createNativeQuery(queryStr,DcallHistory3.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			result = (List<DcallHistory3>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<SpeedPostNo> getspeedno() {
		// TODO Auto-generated method stub
		List<SpeedPostNo> result=new ArrayList<SpeedPostNo>();
		try {
			String queryStr="select SPEEDPOST_NO from DCALLQRY_GEN where (SPEEDPOST_DELI_STATUS is null or SPEEDPOST_DELI_STATUS = 'Not Delivered') and SPEEDPOST_NO is not null";
			
			
			Query query = entityManager.createNativeQuery(queryStr,SpeedPostNo.class);
			result = (List<SpeedPostNo>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ArticleArrInfo> getartarr_withead(String csval, String mapcode) {
		List<ArticleArrInfo> reportColumn=new ArrayList<ArticleArrInfo>();
		System.out.println("csval is "+ csval);
		System.out.println("mapcode is " + mapcode);
   /* 	String qry = "select ROW_NUMBER() OVER (ORDER BY b.POSTINGDT) id,a.article_id articleId,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
				+ "decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt,\r\n"
				+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY') recdeventDt, k.site_code recdfpo,\r\n"
				+ "to_char(recd_dt,'DD/MM/YYYY') recdDt,'' currentStatus, deci_nm eaddeci,recp_id recpid, bag_no bagno  from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join fpo_status o on b.item_id=o.item_id left join fpo_qry_deci h on b.item_id=h.item_id left join article_arr_info c \r\n"
				+ "on a.article_id=c.article_id left join fpo_curr_que g on (c.article_id=g.item_id) left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd where a.status is null and c.status is null \r\n"
				+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode or concat(SUBSTR(ooe, 1,5),5)=:mapcode and ooc_dt is null and a.status is null and c.status is null ";*/
		
  /* 	String qry = "select ROW_NUMBER() OVER (ORDER BY POSTINGDT) id,a.article_id articleId,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,h.deci_cd\r\n"
				+ "decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt,\r\n"
				+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY') recdeventDt, k.site_code recdfpo,\r\n"
				+ "to_char(recd_dt,'DD/MM/YYYY') recdDt,'-' currentStatus, decode(deci_nm,null,'-',deci_nm) eaddeci,recp_id recpid, bag_no bagno  from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join fpo_qry_deci h on b.item_id=h.item_id left join article_arr_info c \r\n"
				+ "on a.article_id=c.article_id left join fpo_curr_que g on (c.article_id=g.item_id)  left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) "
				+ "left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd where a.status is null and c.status is null \r\n"
				+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode  or concat(SUBSTR(ooe, 1,5),5)=:mapcode ) and decode(curr_que,null,b.init_que,curr_que) <>'P8'";*/

		/*String qry="select  articleId,articleDate,eadstatus,cinno,cindt,codeDesc,category,cntryCD,ooe,recdeventDt,recdfpo,recdDt,currentstatus,eaddeci,recpid,bagno from \r\n"
				+ " (select a.article_id articleId,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
				+ " decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, \r\n"
				+ " dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,decode(recd_event_dt,null,'-',to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS')) recdeventDt, k.site_code recdfpo, \r\n"
				+ " to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,'' currentStatus, deci_nm eaddeci,recp_id recpid, bag_no bagno  from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join fpo_qry_deci h on b.item_id=h.item_id left join article_arr_info c  \r\n"
				+ " on a.article_id=c.article_id left join fpo_curr_que g on (c.article_id=g.item_id)  left join fpo_status o on b.item_id=o.item_id left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) \r\n"
				+ " left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd where a.status is null and c.status is null  \r\n"
				+ " and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode  or concat(SUBSTR(ooe, 1,5),5)=:mapcode) and o.ooc_dt is null and b.item_id is not null  \r\n"
				+ " union  \r\n"
				+ " select a.article_id articleId,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,  \r\n"
				+ " decode(a.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),a.cin_no) cinno,decode(b.cin_dt,null,decode(a.cin_dt,null,'-',to_char(a.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt,  \r\n"
				+ " dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(ooe,null,'-',concat(SUBSTR(ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS') recdeventDt, k.site_code recdfpo,  \r\n"
				+ " '-' recdDt, recdDt,'' currentStatus, deci_nm eaddeci,recp_id recpid, '-'  bagno  from article_arr_info a left join fpo_main b on a.article_id=b.item_id left join fpo_qry_deci h on b.item_id=h.item_id  \r\n"
				+ " left join fpo_curr_que g on (b.item_id=g.item_id)  left join fpo_status o on b.item_id=o.item_id left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.ooe,1,5)  \r\n"
				+ " left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd  \r\n"
	            + " where a.status is null and c.status is null and   concat(SUBSTR(ooe, 1,5),5)=:mapcode and o.ooc_dt is null and b.item_id is not null)  where eadstatus='with_ead'  order by articleDate";
		*/
		
		
		String qry="select  articleId,articleDate,eadstatus,cinno,cindt,codeDesc,category,cntryCD,ooe,recdeventDt,recdfpo,recdDt,currentstatus,eaddeci,recpid,bagno from \r\n"
				+ " (select b.item_id articleId,to_char(to_date(substr(b.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,'with_ead' eadstatus,\r\n"
				+ " b.cin_no cinno,to_char(b.cin_dt,'DD/MM/YYYY HH24:MI:SS') cindt, \r\n"
				+ " dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,decode(recd_event_dt,null,'-',to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS')) recdeventDt, k.site_code recdfpo, \r\n"
				+ " to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,s.disp_name currentStatus, deci_nm eaddeci,recp_id recpid, bag_no bagno  from  fpo_main b left join  articlearr_fpo_info a  on b.item_id=a.article_id left join fpo_qry_deci h on b.item_id=h.item_id left join article_arr_info c  \r\n"
				+ " on b.item_id=c.article_id left join fpo_curr_que g on (c.article_id=g.item_id)  left join fpo_status o on b.item_id=o.item_id left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code) \r\n"
				+ " left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd left join (select item_id,qry_type from fpo_qry_deci a where id=(select max(id) from fpo_qry_deci where item_id=a.item_id )) qry  on b.item_id=qry.item_id left join fpo_stages s on decode(qry.qry_type,null,'E1',qry.qry_type)=s.stage_code where c.status is null   \r\n"
				+ " and (concat(SUBSTR(a.recd_fpo, 1,5),5) = map_code  or concat(SUBSTR(ooe, 1,5),5)=map_code) and k.site_code=:mapcode and o.ooc_dt is null and b.item_id is not null order by b.job_no)"
				+ " group by articleId,articleDate,eadstatus,cinno,cindt,codeDesc,category,cntryCD,ooe,recdeventDt,recdfpo,recdDt,currentstatus,eaddeci,recpid,bagno";
		
		
		
		
		
		try {
			Query query = entityManager.createNativeQuery(qry, ArticleArrInfo.class);
			query.setParameter("mapcode",mapcode);
			reportColumn = (List<ArticleArrInfo>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reportColumn;
	}
	
	
	public List<ArticleArrInfo> getartarr_withoutead(String mapcode) {
		List<ArticleArrInfo> reportColumn=new ArrayList<ArticleArrInfo>();
	String qry= "select a.article_id articleId,to_char(c.booking_dt,'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',c.cin_no),c.cin_no) cinno,\r\n"
			+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,c.origcntrycd cntrycd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe , decode(recp_id,null,'-',recp_id) recpid, to_char(recd_event_dt,'DD/MM/YYYY') recdeventDt,concat(SUBSTR(recd_fpo, 1,5),5) recdfpo,\r\n"
			+ "to_char(recd_dt,'DD/MM/YYYY') recdDt, bag_no bagno,f.category, '-' eaddeci, '-' currentStatus from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id left join fpo_delivery g on a.article_id=g.item_id\r\n"
			+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
			+ "on (c.mail_class=dir.code) left join ops$dir.pdi_nature_trans_codes f  on (b.nature_type_cd=f.code) left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(recd_fpo,1,5)  where  a.status is null and c.status is null and SUBSTR(a.recd_fpo, 1,5) = substr(:mapcode,1,5)  and b.item_id is null and g.item_id is null";
	try {
		Query query = entityManager.createNativeQuery(qry, ArticleArrInfo.class);
		query.setParameter("mapcode",mapcode);
		reportColumn = (List<ArticleArrInfo>) query.getResultList();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return reportColumn;
	}
	
	

	public List<ArticleArrInfo> getviewsinglercd(String csval, String itmid, String mapcode) {
		String qry = "select a.article_id articleId,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') articleDate,decode(b.item_id,null,'without_ead','with_ead') eadstatus,\r\n"
				+ "decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt,\r\n"
				+ "dir.codedesc codeDesc,f.category category,b.send_cntry_cd cntryCD,decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY') recdeventDt, k.site_code recdfpo,\r\n"
				+ "to_char(recd_dt,'DD/MM/YYYY') recdDt,'' currentStatus,deci_nm eaddeci, recp_id recpid, bag_no bagno from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id  left join fpo_qry_deci h on b.item_id=h.item_id  left join article_arr_info c \r\n"
				+ "on a.article_id=c.article_id left join ops$dir.pdi_mail_class_codes dir on (b.mail_class_cd=dir.code)  left join ops$dir.fpo_sites k on substr(map_code,1,5)=substr(a.recd_fpo,1,5) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) left join deci_reas n on h.deci_cd=n.deci_cd where a.status is null and c.status is null \r\n"
				+ "and (concat(SUBSTR(a.recd_fpo, 1,5),5) = :mapcode or concat(SUBSTR(ooe, 1,5),5)= :mapcode) and a.article_id = b.item_id and a.article_id = :itmid ";
		try {
			Query query = entityManager.createNativeQuery(qry, ArticleArrInfo.class);
			query.setParameter("mapcode",mapcode);
			query.setParameter("itmid", itmid);
			return (List<ArticleArrInfo>)  query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/*public List<grandtotinfo> getgrandtot(String cinno) {
		String qry =" select x.cin_no, assval, bcd, igst,sws,oth,dutysum,totduty,nvl(tot_fine,0) fine,nvl(tot_penal,0) penalty,tot_amt_payable totpay from (select a.cin_no,sum(assval_insfrt) assval,sum(bcd_amt) bcd,sum(igst_amt) igst,sum(sw_amt) sws,sum(duty) dutysum,sum(nvl(oth_duty,0)) oth FROM fpo_item_det a \r\n"
				+ " left join (select sum(duty_amt) oth_duty,cin_no,item_no FROM fpo_item_det_othduty\r\n"
				+ " where cin_no='" + cinno + "' group by cin_no,item_no) b  on a.cin_no=b.cin_no  and a.item_no=b.item_no group by a.cin_no ) x, (select tot_duty totduty,tot_amt_payable,tot_fine,tot_penal,cin_no FROM fpo_main where CIN_NO = '" + cinno + "') y \r\n"
				+ " where x.cin_no=y.cin_no";
		try {
			return entityManager.createNativeQuery(qry, grandtotinfo.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}*/

	public List<MisReportColumns> getMisReportColumns(String fromdate, String enddate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportColumns> reportColumn = new ArrayList<MisReportColumns>();
		int st = 0;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr = "select a.cus_site as cussite,nvl(fio.DUTY_AMT,0) other_duty,fi.bcd_amt,fi.igst_amt,fi.sw_amt,ROW_NUMBER() OVER (ORDER BY b.ooc_dt) id,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL totassval,TOT_DUTY totduty,TOT_DUTY_FG dutyfg,TOT_AMT_PAYABLE dutycharged,to_char(ooc_dt , 'dd/mm/yyyy') oocdt,to_char(DELVY_DT , 'dd/mm/yyyy')  deldt,a.commercial,fmc.be_no  FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_manual_commercial fmc  on (a.item_id=fmc.item_id)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select a.cus_site as cussite,nvl(fio.DUTY_AMT,0) other_duty,fi.bcd_amt,fi.igst_amt,fi.sw_amt,ROW_NUMBER() OVER (ORDER BY b.ooc_dt) id,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL totassval,TOT_DUTY totduty,TOT_DUTY_FG dutyfg,TOT_AMT_PAYABLE dutycharged,to_char(ooc_dt , 'dd/mm/yyyy') oocdt,to_char(DELVY_DT , 'dd/mm/yyyy')  deldt ,a.commercial,fmc.be_no  FROM fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join fpo_manual_commercial fmc  on (a.item_id=fmc.item_id)   left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where (trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) ";
				st = 1;
				
				System.out.println("querystr is " + queryStr); 
				  // writing code for sitecode looping
				  
				  queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr
							+ ")";
				
				System.out.println("querystr is " + queryStr);
				}
			queryStr=queryStr+"and(";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			queryStr = queryStr
					+ ")order by b.ooc_dt";
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportColumns.class);
			if (st == 0)
			query.setParameter("cuSite", cuSite);
			
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if (st == 1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", enddate);
			System.out.println("querystr is " + queryStr);
			reportColumn = (List<MisReportColumns>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportOocPending> getMisReportOocPending(String fromdate, String enddate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportOocPending> reportColumn = new ArrayList<MisReportOocPending>();
		int st=0; String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
		//	String queryStr = "select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.status pendingqueue,a.ROLE pendingrole,a.OFF_ID pendingofficer,ef.EMP_NAME officername FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_CURR_QUE cq on (a.item_id=cq.item_id) left join fpo_TRACK_CODES ftc on (ftc.code=cq.CURR_QUE) left join ops$dir.d_emp ef on (a.off_id=ef.USER_ID) where ooc_dt is null and a.cus_site=:cuSite and  to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  BETWEEN :fromdate and :enddate  ";
			/* String queryStr="select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,  \r\n"
						+ " dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.disp_name pendingqueue,  \r\n"
						+ " decode(qry.ROLE,null,'PAO',qry.ROLE) pendingrole,decode(qry.OFF_ID,null,er.user_id,qry.off_id) pendingofficer,decode(ef.EMP_NAME,null,ep.emp_name,ef.emp_name) officername FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on  \r\n"
						+ " (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join  \r\n"
						+ " fpo_CURR_QUE cq on (a.item_id=cq.item_id)  left join (select item_id,qry_type,off_id,role from fpo_qry_deci a where id=(select max(id) from fpo_qry_deci where item_id=a.item_id )) qry  on a.item_id=qry.item_id left join fpo_stages ftc on decode(qry.qry_type,null,'E1',qry.qry_type)=ftc.stage_code  \r\n"
						+ " left join ops$dir.d_emp ef on (qry.off_id=ef.USER_ID) , ops$dir.d_emp_roles er, ops$dir.d_emp ep where ooc_dt is null and a.cus_site=:cuSite and (instr(er.mail_class_cd,a.mail_class_cd) > 0 and  decode(qry.ROLE,null,'PAO',qry.ROLE)=er.role_name and er.cus_site=a.cus_site)  and er.user_id=ep.user_id  \r\n"
						+ " order by a.POSTINGDT";*/
			String queryStr="select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,  \r\n"
					+ " dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.disp_name pendingqueue,  \r\n"
					+ " decode(qry.ROLE,null,'PAO',qry.ROLE) pendingrole,decode(qry.OFF_ID,null,er.user_id,qry.off_id) pendingofficer,decode(ef.EMP_NAME,null,ep.emp_name,ef.emp_name) officername FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on  \r\n"
					+ " (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join  \r\n"
					+ " fpo_CURR_QUE cq on (a.item_id=cq.item_id)  left join (select item_id,qry_type,off_id,role from fpo_qry_deci a where id=(select max(id) from fpo_qry_deci where item_id=a.item_id )) qry  on a.item_id=qry.item_id left join fpo_stages ftc on decode(qry.qry_type,null,'E1',qry.qry_type)=ftc.stage_code  \r\n"
					+ " left join ops$dir.d_emp ef on (qry.off_id=ef.USER_ID) , ops$dir.d_emp_roles er, ops$dir.d_emp ep where ooc_dt is null and a.cus_site=:cuSite and (instr(er.mail_class_cd,a.mail_class_cd) > 0 and  decode(qry.ROLE,null,'PAO',qry.ROLE)=er.role_name and er.cus_site=a.cus_site)  and er.user_id=ep.user_id   and  to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  BETWEEN :fromdate and :enddate \r\n";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			/*	queryStr = "select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,\r\n"
						+ "a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') \r\n"
						+ "post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) \r\n"
						+ "FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.status pendingqueue,a.ROLE pendingrole,a.OFF_ID pendingofficer,ef.EMP_NAME officername\r\n"
						+ "\r\n"
						+ "FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) \r\n"
						+ "left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) \r\n"
						+ "left join fpo_CURR_QUE cq on (a.item_id=cq.item_id) left join fpo_TRACK_CODES ftc on (ftc.code=cq.CURR_QUE) left join ops$dir.d_emp ef on (a.off_id=ef.USER_ID)\r\n"
						+ "where ooc_dt is null and  to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  BETWEEN :fromdate and :enddate  ";	*/
				/* queryStr="select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,  \r\n"
						+ " dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.disp_name pendingqueue,  \r\n"
						+ " decode(qry.ROLE,null,'PAO',qry.ROLE) pendingrole,decode(qry.OFF_ID,null,er.user_id,qry.off_id) pendingofficer,decode(ef.EMP_NAME,null,ep.emp_name,ef.emp_name) officername FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on  \r\n"
						+ " (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join  \r\n"
						+ " fpo_CURR_QUE cq on (a.item_id=cq.item_id)  left join (select item_id,qry_type,off_id,role from fpo_qry_deci a where id=(select max(id) from fpo_qry_deci where item_id=a.item_id )) qry  on a.item_id=qry.item_id left join fpo_stages ftc on decode(qry.qry_type,null,'E1',qry.qry_type)=ftc.stage_code  \r\n"
						+ " left join ops$dir.d_emp ef on (qry.off_id=ef.USER_ID) , ops$dir.d_emp_roles er, ops$dir.d_emp ep where ooc_dt is null  and (instr(er.mail_class_cd,a.mail_class_cd) > 0 and  decode(qry.ROLE,null,'PAO',qry.ROLE)=er.role_name and er.cus_site=a.cus_site)  and er.user_id=ep.user_id  \r\n"
						+ " order by a.POSTINGDT";*/
				queryStr = "select ROW_NUMBER() OVER (ORDER BY a.POSTINGDT) id,\r\n"
						+ "a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy') \r\n"
						+ "post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) \r\n"
						+ "FROM fpo_item_det where item_id=a.item_id) noofitem,ftc.disp_name pendingqueue,decode(qry.ROLE,null,'PAO',qry.ROLE) pendingrole,decode(qry.OFF_ID,null,er.user_id,qry.off_id) pendingofficer,decode(ef.EMP_NAME,null,ep.emp_name,ef.emp_name)  officername\r\n"
						+ "\r\n"
						+ "FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) \r\n"
						+ "left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) \r\n"
						+ "left join fpo_CURR_QUE cq on (a.item_id=cq.item_id) left join (select item_id,qry_type,off_id,role from fpo_qry_deci a where id=(select max(id) from fpo_qry_deci where item_id=a.item_id )) qry  on a.item_id=qry.item_id left join fpo_stages ftc on decode(qry.qry_type,null,'E1',qry.qry_type)=ftc.stage_code  \r\n" 
						+ "left join ops$dir.d_emp ef on (qry.off_id=ef.USER_ID) , ops$dir.d_emp_roles er, ops$dir.d_emp ep where ooc_dt is null and (instr(er.mail_class_cd,a.mail_class_cd) > 0 and  decode(qry.ROLE,null,'PAO',qry.ROLE)=er.role_name and er.cus_site=a.cus_site)  and er.user_id=ep.user_id   and  to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  BETWEEN :fromdate and :enddate \r\n"; 
						
				st = 1;
			
				  queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 
					 else {
					  queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  }
			
				  queryStr = queryStr
							+ ")";
				
			}
			 queryStr=queryStr+" and ( ";
			
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			
			  queryStr = queryStr
						+ ") order by a.POSTINGDT";
			 
			System.out.println("querystr is " + queryStr);
			
		
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocPending.class);
				if (st == 0)
				query.setParameter("cuSite", cuSite);
			
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if(st==1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("enddate", enddate);
			System.out.println("successfully querry written");
			reportColumn = (List<MisReportOocPending>) query.getResultList();
			System.out.println("successfully got result");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportWithoutPincode> getMisReportWithoutPincode(String from,String todate, String[] mailclass, int cou,String[] siteco,int sitco,HttpSession session, HttpServletRequest request) {
		List<MisReportWithoutPincode> reportColumn=new ArrayList<MisReportWithoutPincode>();
		int st=0;
		String mc0=null, mc1=null,mc2=null,mc3=null;
		String sc0=null,sc1=null; //,sc2=null,sc3=null,sc4=null,sc5=null,sc6=null,sc7=null,sc8=null,sc9=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
		//	String queryStr="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.cus_site=:cuSite order by b.ALLOT_DT";
		//	String queryStr="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.cus_site=:cuSite and trunc(b.ALLOT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') order by b.ALLOT_DT";
			
			String queryStr ="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.cus_site=:cuSite and trunc(b.ALLOT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and (";
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				//queryStr="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as  cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) order by b.ALLOT_DT";
				//queryStr="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as  cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where trunc(b.ALLOT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') order by b.ALLOT_DT";
				
				queryStr ="select ROW_NUMBER() OVER (ORDER BY b.ALLOT_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,to_char(b.ALLOT_DT,'DD/MM/YYYY') mappeddt,'' status FROM fpo_main a join fpo_map_site b on (a.item_id=b.ARTICLE_ID) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where trunc(b.ALLOT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and (";
				st=1;
			
				for (int i=0 ; i <sitco ; i++)
				{
					if(i==0) {
						sc0=siteco[i];
						queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					else {
					queryStr = queryStr + "or trim(a.cus_site) = trim(:sc" + i + ")";
					}
				
				}
				queryStr = queryStr + ") and (";
			}
			for (int i=0 ; i <cou ; i++)
			{
				if (i==0)
				{
					mc0=mailclass[0];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0)";
				}
				else if (i ==1 ) {
					mc1=mailclass[1];
					queryStr = queryStr + "or  trim(pdm.CODEDESC) = trim(:mc1)";
				}
				else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				}
				else if (i ==3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			
			queryStr = queryStr + " )order by b.ALLOT_DT";
			
		System.out.println(queryStr);	
		
			Query query = entityManager.createNativeQuery(queryStr, MisReportWithoutPincode.class);
			if (st==0) 
			   query.setParameter("cuSite", cuSite);
		
			if (st==1) 
					for (int i=0; i<sitco; i++) {
						if(i ==0) {
							query.setParameter("sc0", sc0);
							}else {
								sc1=siteco[i];
								query.setParameter("sc"+ i, sc1);
								}
							}  
						     
			   
			for (int i=0 ; i < cou ; i++)
			{
				if(i==0)
					query.setParameter("mc0", mc0);
				else if (i==1)
					query.setParameter("mc1", mc1);
				else if (i==2)
					query.setParameter("mc2", mc2);
				else if (i==3)
					query.setParameter("mc3", mc3);
				
				
			}
			
			query.setParameter("fromdate",from);
			query.setParameter("todate", todate);
			
			reportColumn = (List<MisReportWithoutPincode>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	} 

	public List<MisReportQryRaised> getMisReportQryRaised(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportQryRaised> reportColumn=new ArrayList<MisReportQryRaised>();
		int st=0;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();	
			System.out.println("mailclass is " + mailclass);
		//	String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where a.cus_site=:cuSite and b.QRY is null ORDER BY c.GEN_DT";
				
			String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM \r\n"
					+ "as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,\r\n"
					+ "SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,\r\n"
					+ "SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) \r\n"
					+ "join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm \r\n"
					+ "on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where a.cus_site=:cuSite and b.QRY is null and trunc(c.GEN_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";
			
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
		//		queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.QRY is null ORDER BY c.GEN_DT";
          
				queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM\r\n"
						+ "as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) \r\n"
						+ "As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,\r\n"
						+ "decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) \r\n"
						+ "left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) \r\n"
						+ "left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.QRY is null and trunc(c.GEN_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";
				
				st=1;				
			
				 queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 
					 else {
					        queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					    }
				  
				  }
				  queryStr = queryStr
							+ ")";
				 
				System.out.println("querystr is " + queryStr);
				
				
			}
				
				queryStr=queryStr+" and ( ";
			for (int i= 0 ; i<cou ; i++)
			{
			  if (i==0)
			  {
				  mc0=mailclass[i]; 
				  queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0) ";
			  }
			  else if (i==1)
			  {
				  mc1=mailclass[1]; 
				  queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)"; 
			  }
			  else if (i==2)
			  {
				  mc2=mailclass[2]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)"; 
			  }			  
			  else if (i==3)
			  {
				  mc3=mailclass[3]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)"; 
			  }			  
			}
			queryStr = queryStr + " )   ORDER BY c.GEN_DT";
			System.out.println("querystr is " + queryStr);

			Query query = entityManager.createNativeQuery(queryStr, MisReportQryRaised.class);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			
			for (int i= 0 ; i<cou ; i++)
			{
				if (i==0)
					query.setParameter("mc0", mc0);
				else if (i==1)
					query.setParameter("mc1", mc1);
				else if (i==2)
					query.setParameter("mc2", mc2);
				else if (i==3)
					query.setParameter("mc3", mc3);
			}
			if(st==1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportQryRaised>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportQryRaised> getMisAddlReportQryRaised(String fromdate, String todate,HttpSession session,String[] mailclass, int cou,String[] fposite,int sitecou, HttpServletRequest request) {
		List<MisReportQryRaised> reportColumn=new ArrayList<MisReportQryRaised>();
		int st=0;
		String sc0=null;
		String sc1=null;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			
		//	String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_ADDL_QRY b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where a.cus_site=:cuSite and b.QRY_DEF_SLNO is null ORDER BY c.GEN_DT";

			String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,\r\n"
					+ "dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,\r\n"
					+ "TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,\r\n"
					+ "c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_ADDL_QRY b \r\n"
					+ "on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) \r\n"
					+ "left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) \r\n"
					+ "where a.cus_site=:cuSite and b.QRY_DEF_SLNO is null and trunc(c.GEN_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and (";
			
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			//	queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_ADDL_QRY b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where b.QRY_DEF_SLNO is null ORDER BY c.GEN_DT";
			
				queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  \r\n"
						+ "post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) \r\n"
						+ "noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,\r\n"
						+ "b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  \r\n"
						+ "FROM fpo_main a join fpo_ADDL_QRY b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi \r\n"
						+ "on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc \r\n"
						+ "on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where b.QRY_DEF_SLNO is null and trunc(c.GEN_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and (";
				
				st=1;
			
				for(int i=0;i<sitecou;i++) { 
					  
					 if(i==0) {
					  sc0=fposite[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					        queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					    }
				  }
				
				queryStr=queryStr+") and (";
				
				
			}
			for (int i= 0 ; i<cou ; i++)
			{
			  if (i==0)
			  {
				  mc0=mailclass[i]; 
				  queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0) ";
			  }
			  else if (i==1)
			  {
				  mc1=mailclass[1]; 
				  queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)"; 
			  }
			  else if (i==2)
			  {
				  mc2=mailclass[2]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)"; 
			  }			  
			  else if (i==3)
			  {
				  mc3=mailclass[3]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)"; 
			  }			  
			}
			queryStr = queryStr + " ) ORDER BY c.GEN_DT";
			Query query = entityManager.createNativeQuery(queryStr, MisReportQryRaised.class);
			if (st==0)
				query.setParameter("cuSite", cuSite);
			if(st==1)
				for(int i=0;i<sitecou;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=fposite[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
				
				
			for (int i= 0 ; i<cou ; i++)
				{
					if (i==0)
						query.setParameter("mc0", mc0);
					else if (i==1)
						query.setParameter("mc1", mc1);
					else if (i==2)
						query.setParameter("mc2", mc2);
					else if (i==3)
						query.setParameter("mc3", mc3);
				}
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportQryRaised>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportByMailClass> getMisReportByMailClass(String fromdate, String todate, HttpSession session, String[] mailclass,int sitecou,String[] fposite,int cou, HttpServletRequest request) {
		List<MisReportByMailClass> reportColumn=new ArrayList<MisReportByMailClass>();
		int st=0;
		String sc0=null;	
		String sc1=null;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();		
			System.out.println("mailclass is " + mailclass);
			String queryStr="select ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,a.cus_site as cussite,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL from  fpo_main a  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where to_date((substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4)),'DD/MM/YYYY') between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite and ( ";
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				{	queryStr="select ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,a.cus_site as cussite,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL from  fpo_main a  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where to_date((substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4)),'DD/MM/YYYY') between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and ( ";
			System.out.println("in if statement");
				}
				st=1;
				for(int i=0;i< cou ;i++) { 	
				  	
					 if(i==0) {	
					  sc0=fposite[i];	
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";	
					 }	
					 else {	
					        queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";	
					    }	
				  }	
					
				queryStr=queryStr+") and (";	
			}
			
			for (int i= 0 ; i< sitecou ; i++)
			{
			  if (i==0)
			  {
				  mc0=mailclass[i]; 
				  queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0) ";
			  }
			  else if (i==1)
			  {
				  mc1=mailclass[1]; 
				  queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)"; 
			  }
			  else if (i==2)
			  {
				  mc2=mailclass[2]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)"; 
			  }			  
			  else if (i==3)
			  {
				  mc3=mailclass[3]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)"; 
			  }			  
			}
			queryStr = queryStr + " ) group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC ORDER BY sum(a.TOT_ASS_VAL) desc";
			System.out.println("querystr is " + queryStr);
		
			Query query = entityManager.createNativeQuery(queryStr, MisReportByMailClass.class);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1)	
				for(int i=0;i< cou;i++) { 	
					 if(i==0) {	
					 query.setParameter("sc0", sc0);	
					 }	
					 else {	
					        sc1=fposite[i];	
					        query.setParameter("sitevalue" + i , sc1);	
					    }	
				  }
			for (int i= 0 ; i<sitecou ; i++)
			{
				if (i==0)
					query.setParameter("mc0", mc0);
				else if (i==1)
					query.setParameter("mc1", mc1);
				else if (i==2)
					query.setParameter("mc2", mc2);
				else if (i==3)
					query.setParameter("mc3", mc3);
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);

			reportColumn = (List<MisReportByMailClass>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportByItemCategory> getMisReportByItemCategory(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou, String[] itemcat, int couic,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportByItemCategory> reportColumn = new ArrayList<MisReportByItemCategory>();
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr = "select ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,a.cus_site as cussite,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,pdi.CATEGORY as item_category from  fpo_main a  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) where to_date((substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4)),'DD/MM/YYYY')  between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite  ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,a.cus_site as cussite,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,pdi.CATEGORY as item_category from  fpo_main a  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) where to_date((substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4)),'DD/MM/YYYY') between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				st = 1;
  queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + ")";
				
				System.out.println("querystr is " + queryStr);
				}
			  queryStr=queryStr+" and(";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}

			queryStr = queryStr + " ) and  (  ";

			for (int i = 0; i < couic; i++) {
				if (i == 0) {
					it0 = itemcat[i];
					queryStr = queryStr + " trim(pdi.CATEGORY) =  trim(:it0)";
				} else if (i == 1) {
					it1 = itemcat[1];
					queryStr = queryStr + " or  trim(pdi.CATEGORY)=  trim(:it1)";
				} else if (i == 2) {
					it2 = itemcat[2];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it2)";
				} else if (i == 3) {
					it3 = itemcat[3];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it3)";
				} else if (i == 4) {
					it4 = itemcat[4];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it4)";
				}

				else if (i == 5) {
					it5 = itemcat[5];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it5)";
				}

			}

			queryStr = queryStr
					+ " )  group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC,pdi.CATEGORY ORDER BY sum(a.TOT_ASS_VAL) desc";

			System.out.println("querystr=" + queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportByItemCategory.class);

			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			for (int i = 0; i < couic; i++) {
				if (i == 0)
					query.setParameter("it0", it0);
				else if (i == 1)
					query.setParameter("it1", it1);
				else if (i == 2)
					query.setParameter("it2", it2);
				else if (i == 3)
					query.setParameter("it3", it3);
				else if (i == 4)
					query.setParameter("it4", it4);
				else if (i == 5)
					query.setParameter("it5", it5);
			}
			if (st==0)
				query.setParameter("cuSite", cuSite);
			
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			/* query.setParameter("cuSite", cuSite); */
			// query.setParameter("mailclass", mailclass);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			// query.setParameter("itemcat", itemcat);
			reportColumn = (List<MisReportByItemCategory>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	
	
	//for 21st report
	public List<MisReportEAD> getMisReportEAD(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou, String[] itemcat, int couic,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportEAD> reportColumn = new ArrayList<MisReportEAD>();
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr ="select a.cin_dt,a.cus_site as cussite,nvl(fio.DUTY_AMT,0) other_duty,fi.bcd_amt,fi.igst_amt,fi.sw_amt,ROW_NUMBER()\r\n"
					+ "OVER (ORDER BY b.ooc_dt) id,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM\r\n"
					+ "as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL totassval,\r\n"
					+ "TOT_DUTY totduty,TOT_DUTY_FG dutyfg,TOT_AMT_PAYABLE dutycharged,to_char(ooc_dt , 'dd/mm/yyyy') oocdt,to_char(DELVY_DT , 'dd/mm/yyyy')  deldt, a.commercial FROM fpo_main a left join \r\n"
					+ "fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm \r\n"
					+ "on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,\r\n"
					+ "sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on \r\n"
					+ "(a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) and a.cus_site=:cuSite ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr="select a.cin_dt,a.cus_site as cussite,nvl(fio.DUTY_AMT,0) other_duty,fi.bcd_amt,fi.igst_amt,fi.sw_amt,ROW_NUMBER() OVER (ORDER BY b.ooc_dt) id,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL totassval,TOT_DUTY totduty,TOT_DUTY_FG dutyfg,TOT_AMT_PAYABLE dutycharged,to_char(ooc_dt , 'dd/mm/yyyy') oocdt,to_char(DELVY_DT , 'dd/mm/yyyy')  deldt, a.commercial FROM fpo_main a left join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) ";
				st = 1;
				
				queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) ";
				
				System.out.println("querystr is " + queryStr);
				}
			  queryStr=queryStr+" and(";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}

			queryStr = queryStr + " ) and  (  ";

			for (int i = 0; i < couic; i++) {
				if (i == 0) {
					it0 = itemcat[i];
					queryStr = queryStr + " trim(pdi.CATEGORY) =  trim(:it0)";
				} else if (i == 1) {
					it1 = itemcat[1];
					queryStr = queryStr + " or  trim(pdi.CATEGORY)=  trim(:it1)";
				} else if (i == 2) {
					it2 = itemcat[2];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it2)";
				} else if (i == 3) {
					it3 = itemcat[3];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it3)";
				} else if (i == 4) {
					it4 = itemcat[4];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it4)";
				}

				else if (i == 5) {
					it5 = itemcat[5];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it5)";
				}

			}

			queryStr = queryStr
					+ " )  ";

			System.out.println("querystr=" + queryStr);
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportEAD.class);

			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			for (int i = 0; i < couic; i++) {
				if (i == 0)
					query.setParameter("it0", it0);
				else if (i == 1)
					query.setParameter("it1", it1);
				else if (i == 2)
					query.setParameter("it2", it2);
				else if (i == 3)
					query.setParameter("it3", it3);
				else if (i == 4)
					query.setParameter("it4", it4);
				else if (i == 5)
					query.setParameter("it5", it5);
			}
			if (st==0)
				query.setParameter("cuSite", cuSite);
			
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			
	
			reportColumn = (List<MisReportEAD>)query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	
	public List<MisReportOocGivenCountriesNotfWise> getMisReportOocGivenCountriesNotfWisewithoutfilter(String fromdate, String todate,
			HttpSession session, String[] sitevalue, int cou2,HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("role").toString());	
		List<MisReportOocGivenCountriesNotfWise> reportColumn=new ArrayList<MisReportOocGivenCountriesNotfWise>();
		int st=0;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();						
			String queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,bcd_notn as notn,\r\n"
					+ "bcd_nsno as slno,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite group by a.cus_site, dtc.CNTRY_NM, a.SEND_CNTRY_CD, bcd_notn, bcd_nsno \r\n"
					+ "order by sum(a.TOT_DUTY) desc  ";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))
			{
				queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,bcd_notn as notn,\r\n"
						+ "bcd_nsno as slno,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				st=1;
				queryStr=queryStr+" and (";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+" (a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) group by a.cus_site, dtc.CNTRY_NM, a.SEND_CNTRY_CD, bcd_notn, bcd_nsno \r\n"
				  		+ "order by sum(a.TOT_DUTY) desc ";
				
				System.out.println("querystr is " + queryStr);
				}
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountriesNotfWise.class);
			
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportOocGivenCountriesNotfWise>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	
	
	
	
	
	
	

	public List<MisReportOocGivenCountriesNotfWise>getMisReportOocGivenCountriesNotfWise(String fromdate, String todate,
			HttpSession session, String[] sitevalue, int cou2,HttpServletRequest request,String[]notn,int cou_notn,String[]slno,int cou_slno) {
		System.out.println(request.getSession().getAttribute("role").toString());	
		List<MisReportOocGivenCountriesNotfWise> reportColumn=new ArrayList<MisReportOocGivenCountriesNotfWise>();
		int st=0;
		String sc0=null;
		String sc1=null;
		String notntemp=null;
		String slnotemp=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();						
			String queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,  bcd_notn as notn,\r\n"
					+ "   bcd_nsno as slno,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite ";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,  bcd_notn as notn,\r\n"
						+ "  bcd_nsno as slno,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				st=1;
				queryStr=queryStr+" and (";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+" (a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) " ;
				
				
			}
			
				if(cou_notn!=0) {
						queryStr = queryStr + " and (  ( fi.bcd_notn =:notn ) ";
			
					if(cou_notn>1) {
						for(int i=1;i<cou_notn;i++) {
					
				queryStr = queryStr + " or  ( fi.bcd_notn =:notn" + i +" )";
						}
					}
			
			}
				queryStr = queryStr + ")";
				if(cou_slno!=0) {
					queryStr = queryStr + " and  ((fi.bcd_nsno=:slno) ";
				
					if(cou_slno>1) {
						for(int i=1;i<cou_slno;i++) {
						
							queryStr = queryStr + " or  ( fi.bcd_nsno=:slno" + i +" )";
						}
					}
				
			}
				queryStr = queryStr + ")";
			 
			
			
			queryStr = queryStr + " group by a.cus_site, dtc.CNTRY_NM, a.SEND_CNTRY_CD, bcd_notn, bcd_nsno \r\n"
					+ "order by sum(a.TOT_DUTY) desc  ";
			
			System.out.println("querystr is " + queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountriesNotfWise.class);
			
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			
			if(cou_notn!=0) {
				query.setParameter("notn", notn[0]) ;
				System.out.println("notn values are "+notn[0]);
				if(cou_notn>1) {
					for(int i=1;i<cou_notn;i++) {
						System.out.println("notn other values are "+notn[i]);
						notntemp=notn[i];
						query.setParameter("notn" + i , notntemp);
					}
				}
			
		}
			
			if(cou_slno!=0) {
				query.setParameter("slno", slno[0]) ;
				System.out.println("slno values are "+slno[0]);
				if(cou_slno>1) {
					for(int i=1;i<cou_slno;i++) {
						slnotemp=slno[i];
						System.out.println("slno other values are "+slnotemp);
						query.setParameter("slno" + i , slnotemp);
					}
				}
			
		}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			
			reportColumn = (List<MisReportOocGivenCountriesNotfWise>) query.getResultList();
			System.out.println(reportColumn);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	


public List<String> getNotncategory(String fromdate,String todate) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct bcd_notn from fpo_item_det where Cin_dt between to_date (:fromdate, 'dd/MM/yyyy') \r\n"
					+ "AND TO_DATE (:todate, 'dd/MM/yyyy') and bcd_notn is not null  ";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}


	
	
	
	
	
	
	public List<MisReportOocGivenCountries> getMisReportOocGivenCountries(String fromdate, String todate,
			HttpSession session, String[] sitevalue, int cou2,HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("role").toString());	
		List<MisReportOocGivenCountries> reportColumn=new ArrayList<MisReportOocGivenCountries>();
		int st=0;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();						
			String queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite group by dtc.CNTRY_NM ,a.SEND_CNTRY_CD,a.cus_site order by sum(a.TOT_DUTY) desc ";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr="select a.cus_site as cussite,nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,'' as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from  fpo_main a join fpo_status b on (a.item_id = b.item_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				st=1;
				queryStr=queryStr+" and (";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+" (a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) group by dtc.CNTRY_NM ,a.SEND_CNTRY_CD,a.cus_site order by sum(a.TOT_DUTY) desc";
				
				System.out.println("querystr is " + queryStr);
				}
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountries.class);
			
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportOocGivenCountries>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportOocGivenCountriesdelstatus> getMisReportOocGivenCountriesdelistats(String fromdate, String todate,
			HttpSession session, String[] sitevalue, int cou2,HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("role").toString());	
		List<MisReportOocGivenCountriesdelstatus> reportColumn=new ArrayList<MisReportOocGivenCountriesdelstatus>();
		int st=0;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();	
			
			String queryStr=" SELECT a.cus_site AS cussite,\r\n"
					+ "        NVL(SUM(DUTY_AMT), 0) AS other_duty, SUM(BCD_AMT) AS BCD_AMT,SUM(IGST_AMT) AS IGST_AMT,\r\n"
					+ "        SUM(SW_AMT) AS SW_AMT,\r\n"
					+ "        ROW_NUMBER() OVER (ORDER BY SUM(a.TOT_DUTY) DESC) AS id,dtc.CNTRY_NM AS coo,a.SEND_CNTRY_CD,\r\n"
					+ "        COUNT(a.item_id) AS tot_article,\r\n"
					+ "        '' AS mail_class, SUM(a.TOT_ASS_VAL) AS TOT_ASS_VAL,SUM(TOT_DUTY_FG) AS TOT_DUTY_FG,SUM(TOT_DUTY) AS TOT_DUTY,SUM(TOT_AMT_PAYABLE) AS TOT_AMT_PAYABLE,\r\n"
					+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN 1 ELSE 0 END) AS Delivered,\r\n"
					+ "        SUM(CASE WHEN fd.deli_status <> 'DL' THEN 1 ELSE 0 END) AS NotDelivered, \r\n"
					+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN TOT_DUTY ELSE 0 END) AS Delivered_duty_amt,\r\n"
					+ "         SUM(CASE WHEN fd.deli_status <> 'DL' THEN TOT_DUTY ELSE 0 END) AS NotDelivered_duty_amt\r\n"
					+ "        \r\n"
					+ "    FROM\r\n"
					+ "        fpo_main a JOIN fpo_status b ON (a.item_id = b.item_id)LEFT JOIN OPS$DIR.d_cntry_cd dtc ON (a.SEND_CNTRY_CD = dtc.CNTRY_CD) LEFT JOIN fpo_ITEM_DET fi ON (a.item_id = fi.item_id)\r\n"
					+ "        LEFT JOIN fpo_ITEM_DET_OTHDUTY fio ON (a.item_id = fio.item_id) JOIN fpo_delivery fd ON (a.item_id = fd.item_id)\r\n"
					+ "    WHERE\r\n"
					+ "        TRUNC(b.ooc_dt) BETWEEN TO_DATE(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy')\r\n"
					+ "        AND a.cus_site = :cuSite\r\n"
					+ "    GROUP BY\r\n"
					+ "        dtc.CNTRY_NM,a.SEND_CNTRY_CD,a.cus_site\r\n"
					+ "    ORDER BY\r\n"
					+ "        SUM(a.TOT_DUTY) DESC";
					
			
if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))
 {
				
	queryStr="SELECT a.cus_site AS cussite,\r\n"
			+ "        NVL(SUM(DUTY_AMT), 0) AS other_duty, SUM(BCD_AMT) AS BCD_AMT,SUM(IGST_AMT) AS IGST_AMT,\r\n"
			+ "        SUM(SW_AMT) AS SW_AMT,\r\n"
			+ "        ROW_NUMBER() OVER (ORDER BY SUM(a.TOT_DUTY) DESC) AS id,dtc.CNTRY_NM AS coo,a.SEND_CNTRY_CD,\r\n"
			+ "        COUNT(a.item_id) AS tot_article,\r\n"
			+ "        '' AS mail_class, SUM(a.TOT_ASS_VAL) AS TOT_ASS_VAL,SUM(TOT_DUTY_FG) AS TOT_DUTY_FG,SUM(TOT_DUTY) AS TOT_DUTY,SUM(TOT_AMT_PAYABLE) AS TOT_AMT_PAYABLE,\r\n"
			+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN 1 ELSE 0 END) AS Delivered,\r\n"
			+ "        SUM(CASE WHEN fd.deli_status <> 'DL' THEN 1 ELSE 0 END) AS NotDelivered, \r\n"
			+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN TOT_DUTY ELSE 0 END) AS Delivered_duty_amt,\r\n"
			+ "         SUM(CASE WHEN fd.deli_status <> 'DL' THEN TOT_DUTY ELSE 0 END) AS NotDelivered_duty_amt\r\n"
			+ "        \r\n"
			+ "    FROM\r\n"
			+ "        fpo_main a JOIN fpo_status b ON (a.item_id = b.item_id)LEFT JOIN OPS$DIR.d_cntry_cd dtc ON (a.SEND_CNTRY_CD = dtc.CNTRY_CD) LEFT JOIN fpo_ITEM_DET fi ON (a.item_id = fi.item_id)\r\n"
			+ "        LEFT JOIN fpo_ITEM_DET_OTHDUTY fio ON (a.item_id = fio.item_id) JOIN fpo_delivery fd ON (a.item_id = fd.item_id)\r\n"
			+ "    WHERE\r\n"
			+ "        TRUNC(b.ooc_dt) BETWEEN TO_DATE(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy')";
				
					st=1;
				queryStr=queryStr+" and (";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+" (a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr +" )  GROUP BY\r\n"
				  		+ "        dtc.CNTRY_NM,a.SEND_CNTRY_CD,a.cus_site\r\n"
				  		+ "    ORDER BY\r\n"
				  		+ "        SUM(a.TOT_DUTY) DESC";
				
				System.out.println("querystr is " + queryStr);
				}
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountriesdelstatus.class);
			
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportOocGivenCountriesdelstatus>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public List<MisReportOocGivenDeliStatus> getMisReportOocGivenDeliStatus(String fromdate, String todate,String[] siteco,int coun,
			HttpSession session, HttpServletRequest request) {
		List<MisReportOocGivenDeliStatus> reportColumn=new ArrayList<MisReportOocGivenDeliStatus>();
		int st=0;
		String sc0=null, sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();						
			//String queryStr="select nvl(sum(DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL, sum(a.TOT_DUTY) TOT_DUTY,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' else '' end as deliveryack,case when dc.status_desc='DELIVERED' then 'DELIVERED' else '' end as delivered,case when dc.status_desc<>'DELIVERED' and dc.status_desc is not null then 'NOT DELIVERED' else '' end as notdelivered FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date ('"+fromdate+"', 'dd/MM/yyyy') AND TO_DATE ('"+todate+"', 'dd/MM/yyyy') and a.cus_site='"+request.getSession().getAttribute("cuSite")+"' group by pdm.CODEDESC,dc.status_desc order by sum(a.TOT_ASS_VAL) desc ";
			String queryStr="with ooc as (\r\n"
					+ "select a.cus_site as cussite,nvl(sum(fio.DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,count(a.item_id) tot_article,sum(a.TOT_ASS_VAL) as \r\n"
					+ "TOT_ASS_VAL, sum(a.TOT_DUTY) TOT_DUTY,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) \r\n"
					+ "left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between \r\n"
					+ "to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite";
					
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				st=1;
				queryStr="with ooc as (\r\n"
						+ "select a.cus_site as cussite,nvl(sum(fio.DUTY_AMT),0) other_duty,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_ASS_VAL) desc) id,count(a.item_id) tot_article,sum(a.TOT_ASS_VAL) as \r\n"
						+ "TOT_ASS_VAL, sum(a.TOT_DUTY) TOT_DUTY,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) \r\n"
						+ "left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between \r\n"
						+ "to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')";
				for (int i=0 ; i <coun ; i++)
				{
					if(i==0) {
						sc0=siteco[i];
						queryStr=queryStr+" and ((a.cus_site)=(:sc0)";
					 }
					else {
					queryStr = queryStr + " or trim(a.cus_site) = trim(:sc" + i + ")";
					}
				
				}
				queryStr=queryStr +")";
			}
			
			queryStr=queryStr + " group by a.cus_site),oocitem as (\r\n"
					+ "select a.item_id FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi \r\n"
					+ "on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')\r\n"
					+ "),lettercnt as (\r\n"
					+ "  select count(*) letter FROM fpo_main a join oocitem b on (a.item_id=b.item_id and MAIL_CLASS_CD='U')\r\n"
					+ "),emscnt as (\r\n"
					+ "  select count(*) ems FROM fpo_main a join oocitem b on (a.item_id=b.item_id and MAIL_CLASS_CD='E')\r\n"
					+ "),parcelcnt as (\r\n"
					+ "  select count(*) parcel FROM fpo_main a join oocitem b on (a.item_id=b.item_id and MAIL_CLASS_CD='C')\r\n"
					+ "),emptycnt as (\r\n"
					+ "  select count(*) empty FROM fpo_main a join oocitem b on (a.item_id=b.item_id and MAIL_CLASS_CD='T')\r\n"
					+ "),\r\n"
					+ "deliveryackcou as(\r\n"
					+ "select count(*) deliveryack FROM fpo_delivery a right join oocitem b on (a.item_id=b.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) where dc.status_desc is null ),\r\n"
					+ "deliveredcou as(\r\n"
					+ "select count(*) delivered FROM fpo_delivery a join oocitem b on (a.item_id=b.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) where dc.status_desc='DELIVERED' ),\r\n"
					+ "notdeliveredcou as(\r\n"
					+ "select count(*) notdelivered FROM fpo_delivery a join oocitem b on (a.item_id=b.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) where dc.status_desc<>'DELIVERED' and dc.status_desc is not null )\r\n"
					+ "select * from ooc,lettercnt,emscnt,parcelcnt,emptycnt,deliveryackcou,deliveredcou,notdeliveredcou";
			
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenDeliStatus.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if (st==1) 
				for (int i=0; i<coun; i++) {
					if(i ==0) {
						query.setParameter("sc0", sc0);
						}else {
							sc1=siteco[i];
							query.setParameter("sc"+ i, sc1);
							}
						}  
					
			reportColumn = (List<MisReportOocGivenDeliStatus>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

//12
	public List<MisOOEReceptacleProcess> getMisReportOOEReceptacleProcess(String fromdate, String todate,String[] siteco,int coun,
			HttpSession session, HttpServletRequest request) {
		List<MisOOEReceptacleProcess> reportColumn=new ArrayList<MisOOEReceptacleProcess>();
		int st=0;
		String sc0=null, sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
				String queryStr="select  decode(clr_site,null,a.cus_site,clr_site) as cussite,\r\n"
					+ "count( RECP_ID) RECP_ID,dtc.CNTRY_NM as coo FROM \r\n"
					+ "fpo_main a  join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) left join OPS$DIR.d_cntry_cd dtc on\r\n"
					+ "(a.SEND_CNTRY_CD = dtc.CNTRY_CD) where trunc(RECD_EVENT_DT) between to_date (:fromdate, 'dd/MM/yyyy')\r\n"
					+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"
					+ "and b.status is null and RECD_EVENT_DT is not null and dtc.CNTRY_NM is not null\r\n"
					+ "and  substr(b.OOE,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="select  decode(clr_site,null,a.cus_site,clr_site) as cussite,\r\n"
						+ "count( RECP_ID) RECP_ID,dtc.CNTRY_NM as coo FROM \r\n"
						+ "fpo_main a  join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) left join OPS$DIR.d_cntry_cd dtc on\r\n"
						+ "(a.SEND_CNTRY_CD = dtc.CNTRY_CD) where trunc(RECD_EVENT_DT) between to_date (:fromdate, 'dd/MM/yyyy')\r\n"
						+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"
						+ "and b.status is null and RECD_EVENT_DT is not null and dtc.CNTRY_NM is not null ";
				st=1;
				
				for (int i=0 ; i <coun ; i++)
				{
					if(i==0) {
						sc0=siteco[i];
						//queryStr=queryStr+" and ((b.OOE)=(:sc0)";
						queryStr=queryStr+" and ( (substr(b.OOE,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite ) ";
					 }
					else {							
					
						queryStr = queryStr + " or (substr(b.OOE,1,5)=decode(substr(:cuSite" + i + ", 1, 5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite" + i + ", 1, 5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite" + i + " )";
						
						
						//queryStr = queryStr + " or substr(b.OOE, 1, 5)=substr(:cuSite" + i + ", 1, 5)";
					}
				
				}
				queryStr=queryStr +")";
				
				
			}
			
			queryStr=queryStr + " group by decode(clr_site,null,a.cus_site,clr_site),dtc.CNTRY_NM order by 2 desc" ;
			
			Query query = entityManager.createNativeQuery(queryStr, MisOOEReceptacleProcess.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if (st==1) 
				for (int i=0; i<coun; i++) {
					if(i ==0) {
						query.setParameter("cuSite", sc0);
						}else {
							sc1=siteco[i];
							query.setParameter("cuSite"+ i, sc1);
							}
						} 
			reportColumn = (List<MisOOEReceptacleProcess>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	
	
	public List<MisReportOOEReceptacleProcess> getMisReportOOEReceptacleProcessDetails(String fromdate,
			String todate,String[] siteco,int coun, HttpSession session, HttpServletRequest request) {
		List<MisReportOOEReceptacleProcess> reportColumn=new ArrayList<MisReportOOEReceptacleProcess>();
		int st=0;
		String sc0=null, sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			//String queryStr="select ROW_NUMBER() OVER (ORDER BY RECD_EVENT_DT) id,RECP_ID,dtc.CNTRY_NM as coo,to_char(RECD_EVENT_DT , 'dd/mm/yyyy') RECD_EVENT_DT,BAG_NO,OOE,to_char(RECD_DT , 'dd/mm/yyyy') RECD_DT FROM fpo_main a left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) right join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) right join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) where trunc(RECD_EVENT_DT) between to_date ('"+fromdate+"', 'dd/MM/yyyy') AND TO_DATE ('"+todate+"', 'dd/MM/yyyy') and a.cus_site='"+request.getSession().getAttribute("cuSite")+"' order by RECD_EVENT_DT ";
			String 	queryStr="select  decode(clr_site,null,a.cus_site,clr_site) as cussite, RECP_ID,dtc.CNTRY_NM as coo,\r\n"
					+ "to_char(RECD_EVENT_DT , 'dd/mm/yyyy') RECD_EVENT_DT FROM fpo_main a  join ARTICLE_ARR_INFO b on \r\n"
					+ "(a.item_id=b.ARTICLE_ID) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) \r\n"
					+ "where trunc(RECD_EVENT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')\r\n"
					+ "and b.status is null and  RECP_ID is not null and \r\n"
					+ "substr(b.OOE,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite and RECP_ID is not null \r\n";
					

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="select  decode(clr_site,null,a.cus_site,clr_site) as cussite, RECP_ID,dtc.CNTRY_NM as coo,\r\n"
						+ "to_char(RECD_EVENT_DT , 'dd/mm/yyyy') RECD_EVENT_DT FROM fpo_main a  join ARTICLE_ARR_INFO b on \r\n"
						+ "(a.item_id=b.ARTICLE_ID) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) \r\n"
						+ "where trunc(RECD_EVENT_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')\r\n"
						+ "and b.status is null and RECP_ID is not null   ";
                st=1;
                
                for (int i=0 ; i <coun ; i++)
				{
					if(i==0) {
						sc0=siteco[i];
						
						queryStr=queryStr+" and ( (substr(b.OOE,1,5)=decode(substr(:sc0,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sc0,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sc0)";
					 }
					else {							
						queryStr = queryStr + " or (substr(b.OOE,1,5)=decode(substr(:sc" + i + ", 1, 5),'INBPS','INBOM','INFCH','INMAA',(substr(:sc" + i + ", 1, 5))) and decode(clr_site,null,a.cus_site,clr_site)=:sc" + i + ")";
						//queryStr = queryStr + " or substr(b.OOE, 1, 5)=substr(:cuSite" + i + ", 1, 5)";
					}
				
				}
				queryStr=queryStr +" )";
			}
			//commented for count matching
			//queryStr=queryStr +" group by  decode(clr_site,null,a.cus_site,clr_site),RECP_ID,dtc.CNTRY_NM,to_char(RECD_EVENT_DT , 'dd/mm/yyyy')";
			
			System.out.println("to check the query "+queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportOOEReceptacleProcess.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if (st==1) 
				for (int i=0; i<coun; i++) {
					if(i ==0) {
						query.setParameter("sc0", sc0);
						}else {
							sc1=siteco[i];
							query.setParameter("sc"+ i, sc1);
							}
						} 
			reportColumn = (List<MisReportOOEReceptacleProcess>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	

	//for report 25
	
	
	public List<MisReportQueryReplyStatus> getMisReportQueryReplyStatus(String fromdate,
			String todate,String[] siteco,int coun, HttpSession session, HttpServletRequest request) {
		List<MisReportQueryReplyStatus> reportColumn=new ArrayList<MisReportQueryReplyStatus>();
		int st=0;
		String sc0=null, sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();			
			
			List<String>cussites=Arrays.asList(siteco);
			
		String 	queryStr="(select a.item_id,a.cus_site as cussite ,substr(a.dcall_no,1,3) stage,'FIRST QUERY' qry_type ,a.dcall_no,genurl onetime_link,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') as queryraiseddate,qry_off_id query_raised_by, to_char(rply_date,'DD/MM/YYYY HH24:MI:SS') reply_date,\r\n"
				+ "decode(rply_off_id,null,'LINK',rply_off_id) replied_by,\r\n"
				+ "trunc(rply_date-gen_dt)||  ' D '|| abs(trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)) || ' H ' ||\r\n"
				+ "abs(round(((((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)-trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24))*60)) || ' M ' time_taken_to_reply,\r\n"
				+ "emailid,mobileno,ip_address from dcallqry_gen a\r\n"
				+ "left join fpo_query b on a.item_id=b.item_id left join dcallreply_otp c on a.dcall_no=c.dcall_no\r\n"
				+ "where ip_address is not null and qry_reply='Y' and rply_date is not null  and c.id=(select max(id) from dcallreply_otp where dcall_no=a.dcall_no) and (gen_dt between to_date(:fromdate , 'dd/MM/yyyy') and TO_DATE (:todate , 'dd/MM/yyyy')) and a.cus_site=:cuSite )\r\n"
				+ "\r\n"
				+ "union\r\n"
				+ "(select a.item_id,a.cus_site as cussite ,substr(a.dcall_no,1,3) stage,'Additional QUERY' qry_type,a.dcall_no,genurl onetime_link,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS')as queryraiseddate,qry_off_id query_raised_by, to_char(rply_date,'DD/MM/YYYY HH24:MI:SS') reply_date,\r\n"
				+ "decode(rply_off_id,null,'LINK',rply_off_id) replied_by,\r\n"
				+ "trunc(rply_date-gen_dt)||  ' D ' || abs(trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)) || ' H ' ||\r\n"
				+ "abs(round(((((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)-trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24))*60)) || ' M 'time_taken_to_reply,\r\n"
				+ "emailid,mobileno,ip_address from dcallqry_gen a\r\n"
				+ "left join fpo_addl_qry b on a.item_id=b.item_id left join dcallreply_otp c on a.dcall_no=c.dcall_no\r\n"
				+ "where ip_address is not null and a.qry_reply='Y' and rply_date is not null   and c.id=(select max(id) from dcallreply_otp where dcall_no=a.dcall_no) and (gen_dt between to_date(:fromdate , 'dd/MM/yyyy') and TO_DATE (:todate , 'dd/MM/yyyy')) and a.cus_site=:cuSite )\r\n";
				

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="(select a.item_id,a.cus_site as cussite  ,substr(a.dcall_no,1,3) stage,'FIRST QUERY' qry_type ,a.dcall_no,genurl onetime_link,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') as queryraiseddate,qry_off_id query_raised_by, to_char(rply_date,'DD/MM/YYYY HH24:MI:SS') reply_date,\r\n"
						+ "decode(rply_off_id,null,'LINK',rply_off_id) replied_by,\r\n"
						+ "trunc(rply_date-gen_dt)||  ' D ' || abs(trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)) || ' H ' ||\r\n"
						+ "abs(round(((((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)-trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24))*60)) || ' M ' time_taken_to_reply,\r\n"
						+ "emailid,mobileno,ip_address from dcallqry_gen a\r\n"
						+ "left join fpo_query b on a.item_id=b.item_id left join dcallreply_otp c on a.dcall_no=c.dcall_no\r\n"
						+ "where ip_address is not null and qry_reply='Y' and rply_date is not null  and c.id=(select max(id) from dcallreply_otp where dcall_no=a.dcall_no) and (gen_dt between to_date(:fromdate , 'dd/MM/yyyy') and TO_DATE (:todate , 'dd/MM/yyyy')) and a.cus_site IN :cussite )\r\n"
						+ "\r\n"
						+ "union\r\n"
						+ "(select a.item_id,a.cus_site as cussite ,substr(a.dcall_no,1,3) stage,'Additional QUERY' qry_type,a.dcall_no,genurl onetime_link,to_char(gen_dt,'DD/MM/YYYY HH24:MI:SS') as queryraiseddate,qry_off_id query_raised_by, to_char(rply_date,'DD/MM/YYYY HH24:MI:SS') reply_date,\r\n"
						+ "decode(rply_off_id,null,'LINK',rply_off_id) replied_by,\r\n"
						+ "trunc(rply_date-gen_dt)||  ' D ' || abs(trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)) || ' H ' ||\r\n"
						+ "abs(round(((((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24)-trunc(((rply_date-gen_dt)-trunc(rply_date-gen_dt))*24))*60)) || ' M ' time_taken_to_reply,\r\n"
						+ "emailid,mobileno,ip_address from dcallqry_gen a\r\n"
						+ "left join fpo_addl_qry b on a.item_id=b.item_id left join dcallreply_otp c on a.dcall_no=c.dcall_no\r\n"
						+ "where ip_address is not null and a.qry_reply='Y' and rply_date is not null   and c.id=(select max(id) from dcallreply_otp where dcall_no=a.dcall_no) and (gen_dt between to_date(:fromdate , 'dd/MM/yyyy') and TO_DATE (:todate , 'dd/MM/yyyy')) and a.cus_site IN :cussite )";
			
			st=1;
			}
			
			queryStr=queryStr +" order by 9 desc";
			
			System.out.println("to check the query "+queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportQueryReplyStatus.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if(st==1)
				query.setParameter("cussite", cussites);
			
			/*
			 * 
			 * 
			 * if (st==1) { for (int i=0; i<coun; i++) { if(i ==0) {
			 * query.setParameter("sc0", sc0); }else { sc1=siteco[i];
			 * query.setParameter("sc"+ i, sc1); } } }
			 */
			reportColumn = (List<MisReportQueryReplyStatus>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	
	
	//14
		public List<MisFPOReceptacleProcess> getMisReportFPOReceptacleProcess(String fromdate, String todate, HttpSession session,	
				String[] sitevalue, int cou2, HttpServletRequest request) {	
				List<MisFPOReceptacleProcess> reportColumn=new ArrayList<MisFPOReceptacleProcess>();	
				int st=0;	
				String sc0=null;	
				String sc1=null;	
				try {	
					String cuSite = request.getSession().getAttribute("cuSite").toString();					
//					String queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count(BAG_NO) BAG_NO,substr(OOE,1,5)\r\n"	
//							+ "as OOE FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) \r\n"	
//							+ "join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') \r\n"	
//							+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"	
//							+ "and c.status is null and b.status is null and RECD_DT is not null and OOE is not null \r\n"	
//							+ "and substr(recd_fpo,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite  ";	
						String queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count(c.BAG_NO) BAG_NO,substr(b.OOE,1,5) as OOE \r\n"
								+ "FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) \r\n"
								+ " join fpo_scan_info s on (s.bag_no=c.bag_no) \r\n"
								+ "where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and c.status is null and b.status is null and RECD_DT is not null and b.OOE is not null \r\n"
								+ "and substr(recd_fpo,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite \r\n"
								+ " ";
					
					if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 	
					{	
//						queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count(BAG_NO) BAG_NO,substr(OOE,1,5)\r\n"	
//								+ "as OOE FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) \r\n"	
//								+ "join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') \r\n"	
//								+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"	
//								+ "and c.status is null and b.status is null and RECD_DT is not null and OOE is not null  ";	
		               queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count(c.BAG_NO) BAG_NO,substr(b.OOE,1,5) as OOE \r\n"
		               		+ "FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) \r\n"
		               		+ " join fpo_scan_info s on (s.bag_no=c.bag_no) \r\n"
		               		+ "where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and c.status is null and b.status is null and RECD_DT is not null and b.OOE is not null \r\n"
		               		+ "";
		               st=1;	
		                queryStr=queryStr+" and ( ";	
		  			  	
		  			  for(int i=0;i<cou2;i++) { 	
		  				  	
		  				 if(i==0) {	
		  				  sc0=sitevalue[i];	
		  				  queryStr=queryStr+" (substr(recd_fpo,1,5)=decode(substr(:sc0,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sc0,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sc0 )";	
		  					
		  				 }	
		  				 else {	
		  					 queryStr=queryStr+" or (substr(recd_fpo,1,5)=decode(substr(:sitevalue" + i + ",1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sitevalue" + i + ",1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sitevalue" + i + " )";	
		  				
		  				 }	
		  			  } 	
		  			  	
		  			  queryStr = queryStr+" ) ";	
					}	
						
		  			  queryStr = queryStr + " group by decode(clr_site,null,a.cus_site,clr_site),substr(b.OOE,1,5) order by 2 desc";
		  				
		  			  	System.out.println("to check query"+queryStr);	
					Query query = entityManager.createNativeQuery(queryStr, MisFPOReceptacleProcess.class);	
						
					if(st==1) {	
					 for(int i=0;i<cou2;i++) { 	
						  	
						 if(i==0) {	
						 query.setParameter("sc0", sc0);	
						 }	
						 else {	
						        sc1=sitevalue[i];	
						        query.setParameter("sitevalue" + i , sc1);	
						    }	
					  }	
					}	
					query.setParameter("fromdate",fromdate);	
					query.setParameter("todate", todate);	
					if (st==0)	
					   query.setParameter("cuSite", cuSite);	
					reportColumn = (List<MisFPOReceptacleProcess>) query.getResultList();	
						
				} catch (Exception e) {	
					// TODO: handle exception	
					e.printStackTrace();	
				}	
				return reportColumn;	
			}
	
/*	

	public List<MisFPOReceptacleProcess> getMisReportFPOReceptacleProcess(String fromdate, String todate, HttpSession session,
			String[] sitevalue, int cou2, HttpServletRequest request) {
			List<MisFPOReceptacleProcess> reportColumn=new ArrayList<MisFPOReceptacleProcess>();
			int st=0;
			String sc0=null;
			String sc1=null;
			try {
				String cuSite = request.getSession().getAttribute("cuSite").toString();				
				String queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count(distinct BAG_NO) BAG_NO,substr(OOE,1,5)\r\n"
						+ "as OOE FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) \r\n"
						+ "join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') \r\n"
						+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"
						+ "and c.status is null and b.status is null and RECD_DT is not null and OOE is not null \r\n"
						+ "and substr(recd_fpo,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite  ";

				if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
				{
					queryStr="select decode(clr_site,null,a.cus_site,clr_site) as cussite,count( distinct BAG_NO) BAG_NO,substr(OOE,1,5)\r\n"
							+ "as OOE FROM fpo_main a join ARTICLE_ARR_INFO b on (a.item_id=b.ARTICLE_ID) \r\n"
							+ "join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) where trunc(RECD_DT) between to_date (:fromdate, 'dd/MM/yyyy') \r\n"
							+ "AND TO_DATE (:todate, 'dd/MM/yyyy') \r\n"
							+ "and c.status is null and b.status is null and RECD_DT is not null and OOE is not null  ";
	                st=1;
	                queryStr=queryStr+" and ( ";
	  			  
	  			  for(int i=0;i<cou2;i++) { 
	  				  
	  				 if(i==0) {
	  				  sc0=sitevalue[i];
	  				  queryStr=queryStr+" (substr(recd_fpo,1,5)=decode(substr(:sc0,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sc0,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sc0 )";
	  				
	  				 }
	  				 else {
	  					 queryStr=queryStr+" or (substr(recd_fpo,1,5)=decode(substr(:sitevalue" + i + ",1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sitevalue" + i + ",1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sitevalue" + i + " )";
	  			
	  				 }
	  			  } 
	  			  
	  			  queryStr = queryStr+" ) ";
				}
				
	  			  queryStr = queryStr
	  						+ "  group by decode(clr_site,null,a.cus_site,clr_site),substr(OOE,1,5)\r\n"
	  						+ "order by 2 desc\r\n"
	  						+ "";
	  			
	  			  	System.out.println("to check query"+queryStr);
				Query query = entityManager.createNativeQuery(queryStr, MisFPOReceptacleProcess.class);
				
				if(st==1) {
				 for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
				}
				query.setParameter("fromdate",fromdate);
				query.setParameter("todate", todate);
				if (st==0)
				   query.setParameter("cuSite", cuSite);
				reportColumn = (List<MisFPOReceptacleProcess>) query.getResultList();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return reportColumn;
		}
		
		
		*/
	public List<MisReportArtcDetained> getMisReportArtcDetained(String fromdate, String todate, HttpSession session,
			String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportArtcDetained> reportColumn = new ArrayList<MisReportArtcDetained>();
		int st=0;
		String sc0=null;
		String sc1=null;
	
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();

			String queryStr = "select '' queue,'' status,ROW_NUMBER() OVER (ORDER BY ddi.DOC_DATE) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,TOT_ASS_VAL,TOT_DUTY,TOT_FINE,TOT_PENAL,decode(ddi.DOC_TYPE,'Copy of SCN',ddi.DOC_DATE,'') scndt,decode(ddi.DOC_TYPE,'Copy of Order in Original',ddi.DOC_DATE,'') onodt FROM fpo_main a join fpo_DETAINED_INFO di on (a.item_id=di.article_id) join fpo_DETAINED_DOC_INFO ddi on (a.item_id=ddi.article_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where di.DETAIN_COMPLETED is Not null and trunc(ddi.DOC_DATE) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select '' queue,'' status,ROW_NUMBER() OVER (ORDER BY ddi.DOC_DATE) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,TOT_ASS_VAL,TOT_DUTY,TOT_FINE,TOT_PENAL,decode(ddi.DOC_TYPE,'Copy of SCN',ddi.DOC_DATE,'') scndt,decode(ddi.DOC_TYPE,'Copy of Order in Original',ddi.DOC_DATE,'') onodt FROM fpo_main a join fpo_DETAINED_INFO di on (a.item_id=di.article_id) join fpo_DETAINED_DOC_INFO ddi on (a.item_id=ddi.article_id) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where di.DETAIN_COMPLETED is Not null and trunc(ddi.DOC_DATE) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')  ";
				st = 1;
				 queryStr=queryStr+" and (";
   			  
   			  for(int i=0;i<cou2;i++) { 
   				  
   				 if(i==0) {
   				  sc0=sitevalue[i];
   				  queryStr=queryStr+"(a.cus_site)=(:sc0)";
   				 }
   				 else {
   				 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
   				 }
   			  } 
   			 queryStr = queryStr
						+ " )";
   			  
			}
   			 queryStr = queryStr
						+ " order by ddi.DOC_DATE";
		
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportArtcDetained.class);
			if (st==1) {
				
			
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			reportColumn = (List<MisReportArtcDetained>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public String getMisReportprocessed(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr = "select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select cus_site,item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id,cus_site) fi on (a.item_id=fi.item_id) left join (select cus_site,item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id,cus_site) fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite and DELVY_DT is null ";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr = "select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select cus_site,item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id,cus_site) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')  and DELVY_DT is null ";
				st = 1;
			
				queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) ";
				
				System.out.println("querystr is " + queryStr);
			}
			  queryStr=queryStr+" and(";
				for (int i = 0; i < cou; i++) {
					if (i == 0) {
						mc0 = mailclass[i];
						queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
					} else if (i == 1) {
						mc1 = mailclass[1];
						queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
					} else if (i == 2) {
						mc2 = mailclass[2];
						queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
					} else if (i == 3) {
						mc3 = mailclass[3];
						queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
					}
				}		
				
				queryStr = queryStr + " ) ";
			Query query = entityManager.createNativeQuery(queryStr);
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			List<Object> resultList = query.getResultList();
			if (resultList.size() > 0) {
				result = ((BigDecimal) resultList.get(0)).toString();
			}

			if (result == null) {
				result = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "";
		}
		return result;

	}

	public String getMisReportcleared(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr = "select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select cus_site,item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id,cus_site) fi on (a.item_id=fi.item_id) left join (select cus_site,item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id,cus_site) fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite and DELVY_DT is not null ";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr = "select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select cus_site,item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id,cus_site) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')  and DELVY_DT is not null ";
				st = 1;
			
				queryStr=queryStr+" and (";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) ";
				
				System.out.println("querystr is " + queryStr);
			}
			  queryStr=queryStr+" and (";
				for (int i = 0; i < cou; i++) {
					if (i == 0) {
						mc0 = mailclass[i];
						queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
					} else if (i == 1) {
						mc1 = mailclass[1];
						queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
					} else if (i == 2) {
						mc2 = mailclass[2];
						queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
					} else if (i == 3) {
						mc3 = mailclass[3];
						queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
					}
				}		
				
				 queryStr = queryStr + " ) ";
			Query query = entityManager.createNativeQuery(queryStr);
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			List<Object> resultList = query.getResultList();
			if (resultList.size() > 0) {
				result = ((BigDecimal) resultList.get(0)).toString();
			}

			if (result == null) {
				result = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "";
		}
		return result;

	}
	
	
	
	public String getMisReportprocessedead(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou, String[] itemcat, int couic,String[] sitevalue, int cou2, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr ="select count(*) FROM fpo_main a join \r\n"
					+ "fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm \r\n"
					+ "on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,\r\n"
					+ "sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on \r\n"
					+ "(a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) and a.cus_site=:cuSite and DELVY_DT is  null  ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr="select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) and DELVY_DT is  null  ";
				st = 1;
				
				queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) ";
				
				System.out.println("querystr is " + queryStr);
				}
			  queryStr=queryStr+" and(";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}

			queryStr = queryStr + " ) and  (  ";

			for (int i = 0; i < couic; i++) {
				if (i == 0) {
					it0 = itemcat[i];
					queryStr = queryStr + " trim(pdi.CATEGORY) =  trim(:it0)";
				} else if (i == 1) {
					it1 = itemcat[1];
					queryStr = queryStr + " or  trim(pdi.CATEGORY)=  trim(:it1)";
				} else if (i == 2) {
					it2 = itemcat[2];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it2)";
				} else if (i == 3) {
					it3 = itemcat[3];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it3)";
				} else if (i == 4) {
					it4 = itemcat[4];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it4)";
				}

				else if (i == 5) {
					it5 = itemcat[5];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it5)";
				}

			}

			queryStr = queryStr
					+ " )  ";

			System.out.println("querystr=" + queryStr);
			
			Query query = entityManager.createNativeQuery(queryStr);

			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			for (int i = 0; i < couic; i++) {
				if (i == 0)
					query.setParameter("it0", it0);
				else if (i == 1)
					query.setParameter("it1", it1);
				else if (i == 2)
					query.setParameter("it2", it2);
				else if (i == 3)
					query.setParameter("it3", it3);
				else if (i == 4)
					query.setParameter("it4", it4);
				else if (i == 5)
					query.setParameter("it5", it5);
			}
			if (st==0)
				query.setParameter("cuSite", cuSite);
			
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			
//			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
		
			List<Object> resultList = query.getResultList();
			if (resultList.size() > 0) {
				result = ((BigDecimal) resultList.get(0)).toString();
			}

			if (result == null) {
				result = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "";
		}
		return result;

	}

	public String getMisReportclearedead(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou, String[] itemcat, int couic,String[] sitevalue, int cou2, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		int st = 0;
		String mc0 = null, mc1 = null, mc2 = null, mc3 = null;
		String it0 = null, it1 = null, it2 = null, it3 = null, it4 = null, it5 = null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr ="select count(*) FROM fpo_main a join \r\n"
					+ "fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm \r\n"
					+ "on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,\r\n"
					+ "sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on \r\n"
					+ "(a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) and a.cus_site=:cuSite and DELVY_DT is  not null  ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr="select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where (trunc(a.cin_dt) between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')) and DELVY_DT is  not null  ";
				st = 1;
				
				queryStr=queryStr+" and(";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+" or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr + " ) ";
				
				System.out.println("querystr is " + queryStr);
				}
			  queryStr=queryStr+" and (";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0)";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}

			queryStr = queryStr + " ) and  (  ";

			for (int i = 0; i < couic; i++) {
				if (i == 0) {
					it0 = itemcat[i];
					queryStr = queryStr + " trim(pdi.CATEGORY) =  trim(:it0)";
				} else if (i == 1) {
					it1 = itemcat[1];
					queryStr = queryStr + " or  trim(pdi.CATEGORY)=  trim(:it1)";
				} else if (i == 2) {
					it2 = itemcat[2];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it2)";
				} else if (i == 3) {
					it3 = itemcat[3];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it3)";
				} else if (i == 4) {
					it4 = itemcat[4];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it4)";
				}

				else if (i == 5) {
					it5 = itemcat[5];
					queryStr = queryStr + " or  trim(pdi.CATEGORY) =  trim(:it5)";
				}

			}

			queryStr = queryStr
					+ " )  ";

			System.out.println("querystr=" + queryStr);
			
			Query query = entityManager.createNativeQuery(queryStr);

			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			for (int i = 0; i < couic; i++) {
				if (i == 0)
					query.setParameter("it0", it0);
				else if (i == 1)
					query.setParameter("it1", it1);
				else if (i == 2)
					query.setParameter("it2", it2);
				else if (i == 3)
					query.setParameter("it3", it3);
				else if (i == 4)
					query.setParameter("it4", it4);
				else if (i == 5)
					query.setParameter("it5", it5);
			}
			if (st==0)
				query.setParameter("cuSite", cuSite);
			
			if(st==1) {
				for(int i=0;i<cou2;i++) { 
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
			}
			
//			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
		
			List<Object> resultList = query.getResultList();
			if (resultList.size() > 0) {
				result = ((BigDecimal) resultList.get(0)).toString();
			}

			if (result == null) {
				result = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "";
		}
		return result;

	}

	
	public List<MisReportFPOReceptacleProcess> getMisReportFPOReceptacleProcessDetails(String fromdate, String todate, HttpSession session,
			String[] sitevalue, int cou2, HttpServletRequest request) {
		int st=0;

		String sc0=null;
		String sc1=null;
		List<MisReportFPOReceptacleProcess> reportColumn=new ArrayList<MisReportFPOReceptacleProcess>();
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			
//			String queryStr=" select  decode(clr_site,null,a.cus_site,clr_site) as cussite,s.BAG_NO ,decode(f.ooe,null,'-',f.ooe) as ooe ,to_char(c.RECD_DT , 'dd/mm/yyyy') RECD_DT \r\n"
//					+ " FROM fpo_main a  left join (select ooe,article_id from article_arr_info) f on\r\n"
//					+ " a.item_id = f.article_id left join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id),fpo_scan_info s where (s.bag_no=c.bag_no)  \r\n"
//					+ "and c.status is null and substr(recd_fpo,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite   and trunc(RECD_DT) \r\n"
//					+ "between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";
			
			String queryStr="select  decode(clr_site,null,a.cus_site,clr_site) as cussite ,decode(b.ooe,null,'-',b.ooe) as ooe ,to_char(c.RECD_DT , 'dd/mm/yyyy') RECD_DT ,c.bag_no\r\n"
					+ "FROM fpo_main a  join article_arr_info b on a.item_id = b.article_id  join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) \r\n"
					+ " join fpo_scan_info s on (s.bag_no=c.bag_no) \r\n"
					+ "where trunc(RECD_DT) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')and c.status is null and b.status is null and RECD_DT is not null and b.OOE is not null\r\n"
					+ "and substr(recd_fpo,1,5)=decode(substr(:cuSite,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:cuSite,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:cuSite   ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))  
			{
				queryStr=" select  decode(clr_site,null,a.cus_site,clr_site) as cussite ,decode(b.ooe,null,'-',b.ooe) as ooe ,to_char(c.RECD_DT , 'dd/mm/yyyy') RECD_DT ,c.bag_no\r\n"
						+ "FROM fpo_main a  join article_arr_info b on a.item_id = b.article_id  join ARTICLEARR_FPO_INFO c on (a.item_id=c.article_id) \r\n"
						+ " join fpo_scan_info s on (s.bag_no=c.bag_no) \r\n"
						+ "where trunc(RECD_DT) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')and c.status is null and b.status is null and RECD_DT is not null and b.OOE is not null ";
				st=1;
                queryStr=queryStr+" and ( ";
  			  
  			  for(int i=0;i<cou2;i++) { 
  			
  				 if(i==0) {
  				  sc0=sitevalue[i];
  				  queryStr=queryStr+" ( substr(recd_fpo,1,5)=decode(substr(:sc0,1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sc0,1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sc0 )  ";
  				 }
  				 else {
  					 queryStr=queryStr+" or ( substr(recd_fpo,1,5)=decode(substr(:sitevalue" + i + ",1,5),'INBPS','INBOM','INFCH','INMAA',(substr(:sitevalue" +i + ",1,5))) and decode(clr_site,null,a.cus_site,clr_site)=:sitevalue"+ i + ") ";
  				  				 }
  			  } 
  			queryStr = queryStr
						+ " )";
  			  
			}
  			 // queryStr = queryStr+" group by decode(clr_site,null,a.cus_site,clr_site),s.BAG_NO,f.ooe,to_char(c.RECD_DT , 'dd/mm/yyyy')  ";
			
			System.out.println("printing wuery to check "+ queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportFPOReceptacleProcess.class);
			if (st==1) {
			
			for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			if (st==0)
		query.setParameter("cuSite", cuSite);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			
			reportColumn = (List<MisReportFPOReceptacleProcess>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}


	public String getMisReportOocprocessed(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub

		String result="";
		int st=0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			String 	queryStr="select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between  to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite  ";

	
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))  {
				queryStr="select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id) left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between  to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";
				st=1;
		}
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = ((BigDecimal)resultList.get(0)).toString();
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	
	
	}

	public String getMisReportdelivered(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result="";
		
		int st=0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			String	queryStr="select count(*)  FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) join fpo_delivery fd on (a.item_id=fd.item_id and DELI_STATUS='DL') left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between  to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite  " ;
						

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))   {
				queryStr="select count(*) FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) join fpo_delivery fd on (a.item_id=fd.item_id and DELI_STATUS='DL') left join deli_status_codes dc on (dc.status_code=deli_status) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between  to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  ";
				st=1;
			}
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			  List<Object> resultList =query.getResultList();
			  if(resultList.size()>0) { 
				  result = ((BigDecimal)resultList.get(0)).toString();
				  }

			  if(result==null) {
				  result = "";  
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 result="";
		}
		return result;
	
	
	}
	
	
	public List<MisReportCommercialQueue> getmisreportcommercial(String fromdate, String todate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportCommercialQueue> reportColumn=new ArrayList<MisReportCommercialQueue>();
		int st=0;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();	
			System.out.println("mailclass is " + mailclass);
		//	String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where a.cus_site=:cuSite and b.QRY is null ORDER BY c.GEN_DT";
				
			String queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM\r\n"
					+ "as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3)\r\n"
					+ "As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,\r\n"
					+ "decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque \r\n"
					+ "FROM fpo_main a left join fpo_query b on (a.item_id=b.item_id) left join DCALLQRY_GEN c on (a.item_id=c.item_id) \r\n"
					+ "left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) \r\n"
					+ "left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD)   join fpo_qry_deci fqd  on(a.item_id=fqd.item_id) \r\n"
					+ "where  b.QRY is null and to_char(fqd.QRY_DT , 'dd/mm/yyyy')  between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')\r\n"
					+ "and a.cus_site=:cuSite \r\n"
					+ "and qry_type='P6'";
			
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
		//		queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3) As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,b.QRY_EMAILID email,b.QRY_MOBILENO mobno,c.SPEEDPOST_NO dispatchedno,SPEEDPOST_DELI_STATUS delistatus,decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque  FROM fpo_main a join fpo_query b on (a.item_id=b.item_id) join DCALLQRY_GEN c on (a.item_id=c.item_id) left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) where  b.QRY is null ORDER BY c.GEN_DT";
          
				queryStr="select ROW_NUMBER() OVER (ORDER BY c.GEN_DT) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM\r\n"
						+ "as coo,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_DECL_VAL totdec,SUBSTR(c.DCALL_NO, 1, 3)\r\n"
						+ "As qrymodel,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldt,\r\n"
						+ "decode(b.RPLY_DATE,null,'Pending','Replied') rlystatus,'' currentque \r\n"
						+ "FROM fpo_main a left join fpo_query b on (a.item_id=b.item_id) left join DCALLQRY_GEN c on (a.item_id=c.item_id) \r\n"
						+ "left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) \r\n"
						+ "left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD)   join fpo_qry_deci fqd  on(a.item_id=fqd.item_id) \r\n"
						+ "where  b.QRY is null and to_char(fqd.QRY_DT , 'dd/mm/yyyy')  between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')\r\n"
						+ "and qry_type='P6'";
				
				st=1;				
			
				 queryStr=queryStr+" and ( ";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 
					 else {
					        queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					    }
				  
				  }
				  queryStr = queryStr
							+ ")";
				 
				System.out.println("querystr is " + queryStr);
				
				
			}
				
				queryStr=queryStr+" and ( ";
			for (int i= 0 ; i<cou ; i++)
			{
			  if (i==0)
			  {
				  mc0=mailclass[i]; 
				  queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0) ";
			  }
			  else if (i==1)
			  {
				  mc1=mailclass[1]; 
				  queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)"; 
			  }
			  else if (i==2)
			  {
				  mc2=mailclass[2]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)"; 
			  }			  
			  else if (i==3)
			  {
				  mc3=mailclass[3]; 
				  queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)"; 
			  }			  
			}
			
			
			queryStr = queryStr + " )   ORDER BY c.GEN_DT";
			System.out.println("querystr is " + queryStr);

			Query query = entityManager.createNativeQuery(queryStr, MisReportCommercialQueue.class);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			
			for (int i= 0 ; i<cou ; i++)
			{
				if (i==0)
					query.setParameter("mc0", mc0);
				else if (i==1)
					query.setParameter("mc1", mc1);
				else if (i==2)
					query.setParameter("mc2", mc2);
				else if (i==3)
					query.setParameter("mc3", mc3);
			}
			if(st==1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", todate);
			reportColumn = (List<MisReportCommercialQueue>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}






public List<Misreportcommercialunderprocess> getMisReportcommercialunderprocess(String fromdate,
			String todate, HttpSession session,String[] sitevalue,int cou2, HttpServletRequest request) {
		List<Misreportcommercialunderprocess> reportColumn=new ArrayList<Misreportcommercialunderprocess>();
		int st=0;
		String sc0=null, sc1=null;
		
		try {
			String cuSite2 = request.getSession().getAttribute("cuSite").toString();				
			
			String queryStr="select ROW_NUMBER() OVER (ORDER BY a.postingdt) id,b.be_no as beno,to_char(b.be_dt,'dd/MM/yyyy') as bedt,b.item_id, a.cus_site as cussite,b.iec,b.gstin_id as gstinid, b.adcode,b.scheme_cd as schemecd, b.license_no as licenseno,b.challan_no as challanno, to_char(b.challan_dt,'dd/MM/yyyy') as challandt, DECODE(c.ooc_dt, NULL, 'Data Being Entered', 'OOC given in AAF on ' || TO_CHAR(c.ooc_dt, 'dd/MM/yyyy')) AS status from fpo_manual_commercial b left join fpo_main a on (b.item_id = a.item_id) left join fpo_status c on(b.item_id = c.item_id) where to_date((substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4)),'DD/MM/YYYY') between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') and a.cus_site=:cuSite2";
					

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="select ROW_NUMBER() OVER (ORDER BY a.postingdt) id,b.be_no as beno,b.be_dt as bedt,b.item_id, a.cus_site as cussite,b.iec,b.gstin_id as gstinid, b.adcode,b.scheme_cd as schemecd, b.license_no as licenseno,b.challan_no as challanno , to_char(b.challan_dt,'dd/MM/yyyy') as challandt, DECODE(c.ooc_dt, NULL, 'Data Being Entered', 'OOC given in AAF on ' || TO_CHAR(c.ooc_dt, 'dd/MM/yyyy')) AS status from fpo_manual_commercial b left join fpo_main a on (b.item_id = a.item_id) left join fpo_status c on(b.item_id = c.item_id) where to_date((substr(a.postingdt,9,2) ||substr(a.postingdt,6,2)||substr(a.postingdt,0,4)),'DD/MM/YYYY') between to_date (:fromdate, 'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				st=1;				
				
				 queryStr=queryStr+" and ( ";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 
					 else {
					        queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					    }
				  
				  }
				  queryStr = queryStr
							+ ")";
				 
				System.out.println("querystr is " + queryStr);
			}
			
			System.out.println("querystr is " + queryStr);
			
			
			Query query = entityManager.createNativeQuery(queryStr, Misreportcommercialunderprocess.class);
			
			if (st==0)
			   query.setParameter("cuSite2", cuSite2);
			if(st==1) {
				 for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
				}
				query.setParameter("fromdate", fromdate);
				query.setParameter("todate", todate);
				
					reportColumn = (List<Misreportcommercialunderprocess>)query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}



	
	
	

	public List<MisReportOocGivenCountries> getMisReportMailClassOocGivenCountries(String fromdate, String enddate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportOocGivenCountries> reportColumn = new ArrayList<MisReportOocGivenCountries>();
		System.out.println(request.getSession().getAttribute("role").toString());
		int st = 0;
		
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();

			String queryStr = "select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  and a.cus_site=:cuSite ";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite ,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.ooc_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')   ";
				st = 1;
			
				 queryStr=queryStr+" and( ";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr
							+ ")";
				
				System.out.println("querystr is " + queryStr);
				}
			
			queryStr=queryStr+" and( ";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			queryStr = queryStr
					+ " ) group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC  order by sum(a.TOT_DUTY) desc";
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountries.class);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if (st == 1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", enddate);
		
			reportColumn = (List<MisReportOocGivenCountries>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	
	
	//report 27
	public List<MisReportOocGivenCountriesdelstatus> getMisReportMailClassOocGivenCountriesdelstatus(String fromdate, String enddate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportOocGivenCountriesdelstatus> reportColumn = new ArrayList<MisReportOocGivenCountriesdelstatus>();
		System.out.println(request.getSession().getAttribute("role").toString());
		int st = 0;
		
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();

			String queryStr = "select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite,\r\n"
					+ "        sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER()\r\n"
					+ "        OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD\r\n"
					+ "        ,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL)\r\n"
					+ "        as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) \r\n"
					+ "        TOT_AMT_PAYABLE,\r\n"
					+ "          SUM(CASE WHEN fd.deli_status = 'DL' THEN 1 ELSE 0 END) AS Delivered,\r\n"
					+ "        SUM(CASE WHEN fd.deli_status <> 'DL' THEN 1 ELSE 0 END) AS NotDelivered, \r\n"
					+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN TOT_DUTY ELSE 0 END) AS Delivered_duty_amt,\r\n"
					+ "         SUM(CASE WHEN fd.deli_status <> 'DL' THEN TOT_DUTY ELSE 0 END) AS NotDelivered_duty_amt\r\n"
					+ "        from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join\r\n"
					+ "        OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc\r\n"
					+ "        on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join\r\n"
					+ "        fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) \r\n"
					+ "         JOIN fpo_delivery fd ON (a.item_id = fd.item_id)\r\n"
					+ "        where trunc(b.ooc_dt) between to_date\r\n"
					+ "        (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  and a.cus_site=:cuSite  ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = " select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite,\r\n"
						+ "        sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER()\r\n"
						+ "        OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD\r\n"
						+ "        ,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL)\r\n"
						+ "        as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) \r\n"
						+ "        TOT_AMT_PAYABLE,\r\n"
						+ "          SUM(CASE WHEN fd.deli_status = 'DL' THEN 1 ELSE 0 END) AS Delivered,\r\n"
						+ "        SUM(CASE WHEN fd.deli_status <> 'DL' THEN 1 ELSE 0 END) AS NotDelivered, \r\n"
						+ "        SUM(CASE WHEN fd.deli_status = 'DL' THEN TOT_DUTY ELSE 0 END) AS Delivered_duty_amt,\r\n"
						+ "         SUM(CASE WHEN fd.deli_status <> 'DL' THEN TOT_DUTY ELSE 0 END) AS NotDelivered_duty_amt\r\n"
						+ "        from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join\r\n"
						+ "        OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc\r\n"
						+ "        on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join\r\n"
						+ "        fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) \r\n"
						+ "         JOIN fpo_delivery fd ON (a.item_id = fd.item_id)\r\n"
						+ "        where trunc(b.ooc_dt) between to_date\r\n"
						+ "        (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";
				st = 1;
			
				 queryStr=queryStr+" and( ";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+" (a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr
							+ " ) ";
				
				System.out.println("querystr is " + queryStr);
				}
			
			queryStr=queryStr+" and( ";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			queryStr = queryStr
					+ " )  group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC  order by sum(a.TOT_DUTY) desc";
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountriesdelstatus.class);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if (st == 1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", enddate);
		
			reportColumn = (List<MisReportOocGivenCountriesdelstatus>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<MisReportOocGivenCountries> getMisReportMailClassCountriesead(String fromdate, String todate,
			HttpSession session, HttpServletRequest request) {
		List<MisReportOocGivenCountries> reportColumn=new ArrayList<MisReportOocGivenCountries>();
		System.out.println(request.getSession().getAttribute("role").toString());	
		int st=0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
						
			String queryStr="select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(a.job_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  and a.cus_site=:cuSite group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC order by sum(a.TOT_DUTY) desc";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") ||  request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA"))   {
				queryStr="select nvl(sum(DUTY_AMT),0) other_duty,a.cus_site as cussite ,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT,ROW_NUMBER() OVER (ORDER BY sum(a.TOT_DUTY) desc) id,dtc.CNTRY_NM as coo,a.SEND_CNTRY_CD,count(a.item_id) tot_article,pdm.CODEDESC as mail_class,sum(a.TOT_ASS_VAL) as TOT_ASS_VAL,sum(TOT_DUTY_FG) TOT_DUTY_FG,sum(TOT_DUTY) TOT_DUTY,sum(TOT_AMT_PAYABLE) TOT_AMT_PAYABLE from fpo_main a join fpo_status b on (a.item_id = b.item_id)  left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join fpo_ITEM_DET fi on (a.item_id=fi.item_id) left join fpo_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(a.job_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  group by a.cus_site,dtc.CNTRY_NM ,a.SEND_CNTRY_CD,pdm.CODEDESC order by sum(a.TOT_DUTY) desc";
				st=1;
			}
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenCountries.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			reportColumn = (List<MisReportOocGivenCountries>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportOocGivenDeliStatusDetails> getMisReportOocGivenDeliStatusDetails(String fromdate, String enddate, HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportOocGivenDeliStatusDetails> reportColumn=new ArrayList<MisReportOocGivenDeliStatusDetails>();
		int st=0;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			
			String queryStr="select ROW_NUMBER() OVER (ORDER BY b.OOC_DT desc) id,a.cus_site as cussite,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,\r\n"
					+ "pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL,TOT_DUTY, TOT_DUTY_FG, TOT_AMT_PAYABLE,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_status,to_char(b.OOC_DT , 'dd/mm/yyyy') OOC_DT FROM fpo_main a join fpo_status b on \r\n"
					+ "(a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on \r\n"
					+ "(a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join deli_status_codes dc on (dc.status_code=deli_status) left join FPO_ITEM_DET fi on (a.item_id=fi.item_id) left join \r\n"
					+ "FPO_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite ";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") || request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) 
			{
				queryStr="select ROW_NUMBER() OVER (ORDER BY b.OOC_DT desc) id,a.cus_site as cussite,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,\r\n"
						+ "pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL,TOT_DUTY, TOT_DUTY_FG, TOT_AMT_PAYABLE,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_status,to_char(b.OOC_DT , 'dd/mm/yyyy') OOC_DT FROM fpo_main a join fpo_status b on \r\n"
						+ "(a.item_id = b.item_id) left join fpo_delivery fd on (a.item_id=fd.item_id)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on \r\n"
						+ "(a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join deli_status_codes dc on (dc.status_code=deli_status) left join FPO_ITEM_DET fi on (a.item_id=fi.item_id) left join \r\n"
						+ "FPO_ITEM_DET_OTHDUTY fio on (a.item_id=fio.item_id) where trunc(b.OOC_DT) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy')  ";
               st=1;
               
               queryStr=queryStr+" and (";
               for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr
							+ " )";
				
				System.out.println("querystr is " + queryStr);
			
			}
			queryStr=queryStr+" and ( ";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + " trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
              
			queryStr = queryStr + " ) ORDER BY b.OOC_DT desc";
			Query query = entityManager.createNativeQuery(queryStr, MisReportOocGivenDeliStatusDetails.class);
			if (st==0)
				   query.setParameter("cuSite", cuSite);
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if (st == 1) {
				 for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					 query.setParameter("sc0", sc0);
					 }
					 else {
					        sc1=sitevalue[i];
					        query.setParameter("sitevalue" + i , sc1);
					    }
				  }
				}
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", enddate);
			reportColumn = (List<MisReportOocGivenDeliStatusDetails>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}
	public List<FpoSite_Allot> getFpoSite_Allot() {
		List<FpoSite_Allot> result=new ArrayList<FpoSite_Allot>();
		try {
			
			String queryStr="select * from OPS$DIR.FPO_SITE_ALLOT";
			Query query = entityManager.createNativeQuery(queryStr, FpoSite_Allot.class);
	//		query.setParameter("cussite", cussite);
			result = (List<FpoSite_Allot>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public DetentionTrack getDetainDetail(String itemId) {
		List<DetentionTrack> detentionTrack = new ArrayList<>();
		try {
			String queryStr = "select fmt.cus_site as cussite, fmt.cin_no articleId,fmt.ent_dt detentionDate,fmt.officer_id || '-' || def.emp_name detentionOfficer,"
					+ "fdi.detain_decision decisionTaken,fdi.detain_decision_dt decisionDate,"
					+ "fmt.officer_id || '-' || def.emp_name decisionOfficer from (select * from (select a.item_id as mvntitemid,b.* "
					+ "from fpo_main a join fpo_mvmnt b on (a.cin_no=b.cin_no))) fmt left join fpo_detained_info fdi on "
					+ "(fmt.mvntitemid=fdi.article_id) join "
					+ "ops$dir.d_emp def on (fmt.officer_id=def.user_id or fdi.off_id=def.user_id) where fmt.stage='P5' and "
					+ "fmt.cin_no=(select cin_no from fpo_main where item_id=:itemId)";
			Query query = entityManager.createNativeQuery(queryStr, DetentionTrack.class);
			query.setParameter("itemId",itemId);
			detentionTrack = (List<DetentionTrack>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detentionTrack.size() > 0 ? detentionTrack.get(0) : new DetentionTrack();
	}
	
	

	public List<DeliveryArticlesColumns> getDeliveryArticlesColumns(String itemId) {
		String queryStr = "select fd.id,fd.item_id,DELI_PO,to_char(DELI_DT , 'dd/mm/yyyy') as DELI_DT,status_desc as DELI_STATUS, dc.mode_desc as DELI_MODE,codedesc mailclass,'YES' eadstatus,  m.postingdt, m.cus_site cussite, ooc_dt oocdt "
				+ "from fpo_delivery fd left join deli_status_codes dc on (dc.status_code=deli_status) left join deli_mode_codes dc on (fd.deli_mode=dc.mode_code), fpo_main m,fpo_status s,ops$dir.pdi_mail_class_codes p where fd.item_id =:itemId and fd.item_id=s.item_id and fd.item_id=m.item_id and mail_class_cd=p.code";
		Query query = entityManager.createNativeQuery(queryStr, DeliveryArticlesColumns.class);
		query.setParameter("itemId",itemId);
		return (List<DeliveryArticlesColumns>) query.getResultList();
	}

	public List<MisReportArticleStatus> getMisReportArticleStatus(
			HttpSession session, String itemid, HttpServletRequest request) {
		List<MisReportArticleStatus> reportColumn = new ArrayList<MisReportArticleStatus>();
		int st = 0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();

			String queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,nvl(DUTY_AMT,0) other_duty,cus_site as cussite,BCD_AMT,IGST_AMT,SW_AMT,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,\r\n"
					+ "to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL,TOT_DUTY, nvl(TOT_DUTY_FG,0) TOT_DUTY_FG, \r\n"
					+ "TOT_AMT_PAYABLE,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_status,decode(b.OOC_DT,null,'OOC Not Given',concat('OOC Given On ',to_char(b.OOC_DT , 'dd/mm/yyyy'))) OOC_DT,\r\n"
					+ "'' curque,decode(fqd.QRY_DT,null,'Query Raised','Query Not Raised') qrystatus,fdi.detain_decision detention_status,decode(aai.RECD_EVENT_DT,null,'Article Not Arrived',concat('Article Arrived on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))) arrivaldt FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_detained_info fdi on  (a.item_id=fdi.article_id) left join fpo_delivery fd on (a.item_id=fd.item_id)  left join \r\n"
					+ "ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join \r\n"
					+ "deli_status_codes dc on (dc.status_code=deli_status) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join \r\n"
					+ "fpo_qry_deci fqd on (a.item_id=fqd.item_id) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where a.item_id=:itemid and a.cus_site=:cuSite";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,nvl(DUTY_AMT,0) other_duty,cus_site as cussite,BCD_AMT,IGST_AMT,SW_AMT,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,\r\n"
						+ "to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,pdm.CODEDESC as mail_class ,pdi.CATEGORY as item_category,(select max(item_no) FROM fpo_item_det where item_id=a.item_id) noofitem,TOT_ASS_VAL,TOT_DUTY, nvl(TOT_DUTY_FG,0) TOT_DUTY_FG, \r\n"
						+ "TOT_AMT_PAYABLE,case when dc.status_desc is null then 'Delivery Acknowledgement Pending' when dc.status_desc='DELIVERED' then 'DELIVERED' else 'NOT DELIVERED' end as delivery_status,decode(b.OOC_DT,null,'OOC Not Given',concat('OOC Given On ',to_char(b.OOC_DT , 'dd/mm/yyyy'))) OOC_DT,\r\n"
						+ "'' curque,decode(fqd.QRY_DT,null,'Query Raised','Query Not Raised') qrystatus,fdi.detain_decision detention_status,decode(aai.RECD_EVENT_DT,null,'Article Not Arrived',concat('Article Arrived on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))) arrivaldt FROM fpo_main a join fpo_status b on (a.item_id = b.item_id) left join fpo_detained_info fdi on  (a.item_id=fdi.article_id) left join fpo_delivery fd on (a.item_id=fd.item_id)  left join \r\n"
						+ "ops$dir.pdi_nature_trans_codes pdi on (pdi.code=a.nature_type_cd) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code) left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join \r\n"
						+ "deli_status_codes dc on (dc.status_code=deli_status) left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join \r\n"
						+ "fpo_qry_deci fqd on (a.item_id=fqd.item_id) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where a.item_id=:itemid";
				st = 1;
			}
			System.out.println(queryStr);
			Query query = entityManager.createNativeQuery(queryStr, MisReportArticleStatus.class);
			query.setParameter("itemid", itemid);
			if (st == 0)
				query.setParameter("cuSite", cuSite);
			reportColumn = (List<MisReportArticleStatus>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportOOCCancel> getMisReportOOCCancel(String fromdate, String enddate, HttpSession session,
			String[] sitevalue, int cou2, HttpServletRequest request) {
		List<MisReportOOCCancel> reportColumn = new ArrayList<MisReportOOCCancel>();
		int st = 0;
		
		String sc0=null;
		String sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
			String queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,decode(OOC_CANCEL_DT,null,to_char(b.OOC_DT , 'dd/mm/yyyy'),(select to_char(min(EXT_DT) , 'dd/mm/yyyy') from FPO_MVMNT where item_id=a.item_id and stage='P8')) OOC_DT,\r\n"
					+ "decode(OOC_CANCEL_DT,null,'',to_char(foo.OOC_CANCEL_DT , 'dd/mm/yyyy')) OOC_CancelDT,TOT_ASS_VAL,TOT_DUTY,nvl(TOT_DUTY_FG,0) TOT_DUTY_FG,TOT_AMT_PAYABLE,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,decode(OOC_CANCEL_DT,null,'',to_char(b.OOC_DT , 'dd/mm/yyyy')) RevisedOOC_DT,'' status  FROM fpo_main a join fpo_status b on \r\n"
					+ "(a.item_id = b.item_id) left join FPO_OOC_CANCEL_INFO foo on (a.item_id=foo.ARTICLE_ID)  left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET \r\n"
					+ "group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where  trunc(b.OOC_DT) between to_date "
					+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite ";
			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,decode(OOC_CANCEL_DT,null,to_char(b.OOC_DT , 'dd/mm/yyyy'),(select to_char(min(EXT_DT) , 'dd/mm/yyyy') from FPO_MVMNT where item_id=a.item_id and stage='P8')) OOC_DT,\r\n"
						+ "decode(OOC_CANCEL_DT,null,'',to_char(foo.OOC_CANCEL_DT , 'dd/mm/yyyy')) OOC_CancelDT,TOT_ASS_VAL,TOT_DUTY,nvl(TOT_DUTY_FG,0) TOT_DUTY_FG,TOT_AMT_PAYABLE,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,decode(OOC_CANCEL_DT,null,'',to_char(b.OOC_DT , 'dd/mm/yyyy')) RevisedOOC_DT,'' status  FROM fpo_main a join fpo_status b on \r\n"
						+ "(a.item_id = b.item_id) left join FPO_OOC_CANCEL_INFO foo on (a.item_id=foo.ARTICLE_ID)  left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET \r\n"
						+ "group by item_id) fi on (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where  trunc(b.OOC_DT) between to_date "
						+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
			st=1;
				 System.out.println("querystr is " + queryStr); 
				  // writing code for sitecode looping
				  
				  queryStr=queryStr+" and( ";
				  
				  for(int i=0;i<cou2;i++) { 
					  
					 if(i==0) {
					  sc0=sitevalue[i];
					  queryStr=queryStr+"(a.cus_site)=(:sc0)";
					 }
					 else {
					 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
					 }
				  } 
				  queryStr = queryStr
							+ " )";
				
				System.out.println("querystr is " + queryStr);
			
			}
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOOCCancel.class);
		if(st==1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
		}
			
			query.setParameter("fromdate", fromdate);
			query.setParameter("todate", enddate);
			
			 if (st == 0) 
				 query.setParameter("cuSite", cuSite);
			 
			reportColumn = (List<MisReportOOCCancel>) query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportFPOArrival> getMisReportFPOArrival( HttpSession session,
			String[] mailclass, int cou,String[] sitevalue, int cou2, HttpServletRequest request)  {
		List<MisReportFPOArrival> reportColumn = new ArrayList<MisReportFPOArrival>();
		int st = 0;
		String mc0=null,mc1=null,mc2=null,mc3=null;
		String sc0=null;
		String sc1=null;
		
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();
//			String queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,\r\n"
//					+ "pdm.CODEDESC as mail_class ,decode(a.ARR_FPO,null,decode(ARR_INDIA,null,'Yet to reach India',concat('Reached OOE / In Transit on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))),concat('Reached Destination FPO on '"
//					+ ",to_char(aaf.RECD_DT , 'dd/mm/yyyy'))) status  FROM fpo_main a left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)\r\n"
//					+ "left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join (select * from ARTICLEARR_FPO_INFO where  id in "
//					+ "(select max(id) from ARTICLEARR_FPO_INFO group by ARTICLE_ID)) aaf on (aaf.ARTICLE_ID=a.item_id) where  trunc(aai.RECD_EVENT_DT) between to_date "
//					+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite  ";
			
			String queryStr =" select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,\r\n"
					+ "pdm.CODEDESC as mail_class ,decode(a.ARR_FPO,null,decode(ARR_INDIA,null,'Yet to reach India',concat('Reached OOE / In Transit on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))),concat('Reached Destination FPO on '\r\n"
					+ ",to_char(decode(aaf.RECD_DT,null,aai.recd_event_dt,aaf.recd_dt) , 'dd/mm/yyyy'))) status  FROM fpo_main a left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)\r\n"
					+ "left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join (select * from ARTICLEARR_FPO_INFO where  id in \r\n"
					+ "(select max(id) from ARTICLEARR_FPO_INFO group by ARTICLE_ID)) aaf on (aaf.ARTICLE_ID=a.item_id) where  a.cus_site=:cuSite and arr_fpo is null ";

			if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP")
					|| request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
//				queryStr = "select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,\r\n"
//						+ "pdm.CODEDESC as mail_class ,decode(a.ARR_FPO,null,decode(ARR_INDIA,null,'Yet to reach India',concat('Reached OOE / In Transit on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))),concat('Reached Destination FPO on '"
//						+ ",to_char(aaf.RECD_DT , 'dd/mm/yyyy'))) status  FROM fpo_main a left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)\r\n"
//						+ "left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join (select * from ARTICLEARR_FPO_INFO where  id in "
//						+ "(select max(id) from ARTICLEARR_FPO_INFO group by ARTICLE_ID)) aaf on (aaf.ARTICLE_ID=a.item_id) where  trunc(aai.RECD_EVENT_DT) between to_date "
//						+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy') ";
				 queryStr =" select ROW_NUMBER() OVER (ORDER BY a.job_dt desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,dtc.CNTRY_NM as coo,to_char(a.CIN_DT , 'dd/mm/yyyy') as ead_date,\r\n"
						+ "pdm.CODEDESC as mail_class ,decode(a.ARR_FPO,null,decode(ARR_INDIA,null,'Yet to reach India',concat('Reached OOE / In Transit on ',to_char(aai.RECD_EVENT_DT , 'dd/mm/yyyy'))),concat('Reached Destination FPO on '\r\n"
						+ ",to_char(decode(aaf.RECD_DT,null,aai.recd_event_dt,aaf.recd_dt) , 'dd/mm/yyyy'))) status  FROM fpo_main a left join OPS$DIR.d_cntry_cd dtc on (a.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)\r\n"
						+ "left join (select * from article_arr_info where  id in (select max(id) from article_arr_info group by ARTICLE_ID)) aai on (aai.ARTICLE_ID=a.item_id) left join (select * from ARTICLEARR_FPO_INFO where  id in \r\n"
						+ "(select max(id) from ARTICLEARR_FPO_INFO group by ARTICLE_ID)) aaf on (aaf.ARTICLE_ID=a.item_id) where arr_fpo is null ";
				st = 1;
			
			  System.out.println("querystr is " + queryStr); 
			  // writing code for sitecode looping
			  
			  queryStr=queryStr+" and(";
			  
			  for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				  sc0=sitevalue[i];
				  queryStr=queryStr+"(a.cus_site)=(:sc0)";
				 }
				 else {
				 queryStr=queryStr+"or (a.cus_site)=(:sitevalue" + i + ")";
				 }
			  } 
			  queryStr = queryStr
						+ ")";
			
			System.out.println("querystr is " + queryStr);
			}
			
			queryStr=queryStr+" and( ";
			for (int i = 0; i < cou; i++) {
				if (i == 0) {
					mc0 = mailclass[i];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0) ";
				} else if (i == 1) {
					mc1 = mailclass[1];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc1)";
				} else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc2)";
				} else if (i == 3) {
					mc3 = mailclass[3];
					queryStr = queryStr + "or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			queryStr = queryStr
					+ " ) order by job_dt  desc,job_no";
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportFPOArrival.class);
			if (st == 0)
			query.setParameter("cuSite", cuSite);
			
			for (int i = 0; i < cou; i++) {
				if (i == 0)
					query.setParameter("mc0", mc0);
				else if (i == 1)
					query.setParameter("mc1", mc1);
				else if (i == 2)
					query.setParameter("mc2", mc2);
				else if (i == 3)
					query.setParameter("mc3", mc3);
			}
			if(st==1) {
			 for(int i=0;i<cou2;i++) { 
				  
				 if(i==0) {
				 query.setParameter("sc0", sc0);
				 }
				 else {
				        sc1=sitevalue[i];
				        query.setParameter("sitevalue" + i , sc1);
				    }
			  }
			}
			 
		//	query.setParameter("fromdate", fromdate);
		//	query.setParameter("todate", enddate);
			System.out.println("successfully querry written");
			reportColumn = (List<MisReportFPOArrival>) query.getResultList();
			System.out.println("successfully got result");
		} 
			catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportOfficersArticles> getMisReportOfficersArticles(String ssoid, String role,
			String fromdate, String todate,String[] mailclass, int cou,String[] siteco,int sitco, HttpSession session, HttpServletRequest request) {
		List<MisReportOfficersArticles> reportColumn=new ArrayList<MisReportOfficersArticles>();
		int st=0;
		String mc0=null, mc1=null,mc2=null,mc3=null;
		String sc0=null,sc1=null;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();							
			String queryStr="select ROW_NUMBER() OVER (ORDER BY fr.DISPORD) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,pdm.CODEDESC as mail_class,TOT_ASS_VAL,TOT_DUTY,\r\n"
					+ "case when fm.role='PAO' then 'PAO - Assessment' when fm.role='PAC' then 'PAC - Assessment' when fm.role='PSU' then 'PSU - OOC' when fm.role='PIN' then 'PIN - Examination' end as roletype,"
					+ "nvl(TOT_DUTY_FG,0) TOT_DUTY_FG,TOT_AMT_PAYABLE,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,to_char(b.OOC_DT , 'dd/mm/yyyy') OOC_DT  FROM fpo_main a join fpo_status b on (a.item_id = b.item_id)"
					+ " join (select * from FPO_MVMNT where id in (select max(id) from FPO_MVMNT where OFFICER_ID=:ssoid and role<>'PBS' group by role,cin_no)) fm on (a.cin_no=fm.cin_no) join ops$dir.FPO_ROLES fr on (fr.ROLE=fm.ROLE)\r\n"
					+ " left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)  left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on \r\n"
					+ " (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where  trunc(b.OOC_DT) between to_date "
					+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite and (";
			
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") || request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				 queryStr="select ROW_NUMBER() OVER (ORDER BY fr.DISPORD) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,pdm.CODEDESC as mail_class,TOT_ASS_VAL,TOT_DUTY,\r\n"
							+ "case when fm.role='PAO' then 'PAO - Assessment' when fm.role='PAC' then 'PAC - Assessment' when fm.role='PSU' then 'PSU - OOC' when fm.role='PIN' then 'PIN - Examination' end as roletype,"
							+ "nvl(TOT_DUTY_FG,0) TOT_DUTY_FG,TOT_AMT_PAYABLE,nvl(DUTY_AMT,0) other_duty,BCD_AMT,IGST_AMT,SW_AMT,to_char(b.OOC_DT , 'dd/mm/yyyy') OOC_DT  FROM fpo_main a join fpo_status b on (a.item_id = b.item_id)"
							+ " join (select * from FPO_MVMNT where id in (select max(id) from FPO_MVMNT where OFFICER_ID=:ssoid and role<>'PBS' group by role,cin_no)) fm on (a.cin_no=fm.cin_no) join ops$dir.FPO_ROLES fr on (fr.ROLE=fm.ROLE)\r\n"
							+ " left join OPS$DIR.pdi_mail_class_codes pdm on (a.MAIL_CLASS_CD=pdm.code)  left join (select item_id,sum(BCD_AMT) BCD_AMT,sum(IGST_AMT) IGST_AMT,sum(SW_AMT) SW_AMT FROM fpo_ITEM_DET group by item_id) fi on \r\n"
							+ " (a.item_id=fi.item_id) left join (select item_id,sum(DUTY_AMT) DUTY_AMT FROM fpo_ITEM_DET_OTHDUTY group by item_id) fio on (a.item_id=fio.item_id) where  trunc(b.OOC_DT) between to_date "
							+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate, 'dd/MM/yyyy')";
				 st=1;
				 
				 for (int i=0 ; i <sitco ; i++)
					{
						if(i==0) {
							sc0=siteco[i];
							queryStr=queryStr+" and ((a.cus_site)=(:sc0)";
						 }
						else {
						queryStr = queryStr + " or trim(a.cus_site) = trim(:sc" + i + ")";
						}
					
					}
					queryStr = queryStr + ") and (";
				 
			}
			
			for (int i=0 ; i <cou ; i++)
			{
				if (i==0)
				{
					mc0=mailclass[0];
					queryStr = queryStr + "trim(pdm.CODEDESC) = trim(:mc0)";
				}
				else if (i ==1 ) {
					mc1=mailclass[1];
					queryStr = queryStr + " or  trim(pdm.CODEDESC) = trim(:mc1)";
				}
				else if (i == 2) {
					mc2 = mailclass[2];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc2)";
				}
				else if (i ==3) {
					mc3 = mailclass[3];
					queryStr = queryStr + " or trim(pdm.CODEDESC) = trim(:mc3)";
				}
			}
			
			queryStr = queryStr + ")";
			
			Query query = entityManager.createNativeQuery(queryStr, MisReportOfficersArticles.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			query.setParameter("ssoid",ssoid);
			for (int i=0 ; i < cou ; i++)
			{
				if(i==0)
					query.setParameter("mc0", mc0);
				else if (i==1)
					query.setParameter("mc1", mc1);
				else if (i==2)
					query.setParameter("mc2", mc2);
				else if (i==3)
					query.setParameter("mc3", mc3);
				
				
			}
			
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			if (st==1) 
				for (int i=0; i<sitco; i++) {
					if(i ==0) {
						query.setParameter("sc0", sc0);
						}else {
							sc1=siteco[i];
							query.setParameter("sc"+ i, sc1);
							}
						}  
					     
			reportColumn = (List<MisReportOfficersArticles>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<MisReportDCall> getMisReportDCall(String fromdate, String todate, HttpSession session, HttpServletRequest request) {
		List<MisReportDCall> reportColumn=new ArrayList<MisReportDCall>();
		int st=0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			
			
			String queryStr="select ROW_NUMBER() OVER (ORDER BY b.GEN_DT desc) id,a.item_id,a.cus_site as cussite,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldate,\r\n"
					+ "to_char(b.GEN_DT , 'dd/mm/yyyy') pushdcalldate,b.OFF_ID officer from fpo_main a join PUSH_DCALL b on (a.item_id = b.item_id) join DCALLQRY_GEN c on (a.item_id = c.item_id) where  trunc(c.GEN_DT) between to_date "
					+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite order by b.GEN_DT desc";
			
			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") || request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
				queryStr="select ROW_NUMBER() OVER (ORDER BY b.GEN_DT desc) id,a.cus_site as cussite,a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy')  post_dt,c.DCALL_NO dcallno,to_char(c.GEN_DT , 'dd/mm/yyyy') dcalldate,\r\n"
						+ "to_char(b.GEN_DT , 'dd/mm/yyyy') pushdcalldate,b.OFF_ID officer from fpo_main a join PUSH_DCALL b on (a.item_id = b.item_id) join DCALLQRY_GEN c on (a.item_id = c.item_id) where  trunc(c.GEN_DT) between to_date "
						+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') order by b.GEN_DT desc";
				st=1;
			}
			Query query = entityManager.createNativeQuery(queryStr, MisReportDCall.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			if (st==0)
			   query.setParameter("cuSite", cuSite);
			reportColumn = (List<MisReportDCall>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}

	public List<String> getRolesbySsoid(String ssoid) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String queryStr="select distinct ROLE_NAME from ops$dir.D_EMP_ROLES where USER_ID=:ssoid";
			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("ssoid", ssoid);
			result = (List<String>) query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<MisReportPBSOfficersArticles> getMisReportPbsOfficersArticles(String ssoid, String fromdate, String todate,
			HttpSession session, HttpServletRequest request) {
		List<MisReportPBSOfficersArticles> reportColumn=new ArrayList<MisReportPBSOfficersArticles>();
		int st=0;
		try {
			String cuSite = request.getSession().getAttribute("cuSite").toString();				
			//String queryStr="select ROW_NUMBER() OVER (ORDER BY scan_dt desc) id, arrdt, a.BAG_NO data,SCAN_DT value,a.cus_site as cussite FROM fpo_scan_info a, articlearr_fpo_info b, fpo_status c where  a.bag_no=b.bag_no  and a.OFF_ID=:ssoid and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') and a.cus_site=:cuSite group by  a.bag_no,scan_dt,a.cus_site";
			String queryStr="select ROW_NUMBER() OVER (ORDER BY scandt desc) id, 'PBS - Postal Bag Scan Entry' as roletype,bagno,scandt,arrdt, cussite from \r\n"
			+ "(select a.BAG_NO bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, articlearr_fpo_info b, fpo_status c \r\n"
			+ "where  a.bag_no=b.bag_no  and a.OFF_ID=:ssoid  and a.cus_site=:cuSite  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') \r\n"
			+ "union \r\n"
			+ "select b.recp_id bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_event_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, article_arr_info b, fpo_status c \r\n"
			+ "where  a.bag_no=b.recp_id and a.OFF_ID=:ssoid   and a.cus_site=:cuSite  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') )";

			if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("NRP") || request.getSession().getAttribute("role").toString().equalsIgnoreCase("PNA")) {
			/*	queryStr="select id,BAG_NO data,SCAN_DT value,a.cus_site as cussite FROM fpo_main a join fpo_status b on (a.item_id = b.item_id)"
						+ " join (select * from FPO_MVMNT where id in (select max(id) from FPO_MVMNT where OFFICER_ID='"+ssoid+"' and role<>'PBS' group by role,cin_no)) fm on (a.cin_no=fm.cin_no) join ops$dir.FPO_ROLES fr on (fr.ROLE=fm.ROLE)\r\n"
						+ "join FPO_SCAN_INFO fs on (fs.ARTICLE_ID=a.item_id) where  trunc(b.OOC_DT) between to_date "
						+ "(:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') ";*/
			//	queryStr="select ROW_NUMBER() OVER (ORDER BY scan_dt desc) id, a.BAG_NO data,SCAN_DT value,a.cus_site as cussite FROM fpo_scan_info a, articlearr_fpo_info b, fpo_status c where  a.bag_no=b.bag_no  and a.OFF_ID='"+ssoid+"' and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date (:fromdate,'dd/MM/yyyy') AND TO_DATE (:todate,'dd/MM/yyyy') group by  a.bag_no,scan_dt,a.cus_site" ;
				queryStr="select ROW_NUMBER() OVER (ORDER BY scandt desc) id, 'PBS - Postal Bag Scan Entry' as roletype,bagno,scandt,arrdt, cussite from \r\n"
				+ "(select a.BAG_NO bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, articlearr_fpo_info b, fpo_status c \r\n"
				+ "where  a.bag_no=b.bag_no  and a.OFF_ID=:ssoid  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy') \r\n"
				+ "union \r\n"
				+ "select b.recp_id bagno,to_char(scan_dt,'DD/MM/YYYY') scandt,to_char(recd_event_dt,'DD/MM/YYYY') arrdt,a.cus_site as cussite FROM fpo_scan_info a, article_arr_info b, fpo_status c \r\n"
				+ "where  a.bag_no=b.recp_id and a.OFF_ID=:ssoid  and role='PBS' and b.article_id=c.item_id and trunc(ooc_dt) between to_date(:fromdate, 'dd/MM/yyyy') AND TO_DATE(:todate, 'dd/MM/yyyy'))";
				st=1;
			}
			Query query = entityManager.createNativeQuery(queryStr, SelectTag.class);
			query.setParameter("fromdate",fromdate);
			query.setParameter("todate", todate);
			query.setParameter("ssoid", ssoid);
			if (st==0)
			   query.setParameter("cuSite", cuSite);

			reportColumn = (List<MisReportPBSOfficersArticles>) query.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reportColumn;
	}






}
