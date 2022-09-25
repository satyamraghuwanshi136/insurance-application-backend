package com.insuranceapp;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.insuranceapp.model.CustDetails;
import com.insuranceapp.repo.CustDetailsRepository;
import com.insuranceapp.service.CustDetailsService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDetailsTests {
	

		@Mock
		public CustDetailsRepository custDetailsRepository;

		@InjectMocks
		private CustDetailsService custDetailsService;

		// save customer details test
		@SuppressWarnings("deprecation")
		@Test
		@Order(1)
		public void saveCustomerDetailsTest() throws Exception {
			CustDetails custDetails = new CustDetails();
		    custDetails.setArea("catRoad");
		    custDetails.setCity("indore");
		    custDetails.setCoName("ramesh");
		    custDetails.setCustId(1);
		    Date date=new Date(2000, 11, 12);
		    custDetails.setDob(date);
		    custDetails.setGender('F');
		    custDetails.setIdProof("678965489");
		    custDetails.setIncome(40000);
		    custDetails.setLocality("sai royal palm");
		    custDetails.setNationality("indian");
		    custDetails.setOccupation("service");
		    custDetails.setPincode("453331");
		    custDetails.setPlaceOfBirth("nagda");
		    custDetails.setRelationship("single");
		    custDetails.setState("madhyapradesh");
		    custDetails.setStreet("sai royal palm");
			Mockito.when(custDetailsRepository.save(custDetails)).thenReturn(custDetails);
			assertEquals(custDetails, custDetails);

		}
		//get customer details by id
		  
		  @SuppressWarnings("deprecation")
		  @Test
		  @Order(2) 
		  public void getCustomerDetailsByIdTest()
		  {
			    int customerId=1;
			    CustDetails custDetails = new CustDetails();
			    custDetails.setArea("catRoad");
			    custDetails.setCity("indore");
			    custDetails.setCoName("ramesh");
			    custDetails.setCustId(1);
			    Date date=new Date(2000, 11, 12);
			    custDetails.setDob(date);
			    custDetails.setGender('F');
			    custDetails.setIdProof("678965489");
			    custDetails.setIncome(40000);
			    custDetails.setLocality("sai royal palm");
			    custDetails.setNationality("indian");
			    custDetails.setOccupation("service");
			    custDetails.setPincode("453331");
			    custDetails.setPlaceOfBirth("nagda");
			    custDetails.setRelationship("single");
			    custDetails.setState("madhyapradesh");
			    custDetails.setStreet("sai royal palm");
			    
			    when(custDetailsRepository.findById(1)).thenReturn(Optional.ofNullable(custDetails));
				Assertions.assertEquals(custDetails, custDetailsService.getById(customerId));
			    }
		// delete customer by id
			  
			  @SuppressWarnings("deprecation")
			  @Test
			  @Order(3) 
			  public void deleteCustomerByIdTest() 
			  { 
				    CustDetails custDetails = new CustDetails();
				    custDetails.setArea("catRoad");
				    custDetails.setCity("indore");
				    custDetails.setCoName("ramesh");
				    custDetails.setCustId(1);
				    Date date=new Date(2000, 11, 12);
				    custDetails.setDob(date);
				    custDetails.setGender('F');
				    custDetails.setIdProof("678965489");
				    custDetails.setIncome(40000);
				    custDetails.setLocality("sai royal palm");
				    custDetails.setNationality("indian");
				    custDetails.setOccupation("service");
				    custDetails.setPincode("453331");
				    custDetails.setPlaceOfBirth("nagda");
				    custDetails.setRelationship("single");
				    custDetails.setState("madhyapradesh");
				    custDetails.setStreet("sai royal palm");
				    custDetailsRepository.save(custDetails);
			        custDetailsService.delete(custDetails.getCustId());
			        verify(custDetailsRepository, times(1)).deleteById(custDetails.getCustId()); 
    }
			    //get all customerDetails test 
				  @SuppressWarnings("deprecation")
				  @Test
				  @Order(4) 
				  public void getAllCustomerDetailsTest() { 
				  when(custDetailsRepository.findAll()).thenReturn(Stream.of(
				  new CustDetails(2, 'f',"indian", "business", 20000, "ujjain","897664732", "praveen",
				  "married","china town", "new era", "vijay nagar", "indore", "MP","452009"),
				  new CustDetails(3, 'M',"indian", "business", 30000, "ujjain","8976647321", "sangeeta",
				 "single","china town", "new era", "vijay nagar", "indore", "MP","452109"))
				 .collect(Collectors.toList())); 
				  assertEquals(2,custDetailsService.listAll().size()); 
				  }
				  
				//Update customerDetails test.
					 @SuppressWarnings("deprecation")
					 @Test
					 @Order(5) 
					 public void updateCustomerDetailsByIdTest() 
					 {
						 CustDetails custDetails = new CustDetails();
						    custDetails.setArea("catRoad");
						    custDetails.setCity("indore");
						    custDetails.setCoName("ramesh");
						    custDetails.setCustId(1);
						    Date date=new Date(2000, 11, 12);
						    custDetails.setDob(date);
						    custDetails.setGender('F');
						    custDetails.setIdProof("678965489");
						    custDetails.setIncome(40000);
						    custDetails.setLocality("sai royal palm");
						    custDetails.setNationality("indian");
						    custDetails.setOccupation("service");
						    custDetails.setPincode("453331");
						    custDetails.setPlaceOfBirth("nagda");
						    custDetails.setRelationship("single");
						    custDetails.setState("madhyapradesh");
						    custDetails.setStreet("sai royal palm");
						 
					 when(custDetailsRepository.findById(custDetails.getCustId())).thenReturn(Optional.of(
					 custDetails));
					 custDetailsService.updateCustDetails(custDetails, custDetails.getCustId()); 
					 verify(custDetailsRepository,times(1)).save(custDetails); 
					 }
					 
						
						  //get all customerDetailsByState test
						  
						  @Test
						  @Order(6) 
						  public void getAllCustomerDetailsByStateTest() 
						  {
							  String state="MP";
						      when(custDetailsRepository.findByStateLike(state)).thenReturn(Stream.of( new
						      CustDetails(2, 'f',"indian", "business", 20000, "ujjain","897664732",
						      "praveen", "married","china town", "new era", "vijay nagar", "indore",
						      "MP","452009"), 
						      new CustDetails(3, 'M',"indian", "business", 30000,
						      "ujjain","8976647321", "sangeeta", "single","china town", "new era",
						      "vijay nagar", "indore", "MP","452109")) .collect(Collectors.toList()));
						      assertEquals(2, custDetailsService.getByState(state).size());
						  }
						  @SuppressWarnings("deprecation")
						  @Test
						  @Order(7) 
						  public void getAllCustomerDetailsByPincode() 
						  {
							  
						      when(custDetailsRepository.findByPincodeLike("452009")).thenReturn(Stream.of( new
						      CustDetails(2, 'f',"indian", "business", 20000, "ujjain","897664732",
						      "praveen", "married","china town", "new era", "vijay nagar", "indore",
						      "MP","452009"), 
						      new CustDetails(3, 'M',"indian", "business", 30000,
						      "ujjain","8976647321", "sangeeta", "single","china town", "new era",
						      "vijay nagar", "indore", "MP","452009")) .collect(Collectors.toList()));
						      assertEquals(2, custDetailsService.getByPincode("452009").size());
						  }
						  @SuppressWarnings("deprecation")
						  @Test
						  @Order(8) 
						  public void getAllCustomerDetailsByCity() 
						  {
							  
						      when(custDetailsRepository.findByCityLike("indore")).thenReturn(Stream.of( new
						      CustDetails(2, 'f',"indian", "business", 20000, "ujjain","897664732",
						      "praveen", "married","china town", "new era", "vijay nagar", "indore",
						      "MP","452009"), 
						      new CustDetails(3, 'M',"indian", "business", 30000,
						      "ujjain","8976647321", "sangeeta", "single","china town", "new era",
						      "vijay nagar", "indore", "MP","452009")) .collect(Collectors.toList()));
						      assertEquals(2, custDetailsService.getByCity("indore").size());
						  }
						 
				
}



