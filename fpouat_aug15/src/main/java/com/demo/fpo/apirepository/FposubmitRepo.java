package com.demo.fpo.apirepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Fposubmit;


//import consumeCDSAPI.consumeModel.Loadmodel;

@Repository
public interface FposubmitRepo extends JpaRepository<Fposubmit, Long> {

	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_MAIN where cin_no=:cinno")
	List<Fposubmit> findById(@Param("cinno") String cinno);
	
	

	public String getFpoSeq();
	public String getOOESeq();
	public String getfponame(String wherefldName, String CondfldName);
	public String getfpolikename(String wherefldName1, String wherefldName2);
	public String getfpolikecitystname(String fldnm);
}


