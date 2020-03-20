/**
 * 
 */
package com.revature.controllers;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.models.Group;
import com.revature.models.User;
import com.revature.services.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

	
	@Autowired
	private GroupService gService;
	
	@GetMapping("/all_groups")
	@ResponseBody
	public List<Group>allgroups() {
		return gService.getAllGroups();
	}
	
	@GetMapping("/groups_by_id")
	@ResponseBody
	public Group groupsid(@RequestParam("group_id")int groupId) {
		return gService.getGroupById(groupId);
	}
	
	@PostMapping("/new_group")
	@ResponseBody
	public int registerGroup(@RequestParam("Group")Group g){
		return gService. createGroup(g);
	}

}
