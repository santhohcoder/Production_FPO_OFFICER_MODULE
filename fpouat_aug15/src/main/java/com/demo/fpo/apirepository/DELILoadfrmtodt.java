package com.demo.fpo.apirepository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.DELIfromtodt;

@Repository
public interface DELILoadfrmtodt extends JpaRepository<DELIfromtodt, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_DELI_REQ where nvl(Success,'N')='N' and fromdt <= (select sysdate from dual) ")
	List<DELIfromtodt> getfrmtodt();
	
	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value = "UPDATE FPO_DELI_REQ SET SUCCESS = 'Y', UPDATEDTIME=:sentdt, RECORDCOUNT=:recnt where ID = :id")
	void updatedelireq(@Param("id") Long id,@Param("sentdt") Date sentdt,@Param("recnt") Long recnt);
}
