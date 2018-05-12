package com.gt.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gt.bean.dao.SQLQuery;
import com.gt.exception.AppException;


@Service
public class DBService {

	@Autowired
	@Qualifier("mysqlDS")
	DataSource ds;
	


	public void processSQL(SQLQuery sqlQuery)throws AppException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<LinkedHashMap<String, String>> recList = new ArrayList<LinkedHashMap<String, String>>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sqlQuery.getSql());

			ResultSetMetaData meta = null;
			int NumberCols = 0;
			int i = 0;

			while (rs.next()) {

				if (i == 0) {
					meta = rs.getMetaData();
					NumberCols = meta.getColumnCount();
					i++;
				}

				LinkedHashMap<String, String> fields = new LinkedHashMap<String, String>();
				for (int x = 1; x <= NumberCols; x++) {

					fields.put(meta.getColumnLabel(x), rs.getString(x));
				}
				recList.add(fields);
			}

		} catch (Exception e) {
			sqlQuery.setError(e.getMessage());
			System.out.println("Error :" + sqlQuery.getSql());
			throw new AppException(e);
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		if (!recList.isEmpty()) {
			sqlQuery.setResultList(recList);
			sqlQuery.setHasResult(true);
		}

	}

	public void processSQLAsObject(SQLQuery sqlQuery)throws AppException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<LinkedHashMap<Object, Object>> recList = new ArrayList<LinkedHashMap<Object, Object>>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sqlQuery.getSql());

			ResultSetMetaData meta = null;
			int NumberCols = 0;
			int i = 0;

			while (rs.next()) {

				if (i == 0) {
					meta = rs.getMetaData();
					NumberCols = meta.getColumnCount();
					i++;
				}

				LinkedHashMap<Object, Object> fields = new LinkedHashMap<Object, Object>();
				for (int x = 1; x <= NumberCols; x++) {

					fields.put(meta.getColumnLabel(x), rs.getString(x));
				}
				recList.add(fields);
			}

		} catch (Exception e) {
			sqlQuery.setError(e.getMessage());
			System.out.println("Error :" + sqlQuery.getSql());
			throw new AppException(e);
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		if (!recList.isEmpty()) {
			sqlQuery.setResultListsObj(recList);
			sqlQuery.setHasResult(true);
		}

	}

	
	public void processSQL(SQLQuery sqlQuery, boolean autokey)throws AppException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			int rec = stmt.executeUpdate(sqlQuery.getSql(), Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();

			if (rs.next() && rec == 1) {
				sqlQuery.setLastInsertID(rs.getString(1));
			}
		} catch (Exception e) {
			sqlQuery.setError(e.getMessage());
			throw new AppException(e);

		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

}
