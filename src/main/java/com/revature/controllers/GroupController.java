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
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.Group;
import com.revature.services.GroupService;

@CrossOrigin
@RestController
public class GroupController {

	@Autowired
	private GroupService gService;

	ObjectMapper mapper = new ObjectMapper();

	// New Group
	@PostMapping("/group")
	public ResponseEntity<String> newGroup(@RequestBody String object) {
		try {
			Group g = mapper.readValue(object, Group.class);
			gService.createGroup(g);
			return ResponseEntity.ok().body("{ \"Group\": " + mapper.writeValueAsString(g) + "}");
		} catch (IOException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	// Get all groups
	@GetMapping("/group")
	public ResponseEntity<String> getAll() {
		List<Group> groups = gService.getAllGroups();
		try {
			return ResponseEntity.ok().body("{ \"Groups\": " + mapper.writeValueAsString(groups) + "}");
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}

	}

	// Get one group by id
	@GetMapping("/group/{groupId}")
	public ResponseEntity<String> getOne(@PathVariable int groupId) {
		Group g = gService.getGroupById(groupId);

		try {
			return ResponseEntity.ok().body("{" + "\"Group\": " + mapper.writeValueAsString(g) + "}");
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	// Get one group by pass code
	@GetMapping("/group/pass/{passcode}")
	public ResponseEntity<String> getByPasscode(@PathVariable String passcode) {
		Group g = gService.getGroupByPasscode(passcode);

		try {
			return ResponseEntity.ok().body("{" + "\"Group\": " + mapper.writeValueAsString(g) + "}");
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

}
