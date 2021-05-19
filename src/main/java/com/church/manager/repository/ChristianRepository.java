package com.church.manager.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.church.manager.model.Christian;

@Repository
public interface ChristianRepository extends JpaRepository<Christian, Long>{

	@Query(value = "SELECT * FROM Christian c WHERE c.church_id = :id", nativeQuery = true)
	Page<Christian> findAll(Long id, Pageable pageable);

	@Query(value="SELECT COUNT(id) FROM Christian c WHERE c.church_id = :idChurch", nativeQuery = true)
	Long getQuantity(Long idChurch);
}
