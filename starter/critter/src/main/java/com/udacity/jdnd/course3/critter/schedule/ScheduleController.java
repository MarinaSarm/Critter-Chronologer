package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    private ScheduleDTO convertScheduleEntityToDTO(ScheduleEntity scheduleEntity){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleEntity, scheduleDTO);
        if(scheduleEntity.getPet() != null){
            scheduleDTO.setPetIds(convertPetsToPetIds(scheduleEntity.getPet()));
        }
        if(scheduleEntity.getEmployee() != null){
            scheduleDTO.setEmployeeIds(convertEmployeesToEmployeeIds(scheduleEntity.getEmployee()));
        }
        return scheduleDTO;
    }

    private ScheduleEntity convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
        if(scheduleDTO.getPetIds() != null){
            scheduleEntity.setPet(convertPetIdsToPets(scheduleDTO.getPetIds()));
        }
        if(scheduleDTO.getEmployeeIds() != null){
            scheduleEntity.setEmployee(convertEmployeeIdsToEmployees(scheduleDTO.getEmployeeIds()));
        }
        return scheduleEntity;
    }

    private List<PetEntity> convertPetIdsToPets(List<Long> petIds){
        List<PetEntity> pets = new ArrayList<>();
        for(Long petId : petIds){
            pets.add(petService.getPetById(petId));
        }
        return pets;
    }

    private List<EmployeeEntity> convertEmployeeIdsToEmployees(List<Long> employeeIds){
        List<EmployeeEntity> employees = new ArrayList<>();
        for(Long employeeId : employeeIds){
            employees.add(employeeService.getById(employeeId));
        }
        return employees;
    }

    private List<Long> convertPetsToPetIds(List<PetEntity> pets){
        List<Long> petIds = new ArrayList<>();
        for(PetEntity pet : pets){
            petIds.add(pet.getId());
        }
        return petIds;
    }

    private List<Long> convertEmployeesToEmployeeIds(List<EmployeeEntity> employeeEntities){
        List<Long> employeeIds = new ArrayList<>();
        for(EmployeeEntity employee : employeeEntities){
            employeeIds.add(employee.getId());
        }
        return employeeIds;
    }

    private List<ScheduleDTO> convertScheduleEntityListToScheduleDTOList(List<ScheduleEntity> scheduleEntities){
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(ScheduleEntity schedule : scheduleEntities){
            scheduleDTOList.add(convertScheduleEntityToDTO(schedule));
        }
        return scheduleDTOList;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = convertScheduleDTOToEntity(scheduleDTO);
        scheduleService.createSchedule(scheduleEntity);
        return convertScheduleEntityToDTO(scheduleEntity);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return convertScheduleEntityListToScheduleDTOList(scheduleService.getAll());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
