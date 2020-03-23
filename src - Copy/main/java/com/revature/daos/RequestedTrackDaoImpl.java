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

import com.revature.models.RequestedTrack;

@Repository
public class RequestedTrackDaoImpl implements RequestedTrackDao {

	@Autowired
	private SessionFactory sf;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public int createRequestedTrack(RequestedTrack r) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		int pk = (int) s.save(r);
		tx.commit();
		return pk;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void updateRequestedTrack(RequestedTrack r) {
		Session s = sf.getCurrentSession();
		Transaction tx = s.beginTransaction();
		String hql = "update RequestedTrack set "
				+ "spotify_track_id = :spotifyTrackId,"
				+ "spotify_popularity = :spotifyPopularity,"
				+ "employee_id = :employeeId,"
				+ "status = :status "
				+ "where id = :id";
		Query q = s.createQuery(hql);
		q.setParameter("spotifyTrackId", r.getSpotifyTrackId());
		q.setParameter("spotifyPopularity", r.getSpotifyPopularity());
		q.setParameter("employeeId", r.getEmployeeId());
		q.setParameter("status", r.getStatus());
		q.setParameter("id", r.getId());
		
		q.executeUpdate();
		tx.commit();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<RequestedTrack> getRequestedTracksByUserId(int id) {
		Session s = sf.getCurrentSession();
		String hql = "from RequestedTrack where employee_id = :eId";
		Query q = s.createQuery(hql);
		q.setParameter("eId", id);
		List<RequestedTrack> rTracks = q.list();
		return rTracks;
	}

}
