package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity getCustomerByName(String name){
        return customerRepository.getByName(name);
    }

    public CustomerEntity getCustomerById(Long id){
        return customerRepository.getById(id);
    }

    public Long save(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity).getId();
    }

    public List<CustomerEntity> getCustomers() {
        return customerRepository.findCustomerEntities();
    }

    public void addPetToCustomer(PetEntity pet, CustomerEntity customer) {
        // check if we the customer have some pets already
        List<PetEntity> pets = customer.getPet();
        if (pets == null) {
            pets = new ArrayList<PetEntity>();
            pets.add(pet);
        } else {
            pets.add(pet);
        }
        customer.setPet(pets);
        customerRepository.save(customer);
    }
}
