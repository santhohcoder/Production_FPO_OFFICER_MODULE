package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import com.demo.fpo.model.FPO_MANUAL_COMMERCIAL;

@Repository
public interface FPOManComm_REPO extends JpaRepository<FPO_MANUAL_COMMERCIAL, Long> {
	
	
}
