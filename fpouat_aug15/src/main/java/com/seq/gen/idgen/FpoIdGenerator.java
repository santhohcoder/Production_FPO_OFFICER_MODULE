package com.seq.gen.idgen;

import java.io.Serializable;




import org.hibernate.HibernateException;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.fpo.apicontroller.cdsController;
import com.demo.fpo.apirepository.FposubmitRepoImpl;



@Component
public class FpoIdGenerator implements IdentifierGenerator{
	
	
	@Autowired FposubmitRepoImpl fposubmitRepoImpl;
	
	@Autowired
	cdsController cController;
	
	//public static String generatedSequenceValue;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		/*
		 * String prefix = cController.uniqId; String suffix = "00";
		 */
		@SuppressWarnings("static-access")
		String beforeSuffix = cController.fpoSeqNo;
		
		if (Integer.parseInt(beforeSuffix) < 99999999 && Integer.parseInt(beforeSuffix) > 0)
			beforeSuffix = String.format("%08d", Integer.parseInt(beforeSuffix)+1);
		else
			beforeSuffix = String.format("%08d", Integer.parseInt("1"));
		
		//generatedSequenceValue = prefix + beforeSuffix + suffix;
		//System.out.println(generatedSequenceValue);
		/*
		 * Connection connection = session.connection();
		 * 
		 * try { Statement statement=connection.createStatement();
		 * 
		 * ResultSet rs=statement.
		 * executeQuery("select count(Department_Id) as Id from demo.Department");
		 * 
		 * if(rs.next()) { int id=rs.getInt(1)+101; String generatedId = prefix + new
		 * Integer(id).toString(); System.out.println("Generated Id: " + generatedId);
		 * return generatedId; } } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		//return generatedSequenceValue;
		return beforeSuffix;
	}
	
}
