package com.ccshxt.lesson21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.sql.ResultSetMetaData;

public final class DBUtils {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tianyao32";
	
	private static final String USER = "root";
	
	private static final String PASSWORD = "";
	
	private Connection conn;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	private static DBUtils db;
	
	private DBUtils(){
		//ʲô����д
	}
	
	/**
	 * data access object
	 * @return
	 */
	public static DBUtils dao() {
		if(db == null) {
			db = new DBUtils();
		}
		return db;
	}
	
	
	/**
	 * �������ݿ�����
	 * @return
	 */
	private Connection getConn() {
		try {
		//1.��������
			System.out.println("����������������");
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		//��ȡ����
		} catch (ClassNotFoundException e) {
			System.err.println("INFO���������ݿ�����ʧ�ܿ��ܡ�com.mysql.jdbc.Driver д���ˣ�");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("INFO����ȡ����ʧ�ܣ��������˺�������󣬿��������ݿ����ƴ���");
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public List<Map<String,String>> find(String sql,Object... params) {
		List<Map<String,String>> list = new ArrayList<>();
		try {
			ps = getConn().prepareStatement(sql);//Ԥ���봦��
			if(params != null && params.length > 0) {
				for (int i = 0;  i < params.length; i++) {
					ps.setObject(i + 1, params[i]);//��Ԥ���������󴫵ݲ���
				}
			}
			System.out.println(ps);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();//������Ľṹ
			int columnCount = rsmd.getColumnCount();//��ȡ������
			while(rs.next()) {
				Map<String,String> map = new HashMap<>();
				for(int i = 1; i<= columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), rs.getString(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	/**
	 * �޸�
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql,Object... params) {
		int num = 0;
		try {
			ps = getConn().prepareStatement(sql);
			if(params != null && params.length > 0) {
				for (int i = 0;  i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
		num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return num;
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
