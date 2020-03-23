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
	@Column(name="app_User_id")
	private int id;
	
	@Column(name="spotify_id", unique = true, nullable = false)
	private String spotifyId;
	
	@ManyToMany(mappedBy = "usersG")
	private List<Group> groups; 
	
	@ManyToMany(mappedBy = "usersT") 
	private List<TopTrack> topTracks; 
	
	public User() {
		super();
	}
	
	public User(String spotifyId) {
		super();
		this.spotifyId = spotifyId;
	}
	
	public User(int id, String spotifyId) {
		super();
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((spotifyId == null) ? 0 : spotifyId.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (spotifyId == null) {
			if (other.spotifyId != null)
				return false;
		} else if (!spotifyId.equals(other.spotifyId))
			{return false;}
		return true;
	}
	
	
}
