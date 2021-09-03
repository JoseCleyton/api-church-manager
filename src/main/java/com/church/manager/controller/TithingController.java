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
		List<Tithing> tithings = null;
		if(user.get().isAdmin()) {
			tithings = this.tithingService.fetchLatestRecords();
		}else {
			tithings = this.tithingService.fetchLatestRecordsByUser(user.get().getChurch().getId());
		}
		return tithings;
	}

	@GetMapping(path = "/total/retrieve")
	public Double totalRetrieve(Principal principal) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		Date dateStartFomatter = this.getDate("-01");
		Date dateEndFomatter = this.getDate("-31");
		return this.getTotal(user.get().getChurch().getId(), dateStartFomatter, dateEndFomatter);
	}

	@GetMapping(path = "/total")
	public Double total(Principal principal) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		double total = 0;
		Date dateStartFomatter = this.getDate("-01");
		Date dateEndFomatter = this.getDate("-31");
		if(user.get().isAdmin()) {
			total = this.getTotal(null, dateStartFomatter, dateEndFomatter);
			//			opTotal = this.tithingService.retrieveTotal(dateStartFomatter, dateEndFomatter);
		}else {
			total = this.getTotal(user.get().getChurch().getId(), dateStartFomatter, dateEndFomatter);
			//			opTotal = this.tithingService.total(user.get().getChurch().getId(), dateStartFomatter, dateEndFomatter);
		}
		return total;
	}

	@GetMapping(path = "/total/{id}")
	public Double totalByChurch(@PathVariable(name = "id") Long id) throws ParseException {
		Date dateStartFomatter = this.getDate("-01");
		Date dateEndFomatter = this.getDate("-31");
		//		double total = 0;
		//		Optional<Double> opTotal = this.tithingService.total(id, dateStartFomatter, dateEndFomatter);
		//		if(opTotal.isPresent()) {
		//			total = (double) opTotal.get();
		//		}
		return this.getTotal(id, dateStartFomatter, dateEndFomatter);
	}

	@PostMapping
	public Tithing save(Principal principal, @RequestBody Tithing tithing) {
		Optional<User> user = this.userServiceImpl.getUserByLogin(principal.getName());
		tithing.setChurch(user.get().getChurch());
		tithing.setDate(new Date());
		return this.tithingService.save(tithing);
	}

	private Date getDate(String day) {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFomatter = null;
		try {
			dateStartFomatter = formatter.parse(year + "-" + month + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateStartFomatter;
	}

	private double getTotal(Long id, Date dateStartFomatter, Date dateEndFomatter) {
		double total = 0;
		Optional<Double> opTotal = null;
		if(id != null) {
			opTotal = this.tithingService.total(id, dateStartFomatter, dateEndFomatter);
		}else {
			opTotal = this.tithingService.retrieveTotal(dateStartFomatter, dateEndFomatter);
		}
		if(opTotal.isPresent()) {
			total = (double) opTotal.get();
		}
		return total;
	}

}
