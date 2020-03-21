package com.revature.daos;

import java.util.List;

import com.revature.models.Group;

public interface GroupDao {
	
	public List<Group> getAllGroups();
	public Group getGroupById(int groupId);
	public Group getGroupByPasscode(String passcode);
	public int createGroup(Group g);
	// getAllGroups?, deleteGroup?

}
