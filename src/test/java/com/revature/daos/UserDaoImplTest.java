package com.revature.daos;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoImplTest {
	
	//private UserDao ud = new UserDaoImpl();
	@Mock
	private SessionFactory sf;
	
	
		
	/*
	@BeforeClass
	public static void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection()){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	*/
}
