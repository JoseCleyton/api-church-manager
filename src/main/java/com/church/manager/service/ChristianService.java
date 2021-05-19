package com.church.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.church.manager.model.Christian;

public interface ChristianService {
	Christian save(Christian christian);
	Page<Christian> findAll(Long id, Pageable pageable);
	Long getQuantity(Long idChurch);
	Optional<Christian> findById(Long id);
	Christian update(Christian christian);
	void delete(Long id);
}
