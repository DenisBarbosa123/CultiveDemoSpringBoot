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

import br.edu.univas.cultivedemo.entities.User;
import br.edu.univas.cultivedemo.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") long id){
        return userService.getUserById(id);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid User user){
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") long id){
        return userService.deleteUser(id);
    }

}