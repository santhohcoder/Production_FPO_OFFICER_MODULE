package com.demo.fpo.NsmLsmRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fpo.NsmLsmModel.EDIT_FPO_SITE_ALLOT;
import com.demo.fpo.NsmLsmModel.FPO_SITE_ALLOT;
import com.demo.fpo.NsmLsmModel.FPO_SITE_INFO;

@Repository
public interface EDIT_FPO_ALLOT_REPO extends JpaRepository<EDIT_FPO_SITE_ALLOT, Long> {
	
	@Query(nativeQuery = true, value = "select  doc_name from ops$dir.EDIT_FPO_SITE_ALLOT where to_char(UPD_DATE,'DD/MM/YYYY HH24:MI:SS') =:uptodate")
	String getprocesspdf(@Param("uptodate") String uptodate);
	
	@Query(nativeQuery = true, value = "select a.site_code, a.letter_air,a.letter_sal,a.letter_sea,a.ems_air,a.ems_sal,a.ems_sea,a.parcel_air,a.parcel_sal,a.parcel_sea,a.off_id, to_char(a.upd_date,'DD/MM/YYYY HH24:MI:SS'), a.doc_name from ops$dir.edit_fpo_site_allot a left join ops$dir.fpo_site_allot b on a.site_code = b.site_code   \r\n"
			+ " where b.site_code=:sitecode and b.off_id=:offid  order by  a.upd_date desc")
	List<String> getprocessallval(@Param("sitecode") String sitecode, @Param("offid") String offid);
	
	@Query(nativeQuery = true, value = "select a.site_code, a.letter_air,a.letter_sal,a.letter_sea,a.ems_air,a.ems_sal,a.ems_sea,a.parcel_air,a.parcel_sal,a.parcel_sea,a.off_id, to_char(a.upd_date,'DD/MM/YYYY HH24:MI:SS'), a.doc_name from ops$dir.edit_fpo_site_allot a left join ops$dir.fpo_site_allot b on a.site_code = b.site_code order by  a.upd_date desc")
	List<String> getprocessallval1();
}
