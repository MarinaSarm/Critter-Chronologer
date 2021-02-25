package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    private CustomerDTO convertCustomerEntityToDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerEntity, customerDTO);
        if(customerEntity.getPet() != null){
            customerDTO.setPetIds(convertPetsToPetIds(customerEntity.getPet()));
        }
        return customerDTO;
    }

    private List<Long> convertPetsToPetIds(List<PetEntity> pets){
        List<Long> petIds = new ArrayList<>();
        for(PetEntity pet : pets){
            petIds.add(pet.getId());
        }
        return petIds;
    }

    private List<CustomerDTO> convertCustomerEntityListToCustomerDTOList(List<CustomerEntity> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(CustomerEntity customer : customerList){
            customerDTOList.add(convertCustomerEntityToDTO(customer));
        }
        return customerDTOList;
    }

    public CustomerDTO getCustomerDTO(String name){
        return convertCustomerEntityToDTO(customerService.getCustomerByName(name));
    }

    private CustomerEntity convertDTOtoCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        return customerEntity;
    }

    private EmployeeDTO convertEmployeeEntityToDTO(EmployeeEntity employeeEntity){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeEntity, employeeDTO);
        return employeeDTO;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerService.save(customer);
        return convertCustomerEntityToDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        // just convert all entities to DTOs
        return convertCustomerEntityListToCustomerDTOList(customerService.getCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        CustomerEntity customer = petService.getOwnerByPetId(petId);
        return convertCustomerEntityToDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setSkills(employeeDTO.getSkills());
        employeeEntity.setDaysAvailable(employeeDTO.getDaysAvailable());
        employeeService.save(employeeEntity);
        return convertEmployeeEntityToDTO(employeeEntity);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity employeeEntity = employeeService.getById(employeeId);
        return convertEmployeeEntityToDTO(employeeEntity);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
