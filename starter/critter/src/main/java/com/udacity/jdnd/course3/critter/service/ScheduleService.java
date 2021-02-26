package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    public Long createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity).getId();
    }

    public List<ScheduleEntity> getAll() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeService.getById(employeeId);
        return scheduleRepository.getDetailsByEmployee(employeeEntity);
    }

    public List<ScheduleEntity> getScheduleForPet(Long petId) {
        PetEntity petEntity = petService.getPetById(petId);
        return scheduleRepository.getDetailsByPet(petEntity);
    }

    public List<ScheduleEntity> getScheduleForCustomer(Long customerId) {
        CustomerEntity customerEntity = customerService.getCustomerById(customerId);
        List<PetEntity> pets = petService.getPetsByOwnerId(customerId);
        List<ScheduleEntity> schedules = new ArrayList<>();
        for(PetEntity pet : pets){
            schedules.addAll(scheduleRepository.getDetailsByPet(pet));
        }
        return schedules;
    }
}
