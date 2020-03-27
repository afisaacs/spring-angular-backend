package com.clientes.app.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientes.app.springboot.backend.apirest.models.dao.IRegionDao;
import com.clientes.app.springboot.backend.apirest.models.entity.Region;

@Service
public class RegionServiceImpl implements IRegionService {
	
	@Autowired
	private IRegionDao regionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAll() {
		return (List<Region>) regionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Region findById(Long id) {
		return regionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Region save(Region region) {
		return regionDao.save(region);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		regionDao.deleteById(id);
	}

}
