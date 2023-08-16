package com.demo.fpo.NsmLsmRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.EDIT_D_EMP_ROLES;


@Repository
public interface EDIT_D_EMP_ROLES_repo extends JpaRepository<EDIT_D_EMP_ROLES, Long> {

}
