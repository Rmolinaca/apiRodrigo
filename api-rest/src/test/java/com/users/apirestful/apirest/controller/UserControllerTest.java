package com.users.apirestful.apirest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.apirestful.apirest.dto.PhoneDTO;
import com.users.apirestful.apirest.dto.ResponseDTO;
import com.users.apirestful.apirest.dto.UserDTO;
import com.users.apirestful.apirest.dto.UserResponseDTO;
import com.users.apirestful.apirest.mapper.UserMapper;
import com.users.apirestful.apirest.model.Phone;
import com.users.apirestful.apirest.model.User;
import com.users.apirestful.apirest.service.UserService;

public class UserControllerTest {

	private UserController userController;
	private UserService userService;
	private UserMapper userMapper;

	@BeforeEach
	public void setUp() {
		userService = mock(UserService.class);
		userMapper = mock(UserMapper.class);

		userController = new UserController();
		userController.setUserService(userService);
		userController.setUserMapper(userMapper);

	}

	@Test
	public void testCreateUser_Success() {
	    UserDTO userDTO = new UserDTO();
	    userDTO.setName("Rodrigo Molina");
	    userDTO.setEmail("rolejo@gmail.com");
	    userDTO.setPassword("Rodrigo123");

	    List<PhoneDTO> listPhoneDTO = new ArrayList<>();
	    listPhoneDTO.add(new PhoneDTO(123456, 12, 57));
	    listPhoneDTO.add(new PhoneDTO(3322, 22, 55));
	    userDTO.setPhones(listPhoneDTO);

	    User user = new User();
	    user.setName("Rodrigo Molina");
	    user.setEmail("rolejo@gmail.com");
	    user.setPassword("Rodrigo123");

	    List<Phone> listPhone = new ArrayList<>();
	    listPhone.add(new Phone(null, 123456, 12, 57, null));
	    listPhone.add(new Phone(null, 3322, 22, 55, null));
	    user.setPhones(listPhone);
	    
	    UserResponseDTO responseDTO = new UserResponseDTO();
	    responseDTO.setName("Rodrigo Molina");
	    responseDTO.setEmail("rolejo@gmail.com");
	    responseDTO.setPassword("Rodrigo123");
	    responseDTO.setPhones(listPhoneDTO);

	    when(userService.createUser(any(User.class))).thenReturn(user);
	    when(userMapper.entryUserToEntity(userDTO)).thenReturn(user);
	    when(userMapper.entityToRespDTO(user)).thenReturn(responseDTO);

	    ResponseEntity<?> response = userController.createUser(userDTO);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(UserResponseDTO.class, response.getBody().getClass());

	    UserResponseDTO actualUserDTO = (UserResponseDTO) response.getBody();
	    assertEquals(userDTO.getName(), actualUserDTO.getName());
	    assertEquals(userDTO.getEmail(), actualUserDTO.getEmail());
	    assertEquals(userDTO.getPassword(), actualUserDTO.getPassword());
	    assertEquals(userDTO.getPhones().size(), actualUserDTO.getPhones().size());
	}

	@Test
	public void testCreateUser_ValidationException() {
		UserDTO userDTO = new UserDTO();

		ValidationException validationException = new ValidationException(ResponseDTO.INVALID_MAIL);
		when(userService.createUser(userMapper.entryUserToEntity(userDTO))).thenThrow(validationException);

		ResponseEntity<?> response = userController.createUser(userDTO);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody() instanceof ResponseDTO);
		assertEquals(ResponseDTO.INVALID_MAIL, ((ResponseDTO) response.getBody()).getMensaje());
	}

	@Test
	public void testCreateUser_InternalServerError() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("Rodrigo Molina");
		userDTO.setEmail("rolejo@gmail.com");
		userDTO.setPassword("Rodrigo123");

		when(userService.createUser(userMapper.entryUserToEntity(userDTO))).thenThrow(RuntimeException.class);

		ResponseEntity<?> response = userController.createUser(userDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}
