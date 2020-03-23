package com.revature.daos;

import java.util.List;

import com.revature.models.RequestedTrack;

public interface RequestedTrackDao {

	public int createRequestedTrack(RequestedTrack r);
	public void updateRequestedTrack(RequestedTrack r);
	public List<RequestedTrack> getRequestedTracksByUserId(int id);
	
}
