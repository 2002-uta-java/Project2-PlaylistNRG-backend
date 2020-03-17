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
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="spotify_id")
	private int spotifyId;
	
	@Column(name="password")
	private String password;
	
	public User(int id, int spotifyId, String password) {
		super();
		this.id = id;
		this.spotifyId = spotifyId;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(int spotifyId) {
		this.spotifyId = spotifyId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", spotifyId=" + spotifyId + ", password=" + password + "]";
	}
	
	
}
