package com.church.manager.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.church.manager.model.Tithing;

@Repository
public interface TithingRepository extends JpaRepository<Tithing, Long>{
	@Query(value="SELECT * FROM tithing t WHERE t.church_id = :idChurch AND t.date BETWEEN :dateStart AND :dateEnd", nativeQuery = true)
	List<Tithing> findAll(Long idChurch, Date dateStart, Date dateEnd);

	@Query(value="SELECT * FROM tithing t ORDER BY t.date LIMIT 5", nativeQuery = true)
	List<Tithing> fetchLatestRecords();

	@Query(value="SELECT * FROM tithing t WHERE t.church_id = :idChurch ORDER BY t.date LIMIT 5", nativeQuery = true)
	List<Tithing> fetchLatestRecordsByUser(Long idChurch);

	@Query(value="SELECT SUM(value) FROM tithing t WHERE t.church_id = :idChurch AND t.date BETWEEN :dateStart AND :dateEnd", nativeQuery = true)
	Optional<Double> total(Long idChurch, Date dateStart, Date dateEnd);

	@Query(value="SELECT SUM(value) FROM tithing t WHERE t.date BETWEEN :dateStart AND :dateEnd", nativeQuery = true)
	Optional<Double> retrieveTotal(Date dateStart, Date dateEnd);
}
