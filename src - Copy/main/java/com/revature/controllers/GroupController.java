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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;
import com.revature.services.UserService;

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
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	@GetMapping("/group/{group_id}")
	public ResponseEntity<String> getOne(@PathVariable int group_id) {
		Group g = gService.getGroupById(group_id);

		try {
			return ResponseEntity.ok().body("{" + "\"Group\": " + mapper.writeValueAsString(g) + "}");
		} catch (JsonProcessingException e) {
			// return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	// Get one group by passcode
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
