package com.church.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.church.manager.model.Tithing;

public interface TithingService {
	List<Tithing> findAll(Long idChurch, Date dateStart, Date dateEnd);
	Tithing save(Tithing tithing);
	Optional<Double> total(Long idChurch);
	
}
