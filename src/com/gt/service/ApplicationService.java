package com.gt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.bean.dao.SQLQuery;
import com.gt.exception.AppException;



@Service("applicationService")
public class ApplicationService {
	@Autowired
	public DBService dbService;
	
	@Autowired
	public GTService gtService;
	
	public SQLQuery processSQL(String query) {

		SQLQuery sqlQuery = new SQLQuery();
		sqlQuery.setSql(query);
		try {
			dbService.processSQL(sqlQuery);
		} catch (AppException e) {
			
		}
		return sqlQuery;
	}

	public SQLQuery processSQLAsObject(String query) {

		SQLQuery sqlQuery = new SQLQuery();
		sqlQuery.setSql(query);
		try {
			dbService.processSQLAsObject(sqlQuery);
		} catch (AppException e) {
			
		}
		return sqlQuery;
	}

	public SQLQuery processSQL(String query, boolean read) {
		SQLQuery sqlQuery = new SQLQuery();
		sqlQuery.setSql(query);
		try {
			dbService.processSQL(sqlQuery, read);
		} catch (AppException e) {
			
		}
		return sqlQuery;
	}



}
