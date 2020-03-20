package com.revature;

import java.io.File;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class Driver {
	
	public static void main(String[] args) {
		System.out.println("booting app...");

		//ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		
//		System.out.println("after ac");
//		
//		UserDao uDao = (UserDao) ac.getBean("userDaoImpl");
//		
//		System.out.println("before new_user");
//		User u1 = new User("randomId");
//		
//		uDao.createUser(u1);
//		List<User> users = uDao.getAllUsers();
//		for(User u: users) {
//			System.out.println(u);
//		}
		
	}

}
