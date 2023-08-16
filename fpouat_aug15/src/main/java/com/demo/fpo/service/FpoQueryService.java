package com.demo.fpo.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.bean.BagArticleBean;
import com.demo.fpo.bean.QueryReplyHistoryBean;
import com.demo.fpo.controller.LoginController;
import com.demo.fpo.model.FPO_ITEM_DET;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.repos.FPO_AMENDrepost;
import com.demo.fpo.repos.FPO_ITEM_DETrepost;
import com.demo.fpo.repos.FPO_MAINrepost;
import com.demo.fpo.repos.FpoQueryRepo;

@Service
@Component
public class FpoQueryService {

	@Autowired
	FPO_ITEM_DETrepost fpoItemDetRepo;

	@Autowired
	FPO_MAINrepost fpoMainRepost;

	@Autowired
	FpoQueryRepo fpoQueryRepo;

	@Autowired
	FPO_AMENDrepost fpoAmendRepost;

	@Autowired
	FpoDeclaredService fpoDeclaredService;

	@Autowired
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<FPO_MAIN> getAllFpoMainData(List<FPO_MAIN> fpoMain) {
		String sDate1 = fpoMain.get(0).getPOSTINGDT();
		String[] stringArray = sDate1.split("T");
		fpoMain.get(0).setPostDate(stringArray[0].substring(8, 10) + "-" + stringArray[0].substring(5, 7) + "-"
				+ stringArray[0].substring(0, 4));
		fpoMain.get(0).setPOSTINGTIME(stringArray[1]);
		return fpoMain;
	}

	public List<FPO_MAIN> setDateFormat(List<FPO_MAIN> fpoMains) {
		List<FPO_MAIN> fpoMainList = new ArrayList<FPO_MAIN>();
		String sDate1 = null;

		String[] stringArray = new String[10];

		for (FPO_MAIN fpoMain : fpoMains) {
			sDate1 = fpoMain.getPOSTINGDT();
			stringArray = sDate1.split("T");
			fpoMain.setPostDate(stringArray[0].substring(8, 10) + "-" + stringArray[0].substring(5, 7) + "-"
					+ stringArray[0].substring(0, 4));
			if (null != stringArray && stringArray.length >= 2)
				fpoMain.setPOSTINGTIME(stringArray[1]);
			fpoMainList.add(fpoMain);
		}

		/*
		 * for (FPO_MAIN fpoMain : fpoMains) { sDate2 = fpoMain.getCIN_DT(); stringArray
		 * = sDate2.split(" "); fpoMain.setCinDate(stringArray[0].substring(8, 10) + "-"
		 * + stringArray[0].substring(5, 7) + "-" + stringArray[0].substring(0, 4)); if
		 * (null != stringArray && stringArray.length >= 2)
		 * fpoMain.setPOSTINGTIME(stringArray[1]); fpoMainList.add(fpoMain); }
		 */

		return fpoMains;
	}

	public List<FpoQuery> getAllFpoQuery(String cinNo) {
		List<FpoQuery> filteredListQuery = new ArrayList<FpoQuery>();
		List<FPO_ITEM_DET> fpoItems = fpoDeclaredService.getAssesItem(cinNo);

		for (FPO_ITEM_DET fpoItem : fpoItems) {
			FpoQuery filteredQuery = new FpoQuery();
			filteredQuery.setQRY(fpoQueryRepo.getQueryOnCinAndItemNo(fpoItem.getCinNo(), fpoItem.getITEM_NO()));
			filteredQuery.setRply(fpoQueryRepo.getRplyOnCinAndItemNo(fpoItem.getCinNo(), fpoItem.getITEM_NO()));
			filteredQuery.setCinNo(fpoItem.getCinNo());
			filteredQuery.setITEM_ID(fpoItem.getITEM_ID());
			filteredQuery.setITEM_NO(fpoItem.getITEM_NO());
			filteredQuery.setItemDesc(fpoItem.getITEM_DESC());
			filteredListQuery.add(filteredQuery);
		}

		return filteredListQuery;
	}

	public String getFpoQuery(String cinNo) {
		Long queryNo = fpoQueryRepo.getCountQueryNoOnCinNo(cinNo);
		if (null != queryNo)
			return queryNo.toString();
		return null;
	}

