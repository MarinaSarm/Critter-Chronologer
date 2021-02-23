package com.udacity.jdnd.course3.critter.service;

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

    public PetEntity getPetByName(String name){
        return new PetEntity();
    }

    public Long save(PetEntity petEntity){
        return petRepository.save(petEntity).getId();
    }

    public List<PetEntity> getPetsByOwner(Long ownerId) {
        return petRepository.findByCustomerEntityId(ownerId);
    }

    public List<PetEntity> getPets() {
        return petRepository.findPetEntities();
    }

}
