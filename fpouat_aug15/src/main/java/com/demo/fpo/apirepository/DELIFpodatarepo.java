package com.demo.fpo.apirepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.DELIfpodata;




@Repository
public interface DELIFpodatarepo extends JpaRepository<DELIfpodata, Long> {
	@Query(nativeQuery = true, value = "SELECT count(*) FROM fpo_delivery where item_id = :itemid")
	//	int getarrOOE(Date bookdt,String id,String ooe,String recpid,Date recvdt);
	int getDeli(@Param("itemid") String itemid);
}



