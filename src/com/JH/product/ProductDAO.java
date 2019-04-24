package com.JH.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.JH.util.DBConnector;

public class ProductDAO {
	public ProductDTO selectOne(int num) throws Exception {
		ProductDTO dto = null;
		Connection con = DBConnector.getConnection();
		String sql = "select * from product where pnum = ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory(rs.getString("category"));
			dto.setPname(rs.getString("pname"));
			dto.setPrice(rs.getInt("price"));
			dto.setStock(rs.getInt("stock"));
			
		}
		DBConnector.disConnect(rs, st, con);
		return dto;
		
	}
	
	public ArrayList<ProductDTO> selectList() throws Exception {
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		Connection con = DBConnector.getConnection();
		ProductDTO dto = null;
		String sql = "select * from product order by pnum desc";
		
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory(rs.getString("category"));
			dto.setPname(rs.getString("pname"));
			dto.setPrice(rs.getInt("price"));
			dto.setStock(rs.getInt("stock"));
		
			ar.add(dto);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
	}
	
	public int insert(ProductDTO dto, Connection con) throws Exception {
		int result = 0;
		String sql = "insert into product values(product_seq.nextval,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		
		st.setString(1, dto.getCategory());
		st.setString(2, dto.getPname());
		st.setInt(3, dto.getPrice());
		st.setInt(4, dto.getStock());
		
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	//ProductDTO dto
	public int update(ProductDTO dto, Connection con) throws Exception {
		int result = 0;
		String sql = "update product set stock = stock + ? where pnum = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, dto.getStock()); //dto.getstock()
		st.setInt(2, dto.getPnum()); //dto.getpnum
		
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
}
