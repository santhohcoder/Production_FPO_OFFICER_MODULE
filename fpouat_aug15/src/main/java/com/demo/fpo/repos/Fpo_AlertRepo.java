package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.Fpo_Alert;
import com.demo.fpo.model.SelectTag;

public interface Fpo_AlertRepo extends JpaRepository<Fpo_Alert, Long> {

	@Query(nativeQuery = true, value = "select * from FPO_ALERT where trunc(GEN_DT) between :fromdate and :todate and CUS_SITE=:cussite order by id")
	List<Fpo_Alert> getfpo_Alert(@Param("fromdate") String fromdate, @Param("todate") String todate,@Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select * from FPO_ALERT where STATUS='Active' and CUS_SITE=:cussite order by id")
	List<Fpo_Alert> getActivefpo_Alert(@Param("cussite") String cussite);

	@Query(nativeQuery = true, value = "select distinct code as id,code as data,category as value from ops$dir.pdi_nature_trans_codes where category is not null and category <> ' ' order by category")
	List<SelectTag> getOthersMailCatAlert();

	@Query(nativeQuery = true, value = "select * from FPO_ALERT where STATUS='Active' and ALERT_LEVEL='All India' order by id")
	List<Fpo_Alert> getActivefpo_AlertForAll();

	@Query(nativeQuery = true, value = "select * from FPO_ALERT where trunc(GEN_DT) between :fromdate and :todatem and ALERT_LEVEL='All India' order by id")
	List<Fpo_Alert> getfpo_AlertForAll(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Query(nativeQuery = true, value = "select SITE_CODE from ops$dir.FPO_SITES where SITE_NAME = (select SITE_NAME from ops$dir.FPO_SITES where SITE_CODE = :site)")
	List<String> getsites(@Param("site") String site);
    
    
}
