package com.rewardcalculate.service;

import com.rewardcalculate.model.Customer;
import com.rewardcalculate.model.Item;
import com.rewardcalculate.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CustomerService {
    private static Long idItem = 0L;
    private static Long idCust = 100L;
    private static Long idTran = 10L;
    private static List<Item> items1 = new ArrayList<>();
    private static List<Item> items2 = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();

    static {
        items1.add(new Item(++idItem, "PC", 51));
        items1.add(new Item(++idItem, "Mobile", 51));
        items1.add(new Item(++idItem, "Laptop", 51));

        items2.add(new Item(++idItem, "Printer", 51));
        items2.add(new Item(++idItem, "Scanner", 120));

        transactions.add(new Transaction(++idTran, LocalDate.of(2022, 5, 9), items1));
        transactions.add(new Transaction(++idTran, LocalDate.of(2022, 2, 26), items2));

        customers.add(new Customer(++idCust, transactions, 30));
        customers.add(new Customer(++idCust, transactions, 20));
    }

    public List<Customer> findAllCustomers() {
        return customers;
    }

    public Customer findOneCustomerById(Long id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }


    public Customer saveCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }


    public Customer deleteCustomerById(Long id) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getId() == id) {
                iterator.remove();
                return customer;
            }
        }
        return null;
    }


    public Customer calculateTotalCustomerPoint(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();
        calculateCustomerPoint(customer, transactions);
        return customer;

    }

    public Customer calculateCustomerPointPerMonth(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();
        List<Transaction> monthTransactions = new ArrayList<Transaction>();
        LocalDate dateBefore = transactions.get(0).getDate();
        monthTransactions.add(transactions.get(0));
        for (int i = 1; i < transactions.size(); ++i) {
            if (ChronoUnit.DAYS.between(dateBefore, transactions.get(i).getDate()) <= 30) {
                monthTransactions.add(transactions.get(i));
            }
        }
        calculateCustomerPoint(customer, monthTransactions);
        return customer;
    }

    public Customer calculateCustomerPoint(Customer customer, List<Transaction> transactions) {
        int prevPoint = customer.getPoint();
        int newPoint = 0;
        double spent = transactions.stream()
                .map(Transaction::getItems)
                .flatMap(items -> items.stream())
                .mapToDouble(Item::getPrice)
                .sum();

        if (spent > 100) {
            newPoint = (int) (spent - 100) * 2 + 50 + prevPoint;
        }
        if (spent > 50 && spent < 100) {
            newPoint = prevPoint + 50;
        }
        customer.setPoint(newPoint);

        return customer;

    }

}
