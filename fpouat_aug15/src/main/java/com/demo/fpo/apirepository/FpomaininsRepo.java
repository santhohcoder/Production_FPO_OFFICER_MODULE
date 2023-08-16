package com.demo.fpo.apirepository;





import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.Fpomainins;





@Repository
public interface FpomaininsRepo extends JpaRepository<Fpomainins, Long> {
		
	@Transactional
	@Query(nativeQuery = true, value = "SELECT count(*) FROM FPO_MAIN where item_id=:itemId and postingdt=:postingDate")
	int getCountByItemId(@Param("itemId") String itemId,@Param("postingDate") String postingDate);
//	@Query(value = "SELECT cusitm_job_no.nextval FROM dual", nativeQuery = 
//	        true)
//	 Long getNextJOBNO();
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_main set ARR_INDIA='Y' where item_id =:id")
	void updooestatus(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_main set ARR_FPO='Y' where item_id =:id")
	void updfpostatus(@Param("id") String id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from cin_info where cin_no = :cinno")
	int chkcinfou(@Param("cinno") String cinno);
	
//	@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null")
	@Query(nativeQuery = true, value = "select a.role_name from ops$dir.d_emp_roles a where  a.user_id=:offid and end_dt is null and cus_site=(select fpo_cus_site from ops$dir.d_emp where a.user_id=:offid and user_account_control='A')")
	List<String> getmulrole(@Param("offid") String offid);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE fpo_main set DELIVERY='Y' where item_id =:id")
	void upddelistatus(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set frt_curr_rate = (select rate_imp from ops$dir.d_exchange c where c.curr_cd = a.postage_curr_cd and  eff_dt=(select max(eff_dt) from ops$dir.d_exchange e where end_dt is null and e.curr_cd=c.curr_cd group by e.curr_cd)) where upd_cif=null")
	void updfrtcurrrt();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set ins_curr_rate = (select rate_imp from ops$dir.d_exchange c where c.curr_cd = a.ins_val_currcd and eff_dt=(select max(eff_dt) from ops$dir.d_exchange e where end_dt is null and e.curr_cd=c.curr_cd group by e.curr_cd)) where upd_cif=null")
	void updinscurrrt();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set totass_calc_val = (select round(sum(assess_val),2) from fpo_item_det b where b.cin_no=a.cin_no) where upd_cif=null ")
	void updtotassitm();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set frt_calc_val=round(nvl(frt_curr_rate,1)*nvl(postage_amt,0),2) where upd_cif=null ")
	void updfrtcalcval();
			
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set ins_calc_val=round(nvl(ins_curr_rate,1)*nvl(ins_val,0),2) where upd_cif=null ")
    void updinscalcval();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set frt_calc_val=round(totass_calc_val*20/100,2) where (frt_calc_val > totass_calc_val*20/100) or frt_calc_val is null or frt_calc_val=0  and upd_cif=null ")
	void updfrtcalcval2();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set ins_calc_val=round(totass_calc_val*1.125/100,2) where (ins_calc_val > totass_calc_val*1.125/100) or ins_calc_val is null or ins_calc_val=0  and upd_cif=null ") 
	void updinscalcval2();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set tot_ass_val=round(totass_calc_val+ins_calc_val+frt_calc_val,2) where upd_cif=null ")
	void updtotassval();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set tot_duty = (select round(sum(duty),2) from fpo_item_det b where b.cin_no=a.cin_no)  where upd_cif=null ")
	void updtotduty();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set tot_amt_payable=tot_duty  where upd_cif=null ")
    void updtotdutypay();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main a set tot_amt_payable=0 where tot_ass_val < 1000 and nature_type_cd <> '31' and upd_cif=null ") 
	void updtotdutypay2(); 
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_main set upd_cif='Y'")
	void updmaincif();
	

}
