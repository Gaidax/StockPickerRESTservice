/*package com.springboot.data.repository;

import java.util.Calendar;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.springboot.data.entity.YahooStock;

import yahoofinance.histquotes.Interval;


@RepositoryRestResource(collectionResourceRel = "stocks", path = "stocks")
public interface StockRepository extends MongoRepository<YahooStock, String> {

	List<YahooStock> findStockByQuote(@Param("quote") String quote);
	List<YahooStock> findStockHistory(@Param("quote") String quote, 
			@Param("dateFrom") Calendar from, @Param("dateTo") Calendar to, @Param("interval") Interval interval);

}*/