package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		uServ.removeUserFromGroup(u1, 1);
		System.out.println(uServ.getUsersByGroupId(1));
		
		// Top tracks testing.
		
		// Creating elements without IDs (so auto-increment can do its magic).
		u1 = new User("randomId");
		User u2 = new User("randomId2");
		uServ.createUser(u1);
		uServ.createUser(u2);
		
		users = uServ.getAllUsers();
		for(User u: users) {
			System.out.println(u);
		}
		
		g1 = new Group("name", "passcode", 1);
		Group g2 = new Group("name2", "passcode2", 2);
		gServ.createGroup(g1);
		gServ.createGroup(g2);
		
		System.out.println(gServ.getGroupById(1));
		System.out.println(gServ.getGroupById(2));
		
		uServ.addUserToGroup(u1, 1);
		
		List<Group> groups = gServ.getAllGroups();
		for(Group g: groups) {
			System.out.println(g);
		}
		
		RequestedTrackService rServ = (RequestedTrackService) ac.getBean("requestedTrackService");
		
		RequestedTrack r1 = new RequestedTrack("string", 1, 1, "string");
		RequestedTrack r2 = new RequestedTrack("string2", 2, 2, "string2");
		rServ.createRequestedTrack(r1); //issue w/ foreign key
		rServ.createRequestedTrack(r2);
		r1 = new RequestedTrack(1, "name", 80, 1, "approved");
		rServ.updateRequestedTrack(r1);
		
		List<RequestedTrack> rTracks = rServ.getRequestedTracksByGroupId(1);
		for(RequestedTrack rT: rTracks) {
			System.out.println(rT);
		}
		
		TopTrackService tServ = (TopTrackService) ac.getBean("topTrackService");
		TopTrack t1 = new TopTrack("randomSpotId", 99);
		
		tServ.createTopTrack(t1);
		tServ.addTopTrackByUserId(1, 2);
		System.out.println(tServ.getTopTracksByGroupId(2));
//		tServ.deleteTopTracksByUserId(1);
		
		
		uServ.addUserToGroup(u2, 2);
		
		
	}

}
