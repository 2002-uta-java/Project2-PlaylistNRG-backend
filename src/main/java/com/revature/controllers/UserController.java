package com.revature.controllers;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.UserService;

@CrossOrigin
@RestController
public class UserController {
	ObjectMapper mapper = new ObjectMapper();
	private static final String USERSHEADER = "{ \"User\": ";
	
	@Autowired
	private UserService uService;

	//New User
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody String spotifyID){
		User u = new User(spotifyID);
		uService.createUser(u);
		try {
			return ResponseEntity.ok().body(USERSHEADER+mapper.writeValueAsString(u)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	//Get all users
	@GetMapping("/user")
	public ResponseEntity<String>  getAllUsers(){
		List<User>  users = uService.getAllUsers();
		try {
			return ResponseEntity.ok().body(USERSHEADER+mapper.writeValueAsString(users)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
	
	//Get one user by ID
	@GetMapping("/user/{appUserId}")
	public ResponseEntity<String> getUserById(@PathVariable("appUserId") int id) {
		User u =  uService.getUserById(id);
		List<Group> groups = uService.groupsByUser(u.getId());
		try {
			return ResponseEntity.ok().body( USERSHEADER+mapper.writeValueAsString(u)+" , "+"\"groups\": "+mapper.writeValueAsString(groups)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	//Get user by Spotify id, with associated groups
	@GetMapping("/user/spot/{spotifyID}")
	public ResponseEntity<String>  getUserBySpotifyId(@PathVariable("spotifyID") String spotifyID) {
		User u =  uService.getUserBySpotifyId(spotifyID);
		List<Group> groups = uService.groupsByUser(u.getId());
		try {
			return ResponseEntity.ok().body( USERSHEADER+mapper.writeValueAsString(u)+" , "+"\"groups\": "+mapper.writeValueAsString(groups)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}

	}

	//Get users in a group
	@GetMapping("/user/g/{group_id}")
	public ResponseEntity<String> getUsersByGroupId(@PathVariable("group_id") int groupId) {
		List<User> users =  uService.getUsersByGroupId(groupId);
		try {
			return ResponseEntity.ok().body(USERSHEADER+mapper.writeValueAsString(users)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	//Update User
	@PutMapping("/user/update/{appUserId}")
	public ResponseEntity<Object> updateUser(@PathVariable("appUserId") int appUserId) {
		User u = uService.getUserById(appUserId);
		if (u == null) return ResponseEntity.badRequest().body(Collections.emptyList());
		uService.updateUser(u);
		return ResponseEntity.ok().body("User updated!");
	}
	
	//add user tp group
	@PutMapping("/user/{appUserId}")
	public ResponseEntity addUserToGroup(@PathVariable("appUserId") int appUserId, 
			@RequestBody int groupId) 
	{
			User u = uService.getUserById(appUserId);
			if (u == null) return ResponseEntity.badRequest().body("could not add user to group");
			uService.addUserToGroup(u, groupId);
			return ResponseEntity.ok().body(null);
	}
	
	//remove user from group
	@DeleteMapping("/user/{appUserId}")
	public ResponseEntity removeUserFromGroup(@PathVariable("appUserId") int appUserId, @RequestBody int groupId) {
			User u = uService.getUserById(appUserId);
			if (u == null) 
				return ResponseEntity.badRequest().body(null);
			else
				uService.removeUserFromGroup( u,  groupId);
			
			 return ResponseEntity.ok().body(null);
	}
	

}
