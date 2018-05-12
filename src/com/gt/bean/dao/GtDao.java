package com.gt.bean.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gt.exception.AppException;
import com.gt.service.ApplicationService;



@Repository
public class GtDao {
	@Autowired
	ApplicationService appService;

	public boolean registerAccount(String email)
			throws AppException {
		try {

			String sql = "SELECT MemberID FROM member WHERE email='"+email+"'" ;
			
			SQLQuery sqlQuery = appService.processSQL(sql);

			if (sqlQuery.hasResults()) {
				return false;
			}else{
				sql = "INSERT INTO member(email, Balance, CreatedDate) VALUE('"+email+"',10000.00, NOW())";
				appService.processSQL(sql, true);
				return true;
			}
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	public double getAccountBalance(String email)
			throws AppException {
		double balance = -1.00;

		try {
			String sql = "SELECT MemberID, Balance  FROM member WHERE email='"+email+"'" ;
			
			SQLQuery sqlQuery = appService.processSQL(sql);

			if (sqlQuery.hasResults()) {
				List<LinkedHashMap<String, String>> recList = sqlQuery.getResultList();
				if (recList != null) {
					LinkedHashMap<String, String> rec = recList.get(0);
					balance = Double.valueOf( rec.get("Balance") );
				}
			}
		} catch (Exception e) {
			throw new AppException(e);
		}
		
		return balance;
	}
	
	public boolean transfer(String email, String transferee, double amount)
			throws AppException {
		try {
			String sql = "INSERT INTO transaction(email,transferee,action,Amount) VALUE('"+email+"','"+transferee+"', 'transfer',"+amount+")";
			appService.processSQL(sql, true);
			return true;
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	
	public List<LinkedHashMap<String, String>> retrieveLatestTransaction(String email)
			throws AppException {
		try {
			String sql = "SELECT TransacionID id, email `from`, transferee `to`,`action` `type` ,Amount amount,CreatedDate `datetime` FROM `transaction` WHERE email ='"+email+"' OR transferee ='"+email+"'";
			SQLQuery sqlQuery = appService.processSQL(sql);
			if (sqlQuery.hasResults()) {
				return sqlQuery.getResultList();
			}
			
		} catch (Exception e) {
			throw new AppException(e);
		}
		return null;
	}
}
