package com.tampro.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.Position;
import com.tampro.app.repository.PositionRepository;
import com.tampro.app.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	PositionRepository positionRepository;
	
	@Override
	public Page<Position> getAll(Pageable pageable, Position position) {
		// TODO Auto-generated method stub
		Page<Position>	pagePosi = null;
		 
		if(position.getName() != null  ) {
			pagePosi = positionRepository.findByName("%"+position.getName()+"%",pageable);
		}else {			 	
			pagePosi = positionRepository.findAll(pageable);
			 
		}
		 return pagePosi;
	}

	@Override
	public Position getPositionById(long id) {
		// TODO Auto-generated method stub
		return positionRepository.findById(id).orElseGet(null);
	}

	@Override
	public void deletePosition(Position position) throws Exception {
		// TODO Auto-generated method stub
		positionRepository.delete(position);
	}

	@Override
	public void updatePosition(Position position) throws Exception {
		// TODO Auto-generated method stub
		position.setUpdateDate(new Date());
		positionRepository.save(position);
	}

	@Override
	public void addPosition(Position position) throws Exception {
		// TODO Auto-generated method stub
		position.setUpdateDate(new Date());
		position.setCreateDate(new Date());
		positionRepository.save(position);
	}

	@Override
	public List<Position> getAll() {
		// TODO Auto-generated method stub
		return positionRepository.findAll();
	}

}
