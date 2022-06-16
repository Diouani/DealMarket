package com.parent.dealmarketusersection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("")
@EntityScan({"com.root.dealmarketshared.entity" ,"com.root.dealmarketshared.dto", "com.parent.dealmarketusersection"})

public class DealMarketUserSectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealMarketUserSectionApplication.class, args);
	}

}
