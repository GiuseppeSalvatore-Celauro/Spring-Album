package com.example.demo.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Photo;
import com.example.demo.service.IPhotoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class AdminController {
	
    @Autowired
    @Qualifier("mainPhotoService")
	private IPhotoService photoService;

    public AdminController() {
    }

    @GetMapping("/admin/api/photos")
    public Iterable<Photo> getPhotos() {
        return photoService.getAll();
    }

    @GetMapping("/admin/api/photo/{id}")
    public Photo getPhoto(@PathVariable int id) {
        Optional<Photo> photo = photoService.getById(id);
        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo.get();
    }

    @PostMapping("/admin/api/photos/add")
    public Photo addPhoto(@Valid @RequestBody Photo photo) {
        return photoService.create(photo);
    }

    @PutMapping("/admin/api/photos/update/{id}")
    public Photo updatePhoto(@PathVariable int id, @Valid @RequestBody Photo photo) {
        Optional<Photo> photoOptional = photoService.update(id, photo);
        if (photoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photoOptional.get();
    }

    @DeleteMapping("/admin/api/photos/delete/{id}")
    public void deletePhoto(@PathVariable int id) {
        Boolean isDeleted = photoService.delete(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return ;
    }

}
