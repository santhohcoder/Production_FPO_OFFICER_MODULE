package com.demo.fpo.apiservice;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apibean.PostDataServe1;


@Service
public class DeliService {
	@Autowired
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PostDataServe1 fetDeliPostData() {
		// TODO Auto-generated method stub
		String qry = "select deli_token as deliToken,deli_endpoint as deliEndpoint,deli_dataendpoint as deliDataEndpoint from post_dataserv_info";
		Query query = entityManager.createNativeQuery(qry, PostDataServe1.class);
		
		return (PostDataServe1) query.getSingleResult();
	}

}
