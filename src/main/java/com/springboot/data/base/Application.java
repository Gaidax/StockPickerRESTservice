package com.springboot.data.base;

//import java.util.Timer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//import data.logic.StockLoader;

//@SpringBootApplication(scanBasePackages={"com.springboot.data"})
@ComponentScan({"com.springboot.data.entity"})
@ComponentScan({"com.springboot.data.controller"})
@EnableAutoConfiguration
public class Application implements CommandLineRunner {
	
/*	@Autowired
	private StockRepository repository;*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		Timer timer = new Timer();
//		timer.schedule(new StockLoader(), 0, 5000);
	}
}
