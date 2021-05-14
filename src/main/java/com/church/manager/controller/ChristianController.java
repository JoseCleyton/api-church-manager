package com.church.manager.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.church.manager.exception.NotFoundException;
import com.church.manager.model.Christian;
import com.church.manager.model.User;
import com.church.manager.service.ChristianService;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/christian")
public class ChristianController {

	@Autowired
	private ChristianService christianService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping
	public List<Christian> findAll(Principal principal){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.christianService.findAll(user.get().getChurch().getId());
	}

	@GetMapping(path = "/quantity")
	public Long findQuantity(Principal principal){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.christianService.getQuantity(user.get().getChurch().getId());
	}

	@GetMapping(path = "{id}")
	public Christian findBtId(@PathVariable(name="id") Long id) throws NotFoundException{
		return this.christianService.findById(id).orElseThrow(NotFoundException::new);
	}

	@PostMapping
	public Christian save(Principal principal, @RequestBody Christian christian) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		christian.setChurch(user.get().getChurch());
		return this.christianService.save(christian);
	}

	@PutMapping
	public Christian update(Principal principal, @RequestBody Christian christian) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		christian.setChurch(user.get().getChurch());
		return this.christianService.update(christian);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Boolean> delete(@PathVariable(name="id") Long id) {
		this.christianService.delete(id);
		return ResponseEntity.ok(true);
	}
}
