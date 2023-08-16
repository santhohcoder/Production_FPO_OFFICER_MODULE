package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FPO_SETASIDE;
import com.demo.fpo.model.FpoPenalAmend;

@Repository
public interface FpoSetasideRepo extends JpaRepository<FPO_SETASIDE, Long>{ 

}
