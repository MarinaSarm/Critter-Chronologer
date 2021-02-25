package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Long save(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity).getId();
    }

    public EmployeeEntity getById(Long id){
        return employeeRepository.getById(id);
    }

    public List<EmployeeEntity> findAvailableEmployees(Set<EmployeeSkill> skills, Set<DayOfWeek> days) {
        List<EmployeeEntity> allEmployees = findAllEmployees();
        List<EmployeeEntity> availableEmployees = new ArrayList<>();
        for(EmployeeEntity employee : allEmployees){
            if(employee.getSkills().containsAll(skills) && employee.getDaysAvailable().containsAll(days)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.getAll();
    }
}
