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
		String sql = "select top_track_id from employee_top_track where app_User_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, uId);
		return q.list();

	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<String> getSpotifyTrackIdsByUserId(int uId) {
		Session s = sf.getCurrentSession();
		String sql = "select a.spotify_track_id from top_track a join employee_top_track e   on a.top_track_id = e.top_track_id where e.app_user_id = ? ";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, uId);
		return q.list();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public TopTrack getTopTrackByTrackId(int id) {
		Session s = sf.getCurrentSession();
		return (TopTrack) s.get(TopTrack.class, id);
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
	public void addTopTrackByUserId(int tId, int uId) { //issue here
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String sql = "insert into employee_top_track (app_User_id, top_track_id) values (?, ?)";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, uId);
		q.setParameter(1, tId);
		q.executeUpdate();
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void deleteTopTracksByUserId(int id) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String sql = "delete from employee_top_track where app_User_id = ?";
		SQLQuery q = s.createSQLQuery(sql);
		q.setParameter(0, id);
		q.executeUpdate();
		
		
		//clean abandoned tracks
		sql = "delete from top_track tt where tt.top_track_id  not in (select ett.top_track_id from employee_top_track ett)";
		q = s.createSQLQuery(sql);
		q.executeUpdate();
		
		tx.commit();
	}
	
}
