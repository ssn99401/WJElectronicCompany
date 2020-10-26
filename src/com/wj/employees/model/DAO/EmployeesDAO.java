package com.wj.employees.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wj.client.model.VO.ClientVO;
import com.wj.client.model.VO.ProductVO;
import com.wj.utils.ConnUtil;

public class EmployeesDAO {
	private static EmployeesDAO instance = null;
	private EmployeesDAO() {}

	public static EmployeesDAO getInstance() {
		if(instance==null) {
			synchronized (EmployeesDAO.class) {
				instance = new EmployeesDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- Employees table Query--------------------------------------//
	
	// 濡쒓렇�씤寃곌낵 諛섑솚 硫붿꽌�뱶
		public int getLoginResult(String id, String pw) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(
						"SELECT pw FROM employees WHERE id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString("pw").equals(pw)) {
						result = 1;	// 濡쒓렇�씤 �꽦怨�
					} else {
						result = -2;  // 鍮꾨�踰덊샇 ��由�
					}
				} else {
					result = -1; // �븘�씠�뵒媛� �뾾�뒗 寃쎌슦
				}
			} catch (SQLException e) {
				e.printStackTrace();
				result = -3; // �뜲�씠�꽣踰좎씠�뒪 �삤瑜�
			} finally {
				if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
				if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
			}
			return result;
		}
		
		public ArrayList<String> getClientid () {
			ArrayList<String> allids=null;
			ClientVO client = new ClientVO();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(
						"SELECT id FROM client");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					client.setId(rs.getString("id"));
					allids.add(client.getId());
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
				if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
			}
			return allids;
		}
		
		public int getidProductCount(String id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = ConnUtil.getConnection();

				// 鍮꾨�踰덊샇 泥댄겕
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
}
