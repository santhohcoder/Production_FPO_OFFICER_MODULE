package com.demo.fpo.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.FpoTrackDetails;

public interface FpoTrackDetailsRepo extends JpaRepository<FpoTrackDetails, String> {

	@Query(nativeQuery = true, value = "SELECT * from fpo_track_details WHERE item_id=:itemid")
	List<Collection> gettrackdet(@Param("itemid") String itemid);
}
