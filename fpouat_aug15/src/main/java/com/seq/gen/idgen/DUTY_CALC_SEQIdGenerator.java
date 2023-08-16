package com.seq.gen.idgen;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DUTY_CALC_SEQIdGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select DUTY_CALC_ID from seq_tab");

			PreparedStatement stmt = connection.prepareStatement("update seq_tab set DUTY_CALC_ID=? where id=?");
			if (rs.next()) {
				int id = rs.getInt(1) + 1;
				Long valId = Long.valueOf(id);
				stmt.setLong(1, valId);
				stmt.setLong(2, 1L);
			//    int i = stmt.executeUpdate();
				return valId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
