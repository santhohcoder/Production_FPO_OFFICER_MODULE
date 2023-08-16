package com.demo.fpo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.bean.CustomIdName;
import com.demo.fpo.bean.DepartmentCommentsBean;
import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.model.ReportColumns;
import com.demo.fpo.model.SelectModel;
import com.demo.fpo.prop.StaticConstants;

@Service
@Component
public class DetentionService {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<DetainedArticleBean> getDetainedArticles(HttpSession session, HttpServletRequest request) {
		String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();

		String qry = null;
		if (role.equalsIgnoreCase(StaticConstants.APPRAISER) || role.equalsIgnoreCase(StaticConstants.SUPERINTENDENT)) {
			qry = "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
					+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
					+ "afi.recd_dt arrivalFPODate,fqd.qry_dt detentionDate,cur_off_role currentOfficerRole from fpo_main fm\r\n"
					+ "join fpo_qry_deci fqd on  (fm.item_id=fqd.item_id) \r\n"
					+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
					+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
					+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
					+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
					+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
					+ "left join fpo_detained_info fdi on (fm.item_id=fdi.article_id) where decode(fm.clr_site,null,fm.cus_site,fm.clr_site)=:cuSite    \r\n"
					+ "and qry_type='P5' and aai.status is null and afi.status is null and fdi.detain_completed is null \r\n"
					+ "and (fdi.cur_off_role is null or fdi.cur_off_role= :role )";
		} else if (role.equalsIgnoreCase(StaticConstants.ASSISTANTCOMMISSIONER)) {
			qry = "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
					+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
					+ "afi.recd_dt arrivalFPODate,fqd.qry_dt detentionDate,cur_off_role currentOfficerRole from fpo_main fm\r\n"
					+ "join fpo_qry_deci fqd on  (fm.item_id=fqd.item_id) \r\n"
					+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
					+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
					+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
					+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
					+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
					+ "join fpo_detained_info fdi on (fm.item_id=fdi.article_id) where fdi.cus_site=:cuSite \r\n"
					+ " and qry_type='P5' and aai.status is null and afi.status is null and fdi.detain_completed is null \r\n"
					+ "and fdi.cur_off_role=:role";

		}
		try {
			Query query = entityManager.createNativeQuery(qry,DetainedArticleBean.class);
			query.setParameter("cuSite",cuSite);
			query.setParameter("role",role);
			return (List<DetainedArticleBean>) query.getResultList();	
	//		return entityManager.createNativeQuery(qry, DetainedArticleBean.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	
	public List<DetainArticleHistoryBean> getDetainedArticlesHistory(String cuSite) {
		List<DetainArticleHistoryBean> dethistory=new ArrayList<DetainArticleHistoryBean>();
//		String qry = "select fm.item_id as articleId,fm.job_dt as articleDate,cc.cntry_nm as originCountry, mclc.codedesc as mailClass, ntc.category as itemCategory, aai.recd_event_dt as arrivalOOEDate, afi.recd_dt as arrivalFPODate,fqd.qry_dt as  detentionDate,fdi.detain_decision as detainDecision,fdi.detained_no as  detainedNo,detain_decision_dt as detainDecisionDate from fpo_main fm join fpo_detained_info fdi on  (fm.item_id=fdi.article_id) left join fpo_qry_deci fqd on (fm.item_id=fqd.item_id) left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) left join article_arr_info aai on (fm.item_id=aai.article_id)  left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fm.cus_site=:cuSite  and aai.status is null and afi.status is null and fdi.detain_completed ='Y' order by 9 desc";			
		String qry = "select fm.item_id as articleId,fm.job_dt as articleDate,cc.cntry_nm as originCountry, mclc.codedesc as mailClass, ntc.category as itemCategory, aai.recd_event_dt as arrivalOOEDate, afi.recd_dt as arrivalFPODate,fqd.qry_dt as  detentionDate,fdi.detain_decision as detainDecision,fdi.detained_no as  detainedNo,detain_decision_dt as detainDecisionDate,'' as currentStatus  from fpo_main fm join fpo_detained_info fdi on  (fm.item_id=fdi.article_id) left join fpo_qry_deci fqd on (fm.item_id=fqd.item_id) left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd) left join article_arr_info aai on (fm.item_id=aai.article_id)  left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fm.cus_site=:cuSite  and aai.status is null and afi.status is null and fdi.detain_completed ='Y' ";			
		try {
			Query query = entityManager.createNativeQuery(qry,DetainArticleHistoryBean.class);
			query.setParameter("cuSite",cuSite);
			dethistory =  (List<DetainArticleHistoryBean>)  query.getResultList();
		//	return entityManager.createNativeQuery(qry, DetainArticleHistoryBean.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if ( dethistory.size() > 0 )
		  return dethistory;
		else
		  return null;
	}

	public List<DetainArticleHistoryBean> getDetainedArticlesHistory(String fromDate, String toDate, String cuSite) {
		List<DetainArticleHistoryBean> detarthistory=new ArrayList<DetainArticleHistoryBean>();
		String qry = "select fdi.id,fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
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
				+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) where fdi.cus_site=:cuSite \r\n"
				+ "and qry_type='P5' and aai.status is null and afi.status is null and "
				+ "trunc(fdi.detain_decision_dt) between to_date (:fromDate, 'dd/mm/yyyy') AND to_date (:toDate, 'dd/mm/yyyy') order by fdi.detain_decision_dt desc";
		try {
			Query query = entityManager.createNativeQuery(qry,DetainArticleHistoryBean.class);
			query.setParameter("cuSite",cuSite);
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
			detarthistory =  (List<DetainArticleHistoryBean>)  query.getResultList();
		//	return entityManager.createNativeQuery(qry, DetainArticleHistoryBean.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (detarthistory.size() > 0)
			 return detarthistory;
		else
		    return new ArrayList<>();
	}

	public List<SelectModel> getScnDocument() {
		String qry = "select * from scn_doc order by slno";
		try {
			List<SelectModel> result = entityManager.createNativeQuery(qry, SelectModel.class).getResultList();
			entityManager.clear();
			if (result.size() > 0)
			  return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<SelectModel> getDetainDecision() {
		String qry = "select * from detain_decision order by slno";
		try {
			List<SelectModel> result = entityManager.createNativeQuery(qry, SelectModel.class).getResultList();
			entityManager.clear();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	/*public List<DepartmentCommentsBean> getDeptComments(String cinNo, String cuSite) {
		String qry = "select ooc_id id,off_id officerId,dep_cmts comments,entered_dt enteredDate "
				+ "from fpo_ooc_findings where cin_no = '" + cinNo + "' and cus_site = '" + cuSite
				+ "' order by entered_dt desc";
		try {
			return entityManager.createNativeQuery(qry, DepartmentCommentsBean.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}*/

	public List<SelectModel> getDetainedCaseMethodList(String qry) {
		try {
			List<SelectModel> result = entityManager.createNativeQuery(qry, SelectModel.class).getResultList();
			entityManager.clear();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<CustomIdName> getUQCList(String qry) {
		try {
			List<CustomIdName> result = entityManager.createNativeQuery(qry, CustomIdName.class).getResultList();
			entityManager.clear();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

}
