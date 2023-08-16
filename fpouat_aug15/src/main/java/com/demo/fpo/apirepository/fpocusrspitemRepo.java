package com.demo.fpo.apirepository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.cusrspitemdet;



@Repository
public interface fpocusrspitemRepo extends JpaRepository<cusrspitemdet, Long> {

}


