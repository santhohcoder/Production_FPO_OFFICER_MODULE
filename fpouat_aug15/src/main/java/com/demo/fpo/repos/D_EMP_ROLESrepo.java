package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.D_EMP;
import com.demo.fpo.model.D_EMP_ROLES;

public interface D_EMP_ROLESrepo extends JpaRepository<D_EMP_ROLES, Long> {
	
	@Query(nativeQuery = true, value = "select * from ops$dir.d_emp_roles  where user_id= ( select user_id from ops$dir.d_emp where user_id=:id and end_date is null and user_account_control='A') and cus_site=(select fpo_cus_site from ops$dir.d_emp where user_id=:id and end_date is null and user_account_control='A') and user_id=:id and end_dt is null")
	List<D_EMP_ROLES> getcusSite(@Param("id") String id);

	@Query(nativeQuery = true, value = "SELECT DISTINCT role_name FROM ops$dir.D_emp_roles where user_id=:offid")
	List<String>getRoles(@Param("offid")String offid);

	
}
 	