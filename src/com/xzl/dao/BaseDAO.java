package com.xzl.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.xzl.bean.JDBCUtils;

/*
 * /*
 * DAO: data(base) access object
 * 封装了针对于数据表的通用的操作
 */

public abstract class BaseDAO {
	// 通用的增删改操作---version 2.0 （考虑上事务）
	public int update(Connection conn, String sql, Object... args) {
		// 1.预编译sql语句，返回PrepareStatement的实例
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			// 2.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 3.执行
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(null, ps);
		}
		return 0;
	}

	// 通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
	public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();

			// 获取结果的元数据：ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过ResultSetMetaData获取结果集中的列数
			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {
				T t = clazz.newInstance();
				// 处理结果集中一行数据的每一个列
				for (int i = 0; i < columnCount; i++) {
					// 获取列值
					Object columnValue = rs.getObject(i + 1);
					// 获取每个列的列名
					String columnLabel = rsmd.getColumnLabel(i + 1);
					// 给t对象指定的columnName属性，赋值为columnValue：通过反射
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

	// 通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
	public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args)  {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();

			// 获取结果集的元数据：ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过ResultSetMetaData 获取结果集中的列数
			int columnCount = rsmd.getColumnCount();
			// 创建集合对象
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				T t = clazz.newInstance();
				// 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
				for (int i = 0; i < columnCount; i++) {
					// 获取列值
					Object columnValue = rs.getObject(i + 1);
					// 获取每个列的列名
					String columnLabel = rsmd.getColumnLabel(i + 1);

					// 给t 对象指明的columnName属性，赋值为columnValue:通过反射
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);

				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}
	
	//用于查询特殊值的通用的方法
	@SuppressWarnings("unchecked")
	public <E> E getValue(Connection conn ,String sql,Object...args) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			
			rs = ps.executeQuery();
			if(rs.next()) {
				return (E) rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}
}
