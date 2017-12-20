package com.springboot.data.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import yahoofinance.histquotes.Interval;

@Component("StockService")
public class Stock {
	
	public static final Logger logger = LoggerFactory.getLogger(Stock.class);
	
	protected String symbol;
	protected String date;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
