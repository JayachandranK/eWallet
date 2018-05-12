package com.gt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gt.service.ApplicationService;


@RestController
@RequestMapping("/gt")
public class GTController {

	@Autowired
	ApplicationService appService;
	

	@RequestMapping(value = "/register", headers="Accept=*/*", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> registerAccount(@RequestParam(value = "email") String email) {
		Map<String, Object> context = new HashMap<String, Object>();;

		try {
			boolean registered = false;

			if (email != null) {
				registered = appService.gtService.registerAccount(email);
				
				if(registered){
					context.put("success", true);
					context.put("balance", 10000.00);
					return context;
				}
				
				
			}
		} catch (Exception e) {
		
			//return appService.buildResponse(false, "unexpected error occurred", null);
		}
		context.put("success", false);

		return context;
	}

	@RequestMapping(value = "/latestBalance", headers="Accept=*/*", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAccountBalance(@RequestParam(value = "email") String email) {
		Map<String, Object> context = new HashMap<String, Object>();;

		try {
			double balance = 0.00;

			if (email != null) {
				balance = appService.gtService.getAccountBalance(email);
				
				if(balance!= -1.00){
					context.put("success", true);
					context.put("balance", balance);
					return context;
				}
			}
		} catch (Exception e) {
			//return context;
		}
		context.put("success", false);

		return context;
	}
	
	@RequestMapping(value = "/transfer", headers="Accept=*/*", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody  Map<String, Object> transfer(@RequestParam(value = "email") String email, @RequestParam(value = "transferee") String transferee, @RequestParam(value = "amount") double amount) {
		Map<String, Object> context = new HashMap<String, Object>();;
		try {
			boolean transfered = false;

			if (email != null) {
				transfered = appService.gtService.transfer(email,transferee,amount);
				
				if(transfered){
					context.put("success", true);
					return context;
				}
			}
			context.put("success", false);
		} catch (Exception e) {
			//return appService.buildResponse(false, "unexpected error occurred", null);
		}
		return context;
	}
	

	@RequestMapping(value = "/retrieveLatestTransaction", headers="Accept=*/*", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object>  retrieveLatestTransaction(@RequestParam(value = "email") String email) {
		Map<String, Object> context = new HashMap<String, Object>();;

		List<LinkedHashMap<String, String>> transactions = new ArrayList<LinkedHashMap<String, String>>();
		try {
			if (email != null) {
				transactions = appService.gtService.retrieveLatestTransaction(email);
				context.put("success", true);
				context.put("transactions", transactions);

				return context;
			}
		} catch (Exception e) {
			//return appService.buildResponse(false, "unexpected error occurred", null);
		}
		context.put("success", false);

		return context;
	}

}
