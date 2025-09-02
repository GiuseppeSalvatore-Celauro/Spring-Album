package com.example.demo.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Photo;
import com.example.demo.service.IPhotoService;

import org.springframework.web.bind.annotation.RestController;

//import com.photoalbum.model.Photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PhotoController {
    @Autowired
    private IPhotoService photoService;

    public PhotoController() {
    }

    @GetMapping("/api/photos")
    public Iterable<Photo> getPhotos() {
        return photoService.getAll();
    }

    @GetMapping("/api/photo/{id}")
    public Photo getPhoto(@PathVariable int id) {
        Optional<Photo> photo = photoService.getById(id);
        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo.get();
    }

}
