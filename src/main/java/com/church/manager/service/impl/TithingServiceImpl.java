package com.church.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.manager.model.Tithing;
import com.church.manager.repository.TithingRepository;
import com.church.manager.service.TithingService;

@Service
public class TithingServiceImpl implements TithingService{
	
	@Autowired
	private TithingRepository tithingRepository;

	@Override
	public List<Tithing> findAll(Long idChurch, Date dateStart, Date dateEnd) {
		return this.tithingRepository.findAll(idChurch, dateStart, dateEnd);
	}

	@Override
	public Tithing save(Tithing tithing) {
		return this.tithingRepository.save(tithing);
	}

	@Override
	public Optional<Double> total(Long idChurch, Date dateStart, Date dateEnd) {
		return this.tithingRepository.total(idChurch, dateStart, dateEnd);
	}

	@Override
	public List<Tithing> fetchLatestRecords() {
		return this.tithingRepository.fetchLatestRecords();
	}
	
	@Override
	public List<Tithing> fetchLatestRecordsByUser(Long idChurch) {
		return this.tithingRepository.fetchLatestRecordsByUser(idChurch);
	}
	
	@Override
	public Optional<Double> retrieveTotal(Date dateStart, Date dateEnd) {
		return this.tithingRepository.retrieveTotal(dateStart, dateEnd);
	}

}
