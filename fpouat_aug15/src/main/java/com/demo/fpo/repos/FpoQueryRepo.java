package com.demo.fpo.repos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.model.FpoAddlQry;
import com.demo.fpo.model.FpoDefualtQuery;
import com.demo.fpo.model.FpoQuery;

@Repository
public interface FpoQueryRepo extends JpaRepository<FpoQuery, Long> {
	
	// --------------------------------------------------------- added BY VEEMAN on 22/04/2023 -------------------------------------------
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_addl_QRY SET qry_type='U'  where CIN_NO = :cinNo and qry_def_slno is null and Qry_ID= (select max(Qry_ID) from fpo_addl_QRY where CIN_NO = :cinNo and qry_def_slno is null  and qry_type is not null ) and qry_type is not null")
	void updaddnorply(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) FROM fpo_addl_qry where cin_no=:cinNo and Qry_ID=(select max(Qry_ID) from fpo_addl_QRY where CIN_NO = :cinNo and qry_def_slno is null  and qry_type is not null ) and qry_type is not null")
	int chkaddlqryvalid(@Param("cinNo") String cinNo);
	
	// --------------------------------------------------------- ---------------------------- --------------------------------------------
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE dcallqry_gen \r\n"
			+ "SET qry_reply = 'X' \r\n"
			+ "WHERE item_id = :item \r\n"
			+ "AND SUBSTR(dcall_no, 1, 3) = 'EAD'\r\n"
			+ "AND (SELECT COUNT(*) FROM dcallqry_gen WHERE item_id = :item AND SUBSTR(dcall_no, 1, 3) = 'EAD') > 	0")
	void updQryReplyAsY(@Param("item") String item);
	
	
	@Modifying
	@Transactional
	//@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply, rply_off_id = :usrid, rply_date =:date, rply_role =:role where CIN_NO = :cinNo and ITEM_NO = :item_no and ITEM_NO is not null and qry_typ !='D'")
	//void updateresp(@Param("cinNo") String cinNo, @Param("item_no") Long item_no, @Param("reply") String reply, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role);
	@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply, rply_off_id = :usrid, upload_dt=:date, rply_role =:role where CIN_NO = :cinNo and ITEM_NO = :item_no and ITEM_NO is not null and qry_typ !='D'")
	void updateresp(@Param("cinNo") String cinNo, @Param("item_no") Long item_no, @Param("reply") String reply, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role);

	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QUERY SET QRY_TYP = 'D' where CIN_NO = :id ")
	void updateqryStus(String id);

	@Query(nativeQuery = true, value = "select count(*) from fpo_QUERY where CIN_NO = :cinNo and qry_typ !='D'")
	Long getcouqry(@Param("cinNo") String cinNo);
	

	@Query(nativeQuery = true, value = "select * from fpo_query  where ITEM_ID = :itemID and QRY_DATE is not null")
	List<FpoQuery> getoffidQry(@Param("itemID") String itemID);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_query where item_id = :itemId  ")
	int checkQueryRaisedf(@Param("itemId") String itemId);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_query where item_id = :itemId and rply_date is not null and qry is null and qry_no=(select max(qry_no) from fpo_query where item_id=:itemId and rply_date is not null and qry is null)")
	int checkQueryRaisedt(@Param("itemId") String itemId);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_addl_qry where item_id = :itemId and rply_date is not null and QRY_DEF_SLNO is null and qry_id=(select max(qry_id) from fpo_addl_qry where item_id=:itemId and qry_def_slno is null and rply_date is not null)")
	int checkAdditionQueryRaisedt(@Param("itemId") String itemId);
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_addl_qry where item_id = :itemId")
	int checkAdditionQueryRaisedf(@Param("itemId") String itemId);
	
	@Modifying
	@Transactional
	//@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply , RPLY_DEP_CMTS = :dpcmts , rply_off_id = :usrid, rply_date =:date, rply_role =:role  where CIN_NO = :cinNo and ITEM_NO is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO is null  and qry_typ !='D' ) and qry_typ !='D'")
	//void updatedefQry(@Param("cinNo") String cinNo, @Param("reply") String reply, @Param("dpcmts") String dpcmts, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role );
	@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply , RPLY_DEP_CMTS = :dpcmts , rply_off_id = :usrid, upload_dt =:date, rply_role =:role  where CIN_NO = :cinNo and ITEM_NO is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO is null  and qry_typ !='D' ) and qry_typ !='D'")
	void updatedefQry(@Param("cinNo") String cinNo, @Param("reply") String reply, @Param("dpcmts") String dpcmts, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role );

	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply, rply_off_id = :usrid, upload_dt=:date, rply_role =:role, ent_name=:recpname, qry_emailid=:mailid, qry_mobileno=:mobileno,rply_date=:replydt where CIN_NO = :cinNo and ITEM_NO = :item_no and ITEM_NO is not null and qry_typ !='D'")
	void updateresp2(@Param("cinNo") String cinNo, @Param("item_no") Long item_no, @Param("reply") String reply, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role,@Param("recpname") String recpname,@Param("mobileno") String mobileno,@Param("mailid") String mailid,@Param("replydt") Date replydt);

	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QUERY SET rply = :reply , RPLY_DEP_CMTS = :dpcmts , rply_off_id = :usrid, upload_dt =:date,rply_date=:replydt, rply_role =:role, ent_name=:recpname, qry_emailid=:mailid, qry_mobileno=:mobileno where CIN_NO = :cinNo and ITEM_NO is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO is null  and qry_typ !='D' ) and qry_typ !='D'")
	void updatedefQry2(@Param("cinNo") String cinNo, @Param("reply") String reply, @Param("dpcmts") String dpcmts, @Param("usrid") String usrid, @Param("date") Date date, @Param("role") String role,@Param("replydt") Date replydt,@Param("recpname") String recpname,@Param("mobileno") String mobileno,@Param("mailid") String mailid );

	
	@Query(nativeQuery = true, value = "select distinct(to_char(rply_date,'DD/MM/YYYY')) rply_date  from fpo_query where cin_no=:cinNo and id = (select max(id) from fpo_query where cin_no = :cinNo)")
	String getreplydt(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select distinct(to_char(upload_dt,'DD/MM/YYYY')) upload_dt  from fpo_query where cin_no=:cinNo and id = (select max(id) from fpo_query where cin_no = :cinNo)")
	String getupdt(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select a.mobileno,a.emailid from dcallreply_otp a left join dcallqry_gen b on a.DCALL_NO=b.DCALL_NO where a.DCALL_NO=:DCALL_NO and b.QRY_REPLY = 'Y' and a.id = (select max(id) from dcallreply_otp where DCALL_NO=:DCALL_NO)")
	String[][]  getuserde(@Param("DCALL_NO") String DCALL_NO);

	@Query(nativeQuery = true, value = "select * from fpo_query where cin_no=:cinNo and id = (select max(id) from fpo_query where cin_no = :cinNo)")
	FpoQuery getOffDet(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select EMP_NAME from ops$dir.d_emp where user_id=:offid")
	String getusrname(@Param("offid") String offid);


	
	@Query(nativeQuery= true, value="select *  from fpo_query where cin_no=:cinNo and id = (select max(id) from fpo_query where cin_no = :cinNo)")	
	List<FpoQuery> getquerydetails(@Param("cinNo") String cinNo);
	
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update fpo_QUERY SET qry_typ='U'  where CIN_NO = :cinNo and ITEM_NO is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO is null  and qry_typ is null ) and qry_typ is null")
	void updnorply(@Param("cinNo") String cinNo);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update dcallqry_gen SET qry_reply='U'  where item_id = :itemid and ID= (select max(id) from dcallqry_gen where qry_reply is null and item_id = :itemid)")
	void updnorplydcall(@Param("itemid") String itemid);

	
	@Query(nativeQuery = true, value = "select DEF_QUERY from fpo_QUERY where CIN_NO = :cinNo and item_no is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and item_no is null  and qry_typ !='D' ) and qry_typ !='D'")
	String getfpoDefQry(@Param("cinNo") String cinNo);
	/*
	 * @Query(nativeQuery = true, value =
	 * "select REPLY_DATE from fpo_QUERY where CIN_NO = ?1 and item_no is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = ?1 and item_no is null)"
	 * ) String getfpoDefQry(String cinNo);
	 */
	
	@Query(nativeQuery = true, value = "select dcall_no FROM dcallqry_gen where CIN_NO = :cinNo and ID= (select max(ID) from dcallqry_gen where CIN_NO = :cinNo and cus_site=:cuSite) and cus_site=:cuSite")
	String getdcallno(@Param("cinNo") String cinNo,@Param("cuSite") String cuSite);
	
	@Query(nativeQuery = true, value = "select rply_date from fpo_query where CIN_NO = :cinNo and ID= (select max(ID) from fpo_query where CIN_NO = :cinNo and cus_site=:cusite and qry_typ !='D' ) and cus_site=:cusite and qry_typ !='D'")
	String getrplydt(@Param("cinNo") String cinNo,@Param("cusite") String cusite);
	
	
	@Query(nativeQuery = true, value = "select * from fpo_DEFAULT_QUERY order by serial_no")
	List<FpoDefualtQuery> getdata();
	
	
//	@Query(nativeQuery = true, value = "select * from fpo_QUERY a where CIN_NO = ?1 and ITEM_NO is not null and id=(select max(id) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO=a.ITEM_NO  and qry_typ !='D' ) and qry_typ !='D' ORDER by a.ITEM_NO ")
//	List<FpoQuery> getfpoqueryandDin(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select a.ITEM_NO,a.QRY,b.ITEM_DESC,a.rply from fpo_QUERY a RIGHT join FPO_ITEM_DET b on a.CIN_NO = b.CIN_NO and a.ITEM_NO = b.ITEM_NO where a.CIN_NO = :cinNo and a.ITEM_NO is not null and a.id=(select max(id) from fpo_QUERY where CIN_NO = :cinNo and ITEM_NO=a.ITEM_NO  and qry_typ !='D' )  and qry_typ !='D'  ORDER by a.ITEM_NO ")
	List<Collection> getfpoqryandDesc(@Param("cinNo") String cinNo);
	
// latest code
	@Query(nativeQuery = true, value = "SELECT i.item_no,item_desc,cth,qry from fpo_QUERY q INNER JOIN FPO_ITEM_DET i ON  i.ITEM_NO = q.ITEM_NO  where i.CIN_NO = :cinNo and q.qry_no = :queryNo and ASS_DT is not null")
	List<Object> getMemoData(@Param("cinNo") String cinNo, @Param("queryNo") Long queryNo);

	@Query(nativeQuery = true, value = "SELECT item_desc from fpo_QUERY q INNER JOIN FPO_ITEM_DET i ON  i.ITEM_NO = :itemNo  where i.CIN_NO = :cinNo and q.qry_no = :queryNo  and qry_typ !='D' and ASS_DT is null")
	List<String> getQueryOnCinQnoItemNo(@Param("cinNo") String cinNo,  @Param("queryNo") Long queryNo, @Param("itemNo") Long itemNo);

	@Query(nativeQuery = true, value = "select max(qry_no) from fpo_query")
	Long getMaxQueryNo();
	
	@Query(nativeQuery = true, value = "select count(*) from fpo_query  where cin_no=:cinno and qry_off_id=:offid  and qry_typ !='D' ")
	Long getcou(@Param("cinno") String cinno, @Param("offid") String offid);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from fpo_query  where cin_no=:cinno and qry_off_id=:offid and item_no is null  and qry_typ !='D' " )
	void delrec(@Param("cinno") String cinno, @Param("offid") String offid);

	@Query(nativeQuery = true, value = "select max(item_no) from fpo_query where CIN_NO = :cinNo  and qry_typ !='D' ")
	Long getMaxItemNo(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select max(qry_no) from fpo_query where CIN_NO = :cinNo  and qry_typ !='D' ")
	Long getMaxQueryNoOnCinNo(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery=true, value = "select count(*) from fpo_item_det where updass_dt is null and cin_no=:cinNo  ")
	Long getstatusass(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select count(*) from fpo_query where CIN_NO = :cinNo and qry_typ !='D' ")
	Long getCountQueryNoOnCinNo(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select QRY  from fpo_QUERY where CIN_NO = :cinNo and qry_no = :queryNo and item_no is null  and qry_typ !='D'  ")
	String getRemarks(@Param("cinNo") String cinNo, Long queryNo);
	
	@Query(nativeQuery = true, value = "select qry_emailid  from fpo_QUERY where CIN_NO = :cinNo and qry_no = :queryNo and item_no is null   and qry_typ !='D' ")
	String getemail(@Param("cinNo") String cinNo, Long queryNo);
	
	@Query(nativeQuery = true, value = "select qry_mobileno from fpo_QUERY where CIN_NO = :cinNo and qry_no = :queryNo and item_no is null  and qry_typ !='D'  ")
	String getMobileNumber(@Param("cinNo") String cinNo, Long queryNo);
	
	@Query(nativeQuery = true, value = "select qry_emailid  from fpo_addl_qry where CIN_NO = :cinNo and qry_def_slno is null and qry_id=(select max(qry_id) from fpo_addl_qry where CIN_NO = :cinNo and qry_def_slno is null) ")
	String getemailaddlqry(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select qry_mobileno from fpo_addl_qry where CIN_NO = :cinNo  and qry_def_slno is null and qry_id=(select max(qry_id) from fpo_addl_qry where CIN_NO = :cinNo and qry_def_slno is null) ")
	String getMobileNumberaddlqry(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select QRY  from fpo_QUERY where CIN_NO = :cinNo and item_no = :itemNumber and item_no is not null and qry_no=(select max(qry_no) from fpo_query where CIN_NO = :cinNo and item_no = :itemNumber  and qry_typ !='D' )   and qry_typ !='D' ")
	String getQueryOnCinAndItemNo(@Param("cinNo") String cinNo, Long itemNumber);

	@Query(nativeQuery = true, value = "select DEF_QUERY  from fpo_QUERY where CIN_NO = :cinNo and qry_no = :queryNo and item_no is null  and qry_typ !='D' ")
	String getDefualtQuery(@Param("cinNo") String cinNo, Long queryNo);
	
	@Query(nativeQuery = true, value = "select  CASE WHEN SUBSTR(DEF_QUERY, -1) = '7' THEN mark ELSE mark END AS mark\r\n"
			+ "FROM fpo_QUERY \r\n"
			+ "WHERE CIN_NO = :cinNo \r\n"
			+ "  AND qry_no = :queryNo \r\n"
			+ "  AND item_no IS NULL \r\n"
			+ "  and qry_typ != 'D' ")
	String getOthDocName(@Param("cinNo") String cinNo, Long queryNo);
	
	@Query(nativeQuery = true, value = "select rply from fpo_QUERY where CIN_NO = :cinNo and item_no is null and ID= (select max(ID) from fpo_QUERY where CIN_NO = :cinNo and item_no is null  and qry_typ !='D' )  and qry_typ !='D' ")
	String getDefaultQueryReply(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from fpo_query  where CIN_NO = :cinNo  and qry_typ !='D' ")
	List<FpoQuery> getAllData(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select address from ops$dir.fpo_site_info  where fpo_site = :cussite")
	List<String> getfpositeaddr(@Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select email from ops$dir.fpo_site_info  where fpo_site = :cussite")
	List<String> getfpositemail(@Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select phone from ops$dir.fpo_site_info  where fpo_site = :cussite")
	List<String> getfpositeph(@Param("cussite") String cussite);
	
	@Query(nativeQuery = true, value = "select visit_hours from ops$dir.fpo_site_info  where fpo_site = :cussite")
	List<String> getfpositewh(@Param("cussite") String cussite);

	@Query(nativeQuery = true, value = "select * from fpo_query  where CIN_NO = :cinNo and id in (select  max(id) from fpo_query WHERE  CIN_NO = :cinNo  and qry_typ !='D' )  and qry_typ !='D' ")
	List<FpoQuery> getDefQry(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from fpo_query  where CIN_NO = :cinNo and item_no = :itemNumber and qry_no=(select max(qry_no) from fpo_query where CIN_NO = :cinNo and item_no = :itemNumber  and qry_typ !='D' )  and qry_typ !='D' ")
	List<FpoQuery> getQryOnCinAdItem(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);

	@Query(nativeQuery = true, value = "select TO_CHAR(QRY_DATE,'DD-MON-YYYY HH24:MI:SS') from fpo_query where cin_no = :cinNo and ITEM_NO =:itemNumber and qry_no = (select max(qry_no) from fpo_query where cin_no = :cinNo and ITEM_NO =:itemNumber  and qry_typ !='D' )  and qry_typ !='D' ")
	Date getQryDt(@Param("cinNo") String cinNo, Long itemNumber);
	
	@Query(nativeQuery = true, value = "select rply_dep_cmts from fpo_query where CIN_NO = :cinNo and item_no is null and id in (select max(id) from fpo_query WHERE CIN_NO = :cinNo and item_no is null  and qry_typ !='D' )  and qry_typ !='D' ")
	String getDepcmts(@Param("cinNo") String cinNo);
    
	@Query(nativeQuery = true, value = "select RPLY  from fpo_QUERY where CIN_NO = :cinNo and item_no = :itemNumber and item_no is not null and qry_no=(select max(qry_no) from fpo_query where CIN_NO = :cinNo and item_no = :itemNumber  and qry_typ !='D' )  and qry_typ !='D' ")
	String getRplyOnCinAndItemNo(@Param("cinNo") String cinNo, @Param("itemNumber") Long itemNumber);
	
	@Query(nativeQuery = true, value = "select QRY  from fpo_QUERY where CIN_NO = :cinNo and qry_typ = 'P' and item_no is null ")
	String getAdditionalQuery(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_query where CIN_NO = :cinNo and qry_typ='P'")
	List<FpoQuery> getArticleAwaitingQuery(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select * from fpo_query  where item_id = :item_id and id in (select  max(id) from fpo_query WHERE  item_id = :item_id  and qry_typ !='D' )  and qry_typ !='D' ")
	List<FpoQuery> getDefQryByitemid(@Param("item_id") String item_id);
	
	@Query(nativeQuery = true, value = "SELECT x.ITEM_NO, x.ITEM_DESC, x.CTH, x.CTH_REVISED, x.GEN_CTH, x.NOU qty, x.ASSVAL_INSFRT, x.BCD_AMT, x.IGST_AMT, x.SW_AMT, y.other_dt_amt,x.DUTY,x.CURR_RATE \r\n"
			+ "FROM (SELECT CIN_NO, ITEM_NO, ITEM_DESC, CTH, CTH_REVISED, GEN_CTH, NOU, ASSVAL_INSFRT, BCD_AMT, IGST_AMT, SW_AMT, DUTY,CURR_RATE \r\n"
			+ "      FROM fpo_item_det \r\n"
			+ "      WHERE CIN_NO= :cinNo) x\r\n"
			+ "LEFT JOIN (SELECT SUM(duty_amt) other_dt_amt, CIN_NO, item_no \r\n"
			+ "           FROM FPO_ITEM_DET_OTHDUTY \r\n"
			+ "           WHERE CIN_NO= :cinNo  \r\n"
			+ "           GROUP BY cin_no, item_no) y \r\n"
			+ "ON x.CIN_NO = y.CIN_NO AND y.item_no = x.item_no")
	String [] []  getOocItemDet(@Param("cinNo")String cinNo);

		@Query(nativeQuery = true, value = "select CODEDESC from ops$dir.PDI_MAIL_CLASS_CODES WHERE CODE=:CODE")
		String getmailDescptn(@Param("CODE") String mail_CLASS_CD);

		@Query(nativeQuery = true, value = "select Distinct(POST_ORG_NAME_SHORT) from ops$dir.PDI_OOE_CODES WHERE IMPC_CODE_SHORT =:CODE")
		String getOriginPost(@Param("CODE") String origpost_ORG_CD);

			@Query(nativeQuery = true, value = "select CATEGORY from ops$dir.PDI_NATURE_TRANS_CODES WHERE CODE=:CODE")
	String getCategoryByCode(@Param("CODE") String CODE);

			@Query(nativeQuery = true, value = "select SITE_NAME from ops$dir.OOE_SITES WHERE SITE_CODE=:cusite")
			String getSiteNameByCusite(@Param("cusite") String cusite);

			@Query(nativeQuery = true, value = "select  to_char(max(QRY_DATE),'DD/MM/YYYY')QRY_DATE from fpo_query where CIN_NO=:cinNo")
	String oocQueryDt(@Param("cinNo")String cinNo);

			@Query(nativeQuery = true, value = "select max(QRY_DATE) from fpo_addl_qry where CIN_NO=:cinNo")
	String oocaddQueryDt(@Param("cinNo")String cinNo);

			@Query(nativeQuery = true, value = "select  to_char(max(RPLY_DATE),'DD/MM/YYYY')RPLY_DATE from fpo_query where CIN_NO=:cinNo")
			String oocRplDt(@Param("cinNo")String cinNo);

			@Query(nativeQuery = true, value = "SELECT distinct (x.cin_no) cinNo, to_char(x.cin_dt,'DD/MM/YYYY') cindt, substr(x.postingdt,9,2)||'/'||substr(x.postingdt,6,2)||'/'||substr(x.postingdt,0,4) postdt, x.item_id itemId, x.send_cntry_cd send_cntry_cd, x.codedesc codedesc, x.category catgry, x.tot_ass_val tot_ass_val, y.no_items noItems, y.totwt totwt, y.currcd currcd, x.job_no jobNo, x.job_dt jobDt, x.priority prirty     \r\n"
			+ "FROM \r\n"
			+ "	(SELECT t1.cin_no, t1.cin_dt, t1.postingdt, t1.item_id, t1.send_cntry_cd, t5.codedesc, t3.category, t1.tot_ass_val, t1.job_no, t1.job_dt, priority \r\n"
			+ "	FROM ops$dir.d_emp_roles t4, ops$dir.pdi_nature_trans_codes t3, ops$dir.pdi_mail_class_codes t5 , fpo_main t1 \r\n"
			+ "	WHERE t1.cus_site= :cusite \r\n"
			+ "	AND t1.cin_no = :cin_no\r\n"
			+ "	AND t1.nature_type_cd=t3.code  \r\n"
			+ "	AND t1.mail_class_cd=t5.code \r\n"
			+ "	AND set_aside is null\r\n"
			+ "	) x,\r\n"
			+ "	(SELECT count(*) no_items, cin_no, sum(netwt) totwt, max(currcd) currcd \r\n"
			+ "	FROM fpo_item_det \r\n"
			+ "	WHERE cus_site = :cusite \r\n"
			+ "	AND cin_no = :cin_no\r\n"
			+ "	GROUP BY cin_no\r\n"
			+ "	) y \r\n"
			+ "WHERE x.cin_no=y.cin_no")
	String [] []  getOocMain(@Param("cin_no")String cin_No, @Param("cusite")String cusite);

				@Query(nativeQuery = true, value = "SELECT sum(x.ASSVAL_INSFRT), sum(x.BCD_AMT), sum(x.IGST_AMT), sum(x.SW_AMT), sum(y.other_dt_amt),sum(x.DUTY) \r\n"
			+ "FROM (SELECT CIN_NO, ITEM_NO, ITEM_DESC, CTH, CTH_REVISED, GEN_CTH, NOU, ASSVAL_INSFRT, BCD_AMT, IGST_AMT, SW_AMT, DUTY \r\n"
			+ "      FROM fpo_item_det \r\n"
			+ "      WHERE CIN_NO=:cinNo) x\r\n"
			+ "LEFT JOIN (SELECT SUM(duty_amt) other_dt_amt, CIN_NO, item_no \r\n"
			+ "           FROM FPO_ITEM_DET_OTHDUTY \r\n"
			+ "           WHERE CIN_NO=:cinNo  \r\n"
			+ "           GROUP BY cin_no, item_no) y \r\n"
			+ "ON x.CIN_NO = y.CIN_NO AND y.item_no = x.item_no"
			
			)
	String  [] [] getOocTotDuties(@Param("cinNo")String cinNo);

				@Query(nativeQuery = true, value = "SELECT  \r\n"
						+ "       fpo_status.SUP_ID, \r\n"
						+ "       to_char(fpo_status.OOC_DT, 'DD/MM/YYYY') AS OOC_DT, \r\n"
						+ "       to_char(fpo_status.OOC_DT, 'HH24:MI:SS') AS OOC_TM,\r\n"
						+ "       ops$dir.d_emp.emp_name\r\n"
						+ "FROM fpo_status \r\n"
						+ "JOIN ops$dir.d_emp \r\n"
						+ "ON fpo_status.SUP_ID = ops$dir.d_emp.emp_no \r\n"
						+ "WHERE fpo_status.CIN_NO =:cinNo")
				String [][] getOocDtandId(@Param("cinNo")String cinNo);
	
	
	
}
