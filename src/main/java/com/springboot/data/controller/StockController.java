package com.springboot.data.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.data.entity.CurrentStock;
import com.springboot.data.entity.HistoryStock;

import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping("/api")
public class StockController {
	public static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private HistoryStock yahooHistStock;
	@Autowired
	private CurrentStock currentStock;

	@RequestMapping("/stockhis/{quote}")
    public String getHistoryStock(@PathVariable("quote") String quote) throws JsonGenerationException, 
    JsonMappingException, IOException {
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();

	    mapper.writeValue(out, yahooHistStock.getHistoryStock(quote));

	    return new String(out.toByteArray());
    }
	
	@RequestMapping("/stock/{quote}")
    public String getStock(@PathVariable("quote") String quote) throws JsonGenerationException, 
    JsonMappingException, IOException {
	    final ObjectMapper mapper = new ObjectMapper();
	    
	    return mapper.writeValueAsString(currentStock.getCurrentStock(quote));
    }
	
	@RequestMapping("/stockhis/{quote}/{from}/{to}/{interval}")
    public String getHistoryStock(@PathVariable("quote") String quote, 
    		@PathVariable("from") String from, @PathVariable("to")String to, 
    		@PathVariable("interval") String interval) throws JsonGenerationException, 
    JsonMappingException, IOException {
		try{
			Calendar fromCal = Calendar.getInstance();
			fromCal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(from));
			Calendar toCal = Calendar.getInstance();
			toCal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(to));
			
			Interval inter = yahooHistStock.defineInterval(interval);
			
		    final ByteArrayOutputStream out = new ByteArrayOutputStream();
		    final ObjectMapper mapper = new ObjectMapper();

		    mapper.writeValue(out, (yahooHistStock.getHistoryStockInterval(quote, fromCal, toCal, inter)));

		    return new String(out.toByteArray());
			
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
    }
}
