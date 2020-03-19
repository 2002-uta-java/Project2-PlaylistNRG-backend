package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.daos.UserDao;
import com.revature.models.Group;
import com.revature.models.RequestedTrack;
import com.revature.models.TopTrack;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.RequestedTrackService;
import com.revature.services.TopTrackService;
import com.revature.services.UserService;

public class Driver {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("src//main//webapp//WEB-INF//beans.xml");

		
		UserService uServ = (UserService) ac.getBean("userService");
		
		// Test code
		User u1 = new User(1, "randomId");
		uServ.createUser(u1);
		List<User> users = uServ.getAllUsers();
		for(User u: users) {
			System.out.println(u);
		}
		
	}

}
