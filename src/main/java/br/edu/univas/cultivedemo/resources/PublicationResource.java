package br.edu.univas.cultivedemo.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.cultivedemo.entities.Publication;
import br.edu.univas.cultivedemo.services.PublicationService;

@RestController
@RequestMapping("/api/v1")
public class PublicationResource {
    
    @Autowired
    PublicationService publicationService;

    @GetMapping("/publication/{id}")
    public ResponseEntity<Object> getPublicationById(@PathVariable(value = "id") long id){
        return publicationService.getPublicationById(id);
    }

    @GetMapping("/publication")
    public ResponseEntity<Object> getAllPublications(){
        return publicationService.getAllPublications();
    }

    @PostMapping("user/{userId}/publication")
    public ResponseEntity<Object> createPublication(@RequestBody Publication newPublication, @PathVariable(value = "userId") long userId){
        return publicationService.createPublication(newPublication, userId);
    }

    @PutMapping("/publication/{id}")
    public ResponseEntity<Object> updatePublication(@PathVariable(value = "id") long id, @RequestBody @Valid Publication publication){
        return publicationService.updatePublication(publication, id);
    }

    @DeleteMapping("/publication/{id}")
    public ResponseEntity<Object> deletePublicationById(@PathVariable(value = "id") long id){
        return publicationService.deletePublicationById(id);
    }
}