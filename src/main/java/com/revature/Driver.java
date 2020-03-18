package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.UserDao;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;

public class Driver {
	
	public static void main(String[] args) {
		System.out.println("booting app...");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		System.out.println("after ac");
		
		UserService uServ = (UserService) ac.getBean("userService");
		
		System.out.println("before new_user");
		User u1 = new User(1, "randomId");
		
		uServ.createUser(u1);
		List<User> users = uServ.getAllUsers();
		for(User u: users) {
			System.out.println(u);
		}
		
		System.out.println(uServ.getUserById(1));
		
		u1 = new User(1, "updatedId");
		uServ.updateUser(u1);
		System.out.println(uServ.getUserById(1));
		
		// Group testing.
		GroupService gServ = (GroupService) ac.getBean("groupService");
		
		Group g1 = new Group(1, "groupName", "12345", 1);
		gServ.createGroup(g1);
		Group gGet = gServ.getGroupById(1);
		System.out.println(g1);
		System.out.println(gGet);
		
		// Users/Groups
		uServ.addUserToGroup(u1, 1);
		System.out.println(uServ.getUsersByGroupId(1));
		
		// Top tracks testing.
		
		
	}

}
