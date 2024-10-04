package com.example.demo.service.customer_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.customer.CustomerRepository;

import com.example.demo.dto. *;
import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Place;
import com.example.demo.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;


    @Override
    public void save(CustomerDTO customerDTO) {

        Customer customer = convertToEntity.convertCustomerToEntity(customerDTO);
        customerRepository.save(customer);

    }

    @Override
    public void update(CustomerDTO customerDTO) {
        Customer customer = convertToEntity.convertCustomerToEntity(customerDTO);
        customerRepository.save(customer);
    }
    @Override
    public void delete(CustomerDTO customerDTO) {
        Customer customer = convertToEntity.convertCustomerToEntity(customerDTO);
        customerRepository.delete(customer);
    }

    @Override
    public Optional<CustomerDTO> findByIdWithTickets(int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(convertToDTO::convertCustomerToDTO);
    }

    @Override
    public Optional<CustomerDTO> findById(int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(convertToDTO::convertCustomerToDTO);
    }

    @Override
    public List<CustomerDTO> findAll() {

        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(convertToDTO::convertCustomerToDTO)
                .collect(Collectors.toList());


    }
}
