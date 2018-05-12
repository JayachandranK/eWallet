package com.gt.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.bean.dao.GtDao;
import com.gt.exception.AppException;



@Service
public class GTService {

	@Autowired
	ApplicationService appService;
	
	@Autowired
	GtDao gtDao;
	
	@Autowired
	HttpServletRequest req;

	public boolean registerAccount(String email) throws AppException {
		try {
			return gtDao.registerAccount(email);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	public double getAccountBalance(String email) throws AppException {
		try {
			return gtDao.getAccountBalance(email);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	
	public boolean transfer(String email, String transferee, double amount) throws AppException {
		try {
			return gtDao.transfer(email, transferee, amount);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	public List<LinkedHashMap<String, String>> retrieveLatestTransaction(String email) throws AppException {
		try {
			return gtDao.retrieveLatestTransaction(email);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
}
