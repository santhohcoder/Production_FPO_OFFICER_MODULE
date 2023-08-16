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

import com.demo.fpo.NsmLsmModel.D_EMP_ROLES;
import com.demo.fpo.model.D_EMP;




@Repository
public interface D_EMP_ROLErepo_NSM  extends JpaRepository<D_EMP_ROLES, Long> {
	
	@Query(nativeQuery = true, value = "select count(*)  from ops$dir.d_emp  where  user_id= :id and end_dt is null and USER_ACCOUNT_CONTROL='A' ")
	Long userfou(String id);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id=:userid and cus_site=:cusite")
	List<D_EMP_ROLES> getemprolesdata(@Param("userid") String userid, @Param("cusite") String cusite );
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id=:userid")
	List<D_EMP_ROLES> getemprolesdata(@Param("userid") String userid);
	
	/*@Query(nativeQuery = true, value = "select a.user_id, b.EMP_NAME, a.role_name, a.cus_site from d_emp_roles a, d_emp_fpo b where a.user_id=b.user_id and a.user_id= ?1 and passwd = ?2")
	List<Object> getcusSite(String id, String pwd);*/
	
	@Query(nativeQuery = true, value = "select role_name from ops$dir.d_emp_roles where user_id= :userid and end_dt is null")
	String getrolenm(@Param("userid") String userid);

	@Query(nativeQuery = true, value = "select cus_site from ops$dir.d_emp_roles where user_id= :userid and end_dt is null and ROWNUM <= 1 and cus_site=(select fpo_cus_site from ops$dir.d_emp where user_id=:userid and end_date is null  and user_account_control='A')")
	String getcussite(@Param("userid") String userid);
	
	@Query(nativeQuery = true, value ="select EMP_NAME from ops$dir.d_emp where user_id= :userid  and end_date is null and USER_ACCOUNT_CONTROL='A'") 
	String getempname(@Param("userid") String usrid);
	
	@Query(nativeQuery = true, value ="select a.cus_site, b.site_state from ops$dir.d_emp_roles a, ops$dir.fpo_sites b where user_id = :userid and a.cus_site = b.site_code and a.end_dt is null and ROWNUM <= 1") 
	String getNsmsiteNme(@Param("userid") String usrid);
	
