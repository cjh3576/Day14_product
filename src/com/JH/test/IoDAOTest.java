package com.JH.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;

import com.JH.io.IoDAO;
import com.JH.io.IoDTO;
import com.JH.product.ProductDAO;
import com.JH.product.ProductDTO;
import com.JH.util.DBConnector;

public class IoDAOTest {
	//selectone
	//@Test
	public void test() throws Exception {
		IoDAO dao = new IoDAO();
		IoDTO dto = dao.selectOne(1);
		assertNotNull(dto);
	}
	//selectlist
	//@Test
	public void test2() throws Exception {
		IoDAO dao = new IoDAO();
		ArrayList<IoDTO> dto = dao.selectList();
		assertNotEquals(0, dto.size());
	}
	//insert
	@Test
	public void test3() throws Exception {
		IoDAO dao = new IoDAO();
		IoDTO dto = new IoDTO();
		int result = 0;
		dto.setPnum(1);
		dto.setIn_pct(20);
		dto.setIn_date("2019/04/23");
		Connection con = null;
		try {
			con = DBConnector.getConnection();
			con.setAutoCommit(false);
			result = dao.insert(dto, con);
			if(result<1) {
				throw new Exception();
			}

			ProductDAO pdao = new ProductDAO();
			ProductDTO pdto = new ProductDTO();

			pdto.setPnum(122);
			pdto.setStock(dto.getIn_pct());
			result = pdao.update(pdto, con);

			if (result<1) {
				throw new Exception();
			}
			con.commit();

		} catch (Exception e) {
			con.rollback();
		} finally {
			con.close();
			con.setAutoCommit(true);
		}
		assertEquals(1, result);
	}

}
