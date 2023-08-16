package com.demo.fpo.apicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.fpo.apibean.FpoMainBean;
import com.demo.fpo.apirepository.FpoDocDetBeanRepository;
import com.demo.fpo.apirepository.FpoItemDetBeanRepository;
import com.demo.fpo.apirepository.FpoMainBeanRepository;

@RestController
public class ApiController {

	@Autowired
	FpoMainBeanRepository fpoMainBeanRepository;

	@Autowired
	FpoItemDetBeanRepository fpoItemDetBeanRepository;

	@Autowired
	FpoDocDetBeanRepository fpoDocDetBeanRepository;

	@GetMapping(value = "/rmsfresh")
	public void rmsArticles(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("messageType","RMSITM1");
		header.put("eventNo",1);
		header.put("eventCode","EAD");
		jsonObj.put("header", header);
		try {
			List<FpoMainBean> articles = fpoMainBeanRepository.getRmsNotSentArticles();
			List<String> articleIds = new ArrayList<>();
			for (FpoMainBean article : articles) {
				articleIds.add(article.getItemId());
				article.setItemWiseDetails(fpoItemDetBeanRepository.getItemsDetailsByItemId(article.getItemId()));
				article.setDocDetails(fpoDocDetBeanRepository.getDocDetailsByItemId(article.getItemId()));
			}
			if (!articles.isEmpty()) {
				int partitionSize = 1000;
				List<List<String>> partitions = new LinkedList<>();
				for (int i = 0; i < articleIds.size(); i += partitionSize) {
					partitions.add(articleIds.subList(i, Math.min(i + partitionSize, articleIds.size())));
				}
				for (List<String> list : partitions) {
					fpoMainBeanRepository.updateRmsSent(list);
					fpoItemDetBeanRepository.updateRmsSent(list);
				}
			}	
			if (articles.size() > 0)
			   jsonObj.put("articleDetails", articles);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
