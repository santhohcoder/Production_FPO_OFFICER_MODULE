package com.demo.fpo.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.fpo.apirepository.CusrspsentRepo;

import java.sql.Date;


@Service
public class CusrspsentService {
	@Autowired
	public CusrspsentRepo cusrspsentrepo;

	public void findById(Long id,Date sentdt, String cinno) {
		
		cusrspsentrepo.updatecusrsp(id,sentdt, cinno);
	}
}
