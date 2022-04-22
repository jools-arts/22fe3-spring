package com.qa.intro_project.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;
import com.qa.intro_project.dto.UserDTO;

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
		users = List.of(new User(1, "Bob", "bob@mail.com"), new User(2, "Sarah", "sarah@mail.com"));
		userDTOs = List.of(new)
	}
	
	@Test
	public void getAllTest() {
		
		// Arrange-Act-Assert
		when(userRepository.findAll()).thenReturn(users);
		when(modelMapper.map(users.get(0),  userDTOs.class)).thenReturn(userDTOs.get(0));
		when(modelMapper.map(users.get(0),  userDTOs.class)).thenReturn(userDTOs.get(1));
		
		// Arrange: setup the data and components under test
		when(userRepository.findById(id)).thenReturn(Optional.of(users.get(0)));
		
		// Act: performing the action under test
		List<UserDTO> actual  = userService.getUsers();
		
		// Assert: validate the action was successful
		assertEquals(userDTOs, actual);
		verify(userRepository).findAll();
		verify(modelMapper).map(users.get(0), UserDTO.class);
		verify(modelMapper).map(users.get(1), UserDTO.class);
		
	}
	
	@Test
	public void getByIdTest() {
		User user = users.get(0);
		UserDTO userDTO = userDTOs.get(0);
		int id = user.getId();
		
		//Arrange
		when(userRepository.findById(id)).thenReturn((Optional.of(user));
		
	}
	
	@Test
	public void getByInvalidTest() {
		
	}
	
	@Test
	public void createTest() {
		
	}

}
