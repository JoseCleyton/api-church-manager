package com.church.manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.church.manager.model.Church;

@Repository
public interface ChurchRepository extends JpaRepository<Church, Long> {
	@Query(value = "SELECT * FROM Church c", nativeQuery = true)
	Page<Church> findAll(Pageable pageable);

	@Query(value = "SELECT * FROM Church c WHERE c.id != :id and c.name LIKE %:name%", nativeQuery = true)
	Page<Church> findAllByAdm(Long id, String name, Pageable pageable);
}
