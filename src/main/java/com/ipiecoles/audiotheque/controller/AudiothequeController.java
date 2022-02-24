package com.ipiecoles.audiotheque.controller;

import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.service.AlbumService;
import com.ipiecoles.audiotheque.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin
public class AudiothequeController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists/{id}")
    public Artist getArtist(@PathVariable("id") Integer artistId) {
        Optional<Artist> artist = artistService.findById(artistId);
        if (artist.isPresent()) {
            return artist.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @GetMapping(value = "/artists")
    public Page<Artist> getAllArtists(@RequestParam(value = "name", required = false) Optional<String> name, @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortProperty);

        if(name.isEmpty()) {
            return artistService.findAllArtists(page, size, sort);
        } else {
            return artistService.findArtistsByName(name.get(), page, size, sort);
        }
    }

}