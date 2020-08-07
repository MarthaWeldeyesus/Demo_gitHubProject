package com.albums.api.albums_api.controller;

import java.util.HashMap;
import java.util.Map;


import com.albums.api.albums_api.model.Albums;
import com.albums.api.albums_api.service.AlbumsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AlbumsController {

    @Autowired
    private AlbumsService service;

    @GetMapping("/")
    public String greetring() {
        return "welcome to Albums API";
    }

    // get list of Albums
    @GetMapping(value = "/albums", produces = "application/json")
    public Page<Albums> getList(Pageable pageable) {
        return service.getAlbumsList(pageable);
    }

    //post albums
    @PostMapping(value = "/albums", consumes = "application/json", produces = "application/json")
    public Albums addAlbums(@RequestBody Albums albums) {

        return service.addAlbum(albums);
    }
    @GetMapping(value = "/albums/{id}", produces =  "application/json")
    public Albums byId(@PathVariable(value  = "id") long id){
        return service.getAlbums(id);
    }


    //update the albums 
    @PutMapping(value = "/albums/{id}", produces =  "application/json")
    public ResponseEntity<Albums> updateById(@PathVariable(value = "id") long id, @RequestBody Albums albumsDatails){
        
      // check if the id is correct
       //to find if Albums id is in DB
       Albums albums = service.getAlbums(id);
      
       albums.setUser_id(albumsDatails.getUser_id());
       albums.setTitle(albumsDatails.getTitle());   
       final Albums updateAlbums = service.addAlbum(albums);   
       return ResponseEntity.ok(updateAlbums);
    }
 
    //or for update(put)
    // @PutMapping(value = "/albums/{id}", produces =  "application/json")

    // public Albums updateAlbums(@PathVariable(value = "id") long id, @RequestBody Album albums){
    //     Albums albumsById = service.getAlbums(id);
      
    //     albumsById.setUser_id(albums.getUser_id());
    //     albumsById.setTitle(albums.getTitle()); 
    //     service.addAlbum(albumsById);
    //     return albumsById;
    // }

    //delete albums 
    @DeleteMapping(value = "albums/{id}", produces = "application/json")
    public Map<Long, String> removeAlbums(@PathVariable long id){
   
       Albums albums = service.getAlbums(id);
       service.delete(albums);
       Map<Long, String> respose = new HashMap<>();
       respose.put(id, "Album Deleted");
       return respose;
    }
}