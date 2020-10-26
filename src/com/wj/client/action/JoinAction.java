package com.wj.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.client.model.VO.ClientVO;
import com.wj.utils.CommandAction;

public class JoinAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		ClientDAO clientDao = ClientDAO.getInstance();
		String cmd = request.getParameter("cmd");
		String id = request.getParameter("id");

		// 아이디 중복확인
		if(cmd.equals("idDoubleCheck")) {
			int result  = clientDao.idDoubleCheck(id);
			request.setAttribute("result", result);
			request.setAttribute("id", id);
			return "/client/idDoubleCheck.jsp";
		}

		// 회원가입
		if(cmd.equals("join")) {
			ClientVO vo = new ClientVO();
			vo.setId(request.getParameter("id"));
			vo.setPw(request.getParameter("pswd1"));
			vo.setName(request.getParameter("name"));
			vo.setTel(request.getParameter("tel1") + "-" + request.getParameter("secindnum") + "-" + request.getParameter("thirdnum"));
			vo.setEmail(request.getParameter("email"));
			vo.setZipcode(request.getParameter("zipcode"));
			vo.setAddress1(request.getParameter("address1"));
			vo.setAddress2(request.getParameter("address2"));
			int result = clientDao.joinToClient(vo);
			request.setAttribute("result", result);

			return "/client/joinPro.jsp";
		}

		return "/client/index.jsp";
	}
}
