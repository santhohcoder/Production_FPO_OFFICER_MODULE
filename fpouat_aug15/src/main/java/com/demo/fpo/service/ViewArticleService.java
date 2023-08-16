package com.demo.fpo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.bean.ArrivalHistoryBean;
import com.demo.fpo.bean.ArrivalHistoryBean2;
import com.demo.fpo.bean.ArticleArrivalInfoBean;
import com.demo.fpo.bean.ArticleDatas;
import com.demo.fpo.bean.BagArticleBean;
import com.demo.fpo.bean.BagScanBean;
import com.demo.fpo.bean.CustomIdName;
import com.demo.fpo.bean.FpoMovement;
import com.demo.fpo.bean.FpoScndQryData;
import com.demo.fpo.bean.NONARTARRINFO;
import com.demo.fpo.bean.RegulationRecptIDBean;
import com.demo.fpo.bean.ViewArticle;
import com.demo.fpo.controller.LoginController;
import com.demo.fpo.model.FpoScanInfo;
import com.demo.fpo.model.Recently_process;
import com.demo.fpo.bean.ooedata;
import com.demo.fpo.apiservice.*;



@Service
@Component
public class ViewArticleService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	FPO_MAINrepost fporepost;
	
	@Autowired
	PreDesRestService PREDESservice;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*public ViewArticle getArticleDates(String id) {
		String qry = "select fm.cin_no as cinId,\r\n" + "to_char(fm.job_dt,'dd/MM/yyyy') as receiptOfCusItm,\r\n"
				+ "to_char(fqd.qry_dt,'dd/MM/yyyy') as eadDecision,\r\n"
				+ "to_char(fpm.ent_dt,'dd/MM/yyyy') as entryDate,\r\n"
				+ "to_char(fpm.ext_dt,'dd/MM/yyyy') as exitDate,\r\n"
				+ "to_char(qry_date,'dd/MM/yyyy') as queryDate,\r\n"
				+ "to_char(order_date,'dd/MM/yyyy') as orderDate,\r\n" + "to_char(ooc_dt,'dd/MM/yyyy') as oocDate\r\n"
				+ ",'' as status from fpo_main fm\r\n" + "left join \r\n"
				+ "(select * from fpo_qry_deci where id in (select max(id) from fpo_qry_deci where qry_type='E4' group by cin_no)) fqd\r\n"
				+ "on fm.cin_no=fqd.cin_no \r\n" + "left join \r\n"
				+ "(select * from fpo_mvmnt where id in (select max(id) from fpo_mvmnt group by cin_no)) fpm\r\n"
				+ "on fm.cin_no=fpm.cin_no \r\n" + "left join \r\n"
				+ "(select * from fpo_query where id in (select max(id) from fpo_query group by cin_no)) fq\r\n"
				+ "on fm.cin_no=fq.cin_no \r\n" + "left join \r\n"
				+ "(select * from fpo_order where id in (select max(id) from fpo_order group by cin_no)) fo\r\n"
				+ "on fm.cin_no=fo.cin_no \r\n" + "left join \r\n"
				+ "(select * from fpo_status where id in (select max(id) from fpo_status group by cin_no)) fs\r\n"
				+ "on fm.cin_no=fs.cin_no \r\n" + "where fm.cin_no='" + id + "'";
		try {
			Query query = entityManager.createNativeQuery(qry, ViewArticle.class);
			return (ViewArticle) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ViewArticle();
	}*/

/*	public Set<String> getArticleIds(HttpSession session) {
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		try {
			return new HashSet<String>(entityManager
					.createNativeQuery(
							"select distinct(item_id) from fpo_main where cus_site='" + cusSite + "'")
					.getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new HashSet<>();
	}*/

	public List<String> getArticleIdByFilteredDates(Boolean isReceiptOfcusItm, Boolean eadDate, Boolean entryDate,
			Boolean exitDate, Boolean queryDate, Boolean orderDate, Boolean oocDate, String fromDate, String toDate,
			HttpSession session, HttpServletRequest request) {
		List<String> articleIds = null;
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		if (isReceiptOfcusItm) {
		/*	try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_main where trunc(job_dt) between to_date ('"
								+ fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate
								+ "', 'dd/mm/yyyy') and cus_site='" + cusSite + "'")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
		    articleIds.addAll(fporepost.getArticleIdsubdt(fromDate, toDate, cusSite));
		}
		if (eadDate) {
		/*	try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_qry_deci where id in (select max(id) from fpo_qry_deci group by cin_no) "
								+ "and qry_type='E4' and trunc(qry_dt) between to_date ('" + fromDate
								+ "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate + "', 'dd/mm/yyyy') and cus_site='"
								+ cusSite + "'")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdeaddt(fromDate, toDate, cusSite));
		}
		if (entryDate) {
			/*try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_mvmnt where id in "
								+ "(select max(id) from fpo_mvmnt group by cin_no) and trunc(ent_dt) between to_date ('"
								+ fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate
								+ "', 'dd/mm/yyyy') and cus_site='" + cusSite + "')")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdmvmentdt(fromDate, toDate, cusSite));
		}
		if (exitDate) {
			/*try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_mvmnt where id in "
								+ "(select max(id) from fpo_mvmnt group by cin_no) and trunc(ext_dt) between to_date ('"
								+ fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate
								+ "', 'dd/mm/yyyy') and cus_site='" + cusSite + "')")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdmvmextdt(fromDate, toDate, cusSite));
		}
		if (queryDate) {
			/*try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_query where id in (select max(id) from fpo_query group by cin_no) and "
								+ "trunc(qry_date) between to_date ('" + fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('"
								+ toDate + "', 'dd/mm/yyyy') and cus_site='" + cusSite + "'")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdqrydt(fromDate, toDate, cusSite));

		}
		if (orderDate) {
		/*	try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_main where cin_no in (select distinct(cin_no) from fpo_order where id in "
								+ "(select max(id) from fpo_order group by cin_no) and trunc(order_date) between to_date ('"
								+ fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate
								+ "', 'dd/mm/yyyy') and cus_site='" + cusSite + "')")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdorddt(fromDate, toDate, cusSite));
		}
		if (oocDate) {
			/*try {
				articleIds.addAll(entityManager.createNativeQuery(
						"select distinct(item_id) from fpo_status where id in (select max(id) from fpo_status group by cin_no) and "
								+ "trunc(ooc_dt) between to_date ('" + fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('"
								+ toDate + "', 'dd/mm/yyyy') and cus_site='" + cusSite + "'")
						.getResultList());
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			articleIds.addAll(fporepost.getArticleIdoocdt(fromDate, toDate, cusSite));
		}
		return articleIds;
	}

/*	public List<FpoMovement> getFpoMovementsByCinId(String cinId) {
		String qry = "select fm.slno as sNo,to_char(fm.ent_dt,'dd/MM/yyyy HH:MI:SS AM') as entryDate,"
				+ "to_char(fm.ext_dt,'dd/MM/yyyy HH:MI:SS AM') as exitDate,fm.officer_id as officerId,fm.role as roleName,fs.view_action as status "
				+ " from fpo_mvmnt fm left join fpo_stages fs on fm.stage=fs.stage_code where fm.cin_no='" + cinId
				+ "' and fm.officer_id is not null and fm.role is not null order by fm.slno";
		try {
			Query query = entityManager.createNativeQuery(qry, FpoMovement.class);
			return (List<FpoMovement>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}*/
	
	
	

	public List<CustomIdName> fetchCustomQueries(String qry) {
		try {
			Query query = entityManager.createNativeQuery(qry, CustomIdName.class);
			List<CustomIdName> customResult = (List<CustomIdName>) query.getResultList();
			entityManager.clear();
			return customResult;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	public List<NONARTARRINFO> getfiltdata(String mc, String cntry, String cuSite, String frdt, String todt,String bagtype,String fpo)
	{

		List<NONARTARRINFO> pendfiltdata=null;
		String qry=null;
		if (bagtype.equals("B"))
		{    qry = "SELECT t1.item_id articleId, t1.cin_no cinno, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postingdt, t1.send_cntry_cd||' - '||cntry_nm cntryCD, t3.category category,"
				+" t5.codedesc codeDesc,decode(clr_site,null,t1.cus_site,clr_site) cussite , ooe,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS') recdeventDt,p.recp_id precpid, a.recp_id arecpid,   recd_fpo recdfpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,bag_no bagno"
				+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1  left join (select max(id),article_id from article_predes_info group by article_id)d on t1.item_id=d.article_id \r\n"
				+ "             left join article_predes_info p on d.article_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join \r\n"
				+ "			 articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c   where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null  "
				+" and decode(substr(ooe,1,5),null,substr(:fpo,1,5),'INCOK','INKOC',substr(ooe,1,5))=substr(:fpo,1,5) and c.site_code=:cuSite and decode(clr_site,null,decode(t1.cus_site,null,:cuSite,t1.cus_site),clr_site)=:cuSite and   ((arr_india='Y' or arr_india is null) and ( bag_no is null))";}
		else
		{qry = "SELECT t1.item_id articleId, t1.cin_no cinno, to_char(t1.cin_dt,'DD/MM/YYYY HH24:MI:SS') cindt, substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4) postingdt, t1.send_cntry_cd||' - '||cntry_nm cntryCD, t3.category category,"
				+" t5.codedesc codeDesc,decode(clr_site,null,t1.cus_site,clr_site) cussite , ooe,to_char(recd_event_dt,'DD/MM/YYYY HH24:MI:SS') recdeventDt,p.recp_id precpid, a.recp_id arecpid,   recd_fpo recdfpo,to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS') recdDt,bag_no bagno"
				+" FROM ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 ,   fpo_main t1 left join (select max(id),article_id from article_predes_info group by article_id)d on t1.item_id=d.article_id \r\n"
				+ "             left join article_predes_info p on d.article_id=p.article_id left join article_arr_info a on t1.item_id=a.article_id left join\r\n"
				+ "				 articlearr_fpo_info f on t1.item_id=f.article_id,ops$dir.d_cntry_cd y, ops$dir.fpo_sites c  where t1.send_cntry_cd=y.cntry_cd and t1.mail_class_cd=t5.code  and a.status is null and f.status is null and t1.nature_type_cd=t3.code and arr_scan is null  "
				+" and (arr_fpo is null and c.site_code=:cuSite and (arr_india='Y' or arr_india is null))  and (a.recp_id is null and bag_no is null)";}
//		+" and c.site_code=:cuSite and decode(clr_site,null,decode(t1.cus_site,null,:cuSite,t1.cus_site),clr_site)=:cuSite and   decode(arr_fpo,null,decode(arr_india,null,null,arr_india),arr_fpo) is null and (a.recp_id is null and bag_no is null)";}
	    if (mc.length() > 0)
		  qry=qry+ " and t1.mail_class_cd=:mc";
	  //  if (fpo.length()>0)
	  //    qry=qry+ " and  (decode(substr(a.ooe,1,5),null,substr(p.recp_id,7,5),substr(a.ooe,1,5))=substr(:fpo,1,5))";
		if (cntry.length() > 0 )
		  qry=qry+" and t1.send_cntry_cd=:cntry ";
		if (frdt.length() > 0 && todt.length() > 0)
		  qry=qry	+" and  to_date(substr(t1.postingdt,9,2)||'/'||substr(t1.postingdt,6,2)||'/'||substr(t1.postingdt,0,4),'DD/MM/YYYY') between to_date(:frdt,'DD/MM/YYYY') and to_date(:todt, 'DD/MM/YYYY')  ";
		qry=qry +" and  (substr(decode(ooe,null,:cuSite,ooe),1,5)=substr(map_code,1,5) or substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5))  order by t1.job_no";
	//	qry=qry +" and  substr(decode(recd_fpo,null,:cuSite,recd_fpo),1,5)=substr(map_code,1,5) order by t1.job_no";
		try {
		Query query = entityManager.createNativeQuery(qry, NONARTARRINFO.class);
		if (mc.length() > 0)
			 query.setParameter("mc",mc);
		if (cntry.length() > 0)
		     query.setParameter("cntry",cntry);
		if (bagtype.equals("B"))
			query.setParameter("fpo",fpo);
	//	if (fpo.length() > 0)
	//		query.setParameter("fpo",fpo);
		if (frdt.length() > 0 && todt.length() > 0)
		{ query.setParameter("frdt",frdt);
		 query.setParameter("todt",todt);}
		query.setParameter("cuSite", cuSite);
		pendfiltdata =  ((List<NONARTARRINFO>) query.getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pendfiltdata;
	}
	
	
	

	/*public List<ArticleDatas> getArticleDatas(String fromDate, String toDate, Boolean itemPosted, Boolean eadArrival,
			Boolean queryRaised, Boolean queryReplied, Boolean articalArrivalOOE, Boolean oocGiven,
			String senderCountry, String mailCategory, String mailClass, String articleId, HttpSession session, HttpServletRequest request) */
	public List<ArticleDatas> getArticleDatas(String articleId, HttpSession session, HttpServletRequest request){
		String cusSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		List<String> articleIds = null;
		String qry1 = "select cin_no as cinNo,item_id as itemId,to_char(to_date(postingdt,'YYYY-MM-DD\"T\"HH24:MI:SS'),'dd/MM/yyyy HH:MI:SS AM') "
				+ "as postingDate,mcat.interpretation as mailCategory,"
				+ "mclass.interpretation as mailClass,country.cntry_nm as country,'' as status from fpo_main fm "
				+ "left join ops$dir.pdi_nature_trans_codes mcat on fm.nature_type_cd=mcat.code  "
				+ "left join ops$dir.pdi_mail_class_codes mclass on fm.mail_class_cd=mclass.code "
				+ "left join ops$dir.d_cntry_cd country on fm.send_cntry_cd=country.cntry_cd";
		boolean condition = false;
		String qryparam=null;
		/*if (fromDate != null && toDate != null
				&& (itemPosted || eadArrival || queryRaised || queryReplied || articalArrivalOOE || oocGiven)) {
			if (itemPosted) {
			    qryparam="select distinct(item_id) from fpo_main where to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd') between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site= :cusSite";
			}
			if (eadArrival) {
			    qryparam="select distinct(item_id) from fpo_main where trunc(job_dt) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and cus_site=:cusSite";
			}
			if (queryRaised) {
				qryparam="select distinct(item_id) from fpo_query where id in (select max(id) from fpo_query group by cin_no) and trunc(qry_date) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site = :cusSite";
			}
			if (queryReplied) {
			    qryparam="select distinct(item_id) from fpo_query where id in (select max(id) from fpo_query group by cin_no) and trunc(rply_date) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site = :cusSite";
			}
			if (articalArrivalOOE) {
				qryparam="select distinct(article_id) from article_arr_info where id in (select max(id) from article_arr_info group by article_id) and trunc(recd_event_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND TO_DATE (:toDate, 'dd/mm/yyyy') and ooe=:cusSite";	
			}
			if (oocGiven) {
				qryparam="select distinct(item_id) from fpo_status where  trunc(ooc_dt) between to_date (:fromDate , 'dd/mm/yyyy') AND TO_DATE (:toDate , 'dd/mm/yyyy') and cus_site=:cusSite";
			}*/
		
		//	String articleIdString = String.join("','", articleIds);
//			qry1 = qry1.concat(condition ? " and " : " where ").concat("item_id in ('" + articleIdString + "')");
		//	qry1 = qry1.concat(condition ? " and " : " where ").concat("item_id = :articleIdString ");
		//	qry1 = qry1 + " where item_id in (" + qryparam + ") ";
			condition = true;
			 int i=0,j=0,k=0,l=0;			 
			/*  if (senderCountry != null) {
			//	  qry1 = qry1.concat(condition ? " and " : " where ").concat("send_cntry_cd ='" + senderCountry + "'");
				  qry1 = qry1.concat(condition ? " and " : " where ").concat("send_cntry_cd =:senderCountry");
					condition = true;
					i=1;
				}
				if (mailCategory != null) {
				//	qry1 = qry1.concat(condition ? " and " : " where ").concat("mail_category_cd ='" + mailCategory + "'");
					qry1 = qry1.concat(condition ? " and " : " where ").concat("mail_category_cd =:mailCategory");
					condition = true;
					j=1;
				}
				if (mailClass != null) {
				//	qry1 = qry1.concat(condition ? " and " : " where ").concat("mail_class_cd ='" + mailClass + "'");
					qry1 = qry1.concat(condition ? " and " : " where ").concat("mail_class_cd =:mailClass");
					condition = true;
					k=1;
				}*/
				if (articleId != null) {
				//	qry1 = qry1.concat(condition ? " and " : " where ").concat("item_id ='" + articleId + "'");
				//	qry1 = qry1.concat(condition ? " and " : " where ").concat("item_id =:articleId");
					qry1 = qry1.concat(" where item_id=:articleId");
					condition = true;
					l=1;
				}
//				qry1 = qry1.concat(" and cus_site='" + cusSite + "'");
				qry1 = qry1.concat(" and (cus_site=:cusSite or  decode(clr_site,null,cus_site,clr_site)=:cusSite)");
				//if (i==1 && j==1 && k=1 && l==1)
				//	result.addAll(FPOrepost.getdatacntmccatit(qry1,articleIdString,sebderCountry,mailCategory,mailClass,articleId,cusSite));
				try {
					Query query = entityManager.createNativeQuery(qry1, ArticleDatas.class);
				//	query.setParameter("articleIdString",articleIdString );
				/*	if (fromDate != null && toDate != null)
					{
						query.setParameter("fromDate",fromDate);
						query.setParameter("toDate",toDate);
					}
					if (i==1)
					  query.setParameter("senderCountry",senderCountry );
					if (j==1)
					  query.setParameter("mailCategory",mailCategory);
					if (k==1)
					  query.setParameter("mailClass",mailClass);*/
					if (l==1)
					  query.setParameter("articleId",articleId);
					query.setParameter("cusSite", cusSite);
					return ((List<ArticleDatas>) query.getResultList());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			 // }

			return new ArrayList<>();
	}

	public List<FpoScndQryData> getFpoScndQryDataDoc(String cinNo) {
		String qry = "select INP_REQ,QRY_DEF_SLNO,QRY_DESC,QRY_ID from fpo_second_defqry a join fpo_addl_qry b on (a.SLNO=b.QRY_DEF_SLNO) where b.CIN_NO=:cinNo  order by a.slno,QRY_ID";
		try {
			Query query = entityManager.createNativeQuery(qry, FpoScndQryData.class);
			query.setParameter("cinNo", cinNo);
			return (List<FpoScndQryData>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	
	public List<ooedata> getooedata(String cusSite){
		List<ooedata> recpdata = null;
		if (cusSite.equalsIgnoreCase("INBOM5") || cusSite.equalsIgnoreCase("INBPS5") || cusSite.equalsIgnoreCase("INMAA5") || cusSite.equalsIgnoreCase("INFCH5"))
		{
//		String qryp= "sELECT recp_id,item_id from  fpo_main a, article_arr_info b where recp_id not in (select recp_id from fpo_scan_info) and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(:cusSite,1,5)),substr(ooe,1,5))=substr(:cusSite,1,5) and a.item_id=b.article_id and status is null and recp_id is not null and clr_site is null";
		String qryp= "sELECT recp_id,item_id from fpo_main a, article_arr_info b left join fpo_scan_info c on b.recp_id=c.bag_no where recp_id is not null and clr_site is null and b.article_id=a.item_id and c.bag_no is null and ( recp_id like '%INBOM%' or recp_id like '%INMAA%') and status is null and (a.cus_site='INBOM5' or a.cus_site='INBPS5' or a.cus_site='INMAA5' or a.cus_site='INFCH5')";
		
		try {
			Query query = entityManager.createNativeQuery(qryp, ooedata.class);
		//	query.setParameter("cusSite", cusSite);
			recpdata =query.getResultList();
        }
		 catch (Exception ex) {
			ex.printStackTrace();
		}   
		}
		return recpdata;
	}
	
	
	public List<BagScanBean> getBagScan(String cusSite) {
//		String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,totalCount,withEadCurrSite,withEadOtherSite,withoutEad,nvl(notallotted,0) withEadSiteNotAllot from\r\n"
//				+ "(select x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,withEadOtherSite,without_ead withoutEad,notallotted  from \r\n"
//				+ "(select count(*) with_ead_thissite,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'   and status is null group by recp_id) x \r\n"
//				+ "left join nvl(count(*),0) notallotted,(select count(*) withEadOtherSite,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)<>substr(a.cus_site,1,5) and a.cus_site <> '"
//				+ cusSite + "'   and status is null group by recp_id) l,\r\n"
//				+ "(select count(*) withEadSiteNotAllot,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by recp_id) j,\r\n"
//				+ "(select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and status is null group by recp_id)k,\r\n"
//				+ "(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id from fpo_main b ) and status is null group by recp_id) z,\r\n"
//				+ "(select count(*) tot_cou, recp_id from article_arr_info b where status is null group by recp_id  ) y\r\n"
//				+ "where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id and x.recp_id = l.recp_id and x.recp_id = j.recp_id\r\n"
//				+ "group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite,withEadOtherSite,without_ead,withEadSiteNotAllot\r\n" + "union\r\n"
//				+ "select x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad,withEadSiteNotAllot  from \r\n"
//				+ "(select count(*) with_ead_thissite,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'  and status is null group by bag_no) x,\r\n"
//				+ "(select count(*) withEadSiteNotAllot,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null  and status is null group by bag_no) j,\r\n"
//				+ "(select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and status is null group by bag_no) k,\r\n"
//				+ "(select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id from fpo_main b ) and status is null group by bag_no) z,\r\n"
//				+ "(select count(*) tot_cou, bag_no from articlearr_fpo_info b where status is null group by bag_no  ) y\r\n"
//				+ "where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no and x.bag_no = j.bag_no\r\n"
//				+ "group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite,without_ead,withEadSiteNotAllot)\r\n"
//				+ "order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no) from fpo_scan_info)";
		
		// Modified on 20th May 2022 to check whether SCANNING AT CLEARANCE SITE
		

	/*	String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
				+ "  (select with_ead_thissite,x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from   \r\n"
				+ "  (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and substr(b.ooe,1,5)=substr(c.map_code,1,5) and a.cus_site = '"+cusSite + "'   and status is null group by recp_id,clr_site) x left join  \r\n"
				+ "  (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on x.recp_id=j.recp_id left join   \r\n"
				+ "  (select nvl(count(*),0) withEadOtherSite,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and  substr(b.ooe,1,5)=substr(c.map_code,1,5) and a.cus_site <> 'INBOM5'    and status is null group by recp_id) l on x.recp_id=l.recp_id, \r\n"
				+ "  (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and substr(a.cus_site,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k,  \r\n"
				+ "  (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z,  \r\n"
				 + " (select count(*) tot_cou, recp_id  from article_arr_info b where status is null group by recp_id  ) y    \r\n"
				 + " where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id   \r\n"
				+ "  group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  \r\n"
				+ "  union  \r\n"
				+ "  select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
				+ "  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and a.cus_site = '"+cusSite + "'    and status is null group by bag_no,recd_fpo) x left join  \r\n"
				+ "  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
				+ "  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)<>substr(c.map_code,1,5) and a.cus_site <> 'INBOM5'    and status is null group by bag_no) l on x.bag_no=l.bag_no, \r\n"
				+ "  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k,  \r\n"
				+ "  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z,  \r\n"
				+ "  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null group by bag_no  ) y    \r\n"
				+ "  where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no  \r\n"
				+ "  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
				+ "  order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info)  \r\n";*/
		
		//THIS IS THE CORRECT ONE LASTLY EDITED.. REPLACED IN FPO_MAINREPOST... TO MAKE PARAMETERISED QUERY//
		/*String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
				+ " (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
				+ "   (select count(*) tot_cou, recp_id  from article_arr_info b where status is null and substr(recp_id,7,5)=substr('"+cusSite + "',1,5)  group by recp_id  ) y  left join  \r\n"
				+ "   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(a.cus_site,1,5)=substr(c.map_code,1,5) and a.clr_site = '"+cusSite + "'    and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n" 
				+ "   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n" 
				+ "   (select nvl(count(*),0) withEadOtherSite,recp_id from  article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and a.clr_site<>'"+cusSite + "' and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
				+ "   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(a.cus_site,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
				+ "   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
				+ "   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  \r\n"
				+ "  union  \r\n"
				+ "  select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
				+ "  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(b.recd_fpo,1,5) = substr('"+cusSite + "',1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
				+ "  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
				+ "  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and  substr(b.recd_fpo,1,5)<>substr(c.map_code,1,5) and substr(a.clr_site,1,5) <> substr('"+cusSite + "',1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no, \r\n"
				+ "  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k,  \r\n"
				+ "  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z,  \r\n"
				+ "  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null group by bag_no  ) y    \r\n"
				+ "  where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no  \r\n"
				+ "  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
				+ "  order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info)  \r\n";*/
		//THIS IS THE ABOVE CORRECT ONE LASTLY EDITED.. REPLACED IN FPO_MAINREPOST... TO MAKE PARAMETERISED QUERY//
		
		
		/*String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,totalCount,withEadCurrSite,withEadOtherSite,withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from\r\n"
				+ "(select x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,withEadOtherSite,without_ead withoutEad,notallotted,ooe siteValue  from \r\n"
				+ "(select count(*) with_ead_thissite,recp_id,ooe  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and substr(b.ooe,1,5)=substr(c.map_code,1,5) and a.clr_site = '"
				+ cusSite + "'   and status is null group by recp_id,ooe) x left join\r\n"
				+ "(select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.clr_site is null   and status is null group by recp_id) j on x.recp_id=j.recp_id,\r\n"
				+ "(select nvl(count(*),0) withEadOtherSite,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and  substr(b.ooe,1,5)<>substr(c.map_code,1,5) and a.clr_site <> '"
				+ cusSite + "'   and status is null group by recp_id) l,\r\n"
				+ "(select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.clr_site=c.site_code and substr(b.ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k,\r\n"
				+ "(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z,\r\n"
				+ "(select count(*) tot_cou, recp_id  from article_arr_info b where status is null group by recp_id  ) y  \r\n"
				+ "where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id and x.recp_id=l.recp_id\r\n"
				+ "group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite,withEadOtherSite,without_ead,notallotted,ooe\r\n"
				+ "union\r\n"
				+ "select x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from \r\n"
				+ "(select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and a.cus_site = '"
				+ cusSite + "'  and status is null group by bag_no,recd_fpo) x left join\r\n"
				+ "(select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no,\r\n"
				+ "(select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k,\r\n"
				+ "(select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z,\r\n"
				+ "(select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null group by bag_no  ) y  \r\n"
				+ "where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no\r\n"
				+ "group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite,without_ead,notallotted,recd_fpo)\r\n"
				+ "order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info)";*/
	
        	
	/*String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from\r\n"
			+ "			(select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clrsite siteValue  from   \r\n"
			+ "			  (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(ooe,1,5)),'INMAA',decode(:cusSite,'INFCH5','INFCH',substr(ooe,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join \r\n"
			+ "			  (select count(*) with_ead_thissite,recp_id,:cusSite clrsite from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and\r\n"
			+ "			decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)   \r\n"
			+ "			and decode(substr(decode(clr_site,null,a.cus_site,clr_site),1,5),'INBPS','INBOM','INFCH','INMAA',substr(decode(clr_site,null,a.cus_site,clr_site),1,5))=substr(ooe,1,5)\r\n"
			+ "			and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n"
			+ "			  (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n"
			+ "			  (select count(*) withEadOtherSite ,recp_id,:cusSite clrsite from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and\r\n"
			+ "			decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)   \r\n"
			+ "			and decode(substr(decode(clr_site,null,a.cus_site,clr_site),1,5),'INBPS','INBOM','INFCH','INMAA',substr(decode(clr_site,null,a.cus_site,clr_site),1,5))!=substr(ooe,1,5)\r\n"
			+ "			and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) l on y.recp_id=l.recp_id left join  \r\n"
			+ "				  (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join  \r\n"
			+ "			  (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id   \r\n"
			+ "			  where y.recp_id is not null and x.clrsite=:cusSite group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,\r\n"
			+ "              notallotted,\r\n"
			+ "              x.clrsite \r\n"
			+ "			 union \r\n"
			+ "			 select with_ead_thissite+withEadOtherSite with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  0 withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from  \r\n"
			+ "			 (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join \r\n"
			+ "			 (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join\r\n"
			+ "			 (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join\r\n"
			+ "			 (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join \r\n"
			+ "			 (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join \r\n"
			+ "			 (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no \r\n"
			+ "			 where x.bag_no is not null \r\n"
			+ "			 group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo) \r\n"
			+ "			 order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info) ";	*/
		
		String qry = "select *  from (select bagNo,bagNoFlag,receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
				+ " (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clrsite siteValue  from    \r\n"
			//	+ "   (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
				+ "   (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(ooe,1,5)),'INMAA',decode(:cusSite,'INFCH5','INFCH',substr(ooe,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
			//	+ "   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))=substr(:cusSite,1,5)   and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n" 
				+ "   (select count(*) with_ead_thissite,recp_id,:cusSite clrsite from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(:cusSite,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(decode(a.clr_site,null,decode(a.cus_site,null,null,a.cus_site),a.clr_site),1,5)=substr(map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join  \r\n" 
				+ "   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n" 
				//+ "   (select count(*)  withEadOtherSite,recp_id from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(substr(ooe,1,5),'INBOM',decode(:cusSite,'INBPS5','INBPS',substr(ooe,1,5)),'INMAA',decode(:cusSite,'INFCH5','INFCH',substr(ooe,1,5)),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(decode(a.clr_site,null,decode(a.cus_site,null,null,a.cus_site),a.clr_site),1,5)<>substr(:cusSite,1,5)   and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id) l on y.recp_id=l.recp_id left join  \r\n" 
				+ "   (sELECT count(*)  withEadOtherSite, recp_id from fpo_main a, article_arr_info b left join fpo_scan_info c on b.recp_id=c.bag_no where recp_id is not null and b.article_id=a.item_id and c.bag_no is null  and status is null and decode(clr_site,null,a.cus_site,clr_site)!=:cusSite group  by recp_id ) l on y.recp_id=l.recp_id left join  \r\n" 
				//	+ "   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
				+ "   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
				+ "   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
				+ "   where y.recp_id is not null and clrsite=:cusSite group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clrsite  \r\n"
				+ "  union  \r\n"
				+ "  select with_ead_thissite+withEadOtherSite with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  0 withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
				+ "  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
				+ "  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
				+ "  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join \r\n"
				+ "  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.arr_scan is null  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
				+ "  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
				+ "  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no  \r\n"
				+ "  where x.bag_no is not null  \r\n"
				+ "  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
				+ "  order by 3, 2 desc, 5 desc) where bagNo not in (select distinct(bag_no)  from fpo_scan_info)  \r\n";		
		
		
		try {
			Query query = entityManager.createNativeQuery(qry, BagScanBean.class);
			query.setParameter("cusSite", cusSite);
			return (List<BagScanBean>)  query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	
	
	
	public List<BagArticleBean> getBagArticles(String bagNoOrRecpId, boolean bagFlag) {
		String qry = "";
		if (bagFlag) {
			qry = "select afi.article_id articleId,case when (select count(*)  from fpo_main where item_id=afi.article_id)>0 then 'YES' else 'NO' end eadExist,"
					+ "(select decode(cus_site,null,'-',cus_site) mapped from fpo_main where item_id=afi.article_id) mapped,"
					+ "(select decode(clr_site,null,cus_site,clr_site) clrsite from fpo_main where item_id=fsi.article_id) destinationFpo,"
					+ "substr(site_code,0,5) || 5 clrsite,case when (select count(*)  from fpo_order where item_id=afi.article_id)>0 then 'YES' else 'NO' end "
					+ "ExamOrderExist,fsi.scan_report scanReport,country.cntry_nm originCountry  from articlearr_fpo_info afi left join fpo_scan_info fsi on (afi.article_id=fsi.article_id and fsi.bag_type = 'B') "
					+ "left join ops$dir.d_cntry_cd country on substr(afi.article_id,-2)=country.cntry_cd join ops$dir.fpo_sites on substr(recd_fpo,0,5)=substr(map_code,0,5) where afi.bag_no=:bagNoOrRecpId  and afi.status is null order by afi.recd_dt desc";
		} else {
			qry = "select aai.article_id articleId,case when (select count(*)  from fpo_main where item_id=aai.article_id)>0 then 'YES' else 'NO' end eadExist,"
					+ "(select decode(cus_site,null,'-',cus_site) mapped from fpo_main where item_id=aai.article_id) mapped,"
					+ "(select decode(clr_site,null,cus_site,clr_site) clrsite from fpo_main where item_id=aai.article_id) clrsite,"
					// + "coalesce((select substr(recd_fpo,0,5) || 5 from
					// articlearr_fpo_info afi
					// where afi.article_id=aai.article_id and status is null),\r\n"
					// + "coalesce((select substr(aai.ooe,0,5) || 5 from fpo_main fm where
					// aai.article_id=fm.item_id and
					// substr(aai.ooe,1,5)=substr(fm.cus_site,1,5)),'-')) "
					+ "coalesce((select site_code from fpo_main fm join ops$dir.fpo_sites on fm.cus_site=site_code where fm.item_id=aai.article_id),substr(site_code,0,5) || 5) "
					+ " destinationFpo,case when (select count(*)  from fpo_order where item_id=aai.article_id)>0 then 'YES' else 'NO' end ExamOrderExist,fsi.scan_report scanReport,country.cntry_nm originCountry "
					+ " from article_arr_info aai left join fpo_scan_info fsi on (aai.article_id=fsi.article_id and fsi.bag_type = 'R') "
					+ "left join ops$dir.d_cntry_cd country on aai.origcntrycd=country.cntry_cd join ops$dir.fpo_sites on substr(aai.ooe,0,5)=substr(map_code,0,5) where recp_id=:bagNoOrRecpId and aai.status is null order by aai.recd_event_dt desc";
		}
		try {
			Query query = entityManager.createNativeQuery(qry, BagArticleBean.class);
			query.setParameter("bagNoOrRecpId", bagNoOrRecpId);
			return (List<BagArticleBean>) query.getResultList();//return ((List<ArticleDatas>) query.getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public List<ArrivalHistoryBean2> getArrivalHistoryb(String fromDate, String toDate, 
			String cusSite) {	String qry = "select BAG_NO bagNo, BAG_TYPE bagType, to_char(recd_dt,'DD/MM/YYYY HH24:MI:SS')recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt,nvl(totcou,0)totalCount  ,nvl(totead,0)withEadCurrSite,nvl(totnoead,0)withEadOtherSite,nvl(totnotalot,0)withoutEad,nvl(withoutead,0)withEadSiteNotAllot ,scan_dt scndt  from \r\n"
					+ "									(select a.BAG_NO, BAG_TYPE, MAX(recd_event_dt) recd_dt,scan_dt,  totcou,totead,totnoead,totnotalot,(decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))withoutead\r\n"
					+ "										from FPO_SCAN_INFO a left join (select recd_event_dt, article_id from article_arr_info ) b on a.article_id=b.article_id  left join (select count(distinct article_id) totcou, RECP_ID  from  article_arr_info group by recp_id) c on a.BAG_NO = c.recp_id \r\n"
					+ "										left join (select count(distinct article_id) totead,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned = 'O' ) and CUS_SITE=:cusSite and bag_type='R' group by BAG_NO) d on d.BAG_NO=a.BAG_NO left join \r\n"
					+ "										(select count(distinct article_id) totnoead,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned = 'O') and cus_site!=:cusSite and bag_type='R' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
					+ "										(select count(distinct article_id) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned = 'O') and cus_site=null and bag_type='R' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
					+ "										where (SCANNED ='M' or SCANNED='O') and OLD_BAG_NO IS NULL  and CUS_SITE=:cusSite and bag_type='R'and trunc(scan_dt) BETWEEN to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy')  \r\n"
					+ "									GROUP BY a.BAG_NO,BAG_TYPE,scan_dt, totcou,totead,totnoead,totnotalot) \r\n"
					+ "									union\r\n"
					+ "									(select a.BAG_NO, BAG_TYPE, to_char(MAX(recd_dt),'DD/MM/YYYY HH24:MI:SS')recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt, totcou ,totead,totnoead,totnotalot, (decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))  withoutead, scan_dt scndt\r\n"
					+ "					                   from FPO_SCAN_INFO a left join (select recd_dt, article_id from articlearr_fpo_info) b on a.article_id=b.article_id  left join (select count(distinct article_id) totcou ,bag_no from  articlearr_fpo_info group by bag_no) c on a.BAG_NO=c.bag_no \r\n"
					+ "									left join (select count(distinct article_id) totead,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned='O') and CUS_SITE=:cusSite and bag_type='B' group by BAG_NO) d  on d.BAG_NO=a.bag_no left join \r\n"
					+ "										(select count(distinct article_id) totnoead,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned='O') and cus_site!=:cusSite and bag_type='B' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
					+ "										(select count(distinct article_id) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE (SCANNED ='M' or scanned = 'O') and cus_site=null and bag_type='B' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
					+ "										where (SCANNED ='M' or SCANNED='O') and OLD_BAG_NO IS NULL  and CUS_SITE=:cusSite and bag_type='B' and trunc(scan_dt) BETWEEN to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy') \r\n"
					+ "										GROUP BY a.BAG_NO,BAG_TYPE,scan_dt,totcou,totead,totnoead,totnotalot)\r\n"
					+ "					                    order by scndt desc";
			try {
				Query query = entityManager.createNativeQuery(qry,ArrivalHistoryBean2.class);
				query.setParameter("cusSite", cusSite);					
				query.setParameter("fromDate",fromDate);
				query.setParameter("toDate",toDate);
				return (List<ArrivalHistoryBean2>) query.getResultList();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ArrayList<>();
		}

	public List<BagArticleBean> getnBagArticles(String bagNoOrRecpId, boolean bagFlag) {
		String qry = "";
		if (bagFlag) {
			qry = "select fsi.article_id articleId,case when (select count(*)  from fpo_main where item_id=fsi.article_id)>0 then 'YES' else 'NO' end eadExist," 
					+ "(select decode(cus_site,null,'-',cus_site) mapped from fpo_main where item_id=fsi.article_id) mapped,"
					+ "(select decode(clr_site,null,'-',clr_site) clrsite from fpo_main where item_id=fsi.article_id)  destinationFpo,"
					+"					substr(site_code,0,5) || 5 clrsite,case when (select count(*)  from fpo_order where item_id=fsi.article_id)>0 then 'YES' else 'NO' end " 
					+"                ExamOrderExist,fsi.scan_report scanReport,fsi.remarks,fsi.ooe,country.cntry_nm originCountry  from fpo_scan_info fsi left join articlearr_fpo_info afi on (fsi.article_id=afi.article_id and fsi.bag_type = 'B') " 
					+"                left join ops$dir.d_cntry_cd country on substr(fsi.article_id,-2)=country.cntry_cd join ops$dir.fpo_sites on substr(fsi.cus_site,0,5)=substr(map_code,0,5) where fsi.bag_no=:bagNoOrRecpId  and afi.status is null order by fsi.scan_dt desc";
					
				} else {
					qry = "select fsi.article_id articleId,case when (select count(*)  from fpo_main where item_id=afi.article_id)>0 then 'YES' else 'NO' end eadExist,\r\n" 
							+ "(select decode(cus_site,null,'-',cus_site) mapped from fpo_main where item_id=fsi.article_id) mapped,"
							+ "(select decode(clr_site,null,'-',clr_site) clrsite from fpo_main where item_id=fsi.article_id) clrsite,"
							+"					substr(site_code,0,5) || 5  destinationFpo,case when (select count(*)  from fpo_order where item_id=fsi.article_id)>0 then 'YES' else 'NO' end \r\n" 
							+"                ExamOrderExist,fsi.scan_report scanReport,fsi.remarks,country.cntry_nm originCountry  from fpo_scan_info fsi left join article_arr_info afi on (fsi.article_id=afi.article_id and fsi.bag_type = 'R') \r\n" 
							+"                left join ops$dir.d_cntry_cd country on substr(fsi.article_id,-2)=country.cntry_cd join ops$dir.fpo_sites on substr(fsi.cus_site,0,5)=substr(map_code,0,5) where fsi.bag_no=:bagNoOrRecpId and afi.status is null order by fsi.scan_dt desc";
					}
		try {
			Query query = entityManager.createNativeQuery(qry, BagArticleBean.class);
			query.setParameter("bagNoOrRecpId", bagNoOrRecpId);
			return (List<BagArticleBean>) query.getResultList();//return ((List<ArticleDatas>) query.getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<ArrivalHistoryBean> getArrivalnHistory(String fromDate, String toDate, 
			String cusSite) {
//		String qry = "select arrival.*,fsi.scan_dt scannedDate  from (select bagNo,bagNoFlag,receivedDate,totalCount,withEadCurrSite,withEadOtherSite,withoutEad from\r\n"
//				+ "(select x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad  from \r\n"
//				+ "(select count(*) with_ead_thissite,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'   and status is null group by recp_id) x,\r\n"
//				+ "(select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and status is null group by recp_id)k,\r\n"
//				+ "(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id from fpo_main b ) and status is null group by recp_id) z,\r\n"
//				+ "(select count(*) tot_cou, recp_id from article_arr_info b where status is null group by recp_id  ) y\r\n"
//				+ "where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id\r\n"
//				+ "group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite,without_ead\r\n" + "union\r\n"
//				+ "select x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad  from \r\n"
//				+ "(select count(*) with_ead_thissite,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'  and status is null group by bag_no) x,\r\n"
//				+ "(select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and status is null group by bag_no) k,\r\n"
//				+ "(select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id from fpo_main b ) and status is null group by bag_no) z,\r\n"
//				+ "(select count(*) tot_cou, bag_no from articlearr_fpo_info b where status is null group by bag_no  ) y\r\n"
//				+ "where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no\r\n"
//				+ "group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite,without_ead)\r\n"
//				+ "order by 3, 2 desc, 5 desc) arrival join (select * from fpo_scan_info where id in (select max(id) from fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no";
////		if (isRangeSelect) {
////			qry = qry + " where trunc(arrival.receivedDate) between to_date ('" + fromDate
////					+ "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate + "', 'dd/mm/yyyy') order by arrival.receivedDate";
////		} else {
//			qry = qry + " where trunc(fsi.scan_dt) between to_date ('" + fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('"
//					+ toDate + "', 'dd/mm/yyyy') order by fsi.scan_dt desc";
//		}
		String qry = "select bag_no,bag_type,scan_dt,totcou,tot_scan from\r\n"  
				+"(select a.bag_no,'Y' bag_type,a.scan_dt,totcou,tot_scan from\r\n" 
				+"(select bag_no, bag_type,scan_dt,count(*) tot_scan,scanned from fpo_scan_info where bag_type='B' group by bag_no,bag_type,scan_dt,scanned ) a left join  \r\n"  
				+"(select count(*) totcou,bag_no from articlearr_fpo_info where bag_no is null  group by bag_no) b  on a.bag_no=b.bag_no left join\r\n"  
				+"(select count(*) thissite,bag_no from articlearr_fpo_info where bag_no is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no) c on a.bag_no=c.bag_no \r\n"  
				+"where scanned='M')\r\n" 
				+"union\r\n" 
				+"(select a.bag_no,'N' bag_type,a.scan_dt,totcou,tot_scan from\r\n"
				+"(select bag_no, bag_type,scan_dt,count(*) tot_scan,scanned from fpo_scan_info where bag_type='B' group by bag_no,bag_type,scan_dt,scanned ) a left join  \r\n" 
				+"(select count(*) totcou,recp_id from article_arr_info where recp_id is null  group by recp_id ) b  on a.bag_no=b.recp_id left join\r\n"  
				+"(select count(*) thissite,recp_id from article_arr_info where recp_id is null and substr(ooe,1,5)=substr(:cusSite,1,5)  group by recp_id) c on a.bag_no=c.recp_id\r\n" 
				+"where scanned='M') order by scan_dt desc";
				
		try {
			Query query = entityManager.createNativeQuery(qry,ArrivalHistoryBean.class);
			query.setParameter("cusSite", cusSite);					
		//	query.setParameter("fromDate",fromDate);
		//	query.setParameter("toDate",toDate);
			return (List<ArrivalHistoryBean>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	/*public String getScanReportText(String bagNoOrRecpId) {
		String qry = "select scan_report  from fpo_scan_info where article_id is null and scan_report is not null and bag_no='"
				+ bagNoOrRecpId + "'";
		try {
			List<Object> result = entityManager.createNativeQuery(qry).getResultList();
			if (!result.isEmpty())
				return (String) result.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/

	
	
	public List<ArrivalHistoryBean> getArrivalHistory(String fromDate, String toDate, Boolean isRangeSelect,
			String cusSite) {
//		String qry = "select arrival.*,fsi.scan_dt scannedDate  from (select bagNo,bagNoFlag,receivedDate,totalCount,withEadCurrSite,withEadOtherSite,withoutEad from\r\n"
//				+ "(select x.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=x.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad  from \r\n"
//				+ "(select count(*) with_ead_thissite,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'   and status is null group by recp_id) x,\r\n"
//				+ "(select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and substr(b.ooe,1,5)=substr(a.cus_site,1,5) and status is null group by recp_id)k,\r\n"
//				+ "(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id from fpo_main b ) and status is null group by recp_id) z,\r\n"
//				+ "(select count(*) tot_cou, recp_id from article_arr_info b where status is null group by recp_id  ) y\r\n"
//				+ "where x.recp_id=y.recp_id and x.recp_id=z.recp_id and x.recp_id=k.recp_id\r\n"
//				+ "group by x.recp_id,tot_cou,with_ead_totcou,with_ead_thissite,without_ead\r\n" + "union\r\n"
//				+ "select x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,with_ead_totcou-with_ead_thissite withEadOtherSite,without_ead withoutEad  from \r\n"
//				+ "(select count(*) with_ead_thissite,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and a.cus_site = '"
//				+ cusSite + "'  and status is null group by bag_no) x,\r\n"
//				+ "(select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and substr(b.recd_fpo,1,5)=substr(a.cus_site,1,5) and status is null group by bag_no) k,\r\n"
//				+ "(select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id from fpo_main b ) and status is null group by bag_no) z,\r\n"
//				+ "(select count(*) tot_cou, bag_no from articlearr_fpo_info b where status is null group by bag_no  ) y\r\n"
//				+ "where x.bag_no=y.bag_no and x.bag_no=z.bag_no and x.bag_no=k.bag_no\r\n"
//				+ "group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite,without_ead)\r\n"
//				+ "order by 3, 2 desc, 5 desc) arrival join (select * from fpo_scan_info where id in (select max(id) from fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no";
////		if (isRangeSelect) {
////			qry = qry + " where trunc(arrival.receivedDate) between to_date ('" + fromDate
////					+ "', 'dd/mm/yyyy') AND TO_DATE ('" + toDate + "', 'dd/mm/yyyy') order by arrival.receivedDate";
////		} else {
//			qry = qry + " where trunc(fsi.scan_dt) between to_date ('" + fromDate + "', 'dd/mm/yyyy') AND TO_DATE ('"
//					+ toDate + "', 'dd/mm/yyyy') order by fsi.scan_dt desc";
//		}
//		String qry = "select arrival.*,to_char(fsi.scan_dt,'DD/MM/YYYY HH24:MI:SS')scannedDate  from (select bagNo,bagNoFlag,to_char(receivedDate,'DD/MM/YYYY HH24:MI:SS')receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
//				+ "						 (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
//				+ "						   (select count(*) tot_cou, recp_id  from article_arr_info b where status is null and substr(ooe,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
//				+ "						   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(ooe,1,5)=substr(c.map_code,1,5) and decode(decode(a.clr_site,null,a.cus_site,a.clr_site) ,'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))=:cusSite  and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join   \r\n"
//				+ "						   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join    \r\n"
//				+ "						   (select nvl(count(*),0) withEadOtherSite,recp_id from  article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(decode(a.clr_site,null,a.cus_site,a.clr_site),'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))!=:cusSite and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
//				+ "						   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
//				+ "						   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
//				+ "						   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  \r\n"
//				+ "						  union  \r\n"
//				+ "						  select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
//				+ "						  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
//				+ "						  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
//				+ "						  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join \r\n"
//				+ "						  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
//				+ "						  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
//				+ "						  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no  \r\n"
//				+ "						  where x.bag_no is not null  \r\n"
//				+ "						  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
//				+ "						  order by 3, 2 desc, 5 desc) arrival join (select *  from fpo_scan_info where id in (select max(id)  from  \r\n"
//				+ "fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no where trunc(fsi.scan_dt) between to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy') order by fsi.scan_dt desc";
		String qry=" select arrival.*,to_char(fsi.scan_dt,'DD/MM/YYYY HH24:MI:SS')scannedDate  from (select bagNo,bagNoFlag,to_char(receivedDate,'DD/MM/YYYY HH24:MI:SS')receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from\r\n"
				+ "				(select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from    \r\n"
				+ "						(SELECT COUNT(*) AS tot_cou, b.recp_id FROM fpo_scan_info f , article_arr_info b WHERE f.bag_no=b.recp_id  and  f.bag_type = 'R'  AND b.status IS NULL  AND f.cus_site = :cusSite AND f.bag_no NOT LIKE 'NEF%' AND f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no) GROUP BY b.recp_id  ) y  left join  \r\n"
				+ "						( SELECT NVL(COUNT(CASE WHEN decode(substr(recp_id,7,5),'INBOM', decode(a.cus_site,'INBOM5',f.cus_site,'INBPS5',f.cus_site,a.cus_site),'INMAA',decode(a.cus_site,'INMAA5',f.cus_site,'INFCH5',f.cus_site,a.cus_site),a.cus_site) = f.cus_site THEN 1 END), 0) AS with_ead_thissite, f.bag_no AS recp_id,f.cus_site AS clr_site FROM fpo_scan_info f, article_arr_info b, fpo_main a,ops$dir.fpo_sites c\r\n"
				+ "				WHERE f.bag_no = b.recp_id AND b.article_id = a.item_id AND f.bag_type = 'R'  AND f.bag_no NOT LIKE 'NEF%'  AND f.cus_site = :cusSite AND SUBSTR(c.site_code, 1, 5) = SUBSTR(:cusSite, 1, 5) AND b.status IS NULL AND f.id = ( SELECT MAX(id) FROM fpo_scan_info WHERE bag_no = f.bag_no)\r\n"
				+ "				GROUP BY f.bag_no, f.cus_site) x on y.recp_id=x.recp_id left join   \r\n"
				+ "						(select nvl(count(*),0) notallotted,recp_id  from  fpo_scan_info f  , article_arr_info b, fpo_main a where f.bag_no=b.recp_id and a.item_id=b.article_id and f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no) and  a.cus_site is null and f.bag_type='R' and f.bag_no NOT LIKE 'NEF%' and b.status is null group by recp_id) j on y.recp_id=j.recp_id left join    \r\n"
				+ "						( SELECT NVL(COUNT(CASE WHEN decode(substr(recp_id,7,5),'INBOM', decode(a.cus_site,'INBOM5',f.cus_site,'INBPS5',f.cus_site,a.cus_site),'INMAA',decode(a.cus_site,'INMAA5',f.cus_site,'INFCH5',f.cus_site,a.cus_site),a.cus_site)! = f.cus_site THEN 1 END), 0) AS withEadOtherSite, f.bag_no AS recp_id FROM fpo_scan_info f, article_arr_info b, fpo_main a,ops$dir.fpo_sites c\r\n"
				+ "								WHERE f.bag_no = b.recp_id AND b.article_id = a.item_id AND f.bag_type = 'R'  AND f.bag_no NOT LIKE 'NEF%'  AND f.cus_site = :cusSite AND SUBSTR(c.site_code, 1, 5) = SUBSTR(:cusSite, 1, 5) AND b.status IS NULL AND f.id = ( SELECT MAX(id) FROM fpo_scan_info WHERE bag_no = f.bag_no)\r\n"
				+ "								GROUP BY f.bag_no) l on y.recp_id=l.recp_id left join \r\n"
				+ "						(select count(*) with_ead_totcou,recp_id  from article_arr_info b, fpo_main a ,  ops$dir.fpo_sites c  where b.article_id=a.item_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join   \r\n"
				+ "						(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
				+ "									where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site \r\n"
				+ "				                     union\r\n"
				+ "				                     select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
				+ "				                                      (select count(*) with_ead_thissite, f.bag_no,f.cus_site recd_fpo from fpo_scan_info f, articlearr_fpo_info b, fpo_main a where f.bag_no=b.bag_no and b.article_id=a.item_id and f.bag_type='B' and f.bag_no not LIKE 'NEF%'  and decode(f.cus_site,'INBOM5',decode(a.cus_site,'INBPS5','INBOM5','INBOM5','INBOM5',a.cus_site),'INBPS5',decode(a.cus_site,'INBPS5','INBPS5','INBOM5','INBPS5',a.cus_site),'INMAA5',\r\n"
				+ "                                                    decode(a.cus_site,'INMAA5','INMAA5','INFCH5','INMAA5',a.cus_site),'INFCH5',decode(a.cus_site,'INFCH5','INFCH5','INMAA5','INFCH5',a.cus_site),a.cus_site)=f.cus_site\r\n"
				+ "                                                    group by f.bag_no,f.cus_site) x left join  \r\n"
				+ "				                                      (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
				+ "				                                      (select count(*)withEadOtherSite,f.bag_no from fpo_scan_info f, articlearr_fpo_info b, fpo_main a where f.bag_no=b.bag_no and b.article_id=a.item_id and f.bag_type='B' and f.bag_no not LIKE 'NEF%'  and decode(f.cus_site,'INBOM5',decode(a.cus_site,'INBPS5','INBOM5','INBOM5','INBOM5',a.cus_site),'INBPS5',decode(a.cus_site,'INBPS5','INBPS5','INBOM5','INBPS5',a.cus_site),'INMAA5',\r\n"
				+ "                                                        decode(a.cus_site,'INMAA5','INMAA5','INFCH5','INMAA5',a.cus_site),'INFCH5',decode(a.cus_site,'INFCH5','INFCH5','INMAA5','INFCH5',a.cus_site),a.cus_site)!=f.cus_site\r\n"
				+ "                                                        group by f.bag_no) l on x.bag_no=l.bag_no left join \r\n"
				+ "				                                      (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
				+ "													  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
				+ "				                                      (select count(*) tot_cou, b.bag_no  from fpo_scan_info f, articlearr_fpo_info b  where f.bag_no=b.bag_no and f.bag_type='B' and  b.status is null and f.cus_site=:cusSite AND f.bag_no NOT LIKE 'NEF%' AND f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no)  group by b.bag_no  ) y   on x.bag_no=y.bag_no  \r\n"
				+ "													  where x.bag_no is not null  \r\n"
				+ "													  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  \r\n"
				+ "													  order by 3, 2 desc, 5 desc) arrival join (select *  from fpo_scan_info where id in (select max(id)  from  \r\n"
				+ "								fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no where trunc(fsi.scan_dt) between to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy') order by fsi.scan_dt desc\r\n"
				+ "                         ";
		try {
			Query query = entityManager.createNativeQuery(qry,ArrivalHistoryBean.class);
			query.setParameter("cusSite", cusSite);					
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
			return (List<ArrivalHistoryBean>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	/*public String getScanReportText(String bagNoOrRecpId) {
		String qry = "select scan_report  from fpo_scan_info where article_id is null and scan_report is not null and bag_no='"
				+ bagNoOrRecpId + "'";
		try {
			List<Object> result = entityManager.createNativeQuery(qry).getResultList();
			if (!result.isEmpty())
				return (String) result.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/

	
	
	
	
	
	
	
	
	public ArticleArrivalInfoBean getArticleArrivalInfo(String articleId) {

		String qry = "select arr.article_id articleId,recp_id recptId,coalesce(arr.ooe,'-') ooe,recd_event_dt ooeDate,coalesce(fsi.scan_report,'-') scanReport  from "
				+ "article_arr_info arr left join fpo_scan_info fsi on (arr.article_id = fsi.article_id and fsi.bag_type ='R') "
				+ "where arr.article_id=:articleId and arr.status is null";
		try {
			Query query = entityManager.createNativeQuery(qry, ArticleArrivalInfoBean.class);
			query.setParameter("articleId", articleId);
			List<Object> result = query.getResultList();
			if (!result.isEmpty())
				return (ArticleArrivalInfoBean) result.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArticleArrivalInfoBean();
	}
	
	
	
	public List<RegulationRecptIDBean> getRecpIdRegulation() {

		String qry = "SELECT A.ARTICLE_ID article_id,\r\n"
				+ "       TO_CHAR(SCAN_DT,'DD/MM/YYYY HH24:MI:SS') scan_dt,\r\n"
				+ "       BAG_NO bag_no,\r\n"
				+ "       TO_CHAR(RECD_EVENT_DT,'DD/MM/YYYY HH24:MI:SS') recd_event_dt,\r\n"
				+ "       RECP_ID recept_id,\r\n"
				+ "       BAG_TYPE bag_type,\r\n"
				+ "       F.OOC_DT ooc_dt\r\n"
				+ "FROM FPO_SCAN_INFO A\r\n"
				+ "JOIN ARTICLE_ARR_INFO B ON A.ARTICLE_ID=B.ARTICLE_ID\r\n"
				+ "JOIN FPO_STATUS F ON A.ARTICLE_ID=F.ITEM_ID\r\n"
				+ "WHERE (SCANNED='M' OR SCANNED='O') \r\n"
				+ "  AND BAG_TYPE='R' \r\n"
				+ "  AND A.OLD_BAG_NO IS NULL\r\n"
				+ "  AND A.ARTICLE_ID = F.ITEM_ID";
		try {
			Query query = entityManager.createNativeQuery(qry,RegulationRecptIDBean.class);
			
			return (List<RegulationRecptIDBean>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
		
		public List<RegulationRecptIDBean> getRecpIdReglsnHistry() {

			String qry = "SELECT A.ARTICLE_ID article_id,to_char(SCAN_DT,'DD/MM/YYYY HH24:MI:SS')scan_dt,\r\n"
					+ "OLD_BAG_NO bag_no,to_char(MAX(RECD_EVENT_DT),'DD/MM/YYYY HH24:MI:SS')recd_event_dt,RECP_ID recept_id,BAG_TYPE bag_type,\r\n"
					+ "to_char(REGULARISE_DT,'DD/MM/YYYY HH24:MI:SS') ooc_dt FROM FPO_SCAN_INFO A, ARTICLE_ARR_INFO B WHERE A.ARTICLE_ID=B.ARTICLE_ID AND\r\n"
					+ "(SCANNED='M' OR SCANNED='O') AND BAG_TYPE='R' AND OLD_BAG_NO IS NOT NULL GROUP BY A.ARTICLE_ID,SCAN_DT,OLD_BAG_NO,RECP_ID,BAG_TYPE,REGULARISE_DT  ORDER BY REGULARISE_DT DESC \r\n"
					+ "";
			try {
				Query query = entityManager.createNativeQuery(qry,RegulationRecptIDBean.class);
				
				return (List<RegulationRecptIDBean>) query.getResultList();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ArrayList<>();
		}

		
		
	/*public String getMapCode(String articleId) {
		String qry = "select map_code from ops$dir.fpo_sites fs join fpo_main fm on (fs.site_code=fm.cus_site) where item_id='"
				+ articleId + "'";
		try {
			List<Object> result = entityManager.createNativeQuery(qry).getResultList();
			if (!result.isEmpty())
				return (String) result.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}*/

	/*public Set<String> getArticleIds(FpoScanInfo fposcan) {
		String qry = null;
		if (fposcan.getBagType().equalsIgnoreCase("R")) {
			qry = "select distinct(article_id) from article_arr_info where recp_id='" + fposcan.getBagNo()
					+ "' and status is null";
		} else if (fposcan.getBagType().equalsIgnoreCase("B")) {
			qry = "select distinct(article_id) from articlearr_fpo_info where bag_no='" + fposcan.getBagNo()
					+ "' and status is null";
		}
		try {
			return new HashSet<String>(entityManager.createNativeQuery(qry).getResultList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new HashSet<>();
	}*/
}
