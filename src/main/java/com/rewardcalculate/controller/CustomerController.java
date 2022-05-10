package com.rewardcalculate.controller;

import com.rewardcalculate.model.Customer;
import com.rewardcalculate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }


    @GetMapping("/customers/point/total/{id}")
    public ResponseEntity<Customer> getOneCustomerPoint(@PathVariable Long id) {
        customerService.calculateTotalCustomerPoint(customerService.findOneCustomerById(id));
        return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }


    @GetMapping("/customers/point/month/{id}")
    public ResponseEntity<Customer> getPointPerMonth(@PathVariable Long id) {
        customerService.calculateCustomerPointPerMonth(customerService.findOneCustomerById(id));
        return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }


}
