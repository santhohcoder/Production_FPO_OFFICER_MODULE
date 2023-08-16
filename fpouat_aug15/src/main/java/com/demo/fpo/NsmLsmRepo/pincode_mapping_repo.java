package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.Pin_Code_Map;


@Repository
public interface pincode_mapping_repo extends JpaRepository<Pin_Code_Map, Long>{

}
