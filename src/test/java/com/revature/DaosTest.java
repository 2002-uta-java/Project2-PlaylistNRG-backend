package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.revature.models.Group;
import com.revature.models.TopTrack;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.RequestedTrackService;
import com.revature.services.TopTrackService;
import com.revature.services.UserService;

public class DaosTest {
	
	/*
	@Before
	public void setUp() {
		ac = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@After
	public void tearDown() {
		ac = null;
	}
	*/
//
//	@Mock
//	private ApplicationContext ac;
//	
//	@InjectMocks
//	private UserService us;
//	
//	@InjectMocks
//	private GroupService gs;
//	
//	@InjectMocks
//	private TopTrackService tts;
//	
//	@InjectMocks
//	private RequestedTrackService rts;
//	
//    @Test
//    public void testNewUser() {
//    	//UserService us = (UserService) ac.getBean("userService");
//		User u1 = new User("test");
//		//List<User> users = us.getAllUsers();
//		when(us.createUser(u1)).thenReturn(1);
//		assertTrue(us);
//    }
//    
    /*
    @Test
    public void testNewGroup() {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
    	GroupService gs = (GroupService) ac.getBean("groupService");
		Group g1 = new Group("test-group", "test-passcode", 1);
		List<Group> groups = gs.getAllGroups();
		gs.createGroup(g1);
		assertEquals(g1.getId() , groups.get(0).getId());
    }

    @Test
    public void testNewTopTrack() {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
    	TopTrackService tts = (TopTrackService) ac.getBean("topTrackService");
		TopTrack tt1 = new TopTrack("test-track", 1);
		List<TopTrack> topTracks = tts.getAllTopTracks();
		tts.createTopTrack(tt1);
		assertEquals(tt1.getId() , topTracks.get(0).getId());
    }
    
    */
	
	//when(ed.getEmployeeById(5)).thenReturn(new User("test")));
}
