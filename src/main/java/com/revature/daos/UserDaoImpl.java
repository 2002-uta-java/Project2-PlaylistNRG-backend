package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		String hql = "from User";
		List<User> users = s.createQuery(hql).list();
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<User> getUsersByGroupId(int groupId) {
		// TODO: groupId->userId->user
		// might need fancy joincolumn annotations
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
	public int createUser(User u) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int pk = (int) s.save(u);
		tx.commit();
		return pk;
	}

	@Override
	public void updateUser(User u) {
		Session s = sf.getCurrentSession();
		String hql = "update User set id = :id,"
				+ "spotify_id = :spotifyId,"
				+ "password = :password "
				+ "where id = :id";
		// continuing...
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
