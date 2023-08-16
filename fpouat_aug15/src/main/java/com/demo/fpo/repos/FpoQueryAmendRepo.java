package com.demo.fpo.repos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoDefualtQuery;
import com.demo.fpo.model.FpoQuery;
import com.demo.fpo.model.FpoQueryamend;

@Repository
public interface FpoQueryAmendRepo extends JpaRepository<FpoQueryamend, Long> {
	
	
}
