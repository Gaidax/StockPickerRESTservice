package com.springboot.data.entity;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Component("StockServiceCurrent")
public class CurrentStock extends com.springboot.data.entity.Stock{

	private BigDecimal change;
	private BigDecimal price;
	
	public CurrentStock() {
		
	}
	
	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CurrentStock getCurrentStock(String quote) {
			symbol = quote;
			Stock stock = null;
			try {
				stock = YahooFinance.get(quote);
			} catch (IOException e) {
				logger.error(e.toString());
				logger.error(e.getMessage());
			}

			price = stock.getQuote().getPrice();
			change = stock.getQuote().getChangeInPercent();
			date = stock.getQuote().getLastTradeDateStr();
			return this;
		
	}

}
