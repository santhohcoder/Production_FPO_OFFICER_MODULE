package com.demo.fpo.NsmLsmRepo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.NsmLsmModel.FPO_SITE_MGMT;
import com.demo.fpo.NsmLsmModel.D_EMP_ROLES;
import com.demo.fpo.NsmLsmModel.FPO_SITE;
import com.demo.fpo.NsmLsmModel.FPO_SITE_ALLOT;

@Repository
public interface FpoSiteMgmtRepo extends JpaRepository<FPO_SITE_MGMT, Long> {
	
    
	/*@Query(nativeQuery = true, value = "select state_code from ops$dir.d_state_map group by state_code,state_name where STATE_NAME =?")
	String siteState(String fpositestate);*/
	
//	@Query(nativeQuery = true, value = "select max(state_code) from ops$dir.d_state_map group by state_name where STATE_NAME =?")
//	String siteState(String fpositestate);

	/*@Query(nativeQuery = true, value = "SELECT STATE_NAME FROM ops$dir.D_STATE_MAP group by state_code,state_name order by 1")
	List<String> getAllStates();*/
	
	@Query(nativeQuery = true, value = "select max(new_state_code) from ops$dir.d_state_map where STATE_NAME =:statename")
	String siteState(@Param("statename") String fpositestate);
    
    @Query(nativeQuery = true, value = "SELECT STATE_NAME,max(STATE_CODE) FROM ops$dir.d_state_map group by state_name order by 1")
   	List<String> getstates();
    
	@Query(nativeQuery = true, value = "SELECT DISTINCT DISTRICT FROM ops$dir.pdi_pincodes")
	List<String> getdistrict();
	
	@Query(nativeQuery = true, value = "select site_name, site_code, site_state from ops$dir.fpo_site_mgmt where SITE_STATUS = 'N'")
	List<String> getaddsites();
	
	@Query(nativeQuery = true, value = "select state_name from ops$dir.d_state_map where state_code =:statecd") 
	String getStateName(@Param("statecd") String statecd);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_mgmt fst set fst.SITE_STATUS = 'Y', fst.reason= :reson, fst.entry_dt=:actdt, fst.off_id=:offid  where fst.site_code =:siteval and fst.site_id=(select max(site_id) from ops$dir.fpo_site_mgmt where site_code =:siteval)")
	void activatesite(@Param("siteval") String siteval, @Param("reson") String reson, @Param("actdt") Date actdt, @Param("offid") String offid);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.fpo_sites set SITE_ACTIVE = 'Y', activated_dt=:dt, activated_by=:offid  where site_code =:siteval")
	void activatefposite(@Param("siteval") String siteval, @Param("dt") Date dt, @Param("offid") String offid);
	
//	@Query(nativeQuery = true, value = "select site_name,a.site_code,created_dt,site_state,site_status,y.active_dt,b.block_dt,d.delete_dt,c.unblock_dt from\r\n"
//			+ "(SELECT site_name, site_code, to_char(created_dt, 'DD/MM/YYYY') created_dt, site_state, SITE_STATUS FROM ops$dir.FPO_SITE_MGMT) a left join\r\n"
//			+ "(select site_code,max(to_char(crt_dt, 'DD/MM/YYYY')) active_dt from ops$dir.oth_status_fposite b where site_status='Y' group by site_code) y on a.site_code=y.site_code left join\r\n"
//			+ "(select site_code,max(to_char(crt_dt, 'DD/MM/YYYY')) block_dt from ops$dir.oth_status_fposite b where site_status='B' group by site_code) b on a.site_code=b.site_code left join\r\n"
//			+ "(select site_code,max(to_char(crt_dt, 'DD/MM/YYYY')) delete_dt from ops$dir.oth_status_fposite b where site_status='D' group by site_code) d on a.site_code=d.site_code left join\r\n"
//			+ "(select site_code,max(to_char(crt_dt, 'DD/MM/YYYY')) unblock_dt from ops$dir.oth_status_fposite b where site_status='U' group by site_code) c on a.site_code=c.site_code \r\n"
//			+ "order by 2")
	@Query(nativeQuery = true, value = "select site_name,a.site_code,created_dt,site_state,site_status,y.active_dt,b.block_dt,d.delete_dt,c.unblock_dt,clustered,clussite,entry_dt from\r\n"
			+ "(SELECT site_name, site_code, to_char(entry_dt, 'DD/MM/YYYY HH24:MI:SS') entry_dt, site_state, SITE_STATUS FROM ops$dir.FPO_SITE_MGMT) a left join\r\n"
			+ "(SELECT site_code,to_char(created_dt,'DD/MM/YYYY HH24:MI:SS') created_dt,clustered,clussite FROM ops$dir.FPO_SITES) e on a.site_code=e.site_code left join \r\n"
			+ "(select site_code,to_char(max(crt_dt), 'DD/MM/YYYY  HH24:MI:SS') active_dt from ops$dir.oth_status_fposite b where site_status='Y' group by site_code) y on a.site_code=y.site_code left join\r\n"
			+ "(select site_code,to_char(max(crt_dt), 'DD/MM/YYYY  HH24:MI:SS') block_dt from ops$dir.oth_status_fposite b where site_status='B' group by site_code) b on a.site_code=b.site_code left join\r\n"
			+ "(select site_code,to_char(max(crt_dt), 'DD/MM/YYYY  HH24:MI:SS') delete_dt from ops$dir.oth_status_fposite b where site_status='D' group by site_code) d on a.site_code=d.site_code left join\r\n"
			+ "(select site_code,to_char(max(crt_dt), 'DD/MM/YYYY  HH24:MI:SS') unblock_dt from ops$dir.oth_status_fposite b where site_status='U' group by site_code) c on a.site_code=c.site_code \r\n"
			+ "order by 3 desc")
	List<Collection> viewallsite();
	
