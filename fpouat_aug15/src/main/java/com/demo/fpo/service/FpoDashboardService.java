package com.demo.fpo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.fpo.bean.ActiveuserLSM;
import com.demo.fpo.bean.AlertBean;
import com.demo.fpo.bean.CountrywiseOOCPending;
import com.demo.fpo.bean.CtryWsAssCountPercentage;
import com.demo.fpo.bean.CtryWsAssPercentage;
import com.demo.fpo.bean.CtryWsInnerPercentage;
import com.demo.fpo.bean.CtryWsMidPercentage;
import com.demo.fpo.bean.CtryWsPercentage;
import com.demo.fpo.bean.EADInboundArticles;
import com.demo.fpo.bean.MailclassCountryWise;
import com.demo.fpo.bean.OOCDuty;
import com.demo.fpo.bean.OocPercentage;
import com.demo.fpo.bean.PincodeCount;
import com.demo.fpo.bean.TotalArticlesCount;
import com.demo.fpo.bean.TotalCountMailClass;
import com.demo.fpo.bean.TotalIcCount;
import com.demo.fpo.bean.TotalImportCounts;
import com.demo.fpo.model.ReportColumns;

@Service
@Component
public class FpoDashboardService {

	@Autowired
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public TotalCountMailClass gettotcoultr(String cus_site,String role) {
		String qry="";
		TotalCountMailClass totalCountMailClass= new TotalCountMailClass();
	/*	qry = "select ead+aaa+aaf totcoultr, ead1+aaa1+aaf1 totcouems, ead2+aaa2+aaf2 totcoupar, ead3+aaa3+aaf3 totcouemp from\r\n"
				+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site='"+cus_site+"' and mail_class_cd='U')a,  \r\n"
				+"  (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='U' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b,  \r\n"
				+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='U' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c,  \r\n"
				+"  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site='"+cus_site+"' and mail_class_cd='E')a1,  \r\n"
				+"  (select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='E' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b1,  \r\n"
				+"  (select count(*) aaa1 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='E' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c1,  \r\n"
				+"  (select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site='"+cus_site+"' and mail_class_cd='C')a2,   \r\n"
				+"  (select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='C' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b2,  \r\n"
				+"  (select count(*) aaa2 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='C' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c2,  \r\n"
				+"  (select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site='"+cus_site+"' and mail_class_cd='T')a3,   \r\n"
				+"  (select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='T' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b3,  \r\n"
				+"  (select count(*) aaa3 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and mail_class_cd='T' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c3";				
	
		totalCountMailClass =(TotalCountMailClass) entityManager.createNativeQuery(qry,TotalCountMailClass.class).getResultList().get(0);
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cus_site.equalsIgnoreCase("INNSA5"))) && role != null){*/
			qry = "select aaa+aaf+ead totcoultr, aaa1+aaf1+ead1 totcouems, aaa2+aaf2+ead2 totcoupar, aaa3+aaf3+ead3 totcouemp from\r\n"
					+"	(select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site is not null and mail_class_cd='U' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a,  \r\n"
					+"	(select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and mail_class_cd='U' and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y' or arr_scan is null))b, \r\n"
					+"	(select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='U'  and a.cus_site is not null)c, \r\n"
					+"	(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site is not null and mail_class_cd='E'   and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a1,  \r\n"
					+"	(select count(*) aaa1 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and mail_class_cd='E'   and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))b1, \r\n"
					+"	(select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='E' and a.cus_site is not null)c1, \r\n"
					+"	(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and mail_class_cd='C' and a.cin_no=c.cin_no and a.cus_site is not null and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a2,  \r\n"
					+"	(select count(*) aaa2 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no  and mail_class_cd='C' and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))b2, \r\n"
					+"	(select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='C' and a.cus_site is not null)c2, \r\n"
					+"	(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and mail_class_cd='T' and a.cus_site is not null and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a3,  \r\n"
					+"	(select count(*) aaa3 from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no  and mail_class_cd='T'  and a.cus_site is not null and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))b3, \r\n"
					+"	(select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and mail_class_cd='T'  and a.cus_site is not null)c3";
			totalCountMailClass =(TotalCountMailClass) entityManager.createNativeQuery(qry,TotalCountMailClass.class).getResultList().get(0);
//		}
		
		return totalCountMailClass;
			
	}

	public TotalIcCount getItemcategoryCount(String cus_site,String role) {
		String qry="";
		TotalIcCount totalIcCount=new TotalIcCount();
/*		qry = "select ead+aaa+aaf gift, ead1+aaa1+aaf1 saleOfGoods, ead4+aaa4+aaf4 documents, ead2+aaa2+aaf2 returnedGoods, ead5+aaa5+aaf5 others, ead3+aaa3+aaf3 commercialSample from\r\n"
				+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='GIFT')a, \r\n"
				+" (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='GIFT' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b, \r\n"
				+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='GIFT' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'))c, \r\n"
				+"  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='SALE OF GOODS')a1,  \r\n"
				+"  (select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='SALE OF GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b1, \r\n"
				+"  (select count(*) aaa1 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='SALE OF GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c1, \r\n"
				+"  (select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='RETURNED GOODS')a2,  \r\n"
				+"  (select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='RETURNED GOODS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b2, \r\n"
				+"  (select count(*) aaa2 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='RETURNED GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c2, \r\n"
				+"  (select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='COMMERCIAL SAMPLE')a3, \r\n"
				+"  (select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='COMMERCIAL SAMPLE' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b3, \r\n"
				+"  (select count(*) aaa3 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='COMMERCIAL SAMPLE' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c3, \r\n"
				+"   (select count(*) ead4 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='DOCUMENTS')a4,  \r\n"
				+"  (select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='DOCUMENTS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b4, \r\n"
				+"  (select count(*) aaa4 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='DOCUMENTS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c4, \r\n"
				+"  (select count(*) ead5 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c where b.item_id is null and a.cus_site='"+cus_site+"' and nature_type_cd=code and category='OTHERS')a5,  \r\n"
				+"  (select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='OTHERS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b5, \r\n"
				+"  (select count(*) aaa5 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and a.cus_site='"+cus_site+"'  and nature_type_cd=code and category='OTHERS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c5";
		totalIcCount=(TotalIcCount)  entityManager.createNativeQuery(qry,TotalIcCount.class).getResultList().get(0);
		if(role.equalsIgnoreCase("PNA") && cus_site.equalsIgnoreCase("INNSA5") && role!=null) {*/
			qry = "select ead+aaa+aaf gift, ead1+aaa1+aaf1 saleOfGoods, ead4+aaa4+aaf4 documents, ead2+aaa2+aaf2 returnedGoods, ead5+aaa5+aaf5 others, ead3+aaa3+aaf3 commercialSample from\r\n"
					+"	(select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d where a.cin_no=b.cin_no and a.cin_no=c.cin_no and code=nature_type_cd and category='GIFT' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a,  \r\n"
					+"	(select count(*) aaa from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes x where a.cin_no=b.cin_no and code=nature_type_cd and category='GIFT'   and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null )b, \r\n"
					+"	(select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='GIFT'   and a.cus_site is not null)c, \r\n"
					+"	(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='SALE OF GOODS'    and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a1,  \r\n"
					+"	(select count(*) aaa1 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no and code=nature_type_cd and category='SALE OF GOODS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null )b1, \r\n"
					+"	(select count(*) ead1 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='SALE OF GOODS' and a.cus_site is not null)c1, \r\n"
					+"	(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no  and code=nature_type_cd and category='RETURNED GOODS'  and a.cin_no=c.cin_no  and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a2,  \r\n"
					+"	(select count(*) aaa2 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no  and code=nature_type_cd and category='RETURNED GOODS' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null )b2, \r\n"
					+"	(select count(*) ead2 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null and code=nature_type_cd and category='RETURNED GOODS' and a.cus_site is not null)c2, \r\n"
					+"	(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='COMMERCIAL SAMPLE'  and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a3,  \r\n"
					+"	(select count(*) aaa3 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='COMMERCIAL SAMPLE'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null)b3, \r\n"
					+"	(select count(*) ead3 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='COMMERCIAL SAMPLE'   and a.cus_site is not null)c3, \r\n"
				      +"	(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='DOCUMENTS' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a4,  \r\n"
					+"	(select count(*) aaa4 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='DOCUMENTS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null)b4, \r\n"
					+"	(select count(*) ead4 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='DOCUMENTS'   and a.cus_site is not null)c4, \r\n"
				      +"	(select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,  fpo_status c, ops$dir.pdi_nature_trans_codes d  where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and code=nature_type_cd and category='OTHERS'   and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a5,  \r\n"
					+"	(select count(*) aaa5 from fpo_main a,  fpo_curr_que b, ops$dir.pdi_nature_trans_codes c where a.cin_no=b.cin_no    and code=nature_type_cd  and category='OTHERS'  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null)  and a.cus_site is not null)b5, \r\n"
					+"	(select count(*) ead5 from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id, ops$dir.pdi_nature_trans_codes c  where b.item_id is null  and code=nature_type_cd  and category='OTHERS'   and a.cus_site is not null)c5 ";
			totalIcCount=(TotalIcCount)  entityManager.createNativeQuery(qry,TotalIcCount.class).getResultList().get(0);
//		}
		return totalIcCount;
			
	}

	
	public TotalImportCounts getTotalImportCount(String cus_site,String role) {
//		if(role.equalsIgnoreCase("PNA") && cus_site.equalsIgnoreCase("INNSA5") && role!=null) {
			String qry="select coua+coub personalImports,detained,commercialImports from\r\n"
					+ "(select count(*) Detained from fpo_main a,  fpo_curr_que b where a.cus_site is not null\r\n"
					+ " and a.cin_no=b.cin_no  and b.curr_que='P5' ) a,\r\n"
					+ "(select count(*) commercialImports from fpo_main a,  fpo_curr_que b where a.cus_site is not null \r\n"
					+ "and a.cin_no=b.cin_no  and b.curr_que='P6' )b,\r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where a.cus_site is not null \r\n"
					+ "and a.cin_no=b.cin_no and b.curr_que  in ('P1','P2','E1','E2','E3','E4','N1','N2','A1','A2','A3','P3','P4','P9','C1','C2'))c,\r\n"
					+ "(select count(*) coub from (select cin_no from fpo_main a  where a.cus_site is not null\r\n"
					+ "minus select cin_no from fpo_curr_que b where b.cus_site is not null))d";

			return (TotalImportCounts) entityManager.createNativeQuery(qry, TotalImportCounts.class).getResultList().get(0);
	//	}
	/*	String qry = "select coua+coub personalImports,detained,commercialImports from\r\n"
				+ "(select count(*) Detained from fpo_main a,  fpo_curr_que b where a.cus_site='" + cus_site
				+ "' and a.cin_no=b.cin_no  and b.curr_que='P5' ) a,\r\n"
				+ "(select count(*) commercialImports from fpo_main a,  fpo_curr_que b where a.cus_site='" + cus_site
				+ "' and a.cin_no=b.cin_no  and b.curr_que='P6' )b,\r\n" + "\r\n"
				+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where a.cus_site='" + cus_site
				+ "' and a.cin_no=b.cin_no and b.curr_que  in ('P1','P2','E1','E2','E3','E4','N1','N2','A1','A2','A3','P3','P4','P9','C1','C2'))c,\r\n"
				+ "(select count(*) coub from\r\n" + "(select cin_no from fpo_main a  where a.cus_site='" + cus_site
				+ "'\r\n" + "minus\r\n" + "select cin_no from fpo_curr_que b where b.cus_site='" + cus_site + "'))d\r\n"
				+ "";

		return (TotalImportCounts) entityManager.createNativeQuery(qry, TotalImportCounts.class).getResultList().get(0);*/

	}

	public TotalArticlesCount getTotalArticleCounts(String cus_site,String role) {
		String qry="";
		TotalArticlesCount totalArticlesCount=new TotalArticlesCount();
	/*	qry = "select ead, aaa, aaf from \r\n"
				+" (select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site='"+cus_site +"')a,   \r\n"
				+" (select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no and a.cus_site='"+cus_site +"' and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)b,  \r\n"
				+"  (select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no and a.cus_site='"+cus_site +"' and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_fpo is null))c";
		totalArticlesCount=  (TotalArticlesCount) entityManager.createNativeQuery(qry,TotalArticlesCount.class).getResultList().get(0);*/
		
		//if(role.equalsIgnoreCase("PNA") && cus_site.equalsIgnoreCase("INNSA5") )
		{
			qry = "select ead, aaa, aaf from\r\n"
				+"	(select count(*) aaf from fpo_main a,  fpo_qry_deci b,  fpo_status c where a.cin_no=b.cin_no and a.cin_no=c.cin_no  and qry_type  in ('P1','P2','P3','P4','A3','C1','C2','P5','P6','P7') and ( arr_scan='Y') and ooc_dt is null)a,  \r\n"
				+"	(select count(*) aaa from fpo_main a,  fpo_curr_que b where a.cin_no=b.cin_no  and curr_que in ('N1','N2','P1','P2','P3','P4','A2','P6','C1','C2','P5','P7','E4') and (arr_scan<>'Y'  or arr_scan is null))b, \r\n"
				+"	(select count(*) ead from fpo_main a left join fpo_qry_deci b on a.item_id=b.item_id where b.item_id is null and a.cus_site is not null)c";

			totalArticlesCount= (TotalArticlesCount) entityManager.createNativeQuery(qry,TotalArticlesCount.class).getResultList().get(0);
		}
		
		
		return totalArticlesCount;
			
	}

	
	
	
	
