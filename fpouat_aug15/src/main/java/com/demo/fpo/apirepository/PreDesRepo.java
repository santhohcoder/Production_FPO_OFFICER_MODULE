package com.demo.fpo.apirepository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.apimodel.FPOfromtodt;
import com.demo.fpo.apimodel.Predes_fpo_Req;

public interface PreDesRepo extends JpaRepository<Predes_fpo_Req, Long> {
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM PREDES_FPO_REQ where nvl(Success,'N')='N' and fromdt <= (select sysdate from dual) ")
	List<Predes_fpo_Req> getAllData();
	
	
	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value = "UPDATE PREDES_FPO_REQ SET SUCCESS = 'Y', UPDATEDTIME=:sentdt, RECORDCOUNT=:recnt where ID = :id")
	void updatearrreq(@Param("id") Long id,@Param("sentdt") Date sentdt,@Param("recnt") Long recnt);



}
