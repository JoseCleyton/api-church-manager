package com.church.manager.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.church.manager.model.Tithing;
import com.church.manager.model.User;
import com.church.manager.service.TithingService;
import com.church.manager.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/tithing")
public class TithingController {
	@Autowired
	private TithingService tithingService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(path = "/{id}")
	public List<Tithing> findAllByAdm(@PathVariable(name = "id") Long id, @RequestParam(value = "dateStart", required = true) String dateStart, @RequestParam(value = "dateEnd", required = true) String dateEnd) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFomatter = formatter.parse(dateStart);
		Date dateEndFomatter = formatter.parse(dateEnd);
		return this.tithingService.findAll(id, dateStartFomatter, dateEndFomatter);
	}

	@GetMapping
	public List<Tithing> findAll(Principal principal, @RequestParam(value = "dateStart", required = true) String dateStart, @RequestParam(value = "dateEnd", required = true) String dateEnd) throws ParseException {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFomatter = formatter.parse(dateStart);
		Date dateEndFomatter = formatter.parse(dateEnd);
		return this.tithingService.findAll(user.get().getChurch().getId(), dateStartFomatter, dateEndFomatter);
	}

	@GetMapping(path = "/latest-records")
	public List<Tithing> fetchLatestRecords(Principal principal) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		return this.tithingService.fetchLatestRecords(user.get().getChurch().getId());
	}

	@GetMapping(path = "/total")
	public Double total(Principal principal) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());

		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFomatter = null;
		Date dateEndFomatter = null;
		try {
			dateStartFomatter = formatter.parse(year + "-" + month + "-01");
			dateEndFomatter = formatter.parse(year + "-" + month + "-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		double total = 0;
		Optional<Double> opTotal = this.tithingService.total(user.get().getChurch().getId(), dateStartFomatter, dateEndFomatter);
		if(opTotal.isPresent()) {
			total = (double) opTotal.get();
		}
		return total;
	}

	@GetMapping(path = "/total/{id}")
	public Double totalByChurch(@PathVariable(name = "id") Long id) throws ParseException {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFomatter = null;
		Date dateEndFomatter = null;
		try {
			dateStartFomatter = formatter.parse(year + "-" + month + "-01");
			dateEndFomatter = formatter.parse(year + "-" + month + "-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		double total = 0;
		Optional<Double> opTotal = this.tithingService.total(id, dateStartFomatter, dateEndFomatter);
		if(opTotal.isPresent()) {
			total = (double) opTotal.get();
		}
		return total;
	}

	@PostMapping
	public Tithing save(Principal principal, @RequestBody Tithing tithing) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		tithing.setChurch(user.get().getChurch());
		tithing.setDate(new Date());
		return this.tithingService.save(tithing);
	}

}
