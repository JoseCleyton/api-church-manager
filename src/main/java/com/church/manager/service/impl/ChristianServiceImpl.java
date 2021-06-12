package com.church.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.church.manager.model.Christian;
import com.church.manager.repository.ChristianRepository;
import com.church.manager.service.ChristianService;

@Service
public class ChristianServiceImpl implements ChristianService{
	@Autowired
	private ChristianRepository christianRepository;

	@Override
	public Christian save(Christian christian) {
		return this.christianRepository.save(christian);
	}

	@Override
	public Page<Christian> findAll(Long idChurch, String name, int monthOfBirthday, Pageable pageable) {
		return this.christianRepository.findAll(idChurch, name, monthOfBirthday, pageable);
	}

	@Override
	public Page<Christian> findAll(Long idChurch, String name, Pageable pageable) {
		return this.christianRepository.findAll(idChurch, name, pageable);
	}
	
	@Override
	public Optional<Christian> findById(Long id, Long idChurch) {
		return this.christianRepository.findById(id, idChurch);
	}

	@Override
	public Christian update(Christian christian) {
		return this.christianRepository.save(christian);
	}

	@Override
	public void delete(Long id) {
		this.christianRepository.deleteById(id);

	}

	@Override
	public Long getQuantity(Long idChurch) {
		return this.christianRepository.getQuantity(idChurch);
	}

	@Override
	public List<Christian> retrieve(Long idChurch) {
		return this.christianRepository.retrieve(idChurch);
	}

}
