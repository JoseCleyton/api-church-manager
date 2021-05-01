package com.church.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.church.manager.exception.PasswordInvalid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.church.manager.model.User;
import com.church.manager.model.dto.CredentialsDTO;
import com.church.manager.model.dto.TokenDTO;
import com.church.manager.service.JwtService;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl	userService;
	@Autowired
	private JwtService jwtService;


	@PostMapping(path = "/token")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDTO credentialsDTO)
			throws UsernameNotFoundException {
		try {
			User user = new User(credentialsDTO.getLogin(), credentialsDTO.getPassword());
			User userAutheticated = this.userService.authenticate(user);
			String token = this.jwtService.generatedToken(userAutheticated);
			return ResponseEntity.ok(new TokenDTO(userAutheticated.getLogin(), token, userAutheticated.isAdmin()));

		} catch (UsernameNotFoundException | PasswordInvalid e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
