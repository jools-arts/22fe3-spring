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
import com.qa.intro_project.data.entity.UserProfile;
import com.qa.intro_project.data.repository.UserProfileRepository;
import com.qa.intro_project.data.repository.UserRepository;

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class UserController {
	
	private UserRepository userRepository;
	private UserProfileRepository userProfileRepository;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public UserController(UserRepository userRepository, UserProfileRepository userProfileRepository) {
		this.userRepository = userRepository;
		this.userProfileRepository = userProfileRepository;
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
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		UserProfile profile = user.getUserProfile();
		user.setUserProfile(null);
		
		User newUser = userRepository.save(user);
		
		if (profile != null) {
			profile.setUser(newUser);
			profile = userProfileRepository.save(profile);
		}
		newUser.setUserProfile(profile);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/user/" + newUser.getId());

		return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
}
