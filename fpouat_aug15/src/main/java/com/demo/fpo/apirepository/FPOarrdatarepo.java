package com.demo.fpo.apirepository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.FPOarrdata;




@Repository
public interface FPOarrdatarepo extends JpaRepository<FPOarrdata, Long> {
	@Query(nativeQuery = true, value = "SELECT count(*) FROM ARTICLEARR_FPO_INFO where article_id =:id and to_char(recd_dt)=:recvdt and RECD_FPO=:recdfpo and BAG_NO=:bagno")
 	int getarrFPO(@Param("recvdt")Date recvdt, @Param("id") String id, @Param("recdfpo") String recdfpo, @Param("bagno") String bagno);
	
	@Transactional
	@Modifying(clearAutomatically = true)	
    @Query(nativeQuery = true, value = "update ARTICLEARR_FPO_INFO set STATUS='D' where article_id =:id and recd_dt=to_date(:recvdt) and RECD_FPO=:recdfpo and BAG_NO=:bagno")
		void updarrfpoinfo(@Param("recvdt") Date recvdt, @Param("id") String id, @Param("recdfpo") String recdfpo, @Param("bagno") String bagno);
}



