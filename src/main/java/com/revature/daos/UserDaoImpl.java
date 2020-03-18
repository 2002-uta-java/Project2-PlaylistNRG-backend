package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

	@Transactional(propagation=Propagation.SUPPORTS) // is propagation needed?
	@Override
	public List<User> getAllUsers() {
		Session s = sf.getCurrentSession();
		String hql = "from User";
		List<User> users = s.createQuery(hql).list();
		return users;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public User getUserById(int id) {
		// Will return null if id not in db.
		Session s = sf.getCurrentSession();
		User u = (User) s.get(User.class, id);
		return u;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public int createUser(User u) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int pk = (int) s.save(u);
		tx.commit();
		return pk;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void updateUser(User u) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String hql = "update User set "
				+ "spotify_id = :spotifyId "
				+ "where id = :id";
		Query q = s.createQuery(hql);
		q.setParameter("id", u.getId());
		q.setParameter("spotifyId", u.getSpotifyId());
		
		q.executeUpdate();
		tx.commit();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Integer> getUserIdsByGroupId(int groupId) {
		// Needs to be SQLquery because there's no class for the bridge table.
		// Hibernate doesn't know that the bridge table (group_user) exists.
		// Perhaps this can still be done in HQL, but whatever.
		// Update: We now have bridge tables that hibernate is aware of.
		// But at this point, I don't feel like changing the SQL to HQL.
		Session s = sf.getCurrentSession();
		String sql = "select appUser_id from appGroup_appUser where appGroup_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, groupId);
		List<Integer> userIds = q.list();
		return userIds;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void addUserToGroup(User u, int groupId) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String sql = "insert into appGroup_appUser (appUser_id, appGroup_id) values (?, ?)";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, u.getId());
		q.setParameter(1, groupId);
		q.executeUpdate();
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void removeUserFromGroup(User u, int groupId) {
		// Will probably get exception if any parameters don't exist in the table.
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String sql = "delete from appGroup_appUser where appGroup_id = ? and appUser_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, groupId);
		q.setParameter(1, u.getId());
		q.executeUpdate();
		tx.commit();
	}
	
}
