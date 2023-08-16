package com.demo.fpo.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FPO_AMEND;

@Repository
public interface FPO_AMENDrepost extends JpaRepository<FPO_AMEND, Long> {

	@Query(nativeQuery = true, value = "SELECT * from fpo_AMEND_ITEM_DET where CIN_NO = :cinNo order by AMEND_SERIAL_NO")
	List<FPO_AMEND> getFpoAmend(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "SELECT * from fpo_AMEND_ITEM_DET where CIN_NO = :cinNo and item_no = :itemNo order by AMEND_SERIAL_NO")
	List<FPO_AMEND> getFpoAmendOnCinItemNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "SELECT MAX(AMEND_SERIAL_NO)+1 from fpo_AMEND_ITEM_DET where CIN_NO = :cinNo and item_no = :itemNo ")
	Long getMaxFpoItemAmendOnCinItemNo(@Param("cinNo")String cinNo, @Param("itemNo") Long itemNo);

	@Query(nativeQuery = true, value = "SELECT * from fpo_AMEND_ITEM_DET where CIN_NO = :cinNo and item_no = :itemNo  order by AMEND_SERIAL_NO")
	List<FPO_AMEND> getAmendByCinItem(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE FPO_AMEND_ITEM_DET SET amend_flag = :amendFlag WHERE cin_no = :cinNo and item_no = :itemNo ")
	void deleteFpoItem(@Param("amendFlag") String amendFlag, @Param("cinNo") String cinNo, @Param("itemNo") String itemNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_amend_item_det where cin_no = :cinNo and item_no = :itemNo  and amend_seq = (select min(amend_seq) from fpo_amend_item_det where cin_no = :cinNo  and item_no = :itemNo)")
	FPO_AMEND getLatestAmendOnCinAndItemNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	@Query(nativeQuery = true, value = "select amend_seq.nextval from dual")
	Long getSeq();
	
	@Query(nativeQuery = true, value = "select * from fpo_amend_item_det where cin_no = :cinNo and item_no = :itemNo  and amend_seq = (select max(amend_seq) from fpo_amend_item_det where cin_no = :cinNo  and item_no = :itemNo)")
	List<FPO_AMEND> latestAmendOnCinAndItemNo(@Param("cinNo") String cinNo, @Param("itemNo") Long itemNo);
	
	/*
	 * @Modifying
	 * 
	 * @Transactional public static List<FPO_AMEND>
	 * findBooksByAuthorNameAndTitle(String authorName, EntityManager entityManager)
	 * { CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	 * CriteriaQuery<FPO_AMEND> cq = cb.createQuery(FPO_AMEND.class);
	 * 
	 * Root<FPO_AMEND> fpoAmend = cq.from(FPO_AMEND.class); Predicate cinNo =
	 * cb.equal(fpoAmend.get("CIN_NO"), authorName); cq.where(cinNo);
	 * 
	 * Root<FPO_AMEND> fpoAmend = cq.from(FPO_AMEND.class); TypedQuery<FPO_AMEND>
	 * query = entityManager.createQuery(cq); return query.getResultList(); }
	 */

}