	public List<PincodeCount> getWpPincode() {
		String qry = "select ROW_NUMBER() OVER (ORDER BY c.cus_site) as id,nvl(coua,0)+coub no_articles,c.cus_site from\r\n"
				+ "(select count(*) coub, cus_site from fpo_main  where cin_no in \r\n"
				+ "(select cin_no from fpo_main \r\n" + "minus\r\n"
				+ "select cin_no from fpo_curr_que) group by cus_site)c left join\r\n"
				+ "(select count(*) coua, a.cus_site from fpo_main a,  fpo_curr_que  b  \r\n"
				+ "where  curr_que in ('E1','E2','E3') and a.cin_no=b.cin_no group by a.cus_site) d \r\n"
				+ "on c.cus_site=d.cus_site order by cus_site";

		return entityManager.createNativeQuery(qry, PincodeCount.class).getResultList();
	}

	/*public List<EADInboundArticles> getCountryWiseArticles(String cus_site) {
		/*
		 * String qry = "select send_cntry_cd country\r\n" +
		 * "       , count(*) noArticles\r\n" +
		 * "       , nvl(SUM(TOT_ASS_VAL),0) totAssVal\r\n" + "	from\r\n" +
		 * "         fpo_main\r\n" + "	where\r\n" +
		 * "         trunc(to_date(substr(postingdt,9,2)\r\n" +
		 * "                  ||'/'\r\n" +
		 * "                  ||substr(postingdt,6,2)\r\n" +
		 * "                  ||'/'\r\n" +
		 * "                  ||substr(postingdt,0,4),'DD/MM/YYYY')) between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM') and LAST_DAY(ADD_MONTHS(SYSDATE,-8))\r\n"
		 * + "         and cus_site='"+cus_site+"'\r\n" + "	group by\r\n" +
		 * "         send_cntry_cd\r\n" + "	order by\r\n" + "	2 desc\r\n" +
		 * "	,3 desc";
		 */
// the below one is the correct one
/*		String qry = "select * from (select send_cntry_cd country\r\n" + "       , count(*) noArticles\r\n"
				+ "       , nvl(SUM(TOT_ASS_VAL),0) totAssVal\r\n" + "	from\r\n" + "         fpo_main\r\n"
				+ "	where\r\n" + "         trunc(to_date(substr(postingdt,9,2)\r\n" + "                  ||'/'\r\n"
				+ "                  ||substr(postingdt,6,2)\r\n" + "                  ||'/'\r\n"
				+ "                  ||substr(postingdt,0,4),'DD/MM/YYYY')) between TRUNC(ADD_MONTHS(SYSDATE, 
),'MM') and LAST_DAY(ADD_MONTHS(SYSDATE,-8))\r\n"
				+ "         and cus_site='" + cus_site + "' \r\n" + "	group by\r\n" + "         send_cntry_cd\r\n"
				+ "	order by\r\n" + "	2 desc\r\n" + "	,3 desc )where rownum <= 5";

		return entityManager.createNativeQuery(qry, EADInboundArticles.class).getResultList();
	}*/

	public List<CountrywiseOOCPending> getCountryWisePendingArticles(String cuSite) {
		String qry = "select  send_cntry_cd country  , count(*) noArticles, round(nvl(SUM(TOT_ASS_VAL),0),2) totAssVal from  fpo_main a,fpo_status b where a.cin_no =b.cin_no and ooc_dt is null and a.cus_site=:cuSite group by send_cntry_cd order by 2 desc , 3 desc";
		
        Query query = entityManager.createNativeQuery(qry,CountrywiseOOCPending.class);
        query.setParameter("cuSite",cuSite);
		return  query.getResultList();
	//	return entityManager.createNativeQuery(qry, CountrywiseOOCPending.class).getResultList();
	}

	public OocPercentage getOOCPercentage() {
		String qry ="select\r\n"
				+ "      yes_duty withDutyArticles\r\n"
				+ "     , no_duty_concession noDutyConcession\r\n"
				+ "     , yes_duty+no_duty_concession noArticles\r\n"
				+ "     , duty_payable dutyPayable\r\n"
				+ "     , totAmount totAmount\r\n"
				+ "     , CASE WHEN yes_duty = 0 or no_duty_concession=0 THEN 0 ELSE round(yes_duty/(yes_duty+no_duty_concession)*100,2) END as withDutyPercentage\r\n"
				+ "     , CASE WHEN yes_duty = 0 or no_duty_concession=0 THEN 0 ELSE round(no_duty_concession /(yes_duty+no_duty_concession)*100,2) END as noDutyConcessionPercentage\r\n"
				+ "     , to_char(ADD_MONTHS(SYSDATE,-1),'MON-YYYY') lastMonth \r\n"
				+ "from\r\n"
				+ "       (\r\n"
				+ "              select\r\n"
				+ "                     count(*) yes_duty\r\n"
				+ "                   , sum(tot_amt_payable) Duty_payable\r\n"
				+ "              from\r\n"
				+ "                     fpo_main   a\r\n"
				+ "                   ,  fpo_status b\r\n"
				+ "              where\r\n"
				+ "                     a.cin_no =b.cin_no\r\n"
				+ "                     and ooc_dt is not null\r\n"
				+ "                     and tot_amt_payable  > 0 \r\n"
				+ "                     and trunc(to_date(substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "                     between TRUNC(ADD_MONTHS(SYSDATE, -9),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-1))\r\n"
				+ "       )\r\n"
				+ "       a,\r\n"
				+ "       ( select\r\n"
				+ "                     count(*) no_duty_concession,\r\n"
				+ "                     sum(tot_duty) totAmount\r\n"
				+ "              from\r\n"
				+ "                     fpo_main a\r\n"
				+ "                   ,  fpo_status b\r\n"
				+ "              where\r\n"
				+ "                     a.cin_no =b.cin_no\r\n"
				+ "                     and ooc_dt is not null\r\n"
				+ "                     and tot_duty!=tot_amt_payable\r\n"
				+ "                     and trunc(to_date(substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "                     between TRUNC(ADD_MONTHS(SYSDATE, -9),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-1))) c";	

		return (OocPercentage) entityManager.createNativeQuery(qry, OocPercentage.class).getResultList().get(0);

	}

	public List<MailclassCountryWise> getCountryWiseMailClass(String cuSite) {
		String qry = " select a.send_cntry_cd country, totAssVal, totAssValLtr, totAssValEms, totAssValPar,totAssValEmp from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssVal from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE, -8)) and cus_site=:cuSite"
				+ " group by send_cntry_cd\r\n" + "order by 2 desc ) a left join \r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValLtr from fpo_main where mail_class_cd='U' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE, -8)) and cus_site=:cuSite group by send_cntry_cd) b  on a.send_cntry_cd=b.send_cntry_cd left join \r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValEms from fpo_main where mail_class_cd='E' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE, -8)) and cus_site= :cuSite group by send_cntry_cd) c  on a.send_cntry_cd=c.send_cntry_cd left join \r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValPar from fpo_main where mail_class_cd='C' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE, -8)) and cus_site=:cuSite group by send_cntry_cd)d  on a.send_cntry_cd=d.send_cntry_cd left join \r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValEmp from fpo_main where mail_class_cd='T' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE, -8)) and cus_site=:cuSite group by send_cntry_cd) e  on a.send_cntry_cd=c.send_cntry_cd\r\n"
				+ " where rownum  <= 5  order by 2 desc";
		List<MailclassCountryWise> mcwise=new ArrayList<MailclassCountryWise>();
        Query query = entityManager.createNativeQuery(qry,MailclassCountryWise.class);
        query.setParameter("cuSite",cuSite);
        mcwise = (List<MailclassCountryWise>) query.getResultList();
		return mcwise;
	}

