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
import com.users.apirestful.apirest.model.User;
import com.users.apirestful.apirest.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {

		ResponseDTO response = new ResponseDTO();
		HttpHeaders headers = new HttpHeaders();
		UserDTO userDTO;
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {

			boolean isNotValidPass = userService.isNotValidPassword(user.getPassword());

			if (isNotValidPass) {
				response.setMensaje(ResponseDTO.INVALID_PASSWORD);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers)
						.body(response);
			}

			boolean isNotValidMail = userService.isNotValidMail(user.getEmail());

			if (isNotValidMail) {
				response.setMensaje(ResponseDTO.INVALID_MAIL);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers)
						.body(response);
			}

			boolean existMail = userService.existMail(user.getEmail());

			if (existMail) {
				response.setMensaje(ResponseDTO.EXIST_MAIL);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers)
						.body(response);
			}

			user = userService.createUser(user);
			userDTO = new UserDTO(user);

			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userDTO);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(response.getErrorMessage(e.getMessage()));

		}

	}

	public void setUserService(UserService userService2) {
		this.userService = userService2;
	}

}
