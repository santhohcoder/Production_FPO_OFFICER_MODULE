package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.fpo.model.FPO_MAP_SITE;
import com.demo.fpo.model.CIN_INFO;

public interface CININFO_repo extends JpaRepository<CIN_INFO, Long>{

	@Query(nativeQuery = true, value = "SELECT cininfo_seq+1 FROM seq_tab")
 	Long getcinseq();
	
	@Query(nativeQuery = true, value = "SELECT cinarrinfo_seq+1 FROM seq_tab")
 	Long getcinarrseq();
}
