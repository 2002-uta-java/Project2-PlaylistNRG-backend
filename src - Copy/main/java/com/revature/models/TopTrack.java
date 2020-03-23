package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
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
    @JoinTable(name = "employee_top_track", joinColumns = { @JoinColumn(name = "top_track_id") },
    								inverseJoinColumns = { @JoinColumn(name = "appUser_id") })
    private List<User> users_t;
	
	public TopTrack() {
		super();
	}

	public TopTrack(String spotifyTrackId) {
		super();
		this.spotifyTrackId = spotifyTrackId;
	}

	public TopTrack(String spotifyTrackId, int spotifyPopularity) {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spotifyTrackId == null) ? 0 : spotifyTrackId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopTrack other = (TopTrack) obj;
		if (spotifyTrackId == null) {
			if (other.spotifyTrackId != null)
				return false;
		} else if (!spotifyTrackId.equals(other.spotifyTrackId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TopTrack [id=" + id + ", spotifyTrackId=" + spotifyTrackId + ", users_t=" + users_t + "]";
	}

}