//	public List<MailclassCountryWiseNoArt> getCountryWiseMailClassNoArticles(String cus_site) {
//		String qry = " select a.send_cntry_cd country, totAssVal, totAssValLtr, totAssValEms, totAssValPar,totAssValEmp from\r\n"
//				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssVal from fpo_main where\r\n"
//				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
//				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site='"+cus_site+"' group by send_cntry_cd\r\n"
//				+ "order by 2 desc ) a left join \r\n"
//				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValLtr from fpo_main where mail_class_cd='U' and\r\n"
//				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
//				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site='"+cus_site+"' group by send_cntry_cd) b  on a.send_cntry_cd=b.send_cntry_cd left join \r\n"
//				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValEms from fpo_main where mail_class_cd='E' and\r\n"
//				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
//				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site='"+cus_site+"' group by send_cntry_cd) c  on a.send_cntry_cd=c.send_cntry_cd left join \r\n"
//				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValPar from fpo_main where mail_class_cd='C' and\r\n"
//				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
//				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site='"+cus_site+"' group by send_cntry_cd)d  on a.send_cntry_cd=d.send_cntry_cd left join \r\n"
//				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) totAssValEmp from fpo_main where mail_class_cd='T' and\r\n"
//				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
//				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site='"+cus_site+"' group by send_cntry_cd) e  on a.send_cntry_cd=c.send_cntry_cd\r\n"
//				+ " where rownum  <= 20  order by 2 desc";
//		
//		return entityManager.createNativeQuery(qry,MailclassCountryWise.class).getResultList();
//	}

	public List<CtryWsPercentage> getCountryWisePercentage(String cuSite,String role) {
		List<CtryWsPercentage> cntrywisepercent = new ArrayList<CtryWsPercentage>();
		String qry = "";
		int st=0;
		qry = "select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,0) volumePercentage,length(send_cntry_cd) len,tot_articles totArt,tot_articles_top10 noOfValue,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
				+ "(select send_cntry_cd, tot_articles_top10,tot_articles from\r\n"
				+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
				+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite\r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) a, \r\n"
				+ "(select count(*) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite ) b,\r\n"
				+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
				+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite \r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) c \r\n"
				+ "union\r\n"
				+ "select 'OTHERS', tot_articles - other_articles_sum tot_articles, tot_articles from\r\n"
				+ "(select sum(tot_articles_top10) other_articles_sum  from\r\n"
				+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
				+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite \r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5)) c ,\r\n"
				+ "(select count(*) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite )d)\r\n"
				+ "order by 3, 2 desc";
		
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
			st=1;
			qry = "select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,0) volumePercentage,length(send_cntry_cd) len,tot_articles totArt,tot_articles_top10 noOfValue,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
					+ "(select send_cntry_cd, tot_articles_top10,tot_articles from\r\n"
					+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
					+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) a, \r\n"
					+ "(select count(*) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null) b,\r\n"
					+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
					+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) c \r\n"
					+ "union\r\n"
					+ "select 'OTHERS', tot_articles - other_articles_sum tot_articles, tot_articles from\r\n"
					+ "(select sum(tot_articles_top10) other_articles_sum  from\r\n"
					+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
					+ "(select send_cntry_cd,count(*) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5)) c ,\r\n"
					+ "(select count(*) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null)d)\r\n"
					+ "order by 3, 2 desc";}
	    
	
		 Query query = entityManager.createNativeQuery(qry,CtryWsPercentage.class);
		 if (st==0)
			 query.setParameter("cuSite",cuSite);
		 cntrywisepercent  = (List<CtryWsPercentage>) query.getResultList();
		//entityManager.refresh(ctryWsPercentage);
		return cntrywisepercent;
	}
	
	public CtryWsMidPercentage getCtryWsMidPercentage(String cuSite,String role) {
		String qry ="";
		int st=0;
		CtryWsMidPercentage ctryWsMidPercentage=new CtryWsMidPercentage();
		qry = "select CASE WHEN letters = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(letters/tot_articles*100,0)) END as letters, letters ltrVal, CASE WHEN ems = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(ems/tot_articles*100,0)) END as ems, ems emsVal,\r\n"
				+ "CASE WHEN parcels = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(parcels/tot_articles*100,0)) END as parcels,parcels parVal,CASE WHEN Emp_recep = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Emp_recep/tot_articles*100,0)) END as empRecep,Emp_recep empRecepVal from\r\n"
				+ "(select count(*) letters from fpo_main where mail_class_cd='U' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)a,\r\n"
				+ "(select count(*) ems from fpo_main where mail_class_cd='E' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)b,\r\n"
				+ "(select count(*) parcels from fpo_main where mail_class_cd='C' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)c,\r\n"
				+ "(select count(*) Emp_recep from fpo_main where mail_class_cd='T' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)d,\r\n"
				+ "(select count(*) tot_articles from fpo_main where \r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)e";
	//	ctryWsMidPercentage= (CtryWsMidPercentage)  entityManager.createNativeQuery(qry,CtryWsMidPercentage.class).getResultList().get(0);
		
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
			st=1;
		    qry = "select CASE WHEN letters = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(letters/tot_articles*100,0)) END as letters, letters ltrVal, CASE WHEN ems = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(ems/tot_articles*100,0)) END as ems, ems emsVal,\r\n"
					+ "CASE WHEN parcels = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(parcels/tot_articles*100,0)) END as parcels,parcels parVal,CASE WHEN Emp_recep = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Emp_recep/tot_articles*100,0)) END as empRecep,Emp_recep empRecepVal from\r\n"
					+ "(select count(*) letters from fpo_main where mail_class_cd='U' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) )a,\r\n"
					+ "(select count(*) ems from fpo_main where mail_class_cd='E' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) )b,\r\n"
					+ "(select count(*) parcels from fpo_main where mail_class_cd='C' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))c,\r\n"
					+ "(select count(*) Emp_recep from fpo_main where mail_class_cd='T' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))d,\r\n"
					+ "(select count(*) tot_articles from fpo_main where \r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))e";}
		//	ctryWsMidPercentage= (CtryWsMidPercentage)  entityManager.createNativeQuery(qry,CtryWsMidPercentage.class).getResultList().get(0);
			Query query = entityManager.createNativeQuery(qry,CtryWsMidPercentage.class);
			 if (st==0)
				 query.setParameter("cuSite",cuSite);
			 ctryWsMidPercentage  =  (CtryWsMidPercentage) query.getResultList().get(0);
			//entityManager.refresh(ctryWsPercentage);
		//	return cntrywisepercent;
		return ctryWsMidPercentage;

	}
	public CtryWsInnerPercentage getCtryWsInnerPercentage(String cuSite, String role) {
		String qry = "";
		int st=0;
		CtryWsInnerPercentage ctryWsInnerPercentage=new CtryWsInnerPercentage();
		qry = "select CASE WHEN gift = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(gift/tot_articles*100,0)) END as  gift,gift noOfGift, CASE WHEN Sale_of_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Sale_of_goods/tot_articles*100,0)) END as saleOfGoods,Sale_of_goods noOfSaleOfGoods,\r\n"
				+ " CASE WHEN returned_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(returned_goods/tot_articles*100,0)) END as returnedGoods,returned_goods noOfReturnedGoods,\r\n"
				+ "CASE WHEN commercial_sample = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(commercial_sample/tot_articles*100,0)) END as  commercialSample,commercial_sample noOfCommercialSample,\r\n"
				+ "CASE WHEN documents = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(documents/tot_articles*100,0)) END as  documents,documents noOfDocuments,\r\n"
				+ "CASE WHEN others = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(others/tot_articles*100,0)) END as  others,others noOfOthers from\r\n"
				+ "(select count(*) gift from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='GIFT' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)a,\r\n"
				+ "(select count(*) Sale_of_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='SALE OF GOODS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)b,\r\n"
				+ "(select count(*) returned_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='RETURNED GOODS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)c,\r\n"
				+ "(select count(*) commercial_sample from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='COMMERCIAL SAMPLE' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)d,\r\n"
				+ "(select count(*) documents from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='DOCUMENTS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)e,\r\n"
				+ "(select count(*) others from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='OTHERS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)f,\r\n"
				+ "(select count(*) tot_articles from fpo_main  where \r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)g";
	//	ctryWsInnerPercentage=(CtryWsInnerPercentage)  entityManager.createNativeQuery(qry,CtryWsInnerPercentage.class).getResultList().get(0);
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
			st=1;
			qry = "select CASE WHEN gift = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(gift/tot_articles*100,0)) END as  gift,gift noOfGift, CASE WHEN Sale_of_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Sale_of_goods/tot_articles*100,0)) END as saleOfGoods,Sale_of_goods noOfSaleOfGoods,\r\n"
					+ " CASE WHEN returned_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(returned_goods/tot_articles*100,0)) END as returnedGoods,returned_goods noOfReturnedGoods,\r\n"
					+ "CASE WHEN commercial_sample = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(commercial_sample/tot_articles*100,0)) END as  commercialSample,commercial_sample noOfCommercialSample,\r\n"
					+ "CASE WHEN documents = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(documents/tot_articles*100,0)) END as  documents,documents noOfDocuments,\r\n"
					+ "CASE WHEN others = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(others/tot_articles*100,0)) END as  others,others noOfOthers from\r\n"
					+ "(select count(*) gift from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='GIFT' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))a,\r\n"
					+ "(select count(*) Sale_of_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='SALE OF GOODS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))b,\r\n"
					+ "(select count(*) returned_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='RETURNED GOODS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))c,\r\n"
					+ "(select count(*) commercial_sample from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='COMMERCIAL SAMPLE' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))d,\r\n"
					+ "(select count(*) documents from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='DOCUMENTS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))e,\r\n"
					+ "(select count(*) others from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='OTHERS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))f,\r\n"
					+ "(select count(*) tot_articles from fpo_main  where \r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))g";}
			 Query query = entityManager.createNativeQuery(qry,CtryWsInnerPercentage.class);
			 if (st==0)
				 query.setParameter("cuSite",cuSite);
			 ctryWsInnerPercentage  =  (CtryWsInnerPercentage) query.getResultList().get(0);
			//entityManager.refresh(ctryWsPercentage);
		//	return cntrywisepercent;
		return ctryWsInnerPercentage;
		}
		
	
	public List<CtryWsAssPercentage> getCountryWiseAssValPercentage(String cuSite,String role) {
		String qry = "";
		int st=0;
		List<CtryWsAssPercentage> ctryWsAssPercentage=new ArrayList<CtryWsAssPercentage>();
		qry = "select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,0) volumePercentage,length(send_cntry_cd) len,round(tot_articles,0) totArt,round(tot_articles_top10,0) noOfValue,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
				+ "(select send_cntry_cd, tot_articles_top10,tot_articles from\r\n"
				+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite\r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) a, \r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite) b,\r\n"
				+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite\r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) c \r\n"
				+ "union\r\n"
				+ "select 'OTHERS', tot_articles - other_articles_sum tot_articles, tot_articles from\r\n"
				+ "(select sum(tot_articles_top10) other_articles_sum  from\r\n"
				+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite\r\n"
				+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5)) c ,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)d)\r\n"
				+ "order by 3, 2 desc\r\n"
				+ "";
	//	ctryWsAssPercentage= (List<CtryWsAssPercentage>)entityManager.createNativeQuery(qry,CtryWsAssPercentage.class).getResultList();
	//	entityManager.clear();
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
			st=1;
			qry = "select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,0) volumePercentage,length(send_cntry_cd) len,round(tot_articles,0) totArt,round(tot_articles_top10,0) noOfValue,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
					+ "(select send_cntry_cd, tot_articles_top10,tot_articles from\r\n"
					+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
					+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) a, \r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null) b,\r\n"
					+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
					+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5) c \r\n"
					+ "union\r\n"
					+ "select 'OTHERS', tot_articles - other_articles_sum tot_articles, tot_articles from\r\n"
					+ "(select sum(tot_articles_top10) other_articles_sum  from\r\n"
					+ "(select send_cntry_cd, tot_articles tot_articles_top10 from\r\n"
					+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null\r\n"
					+ "group by send_cntry_cd order by 2 desc ) where rownum <= 5)) c ,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site is not null)d)\r\n"
					+ "order by 3, 2 desc\r\n"
					+ "";}
		 Query query = entityManager.createNativeQuery(qry,CtryWsAssPercentage.class);
		 if (st==0)
			 query.setParameter("cuSite",cuSite);
		 ctryWsAssPercentage  =  (List<CtryWsAssPercentage>) query.getResultList();		
		return  ctryWsAssPercentage;
		
	}
	
	public CtryWsMidPercentage getCtryWsMidAssValPercentage(String cuSite,String role) {
		String qry ="";
		int st=0;
		CtryWsMidPercentage ctryWsMidPercentage= new CtryWsMidPercentage();
		qry = "select CASE WHEN letters = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(letters/tot_articles*100,0)) END as letters,round(letters,0) ltrVal, CASE WHEN ems = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(ems/tot_articles*100,0)) END as ems,round(ems,0) emsVal,\r\n"
				+ "CASE WHEN parcels = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(parcels/tot_articles*100,0)) END as parcels,round(parcels,0) parVal,CASE WHEN Emp_recep = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Emp_recep/tot_articles*100,0)) END as empRecep,round(Emp_recep,0) empRecepVal from\r\n"
				+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) letters from fpo_main where mail_class_cd='U' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)a,\r\n"
				+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) ems from fpo_main where mail_class_cd='E' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)b,\r\n"
				+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) parcels from fpo_main where mail_class_cd='C' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)c,\r\n"
				+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) Emp_recep from fpo_main where mail_class_cd='T' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)d,\r\n"
				+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) tot_articles from fpo_main where \r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)e";
		//ctryWsMidPercentage=(CtryWsMidPercentage)  entityManager.createNativeQuery(qry,CtryWsMidPercentage.class).getResultList().get(0);
		if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
			st=1;
			qry = "select CASE WHEN letters = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(letters/tot_articles*100,0)) END as letters,round(letters,0) ltrVal, CASE WHEN ems = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(ems/tot_articles*100,0)) END as ems,round(ems,0) emsVal,\r\n"
					+ "CASE WHEN parcels = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(parcels/tot_articles*100,0)) END as parcels,round(parcels,0) parVal,CASE WHEN Emp_recep = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Emp_recep/tot_articles*100,0)) END as empRecep,round(Emp_recep,0) empRecepVal from\r\n"
					+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) letters from fpo_main where mail_class_cd='U' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) )a,\r\n"
					+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) ems from fpo_main where mail_class_cd='E' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))b,\r\n"
					+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) parcels from fpo_main where mail_class_cd='C' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))c,\r\n"
					+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) Emp_recep from fpo_main where mail_class_cd='T' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) )d,\r\n"
					+ "(select COALESCE(nvl(SUM(TOT_ASS_VAL),0),0) tot_articles from fpo_main where \r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))e";
		//	ctryWsMidPercentage=(CtryWsMidPercentage)  entityManager.createNativeQuery(qry,CtryWsMidPercentage.class).getResultList().get(0);
		}
		 Query query = entityManager.createNativeQuery(qry,CtryWsMidPercentage.class);
		 if (st==0)
			 query.setParameter("cuSite",cuSite);
		 ctryWsMidPercentage  =  (CtryWsMidPercentage) query.getResultList().get(0);		
		
		return ctryWsMidPercentage;
			
	}
	
	public CtryWsInnerPercentage getCtryWsInnerAssValPercentage(String cuSite,String role) {
		String qry = "";
		int st=0;
		CtryWsInnerPercentage ctryWsInnerPercentage=new CtryWsInnerPercentage();
		qry = "select CASE WHEN gift = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(gift/tot_articles*100,0)) END as  gift,round(gift,0) noOfGift, CASE WHEN Sale_of_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Sale_of_goods/tot_articles*100,0)) END as saleOfGoods,round(Sale_of_goods,0) noOfSaleOfGoods,\r\n"
				+ " CASE WHEN returned_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(returned_goods/tot_articles*100,0)) END as returnedGoods,round(returned_goods,0) noOfReturnedGoods,\r\n"
				+ "CASE WHEN commercial_sample = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(commercial_sample/tot_articles*100,0)) END as  commercialSample,round(commercial_sample,0) noOfCommercialSample, \r\n"
				+ "CASE WHEN documents = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(documents/tot_articles*100,0)) END as  documents,round(documents,0) noOfDocuments,\r\n"
				+ "CASE WHEN others = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(others/tot_articles*100,0)) END as  others,round(others,0) noOfOthers from\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) gift from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='GIFT' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)a,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) Sale_of_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='SALE OF GOODS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)b,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) returned_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='RETURNED GOODS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)c,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) commercial_sample from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='COMMERCIAL SAMPLE' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)d,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) documents from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='DOCUMENTS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)e,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) others from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='OTHERS' and\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)f,\r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main  where \r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite)g\r\n";
			//ctryWsInnerPercentage=(CtryWsInnerPercentage)  entityManager.createNativeQuery(qry,CtryWsInnerPercentage.class).getResultList().get(0);
			if((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && role != null){
				st=1;
				qry = "select CASE WHEN gift = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(gift/tot_articles*100,0)) END as  gift,round(gift,0) noOfGift, CASE WHEN Sale_of_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(Sale_of_goods/tot_articles*100,0)) END as saleOfGoods,round(Sale_of_goods,0) noOfSaleOfGoods,\r\n"
					+ " CASE WHEN returned_goods = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(returned_goods/tot_articles*100,0)) END as returnedGoods,round(returned_goods,0) noOfReturnedGoods,\r\n"
					+ "CASE WHEN commercial_sample = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(commercial_sample/tot_articles*100,0)) END as  commercialSample,round(commercial_sample,0) noOfCommercialSample, \r\n"
					+ "CASE WHEN documents = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(documents/tot_articles*100,0)) END as  documents,round(documents,0) noOfDocuments,\r\n"
					+ "CASE WHEN others = 0 or tot_articles = 0  THEN '0' ELSE to_char(round(others/tot_articles*100,0)) END as  others,round(others,0) noOfOthers from\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) gift from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='GIFT' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) )a,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) Sale_of_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='SALE OF GOODS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))b,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) returned_goods from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='RETURNED GOODS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))c,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) commercial_sample from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='COMMERCIAL SAMPLE' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))d,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) documents from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='DOCUMENTS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))e,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) others from fpo_main a, ops$dir.pdi_nature_trans_codes b where nature_type_cd=code and category='OTHERS' and\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))f,\r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main  where \r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)))g\r\n";
		//	ctryWsInnerPercentage=(CtryWsInnerPercentage)  entityManager.createNativeQuery(qry,CtryWsInnerPercentage.class).getResultList().get(0);
		}
			 Query query = entityManager.createNativeQuery(qry,CtryWsInnerPercentage.class);
			 if (st==0)
				 query.setParameter("cuSite",cuSite);
			 ctryWsInnerPercentage  =  (CtryWsInnerPercentage) query.getResultList().get(0);		
	
		
		return ctryWsInnerPercentage;
			
	}

	public TotalArticlesCount getUserWiseTotalArticleCounts(String cuSite, String offId, String sessionRole) {
		String qry = "";
		int st=0;
		int off=0;
		TotalArticlesCount articleCount = new TotalArticlesCount();
		if (sessionRole.equalsIgnoreCase("PAC") && sessionRole != null) {
			
		   qry= "select ead, aaa1+aaa2 aaa , aaf from\r\n"
		   		+ "(select count(*) ead from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no   where t1.role='PAC' and (acL_offid is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
		   		+ "and t2.cin_no is null and set_aside is null ) a, \r\n"
		   		+ "(sELECT count(*) aaa1\r\n"
		   		+ "						from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
		   		+ "						and q.cin_no=r.cin_no and r.cus_site= :cuSite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
		   		+ "			           ) b,\r\n"
		   		+ "(sELECT count(*) aaa2\r\n"
		   		+ "						from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
		   		+ "						and q.cin_no=r.cin_no and qry_def_slno is null and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c,\r\n"
		   		+ "(select count(*) aaf from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no\r\n"
		   		+ "					where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
		   		+ "			            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC' and t1.acl_offid=:offId) d\r\n";
		  off=1;

		} else if (sessionRole.equalsIgnoreCase("PAO") && sessionRole != null) {
			qry="select ead, aaa1+aaa2 aaa,aaf1+aaf2+aaf3+aaf4+aaf5 aaf from \r\n"
					+ "					(select count(*) ead from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "																			and k.user_id=:offId and k.role_name='PAO'   and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a, \r\n"
					+ "												(sELECT  count(*) aaa1\r\n"
					+ "			from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId\r\n"
					+ "			and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))b,\r\n"
					+ "			(sELECT count(*) aaa2 \r\n"
					+ "			from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r\r\n"
					+ "			left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "			left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no    where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and\r\n"
					+ "			(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "			and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
					+ "			and s.role_name = 'PAO')c, \r\n"
					+ "(sELECT count(*) aaf1 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "								            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j,  ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "								            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
					+ "								            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.id in (select max(id) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "								            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) \r\n"
					+ "								            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO' )t,\r\n"
					+ "(sELECT count(*) aaf2 \r\n"
					+ "					from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "			        where i.cus_site=:cuSite  and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3')\r\n"
					+ "			       and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "					and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u,\r\n"
					+ "(sELECT count(*) aaf3 \r\n"
					+ "			from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "             where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3')\r\n"
					+ "           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "			and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null \r\n"
					+ "            and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v,                       \r\n"
					+ "(sELECT count(*) aaf4 from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  \r\n"
					+ "  where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "  and  q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null ) x,\r\n"
					+ "(sELECT count(*) aaf5 from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y'  and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null) y \r\n";
		
			off=1;

			/*qry = "select coua,coub,couc,coua+coub+couc ead, aaa, aaf from\r\n"
					+ "				(select count(*) coub from\r\n"
					+ "(select x.cin_no from fpo_main x,ops$dir.d_emp_roles k,\r\n"
					+ "						(select cin_no from fpo_main where cus_site = '" + cus_site +"' \r\n"
					+ "						 minus\r\n"
					+ "						select cin_no from fpo_curr_que  where cus_site='" + cus_site +"' )y where  x.cin_no=y.cin_no and ((x.off_id='" + offId + "' or x.off_id is null) and (x.role='PAO'  or  x.role is null)) \r\n"
					+ "                        and k.user_id='" + offId + "' and instr(k.mail_class_cd,x.mail_class_cd) > 0)) a,\r\n"
					+ "				        \r\n"
					+ "                       (select count(*) coua from \r\n"
					+ "            (select x.cin_no from fpo_main x, ops$dir.d_emp_roles k,          \r\n"
					+ "                        (select cin_no from fpo_qry_deci where qry_type='E4' and cus_site = '" + cus_site +"' \r\n"
					+ "                        minus\r\n"
					+ "                        select cin_no from fpo_qry_deci where qry_type<>'E4' and cus_site = '" + cus_site +"')y  where  x.cin_no=y.cin_no and x.off_id='" + offId + "' and x.role='PAO' \r\n"
					+ "                         and k.user_id='" + offId + "' and instr(k.mail_class_cd,x.mail_class_cd) > 0)) b ,\r\n"
					+ "		                \r\n"
					+ "                        (select count(*) couc from fpo_curr_que a,  fpo_main b where curr_que in ('E1','E2','E3') and a.off_id='" + offId + "' and (b.role='PAO' or b.role is null) and a.cin_no=b.cin_no)c,\r\n"
					+ "				(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='" + cus_site +"' and b.off_id='" + offId + "' and b.role='PAO' and qry_type  in ('P1','P2','A3') and ( arr_scan='Y'))d,\r\n"
					+ "				\r\n"
					+ "				(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='" + cus_site +"' and b.off_id='" + offId + "' and b.role='PAO' and qry_type  in ('N1','N2','P2','A2','P6') and \r\n"
					+ "(arr_fpo is null))e";*/

			/*
			 * qry="select coua+coub ead,aaa,aaf from \r\n" +
			 * "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
			 * + "and a.cin_no=b.cin_no and (a.off_id='"+offId+"' or a.off_id is null) \r\n"
			 * + "and (a.role='PAO' or a.role is null) and b.off_id='"+offId+"') x,\r\n" +
			 * "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n" +
			 * "(select cin_no from fpo_main where cus_site='"
			 * +cus_site+"' and (role='PAO' or role is null) and (off_id='"
			 * +offId+"' or off_id is null)\r\n" + " minus\r\n" +
			 * "select cin_no from fpo_curr_que  where cus_site='"
			 * +cus_site+"' and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
			 * + "a.cin_no=b.cin_no and b.cus_site='"
			 * +cus_site+"' and instr(c.mail_class_cd,b.mail_class_cd) > 0 and \r\n" +
			 * "c.user_id=decode(b.off_id,null,'"+offId+"','"+offId+"'))) y,\r\n" +
			 * "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='"
			 * +cus_site+"' and qry_type \r\n" +
			 * " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id='"
			 * +offId+"' and b.role='PAO')c,\r\n" +
			 * "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='"
			 * +cus_site+"' and qry_type  \r\n" +
			 * "in ('N1','N2','P2','A2') and (arr_fpo is null) and b.off_id='"
			 * +offId+"' and b.role='PAO')d";
			 */
		} else if (sessionRole.equalsIgnoreCase("PAL") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite and (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n"
					+ "select cin_no from fpo_curr_que  where cus_site= :cuSite and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site= :cuSite  and instr(c.mail_class_cd,b.mail_class_cd) > 0 and c.role_name='PAO' and c.user_id=decode(b.off_id,null,:offId,:offId ))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y' or arr_scan is null) and b.off_id=:offId and b.role='PAO')d";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("PAA") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId  or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId ) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite  and (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where cus_site= :cuSite  and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site= :cuSite  and instr(c.mail_class_cd,b.mail_class_cd) > 0 and c.role_name='PAO' and c.user_id=decode(b.off_id,null,:offId ,:offId ))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId  and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId and b.role='PAO')d";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("PLA") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId  or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId ) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite and (role='PAO' or role is null) and (off_id=:offId  or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where cus_site= :cuSite  and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site= :cuSite  and instr(c.mail_class_cd,b.mail_class_cd) > 0 and c.role_name='PAO' and c.user_id=decode(b.off_id,null,:offId ,:offId ))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId  and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId  and b.role='PAO')d";
			off=1;
		} else if ((sessionRole.equalsIgnoreCase("PNA")  || sessionRole.equalsIgnoreCase("NRP") || (sessionRole.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) && sessionRole != null) {
            st=1;
			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId  or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId ) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where  role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and  instr(c.mail_class_cd,b.mail_class_cd) > 0 and c.role_name='PAO' and c.user_id=decode(b.off_id,null,:offId ,:offId ))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and  qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId  and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId  and b.role='PAO')d";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("PSU") && sessionRole != null) {
		//	qry = "select 0 ead,0 aaa, count(*) aaf from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and (  arr_scan='Y') and (b.off_id=:offId  or  b.off_id is null) and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and b.role='PSU' and x.role_name = 'PSU' ";
			qry = "select 0 ead,0 aaa, count(*) aaf from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite and qry_type='P3' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PSU'  ";
			off=1;
			//qry = "select 0 ead,0 aaa, count(*) aaf from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and ( arr_scan='Y') and (b.off_id='" + offId + "' or  b.off_id is null) and user_id='" + offId + "' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and b.role='PSU'";
		} else if (sessionRole.equalsIgnoreCase("PIN") && sessionRole != null) {
			qry = "select 0 aaa,0 ead ,count(*) aaf from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0  and x.role_name = 'PIN'";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("PBS") && sessionRole != null) {
			st=2;
			qry = "select 0 aaa, 0 ead, (recpcou+bagcou) aaf from \r\n"
			+"		(select count(*) recpcou from \r\n"
			+"		  (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from  \r\n"  
			+"		   (select count(*) tot_cou, recp_id  from article_arr_info b, ops$dir.fpo_sites c where status is null and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cuSite,1,5)  group by recp_id  ) y  left join  \r\n"
			+"		   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))=substr(:cuSite,1,5)   and substr(c.site_code,1,5)=substr(:cuSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join \r\n" 
			+"		   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n"
			+"		   (select nvl(count(*),0) withEadOtherSite,recp_id from  ops$dir.fpo_sites c, article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(substr(a.cus_site,1,5),'INBPS','INBOM','INFCH','INMAA',substr(a.cus_site,1,5))!=substr(c.map_code,1,5)  and substr(c.site_code,1,5)=substr(:cuSite,1,5)  and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
			+"		   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cuSite and decode(substr(ooe,1,5),'INBOM',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INBPS','AB','INBPS','AC','INBOM','BA','INBPS','BB','INBPS','BC','INBOM','CA','INBOM','CB','INBOM','CC','INBOM'),'INMAA',decode(substr(recp_id,12,1)||substr(recp_id,13,1),'AA','INMAA','AB','INMAA','AC','INFCH','BA','INMAA','BB','INMAA','BC','INFCH','CA','INMAA','CB','INMAA','CC','INFCH'),substr(ooe,1,5))=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cuSite,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join  \r\n" 
			+"		   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id    \r\n"
			+"		   where y.recp_id is not null and clr_site=:cuSite group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site) where bagNo not in  (select distinct(bag_no)  from fpo_scan_info))  x, \r\n"
					   
			+"		   (select count(*) bagcou  from  \r\n"
			+"		   (select with_ead_thissite+withEadOtherSite with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  0 withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from   \r\n"
			+"		  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cuSite,1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
			+"		  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
			+"		  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cuSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join \r\n"
			+"		  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
			+"		  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
			+"		  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cuSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no \r\n"
			+" 	      where x.bag_no is not null  \r\n"
			+"		  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo)  where bagNo not in (select distinct(bag_no)  from fpo_scan_info)) y";}
					  					
		else if (sessionRole.equalsIgnoreCase("COM") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId  or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId ) x,\r\n"
					+ "(select count(*) coub from (select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite  and (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n"
					+ "select cin_no from fpo_curr_que  where cus_site= :cuSite  and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site= :cuSite  and instr(c.mail_class_cd,b.mail_class_cd) > 0 and c.user_id=decode(b.off_id,null,:offId,:offId))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y')  and  c.role_name = 'PAO' and b.off_id=:offId and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId and b.role='PAO')d";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("ARP") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId  or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId ) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite and (role='PAO' or role is null) and (off_id=:offId  or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where cus_site= :cuSite  and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site= :cuSite and instr(c.mail_class_cd,b.mail_class_cd) > 0  and c.role_name = 'PAO' and c.user_id=decode(b.off_id,null,:offId,:offId))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId and b.role='PAO')d";
			off=1;
		} else if (sessionRole.equalsIgnoreCase("NAL") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site= :cuSite  and (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where cus_site= :cuSite and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site=' :cuSite  and instr(c.mail_class_cd,b.mail_class_cd) > 0  and c.role_name = 'PAO' and c.user_id=decode(b.off_id,null,:offId,:offId))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id=:offId and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site= :cuSite  and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_scan<>'Y'  or arr_scan is null) and b.off_id=:offId  and b.role='PAO')d";
			off=1;
		}/* else if (sessionRole.equalsIgnoreCase("PBS") && sessionRole != null) {

			qry = "select coua+coub ead,aaa,aaf from \r\n"
					+ "(select count(*) coua from fpo_main a,  fpo_curr_que b where curr_que in ('E1','E2','E3') \r\n"
					+ "and a.cin_no=b.cin_no and (a.off_id=:offId or a.off_id is null) \r\n"
					+ "and (a.role='PAO' or a.role is null) and b.off_id=:offId) x,\r\n"
					+ "(select count(*) coub from\r\n" + "(select a.cin_no from\r\n"
					+ "(select cin_no from fpo_main where cus_site='" + cus_site
					+ "' and (role='PAO' or role is null) and (off_id=:offId or off_id is null)\r\n"
					+ " minus\r\n" + "select cin_no from fpo_curr_que  where cus_site='" + cus_site
					+ "' and role='PAO') a,  fpo_main b, ops$dir.d_emp_roles c where\r\n"
					+ "a.cin_no=b.cin_no and b.cus_site='" + cus_site
					+ "' and instr(c.mail_class_cd,b.mail_class_cd) > 0 and \r\n" + "c.user_id=decode(b.off_id,null,'"
					+ offId + "',:offId))) y,\r\n"
					+ "(select count(*) aaf from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='"
					+ cus_site + "' and qry_type \r\n" + " in ('P1','P2','A3') and ( arr_scan='Y') and b.off_id='"
					+ offId + "' and b.role='PAO')c,\r\n"
					+ "(select count(*) aaa from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site='"
					+ cus_site + "' and qry_type  \r\n"
					+ "in ('N1','N2','P2','A2') and (arr_fpo is null) and b.off_id='" + offId
					+ "' and b.role='PAO')d";
		} */
		else {
			return articleCount;
		}
		Query query = entityManager.createNativeQuery(qry,TotalArticlesCount.class);
		if (off==1)
		  query.setParameter("offId", offId);
		if (st==0 || st==2)
			query.setParameter("cuSite", cuSite);
		articleCount = (TotalArticlesCount)  query.getResultList().get(0);	
		return articleCount;
	}

	public TotalCountMailClass getUserwiseMc(String cuSite, String offId, String sessionRole, String allotmailcat) {

		String qry = "";
		TotalCountMailClass totalCountMailClass = new TotalCountMailClass();

		if (sessionRole.equalsIgnoreCase("PAO") && sessionRole != null) {
			qry = "select ead+aaa1+aaa2+aaf1+aaf2+aaf3+aaf4+aaf5 totcoultr, ead1+aaa11+aaa21+aaf11+aaf21+aaf31+aaf41+aaf51 totcouems,\r\n"
					+ " ead2+aaa12+aaa22+aaf12+aaf22+aaf32+aaf42+aaf52 totcoupar,\r\n"
					+ " ead3+aaa13+aaa23+aaf13+aaf23+aaf33+aaf43+aaf53 totcouemp  from \r\n"
					+ "										(select count(*) ead from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "                                                                    and a.mail_class_cd='U'	and k.user_id=:offId and k.role_name='PAO'   and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a, \r\n"
					+ "																	(sELECT  count(*) aaa1\r\n"
					+ "								from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId\r\n"
					+ "								and q.cin_no=r.cin_no  and q.mail_class_cd='U'	and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))b, \r\n"
					+ "								(sELECT count(*) aaa2 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "								left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "								left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no    where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and\r\n"
					+ "								(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='U'	and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "								and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
					+ "								and s.role_name = 'PAO')c, \r\n"
					+ "					(sELECT count(*) aaf1 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "													            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j,  ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "													            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
					+ "													            and i.qry_no is null and (  arr_scan='Y' )  and q.mail_class_cd='U'	 and i.cin_no=j.cin_no and j.id in (select max(id) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "													            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) \r\n"
					+ "													            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO' )t,\r\n"
					+ "					(sELECT count(*) aaf2 \r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								        where i.cus_site=:cuSite   and q.mail_class_cd='U'	 and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3')\r\n"
					+ "								       and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "										and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u,\r\n"
					+ "					(sELECT count(*) aaf3 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "					            where i.cus_site=:cuSite  and q.mail_class_cd='U'	 and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3')\r\n"
					+ "					          and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "								and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null \r\n"
					+ "					           and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v,                       \r\n"
					+ "					(sELECT count(*) aaf4 from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  \r\n"
					+ "					 where  i.cus_site=:cuSite  and q.mail_class_cd='U'	 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "					 and  q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null ) x,\r\n"
					+ "					(sELECT count(*) aaf5 from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "					where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='U' and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y'  and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "					and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null) y ,\r\n"
					+ "  (select count(*) ead1 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "                                                                    and a.mail_class_cd='E'	and k.user_id=:offId and k.role_name='PAO'   and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a1, \r\n"
					+ "																	(sELECT  count(*) aaa11\r\n"
					+ "								from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId\r\n"
					+ "								and q.cin_no=r.cin_no  and q.mail_class_cd='E'	and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))b1,\r\n"
					+ "								(sELECT count(*) aaa21 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r\r\n"
					+ "								left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no    where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and\r\n"
					+ "								(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='E'	and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "								and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
					+ "								and s.role_name = 'PAO')c1, \r\n"
					+ "					(sELECT count(*) aaf11 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "													            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j,  ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "													            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
					+ "													            and i.qry_no is null and (  arr_scan='Y' )  and q.mail_class_cd='E'	 and i.cin_no=j.cin_no and j.id in (select max(id) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "													            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) \r\n"
					+ "													            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO' )t1,\r\n"
					+ "					(sELECT count(*) aaf21 \r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								        where i.cus_site=:cuSite   and q.mail_class_cd='E'	 and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3')\r\n"
					+ "								       and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "										and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u1,\r\n"
					+ "					(sELECT count(*) aaf31 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "					            where i.cus_site=:cuSite  and q.mail_class_cd='E'	 and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3')\r\n"
					+ "					          and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "								and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null \r\n"
					+ "					           and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v1,                       \r\n"
					+ "					(sELECT count(*) aaf41 from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  \r\n"
					+ "					 where  i.cus_site=:cuSite  and q.mail_class_cd='E'	 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "					 and  q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null ) x1,\r\n"
					+ "					(sELECT count(*) aaf51 from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "					where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='E' and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y'  and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "					and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null) y1 ,\r\n"
					+ "             (select count(*) ead2 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "                                                                    and a.mail_class_cd='C'	and k.user_id=:offId and k.role_name='PAO'   and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a2, \r\n"
					+ "																	(sELECT  count(*) aaa12\r\n"
					+ "								from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId\r\n"
					+ "								and q.cin_no=r.cin_no  and q.mail_class_cd='C'	and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))b2,\r\n"
					+ "								(sELECT count(*) aaa22 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r\r\n"
					+ "								left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no    where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and \r\n"
					+ "								(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='C'	and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "								and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
					+ "								and s.role_name = 'PAO')c2, \r\n"
					+ "					(sELECT count(*) aaf12 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "													            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j,  ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "													            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
					+ "													            and i.qry_no is null and (  arr_scan='Y' )  and q.mail_class_cd='C'	 and i.cin_no=j.cin_no and j.id in (select max(id) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "													            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) \r\n"
					+ "													            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO' )t2,\r\n"
					+ "					(sELECT count(*) aaf22 \r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								        where i.cus_site=:cuSite   and q.mail_class_cd='C'	 and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3')\r\n"
					+ "								       and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "										and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u2,\r\n"
					+ "					(sELECT count(*) aaf32 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "					            where i.cus_site=:cuSite  and q.mail_class_cd='C'	 and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3')\r\n"
					+ "					          and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "								and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null \r\n"
					+ "					           and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v2,                       \r\n"
					+ "					(sELECT count(*) aaf42 from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  \r\n"
					+ "					 where  i.cus_site=:cuSite  and q.mail_class_cd='C'	 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "					 and  q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null ) x2,\r\n"
					+ "					(sELECT count(*) aaf52 from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "					where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='C' and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y'  and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "					and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null) y2,\r\n"
					+ "            (select count(*) ead3 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "                                                                    and a.mail_class_cd='T'	and k.user_id=:offId and k.role_name='PAO'   and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a3, \r\n"
					+ "																	(sELECT  count(*) aaa13\r\n"
					+ "								from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ),  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId\r\n"
					+ "								and q.cin_no=r.cin_no  and q.mail_class_cd='T'	and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId and s.role_name = 'PAO' and q.mail_class_cd=d.code and r.item_no is null   and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))b3,\r\n"
					+ "								(sELECT count(*) aaa23 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r\r\n"
					+ "								left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								left join fpo_SPEEDDELISTATUS fd on (dc.SPEEDPOST_NO = fd.SPEEDPOST_NO and dc.SPEEDPOST_DELI_STATUS=fd.DELI_STATUS ) left join fpo_mvmnt m on r.cin_no = m.cin_no    where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and\r\n"
					+ "								(i.qry_type = 'A2' or i.qry_type='P1')  and q.mail_class_cd='T'	and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "								and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null)\r\n"
					+ "								and s.role_name = 'PAO')c3, \r\n"
					+ "					(sELECT count(*) aaf13 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "													            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j,  ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "													            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1')  and j.id=(select max(id) from fpo_mvmnt where item_id=q.item_id) \r\n"
					+ "													            and i.qry_no is null and (  arr_scan='Y' )  and q.mail_class_cd='T'	 and i.cin_no=j.cin_no and j.id in (select max(id) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "													            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)   or (stage='P1' and ext_dt is null) )) \r\n"
					+ "													            and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO' )t3,\r\n"
					+ "					(sELECT count(*) aaf23\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "								        where i.cus_site=:cuSite   and q.mail_class_cd='T'	 and i.CIN_NO = q.CIN_NO   and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='P1' or i.qry_type='A3')\r\n"
					+ "								       and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "										and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u3,\r\n"
					+ "					(sELECT count(*) aaf33 \r\n"
					+ "								from  fpo_main q , fpo_qry_deci i, fpo_mvmnt t, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)\r\n"
					+ "					            where i.cus_site=:cuSite  and q.mail_class_cd='T'	 and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='P2' or i.qry_type='A3')\r\n"
					+ "					          and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' ) \r\n"
					+ "								and q.cin_no=t.cin_no and t.id=(select max(id) from fpo_mvmnt where cin_no=q.cin_no) and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null \r\n"
					+ "					           and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v3,                       \r\n"
					+ "					(sELECT count(*) aaf43 from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no)  \r\n"
					+ "					 where  i.cus_site=:cuSite  and q.mail_class_cd='T'	 and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "					 and  q.cin_no=r.cin_no and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null ) x3,\r\n"
					+ "					(sELECT count(*) aaf53 from  fpo_main q , ops$dir.d_emp_roles s,  fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) \r\n"
					+ "					where  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.mail_class_cd='T' and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y'  and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "					and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null) y3" ;	
			 Query query = entityManager.createNativeQuery(qry,TotalCountMailClass.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			totalCountMailClass = (TotalCountMailClass) query.getResultList().get(0);
			if (allotmailcat.indexOf("U") < 0) {
				totalCountMailClass.setTotcoultr(0L);
			}
			if (allotmailcat.indexOf("E") < 0) {
				totalCountMailClass.setTotcouems(0L);
			}
			if (allotmailcat.indexOf("C") < 0) {
				totalCountMailClass.setTotcoupar(0L);
			}
			if (allotmailcat.indexOf("T") < 0) {
				totalCountMailClass.setTotcouemp(0L);
			}
		}else if(sessionRole.equalsIgnoreCase("PAC") && sessionRole != null) {
			qry="select (ead+aaa1+aaa2+aaf) totcoultr,(ead1+aaa11+aaa21+aaf1) totcouems,(ead2+aaa12+aaa22+aaf2) totcoupar,(ead3+aaa13+aaa23+aaf3) totcouemp from \r\n"
							   + "		   		(select count(*) ead from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no   where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
							   + "		   		and t2.cin_no is null and set_aside is null and mail_class_cd='U' ) a, \r\n"
							   + "		   		(sELECT count(*) aaa1\r\n"
							   + "		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
							   + "		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
							   + "		   					          and mail_class_cd='U' ) b,\r\n"
							   + "		   		(sELECT count(*) aaa2\r\n"
							   + "		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
							   + "		   								and q.cin_no=r.cin_no and qry_def_slno is null  and mail_class_cd='U' and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c,\r\n"
							   + "		   		(select count(*) aaf from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no\r\n"
							   + "		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
							   + "		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and mail_class_cd='U' and t1.acl_offid=:offId) d, \r\n"	
							   + "		   		(select count(*) ead1 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no   where t1.role='PAC' and (acL_offid is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
							   + "		   		and t2.cin_no is null and set_aside is null and mail_class_cd='E' ) a1, \r\n"
							   + "		   		(sELECT count(*) aaa11\r\n"
							   + "		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
							   + "		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
							   + "		   					         and mail_class_cd='E' ) b1,\r\n"
							   + "		   		(sELECT count(*) aaa21\r\n"
							   + "		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
							   + "		   								and q.cin_no=r.cin_no and qry_def_slno is null  and mail_class_cd='E' and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c1,\r\n"
							   + "		   		(select count(*) aaf1 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no\r\n"
							   + "		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
							   + "		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and mail_class_cd='E' and t1.acl_offid=:offId) d1, \r\n"
			                   + "		   		(select count(*) ead2 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no   where t1.role='PAC' and (acL_offid is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
			                   + "		   		and t2.cin_no is null and set_aside is null and mail_class_cd='C' ) a2, \r\n"
			                   + "		   		(sELECT count(*) aaa12\r\n"
			                   + "		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
			                   + "		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
			                   + "		   					          and mail_class_cd='C' ) b2,\r\n"
			                   + "		   		(sELECT count(*) aaa22\r\n"
			                   + "		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
			                   + "		   								and q.cin_no=r.cin_no and qry_def_slno is null  and mail_class_cd='C' and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c2,\r\n"
			                   + "		   		(select count(*) aaf2 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no\r\n"
			                   + "		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
			                   + "		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and mail_class_cd='C' and t1.acl_offid=:offId) d2, \r\n"
                               + "		   		(select count(*) ead3 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no   where t1.role='PAC' and (acL_offid is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
                               + "		   		and t2.cin_no is null and set_aside is null and mail_class_cd='T' ) a3, \r\n"
                               + "		   		(sELECT count(*) aaa13\r\n"
                               + "		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
                               + "		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite  and q.mail_class_cd=d.code and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
                               + "		   					         and mail_class_cd='T' ) b3,\r\n"
                               + "		   		(sELECT count(*) aaa23\r\n"
                               + "		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
                               + "		   								and q.cin_no=r.cin_no and qry_def_slno is null  and mail_class_cd='T' and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c3,\r\n"
                               + "		   		(select count(*) aaf3 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no\r\n"
                               + "		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
                               + "		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and mail_class_cd='T' and t1.acl_offid=:offId) d3 \r\n";

			//	totalCountMailClass = (TotalCountMailClass) entityManager.createNativeQuery(qry, TotalCountMailClass.class)
		//			.getResultList().get(0);
			 Query query = entityManager.createNativeQuery(qry,TotalCountMailClass.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			totalCountMailClass = (TotalCountMailClass) query.getResultList().get(0);	
		}else if(sessionRole.equalsIgnoreCase("PIN") && sessionRole != null) {
			qry="select aaf1 totcoultr,aaf2 totcouems,aaf3 totcoupar,aaf4 totcouemp from\r\n"
					+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='U'\r\n"
					+ "and qry_type='P4' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PIN')a, \r\n"
					+ "(select  count(*) aaf2 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='E'\r\n"
					+ "and qry_type='P4' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PIN')b, \r\n"
					+ "\r\n"
					+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='C'\r\n"
					+ "and qry_type='P4' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PIN')c, \r\n"
					+ "\r\n"
					+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='T'\r\n"
					+ "and qry_type='P4' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PIN')d \r\n";
			//			totalCountMailClass = (TotalCountMailClass) entityManager.createNativeQuery(qry, TotalCountMailClass.class)
//					.getResultList().get(0);
			 Query query = entityManager.createNativeQuery(qry,TotalCountMailClass.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			totalCountMailClass = (TotalCountMailClass) query.getResultList().get(0);
		}else if(sessionRole.equalsIgnoreCase("PSU") && sessionRole != null) { //and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU'
//			qry="select aaf1 totcoultr,aaf2 totcouems,aaf3 totcoupar,aaf4 totcouemp from\r\n"
//					+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and  arr_scan='Y' and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and mail_class_cd='U')a,\r\n"
//					+ "(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and  arr_scan='Y' and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and mail_class_cd='E')b,\r\n"
//					+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and  arr_scan='Y' and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and mail_class_cd='C')c,\r\n"
//					+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b where a.cin_no=b.cin_no and a.cus_site=:cuSite and qry_type='P3' and  arr_scan='Y' and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and mail_class_cd='T')d\r\n";
			
			qry="select aaf1 totcoultr,aaf2 totcouems,aaf3 totcoupar,aaf4 totcouemp from\r\n"
					+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='U'\r\n"
					+ "and qry_type='P3' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PSU')a, \r\n"
					+ "(select  count(*) aaf2 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='E'\r\n"
					+ "and qry_type='P3' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PSU')b, \r\n"
					+ "\r\n"
					+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='C'\r\n"
					+ "and qry_type='P3' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PSU')c, \r\n"
					+ "\r\n"
					+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b, ops$dir.d_emp_roles x where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site)=:cuSite\r\n"
					+ "and a.mail_class_cd='T'\r\n"
					+ "and qry_type='P3' and (  arr_scan='Y')  and user_id=:offId  and instr(x.mail_class_cd, a.mail_class_cd) > 0 and x.role_name='PSU')d \r\n";
					

		//	totalCountMailClass = (TotalCountMailClass) entityManager.createNativeQuery(qry, TotalCountMailClass.class)
		//			.getResultList().get(0);
			 Query query = entityManager.createNativeQuery(qry,TotalCountMailClass.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			totalCountMailClass = (TotalCountMailClass) query.getResultList().get(0);		}
		return totalCountMailClass;
	}

	public TotalIcCount getUsWsItemcategoryCount(String cuSite, String offId, String sessionRole) {
		String qry = "";
		TotalIcCount totalIcCount = new TotalIcCount();

		if (sessionRole.equalsIgnoreCase("PAO") && sessionRole != null) {
			qry = "select ead, aaa1+aaa2, aaf1+aaf2+aaf3+aaf4+aaf5,ead+aaa1+aaa2+aaf1+aaf2+aaf3+aaf4+aaf5 gift,  ead1, aaa11+aaa21, aaf11+aaf21+aaf31+aaf41+aaf51,ead1+aaa11+aaa21+aaf11+aaf21+aaf31+aaf41+aaf51 saleOfGoods,\r\n"
					+ " ead2, aaa12+aaa22, aaf12+aaf22+aaf32+aaf42+aaf52,ead2+aaa12+aaa22+aaf12+aaf22+aaf32+aaf42+aaf52 returnedGoods,\r\n"
					+ "  ead3, aaa13+aaa23, aaf13+aaf23+aaf33+aaf43+aaf53,ead3+aaa13+aaa23+aaf13+aaf23+aaf33+aaf43+aaf53 commercialSample,\r\n"
					+ "    ead4, aaa14+aaa24, aaf14+aaf24+aaf34+aaf44+aaf54,ead4+aaa14+aaa24+aaf14+aaf24+aaf34+aaf44+aaf54 documents,\r\n"
					+ "     ead5, aaa15+aaa25, aaf15+aaf25+aaf35+aaf45+aaf55,ead5+aaa15+aaa25+aaf15+aaf25+aaf35+aaf45+aaf55 others from"
					+ "(select count(*) ead from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='GIFT'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a, \r\n"
					+ "							 (sELECT count(*) aaa1\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='GIFT'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d, \r\n"
					+ "										(sELECT count(*) aaa2\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='GIFT'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b, \r\n"
					+ "							 (sELECT count(*) aaf1 \r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='GIFT'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u,\r\n"
					+ "							(sELECT count(*) aaf2\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code and t3.category='GIFT' and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='GIFT'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v,\r\n"
					+ "							 (sELECT count(*) aaf3 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='GIFT'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w,\r\n"
					+ "							(sELECT count(*) aaf4 	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='GIFT'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x,\r\n"
					+ "							(sELECT count(*) aaf5\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='GIFT'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y,\r\n"
					+ " (select count(*) ead1 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='SALE OF GOODS'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a1, \r\n"
					+ "							 (sELECT count(*) aaa11\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='SALE OF GOODS'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d1, \r\n"
					+ "										(sELECT count(*) aaa21\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='SALE OF GOODS'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b1, \r\n"
					+ "							 (sELECT count(*) aaf11 \r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='SALE OF GOODS'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u1,\r\n"
					+ "							(sELECT count(*) aaf21\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code   and t3.category='SALE OF GOODS'  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='SALE OF GOODS'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v1,\r\n"
					+ "							 (sELECT count(*) aaf31 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='SALE OF GOODS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w1,\r\n"
					+ "							(sELECT count(*) aaf41 	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='SALE OF GOODS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x1,\r\n"
					+ "							(sELECT count(*) aaf51\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='SALE OF GOODS'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y1,\r\n"
					+ "(select count(*) ead2 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='RETURNED GOODS'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a2, \r\n"
					+ "							 (sELECT count(*) aaa12\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='RETURNED GOODS'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d2, \r\n"
					+ "										(sELECT count(*) aaa22\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='RETURNED GOODS'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b2, \r\n"
					+ "							 (sELECT count(*) aaf12\r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='RETURNED GOODS'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u2,\r\n"
					+ "							(sELECT count(*) aaf22\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code   and t3.category='RETURNED GOODS'  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='RETURNED GOODS'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v2,\r\n"
					+ "							 (sELECT count(*) aaf32 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='RETURNED GOODS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w2,\r\n"
					+ "							(sELECT count(*) aaf42 	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='RETURNED GOODS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x2,\r\n"
					+ "							(sELECT count(*) aaf52\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='RETURNED GOODS'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y2,\r\n"
					+ "(select count(*) ead3 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='COMMERCIAL SAMPLE'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a2, \r\n"
					+ "							 (sELECT count(*) aaa13\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='COMMERCIAL SAMPLE'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d3, \r\n"
					+ "										(sELECT count(*) aaa23\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
						+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='COMMERCIAL SAMPLE'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b3, \r\n"
					+ "							 (sELECT count(*) aaf13\r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='COMMERCIAL SAMPLE'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u3,\r\n"
					+ "							(sELECT count(*) aaf23\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code   and t3.category='COMMERCIAL SAMPLE'  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='COMMERCIAL SAMPLE'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v3,\r\n"
					+ "							 (sELECT count(*) aaf33 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='COMMERCIAL SAMPLE'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w3,\r\n"
					+ "							(sELECT count(*) aaf43	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='COMMERCIAL SAMPLE'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x3,\r\n"
					+ "							(sELECT count(*) aaf53\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='COMMERCIAL SAMPLE'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y3,\r\n"
					+ "(select count(*) ead4 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='DOCUMENTS'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a4, \r\n"
					+ "							 (sELECT count(*) aaa14\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='DOCUMENTS'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d4, \r\n"
					+ "										(sELECT count(*) aaa24\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='DOCUMENTS'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b4, \r\n"
					+ "							 (sELECT count(*) aaf14\r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='DOCUMENTS'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u4,\r\n"
					+ "							(sELECT count(*) aaf24\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code   and t3.category='DOCUMENTS'  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='DOCUMENTS'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v4,\r\n"
					+ "							 (sELECT count(*) aaf34 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='DOCUMENTS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w4,\r\n"
					+ "							(sELECT count(*) aaf44	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='DOCUMENTS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x3,\r\n"
					+ "							(sELECT count(*) aaf54\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='DOCUMENTS'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y4,\r\n"
					+ "    (select count(*) ead5 from fpo_main a left join fpo_qry_deci b  on a.cin_no=b.cin_no, ops$dir.d_emp_roles k, ops$dir.pdi_nature_trans_codes l where a.cus_site= :cuSite and b.cin_no is null and ((a.off_id is null and a.role is null) or  (a.off_id=:offId and a.role='PAO') or ( a.off_id=:offId and a.role is null)) \r\n"
					+ "														and k.user_id=:offId and k.role_name='PAO'  and a.nature_type_cd=l.code and l.category='OTHERS'  and instr(k.mail_class_cd,a.mail_class_cd) > 0 ) a4, \r\n"
					+ "							 (sELECT count(*) aaa15\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes l, fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no) ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and decode(q.role,null,'PAO',q.role)='PAO' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.off_id=:offId \r\n"
					+ "										and q.cin_no=r.cin_no and r.cus_site= :cuSite and instr(s.mail_class_cd,q.mail_class_cd) > 0   and q.nature_type_cd=l.code and l.category='OTHERS'  and s.user_id=:offId and s.role_name = 'PAO'  and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id)) d5, \r\n"
					+ "										(sELECT count(*) aaa25\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s, fpo_curr_que c, fpo_addl_qry r \r\n"
					+ "										 left join fpo_mvmnt m on r.cin_no = m.cin_no  , \r\n"
					+ "										ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code  and \r\n"
					+ "										(i.qry_type = 'A2' or i.qry_type='P1') and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.off_id=:offId and m.id=(select max(id) from fpo_mvmnt where cin_no=r.cin_no)\r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='OTHERS'   and s.user_id=:offId  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and s.role_name = 'PAO'  and set_aside is null) b5, \r\n"
					+ "							 (sELECT count(*) aaf15\r\n"
					+ "												from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_query r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "										       ops$dir.pdi_nature_trans_codes t3 , ops$dir.pdi_mail_class_codes m where i.cus_site=:cuSite   and t3.category='OTHERS'  and q.mail_class_cd=m.code and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P2' or i.qry_type='A3') \r\n"
					+ "										      and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "												and q.cin_no=r.cin_no and item_no is null and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_typ!='U'  AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id))u5,\r\n"
					+ "							(sELECT count(*) aaf25\r\n"
					+ "										from  fpo_main q , fpo_qry_deci i, ops$dir.d_emp_roles s,  fpo_addl_qry r  left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no), \r\n"
					+ "							            ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO   and q.nature_type_cd=t3.code   and t3.category='OTHERS'  and (i.qry_type = 'A2' or i.qry_type='P1' or i.qry_type='A3') \r\n"
					+ "							           and decode(q.role,null,'PAO',q.role)='PAO' and i.qry_no is null and (q.arr_scan='Y' )  \r\n"
					+ "										and q.cin_no=r.cin_no and qry_def_slno is null and instr(s.mail_class_cd,q.mail_class_cd) > 0   and t3.category='OTHERS'  and s.user_id=:offId   and s.role_name = 'PAO' and r.qry_type!='U' AND dc.ASS_MVMNT IS NULL  and r.rply_date is null and set_aside is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=r.item_id))v5,\r\n"
					+ "							 (sELECT count(*) aaf35 from  fpo_main q , fpo_qry_deci i  left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) \r\n"
					+ "															            dc on (i.cin_no = dc.cin_no), fpo_mvmnt j, ops$dir.pdi_nature_trans_codes t3,\r\n"
					+ "															            ops$dir.que_disp k, ops$dir.d_emp_roles s where decode(j.stage,'O1',decode(q.clr_site,null,q.cus_site,q.clr_site),'P5',decode(q.clr_site,null,q.cus_site,q.clr_site),'P6',decode(q.clr_site,null,q.cus_site,q.clr_site),\r\n"
					+ "															            'R6',decode(q.clr_site,null,q.cus_site,q.clr_site),q.cus_site)=:cuSite  and i.CIN_NO = q.CIN_NO  and (i.qry_type = 'P1') \r\n"
					+ "															            and i.qry_no is null and (  arr_scan='Y' ) and i.cin_no=j.cin_no and j.slno in (select max(slno) from  fpo_mvmnt t where t.cin_no=i.cin_no and (ext_dt is not null or (stage='C1' and ext_dt is null) or \r\n"
					+ "															            (stage='R6' and ext_dt is null) or (stage='C2' and ext_dt is null) or (stage='O1' and ext_dt is null) or (stage='P6' and ext_dt is null)  )) and k.role=j.role and q.nature_type_cd=t3.code   \r\n"
					+ "															              and t3.category='OTHERS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0 and s.user_id=:offId and s.role_name='PAO')w5,\r\n"
					+ "							(sELECT count(*) aaf45	from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_query r left join (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),   ops$dir.pdi_nature_trans_codes t3   , (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and i.cus_site=:cuSite and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7') and i.qry_no is null and ( arr_scan='Y') \r\n"
					+ "										and q.cin_no=r.cin_no   and t3.category='OTHERS'   and instr(s.mail_class_cd,q.mail_class_cd) > 0  and s.user_id=:offId  and s.role_name = 'PAO' and r.cus_site= :cuSite and r.item_no is null and r.rply_date is not null )x5,\r\n"
					+ "							(sELECT count(*) aaf55\r\n"
					+ "										from  fpo_main q , ops$dir.d_emp_roles s, fpo_qry_deci i, fpo_addl_qry r left join  (select * from dcallqry_gen where id in (select max(id) from dcallqry_gen group by cin_no)) dc on (r.cin_no = dc.cin_no),  ops$dir.pdi_nature_trans_codes t3 , ops$dir.que_disp k, (select count(*) cou,cin_no from  fpo_item_det group by cin_no) m where  m.cin_no=q.cin_no and  i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO and q.nature_type_cd=t3.code and (i.qry_type = 'A3' or i.qry_type='A2' ) and i.qry_no is null and  arr_scan='Y' \r\n"
					+ "										and q.cin_no=r.cin_no and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null) \r\n"
					+ "										and instr(s.mail_class_cd,q.mail_class_cd) > 0    and t3.category='OTHERS'   and s.user_id=:offId   and s.role_name = 'PAO' and (i.role='PAO' or i.role='IMP') and q.off_id=:offId and qry_def_slno is null and r.rply_date is not null)y5";
			 Query query = entityManager.createNativeQuery(qry,TotalIcCount.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			 totalIcCount = (TotalIcCount) query.getResultList().get(0);			
			 return totalIcCount;		
		//	return (TotalIcCount) entityManager.createNativeQuery(qry, TotalIcCount.class).getResultList().get(0);
		}else if (sessionRole.equalsIgnoreCase("PAC") && sessionRole != null) {
			qry="select (ead+aaa1+aaa2+aaf) gift,(ead1+aaa11+aaa21+aaf1) saleOfGoods,(ead2+aaa12+aaa22+aaf2) returnedGoods,(ead3+aaa13+aaa23+aaf3) commercialSample,(ead4+aaa14+aaa24+aaf4) documents,\r\n"
					+ "					(ead5+aaa15+aaa25+aaf5) others from 				\r\n"
					+ "                                    (select count(*) ead from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='GIFT') a, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa1\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					          and q.nature_type_cd=n.code and n.category='GIFT'  ) b,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa2\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='GIFT'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c,\r\n"
					+ "                   						  		   		(select count(*) aaf from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='GIFT'  and t1.acl_offid=:offId) d, \r\n"
					+ "                                    (select count(*) ead1 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='SALE OF GOODS') a1, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa11\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					           and q.nature_type_cd=n.code and n.category='SALE OF GOODS'  ) b1,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa21\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='SALE OF GOODS'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c1,\r\n"
					+ "                   						  		   		(select count(*) aaf1 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='SALE OF GOODS'  and t1.acl_offid=:offId) d1, \r\n"
					+ "			                        (select count(*) ead2 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='RETURNED GOODS') a2, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa12\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					           and q.nature_type_cd=n.code and n.category='RETURNED GOODS'  ) b2,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa22\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='RETURNED GOODS'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c2,\r\n"
					+ "                   						  		   		(select count(*) aaf2 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='RETURNED GOODS'  and t1.acl_offid=:offId) d2, \r\n"
					+ "    		                        (select count(*) ead3 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='COMMERCIAL SAMPLE') a3, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa13\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					           and q.nature_type_cd=n.code and n.category='COMMERCIAL SAMPLE'  ) b3,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa23\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r  ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='COMMERCIAL SAMPLE'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c3,\r\n"
					+ "                   						  		   		(select count(*) aaf3 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='COMMERCIAL SAMPLE'  and t1.acl_offid=:offId) d3, \r\n"
					+ "			                        (select count(*) ead4 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='DOCUMENTS') a4, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa14\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					           and q.nature_type_cd=n.code and n.category='DOCUMENTS'  ) b4,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa24\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='DOCUMENTS'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c4,\r\n"
					+ "                   						  		   		(select count(*) aaf4 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='DOCUMENTS'  and t1.acl_offid=:offId) d4, \r\n"
					+ "			                        (select count(*) ead5 from  fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n   \r\n"
					+ "                                    where t1.role='PAC' and (acL_offId is null or acl_offid=:offId) and t1.cus_site=:cuSite \r\n"
					+ "                   						  		   		and t2.cin_no is null and set_aside is null and t1.nature_type_cd=n.code and category='OTHERS') a5, \r\n"
					+ "                   						  		   		(sELECT count(*) aaa15\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i,fpo_curr_que c, ops$dir.pdi_nature_trans_codes n, fpo_query r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and q.role='PAC' and q.nature_type_cd=t3.code and i.CIN_NO = q.CIN_NO and (i.qry_type = 'P2' or i.qry_type='P7' or i.qry_type='P1') and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)   and c.cin_no=q.cin_no and q.acl_offid=:offId \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and r.cus_site= :cuSite   and r.item_no is null and set_aside is null  and r.qry_no=(select max(qry_no) from fpo_query where item_id=r.item_id) \r\n"
					+ "                   						  		   					           and q.nature_type_cd=n.code and n.category='OTHERS'  ) b5,\r\n"
					+ "                   						  		   		(sELECT count(*) aaa25\r\n"
					+ "                   						  		   								from  fpo_main q , fpo_qry_deci i, fpo_curr_que c, ops$dir.pdi_mail_class_codes d, ops$dir.pdi_nature_trans_codes n, fpo_addl_qry r   ,  ops$dir.pdi_nature_trans_codes t3  where i.cus_site=:cuSite and i.CIN_NO = q.CIN_NO  and q.nature_type_cd=t3.code and (i.qry_type = 'A2' or i.qry_type='P1') and q.role='PAC' and i.qry_no is null and (q.arr_scan<>'Y' or q.arr_scan is null)  and c.cin_no=q.cin_no and q.acl_offid=:offId  \r\n"
					+ "                   						  		   								and q.cin_no=r.cin_no and qry_def_slno is null  and q.nature_type_cd=n.code and n.category='OTHERS'  and q.mail_class_cd=d.code and set_aside is null and r.qry_type <>'D'  and r.qry_id=(select max(qry_id) from fpo_addl_qry where item_id=q.item_id and qry_def_slno is null))c5,\r\n"
					+ "                   						  		   		(select count(*) aaf5 from fpo_main t1 left join fpo_qry_deci t2 on t2.cin_no=t1.cin_no, ops$dir.pdi_nature_trans_codes n\r\n"
					+ "                   						  		   							where  t1.cus_site=:cuSite and t1.role='PAC' and (t1.acl_offid=:offId ) and arr_scan='Y' \r\n"
					+ "                   						  		   					            and (t2.qry_type='P1' or t2.qry_type='P2' or t2.qry_type='A3') and t1.role='PAC'  and t1.nature_type_cd=n.code and n.category='OTHERS'  and t1.acl_offid=:offId) d5";
				 Query query = entityManager.createNativeQuery(qry,TotalIcCount.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			 totalIcCount = (TotalIcCount) query.getResultList().get(0);			
			 return totalIcCount;	
		//	return (TotalIcCount) entityManager.createNativeQuery(qry, TotalIcCount.class).getResultList().get(0);
		}else if (sessionRole.equalsIgnoreCase("PIN") && sessionRole != null) {
				qry="select aaf1 gift, aaf2 saleOfGoods, aaf3 returnedGoods, aaf4 commercialSample, aaf5 documents, aaf6 others from\r\n"
						
						+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x   where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0  and nature_type_cd=c.code and category='GIFT')a,\r\n"
						+ "(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x   where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='SALE OF GOODS')b,\r\n"
						+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x   where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0  and nature_type_cd=c.code and category='RETURNED GOODS')c,\r\n"
						+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x   where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='COMMERCIAL SAMPLE')d,\r\n"
						+ "(select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c , ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='DOCUMENTS')e,\r\n"
						+ "(select count(*) aaf6 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x   where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite   and qry_type='P4' and ( arr_scan='Y')  and user_id=:offId  and x.role_name = 'PIN' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='OTHERS')f\r\n"
						+ "";
				 Query query = entityManager.createNativeQuery(qry,TotalIcCount.class);
				 query.setParameter("cuSite",cuSite);
				 query.setParameter("offId", offId);
				 totalIcCount = (TotalIcCount) query.getResultList().get(0);			
				 return totalIcCount;	
			//	return (TotalIcCount) entityManager.createNativeQuery(qry, TotalIcCount.class).getResultList().get(0);
		}else if (sessionRole.equalsIgnoreCase("PSU") && sessionRole != null) {
//			qry="select aaf1 gift, aaf2 saleOfGoods, aaf3 returnedGoods, aaf4 commercialSample, aaf5 documents, aaf6 others from\r\n"
//					+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and nature_type_cd=c.code and category='GIFT')a,\r\n"
//					+ "(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and nature_type_cd=c.code and category='SALE OF GOODS')b,\r\n"
//					+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU' and nature_type_cd=c.code and category='RETURNED GOODS')c,\r\n"
//					+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU'and nature_type_cd=c.code and category='COMMERCIAL SAMPLE')d,\r\n"
//					+ "(select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU'and nature_type_cd=c.code and category='DOCUMENTS')e,\r\n"
//					+ "(select count(*) aaf6 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c  where a.cin_no=b.cin_no and a.cus_site=:cuSite  and qry_type='P3' and ( arr_scan='Y') and (b.off_id=:offId or  b.off_id is null) and b.role='PSU'and nature_type_cd=c.code and category='OTHERS')f\r\n";
			
			qry="select aaf1 gift, aaf2 saleOfGoods, aaf3 returnedGoods, aaf4 commercialSample, aaf5 documents, aaf6 others from\r\n"
					+ "(select count(*) aaf1 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='GIFT')a,\r\n"
					+ "(select count(*) aaf2 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0  and nature_type_cd=c.code and category='SALE OF GOODS')b,\r\n"
					+ "(select count(*) aaf3 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='RETURNED GOODS')c,\r\n"
					+ "(select count(*) aaf4 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0  and nature_type_cd=c.code and category='COMMERCIAL SAMPLE')d,\r\n"
					+ "(select count(*) aaf5 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0 and nature_type_cd=c.code and category='DOCUMENTS')e,\r\n"
					+ "(select count(*) aaf6 from fpo_main a,  fpo_qry_deci b,ops$dir.pdi_nature_trans_codes c, ops$dir.d_emp_roles x  where a.cin_no=b.cin_no and decode(a.clr_site,null,a.cus_site,a.clr_site) =:cuSite  and qry_type='P3' and ( arr_scan='Y') and user_id=:offId  and x.role_name = 'PSU' and instr(x.mail_class_cd, a.mail_class_cd) > 0  and nature_type_cd=c.code and category='OTHERS')f";
			 Query query = entityManager.createNativeQuery(qry,TotalIcCount.class);
			 query.setParameter("cuSite",cuSite);
			 query.setParameter("offId", offId);
			 totalIcCount = (TotalIcCount) query.getResultList().get(0);			
			 return totalIcCount;	
	//		return (TotalIcCount) entityManager.createNativeQuery(qry, TotalIcCount.class).getResultList().get(0);
	}
		return totalIcCount;
	}
	
	public AlertBean getAlerts(String sessionRole) {
		String qry = "";
		if(sessionRole.equalsIgnoreCase("PAL")&&sessionRole!=null) {
			qry = "select generic,specific from\r\n"
					+ "(select count(*) generic from fpo_ALERT where status='Active' and alert_type='Generic'),\r\n"
					+ "(select count(*) specific from fpo_ALERT where status='Active' and alert_type='Specific')";
		}else if(sessionRole.equalsIgnoreCase("NAL")&&sessionRole!=null) {
			qry = "select generic,specific from\r\n"
					+ "(select count(*) generic from fpo_ALERT where status='Active' and alert_type='Generic' and alert_level='All India'),\r\n"
					+ "(select count(*) specific from fpo_ALERT where status='Active' and alert_type='Specific' and alert_level='All India')";
		}
		return (AlertBean)  entityManager.createNativeQuery(qry,AlertBean.class).getResultList().get(0);
	}
	public List<CtryWsAssCountPercentage> getAllCountryWiseAssValPercentage(String cuSite,String role) {
		String qry = "";
		List<CtryWsAssCountPercentage> ctryWsAssPercentage=new ArrayList<CtryWsAssCountPercentage>();
		qry="select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,1) volumePercentage,length(send_cntry_cd) len,round(tot_articles,0) totArt,round(tot_articles_top10,0) noOfValue, tot_art_count totalArtCount ,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
				+ "(select send_cntry_cd, tot_articles_top10,tot_articles,tot_art_count from\r\n"
				+ "(select send_cntry_cd, tot_articles tot_articles_top10, tot_art_count from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles,count(*) tot_art_count from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite \r\n"
				+ "group by send_cntry_cd order by 2 desc )) a, \r\n"
				+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite ) b,\r\n"
				+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
				+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
				+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8)) and cus_site=:cuSite \r\n"
				+ "group by send_cntry_cd order by 2 desc )) c)\r\n"
				+ "order by 5 desc";
		 Query query = entityManager.createNativeQuery(qry,CtryWsAssCountPercentage.class);
		 query.setParameter("cuSite",cuSite);
		 ctryWsAssPercentage = (List<CtryWsAssCountPercentage>) query.getResultList();			
		//ctryWsAssPercentage= (List<CtryWsAssCountPercentage>)entityManager.createNativeQuery(qry,CtryWsAssCountPercentage.class).getResultList();
		entityManager.clear();
		if ((role.equalsIgnoreCase("PNA")  || role.equalsIgnoreCase("NRP") || (role.equalsIgnoreCase("ARP") &&  cuSite.equalsIgnoreCase("INNSA5"))) &&   role!=null) {
			qry="select send_cntry_cd country, round(tot_articles_top10/tot_articles*100,1) volumePercentage,length(send_cntry_cd) len,round(tot_articles,0) totArt,round(tot_articles_top10,0) noOfValue, tot_art_count totalArtCount ,to_char(ADD_MONTHS(SYSDATE,-8),'MON-YYYY') lastMonth from\r\n"
					+ "(select send_cntry_cd, tot_articles_top10,tot_articles,tot_art_count from\r\n"
					+ "(select send_cntry_cd, tot_articles tot_articles_top10, tot_art_count from\r\n"
					+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles,count(*) tot_art_count from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8))\r\n"
					+ "group by send_cntry_cd order by 2 desc )) a, \r\n"
					+ "(select nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8))) b,\r\n"
					+ "(select  sum(tot_articles) tot_articles_top10_sum from\r\n"
					+ "(select send_cntry_cd,nvl(SUM(TOT_ASS_VAL),0) tot_articles from fpo_main where\r\n"
					+ "trunc(to_date(substr(postingdt,9,2)||'/'||substr(postingdt,6,2)||'/'||substr(postingdt,0,4),'DD/MM/YYYY'))\r\n"
					+ "between TRUNC(ADD_MONTHS(SYSDATE, -8),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-8))\r\n"
					+ "group by send_cntry_cd order by 2 desc )) c)\r\n"
					+ "order by 5 desc";
			 ctryWsAssPercentage= (List<CtryWsAssCountPercentage>)entityManager.createNativeQuery(qry,CtryWsAssCountPercentage.class).getResultList();
		}
		return  ctryWsAssPercentage;
		
	}
	
	public OOCDuty getOOCTotalDutyDetails() {
		String qry = "select sum(tot_duty) totDuty ,sum(tot_duty_fg) totDutyFg ,sum(tot_amt_payable) totAmtPayable, sum(tot_fine) +sum(tot_penal) finePenal\r\n"
				+ "from fpo_main a,  fpo_curr_que b\r\n"
				+ "where a.cin_no=b.cin_no and b.curr_que<>'P8' and trunc(to_date(substr(a.postingdt,9,2)||'/'||substr(a.postingdt,6,2)||'/'||substr(a.postingdt,0,4),'DD/MM/YYYY'))\r\n"
				+ "between TRUNC(ADD_MONTHS(SYSDATE, -1),'MM')  and LAST_DAY(ADD_MONTHS(SYSDATE,-1))";
		
		return (OOCDuty)  entityManager.createNativeQuery(qry,OOCDuty.class).getResultList().get(0);
	}
	
	public BigDecimal getTotalActiveUsersCount(String cuSite,String offId) {
		String qry = "select count(DISTINCT user_id) from ops$dir.d_emp_roles where end_dt is null and role_name not in ('PNA', 'PLA') and cus_site = :cuSite";
		 Query query = entityManager.createNativeQuery(qry);
		 query.setParameter("cuSite",cuSite);
		 return (BigDecimal) query.getResultList().get(0);		
	//	return (BigDecimal) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
	public List<ActiveuserLSM> getActiveUserLSM(String cuSite,String offId) {
		String qry = "select role_name role,count(*) counts from ops$dir.d_emp_roles where end_dt is null  and role_name not in ('PLA','PNA') and cus_site = :cuSite group by role_name";
		Query query = entityManager.createNativeQuery(qry);
		query.setParameter("cuSite",cuSite);
		 return (List<ActiveuserLSM>) query.getResultList().get(0);	
	//	return entityManager.createNativeQuery(qry,ActiveuserLSM.class).getResultList();
	}
	
	public BigDecimal getTotalActiveUsersNoRoleCount(String cuSite,String offId) {
		String qry = "select  count(a.user_id) as total_users  from ops$dir.d_emp a where user_id  not in ( select distinct(user_id) from ops$dir.d_emp_roles b where cus_site=:cuSite and end_dt is null) and fpo_cus_site=:cuSite";
			//	+ "select  count(*)  from ops$dir.d_emp a where user_id not in ( select user_id from ops$dir.d_emp_roles k where end_dt is null and cus_site=:cuSite ) \r\n"
			//	+ "and fpo_cus_site=:cuSite group by a.user_id";
		Query query = entityManager.createNativeQuery(qry);
		query.setParameter("cuSite",cuSite);
		 return (BigDecimal) query.getResultList().get(0);	 
	//	return (BigDecimal) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
	
	
	public BigDecimal getTotalActiveUsersAccrossIndia(String cuSite) {
		String qry = "select count(distinct USER_ID) counts from ops$dir.d_emp_roles where end_dt is null";
		return (BigDecimal) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
	
	public List<ActiveuserLSM> getAdminUsersCount(String cuSite) {
		String qry = "select role_name role ,count(*) counts from ops$dir.d_emp_roles where end_dt is null and role_name in('PNA','NRP','ARP','PLA') group by role_name";
		return entityManager.createNativeQuery(qry,ActiveuserLSM.class).getResultList();
	}
	
	public BigDecimal getTotalActiveFpoSites() {
		String qry = "select count(*) counts from ops$dir.fpo_sites where site_active ='Y' and site_code not in('INNSA5')";
		return (BigDecimal) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
	
	public Object getFpoSiteAllotDetails(String cuSite) {
		String qry = "select * from ops$dir.FPO_SITE_ALLOT where site_code=:cuSite";
		Query query = entityManager.createNativeQuery(qry);
		query.setParameter("cuSite",cuSite);
		return (Object) query.getResultList().get(0);	 
	//	return (Object) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
	
	public BigDecimal getTotalCountForWP(String cuSite, String csdisp) {
		String qry = "select count(*) from (select a.article_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'),decode(b.item_id,null,'without_ead','with_ead') eadstatus,decode(c.cin_no,null,decode(b.cin_no,null,'-',b.cin_no),c.cin_no) cinno,\r\n"
				+ "decode(b.cin_dt,null,decode(c.cin_dt,null,'-',to_char(c.cin_dt,'DD/MM/YYYY')),to_char(b.cin_dt,'DD/MM/YYYY')) cindt, dir.codedesc,b.send_cntry_cd, decode(c.ooe,null,'-',concat(SUBSTR(c.ooe, 1,5),5)) ooe,to_char(recd_event_dt,'DD/MM/YYYY'), :csdisp,\r\n"
				+ "to_char(recd_dt,'DD/MM/YYYY'), f.category from articlearr_fpo_info a left join fpo_main b on a.article_id=b.item_id left join article_arr_info c on a.article_id=c.article_id \r\n"
				+ "left join ops$dir.pdi_mail_class_codes dir \r\n"
				+ "on (b.mail_class_cd=dir.code) left join ops$dir.pdi_nature_trans_codes f on (b.nature_type_cd=f.code) where b.cus_site is null  and a.status is null and c.status is null and concat(SUBSTR(a.recd_fpo, 1,5),5) = :cuSite and a.article_id = b.item_id)";
		Query query = entityManager.createNativeQuery(qry);
		query.setParameter("cuSite",cuSite);
		query.setParameter("csdisp",csdisp);
		return (BigDecimal) query.getResultList().get(0);	 
	//	return (BigDecimal) entityManager.createNativeQuery(qry).getResultList().get(0);
	}
}
