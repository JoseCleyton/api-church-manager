package com.church.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.church.manager.model.Christian;

public interface ChristianService {
	Christian save(Christian christian);
	Page<Christian> findAll(Long idChurch, String name, int monthOfBirthday, Pageable pageable);
	Page<Christian> findAll(Long idChurchd, String name, Pageable pageable);
	List<Christian> retrieve(Long idChurch);
	Long getQuantity(Long idChurch);
	Optional<Christian> findById(Long id, Long idChurchd);
	Christian update(Christian christian);
	void delete(Long id);
}
