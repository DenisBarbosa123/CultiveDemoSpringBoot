package br.edu.univas.cultivedemo.repository;

import org.springframework.stereotype.Repository;

import br.edu.univas.cultivedemo.entities.Image;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
}