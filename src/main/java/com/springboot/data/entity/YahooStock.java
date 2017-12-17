package com.springboot.data.entity;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

@Component("StockService")
public class YahooStock {
	
	public static final Logger logger = LoggerFactory.getLogger(YahooStock.class);
	
	@SuppressWarnings("unused")
	private String symbol;
	private BigDecimal close;
	private BigDecimal open;
	private long vol;
	private BigDecimal adj;
	private BigDecimal high;
	private BigDecimal low;
	private String date;

	public YahooStock() {
		
	}
	
	public YahooStock(String symbol, BigDecimal open, BigDecimal close, 
			long vol, BigDecimal adj, BigDecimal high,
			BigDecimal low, String date) {
		this.symbol = symbol;
		this.open = open;
		this.close = close;
		this.vol = vol;
		this.adj = adj;
		this.high = high;
		this.low = low;
		this.date = date;

	}
	
	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
	}

	public BigDecimal getAdj() {
		return adj;
	}

	public void setAdj(BigDecimal adj) {
		this.adj = adj;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<YahooStock> getStock(String quote, Calendar from, Calendar to, Interval interval) {
		symbol = quote;
		List<HistoricalQuote> history = null;
		try {
			history =  (YahooFinance.get(quote, from, to, interval)).getHistory();
		} catch (IOException e) {
			logger.error(e.toString());
			logger.error(e.getMessage());
		}
		List<YahooStock> historyStocks = new ArrayList<YahooStock>();
		for(HistoricalQuote historyQuote : history) {
			
			historyStocks.add(new YahooStock(historyQuote.getSymbol(), historyQuote.getOpen(), 
					historyQuote.getClose(), historyQuote.getVolume(),
					historyQuote.getAdjClose(), historyQuote.getHigh(), historyQuote.getLow(),
					new SimpleDateFormat("yyyy-MM-dd").format(historyQuote.getDate().getTime())));
		}
	
		return historyStocks;
	}
	
	public List<YahooStock> getStock(String quote, boolean historical) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		if(historical) {
			from.add(Calendar.MONTH, -1);
			return getStock(quote, from, to, Interval.WEEKLY);
		} else {
			from.add(Calendar.DAY_OF_WEEK, -1);
			return getStock(quote, from, to, Interval.DAILY);
		}
	}
	
	public Interval defineInterval(String stringInt) {
		switch(stringInt){
		case "WEEKLY":  return Interval.WEEKLY;
		case "MONTLY" : return Interval.MONTHLY;
		case "DAILY" : return Interval.DAILY;
		default : return null;
		}
	}

}
