package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.OTH_STATUS_FPOSITE;


@Repository
public interface OTH_STS_FPOrepo extends JpaRepository<OTH_STATUS_FPOSITE, Long> {

}
