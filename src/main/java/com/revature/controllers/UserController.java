package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.models.User;
import com.revature.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService uService;
	
	@PostMapping("/new_user")
	@ResponseStatus(value=HttpStatus.CREATED)
	@ResponseBody
	public User registerUser(@RequestParam("spotify_id") String spotify_id) {
		User u = new User(spotify_id);
		uService.createUser(u);
		return u;
	}
	
	@GetMapping("/all_users")
	@ResponseBody
	public List<User> allUsers(){
		return uService.getAllUsers();
	}

	@GetMapping("/user_by_id")
	@ResponseBody
	public User userbyID(@RequestParam("appUser_id") int id) {
		return uService.getUserById(id);
	}


	@GetMapping("/user_by_spotify")//nope
	@ResponseBody
	public User userbyspotID(@RequestParam("spotify_id") String spotify_id) {
		return uService.getUserBySpotifyId(spotify_id);
	}


	@GetMapping("/users_by_group")
	@ResponseBody
	public List<User> groupUsers(@RequestParam("group_id") int groupId) {
		return uService.getUsersByGroupId(groupId);
	}


	@GetMapping("/update_user")
	@ResponseBody
	public boolean  UserUpdate(@RequestParam("appUser_id") int appUser_id) {
		User u = uService.getUserById(appUser_id);
		if (u == null) return false;
		uService.updateUser(u);
		return true;
	}
	
	//TODO: testing
	@PostMapping("/user_to_group")
	@ResponseBody
	public boolean usertoGroup(@RequestParam("appUser_id") int appUser_id, 
			@RequestParam("group_id") int groupId) {
			User u = uService.getUserById(appUser_id);
			if (u == null) return false;
			uService.addUserToGroup(u, groupId);
			return true;
	}
	
	@PostMapping("/remove_user")
	@ResponseBody
	public boolean deleteUser(@RequestParam("appUser_id") int appUser_id, 
			@RequestParam("group_id") int groupId) {
			User u = uService.getUserById(appUser_id);
			if (u == null) return false;
			 uService.removeUserFromGroup( u,  groupId);
			 return true;
	}
	

}
