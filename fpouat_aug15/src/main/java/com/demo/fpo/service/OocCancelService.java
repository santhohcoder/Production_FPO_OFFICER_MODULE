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

import com.demo.fpo.bean.ArticleDatas;
import com.demo.fpo.bean.BagArticleBean;
import com.demo.fpo.bean.DetainArticleHistoryBean;
import com.demo.fpo.bean.DetainedArticleBean;
import com.demo.fpo.bean.OocDeliveryBean;

@Service
@Component
public class OocCancelService {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

/*	public boolean checkOocGiven(String itemId) {
		boolean result = false;
		try {
			String queryStr = "select count(*) from fpo_status where item_id = '" + itemId + "' and ooc_dt is not null";
			Query query = entityManager.createNativeQuery(queryStr);
			List<Object> resultList = query.getResultList();
			result = ((BigDecimal) resultList.get(0)).longValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	
	public List<DetainedArticleBean> getOocCancelProcessArticles(HttpSession session,  HttpServletRequest request) {
		String role = request.getSession().getAttribute("role") == null ? null : request.getSession().getAttribute("role").toString();
		String cuSite = request.getSession().getAttribute("cuSite") == null ? null : request.getSession().getAttribute("cuSite").toString();
		String qry = "select fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry, \r\n"
				+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate, \r\n"
				+ "afi.recd_dt arrivalFPODate,fs.ooc_dt detentionDate,cur_off_role currentOfficerRole from fpo_main fm\r\n"
				+ "join fpo_status fs on  (fm.item_id=fs.item_id) \r\n"
				+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
				+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
				+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
				+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
				+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
				+ " join fpo_ooc_cancel_info foci on (fm.item_id=foci.article_id), ops$dir.fpo_sites s where s.site_code=:cuSite and decode(s.clustered,1,decode(fm.clr_site,null,fm.cus_site,fm.clr_site),fm.cus_site)=:cuSite"
				+ " and aai.status is null and afi.status is null and foci.ooc_cancel_completed is null ";
			//	+ "and aai.status is null and afi.status is null and foci.ooc_cancel_completed is null \r\n"
			//	+ "and foci.cur_off_role=:role";
		try {
			Query query = entityManager.createNativeQuery(qry, DetainedArticleBean.class);
		//	query.setParameter("role",role);
			query.setParameter("cuSite",cuSite);
			List<DetainedArticleBean> resultList = query.getResultList();
			return resultList;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<DetainArticleHistoryBean> getOocCancelledArticlesHistory(String fromDate, String toDate,
			String cusSite) {
		String qry = "select foci.id,fm.item_id articleId,fm.job_dt articleDate,cc.cntry_nm originCountry,\r\n"
				+ "mclc.codedesc mailClass,ntc.category as itemCategory,aai.recd_event_dt arrivalOOEDate,\r\n"
				+ "afi.recd_dt arrivalFPODate,fs.ooc_dt detentionDate,foci.ooc_cancel_status detainDecision,"
				+ "foci.ooc_cancel_no detainedNo,'' currentStatus,\r\n"
				+ "foci.ooc_cancel_dt detainDecisionDate from fpo_main fm\r\n"
				+ "join fpo_status fs on  (fm.item_id=fs.item_id) \r\n"
				+ "left join ops$dir.pdi_mail_class_codes mclc on (fm.mail_class_cd=mclc.code) \r\n"
				+ "left join ops$dir.pdi_nature_trans_codes ntc on (fm.nature_type_cd=ntc.code) \r\n"
				+ "left join ops$dir.d_cntry_cd cc on (fm.send_cntry_cd=cc.cntry_cd)\r\n"
				+ "left join article_arr_info aai on (fm.item_id=aai.article_id) \r\n"
				+ "left join articlearr_fpo_info afi on (fm.item_id=afi.article_id) \r\n"
				+ "left join fpo_ooc_cancel_info foci on (fm.item_id=foci.article_id), ops$dir.fpo_sites s where s.site_code=:cusSite and decode(s.clustered,1,decode(fm.clr_site,null,fm.cus_site,fm.clr_site),fm.cus_site)=:cusSite  \r\n"
				+ " and aai.status is null and afi.status is null and foci.ooc_cancel_completed is not null and \r\n"
				+ "trunc(foci.ooc_cancel_dt) between to_date(:fromDate, 'dd/mm/yyyy') AND to_date (:toDate, 'dd/mm/yyyy') order by foci.ooc_cancel_dt desc";
		try {
			Query query = entityManager.createNativeQuery(qry,DetainArticleHistoryBean.class);
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
			query.setParameter("cusSite",cusSite);
			return (List<DetainArticleHistoryBean>) query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	public OocDeliveryBean getOocDeliveryDetails(String itemId,String cusite) {
		OocDeliveryBean oocDeliveryBean = new OocDeliveryBean();
		try {
			String queryStr = "select fs.item_id articleId,status_desc deliveryStatus,fs.delvy_dt deliveryDate,fs.ooc_dt oocDate from fpo_status fs "
					+ "left join fpo_delivery fd on fs.item_id=fd.item_id left join deli_status_codes dsc on fd.deli_status=dsc.status_code, ops$dir.fpo_sites f, fpo_main m "
					+ " where m.item_id=fs.item_id and f.site_code=:cusite and fs.item_id =:itemId and decode(f.clustered,1,decode(m.clr_site,null,m.cus_site,m.clr_site),m.cus_site)=:cusite";
			Query query = entityManager.createNativeQuery(queryStr, OocDeliveryBean.class);
			query.setParameter("itemId", itemId);
			query.setParameter("cusite", cusite);
			List<OocDeliveryBean> resultList = query.getResultList();
			if (resultList.size() > 0) {
				oocDeliveryBean = ((OocDeliveryBean) resultList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oocDeliveryBean;
	}

}
