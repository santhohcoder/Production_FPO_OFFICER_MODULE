package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoDefualtQuery;
import com.demo.fpo.model.FpoSecondDefaultQuery;


public interface FpoSecondDefualtQueryRepo extends JpaRepository<FpoSecondDefaultQuery, Long>{

	@Query(nativeQuery = true, value = "select * from fpo_second_defqry  where slno = :slno")
	List<FpoSecondDefaultQuery> getsecdefQry(@Param("slno") String slno);	
	
	@Query(nativeQuery = true, value = "select * from fpo_second_defqry order by SECQRY_SEQ")
	List<FpoSecondDefaultQuery> getsecdefQry1();
	
}
