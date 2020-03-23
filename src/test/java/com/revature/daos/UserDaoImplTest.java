package com.revature.daos;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.revature.models.User;

import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {


    private Session mockSession;

    private Transaction mockTransaction;
 

    
	@Before
	public void setUp() {

		mockSession = mock(Session.class);
		mockTransaction = mock(Transaction.class);
	}
	
	@Test
	@Transactional(propagation=Propagation.SUPPORTS)
	public void testCreateUser() {
	    User testUser;
		testUser = new User(1, "test");
		when(mockSession.save(testUser)).thenReturn(1);
		int pk = (int) mockSession.save(testUser);
		mockTransaction.commit();
		assertEquals(1, pk);
	}
}
