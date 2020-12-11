package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.entity.Position;
import com.tampro.app.entity.Team;

public interface PositionService {

	Page<Position> getAll(Pageable pageable, Position position);

	Position getPositionById(long id);

	void deletePosition(Position position) throws Exception;

	void updatePosition(Position position)	throws Exception;

	void addPosition(Position position)	throws Exception;

	List<Position> getAll();
}
