package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.models.TopTrack;
import com.revature.services.TopTrackService;

@CrossOrigin
@RestController
public class TopTrackController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private TopTrackService tService;

	// set a user's top tracks (takes array of spotify track id)

	@PostMapping("/toptracks/{appUser_id}")
	public ResponseEntity<String> setTopTracks(@PathVariable("appUser_id") int appUser_id,
		@RequestBody String  top_track_arr)
	{
		//clear tracks
		tService.deleteTopTracksByUserId(appUser_id);
		
		try {
			//parse array of id
			List<TopTrack> arr = mapper.readValue(top_track_arr, new TypeReference<List<TopTrack>>(){});
			
			for(TopTrack index : arr) {
				int pk = tService.createTopTrack(new TopTrack(index.getSpotifyTrackId(),index.getSpotifyPopularity()));
				tService.addTopTrackByUserId(pk, appUser_id);
			}
			return ResponseEntity.ok().body(null);
		} catch (JsonParseException e) {
			return ResponseEntity.ok().body(e.getMessage());
		} catch (JsonMappingException e) {
			return ResponseEntity.ok().body(e.getMessage());
		} catch (IOException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
	
	@GetMapping("/toptracks/{appUser_id}")
	public ResponseEntity<String> setTopTracks(@PathVariable("appUser_id") int appUser_id){
		List<String> spot_ids = tService.getUsersSpotifyTracks(appUser_id);
		try {
			return ResponseEntity.ok().body( "{ \"SpotifyTracks\": "+mapper.writeValueAsString(spot_ids)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}

		
	}
	
	// Get a group's top tracks.
	@GetMapping("/toptracks/group/{group_id}")
	public ResponseEntity<String> getGroupTopTracks(@PathVariable("group_id") int appGroup_id){
		List<TopTrack> spot_ids = tService.getTopTracksByGroupId(appGroup_id);
		
		try {
			return ResponseEntity.ok().body( "{ \"SpotifyTracks\": "+mapper.writeValueAsString(spot_ids)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}


}
