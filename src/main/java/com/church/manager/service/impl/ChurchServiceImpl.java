package com.church.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return (List<Church>) this.churchRepository.findAll();
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
