package com.demo.fpo.apirepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.FpoItemDetothduty;





@Repository
public interface FpoitemdetothdutyRepo extends JpaRepository<FpoItemDetothduty, Long> {
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_ITEM_DET_OTHDUTY where item_no=:id and cin_no=:cinno order by id")
	//List<fposubmititem> findById(@Param("cinno") List<Long> fpoitemList);
	List<FpoItemDetothduty> getAllData(@Param("id") Long id,@Param("cinno") String cinno);
	//List<fposubmititem> findAllById(String cinno);
	//fposubmititem findById(String cinno);
	
}
