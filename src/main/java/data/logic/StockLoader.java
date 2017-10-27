package data.logic;

import java.util.ArrayList;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.data.entity.YahooStock;
import com.springboot.data.repository.StockRepository;

public class StockLoader extends TimerTask{

	public static final Logger logger = LoggerFactory.getLogger(StockLoader.class);
	public final static String[] stocks = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
	private StockRepository repository;
	
	public StockLoader(StockRepository repo) {
		repository = repo;
	}
	
	private void populate() {
		repository.deleteAll();
/*		repository.save(StockLoader.loadStockByName("INTC"));
		repository.save(StockLoader.loadStockByName("BBD-B.TO"));*/
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
	}
	
	
	public static ArrayList<YahooStock> loadMajorStocks() {
		return loadStocksByNames(stocks);
	}
	
	private static ArrayList<YahooStock> loadStocksByNames(String[] stockStrings) {
		ArrayList<YahooStock> yList = new ArrayList<YahooStock>();
		for(String stock : stockStrings) {
			yList.add(loadStockByName(stock));
		}
		
		return yList;
	}
	
	public static YahooStock loadStockByName(String symb) {
			return new YahooStock().getStock(symb);
		}

	@Override
	public void run() {
		populate();
	}
}
