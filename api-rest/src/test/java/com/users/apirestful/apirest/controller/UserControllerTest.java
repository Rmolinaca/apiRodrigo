package com.users.apirestful.apirest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.apirestful.apirest.dto.ResponseDTO;
import com.users.apirestful.apirest.dto.UserDTO;
import com.users.apirestful.apirest.model.Phone;
import com.users.apirestful.apirest.model.User;
import com.users.apirestful.apirest.service.UserService;

public class UserControllerTest {

	private UserController userController;
	private UserService userService;

	@BeforeEach
	public void setUp() {
		userService = mock(UserService.class);
		userController = new UserController();
		userController.setUserService(userService);
	}

	@Test
	public void testCreateUser_Success() {
		User user = new User();
		user.setName("Rodrigo Molina");
		user.setEmail("rolejo@gmail.com");
		user.setPassword("Rodrigo123");

		
		List<Phone> listPhone = new ArrayList<>();
		listPhone.add(new Phone(null, 123456, 12, 57, null));
		listPhone.add(new Phone(null, 3322, 22, 55, null));
		user.setPhones(listPhone);
		
		UserDTO userDTO = new UserDTO(user);


		when(userService.isNotValidPassword(user.getPassword())).thenReturn(false);
		when(userService.isNotValidMail(user.getEmail())).thenReturn(false);
		when(userService.existMail(user.getEmail())).thenReturn(false);
		when(userService.createUser(user)).thenReturn(user);

		ResponseEntity<?> response = userController.createUser(user);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	    UserDTO actualUserDTO = (UserDTO) response.getBody();
	    assertEquals(userDTO.getName(), actualUserDTO.getName());
	    assertEquals(userDTO.getEmail(), actualUserDTO.getEmail());
	    assertEquals(userDTO.getPassword(), actualUserDTO.getPassword());
	    assertEquals(userDTO.getPhones().size(), actualUserDTO.getPhones().size());

	}

	@Test
	public void testCreateUser_InvalidPassword() {
		ResponseDTO responseDTO = new ResponseDTO();
		User user = new User();
		user.setName("Rodrigo Molina");
		user.setEmail("rolejo@gmail.com");
		user.setPassword("12");

		List<Phone> listPhone = new ArrayList<>();
		listPhone.add(new Phone(null, 123456, 12, 57, null));
		listPhone.add(new Phone(null, 3322, 22, 55, null));
		user.setPhones(listPhone);

		when(userService.isNotValidPassword(user.getPassword())).thenReturn(true);

		ResponseEntity<?> response = userController.createUser(user);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(responseDTO.INVALID_PASSWORD, ((ResponseDTO)response.getBody()).getMensaje());
	}

	@Test
	public void testCreateUser_InvalidEmail() {
		ResponseDTO responseDTO = new ResponseDTO();
		User user = new User();
		user.setName("Rodrigo Molina");
		user.setEmail("rolejo");
		user.setPassword("Rodrigo123");

		when(userService.isNotValidMail(user.getEmail())).thenReturn(true);

		ResponseEntity<?> response = userController.createUser(user);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(responseDTO.INVALID_MAIL, ((ResponseDTO)response.getBody()).getMensaje());

	}

	@Test
	public void testCreateUser_ExistingEmail() {
		ResponseDTO responseDTO = new ResponseDTO();
		User user = new User();
		user.setName("Rodrigo Molina");
		user.setEmail("rolejo@gmail.com");
		user.setPassword("Rodrigo123");

		when(userService.existMail(user.getEmail())).thenReturn(true);

		ResponseEntity<?> response = userController.createUser(user);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(responseDTO.EXIST_MAIL, ((ResponseDTO)response.getBody()).getMensaje());


	}
}