	@Query(nativeQuery = true, value = "SELECT a.SITE_NAME, a.SITE_CODE, created_dt CREATED_DT, a.SITE_STATE, a.SITE_ID, SITE_STATUS, a.site_type,decode(clustered,null,'null',clustered),decode(clussite,null,'null',clussite) FROM ops$dir.fpo_site_mgmt a, ops$dir.fpo_sites b  where a.site_code=b.site_code and SITE_STATUS!='D' and SITE_STATUS!='N' order by 3 desc")
	List<Collection> viewactivesite();
	
	 @Query(nativeQuery = true, value = "SELECT SITE_NAME, SITE_CODE, SITE_STATE, to_char((select count (distinct a.user_id) from ops$dir.d_emp_roles a join ops$dir.d_emp b on a.user_id = b.user_id where a.cus_site = SITE_CODE and a.end_dt is null  and user_account_control='A')) count FROM ops$dir.fpo_site_mgmt  where SITE_STATUS='Y' group by  SITE_NAME, SITE_CODE, SITE_STATE")
	List<Collection> getactiveSites();
	 
	 @Query(nativeQuery = true, value = "select distinct(c.user_id), a.SITE_NAME, c.cus_site, b.EMP_NAME, c.role_name,\r\n"
	 		+ "	 		to_char( c.start_dt, 'DD/MM/YYYY')as startdt, to_char( c.end_dt, 'DD/MM/YYYY')as enddt, c.start_dt from ops$dir.fpo_site_mgmt a, ops$dir.d_emp b, ops$dir.d_emp_roles c where c.cus_site= :sitecde and c.user_id = b.user_id and c.role_name = 'PLA' and a.site_code = c.cus_site    and user_account_control='A' order by c.start_dt desc"
	 		)
	List<Collection> viewusersite(@Param("sitecde") String sitecde);
	 
	 @Query(nativeQuery = true, value = "select distinct(c.user_id), a.SITE_NAME, c.cus_site, b.EMP_NAME, c.role_name,\r\n"
		 		+ "	 		to_char( c.start_dt, 'DD/MM/YYYY')as startdt, to_char( c.end_dt, 'DD/MM/YYYY')as enddt, c.start_dt from ops$dir.fpo_site_mgmt a, ops$dir.d_emp b, ops$dir.d_emp_roles c where  c.user_id = b.user_id and c.role_name = 'PLA' and a.site_code = c.cus_site   and user_account_control='A'  order by c.start_dt desc"
		 		)
		List<Collection> viewusersiteall(@Param("sitecde") String sitecde);
	 
