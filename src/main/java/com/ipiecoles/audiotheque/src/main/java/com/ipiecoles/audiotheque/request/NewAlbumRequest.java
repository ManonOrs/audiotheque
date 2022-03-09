package com.ipiecoles.audiotheque.request;

import com.ipiecoles.audiotheque.entity.Artist;

public class NewAlbumRequest {
    String title;
    Artist artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}