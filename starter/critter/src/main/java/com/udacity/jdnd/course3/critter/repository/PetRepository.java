package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findByCustomerEntityId(Long id);

    List<PetEntity> findPetEntities();
}
