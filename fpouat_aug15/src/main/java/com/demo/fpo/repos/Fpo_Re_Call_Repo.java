package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.fpo.model.FPO_RE_CALL;

@Repository
public interface Fpo_Re_Call_Repo extends JpaRepository<FPO_RE_CALL, Long> {

}
