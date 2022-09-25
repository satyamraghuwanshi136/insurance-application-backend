package com.insuranceapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.insuranceapp.model.Nominee;
import com.insuranceapp.repo.NomineeRepository;
import com.insuranceapp.service.NomineeService;

@SpringBootTest
public class NomineeServiceTest {
	
	@Autowired
	private NomineeService service;
	
	@MockBean
	private NomineeRepository repo;
	
	/*@Test
	void saveNomineeTest()
	{
		Nominee n = new Nominee(1, 1, "john", "father", "123456");
		when(repo.save(n)).thenReturn(n);
		assertEquals(n, service.saveNominee(n, 1));
	}*/
	
	@Test
	void getByIdTest()
	{
		int id = 2;
		Date date = new Date(2022,10,10);
		Nominee n = new Nominee(2, 2, "dev",date,"father", "123457");
		when(repo.findById(id)).thenReturn(Optional.ofNullable(n));
		assertEquals(n, service.getById(id));
	}

	@Test
	void deleteTest()
	{
		int id=3;
		Date date = new Date(2022,10,10);
		Nominee n = new Nominee(3, 3, "som",date, "father", "123458");
		service.delete(id);
		verify(repo).deleteById(id);;
	}
	
	@Test
	void listAllTest()
	{
		Date date = new Date(2022,10,10);
		when(repo.findAll()).thenReturn(Stream.of(new Nominee(4,4, "dev",date, "father", "123459"),new Nominee(4,4, "som",date, "father", "123455") ).collect(Collectors.toList()));
		assertEquals(2,service.listAll().size());
	}
	
	@Test
	void updateNomineeTest()
	{
		String name = "ram";
		String relationship = "father";
		String idProof = "11111";
		Date date = new Date(2022,10,10);
		Nominee n = new Nominee(2,2,name,date,relationship,idProof);
		when(repo.findById(2)).thenReturn(Optional.ofNullable(n));
		assertEquals(n, service.updateNominee(n, 2));
	}

	/*@Test
	void findCustomerNomineeTest()
	{
		
	}*/
	
}
