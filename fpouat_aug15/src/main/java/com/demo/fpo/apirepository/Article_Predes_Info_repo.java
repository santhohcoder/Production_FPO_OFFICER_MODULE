package com.demo.fpo.apirepository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.apimodel.Article_Predes_Info;
import com.demo.fpo.apimodel.Predes_fpo_Req;

public interface Article_Predes_Info_repo extends JpaRepository<Article_Predes_Info, Long> {

	
	@Query(nativeQuery = true, value = "SELECT * FROM ARTICLE_PREDES_INFO where ARTICLE_ID=:item_ID and ROWNUM <= 1 order by PREDEC_DT desc")
	Article_Predes_Info getAllByItemId(@Param("item_ID") String item_ID);



}
