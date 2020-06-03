package br.edu.univas.cultivedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.univas.cultivedemo.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}