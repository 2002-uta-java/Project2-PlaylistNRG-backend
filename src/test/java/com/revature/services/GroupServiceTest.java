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
import com.revature.daos.GroupDao;
import com.revature.models.Group;
import com.revature.services.GroupService;


@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {
	
	@InjectMocks
	private GroupService gs;
	
	@Mock
	private GroupDao gd;
	
	@Test
	public void testCreation() {
		Group group = new Group("testGroup", "test", 1);
		when(gd.createGroup(group)).thenReturn(1);
		assertTrue(gs.createGroup(group) == 1);
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		Group group = null;
		when(gd.createGroup(group)).thenReturn(0);
		assertFalse(gs.createGroup(group) == 1);
	}
	
	@Test
	public void getGroupByValidId() {
		when(gd.getGroupById(1)).thenReturn(new Group(1,"testGroup", "test", 1));
		Group expected = new Group(1, "testGroup", "test", 1);
		assertEquals(expected, gs.getGroupById(1));
	}
	
	@Test
	public void getGroupByInvalidId() {
		when(gd.getGroupById(0)).thenReturn(null);
		assertNull(gs.getGroupById(0));
	}
	
	

}