	@Query(nativeQuery = true, value = "SELECT * FROM ops$dir.d_emp_roles where user_id=:userid and role_name=:role and end_dt is null")
	List<D_EMP_ROLES> getalldata(@Param("userid") String userid, @Param("role") String role);
	
	
	  
	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from d_emp_roles where user_id= ?1") D_EMP_ROLES_NSM getdata(String
	 * id);
	 */
	
	// LSM Role Allocation
	
	//@Query(nativeQuery = true, value = "select cus_site from ops$dir.d_emp_roles where user_id= :userid and end_dt is null and ROWNUM <= 1")
	@Query(nativeQuery = true, value = "select cus_site from ops$dir.d_emp_roles where user_id= :userid and end_dt is null and ROWNUM <= 1 and cus_site=(select fpo_cus_site from ops$dir.d_emp where user_id=:userid and end_date is null  and user_account_control='A')")
    String LsmSiteCde(@Param("userid") String userid);
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select count(*)  from d_emp_fpo  where  user_id= ?1 and passwd = ?2") String
	 * userid(String id, String pwd);
	 */

	@Query(nativeQuery = true, value = "select user_id, EMP_NAME, DESIGNATION, fpo_cus_site, sex, to_char(start_date,'DD/MM/YYYY'), to_char(end_date,'DD/MM/YYYY'), mgr_id,address,mobile_no,to_char(dob,'DD/MM/YYYY'),comm_name,email_id,role,user_account_control from ops$dir.d_emp where user_id = :userid  and end_date is null and USER_ACCOUNT_CONTROL='A' and user_id not in (select user_id from ops$dir.d_emp_roles where end_dt is null)")
	List<Collection> LsmuserId(@Param("userid") String userid);
	
	@Query(nativeQuery = true, value = "select user_id, EMP_NAME, DESIGNATION, fpo_cus_site, sex, to_char(start_date,'DD/MM/YYYY'), to_char(end_date,'DD/MM/YYYY'), mgr_id,address,mobile_no,to_char(dob,'DD/MM/YYYY'),comm_name,email_id,role,user_account_control from ops$dir.d_emp where user_id = :userid  and end_date is null and USER_ACCOUNT_CONTROL='A' and user_id  in (select user_id from ops$dir.d_emp_roles where end_dt is null)")
	List<Collection> editroles(@Param("userid") String userid);
	
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "select role_name from d_emp_roles where user_id = ?1 and role_name not in 'LSM' and role_name not in 'NSM'"
	 * ) List<Collection> getroledata(String userid);
	 */
	
	//Get Assigned role and mailclas
	@Query(nativeQuery = true, value = "select distinct user_id, user_id || ' - ' || emp_name from ops$dir.d_emp where fpo_cus_site <> 'INNSA5' and user_id in ( select user_id from ops$dir.d_emp_roles where cus_site = :sitecde and role_name not in 'PLA' and role_name not in 'PNA' and end_dt is null) and user_account_control='A' ")
	List<Collection> getassid(@Param("sitecde") String sitecde);
	
	@Query(nativeQuery = true, value = "select distinct user_id, user_id || ' - ' || emp_name  from ops$dir.d_emp where fpo_cus_site = 'INNSA5' and user_id in ( select user_id from ops$dir.d_emp_roles where  end_dt is null) and user_account_control='A' ")
	List<Collection> getNsmusreditinfo();
	
	@Query(nativeQuery = true, value = "select count(*)  from ops$dir.d_emp where fpo_cus_site = 'INNSA5' and user_id in ( select user_id from ops$dir.d_emp_roles where  end_dt is null and role_name in ('PAO', 'PIN', 'PSU', 'PBS')) and user_account_control='A'  and end_date is null and user_id=:userid" )
	int getanytransrole(@Param("userid") String userid );
	
	@Query(nativeQuery = true, value = "select count(*)  from ops$dir.d_emp where fpo_cus_site = :sitecde and user_id in ( select user_id from ops$dir.d_emp_roles where  end_dt is null and role_name in ('PAO', 'PIN', 'PSU', 'PBS')) and user_account_control='A'  and end_date is null and user_id=:userid" )
	int getanyothtransrole(@Param("userid") String userid,@Param("sitecde") String sitecde );
	
	
	
	@Query(nativeQuery = true, value = "select role_name,mail_class_cd from ops$dir.d_emp_roles where cus_site = :sitecde and user_id= :userid  and end_dt is null")
	List<Collection> getassignedroles(@Param("sitecde") String sitecde, @Param("userid") String userid );
	
	//Nsmgetassigned role
		@Query(nativeQuery = true, value = "select role_name from ops$dir.d_emp_roles where cus_site = :sitecde and user_id= :userid and end_dt is null")
		List<Collection> nsmAssignedRole(@Param("sitecde") String sitecde, @Param("userid") String userid );
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id = :sitecde and cus_site = :userid")
	List<Collection> getUpdtroles(@Param("sitecde") String sitecde, @Param("userid") String userid );
	
	// updating roles
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id = :sitecde and cus_site =:cs and end_dt is null")
	List<D_EMP_ROLES> getolddata(@Param("sitecde") String sitecde, @Param("cs") String cs);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id = :cs and cus_site <>'INNSA5' and end_dt is null")
	List<D_EMP_ROLES> getolddataothPNA(@Param("cs") String cs);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles where user_id = :sitecde and cus_site=:cs and end_dt is null")
	List<D_EMP_ROLES> getolddataothPLA(@Param("sitecde") String sitecde, @Param("cs") String cs);
	
	// Delete old record form d_emp_roles
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.d_emp_roles where cus_site =:delcd and user_id = :cs and end_dt is null")
	void Deleteoldsite(@Param("delcd") String delcd, @Param("cs") String cs);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.d_emp_roles where  user_id = :cs")
	void Deleteoldsite( @Param("cs") String cs);

	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.d_emp_roles where cus_site<>'INNSA5' and user_id = :cs and end_dt is null")
	void delothPNA(@Param("cs") String cs);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.d_emp_roles where cus_site=:cs and user_id = :delcd and end_dt is null and role_name in ('PAO','PBS','PIN','PSU')")
	void delothPLA(@Param("delcd") String delcd, @Param("cs") String cs);
	
	
	//view active Lsm users
	@Query(nativeQuery = true, value = "(select distinct a.EMP_NAME , b.user_id, b.assign_by, b.mail_class_cd, to_char( b.assign_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.d_emp_roles where user_id =:usid and cus_site = :sitecde and end_dt is null and role_name not in ('PNA', 'PLA','PAO','PSU','PIN') ) as role_name  from ops$dir.d_emp a, ops$dir.d_emp_roles b where b.user_id = :usid and b.cus_site = :sitecde and a.user_id = b.user_id and end_dt is null and role_name not in ('PNA', 'PLA','PAO','PSU','PIN')) \r\n"
			                           +" union \r\n" 
			                           + "(select distinct a.EMP_NAME , b.user_id, b.assign_by, b.mail_class_cd, to_char( b.assign_dt, 'DD/MM/YYYY'), role_name from ops$dir.d_emp a, ops$dir.d_emp_roles b where b.user_id =:usid and  b.cus_site = :sitecde and a.user_id = b.user_id and end_dt is null and role_name in ('PAO','PSU','PIN')) order by 6,5")
	List<Collection> getLsmactveUsr(@Param("sitecde") String sitecde, @Param("usid") String usid);
	
	//view All active Lsm users
		@Query(nativeQuery = true, value = "(select  distinct a.EMP_NAME , b.user_id, b.assign_by,b.mail_class_cd, to_char( b.assign_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.d_emp_roles where user_id = b.user_id and cus_site = :sitecde and end_dt is null and role_name not in ('PNA', 'PLA' ,'PAO','PSU','PIN') ) as role_name \r\n"
				+ "from ops$dir.d_emp a, ops$dir.d_emp_roles b where  b.cus_site = :sitecde and a.user_id = b.user_id and end_dt is null  and user_account_control='A' and role_name not in ('PNA', 'PLA','PAO','PSU','PIN') ) \r\n"
				+ " union \r\n"
				+ "(select  distinct a.EMP_NAME , b.user_id, b.assign_by,mail_class_cd,to_char( b.assign_dt, 'DD/MM/YYYY'),role_name \r\n"
				+" from ops$dir.d_emp a, ops$dir.d_emp_roles b where  b.cus_site = :sitecde and a.user_id = b.user_id and end_dt is null  and user_account_control='A' and role_name in ('PAO','PSU','PIN')) order by 6,5")
		List<Collection> getallLsmactveUsr(@Param("sitecde") String sitecde);
	
	//Remove all roles to the User
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "Delete from ops$dir.d_emp_roles where cus_site =:cs and user_id = :usid")
	void remveroles(@Param("usid") String usid, @Param("cs") String cs);
	
	//view active Lsm users
		@Query(nativeQuery = true, value = "select distinct a.user_id,  a.user_id || ' - ' || emp_name from ops$dir.d_emp_roles a, ops$dir.d_emp b where end_dt is null and a.role_name not in ('PNA', 'PLA') and cus_site = :sitecde and a.user_id=b.user_id  and user_account_control='A'")
		List<Collection> getLsmuserList(@Param("sitecde") String sitecde);
		
		//SHow revoked data in edit_roles
		@Query(nativeQuery = true, value = "select distinct a.user_id  ,  a.user_id || ' - ' || emp_name from ops$dir.edit_d_emp_roles a, ops$dir.d_emp s where role_name not in ('PNA', 'PLA') and cus_site = :sitecde and a.user_id=s.user_id")
		List<Collection> getrevokedData(@Param("sitecde") String sitecde);
		
		//View Lsm user Histry
	//	@Query(nativeQuery = true, value = "select distinct a.EMP_NAME , b.user_id, b.assign_by, to_char( b.assign_dt, 'DD/MM/YYYY'), b.revoked_by, to_char( b.end_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.edit_d_emp_roles where user_id = :usid and cus_site = :sitecde \r\n"
	//			+ "and role_name not in ('PNA', 'PLA') and assign_dt=b.assign_dt ) as role_name  from ops$dir.d_emp a, ops$dir.edit_d_emp_roles b where b.user_id = :usid and b.cus_site = :sitecde and a.user_id = b.user_id  and user_account_control='A' and role_name not in ('PNA', 'PLA') ")
	//	List<Collection> getLsmuserHstry(@Param("sitecde") String sitecde, @Param("usid") String usid);
		
		@Query(nativeQuery = true, value = "select distinct a.EMP_NAME , b.user_id, b.assign_by, to_char( b.assign_dt, 'DD/MM/YYYY'), b.revoked_by, to_char( b.end_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.edit_d_emp_roles where user_id = :usid and cus_site = :sitecde \r\n"
				+ "and role_name not in ('PNA', 'PLA') and assign_dt=b.assign_dt ) as role_name  from ops$dir.d_emp a, ops$dir.edit_d_emp_roles b where b.user_id = :usid and b.cus_site = :sitecde and a.user_id = b.user_id  and user_account_control='A' and role_name not in ('PNA', 'PLA') ORDER BY TO_DATE(TO_CHAR(b.assign_dt, 'DD/MM/YYYY'), 'DD/MM/YYYY') DESC ")
		List<Collection> getLsmuserHstry(@Param("sitecde") String sitecde, @Param("usid") String usid);
		
		
		//View Lsm user Histry
		//		@Query(nativeQuery = true, value = "select distinct a.EMP_NAME , b.user_id, b.assign_by, to_char( b.assign_dt, 'DD/MM/YYYY'), b.revoked_by, to_char( b.end_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.edit_d_emp_roles where  cus_site = :sitecde \r\n"
		//				+ "and role_name not in ('PNA', 'PLA') and assign_dt=b.assign_dt ) as role_name  from ops$dir.d_emp a, ops$dir.edit_d_emp_roles b where  b.cus_site = :sitecde  and user_account_control='A' and role_name not in ('PNA', 'PLA') ")
		//		List<Collection> getallLsmuserHstry(@Param("sitecde") String sitecde);
		
		@Query(nativeQuery = true, value = "select distinct a.EMP_NAME , b.user_id, b.assign_by, to_char( b.assign_dt, 'DD/MM/YYYY'), b.revoked_by, to_char( b.end_dt, 'DD/MM/YYYY'),(select listagg(role_name,', ') within group(order by ID)as rlname FROM ops$dir.edit_d_emp_roles where  cus_site = :sitecde \r\n"
				+ "and role_name not in ('PNA', 'PLA') and assign_dt=b.assign_dt ) as role_name  from ops$dir.d_emp a, ops$dir.edit_d_emp_roles b where  b.cus_site = :sitecde  and user_account_control='A' and role_name not in ('PNA', 'PLA') ORDER BY TO_DATE(TO_CHAR(b.assign_dt, 'DD/MM/YYYY'), 'DD/MM/YYYY') DESC ")
		List<Collection> getallLsmuserHstry(@Param("sitecde") String sitecde);		
				
	
		// Push Article from 1 site to another site
		
		@Query(nativeQuery = true, value = "select cus_site from fpo_main where item_id = :articleid ")
		String getarticleCussite(@Param("articleid") String articleid);
		
		@Query(nativeQuery = true, value = " SELECT site_code FROM ops$dir.fpo_site_mgmt where site_code not in :articleid and site_status = 'Y'")
		List<Collection> pushsiteCode(@Param("articleid") String articleid);
		
		@Transactional
		@Modifying 
		@Query(nativeQuery = true, value = " update ops$dir.d_emp_roles set mobile_no = :mblnum, email_id = :mailid where user_id = :usrid")
		void updatedetails(@Param("usrid") String usrid, @Param("mblnum") String mblnum, @Param("mailid") String mailid );
		
		
		@Query(nativeQuery = true, value = "select site_code,site_code || ' - ' || site_name from ops$dir.fpo_sites where site_code not in 'INNSA5' and site_active='Y' ")
		List<Collection> getsitecde();
		
		@Query(nativeQuery = true, value = "select reas_cde, reas_desc from reallocation_reas")
		List<String> getreallocationdesc();
			 
		@Query(nativeQuery = true, value = "select role_name from ops$dir.d_emp_roles where user_id = :ssoid")
		List<String> getrolename(@Param("ssoid") String ssoid);
		
		@Query(nativeQuery = true, value = "select mail_class_cd from ops$dir.d_emp_roles where user_id = :ssoid and role_name = :rolenme")
		List<String> getmailclass(@Param("ssoid") String ssoid, String rolenme);
		
		// Replace Reallocation
		
		@Transactional
		@Modifying 
		@Query(nativeQuery = true, value = "update fpo_main a set a.off_id = :tossid where exists (select 1 from fpo_status b where a.item_id = b.item_id and a.off_id = :frmssid and a.role= :role and a.mail_class_cd= :mailcls and b.ooc_dt is null)")
		void replaceoffid(@Param("role") String role, @Param("frmssid") String frmssid, @Param("tossid") String tossid, @Param("mailcls") String mailcls);
		
		@Transactional
		@Modifying 
		@Query(nativeQuery = true, value = "update fpo_main a set a.off_id = :tossid where exists (select 1 from fpo_status b where a.item_id = b.item_id and a.off_id = :frmssid and a.role= :role and a.mail_class_cd= :mailcls and a.item_id = :specificid and b.ooc_dt is null)")
		void specificid(@Param("role") String role,@Param("frmssid") String frmssid, @Param("tossid") String tossid, @Param("mailcls") String mailcls, @Param("specificid") String specificid);
		
		//Pincode Change
		
		//Get Cusite
		/*@Query(nativeQuery = true, value = "select distinct customs_fpo_site_code, port_cd||' - ' || site_id from ops$dir.pdi_pincode_mapping_india a, ops$dir.d_globl_nonedi b where trim(a.customs_fpo_site_code)=trim(b.port_cd)")
		List<Collection> getCuSite();*/
		
		@Query(nativeQuery = true, value = "select distinct customs_fpo_site_code, site_code||' - ' || site_name from ops$dir.pdi_pincode_mapping_india a, ops$dir.ooe_sites b where trim(a.customs_fpo_site_code)=trim(b.site_code)")
		List<Collection> getCuSite();
		
		/*
		 * @Query(nativeQuery = true, value =
		 * "select state_name, inbound_start_pincode, inbound_end_pincode, fpo_destn, customs_fpo_site_code from pdi_pincode_mapping_india"
		 * ) List<Collection> getallpincdemap(String cs);
		 */
		
		@Query(nativeQuery = true, value = "select state_name, inbound_start_pincode, inbound_end_pincode, fpo_destn, customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india where customs_fpo_site_code = :cs")
		List<Collection> getpincdemap(@Param("cs") String cs);
		
		@Query(nativeQuery = true, value = "select state_name, inbound_start_pincode, inbound_end_pincode, fpo_destn, customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india ")
		List<Collection> getpincdemapall(@Param("cs") String cs);
		
		//Get pincode Mapped Cusite
		@Query(nativeQuery = true, value = "select state_name, customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india where inbound_start_pincode = :pin or inbound_end_pincode = :pin")
		List<Collection> getmapSite(@Param("pin") String pin);
		
		@Query(nativeQuery = true, value = "select circle_name,region_name,division_name,office_name,pincode,district,state_name from ops$dir.pdi_pincodes where state_name=:stnm")
		List<Collection> getpincodes(@Param("stnm") String stnm);
		
		@Query(nativeQuery = true, value = "select distinct(state_name) from ops$dir.pdi_pincodes")
		List<Collection> getstnm_pincodes();
		
		@Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (ORDER BY cntry_nm) id,cntry_cd,cntry_nm from ops$dir.D_CNTRY_CD order by 3")
		List<Collection> getcntrycd();
		
		@Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (ORDER BY cntry_cd) id,curr_cd,curr_desc,cntry_cd from ops$dir.D_CURR_CD order by 4")
		List<Collection> getcurrcd();
		
		@Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (ORDER BY impc_code) id,impc_code,impc_name,impc_code_short,post_org_name from ops$dir.pdi_ooe_codes order by 2")
		List<Collection> getooecd();
		
		@Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (ORDER BY site_code) id,site_code,site_name from ops$dir.ooe_sites where site_code<>'INNSA5' order by 3")
		List<Collection> getfposites();
		
		@Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (ORDER BY state_name) id,max(state_code),state_name from ops$dir.D_STATE_MAP group by state_name order by 3")
		List<Collection> getstcd();
		
		//Get Unmapped Site
		@Query(nativeQuery = true, value = "select distinct customs_fpo_site_code , site_code||' - ' || site_name from ops$dir.pdi_pincode_mapping_india a, ops$dir.ooe_sites b where trim(a.customs_fpo_site_code)=trim(b.site_code) and  customs_fpo_site_code not in :pin")
		List<Collection> getUnmapSite(@Param("pin") String pin);
		
		// 
		@Query(nativeQuery = true, value = "select state_name,customs_fpo_site_code,inbound_start_pincode,inbound_end_pincode from ops$dir.pdi_pincode_mapping_india where :strtpin >= inbound_start_pincode and :endpin <= inbound_end_pincode  and :strtpin <= :endpin")
		List<Collection> rangepincode(@Param("strtpin") String strtpin, @Param("endpin") String endpin);
		
		@Query(nativeQuery = true, value = "select distinct state_name from ops$dir.pdi_pincode_mapping_india where state_name not in 'Bihar +Jharkhand' order by state_name")
		String[] getnewstate();
		
		@Query(nativeQuery = true, value = "select inbound_start_pincode, inbound_end_pincode, customs_fpo_site_code from ops$dir.pdi_pincode_mapping_india where state_name = :stNm")
		List<Collection> listofpincode(@Param("stNm") String stNm);
		
       //Update Site Details
		
		@Query(nativeQuery = true, value = "select * from ops$dir.fpo_site_info where fpo_site = :cusSite")
		Object siteDetails(@Param("cusSite") String cusSite);


		@Transactional
		@Modifying 
		@Query(nativeQuery = true, value = "update fpo_main a set a.CLR_SITE=:clrsite where a.item_id = :article_id ")
		void updateClrSite(@Param("clrsite") String clrsite, @Param("article_id") String article_id);
		
		@Query(nativeQuery=true, value = "select site_name from ops$dir.fpo_sites where site_code=:cusite")
		String getsitename(@Param("cusite") String cusite);
		
		
		@Query(nativeQuery=true, value = "select (substr(fpo_code,1,1)||to_char(to_number(substr(fpo_code,2,1))+1))  from ops$dir.pdi_pincode_mapping_india where CUSTOMS_FPO_SITE_CODE=:cusite and id=(select max(id) from ops$dir.pdi_pincode_mapping_india  where CUSTOMS_FPO_SITE_CODE=:cusite)")
	    String getfpocodenew(@Param("cusite") String cusite);
		
		@Query(nativeQuery=true, value = "select fpo_Code  from ops$dir.pdi_pincode_mapping_india where CUSTOMS_FPO_SITE_CODE=:cusite and  inbound_start_pincode=:stpin and inbound_end_pincode=:enpin")
	    String getfpocode(@Param("cusite") String cusite,@Param("stpin") String stpin, @Param("enpin")  String enpin);
		
//		demproleNSM.updPINMAP(insertdata.getStart_pincode(),insertdata.getEnd_pincode(),insertdata.getNew_state(),insertdata.getNew_map_cussite(),insertdata.getNew_map_cussite().substring(0,5),demproleNSM.getfpocode(insertdata.getNew_map_cussite()));
				
		@Transactional
		@Modifying 
		@Query(nativeQuery = true, value = "update pdi_pincode_mapping_india set state_name=:stnm,inbound_start_pincode=:stpin,inbound_end_pincode=:enpin,fpo_destn=:destn,postal_site_code=:pcusite,customs_fpo_site_code=:cusite where fpo_code = :fpocode ")
		void updPINMAP(String fpocode,String stnm,@Param("stpin") String stpin,@Param("enpin") String enpin, @Param("destn") String destn,@Param("pcusite") String pcusite,@Param("cusite") String cusite);
		
		
}
