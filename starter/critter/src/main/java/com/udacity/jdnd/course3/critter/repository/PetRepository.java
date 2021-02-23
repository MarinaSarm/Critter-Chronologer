package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findByCustomerEntity(CustomerEntity customerEntity);

    @Query("select p from PetEntity p")
    List<PetEntity> findPetEntities();

    PetEntity getById(Long petId);
}
