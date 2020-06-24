package br.edu.univas.cultivedemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.univas.cultivedemo.entities.ResponseError;
import br.edu.univas.cultivedemo.entities.User;
import br.edu.univas.cultivedemo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> getUserById(long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(user);
        } 

        ResponseError error = new ResponseError("User not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any user with id " + id);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Object> getAllUsers() {
      List<User> users = userRepository.findAll();
      return ResponseEntity.ok(users);
    }

    public ResponseEntity<Object> createUser(User newUser){
        try {
            User createdUser = userRepository.save(newUser);
            return ResponseEntity.ok(createdUser); 
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while saving the new user ", HttpStatus.BAD_REQUEST.toString(), e.toString());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateUser(User user, Long id){
        Optional<User> oldUser = userRepository.findById(id);

        if(!oldUser.isPresent()){
            ResponseError error = new ResponseError("User not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any user with id " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        try {
            User updatedUser = userRepository.save(buildUserToUpdate(oldUser.get(), user));
            return ResponseEntity.ok(updatedUser); 
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while updating the user with Id " + id, HttpStatus.BAD_REQUEST.toString(), e.toString());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Object> deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            ResponseError error = new ResponseError("User not found with Id " + id, HttpStatus.NOT_FOUND.toString(), "It wasn't found any user with id " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        try {
           userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            ResponseError error = new ResponseError("An error occurred while deleting the user with Id " + id, HttpStatus.BAD_REQUEST.toString(), e.toString());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    private User buildUserToUpdate(User oldUser, User newUser){

        if(newUser.getName() != null){
            oldUser.setName(newUser.getName());
        }

        if(newUser.getEmail() != null){
            oldUser.setEmail(newUser.getEmail());
        }

        if(newUser.getPassword() != null){
            oldUser.setPassword(newUser.getPassword());
        }

        if(newUser.getAddress() != null){
            oldUser.setAddress(newUser.getAddress());
        }

        if(newUser.getPhone() != null){
            oldUser.setPhone(newUser.getPhone());
        }

        if(newUser.getMobilePhone() != null){
            oldUser.setMobilePhone(newUser.getMobilePhone());
        }

        return oldUser;
    }
}