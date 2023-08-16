package com.demo.fpo.apirepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.fpo.apimodel.Fpoqrydeci;


//import consumeCDSAPI.consumeModel.Deciqryfpostatus;

public interface  FpodeciqryRepo extends JpaRepository<Fpoqrydeci,Long> {
//	@Query(nativeQuery = true, value = "SELECT a.max(id),a.cin_no,a.deci_cd,a.qry_type,b.ass_dt from fpo_qry_deci a, fpo_status b where nvl(sent,0)=0"
//			+ "and a.cin_no=b.cin_no  group by a.cin_no,a.deci_cd,a.qry_type")
//	List<Fpoqrydeci> getdeciqry();
//	@Query(nativeQuery = true, value = "SELECT max(a.id) as id,a.cin_no as CIN_NO,a.deci_cd as DECI_CD,a.qry_type as QRY_TYPE,b.ass_dt as ASS_DT from fpo_qry_deci a, fpo_status b where nvl(sent,0)=0"
//	+ "and a.cin_no=b.cin_no and a.qry_type not in ('E1','E2','E3','E4') group by a.cin_no,a.deci_cd,a.qry_type,b.ass_dt")
							
	@Query(nativeQuery = true, value = "select a.cin_no as CIN_NO,a.deci_cd as DECI_CD,a.qry_type as QRY_TYPE,b.ass_dt as ASS_DT from fpo_qry_deci a, fpo_status b where "
		+ "a.cin_no=b.cin_no and a.id in (select max(id) from fpo_qry_deci c where nvl(sent,0)=0 and c.cin_no=a.cin_no "
		+ "and c.qry_type not in ('E1','E2','E3','E4'))")
	List<Deciqryfpostatus> getdeciqry();
//	@Query("SELECT max(a.id) as id,a.CIN_NO as CIN_NO,a.DECI_CD as DECI_CD,a.QRY_TYPE as QRY_TYPE from Fpoqrydeci a where nvl(a.sent,0)=0"
//			+ " group by a.CIN_NO,a.DECI_CD,a.QRY_TYPE")
//	@Query(nativeQuery = true, value = "SELECT a.id as id,a.cin_no as CIN_NO,a.deci_cd as DECI_CD,a.qry_type as QRY_TYPE ,b.ass_dt as ASS_DT from fpo_qry_deci a, fpo_status b where nvl(sent,0)=0 and id=?1")
//	
//		List<Deciqryfpostatus> getdeciqry(Long id);
	
	public interface Deciqryfpostatus {
	    //Long getid();    
	    String getCIN_NO();
	    Long getDECI_CD();
	    String getQRY_TYPE();
	    Date getASS_DT();
	    
	}
	
//	@Query(nativeQuery = true, value = "select a.cin_no as CIN_NO,a.deci_cd as DECI_CD,a.qry_type as QRY_TYPE,b.ass_dt as ASS_DT from fpo_qry_deci a, fpo_status b where "
//			+ "a.cin_no=?1 and a.cin_no=b.cin_no and a.id in (select max(id) from fpo_qry_deci c where nvl(sent,0)=0 and c.cin_no=a.cin_no "
//			+ "and c.qry_type not in ('E1','E2','E3','E4'))")
	@Query(nativeQuery = true, value = "select a.cin_no as CIN_NO,a.deci_cd as DECI_CD,a.qry_type as QRY_TYPE,b.ass_dt as ASS_DT from fpo_qry_deci a, fpo_status b where "
			+ "a.cin_no=:cinno and a.cin_no=b.cin_no and a.id in (select max(id) from fpo_qry_deci c where c.cin_no=a.cin_no "
			+ "and c.qry_type not in ('E1','E2','E3','E4'))")
		List<Deciqryfpostatus> getdeciqry1(@Param("cinno") String cinno);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE Fpoqrydeci SET SENT = 1 where nvl(sent,0)=0 and ID =:id")
	void updatedeciqry(@Param("id") Long id);
}
