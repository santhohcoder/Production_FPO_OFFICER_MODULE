package com.demo.fpo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.demo.fpo.model.FPO_MAIL;

public interface MailSeqRepo extends JpaRepository<FPO_MAIL, Long> {

}
