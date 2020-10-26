package com.wj.employees.model.DAO;

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


	// Î°úÍ∑∏?ù∏Í≤∞Í≥º Î∞òÌôò Î©îÏÑú?ìú
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
					result = 1;	// Î°úÍ∑∏?ù∏ ?Ñ±Í≥?
				} else {
					result = -2;  // ÎπÑÎ?Î≤àÌò∏ ??Î¶?
				}
			} else {
				result = -1; // ?ïÑ?ù¥?îîÍ∞? ?óÜ?äî Í≤ΩÏö∞
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; // ?ç∞?ù¥?Ñ∞Î≤†Ïù¥?ä§ ?ò§Î•?
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	// ?ïÑ?ù¥?îî Ï§ëÎ≥µ Ï≤¥ÌÅ¨
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
				result = -1; // ?ïÑ?ù¥?îî Ï§ëÎ≥µ
			} else {
				result = 1; // ?Ç¨?ö©Í∞??ä•?ïú ?ïÑ?ù¥?îî(Ï§ëÎ≥µX)
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; // ?ç∞?ù¥?Ñ∞Î≤†Ïù¥?ä§ ?ò§Î•?
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	// ?öå?õêÍ∞??ûÖ Î©îÏÑú?ìú
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
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());

			if(pstmt.executeUpdate() > 0) {
				result = 1;	// ?öå?õêÍ∞??ûÖ ?Ñ±Í≥?
			} else {
				result = -1; // ?öå?õêÍ∞??ûÖ ?ã§?å®
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; // ?ç∞?ù¥?Ñ∞Î≤†Ïù¥?ä§ ?ò§Î•?
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	// ÎßàÏù¥?éò?ù¥Ïß?Ï∞? ?öå?õê?†ïÎ≥? Î∞òÌôò Î©îÏÑú?ìú
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

	// client ?†ïÎ≥? Î≥?Í≤?
	public int updateClientInfo(ClientVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// ÎπÑÎ?Î≤àÌò∏ Ï≤¥ÌÅ¨
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id=?");
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return -10;	// ?ïÑ?ù¥?îî ?ò§Î•?
			} else {
				if(!rs.getString("pw").equals(vo.getPw())) {
					return -11;	// ÎπÑÎ?Î≤àÌò∏ ?ò§Î•?
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
				result = 1;	// ?†ïÎ≥¥Ïàò?†ï ?Ñ±Í≥?
			} else {
				result = -1; // ?†ïÎ≥¥Ïàò?†ï ?ã§?å®
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; // ?ç∞?ù¥?Ñ∞Î≤†Ïù¥?ä§ ?ò§Î•?
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}

	// client ÎπÑÎ?Î≤àÌò∏ Î≥?Í≤?
	public int updateClientPassword(String id, String pw, String newPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnUtil.getConnection();

			// ÎπÑÎ?Î≤àÌò∏ Ï≤¥ÌÅ¨
			pstmt = conn.prepareStatement(
					"SELECT pw FROM client WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return -10;	// ?ïÑ?ù¥?îî ?ò§Î•?
			} else {
				if(!rs.getString("pw").equals(pw)) {
					return -11;	// ÎπÑÎ?Î≤àÌò∏ ?ò§Î•?
				}
			}

			pstmt = conn.prepareStatement(
					"UPDATE client set pw=? WHERE id=?");
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);

			if(pstmt.executeUpdate() > 0) {
				result = 1;	// ?†ïÎ≥¥Ïàò?†ï ?Ñ±Í≥?
			} else {
				result = -1; // ?†ïÎ≥¥Ïàò?†ï ?ã§?å®
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -3; // ?ç∞?ù¥?Ñ∞Î≤†Ïù¥?ä§ ?ò§Î•?
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException sqle1) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException sqle1) {}
			if(conn!=null) try {conn.close();} catch(SQLException sqle1) {}
		}
		return result;
	}
	
	// ?ïÑ?ù¥?îî Ï∞æÍ∏∞ ÏøºÎ¶¨ Î©îÏÑú?ìú
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
				result = "-1";	// ?ûÖ?†•?†ïÎ≥? Î∂àÏùºÏπ?
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// ÎπÑÎ?Î≤àÌò∏ Ï∞æÍ∏∞ ÏøºÎ¶¨ Î©îÏÑú?ìú
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
				result = 1;	// ÎπÑÎ?Î≤àÌò∏ Ï∞æÍ∏∞ ?Ñ±Í≥?
			} else {
				result = -1;	// ?ûÖ?†•?†ïÎ≥? Î∂àÏùºÏπ?
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// ÎπÑÎ?Î≤àÌò∏ Ï∞æÍ∏∞ ?ûÑ?ãú ÎπÑÎ?Î≤àÌò∏ ???û•
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
