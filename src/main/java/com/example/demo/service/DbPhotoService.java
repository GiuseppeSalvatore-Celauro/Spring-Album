package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Photo;
import com.example.demo.repository.IPhotoRepository;


@Service("mainPhotoService")
public class DbPhotoService implements IPhotoService{

	@Autowired
	private IPhotoRepository photoRepository;
	
	@Override
	public Iterable<Photo> getAll() {
		return photoRepository.findAll();
	}

	@Override
	public Optional<Photo> getById(int id) {
	     return photoRepository.findById(id);
	}

	@Override
	public Photo create(Photo photo) {
        return photoRepository.save(photo);
	}

	@Override
	public Optional<Photo> update(int id, Photo photo) {
		Optional<Photo> photoOptional = photoRepository.findById(id);
        if (photoOptional.isEmpty()) {
            return Optional.empty();
        }
        photoOptional.get().setUrl(photo.getUrl());
        photoRepository.save(photoOptional.get());
        return photoOptional;
	}

	@Override
	public Boolean delete(int id) {
		Optional<Photo> photoOptional = photoRepository.findById(id);
        if (photoOptional.isEmpty()) {
            return false;
        }
        photoRepository.delete(photoOptional.get());
        return true;
	}

}
