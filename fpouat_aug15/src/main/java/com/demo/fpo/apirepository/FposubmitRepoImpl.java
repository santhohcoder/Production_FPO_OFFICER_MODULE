package com.demo.fpo.apirepository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.Fposubmit;





@Component
public class FposubmitRepoImpl implements FposubmitRepo {

	@PersistenceContext
	private EntityManager entityManager;


		public String getfponame(String wherefldName, String condfldName) {
		@SuppressWarnings("unchecked")
		List<Object> resultSet = this.entityManager.createNativeQuery(
				//"Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name)=upper(b.state_name) and upper(b."
				"Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name)=upper(b.state_name) and upper(b."
						+ condfldName + ")=?1")
				.setParameter(1, wherefldName).getResultList();
		entityManager.close();
		if (resultSet.size() == 0)
			return null;
		else if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return null;
	}


//	@SuppressWarnings("unchecked")
//	public List<Fposubmit> GetById(String cinno) {
//		List<Fposubmit> resultSet = this.entityManager.createNativeQuery(
//				"SELECT * FROM FPO_MAIN where cin_no=?1")
//				.setParameter(1, cinno).getResultList();
//		entityManager.close();
//		if (resultSet.size() == 0)
//			return null;
//		else if (null != resultSet.get(0))
//			return resultSet;
//	 
//		else
//			return null;
//	}

	public String getfpolikecitystname(String fldnm) {
		@SuppressWarnings("unchecked")
		//List<Object> resultSet = this.entityManager.createNativeQuery("Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?1)) or upper(b.state_name) = upper(trim(?1)))")
	List<Object> resultSet = this.entityManager.createNativeQuery("Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?1)) or upper(b.state_name) = upper(trim(?1)))")
				.setParameter(1, fldnm).getResultList();
		entityManager.close();
		if (resultSet.size() == 0)
			return null;
		else if (null != resultSet.get(0))
			return resultSet.get(0).toString();

		else
			return null;
	}
	
	
	public String getfponamemapping(String pincode) {
		@SuppressWarnings("unchecked")
		//List<Object> resultSet = this.entityManager.createNativeQuery("Select fpo_code from ops$dir.pdi_pincode_mapping_india  where to_number(?1) >= to_number(in_start_pincode) and to_number(?1) <= to_number(in_end_pincode)")
	    
	//	List<Object> resultSet = this.entityManager.createNativeQuery("Select distinct(CUSTOMS_FPO_SITE_CODE) from ops$dir.pdi_pincode_mapping_india  where to_number(?1) >= to_number(inbound_start_pincode) and to_number(?1) <= to_number(inbound_end_pincode)")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("Select fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports where to_number(?1) >= to_number(inbound_start_pincode) and to_number(?1) <= to_number(inbound_end_pincode)").setParameter(1, pincode).getResultList();
	List<Object> resultSet = this.entityManager.createNativeQuery("Select fpo_code from ops$dir.pdi_pincode_mapping_india  where to_number(?1) >= to_number(inbound_start_pincode) and to_number(?1) <= to_number(inbound_end_pincode)").setParameter(1, pincode).getResultList();
        entityManager.close();
        if (resultSet.size() == 0)
            return null;
        else if (null != resultSet.get(0))
            return resultSet.get(0).toString();
        else
            return null;		
	}
	
