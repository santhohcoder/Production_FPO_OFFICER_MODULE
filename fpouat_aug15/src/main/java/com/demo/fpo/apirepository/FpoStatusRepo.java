package com.demo.fpo.apirepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.Fpoqrydeci;
import com.demo.fpo.model.FpoStatus;



@Repository
public interface FpoStatusRepo extends JpaRepository<FpoStatus, Long>{

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_status set ARR_INDIA_DT=:gdsrcvdt where item_id =:id")
	void updrcvdtooestatus(@Param("gdsrcvdt") Date gdsrcvdt, @Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_status set ARR_FPO_DT=:gdsrcvdt where item_id =:id")
	void updrcvdtfpostatus(@Param("gdsrcvdt") Date gdsrcvdt, @Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT count(*) FROM fpo_status where item_id = :id")
 	int getfpostat(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_status set delvy_dt=:deldt, delvy_id='POSTMSG' where item_id =:id")
	void upddelvstatus(@Param("deldt") Date deldt, @Param("id") String id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM fpo_status where item_id = :itemId and apr_dt is not null")
	List<FpoStatus> geteadDetail(@Param("itemId") String itemId);	
	
	@Query(nativeQuery = true, value = "SELECT * FROM fpo_status where item_id = :itemId and acl_dt is not null")
	List<FpoStatus> geteadDetailacl(@Param("itemId") String itemId);
	
	@Query(nativeQuery = true, value = "SELECT cin_no,ass_dt from fpo_status where cin_no=:id" )
	List<Fpoqrydeci> getdeciqry(@Param("id") Long id);
	
	@Query(nativeQuery = true, value = "select id from fpo_status where cin_no=:cinno and cusitm_dt is not null")
	Long getIdFpoStatus(@Param("cinno") String cinno);

	@Query(nativeQuery = true, value = "SELECT * FROM fpo_status where item_id = :itemId and ooc_dt is not null")
	List<FpoStatus> getOocDetail(@Param("itemId") String itemId);
		
}
