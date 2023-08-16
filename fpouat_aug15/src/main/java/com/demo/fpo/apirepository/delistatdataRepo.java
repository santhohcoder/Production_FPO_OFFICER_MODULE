package com.demo.fpo.apirepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.Delistatusdata;





@Repository
public interface delistatdataRepo extends JpaRepository<Delistatusdata, Long> {


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "delete from FPO_SPEEDDELISTATUS  where SPEEDPOST_NO = :itemid ")
	void deleteSpeedPost(@Param("itemid") String itemid);

	

}
