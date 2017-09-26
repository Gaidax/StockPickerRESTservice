package com.springboot.data.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.springboot.data.entity.YahooStock;


@RepositoryRestResource(collectionResourceRel = "stocks", path = "stocks")
public interface StockRepository extends MongoRepository<YahooStock, String> {

	List<YahooStock> findStockBySymbol(@Param("symbol") String symbol);

}