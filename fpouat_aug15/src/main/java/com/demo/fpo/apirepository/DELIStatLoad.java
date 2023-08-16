package com.demo.fpo.apirepository;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.delistatLoad;


@Repository
public interface DELIStatLoad extends JpaRepository<delistatLoad, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_DELISTAT_REQ")
	List<delistatLoad> getspeedno();

}
