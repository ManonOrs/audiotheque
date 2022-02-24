package com.ipiecoles.audiotheque.service;

import com.ipiecoles.audiotheque.entity.Artist;
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

    public Optional<Artist> findById(Integer id) {
        return albumRepository.findById(id);
    }
}
