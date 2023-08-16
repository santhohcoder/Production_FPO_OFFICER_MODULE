package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.Pincode_chnage;


@Repository
public interface Pincode_Map_CusSiterepo extends JpaRepository<Pincode_chnage, Long>{

}
