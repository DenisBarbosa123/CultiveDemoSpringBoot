package br.edu.univas.cultivedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.univas.cultivedemo.entities.Publication;
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}