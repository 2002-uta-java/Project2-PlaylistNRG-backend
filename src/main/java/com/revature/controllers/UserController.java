package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService uService;

	//New User
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody String spotify_id) {
		User u = new User(spotify_id);
		uService.createUser(u);
		return ResponseEntity.ok().body(u);
	}

	//Get all users
	@GetMapping("/user")
	public ResponseEntity<List<User>>  getAllUsers(){
		List<User>  users = uService.getAllUsers();
		return ResponseEntity.ok().body(users);
	}
	
	//Get one user by ID, plus Associated groups
	@GetMapping("/user/{appUser_id}")
	public ResponseEntity<User> getUserById(@PathVariable("appUser_id") int id) {
		User u =  uService.getUserById(id);
		return ResponseEntity.ok().body(u);
	}

	//One user by Spotify id
	@GetMapping("/user/spot/{spotify_id}")
	public ResponseEntity<User>  getUserBySpotifyId(@PathVariable("spotify_id") String spotify_id) {
		User u =  uService.getUserBySpotifyId(spotify_id);
		return ResponseEntity.ok().body(u);
	}

	//One users in a group
	@GetMapping("/user/g/{group_id}")
	public ResponseEntity<List<User>> getUsersByGroupId(@PathVariable("group_id") int groupId) {
		List<User> users =  uService.getUsersByGroupId(groupId);
		return ResponseEntity.ok().body(users);
	}
	
//	//Get groups associated to this user
//	@GetMapping("/user/ag/{appUser_id}")
//	public ResponseEntity<List<Group>> getAssociatedGroups(@PathVariable("appUser_id") int appUser_id) {
//	 List<Group> groups = uService.groupsByUser(appUser_id);
//	 return ResponseEntity.ok().body(groups);
//	}

	//Update User
	@PutMapping("/user/update/{appUser_id}")
	public ResponseEntity<?>  updateUser(@PathVariable("appUser_id") int appUser_id) {
		User u = uService.getUserById(appUser_id);
		if (u == null) return ResponseEntity.badRequest().body(null);
		uService.updateUser(u);
		return ResponseEntity.ok().body("User updated!");
	}
	
	//TODO: testing
	@PutMapping("/user/{appUser_id}")
	public ResponseEntity<?> addUserToGroup(@PathVariable("appUser_id") int appUser_id, @RequestBody int groupId) {
			User u = uService.getUserById(appUser_id);
			if (u == null) return ResponseEntity.badRequest().body(null);
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
