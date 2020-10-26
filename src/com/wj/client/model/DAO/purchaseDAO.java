package com.wj.client.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wj.client.model.VO.purchaseVO;
import com.wj.utils.ConnUtil;

public class purchaseDAO {
	private static purchaseDAO instance = null;
	private purchaseDAO() {}

	public static purchaseDAO getInstance() {
		if(instance==null) {
			synchronized (purchaseDAO.class) {
				instance = new purchaseDAO();
			}
		}
		return instance;
	}


	//-------------------------------------- purchaseList table Query--------------------------------------//

	// 주문 내역 DB에 저장 (단일 제품)
	public int insertPurchaseInfo(String purchaseCode, String buyerId, String productCode, String quantity, int price, String purchaseType) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO PURCHASELIST(PURCHASE_CODE,BUYER_ID,PURCHASE_PRODUCT_CODE,PURCHASE_PRODUCT_QUANTITY,PURCHASE_PRICE,PURCHASE_TYPE) VALUES(?,?,?,?,?,?)");
			pstmt.setString(1, purchaseCode);
			pstmt.setString(2, buyerId);
			pstmt.setString(3, productCode);
			pstmt.setString(4, quantity);
			pstmt.setInt(5, price);
			pstmt.setString(6, purchaseType);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}

		return result;
	}

	// 현재 고객의 주문내역 리스트 반환
	public ArrayList<purchaseVO> getProductListAll(String id,int pageNum) {
		ArrayList<purchaseVO> purchaseList = new ArrayList<purchaseVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM "
							+ "(SELECT ROWNUM as \"prow\", PURCHASE_CODE, BUYER_ID, PURCHASE_PRODUCT_CODE, PURCHASE_DATE, PURCHASE_PRODUCT_QUANTITY, PURCHASE_PRICE, PURCHASE_TYPE,STATUS "
							+ "FROM (SELECT * FROM purchaseList ORDER BY PURCHASE_DATE DESC, PURCHASE_CODE) WHERE BUYER_ID = ? ) "
							+ "WHERE \"prow\" >= ? AND \"prow\" <= ?");
			pstmt.setString(1, id);
			pstmt.setInt(2, 4 * (pageNum-1) + 1);
			pstmt.setInt(3, 4 * pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				purchaseVO vo = new purchaseVO();
				vo.setPurchaseCode(rs.getString("PURCHASE_CODE"));
				vo.setBuyerId(rs.getString("BUYER_ID"));
				vo.setProductCode(rs.getString("PURCHASE_PRODUCT_CODE"));
				vo.setPurchaseDate(dateFormat.format(rs.getTimestamp("PURCHASE_DATE")));
				vo.setPurchaseType(rs.getString("PURCHASE_TYPE"));
				vo.setQuantity(rs.getString("PURCHASE_PRODUCT_QUANTITY"));
				vo.setPrice(rs.getInt("PURCHASE_PRICE"));
				vo.setStatus(rs.getString("STATUS"));
				purchaseList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return purchaseList;
	}

	// 현재 고객의 주문내역 총 갯수 반환
	public int getAllProductCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// 비밀번호 체크
			pstmt = conn.prepareStatement(
					"SELECT count(*) FROM PURCHASELIST WHERE BUYER_ID = ?");
			pstmt.setString(1, id);
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
}
