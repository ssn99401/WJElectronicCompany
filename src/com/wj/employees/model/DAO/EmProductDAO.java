package com.wj.employees.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wj.employees.model.VO.EmProductVO;
import com.wj.utils.ConnUtil;

public class EmProductDAO {
	private static EmProductDAO instance = null;
	private EmProductDAO() {}

	public static EmProductDAO getInstance() {
		if(instance==null) {
			synchronized (EmProductDAO.class) {
				instance = new EmProductDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- EmProduct table Query--------------------------------------//
	
	// 제품코드로 제품 정보 조회
	public EmProductVO getProductInfo(String productCode) {
		EmProductVO product = new EmProductVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM product WHERE pCode = ?");
			pstmt.setString(1, productCode);
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product.setpCode(productCode);
				product.setpName(rs.getString("pName"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setInfo(rs.getString("info"));
				product.setImageLink(rs.getString("imageLink"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
}
