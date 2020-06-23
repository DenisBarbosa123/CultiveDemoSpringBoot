package br.edu.univas.cultivedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.cultivedemo.entities.Image;
import br.edu.univas.cultivedemo.entities.Publication;
import br.edu.univas.cultivedemo.repository.ImageRepository;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public void saveImages(Publication publication){
        List<Image> imagesToBeSaved = publication.getImages();
        if(!imagesToBeSaved.isEmpty()){
            imagesToBeSaved.stream().forEach(image -> {
                image.setPublication(publication);
                imageRepository.save(image);
            });
        }
    }

    public void deleteImages(List<Image> imagesToBeDeleted){
        if(!imagesToBeDeleted.isEmpty()){
            imagesToBeDeleted.stream().forEach(image -> imageRepository.delete(image));
        }
    }

    public List<Image> getImages(Publication publication){
        return imageRepository.findByPublication(publication);
    }
}