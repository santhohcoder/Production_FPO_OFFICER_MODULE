package com.demo.fpo.apirepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Fpoitemins;



@Repository
public interface FpoiteminsRepo extends JpaRepository<Fpoitemins, Long> {

//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "UPDATE fpo_main SET ITEM_DESC=?2,NETWT=?1 where CIN_NO = ?3")
//	void getmailid(String itemdesc, String id);
	
	
}
