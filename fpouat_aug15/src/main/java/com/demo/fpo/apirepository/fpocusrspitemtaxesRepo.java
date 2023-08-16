package com.demo.fpo.apirepository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.demo.fpo.apimodel.cusrspitemtaxes;




@Repository
public interface fpocusrspitemtaxesRepo extends JpaRepository<cusrspitemtaxes, Long> {
	
}


