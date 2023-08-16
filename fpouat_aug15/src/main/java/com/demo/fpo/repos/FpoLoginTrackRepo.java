package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.DCallDocInfo;
import com.demo.fpo.model.FpoLoginTrack;

public interface FpoLoginTrackRepo  extends JpaRepository<FpoLoginTrack, Long> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update fpo_login_track set role=:role where id=(select max(id) from fpo_login_track where off_id=:offid)")
	void updateRolelogin(@Param("role") String role,@Param("offid") String offid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "update "
			+ "fpo_login_track set LOGOUT_TIME=:date where id=(select max(id) from fpo_login_track where off_id=:offid)")
	void updateLogout(@Param("date") Date date,@Param("offid") String offid);


}

