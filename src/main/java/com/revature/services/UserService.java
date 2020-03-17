package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDao;
import com.revature.models.User;

@Service
public class UserService {

	@Autowired
	private UserDao uDao;
	
	public List<User> getAllUsers() {
		return uDao.getAllUsers();
	}
	
	public getUserById(int id) {
		return uDao.getUserById(id);
	}
	
	public List<User> getUsersByGroupId(int groupId) {
		// TODO: groupId->userId->user
		// joining tables might cause mapping complications, doing it the caveman way
		// 1. look at bridge table (group_user)
		// 		- "select user_id from group_user"
		// 2. Gather list of userids in particular groupId.
		// 		- "where group_id = groupId"
		// 3. for each user, obtain their data, add to list.
		// 		- from User where id = user_id (for each i)
		// 4. return list.
		//		- .list?
		// get list of users -> call get userById for each one.
		// MOVE THIS TO SERVICES.
		
		
		return null;
		
	}
	
	public int createUser(User u) {
		return uDao.createUser(u);
	}
	
	public void updateUser(User u) {
		uDao.updateUser(u);
	}
	
	public void addUserToGroup(User u, int groupId) {
		uDao.addUserToGroup(u, groupId);
	}
	
	public void removeUserFromGroup(User u, int groupId) {
		uDao.removeUserFromGroup(u, groupId);
	}
}
