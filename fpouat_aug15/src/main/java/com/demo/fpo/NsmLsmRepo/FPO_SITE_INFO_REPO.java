package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.FPO_SITE_INFO;

@Repository
public interface FPO_SITE_INFO_REPO extends JpaRepository<FPO_SITE_INFO, Long> {
	
	@Query(nativeQuery = true, value = "select * from ops$dir.fpo_site_info where fpo_site = :cusSite")
	FPO_SITE_INFO getSite(@Param("cusSite") String cusSite);

}
