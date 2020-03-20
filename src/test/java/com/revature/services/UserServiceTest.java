package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService us;
	
	@Mock
	private UserDao ud;
	
	@Test
	public void testCreation() {
		User user = new User("test");
		when(ud.createUser(user)).thenReturn(1);
		assertTrue(us.createUser(user) == 1);
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		User user = null;
		when(ud.createUser(user)).thenReturn(0);
		assertFalse(us.createUser(user) == 1);
	}
	
	@Test
	public void getUserByValidId() {
		when(ud.getUserById(1)).thenReturn(new User(1, "test"));
		User expected = new User(1, "test");
		assertEquals(expected, us.getUserById(1));
	}
	
	@Test
	public void getUserByInvalidId() {
		when(ud.getUserById(0)).thenReturn(null);
		assertNull(us.getUserById(0));
	}
	
	

}
