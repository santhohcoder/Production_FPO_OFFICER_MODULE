package com.demo.fpo.NsmLsmRepo;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.ACL_ASSVAL;

public interface ACL_ASSVALrepo extends  JpaRepository<ACL_ASSVAL, Long> {
	
	@Query(nativeQuery = true, value = "SELECT  maxassval from ops$dir.acl_assval where to_dt is null")
	String getthresholdval();
	
	
	@Query(nativeQuery = true, value = "SELECT  maxassval,CREATED_BY,to_char(from_dt, 'DD/MM/YYYY HH24:MI:SS'), to_char(to_dt, 'DD/MM/YYYY HH24:MI:SS'), reason,doc_name FROM ops$dir.acl_assval order by from_dt desc")
	List<String>  getthresoldvalall();
	
	@Query(nativeQuery = true, value = "SELECT  maxassval,CREATED_BY,to_char(from_dt, 'DD/MM/YYYY HH24:MI:SS'), to_char(to_dt, 'DD/MM/YYYY HH24:MI:SS'), reason,doc_name FROM ops$dir.acl_assval order by from_dt desc")
	List<Collection>  getthresoldvalueall();

	@Query(nativeQuery = true, value = "SELECT  to_char(from_dt, 'DD/MM/YYYY HH24:MI:SS') from ops$dir.acl_assval where to_dt is null")
	String getassvaldate();
	
	
/*	@Query(nativeQuery = true, value = "select doc_path  from acl_assval")
	String getpdf();*/
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE ops$dir.ACL_ASSVAL SET TO_DT = sysdate WHERE TO_DT IS NULL")
	void updatetodate();
	
	
/*	@Query(nativeQuery = true, value = "select doc_path filename from ops$dir.acl_assval where maxassval =:max_vale  and doc_name=:document_name")
	String getpdf(@Param("max_vale") String max_vale,@Param("document_name") String document_name);*/
	
	@Query(nativeQuery = true, value = "select  doc_name from ops$dir.acl_assval where to_char(from_dt, 'DD/MM/YYYY HH24:MI:SS') =:max_vale")
	String getpdf(@Param("max_vale") String max_vale);

}
