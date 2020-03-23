package com.revature.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.RequestedTrackDao;

import com.revature.models.RequestedTrack;


@RunWith(MockitoJUnitRunner.class)
public class RequestedTrackServiceTest {
	

	
	@InjectMocks
	private RequestedTrackService rts;
	
	@Mock
	private RequestedTrackDao rtd;
	
	@Test
	public void testSuccessfulCreation() {
		RequestedTrack rTrack = new RequestedTrack("test", 1, 0, null);
		when(rtd.createRequestedTrack(rTrack)).thenReturn(1);
		assertTrue(rts.createRequestedTrack(rTrack) == 1);
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		RequestedTrack rTrack = null;
		when(rtd.createRequestedTrack(rTrack)).thenReturn(0);
		assertFalse(rts.createRequestedTrack(rTrack) == 1);
	}

}

