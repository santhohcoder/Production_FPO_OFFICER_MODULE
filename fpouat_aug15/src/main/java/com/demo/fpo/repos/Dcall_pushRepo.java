package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.Push_dcall;


public interface Dcall_pushRepo extends JpaRepository<Push_dcall, Long> {

	
	@Query(nativeQuery = true, value = "select * from push_dcall where DCALL_NO = :dcall_no and (EMAIL_ID_1 is not null or EMAIL_ID_2 is not null) and MOBILE_NO_2 is null order by GEN_DT desc")
	List<Push_dcall> getPushDcallEmailHistory(@Param("dcall_no") String dcall_no);

	@Query(nativeQuery = true, value = "select * from push_dcall where DCALL_NO = :dcall_no and (MOBILE_NO_1 is not null or MOBILE_NO_2 is not null) and EMAIL_ID_2 is null order by GEN_DT desc")
	List<Push_dcall> getPushDcallSMSHistory(@Param("dcall_no") String dcall_no);
	
}
