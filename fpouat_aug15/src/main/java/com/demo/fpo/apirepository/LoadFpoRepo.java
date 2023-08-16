package com.demo.fpo.apirepository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.Loadmodel;



@Component
public interface LoadFpoRepo extends JpaRepository<Loadmodel, Long> {
	//@Transactional 
	//@Query(nativeQuery = true, value = "SELECT * FROM CUSITM_LOAD where nvl(Success,'N')='N'")
	//@Query(nativeQuery = true, value = "SELECT TOKEN,POST_ORG_CD,CUST_ORG_CD,ITEM_ID,FLOW FROM CUSITM_LOAD")
 //List<consumeCDSAPI.consumeModel.Loadmodel> getAllData();
	
	@Transactional 
	//@Query(nativeQuery = true, value = "SELECT TOKEN,POST_ORG_CD,CUST_ORG_CD,ITEM_ID,FLOW FROM CUSITM_LOAD")
	@Query(nativeQuery = true, value = "SELECT * FROM CUSITM_LOAD where nvl(Success,'N')='N' and fromdt <= (select sysdate from dual) order by ID")
	List<com.demo.fpo.apimodel.Loadmodel> getAllData();
	
	 
	public interface Loadmodel {
	    //Long getid();    
	    String getTOKEN();
	    String getPOST_ORG_CD();
	    String getCUST_ORG_CD();
	    String getITEM_ID();
	    String getFLOW();
	}

	
	@Query(nativeQuery=true, value="select count(*) from articlearr_fpo_info a, ops$dir.fpo_sites b  where article_id=:itemid and site_code=:cusite  and substr(recd_fpo,1,5)=substr(map_code,1,5) ")
    int getcoufpopreead(@Param("itemid") String itemid, @Param("cusite") String cusite);
	
	@Query(nativeQuery=true, value="select count(*) from article_arr_info a, ops$dir.fpo_sites b where article_id=:itemid and site_code=:cusite  and substr(ooe,1,5)=substr(map_code,1,5) ")
    int getcouooepreead(@Param("itemid") String itemid, @Param("cusite") String cusite);
	
	
	@Transactional 
	@Query(nativeQuery = true, value = "SELECT fromdt + 1/48 from cusitm_load where id=(select max(id) from cusitm_load)")
	java.util.Date getfrdt();
	
	@Transactional 
	@Query(nativeQuery = true, value = "SELECT todt + 1/48 from cusitm_load where id=(select max(id) from cusitm_load)")
	java.util.Date gettodt();
	

	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value = "UPDATE CUSITM_LOAD SET SUCCESS = 'Y', LAST_EXECUTED=:sentdt ,CDSRECORDS=:recnt where ID = :id")
	void updateLoad(@Param("id") Long id,@Param("sentdt") Date sentdt,@Param("recnt") long recnt);
	

}
