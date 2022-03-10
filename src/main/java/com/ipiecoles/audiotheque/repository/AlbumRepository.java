package com.ipiecoles.audiotheque.repository;

import com.ipiecoles.audiotheque.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
