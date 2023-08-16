package com.demo.fpo.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.C_CUSITM;

@Repository
public interface C_CUSITMrepost extends JpaRepository<C_CUSITM, Long>{
	
	@Query(nativeQuery=true,value="SELECT * FROM C_CUSITM")
	List<C_CUSITM> getpending();
	
	
	@Query(nativeQuery=true,value="SELECT COUNT(*) FROM C_CUSITM")
	List<C_CUSITM> getcount();
	


}
