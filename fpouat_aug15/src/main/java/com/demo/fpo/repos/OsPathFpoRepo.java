package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.fpo.model.OsPathFpo;

public interface OsPathFpoRepo extends JpaRepository<OsPathFpo, Long> {

	@Query(nativeQuery = true, value = "select dcall_qry_rply from ospath_fpo")
	String getQryReplyPath();

	@Query(nativeQuery = true, value = "select detained_queue from ospath_fpo")
	String getDetainedQueuePath();

	@Query(nativeQuery = true, value = "select ooc_cancel from ospath_fpo")
	String getOocCancelPath();
	
	@Query(nativeQuery = true, value ="select OTH_DOC from OSPATH_FPO")
	String getothPath();
	
	@Query(nativeQuery = true, value ="select RAISE_COMP_DOC from OSPATH_FPO")
	String getRiseCompDocPath();
}
