package com.revature;


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

import com.revature.daos.TopTrackDao;

import com.revature.models.TopTrack;

import com.revature.services.TopTrackService;


@RunWith(MockitoJUnitRunner.class)
public class TopTrackServiceTest {
	
	@InjectMocks
	private TopTrackService tts;
	
	@Mock
	private TopTrackDao ttd;
	
	@Test
	public void testCreation() {
		TopTrack topTrack = new TopTrack("test", 1);
		when(ttd.createTopTrack(topTrack)).thenReturn(1);
		assertTrue(ttd.createTopTrack(topTrack) == 1);
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		TopTrack topTrack = null;
		when(ttd.createTopTrack(topTrack)).thenReturn(0);
		assertFalse(tts.createTopTrack(topTrack) == 1);
	}
	
	@Test
	public void getTopTrackByValidId() {
		when(ttd.getTopTrackByTrackId(1)).thenReturn(new TopTrack(1, "test", 1));

		TopTrack expected = new TopTrack(1, "test", 1);
		assertEquals(expected, tts.getTopTrackByTrackId(1));
	}
	
	@Test
	public void getTopTrackByInvalidId() {
		when(ttd.getTopTrackByTrackId(0)).thenReturn(null);
		assertNull(ttd.getTopTrackByTrackId(0));
	}	
}


