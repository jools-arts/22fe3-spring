package com.qa.intro_project.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;

// Not using @SpringBootTest because we do not need the whole application context
//
// Using Junit 5 (jupiter) @ExtendWith annotation to specify the test runner
// - MockitoExtenson.class configures JUnit 5 to use Mockito

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
	
	@Mock // Equivalent to @MockBean in Spring
	private UserRepository userRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks // equivalent to using @Autowired
	
	private UserService userService;
	
	private List<User> users;
	
	@BeforeEach
	public void init() {
		users = List.of(new User(1, "Bob", "bob@mail.com"), new User(2, "Sarah", "sarah@mail.com");
	}
	
	@Test
	public void getAllTest() {
		
		// Arrange-Act-Assert
		when(userRepository.findAll()).thenReturn(users);
		when(modelMapper.map(users.get(0),  null));
		
		// Arrange: setup the data and components under test
		
		// Act: performing the action under test
		
		// Assert: validate the action was successful
		
	}
	
	@Test
	public void getByIdTest() {
		
	}
	
	@Test
	public void getByInvalidTest() {
		
	}
	
	@Test
	public void createTest() {
		
	}

}
