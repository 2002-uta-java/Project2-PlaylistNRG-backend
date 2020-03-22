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
import com.revature.models.RequestedTrack;
import com.revature.models.TopTrack;
import com.revature.models.User;
import com.revature.services.RequestedTrackService;


@CrossOrigin
@RestController
public class RequestedTrackController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private RequestedTrackService rService = new RequestedTrackService();
	
	//Create request
	@PostMapping("/request/{appUser_id}")
	public ResponseEntity<String> addRequest(@PathVariable("appUser_id") int appUser_id,
			@RequestBody String  request){
		
		try {
			RequestedTrack g = mapper.readValue(request, RequestedTrack.class);
			g.setEmployeeId(appUser_id);
			System.out.println(g);
			rService.createRequestedTrack(g);
			return ResponseEntity.ok().body(null);
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		} catch (IOException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}		
	}
	
	//Update Request
	
	//Delete Request
	
	//Get Request by group
}