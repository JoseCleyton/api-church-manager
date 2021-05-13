package com.church.manager.service;

import java.util.List;
import java.util.Optional;

import com.church.manager.model.Christian;

public interface ChristianService {
	Christian save(Christian christian);
	List<Christian> findAll(Long id);
	Optional<Christian> findById(Long id);
	Christian update(Christian christian);
	void delete(Long id);
}
