package com.insuranceapp;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Date;
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
import com.insuranceapp.model.FamilyMembers;
import com.insuranceapp.model.Purchase;
import com.insuranceapp.repo.Customer_Repo;
import com.insuranceapp.repo.FamilyRepository;
import com.insuranceapp.service.CustomerServiceImple;
import com.insuranceapp.service.FamilyServiceImpl;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyMemberTest {

	@Mock
	public FamilyRepository repo;;

	@InjectMocks
	private FamilyServiceImpl service;

	// create new family Test
	@SuppressWarnings("deprecation")
	@Test
	public void addFamily() throws Exception {
		Customer customer=new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
		Purchase purchase = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime
				.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
		Date date=new Date(2000, 11, 12);
		FamilyMembers family = new FamilyMembers(23,"name","mother",date, "8962228044",customer,purchase);
		Mockito.when(repo.save(family)).thenReturn(family);
		assertEquals(family,family);

	}
	// delete family by id
		@SuppressWarnings("deprecation")
		@Test
		public void deleteFamilyByIdTest() {
			Customer customer = new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
			Purchase purchase = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime
		    .parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
			Date date=new Date(2000, 11, 12);
			FamilyMembers family = new FamilyMembers(23,"name","mother",date, "8962228044",customer,purchase);
			service.deleteFamily(family.getFamilyMemId());
			verify(repo, times(1)).deleteById(family.getFamilyMemId());
			
		}
		
		 //Update family Test
		 
		   @SuppressWarnings("deprecation")
		   @Test
		   public void updateFamilyByIdTest() { 
			   Customer customer = new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
				Purchase purchase = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime
			    .parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
				Date date=new Date(2000, 11, 12);
				FamilyMembers family = new FamilyMembers(23,"name","mother",date, "8962228044",customer,purchase);
			    when(repo.findById(family.getFamilyMemId())).thenReturn(Optional.of(family));
			    service.updateFamily(family, family.getFamilyMemId());
			     verify(repo,times(1)).save(family);
		   }
		 //get all family test
			@SuppressWarnings("deprecation")
			@Test
			public void getAllFamilyTest() {
				Customer customer = new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
				Purchase purchase = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime
			    .parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
				Date date=new Date(2000, 11, 12);
				when(repo.findAll())
						.thenReturn(Stream.of(new FamilyMembers(23,"name","mother",date, "8962228044",customer,purchase))
								.collect(Collectors.toList()));
				assertEquals(1, service.showAll().size());
			}
				//get family by customer and purchase Id
				@SuppressWarnings("deprecation")
				@Test
				public void getFamilyByCustIdAndPurchaseId() {
					Customer customer = new Customer(33, "sneha", "sneha123@gmail.com", "sneha@123", "1234567890");
					Purchase purchase = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime
				    .parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
					Date date=new Date(2000, 11, 12);
					when(repo.findAll())
							.thenReturn(Stream.of(new FamilyMembers(23,"name","mother",date, "8962228044",customer,purchase))
									.collect(Collectors.toList()));
					assertEquals(1, service.getByCustPurchaseId(33,1).size());
				}
		 

}
