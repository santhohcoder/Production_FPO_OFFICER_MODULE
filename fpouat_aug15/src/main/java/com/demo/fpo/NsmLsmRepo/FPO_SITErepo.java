package com.demo.fpo.NsmLsmRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.NsmLsmModel.FPO_SITE;


@Repository
public interface FPO_SITErepo extends JpaRepository<FPO_SITE, Long> {

	
	@Query(nativeQuery = true, value = "SELECT * from ops$dir.FPO_SITES where site_code = :sitecd") 
	FPO_SITE getfposite(@Param("sitecd") String sitecd);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "update ops$dir.fpo_sites set  clustered=:clustered, clussite=:clussite where site_code=:sitecode")
	void updatefposite(@Param("clustered") String clustered,@Param("clussite") String clussite,@Param("sitecode") String sitecode);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true, value = "update ops$dir.fpo_sites set  site_active='D' where site_code=:sitecode")
	void delfposite(@Param("sitecode") String sitecode);
	
	@Query(nativeQuery = true, value = "select map_code from ops$dir.fpo_sites fs join fpo_main fm on (fs.site_code=fm.cus_site) where item_id= :articleId")
	String getMapCode(@Param("articleId") String articleId);
	
}
