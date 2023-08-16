package com.demo.fpo.apirepository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.cusrsp;



@Repository
public interface fpocusrspRepo extends JpaRepository<cusrsp, Long> {

}


