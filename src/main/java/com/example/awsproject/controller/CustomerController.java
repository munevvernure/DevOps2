package com.example.awsproject.controller;

import com.example.awsproject.model.Customer;
import com.example.awsproject.repository.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

@RestController
@RequestMapping("/cust")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "",produces = "application/json")
    public ResponseEntity<Iterable<Customer>>getAllCustomer(){
        Iterable<Customer> custList=customerRepository.findAll();
        return new ResponseEntity<>(custList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id)
    {
        Customer customer= customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id: " + id));
        logger.info("Get customer with id: " + id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @PostMapping(value = "/add",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.info("Customer created with id: " + customer.getCustid());
        return new ResponseEntity<>(customerRepository.save(customer),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
        if(id==null || id==0 || customer== null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        c.setCustid(id);
        c.setFirstname(customer.getFirstname());
        c.setAddress(customer.getAddress());
        c.setCity(customer.getCity());

        Customer updatedCustomer = customerRepository.save(c);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id){
        if (id==null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Customer c =customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
