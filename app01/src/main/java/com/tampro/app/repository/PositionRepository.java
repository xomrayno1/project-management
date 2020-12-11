package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>{
	Page<Position> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM Position P WHERE  P.NAME LIKE  ?1",nativeQuery = true)
 	Page<Position> findByName(String name, Pageable pageable);
	
	
	List<Position> findAll();
}
