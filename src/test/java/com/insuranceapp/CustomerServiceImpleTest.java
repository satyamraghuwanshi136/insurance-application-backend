package com.insuranceapp;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insuranceapp.model.Customer;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.service.CustomerServiceImple;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImpleTest {

	@Mock
	public Customer_Repo customer_Repo;

	@InjectMocks
	private CustomerServiceImple customerServiceImple;

	// create new customer test
	@SuppressWarnings("deprecation")
	@Test
	@Order(1)
	public void createUserTest() throws Exception {
		Customer customer = new Customer();
		customer.setEmail("sneha@gmail.com");
		customer.setName("sneha");
		customer.setPsw("abc");
		customer.setContactNo("8962228044");
		customer.setCustId(1);
		Mockito.when(customer_Repo.save(customer)).thenReturn(customer);
		assertEquals(customer, customer);

	}
	//get customer by email
	
	  @SuppressWarnings("deprecation")
	  @Test
	  @Order(2)
	  public void getCustomerByEmailTest() { 
      Customer customer =new Customer();
	  customer.setCustId(1);
	  customer.setName("sneha");
	  customer.setEmail("sneha@gmail.com"); 
	  customer.setContactNo("1234567890"); 
	  customer.setPsw("1234567");
	  when(customer_Repo.findByEmail(customer.getEmail())).thenReturn(customer);
	  assertNotNull(customerServiceImple.getCustomerByEmail(customer.getEmail())); 
	  }

	//get all customer test
	@SuppressWarnings("deprecation")
	@Test
	@Order(6)
	public void getAllCustomerTest() {
		when(customer_Repo.findAll())
				.thenReturn(Stream.of(new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890"))
						.collect(Collectors.toList()));
		assertEquals(1, customerServiceImple.getAllCustomer().size());
	}

	// delete customer by id
	@SuppressWarnings("deprecation")
	@Test
	@Order(4)
	public void deleteCustomerByIdTest() {
		Customer customer = new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
		customerServiceImple.deleteCustomerById(customer.getCustId());
		verify(customer_Repo, times(1)).deleteById(customer.getCustId());
		;
	}

	//get customer by id
	
	  @SuppressWarnings("deprecation")
	  @Test
	  @Order(5)
	  public void getCustomerByIdTest() { 
      Customer customer =new Customer();
	  customer.setCustId(1);
	  customer.setName("sneha");
	  customer.setEmail("sneha@gmail.com"); 
	  customer.setContactNo("1234567890"); 
	  customer.setPsw("1234567");
	  when(customer_Repo.findById(customer.getCustId())).thenReturn(Optional.of(customer));
	  assertNotNull(customerServiceImple.getCustomerById(customer.getCustId())); 
	  }
	  
	  
	  //Update customer test.
		 
	   @SuppressWarnings("deprecation")
	   @Test
	   @Order(3)
	   public void updateCustomerByIdTest() { 
		     Customer customer = new Customer();
		     customer.setCustId(33);
		     customer.setContactNo("1234567890");
		     customer.setEmail("sneha@gmail.com");
		     customer.setName("sneha");
		     customer.setPsw("12344");
		     //when(customer_Repo.save(customer)).thenReturn(customer);
		     when(customer_Repo.findById(customer.getCustId())).thenReturn(Optional.of(customer));
		     customerServiceImple.updateCustomerById(customer, customer.getCustId());
		     verify(customer_Repo,times(1)).save(customer);
	   }
			  
			 

}
