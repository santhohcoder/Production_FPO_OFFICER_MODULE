package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.PLA_DOC;

public interface PLA_DOCrepo extends JpaRepository<PLA_DOC, Long> {
	
	@Query(nativeQuery = true, value = "select substr(doc_path, 24) filename from ops$dir.d_emp_roles a, ops$dir.fpo_sites b, ops$dir.d_emp c ,pla_doc d where a.cus_site = b.site_code  and user_account_control='A' and a.user_id = c.user_id and a.role_name = 'PLA'and d.UPLOADED_DT=a.START_DT and a.end_dt is null and d.PLA_OFFID=:offid order by start_dt desc")
	String findByssoid(@Param("offid") String offid);


}
