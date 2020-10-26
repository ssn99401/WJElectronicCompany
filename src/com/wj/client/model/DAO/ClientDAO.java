package com.wj.client.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wj.client.model.VO.ClientVO;
import com.wj.utils.ConnUtil;

public class ClientDAO {
	private static ClientDAO instance = null;
	private ClientDAO() {}

	public static ClientDAO getInstance() {
		if(instance==null) {
			synchronized (ClientDAO.class) {
				instance = new ClientDAO();
			}
		}
		return instance;
	}


	//-------------------------------------- Client table Query--------------------------------------//

	public int getLoginResult(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)) {
					result = 1;	
				} else {
					result = -2; 
				}
			} else {
				result = -1; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; 
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	public int idDoubleCheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = -1; 
			} else {
				result = 1; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3;
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	public int joinToClient(ClientVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO client VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
//			pstmt.setString(8, vo.getName());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
//			pstmt.setString(3, vo.getAddress2());

			if(pstmt.executeUpdate() > 0) {
				result = 1;	
			} else {
				result = -1; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3;
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	public ClientVO getClient(String id) {
		ClientVO client = new ClientVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT * FROM client WHERE id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				client.setId(id);
				client.setPw(rs.getString("pw"));
				client.setName(rs.getString("name"));
				client.setTel(rs.getString("tel"));
				client.setEmail(rs.getString("email"));
				client.setZipcode(rs.getString("zipcode"));
				client.setAddress1(rs.getString("address1"));
				client.setAddress2(rs.getString("address2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return client;
	}

	// client
	public int updateClientInfo(ClientVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// 鍮꾨�踰덊샇 泥댄겕
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id=?");
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return -10;	// �븘�씠�뵒 �삤瑜�
			} else {
				if(!rs.getString("pw").equals(vo.getPw())) {
					return -11;	// 鍮꾨�踰덊샇 �삤瑜�
				}
			}

			pstmt = conn.prepareStatement(
					"UPDATE client set name=?, tel=?, email=?, zipcode=?, address1=?, address2=? WHERE id=?");
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getZipcode());
			pstmt.setString(5, vo.getAddress1());
			pstmt.setString(6, vo.getAddress2());
			pstmt.setString(7, vo.getId());

			if(pstmt.executeUpdate() > 0) {
				result = 1;	// �젙蹂댁닔�젙 �꽦怨�
			} else {
				result = -1; // �젙蹂댁닔�젙 �떎�뙣
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

	// client 鍮꾨�踰덊샇 蹂�寃�
	public int updateClientPassword(String id, String pw, String newPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// 鍮꾨�踰덊샇 泥댄겕
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return -10;	// �븘�씠�뵒 �삤瑜�
			} else {
				if(!rs.getString("pw").equals(pw)) {
					return -11;	// 鍮꾨�踰덊샇 �삤瑜�
				}
			}

			pstmt = conn.prepareStatement(
					"UPDATE client set pw=? WHERE id=?");
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);

			if(pstmt.executeUpdate() > 0) {
				result = 1;	// �젙蹂댁닔�젙 �꽦怨�
			} else {
				result = -1; // �젙蹂댁닔�젙 �떎�뙣
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
	
	// �븘�씠�뵒 李얘린 荑쇰━ 硫붿꽌�뱶
	public String searchId(String name, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT id FROM CLIENT WHERE name = ? AND email = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			} else {
				result = "-1";	// �엯�젰�젙蹂� 遺덉씪移�
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 鍮꾨�踰덊샇 李얘린 荑쇰━ 硫붿꽌�뱶
	public int searchPassword(String id, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT pw FROM CLIENT WHERE id = ? AND email = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;	// 鍮꾨�踰덊샇 李얘린 �꽦怨�
			} else {
				result = -1;	// �엯�젰�젙蹂� 遺덉씪移�
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 鍮꾨�踰덊샇 李얘린 �엫�떆 鍮꾨�踰덊샇 ���옣
	public void updateToTemporaryPassword(String id, String tmpPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();

			pstmt = conn.prepareStatement(
					"UPDATE client set pw=? WHERE id=?");
			pstmt.setString(1, tmpPw);
			pstmt.setString(2, id);
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
