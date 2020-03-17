package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sf;

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<User> getAllUsers() {
		Session s = sf.getCurrentSession();
		List<User> users = s.createQuery("from User").list();
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<User> getUsersByGroupId(int groupId) {
		// TODO: groupId->userId->user
		// might use fancy joincolumn annotations
		List<User> users = null;
		
		
		return users;
	}

	@Override
	public User getUserById(int id) {
		// Will return null if id not in db.
		Session s = sf.getCurrentSession();
		User u = (User) s.get(User.class, id);
		return u;
	}

	@Override
	public void createUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserToGroup(User u, int groupId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserFromGroup(User u, int groupId) {
		// TODO Auto-generated method stub
		
	}
	
}
