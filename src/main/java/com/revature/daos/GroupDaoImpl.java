package com.revature.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Group;

@Repository
public class GroupDaoImpl implements GroupDao {
	
	@Autowired
	private SessionFactory sf;

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Group getGroupById(int groupId) {
		Session s = sf.getCurrentSession();
		Group g = (Group) s.get(Group.class, groupId);
		return g;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public int createGroup(Group g) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int pk = (int) s.save(g);
		tx.commit();
		return pk;
	}
	
	
}
