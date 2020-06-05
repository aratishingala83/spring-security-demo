package com.example.demo.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.reposiroty.CustomerRepository;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;

    private static List<CustomerDTO> CUSTOMERS =  new ArrayList<CustomerDTO>();

    
    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_ADMIN')")
    @Secured({ "ROLE_CUSTOMER", "ROLE_ADMIN" })
    public List<CustomerDTO> getCUstomers(){
    	return CUSTOMERS;
    }
    
    @GetMapping(path = "{customerId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public CustomerDTO getStudent(@PathVariable("customerId") Integer customerId) {
        return CUSTOMERS.stream()
                .filter(customer -> customerId.equals(customer.getCustomerId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student " + customerId + " does not exists"
                ));
    }
    
    
    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public void registerCustomer(@RequestBody CustomerDTO customerDTO) {
    	System.out.println("####################registerCustomer################"+customerDTO);
    	Objects.requireNonNull(customerDTO, "Customer request should not have null or empty parameters");
    	Customer customer = new Customer();
    	customer.setName(customerDTO.getCustomerName());
    	customer = customerRepository.save(customer);
    	customerDTO.setCustomerId(customer.getId());
    	CUSTOMERS.add( customerDTO );
    	System.out.println("Customer has been added "+customer.getId());
    }
    
    
    @DeleteMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) {
    	CUSTOMERS = CUSTOMERS.stream().filter(customer -> !(customer.getCustomerId().equals(customerId))).collect(Collectors.toList());
    	System.out.println("Customer deleted for customer id :"+customerId);
    }
}