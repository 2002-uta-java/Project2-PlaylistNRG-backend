package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.TopTrack;
import com.revature.services.TopTrackService;

@CrossOrigin
@RestController
public class TopTrackController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private TopTrackService tService;

	// set a user's top tracks (takes array of spotify track id)

	@PostMapping("/toptracks/{appUserID}")
	public ResponseEntity<String> setTopTracks(@PathVariable("appUserID") int appUserID,
		@RequestBody String  topTrackArr)
	{
		//clear tracks
		tService.deleteTopTracksByUserId(appUserID);
		
		try {
			//parse array of id
			List<TopTrack> arr = mapper.readValue(topTrackArr, new TypeReference<List<TopTrack>>(){});
			
			for(TopTrack index : arr) {
				int pk = tService.createTopTrack(new TopTrack(index.getSpotifyTrackId(),index.getSpotifyPopularity()));
				tService.addTopTrackByUserId(pk, appUserID);
			}
			return ResponseEntity.ok().body(null);
		} catch (IOException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
	
	@GetMapping("/toptracks/{appUserID}")
	public ResponseEntity<String> setTopTracks(@PathVariable("appUserID") int appUserID){
		List<String> spotifyIds = tService.getUsersSpotifyTracks(appUserID);
		try {
			return ResponseEntity.ok().body( "{ \"SpotifyTracks\": "+mapper.writeValueAsString(spotifyIds)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}

		
	}
	
	// Get a group's top tracks.
	@GetMapping("/toptracks/group/{group_id}")
	public ResponseEntity<String> getGroupTopTracks(@PathVariable("group_id") int appGroupId){
		List<TopTrack> spotifyIds = tService.getTopTracksByGroupId(appGroupId);
		
		try {
			return ResponseEntity.ok().body( "{ \"SpotifyTracks\": "+mapper.writeValueAsString(spotifyIds)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}


}
