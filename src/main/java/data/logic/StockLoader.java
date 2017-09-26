package data.logic;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.data.entity.YahooStock;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockLoader {
	
	public static final Logger logger = LoggerFactory.getLogger(StockLoader.class);
	
	
	public StockLoader() {
	}
	
	public static Map<String, Stock> loadStocksByNames(String[] stockStrings) {
		//String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
		
		try {
			return YahooFinance.get(stockStrings);
		} catch (IOException e) {
			logger.error(e.toString());
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static YahooStock loadStockByName(String symb) {
			return new YahooStock().getStock(symb);
		}
}
