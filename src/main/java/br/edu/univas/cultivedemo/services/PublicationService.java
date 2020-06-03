package br.edu.univas.cultivedemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.cultivedemo.repository.PublicationRepository;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    
}