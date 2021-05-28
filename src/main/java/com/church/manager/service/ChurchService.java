package com.church.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.church.manager.model.Church;

public interface ChurchService {
	Church save(Church church);
	List<Church> findAll();
	Page<Church> findAllByAdm(Long id, String name, Pageable pageable);
	Optional<Church> findById(Long id);
	Long findQuantity();
	Church update(Church church);
	void delete(Long id);
}
