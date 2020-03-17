package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	public List<User> getAllUsers();
	public List<User> getUsersByGroupId(int groupId);
	public User getUserById(int id);
	public void createUser(User u);
	public void updateUser(User u);
	// deleteUser?
	
	public void addUserToGroup(User u, int groupId);
	public void removeUserFromGroup(User u, int groupId);
	
}