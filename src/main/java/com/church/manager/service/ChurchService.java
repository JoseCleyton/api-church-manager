package com.church.manager.service;

import java.util.List;
import java.util.Optional;

import com.church.manager.model.Church;

public interface ChurchService {
	Church save(Church church);
	List<Church> findAll();
	List<Church> findAllByAdm(Long id);
	Optional<Church> findById(Long id);
	Long findQuantity();
	Church update(Church church);
	void delete(Long id);
}
