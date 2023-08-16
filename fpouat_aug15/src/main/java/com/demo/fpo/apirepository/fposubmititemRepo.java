package com.demo.fpo.apirepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.fposubmititem;






@Repository
public interface fposubmititemRepo extends JpaRepository<fposubmititem, Long> {

	
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "UPDATE fpo_main SET ITEM_DESC=?2,NETWT=?1 where CIN_NO = ?3")
//	void getmailid(String itemdesc, String id);
	public String getBCD(String CTH);
	public String getIGST(String CTH);
	public String getSWS(String CTH);
	public String getcurrt(String currt);
}
