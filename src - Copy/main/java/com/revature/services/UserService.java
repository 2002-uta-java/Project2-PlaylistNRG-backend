package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDao;
import com.revature.models.Group;
import com.revature.models.User;

@Service
public class UserService {

	@Autowired
	private UserDao uDao;
	
	public List<User> getAllUsers() {
		return uDao.getAllUsers();
	}
	
	public User getUserById(int id) {
		return uDao.getUserById(id);
	}
	
	public User getUserBySpotifyId(String  spotify_id) {
		return uDao.getUserBySpotId(spotify_id);
	}
	
	public List<User> getUsersByGroupId(int groupId) {
		List<Integer> userIds = uDao.getUserIdsByGroupId(groupId);
		List<User> users = new ArrayList<>();
		
		if (userIds == null) {
			return null;
		}
		else {
			for (int uId: userIds) {
				users.add(uDao.getUserById(uId));
			}
		}
		return users;	
	}
	
	public List<Group> groupsByUser(int id){
		return uDao.getAssociatedGroups(id);
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
