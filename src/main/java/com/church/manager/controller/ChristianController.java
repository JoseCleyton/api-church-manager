package com.church.manager.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.church.manager.exception.NotFoundException;
import com.church.manager.model.Christian;
import com.church.manager.model.User;
import com.church.manager.service.ChristianService;
import com.church.manager.service.ChurchService;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/christian")
public class ChristianController {

	@Autowired
	private ChristianService christianService;

	@Autowired
	private ChurchService churchService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping
	public Page<Christian> findAll(@RequestParam(name = "name", required = false, defaultValue = "") String name, @RequestParam(name = "monthOfBirthday", required = false) Integer monthOfBirthday, Principal principal, Pageable pageable){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		if(monthOfBirthday == 0) {
			return this.christianService.findAll(user.get().getChurch().getId(), name, pageable);
		}
		return this.christianService.findAll(user.get().getChurch().getId(), name, monthOfBirthday, pageable);
	}

	@GetMapping(path = "retrieve")
	public List<Christian> retrieve(Principal principal, Pageable pageable){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.christianService.retrieve(user.get().getChurch().getId());
	}

	@GetMapping(path = "/quantity")
	public Long findQuantity(Principal principal){
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.christianService.getQuantity(user.get().getChurch().getId());
	}

	@GetMapping(path = "{id}")
	public Christian findBtId(@PathVariable(name="id") Long id, Principal principal) throws NotFoundException{
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		Optional<Christian> christian =this.christianService.findById(id, user.get().getChurch().getId());
		if(christian.isPresent()) {
			return christian.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Registro Encontrado");
	}

	@PostMapping
	public Christian save(Principal principal, @RequestBody Christian christian) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		christian.setChurch(user.get().getChurch());
		Long quantity = this.christianService.getQuantity(user.get().getChurch().getId());
		quantity = quantity+1;
		user.get().getChurch().setNumberOfTithers(quantity.toString());
		this.churchService.update(user.get().getChurch());
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
