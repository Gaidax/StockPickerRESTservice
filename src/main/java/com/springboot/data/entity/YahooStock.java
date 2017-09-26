package com.springboot.data.entity;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class YahooStock {
	
	public static final Logger logger = LoggerFactory.getLogger(YahooStock.class);
	
	@Id 
	private String id;
	
	private String symbol;
	private String name;
	private String currency;
	private BigDecimal price;
	private BigDecimal change;
	private BigDecimal peg;
	private BigDecimal dividend;

	public YahooStock() {

	}
	
	public String getSymbol() {
		return symbol;
	}



	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public BigDecimal getChange() {
		return change;
	}



	public void setChange(BigDecimal change) {
		this.change = change;
	}



	public BigDecimal getPeg() {
		return peg;
	}



	public void setPeg(BigDecimal peg) {
		this.peg = peg;
	}



	public BigDecimal getDividend() {
		return dividend;
	}



	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}



	public YahooStock getStock(String symb) {
		symbol = symb;
		Stock stock = null;
		try {
			stock = YahooFinance.get(symb);
		} catch (IOException e) {
			logger.error(e.toString());
			logger.error(e.getMessage());
		}

		price = stock.getQuote().getPrice();
		change = stock.getQuote().getChangeInPercent();
		peg = stock.getStats().getPeg();
		dividend = stock.getDividend().getAnnualYieldPercent();
		return this;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Stock[id=%s, symb='%s', price='%s', change = '%s', peg = '%s', dividend = '%s']",
                id, symbol, price, change, peg, dividend);
    }

}
