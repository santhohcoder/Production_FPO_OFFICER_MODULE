package com.demo.fpo.repos;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.apibean.SpeedPostNo;
import com.demo.fpo.model.DCALLQRY_GEN;
import com.demo.fpo.model.FPO_SETASIDE;
import com.demo.fpo.model.FpoPenalAmend;

@Repository
public interface FpoDcallQRYRepo extends JpaRepository<DCALLQRY_GEN, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET mail_sent_dt=:curdt, mail_to=:to, mail_cc = :tomailid where DCALL_NO = :dcallno ")
	void dcallqryupd(@Param("to") String to, @Param("tomailid") String tomailid, @Param("curdt") Date curdt, @Param("dcallno") String dcallno);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where item_id=:itemid and  to_char(trunc(gen_dt),'DD-MM-YYYY')=:gendt")
	Long getdcallqrycou(@Param("itemid") String itemid, @Param("gendt") String gendt);

	@Query(nativeQuery = true, value = "select * from dcallqry_gen where cin_no = :cinNo and id=(select min(id) from dcallqry_gen where cin_no = :cinNo)")	
	DCALLQRY_GEN getFirstDCallNumberByCinNo(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from dcallqry_gen where cin_no = :cinNo and id=(select max(id) from dcallqry_gen where cin_no = :cinNo)")
	DCALLQRY_GEN getAdditionalDCallNumberByCinNo(@Param("cinNo") String cinNo);

	@Query(nativeQuery = true, value = "select * from dcallqry_gen where cin_no = :cinNo and id=(select max(id) from dcallqry_gen where cin_no = :cinNo)")
	DCALLQRY_GEN getdCallNumberByCinNo(@Param("cinNo") String cinNo);
	
	//added by veem and santhosh
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where cin_no = :cinNo and id=(select max(id) from dcallqry_gen where cin_no = :cinNo)")	
	int getBreadcrumbDetails(@Param("cinNo") String cinNo);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen where cin_no = :cinNo ")	
	int countdcallletter(@Param("cinNo") String cinNo);
	
	//
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET viewcou = :count where cin_no = :cinNo  and DCALL_NO = :dcall_no")
	void dcallqrypdfviewcountupdate(@Param("count") int count, @Param("cinNo") String cinNo, @Param("dcall_no") String dcall_no);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET mobile_no_2=:mobile ,SMSCOU = :count where DCALL_NO = :dcallno ")
	void updateDcallMobileNO(@Param("dcallno") String dcallno, @Param("mobile") String mobile, @Param("count") int count);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET MAIL_CC=:email ,EMAILCOU = :count where DCALL_NO = :dcallno ")
	void updateDcallEmail(@Param("dcallno") String dcallno, @Param("email") String email, @Param("count") int count);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET PRINTCOU = :count where cin_no = :cinno and DCALL_NO = :dcall_no ")
	void dcallqrypdfprintcountupdate(@Param("count") int count, @Param("cinno") String cinno, @Param("dcall_no") String dcall_no);

	@Query(nativeQuery = true, value = "select * from dcallqry_gen where DCALL_NO = :dcallno and id=(select max(id) from dcallqry_gen where dcall_no=:dcallno)")
	DCALLQRY_GEN getdCALLQRY_GENBydcallno(String dcallno);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0")
	String getprintcount(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and SMSCOU>0")
	String getsmscount(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and EMAILCOU>0")
	String getemailcount(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);


	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0")
	String getprintcountPush(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and SMSCOU>0 and PRINTCOU>0")
	String getsmscountPush(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and EMAILCOU>0 and PRINTCOU>0")
	String getemailcountPush(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0 and PRINTCOU is null")
	String getprintcountPendingPrint( @Param("offId")  String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and SMSCOU>0 and PRINTCOU is null")
	String getsmscountPendingPrint(  @Param("offId") String offId);

	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and EMAILCOU>0 and PRINTCOU is null")
	String getemailcountPendingPrint( @Param("offId")  String offId);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where  b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= :month and PRINTCOU>0")
	String getprintcounthistory(@Param("month") String month, @Param("offId") String offId);

	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= :month and SMSCOU>0")
	String getsmscounthistory(@Param("month") String month, @Param("offId") String offId);

	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where   b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= :month and EMAILCOU>0")
	String getemailcounthistory(@Param("month") String month, @Param("offId") String offId);
	
	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU is null")
	String getpendingprintcount(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);

//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null))) where id=1")
//	String getfirstquerytoday(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null))) where id>1")
//	String getaddlquerytoday(@Param("fromdate") String fromdate, @Param("todate") String todate,  @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(distinct a.item_id) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null))")
//	String gettotalarticlestoday(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and  PRINTCOU is null and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null))) where id=1")
//	String getpendingfirstquerytoday( @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and  PRINTCOU is null and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null))) where id>1")
//	String getpendingaddlquerytoday( @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select  count(distinct a.item_id) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and  PRINTCOU is null")
//	String getpendingtotalarticles( @Param("offId") String offId);
//	
//	@Query(nativeQuery = true, value = "select count(*) from (select  ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0) where id=1")
//	String getfirstqueryfilter(@Param("fromdate") String fromdate, @Param("todate") String todate,  @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select  ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0) where id>1")
//	String getaddlqueryfilter(@Param("fromdate") String fromdate, @Param("todate") String todate,  @Param("offId") String offId);
//	
//	@Query(nativeQuery = true, value = "select count(distinct a.item_id) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where 0=(select count(*) from fpo_curr_que where item_id=a.item_id and curr_que='P8') and b.OFF_ID=:offId and trunc(a.GEN_DT) between to_date (:fromdate, 'dd/mm/yyyy') AND TO_DATE (:todate, 'dd/mm/yyyy') and (1<=(select count(*) from fpo_query where item_id=a.item_id and RPLY_DATE is null) or 1<=(select count(*) from fpo_addl_qry where item_id=a.item_id and RPLY_DATE is null)) and PRINTCOU>0")
//	String gettotalarticlesfilter(@Param("fromdate") String fromdate, @Param("todate") String todate, @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where  b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= ?1 and PRINTCOU is null")
//	String getpendingprintcounthistory(@Param("month") String month,  @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= ?1) where id=1")
//	String getpendingfirstqueryhistory(@Param("month") String month,  @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(*) from (select ROW_NUMBER() OVER(PARTITION BY a.ITEM_ID ORDER BY a.ID) as id from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where  b.OFF_ID=:offId and to_char(a.GEN_DT , 'yyyy-mm') <= ?1) where id>1")
//	String getpendingaddlqueryhistory(@Param("month") String month,  @Param("offId") String offId);
//
//	@Query(nativeQuery = true, value = "select count(distinct a.item_id) from dcallqry_gen a join fpo_main b on (a.item_id=b.item_id)  left join OPS$DIR.d_cntry_cd dtc on (b.SEND_CNTRY_CD = dtc.CNTRY_CD) left join OPS$DIR.pdi_mail_class_codes pdm on (b.MAIL_CLASS_CD=pdm.code)  left join ops$dir.pdi_nature_trans_codes pdi on (pdi.code=b.nature_type_cd) left join fpo_qry_din fqd on (a.item_id=fqd.item_id) where  b.OFF_ID=?2 and to_char(a.GEN_DT , 'yyyy-mm') <= ?1")
//	String getpendingtotalarticles(@Param("month") String month,  @Param("offId") String offId);


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET SPEEDPOST_NO = :speedpostno,SPEEDPOST_DT = :dt  where id = :id ")
	void updateSpeedPostNo(@Param("id") Long id, @Param("speedpostno") String speedpostno, @Param("dt") Date dt);

	@Query(nativeQuery = true, value = "select SPEEDPOST_NO from dcallqry_GEN where SPEEDPOST_DELI_STATUS is null")
	List<SpeedPostNo> getspeedno();



	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET SPEEDPOST_DELI_STATUS = :status  where SPEEDPOST_NO = :speedpostno ")
	void updateSpeedPostDeliStatus(@Param("speedpostno") String speedpostno, @Param("status") String status);

	@Query(nativeQuery = true, value = "select distinct a.CIN_NO from dcallqry_GEN a join FPO_SPEEDDELISTATUS b on (a.SPEEDPOST_NO=b.SPEEDPOST_NO) where a.SPEEDPOST_NO is not null and (a.SPEEDPOST_DELI_STATUS = 'Not Delivered' or a.SPEEDPOST_DELI_STATUS = 'Item Delivered') and b.RECD_DT < sysdate-15 and  a.ASS_MVMNT is null")
	List<String> getCinNoFromDCALLQRY_GEN();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE DCALLQRY_GEN SET ASS_MVMNT = 'Y' , ASS_MVMNT_DT = :date where CIN_NO = :cinno ")
	void updateASS_MVMNT(@Param("cinno") String cinno, @Param("date") Date date);

	@Query(nativeQuery = true, value = "SELECT genurl FROM dcallqry_gen where DCALL_NO = :dcallno and id=(select max(id) from dcallqry_gen where dcall_no=:dcallno) ")
	String getsmsUrl(@Param("dcallno") String dcallno);

}