	 @Query(nativeQuery = true, value = "select count (distinct a.user_id) from ops$dir.d_emp_roles a join ops$dir.d_emp b on a.user_id = b.user_id where a.cus_site= :sitecde and a.end_dt is null  and user_account_control='A'")
		String getcountofusers(@Param("sitecde") String sitecde);
		 
	 
	 @Query(nativeQuery = true, value = "SELECT distinct SITE_NAME,site_code FROM ops$dir.fpo_site_mgmt where SITE_STATUS='Y'")
	List<Collection> getactiveusers();
	 
	 // Lsm hstry roles
	 /* @Query(nativeQuery = true, value = " select distinct(c.user_id), a.SITE_NAME, c.cus_site, b.EMP_NAME, (select listagg(role_name,', ') within group(order by ID)as roles FROM ops$dir.d_emp_roles r where cus_site = ?1 and c.user_id = r.user_id )as rolenme,\r\n"
	 		+ "	 		to_char( c.start_dt, 'DD/MM/YYYY')as startdt, to_char( c.end_dt, 'DD/MM/YYYY')as enddt from ops$dir.fpo_site_mgmt a, ops$dir.d_emp b, ops$dir.d_emp_roles c where c.cus_site= ?1 and c.user_id = b.user_id and a.site_code = c.cus_site and c.role_name not in 'PLA' order by to_char( c.start_dt, 'DD/MM/YYYY')"
		 		)
		List<Collection> viewhstryroles(String sitecde);*/ 
	 
	 @Query(nativeQuery = true, value = " select distinct(c.user_id), a.SITE_NAME, c.cus_site, b.EMP_NAME, (select listagg(role_name,', ') within group(order by ID)as roles FROM ops$dir.d_emp_roles r where cus_site = :sitecde and c.user_id = r.user_id )as rolenme,\r\n"
		 		+ "	 		to_char( c.start_dt, 'DD/MM/YYYY')as startdt, to_char( c.end_dt, 'DD/MM/YYYY')as enddt, c.start_dt from ops$dir.fpo_site_mgmt a, ops$dir.d_emp b, ops$dir.d_emp_roles c where c.cus_site= :sitecde and c.user_id = b.user_id and a.site_code = c.cus_site    and user_account_control='A'  order by c.start_dt desc "
			 		)
			List<Collection> viewhstryroles(@Param("sitecde") String sitecde);
	 
	 @Query(nativeQuery = true, value = " select distinct(c.user_id), a.SITE_NAME, c.cus_site, b.EMP_NAME, (select listagg(role_name,', ') within group(order by ID)as roles FROM ops$dir.d_emp_roles r where c.user_id = r.user_id )as rolenme,\r\n"
		 		+ "	 		to_char( c.start_dt, 'DD/MM/YYYY')as startdt, to_char( c.end_dt, 'DD/MM/YYYY')as enddt , c.start_dt from ops$dir.fpo_site_mgmt a, ops$dir.d_emp b, ops$dir.d_emp_roles c where c.user_id = b.user_id  and user_account_control='A' and a.site_code = c.cus_site    order by c.start_dt desc "
			 		)
			List<Collection> viewhstryrolesall(@Param("sitecde") String sitecde);
		 
	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(nativeQuery = true, value
	 * ="update fpo_site_mgmt fst set fst.site_name= ?1, fst.site_state= ?2, fst.off_id= ?4, fst.site_type= ?5, fst.created_dt= ?6, fst.site_state_cd= ?7 where fst.site_code= ?3"
	 * ) void updatefpoedit(String fpositename, String fpositestate, String
	 * fpositecde, String crtby, String fpotyp, Date date, String fpoSs);
	 */
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_mgmt fst set fst.SITE_STATUS = 'D', fst.reason= :resp where fst.site_code = :sitecde")
	void delfposite(@Param("sitecde") String sitecde, @Param("resp") String resp);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.d_emp_roles set  designation = :design, mobile_no=:mobileno, email_id=:email, reason=:reason, assign_by=:assignby , assign_dt=:assigndt where cus_site = :sitecde and role_name='PLA' and user_id=:offid")
	void upddata(@Param("sitecde") String sitecde, @Param("offid") String offid, @Param("design") String design, @Param("mobileno") String mobileno, @Param("email") String email, @Param("reason") String reason,@Param("assignby") String assignby, @Param("assigndt") Date assigndt);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_mgmt fst set fst.SITE_STATUS = 'B', fst.reason= :resblc where fst.site_code = :sitecde and fst.site_id=(select max(site_id) from ops$dir.fpo_site_mgmt where site_code =:sitecde)")
	void blcfposite(@Param("sitecde") String sitecde, @Param("resblc") String resblc);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_mgmt fst set fst.SITE_STATUS = 'Y', fst.reason= :respunblc where fst.site_code = :sitecde and fst.site_id=(select max(site_id) from ops$dir.fpo_site_mgmt where site_code =:sitecde)")
	void unBlcFpoSite(@Param("sitecde") String sitecde, @Param("respunblc") String respunblc);
	
