package com.ipiecoles.audiotheque.service;

import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Optional<Artist> findById(Integer id) {
        return artistRepository.findById(id);
    }

    public Page<Artist> findAllArtists(int page, int size, Sort sort) {
        Pageable artists = PageRequest.of(page, size, sort);
        return artistRepository.findAll(artists);
    }

    public Page<Artist> findArtistsByName(String name, Integer page, Integer size, Sort sort) {
        Pageable artists = PageRequest.of(page, size, sort);
        return artistRepository.findArtistsByNameContaining(name, artists);
    }

    public boolean exist(String name) {
        return artistRepository.existsByNameIgnoreCase(name);
    }

    public Artist newArtiste(String name) {
        Artist newArtist = new Artist(name);
        return artistRepository.save(newArtist);
    }

    public Artist updateArtist(Artist modifiedArtist) {
        return artistRepository.save(modifiedArtist);
    }

    public void deleteArtist(Artist deleteArtist) {
        artistRepository.delete(deleteArtist);
    }
}
