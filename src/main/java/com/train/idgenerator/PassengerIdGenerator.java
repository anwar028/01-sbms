package com.train.idgenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PassengerIdGenerator implements IdentifierGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		
		String prefix="P_1000";
		String suffix="";
	
         try {
			JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
			Connection connection = jdbcConnectionAccess.obtainConnection();
			Statement statement = connection.createStatement();
			                                                                                   
			String sql="";
			ResultSet rs = statement.executeQuery("SELECT id FROM passenger_id_gen");  

			if(rs.next()) 
			{ 
				int seq = rs.getInt("id");           
				 suffix = String.valueOf(seq);    
			    System.out.println(suffix);
				  
	               statement.executeUpdate("UPDATE passenger_id_gen SET id = id + 1");  //
	              
	               
	         }
		} catch (SQLException e) {
             e.printStackTrace();
		}
         return prefix+suffix;
	}

}
