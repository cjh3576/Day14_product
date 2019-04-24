package com.JH.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.JH.util.DBConnector;

public class IoDAO {
	public int insert(IoDTO dto, Connection con) throws Exception {
		int result = 0;
		
		String sql = "insert into io values (io_sequence.nextval,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		
		st.setInt(1, dto.getPnum());
		st.setInt(2, dto.getIn_pct());
		st.setString(3, dto.getIn_date());
		st.setInt(4, dto.getOut_pct());
		st.setString(5, dto.getOut_date());
		
	result = st.executeUpdate();
	DBConnector.disConnect(st, con);
	return result;
	}
	
	public IoDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnection();
		IoDTO dto = null;
		String sql = "select * from io where num = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			dto = new IoDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setNum(rs.getInt("num"));
			dto.setIn_pct(rs.getInt("in_pct"));
			dto.setIn_date(rs.getString("in_date"));
			dto.setOut_pct(rs.getInt("out_pct"));
			dto.setOut_date(rs.getString("out_date"));
			
		}
		DBConnector.disConnect(rs, st, con);
		return dto;
	}
	
	public ArrayList<IoDTO> selectList() throws Exception {
		ArrayList<IoDTO> ar = new ArrayList<IoDTO>();
		Connection con = DBConnector.getConnection();
		
		String sql = "select * from io order by num desc";
		
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			IoDTO dto = new IoDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setNum(rs.getInt("num"));
			dto.setIn_pct(rs.getInt("in_pct"));
			dto.setIn_date(rs.getString("in_date"));
			dto.setOut_pct(rs.getInt("out_pct"));
			dto.setOut_date(rs.getString("out_date"));
			ar.add(dto);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
		
	}
}
