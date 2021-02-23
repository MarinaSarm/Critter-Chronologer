package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
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
        List<CustomerDTO> retCustomerDTOList = new ArrayList<>();
        for(CustomerEntity customer : customerList){
            retCustomerDTOList.add(convertCustomerEntityToDTO(customer));
        }
        return retCustomerDTOList;
    }

    public CustomerDTO getCustomerDTO(String name){
        return convertCustomerEntityToDTO(customerService.getCustomerByName(name));
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerService.save(customer);
        customerDTO.setId(customer.getId());
        customerService.save(customer);
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return convertCustomerEntityListToCustomerDTOList(customerService.getCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
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
