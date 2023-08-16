package com.demo.fpo.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.D_EMP;
import com.demo.fpo.model.D_EMP_FPO;

public interface D_EMP_FPOrepo extends JpaRepository<D_EMP_FPO, Long> {
	
//	@Query(nativeQuery = true, value = "select a.user_id,a.cus_site,a.role_name,b.EMP_NAME from ops$dir.d_emp_roles a, ops$dir.d_emp_fpo b where a.user_id=b.user_id and a.user_id=?1 and passwd = ?2 and end_dt is null")
//	List<Collection> getempName(String id,String pwd);
	
	@Query(nativeQuery = true, value = "select a.user_id,a.cus_site,a.role_name,b.EMP_NAME from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id and end_dt is null and USER_ACCOUNT_CONTROL='A'")
	List<Collection> getempName(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null")
	List<String> getmulrole(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select fpo_cus_site from ops$dir.d_emp where user_id=:id and end_date is null and USER_ACCOUNT_CONTROL='A'")
	String getempsite(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select emp_name from ops$dir.d_emp where user_id=:id and fpo_cus_site=:cussite and end_date is null and USER_ACCOUNT_CONTROL='A'")
	String getempname(@Param("id") String id, @Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select count(*) from ops$dir.fpo_sites where site_code=:site")
	int validsite(@Param("site") String site);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles a, ops$dir.d_emp_fpo b where a.user_id=b.user_id and a.user_id=:id and passwd = :pwd")
	List<D_EMP_FPO> getrole(@Param("id") String id,@Param("pwd") String pwd);
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles a, ops$dir.d_emp b where a.user_id=b.user_id and a.user_id=:id ")
	List<D_EMP_FPO> getrole(@Param("id") String id);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid) where disp_group = 1 and b.end_dt is null order by dispord")
	List<Collection> getallroles_a(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid) where disp_group = 2 and b.end_dt is null order by dispord") 
	List<Collection> getallroles_b(@Param("offid") String offid, @Param("cusite") String cusite);
	
//	@Query(nativeQuery = true, value = "select 'images/'||lower(a.role)||'_icon.JPG',a.role newrole,disp_rolenm,decode(b.user_id,:offid,1,0),a.role from ops$dir.fpo_roles a left join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid) where disp_group = 3 and a.site_access = ?2 and b.end_dt is null order by dispord")
//	List<Collection> getallroles_c(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select distinct disp_group from ops$dir.fpo_roles a  join ops$dir.d_emp_roles b on (a.role=b.role_name and b.cus_site=:cusite and b.user_id=:offid and b.end_dt is null)")
	List<String> getroletype(@Param("offid") String offid, @Param("cusite") String cusite);
	
	@Query(nativeQuery = true, value = "select user_id, EMP_NAME, DESIGNATION, fpo_cus_site, sex, to_char(start_date,'DD/MM/YYYY'), to_char(end_date,'DD/MM/YYYY'), mgr_id,address,mobile_no,to_char(dob,'DD/MM/YYYY'),comm_name,email_id,role,user_account_control from ops$dir.d_emp where user_id = :userid  and end_date is null and USER_ACCOUNT_CONTROL='A' ")
	List<Collection> getprofile(@Param("userid") String userid);
	
}
