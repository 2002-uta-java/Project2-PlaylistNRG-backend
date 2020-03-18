package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="top_track")
public class TopTrack implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="top_track_id")
	private int id;
	
	@Column(name="spotify_track_id")
	private String spotifyTrackId;
	
	@Column(name="spotify_popularity")
	private int spotifyPopularity;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "employee_top_track", joinColumns = { @JoinColumn(name = "appUser_id") }, inverseJoinColumns = { @JoinColumn(name = "top_track_id") })
    private List<Group> top_tracks;
	
	public TopTrack() {
		super();
	}
	
	public TopTrack(int id, String spotifyTrackId, int spotifyPopularity) {
		super();
		this.id = id;
		this.spotifyTrackId = spotifyTrackId;
		this.spotifyPopularity = spotifyPopularity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpotifyTrackId() {
		return spotifyTrackId;
	}

	public void setSpotifyTrackId(String spotifyTrackId) {
		this.spotifyTrackId = spotifyTrackId;
	}

	public int getSpotifyPopularity() {
		return spotifyPopularity;
	}

	public void setSpotifyPopularity(int spotifyPopularity) {
		this.spotifyPopularity = spotifyPopularity;
	}

	@Override
	public String toString() {
		return "TopTrack [id=" + id + ", spotifyTrackId=" + spotifyTrackId + ", spotifyPopularity=" + spotifyPopularity
				+ "]";
	}
	
	
}
