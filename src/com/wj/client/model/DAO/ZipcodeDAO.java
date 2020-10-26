package com.wj.client.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.wj.client.model.VO.ZipCodeVO;
import com.wj.utils.ConnUtil;

public class ZipcodeDAO {
	private static ZipcodeDAO instance = null;
	private ZipcodeDAO() {}

	public static ZipcodeDAO getInstance() {
		if(instance==null) {
			synchronized (ZipcodeDAO.class) {
				instance = new ZipcodeDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- Zipcode table Query--------------------------------------//
	
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM zipcode WHERE dong like '"+dong+"%'");
			rs= pstmt.executeQuery();
			while(rs.next()) {
				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle2) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return vecList;
	}
	
}
