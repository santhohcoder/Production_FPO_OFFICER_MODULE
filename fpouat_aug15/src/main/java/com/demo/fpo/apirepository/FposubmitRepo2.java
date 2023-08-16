

package com.demo.fpo.apirepository;





import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Fposubmit;




@Repository
public interface FposubmitRepo2 extends JpaRepository<Fposubmit, Long> {

	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_MAIN where cin_no=:cinno")
//	List<FPO_MAIN> findById(String cinno);
	Fposubmit findById(@Param("cinno") String cinno);
	
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_MAIN where item_id=:itemid and trim(postingdt)=:bookdt")
	Fposubmit itemIdbookdt(@Param("itemid") String itemid, @Param("bookdt") String bookdt);
	
	@Transactional
	@Query(nativeQuery = true, value = "SELECT count(*)  FROM FPO_MAIN where item_id=:itemid")
	int getcouitemId(@Param("itemid") String itemid);
	
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_MAIN where item_id=:itemid")
	Fposubmit itemId(@Param("itemid") String itemid);
}


