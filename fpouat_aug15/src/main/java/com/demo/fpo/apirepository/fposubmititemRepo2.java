package com.demo.fpo.apirepository;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.fposubmititem;





@Repository
public interface fposubmititemRepo2 extends JpaRepository<fposubmititem, Long> {

	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM FPO_ITEM_DET where cin_no=:cinno")
	//List<fposubmititem> findById(@Param("cinno") List<Long> fpoitemList);
	List<fposubmititem> getAllData(@Param("cinno") String cinno);
	//List<fposubmititem> findAllById(String cinno);
	//fposubmititem findById(String cinno);
	
	
	@Transactional
	@Query(nativeQuery = true, value = "SELECT count(*) FROM c_cusitm where item_id=:itemid and postingdt=:postdt")
	//List<fposubmititem> findById(@Param("cinno") List<Long> fpoitemList);
	Long chkrecfou(@Param("itemid") String itemid, @Param("postdt") String postdt);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	//@Query(nativeQuery = true, value = "update fposubmititem a set ASSVAL_INSFRT=(select round((tot_ass_val/totass_calc_val)*assess_val,2) from fpo_main b, fpo_item_det c where a.item_id=b.item_id and a.item_no=c.item_no and a.item_id=c.item_id and assess_val > 0)  where UPD_CIF=null")
	@Query(nativeQuery = true, value = "update fpo_item_det a set assval_insfrt=(select round((tot_ass_val/totass_calc_val)*assess_val,2) from fpo_main b, fpo_item_det c where a.item_id=c.item_id and a.item_no=c.item_no and assess_val > 0 and a.cin_no=b.cin_no)  where upd_cif=null")
	
	void updassvalinsfrt();	

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det c set bcd_amt=(select round((tot_ass_val/totass_calc_val)*assess_val*(bcd_rta/100),2)  from fpo_main a, fpo_item_det b where a.item_id=b.item_id and b.item_id=c.item_id and b.item_no=c.item_no and assess_val > 0 )  where upd_cif=null")
	void updbcdamt();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det c set sw_amt=(select round((bcd_amt*sw_rta/100),2) from fpo_main a, fpo_item_det b where a.item_id=b.item_id and bcd_amt > 0 and b.item_id=c.item_id and b.item_no=c.item_no)   where upd_cif=null")
	void updswamt();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det c set igst_amt=(select round((((tot_ass_val/totass_calc_val)*assess_val) + bcd_amt+sw_amt) *(igst_rta/100),2) from fpo_main a, fpo_item_det b where a.item_id=b.item_id and assess_val > 0 and b.item_id=c.item_id and b.item_no=c.item_no) where upd_cif=null")
	void updigstamt();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det set upd_cif='Y'")
	void upitmcif();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_item_det set UPD_CIF='Y', ASSVAL_INSFRT=:assinsfrt, BCD_AMT=:bcdamt, SW_AMT=:swsamt, IGST_AMT=:igstamt, DUTY=:dutyitm where ITEM_UNIQUE=:id")
	void upfpoitm(@Param("id")Long id, @Param("assinsfrt") BigDecimal assinsfrt, @Param("bcdamt") BigDecimal bcdamt, @Param("swsamt")BigDecimal swsamt, @Param("igstamt")BigDecimal igstamt, @Param("dutyitm")BigDecimal dutyitm);

	@Query(nativeQuery = true, value = "select  sum(BCD_AMT) from fpo_item_det where cin_no = :cin_NO")
	BigDecimal getBCDAmnt(@Param("cin_NO") String cin_NO);
	
	@Query(nativeQuery = true, value = "select  max(BCD_RTA) from fpo_item_det where cin_no =  :cin_NO")
	String getBCD_RTA(@Param("cin_NO") String cin_NO);

	@Query(nativeQuery = true, value = "select  sum(IGST_AMT) from fpo_item_det where cin_no = :cin_NO")
	BigDecimal getIGST_AMT(@Param("cin_NO") String cin_NO);


	@Query(nativeQuery = true, value = "select  max(IGST_RTA) from fpo_item_det where cin_no =  :cin_NO")
	String getIGST_RTA(@Param("cin_NO") String cin_NO);


	@Query(nativeQuery = true, value = "select  sum(SW_AMT) from fpo_item_det where cin_no =  :cin_NO")
	BigDecimal getSW_AMT(@Param("cin_NO") String cin_NO);


	@Query(nativeQuery = true, value = "select  max(SW_RTA) from fpo_item_det where cin_no =  :cin_NO")
	String getSW_RTA(@Param("cin_NO") String cin_NO);
}