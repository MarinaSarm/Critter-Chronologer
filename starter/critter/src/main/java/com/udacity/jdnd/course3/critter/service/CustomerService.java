package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity getCustomerByName(String name){
        return new CustomerEntity();
    }

    public Long save(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity).getId();
    }

    public List<CustomerEntity> getCustomers() {
        return customerRepository.findCustomerEntities();
    }

    public CustomerEntity getOwnerByPet(PetEntity petEntity) {
        return customerRepository.findByPetEntity(petEntity);
    }
}
