package com.revature.daos;

import java.util.List;

import com.revature.models.RequestedTrack;

public interface RequestedTrackDao {

	public List<RequestedTrack> getAllRequestedTracksByGroupId(int groupId);
	// ^ this will be more complicated, involves bridge table
	// steps: groupId -> userIds -> trackIds
	public int createRequestedTrack(RequestedTrack r);
	public void updateRequestedTrack(RequestedTrack r);
	
}
