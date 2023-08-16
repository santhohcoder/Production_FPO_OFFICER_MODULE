package com.demo.fpo.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.D_EMP;

public interface D_EMPrepo extends JpaRepository<D_EMP, Long> {
	
//	@Query(nativeQuery = true, value = "select a.user_id,a.cus_site,a.role_name,b.EMP_NAME from ops$dir.d_emp_roles a, ops$dir.d_emp_fpo b where a.user_id=b.user_id and a.user_id=?1 and passwd = ?2 and end_dt is null")
//	List<Collection> getempName(String id,String pwd);
	
	@Query(nativeQuery = true, value = "select a.user_id,a.cus_site,a.role_name,b.EMP_NAME from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id and end_dt is null and USER_ACCOUNT_CONTROL='A'")
	List<Collection> getempName(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null and cus_site=(select fpo_cus_site from ops$dir.d_emp where end_date is null and user_id=:offid)")
	List<String> getmulrole(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select fpo_cus_site from ops$dir.d_emp where user_id=:id and end_date is null and USER_ACCOUNT_CONTROL='A'")
	String getempsite(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select site_name from ops$dir.fpo_sites where site_code=:id")
	String getsitenm(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select emp_name from ops$dir.d_emp where user_id=:id and fpo_cus_site=:cussite and end_date is null and USER_ACCOUNT_CONTROL='A'")
	String getempname(@Param("id")String id, @Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select count(*) from ops$dir.fpo_sites where site_code=:site")
	int validsite(@Param("site") String site);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id and end_date is null and USER_ACCOUNT_CONTROL='A'")
	List<D_EMP> getrole(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid) where disp_group = 1 and b.end_dt is null and b.cus_site=:cusite  and b.user_id=:offid order by dispord")
	List<Collection> getallroles_a(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite  and b.user_id=:offid) where disp_group = 2 and b.end_dt is null and b.cus_site=:cusite  and b.user_id=:offid order by dispord") 
	List<Collection> getallroles_b(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite  and b.user_id=:offid) where disp_group = 3 and b.end_dt is null and b.cus_site=:cusite  and b.user_id=:offid order by dispord")
	List<Collection> getallroles_c(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select distinct disp_group from ops$dir.fpo_roles a  join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid and b.end_dt is null)")
	List<String> getroletype(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select b.CIN_NO,b.ITEM_ID,to_char(b.PRIORITY_DT, 'DD/MM/YYYY HH24:MI:SS') priority_dt,b.OFF_ID, b.ROLE,a.EMP_NAME,b.REASON from ops$dir.D_EMP a INNER JOIN FPO_PRIORITY b 	ON b.OFF_ID =a.USER_ID")
	List<Collection> getPriorityHist();
	
	/*@Query(nativeQuery = true, value = "select BAG_NO, BAG_TYPE, recd_dt,scan_dt,totcou  ,totead,totnoead,totnotalot, withoutead   from \r\n"
			+ "(select a.BAG_NO, BAG_TYPE, recd_event_dt recd_dt,scan_dt,  totcou,totead,totnoead,totnotalot, (decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))  withoutead\r\n"
			+ "from FPO_SCAN_INFO a left join (select recd_event_dt, article_id from article_arr_info ) b on a.article_id=b.article_id  left join (select count(*) totcou, RECP_ID  from  article_arr_info group by recp_id) c on a.BAG_NO = c.recp_id \r\n"
			+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R' group by BAG_NO,article_id) d on d.BAG_NO=a.BAG_NO left join \r\n"
			+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!=:cusSite and bag_type='R' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
			+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='R' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
			+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R'\r\n"
			+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_event_dt,scan_dt, totcou,totead,totnoead,totnotalot) \r\n"
			+ "union\r\n"
			+ "(select a.BAG_NO, BAG_TYPE, recd_dt,scan_dt, totcou ,totead,totnoead,totnotalot, (decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))  withoutead\r\n"
			+ "from FPO_SCAN_INFO a left join (select recd_dt, article_id from articlearr_fpo_info) b on a.article_id=b.article_id  left join (select count(*) totcou ,bag_no from  articlearr_fpo_info group by bag_no) c on a.BAG_NO=c.bag_no \r\n"
			+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B' group by BAG_NO,article_id) d  on d.BAG_NO=a.bag_no left join \r\n"
			+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!= :cusSite and bag_type='B' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
			+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='B' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
			+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B'\r\n"
			+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_dt,scan_dt,totcou,totead,totnoead,totnotalot) \r\n"
			+ "order by scan_dt desc")
	List<Collection> getConformHistB( @Param("cusSite") String cusSite);*/
	
	@Query(nativeQuery = true, value = "select BAG_NO, BAG_TYPE, recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt,nvl(totcou,0)  ,nvl(totead,0),nvl(totnoead,0),nvl(totnotalot,0),nvl(withoutead,0)   from \r\n"
			+ "(select a.BAG_NO, BAG_TYPE, recd_event_dt recd_dt,scan_dt,  totcou,totead,totnoead,totnotalot,(decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))withoutead\r\n"
			+ "from FPO_SCAN_INFO a left join (select recd_event_dt, article_id from article_arr_info ) b on a.article_id=b.article_id  left join (select count(*) totcou, RECP_ID  from  article_arr_info group by recp_id) c on a.BAG_NO = c.recp_id \r\n"
			+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R' group by BAG_NO) d on d.BAG_NO=a.BAG_NO left join \r\n"
			+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!=:cusSite and bag_type='R' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
			+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='R' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
			+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R'and scan_dt BETWEEN :fromDate AND :toDate   \r\n"
			+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_event_dt,scan_dt, totcou,totead,totnoead,totnotalot) \r\n"
			+ "union\r\n"
			+ "(select a.BAG_NO, BAG_TYPE, recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt, totcou ,totead,totnoead,totnotalot, (decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))  withoutead\r\n"
			+ "from FPO_SCAN_INFO a left join (select recd_dt, article_id from articlearr_fpo_info) b on a.article_id=b.article_id  left join (select count(*) totcou ,bag_no from  articlearr_fpo_info group by bag_no) c on a.BAG_NO=c.bag_no \r\n"
			+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B' group by BAG_NO) d  on d.BAG_NO=a.bag_no left join \r\n"
			+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!=:cusSite and bag_type='B' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
			+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='B' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
			+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B' and scan_dt BETWEEN :fromDate AND :toDate  \r\n"
			+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_dt,scan_dt,totcou,totead,totnoead,totnotalot) \r\n"
			+ "order by scan_dt desc")
	List<Collection> getConformHistB( @Param("cusSite") String cusSite,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	@Query(nativeQuery = true, value = "select distinct(scan_report) from fpo_scan_info where scanned='O' and bag_no=:bagNo") 
	String getReportStatus(@Param("bagNo") String bagNo);


	@Query(nativeQuery = true, value = "select ROLE,ROLE_NM, DISP_ROLENM from ops$dir.fpo_roles where disp_group = 1 order by DISPORD")
	List<String> getViewRolea();
	
	
	@Query(nativeQuery = true, value = "select ROLE,ROLE_NM, DISP_ROLENM from ops$dir.fpo_roles where disp_group = 2 order by DISPORD")
	List<String> getViewRoleb();
	
	
	@Query(nativeQuery = true, value = "select ROLE,ROLE_NM, DISP_ROLENM from ops$dir.fpo_roles where disp_group = 3 order by DISPORD")
	List<String> getViewRolec();	
	
	
	
/*@Query(nativeQuery = true, value ="select article_id articleId,case when (select count(*)  from fpo_main where item_id=article_id)>0 then 'YES' else 'NO' end eadExist,\r\n"
			+ "					(select decode(cus_site,null,'-',cus_site) mapped from fpo_main where item_id=article_id) mapped,\r\n"
			+ "					(select decode(clr_site,null,'-',clr_site) clrsite from fpo_main where item_id=article_id) clrsite,					\r\n"
			+ "                    cus_site  destinationFpo,case when (select count(*)  from fpo_order where item_id=article_id)>0 then 'YES' else 'NO' end ExamOrderExist,fsi.scan_report scanReport,\r\n"
			+ "                    c1.cntry_nm originCountry ,c3.cntry_nm originCountry1 \r\n"
			+ "					from fpo_scan_info fsi  left join ops$dir.d_cntry_cd  c1 on substr(article_id,12,2)=c1.cntry_cd\r\n"
			+ "                    left join (select cntry_nm,item_id from fpo_main left join ops$dir.d_cntry_cd c2 on send_cntry_cd=c2.cntry_cd)c3 on item_id=article_id\r\n"
			+ "                    where Scanned='M' AND   bag_no=:bagNo order by scan_dt desc")
List<Collection> getListOfArtclHistry(@Param("bagNo")String bagNo);*/

@Query(nativeQuery = true, value ="select a.article_id  articleId, case when (select count(*)  from fpo_main where item_id=a.article_id)>0 then 'YES' else 'NO' end eadExist, mapped,clrsite, a.cus_site  destinationFpo,\r\n"
		+ "case when (select count(*)  from fpo_order where item_id=a.article_id)>0 then 'YES' else 'NO' end ExamOrderExist,scan_report scanReport, c1.cntry_nm originCountry ,c3.cntry_nm originCountry1	\r\n"
		+ "                   from                           \r\n"
		+ "(select article_id,scan_dt,cus_site  from fpo_scan_info where bag_no=:bagNo group by article_id,scan_dt,cus_site) a left join\r\n"
		+ "( select article_id, scan_report from fpo_scan_info where scanned='M' and bag_no=:bagNo) b on a.article_id=b.article_id left join\r\n"
		+ "(select decode(cus_site,null,'-',cus_site) mapped ,item_id from fpo_main) m on m.item_id=a.article_id left join\r\n"
		+ "(select decode(clr_site,null,'-',clr_site) clrsite,item_id from fpo_main) d  on d.item_id=a.article_id  left join\r\n"
		+ " ops$dir.d_cntry_cd  c1 on substr(a.article_id,12,2)=c1.cntry_cd\r\n"
		+ "                    left join (select cntry_nm,item_id from fpo_main left join ops$dir.d_cntry_cd c2 on send_cntry_cd=c2.cntry_cd)c3 on c3.item_id=a.article_id\r\n"
		+ "order by a.scan_dt desc")
List<Collection> getListOfArtclHistry(@Param("bagNo")String bagNo);


@Query(nativeQuery = true, value = "select BAG_NO, BAG_TYPE, recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt,nvl(totcou,0)  ,nvl(totead,0),nvl(totnoead,0),nvl(totnotalot,0),nvl(withoutead,0)   from \r\n"
		+ "(select a.BAG_NO, BAG_TYPE, recd_event_dt recd_dt,scan_dt,  totcou,totead,totnoead,totnotalot,(decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))withoutead\r\n"
		+ "from FPO_SCAN_INFO a left join (select recd_event_dt, article_id from article_arr_info ) b on a.article_id=b.article_id  left join (select count(*) totcou, RECP_ID  from  article_arr_info group by recp_id) c on a.BAG_NO = c.recp_id \r\n"
		+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R' group by BAG_NO) d on d.BAG_NO=a.BAG_NO left join \r\n"
		+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!=:cusSite and bag_type='R' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
		+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='R' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
		+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='R'and scan_dt BETWEEN :fromDate AND :toDate   \r\n"
		+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_event_dt,scan_dt, totcou,totead,totnoead,totnotalot) \r\n"
		+ "union\r\n"
		+ "(select a.BAG_NO, BAG_TYPE, recd_dt,to_char(scan_dt,'DD/MM/YYYY HH24:MI:SS')scan_dt, totcou ,totead,totnoead,totnotalot, (decode(nvl(totcou,0),0,totead,totcou)-(nvl(totead,0)+nvl(totnoead,0)+nvl(totnotalot,0)))  withoutead\r\n"
		+ "from FPO_SCAN_INFO a left join (select recd_dt, article_id from articlearr_fpo_info) b on a.article_id=b.article_id  left join (select count(*) totcou ,bag_no from  articlearr_fpo_info group by bag_no) c on a.BAG_NO=c.bag_no \r\n"
		+ "left join (select count(*) totead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B' group by BAG_NO) d  on d.BAG_NO=a.bag_no left join \r\n"
		+ "(select count(*) totnoead,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site!=:cusSite and bag_type='B' group by BAG_NO)e  on e.BAG_NO=a.bag_no left join\r\n"
		+ "(select count(*) totnotalot,BAG_NO from FPO_SCAN_INFO WHERE SCANNED ='M' and cus_site=null and bag_type='B' group by BAG_NO)f  on f.BAG_NO=a.bag_no\r\n"
		+ "where SCANNED ='M' and CUS_SITE=:cusSite and bag_type='B' and scan_dt BETWEEN :fromDate AND :toDate  \r\n"
		+ "GROUP BY a.BAG_NO,BAG_TYPE,recd_dt,scan_dt,totcou,totead,totnoead,totnotalot) \r\n"
		+ "order by scan_dt desc")
List<Collection> getDefltConfirmHistBa(@Param("cusSite")String cusSite,@Param("toDate") String toDate,@Param("fromDate") String fromDate);

//@Query(nativeQuery = true, value = "\r\n" +
//		  "select arrival.*, to_char(fsi.scan_dt,'DD/MM/YYYY HH24:MI:SS')scannedDate  from (select bagNo,bagNoFlag,to_char(receivedDate,'DD/MM/YYYY HH24:MI:SS')receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from \r\n"
//		  +
//		  "									 (select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from   \r\n"
//		  +
//		  "								   (select count(*) tot_cou, recp_id  from article_arr_info b where status is null and substr(ooe,1,5)=substr(:cusSite,1,5)  group by recp_id  ) y  left join  \r\n"
//		  +
//		  "								   (select count(*) with_ead_thissite,recp_id,clr_site from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and substr(ooe,1,5)=substr(c.map_code,1,5) and decode(decode(a.clr_site,null,a.cus_site,a.clr_site) ,'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))=:cusSite  and substr(c.site_code,1,5)=substr(:cusSite,1,5) and status is null group by recp_id,clr_site) x on y.recp_id=x.recp_id left join   \r\n"
//		  +
//		  "							   (select nvl(count(*),0) notallotted,recp_id  from fpo_main a , article_arr_info b where a.item_id=b.article_id and  a.cus_site is null   and status is null group by recp_id) j on y.recp_id=j.recp_id left join    \r\n"
//		  +
//		  "										   (select nvl(count(*),0) withEadOtherSite,recp_id from  article_arr_info b left join fpo_main a on b.article_id=a.item_id  where b.status is null and decode(decode(a.clr_site,null,a.cus_site,a.clr_site),'INBPS5','INBOM5','INFCH5','INMAA5',decode(a.clr_site,null,a.cus_site,a.clr_site))!=:cusSite and a.cus_site is not null  group by recp_id) l on y.recp_id=l.recp_id left join \r\n"
//		  +
//		  "							   (select count(*) with_ead_totcou,recp_id  from fpo_main a , article_arr_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join  \r\n"
//		  +
//		  "									   (select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id   \r\n"
//		  +
//		  "									   where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site  \r\n"
//		  + "								  union  \r\n" +
//		  "									  select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from  \r\n"
//		  +
//		  "							  (select count(*) with_ead_thissite,bag_no,recd_fpo  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5) = substr(:cusSite,1,5)    and status is null group by bag_no,recd_fpo) x left join  \r\n"
//		  +
//		  "									  (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join \r\n"
//		  +
//		  "									  (select nvl(count(*),0) withEadOtherSite,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and decode(a.clr_site,null,a.cus_site,a.clr_site)<>c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and substr(c.site_code,1,5)=substr(:cusSite,1,5)     and status is null group by bag_no) l on x.bag_no=l.bag_no left join \r\n"
//		  +
//		  "									  (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join  \r\n"
//		  +
//		  "									  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join  \r\n"
//		  +
//		  "								  (select count(*) tot_cou, bag_no  from articlearr_fpo_info b where status is null and substr(recd_fpo,1,5)=substr(:cusSite,1,5)  group by bag_no  ) y   on x.bag_no=y.bag_no  \r\n"
//		  + "									  where x.bag_no is not null  \r\n" +
//		  "									  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo) \r\n"
//		  +
//		  "										  order by 3, 2 desc, 5 desc) arrival join (select *  from fpo_scan_info where id in (select max(id)  from \r\n"
//		  +
//		  "				 fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no where trunc(fsi.scan_dt) between to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy') order by fsi.scan_dt desc"
//		  ) List<Collection> getConfirmA(@Param("fromDate") String
//		  fromDate,@Param("toDate") String toDate,@Param("cusSite") String cusSite);

@Query(nativeQuery = true, value = "select arrival.*,to_char(fsi.scan_dt,'DD/MM/YYYY HH24:MI:SS')scannedDate  from (select bagNo,bagNoFlag,to_char(receivedDate,'DD/MM/YYYY HH24:MI:SS')receivedDate,nvl(totalCount,0) totalCount,nvl(withEadCurrSite,0) withEadCurrSite, nvl(withEadOtherSite,0) withEadOtherSite,nvl(withoutEad,0) withoutEad,nvl(notallotted,0) withEadSiteNotAllot,siteValue from\r\n"
		+ "								(select with_ead_thissite,y.recp_id bagNo,'N' bagNoFlag,(select max(recd_event_dt) from article_arr_info where status is null and recp_id=y.recp_id) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite, withEadOtherSite,without_ead withoutEad,notallotted,x.clr_site siteValue  from   \r\n"
		+ "										(SELECT COUNT(*) AS tot_cou, b.recp_id FROM fpo_scan_info f , article_arr_info b WHERE f.bag_no=b.recp_id  and  f.bag_type = 'R'  AND b.status IS NULL  AND f.cus_site = :cusSite AND f.bag_no NOT LIKE 'NEF%' AND f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no) GROUP BY b.recp_id  ) y  left join \r\n"
		+ "										( SELECT NVL(COUNT(CASE WHEN decode(substr(recp_id,7,5),'INBOM', decode(a.cus_site,'INBOM5',f.cus_site,'INBPS5',f.cus_site,a.cus_site),'INMAA',decode(a.cus_site,'INMAA5',f.cus_site,'INFCH5',f.cus_site,a.cus_site),a.cus_site) = f.cus_site THEN 1 END), 0) AS with_ead_thissite, f.bag_no AS recp_id,f.cus_site AS clr_site FROM fpo_scan_info f, article_arr_info b, fpo_main a,ops$dir.fpo_sites c\r\n"
		+ "								WHERE f.bag_no = b.recp_id AND b.article_id = a.item_id AND f.bag_type = 'R'  AND f.bag_no NOT LIKE 'NEF%'  AND f.cus_site = :cusSite AND SUBSTR(c.site_code, 1, 5) = SUBSTR(:cusSite, 1, 5) AND b.status IS NULL AND f.id = ( SELECT MAX(id) FROM fpo_scan_info WHERE bag_no = f.bag_no)\r\n"
		+ "								GROUP BY f.bag_no, f.cus_site) x on y.recp_id=x.recp_id left join  \r\n"
		+ "										(select nvl(count(*),0) notallotted,recp_id  from  fpo_scan_info f  , article_arr_info b, fpo_main a where f.bag_no=b.recp_id and a.item_id=b.article_id and f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no) and  a.cus_site is null and f.bag_type='R' and f.bag_no NOT LIKE 'NEF%' and b.status is null group by recp_id) j on y.recp_id=j.recp_id left join   \r\n"
		+ "										( SELECT NVL(COUNT(CASE WHEN decode(substr(recp_id,7,5),'INBOM', decode(a.cus_site,'INBOM5',f.cus_site,'INBPS5',f.cus_site,a.cus_site),'INMAA',decode(a.cus_site,'INMAA5',f.cus_site,'INFCH5',f.cus_site,a.cus_site),a.cus_site)! = f.cus_site THEN 1 END), 0) AS withEadOtherSite, f.bag_no AS recp_id FROM fpo_scan_info f, article_arr_info b, fpo_main a,ops$dir.fpo_sites c\r\n"
		+ "												WHERE f.bag_no = b.recp_id AND b.article_id = a.item_id AND f.bag_type = 'R'  AND f.bag_no NOT LIKE 'NEF%'  AND f.cus_site = :cusSite AND SUBSTR(c.site_code, 1, 5) = SUBSTR(:cusSite, 1, 5) AND b.status IS NULL AND f.id = ( SELECT MAX(id) FROM fpo_scan_info WHERE bag_no = f.bag_no)\r\n"
		+ "												GROUP BY f.bag_no) l on y.recp_id=l.recp_id left join\r\n"
		+ "										(select count(*) with_ead_totcou,recp_id  from article_arr_info b, fpo_main a ,  ops$dir.fpo_sites c  where b.article_id=a.item_id   and site_code=:cusSite and substr(ooe,1,5)=substr(c.map_code,1,5) and status is null group by recp_id)k on y.recp_id=k.recp_id left join  \r\n"
		+ "										(select count(*) without_ead,recp_id   from article_arr_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by recp_id) z  on y.recp_id=z.recp_id   \r\n"
		+ "													where x.recp_id is not null group by y.recp_id,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,clr_site\r\n"
		+ "								                     union\r\n"
		+ "								                     select with_ead_thissite,x.bag_no bagNo,'Y' bagNoFlag,(select max(recd_dt) from articlearr_fpo_info where status is null and bag_no=x.bag_no) receivedDate,tot_cou totalCount,with_ead_thissite withEadCurrSite,  withEadOtherSite,without_ead withoutEad,notallotted,recd_fpo siteValue  from  \r\n"
		+ "								                                      (select count(*) with_ead_thissite, f.bag_no,f.cus_site recd_fpo from fpo_scan_info f, articlearr_fpo_info b, fpo_main a where f.bag_no=b.bag_no and b.article_id=a.item_id and f.bag_type='B' and f.bag_no not LIKE 'NEF%'  and decode(f.cus_site,'INBOM5',decode(a.cus_site,'INBPS5','INBOM5','INBOM5','INBOM5',a.cus_site),'INBPS5',decode(a.cus_site,'INBPS5','INBPS5','INBOM5','INBPS5',a.cus_site),'INMAA5',\r\n"
		+ "				                                                    decode(a.cus_site,'INMAA5','INMAA5','INFCH5','INMAA5',a.cus_site),'INFCH5',decode(a.cus_site,'INFCH5','INFCH5','INMAA5','INFCH5',a.cus_site),a.cus_site)=f.cus_site\r\n"
		+ "				                                                    group by f.bag_no,f.cus_site) x left join \r\n"
		+ "								                                      (select count(*) notallotted,bag_no  from fpo_main a , articlearr_fpo_info b where a.item_id=b.article_id and a.cus_site is null and status is null group by bag_no) j on x.bag_no=j.bag_no left join\r\n"
		+ "								                                      (select count(*)withEadOtherSite,f.bag_no from fpo_scan_info f, articlearr_fpo_info b, fpo_main a where f.bag_no=b.bag_no and b.article_id=a.item_id and f.bag_type='B' and f.bag_no not LIKE 'NEF%'  and decode(f.cus_site,'INBOM5',decode(a.cus_site,'INBPS5','INBOM5','INBOM5','INBOM5',a.cus_site),'INBPS5',decode(a.cus_site,'INBPS5','INBPS5','INBOM5','INBPS5',a.cus_site),'INMAA5',\r\n"
		+ "				                                                        decode(a.cus_site,'INMAA5','INMAA5','INFCH5','INMAA5',a.cus_site),'INFCH5',decode(a.cus_site,'INFCH5','INFCH5','INMAA5','INFCH5',a.cus_site),a.cus_site)!=f.cus_site\r\n"
		+ "				                                                        group by f.bag_no) l on x.bag_no=l.bag_no left join\r\n"
		+ "								                                      (select count(*) with_ead_totcou,bag_no  from fpo_main a , articlearr_fpo_info b, ops$dir.fpo_sites c  where a.item_id=b.article_id  and a.cus_site=c.site_code and  substr(b.recd_fpo,1,5)=substr(c.map_code,1,5) and status is null group by bag_no) k on x.bag_no=k.bag_no left join \r\n"
		+ "																	  (select count(*) without_ead,bag_no  from articlearr_fpo_info b where article_id not in (select item_id  from fpo_main b ) and status is null group by bag_no) z on x.bag_no=z.bag_no left join \r\n"
		+ "								                                      (select count(*) tot_cou, b.bag_no  from fpo_scan_info f, articlearr_fpo_info b  where f.bag_no=b.bag_no and f.bag_type='B' and  b.status is null and f.cus_site=:cusSite AND f.bag_no NOT LIKE 'NEF%' AND f.id = (SELECT MAX(id)FROM fpo_scan_info WHERE bag_no = f.bag_no)  group by b.bag_no  ) y   on x.bag_no=y.bag_no \r\n"
		+ "																	  where x.bag_no is not null \r\n"
		+ "																	  group by x.bag_no,tot_cou,with_ead_totcou,with_ead_thissite, withEadOtherSite,without_ead,notallotted,recd_fpo) \r\n"
		+ "																	  order by 3, 2 desc, 5 desc) arrival join (select *  from fpo_scan_info where id in (select max(id)  from \r\n"
		+ "												fpo_scan_info group by bag_no)) fsi on arrival.bagNo = fsi.bag_no where trunc(fsi.scan_dt) between to_date(:fromDate, 'dd/mm/yyyy') AND TO_DATE(:toDate, 'dd/mm/yyyy') order by fsi.scan_dt desc"
		  ) List<Collection> getConfirmA(@Param("fromDate") String
		  fromDate,@Param("toDate") String toDate,@Param("cusSite") String cusSite);


	
}
