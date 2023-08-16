package com.demo.fpo.apiservice;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apibean.PostDataServe;




@Service
public class ArrpostDataserveOOE {
	@Autowired
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PostDataServe fetArrPostData() {
		// TODO Auto-generated method stub
		String qry = "select arr_token as arrToken,arr_endpoint as arrEndpoint,arr_dataendpoint as arrDataEndpoint from post_dataserv_info";
		Query query = entityManager.createNativeQuery(qry, PostDataServe.class);
		
		return (PostDataServe) query.getSingleResult();
	}

}
