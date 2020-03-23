package com.revature.controllers;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.RequestedTrack;
import com.revature.services.RequestedTrackService;


@CrossOrigin
@RestController
public class RequestedTrackController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private RequestedTrackService rService = new RequestedTrackService();
	
	//Create request
	@PostMapping("/request/{appUserId}")
	public ResponseEntity<String> addRequest(@PathVariable("appUserId") int appUserId,
			@RequestBody String  request){
		
		try {
			RequestedTrack g = mapper.readValue(request, RequestedTrack.class);
			g.setEmployeeId(appUserId);
			rService.createRequestedTrack(g);
			return ResponseEntity.ok().body(null);
		} catch (IOException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}		
	}
	
	//Get Requests by group
	@GetMapping("/request/{groupId}")
	public ResponseEntity<String> getRequestedTracksbyID(@PathVariable("groupId") int groupId){
		List<RequestedTrack> reqs = rService.getRequestedTracksByGroupId(groupId);
		
		try {
			return ResponseEntity.ok().body( "{ \"RequestedTracks\": "+mapper.writeValueAsString(reqs)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
	
	
	//Update Request
	@PutMapping("/request/{reqTrackId}")
	public ResponseEntity<String> updateRequestedTrack(@PathVariable("reqTrackId") int reqTrackId,
			@RequestBody String request){
		
		try {
			RequestedTrack r = mapper.readValue(request, RequestedTrack.class);
			r.setEmployeeId(reqTrackId);
			rService.updateRequestedTrack(r);
			return ResponseEntity.ok().body(null);
		} catch (IOException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}	

	}
	
	
	//Delete Request
	@PutMapping("/request/d/{reqTrackId}")
	public ResponseEntity<String> deleteRequestedTrack(@PathVariable("reqTrackId") int reqTrackId){
		rService.deleteRequestedTrack(reqTrackId);
		return ResponseEntity.ok().body(null);
	}

}