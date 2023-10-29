package com.sushmanth.repositary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sushmanth.entity.EmployeeDTO;

public interface EmployeeRepositary extends MongoRepository<EmployeeDTO, Integer>{

}
