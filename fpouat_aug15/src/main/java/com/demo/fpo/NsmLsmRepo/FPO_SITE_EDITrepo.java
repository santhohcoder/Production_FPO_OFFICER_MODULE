
  package com.demo.fpo.NsmLsmRepo;
  
  import org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.EDIT_FPO_SITE;
  
  
  @Repository public interface FPO_SITE_EDITrepo extends
  JpaRepository<EDIT_FPO_SITE, Long>{
  
  }
 