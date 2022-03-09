package com.ipiecoles.audiotheque.entity;

import javax.persistence.*;

@Entity
@AttributeOverride(name = "artistId", column = @Column(name="artist_id"))
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer artistId; //artist_id

    @Transient
    private Artist artist;

    public Album() {

    }
    public Album(String title, Integer artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}