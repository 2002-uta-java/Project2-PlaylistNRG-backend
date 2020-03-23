package com.revature.daos;

import java.util.List;

import com.revature.models.TopTrack;

public interface TopTrackDao {

	public List<Integer> getTopTrackIdsByUserId(int uId);
	public List<String> getSpotifyTrackIdsByUserId(int uId);
	public TopTrack getTopTrackByTrackId(int id);
	public int createTopTrack(TopTrack t);
	public void addTopTrackByUserId(int tId, int uId);
	public void deleteTopTracksByUserId(int id);
	
}
