package com.gt.bean.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class SQLQuery {

	private String sql;
	private List<LinkedHashMap<String, String>> resultLists = null;
	private List<LinkedHashMap<Object, Object>> resultListsObj = null;
	private boolean hasResults;
	private boolean hasError;
	private String error;
	private String query;
	private String lastInsertID;

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setResultList(List<LinkedHashMap<String, String>> processList) {
		this.resultLists = processList;
	}

	public List<LinkedHashMap<String, String>> getResultList() {
		return resultLists;
	}

	public LinkedHashMap<String, String> getResult() {
		if (resultLists != null && !resultLists.isEmpty()) {
			setHasResult(true);
			return resultLists.get(0);
		}
		return null;
	}

	public void setHasResult(boolean hasResults) {
		if (resultLists != null && !resultLists.isEmpty()) {
			this.hasResults = true;
		}
		this.hasResults = hasResults;
	}

	public boolean hasResults() {
		return this.hasResults;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;

	}

	public boolean hasError() {
		return this.hasError;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public String getLastInsertID() {
		return lastInsertID;
	}

	public void setLastInsertID(String lastInsertID) {
		this.lastInsertID = lastInsertID;
	}

	public List<LinkedHashMap<Object, Object>> getResultListsObj() {
		return resultListsObj;
	}

	public void setResultListsObj(List<LinkedHashMap<Object, Object>> resultListsObj) {
		this.resultListsObj = resultListsObj;
	}

}
