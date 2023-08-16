package com.demo.fpo.NsmLsmRepo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.D_EMP;

public interface NsmLsm_D_EMPrepo extends JpaRepository<D_EMP, Long> {
	
	@Query(nativeQuery = true, value = "select a.user_id,a.cus_site,a.role_name,b.EMP_NAME from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id and end_date is null and end_dt is null and USER_ACCOUNT_CONTROL='A'")
	List<Collection> getempName(@Param("id") String id);
	
	//@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null")
	@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null and cus_site=(select fpo_cus_site from ops$dir.d_emp where a.user_id=:offid  and end_date is null and user_account_control='A')")
	List<String> getmulrole(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id  and end_dt is null and USER_ACCOUNT_CONTROL='A'")
	List<D_EMP> getrole(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(ROLE)||'_icon.JPG',a.ROLE,disp_rolenm,decode(b.user_id,:offid,1,0),a.OLDROLE from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite) where disp_group = 1 and role<>'PNA' and role<>'PLA' order by dispord")
	List<Collection> getallroles_a(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(ROLE)||'_icon.JPG',a.ROLE,disp_rolenm,decode(b.user_id,:offid,1,0),a.OLDROLE from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite) where disp_group = 2 order by dispord") 
	List<Collection> getallroles_b(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(ROLE)||'_icon.JPG',a.ROLE,disp_rolenm,decode(b.user_id,:offid,1,0),a.OLDROLE from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite) where disp_group = 3 order by dispord")
	List<Collection> getallroles_c(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select listagg(mail_class_cd,',') within group(order by ID)as mailclass from ops$dir.d_emp_roles where cus_site=:cusite and role_name=:role") 
	List<Collection> getmailclass(@Param("cusite") String cusite,@Param("role")String role);
	//LSA roles with designation
	/*
	 * @Query(nativeQuery = true, value = "\r\n" +
	 * "SELECT t.DISP_ROLENM,t.ROLE,Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value)) AS DESIGNATION  FROM   OPS$DIR.fpo_roles t,\r\n"
	 * +
	 * "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.DESIGNATION, ',') + 1) AS sys.ODCINUMBERLIST) )lines where site_access <> 'INNSA5' and disp_group = 1 and LOWER(Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value))) = LOWER(?1)\r\n"
	 * + "         ORDER  BY dispord, lines.column_value") List<Collection>
	 * getdisproleNm_a(String desg);
	 * 
	 * @Query(nativeQuery = true, value = "\r\n" +
	 * "SELECT t.DISP_ROLENM,t.ROLE,Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value)) AS DESIGNATION  FROM   OPS$DIR.fpo_roles t,\r\n"
	 * +
	 * "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.DESIGNATION, ',') + 1) AS sys.ODCINUMBERLIST) )lines where site_access <> 'INNSA5' and disp_group = 2 and LOWER(Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value))) = LOWER(?1)\r\n"
	 * + "         ORDER  BY dispord, lines.column_value") List<Collection>
	 * getdisproleNm_b(String desg);
	 * 
	 * @Query(nativeQuery = true, value = "\r\n" +
	 * "SELECT t.DISP_ROLENM,t.ROLE, Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value)) AS DESIGNATION  FROM   OPS$DIR.fpo_roles t,\r\n"
	 * +
	 * "                TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.DESIGNATION, ',') + 1) AS sys.ODCINUMBERLIST) )lines where site_access <> 'INNSA5' and disp_group = 3 and (LOWER(Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value))) = LOWER(?1) or t.designation is null)\r\n"
	 * + "         ORDER  BY dispord, lines.column_value") List<Collection>
	 * getdisproleNm_c(String desg);
	 */


	// Nsm role with designation
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "SELECT t.DISP_ROLENM,t.ROLE,Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value)) AS DESIGNATION  FROM   OPS$DIR.fpo_roles t,\r\n"
	 * +
	 * "                       TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.DESIGNATION, ',') + 1) AS sys.ODCINUMBERLIST) )lines where t.site_access= ?2 and disp_group = 1 and LOWER(Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value))) = LOWER(?1)\r\n"
	 * + "             ORDER  BY dispord, lines.column_value") List<Collection>
	 * getNsmadminrole_a(String desg, String siteaces);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "SELECT t.DISP_ROLENM,t.ROLE,Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value)) AS DESIGNATION  FROM   OPS$DIR.fpo_roles t,\r\n"
	 * +
	 * "			                   TABLE(Cast(MULTISET(SELECT LEVEL FROM   dual CONNECT BY LEVEL <= Regexp_count(t.DESIGNATION, ',') + 1) AS sys.ODCINUMBERLIST) )lines where t.site_access= ?2 and disp_group = 3 and (LOWER(Trim(Regexp_substr(t.DESIGNATION, '[^,]+', 1, lines.column_value))) = LOWER(?1) or t.designation is null)\r\n"
	 * + "			            ORDER  BY dispord, lines.column_value")
	 * List<Collection> getNsmmiscel_c(String desg, String siteaces);
	 */
	
	
//Nsm without designation
	
