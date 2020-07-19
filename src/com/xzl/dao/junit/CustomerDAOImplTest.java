package com.xzl.dao.junit;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.xzl.bean.Customer;
import com.xzl2.connection.util.JDBCUtils;
import com.xzl.dao.CustomerDAOImpl;

class CustomerDAOImplTest {
	private CustomerDAOImpl dao = new CustomerDAOImpl();

	@Test
	void testInsert() {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			Customer cust = new Customer(1, "于小飞", "xiaofei@126.com", new Date(12321362136L));
			dao.insert(conn, cust);
			System.out.println("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(conn, null);
		}
	}

	@Test
	void testDeleteById() {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			dao.deleteById(conn, 13);
			System.out.println("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(conn, null);
		}
	}

	@Test
	void testUpdateConnectionCustomer() {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			Customer cust = new Customer(18, "贝多芬", "beiduofen@gmail.com", new Date(927417528634L));
			dao.update(conn, cust);
			System.out.println("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JDBCUtils.closeResource(conn, null);
		}
	}

	@Test
	void testGetCustomerById() {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection3();
			Customer cust= dao.getCustomerById(conn, 19);
			System.out.println(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JDBCUtils.closeResource(conn, null);
		}
		
	}

	@Test
	void testGetAll() {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			List<Customer> list = dao.getAll(conn);
			list.forEach(System.out::println);
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCUtils.closeResource(conn, null);
	}
	

	@Test
	void testGetCount() {

		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			Long count = dao.getCount(conn);
			System.out.println("表中的记录为："+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	void testGetMaxBrith() {
		Connection conn=null;
		try {
			conn = JDBCUtils.getConnection();
			Date maxBrith = dao.getMaxBrith(conn);
			System.out.println("最大的生日为："+maxBrith);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JDBCUtils.closeResource(conn, null);
		}
	}

}
