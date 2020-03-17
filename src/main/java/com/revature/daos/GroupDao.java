package com.revature.daos;

import java.util.List;

import com.revature.models.Group;

public interface GroupDao {
	
	public Group getGroupById(int groupId);
	public void createGroup(Group g);
	// getAllGroups?, deleteGroup?

}
