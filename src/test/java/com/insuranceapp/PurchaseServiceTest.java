package com.insuranceapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.insuranceapp.model.Purchase;
import com.insuranceapp.model.User;
import com.insuranceapp.repo.PurchaseRepo;
import com.insuranceapp.service.PurchaseServiceImpl;

@SpringBootTest
public class PurchaseServiceTest {
	
	@Autowired
	private PurchaseServiceImpl service;
	
	@MockBean
	private PurchaseRepo repo;
	
	/*@Test
	void  createPurchaseTest()
	{
		Purchase p = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
		when(repo.save(p)).thenReturn(p);
		assertEquals(p, service.createPurchase(p,1,1,1,1));
	}*/
	
	@Test
	void deletePurchaseByPidTest()
	{
		Purchase p = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
		service.deletePurchaseByPid(1);
		verify(repo).deleteById(1);
	}
	
	@Test
	void getPurchaseByPidTest()
	{
		Purchase p = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
		when(repo.findById(1)).thenReturn(Optional.ofNullable(p));
		assertEquals(p, service.getPurchaseByPid(1));
	}
	
	/*@Test
	void getPurchaseByCidTest()
	{
		int c_id = 2;
		when(repo.findAll()).thenReturn(Stream.of(new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved"),new Purchase(2,2,2,2, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved")).collect(Collectors.toList()));
		assertEquals(1, service.getPurchaseByCid(c_id).size());
	}*/
	
	/*@Test
	void getPurchaseByStatusTest()
	{
		when(repo.getAllByStatus("approved")).thenReturn(Stream.of(new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"),
				LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved"),
				new Purchase(2,2,2,2, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"),
						"monthly",10000L, "appr")).
				collect(Collectors.toList()));

		assertEquals(2, service.getPurchaseByStatus("approved").size());
	}*/
	
	/*@Test
	void getAllPurchaseTest()
	{
		
	}*/
	
	/*@Test
	void updatePurchaseByPidTest()
	{
		String status = "approved";
		Purchase p = new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved");
		//userRepo.save(user);
		when(repo.findById(1)).thenReturn(Optional.ofNullable(p));
		Assertions.assertEquals(p, service.updatePurchaseByPid(p,1,1));
	}*/
	
	@Test
	void getAllPurchaseTest()
	{
		when(repo.findAll()).thenReturn(Stream.of(new Purchase(1, 1, 1, 1, LocalDateTime.parse("2022-03-08T17:19:33"),
				LocalDateTime.parse("2023-03-08T17:19:33"), "monthly",10000L, "approved"),
				new Purchase(2,2,2,2, LocalDateTime.parse("2022-03-08T17:19:33"), LocalDateTime.parse("2023-03-08T17:19:33"),
						"monthly",10000L, "approved")).
				collect(Collectors.toList()));

		assertEquals(2, service.getAllPurchase().size());
	}

}
