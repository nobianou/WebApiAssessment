package com.rewardcalculate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.rewardcalculate.model.Customer;
import com.rewardcalculate.model.Item;
import com.rewardcalculate.model.Transaction;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @Autowired
    private Customer customer;

    @Autowired
    private CustomerService customerService;

    @Test
    void testFindOneCustomerById() {
        assertNull(this.customerService.findOneCustomerById(123L));
    }

    @Test
    void testSaveCustomer() {

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        assertSame(customer, customerService.saveCustomer(customer));
    }

    @Test
    void testDeleteCustomerById() {
        assertNull(this.customerService.deleteCustomerById(123L));
    }


    @Test
    void testCalculateTotalCustomerPoint() {

        CustomerService customerService = new CustomerService();

        Customer customer = new Customer();
        customer.setTransactions(new ArrayList<>());
        Customer actualCalculateTotalCustomerPointResult = customerService.calculateTotalCustomerPoint(customer);
        assertSame(customer, actualCalculateTotalCustomerPointResult);
        assertEquals(0, actualCalculateTotalCustomerPointResult.getPoint());
    }

    @Test
    void testCalculateCustomerPointPerMonth() {
        CustomerService customerService = new CustomerService();
        LocalDate date = LocalDate.ofEpochDay(1L);

        Transaction transaction = new Transaction(123L, date, new ArrayList<>());
        transaction.setDate(LocalDate.ofEpochDay(1L));

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        LocalDate date1 = LocalDate.ofEpochDay(1L);
        transactionList.add(new Transaction(123L, date1, new ArrayList<>()));
        Customer customer = new Customer(123L, transactionList, 1);

        Customer actualCalculateCustomerPointPerMonthResult = customerService.calculateCustomerPointPerMonth(customer);
        assertSame(customer, actualCalculateCustomerPointPerMonthResult);
        assertEquals(0, actualCalculateCustomerPointPerMonthResult.getPoint());
    }

    @Test
    void testCalculateCustomerPoint() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        LocalDate date = LocalDate.ofEpochDay(1L);
        transactionList.add(new Transaction(123L, date, new ArrayList<>()));
        assertEquals(0, this.customerService.calculateCustomerPoint(this.customer, transactionList).getPoint());
    }

    @Test
    void testConstructor() {
        List<Customer> findAllCustomersResult = (new CustomerService()).findAllCustomers();
        Customer getResult = findAllCustomersResult.get(0);
        List<Transaction> transactions = getResult.getTransactions();
        assertEquals(2, transactions.size());
        assertEquals(101L, getResult.getId().longValue());
        Customer getResult1 = findAllCustomersResult.get(1);
        assertEquals(102L, getResult1.getId().longValue());
        assertEquals(20, getResult1.getPoint());
        assertEquals(30, getResult.getPoint());
        Transaction getResult2 = transactions.get(1);
        List<Item> items = getResult2.getItems();
        assertEquals(2, items.size());
        Transaction getResult3 = transactions.get(0);
        List<Item> items1 = getResult3.getItems();
        assertEquals(3, items1.size());
        assertEquals("2022-02-26", getResult2.getDate().toString());
        assertEquals("2022-05-09", getResult3.getDate().toString());
        assertEquals(11L, getResult3.getId().longValue());
        assertEquals(12L, getResult2.getId().longValue());
        Item getResult4 = items.get(1);
        assertEquals(120.0d, getResult4.getPrice());
        Item getResult5 = items1.get(0);
        assertEquals(51.0d, getResult5.getPrice());
        assertEquals("PC", getResult5.getName());
        assertEquals(1L, getResult5.getId().longValue());
        assertEquals("Scanner", getResult4.getName());
        assertEquals(5L, getResult4.getId().longValue());
        Item getResult6 = items1.get(1);
        assertEquals("Mobile", getResult6.getName());
        assertEquals(2L, getResult6.getId().longValue());
        Item getResult7 = items1.get(2);
        assertEquals("Laptop", getResult7.getName());
        assertEquals(3L, getResult7.getId().longValue());
        Item getResult8 = items.get(0);
        assertEquals("Printer", getResult8.getName());
        assertEquals(4L, getResult8.getId().longValue());
        assertEquals(51.0d, getResult8.getPrice());
        assertEquals(51.0d, getResult6.getPrice());
        assertEquals(51.0d, getResult7.getPrice());
    }
}

