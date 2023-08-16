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
import org.springframework.stereotype.Component;

import com.demo.fpo.apimodel.fposubmititem;



@Component
public class fposubmititemRepoImpl implements fposubmititemRepo{
	@PersistenceContext
	private EntityManager entityManager;

	public String getcurrt(String currcd) {
		// TODO Auto-generated method stub
		String effdt;
		effdt="";
		@SuppressWarnings("unchecked")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("select to_char(max(eff_dt),'DD-MON-YY') from dir.d_exchange@icesdev_imports  where curr_cd=?1 and end_dt is null")
	List<Object> resultSet = this.entityManager.createNativeQuery("select to_char(max(eff_dt),'DD-MON-YY') from ops$dir.d_exchange  where curr_cd=?1 and end_dt is null")
	.setParameter(1, currcd)
    .getResultList();
  entityManager.close();
  if (resultSet.size() == 0)
		return null;
//    if (resultSet.size()==0)	
//    {	List<Object> resultSet2 = this.entityManager.createNativeQuery("select to_char(max(eff_dt),'DD-MON-YY') from ops$dir.noncurr_det where curr_cd=?1")
//    			.setParameter(1, currcd)
//    		    .getResultList();
//    		  entityManager.close();	
//			    if(null != resultSet2.get(0))
//			    {
//			     effdt=resultSet2.get(0).toString();
//			     List<Object> resultSet3 = this.entityManager.createNativeQuery("select curr_rate from ops$dir.noncurr_det where eff_dt=to_date(?2,'DD-MON-YY') and curr_cd=?1")
//    			.setParameter(1, currcd)
//    			.setParameter(2, effdt)
//    		    .getResultList();
//    		     entityManager.close();
//    		     if (resultSet3.size()==0)
//    		    	 return null;
//    		     else if (null != resultSet3.get(0))
//			        return resultSet3.get(0).toString();	
//			     else
//			        return null;
//    		    }			    
//    }
  else if(null != resultSet.get(0))
    {
    	effdt=resultSet.get(0).toString();
    	//List<Object> resultSet4 = this.entityManager.createNativeQuery("select rate_imp from dir.d_exchange@icesdev_imports where eff_dt=to_date(?2,'DD-MON-YY') and curr_cd=?1")
    	@SuppressWarnings("unchecked")
    //	List<Object> resultSet4 = this.entityManager.createNativeQuery("select rate_imp from dir.d_exchange@icesdev_imports  where eff_dt=to_date(?2,'DD-MON-YY') and curr_cd=?1 and end_dt is null")
	List<Object> resultSet4 = this.entityManager.createNativeQuery("select rate_imp from ops$dir.d_exchange  where eff_dt=to_date(?2,'DD-MON-YY') and curr_cd=?1 and end_dt is null")
    	.setParameter(1, currcd)
    	.setParameter(2, effdt)
    	 .getResultList();
		  entityManager.close();
		  if (resultSet4.size()==0)
		    	 return null;
		     else if (null != resultSet4.get(0))
   	             return resultSet4.get(0).toString();	
          else
	             return null;
     }
    return null;
	}
	
	public String getBCD(String CTH) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("select rta from dir.dt_bcd@icesdev_imports where end_dt is null and cth=?1")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("select rta from ops$dir.dt_bcd  where   nvl(end_dt,sysdate)>= sysdate and cth=?1 ")
		List<Object> resultSet = this.entityManager.createNativeQuery("select rta from ops$dir.dt_notn_slno where cth=?1 and notn_type='C' and ad_flg is null and notn='050/2017' and slno='608' and nvl(notn_endt,sysdate)>= sysdate")
				
				.setParameter(1, CTH)
			      .getResultList();
			    entityManager.close();
			    if (resultSet.size() == 0)
					return null;
			    else if(null != resultSet.get(0))
			    return resultSet.get(0).toString();	
			    else
			    return null;
	}
	
	
	public String getBCD_GIFT(String CTH) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("select rta from dir.dt_bcd@icesdev_imports where end_dt is null and cth=?1")
		List<Object> resultSet = this.entityManager.createNativeQuery("select rta from ops$dir.dt_bcd  where   nvl(end_dt,sysdate)>= sysdate and cth=?1 ")
				.setParameter(1, CTH)
			      .getResultList();
			    entityManager.close();
			    if (resultSet.size() == 0)
					return null;
			    else if(null != resultSet.get(0))
			    return resultSet.get(0).toString();	
			    else
			    return null;
	}

	public String getIGST(String CTH) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	//	List<Object> resultSet = this.entityManager.createNativeQuery("select max(rta) from dir.dt_notn_slno@icesdev_imports where cth=?1 and notn_type='G' and notn_endt is null")
	List<Object> resultSet = this.entityManager.createNativeQuery("select max(rta) from ops$dir.dt_notn_slno  where cth=?1 and notn_type='G' and nvl(notn_endt,sysdate)>= sysdate")
				.setParameter(1, CTH)
			      .getResultList();
			    entityManager.close();
			    if (resultSet.size() == 0)
					return null;
			    else if(null != resultSet.get(0))
			    return resultSet.get(0).toString();	
			    else
			    return null;
	}
	

	@Override
	public String getSWS(String CTH) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	//List<Object> resultSet = this.entityManager.createNativeQuery("select scd_rt from dir.dt_scd_rate@icesdev_imports where end_dt is null")
		List<Object> resultSet = this.entityManager.createNativeQuery("select scd_rt from ops$dir.dt_scd_rate  where end_dt is null")
			      .getResultList();
			    entityManager.close();
			    if (resultSet.size() == 0)
					return null;
			    else if(null != resultSet.get(0))
			    return resultSet.get(0).toString();	
			    else
			    return null;
	}


	@Override
	public List<fposubmititem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<fposubmititem> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<fposubmititem> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends fposubmititem> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends fposubmititem> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<fposubmititem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public fposubmititem getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends fposubmititem> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends fposubmititem> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<fposubmititem> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public <S extends fposubmititem>  S save(S entity) {
		// TODO Auto-generated method stub
		this.entityManager.persist(entity);
		entityManager.close();
		return entity;
	}


	@Override
	public Optional<fposubmititem> findById(Long id) {
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
	public void delete(fposubmititem entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends fposubmititem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends fposubmititem> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends fposubmititem> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends fposubmititem> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends fposubmititem> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}