package com.users.apirestful.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.apirestful.apirest.dto.ResponseDTO;
import com.users.apirestful.apirest.dto.UserDTO;
import com.users.apirestful.apirest.dto.UserResponseDTO;
import com.users.apirestful.apirest.mapper.UserMapper;
import com.users.apirestful.apirest.model.User;
import com.users.apirestful.apirest.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO user) {

		ResponseDTO response = new ResponseDTO();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			User createdUser = userService.createUser(userMapper.entryUserToEntity(user));
			UserResponseDTO userResponseDTO = userMapper.entityToRespDTO(createdUser);
			return ResponseEntity.ok(userResponseDTO);
		} catch (ValidationException e) {
			response.setMensaje(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	public void setUserService(UserService userService2) {
		this.userService = userService2;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
