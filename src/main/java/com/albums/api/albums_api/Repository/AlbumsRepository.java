package com.albums.api.albums_api.Repository;

import com.albums.api.albums_api.model.Albums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumsRepository extends JpaRepository<Albums, Long> {
    
}