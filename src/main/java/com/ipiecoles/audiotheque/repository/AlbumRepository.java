package com.ipiecoles.audiotheque.repository;

import com.ipiecoles.audiotheque.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Artist, Integer> {
}
