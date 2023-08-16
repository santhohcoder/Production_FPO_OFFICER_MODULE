package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.DCallDocInfo;

@Repository
public interface DCallDocInfoRepo extends JpaRepository<DCallDocInfo, Long> {

	@Query(nativeQuery = true, value = "select * from dcall_doc_info where dcall_no=:dcallNumber")
	List<DCallDocInfo> findByDcallNumber(@Param("dcallNumber") String dcallNumber);
	
}
