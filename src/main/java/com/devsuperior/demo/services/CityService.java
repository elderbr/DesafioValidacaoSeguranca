package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional
    public CityDTO save(CityDTO dto){
        City newCity = repository.save(dto.parseToEntity());
        return parseToDTO(newCity);
    }

    @Transactional(readOnly = true)
    public List<CityDTO> findAllOrderByName(){
        List<City> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(CityDTO::new).toList();
    }

    private CityDTO parseToDTO(City entity) {
        return new CityDTO(entity.getId(), entity.getName());
    }
}
