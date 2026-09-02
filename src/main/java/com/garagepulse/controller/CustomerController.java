package com.garagepulse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagepulse.dto.GetCustomerResponseDto;
import com.garagepulse.dto.UpdateCustomerDetailsDto;
import com.garagepulse.dto.CreateCustomerDto;
import com.garagepulse.entity.Customer;
import com.garagepulse.service.CustomerServices;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private final CustomerServices customerService;

	public CustomerController(CustomerServices customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/Customer")
	public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDto dto) {
		Customer customer = new Customer();
		try {
			customer = customerService.createCustomer(dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}

	@GetMapping("/Customers")
	public ResponseEntity<List<GetCustomerResponseDto>> getAllCustomers() {
		List<GetCustomerResponseDto> dto = new ArrayList<>();
		dto = customerService.getAllCustomers();
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/Customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) throws Exception {
		GetCustomerResponseDto cust = new GetCustomerResponseDto();
		try {
			cust = customerService.getCustomerById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);

	}

	@PutMapping("/Customer/{id}")
	public ResponseEntity<?> updateCustomerDetail(@PathVariable int id,
			@RequestBody UpdateCustomerDetailsDto customerDetailsDto) throws Exception {
		Customer dto = new Customer();
		try {
			dto = customerService.updateCustomer(id, customerDetailsDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@DeleteMapping("/Customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
		try {
			customerService.deleteCustomer(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}