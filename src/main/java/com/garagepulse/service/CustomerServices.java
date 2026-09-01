package com.garagepulse.service;

import java.util.List;

import com.garagepulse.dto.CreateCustomerDto;
import com.garagepulse.dto.GetCustomerResponseDto;
import com.garagepulse.dto.UpdateCustomerDetailsDto;
import com.garagepulse.entity.Customer;

public interface CustomerServices {

	Customer createCustomer(CreateCustomerDto dto) throws Exception;

	List<Customer> getAllCustomers();

	GetCustomerResponseDto getCustomerById(int id) throws Exception;

	Customer updateCustomer(int id, UpdateCustomerDetailsDto dto) throws Exception;

	String deleteCustomer(int id) throws Exception;
}
