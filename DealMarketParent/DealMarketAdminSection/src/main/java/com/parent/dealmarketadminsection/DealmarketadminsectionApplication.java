package com.parent.dealmarketadminsection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.*;

@SpringBootApplication
@EntityScan({"com.root.dealmarketshared.entity" , "com.parent.dealmarketadminsection.user"})
public class DealmarketadminsectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealmarketadminsectionApplication.class, args);
	}

}
