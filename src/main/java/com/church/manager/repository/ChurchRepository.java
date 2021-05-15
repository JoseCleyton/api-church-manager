package com.church.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.church.manager.model.Church;

@Repository
public interface ChurchRepository extends JpaRepository<Church, Long> {
	@Query(value = "SELECT * FROM Church c", nativeQuery = true)
	List<Church> findAll();
	
	@Query(value = "SELECT * FROM Church c WHERE c.id != ?1", nativeQuery = true)
	List<Church> findAllByAdm(Long id);
}
