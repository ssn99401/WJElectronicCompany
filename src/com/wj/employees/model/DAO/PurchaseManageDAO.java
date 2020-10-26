package com.wj.employees.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wj.client.model.VO.ClientVO;
import com.wj.employees.model.VO.EmPurchaseManageVO;
import com.wj.utils.ConnUtil;

public class PurchaseManageDAO {
	private static PurchaseManageDAO instance = null;
	private PurchaseManageDAO() {}

	public static PurchaseManageDAO getInstance() {
		if(instance==null) {
			synchronized (PurchaseManageDAO.class) {
				instance = new PurchaseManageDAO();
			}
		}
		return instance;
	}


	//-------------------------------------- purchaseList table Query--------------------------------------//

	// 주문 ?��?�� DB?�� ???�� (?��?�� ?��?��)
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

	// ?��?�� 고객?�� 주문?��?�� 리스?�� 반환
	public ArrayList<EmPurchaseManageVO> getProductListAll(int pageNum) {
		ArrayList<EmPurchaseManageVO> purchaseList = new ArrayList<EmPurchaseManageVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM "
							+ "(SELECT ROWNUM as \"prow\", PURCHASE_CODE, BUYER_ID, PURCHASE_PRODUCT_CODE, PURCHASE_DATE, PURCHASE_PRODUCT_QUANTITY, PURCHASE_PRICE, PURCHASE_TYPE,STATUS "
							+ "FROM (SELECT * FROM purchaseList ORDER BY PURCHASE_DATE DESC, PURCHASE_CODE)) "
							+ "WHERE \"prow\" >= ? AND \"prow\" <= ?");
			pstmt.setInt(1, 10 * (pageNum-1) + 1);
			pstmt.setInt(2, 10 * pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmPurchaseManageVO vo = new EmPurchaseManageVO();
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

	// ?��?�� 고객?�� 주문?��?�� �? �??�� 반환
	public int getAllProductCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// 비�?번호 체크
			pstmt = conn.prepareStatement(
					"SELECT count(*) FROM PURCHASELIST");
			
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
	
	
	public void updateStatus(String purchaseCode, String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnUtil.getConnection();

			pstmt = conn.prepareStatement("UPDATE purchaselist set status=? where PURCHASE_CODE=?");
			
			pstmt.setString(1, status);
			pstmt.setString(2, purchaseCode);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
	}
	
}