	public FpoQuery updateRespQry( FpoQuery fpoqry ,String name, String mobileno,String mail, HttpSession session, HttpServletRequest request) throws ParseException {
        String userid = fpoqry.setRPLY_OFF_ID(request.getSession().getAttribute("offId") == null ? null : request.getSession().getAttribute("offId").toString());
        String role = fpoqry.setRPLY_OFF_ID(request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString());
        Date currentdate = new Date();
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date1 = time.format(currentdate);
		Date date = time.parse(date1);
		
	/*	if (fpoqry.getItem_no() != null) {
			fpoQueryRepo.updateresp(fpoqry.getCinNo(), fpoqry.getItem_no(), fpoqry.getRply(), userid, date, role);
		} else {
			fpoQueryRepo.updatedefQry(fpoqry.getCinNo(), fpoqry.getRply(), fpoqry.getDpcmts(), userid, date, role);
		} */
		
		
		
		Object attribute = session.getAttribute("replydt");
		String dateString = attribute.toString();	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		
		Date replydt=new Date();
		try {
			replydt = dateFormat.parse(dateString);		    
		} catch (ParseException e) {
	
		    e.printStackTrace();
		}

		
	
		
		if(name==null && mobileno==null && mail==null)
		{
			if (fpoqry.getItem_no() != null) 
				fpoQueryRepo.updateresp(fpoqry.getCinNo(), fpoqry.getItem_no(), fpoqry.getRply(), userid, date, role);
			 else 
				fpoQueryRepo.updatedefQry(fpoqry.getCinNo(), fpoqry.getRply(), fpoqry.getDpcmts(), userid, date, role);
		}
		else {
			
		if (fpoqry.getItem_no() != null) 
			fpoQueryRepo.updateresp2(fpoqry.getCinNo(), fpoqry.getItem_no(), fpoqry.getRply(), userid, date, role,name,mobileno,mail,replydt);
		 else 
			fpoQueryRepo.updatedefQry2(fpoqry.getCinNo(), fpoqry.getRply(), fpoqry.getDpcmts(), userid, date, role,replydt,name,mobileno,mail);
		
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return fpoqry;
	}

	/*public boolean checkQueryRaised(String itemId, boolean reply) {
		boolean result = false;
		try {
			String queryStr = "";
			if (reply) {
				queryStr = "select count(*) from fpo_query where item_id = '" + itemId + "' and rply_date is not null";
			} else {
				queryStr = "select count(*) from fpo_query where item_id = '" + itemId + "'";
			}
			Query query = entityManager.createNativeQuery(queryStr);
			List<Object> resultList = query.getResultList();
			result = ((BigDecimal) resultList.get(0)).longValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/*public boolean checkAdditionQueryRaised(String itemId, boolean reply) {
		boolean result = false;
		try {
			String queryStr = "";
			if (reply) {
				queryStr = "select count(*) from fpo_addl_qry where item_id = '" + itemId
						+ "' and rply_date is not null";
			} else {
				queryStr = "select count(*) from fpo_addl_qry where item_id = '" + itemId + "'";
			}
			Query query = entityManager.createNativeQuery(queryStr);
			List<Object> resultList = query.getResultList();
			result = ((BigDecimal) resultList.get(0)).longValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	public String getQryType(String articleId) {
		String qry = "select qry_type from fpo_addl_qry where qry_id = (select max(qry_id) from fpo_addl_qry where item_id ='"
				+ articleId + "')";
		try {
			List<Object> result = entityManager.createNativeQuery(qry).getResultList();
			if (!result.isEmpty())
				return (String) result.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<QueryReplyHistoryBean> getQryReplyArticlesHistory(String fromDate, String toDate, String cusSite) {
		String qry = "select  ROW_NUMBER() over (order by (SELECT 0 from dual)) as id,history.* from (select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
				+ "afi.recd_dt arrivalFPODate,faq.rply_date replyDate,faq.rply_off_id || '-' || def.emp_name officerId,fm.cin_no cinNo,faq.qry_type articleStage,1 additionalQry,'' currentStatus \r\n"
				+ "from fpo_main fm join fpo_addl_qry faq on (fm.item_id=faq.item_id) \r\n"
				+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
				+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
				+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) \r\n"
				+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
				+ " join ops$dir.d_emp def on (def.user_id=faq.rply_off_id) \r\n"
				+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n" + "where fm.cus_site=:cusSite and aai.status is null and afi.status is null \r\n"
				+ "and trunc(faq.rply_date) between to_date (:fromDate, 'dd/mm/yyyy') AND to_date (:toDate, 'dd/mm/yyyy') "
				+ "union\r\n "
				+ "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
				+ "afi.recd_dt arrivalFPODate,fq.rply_date replyDate,fq.rply_off_id || '-' || def.emp_name officerId,fm.cin_no cinNo,'' articleStage,0 additionalQry,'' currentStatus \r\n"
				+ "from fpo_main fm join fpo_query fq on (fm.item_id=fq.item_id) \r\n"
				+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
				+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
				+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) \r\n"
				+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
				+ " join ops$dir.d_emp def on (def.user_id=fq.rply_off_id) \r\n"
				+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fm.cus_site= :cusSite and aai.status is null and afi.status is null \r\n"
				+ "and trunc(fq.rply_date) between to_date (:fromDate, 'dd/mm/yyyy') AND to_date (:toDate, 'dd/mm/yyyy')) history order by history.replyDate desc";
		try {
			 Query query = entityManager.createNativeQuery(qry, QueryReplyHistoryBean.class);
			 query.setParameter("fromDate", fromDate);
			 query.setParameter("toDate", toDate);
			 query.setParameter("cusSite", cusSite);
			 return (List<QueryReplyHistoryBean>) query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
}


class RatingCompare implements Comparator<FpoQuery> {
	public int compare(FpoQuery query1, FpoQuery query2) {
		if (query1.getQRY_NO() < query2.getQRY_NO())
			return -1;
		if (query1.getQRY_NO() > query2.getQRY_NO())
			return 1;
		else
			return 0;
	}
}
