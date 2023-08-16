package com.demo.fpo.repos;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.Asstreport;

@Repository

public interface Asstreportrepost extends JpaRepository<Asstreport, Long>{
	@Query(nativeQuery=true,value="SELECT * FROM fpo_item_det")
	List<Asstreport> getNetwt();
	
	@Query(nativeQuery=true,value="SELECT * FROM fpo_item_det where CIN_NO = :id")
	List<Asstreport> getNetwt(@Param("id") String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="UPDATE fpo_item_det SET ITEM_DESC=:itemdesc,NETWT=:netwt where CIN_NO = :id")
	void getNetwt1(@Param("netwt") float netwt, @Param("itemdesc") String itemdesc, @Param("id") String id); 
	
}
