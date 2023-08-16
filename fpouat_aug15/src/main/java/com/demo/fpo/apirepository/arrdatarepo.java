package com.demo.fpo.apirepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.arrdata;




@Repository
public interface arrdatarepo extends JpaRepository<arrdata, Long> {

}
