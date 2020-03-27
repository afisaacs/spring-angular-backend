package com.clientes.app.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clientes.app.springboot.backend.apirest.models.entity.Region;

public interface IRegionDao extends CrudRepository<Region, Long> {

}