	@Query(nativeQuery = true, value = "SELECT site_name FROM ops$dir.fpo_sites where site_name LIKE %:val%")
	List<String> getdistrcit(@Param("val") String val);
	
//	@Query(nativeQuery = true, value = "SELECT DISTINCT site_name FROM ops$dir.fpo_sites where site_code<>'INNSA5'")
//	List<String> getsitenames();
	
//	@Query(nativeQuery = true, value = "SELECT site_id FROM ops$dir.d_globl_nonedi where port_cd not in (select site_code from ops$dir.fpo_sites where (site_active='Y' or site_active='N' or site_active='B' or site_active is null)) and category='P'")
//	List<String> getsitenames();
	
	@Query(nativeQuery = true, value = "SELECT site_name FROM ops$dir.ooe_sites where site_active='Y' and site_code <> 'INNSA5' and site_code not in (select site_code from ops$dir.fpo_sites where (site_active='Y' or site_active='N' or site_active='B' or site_active is null)) ")
	List<String> getsitenames();
	
	@Query(nativeQuery = true, value = "SELECT a.site_name,a.site_code,a.site_type,a.site_state,a.off_id,b.clustered,b.clussite FROM ops$dir.fpo_site_mgmt a, ops$dir.fpo_sites b where site_status='Y' and  a.site_code=b.site_code and a.site_code=:sitecode")
	List<String> getsitenamestoalter(@Param("sitecode") String sitecode);

	@Query(nativeQuery = true, value = "select distinct a.site_name,a.site_code from ops$dir.fpo_sites a, ops$dir.fpo_site_mgmt b\r\n"
			+ "			 where  a.site_code=b.site_code and site_status='Y' and b.site_code  not in \r\n"
			+ "			 ( select cus_site from ops$dir.d_emp_roles where role_name='PLA' and end_dt is null group by cus_site,role_name  having count(*) >= 2 ) ")
	List<String> getLsmSitenme();
	
	@Query(nativeQuery = true, value = "SELECT site_code, site_state, site_type,clustered,clussite from ops$dir.fpo_sites where site_name = :sitename")
	List<Collection> getsitelist(@Param("sitename") String sitename);
	
	/*@Query(nativeQuery = true, value = "SELECT port_cd,site_id from ops$dir.d_globl_nonedi where site_id = :sitename")
	List<Collection> getsiteinfo(@Param("sitename") String sitename);*/
	
	//@Query(nativeQuery = true, value = "SELECT site_code,site_name from ops$dir.d_globl_nonedi where site_name = :sitename")
	//List<Collection> getsiteinfo(@Param("sitename") String sitename);
	
	@Query(nativeQuery = true, value = "SELECT site_code,site_name,site_state from ops$dir.ooe_sites where site_name = :sitename and site_code <> 'INNSA5'")
	List<Collection> getsiteinfo(@Param("sitename") String sitename);
	
