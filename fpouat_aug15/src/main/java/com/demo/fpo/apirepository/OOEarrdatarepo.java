package com.demo.fpo.apirepository;

//import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.OOEarrdata;




@Repository
public interface OOEarrdatarepo extends JpaRepository<OOEarrdata, Long> {
	//@Query(nativeQuery = true, value = "SELECT count(*) FROM ARTICLE_ARR_INFO where article_id = ?2 and BOOKING_DT=?1 and OOE=?3 and RECP_ID=?4 and RECD_EVENT_DT=?5")
	@Query(nativeQuery = true, value = "SELECT count(*) FROM ARTICLE_ARR_INFO where article_id = :itemid and RECP_ID=:recpid ")
	//	int getarrOOE(Date bookdt,String id,String ooe,String recpid,Date recvdt);
	int getarrOOE(@Param("itemid") String itemid, @Param("recpid") String recpid);
	
	@Query(nativeQuery = true, value = "SELECT * FROM ARTICLE_ARR_INFO where article_id = :itemid and RECP_ID=:recpid ")
	//	int getarrOOE(Date bookdt,String id,String ooe,String recpid,Date recvdt);
	OOEarrdata getallarrOOE(@Param("itemid") String itemid, @Param("recpid") String recpid);
	
	@Transactional
	@Modifying(clearAutomatically = true)
		@Query(nativeQuery = true, value = "update ARTICLE_ARR_INFO set STATUS='D' where article_id =:itemid and RECP_ID=:recpid")
	
	void updarrooeinfo(@Param("itemid") String itemid, @Param("recpid") String recpid);	
	
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
		@Query(nativeQuery = true, value = "update seq_tab set cinarrinfo_seq=cinarrinfo_seq+1")	
	void updcinarrinfo();	
}
