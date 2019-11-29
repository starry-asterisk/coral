package com.coral.www;


import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
public class test {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testConnection(){
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.216:1521:xe","TEST1","1234")) {
			System.out.println("\nsuccess");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
