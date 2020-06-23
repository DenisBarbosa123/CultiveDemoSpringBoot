package br.edu.univas.cultivedemo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.univas.cultivedemo.entities.Publication;
import br.edu.univas.cultivedemo.entities.ResponseError;
import br.edu.univas.cultivedemo.entities.User;
import br.edu.univas.cultivedemo.repository.PublicationRepository;
import br.edu.univas.cultivedemo.repository.UserRepository;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    UserRepository userRepository;

	public ResponseEntity<Object> getPublicationById(long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        
        if(publication.isPresent()) {
            publication.get().setImages(imageService.getImages(publication.get()));
            return ResponseEntity.ok(publication.get());
        }

        ResponseError error = new ResponseError("Publication not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any Publication with id " + id);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
		return ResponseEntity.ok(publications);
	}

	public ResponseEntity<Object> createPublication(Publication newPublication, long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            ResponseError error = new ResponseError("User with id "+ userId + "not exists", HttpStatus.NOT_FOUND.toString(), "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
        }

        newPublication.setUser(user.get());

        try {
            if(newPublication.getImages() != null){
                imageService.saveImages(newPublication); // saving the images belongs to publication
            }
            Publication createdPublication = publicationRepository.save(newPublication);
            return ResponseEntity.ok(createdPublication); 
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while saving the new publication ", HttpStatus.BAD_REQUEST.toString(), e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
	}

	public ResponseEntity<Object> updatePublication(@Valid Publication newPublication, long id) {
		Optional<Publication> oldPublication = publicationRepository.findById(id);
        
        if(!oldPublication.isPresent()) {
            ResponseError error = new ResponseError("Publication not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any Publication with id " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        try {
            Publication publication = buildPublicationTobeUpdated(newPublication, oldPublication.get());
            if(publication.getImages() != null) {
                imageService.saveImages(publication);
            }
            publicationRepository.save(publication);
            return ResponseEntity.ok(publicationRepository.findById(id));
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while updating the publication with Id " + id, HttpStatus.BAD_REQUEST.toString(), e.toString());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
	}

	public ResponseEntity<Object> deletePublicationById(long id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        
        if(!publication.isPresent()) {
            ResponseError error = new ResponseError("Publication not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any Publication with id " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        try {
            imageService.deleteImages(publication.get().getImages()); //deleting images belongs to publication
            publicationRepository.delete(publication.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while deleting the publication with Id " + id, HttpStatus.BAD_REQUEST.toString(), e.toString());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    private Publication buildPublicationTobeUpdated(Publication newPub, Publication oldPub){
        if(newPub.getTitle() != null) {
            oldPub.setTitle(newPub.getTitle());
        }

        if(newPub.getBody() != null) {
            oldPub.setBody(newPub.getBody());
        }

        if(newPub.getImages() != null) {
            oldPub.setImages(newPub.getImages());
        }

        return oldPub;
    }



    
}