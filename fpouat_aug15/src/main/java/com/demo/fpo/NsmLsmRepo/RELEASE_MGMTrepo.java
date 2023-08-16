package com.demo.fpo.NsmLsmRepo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.fpo.NsmLsmModel.RELEASE_MGMT;

public interface RELEASE_MGMTrepo extends JpaRepository<RELEASE_MGMT, Long> {

	
	@Query(nativeQuery = true, value = "select * from release_mgmt order by upd_dt desc")
	List<String>  getreleasemgmtallval();
	
	@Query(nativeQuery = true, value = "select appname, modname, rolename,year, month, version, upd_data, to_char(upd_dt, 'DD/MM/YYYY HH24:MI:SS') from release_mgmt order by  to_char(upd_dt, 'DD/MM/YYYY HH24:MI:SS') desc")
	List<Collection>  getreleaseMgmt();

}
