package com.hfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@EnableCaching
@SpringBootApplication
public class DoubanDataApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DoubanDataApplication.class, args);

//		DataSource ds = context.getBean(DataSource.class);
//		try {
//			Connection conn = ds.getConnection();
//			System.out.println("连接：" + conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		System.out.println("---启动完毕---");
	}

}
