package com.demo.fpo.repos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FPO_AMEND;

//@Repository
//@Component
public class FPO_AMENDrepostImpl {

	 EntityManager entityManager;

	 public List<FPO_AMEND> findBooksByAuthorNameAndTitle(String authorName, String title) {
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<FPO_AMEND> cq = cb.createQuery(FPO_AMEND.class);
	     
	        Root<FPO_AMEND> book = cq.from(FPO_AMEND.class);
	        Predicate authorNamePredicate = cb.equal(book.get("cin_no"), authorName);
	        //Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
	        cq.where(authorNamePredicate);
	 
	        TypedQuery<FPO_AMEND> query = entityManager.createQuery(cq);
	        return query.getResultList();
	    }

}
