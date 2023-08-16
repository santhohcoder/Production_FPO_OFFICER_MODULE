package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoSecondDefaultQuery;

public interface FpoAddlQryRepo extends JpaRepository<FpoAddlQry, Long>{

	
	// --------------------------------------------------------- added BY VEEMAN on 22/04/2023 -------------------------------------
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where CIN_NO = :cinNo and qry_id in (select  max(qry_id) from fpo_addl_qry WHERE  CIN_NO = :cinNo  and qry_type !='D' )  and qry_type !='D'")
	List<FpoAddlQry> getAddQry(@Param("cinNo") String cinNo);
	// --------------------------------------------------------- ---------------------- -------------------------------------------- 

	
	//santhosh kanna,kani,veem
	
		@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where CIN_NO = :cinNo and\r\n"
				+ "			qry_id in (select  max(qry_id) from fpo_addl_qry WHERE \r\n"
				+ "			CIN_NO = :cinNo) ")
		List<FpoAddlQry> getAddQryDetails(@Param("cinNo") String cinNo);
		
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where cin_no = :cinNo and QRY_DEF_SLNO = :slno")
	List<FpoAddlQry> getFpoAddlQry(@Param("cinNo")  String cinNo, @Param("slno") String slno);

	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where cin_no = :cinNo and QRY_DEF_SLNO is null")
	List<FpoAddlQry> getFpoAddlQry(@Param("cinNo")  String cinNo);

	
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry where CIN_NO = :cinNo and QRY_TYPE = :qrytype and QRY_DEF_SLNO is null")
	List<FpoAddlQry> getArticleAwaitingQuery(@Param("cinNo")  String cinNo, @Param("qrytype") String qrytype);
	
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry where CIN_NO = :cinNo and QRY_DEF_SLNO is null and  qry_id = (select max(qry_id) from fpo_addl_qry where cin_no=:cinNo and qry_def_slno is null)")
	List<FpoAddlQry> getArticlenostage(@Param("cinNo")  String cinNo);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "delete from fpo_addl_qry where CIN_NO = :cinNo and qry_role=:role")
	void deleteFpoAddlQrybyCinno(@Param("cinNo")  String cinNo,@Param("role")  String role);
	
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where CIN_NO = :cinNo and rply_date is null order by qry_def_slno,qry_id")
	List<FpoAddlQry> getAllFpoaddlQuery(@Param("cinNo")  String cinNo);
	
	//written by santhosh for ordering  and generating correct additional dcall letter
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where CIN_NO = :cinNo and rply_date is null  and qry_role='PAC' order by qry_def_slno,qry_id")
	List<FpoAddlQry> getAllFpoaddlQueryonlyforPAC(@Param("cinNo")  String cinNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_addl_qry SET QRY_TYPE = 'D' where CIN_NO = :id ")
	void updateaddlqryStus(String id);

	
	@Modifying
	@Transactional
	//@Query(nativeQuery = true, value = "update  fpo_addl_qry set qry_reply = :generalQueryResponse , rply_off_id = :offId, rply_date =:date, rply_role =:role where cin_no = :cinNo and qry_def_slno is null and qry_id= (select max(qry_id) from fpo_addl_qry where cin_no = :cinNo and qry_def_slno is null)")
	//void updateAdditionalRespQry(@Param("cinNo")  String cinNo, @Param("generalQueryResponse") String generalQueryResponse,@Param("offId") String offId, @Param("date") Date date, @Param("role") String role);
	@Query(nativeQuery = true, value = "update  fpo_addl_qry set qry_reply = :generalQueryResponse , rply_off_id = :offId, upload_dt =:date, rply_role =:role, rply_date=:rplydt, ent_name=:entname, qry_mobileno=:mobile, qry_emailid=:mailid where cin_no = :cinNo and qry_def_slno is null and qry_id= (select max(qry_id) from fpo_addl_qry where cin_no = :cinNo and qry_def_slno is null)")
	void updateAdditionalRespQry(@Param("cinNo")  String cinNo, @Param("generalQueryResponse") String generalQueryResponse,@Param("offId") String offId, @Param("date") Date date, @Param("role") String role , @Param("rplydt") Date rplydt,@Param("entname")  String entname,@Param("mobile")  String mobile ,@Param("mailid")  String mailid);

	
	//prem 
	
	//@Query(nativeQuery = true, value = "SELECT * from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DEF_SLNO is null")
	@Query(nativeQuery = true, value = "select * from fpo_addl_qry  where CIN_NO = :cinNo and qry_id in (select  max(qry_id) from fpo_addl_qry WHERE  CIN_NO = :cinNo  and qry_type !='D' )  and qry_type !='D'")
	FpoAddlQry getFpoaddQry(@Param("cinNo")  String cinNo);
	

	@Query(nativeQuery = true, value = "select distinct(to_char(rply_date,'DD/MM/YYYY')) rply_date  from fpo_addl_qry where cin_no=:cinNo and QRY_ID= (select max(qry_id) from fpo_query where cin_no = :cinNo) and rply_date is not null")
	String getreplydt(@Param("cinNo")  String cinNo);

	
	@Query(nativeQuery = true, value = "select a.mobileno,a.emailid from dcallreply_otp a left join dcallqry_gen b on a.dcall_no=b.dcall_no where a.dcall_no=:dcalno and b.QRY_REPLY = 'Y' and a.id = (select max(id) from dcallreply_otp where dcall_no=:dcalno)")	
	String[][]  getUserDe(@Param("dcalno") String dcalno);
	
	
	
	@Query(nativeQuery = true, value = "select EMP_NAME from ops$dir.d_emp where user_id=:offid")
	String getusrname(@Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select distinct(to_char(upload_dt,'DD/MM/YYYY')) upload_dt  from fpo_addl_qry where cin_no=:cinNo and QRY_ID= (select max(qry_id) from fpo_query where cin_no = :cinNo) and rply_date is not null")
	String getupdt(@Param("cinNo")  String cinNo);
	
	
	
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null and qry_def_slno is null and qry_type=:stage")
	Long getaddcounoqry(@Param("cinNo")  String cinNo, @Param("stage") String stage);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null and qry_def_slno is null and qry_type=:stage and qry_role = 'PAC' ")
	Long getaddcounoqry1(@Param("cinNo")  String cinNo, @Param("stage") String stage);
	
	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null and qry_def_slno is null and qry_type=:stage and qry_role = 'PAC'")
	Long getaddcounoqry2(@Param("cinNo")  String cinNo, @Param("stage") String stage);

	@Query(nativeQuery = true, value = "SELECT count(*) from  fpo_addl_qry where CIN_NO = :cinNo and QRY_DATE is not null and qry_def_slno is null and qry_type=:stage and rply_date is not null")
	Long getcounoqryreply(@Param("cinNo")  String cinNo, @Param("stage") String stage);
}