	public String getfposite(String fpocode)
	{
		@SuppressWarnings("unchecked")
		//List<Object> resultSet = this.entityManager.createNativeQuery("select distinct(site_code) from ops$dir.fpo_sites a, ops$dir.pdi_pincode_mapping_india b where a.site_name=b.fpo_origin and site_active='Y' and fpo_code=?1")
		//List<Object> resultSet = this.entityManager.createNativeQuery("Select distinct(CUSTOMS_FPO_SITE_CODE) from dir.pdi_pincode_mapping_india@icesdev_imports where fpo_code=?1")		
		List<Object> resultSet = this.entityManager.createNativeQuery("Select distinct(CUSTOMS_FPO_SITE_CODE) from ops$dir.pdi_pincode_mapping_india  where fpo_code=?1")
		.setParameter(1, fpocode)
			      .getResultList();
			    entityManager.close();
	     if (resultSet.size() == 0)
	    	 return null;
	     else if(null != resultSet.get(0))
			 return resultSet.get(0).toString();	
	     else
			 return null;
	}
	
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	public String getfpolikename(String wherefldName1, String wherefldName2) {
		List<Object> resultSet = this.entityManager.createNativeQuery(
				//"Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) =upper(trim(?1) ) or upper(b.state_name) = upper(trim(?1)))")
				"Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) =upper(trim(?1) ) or upper(b.state_name) = upper(trim(?1)))")
				.setParameter(1, wherefldName1).getResultList();
		entityManager.close();
		if (resultSet.size() == 0) {
			@SuppressWarnings({ })
			List<Object> resultSet2 = this.entityManager.createNativeQuery(
					//"Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?2)) or upper(b.district) = upper(trim(?2)))")
					"Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?2)) or upper(b.district) = upper(trim(?2)))")
					.setParameter(2, wherefldName2).getResultList();
			entityManager.close();
			if (resultSet.size() == 0) {
				List<Object> resultSet3 = this.entityManager.createNativeQuery(
						//"Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?2)) or upper(b.district) = upper(trim(?2)))")
					"Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name) = upper(b.state_name) and (upper(b.district)=upper(trim(?2)) or upper(b.state_name) = upper(trim(?1)))")
						.setParameter(1, wherefldName1).setParameter(2, wherefldName2).getResultList();
				entityManager.close();
				if (resultSet.size() == 0) {
					List<Object> resultSet4 = this.entityManager.createNativeQuery(
						//	"Select a.fpo_code from dir.pdi_pincode_mapping_india@icesdev_imports a , dir.pdi_pincodes@icesdev_imports b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?2)) or upper(b.district) = upper(trim(?2)))")
							"Select a.fpo_code from ops$dir.pdi_pincode_mapping_india a , ops$dir.pdi_pincodes b where upper(a.state_name) = upper(b.state_name) and (upper(b.district) = upper(trim(?1)) or upper(b.state_name) = upper(trim(?2)))")
							.setParameter(1, wherefldName1).setParameter(2, wherefldName2).getResultList();
					entityManager.close();
					if (resultSet.size() == 0)
						return null;
				} else if (null != resultSet.get(0))
					return resultSet.get(0).toString();
				else
					return null;
			} else if (null != resultSet.get(0))
				return resultSet.get(0).toString();
			else
				return null;
		} else if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return null;
		return null;
	}

//	public String getfponame_cityname(String citynm) {
//		  List<Object> resultSet = this.entityManager.createNativeQuery("Select fpo_origin from dir.pdi_pincode_mapping_india a , dir.pdi_pincodes@icesdev_imports b where a.state_name=b.state_name and b.district=?1")
//				    .setParameter(1, citynm)
//		            .getResultList();
//					    entityManager.close();
//	                    if (resultSet.size()==0)
//	                    	return null;
//	                    else if(null != resultSet.get(0))
//					    return resultSet.get(0).toString();	
//					    else
//					    return null;
//			}

	public String insertWithQuery(String cd, String colName, String TabName, String fldName) {
		@SuppressWarnings("unchecked")
		List<Object> resultSet = this.entityManager
				.createNativeQuery("SELECT distinct " + colName + " FROM  " + TabName + " where " + fldName + " = ?1")
				.setParameter(1, cd).getResultList();
		entityManager.close();
		if (resultSet.size()==0)
        	return null;
		else if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return null;
	}

	public String currencyQuery(String cd) {
		@SuppressWarnings("unchecked")
		List<Object> resultSet = this.entityManager
			//	.createNativeQuery("SELECT curr_desc FROM dir.d_curr_cd@icesdev_imports where curr_cd = ?1").setParameter(1, cd)
				.createNativeQuery("SELECT curr_desc FROM ops$dir.d_curr_cd  where curr_cd = ?1").setParameter(1, cd)
				.getResultList();
		entityManager.close();
		if (resultSet.size()==0)
        	return null;
		else if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return null;
	}

	@Transactional
	@Override
	public String getFpoSeq() {
		@SuppressWarnings("unchecked")
		List<Object> resultSet = this.entityManager.createNativeQuery("select max(fpomain_no) from fpo_main")
				.getResultList();
		entityManager.close();
		if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return "1";
	}

	@Transactional
	@Override
	public String getOOESeq() {
		@SuppressWarnings("unchecked")
		List<Object> resultSet = this.entityManager.createNativeQuery("select max(id) from article_arr_info")
				.getResultList();
		entityManager.close();
		if (null != resultSet.get(0))
			return resultSet.get(0).toString();
		else
			return "1";
	}

	@Override
	public List<Fposubmit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fposubmit> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fposubmit> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Fposubmit> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Fposubmit> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Fposubmit> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public Fposubmit getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Fposubmit> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Fposubmit> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Fposubmit> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Fposubmit> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Fposubmit entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Fposubmit> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Fposubmit> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Fposubmit> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Fposubmit> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Fposubmit> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public <S extends Fposubmit> S save(S entity) {
		// TODO Auto-generated method stub
		this.entityManager.persist(entity);
		entityManager.close();
		return entity;
	}


	@Override
	public List<Fposubmit> findById(String cinno) {
		// TODO Auto-generated method stub
		return null;
	}

}
