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
public class TopTrack implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="spotify_track_id")
	private int spotifyTrackId;
	
	@Column(name="spotify_popularity")
	private int spotifyPopularity;
	
	public TopTrack(int id, int spotifyTrackId, int spotifyPopularity) {
		super();
		this.id = id;
		this.spotifyTrackId = spotifyTrackId;
		this.spotifyPopularity = spotifyPopularity;
	}
}
