package com.demo.fpo.apirepository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.Cusrspsent;




public interface CusrspsentRepo  extends JpaRepository<Cusrspsent, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM CUSRSP_SENT where sent_DT is null")
 	List<Cusrspsent> getcusrsp();
	
	@Modifying
	@Transactional
	 @Query(nativeQuery = true, value = "UPDATE CUSRSP_SENT SET SENT_DT = :sentdt where ID = :id and cin_no=:cinno")
	void updatecusrsp(@Param("id") Long id, @Param("sentdt") Date sentdt, @Param("cinno") String cinno);
}


