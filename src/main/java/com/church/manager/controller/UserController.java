package com.church.manager.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.church.manager.exception.PasswordInvalid;
import com.church.manager.model.ChangePassword;
import com.church.manager.model.User;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping (path = "/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PutMapping
	public ResponseEntity<User> changePassword(Principal principal, @RequestBody ChangePassword changePassword) throws PasswordInvalid {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());

		if (passwordEncoder.matches(changePassword.getPasswordAdmin(), user.get().getPassword())) {
			changePassword.setNewPassword(this.passwordEncoder.encode(changePassword.getNewPassword()));
			Optional<User> userChangePassword = this.userServiceImpl.findById(changePassword.getIdUser());
			userChangePassword.get().setPassword(changePassword.getNewPassword());
			return ResponseEntity.ok(this.userServiceImpl.update(userChangePassword.get()));
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha de Administrador Inválida !!! ");

	}

	@PutMapping(path = "admin")
	public ResponseEntity<User> changePasswordAdmin(Principal principal, @RequestBody ChangePassword changePassword) throws PasswordInvalid {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());

		if (passwordEncoder.matches(changePassword.getPasswordAdmin(), user.get().getPassword())) {
			changePassword.setNewPassword(this.passwordEncoder.encode(changePassword.getNewPassword()));
			user.get().setPassword(changePassword.getNewPassword());
			return ResponseEntity.ok(this.userServiceImpl.update(user.get()));
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha Inválida !!! ");

	}

}
