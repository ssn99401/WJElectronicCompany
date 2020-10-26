package com.wj.client.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wj.client.model.VO.ProductVO;
import com.wj.utils.ConnUtil;

public class ProductDAO {
	private static ProductDAO instance = null;
	private ProductDAO() {}

	public static ProductDAO getInstance() {
		if(instance==null) {
			synchronized (ProductDAO.class) {
				instance = new ProductDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- Product table Query--------------------------------------//
	
	// 제품 전체 리스트 반환
	public ArrayList<ProductVO> getProductListAll(int pageNum) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM "
					+ "(SELECT ROWNUM as \"prow\", pcode, pname, price, category, info, imageLink FROM product) "
					+ "WHERE \"prow\" >= ? AND \"prow\" <= ? ORDER BY category, pcode");
			pstmt.setInt(1, 8 * (pageNum-1) + 1);
			pstmt.setInt(2, 8 * pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setpCode(rs.getString("pCode"));
				vo.setpName(rs.getString("pName"));
				vo.setInfo(rs.getString("info"));
				vo.setPrice(Integer.parseInt(rs.getString("price")));
				vo.setCategory(rs.getString("category"));
				vo.setImageLink(rs.getString("imageLink"));
				productList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return productList;
	}
	
	// 제품 카테코리별 리스트 반환
	public ArrayList<ProductVO> getProductListToCategory(String category, int pageNum) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM "
					+ "(SELECT ROWNUM as \"prow\", pcode, pname, price, category, info, imageLink FROM product WHERE category=?) "
					+ "WHERE \"prow\" >= ? AND \"prow\" <= ?");
			pstmt.setString(1, category);
			pstmt.setInt(2, 8 * (pageNum-1) + 1);
			pstmt.setInt(3, 8 * pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setpCode(rs.getString("pCode"));
				vo.setpName(rs.getString("pName"));
				vo.setInfo(rs.getString("info"));
				vo.setPrice(Integer.parseInt(rs.getString("price")));
				vo.setCategory(rs.getString("category"));
				vo.setImageLink(rs.getString("imageLink"));
				productList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return productList;
		
	}
	
	// 전체 제품 목록의 갯수 반환
	public int getAllProductCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// 비밀번호 체크
			pstmt = conn.prepareStatement(
					"SELECT count(*) FROM product");
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			else result = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}
	
	// 조회 제품 목록의 갯수 반환
	public int getProductCount(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT count(*) FROM product WHERE category = ?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			else result = 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}
	
	// 제품코드로 제품 정보 조회
	public ProductVO getProductInfo(String productCode) {
		ProductVO product = new ProductVO();
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
