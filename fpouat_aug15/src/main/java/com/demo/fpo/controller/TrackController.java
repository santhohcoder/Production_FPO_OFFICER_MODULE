package com.demo.fpo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fpo.model.FpoTrackDetails;
import com.demo.fpo.repos.FpoTrackDetailsRepo;

@Controller
public class TrackController {

	@Autowired
	FpoTrackDetailsRepo fpoTrackDetailsRepo;

	@GetMapping(value = "/trackarticle")
	public ModelAndView trackArticle() {
		return new ModelAndView("article/track-article");
	}

	@GetMapping(value = "/articleDetails")
	public void articleDetails(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject jsonObj = new JSONObject();
		try {
			String articleId = request.getParameter("articleId");
			Optional<FpoTrackDetails> trackDetails = fpoTrackDetailsRepo.findById(articleId);
			jsonObj.put("trackDetails",
					new JSONObject(trackDetails.isPresent() ? trackDetails.get() : new FpoTrackDetails()));
			jsonObj.put("status", trackDetails.isPresent() ? true : false);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
