package com.revature.daos;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.TopTrack;

@Repository
public class TopTrackDaoImpl implements TopTrackDao {
	
	@Autowired
	private SessionFactory sf;

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Integer> getTopTrackIdsByUserId(int uId) {
		Session s = sf.getCurrentSession();
		String sql = "select * from employee_top_track where employee_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(1, uId);
		List<Integer> topTrackIds = q.list();
		return topTrackIds;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public TopTrack getTopTrackByTrackId(int id) {
		Session s = sf.getCurrentSession();
		TopTrack t = (TopTrack) s.get(TopTrack.class, id);
		return t;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public int createTopTrack(TopTrack t) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int pk = (int) s.save(t);
		tx.commit();
		return pk;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void deleteTopTracksByUserId(int id) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String sql = "delete from employee_top_track where employee_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(1, id);
		q.executeUpdate();
		tx.commit();
	}
	
}