	@Query(nativeQuery = true, value = " SELECT t.DISP_ROLENM,t.ROLE,t.designation FROM OPS$DIR.fpo_roles t where site_access= :siteaces and disp_group = 1 ORDER  BY dispord")
	List<Collection> getNsmadminrole_a(@Param("siteaces") String siteaces);
	
	@Query(nativeQuery = true, value = " SELECT t.DISP_ROLENM,t.ROLE,t.designation FROM OPS$DIR.fpo_roles t where (site_access= :siteaces or site_access='BOTH') and disp_group = 3 ORDER  BY dispord ")
	List<Collection> getNsmmiscel_c(@Param("siteaces") String siteaces);
	
	//LSA Role without designation
	@Query(nativeQuery = true, value = "SELECT t.DISP_ROLENM,t.ROLE,t.designation FROM OPS$DIR.fpo_roles t\r\n"
			+ "                 where site_access <> 'INNSA5' and disp_group = 1 and role<>'PLA' \r\n"
			+ "         ORDER  BY dispord")
	List<Collection> getdisproleNm_a();
	
	@Query(nativeQuery = true, value = "SELECT t.DISP_ROLENM,t.ROLE,t.designation FROM OPS$DIR.fpo_roles t\r\n"
			+ "                 where site_access <> 'INNSA5' and disp_group = 2 \r\n"
			+ "         ORDER  BY dispord")
	List<Collection> getdisproleNm_b();
	
	@Query(nativeQuery = true, value = "SELECT t.DISP_ROLENM,t.ROLE,t.designation FROM OPS$DIR.fpo_roles t\r\n"
			+ "               where site_access <> 'INNSA5' and disp_group = 3 \r\n"
			+ "         ORDER  BY dispord")
	List<Collection> getdisproleNm_c();
	
	//Get count of PNA
	@Query(nativeQuery = true, value = "select count(*) as PNAcount from OPS$DIR.d_emp_roles where role_name = 'PNA' and cus_site = 'INNSA5' and end_dt is null")
	Long getPNAcount();
	
	@Query(nativeQuery = true, value = "select user_id from OPS$DIR.d_emp_roles where role_name = 'PNA' and cus_site = 'INNSA5' and end_dt is null")
	List<Collection> getPNAdata();
	
	//Get count of APR
	@Query(nativeQuery = true, value = "select count(*) as APRcount from OPS$DIR.d_emp_roles where role_name = 'ARP' and cus_site = 'INNSA5' and end_dt is null")
	int getARPcount();
		
	// Get count of PLA
	@Query(nativeQuery = true, value = "select count(*) as PLAcount from OPS$DIR.d_emp_roles where role_name = :Nsmrole and cus_site = :cs and end_dt is null ")
	int getPLAcount(@Param("Nsmrole") String Nsmrole, @Param("cs") String cs);
	
	// Get count of PAA
		@Query(nativeQuery = true, value = "select count(*) as PAAcount from OPS$DIR.d_emp_roles where role_name = :Nsmrole and cus_site = :cs and end_dt is null ")
		int getPAAcount(@Param("Nsmrole") String Nsmrole, @Param("cs") String cs);
		
		// Get count of ARP
		@Query(nativeQuery = true, value = "select count(*) as PAAcount from OPS$DIR.d_emp_roles where role_name = :Nsmrole and cus_site = :cs and end_dt is null ")
		int getARPcount(@Param("Nsmrole") String Nsmrole, @Param("cs") String cs);
        
		@Query(nativeQuery = true, value = "select  item_id,to_char(to_date(substr(POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'), cin_no,to_char(cin_dt, 'DD/MM/YYYY') as Cin_dt,mail_class_cd from fpo_main where cus_site is null")
		List<Collection> getnotmappedSite();
		
		@Query(nativeQuery = true, value = "select a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'), a.cin_no,to_char(a.cin_dt, 'DD/MM/YYYY') as Cin_dt, a.mail_class_cd, b.recd_fpo, to_char(b.recd_dt, 'DD/MM/YYYY') as recdDT  from fpo_main a left join articlearr_fpo_info b on a.item_id = b.article_id  where a.cus_site is null and b.recd_fpo is not null")	
		List<Collection> yettobemappedSite();
		
		@Query(nativeQuery = true, value = "select a.item_id,to_char(to_date(substr(a.POSTINGDT,0,10),'yyyy-mm-dd'),'dd/mm/yyyy'), a.cin_no,to_char(a.cin_dt, 'DD/MM/YYYY') as Cin_dt, a.mail_class_cd, b.recd_fpo, to_char(b.recd_dt, 'DD/MM/YYYY') as recdDT,officer_id,to_char(allot_dt,'DD/MM/YYYY'),reason  from fpo_map_site c, fpo_main a left join articlearr_fpo_info b on a.item_id = b.article_id  where c.article_id=a.item_id and a.cus_site is not null and b.recd_fpo is not null")	
		List<Collection> mapbyoff();

}
