package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.RequestedTrackDao;
import com.revature.daos.UserDao;
import com.revature.models.RequestedTrack;

@Service
public class RequestedTrackService {
	
	@Autowired
	private RequestedTrackDao rDao;
	
	@Autowired
	private UserDao uDao;
	
	public List<RequestedTrack> getRequestedTracksByGroupId(int groupId) {
		List<Integer> userIds = uDao.getUserIdsByGroupId(groupId);
		List<RequestedTrack> rTracks = new ArrayList<>();
		
		if (userIds == null) {
			return null;
		}
		else {
			for (int uId: userIds) {
				List<RequestedTrack> userRTracks = rDao.getRequestedTracksByUserId(uId);
				
				for (RequestedTrack rTrack: userRTracks) { // may have multiple requested tracks per user
					rTracks.add(rTrack);
				}
			}
		}
		
		return rTracks;
	}
	
	public int createRequestedTrack(RequestedTrack r) {
		return rDao.createRequestedTrack(r);
	}
	
	public void updateRequestedTrack(RequestedTrack r) {
		rDao.updateRequestedTrack(r);
	}
	
}
