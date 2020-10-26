package com.wj.employees.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.wj.client.model.VO.AsVO;
import com.wj.client.model.VO.ClientNoticeVO;
import com.wj.employees.model.VO.EmployeesNoticeVO;
import com.wj.employees.model.VO.EmployeesVO;
import com.wj.utils.ConnUtil;

public class EmployeesNoticeDAO {
	private static EmployeesNoticeDAO instance = null;
	private EmployeesNoticeDAO() {}

	public static EmployeesNoticeDAO getInstance() {
		if(instance==null) {
			synchronized (EmployeesNoticeDAO.class) {
				instance = new EmployeesNoticeDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- EmployeesNotice table Query--------------------------------------//
	
	public ArrayList<EmployeesNoticeVO> getNoticeList(int pageNum) {
		ArrayList<EmployeesNoticeVO> arr = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM "
					+ "(SELECT ROWNUM as A, clno, title, sdate FROM Cli_Notice order by clno desc) "
					+ "WHERE A >= ? AND A <= ?");
			
			pstmt.setInt(1, 10 * (pageNum-1) + 1);
			pstmt.setInt(2, 10 * pageNum);
			rs = pstmt.executeQuery();
			arr = new ArrayList<>();
			while(rs.next()) {
				EmployeesNoticeVO cn = new EmployeesNoticeVO(); // 불러올 클라이언트 노티스
				cn.setClno(rs.getString("clno"));
				cn.setTitle(rs.getString("title"));
				Date d = new Date(rs.getDate("sdate").getTime());
				cn.setSdate(d);
				arr.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if(arr!=null) arr = null;
			
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return arr;
	}
	
	//게시물 총 몇개냐?
	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			pstmt = conn.prepareStatement(
					"SELECT count(*) FROM cli_Notice");
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

	//게시물 하나 불러오기
	public EmployeesNoticeVO getNoticeOne(int i) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeesNoticeVO cn = null;
		try {
			conn = ConnUtil.getConnection();

			pstmt = conn.prepareStatement(
					"SELECT * FROM cli_Notice where clno=?");
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			if(rs.next())  {
				cn = new EmployeesNoticeVO(); // 불러올 클라이언트 노티스
				cn.setClno(rs.getString("clno"));
				cn.setTitle(rs.getString("title"));
				Date d = new Date(rs.getDate("sdate").getTime());
				cn.setSdate(d);
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
	
	public int getNext() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select clno from Cli_Notice order by clno desc";

		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getInt(1) + 1;

			}

			return 1;

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

		return -1; 

	}
	
	public int write(String id,String title, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into Cli_Notice(clno,id,title,content) values(?,?,?,?)";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, id);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
}
	
