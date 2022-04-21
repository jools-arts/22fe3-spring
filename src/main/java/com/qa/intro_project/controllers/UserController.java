package com.qa.intro_project.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class UserController {
	
	private UserRepository userRepository;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping // Creating a user
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userRepository.save(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/user/" + newUser.getId());

		return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(name = "id") int id) {
		// TODO: Implement the update user functionality using the user repository
		// (HINT) Check if user exists, retrieve user if so, update fields on retrieved user, save retrieved user
		
		// Get user
		Optional<User> savedUser = userRepository.findById(id);
		// Check if User exists
		if (savedUser.isPresent()) {
				User toUpdate = savedUser.get();
			
		// Update fields
		toUpdate.setUsername(user.getUsername());
		toUpdate.setEmail(user.getEmail());
				
		// Save updates
		User updated = userRepository.save(toUpdate);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
		
	}
	// user doesn't exist
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(name = "id") int id) {
		// TODO: Implement the delete user functionality using the user repository
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
