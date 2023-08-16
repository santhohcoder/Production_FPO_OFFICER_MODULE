package com.demo.fpo.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoDefualtQuery;

import antlr.collections.List;

@Repository
public interface FpoDefualtQueryRepo extends JpaRepository<FpoDefualtQuery, Long> {
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update dcallqry_gen set qry_reply='Y',ip_address=:ipAddress where dcall_no=:dcallLetterNumber")
//	void updateQryReplyByDCallNo(@Param("dcallLetterNumber") String dcallLetterNumber,@Param("ipAddress") String ipAddress);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE dcallqry_gen SET qry_reply = 'Y', ip_address = :ipAddress WHERE id = (SELECT MAX(id) FROM dcallqry_gen WHERE dcall_no = :dcallLetterNumber)")
	void updateQryReplyByDCallNo(@Param("dcallLetterNumber") String dcallLetterNumber,@Param("ipAddress") String ipAddress);
	
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "select * from fpo_default_query order by SERIAL_NO")
	java.util.List<FpoDefualtQuery> getDefQryData();
}
