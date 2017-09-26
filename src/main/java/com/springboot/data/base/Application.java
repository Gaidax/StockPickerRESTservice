package com.springboot.data.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.springboot.data.entity.YahooStock;
import com.springboot.data.repository.StockRepository;

import data.logic.StockLoader;

@SpringBootApplication(scanBasePackages={"com.springboot.data"})
@ComponentScan({"com.springboot.data.controller"})
@EntityScan("com.springboot.data.entity")
@EnableMongoRepositories("com.springboot.data.repository")
public class Application implements CommandLineRunner {
	
	@Autowired
	private StockRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		repository.deleteAll();
		
		repository.save(StockLoader.loadStockByName("INTC"));
		System.out.println("Person found with findAll():");
		System.out.println("-------------------------------");
		for (YahooStock stocks : repository.findAll()) {
			System.out.println(stocks);
		}
		
		System.out.println(repository.findStockBySymbol("INTC"));
		
	}

}
