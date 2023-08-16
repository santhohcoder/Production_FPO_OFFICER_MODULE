package com.demo.fpo.apiservice;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apimodel.PostDataServecds;



@Service
public class PostService {
	@Autowired
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PostDataServecds fetchPostData() {
		// TODO Auto-generated method stub
		String qry = "select cusitm_token as CusitmToken,cusitm_url1 as Cusitmurl1,cusitm_url2 as Cusitmurl2,cusrsp_token as CusrspToken,cusrsp_url1 as Cusrspurl1,cusrsp_url2 as Cusrspurl2,cusrspuser_cd as Cusrspusercd from post_dataserv_info";
		Query query = entityManager.createNativeQuery(qry, PostDataServecds.class);
		
		return (PostDataServecds) query.getSingleResult();
	}

}
