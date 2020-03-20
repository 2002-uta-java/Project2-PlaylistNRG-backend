package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.RequestedTrackDao;

import com.revature.models.RequestedTrack;
import com.revature.services.RequestedTrackService;


@RunWith(MockitoJUnitRunner.class)
public class RequestedTrackServiceTest {
	
	private List<RequestedTrack> tracks;
	
	@InjectMocks
	private RequestedTrackService rts;
	
	@Mock
	private RequestedTrackDao rtd;
	
	@Test
	public void testCreation() {
		RequestedTrack rTrack = new RequestedTrack("test", 1, 0, null);
		when(rtd.createRequestedTrack(rTrack)).thenReturn(1);
		assertTrue(rtd.createRequestedTrack(rTrack) == 1);
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		RequestedTrack rTrack = null;
		when(rtd.createRequestedTrack(rTrack)).thenReturn(0);
		assertFalse(rts.createRequestedTrack(rTrack) == 1);
	}
	
	@Test
	public void getRequestedTrackByValidId() {
		RequestedTrack track = new RequestedTrack(1, "test", 1, 1, "test");
		tracks.add(track);
		when(rtd.getRequestedTracksByUserId(1)).thenReturn(tracks);

		List<RequestedTrack> expected = (List<RequestedTrack>) new RequestedTrack(1, "test", 1, 1, "test");
		assertEquals(expected, rts.getRequestedTracksByUserId(1));
	}
	
	@Test
	public void getRequestedTrackByInvalidId() {
		when(rtd.getRequestedTracksByUserId(0)).thenReturn(null);
		assertNull(rtd.getRequestedTracksByUserId(0));
	}	
}

