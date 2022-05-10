package com.rewardcalculate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewardcalculate.model.Customer;
import com.rewardcalculate.service.CustomerService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class, CustomerService.class, Customer.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private Customer customer;

    @Autowired
    private CustomerController customerController;

    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setPoint(1);
        customer.setTransactions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"transactions\":[],\"point\":1}"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    void testGetOneCustomer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetOneCustomerPoint() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/point/total/{id}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetPointPerMonth() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/point/month/{id}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

