package com.garagepulse.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagepulse.dto.GetCustomerResponseDto;
import com.garagepulse.dto.UpdateCustomerDetailsDto;
import com.garagepulse.dto.CreateCustomerDto;
import com.garagepulse.entity.Customer;
import com.garagepulse.repository.CustomerRepository;
import com.garagepulse.service.CustomerServices;

@Service
public class CustomerServiceImplementation implements CustomerServices {

	@Autowired
	public CustomerRepository repo;

	public Customer createCustomer(CreateCustomerDto dto) throws Exception {

		Optional<Customer> optCustomer = repo.findByEmail(dto.getEmail());

		if (optCustomer.isPresent()) {
			throw new Exception("Customer already Exists!");
		} else {
			Customer customer = new Customer();
			customer.setFirstName(dto.getFirstName());
			customer.setLastName(dto.getLastName());
			customer.setPhone(dto.getPhone());
			customer.setEmail(dto.getEmail());
			customer.setAddress(dto.getAddress());

			customer = repo.save(customer);
			return customer;
		}
	}

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	@Override
	public GetCustomerResponseDto getCustomerById(int id) throws Exception {

		Optional<Customer> optCustomer = repo.findById(id);
		if (optCustomer.isPresent()) {
			GetCustomerResponseDto dto = new GetCustomerResponseDto();
			Customer customer = optCustomer.get();
			dto.setId(customer.getId());
			dto.setFirstName(customer.getFirstName());
			dto.setLastName(customer.getLastName());
			dto.setPhone(customer.getPhone());
			dto.setEmail(customer.getEmail());
			dto.setAddress(customer.getAddress());

			return dto;
		}
		throw new Exception("Customer not found");
	}

	public Customer updateCustomer(int id, UpdateCustomerDetailsDto dto) throws Exception {

		Optional<Customer> optCustomer = repo.findById(id);

		if (optCustomer.isPresent()) {
			Customer cust = optCustomer.get();

			cust.setFirstName(dto.getFirstName());
			cust.setLastName(dto.getLastName());
			cust.setPhone(dto.getPhone());
			cust.setEmail(dto.getEmail());
			cust.setAddress(dto.getAddress());

			repo.save(cust);

			return cust;
		}
		throw new Exception("No Record Found!");

	}

	public String deleteCustomer(int id) throws Exception {
		Optional<Customer> customer = repo.findById(id);
		Customer cust = new Customer();
		if (customer.isPresent()) {
			cust = customer.get();

			repo.delete(cust);
			return "Customer Deleted!";
		} else {
			throw new Exception("No Record Found!");
		}
	}
}
