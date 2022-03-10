package com.ipiecoles.audiotheque.controller;

import com.ipiecoles.audiotheque.entity.Album;
import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.request.NewAlbumRequest;
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

    @PostMapping(value = "/artists")
    public Artist newArtist(@RequestBody Artist artist) {
        if(artistService.exist(artist.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "L'artiste existe déjà");
        } else {
            return artistService.newArtiste(artist.getName());
        }
    }

    @PutMapping(value = "/artists/{id}")
    public Optional<Artist> updateArtist(@PathVariable("id")Integer artistId, @RequestBody Artist artist) {
        Optional<Artist> artistToModify = artistService.findById(artistId);

        if (artistToModify.isPresent()) {
            Artist modifiedArtist = artistToModify.get();
            modifiedArtist.setName(artist.getName());
            return Optional.of(artistService.updateArtist(modifiedArtist));
        }
        return Optional.empty();
    }

    @DeleteMapping(value = "/artists/{id}")
    public boolean deleteArtist(@PathVariable("id")Integer artistId) {
        Optional<Artist> artistToDelete = artistService.findById(artistId);

        if (artistToDelete.isPresent()) {
            Artist deleteArtist = artistToDelete.get();
            artistService.deleteArtist(deleteArtist);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(value = "/albums")
    public Optional<Album> newAlbum(@RequestBody NewAlbumRequest data) {
        Integer artistId = data.getArtist().getId();
        Optional<Artist> artist = artistService.findById(artistId);
        if (artist.isPresent()) {
            return Optional.of( albumService.newAlbum(data.getTitle(), artistId));
        }
        return Optional.empty();
    }

    @DeleteMapping(value = "/albums/{id}")
    public Optional<Optional<Album>> deleteAlbum(@PathVariable("id")Integer albumId) {
        Optional<Album> album = albumService.findById(albumId);
        System.out.println(albumId);
        if (album.isPresent()) {
            albumService.deleteAlbum(album.get());
            return Optional.of(album);
        }
        return Optional.empty();
    }

}