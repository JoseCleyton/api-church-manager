package com.church.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.church.manager.model.Church;
import com.church.manager.repository.ChurchRepository;
import com.church.manager.service.ChurchService;

@Service
public class ChurchServiceImpl implements ChurchService{
	@Autowired
	private ChurchRepository churchRepository;

	@Override
	public Church save(Church church) {
		return this.churchRepository.save(church);
	}

	@Override
	public List<Church> findAll() {
		return this.churchRepository.findAll();
	}
	
	@Override
	public Page<Church> findAllByAdm(Long id, String name, Pageable pageable) {
		return this.churchRepository.findAllByAdm(id, name, pageable);
	}
	@Override
	public Optional<Church> findById(Long id) {
		return this.churchRepository.findById(id);
	}

	@Override
	public Long findQuantity() {
		return this.churchRepository.count();
	}

	@Override
	public Church update(Church church) {
		return this.churchRepository.save(church);
	}

	@Override
	public void delete(Long id) {
		this.churchRepository.deleteById(id);;
	}


}
