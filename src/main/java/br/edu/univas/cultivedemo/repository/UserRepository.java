package br.edu.univas.cultivedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.univas.cultivedemo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}