	/*@Query(nativeQuery = true, value = "SELECT port_cd,site_id from ops$dir.d_globl_nonedi where port_cd != :sitename")
	List<Collection> getclussiteinfo(@Param("sitename") String sitename);*/
	
	
	@Query(nativeQuery = true, value = "SELECT site_code,site_name from ops$dir.ooe_sites where site_code != :sitename and site_active='Y' and site_code<>'INNSA5'")
	List<Collection> getclussiteinfo(@Param("sitename") String sitename);

	
	@Query(nativeQuery = true, value = "SELECT user_id from ops$dir.d_emp where fpo_cus_site = :sitename  and user_account_control='A' and user_id not in (select user_id from ops$dir.d_emp_roles where end_dt is null)")
	List<Collection> getssolist(@Param("sitename") String sitename);
	
	
	@Query(nativeQuery = true, value = "select EMP_NAME, DESIGNATION, fpo_cus_site,mobile_no,email_id from ops$dir.d_emp where user_id = :offid  and user_account_control='A' and fpo_cus_site=:cusite")
	List<Collection> getnamendesg(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select  count(*)  from ops$dir.d_emp_roles where user_id = :offid and cus_site=:cusite and end_dt is null")
	List<Collection> chkplafou(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select  count(*)  from ops$dir.d_emp_roles where user_id = :offid  and role_name='PLA' and cus_site=:cusite and end_dt is null")
	int plafouint(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select  decode(count(*),0,'SSO ID entered is Invalid!',1,'SSO ID entered is not mapped to this site!')   from ops$dir.d_emp where user_id = :offid and user_account_control='A'")
	List<Collection> plafouemp(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select  count(*)   from ops$dir.d_emp_roles where user_id = :offid and role_name='PLA' and cus_site=:cusite and end_dt is null")
	int plaalreadyfou(@Param("offid") String offid, @Param("cusite") String cusite);

	
	@Query(nativeQuery = true, value = "SELECT * from ops$dir.FPO_SITE_MGMT where site_code = :sitecd and site_id=(select max(site_id) from ops$dir.fpo_site_mgmt where site_code =:sitecd)") 
	FPO_SITE_MGMT geteditsite(@Param("sitecd") String sitecd);
	
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_mgmt set site_type=:site_type, site_state=:site_state, entry_dt=:date, off_id=:offid, reason=:reason where site_code=:site_code and site_id=(select max(site_id) from ops$dir.fpo_site_mgmt where site_code =:site_code)")
	void updatefpositemgmt(@Param("site_type") String site_type, @Param("site_state") String site_state, @Param("date") Date date, @Param("offid") String offid, @Param("reason") String reason,@Param("site_code") String site_code);
	
	
	@Query(nativeQuery = true, value = "select distinct a.user_id, b.EMP_NAME, (select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.d_emp_roles c where cus_site = :code and end_dt is null and a.user_id = c.user_id  )as rolenme from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.cus_site= :code and a.user_id = b.user_id and a.end_dt is null")
	List<Collection> getidandname(@Param("code") String code);
	
	/*@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.FPO_SITE_MGMT where site_code =?1")
	void sitedelete(String delcd);*/
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.FPO_SITES where site_code =:delcd")
	void sitedelete(String delcd);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "update ops$dir.D_EMP_ROLES demp set demp.end_dt = :date, demp.revoked_by = :offid, demp.reason = :remarks where user_id =:ssoid and role_name ='PLA' and end_dt is null")
	void deletelsmsite(@Param("ssoid") String ssoid, @Param("date") Date date, @Param("offid") String offid, @Param("remarks") String remarks);
	
	
	@Query(nativeQuery = true, value = "select site_name from ops$dir.fpo_site_mgmt") 
	List<String> getsitename();
	
	//Get allocate Lsm
	@Query(nativeQuery = true, value = "select cus_site, user_id from ops$dir.d_emp_roles where role_name = 'PLA' and end_dt is null")
	List<Collection> getallctlsm();
	 
	// Nsm table
	
	@Query(nativeQuery = true, value = "select b.site_name, a.cus_site, b.site_state, c.EMP_NAME, a.user_id, c.DESIGNATION, c.email_id,  c.mobile_no, to_char(a.start_dt, 'DD/MM/YYYY'), to_char(a.start_dt, 'HH24:MM:SS')  from ops$dir.d_emp_roles a, ops$dir.fpo_sites b, ops$dir.d_emp c \r\n"
			+ "where a.cus_site = b.site_code and a.user_id = c.user_id and a.role_name = 'PNA' and a.end_dt is null \r\n" + "order by start_dt desc")
	List<Collection> getNsmdata();
	
	@Query(nativeQuery = true, value = "select b.site_name, a.cus_site, b.site_state, c.EMP_NAME, a.user_id, c.DESIGNATION, c.email_id,  c.mobile_no, to_char(a.start_dt, 'DD/MM/YYYY'), to_char(a.start_dt, 'HH24:MM:SS'),to_char(a.end_dt, 'DD/MM/YYYY'), to_char(a.end_dt, 'HH24:MM:SS')   from ops$dir.edit_d_emp_roles a, ops$dir.fpo_sites b, ops$dir.d_emp c \r\n"
			+ "where a.cus_site = b.site_code and a.user_id = c.user_id and a.role_name = 'PNA' and a.end_dt is not null \r\n" + "order by start_dt desc")
	List<Collection> getNsmdatapast();
	
	//Lsm table
	/*@Query(nativeQuery = true, value = "select b.site_name, a.cus_site, b.site_state, c.EMP_NAME, a.user_id, c.DESIGNATION, c.email_id,  c.mobile_no, to_char(a.start_dt, 'DD/MM/YYYY'), to_char(a.start_dt, 'HH24:MM:SS') from ops$dir.d_emp_roles a, ops$dir.fpo_sites b, ops$dir.d_emp c \r\n"
			+ "where a.cus_site = b.site_code and a.user_id = c.user_id and a.role_name = 'PLA' and a.end_dt is null \r\n" + "order by start_dt desc")
	List<Collection> getLsmdata();*/
	
	//removeLsm data
	@Query(nativeQuery = true, value = "select DISTINCT a.cus_site, b.site_name from ops$dir.d_emp_roles a, ops$dir.fpo_sites b where a.cus_site = b.site_code and a.role_name = 'PLA' and a.end_dt is null and a.cus_site<>'INNSA5'")
	List<String> sitecdnm();
	
	@Query(nativeQuery = true, value = "select b.site_name, a.cus_site, b.site_state, c.EMP_NAME, a.user_id, c.DESIGNATION, c.email_id,  c.mobile_no, to_char(a.start_dt, 'DD/MM/YYYY'), to_char(a.start_dt, 'HH24:MM:SS'),DOC_NAME \r\n"
			+ "from ops$dir.d_emp_roles a left join pla_doc d on d.UPLOADED_DT=a.START_DT and d.pla_offid=a.user_id left join ops$dir.fpo_sites b on a.cus_site=b.site_code left join ops$dir.d_emp c on a.user_id = c.user_id  \r\n"
			+ "where  a.role_name = 'PLA'and a.end_dt is null  and user_account_control='A' order by start_dt desc")
	List<Collection> getLsmdata();
	
	@Query(nativeQuery = true, value = "select a.user_id, c.EMP_NAME, a.cus_site, c.DESIGNATION, c.email_id, c.mobile_no from ops$dir.d_emp_roles a, ops$dir.fpo_sites b, d_emp c \r\n"
			+ "			where a.cus_site = b.site_code and a.user_id = c.user_id and a.role_name = 'PLA' and a.end_dt is null and a.cus_site = :cssite  and user_account_control='A'")
	List<Collection> removelsmdata(@Param("cssite") String cssite);
	 
	@Query(nativeQuery = true, value = "select distinct site_code,site_name,site_status from ops$dir.fpo_site_mgmt where site_name = :sitename")
	List<Collection> getsiteNameHstry(@Param("sitename") String sitename);
	
	@Query(nativeQuery = true, value = "select distinct site_code,site_name,site_status from ops$dir.fpo_site_mgmt ")
	List<Collection> getsiteNameHstryall(@Param("sitename") String sitename);
	
	@Query(nativeQuery = true, value = "select distinct a.cus_site, b.site_name, a.user_id, f.EMP_NAME, a.assign_by, c.EMP_NAME as nsm_Name, to_char(a.assign_dt, 'DD/MM/YYYY'), a.revoked_by, e.EMP_NAME as revoke_name, to_char(a.end_dt, 'DD/MM/YYYY'),a.assign_dt \r\n"
			+ "from  ops$dir.fpo_sites b, ops$dir.d_emp f, ops$dir.d_emp_roles a  left join \r\n"
			+ "(select user_id,EMP_NAME from ops$dir.d_emp ) c  on  c.user_id=a.assign_by left join\r\n"
			+ "(select user_id, EMP_NAME from ops$dir.d_emp ) e on  e.user_id= a.revoked_by \r\n"
			+ " where  role_name= 'PLA' and a.cus_site = :sitename  and user_account_control='A' and a.cus_site = b.site_code and a.user_id = f.user_id order by a.assign_dt desc\r\n"
			+ " "	
		)
	List<Collection> getLsmHstry(@Param("sitename") String sitename);

	
	@Query(nativeQuery = true, value = "select distinct a.cus_site, b.site_name, a.user_id, f.EMP_NAME, a.assign_by, c.EMP_NAME as nsm_Name, to_char(a.assign_dt, 'DD/MM/YYYY'), a.revoked_by, e.EMP_NAME as revoke_name, to_char(a.end_dt, 'DD/MM/YYYY'),a.assign_dt \r\n"
			+ "from  ops$dir.fpo_sites b, ops$dir.d_emp f, ops$dir.d_emp_roles a  left join \r\n"
			+ "(select user_id,EMP_NAME from ops$dir.d_emp ) c  on  c.user_id=a.assign_by left join\r\n"
			+ "(select user_id, EMP_NAME from ops$dir.d_emp ) e on  e.user_id= a.revoked_by \r\n"
			+ " where  role_name= 'PLA'  and a.cus_site = b.site_code  and user_account_control='A' and a.user_id = f.user_id order by a.assign_dt desc\r\n"
			+ " "	
		)
	List<Collection> getLsmHstryall();


	
	/*@Query(nativeQuery = true, value = "select to_char(b.crt_dt, 'DD/MM/YYYY HH24:MI:SS'),b.site_status, b.site_code,a.reason,c.user_id,c.EMP_NAME from ops$dir.oth_status_fposite a,\r\n"
			+ "			(select max(crt_dt) crt_dt, site_status, site_code from ops$dir.oth_status_fposite where site_code = ?1 group by site_status, site_code) b,\r\n"
			+ "            (select user_id, EMP_NAME from ops$dir.d_emp where user_id= ?2) c\r\n"
			+ "			where a.site_code=b.site_code and a.site_status=b.site_status and a.crt_dt=b.crt_dt")
	List<Collection> shwsiteHstry(String siteCde, String offid);*/
	
	/*@Query(nativeQuery = true, value = "select to_char(b.crt_dt, 'DD/MM/YYYY HH24:MI:SS'),b.site_status, b.site_code,a.reason from ops$dir.oth_status_fposite a,\r\n"
			+ "			(select max(crt_dt) crt_dt, site_status, site_code from ops$dir.oth_status_fposite where site_code = ?1 group by site_status, site_code) b\r\n"
			+ "			where a.site_code=b.site_code and a.site_status=b.site_status and a.crt_dt=b.crt_dt")
	List<Collection> shwsiteHstry(String siteCde, String offid);*/
	
	
	@Query(nativeQuery = true, value = "select site_code,to_char(crt_dt,'DD/MM/YYYY HH24:MI:SS') crt_dt,site_status,reason,off_id,emp_name from \r\n"
	+ "(select max(crt_dt) crt_dt,  site_code,site_status, reason,off_id,emp_name from ops$dir.oth_status_fposite a, ops$dir.d_emp b  where a.site_code = :siteCde  and user_account_control='A' and a.off_id=b.user_id group by site_status, site_code,off_id,emp_name,reason order by crt_dt desc) order by crt_dt desc")
	List<Collection> shwsiteHstry(@Param("siteCde") String siteCde);
	
	@Query(nativeQuery = true, value = "select site_code,to_char(crt_dt,'DD/MM/YYYY HH24:MI:SS') crtdtc,site_status,reason,off_id,emp_name,crt_dt from \r\n"
			+ "(select max(crt_dt) crt_dt,  site_code,site_status, reason,off_id,emp_name from ops$dir.oth_status_fposite a, ops$dir.d_emp b  where  a.off_id=b.user_id  and user_account_control='A' group by site_status, site_code,off_id,emp_name,reason order by crt_dt desc) order by crt_dt desc")
			List<Collection> shwsiteHstryall(String siteCde);
	
//	@Query(nativeQuery = true, value = "select EMP_NAME from ops$dir.d_emp where user_id = ?1  and end_dt is null and USER_ACCOUNT_CONTROL='A'") 
//	String getoffcierName(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select site_code from ops$dir.fpo_site_mgmt where site_status!='D'") 
	List<Collection> getaddedsites();
	
	@Query(nativeQuery = true, value = "select COUNT(*) COUNT FROM ops$dir.d_emp_roles WHERE cus_site= :sitename and role_name ='PLA' and end_dt is null")
	Long getLSMsitecdeCunt(@Param("sitename") String sitename);
	
	
	@Query(nativeQuery = true, value = "select user_id from ops$dir.d_emp_roles where cus_site = :sitename and role_name = 'PLA' and end_dt is null")
	String[] listofSSOid(@Param("sitename") String sitename);
	
	@Query(nativeQuery = true, value = "select c.EMP_NAME, c.DESIGNATION, c.mobile_no, c.email_id,a.cus_site from ops$dir.d_emp_roles a, ops$dir.d_emp c \r\n"
			+ "    where a.user_id = c.user_id and a.role_name = 'PLA'  and user_account_control='A' and a.end_dt is null and a.user_id = :SSOID")
	List<Collection> getvalueofUser(@Param("SSOID") String SSOID);
	

	
	//LSM site Hstry
	@Query(nativeQuery = true, value = "select distinct a.cus_site, b.site_name from ops$dir.d_emp_roles a, ops$dir.fpo_sites b where role_name= 'PLA' and a.cus_site <> 'INNSA5' and a.cus_site = b.site_code\r\n"
			+ "")
	List<String> getLsmsiteHstry();

	//Miscellaneous
	
	@Query(nativeQuery = true, value = "select maxassval from ops$dir.ACL_ASSVAL")
	String getthresholdval();

	@Query(nativeQuery = true, value = "select to_char(from_dt, 'DD/MM/YYYY HH24:MI:SS') from ops$dir.ACL_ASSVAL")
	String getassvaldate();

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "Update ops$dir.acl_assval set maxassval=:assmaxamut, created_by=:crtby, from_dt=:date1, reason=:threshldresp")
	void updateassmaxval(@Param("assmaxamut") String assmaxamut, @Param("crtby") String crtby, @Param("date1") Date date1, @Param("threshldresp") String threshldresp);
	
	//LSM role allocation 
	@Query(nativeQuery = true, value = "select user_id, user_id || ' - ' || emp_name from ops$dir.d_emp where fpo_cus_site=:cusSite and user_account_control='A' and end_date is null and (user_id not in (select user_id from ops$dir.d_emp_roles where role_name <>'PLA' and role_name <>'PNA' and end_dt is not null) and user_id not in (select user_id from ops$dir.d_emp_roles where cus_site=:cusSite)) or ( user_id in (select user_id from ops$dir.d_emp_roles a where (role_name='PLA' or role_name='PNA') and end_dt is not null and start_dt=( select max(start_dt) from ops$dir.d_emp_roles where user_id=a.user_id) and cus_site=:cusSite))")
	List<String> getLsmusers(@Param("cusSite") String cusSite);
	
	@Query(nativeQuery = true, value = "select a.cus_site, b.site_name from ops$dir.d_emp_roles a, ops$dir.fpo_sites b where user_id = :sitecde and a.cus_site = b.site_code and a.role_name ='PLA' and a.end_dt is null and ROWNUM <= 1")
	String getLsmSitecde(@Param("sitecde") String sitecde);
	
	//@Query(nativeQuery = true, value = "select user_id from ops$dir.d_emp where fpo_cus_site = ?1 and user_id not in (select user_id from ops$dir.d_emp_roles where role_name <> 'PLA' and cus_site = ?1)")
	@Query(nativeQuery = true, value = "select user_id from ops$dir.d_emp where fpo_cus_site = :Nsmsitecde  and user_account_control='A' and user_id not in (select user_id from ops$dir.d_emp_roles where end_dt is null)")
	String[] getNsasitecd(@Param("Nsmsitecde") String Nsmsitecde);
	
	@Query(nativeQuery = true, value = "select user_id,user_id || ' - ' || emp_name from ops$dir.d_emp where fpo_cus_site = :Nsmsitecde  and user_account_control='A' and user_id not in (select user_id from ops$dir.d_emp_roles where end_dt is null)")
	List<Collection> getNsauserinfo(@Param("Nsmsitecde") String Nsmsitecde);
	
	@Query(nativeQuery = true, value = "SELECT site_code,site_name FROM ops$dir.ooe_sites where site_code not in (select site_code from ops$dir.fpo_sites) and site_code<>'INNSA5' and site_active='Y'")
	List<Collection> getSiteNameSiteCode();
	
}

