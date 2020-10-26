package com.wj.employees.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.wj.employees.model.VO.AsVO;
import com.wj.utils.ConnUtil;

public class AsDAO {

	private static AsDAO instance = null;

	private AsDAO() {
	}

	public static AsDAO getInstance() {
		if (instance == null) {
			synchronized (AsDAO.class) {
				instance = new AsDAO();
			}
		}
		return instance;
	}

	// -------------------------------------- AS table
	// Query--------------------------------------//
	public ArrayList<AsVO> getNoticeList(int pageNum) {
		ArrayList<AsVO> arr = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM " + "(SELECT ROWNUM as A, asno, title, id, sdate FROM as1 order by asno desc) "
					+ "WHERE A >= ? AND A <= ?");

			pstmt.setInt(1, 10 * (pageNum - 1) + 1);
			pstmt.setInt(2, 10 * pageNum);
			rs = pstmt.executeQuery();
			arr = new ArrayList<>();
			while (rs.next()) {
				AsVO cn = new AsVO(); // 불러올 클라이언트 노티스
				cn.setAsNo(rs.getInt("asno"));
				cn.setTitle(rs.getString("title"));
				cn.setWriter(rs.getString("id"));
				Date d = new Date(rs.getDate("sdate").getTime());
				cn.setDate(d);
				arr.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (arr != null)
				arr = null;

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle1) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
		}
		return arr;
	}

	// 게시글 번호 가져오는 함수
	public int getNext() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String SQL = "SELECT asno FROM as1 ORDER BY asno DESC";

		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getInt(1) + 1;

			}

			return 1;// 첫 번째 게시물인 경우

		} catch (Exception e) {

			e.printStackTrace();

		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle1) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
		}

		return -1; // 데이터베이스 오류

	}

	public int write(String title, String id, String name, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO as1 VALUES(?, ?, ?, ?, ?, sysdate, ?)");

			pstmt.setInt(1, getNext());

			pstmt.setString(2, id);

			pstmt.setString(3, title);

			pstmt.setString(4, name);

			pstmt.setString(5, content);

			pstmt.setInt(6, 1);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle1) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
		}

		return 0;
	}

	public AsVO getAsOne(int i) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AsVO cn = null;
		try {
			conn = ConnUtil.getConnection();

			pstmt = conn.prepareStatement(
					"SELECT * FROM as1 where asno=?");
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			if(rs.next())  {
				cn = new AsVO(); 
				cn.setAsNo(rs.getInt("asno"));
				cn.setTitle(rs.getString("title"));
				cn.setWriter(rs.getString("id"));
				Date d = new Date(rs.getDate("sdate").getTime());
				cn.setDate(d);
				cn.setContent(rs.getString("content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return cn;
	}

}
