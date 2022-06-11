package com.parent.dealmarketusersection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.root.dealmarketshared.entity" , "com.parent.dealmarketusersection"})

public class DealMarketUserSectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealMarketUserSectionApplication.class, args);
	}

}
