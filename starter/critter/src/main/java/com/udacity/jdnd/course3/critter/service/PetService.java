package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerService customerService;

    public PetEntity getPetByName(String name){
        return petRepository.getByName(name);
    }

    public PetEntity getPetById(Long id) {
        return petRepository.getById(id);
    }

    public Long save(PetEntity petEntity){
        petRepository.save(petEntity);
        // we need to save pet also by customer
        customerService.addPetToCustomer(petEntity, petEntity.getCustomerEntity());
        return petEntity.getId();
    }

    public List<PetEntity> getPetsByOwnerId(Long ownerId) {
        CustomerEntity savedCustomer = customerService.getCustomerById(ownerId);
        return petRepository.findByCustomerEntity(savedCustomer);
    }

    public List<PetEntity> getPets() {
        return petRepository.findPetEntities();
    }

    public CustomerEntity getOwnerByPetId(Long petId) {
        PetEntity petEntity = petRepository.getById(petId);
        return customerService.getCustomerById(petEntity.getCustomerEntity().getId());
    }
}
