package com.demo.fpo.apirepository;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.OOEfromtodt;



@Repository
public interface OOELoadfrmtodt extends JpaRepository<OOEfromtodt, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM ARTICLEARR_OOE_REQ where nvl(Success,'N')='N' and fromdt <= (select sysdate from dual) ")
	List<OOEfromtodt> getAllData();
	
	
	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value = "UPDATE ARTICLEARR_OOE_REQ SET SUCCESS = 'Y', UPDATEDTIME=:sentdt, RECORDCOUNT=:recnt, DUPLREC=:dupl where ID = :id")
	void updatearrreq(@Param("id") Long id,@Param("sentdt") Date sentdt,@Param("recnt") Long recnt, @Param("dupl") Long dupl);
}
