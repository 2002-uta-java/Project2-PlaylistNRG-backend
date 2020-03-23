package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.TopTrackDao;
import com.revature.daos.UserDao;
import com.revature.models.TopTrack;

@Service
public class TopTrackService {

	@Autowired
	private TopTrackDao tDao;
	
	@Autowired
	private UserDao uDao;
	
	public List<TopTrack> getTopTracksByGroupId(int groupId){
		List<Integer> userIds = uDao.getUserIdsByGroupId(groupId);
		List<TopTrack> topTracks = new ArrayList<>();
		
		if (userIds == null) {
			return null;
		}
		else {
			List<Integer> topTrackIds = new ArrayList<>();
			
			// Retrieve a list of the group's top track IDs (including duplicate tracks).
			// This midstep has to be done due to bridge tables.
			for (int uId: userIds) {
				List<Integer> userTopTrackIds = tDao.getTopTrackIdsByUserId(uId);
				
				for (int userTopTrackId: userTopTrackIds) { // may have multiple top tracks per user
					topTrackIds.add(userTopTrackId);
				}
			}
			
			// Retrieve a list of the group's top tracks using those IDs.
			for (int topTrackId: topTrackIds) { // may have multiple top tracks per user
				topTracks.add(tDao.getTopTrackByTrackId(topTrackId));
			}
		}
		
		return topTracks;
		
	}
	
	public int createTopTrack(TopTrack t) {
		return tDao.createTopTrack(t);
	}
	
	public void addTopTrackByUserId(int tId, int uId) {
		tDao.addTopTrackByUserId(tId, uId);
	}
	
	public void deleteTopTracksByUserId(int id) {
		// Note: You can delete a user/track in the toptrack_user bridge table, but cannot delete
		// the top track in the track table, because someone else might refer to that track.
		// Therefore, top tracks will stay in the database forever.	
		// In the long run, this could lead to massive unnecessary memory usage, but who cares.
		// Fix: Function that deletes all tracks that no users refer to.
		tDao.deleteTopTracksByUserId(id);
	}
	
	public List<String> getUsersSpotifyTracks(int uid){
		return tDao.getSpotifyTrackIdsByUserId(uid);
	}
}
