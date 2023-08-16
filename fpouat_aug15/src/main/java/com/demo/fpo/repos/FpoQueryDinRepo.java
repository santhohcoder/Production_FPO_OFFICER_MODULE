package com.demo.fpo.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FpoQueryDin;

@Repository
public interface FpoQueryDinRepo extends JpaRepository<FpoQueryDin, Long>{
	
	
	
	
	@Modifying	
	@Transactional	
	@Query(nativeQuery = true, value = "update fpo_QRY_DIN SET DEP_COMMENTS = :queryRemarks1 where CIN_NO = :cinNo and DIN_SERIAL_NO =(select max(DIN_SERIAL_NO) from fpo_QRY_DIN where CIN_NO = :cinNo)")	
	void updateCmts(@Param("cinNo") String cinNo,@Param("queryRemarks1") String queryRemarks1);

	@Query(nativeQuery = true, value = "select * from fpo_QRY_DIN  where CIN_NO = :cinNo ORDER BY FPO_QRY_ID desc")
	List<FpoQueryDin> getFpoQueryDIN(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select unique_no from fpo_QRY_DIN  where ITEM_ID = :itemid and din_serial_no = (select max(din_serial_no) from fpo_qry_din where item_id=:itemid) ORDER BY FPO_QRY_ID desc")
	String getdcallno(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select DEP_COMMENTS from fpo_QRY_DIN  where ITEM_ID = :itemid  and fpo_qry_id = (select max(fpo_qry_id) from fpo_qry_din where   	item_id=:itemid)")
	String getdcallnoq(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select DEP_CMTS_ACL from fpo_QRY_DIN  where ITEM_ID = :itemid  and fpo_qry_id = (select max(fpo_qry_id) from fpo_qry_din where 	item_id=:itemid)")
	String getdecomacl(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select QRY_EMAILID from fpo_QUERY  where ITEM_ID = :itemid  and ID = (select max(ID) from fpo_QUERY where item_id=:itemid)")
	String getgmail(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select QRY_MOBILENO from fpo_QUERY  where ITEM_ID = :itemid  and ID = (select max(ID) from fpo_QUERY where item_id=:itemid)")
	String getmobile(@Param("itemid") String itemid);
	
	@Query(nativeQuery = true, value = "select * from fpo_QRY_DIN  where CIN_NO = :cinNo and DIN_SERIAL_NO =(select max(DIN_SERIAL_NO) from fpo_QRY_DIN  where CIN_NO = :cinNo)")
	List<FpoQueryDin> getFpoQueryDINSerialNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select max(din_serial_no) from  FPO_QRY_DIN  where CIN_NO = :cinNo and cus_site=:cusite")
	Long getdincou(@Param("cinNo") String cinNo, @Param("cusite") String cusite);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DIN SET DEP_CMTS_ACL = :queryRemarks1 where CIN_NO = :cinNo and DIN_SERIAL_NO =(select max(DIN_SERIAL_NO) from fpo_QRY_DIN where CIN_NO = :cinNo)")
	void updateAclCmts(@Param("cinNo") String cinNo,@Param("queryRemarks1") String queryRemarks1);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DIN SET din1=:dinno, DEP_COMMENTS = :queryRemarks1 where CIN_NO = :cinNo and DIN_SERIAL_NO = :slno")
	void updateAprCmts(@Param("cinNo") String cinNo,@Param("queryRemarks1") String queryRemarks1,@Param("dinno") String dinno, @Param("slno") Long slno);
	
	@Query(nativeQuery = true, value ="select * from fpo_QRY_DIN where CIN_NO = :cinNo and din_serial_no= (select max(din_serial_no) from fpo_QRY_DIN where CIN_NO = :cinNo)")
	List<FpoQueryDin> getfpouniqueno(@Param("cinNo") String cinNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DIN SET din2 = :dinNo where CIN_NO = :cinNo and DIN_SERIAL_NO =(select max(DIN_SERIAL_NO) from fpo_QRY_DIN where CIN_NO = :cinNo)")
	void updateAdditionQueryDinNo(@Param("cinNo") String cinNo, @Param("dinNo") String dinNo);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QRY_DIN SET general_qry_reply = :reply where CIN_NO = :cinNo and DIN_SERIAL_NO =(select max(DIN_SERIAL_NO) from fpo_QRY_DIN where CIN_NO = :cinNo)")
	void updateGeneralQueryReply(@Param("cinNo") String cinNo, @Param("reply") String reply);
}
