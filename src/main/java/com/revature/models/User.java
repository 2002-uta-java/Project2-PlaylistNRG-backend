package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="appUser")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="appUser_id")
	private int id;
	
	@Column(name="spotify_id")
	private String spotifyId;
	
	@ManyToMany(mappedBy = "users_g")
	private List<Group> groups; 
	
	@ManyToMany(mappedBy = "users_t") 
	private List<TopTrack> top_tracks; 
	
	public User() {
		super();
	}
	
	public User(int id, String spotifyId) {
		super();
		this.id = id;
		this.spotifyId = spotifyId;
	}
	public User(String spotifyId) {
		super();
		this.spotifyId = spotifyId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", spotifyId=" + spotifyId + "]";
	}
	
	
}
