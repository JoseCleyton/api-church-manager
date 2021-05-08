package com.church.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.church.manager.model.Church;

@Repository
public interface ChurchRepository extends JpaRepository<Church, Long> {

}
