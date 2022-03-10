package com.ipiecoles.audiotheque.service;

import com.ipiecoles.audiotheque.entity.Album;
import com.ipiecoles.audiotheque.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album newAlbum(String title, Integer artistId) {
        Album newAlbum = new Album(title, artistId);
        return albumRepository.save(newAlbum);
    }

    public void deleteAlbum(Album deleteAlbum) {
        albumRepository.delete(deleteAlbum);
    }

    public Optional<Album> findById(Integer id) {
        return albumRepository.findById(id);
    }
}
