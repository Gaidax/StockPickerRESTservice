/*package data.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.data.entity.YahooStock;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class StockLoader extends TimerTask{

	public static final Logger logger = LoggerFactory.getLogger(StockLoader.class);
	public static final  String[] stocks = new String[] {"INTC", "BABA", "TSLA", "AIR.PA"};
	private StockRepository repository;
	
	public StockLoader(StockRepository repo) {
		repository = repo;
	}
	
	private void populate() {
		//repository.deleteAll();
		ArrayList<YahooStock> majorStocks = StockLoader.loadMajorStocks();
		for(YahooStock yStock : majorStocks) {
			repository.save(yStock);
		}
		
		System.out.println("Stocks that are found with findAll():");
		System.out.println("-------------------------------");
		for (YahooStock stocks : repository.findAll()) {
			System.out.println(stocks);
		}
		
		System.out.println(repository.findStockBySymbol("INTC"));
		 
		List<YahooStock> google = new YahooStock().getStock("GOOG", true);
		System.out.println("Stocks that are found Historical:");
		System.out.println("-------------------------------");
		for(YahooStock quote : google) {
			System.out.println(quote.getDate());
		}
			
		
	}
	
	
	public static ArrayList<YahooStock> loadMajorStocks() {
		return loadStocksByNames(stocks);
	}
	
	private static ArrayList<YahooStock> loadStocksByNames(String[] quoteStrings) {
		ArrayList<YahooStock> yList = new ArrayList<YahooStock>();
		for(String quote : quoteStrings) {
			yList.add(new YahooStock().getStock(quote, false).get(0));
		}
		
		return yList;
	}
	
	@Override
	public void run() {
		populate();
	}
}
*/