package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.dto.UserDTO;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
public class CustomerTransformer implements DTOTransform<Customer, CustomerDTO> {


    @Override
    public CustomerDTO toDTO(Customer domainBase) {
        return new CustomerDTO(domainBase.getFirstName(),domainBase.getLastName(),domainBase.getEmail(),domainBase.isActive());
    }

    public List<CustomerDTO> toDTO(List<Customer> customers){
        return customers.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Customer fromDTO(CustomerDTO baseDTO) {
        Customer customer = new Customer();
        customer.setFirstName(baseDTO.getFirstName());
        customer.setLastName(baseDTO.getLastName());
        customer.setPassword(baseDTO.getPassword());
        customer.setEmail(baseDTO.getEmail());
        customer.setContact(baseDTO.getMobileNo());
        customer.setAddresses(baseDTO.getAddresses());
        return customer;
    }
}
