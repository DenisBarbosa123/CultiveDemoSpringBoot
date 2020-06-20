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
@RequestMapping("/api/v1/publication")
public class PublicationResource {
    
    @Autowired
    PublicationService publicationService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublicationById(@PathVariable(value = "id") long id){
        return publicationService.getPublicationById(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllPublications(){
        return publicationService.getAllPublications();
    }

    @PostMapping()
    public ResponseEntity<Object> createPublication(@RequestBody Publication newPublication){
        return publicationService.createPublication(newPublication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublication(@PathVariable(value = "id") long id, @RequestBody @Valid Publication publication){
        return publicationService.updatePublication(publication, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublicationById(@PathVariable(value = "id") long id){
        return publicationService.deletePublicationById(id);
    }
}