package com.albums.api.albums_api.service;

import com.albums.api.albums_api.Repository.AlbumsRepository;
import com.albums.api.albums_api.exceptions.DataNotFoundException;
import com.albums.api.albums_api.model.Albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlbumsService {

    @Autowired
    private AlbumsRepository repository;

    public Page<Albums> getAlbumsList(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public Albums addAlbum(Albums albums) {

        return repository.save(albums);
    }

    public Albums getAlbums(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Album with id number " + id + " Data Not Found"));
    }

    public void delete(Albums albums) {
        repository.delete(albums);
    }

}