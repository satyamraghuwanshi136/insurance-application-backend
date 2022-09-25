package com.insuranceapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.insuranceapp.model.Purchase;

@Service
public class AppScheduler {

	Logger log=org.slf4j.LoggerFactory.getLogger(AppScheduler.class);
	@Autowired
	PurchaseService service;
	@Scheduled(fixedRate=14400000)
	public void Pending() {
		List<Purchase> pendingList=service.getPurchaseByStatus("Pending");
		LocalDateTime now=LocalDateTime.now();
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		String time=customFormatter.format(now);
		log.info("Last review taken on "+time);
		System.out.println("No of reviews pending are "+pendingList.size());
	}
}
