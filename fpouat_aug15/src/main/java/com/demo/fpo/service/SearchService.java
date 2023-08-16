package com.demo.fpo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.demo.fpo.model.FPO_MAIN;
import com.demo.fpo.repos.FpoQueryDecisionRepo;

@Service
@Component
public class SearchService {

	@Autowired
	FpoQueryDecisionRepo fpoQueryDecisionRepo;

	public List<FPO_MAIN> setQueue(List<FPO_MAIN> fpoMainList) {
		List<FPO_MAIN> fpoMainModifiedList = new ArrayList<FPO_MAIN>();
		for (FPO_MAIN fpoMain : fpoMainList) {
			List<String> fpoDcs = fpoQueryDecisionRepo.getQueueStatus(fpoMain.getId());
			if (null != fpoDcs && !fpoDcs.isEmpty()) {
				if (fpoDcs.get(0) == "P2") {
					fpoMain.setQueue("From EAD");
				}
				fpoMainModifiedList.add(fpoMain);
			}
		}
		return fpoMainModifiedList;
	}

}