package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    private PetDTO convertPetEntityToDTO(PetEntity petEntity){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(petEntity, petDTO);
        petDTO.setOwnerId(petEntity.getCustomerEntity().getId());
        return petDTO;
    }

    public PetDTO getPetDTO(String name){
        return convertPetEntityToDTO(petService.getPetByName(name));
    }

    private List<PetDTO> convertPetEntityListToPetDTOList(List<PetEntity> petList){
        List<PetDTO> petDTOList = new ArrayList<>();
        for(PetEntity pet : petList){
            petDTOList.add(convertPetEntityToDTO(pet));
        }
        return petDTOList;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity pet = new PetEntity();
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        CustomerEntity customerEntity = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setCustomerEntity(customerService.getCustomerById(petDTO.getOwnerId()));
        petService.save(pet);
        return convertPetEntityToDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetEntity pet = petService.getPetById(petId);
        return convertPetEntityToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return convertPetEntityListToPetDTOList(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetEntity> pets = petService.getPetsByOwnerId(ownerId);
        return convertPetEntityListToPetDTOList(pets);
    }
}
