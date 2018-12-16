package com.hfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DoubanDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoubanDataApplication.class, args);

		System.out.println("---启动完毕---");
	}

}
