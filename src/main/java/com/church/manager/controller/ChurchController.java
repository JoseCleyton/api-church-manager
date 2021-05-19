package com.church.manager.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.church.manager.exception.NotFoundException;
import com.church.manager.model.Church;
import com.church.manager.model.User;
import com.church.manager.service.ChurchService;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/church")
public class ChurchController {

	@Autowired
	private ChurchService churchService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public Page<Church> findAllByIsAdm(Principal principal, Pageable pageable){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.churchService.findAllByAdm(user.get().getChurch().getId(), pageable);
	}

	@GetMapping(path = "/all")
	public Page<Church> findAll(Pageable pageable){
		return this.churchService.findAll(pageable);
	}

	@GetMapping(path = "/{id}")
	public Church findById(@PathVariable(name="id") Long id) throws NotFoundException{
		return this.churchService.findById(id).orElseThrow(NotFoundException::new);
	}

	@GetMapping(path = "/quantity")
	public Long findQuantity(){
		return this.churchService.findQuantity();
	}

	@PostMapping
	public Church save(@RequestBody Church church) {
		church.getUser().setPassword(passwordEncoder.encode(church.getUser().getPassword()));
		return this.churchService.save(church);
	}

	@PutMapping
	public Church update(@RequestBody Church church) {
		return this.churchService.update(church);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Boolean> delete(@PathVariable(name="id") Long id) {
		this.churchService.delete(id);
		return ResponseEntity.ok(true);
	}
}
