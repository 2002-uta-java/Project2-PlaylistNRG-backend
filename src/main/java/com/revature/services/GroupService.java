package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.GroupDao;
import com.revature.models.Group;

@Service
public class GroupService {

	@Autowired
	private GroupDao gDao;
	
	public Group getGroupById(int groupId) {
		return gDao.getGroupById(groupId);
	}
	
	public int createGroup(Group g) {
		return gDao.createGroup(g);
	}
}
