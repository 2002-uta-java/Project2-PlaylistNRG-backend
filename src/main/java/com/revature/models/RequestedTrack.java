package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class RequestedTrack implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="spotify_track_id")
	private int spotifyTrackId;
	
	@Column(name="spotify_popularity")
	private int spotifyPopularity;
	
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="status")
	private String status;
	
	public RequestedTrack(int id, int spotifyTrackId, int spotifyPopularity, int employeeId, String status) {
		super();
		this.id = id;
		this.spotifyTrackId = spotifyTrackId;
		this.spotifyPopularity = spotifyPopularity;
		this.employeeId = employeeId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpotifyTrackId() {
		return spotifyTrackId;
	}

	public void setSpotifyTrackId(int spotifyTrackId) {
		this.spotifyTrackId = spotifyTrackId;
	}

	public int getSpotifyPopularity() {
		return spotifyPopularity;
	}

	public void setSpotifyPopularity(int spotifyPopularity) {
		this.spotifyPopularity = spotifyPopularity;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestedTrack [id=" + id + ", spotifyTrackId=" + spotifyTrackId + ", spotifyPopularity="
				+ spotifyPopularity + ", employeeId=" + employeeId + ", status=" + status + "]";
	}
	
}
