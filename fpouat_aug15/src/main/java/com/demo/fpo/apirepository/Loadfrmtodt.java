package com.demo.fpo.apirepository;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.fromtodt;


@Repository
public interface Loadfrmtodt extends JpaRepository<fromtodt, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM ARTICLE_ARR_INFO_REQ where nvl(Success,'N')='N'")
	List<fromtodt> getAllData();
	
	
	@Modifying
	@Transactional 
	 @Query(nativeQuery = true, value = "UPDATE ARTICLE_ARR_INFO_REQ SET SUCCESS = 'Y', UPDATEDTIME=:sentdt, RECORDCOUNT=:recnt where ID = :id")
	void updatearrreq(@Param("id") Long id,@Param("sentdt") Date sentdt,@Param("recnt") long recnt);
}
