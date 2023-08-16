package com.demo.fpo.NsmLsmRepo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.FPO_SITE_ALLOT;
import com.demo.fpo.NsmLsmModel.FPO_SITE_INFO;

@Repository
public interface FPO_SITE_ALLOT_REPO extends JpaRepository<FPO_SITE_ALLOT, Long> {
	

	@Query(nativeQuery = true, value = "select * from ops$dir.fpo_site_allot where site_code = :cusSite")
	List<FPO_SITE_ALLOT> getSite(@Param("cusSite") String cusSite);
	
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_allot set ems_air = :emsair, ems_sal = :emssal, ems_sea = :emssea where site_code=:cusite")
	void updfpoallotsiteems(@Param("cusite") String cusite,@Param("emsair") String emsair,@Param("emssal") String emssal,@Param("emssea") String emssea);
	
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_allot set letter_air = :ltrair, letter_sal = :ltrsal, letter_sea = :ltrsea where site_code=:cusite")
	void updfpoallotsiteltr(@Param("cusite") String cusite,@Param("ltrair") String ltrair,@Param("ltrsal") String ltrsal,@Param("ltrsea") String ltrsea);
	
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_allot set ems_air = :parair, ems_sal = :parsal, ems_sea = :parsea where site_code=:cusite")
	void updfpoallotsitepar(@Param("cusite") String cusite,@Param("parair") String parair,@Param("parsal") String parsal,@Param("parsea") String parsea);
	
	@Query(nativeQuery = true, value = "update ops$dir.fpo_site_allot set role = :role, off_id = :offid,upd_date=:dt  where site_code=:cusite")
	void updfpoallotsiteoth(@Param("cusite") String cusite,@Param("offid") String offid,@Param("role") String role, @Param("dt") Date dt);
	
}
