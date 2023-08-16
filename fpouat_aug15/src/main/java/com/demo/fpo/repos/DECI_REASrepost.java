package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.DECI_REAS;

@Repository
public interface DECI_REASrepost extends JpaRepository<DECI_REAS, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM DECI_REAS where deci_cd < 10")
	List<DECI_REAS> getREAS();

}
