package com.revature.controllers;

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
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.UserService;

@CrossOrigin
@RestController
public class UserController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UserService uService;

	//New User
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody String spotify_id){
		User u = new User(spotify_id);
		uService.createUser(u);
		try {
			return ResponseEntity.ok().body("{ \"User\": "+mapper.writeValueAsString(u)+"}");
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
			return ResponseEntity.ok().body("{ \"Users\": "+mapper.writeValueAsString(users)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
	
	//Get one user by ID
	@GetMapping("/user/{appUser_id}")
	public ResponseEntity<String> getUserById(@PathVariable("appUser_id") int id) {
		User u =  uService.getUserById(id);
		List<Group> groups = uService.groupsByUser(u.getId());
		try {
			return ResponseEntity.ok().body( "{ \"User\": "+mapper.writeValueAsString(u)+" , "+"\"groups\": "+mapper.writeValueAsString(groups)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	//Get user by Spotify id, with associated groups
	@GetMapping("/user/spot/{spotify_id}")
	public ResponseEntity<String>  getUserBySpotifyId(@PathVariable("spotify_id") String spotify_id) {
		User u =  uService.getUserBySpotifyId(spotify_id);
		List<Group> groups = uService.groupsByUser(u.getId());
		try {
			return ResponseEntity.ok().body( "{ \"User\": "+mapper.writeValueAsString(u)+" , "+"\"groups\": "+mapper.writeValueAsString(groups)+"}");
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
			return ResponseEntity.ok().body("{ \"Users\": "+mapper.writeValueAsString(users)+"}");
		} catch (JsonProcessingException e) {
			//return exception message on failure
			return ResponseEntity.ok().body(e.getMessage());
		}
	}

	//Update User
	@PutMapping("/user/update/{appUser_id}")
	public ResponseEntity<?> updateUser(@PathVariable("appUser_id") int appUser_id) {
		User u = uService.getUserById(appUser_id);
		if (u == null) return ResponseEntity.badRequest().body(null);
		uService.updateUser(u);
		return ResponseEntity.ok().body("User updated!");
	}
	
	//add user tp group
	@PutMapping("/user/{appUser_id}")
	public ResponseEntity<?> addUserToGroup(@PathVariable("appUser_id") int appUser_id, 
			@RequestBody int groupId) 
	{
			User u = uService.getUserById(appUser_id);
			if (u == null) return ResponseEntity.badRequest().body("could not add user to group");
			uService.addUserToGroup(u, groupId);
			return ResponseEntity.ok().body(null);
	}
	
	//remove user from group
	@DeleteMapping("/user")
	public ResponseEntity<?> removeUserFromGroup(@PathVariable("appUser_id") int appUser_id, @RequestBody int groupId) {
			User u = uService.getUserById(appUser_id);
			if (u == null) return ResponseEntity.badRequest().body(null);
			 uService.removeUserFromGroup( u,  groupId);
			 return ResponseEntity.ok().body(null);
	}
	

}
