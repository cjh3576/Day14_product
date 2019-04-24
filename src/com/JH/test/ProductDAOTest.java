package com.JH.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;

import com.JH.product.ProductDAO;
import com.JH.product.ProductDTO;
import com.JH.util.DBConnector;

public class ProductDAOTest {
	//product selectone
	//@Test
	public void test() throws Exception {
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = dao.selectOne(1);
		assertNotNull(dto);
	}
	//product selectlist
	//@Test
	public void test1() throws Exception{
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> dto = dao.selectList();
		assertNotEquals(0, dto.size());
	}
	
	//PRODUCT INSERT
	//@Test
	public void test2() throws Exception{
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		Connection con = DBConnector.getConnection();
		dto.setCategory("hi");
		dto.setPname("test");
		dto.setPrice(300);
		dto.setStock(30);
		int result = dao.insert(dto, con);
		assertEquals(1, result);
		
	}
	//update
	@Test
	public void test3() throws Exception {
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		
		Connection con = DBConnector.getConnection();
		int result = dao.update(dto, con);
		assertEquals(1, result);
		
	}
	
	
}
