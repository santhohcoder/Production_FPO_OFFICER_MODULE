package com.demo.fpo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.fpo.model.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
	
	@Query(nativeQuery = true, value = "select off_id, to_char(feedback_dt, 'DD/MM/YYYY HH24:MI:SS'), feedback_type, feedback_text from feedback where off_id != 'RPLYLINK' order by  to_char(feedback_dt, 'DD/MM/YYYY HH24:MI:SS') desc")
	List<String>  feedbacknsmval();
	
	@Query(nativeQuery = true, value = "select a.name,a.dcall_no,b.emailid,b.mobileno ,to_char(a.feedback_dt, 'DD/MM/YYYY HH24:MI:SS'), a.feedback_type, a.feedback_text from feedback a  left join \r\n"
			+ "dcallreply_otp b on a.dcall_no = b.dcall_no\r\n"
			+ "where a.off_id = 'RPLYLINK' and b.id=(select max(id) from dcallreply_otp where dcall_no = a.dcall_no) order by  to_char(a.feedback_dt, 'DD/MM/YYYY HH24:MI:SS')desc")
	List<String>  feedbackrplylnkval();

}
