package com.xzl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.xzl.bean.Customer;

/*
 * 此接口用于规范针对于customers表的常用操作
 */
public interface CustomerDAO {
	/**
	 * 
	* @Description  将cust对象添加到数据库中
	* @author dxzl
	* @date 2020-7-1716:20:10
	* @param conn
	* @param cust
	 */
	void insert(Connection conn ,Customer cust);
	
	/**
	 * 
	* @Description 针对指定的id，删除表中的一条记录
	* @author dxzl
	* @date 2020-7-1716:20:53
	* @param conn
	* @param id
	 */
	void deleteById(Connection conn,int id);
	
	/**
	 * 
	* @Description 针对指定的id查询得到对应的Customer对象
	* @author dxzl
	* @date 2020-7-1716:21:40
	* @param conn
	* @param cust
	 */
	void update(Connection conn,Customer cust);
	
	/**
	 * 
	* @Description 针对指定的id查询得到对应的Customer对象
	* @author dxzl
	* @date 2020-7-1716:22:29
	* @param conn
	* @return
	 */
	Customer getCustomerById(Connection conn ,int id);
	
	/**
	 * 
	* @Description 查询表中的所有记录构成的集合
	* @author dxzl
	* @date 2020-7-1716:23:25
	* @param conn
	* @return
	 */
	List<Customer> getAll(Connection conn);
	
	/**
	 * 
	* @Description  返回数据表中的数据的条目数
	* @author dxzl
	* @date 2020-7-1716:25:13
	* @param conn
	* @return
	 */
	Long getCount(Connection conn);
	
	/**
	 * 
	* @Description 返回数据表中最大的生日
	* @author dxzl
	* @date 2020-7-1716:26:00
	* @param conn
	* @return
	 */
	Date getMaxBrith(Connection conn);
}
